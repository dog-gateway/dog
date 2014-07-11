/*
 * Dog - Addons
 * 
 * Copyright (c) 2013-2014 Claudio Degioanni, Luigi De Russis, Dario Bonino
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
package it.polito.elite.dog.addons.h2eventstore;

import it.polito.elite.dog.addons.h2eventstore.dao.DeviceDao;
import it.polito.elite.dog.addons.h2eventstore.dao.NotificationDao;
import it.polito.elite.dog.addons.h2eventstore.dao.StateDao;
import it.polito.elite.dog.addons.h2eventstore.db.H2Storage;
import it.polito.elite.dog.addons.storage.EventDataStream;
import it.polito.elite.dog.addons.storage.EventDataStreamSet;
import it.polito.elite.dog.addons.storage.EventStore;
import it.polito.elite.dog.addons.storage.EventStoreInfo;
import it.polito.elite.dog.core.housemodel.api.HouseModel;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.notification.NonParametricNotification;
import it.polito.elite.dog.core.library.model.notification.ParametricNotification;
import it.polito.elite.dog.core.library.model.notification.annotation.NotificationParam;
import it.polito.elite.dog.core.library.model.state.ContinuousState;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.StateValue;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

import javax.measure.Measure;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.BundleTracker;
import org.osgi.util.tracker.BundleTrackerCustomizer;

/**
 * <p>
 * A class implementing a simple event store for the dog gateway. It uses a very
 * simple event persistence schema and can either work as full or temporary
 * storage. It also offers facilities for being loaded with historical data, and
 * for extracting sets of stored events on demand. Data query can either be
 * performed in read mode or in extraction mode. In read mode, data is simply
 * read and no modification is carried on the data repository. Instead in
 * extraction mode, data is read and extracted from the persistent repository
 * (deleted permanently). This second mode is typically used to limit the
 * database size on resource constrained installations.
 * </p>
 * 
 * <p>
 * Limits expressed in total number of measures can also be set, to avoid
 * filling up available disk space in resource constrained installations.
 * </p>
 * 
 * <pre>
 * +--------------------------+         generates           +---------------------+
 * | Continuous Notification  +-----------------------------|        Device       |
 * +--------------------------+                             +---------------------+
 * -id                                                     -id
 * -timestamp                                              -class
 * -unit                                                   -name
 * -value
 * -name
 * -params
 * 
 * +-------------------------+         generates           +---------------------+
 * |  Discrete Notification  +-----------------------------|        Device       |
 * +-------------------------+                             +---------------------+
 * -id                                                     -id
 * -timestamp                                              -class
 * -value                                                  -name
 * -name
 * 
 * 
 * +--------------------------+         generates           +---------------------+
 * | Continuous State         +-----------------------------|        Device       |
 * +--------------------------+                             +---------------------+
 * -id                                                     -id
 * -timestamp                                              -class
 * -unit                                                   -name
 * -value
 * -name
 * -params
 * 
 * +-------------------------+         generates           +---------------------+
 * |  Discrete State         +-----------------------------|        Device       |
 * +-------------------------+                             +---------------------+
 * -id                                                     -id
 * -timestamp                                              -class
 * -value                                                  -name
 * -name
 * </pre>
 * 
 * <pre>
 * Event(**id**,timestamp, value, name, idDevice)
 * RealEvent(**id**,timestamp, value, name, params, idDevice)
 * Device(**id**,class,name)
 * </pre>
 * 
 * @author <a href="mailto:claudiodegio@gmail.com">Claudio Degioanni</a> (first
 *         version)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a> (minor
 *         editing)
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a> (second
 *         version)
 * 
 * 
 */
