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
package it.polito.elite.dog.core.library.model;

import it.polito.elite.dog.core.library.model.devicecategory.Controllable;
import it.polito.elite.dog.core.library.model.notification.Notification;
import it.polito.elite.dog.core.library.util.EventFactory;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.device.Device;
import org.osgi.service.device.Driver;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.log.LogService;
import org.osgi.service.monitor.MonitorListener;
import org.osgi.service.monitor.Monitorable;
import org.osgi.service.monitor.StatusVariable;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * This abstract class represents a Device controllable by Dog. This class is
 * used as a superclass for the device implementations. The device classes are
 * directly generated from the ontology.
 * 
 * @author <a href="mailto:emiliano.castellina@polito.it">Emiliano
 *         Castellina</a> (original implementation)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public abstract class AbstractDevice extends ControllableDevice implements
		ServiceTrackerCustomizer<Object, Object>, Monitorable
{
	// Reference to the driver. Null when any driver is attached
	protected Object driver;
	// OSGi framework context
	protected BundleContext context;
	// Reference to the MonitorListener
	protected MonitorListener monitorListener;

	// / Reference to a ServiceTracker listening for the EventAdmin service
	protected ServiceTracker<?, ?> trackerEventAdmin;
	// Reference to a ServiceTracker listening for the MonitorListener service
	protected ServiceTracker<?, ?> trackerMonitorListener;

	// Monitorable service registration in the OSGi framework
	protected ServiceRegistration<?> serviceRegMonitorable;
	// Service registration for interacting with the device (after the
	// DeviceManager matching)
	protected ServiceRegistration<?> serviceRegCommand;
	// Service registration in the OSGi framework (for the DeviceManager
	// matching, only) */
	volatile private ServiceRegistration<?> serviceRegDevice;

	// OSGi logger
	LogHelper logger;

	Timer delayedAttachment = new Timer();

	volatile boolean changedProperties = true;

	/**
	 * Class constructor
	 * 
	 * @param context
	 *            OSGi context
	 */
	public AbstractDevice(BundleContext context)
	{
		// init
		this.context = context;
		this.logger = new LogHelper(context);
		// create and open a ServiceTracker for the EventAdmin service
		this.trackerEventAdmin = new ServiceTracker<Object, Object>(context,
				EventAdmin.class.getName(), null);
		this.trackerEventAdmin.open();
		// create and open a ServiceTracker for the MonitorLister service
		this.trackerMonitorListener = new ServiceTracker<Object, Object>(
				context, MonitorListener.class.getName(), this);
		this.trackerMonitorListener.open();

	}

	/**
	 * Alternative class constructor. It calls the standard constructor and set
	 * the device properties
	 * 
	 * @param context
	 *            OSGi context
	 * @param dogDeviceDescriptor
	 *            the device properties to set
	 */
	public AbstractDevice(BundleContext context,
			DeviceDescriptor dogDeviceDescriptor)
	{
		this(context);
		this.setDeviceProperties(dogDeviceDescriptor);
	}

	@Override
	public Object addingService(ServiceReference<Object> reference)
	{
		// get the object that implements one of the interfaces the bundle is
		// listening for
		Object serviceInstance = this.context.getService(reference);

		// check its type
		if (serviceInstance instanceof MonitorListener)
		{
			// store a reference of the MonitorAdmin MonitorListener
			monitorListener = (MonitorListener) serviceInstance;
		}
		return serviceInstance;
	}

	@Override
	public void modifiedService(ServiceReference<Object> reference,
			Object service)
	{
		// intentionally left empty

	}

	@Override
	public void removedService(ServiceReference<Object> reference,
			Object service)
	{
		// check if the removed service is equals to the current referenced
		// object
		if (service == this.monitorListener)
		{
			this.monitorListener = null;
		}
	}

	@Override
	public void setDriver(Object obj)
	{
		this.driver = obj;
		this.updateProperties();

		// Search the command device interface
		for (Class<?> currentInterface : this.getClass().getInterfaces())
		{
			// The specific command interface extends Controllable
			if (Controllable.class.isAssignableFrom(currentInterface))
			{
				// Register the device service with the command device interface
				// (and no properties in order to avoid the DeviceManager
				// intervention)
				this.serviceRegCommand = this.context.registerService(
						currentInterface.getName(), this, null);
			}
		}

		// Create the property hashtable
		Hashtable<String, String> properties = new Hashtable<String, String>();
		// Add the pid property to the Monitorable service
		properties.put("service.pid", AbstractDevice.toMonitorableId(this.deviceId));
		// Register a Monitorable service (MonitorAdmin bundle)
		this.serviceRegMonitorable = context.registerService(
				Monitorable.class.getName(), this, properties);

	}

	public void updateProperties()
	{
		if (this.serviceRegDevice == null)
		{

			delayedAttachment.schedule(new TimerTask()
			{

				@Override
				public void run()
				{

					updateProperties();

				}
			}, 1000);

		}
		else
		{

			delayedAttachment.cancel();
			this.deviceProp.put(DeviceCostants.ACTIVE, "true");

			this.serviceRegDevice.setProperties(this.deviceProp);
			if (!this.serviceRegDevice.getReference()
					.getProperty(DeviceCostants.ACTIVE).equals("true"))
			{
				updateProperties();
			}
			this.logger.log(LogService.LOG_INFO, "ACTIVATED DRIVER "
					+ this.driver + " for device " + this.deviceId + " \n");
		}
	}

	protected void registerDevice(String deviceClass)
	{

		try
		{
			// register the device
			this.serviceRegDevice = this.context.registerService(
					Device.class.getName(), this, this.deviceProp);
			this.logger.log(LogService.LOG_INFO, "REGISTERED DEVICE "
					+ this.serviceRegDevice);

		}
		catch (Exception e)
		{
			this.logger.log(LogService.LOG_ERROR,
					"DEVICE REGISTRATION EXCEPTION ", e);
		}

	}

	/**
	 * Detach the driver to the device
	 * 
	 * @param driverRemoved
	 *            driver that can detach the device
	 * @param remove
	 *            if true remove the device
	 */
	public void unSetDriver(Driver driverRemoved)
	{
		if (this.driver.equals(driverRemoved))
		{
			this.logger.log(LogService.LOG_INFO, "DEACTIVATED DRIVER for "
					+ this.deviceId);

			// unregister the command device service
			if (this.serviceRegCommand != null)
			{
				this.serviceRegCommand.unregister();
			}

			// unregister the Monitorable service
			if (this.serviceRegMonitorable != null)
			{
				this.serviceRegMonitorable.unregister();
			}

			this.driver = null;

			this.deviceProp.put(DeviceCostants.ACTIVE, "false");
			if (this.serviceRegDevice != null)
			{
				this.serviceRegDevice.setProperties(this.deviceProp);
			}

		}

	}

	public void removeDevice()
	{
		this.logger.log(LogService.LOG_INFO, "UNREGISTERING device "
				+ this.deviceId);

		// unregister the command device service
		if (this.serviceRegCommand != null)
		{
			this.serviceRegCommand.unregister();
		}

		// unregister the Monitorable service
		if (this.serviceRegMonitorable != null)
		{
			this.serviceRegMonitorable.unregister();
		}

		// unregister the device service (DeviceManager)
		if (this.serviceRegDevice != null)
		{
			this.serviceRegDevice.unregister();
		}
	}

	/**
	 * Get all the states (DeviceStatus) of the device. It call the getStates
	 * method on the driver
	 * 
	 * @return map containing the name of the state and the State objects
	 */
	public abstract Object getState();

	/**
	 * Get all the states of the device. It call the getStates method on the
	 * driver
	 * 
	 * @return map containing the name of the state and the State objects
	 */
	public Object getInnerState()
	{
		DeviceStatus state = null;
		if (this.driver != null && this.driver instanceof StatefulDevice)
		{
			StatefulDevice dogDriver = (StatefulDevice) this.driver;
			state = dogDriver.getState();

		}
		return state;
	}

	/* Notification: StateChangeNotification */
	public void updateStatus()
	{
		// When the device is notified, retrieve the DeviceStatus
		DeviceStatus deviceStatus = ((DeviceStatus) this.getState());
		// Notify the MonitorAdmin about the new DeviceStatus
		notifyMonitorAdmin(deviceStatus);

	}

	/**
	 * Notify to the event admin a just happened event 
	 * 
	 * @param eventTopic
	 *            string representing the type of notification, e.g.
	 *            it/polito/elite
	 *            /Notification/NonParametricNotification/ButtonNotification
	 *            /pressed
	 * @param eventProp
	 *            Properties associated to the event
	 */
	protected void notify(String eventTopic, Map<String, Object> eventProp)
	{
		EventAdmin ea = (EventAdmin) this.trackerEventAdmin.getService();
		
		// add standard properties:
		
		// --add device uri 
		eventProp.put(DeviceCostants.DEVICEURI, this.deviceId);

		// --add event name 
		eventProp.put(org.osgi.service.event.EventConstants.EVENT_TOPIC,
				eventTopic);

		GregorianCalendar calendar = new GregorianCalendar();
		eventProp
				.put(org.osgi.service.event.EventConstants.TIMESTAMP, calendar);

		if (ea != null)
		{
			ea.postEvent(new Event(eventTopic, eventProp));
		}

	}

	/**
	 * Method for sending an event to the OSGi Event Admin
	 * 
	 * @param notification
	 *            the notification to deliver
	 */
	protected void notifyEventAdmin(Notification notification)
	{
		// Retrieve a reference of the EventAdmin service
		EventAdmin ea = (EventAdmin) this.trackerEventAdmin.getService();
		// Create the event through the DogEventFactory
		Event notificationEvent = EventFactory.createEvent(notification, this
				.getClass().getSimpleName());

		if (ea != null)
		{
			// Send the event to the EventAdmin
			ea.postEvent(notificationEvent);
		}
	}

	/**
	 * Method for sending a notification to the OSGi Monitor Admin when the
	 * DeviceStatus changes
	 * 
	 * @param deviceStatus
	 *            the changed device status
	 */
	protected void notifyMonitorAdmin(DeviceStatus deviceStatus)
	{
		// The DeviceStatus is serialized into a String (using a byte encoding)
		String deviceStatusSerialization = null;
		// TODO Check why this exception is generated, sometimes...
		try
		{
			deviceStatusSerialization = DeviceStatus
					.serializeToString(deviceStatus);
			// For each DeviceStatus is generated a StatusVariable object
			// notified through the MonitorListener service with updated method
			this.monitorListener.updated(AbstractDevice.toMonitorableId(this.deviceId),
					new StatusVariable("status", StatusVariable.CM_SI,
							deviceStatusSerialization));
		}
		catch (Exception e)
		{
			logger.log(LogService.LOG_ERROR,
					"DeviceStatus notification error for device " + deviceId
							+ ": " + e);
		}
	}

	@Override
	public void noDriverFound()
	{
		this.logger.log(LogService.LOG_INFO, this.deviceId
				+ " any drivers found!");

	}

	@Override
	public String[] getStatusVariableNames()
	{
		String[] statusVariableName = new String[1];
		statusVariableName[0] = "status";
		return statusVariableName;
	}

	@Override
	public StatusVariable getStatusVariable(String id)
			throws IllegalArgumentException
	{
		// Check that the id arguments is "status"
		if (id.equals("status"))
		{
			// Retrieve the DeviceStatus
			DeviceStatus deviceStatus = ((DeviceStatus) this.getState());
			// The DeviceStatus is serialized into a String (using XML encoding)
			String deviceStatusSerialization = "";
			try
			{
				deviceStatusSerialization = DeviceStatus
						.serializeToString(deviceStatus);
			}
			catch (IOException e)
			{
				logger.log(LogService.LOG_ERROR, this.deviceId
						+ " serialization error!");
			}
			// For each DeviceStatus is generated and returned a StatusVariable
			// object
			return new StatusVariable("status", StatusVariable.CM_SI,
					deviceStatusSerialization);
		}
		else
			throw new IllegalArgumentException("Invalid Status Variable name "
					+ id);
	}

	@Override
	public boolean notifiesOnChange(String id) throws IllegalArgumentException
	{
		return true;
	}

	@Override
	public boolean resetStatusVariable(String id)
			throws IllegalArgumentException
	{
		return false;
	}

	@Override
	public String getDescription(String id) throws IllegalArgumentException
	{
		if ("status".equals(id))
			return this.getDeviceDescriptor().getDescription();
		else
			throw new IllegalArgumentException("Invalid Status Variable name "
					+ id);
	}

	/**
	 * Gets a monitorable unique and with a maximum length of 32 bytes
	 * 
	 * @param deviceId
	 * @return
	 */
	public static String toMonitorableId(String deviceId)
	{
		String id = null;
		byte[] hash = null;

		try
		{
			hash = MessageDigest.getInstance("MD5").digest(deviceId.getBytes());
			BigInteger bi = new BigInteger(1, hash);

			id = String.format("%0" + (hash.length << 1) + "x", bi);
		}
		catch (NoSuchAlgorithmException e)
		{

		}

		return id;
	}
}
