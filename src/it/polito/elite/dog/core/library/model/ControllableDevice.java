/*
 * Dog - Core
 * 
 * Copyright (c) 2009-2013 Emiliano Castellina and Luigi De Russis
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
package it.polito.elite.dog.core.library.model;

import it.polito.elite.dog.core.library.model.devicecategory.Controllable;

import java.util.Hashtable;

import org.osgi.service.device.Device;
import org.osgi.service.device.Driver;

/**
 * This class represents an abstract ControllableDevice. It is the base class
 * for all the Dog*Model classes.
 * 
 * @author <a href="mailto:emiliano.castellina@polito.it">Emiliano
 *         Castellina</a> (original implementation)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public abstract class ControllableDevice implements Device
{
	
	private DeviceDescriptor deviceDescriptor;
	private final String defaultPackage = Controllable.class.getPackage().getName();
	
	// store the devices properties, defined in the DEVICE_CATEGORY
	volatile protected Hashtable<String, Object> deviceProp;
	// unique device identifier (replicated in the deviceProp)
	protected String deviceId;
	
	public abstract void setDriver(Object obj);
	
	public ControllableDevice(DeviceDescriptor deviceProp)
	{
		this.setDeviceProperties(deviceProp);
	}
	
	public ControllableDevice()
	{
		this.deviceId = "-";
		this.deviceProp = new Hashtable<String, Object>();
		
	}
	
	/**
	 * @return the deviceDescriptor
	 */
	public DeviceDescriptor getDeviceDescriptor()
	{
		return deviceDescriptor;
	}
	
	/**
	 * Set the device properties (device type, URI, manufacturer, etc.)
	 * 
	 * @param deviceDescriptor
	 *            the device properties
	 */
	public void setDeviceProperties(DeviceDescriptor deviceDescriptor)
	{
		this.parseDescriptor(deviceDescriptor);
		
		if (!this.deviceProp.containsKey(DeviceCostants.ACTIVE))
		{
			this.deviceProp.put(DeviceCostants.ACTIVE, "false");
		}
		
	}
	
	/***
	 * Fill the hash table (storing the device properties) with some of the
	 * device descriptor fields
	 * 
	 * @param deviceDescriptor
	 */
	private void parseDescriptor(DeviceDescriptor deviceDescriptor)
	{
		this.deviceProp.put(DeviceCostants.DEVICE_CATEGORY, defaultPackage + "." + deviceDescriptor.getDevCategory());
		this.deviceProp.put(DeviceCostants.DEVICEURI, deviceDescriptor.getDevURI());
		this.deviceProp.put(DeviceCostants.MANUFACTURER, deviceDescriptor.getDevTechnology());
		this.deviceProp.put(DeviceCostants.DEVICELOCATION, deviceDescriptor.getDevLocation());
		this.deviceId = deviceDescriptor.getDevURI();
		this.deviceDescriptor = deviceDescriptor;
	}
	
	public abstract void unSetDriver(Driver driverRemoved);
	
	public String getDeviceId()
	{
		
		return this.deviceId;
	}
	
}