public class H2EventStore implements EventHandler, ManagedService,
		BundleTrackerCustomizer<Object>, EventStore
{
	// the logger
	private LogHelper logger;

	// the data access objects
	private H2Storage h2Storage;
	private DeviceDao devDao;
	private NotificationDao notifDao;
	private StateDao stateDao;

	// the OSGi bundle context used for service registration
	private BundleContext context;

	// the service registration object to publish services offered by this
	// bundle
	private ServiceRegistration<EventStore> storageService;
	private ServiceRegistration<EventHandler> eventHandler;
	
	// the reference to the Dog House Model interface
	private AtomicReference<HouseModel> houseModel;

	// the data store limit expressed as number of rows, initially unlimited
	// (-1)
	private int maxSize;

	// the event retention mode: replace, drops old events in favor of newer
	// events, drop drops incoming events.
	private DataRetentionMode dataRetention;

	// the event handling mode
	private boolean eventHandlingEnabled;

	// the notifications storage flag
	private boolean storeNotifications;

	// the states storage flag
	private boolean storeStates;

	// the database location
	private String databaseLocation;

	/**
	 * The class constructor, creates an instance of the {@link H2EventStore}.
	 * 
	 */
	public H2EventStore()
	{
		// initialize the inner data structures
		
		this.houseModel = new AtomicReference<>();

		// by default store neither notifications nor states
		this.storeNotifications = false;
		this.storeStates = false;

		// default data retention mode
		this.dataRetention = DataRetentionMode.DROP;

		// default persistent storage size
		this.maxSize = EventStoreInfo.UNLIMITED_SIZE;

		// default event handling
		this.eventHandlingEnabled = true;
	}

	/**
	 * Code performed when the bundle is activated, attaches the log service and
	 * performs the needed OSGi service house keeping.
	 * 
	 * @param context
	 */
	public void activate(BundleContext context)
	{
		// initialize the logger
		this.logger = new LogHelper(context);

		// store the context
		this.context = context;

		// log the activation
		this.logger.log(LogService.LOG_DEBUG,
				"H2 Event Store has been activated...");

		// open a bundle tracker for waiting h2 to start, this could be avoided
		// when using OSGI enterprise.
		BundleTracker<?> bundleTracker = new BundleTracker<>(this.context,
				Bundle.ACTIVE, this);

		bundleTracker.open();
	}

	/**
	 * Code performed when the bundle is deactivated, detaches the log service
	 * and performs the needed OSGi service house keeping.
	 */
	public void deactivate()
	{
		// log the deativation
		this.logger.log(LogService.LOG_DEBUG,
				"H2 Event Store has been deactivated...");

		// deregister the service
		this.unRegisterService();

		// close data access
		if (this.devDao != null)
			this.devDao.close();
		if (this.notifDao != null)
			this.notifDao.close();
		if (this.stateDao != null)
			this.stateDao.close();
		try
		{
			if (this.h2Storage != null)
				this.h2Storage.close();
		}
		catch (SQLException e)
		{
			this.logger.log(LogService.LOG_ERROR,
					"Unable to close the db connection", e);
		}

		// detach the logger
		// this.logger = null;
	}
	
	/**
	 * Bind the {@link HouseModel} service
	 * 
	 * @param houseModel
	 */
	public void addedHouseModel(HouseModel houseModel)
	{
		this.houseModel.set(houseModel);
	}
	
	/**
	 * Remove binding to the {@link HouseModel} service
	 * @param houseModel
	 */
	public void removedHouseModel(HouseModel houseModel)
	{
		this.houseModel.compareAndSet(houseModel, null);
	}

	@Override
	/**
	 * Handles configuration properties
	 */
	public void updated(Dictionary<String, ?> properties)
			throws ConfigurationException
	{
		// check that provided properties are not null or empty
		if ((properties != null) && (!properties.isEmpty()))
		{
			// get the persistent store location
			String databaseLocationAsString = (String) properties
					.get(EventStoreInfo.DB_LOCATION);

			// handle the persistent store initialization
			if ((databaseLocationAsString != null)
					&& (!databaseLocationAsString.isEmpty()))
			{
				// create the event DAO
				this.databaseLocation = databaseLocationAsString;

				// try to init the dao
				initDao(databaseLocation);
			}
			else
			{
				// log the error
				this.logger.log(LogService.LOG_ERROR,
						"Missing configuration param "
								+ EventStoreInfo.DB_LOCATION);
			}

			// get the persistent store location
			String storeNotificationsAsString = (String) properties
					.get(EventStoreInfo.NOTIFICATIONS_ENABLED);

			if ((storeNotificationsAsString != null)
					&& (!storeNotificationsAsString.isEmpty()))
			{
				this.storeNotifications = Boolean
						.valueOf(storeNotificationsAsString);
			}

			else
			{
				// log the error
				this.logger.log(LogService.LOG_ERROR,
						"Missing configuration param "
								+ EventStoreInfo.NOTIFICATIONS_ENABLED);
			}

			// get the persistent store location
			String storeStatesAsString = (String) properties
					.get(EventStoreInfo.STATES_ENABLED);

			if ((storeStatesAsString != null)
					&& (!storeStatesAsString.isEmpty()))
			{
				this.storeStates = Boolean.valueOf(storeStatesAsString);
			}
			else
			{
				// log the error
				this.logger.log(LogService.LOG_ERROR,
						"Missing configuration param "
								+ EventStoreInfo.STATES_ENABLED);
			}

			// get optional parameters
			String retentionModeAsString = (String) properties
					.get(EventStoreInfo.DB_RETENTION_MODE);

			// check not null
			if ((retentionModeAsString != null)
					&& (!retentionModeAsString.isEmpty()))
			{
				try
				{
					this.dataRetention = DataRetentionMode
							.valueOf(retentionModeAsString);
				}
				catch (IllegalArgumentException | NullPointerException e)
				{
					// use the default
					this.dataRetention = DataRetentionMode.DROP;

					// log the error
					this.logger.log(LogService.LOG_WARNING,
							"DataRetentionMode not supported, using: "
									+ this.dataRetention.name());

				}
			}

			// get optional parameters
			String maxSizeAsString = (String) properties
					.get(EventStoreInfo.DB_MAX_SIZE);

			// check not null
			if ((maxSizeAsString != null) && (!maxSizeAsString.isEmpty()))
			{
				try
				{
					this.maxSize = Integer.valueOf(maxSizeAsString);
				}
				catch (IllegalArgumentException | NullPointerException e)
				{
					// use the default
					this.maxSize = EventStoreInfo.UNLIMITED_SIZE;

					// log the error
					this.logger.log(LogService.LOG_WARNING,
							"Max size not supported, using: " + this.maxSize);
				}
			}

			// get optional parameters
			String autoEventHandlingAsString = (String) properties
					.get(EventStoreInfo.DB_MAX_SIZE);
			// check not null
			if ((autoEventHandlingAsString != null)
					&& (!autoEventHandlingAsString.isEmpty()))
			{
				try
				{
					this.eventHandlingEnabled = Boolean
							.valueOf(autoEventHandlingAsString);
				}
				catch (IllegalArgumentException | NullPointerException e)
				{
					// use the default
					this.eventHandlingEnabled = true;

					// log the error
					this.logger.log(LogService.LOG_WARNING,
							"Auto event handling not supported, using: "
									+ this.eventHandlingEnabled);
				}
			}

			// if everything has been accomplished, register the service
			if ((this.h2Storage != null) && (this.devDao != null)
					&& (this.notifDao != null) && (this.stateDao != null)
					&& (storageService == null))
				this.registerService();
		}
	}

	@SuppressWarnings("unchecked")
	private void registerService()
	{
		// register the driver service if not already registered
		if (this.storageService == null)
			this.storageService = (ServiceRegistration<EventStore>) this.context
					.registerService(EventStore.class.getName(), this, null);

		// register the EventHandler service
		Hashtable<String, Object> p = new Hashtable<String, Object>();

		// Add this bundle as a listener of the MonitorAdmin events and of all
		// notifications
		p.put(EventConstants.EVENT_TOPIC, new String[] {
				"org/osgi/service/monitor/MonitorEvent",
				"it/polito/elite/dog/core/library/model/notification/*" });
		this.eventHandler = (ServiceRegistration<EventHandler>) this.context
				.registerService(EventHandler.class.getName(), this, p);
	}

	/**
	 * Unregisters the H2StorageService from the OSGi registry
	 */
	private void unRegisterService()
	{
		if (this.eventHandler != null)
			this.eventHandler.unregister();
		if (this.storageService != null)
			this.storageService.unregister();
	}

	/**
	 * Creates the EventDao used by this {@link H2EventStore} instance to handle
	 * event data persistence.
	 * 
	 * @param databaseLocation
	 */
	private void initDao(String databaseLocation)
	{
		try
		{
			// initialize the h2 storage layer
			this.h2Storage = new H2Storage("jdbc:h2:" + databaseLocation,
					"dog", "");
			this.devDao = new DeviceDao(this.h2Storage, this.context);
			this.notifDao = new NotificationDao(this.devDao, this.h2Storage,
					this.context);
			this.stateDao = new StateDao(this.devDao, this.h2Storage,
					this.context);
			
			//intialize the set of devices
			this.initializePermittedDevices();
		}
		catch (SQLException e)
		{
			// log the error
			this.logger.log(LogService.LOG_ERROR,
					"Impossible to create the EventStore DAO", e);
		}
	}

	private void initializePermittedDevices()
	{
		//get all devices from the house model
		HouseModel hModel = this.houseModel.get();
		
		//check not null
		if(hModel!=null)
		{
			//get the device list
			Vector<DeviceDescriptor> allDevices = hModel.getConfiguration();
			
			//iterate over the device set
			for(DeviceDescriptor device : allDevices)
			{
				if (!this.devDao.isDevicePresent(device.getDeviceURI()))
				{
					this.devDao.insertDevice(device.getDeviceURI(), device.getDeviceCategory(), null);
					this.logger.log(LogService.LOG_DEBUG, "Added device "+device.getDeviceURI()+" to storage");
				}
			}
		}
		
	}

	@Override
	public void handleEvent(Event event)
	{
		if (this.eventHandlingEnabled)
		{
			// debug logging
			this.logger.log(LogService.LOG_DEBUG, "TOPIC: " + event.getTopic());

			// check if the event is a status update
			if ((event.getTopic()
					.equals("org/osgi/service/monitor/MonitorEvent"))
					&& (this.storeStates))
			{
				if (event.getProperty("mon.listener.id") == null)
				{
					// handle states
					if (this.stateDao != null)
					{
						DeviceStatus currentDeviceState = null;
						try
						{
							// Try the deserialization of the DeviceStatus
							// (property mon.statusvariable.value)
							currentDeviceState = DeviceStatus
									.deserializeFromString((String) event
											.getProperty("mon.statusvariable.value"));
						}
						catch (Exception e)
						{
							this.logger.log(LogService.LOG_ERROR,
									"Device status deserialization error "
											+ e.getClass().getSimpleName());
						}

						// handle
						this.handleStates(currentDeviceState);
					}
				}
			}
			else if (this.storeNotifications)
			{
				// handle Notification
				Object eventContent = event.getProperty(EventConstants.EVENT);

				// check if the corresponding dao exists
				if (this.notifDao != null)
				{
					// handle parametric notifications

					if (eventContent instanceof ParametricNotification)
					{
						this.handleParametricNotification((ParametricNotification) eventContent);
					}
					else if (eventContent instanceof NonParametricNotification)
					{
						this.handleNonParametricNotification((NonParametricNotification) eventContent);
					}
				}
			}
		}
	}

	/**
	 * Handles events received through the monitor admin bundle and representing
	 * the last snapshot of a device state.
	 * 
	 * @param currentDeviceState
	 *            The last state snapshot.
	 */
	private void handleStates(DeviceStatus currentDeviceState)
	{
		// If the deserialization works
		if (currentDeviceState != null)
		{
			Map<String, State> allStates = currentDeviceState.getStates();
			for (String stateName : allStates.keySet())
			{
				// handle each different state
				State stateInstance = allStates.get(stateName);

				// check the state type
				if (stateInstance instanceof ContinuousState)
				{
					this.handleContinuousStates(stateName, stateInstance,
							currentDeviceState.getDeviceURI());
				}
				else
				{
					this.handleDiscreteStates(stateName, stateInstance,
							currentDeviceState.getDeviceURI());
				}

				// debug
				logger.log(LogService.LOG_DEBUG,
						"State " + stateName + " and deviceURI-> "
								+ currentDeviceState.getDeviceURI());
			}
		}
	}

	/**
	 * Handles updates for {@link ContinuousState}s, store the state value in
	 * the continuous state db table.
	 * 
	 * @param stateName
	 *            The name of the state.
	 * @param stateInstance
	 *            The Instance of {@link State} representing the state.
	 * @param deviceUri
	 *            The device URI.
	 */
	private void handleContinuousStates(String stateName, State stateInstance,
			String deviceUri)
	{
		// handle continuous state values
		StateValue[] currentStateValue = stateInstance.getCurrentStateValue();

		// insert all values
		for (int i = 0; i < currentStateValue.length; i++)
		{
			// get the state value
			Object value = currentStateValue[i].getValue();

			// check value type
			if (value instanceof Measure<?, ?>)
			{
				// get the features and compose the additional parameters, if
				// needed
				HashMap<String, Object> features = currentStateValue[i]
						.getFeatures();

				StringBuffer stateParams = new StringBuffer();
				boolean first = true;
				for (String featureName : features.keySet())
				{
					if (!featureName.equals("realStateValue"))
					{
						if ((!featureName.isEmpty())
								&& (features.get(featureName) != null)
								&& (!features.get(featureName).toString()
										.isEmpty()))
						{
							if (!first)
								stateParams.append("+");
							else
								first = false;

							stateParams.append(featureName + "-"
									+ features.get(featureName));
						}
					}
				}

				this.stateDao.insertContinuousState(deviceUri, new Date(),
						(Measure<?, ?>) value, stateName,
						stateParams.toString());
			}
		}
	}

	/**
	 * Handles updates for {@link DiscreteState}s, store the state value in the
	 * discrete state db table.
	 * 
	 * @param stateName
	 *            The name of the state.
	 * @param stateInstance
	 *            The Instance of {@link State} representing the state.
	 * @param deviceUri
	 *            The device URI.
	 */
	private void handleDiscreteStates(String stateName, State stateInstance,
			String deviceUri)
	{
		// handle continuous state values
		StateValue[] currentStateValue = stateInstance.getCurrentStateValue();

		// insert all values
		for (int i = 0; i < currentStateValue.length; i++)
		{
			// get the state value
			Object value = currentStateValue[i].getValue();

			this.stateDao.insertDiscreteState(deviceUri, new Date(),
					value.toString(), stateName);

		}
	}

	/**
	 * Handles {@link ParametricNotification}s, store the notification value in
	 * the parametric notification db table.
	 * 
	 * @param receivedNotification
	 *            The notification to handle.
	 * 
	 */
	private void handleParametricNotification(
			ParametricNotification receivedNotification)
	{
		// get the device uri
		String deviceURI = receivedNotification.getDeviceUri();

		// prepare the notification measure
		Measure<?, ?> eventValue = null;
		// generate the notification timestamp
		Date eventTimestamp = new Date();

		// get the notification name from the topic
		String topic = receivedNotification.getNotificationTopic();
		String notificationName = topic.substring(topic.lastIndexOf('/') + 1);

		// Get notification parameters, to use for
		// distinguishing
		// same
		// typed notifications referred to different parameter
		// values.
		String notificationParams = getNotificationParams(receivedNotification);

		// log the error
		this.logger.log(LogService.LOG_DEBUG, "Notification parameters"
				+ notificationParams);

		// handle all low-level events
		eventValue = this.getParametricNotificationValue(receivedNotification);

		// debug
		logger.log(LogService.LOG_DEBUG, "Notification " + notificationName
				+ " and deviceURI-> " + deviceURI + " params-> "
				+ notificationParams);

		// do nothing for null values
		if ((eventValue != null) && (deviceURI != null)
				&& (!deviceURI.isEmpty()))
		{
			// insert the event
			this.notifDao.insertParametricNotification(deviceURI,
					eventTimestamp, eventValue, notificationName,
					notificationParams);
		}
	}

	/**
	 * Handles {@link NonParametricNotification}s, store the notification value
	 * in the non parametric notification db table.
	 * 
	 * @param receivedNotification
	 *            The notification to handle.
	 * 
	 */
	private void handleNonParametricNotification(
			NonParametricNotification receivedNotification)
	{
		// get the device uri
		String deviceURI = receivedNotification.getDeviceUri();

		// generate the notification timestamp
		Date eventTimestamp = new Date();

		// get the notification name from the topic
		String topic = receivedNotification.getNotificationTopic();
		String notificationName = topic.substring(topic.lastIndexOf('/') + 1);

		String notificationValue = this
				.getNonParametricNotificationValue(receivedNotification);

		// debug
		logger.log(LogService.LOG_DEBUG, "Notification " + notificationName
				+ " and deviceURI-> " + deviceURI);

		// do nothing for null values
		if ((notificationValue != null) && (deviceURI != null)
				&& (!deviceURI.isEmpty()))
		{
			// insert the event
			this.notifDao.insertNonParametricNotification(deviceURI,
					eventTimestamp, notificationValue, notificationName);
		}
	}

	/**
	 * Get the value of a {@link NonParametricNotification}
	 * 
	 * @param receivedNotification
	 *            The notification from which the value must be extracted.
	 * @return The notification value as a {@link String}.
	 */
	private String getNonParametricNotificationValue(
			NonParametricNotification receivedNotification)
	{
		String value = "";
		try
		{
			value = (String) receivedNotification.getClass()
					.getField("notificationName").get(null);
		}
		catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value.isEmpty() ? null : value;
	}

	/**
	 * Get the value of a {@link ParametricNotification}
	 * 
	 * @param receivedNotification
	 *            The notification from which the value must be extracted.
	 * @return The notification value as a {@link Measure}
	 */
	private Measure<?, ?> getParametricNotificationValue(
			ParametricNotification receivedNotification)
	{
		// the value, initially null
		Measure<?, ?> value = null;

		// get all the notification methods
		Method[] notificationMethods = receivedNotification.getClass()
				.getDeclaredMethods();

		// extract the measure value...
		for (Method currentMethod : notificationMethods)
		{
			if (currentMethod.getReturnType().isAssignableFrom(Measure.class))
			{
				try
				{
					// read the value
					value = (Measure<?, ?>) currentMethod.invoke(
							receivedNotification, new Object[] {});
					break;
				}
				catch (Exception e)
				{
					this.logger.log(LogService.LOG_ERROR,
							"Error in getting notification value", e);
				}
			}
		}
		return value;
	}

	/**
	 * <p>
	 * Gets the parameters of a notification, encoded in a post-like manner:
	 * </p>
	 * <p>
	 * <code>paramname1-paramvalue1/paramname2-paramvalue2/...</code>
	 * </p>
	 * 
	 * @param receivedNotification
	 *            The notification from which the parameters must be extracted.
	 * @return The parameters.
	 */
	private String getNotificationParams(
			ParametricNotification receivedNotification)
	{
		// get all notfication getters
		Method[] methods = receivedNotification.getClass().getMethods();

		// prepare the buffer for parameters
		StringBuffer qfParams = new StringBuffer();

		// the first flag
		boolean first = true;

		for (Method currentMethod : methods)
		{
			// get only the properly annotated methods
			if (currentMethod.isAnnotationPresent(NotificationParam.class))
			{

				try
				{
					// get the param value
					String methodReturnValue = currentMethod.invoke(
							receivedNotification, (Object[]) null).toString();
					// get the annotation value, i.e., the param name
					String annotationValue = currentMethod.getAnnotation(
							NotificationParam.class).value();

					if ((annotationValue != null)
							&& (!annotationValue.isEmpty())
							&& (methodReturnValue != null)
							&& (!methodReturnValue.isEmpty()))
					{
						// concate the params
						if (!first)
							qfParams.append("+");
						qfParams.append(annotationValue + "-"
								+ methodReturnValue);
					}
				}
				catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e)
				{
					// log the error
					this.logger.log(
							LogService.LOG_ERROR,
							"Unable to extract notification parameters for "
									+ receivedNotification
											.getNotificationTopic(), e);
				}
			}
		}

		return qfParams.toString();
	}

	// ----------------- Bundle tracker customizer --------
	// used to attach the h2 bundle even if not available when the updated
	// method
	// is called, this is useless if using the osgi enterprise specification

	@Override
	public Object addingBundle(Bundle bundle, BundleEvent event)
	{
		if ((bundle.getSymbolicName().equals("org.h2"))
				&& ((h2Storage == null) || (devDao == null)
						|| (notifDao == null) || (stateDao == null))
				&& (this.databaseLocation != null)
				&& (!this.databaseLocation.isEmpty()))
		{
			this.logger.log(LogService.LOG_INFO, "Activated H2");
			Runnable initAndRegister = new Runnable()
			{

				@Override
				public void run()
				{
					initDao(databaseLocation);

					// if everything has been accomplished, register the service
					// if everything has been accomplished, register the service
					if ((h2Storage != null) && (devDao != null)
							&& (notifDao != null) && (stateDao != null)
							&& (storageService == null))
						registerService();

				}
			};

			Thread executor = new Thread(initAndRegister);
			executor.start();

		}
		return null;
	}

	@Override
	public void modifiedBundle(Bundle bundle, BundleEvent event, Object object)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void removedBundle(Bundle bundle, BundleEvent event, Object object)
	{
		// TODO Auto-generated method stub

	}

	// -------------------------- EventStore implementation -------------

	@Override
	public EventDataStreamSet getAllDeviceParametricNotifications(
			String deviceURI, Date startDate, Date endDate, int startCount,
			int nResults)
	{
		return this.notifDao.getAllDeviceParametricNotifications(deviceURI,
				startDate, endDate, startCount, nResults);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.addons.storage.EventStore#getAllDeviceEvents(java
	 * .lang.String, java.util.Date, java.util.Date, int, int, boolean)
	 */
	@Override
	public EventDataStreamSet getAllDeviceNonParametricNotifications(
			String deviceURI, Date startDate, Date endDate, int startCount,
			int nResults, boolean aggregated)
	{
		return this.notifDao.getAllDeviceNonParametricNotifications(deviceURI,
				startDate, endDate, startCount, nResults, aggregated);
	}

	@Override
	public EventDataStream getSpecificDeviceParametricNotifications(
			String deviceURI, String notificationName,
			String notificationParams, Date startDate, Date endDate,
			int startCount, int nResults)
	{
		return this.notifDao.getSpecificDeviceParametricNotifications(
				deviceURI, notificationName, notificationParams, startDate,
				endDate, startCount, nResults);
	}

	@Override
	public EventDataStream getSpecificDeviceNonParametricNotifications(
			String deviceURI, String notificationName, Date startDate,
			Date endDate, int startCount, int nResults)
	{
		return this.notifDao.getSpecificDeviceNonParametricNotifications(
				deviceURI, notificationName, startDate, endDate, startCount,
				nResults);
	}

	@Override
	public EventDataStream getSpecificDeviceNonParametricNotifications(
			String deviceURI, Set<String> notificationNames,
			String eventStreamName, Date startDate, Date endDate,
			int startCount, int nResults)
	{
		return this.notifDao.getSpecificDeviceNonParametricNotifications(
				deviceURI, notificationNames, eventStreamName, startDate,
				endDate, startCount, nResults);
	}

	@Override
	public EventDataStreamSet getSpecificDeviceNonParametricNotifications(
			String deviceURI, Map<String, Set<String>> notificationNames,
			Date startDate, Date endDate, int startCount, int nResults)
	{
		return this.notifDao.getSpecificDeviceNonParametricNotifications(
				deviceURI, notificationNames, startDate, endDate, startCount,
				nResults);
	}

	@Override
	public EventDataStreamSet getAllDeviceContinuousStates(String deviceUri,
			Date startDate, Date endDate, int startCount, int nResults)
	{
		return this.stateDao.getAllDeviceContinuousStates(deviceUri, startDate,
				endDate, startCount, nResults);
	}

	@Override
	public EventDataStreamSet getAllDeviceDiscreteStates(String deviceUri,
			Date startDate, Date endDate, int startCount, int nResults,
			boolean aggregated)
	{
		return this.stateDao.getAllDeviceDiscreteStates(deviceUri, startDate,
				endDate, startCount, nResults, aggregated);
	}

	@Override
	public EventDataStream getSpecificDeviceContinuousStates(String deviceURI,
			String stateName, String stateParams, Date startDate,
			Date endDate, int startCount, int nResults)
	{
		return this.stateDao.getSpecificDeviceContinuousStates(deviceURI,
				stateName, stateParams, startDate, endDate,
				startCount, nResults);
	}

	@Override
	public EventDataStream getSpecificDeviceDiscreteStates(String deviceURI,
			String stateName, Date startDate, Date endDate, int startCount,
			int nResults)
	{
		return this.stateDao.getSpecificDeviceDiscreteStates(deviceURI,
				stateName, startDate, endDate, startCount, nResults);
	}

	@Override
	public void insertParametricNotifications(
			EventDataStreamSet notificationsSet)
	{
		this.notifDao.insertParametricNotifications(notificationsSet);
	}

	@Override
	public void insertNonParametricNotifications(
			EventDataStreamSet notificationSet)
	{
		this.notifDao.insertNonParametricNotifications(notificationSet);
	}

	@Override
	public void insertContinuousStates(EventDataStreamSet stateSet)
	{
		this.stateDao.insertContinuousStates(stateSet);
	}

	@Override
	public void insertDiscreteStates(EventDataStreamSet stateSet)
	{
		this.stateDao.insertDiscreteStates(stateSet);
	}

}