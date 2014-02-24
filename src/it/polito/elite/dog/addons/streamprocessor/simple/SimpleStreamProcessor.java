/*
 * Dog - Addons
 * 
 * Copyright (c) 2014 Dario Bonino
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
package it.polito.elite.dog.addons.streamprocessor.simple;

import it.polito.elite.dog.core.library.model.notification.CloseNotification;
import it.polito.elite.dog.core.library.model.notification.DetectedNotification;
import it.polito.elite.dog.core.library.model.notification.EventNotification;
import it.polito.elite.dog.core.library.model.notification.IsPresentNotification;
import it.polito.elite.dog.core.library.model.notification.MovementCeasedNotification;
import it.polito.elite.dog.core.library.model.notification.MovementDetectedNotification;
import it.polito.elite.dog.core.library.model.notification.NonParametricNotification;
import it.polito.elite.dog.core.library.model.notification.NotDetectedNotification;
import it.polito.elite.dog.core.library.model.notification.NotPresentNotification;
import it.polito.elite.dog.core.library.model.notification.OffNotification;
import it.polito.elite.dog.core.library.model.notification.OnNotification;
import it.polito.elite.dog.core.library.model.notification.OpenNotification;
import it.polito.elite.dog.core.library.model.notification.ParametricNotification;
import it.polito.elite.dog.core.library.model.notification.PressedNotification;
import it.polito.elite.dog.core.library.model.notification.ReleasedNotification;
import it.polito.elite.dog.core.library.stream.source.mapping.SensorDescriptor;
import it.polito.elite.dog.core.library.stream.source.mapping.xml.SensorCollectionType;
import it.polito.elite.dog.core.library.stream.source.mapping.xml.SensorData;
import it.polito.elite.dog.core.library.stream.source.mapping.xml.SourceToDeviceMappingSpecification;
import it.polito.elite.dog.core.library.util.EventFactory;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.stream.processing.events.RealEvent;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Dictionary;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicReference;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.osgi.framework.BundleContext;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.osgi.service.log.LogService;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class SimpleStreamProcessor implements EventHandler, ManagedService
{
	// the static constants used for retrieving configuration parameters
	public static final String MAPPING_FILE = "processor.source.mapping";

	// the service logger
	private LogHelper logger;

	// the event admin service
	private AtomicReference<EventAdmin> eventAdmin;

	// the source mapping file
	private File sourceMappingFile;

	private Hashtable<String, SensorDescriptor> sourceDefinitions;

	/**
	 * 
	 */
	public SimpleStreamProcessor()
	{
		// init the source definitions
		this.sourceDefinitions = new Hashtable<String, SensorDescriptor>();
		
		// init   the atomic references
		this.eventAdmin = new AtomicReference<EventAdmin>();
	}

	/****************************************************************
	 * 
	 * Service Binding
	 * 
	 ****************************************************************/

	/**
	 * Handle the bundle activation
	 */
	protected void activate(BundleContext ctx)
	{
		// init the logger with a null logger
		this.logger = new LogHelper(ctx);

		// log the activation
		this.logger
				.log(LogService.LOG_INFO,
						"Activated SimpleStreamProcessor, processor is now configuring and starting.");
	}

	/**
	 * Handle the bundle de-activation
	 */
	protected void deactivate()
	{
		// if running stop
		if (this.logger != null)
			this.logger.log(LogService.LOG_INFO,
					"Deactivated SpChainsOSGi, processor is shutting down.");

	}

	/**
	 * Handle Event Admin Binding
	 * 
	 * @param eventAdmin
	 */
	protected void setEventAdmin(EventAdmin eventAdmin)
	{
		this.eventAdmin.set(eventAdmin);
	}

	/**
	 * Handle Event Admin UnBinding
	 * 
	 * @param eventAdmin
	 */
	protected void unsetEventAdmin(EventAdmin eventAdmin)
	{
		this.eventAdmin.compareAndSet(eventAdmin, null);
	}

	@Override
	public void updated(Dictionary<String, ?> properties)
			throws ConfigurationException
	{
		if (properties != null)
		{
			// get the configuration parameters
			String mappingFileName = (String) properties
					.get(SimpleStreamProcessor.MAPPING_FILE);

			// debug

			this.logger.log(LogService.LOG_DEBUG, "Mapping file: "
					+ mappingFileName);

			// check absolute vs relative
			this.sourceMappingFile = new File(mappingFileName);
			if (!this.sourceMappingFile.isAbsolute())
				this.sourceMappingFile = new File(
						System.getProperty("configFolder") + "/"
								+ mappingFileName);

			if ((this.sourceMappingFile != null)
					&& (this.sourceMappingFile.exists()))
			{

				// initialize the sources
				this.initSources(this.sourceMappingFile);

			}
			else
			{
				// error

				this.logger
						.log(LogService.LOG_ERROR,
								"Configuration files are missing or empty, the stream processor service will not be running");
			}
		}

	}

	@Override
	public void handleEvent(Event event)
	{
		// filter own notifications
		if ((!(event.containsProperty(EventConstants.BUNDLE_SYMBOLICNAME) && ((String) event
				.getProperty(EventConstants.BUNDLE_SYMBOLICNAME))
				.equalsIgnoreCase(this.getClass().getSimpleName())))
				&& (this.sourceDefinitions != null)
				&& (!this.sourceDefinitions.isEmpty()))
		{
			// debug
			this.logger.log(LogService.LOG_DEBUG, "Received new measure "
					+ event.getTopic());

			// handle Notification
			Object eventContent = event.getProperty(EventConstants.EVENT);

			// the vent to post
			RealEvent eventToPost = null;

			if ((eventContent instanceof ParametricNotification))
			{
				// store the received notification
				ParametricNotification receivedNotification = (ParametricNotification) eventContent;

				// the device uri
				String deviceURI = receivedNotification.getDeviceUri();

				// the notification measure
				Measure<?, Quantity> value = this
						.getNotificationValue(receivedNotification);

				// get the notification name from the topic
				String topic = event.getTopic();
				String notification = topic
						.substring(topic.lastIndexOf('/') + 1);
				// GetQFPARAM
				String qfParams = getNotificationQFParams(receivedNotification);

				// check the notification
				String iuuid = SensorDescriptor.generateInnerUUID(deviceURI,
						notification, qfParams);
				SensorDescriptor registeredSource = this.sourceDefinitions
						.get(iuuid);

				if (registeredSource != null)
				{
					// create the spChains event and post-it to the
					// incoming
					// queue
					eventToPost = new RealEvent(registeredSource.getUid(),
							registeredSource.getUid(),
							GregorianCalendar.getInstance(),
							value.doubleValue(value.getUnit()), value.getUnit()
									.toString());

				}
			}

			else if (eventContent instanceof NonParametricNotification)
			{
				NonParametricNotification receivedNotification = (NonParametricNotification) eventContent;

				// the device uri
				String deviceURI = receivedNotification.getDeviceUri();

				Double value = -1.0;

				// handle supported notifications (binary)
				if ((receivedNotification instanceof OnNotification)
						|| (receivedNotification instanceof MovementDetectedNotification)
						|| (receivedNotification instanceof OpenNotification)
						|| (receivedNotification instanceof IsPresentNotification)
						|| (receivedNotification instanceof DetectedNotification)
						|| (receivedNotification instanceof PressedNotification))
				{
					value = 1.0;
				}
				else if ((receivedNotification instanceof OffNotification)
						|| (receivedNotification instanceof MovementCeasedNotification)
						|| (receivedNotification instanceof CloseNotification)
						|| (receivedNotification instanceof NotPresentNotification)
						|| (receivedNotification instanceof NotDetectedNotification)
						|| (receivedNotification instanceof ReleasedNotification))
				{
					value = 0.0;
				}

				// value >=0 then the notification is supported
				if (value >= 0.0)
				{
					// get the notification name from the topic
					String topic = event.getTopic();
					String notification = topic.substring(topic
							.lastIndexOf('/') + 1);
					// GetQFPARAM
					String qfParams = "";

					// check the notification
					String iuuid = SensorDescriptor.generateInnerUUID(
							deviceURI, notification, qfParams);
					SensorDescriptor registeredSource = this.sourceDefinitions
							.get(iuuid);

					if (registeredSource != null)
					{
						// create the spChains event and post-it to the
						// incoming
						// queue
						eventToPost = new RealEvent(registeredSource.getUid(),
								registeredSource.getUid(),
								GregorianCalendar.getInstance(), value, "");

					}
				}

			}

			if (eventToPost != null)
			{
				// create the event notification
				EventNotification notificationToPost = new EventNotification(
						eventToPost);

				// set the device uri to the name of the output stream
				notificationToPost.setDeviceUri(eventToPost.getStreamName());

				// create the OSGi event
				Event notificationEvent = EventFactory.createEvent(
						notificationToPost,
						SimpleStreamProcessor.class.getSimpleName());

				// post the event
				this.eventAdmin.get().postEvent(notificationEvent);

				// debug
				this.logger.log(LogService.LOG_DEBUG,
						"Sending event to " + eventToPost.getStreamName()
								+ " value " + eventToPost.getValue() + " "
								+ eventToPost.getUnitOfMeasure());
			}
		}

	}

	@SuppressWarnings("unchecked")
	private Measure<?, Quantity> getNotificationValue(
			ParametricNotification receivedNotification)
	{
		// the value, initially null
		Measure<?, Quantity> value = null;

		// get all the notification methods
		Method[] notificationMethods = receivedNotification.getClass()
				.getDeclaredMethods();

		// extract the measure value...
		for (Method currentMethod : notificationMethods)
		{
			if (Measure.class.isAssignableFrom(currentMethod.getReturnType()))
			{
				try
				{
					// read the value
					value = (Measure<?, Quantity>) currentMethod.invoke(
							receivedNotification, new Object[] {});
					break;
				}
				catch (IllegalAccessException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IllegalArgumentException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (InvocationTargetException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	private String getNotificationQFParams(
			ParametricNotification receivedNotification)
	{

		// get all the notification methods
		Field[] notificationFields = receivedNotification.getClass()
				.getDeclaredFields();

		// prepare the buffer for parameters
		StringBuffer qfParams = new StringBuffer();

		// the first flag
		boolean first = true;

		// extract the parameter values...
		for (Field currentField : notificationFields)
		{
			// check the current field to be different from deviceURI and from
			// measure
			if ((!currentField.getName().equals("deviceUri"))
					&& (!currentField.getName().equals("notificationName"))
					&& (!currentField.getName().equals("notificationTopic"))
					&& (!(currentField.getType()
							.isAssignableFrom(Measure.class)))
					&& (currentField.getType().isAssignableFrom(String.class)))
			{
				try
				{
					// append a quote
					if (first)
						first = false;
					else
						qfParams.append(",");

					// suppress access control
					currentField.setAccessible(true);

					// get the value
					qfParams.append(currentField.get(receivedNotification));

					// reset access control
					currentField.setAccessible(false);

				}
				catch (IllegalAccessException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IllegalArgumentException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return qfParams.toString();
	}

	/**
	 * 
	 * @param sourceMappingFile
	 */
	private void initSources(File sourceMappingFile)
	{
		// parse the mapping file
		SensorCollectionType mappingSpec = SourceToDeviceMappingSpecification
				.parseXMLSpecification(sourceMappingFile);

		// generate and store sensor descriptors
		for (SensorData sData : mappingSpec.getSensor())
		{
			// create a sensor descriptor object
			SensorDescriptor desc = new SensorDescriptor(sData.getSensorURI(),
					sData.getSensorQFunctionality(), sData.getSensorQFParams(),
					sData.getUid());

			// store the sensor descriptor
			this.sourceDefinitions.put(desc.getIUUID(), desc);
		}

	}

}
