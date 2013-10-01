/*                               
    _/_/_/                          _/_/                _/      
   _/    _/    _/_/      _/_/_/  _/    _/  _/_/_/    _/_/_/_/   
  _/    _/  _/    _/  _/    _/  _/    _/  _/    _/    _/        
 _/    _/  _/    _/  _/    _/  _/    _/  _/    _/    _/         
_/_/_/      _/_/      _/_/_/    _/_/    _/    _/      _/_/      
                         _/                                     
                    _/_/                                DogOnt + Dog -> Semantic House Model

WEBSITE: http://elite.polito.it/dog-tools-80

Copyright (c) [2009] 
[Dario Bonino (dario.bonino@polito.it), Politecnico di Torino] 
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and limitations under the License. 

 */
package it.polito.elite.dog.core.housemodel.semantic;

import it.polito.elite.dog.core.housemodel.api.HouseModel;
import it.polito.elite.dog.core.housemodel.semantic.api.DogModelProviderInterface;
import it.polito.elite.dog.core.housemodel.semantic.loader.ThreadedDeviceAdder;
import it.polito.elite.dog.core.housemodel.semantic.loader.ThreadedDeviceRemover;
import it.polito.elite.dog.core.housemodel.semantic.loader.ThreadedModelLoader;
import it.polito.elite.dog.core.housemodel.semantic.loader.ThreadedModelLoader.LoadingModes;
import it.polito.elite.dog.core.housemodel.semantic.loader.ThreadedModelLoader.ModelTypes;
import it.polito.elite.dog.core.housemodel.semantic.query.SPARQLQueryWrapper;
import it.polito.elite.dog.core.housemodel.semantic.util.DogOnt2XMLDog;
import it.polito.elite.dog.core.housemodel.semantic.util.OntologyDescriptorSet;
import it.polito.elite.dog.core.housemodel.api.ModelUpdate;
import it.polito.elite.dog.core.library.jaxb.DogHomeConfiguration;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import it.polito.elite.dog.core.library.model.notification.core.AddedNewDeviceNotification;
import it.polito.elite.dog.core.library.model.notification.core.RemovedDeviceNotification;
import it.polito.elite.dog.core.library.model.notification.core.UpdatedModelNotification;
import it.polito.elite.dog.core.library.util.ElementDescription;
import it.polito.elite.dog.core.library.util.EventFactory;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import com.hp.hpl.jena.ontology.OntModel;

/**
 * <p>
 * The Semantic House Model bundle manages a DogOnt-based house model inside the
 * Dog2.0 gateway. <br/>
 * It offers average-level semantic capabilities for querying both the DogOnt
 * main schema and the house-specific instance run by Dog. It complies with the
 * HouseModel DeviceCategory by responding to the getConfiguration messages.
 * </p>
 * 
 * <p>
 * In addition it is designed to support more advanced query functionalities
 * such as query by location, by functionality, by energy profile, etc.
 * 
 * These advanced functionalities are implemented as further extensions of the
 * basic HouseModel interface...
 * </p>
 * 
 * TODO: evaluate how to integrated these new functionalities, at 06/2010 only
 * the HouseModel interface is supported
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * 
 */
