/*
 * Dog - Core
 * 
 * Copyright (c) 2009-2014 Dario Bonino and Luigi De Russis
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
import it.polito.elite.dog.core.housemodel.semantic.jaxb.DogOnt2JAXB;
import it.polito.elite.dog.core.housemodel.semantic.loader.LoadingModes;
import it.polito.elite.dog.core.housemodel.semantic.loader.ModelLoader;
import it.polito.elite.dog.core.housemodel.semantic.owl.model.ControllableModel;
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
import it.polito.elite.dog.core.library.semantic.OWLWrapper;
import it.polito.elite.dog.core.library.semantic.xml.Ontologies;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.log.LogService;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

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
	// the private reference to the service registration objects
	private ServiceRegistration<?> srHouseModel;
	private ServiceRegistration<?> srOntModel;
	
	// the internal reference to the context
	private BundleContext context;
	// the logger
	private LogHelper logger;
	
	// the DogOnt instance SVG footprint
	private String homeSVGFootprint;
	
	// the OWL API models and prefixes used
	private Ontologies ontoDescSet;
	private OWLOntology ontModel;
	private Map<String, OWLWrapper> subModels;
	// an OWL wrapper to prepare needed information for extracting and managing
	// data
	private OWLWrapper owlWrapper;
	
	// the XML configuration (for external communication and for describing a
	// device)
	private DogHomeConfiguration xmlConfiguration;
	// the JAXB representation of the devices without network-related info
	private List<Controllables> simpleDevicesConfiguration;
	private JAXBContext jaxbContext;
	
	/**
	 * Default (empty) constructor
	 */
	public SemanticHouseModel()
	{
		// init data structures
		this.subModels = new ConcurrentHashMap<String, OWLWrapper>();
	}
	
	/**
	 * Activate this bundle
	 * 
	 * @param bundleContext
	 *            the OSGi bundle context
	 */
	public void activate(BundleContext bundleContext)
	{
		// init
		this.context = bundleContext;
		this.logger = new LogHelper(bundleContext);
		
		this.homeSVGFootprint = "no map loaded";
	}
	
	/**
	 * Deactivate this component
	 */
	public void deactivate()
	{
		// set everything to null
		this.context = null;
		this.logger = null;
		this.homeSVGFootprint = null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.service.cm.ManagedService#updated(java.util.Dictionary)
	 */
	@Override
	public void updated(Dictionary<String, ?> properties) throws ConfigurationException
	{
		if (properties != null)
		{
			String ontologyFileName = (String) properties.get(DeviceCostants.ONTOLOGY);
			String svgFileName = (String) properties.get(DeviceCostants.SVGPLAN);
			
			if (ontologyFileName != null && !ontologyFileName.isEmpty())
			{
				// log the update data received
				this.logger.log(LogService.LOG_INFO, "Received ontology configuration...");

				try
				{
					this.jaxbContext = JAXBContext.newInstance(Ontologies.class.getPackage().getName());
					
					Unmarshaller unmarshaller = this.jaxbContext.createUnmarshaller();
					
					// check absolute vs relative
					File xmlFile = new File(ontologyFileName);
					if (!xmlFile.isAbsolute())
						ontologyFileName = System.getProperty("configFolder") + "/" + ontologyFileName;
					
					// unmarshall
					this.ontoDescSet = (unmarshaller.unmarshal(new StreamSource(ontologyFileName), Ontologies.class))
							.getValue();
				}
				catch (JAXBException e)
				{
					this.logger.log(LogService.LOG_ERROR, "JAXB error in reading the ontology descriptor", e);
				}
				
				if ((ontoDescSet != null) && (!ontoDescSet.getOntology().isEmpty()))
				{
					// create a new model loader pointing at the bundle
					ModelLoader loader = new ModelLoader(this);
					// load the model
					loader.loadModel(ontoDescSet, LoadingModes.LOAD_REPLACE);
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
	 * Unregister the bundle services from OSGi framework
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
	 * Store the given {@link OWLOntology} model in the relative instance
	 * variable, init OWL-related information, generates the JAXB representation
	 * of the ontology (for creating {@link DeviceDescriptor}s and for external
	 * services), and register the bundle in the OSGi framework
	 * 
	 * @param loadedModel
	 *            a {@link OWLOntology} to load
	 * @param prefixManager
	 *            a {@link DefaultPrefixManager} storing the prefixes and
	 *            namespaces read from the Ontology Descriptor file
	 */
	public void setModel(OWLOntology loadedModel, DefaultPrefixManager prefixManager)
	{
		this.ontModel = loadedModel;
		
		// create the OWL wrapper that add all the needed prefixes and init the
		// reasoner
		this.owlWrapper = new OWLWrapper(loadedModel, prefixManager);
		
		// extract the XML representation needed to answer queries from external
		// applications
		Runnable XMLConfigWorker = new Runnable() {
			
			@Override
			public void run()
			{
				// start time
				long time = System.currentTimeMillis();
				
				// debug
				logger.log(LogService.LOG_DEBUG, "Reasoning and computing JAXB configuration...");
				
				// create the XML translator
				DogOnt2JAXB toXMLTranslator = new DogOnt2JAXB(owlWrapper);
				
				// get the JAXB configuration
				xmlConfiguration = toXMLTranslator.getJAXBXMLDog();
				
				// finish!
				logger.log(LogService.LOG_DEBUG, "The JAXB configuration has been successfully generated in "
						+ ((float) (System.currentTimeMillis() - time) / 1000) + " seconds!");
				
				// create the JAXB object representing the device list without
				// their network-related properties
				createSimpleDevicesRepresentation();
				
				// register the services provided by the bundle
				registerServices();
			}
		};
		
		// start the thread executor
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(XMLConfigWorker);
		executor.shutdown();
	}
	
	/**
	 * Add a (sub)model to the HouseModel running ontology model
	 * 
	 * @param modelIRI
	 *            the IRI of the ontology
	 * @param modelToAdd
	 *            the ontology to add as a submodel
	 * @param prefixesToAdd
	 *            the prefixes of the ontology to add as a submodel
	 */
	public void addModel(String modelIRI, OWLOntology modelToAdd, DefaultPrefixManager prefixesToAdd)
	{
		// check if the sub-model imports the current instance of the HouseModel
		if (modelToAdd.getImports().contains(this.ontModel))
		{
			// ok, it includes the current entry point ontology:
			// do not modify the current model, just add the new model to the
			// submodel list
			// TODO Check if it is ok, or if it is better to update the
			// HouseModel ontology, too
			this.subModels.put(modelIRI, new OWLWrapper(modelToAdd, prefixesToAdd));
		}
		else
		{
			this.logger.log(LogService.LOG_ERROR, "Impossible to add the submodel " + modelIRI
					+ "to the HouseModel since it does not include the running model.");
		}
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.core.housemodel.semantic.api.OntologyModel#getModel()
	 */
	@Override
	public OWLWrapper getSubModel(String ontologyIRI)
	{
		return this.subModels.get(ontologyIRI);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.core.housemodel.semantic.api.OntologyModel#loadAndMerge
	 * (it.polito.elite.dog.core.library.semantic.util.OntologyDescriptorSet)
	 */
	@Override
	public void loadAndMerge(Ontologies setToLoad)
	{
		// set the model to merge
		ModelLoader loader = new ModelLoader(this);
		// load the model
		loader.loadModel(setToLoad, LoadingModes.LOAD_MERGE);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.core.housemodel.semantic.api.OntologyModel#remove
	 * (java.util.Set)
	 */
	@Override
	public void remove(Set<String> modelsToRemove)
	{
		// TODO Complete!
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.core.housemodel.api.HouseModel#updateConfiguration
	 * (java.util.Vector)
	 */
	@Override
	public void updateConfiguration(Vector<DeviceDescriptor> updatedDescriptors)
	{
		for (DeviceDescriptor descriptor : updatedDescriptors)
		{
			this.updateConfiguration(descriptor);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.core.housemodel.api.HouseModel#updateConfiguration
	 * (it.polito.elite.dog.core.library.model.DeviceDescriptor)
	 */
	@Override
	public void updateConfiguration(DeviceDescriptor updatedDescriptor)
	{
		this.removeDeviceImpl(updatedDescriptor.getDeviceURI());
		
		this.addNewDeviceImpl(updatedDescriptor);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.core.housemodel.api.HouseModel#addToConfiguration
	 * (java.util.Vector)
	 */
	@Override
	public void addToConfiguration(Vector<DeviceDescriptor> newDescriptors)
	{
		for (DeviceDescriptor dd : newDescriptors)
		{
			this.addToConfiguration(dd);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.core.housemodel.api.HouseModel#addToConfiguration
	 * (it.polito.elite.dog.core.library.model.DeviceDescriptor)
	 */
	@Override
	public void addToConfiguration(DeviceDescriptor newDescriptor)
	{
		this.addNewDeviceImpl(newDescriptor);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.core.housemodel.api.HouseModel#removeFromConfiguration
	 * (java.util.Set)
	 */
	@Override
	public void removeFromConfiguration(Set<String> deviceURIs)
	{
		for (String device : deviceURIs)
		{
			this.removeFromConfiguration(device);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.core.housemodel.api.HouseModel#removeFromConfiguration
	 * (java.lang.String)
	 */
	@Override
	public void removeFromConfiguration(String deviceURI)
	{
		this.removeDeviceImpl(deviceURI);
	}
	
	/**
	 * 
	 * @param deviceDescriptor
	 */
	private void addNewDeviceImpl(DeviceDescriptor deviceDescriptor)
	{
		// TODO Complete!
	}
	
	/**
	 * 
	 * @param deviceURI
	 */
	private void removeDeviceImpl(String deviceURI)
	{
		// TODO Complete!
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.core.housemodel.api.HouseModel#getConfiguration()
	 */
	@Override
	public Vector<DeviceDescriptor> getConfiguration()
	{
		return this.getConfigDevice();
	}
	
	/**
	 * Implementation of the getConfiguration() method. It gets the list of
	 * devices from the model to retrieve their information like a set of
	 * {@link DeviceDescriptor}.
	 * 
	 * @return a list of {@link DeviceDescriptor}, containing devices
	 *         information
	 */
	private Vector<DeviceDescriptor> getConfigDevice()
	{
		// prepare the device description object
		Vector<DeviceDescriptor> configs = new Vector<DeviceDescriptor>();
		
		ControllableModel cModel = new ControllableModel(owlWrapper);
		// get all controllables...
		Set<String> devices = cModel.getAllControllableInstances();
		
		if (this.xmlConfiguration != null)
		{
			for (Device dev : this.xmlConfiguration.getControllables().get(0).getDevice())
			{
				if (devices.contains(dev.getId()))
				{
					configs.add(new DeviceDescriptor(dev));
				}
			}
		}
		
		return configs;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.core.housemodel.api.HouseModel#getJAXBConfiguration()
	 */
	@Override
	public DogHomeConfiguration getJAXBConfiguration()
	{
		// return the complete JAXB configuration (i.e., rooms, devices,
		// low-level properties, etc.)
		return this.xmlConfiguration;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.dog.core.housemodel.api.HouseModel#getDevices()
	 */
	@Override
	public List<Controllables> getDevices()
	{
		return this.xmlConfiguration.getControllables();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.dog.core.housemodel.api.HouseModel#getJAXBDevices()
	 */
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.core.housemodel.api.HouseModel#getSimpleDevices()
	 */
	@Override
	public List<Controllables> getSimpleDevices()
	{
		return this.simpleDevicesConfiguration;
	}
	
	/**
	 * Get the logger
	 * 
	 * @return the logger
	 */
	public LogHelper getLogger()
	{
		return this.logger;
	}
	
	/**
	 * Get the SVG house plan
	 * 
	 * @return the SVG as a {@link String}
	 */
	public String getSVGMap()
	{
		return this.homeSVGFootprint;
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
