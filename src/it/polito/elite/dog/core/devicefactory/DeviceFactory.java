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

import it.polito.elite.dog.core.housemodel.api.HouseModel;
import it.polito.elite.dog.core.library.model.AbstractDevice;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import it.polito.elite.dog.core.library.model.devicemodel.DogControllable;
import it.polito.elite.dog.core.library.model.notification.core.AddedNewDeviceNotification;
import it.polito.elite.dog.core.library.model.notification.core.RemovedDeviceNotification;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.device.Device;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.osgi.service.log.LogService;

/**
 * This class listens for the HouseModel and creates (Dog)Device instances at
 * runtime
 * 
 * @author <a href="mailto:emiliano.castellina@polito.it">Emiliano
 *         Castellina</a> (original implementation)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class DeviceFactory implements EventHandler
{
	// class prefix for loading DogDevices at runtime
	private final String classPrefix = DogControllable.class.getPackage().getName();
	// logger
	private LogHelper logger;
	// Bundle context
	private BundleContext context;
	// Reference to the registration of the EventHandler
	private ServiceRegistration<?> srEventHandler;
	// reference to the HouseModel
	private AtomicReference<HouseModel> houseModel = null;
	
	/**
	 * Class constructor.
	 */
	public DeviceFactory()
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
		this.srEventHandler = null;
	}
	
	/**
	 * Unregister all the services...
	 */
	public void unRegister()
	{
		if (this.srEventHandler != null)
		{
			this.srEventHandler.unregister();
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
	 * Handle events related to newly added devices and removed devices (at
	 * runtime)
	 */
	@Override
	public void handleEvent(Event event)
	{
		// get the event topic
		String eventTopic = event.getTopic();
		
		// debug
		this.logger.log(LogService.LOG_DEBUG, "Received event of topic: " + eventTopic);
		
		// request to add a new device
		if (eventTopic.equals(AddedNewDeviceNotification.notificationTopic))
		{
			// log
			this.logger.log(LogService.LOG_INFO, "AddedNewDeviceNotification received");
			
			// get device info
			AddedNewDeviceNotification notification = (AddedNewDeviceNotification) event
					.getProperty(EventConstants.EVENT);
			DeviceDescriptor deviceDescr = notification.getNewDeviceDescriptor();
			String deviceUri = deviceDescr.getDevURI();
			
			// log
			this.logger.log(LogService.LOG_INFO, "Adding " + deviceUri);
			
			// check if the device exists...
			String deviceFilter = String.format("(%s=%s)", DeviceCostants.DEVICEURI, deviceUri);
			try
			{
				ServiceReference<?> references[] = this.context.getServiceReferences(Device.class.getName(),
						deviceFilter);
				if (references == null)
				{
					// it creates the device from a property set
					this.createDeviceFromDeviceDescriptor(deviceDescr);
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
				this.logger.log(LogService.LOG_ERROR, "Exception while creating " + deviceDescr, e);
				
			}
		}
		// request to remove an existing device
		else if (eventTopic.equals(RemovedDeviceNotification.notificationTopic))
		{
			this.removeDevice((RemovedDeviceNotification) event.getProperty(EventConstants.EVENT));
		}
		
	}
	
	/**
	 * Remove a device
	 * 
	 * @param notification
	 *            the RemovedDeviceNotification
	 */
	private void removeDevice(RemovedDeviceNotification notification)
	{
		// get device info
		DeviceDescriptor deviceDescr = notification.getRemovedDeviceDescriptor();
		String deviceUri = deviceDescr.getDevURI();
		
		// log
		this.logger.log(LogService.LOG_INFO, "Removing " + deviceUri);
		
		// get the device from the framework
		String deviceFilter = String.format("(%s=%s)", DeviceCostants.DEVICEURI, deviceUri);
		try
		{
			ServiceReference<?> references[] = this.context.getServiceReferences(Device.class.getName(), deviceFilter);
			if (references != null)
			{
				AbstractDevice device = (AbstractDevice) this.context.getService(references[0]);
				// remove the device!
				device.removeDevice();
				
				// log success
				this.logger.log(LogService.LOG_INFO, "DeviceFactory removed " + deviceUri);
				
				// log a warning if more than a device exists with
				// the same URI (it should be impossible by construction, in any
				// way...)
				if (references.length > 1)
				{
					this.logger.log(LogService.LOG_WARNING, "Found more than one device with the URI:" + deviceDescr
							+ ". Only the first has been removed.");
				}
			}
			else
			{
				// no device found
				this.logger.log(LogService.LOG_WARNING, deviceUri + " not found. Skipping removing procedure...");
			}
		}
		catch (Exception e)
		{
			// error in removing the device
			this.logger.log(LogService.LOG_ERROR, "Exception while destroying " + deviceDescr, e);
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
		Class<?> cls = DeviceFactory.class.getClassLoader().loadClass(
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
}
