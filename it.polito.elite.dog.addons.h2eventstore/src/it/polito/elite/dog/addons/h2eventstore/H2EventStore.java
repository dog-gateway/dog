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

import it.polito.elite.dog.addons.h2eventstore.dao.EventDaoImpl;
import it.polito.elite.dog.addons.storage.EventDao;
import it.polito.elite.dog.addons.storage.EventStore;
import it.polito.elite.dog.addons.storage.EventStoreInfo;
import it.polito.elite.dog.core.library.model.notification.Notification;
import it.polito.elite.dog.core.library.model.notification.ParametricNotification;
import it.polito.elite.dog.core.library.model.notification.annotation.NotificationParam;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;
import java.util.Dictionary;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.osgi.service.log.LogService;

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
 * +-----------------+         generatesReal       +---------------------+
 * |    RealEvent    +-----------------------------|        Device       |
 * +-----------------+                             +---------------------+
 * -id                                              -id
 * -timestamp                                       -class
 * -unit                                            -name
 * -value
 * -name
 * -params
 * 
 * +-----------------+         generates           +---------------------+
 * |      Event      +-----------------------------|        Device       |
 * +-----------------+                             +---------------------+
 * -id                                              -id
 * -timestamp                                       -class
 * -value                                            -name
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
public class H2EventStore implements EventHandler, ManagedService
{
	// the logger
	private LogHelper logger;

	// the data access object
	private EventDao eventDao;

	// the OSGi bundle context used for service registration
	private BundleContext context;

	// the service registration object to publish services offered by this
	// bundle
	private ServiceRegistration<H2EventStore> h2StorageService;

	// the data store limit expressed as number of rows, initially unlimited
	// (-1)
	private int maxSize;

	// the event retention mode: replace, drops old events in favor of newer
	// events, drop drops incoming events.
	private DataRetentionMode dataRetention;

	// the event handling mode
	private boolean eventHandlingEnabled;

	/**
	 * The class constructor, creates an instance of the {@link H2EventStore}.
	 * 
	 */
	public H2EventStore()
	{
		// initialize the inner data structures

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

		// detach the logger
		this.logger = null;
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
			String databaseLocation = (String) properties
					.get(EventStoreInfo.DB_LOCATION);

			// handle the persistent store initialization
			if ((databaseLocation != null) && (!databaseLocation.isEmpty()))
			{
				// create the event DAO
				this.initDao(databaseLocation);
			}
			else
			{
				// log the error
				this.logger.log(LogService.LOG_ERROR,
						"Missing configuration param "
								+ EventStoreInfo.DB_LOCATION);
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
			if (this.eventDao != null)
				this.registerService();
		}
	}

	@SuppressWarnings("unchecked")
	private void registerService()
	{
		// register the driver service if not already registered
		if (this.h2StorageService == null)
			this.h2StorageService = (ServiceRegistration<H2EventStore>) this.context
					.registerService(EventStore.class.getName(), this, null);
	}

	/**
	 * Unregisters the H2StorageService from the OSGi registry
	 */
	private void unRegisterService()
	{
		if (this.h2StorageService != null)
			this.h2StorageService.unregister();
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
			// initialize the dao
			this.eventDao = new EventDaoImpl("jdbc:h2:" + databaseLocation,
					"dog", "", this.context);
		}
		catch (SQLException e)
		{
			// log the error
			this.logger.log(LogService.LOG_ERROR,
					"Impossible to create the EventStore DAO", e);
		}
	}

	@Override
	public void handleEvent(Event event)
	{
		if (this.eventHandlingEnabled)
		{
			// handle Notification
			Object eventContent = event.getProperty(EventConstants.EVENT);

			// handle parametric notifications
			if (this.eventDao != null
					&& eventContent instanceof ParametricNotification)
			{
				// get the received notification
				ParametricNotification receivedNotification = (ParametricNotification) eventContent;

				// get the device uri
				String deviceURI = receivedNotification.getDeviceUri();

				// prepare the notification measure
				DecimalMeasure<?> eventValue = null;
				// generate the notification timestamp
				Date eventTimestamp = new Date();

				// get the notification name from the topic
				String topic = receivedNotification.getNotificationTopic();
				String notificationName = topic.substring(topic
						.lastIndexOf('/') + 1);

				// Get notification parameters, to use for distinguishing same
				// typed notifications referred to different parameter values.
				String notificationParams = getNotificationParams(receivedNotification);

				// log the error
				this.logger.log(LogService.LOG_DEBUG, "Notification parameters"
						+ notificationParams);

				// handle all low-level events
				eventValue = this.getNotificationValue(receivedNotification);

				// debug
				logger.log(LogService.LOG_DEBUG, "Notification "
						+ notificationName + " and deviceURI-> " + deviceURI
						+ " params-> " + notificationParams);

				// do nothing for null values
				if ((eventValue != null) && (deviceURI != null)
						&& (!deviceURI.isEmpty()))
				{
					// insert the event
					this.eventDao.insertRealEvent(deviceURI, eventTimestamp, eventValue,
							Notification.class.getSimpleName(),
							notificationName, notificationParams);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private DecimalMeasure<Quantity> getNotificationValue(
			ParametricNotification receivedNotification)
	{
		// the value, initially null
		DecimalMeasure<Quantity> value = null;

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
					value = (DecimalMeasure<Quantity>) currentMethod.invoke(
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

					// concate the params
					if (!first)
						qfParams.append("&");
					qfParams.append(annotationValue + "=" + methodReturnValue);
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

}