public class SemanticHouseModel implements HouseModel, DogModelProviderInterface, ModelUpdate,
		ManagedService, ServiceTrackerCustomizer<Object, Object>
{
	// the bundle intelligence level
	public static final String OWNINTELLIGENCE = "DOGONT";
	
	// the private reference to the service registration object
	private ServiceRegistration<?> serReg;
	private ServiceRegistration<?> serRegModelProvider;
	private ServiceRegistration<?> serRegModelUpdaterProvider;
	
	private Hashtable<String, Object> registrationPropertiesHC;
	private Hashtable<String, Object> registrationPropertiesMP;
	private Hashtable<String, Object> registrationPropertiesMUP;
	
	// queue flag, if true the message decoding queue is running...
	protected volatile boolean run;
	
	// the internal reference to the context
	protected BundleContext context;
	
	// the dogont instance svg footprint, initially empty
	private String homeSVGFootprint = "no map available";
	
	// the current Jena model used by the bundle
	protected OntologyDescriptorSet ontoDescSet;
	protected OntModel dogontModel;
	protected OntModel plainDogOntModel;
	
	// the map of currently added models (TODO: check if this way of managing
	// models wors....)
	private HashMap<String, OntModel> modelsInUse;
	private HashMap<String, Map<String, String>> namespacesInUse;
	
	// the internal namespace map
	private Map<String, String> namespaces;
	
	// the entry point namespace for the current base model
	private String entryPoint;
	
	// the query wrapper used to wrap SPARQL queries on to method calls
	private SPARQLQueryWrapper qWrapper;
	
	protected LogHelper logger;
	// ServiceTracker for the EventAdmin service
	private ServiceTracker<?, ?> trackerEventAdmin;
	
	// to export xml configuration to external all (REST); useless, right now
	@SuppressWarnings("unused")
	private DogHomeConfiguration xmlConfiguration;
	
	/**
	 * The Semantic House Model constructor, here the semantic model is
	 * instantiated and all the necessary helpers are created, allowing other
	 * Dog bundles to exploit the semantics-rich services offered by this
	 * enhanced house model bundle
	 * 
	 * @param context
	 *            The bundle context object linking this bundle with the OSGi
	 *            container in which it runs
	 */
	public SemanticHouseModel(BundleContext context)
	{
		// store the context
		this.context = context;
		
		// register the offered services (as quickly as possible...)
		this.registerDriver();
		
		// init the logger
		this.logger = new LogHelper(this.context);
		
		// create and open a ServiceTracker for the EventAdmin service
		this.trackerEventAdmin = new ServiceTracker<Object, Object>(context, EventAdmin.class.getName(), null);
		this.trackerEventAdmin.open();
		
		// register the offered services
		this.registerServiceFactory();
		
		// prepare the maps for handling modular ontology models
		this.modelsInUse = new HashMap<String, OntModel>();
		this.namespacesInUse = new HashMap<String, Map<String, String>>();
		
	}
	
	/**
     * 
     */
	@Override
	public void updated(Dictionary<String, ?> properties)
	{
		
		if (properties != null)
		{
			String ontologyFileName = (String) properties.get(DeviceCostants.ONTOLOGY);
			String svgFileName = (String) properties.get(DeviceCostants.SVGPLAN);
			
			if (ontologyFileName != null && !ontologyFileName.isEmpty())
			{
				// log the update data received
				this.logger.log(LogService.LOG_INFO, "received ontology configuration...");
				
				// get the XMLString representing the model set to load
				String ontoDescSetAsString = this.filetoString(ontologyFileName);
				
				// parse the set
				this.ontoDescSet = OntologyDescriptorSet.parse(ontoDescSetAsString);
				
				if ((ontoDescSet != null) && (!ontoDescSet.isEmpty()))
				{
					// create a new loader Thread pointing at the bundle context
					ThreadedModelLoader loader = new ThreadedModelLoader(this);
					loader.setModelToLoad(ontoDescSet, LoadingModes.LOAD_REPLACE, ModelTypes.REASONED_WITH_PELLET);
					
					// start loading data...
					loader.start();
				}
			}
			
			if (svgFileName != null && !svgFileName.isEmpty())
			{
				// loads the svg plan in the same way of the simple house model
				this.homeSVGFootprint = this.filetoString(svgFileName);
			}
			
		}
	}
	
	/***
	 * Register the SemanticHouseModel driver to listen configuration data
	 */
	private void registerServiceFactory()
	{
		// create a properties object with services id value
		Hashtable<String, Object> serviceFactoryProp = new Hashtable<String, Object>();
		
		// prepare the bundle properties for registration
		serviceFactoryProp.put(org.osgi.framework.Constants.SERVICE_PID, this.context.getBundle().getSymbolicName());
		
		// registers the service offered by this bundle
		this.context.registerService(ManagedService.class.getName(), this, serviceFactoryProp);
		
	}
	
	/**
	 * When called it unregisters the SemanticHouseModel service from the OSGi
	 * framework
	 */
	public void unRegisterServices()
	{
		// stop the message decoding queue
		this.run = false;
		
		// unregister the service
		if (this.serReg != null)
		{
			// unregister
			this.serReg.unregister();
			
			// free the object
			this.serReg = null;
			
		}
		
		// unregister the model provider service... (right ?!?!?!?)
		if (this.serRegModelProvider != null)
		{
			this.serRegModelProvider.unregister();
			
			this.serRegModelProvider = null;
		}
	}
	
	/**
	 * Register the Semantic House Model services as existing but not active....
	 * they will become active when the ontology model has been fully loaded...
	 */
	private void registerDriver()
	{
		// register as a not active house configuration provider
		this.registrationPropertiesHC = new Hashtable<String, Object>();
		
		// the intelligence
		this.registrationPropertiesHC.put(DeviceCostants.INTELLIGENCE, "DOGONT");
		// not active
		this.registrationPropertiesHC.put(DeviceCostants.ACTIVE, false);
		
		// register the house configuration provider
		this.serReg = this.context.registerService(HouseModel.class.getName(), this, this.registrationPropertiesHC);
		
		// register the model provider
		this.registrationPropertiesMP = new Hashtable<String, Object>();
		
		// the intelligence
		this.registrationPropertiesMP.put(DeviceCostants.INTELLIGENCE, "DOGONT");
		// not active
		this.registrationPropertiesMP.put(DeviceCostants.ACTIVE, false);
		
		this.serRegModelProvider = this.context.registerService(DogModelProviderInterface.class.getName(), this,
				this.registrationPropertiesMP);
		
		// register the model update provider
		this.registrationPropertiesMUP = new Hashtable<String, Object>();
		
		// the intelligence
		this.registrationPropertiesMUP.put(DeviceCostants.INTELLIGENCE, "DOGONT");
		// not active
		this.registrationPropertiesMUP.put(DeviceCostants.ACTIVE, false);
		
		this.serRegModelUpdaterProvider = this.context.registerService(ModelUpdate.class.getName(), this,
				this.registrationPropertiesMUP);
		
	}
	
	/**
	 * Register the Semantic House Model driver (service) as ACTIVE
	 */
	protected void activateDriver()
	{
		// update the active status
		this.registrationPropertiesHC.put(DeviceCostants.ACTIVE, true);
		this.registrationPropertiesMP.put(DeviceCostants.ACTIVE, true);
		this.registrationPropertiesMUP.put(DeviceCostants.ACTIVE, true);
		
		// register driver service
		this.serReg.setProperties(this.registrationPropertiesHC);
		this.serRegModelProvider.setProperties(this.registrationPropertiesMP);
		this.serRegModelUpdaterProvider.setProperties(this.registrationPropertiesMUP);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.util.tracker.ServiceTrackerCustomizer#addingService(org.osgi
	 * .framework.ServiceReference)
	 */
	@Override
	public Object addingService(ServiceReference<Object> reference)
	{
		// intentionally left empty
		
		return reference;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.util.tracker.ServiceTrackerCustomizer#modifiedService(org.osgi
	 * .framework.ServiceReference, java.lang.Object)
	 */
	@Override
	public void modifiedService(ServiceReference<Object> reference, Object service)
	{
		// empty method
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.util.tracker.ServiceTrackerCustomizer#removedService(org.osgi
	 * .framework.ServiceReference, java.lang.Object)
	 */
	@Override
	public void removedService(ServiceReference<Object> reference, Object service)
	{
		// empty method
		
	}
	
	public BundleContext getContext()
	{
		return context;
	}
	
	/**
	 * @return the namespaces
	 */
	public synchronized Map<String, String> getNamespaces()
	{
		return namespaces;
	}
	
	/**
	 * @return the qWrapper
	 */
	public synchronized SPARQLQueryWrapper getQWrapper()
	{
		return qWrapper;
	}
	
	/**
	 * @return the entryPoint
	 */
	public synchronized String getEntryPoint()
	{
		return entryPoint;
	}
	
	/**
	 * When called, stores the given Jena OntModel in the instance variable
	 * named dogontModel and if the driver is still not registered with the
	 * framework registers the driver as available...
	 * 
	 * @param model
	 *            the model on which the driver will work
	 */
	public synchronized void setModel(OntModel plainModel, OntModel reasonedModel, Map<String, String> namespace,
			String entrypoint)
	{
		this.dogontModel = reasonedModel;
		this.plainDogOntModel = plainModel;
		this.namespaces = namespace;
		this.entryPoint = entrypoint;
		
		// create the query wrapper object
		this.qWrapper = new SPARQLQueryWrapper(this.getNamespaces(), this.dogontModel);
		
		this.logger.log(LogService.LOG_INFO, "Loaded the dogont ontology");
		
		// extract the xml-representation needed to answer queries from external
		// applications
		
		Runnable XMLConfigWorker = new Runnable() {
			
			@Override
			public void run()
			{
				/*
				 * PelletInfGraph infG = ((PelletInfGraph)
				 * dogontModel.getGraph()); while (!infG.isRealized()) { try {
				 * logger.log(LogService.LOG_DEBUG,
				 * "[SemanticHouseModel]: sleeping..."); Thread.sleep(500); }
				 * catch (InterruptedException e1) { // TODO Auto-generated
				 * catch block e1.printStackTrace(); } }
				 */
				
				try
				{
					logger.log(LogService.LOG_DEBUG, "[SemanticHouseModel]: computing JAXBXML configuration...");
					DogOnt2XMLDog toXMLTranslator = new DogOnt2XMLDog(ontoDescSet);
					xmlConfiguration = toXMLTranslator.getJAXBXMLDog();
					logger.log(LogService.LOG_DEBUG, "[SemanticHouseModel]: generated JAXBXML configuration.");
				}
				catch (Exception e)
				{
					logger.log(LogService.LOG_ERROR,
							"[SemanticHouseModel]: Error while translating the ontology to JAXBXML" + e);
				}
			}
		};
		
		// register the service if not already active...
		if ((this.serReg != null) && (!(Boolean) this.registrationPropertiesHC.get(DeviceCostants.ACTIVE))
				&& (this.serRegModelProvider != null)
				&& (!(Boolean) this.registrationPropertiesMP.get(DeviceCostants.ACTIVE)))
			this.activateDriver();
		
		// start the thread worker
		Thread threadXMLConfigWorker = new Thread(XMLConfigWorker);
		threadXMLConfigWorker.start();
	}
	
	/**
	 * 
	 * @param model
	 * @param namespaces
	 */
	public synchronized void addModel(OntModel model, Map<String, String> namespaces, String entryPoint)
	{
		// really experimental implementation
		// TODO: check if this way of working is correct and efficient... (may
		// be not)
		System.err.println("Entry Point " + entryPoint);
		// ok first let's store a reference to the model in the modelsInUse
		this.modelsInUse.put(entryPoint, model);
		
		// second, add the model as a sub model of the current one (do not know
		// if it works)
		this.plainDogOntModel.addSubModel(model);
		this.dogontModel.addSubModel(model); // Adding model to the reason model
		
		// third, add the new namespaces and keep track of them...
		this.namespacesInUse.put(entryPoint, namespaces);
		this.getNamespaces().putAll(namespaces);
	}
	
	/*********************************************************************************
	 * 
	 * Methods for supporting the - HouseConfigurationProvider - Interface
	 * 
	 ********************************************************************************/
	
	/************ Implementations of the required helper methods ****************/
	
	/**
	 * Load a file in memory.
	 * 
	 * @param propFile
	 */
	private String filetoString(String fileName)
	{
		FileInputStream inputStream;
		StringBuffer buffer = null;
		try
		{
			inputStream = new FileInputStream(System.getProperty("configFolder") + "/" + fileName);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			buffer = new StringBuffer();
			
			while ((line = reader.readLine()) != null)
			{
				buffer.append(line);
				
			}
			reader.close();
		}
		catch (IOException e)
		{
			
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	/**
	 * <p>
	 * Gets the device configurations in the property format defined by dog and
	 * obtained through the {@link DogDeviceDescription} object (by calling the
	 * {@link asDogDeviceConfigurationP()) method. Conditions might be given
	 * restricting the set of devices for which configurations must be provided
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * back. They are given as an {@link Hashtable} of maximum 2 {@link HashSet}
	 * <{@link String}>. This sets are respectively referenced by the keys:
	 * {@link DogDeviceConstants.DEVICEURI} and
	 * {@link DogDeviceConstants.DEVICE_CATEGORY}.
	 * </p>
	 * <p>
	 * The first set includes a detailed list of devices (URIs) that must be
	 * included in the extracted configurations, the second, instead, specifies
	 * a list of device categories (descending from dogOnt:Controllable) whose
	 * instances must be included in the extracted configurations. If both sets
	 * are present at the same time, the set union between the two conditions is
	 * performed returning the configurations of all devices listed in the first
	 * set and of all the instances of the categories reported in the second
	 * set, avoiding duplication.
	 * 
	 * @param condition
	 *            The conditions
	 * @return The device configurations in the DOG property format
	 */
	private Vector<DeviceDescriptor> getDeviceConfig(Hashtable<String, HashSet<String>> condition)
	{
		// prepare the device description object
		Vector<DeviceDescriptor> configs = new Vector<DeviceDescriptor>();
		DeviceDescriptor devDesc;
		ElementDescription notificationDescription;
		ElementDescription commandDescription;
		
		// the device list
		Set<String> devList = new HashSet<String>();
		
		// check the condition
		if ((condition != null) && (!condition.isEmpty()))
		{
			// check the directly specified URIs
			Set<String> conditions = condition.get(DeviceCostants.DEVICEURI);
			
			if (conditions != null)
			{
				for (String cCond : conditions)
				{
					cCond = this.qWrapper.checkAndRepairDogOntNameSpace(cCond);
					devList.add(cCond);
				}
			}
			
			// check the device categories
			conditions = condition.get(DeviceCostants.DEVICE_CATEGORY);
			
			// if not null, must extract the set of classes descending from the
			// given
			// device categories
			if (conditions != null)
			{
				Set<String> devices = this.qWrapper.getCategoryFilteredControllableInstances(conditions);
				
				// load all to the device list
				devList.addAll(devices);
			}
		}
		else
		{
			// get all controllables...
			devList.addAll(this.qWrapper.getAllControllableInstances());
		}
		
		// ok here there should be a non empty list of devices from which
		// getting
		// the low level information needed to answer the get config request
		Map<String, String> basicData;
		String location;
		Set<String> propDevices = null;
		Set<String> propCmds = null;
		Set<String> propNotifs = null;
		String gateway = null;
		
		for (String devURI : devList)
		{
			// get basic device data
			basicData = this.qWrapper.getControllableInstanceBasicData(devURI);
			
			// the basic device descriptor
			devDesc = new DeviceDescriptor(basicData.get(DeviceCostants.DEVICEURI),
					basicData.get(DeviceCostants.TYPE), basicData.get(DeviceCostants.DEVICE_DESCRIPTION),
					basicData.get(DeviceCostants.MANUFACTURER));
			
			// the location
			location = this.qWrapper.getEntityLocationInRoom(devURI);
			
			// the gateway
			gateway = this.qWrapper.getHasGateway(devURI);
			
			// check not null
			if ((gateway != null) && (!gateway.isEmpty()))
				devDesc.setGateway(gateway);
			
			// actuatorOf
			devDesc.setActuatorOf(this.qWrapper.getActuatorOf(devURI));
			
			// sensorOf
			devDesc.setSensorOf(this.qWrapper.getSensorOf(devURI));
			
			// controlled objects
			Set<String> controlledObjects = this.qWrapper.getControlledObjects(devURI);
			if ((controlledObjects != null) && (!controlledObjects.isEmpty()))
				devDesc.setControlledObjects(controlledObjects);
			
			// hasMeter
			devDesc.setHasMeter(this.qWrapper.getHasMeter(devURI));
			
			// meter of
			Set<String> meteredObjects = this.qWrapper.getMeterOf(devURI);
			if ((meteredObjects != null) && (!meteredObjects.isEmpty()))
				devDesc.setMeterOf(meteredObjects);
			
			// plugged in
			devDesc.setPluggedIn(this.qWrapper.getPluggedIn(devURI));
			
			// add the location
			devDesc.setDevLocation(location);
			
			// check manufacturer null
			if (devDesc.getDevTechnology() == null)
			{
				// shall set the manufacturer to e-lite, no specific parameter
				// needed here...
				devDesc.setDevTechnology("ELITE");
			}
			else
			{
				
				if (propDevices == null)
					propDevices = this.qWrapper.getDatatypePropertiesOf("dogOnt:DomoticNetworkComponent");
				
				// get all the network-specific parameters for this device
				// instance
				Map<String, Set<String>> netData = this.qWrapper.getInstanceProperties(devURI, propDevices);
				
				// store the data in the descriptor
				if ((netData != null) && (!netData.isEmpty()))
				{
					devDesc.setDevSimpleConfigurationParams(netData);
				}
				
				// get all the network commands
				Set<String> cmds = this.qWrapper.getDeviceInstanceNetworkSpecificCommands(devURI);
				
				// check null
				if ((cmds != null) && (!cmds.isEmpty()))
				{
					// for each command get the network specific parameters
					for (String cmd : cmds)
					{
						if (propCmds == null)
							propCmds = this.qWrapper.getDatatypePropertiesOf("dogOnt:NetworkSpecificCommand");
						
						// get the command-specific network data...
						netData = this.qWrapper.getInstanceProperties(cmd, propCmds);
						
						// if net data is not null or empty...add it to the
						// command description and
						// then to the device descriptor
						if ((netData != null) && (!netData.isEmpty()))
						{
							// create the command description object
							String cmdType = this.qWrapper.getType(cmd, this.getNamespaces().get("dogOnt")
									+ "#NetworkSpecificCommand");
							
							commandDescription = new ElementDescription(cmd, cmdType);
							
							// get all the network parameters...
							Map<String, Set<String>> cmdParams = this.qWrapper.getInstanceProperties(cmd, propCmds);
							
							// add the params... shall never be more than
							// one..however also the multiple case is supported
							// here
							Set<String> values;
							for (String paramName : cmdParams.keySet())
							{
								values = cmdParams.get(paramName);
								if ((values != null) && (values.size() == 1))
									commandDescription.addElementParam(paramName, values.iterator().next());
								else
									commandDescription.addElementParam(paramName, values.toString());
							}
							
							// add the command description
							devDesc.addDevCommandSpecificParam(commandDescription);
						}
					}
				}
				
				// get all the network notifications
				Set<String> notifications = this.qWrapper.getDeviceInstanceNetworkSpecificNotifications(devURI);
				
				// check null
				if ((notifications != null) && (!notifications.isEmpty()))
				{
					// for each command get the network specific parameters
					for (String notification : notifications)
					{
						if (propNotifs == null)
							propNotifs = this.qWrapper.getDatatypePropertiesOf("dogOnt:Notification");
						
						// get the command-specific network data...
						netData = this.qWrapper.getInstanceProperties(notification, propNotifs);
						
						// if net data is not null or empty...add it to the
						// command description and
						// then to the device descriptor
						if ((netData != null) && (!netData.isEmpty()))
						{
							// create the command description object
							String notificationType = this.qWrapper.getType(notification,
									this.getNamespaces().get("dogOnt") + "#NetworkSpecificNotification");
							
							notificationDescription = new ElementDescription(notification, notificationType);
							
							// get all the network parameters...
							Map<String, Set<String>> notificationParams = this.qWrapper
									.getInstanceLevelNotificationNetworkParameters(notification); // getInstanceProperties(notification,
																									// propNotifs);
							notificationParams.putAll(this.qWrapper
									.getInstanceLevelNotificationParameters(notification));
							// add the params... shall never be more than
							// one..however also the multiple case is supported
							// here
							Set<String> values;
							for (String paramName : notificationParams.keySet())
							{
								values = notificationParams.get(paramName);
								if ((values != null) && (values.size() == 1))
									notificationDescription.addElementParam(paramName, values.iterator().next());
								else
									notificationDescription.addElementParam(paramName, values.toString());
							}
							
							// add the command description
							devDesc.addDevNotificationSpecificParam(notificationDescription);
						}
					}
				}
			}
			
			// add the device description as a property concatenation
			configs.add(devDesc);// .asDogDeviceConfigurationP());
		}
		
		return configs;
	}
	
	/*********************************************************************************
	 * 
	 * Methods for supporting the - ModelProvider - Interface
	 * 
	 ********************************************************************************/
	// all requests must be asynchronous and therefore they are enqueued in the
	// general message queue of the semantic house model bundle
	@Override
	public OntModel getModel()
	{
		return this.dogontModel;
	}
	
	@Override
	public void loadAndMerge(OntologyDescriptorSet setToLoad)
	{
		this.loadAndMergeImpl(setToLoad);
	}
	
	@Override
	public void remove(Set<String> modelsToRemove)
	{
		this.removeImpl(modelsToRemove);
	}
	
	/************ Implementations of the above methods ****************/
	
	private void loadAndMergeImpl(OntologyDescriptorSet setToLoad)
	{
		// call a threaded model loader in merge mode
		ThreadedModelLoader loader = new ThreadedModelLoader(this);
		loader.setModelToLoad(setToLoad, LoadingModes.LOAD_MERGE, ModelTypes.PLAIN_IN_MEMORY); // to
																								// check
																								// if
																								// plain
																								// is
																								// sufficient
		
		// start loading data...
		loader.start();
	}
	
	// TODO: if too intensive it must be moved to a separated worker thread...
	private synchronized void removeImpl(Set<String> modelsToRemove)
	{
		OntModel cOntModel;
		Map<String, String> cNamespaces;
		
		// first remove the model if in Use
		for (String cModel : modelsToRemove)
		{
			System.err.println(cModel);
			// get the corresponding OntModel
			cOntModel = this.modelsInUse.get(cModel);
			
			// if it exists, remove it from the currently used model
			if (cOntModel != null)
			{
				this.dogontModel.remove(cOntModel);
				// this.dogontModel.removeSubModel(cOntModel);
				this.dogontModel.write(System.out);
			}
			
			// remove the model
			this.modelsInUse.remove(cModel);
			
			// get the namespaces associated to this model and
			cNamespaces = this.namespacesInUse.get(cModel);
			
			// remove them from the list of used namespaces
			if (cNamespaces != null)
			{
				for (String namespace : cNamespaces.keySet())
				{
					this.getNamespaces().remove(namespace);
				}
			}
			
			// remove the namespace entry
			this.namespacesInUse.remove(cModel);
			
		}
		
		// restore the list of still valid namespaces
		for (Map<String, String> oNamespaces : this.namespacesInUse.values())
		{
			this.getNamespaces().putAll(oNamespaces);
		}
		
		this.logger.log(LogService.LOG_INFO, "Loading the Reset Model");
		
	}
	
	/*********************************************************************************
	 * 
	 * Methods for supporting the - ModelUpdater - Interface
	 * 
	 ********************************************************************************/
	
	@Override
	public void addNewDevice(DeviceDescriptor deviceDescriptor)
	{
		this.addNewDeviceImpl(deviceDescriptor);
	}
	
	@Override
	public void removeDevice(String deviceURI)
	{
		this.removeDeviceImpl(deviceURI);
	}
	
	@Override
	public void setDeviceLocation(String deviceURI, String deviceLocation)
	{
		this.setDeviceLocationImpl(deviceURI, deviceLocation);
	}
	
	/************ Implementations of the above methods ****************/
	
	private void addNewDeviceImpl(DeviceDescriptor deviceDescriptor)
	{
		// log the addition request
		this.logger.log(LogService.LOG_INFO, "Received request to add: " + deviceDescriptor.getDevURI());
		
		// create and launch the device addition thread
		ThreadedDeviceAdder devAdder = new ThreadedDeviceAdder(deviceDescriptor, this.dogontModel,
				this.plainDogOntModel, this);
		devAdder.run();
	}
	
	private void removeDeviceImpl(String deviceURI)
	{
		// TODO implement this method.... now just log the message
		this.logger.log(LogService.LOG_INFO, "Received request to remove: " + deviceURI);
		
		// create and launch the device removal thread
		ThreadedDeviceRemover devRemover = new ThreadedDeviceRemover(deviceURI, this.dogontModel,
				this.plainDogOntModel, this);
		devRemover.start();
	}
	
	private void setDeviceLocationImpl(String deviceURI, String deviceLocation)
	{
		// TODO implement this method.... now just log the message
		this.logger.log(LogService.LOG_INFO, "Received request to move " + deviceURI + " to " + deviceLocation);
		
	}
	
	/*********************************************************************************
	 * 
	 * Methods for supporting the - EventAdmin - notification
	 * 
	 ********************************************************************************/
	
	public synchronized void notifyModelUpdated()
	{
		// get a pointer to the EventAdmin service
		EventAdmin ea = (EventAdmin) this.trackerEventAdmin.getService();
		
		// if the EventAdmin is available
		if (ea != null)
		{
			// Create the notification
			UpdatedModelNotification notification = new UpdatedModelNotification();
			// Create the event
			Event event = EventFactory.createEvent(notification, this.getClass().getSimpleName());
			// Send the event to the EventAdmin
			ea.postEvent(event);
		}
		
	}
	
	public synchronized void notifyAddedNewDeviced(DeviceDescriptor desc)
	{
		// get a pointer to the EventAdmin service
		EventAdmin ea = (EventAdmin) this.trackerEventAdmin.getService();
		
		// if the EventAdmin is available
		if (ea != null)
		{
			// Create the notification
			AddedNewDeviceNotification notification = new AddedNewDeviceNotification(desc);
			// Create the event
			Event event = EventFactory.createEvent(notification, this.getClass().getSimpleName());
			// Send the event to the EventAdmin
			ea.postEvent(event);
		}
		
		// send also a generic update notification
		this.notifyModelUpdated();
		
	}
	
	public void notifyRemovedDevice(DeviceDescriptor desc)
	{
		// get a pointer to the EventAdmin service
		EventAdmin ea = (EventAdmin) this.trackerEventAdmin.getService();
		
		// if the EventAdmin is available
		if (ea != null)
		{
			// Create the notification
			RemovedDeviceNotification notification = new RemovedDeviceNotification(desc);
			// Create the event
			Event event = EventFactory.createEvent(notification, this.getClass().getSimpleName());
			// Send the event to the EventAdmin
			ea.postEvent(event);
		}
		
		// send also a generic update notification
		this.notifyModelUpdated();
		
	}
	
	@Override
	public Vector<DeviceDescriptor> getConfiguration()
	{
		return this.getDeviceConfig(null);
	}

	@Override
	public String getSVGPlan()
	{
		return this.homeSVGFootprint;
	}
	
}
