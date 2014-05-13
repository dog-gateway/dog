/*
 * Dog - Core
 * 
 * Copyright (c) 2009-2014 Dario Bonino, Luigi De Russis and Emiliano Castellina
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package it.polito.elite.dog.core.housemodel.semantic;

import it.polito.elite.dog.core.housemodel.api.HouseModel;
import it.polito.elite.dog.core.housemodel.semantic.api.OntologyModel;
import it.polito.elite.dog.core.housemodel.semantic.loader.ThreadedDeviceAdder;
import it.polito.elite.dog.core.housemodel.semantic.loader.ThreadedDeviceRemover;
import it.polito.elite.dog.core.housemodel.semantic.loader.ThreadedModelLoader;
import it.polito.elite.dog.core.housemodel.semantic.loader.ThreadedModelLoader.LoadingModes;
import it.polito.elite.dog.core.housemodel.semantic.loader.ThreadedModelLoader.ModelTypes;
import it.polito.elite.dog.core.housemodel.semantic.query.SPARQLQueryWrapper;
import it.polito.elite.dog.core.housemodel.semantic.util.DogOnt2XMLDog;
import it.polito.elite.dog.core.library.jaxb.Configcommand;
import it.polito.elite.dog.core.library.jaxb.Confignotification;
import it.polito.elite.dog.core.library.jaxb.Configparam;
import it.polito.elite.dog.core.library.jaxb.Configstate;
import it.polito.elite.dog.core.library.jaxb.ControlFunctionality;
import it.polito.elite.dog.core.library.jaxb.Controllables;
import it.polito.elite.dog.core.library.jaxb.Device;
import it.polito.elite.dog.core.library.jaxb.DogHomeConfiguration;
import it.polito.elite.dog.core.library.jaxb.NotificationFunctionality;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import it.polito.elite.dog.core.library.semantic.util.OntologyDescriptorSet;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.log.LogService;

import com.hp.hpl.jena.ontology.OntModel;

/**
 * <p>
 * The Semantic House Model bundle manages a DogOnt-based house model inside the
 * Dog gateway. <br/>
 * It offers average-level semantic capabilities for querying both the DogOnt
 * main schema and the house-specific instance run by Dog.
 * </p>
 * 
 * <p>
 * It is designed to support more advanced query functionalities such as query
 * by location, by functionality, by energy profile, etc.
 * 
 * These advanced functionalities are implemented as further extensions of the
 * basic HouseModel interface...
 * </p>
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class SemanticHouseModel implements HouseModel, OntologyModel, ManagedService
{
	// the private reference to the service registration object
	private ServiceRegistration<?> srHouseModel;
	private ServiceRegistration<?> srOntModel;
	
	// the internal reference to the context
	private BundleContext context;
	// the DogOnt instance SVG footprint
	private String homeSVGFootprint;
	
	// the current Jena model used by the bundle
	private OntologyDescriptorSet ontoDescSet;
	private OntModel reasonedOntModel;
	private OntModel plainOntModel;
	
	// the map of currently added models
	private HashMap<String, OntModel> modelsInUse;
	private HashMap<String, Map<String, String>> namespacesInUse;
	// the internal namespace map
	private Map<String, String> namespaces;
	// the entry point namespace for the current base model
	private String entryPoint;
	
	// the query wrapper used to wrap SPARQL queries on to method calls
	private SPARQLQueryWrapper qWrapper;
	
	// the logger
	private LogHelper logger;
	
	// the XML configuration (for external communication and for describing a
	// device)
	private DogHomeConfiguration xmlConfiguration;
	// the JAXB representation of the devices without network-related info
	private List<Controllables> simpleDevicesConfiguration;
	
	/**
	 * Default (empty) constructor
	 */
	public SemanticHouseModel()
	{
		
	}
	
	/**
	 * Activate this component
	 * 
	 * @param bundleContext
	 *            the bundle context
	 */
	public void activate(BundleContext bundleContext)
	{
		// init
		this.context = bundleContext;
		this.logger = new LogHelper(bundleContext);
		
		// prepare the maps for handling modular ontology models
		this.modelsInUse = new HashMap<String, OntModel>();
		this.namespacesInUse = new HashMap<String, Map<String, String>>();
		
		this.homeSVGFootprint = "no map loaded";
	}
	
	/**
	 * Deactivate this component
	 */
	public void deactivate()
	{
		// unregister services
		this.unRegister();
		
		// set everything to null
		this.context = null;
		this.logger = null;
		this.modelsInUse = null;
		this.namespacesInUse = null;
		this.homeSVGFootprint = null;
		
		this.srHouseModel = null;
		this.srOntModel = null;
	}
	
	/**
	 * Unregister its services from OSGi framework
	 */
	public void unRegister()
	{
		if (this.srHouseModel != null)
		{
			this.srHouseModel.unregister();
		}
		
		if (this.srOntModel != null)
		{
			this.srOntModel.unregister();
		}
	}
	
	/**
	 * Listen for the configuration and start the ontology loading...
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
				this.logger.log(LogService.LOG_INFO, "Received ontology configuration...");
				
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
				// load the SVG plan
				this.homeSVGFootprint = this.filetoString(svgFileName);
			}
			
		}
	}
	
	/**
	 * Register the services provided by this bundle
	 */
	private void registerServices()
	{
		if (this.srHouseModel == null)
		{
			// register the house model service
			this.srHouseModel = this.context.registerService(HouseModel.class.getName(), this, null);
		}
		if (this.srOntModel == null)
		{
			// register the ontology model service
			this.srOntModel = this.context.registerService(OntologyModel.class.getName(), this, null);
		}
	}
	
	/**
	 * @return the SVG footprint
	 */
	public String getSVGPlan()
	{
		return this.homeSVGFootprint;
	}
	
	/**
	 * @return the logger
	 */
	public LogHelper getLogger()
	{
		return logger;
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
		this.reasonedOntModel = reasonedModel;
		this.plainOntModel = plainModel;
		this.namespaces = namespace;
		this.entryPoint = entrypoint;
		
		// create the query wrapper object
		this.qWrapper = new SPARQLQueryWrapper(this.getNamespaces(), this.reasonedOntModel);
		
		this.logger.log(LogService.LOG_INFO, "Loaded the dogont ontology");
		
		// extract the xml-representation needed to answer queries from external
		// applications
		
		Runnable XMLConfigWorker = new Runnable() {
			
			@Override
			public void run()
			{
				try
				{
					logger.log(LogService.LOG_DEBUG, "Computing JAXB configuration...");
					
					// create the XML translator
					DogOnt2XMLDog toXMLTranslator = new DogOnt2XMLDog(reasonedOntModel, namespaces, entryPoint);
					// get the JAXB configuration
					xmlConfiguration = toXMLTranslator.getJAXBXMLDog();
					
					// create the JAXB object representing the device list without their
					// network-related properties
					createSimpleDevicesRepresentation();
					
					// finish!
					logger.log(LogService.LOG_DEBUG, "Generated JAXB configuration.");
				}
				catch (Exception e)
				{
					logger.log(LogService.LOG_ERROR, "Error while translating the ontology to JAXBXML ", e);
				}
				
				// register the services provided by the bundle
				registerServices();
			}
		};
		
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
		this.plainOntModel.addSubModel(model);
		this.reasonedOntModel.addSubModel(model); // Adding model to the reason
													// model
		
		// third, add the new namespaces and keep track of them...
		this.namespacesInUse.put(entryPoint, namespaces);
		this.getNamespaces().putAll(namespaces);
	}
	
	/**
	 * Create the devices representation suitable for external usage (e.g., the
	 * REST API), without network related informations.
	 */
	private synchronized void createSimpleDevicesRepresentation()
	{
		// get the full device representation
		this.simpleDevicesConfiguration = this.xmlConfiguration.clone().getControllables();
		
		for (Device dev : this.simpleDevicesConfiguration.get(0).getDevice())
		{
			this.cleanJaxbDevice(dev);
		}
	}
	
	/**
	 * Prepare the JAXB Device to contain the proper information for external
	 * applications. It removes all the network-related properties and hides
	 * some redundant arrays for the JSON serialization.
	 * 
	 * @param device
	 *            the "full" JAXB Device to clean
	 */
	private synchronized void cleanJaxbDevice(Device device)
	{
		// store the parameters to be removed from the current device
		Vector<Configparam> paramsToRemove = new Vector<Configparam>();
		
		// remove all the "first-level" params, since they are network-related
		device.getParam().clear();
		
		// clean the description field
		String description = device.getDescription().trim();
		description = description.replaceAll("\t", "");
		description = description.replaceAll("\n", " ");
		device.setDescription(description);
		
		// get all the control functionalites...
		List<ControlFunctionality> controlFunctionalities = device.getControlFunctionality();
		for (ControlFunctionality controlFunctionality : controlFunctionalities)
		{
			// get all the commands
			for (Configcommand command : controlFunctionality.getCommands().getCommand())
			{
				for (Configparam param : command.getParam())
				{
					// get all the command parameters to remove
					// (network-related), i.e., preserve only the
					// "realCommandName" prop
					if (!param.getName().equalsIgnoreCase("realCommandName"))
					{
						paramsToRemove.add(param);
					}
				}
				// effectively remove the parameters
				for (Configparam param : paramsToRemove)
				{
					command.getParam().remove(param);
				}
				paramsToRemove.clear();
			}
			
			// improve non-XML rendering by creating a redundant array
			controlFunctionality.setCommandList(controlFunctionality.getCommands().getCommand());
		}
		
		// get all the notification functionalities...
		List<NotificationFunctionality> notificationsFunctionalities = device.getNotificationFunctionality();
		for (NotificationFunctionality notificationFunctionality : notificationsFunctionalities)
		{
			// get all the notifications...
			for (Confignotification notification : notificationFunctionality.getNotifications().getNotification())
			{
				for (Configparam param : notification.getParam())
				{
					// get all the notification parameters to remove
					// (network-related), i.e., preserve only the
					// "notificationName" and "notificationParamName" props
					if ((!param.getName().equalsIgnoreCase("notificationName"))
							&& (!param.getName().equalsIgnoreCase("notificationParamName")))
					{
						paramsToRemove.add(param);
					}
				}
				// effectively remove the parameters
				for (Configparam param : paramsToRemove)
				{
					notification.getParam().remove(param);
				}
				paramsToRemove.clear();
			}
			
			// improve non-XML rendering by creating a redundant array
			notificationFunctionality.setNotificationList(notificationFunctionality.getNotifications()
					.getNotification());
		}
		
		// improve non-XML rendering by creating a redundant array for states
		for (Configstate status : device.getState())
			status.setStatevalueList(status.getStatevalues().getStatevalue());
		
	}
	
	/*********************************************************************************
	 * 
	 * HouseModel service - implemented methods
	 * 
	 ********************************************************************************/
	
	@Override
	public Vector<DeviceDescriptor> getConfiguration()
	{
		return this.getDeviceConfig(null);
	}
	
	/**
	 * <p>
	 * Gets the device configurations in the property format defined by dog and
	 * obtained through the {@link DogDeviceDescription} object (by calling the
	 * {@link asDogDeviceConfigurationP()) method. Conditions might be given
	 * restricting the set of devices for which configurations must be provided
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
			// given device categories
			if (conditions != null)
			{
				Set<String> devices = this.qWrapper.getCategoryFilteredControllableInstances(conditions);
				
				// load all to the device list and convert it in short form
				for (String devURI : devices)
				{
					devList.add(this.qWrapper.toShortForm(devURI));
				}
			}
		}
		else
		{
			// get all controllables...
			Set<String> devices = this.qWrapper.getAllControllableInstances();
			
			// convert it in short form
			for (String devURI : devices)
			{
				devList.add(this.qWrapper.toShortForm(devURI));
			}
		}
		
		if (this.xmlConfiguration != null)
		{
			for (Device dev : this.xmlConfiguration.getControllables().get(0).getDevice())
			{
				if (devList.contains(dev.getId()))
				{
					configs.add(new DeviceDescriptor(dev));
				}
			}
		}
		
		return configs;
	}
	
	@Override
	public void updateConfiguration(Vector<DeviceDescriptor> updatedDescriptors)
	{
		for (DeviceDescriptor descriptor : updatedDescriptors)
		{
			this.updateConfiguration(descriptor);
		}
		
	}
	
	@Override
	public void updateConfiguration(DeviceDescriptor updatedDescriptor)
	{
		this.removeDeviceImpl(updatedDescriptor.getDeviceURI());
		
		this.addNewDeviceImpl(updatedDescriptor);
		
	}
	
	@Override
	public void addToConfiguration(Vector<DeviceDescriptor> newDescriptors)
	{
		for (DeviceDescriptor dd : newDescriptors)
		{
			this.addToConfiguration(dd);
		}
		
	}
	
	@Override
	public void addToConfiguration(DeviceDescriptor newDescriptor)
	{
		this.addNewDeviceImpl(newDescriptor);
		
	}
	
	@Override
	public void removeFromConfiguration(Set<String> deviceURIs)
	{
		for (String device : deviceURIs)
		{
			this.removeFromConfiguration(device);
		}
		
	}
	
	@Override
	public void removeFromConfiguration(String deviceURI)
	{
		this.removeDeviceImpl(deviceURI);
	}
	
	private void addNewDeviceImpl(DeviceDescriptor deviceDescriptor)
	{
		// log the addition request
		this.logger.log(LogService.LOG_INFO, "Received request to add: " + deviceDescriptor.getDeviceURI());
		
		// create and launch the device addition thread
		ThreadedDeviceAdder devAdder = new ThreadedDeviceAdder(deviceDescriptor, this.reasonedOntModel,
				this.plainOntModel, this);
		devAdder.run();
	}
	
	private void removeDeviceImpl(String deviceURI)
	{
		this.logger.log(LogService.LOG_INFO, "Received request to remove: " + deviceURI);
		
		// create and launch the device removal thread
		ThreadedDeviceRemover devRemover = new ThreadedDeviceRemover(deviceURI, this.reasonedOntModel,
				this.plainOntModel, this);
		devRemover.start();
	}
	
	@Override
	public DogHomeConfiguration getJAXBConfiguration()
	{
		// return the complete JAXB configuration (i.e., rooms, devices,
		// low-level properties, etc.)
		return this.xmlConfiguration;
	}
	
	@Override
	public List<Controllables> getJAXBDevices()
	{
		List<Controllables> devices = new ArrayList<Controllables>();
		
		if ((this.xmlConfiguration != null) && (!this.xmlConfiguration.getControllables().isEmpty()))
		{
			devices = this.xmlConfiguration.clone().getControllables();
		}
		
		// return all the devices with their properties, in their JAXB
		// representation
		return devices;
	}
	
	@Override
	public List<Controllables> getDevices()
	{
		return this.xmlConfiguration.getControllables();
	}
	
	@Override
	public List<Controllables> getSimpleDevices()
	{
		return this.simpleDevicesConfiguration;
	}
	
	/*********************************************************************************
	 * 
	 * OntologyModel service - implemented methods
	 * 
	 ********************************************************************************/
	
	@Override
	public OntModel getModel()
	{
		return this.reasonedOntModel;
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
				this.reasonedOntModel.remove(cOntModel);
				// this.dogontModel.removeSubModel(cOntModel);
				this.reasonedOntModel.write(System.out);
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
	 * Helper methods
	 * 
	 ********************************************************************************/
	
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
	
}
