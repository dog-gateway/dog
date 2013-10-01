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
package it.polito.elite.dog.core.devicefactory;

import it.polito.elite.dog.core.devicefactory.api.DeviceFactory;
import it.polito.elite.dog.core.housemodel.api.HouseModel;
import it.polito.elite.dog.core.library.model.AbstractDevice;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import it.polito.elite.dog.core.library.model.devicemodel.DogControllable;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.device.Device;
import org.osgi.service.log.LogService;

/**
 * This class listens for the HouseModel and creates (Dog)Device instances at
 * runtime
 * 
 * @author Emiliano Castellina (original implementation)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class DeviceFactoryImpl implements DeviceFactory
{
	// class prefix for loading DogDevices at runtime
	private final String classPrefix = DogControllable.class.getPackage().getName();
	// logger
	private LogHelper logger;
	// Bundle context
	private BundleContext context;
	// Reference to the registration of the DeviceFactory
	private ServiceRegistration<?> srDeviceFactory;
	// reference to the HouseModel
	private AtomicReference<HouseModel> houseModel = null;
	
	/**
	 * Class constructor.
	 */
	public DeviceFactoryImpl()
	{
		this.houseModel = new AtomicReference<HouseModel>();
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
		this.logger = new LogHelper(context);
		
		// if the HouseModel service bind was ok, get and parse the house
		// configuration...
		HouseModel activeModel = houseModel.get();
		if (activeModel != null)
		{
			Vector<DeviceDescriptor> devicesProp = activeModel.getConfiguration();
			this.parseConfigurationMessage(devicesProp);
		}
	}
	
	/**
	 * Deactivate this component (before its unbind)
	 */
	public void deactivate()
	{
		// unregister the EventAdmin service
		this.unRegister();
		
		// set everything to null
		this.logger = null;
		this.context = null;
		this.srDeviceFactory = null;
	}
	
	/**
	 * Unregister all the services...
	 */
	public void unRegister()
	{
		if (this.srDeviceFactory != null)
		{
			this.srDeviceFactory.unregister();
		}
	}
	
	/**
	 * Bind the HouseModel to this component
	 * 
	 * @param houseModel
	 *            the HouseModel service to add
	 */
	public void addedHouseModel(HouseModel houseModel)
	{
		this.houseModel.set(houseModel);
	}
	
	/**
	 * Unbind the HouseModel to this component
	 * 
	 * @param houseModel
	 *            the HouseModel service to remove
	 */
	public void removedHouseModel(HouseModel houseModel)
	{
		this.houseModel.compareAndSet(houseModel, null);
	}
	
	/**
	 * Parse a configuration message and create the DogDevice instances
	 * 
	 * @param devicesProp
	 *            the properties of the devices to create
	 */
	private synchronized void parseConfigurationMessage(Vector<DeviceDescriptor> devicesProp)
	{
		// for each device in the configuration
		for (DeviceDescriptor devProp : devicesProp)
		{
			try
			{
				// instantiate the device
				this.createDeviceFromDeviceDescriptor(devProp);
			}
			catch (Exception e)
			{
				// log the error
				this.logger.log(LogService.LOG_ERROR, devProp.getDevURI() + " gives the exception: ", e);
			}
			
		}
	}
	
	/**
	 * Create a device class from a device descriptor
	 * 
	 * @param devDescriptor
	 *            the device descriptor needed to create a new device
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws IllegalArgumentException
	 */
	private void createDeviceFromDeviceDescriptor(DeviceDescriptor devDescriptor) throws ClassNotFoundException,
			SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException
	{
		// get the class corresponding to the desired DogDevice category
		Class<?> cls = DeviceFactoryImpl.class.getClassLoader().loadClass(
				this.classPrefix + ".Dog" + devDescriptor.getDevCategory());
		
		// create the desired DogDevice instance
		Class<?> partypes[] = new Class[2];
		partypes[0] = BundleContext.class;
		partypes[1] = DeviceDescriptor.class;
		Constructor<?> ct = cls.getConstructor(partypes);
		Object arglist[] = new Object[2];
		arglist[0] = this.context;
		arglist[1] = devDescriptor;
		ct.newInstance(arglist);
		
		// log success
		this.logger.log(LogService.LOG_INFO, "Created class " + devDescriptor.toString());
	}
	
	@Override
	// TODO review
	public void addNewDevice(DeviceDescriptor descriptor)
	{
		// update the model
		houseModel.get().addToConfiguration(descriptor);
		
		String deviceUri = descriptor.getDevURI();
		// log
		this.logger.log(LogService.LOG_INFO, "Adding " + deviceUri);
		
		// check if the device exists...
		String deviceFilter = String.format("(%s=%s)", DeviceCostants.DEVICEURI, deviceUri);
		try
		{
			ServiceReference<?> references[] = this.context.getServiceReferences(Device.class.getName(), deviceFilter);
			if (references == null)
			{
				// it creates the device from a property set
				this.createDeviceFromDeviceDescriptor(descriptor);
			}
			else
			{
				// log a warning
				this.logger.log(LogService.LOG_WARNING, "Device " + deviceUri
						+ " already exist. Skipping its creation...");
			}
		}
		catch (Exception e)
		{
			// error in the device creation
			this.logger.log(LogService.LOG_ERROR, "Exception while creating " + descriptor, e);
			
		}
		
	}
	
	@Override
	// TODO review
	public void removeDevice(String deviceURI)
	{
		// update the model
		this.houseModel.get().removeFromConfiguration(deviceURI);
		
		// log
		this.logger.log(LogService.LOG_INFO, "Removing " + deviceURI);
		
		// get the device from the framework
		String deviceFilter = String.format("(%s=%s)", DeviceCostants.DEVICEURI, deviceURI);
		try
		{
			ServiceReference<?> references[] = this.context.getServiceReferences(Device.class.getName(), deviceFilter);
			if (references != null)
			{
				AbstractDevice device = (AbstractDevice) this.context.getService(references[0]);
				// remove the device!
				device.removeDevice();
				
				// log success
				this.logger.log(LogService.LOG_INFO, "DeviceFactory removed " + deviceURI);
				
				// log a warning if more than a device exists with
				// the same URI (it should be impossible by construction, in any
				// way...)
				if (references.length > 1)
				{
					this.logger.log(LogService.LOG_WARNING, "Found more than one device with the URI:" + deviceURI
							+ ". Only the first has been removed.");
				}
			}
			else
			{
				// no device found
				this.logger.log(LogService.LOG_WARNING, deviceURI + " not found. Skipping removing procedure...");
			}
		}
		catch (Exception e)
		{
			// error in removing the device
			this.logger.log(LogService.LOG_ERROR, "Exception while destroying " + deviceURI, e);
		}
		
	}
	
	// TODO complete
	@Override
	public void updateDevice(DeviceDescriptor descriptor)
	{
		// update the HouseModel
		
		// get the device from the framework
		
		// update the device in the framework
	}
}
