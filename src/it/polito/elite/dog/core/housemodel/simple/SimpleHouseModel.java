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
import it.polito.elite.dog.core.library.jaxb.Building;
import it.polito.elite.dog.core.library.jaxb.BuildingEnvironment;
import it.polito.elite.dog.core.library.jaxb.Controllables;
import it.polito.elite.dog.core.library.jaxb.Device;
import it.polito.elite.dog.core.library.jaxb.DogHomeConfiguration;
import it.polito.elite.dog.core.library.jaxb.Flat;
import it.polito.elite.dog.core.library.jaxb.Room;
import it.polito.elite.dog.core.library.jaxb.Storey;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	private DogHomeConfiguration xmlConfiguration;
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
				this.xmlConfiguration = (DogHomeConfiguration) ((JAXBElement<DogHomeConfiguration>) unmarshaller
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
		if (this.xmlConfiguration != null)
		{
			for (Device dev : this.xmlConfiguration.getControllables().get(0).getDevice())
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
	private synchronized void addDevice(DeviceDescriptor descriptor)
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
		if (this.xmlConfiguration != null)
		{
			this.xmlConfiguration.getControllables().get(0).getDevice().add(descriptor.getJaxbDevice());
		}
	}
	
	/**
	 * Implementation of the removeFromConfiguration methods, for removing
	 * devices from the HouseModel at runtime
	 * 
	 * @param deviceURI
	 *            the URI of the device to remove
	 */
	private synchronized void removeDevice(String deviceURI)
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
		if (this.xmlConfiguration != null)
		{
			Device removedDevice = null;
			List<Device> devices = this.xmlConfiguration.getControllables().get(0).getDevice();
			boolean found = false;
			for (int i = 0; i < devices.size() && !found; i++)
			{
				Device device = devices.get(i);
				if (device.getId().equals(deviceURI))
				{
					removedDevice = device;
					found = true;
				}
				
			}
			if (removedDevice != null)
			{
				this.xmlConfiguration.getControllables().get(0).getDevice().remove(removedDevice);
			}
		}
	}
	
	/**
	 * Save the updated XML configuration onto the disk
	 */
	private synchronized void saveConfiguration()
	{
		try
		{
			// create the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			// write the updated XML configuration upon the old one...
			if ((this.configurationPath != null) && (!this.configurationPath.isEmpty())
					&& (this.xmlConfiguration != null))
				marshaller.marshal(this.xmlConfiguration, new StreamResult(this.configurationPath));
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
	
	@Override
	public String getSVGPlan()
	{
		return this.svgPlan;
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
			devices.addAll(this.xmlConfiguration.getControllables());
		}
		
		// return all the devices with their properties, in their JAXB
		// representation
		return devices;
		
	}
	
	@Override
	public List<BuildingEnvironment> getJAXBBuildingEnvironment()
	{
		List<BuildingEnvironment> building = new ArrayList<BuildingEnvironment>();
		
		if ((this.xmlConfiguration != null) && (!this.xmlConfiguration.getBuildingEnvironment().isEmpty()))
		{
			building.addAll(this.xmlConfiguration.getBuildingEnvironment());
		}
		
		// return the building structures (flats, rooms, etc.) in their JAXB
		// representation
		return building;
	}
	
	@Override
	public void updateBuildingConfiguration(Room roomToUpdate, String containerURI)
	{
		if (this.xmlConfiguration != null)
		{
			boolean removed = this.removeRoom(roomToUpdate.getId(), containerURI);
			
			boolean added = this.addRoom(roomToUpdate, containerURI);
			
			if ((added) && (removed))
				this.saveConfiguration();
		}
	}
	
	@Override
	public void updateBuildingConfiguration(Flat flatToUpdate)
	{
		if (this.xmlConfiguration != null)
		{
			boolean removed = this.removeFlat(flatToUpdate.getId());
			
			boolean added = this.addFlat(flatToUpdate);
			
			if ((added) && (removed))
				this.saveConfiguration();
		}
	}
	
	@Override
	public void updateBuildingConfiguration(Flat flatToUpdate, String storeyURI)
	{
		if (this.xmlConfiguration != null)
		{
			boolean removed = this.removeFlat(flatToUpdate.getId());
			
			boolean added = this.addFlat(flatToUpdate, storeyURI);
			
			if ((added) && (removed))
				this.saveConfiguration();
		}
	}
	
	@Override
	public void updateBuildingConfiguration(Storey storeyToUpdate)
	{
		if (this.xmlConfiguration != null)
		{
			boolean removed = this.removeStorey(storeyToUpdate.getId());
			
			boolean added = this.addStorey(storeyToUpdate);
			
			if ((added) && (removed))
				this.saveConfiguration();
		}
	}
	
	@Override
	public void addRoomToBuilding(Room roomToAdd, String containerURI)
	{
		if (this.xmlConfiguration != null && !this.xmlConfiguration.getBuildingEnvironment().isEmpty())
		{
			if (!this.xmlConfiguration.getBuildingEnvironment().get(0).getBuilding().isEmpty())
			{
				boolean added = this.addRoom(roomToAdd, containerURI);
				
				if (added)
					this.saveConfiguration();
			}
		}
	}
	
	/**
	 * Implementation of the addRoomToBuilding() method.
	 * 
	 * @param roomToAdd
	 *            the JAXB room object to add
	 * @param containerURI
	 *            the unique name representing where the room is located
	 * @return true if the operation was successful, false otherwise
	 */
	private synchronized boolean addRoom(Room roomToAdd, String containerURI)
	{
		boolean found = false;
		
		Building building = this.xmlConfiguration.getBuildingEnvironment().get(0).getBuilding().get(0);
		
		for (Flat flat : building.getFlat())
		{
			if (flat.getId().equals(containerURI) && (!found))
			{
				flat.getRoom().add(roomToAdd);
				found = true;
			}
		}
		for (Storey storey : building.getStorey())
		{
			if (storey.getId().equals(containerURI) && (!found))
			{
				storey.getRoom().add(roomToAdd);
				found = true;
			}
			for (Flat flat : storey.getFlat())
			{
				if (flat.getId().equals(containerURI) && (!found))
				{
					flat.getRoom().add(roomToAdd);
					found = true;
				}
			}
		}
		
		return found;
	}
	
	@Override
	public void addFlatToBuilding(Flat flatToAdd)
	{
		if (this.xmlConfiguration != null && !this.xmlConfiguration.getBuildingEnvironment().isEmpty())
		{
			if (!this.xmlConfiguration.getBuildingEnvironment().get(0).getBuilding().isEmpty())
			{
				boolean added = this.addFlat(flatToAdd);
				
				if (added)
					this.saveConfiguration();
			}
		}
	}
	
	/**
	 * Implementation of the addFlatToBuilding() method.
	 * 
	 * @param flatToAdd
	 *            the JAXB flat object to add
	 * @return true if the operation was successful, false otherwise
	 */
	private synchronized boolean addFlat(Flat flatToAdd)
	{
		boolean added = this.xmlConfiguration.getBuildingEnvironment().get(0).getBuilding().get(0).getFlat()
				.add(flatToAdd);
		
		return added;
	}
	
	@Override
	public void addStoreyToBuilding(Storey storeyToAdd)
	{
		if (this.xmlConfiguration != null && !this.xmlConfiguration.getBuildingEnvironment().isEmpty())
		{
			if (!this.xmlConfiguration.getBuildingEnvironment().get(0).getBuilding().isEmpty())
			{
				boolean added = this.addStorey(storeyToAdd);
				
				if (added)
					this.saveConfiguration();
			}
		}
	}
	
	/**
	 * Implementation of the addStoreyToBuilding() method.
	 * 
	 * @param storeyToAdd
	 *            the JAXB storey object to add
	 * @return true if the operation was successful, false otherwise
	 */
	private synchronized boolean addStorey(Storey storeyToAdd)
	{
		boolean added = this.xmlConfiguration.getBuildingEnvironment().get(0).getBuilding().get(0).getStorey()
				.add(storeyToAdd);
		
		return added;
	}
	
	@Override
	public void addFlatToStorey(Flat flatToAdd, String storeyURI)
	{
		if (this.xmlConfiguration != null && !this.xmlConfiguration.getBuildingEnvironment().isEmpty())
		{
			if (!this.xmlConfiguration.getBuildingEnvironment().get(0).getBuilding().isEmpty())
			{
				boolean added = this.addFlat(flatToAdd, storeyURI);
				
				if (added)
					this.saveConfiguration();
			}
		}
	}
	
	/**
	 * Implementation of the addFlatToStorey() method.
	 * 
	 * @param flatToAdd
	 *            the JAXB flat object to add
	 * @param storeyURI
	 *            the unique name representing in which storey the flat is
	 *            located
	 * @return true if the operation was successful, false otherwise
	 */
	private synchronized boolean addFlat(Flat flatToAdd, String storeyURI)
	{
		boolean added = false;
		
		for (Storey storey : this.xmlConfiguration.getBuildingEnvironment().get(0).getBuilding().get(0).getStorey())
		{
			if (storey.getId().equals(storeyURI))
			{
				added = storey.getFlat().add(flatToAdd);
			}
		}
		return added;
	}
	
	@Override
	public void removeRoomFromBuilding(String roomURI, String containerURI)
	{
		if (this.xmlConfiguration != null && !this.xmlConfiguration.getBuildingEnvironment().isEmpty())
		{
			if (!this.xmlConfiguration.getBuildingEnvironment().get(0).getBuilding().isEmpty())
			{
				boolean removed = this.removeRoom(roomURI, containerURI);
				
				if (removed)
					this.saveConfiguration();
			}
		}
	}
	
	/**
	 * Implementation of the removeRoomFromBuilding() method.
	 * 
	 * @param roomURI
	 *            the unique name representing the room to remove
	 * @param containerURI
	 *            the unique name representing where the room is located
	 * @return true if the operation was successful, false otherwise
	 */
	private synchronized boolean removeRoom(String roomURI, String containerURI)
	{
		Room removedRoom = null;
		
		boolean found = false;
		
		Building building = this.xmlConfiguration.getBuildingEnvironment().get(0).getBuilding().get(0);
		Flat containerFlat = null;
		
		for (Flat flat : building.getFlat())
		{
			if (flat.getId().equals(containerURI) && (!found))
			{
				for (Room room : flat.getRoom())
				{
					if (room.getId().equals(roomURI) && (!found))
					{
						removedRoom = room;
						containerFlat = flat;
						found = true;
					}
				}
			}
		}
		if ((removedRoom != null) && (containerFlat != null))
		{
			containerFlat.getRoom().remove(removedRoom);
		}
		else
		{
			Storey containerStorey = null;
			for (Storey storey : building.getStorey())
			{
				if (storey.getId().equals(containerURI) && (!found))
				{
					for (Room room : storey.getRoom())
					{
						if (room.getId().equals(roomURI) && (!found))
						{
							containerStorey = storey;
							removedRoom = room;
							found = true;
						}
					}
				}
				for (Flat flat : storey.getFlat())
				{
					if (flat.getId().equals(containerURI) && (!found))
					{
						for (Room room : flat.getRoom())
						{
							if (room.getId().equals(roomURI) && (!found))
							{
								containerFlat = flat;
								removedRoom = room;
								found = true;
							}
						}
					}
				}
			}
			if (removedRoom != null)
			{
				if (containerStorey != null)
				{
					containerStorey.getRoom().remove(removedRoom);
				}
				else if (containerFlat != null)
				{
					containerFlat.getRoom().remove(removedRoom);
				}
			}
		}
		return found;
	}
	
	@Override
	public void removeFlatFromBuilding(String flatURI)
	{
		if (this.xmlConfiguration != null && !this.xmlConfiguration.getBuildingEnvironment().isEmpty())
		{
			if (!this.xmlConfiguration.getBuildingEnvironment().get(0).getBuilding().isEmpty())
			{
				boolean removed = this.removeFlat(flatURI);
				
				if (removed)
					this.saveConfiguration();
			}
		}
	}
	
	/**
	 * Implementation of the removeFlatFromBuilding() method.
	 * 
	 * @param flatURI
	 *            the unique name representing the flat to remove
	 * @return true if the operation was successful, false otherwise
	 */
	private synchronized boolean removeFlat(String flatURI)
	{
		Flat flatToRemove = null;
		boolean found = false;
		
		for (Flat flat : this.xmlConfiguration.getBuildingEnvironment().get(0).getBuilding().get(0).getFlat())
		{
			if (flat.equals(flatURI) && (!found))
			{
				flatToRemove = flat;
				found = true;
			}
		}
		if (flatToRemove != null)
			this.xmlConfiguration.getBuildingEnvironment().get(0).getBuilding().get(0).getFlat().remove(flatToRemove);
		else
		{
			for (Storey storey : this.xmlConfiguration.getBuildingEnvironment().get(0).getBuilding().get(0).getStorey())
			{
				for (Flat flat : storey.getFlat())
				{
					if (flat.equals(flatURI) && (!found))
					{
						flatToRemove = flat;
						found = true;
					}
				}
				if (flatToRemove != null)
					storey.getFlat().remove(flatToRemove);
			}
		}
		return found;
	}
	
	@Override
	public void removeStoreyFromBuilding(String storeyURI)
	{
		if (this.xmlConfiguration != null && !this.xmlConfiguration.getBuildingEnvironment().isEmpty())
		{
			if (!this.xmlConfiguration.getBuildingEnvironment().get(0).getBuilding().isEmpty())
			{
				boolean removed = this.removeStorey(storeyURI);
				
				if (removed)
					this.saveConfiguration();
			}
		}
		
	}
	
	/**
	 * Implementation of the removeStoreyFromBuilding() method.
	 * 
	 * @param storeyURI
	 *            the unique name representing the storey to remove
	 * @return true if the operation was successful, false otherwise
	 */
	private synchronized boolean removeStorey(String storeyURI)
	{
		Storey storeyToRemove = null;
		boolean found = false;
		
		for (Storey storey : this.xmlConfiguration.getBuildingEnvironment().get(0).getBuilding().get(0).getStorey())
		{
			if (storey.equals(storeyURI) && (!found))
			{
				storeyToRemove = storey;
				found = true;
			}
		}
		if (storeyToRemove != null)
		{
			this.xmlConfiguration.getBuildingEnvironment().get(0).getBuilding().get(0).getFlat().remove(storeyToRemove);
		}
		return found;
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
	private static String fileToString(String fileName)
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
