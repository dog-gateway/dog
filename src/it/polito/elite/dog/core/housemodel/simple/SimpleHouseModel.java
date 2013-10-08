/*
 * Dog - Core
 * 
 * Copyright (c) 2010-2013 Dario Bonino, Luigi De Russis and Emiliano Castellina
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
package it.polito.elite.dog.core.housemodel.simple;

import it.polito.elite.dog.core.housemodel.api.HouseModel;
import it.polito.elite.dog.core.library.jaxb.Device;
import it.polito.elite.dog.core.library.jaxb.DogHomeConfiguration;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.log.LogService;

/**
 * This class implements the HouseModel interface.<br/>
 * It is named SimpleHouseModel since it works without using the ontology.
 * 
 * @author Emiliano Castellina
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class SimpleHouseModel implements HouseModel, ManagedService
{
	// bundle context
	private BundleContext context;
	// the device list read from file
	private Hashtable<String, DeviceDescriptor> deviceList;
	// devices group by their type
	private Hashtable<String, HashSet<String>> deviceCategoriesUriList;
	// the SVG house plan
	private String svgPlan;
	// the XML configuration
	private DogHomeConfiguration xlmConfiguration;
	// the logger
	private LogHelper logger;
	// the JAXB context
	private JAXBContext jaxbContext;
	// the XML configuration full path
	private String configurationPath;
	
	// HouseModel service registration
	private ServiceRegistration<?> srHouseModel;
	
	/**
	 * Default (empty) constructor
	 */
	public SimpleHouseModel()
	{
		
	}
	
	/**
	 * Activate this component (after its bind)
	 * 
	 * @param bundleContext
	 *            the bundle context
	 */
	public void activate(BundleContext bundleContext)
	{
		// init
		this.context = bundleContext;
		this.logger = new LogHelper(bundleContext);
		
		this.deviceCategoriesUriList = new Hashtable<String, HashSet<String>>();
		this.deviceList = new Hashtable<String, DeviceDescriptor>();
		
		this.svgPlan = "no map loaded";
	}
	
	/**
	 * Deactivate this component (before its unbind)
	 */
	public void deactivate()
	{
		// unregister services
		this.unRegister();
		
		// set everything to null
		this.context = null;
		this.logger = null;
		this.deviceCategoriesUriList = null;
		this.deviceList = null;
		this.svgPlan = null;
		
		this.srHouseModel = null;
	}
	
	/***
	 * Unregister its services from OSGi framework
	 */
	public void unRegister()
	{
		if (this.srHouseModel != null)
		{
			this.srHouseModel.unregister();
		}
	}
	
	/**
	 * Listen for the configuration and start the XML parsing...
	 */
	@Override
	public void updated(Dictionary<String, ?> properties)
	{
		if (properties != null)
		{
			String devicesFileName = (String) properties.get(DeviceCostants.DEVICES);
			String svgFileName = (String) properties.get(DeviceCostants.SVGPLAN);
			
			if (devicesFileName != null && !devicesFileName.isEmpty())
			{
				this.loadXmlConfiguration(devicesFileName, DeviceCostants.DEVICES);
			}
			
			if (svgFileName != null && !svgFileName.isEmpty())
			{
				this.svgPlan = SimpleHouseModel.fileToString(svgFileName);
			}
			
			this.registerServices();
		}
	}
	
	/**
	 * Load and parse the XML configuration of the current environment.
	 * 
	 * @param xmlFilename
	 *            the XML file storing the configuration
	 * @param type
	 *            the configuration type (it supports {@link DeviceCostants}
	 *            .DEVICES only, at the moment)
	 * @return true if the parsing has been successful or the configuration is
	 *         not empty; false, otherwise.
	 */
	private boolean loadXmlConfiguration(String xmlFilename, String type)
	{
		try
		{
			this.jaxbContext = JAXBContext.newInstance(DogHomeConfiguration.class.getPackage().getName());
			Unmarshaller unmarshaller = this.jaxbContext.createUnmarshaller();
			
			if (type.equals(DeviceCostants.DEVICES))
			{
				// store the configuration path
				this.configurationPath = System.getProperty("configFolder") + "/" + xmlFilename;
				
				// unmarshall
				this.xlmConfiguration = (DogHomeConfiguration) ((JAXBElement<DogHomeConfiguration>) unmarshaller
						.unmarshal(new StreamSource(this.configurationPath), DogHomeConfiguration.class)).getValue();
			}
			
		}
		catch (JAXBException e)
		{
			this.logger.log(LogService.LOG_ERROR, "JAXB Error", e);
		}
		
		long t1 = System.currentTimeMillis();
		
		// create a DeviceDescriptor-based representation of current XML
		// configuration
		this.createDeviceDescriptors();
		
		long t2 = System.currentTimeMillis();
		this.logger.log(LogService.LOG_INFO, String.format("Parsing time: JAXB %.2f s\n", (float) (t2 - t1) / 1000f));
		
		return this.deviceList.size() > 0;
		
	}
	
	/**
	 * Create a DeviceDescriptor for each JAXB device obtained from the XML
	 * configuration
	 */
	private void createDeviceDescriptors()
	{
		if (this.xlmConfiguration != null)
		{
			for (Device dev : this.xlmConfiguration.getControllables().get(0).getDevice())
			{
				DeviceDescriptor currentDescriptor = new DeviceDescriptor(dev);
				
				// add the Device Descriptor to the device list
				this.deviceList.put(currentDescriptor.getDeviceURI(), currentDescriptor);
				
				// add the element to the map that stores information grouped by
				// Device Category
				HashSet<String> devicesForDevCategory = this.deviceCategoriesUriList.get(currentDescriptor
						.getDeviceCategory());
				// category does not exist yet...
				if (devicesForDevCategory == null)
				{
					devicesForDevCategory = new HashSet<String>();
					this.deviceCategoriesUriList.put(currentDescriptor.getDeviceCategory(), devicesForDevCategory);
				}
				devicesForDevCategory.add(currentDescriptor.getDeviceURI());
			}
			
		}
		// final log
		this.logger.log(LogService.LOG_INFO,
				String.format("SimpleHouseModel contains %d device descriptions.", this.deviceList.size()));
		
	}
	
	/**
	 * Register the services provided by this bundle
	 */
	private void registerServices()
	{
		if (this.srHouseModel == null)
		{
			// register the offered service
			this.srHouseModel = this.context.registerService(HouseModel.class.getName(), this, null);
		}
	}
	
	/*********************************************************************************
	 * 
	 * HouseModel service - implemented methods
	 * 
	 ********************************************************************************/
	@Override
	public void updateConfiguration(Vector<DeviceDescriptor> updatedDescriptors)
	{
		for (DeviceDescriptor descriptor : updatedDescriptors)
		{
			this.updateDevice(descriptor);
		}
		
		// write a new XML configuration file on disk
		this.saveConfiguration();
	}
	
	@Override
	public void updateConfiguration(DeviceDescriptor updatedDescriptor)
	{
		// update the device present in the configuration
		this.updateDevice(updatedDescriptor);
		
		// write a new XML configuration file on disk
		this.saveConfiguration();
	}
	
	@Override
	public void addToConfiguration(Vector<DeviceDescriptor> newDescriptors)
	{
		for (DeviceDescriptor descriptor : newDescriptors)
		{
			// insert the device in the configuration
			this.addDevice(descriptor);
		}
		
		// write a new XML configuration file on disk
		this.saveConfiguration();
	}
	
	@Override
	public void addToConfiguration(DeviceDescriptor newDescriptor)
	{
		// insert the device in the configuration
		this.addDevice(newDescriptor);
		
		// write a new XML configuration file on disk
		this.saveConfiguration();
	}
	
	@Override
	public void removeFromConfiguration(Set<String> deviceURIs)
	{
		for (String device : deviceURIs)
		{
			this.removeDevice(device);
		}
		
		// write a new XML configuration file on disk
		this.saveConfiguration();
	}
	
	@Override
	public void removeFromConfiguration(String deviceURI)
	{
		// remove the given device from the configuration
		this.removeDevice(deviceURI);
		
		// write a new XML configuration file on disk
		this.saveConfiguration();
	}
	
	/**
	 * Implementation of the updateConfiguration methods, for updating the
	 * devices present in the HouseModel at runtime
	 * 
	 * @param updatedDescriptor
	 *            the {@link DeviceDescriptor} representing the device to update
	 */
	private void updateDevice(DeviceDescriptor updatedDescriptor)
	{
		// remove the device from the current configuration
		this.removeDevice(updatedDescriptor.getDeviceURI());
		
		// add the updated device
		this.addDevice(updatedDescriptor);
	}
	
	/**
	 * Implementation of the addToConfiguration methods, for adding devices to
	 * the HouseModel at runtime
	 * 
	 * @param descriptor
	 *            the {@link DeviceDescriptor} representing the device to add
	 */
	private void addDevice(DeviceDescriptor descriptor)
	{
		// add the device into the device list
		this.deviceList.put(descriptor.getDeviceURI(), descriptor);
		
		// add the device into the category list
		HashSet<String> deviceUris = null;
		if (this.deviceCategoriesUriList.containsKey(descriptor.getDeviceCategory()))
		{
			deviceUris = this.deviceCategoriesUriList.get(descriptor.getDeviceCategory());
		}
		else
		{
			deviceUris = new HashSet<String>();
			this.deviceCategoriesUriList.put(descriptor.getDeviceCategory(), deviceUris);
		}
		deviceUris.add(descriptor.getDeviceURI());
		
		// add the new device into the XML configuration
		if (this.xlmConfiguration != null)
		{
			this.xlmConfiguration.getControllables().get(0).getDevice().add(descriptor.getJaxbDevice());
		}
	}
	
	/**
	 * Implementation of the removeFromConfiguration methods, for removing
	 * devices from the HouseModel at runtime
	 * 
	 * @param deviceURI
	 *            the URI of the device to remove
	 */
	private void removeDevice(String deviceURI)
	{
		// remove the device from the device list
		DeviceDescriptor deviceProp = this.deviceList.remove(deviceURI);
		
		// remove the device from the category list
		if (deviceProp != null)
		{
			String deviceCategory = deviceProp.getDeviceCategory();
			if (deviceCategory != null)
			{
				HashSet<String> devices = this.deviceCategoriesUriList.get(deviceCategory);
				if (devices != null)
				{
					devices.remove(deviceURI);
				}
			}
		}
		
		// remove the device from the XML configuration
		if (this.xlmConfiguration != null)
		{
			Device removedDevice = null;
			List<Device> devices = this.xlmConfiguration.getControllables().get(0).getDevice();
			boolean found = false;
			for (int i = 0; i < devices.size() && !found; i++)
			{
				Device device = devices.get(i);
				if (device.getName().equals(deviceURI))
				{
					removedDevice = device;
					found = true;
				}
				
			}
			if (removedDevice != null)
			{
				this.xlmConfiguration.getControllables().get(0).getDevice().remove(removedDevice);
			}
		}
	}
	
	/**
	 * Save the updated XML configuration onto the disk
	 */
	private void saveConfiguration()
	{
		try
		{
			// create the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			// write the updated XML configuration upon the old one...
			if ((this.configurationPath != null) && (!this.configurationPath.isEmpty()))
				marshaller.marshal(this.xlmConfiguration, new StreamResult(this.configurationPath));
		}
		catch (JAXBException e)
		{
			this.logger.log(LogService.LOG_ERROR, "Exception in saving the XML configuration onto the disk", e);
		}
	}
	
	@Override
	public Vector<DeviceDescriptor> getConfiguration()
	{
		return this.getConfigDevice();
	}
	
	@Override
	public String getSVGPlan()
	{
		return this.svgPlan;
	}
	
	/**
	 * Implementation of the getConfiguration() method. It inspects the list of
	 * devices to retrieve their information.
	 * 
	 * @return a list of {@link DeviceDescriptor}, containing devices
	 *         information
	 */
	private Vector<DeviceDescriptor> getConfigDevice()
	{
		Hashtable<String, HashSet<String>> condition = null;
		return this.getConfigDevice(condition);
	}
	
	/**
	 * Implementation of the getConfiguration() method. It inspects the list of
	 * devices to retrieve their information.
	 * 
	 * @param condition
	 *            null for getting all the devices, a list of device URI
	 *            otherwise
	 * @return a list of {@link DeviceDescriptor}, containing devices
	 *         information
	 */
	private Vector<DeviceDescriptor> getConfigDevice(Hashtable<String, HashSet<String>> condition)
	{
		Vector<DeviceDescriptor> devicesProp = new Vector<DeviceDescriptor>();
		HashSet<String> conditionDevices = null;
		HashSet<String> conditionDeviceCategories = null;
		
		if (condition == null)
		{
			// get all the devices
			conditionDevices = new HashSet<String>(this.deviceList.keySet());
		}
		else
		{
			conditionDevices = condition.get(DeviceCostants.DEVICEURI);
			conditionDeviceCategories = condition.get(DeviceCostants.DEVICE_CATEGORY);
			if (conditionDeviceCategories.size() == 0 && conditionDevices.size() == 0)
			{
				conditionDevices = new HashSet<String>(this.deviceList.keySet());
			}
		}
		
		if (conditionDevices != null)
		{
			// select only the requested devices
			for (String uri : conditionDevices)
			{
				DeviceDescriptor uriProp = this.deviceList.get(uri);
				if (uriProp != null)
				{
					devicesProp.add(uriProp);
				}
				
			}
		}
		if (conditionDeviceCategories != null)
		{
			for (String deviceCategory : conditionDeviceCategories)
			{
				HashSet<String> devices = this.deviceCategoriesUriList.get(deviceCategory);
				if (devices != null)
				{
					for (String uri : devices)
					{
						DeviceDescriptor uriProp = this.deviceList.get(uri);
						if (uriProp != null)
						{
							
							devicesProp.add(uriProp);
						}
					}
				}
				
			}
			
		}
		return devicesProp;
	}
	
	/*********************************************************************************
	 * 
	 * Bundle utilities
	 * 
	 ********************************************************************************/
	
	/**
	 * Read a XML-like file and convert it into a String. Used for reading the
	 * SVG house plan.
	 * 
	 * @param fileName
	 *            the XML-like file to read
	 * @return the file content into a String object
	 */
	protected static String fileToString(String fileName)
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
