/*
 * Dog - Addons
 * 
 * Copyright (c) 2013-2014 Claudio Degioanni, Luigi De Russis
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

import it.polito.elite.dog.addons.h2eventstore.dao.MeasureDao;
import it.polito.elite.dog.addons.h2eventstore.dao.MeasureDaoImp;
import it.polito.elite.dog.core.library.model.notification.EventNotification;
import it.polito.elite.dog.core.library.model.notification.ParametricNotification;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.stream.processing.addon.event.source.dog.xmlrpc.SensorDescriptor;
import it.polito.elite.stream.processing.addon.event.source.dog.xmlrpc.xml.SensorCollectionType;
import it.polito.elite.stream.processing.addon.event.source.dog.xmlrpc.xml.SensorData;
import it.polito.elite.stream.processing.addon.event.source.dog.xmlrpc.xml.SourceToDeviceMappingSpecification;
import it.polito.elite.stream.processing.events.GenericEvent;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.osgi.framework.BundleContext;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.osgi.service.log.LogService;

public class H2EventStore implements EventHandler, ManagedService
{
	// the logger
	private LogHelper logger;
	
	// the dao
	private MeasureDao dao;
	
	// the OSGi context
	private BundleContext context;
	
	// the source definitions
	private Hashtable<String, SensorDescriptor> sourceDefinitions;
	
	/**
	 * The class constructor, creates an instance of the {@link H2EventStore}
	 * 
	 */
	public H2EventStore()
	{
		// init everything
		this.dao = null;
		
		this.sourceDefinitions = new Hashtable<String, SensorDescriptor>();
	}
	
	public void activate(BundleContext context)
	{
		// init the logger
		this.logger = new LogHelper(context);
		
		// store the context
		this.context = context;
		
		this.logger.log(LogService.LOG_INFO, "Activate H2 Event Store...");
	}
	
	public void deactivate()
	{
		this.logger.log(LogService.LOG_INFO, "Deactivate H2 Event Store...");
		
		this.logger = null;
	}
	
	private void initDao(String databaseLocation)
	{
		try
		{
			// init the dao
			this.dao = new MeasureDaoImp("jdbc:h2:" + databaseLocation, "dog", "", this.context);
		}
		catch (SQLException e)
		{
			this.logger.log(LogService.LOG_ERROR, "Impossible to create the DAO", e);
		}
	}
	
	/**
	 * 
	 * @param sourceMappingFile
	 */
	private void initSources(File sourceMappingFile)
	{
		// parse the mapping file
		SensorCollectionType mappingSpec = SourceToDeviceMappingSpecification.parseXMLSpecification(sourceMappingFile);
		
		// generate and store sensor descriptors
		for (SensorData sData : mappingSpec.getSensor())
		{
			// create a sensor descriptor object
			SensorDescriptor desc = new SensorDescriptor(sData.getSensorURI(), sData.getSensorQFunctionality(),
					sData.getSensorQFParams(), sData.getUid());
			
			// store the sensor descriptor
			this.sourceDefinitions.put(desc.getIUUID(), desc);
		}
		
	}
	
	@Override
	public void updated(Dictionary<String, ?> properties) throws ConfigurationException
	{
		File sourceMappingFile = new File(System.getProperty("configFolder") + "/"
				+ (String) properties.get(Constants.MAPPING_FILE));
		String databaseLocation = (String) properties.get(Constants.DB_LOCATION);
		
		if ((databaseLocation != null) && (!databaseLocation.isEmpty()))
		{
			// create the DAO
			this.initDao(databaseLocation);
		}
		else
		{
			this.logger.log(LogService.LOG_ERROR, "Missing configuration param " + Constants.DB_LOCATION);
		}
		
		if (!sourceMappingFile.isFile())
		{
			this.logger.log(LogService.LOG_ERROR, "Missing configuration param " + Constants.MAPPING_FILE);
		}
		else
		{
			// load mapping file
			initSources(sourceMappingFile);
		}
	}
	
	@Override
	public void handleEvent(Event event)
	{
		// handle Notification
		Object eventContent = event.getProperty(EventConstants.EVENT);
		
		if (this.dao != null && eventContent instanceof ParametricNotification)
		{
			// store the received notification
			ParametricNotification receivedNotification = (ParametricNotification) eventContent;
			
			// the device uri
			String deviceURI = receivedNotification.getDeviceUri();
			
			// the notification measure
			Measure<?, ?> value = null;
			Date timestamp = new Date();
			
			// get the notification name from the topic
			String topic = event.getTopic();
			String notification = topic.substring(topic.lastIndexOf('/') + 1);
			
			// GetQFPARAM
			String qfParams = getNotificationQFParams(receivedNotification);
			
			// handle spChains notifications
			if ((eventContent instanceof EventNotification)
					&& (event.containsProperty(EventConstants.BUNDLE_SYMBOLICNAME) && ((String) event
							.getProperty(EventConstants.BUNDLE_SYMBOLICNAME)).equalsIgnoreCase("SpChainsOSGi")))
			{
				// handle the generic event
				GenericEvent gEvt = (GenericEvent) ((EventNotification) eventContent).getEvent();
				
				// handle cases where the event value is null, typically due to
				// window sizes equal or lower than the sampling period
				if (gEvt.getValue() != null)
				{
					value = gEvt.getValueAsMeasure();
					timestamp = gEvt.getTimestamp().getTime();
				}
			}
			else
			{
				// handle all low-level events
				value = this.getNotificationValue(receivedNotification);
			}
			
			// debug
			logger.log(LogService.LOG_DEBUG, "Notification " + notification + " and deviceURI-> " + deviceURI
					+ " QFParams-> " + qfParams);
			
			// do nothing for null values
			if ((value != null) && (deviceURI != null) && (!deviceURI.isEmpty()))
			{
				// Here "raw" and "virtual devices" must be extracted while all
				// other spChains-generated events shall be discarded
				
				String iuuid = SensorDescriptor.generateInnerUUID(deviceURI, notification, qfParams);
				
				if (sourceDefinitions.containsKey(iuuid))
				{
					SensorDescriptor desc = sourceDefinitions.get(iuuid);
					try
					{
						this.dao.insert(desc.getUid(), value, timestamp);
					}
					catch (SQLException e)
					{
						this.logger.log(LogService.LOG_ERROR, "Error in inserting the value " + value + "in the DB", e);
					}
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private DecimalMeasure<Quantity> getNotificationValue(ParametricNotification receivedNotification)
	{
		// the value, initially null
		DecimalMeasure<Quantity> value = null;
		
		// get all the notification methods
		Method[] notificationMethods = receivedNotification.getClass().getDeclaredMethods();
		
		// extract the measure value...
		for (Method currentMethod : notificationMethods)
		{
			if (currentMethod.getReturnType().isAssignableFrom(Measure.class))
			{
				try
				{
					// read the value
					value = (DecimalMeasure<Quantity>) currentMethod.invoke(receivedNotification, new Object[] {});
					break;
				}
				catch (Exception e)
				{
					this.logger.log(LogService.LOG_ERROR, "Error in getting notification value", e);
				}
			}
		}
		return value;
	}
	
	private String getNotificationQFParams(ParametricNotification receivedNotification)
	{
		
		// get all the notification methods
		Field[] notificationFields = receivedNotification.getClass().getDeclaredFields();
		
		// prepare the buffer for parameters
		StringBuffer qfParams = new StringBuffer();
		
		// the first flag
		boolean first = true;
		
		// extract the parameter values...
		for (Field currentField : notificationFields)
		{
			// check the current field to be different from deviceURI and from
			// measure
			if ((!currentField.getName().equals("deviceUri")) && (!currentField.getName().equals("notificationName"))
					&& (!currentField.getName().equals("notificationTopic"))
					&& (!(currentField.getType().isAssignableFrom(Measure.class)))
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
				catch (Exception e)
				{
					this.logger.log(LogService.LOG_ERROR, "Error in getting notification QF value", e);
				}
			}
			
		}
		return qfParams.toString();
	}
	
}