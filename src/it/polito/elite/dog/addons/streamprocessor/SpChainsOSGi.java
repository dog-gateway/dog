/*
 * Dog - Addons
 * 
 * Copyright (c) 2011-2014 Dario Bonino, Luigi De Russis
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
package it.polito.elite.dog.addons.streamprocessor;

import it.polito.elite.dog.addons.streamprocessor.queue.IncomingEventQueue;
import it.polito.elite.dog.addons.streamprocessor.queue.OutgoingEventQueue;
import it.polito.elite.dog.core.library.model.notification.EventNotification;
import it.polito.elite.dog.core.library.model.notification.ParametricNotification;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.stream.processing.EsperStreamProcessor;
import it.polito.elite.stream.processing.StreamProcessor;
import it.polito.elite.stream.processing.StreamProcessorSpecification;
import it.polito.elite.stream.processing.addon.event.source.dog.xmlrpc.SensorDescriptor;
import it.polito.elite.stream.processing.addon.event.source.dog.xmlrpc.xml.SensorCollectionType;
import it.polito.elite.stream.processing.addon.event.source.dog.xmlrpc.xml.SensorData;
import it.polito.elite.stream.processing.addon.event.source.dog.xmlrpc.xml.SourceToDeviceMappingSpecification;
import it.polito.elite.stream.processing.addon.jobs.StartJob;
import it.polito.elite.stream.processing.addon.management.ProcessorManager;
import it.polito.elite.stream.processing.addon.management.config.UnitFormatManager;
import it.polito.elite.stream.processing.addon.management.config.UpdateChainTask;
import it.polito.elite.stream.processing.core.EventDrain;
import it.polito.elite.stream.processing.events.GenericEvent;
import it.polito.elite.stream.processing.events.RealEvent;
import it.polito.elite.stream.processing.factory.EsperXMLStreamProcessorFactory;
import it.polito.elite.stream.processing.interfaces.EventConsumer;
import it.polito.elite.stream.processing.xml.StreamProcessingConfigurationType;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Dictionary;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Timer;

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
import org.quartz.CronExpression;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a> (original
 *         version)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a> (minor
 *         editing)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class SpChainsOSGi implements EventHandler, ManagedService, ProcessorManager, EventConsumer
{
	// the static constants used for retrieving configuration parameters
	public static final String MAPPING_FILE = "processor.source.mapping";
	public static final String PROCESSOR_FILE = "processor.XML";
	public static final String PROCESSOR_REFRESH = "processor.XMLRefresh";
	public static final String PROCESSOR_START_AT = "processor.startAt";
	
	// the service logger
	private LogHelper logger;
	
	// the event admin service
	private EventAdmin eventAdmin;
	
	// the log id
	public static final String logId = "[SpChainsOSGi]: ";
	
	// the default refresh time
	public static final int defaultRefreshTimeMillis = 30000;
	
	// the processor file
	private File processorXMLFile;
	
	// the source mapping file
	private File sourceMappingFile;
	
	// the configuration refresh time in milliseconds
	private int refreshTimeMillis;
	
	// the stream processor instance
	private EsperStreamProcessor sp;
	
	// the source definitions
	private Hashtable<String, SensorDescriptor> sourceDefinitions;
	
	// the readiness flag
	private boolean ready;
	
	// the event handling queues
	private IncomingEventQueue inQueue;
	private OutgoingEventQueue outQueue;
	
	/**
	 * The class constructor for common initializations...
	 */
	public SpChainsOSGi()
	{
		// set the ready flag at false
		this.ready = false;
		
		// init sources definition map
		this.sourceDefinitions = new Hashtable<String, SensorDescriptor>();
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
		this.logger.log(LogService.LOG_INFO, SpChainsOSGi.logId
				+ "Activated SpChainsOSGi, processor is now configuring and starting.");
		
		// initialize the units of measure
		UnitFormatManager.SetUnitFormat();
		
	}
	
	/**
	 * Handle the bundle de-activation
	 */
	protected void deactivate()
	{
		// if running stop
		if ((this.sp != null) && (this.sp.isPlugged()))
			this.stop();
		
		if (this.logger != null)
			this.logger.log(LogService.LOG_INFO, SpChainsOSGi.logId
					+ "Deactivated SpChainsOSGi, processor is shutting down.");
		
	}
	
	protected void setEventAdmin(EventAdmin eventAdmin)
	{
		this.eventAdmin = eventAdmin;
	}
	
	protected void unsetEventAdmin(EventAdmin eventAdmin)
	{
		if (this.eventAdmin == eventAdmin)
		{
			this.eventAdmin = null;
		}
	}
	
	@Override
	public void updated(Dictionary<String, ?> properties) throws ConfigurationException
	{
		if (properties != null)
		{
			// get the configuration parameters
			String mappingFileName = (String) properties.get(SpChainsOSGi.MAPPING_FILE);
			String processorFileName = (String) properties.get(SpChainsOSGi.PROCESSOR_FILE);
			String refreshTimeMillisAsString = (String) properties.get(PROCESSOR_REFRESH);
			String startAt = (String) properties.get(PROCESSOR_START_AT);
			
			// debug
			
			this.logger.log(LogService.LOG_DEBUG, SpChainsOSGi.logId + "Mapping file: " + mappingFileName
					+ " Processor file: " + processorFileName + " Refresh time (ms): " + refreshTimeMillisAsString);
			
			// check files
			this.sourceMappingFile = new File(System.getProperty("configFolder") + "/" + mappingFileName);
			this.processorXMLFile = new File(System.getProperty("configFolder") + "/" + processorFileName);
			
			if ((this.sourceMappingFile != null) && (this.processorXMLFile != null)
					&& (this.sourceMappingFile.exists()) && (this.processorXMLFile.exists()))
			{
				
				// file exist, can configure the processor...
				if ((refreshTimeMillisAsString != null) && (!refreshTimeMillisAsString.isEmpty()))
				{
					// check the refresh time
					try
					{
						this.refreshTimeMillis = Integer.valueOf(refreshTimeMillisAsString);
					}
					catch (NumberFormatException e)
					{
						
						this.logger.log(LogService.LOG_WARNING, SpChainsOSGi.logId
								+ "Refresh time cannot be parsed, setting the default refresh time at 30s");
					}
					finally
					{
						this.refreshTimeMillis = SpChainsOSGi.defaultRefreshTimeMillis;
					}
					
					// everything ready,
					
					// initialize the stream processor
					this.initProcessor(this.processorXMLFile, this.refreshTimeMillis);
					
					// initialize the sources
					this.initSources(this.sourceMappingFile);
					
					// initialize the drains
					this.initDrains();
					
					// initialize the queues
					this.inQueue = new IncomingEventQueue(this.logger, this.sp);
					this.outQueue = new OutgoingEventQueue(this.logger, this.eventAdmin);
					
					// handle scheduled start
					
					if (startAt != null)
					{
						try
						{
							// create a cron expression fro the given parameter
							CronExpression cronExp = new CronExpression(startAt);
							
							// get the next valid date
							Date startTime = cronExp.getNextValidTimeAfter(new Date());
							
							// log the start time
							
							this.logger.log(LogService.LOG_INFO, SpChainsOSGi.logId + "Scheduled start at: "
									+ startTime);
							
							// create a new scheduler
							SchedulerFactory sFactory = new StdSchedulerFactory();
							Scheduler scheduler = sFactory.getScheduler();
							
							// create a simple trigger
							SimpleTrigger trigger = new SimpleTrigger();
							// set the trigger name
							trigger.setName("startTrigger");
							// set the trigger to start at the next valid date
							trigger.setStartTime(startTime);
							// set fire count at 1 (just one call to start())
							trigger.setRepeatCount(0);
							trigger.setRepeatInterval(10);
							
							// define a job to start (StartJob, basically calls
							// the
							// spManager.start()) method)
							JobDetail jobDetail = new JobDetail("start", "1", StartJob.class);
							
							jobDetail.getJobDataMap().put("managerToStart", this);
							
							scheduler.scheduleJob(jobDetail, trigger);
							
							// start the scheduler
							scheduler.start();
						}
						catch (Exception e)
						{
							// log the error
							this.logger.log(LogService.LOG_WARNING, SpChainsOSGi.logId
									+ "Unable to start at scheduled time, starting immediately...");
							
							// direct start
							this.start();
						}
						
					}
					else
					{
						// direct start
						this.start();
					}
					
					this.ready = true;
					
				}
				
			}
			else
			{
				// error
				
				this.logger.log(LogService.LOG_ERROR, SpChainsOSGi.logId
						+ "Configuration files are missing or empty, the stream processor service will not be running");
			}
		}
		
	}
	
	@Override
	public void handleEvent(Event event)
	{
		if ((this.sp != null) && (this.sp.isPlugged()))
		{
			// filter own notifications
			if (!(event.containsProperty(EventConstants.BUNDLE_SYMBOLICNAME) && ((String) event
					.getProperty(EventConstants.BUNDLE_SYMBOLICNAME)).equalsIgnoreCase(this.getClass().getSimpleName())))
			{
				// debug
				
				this.logger.log(LogService.LOG_DEBUG, SpChainsOSGi.logId + "Received new measure " + event.getTopic());
				
				// handle Notification
				Object eventContent = event.getProperty(EventConstants.EVENT);
				
				if ((this.sp != null) && (this.sp.isPlugged()) && (eventContent instanceof ParametricNotification))
				{
					if (eventContent instanceof EventNotification)
					{
						// assume the event can be directly fed to the sp
						// processor...
						EventNotification receivedNotification = (EventNotification) eventContent;
						
						// extract the inner event
						if (receivedNotification.getEvent() instanceof GenericEvent)
						{
							GenericEvent innerEvent = (GenericEvent) receivedNotification.getEvent();
							
							this.inQueue.addEvent(innerEvent);
							
							// debug
							this.logger.log(LogService.LOG_DEBUG,
									SpChainsOSGi.logId + "Received notification from " + innerEvent.getSrc()
											+ " value " + innerEvent.getValue() + " " + innerEvent.getUnitOfMeasure()
											+ " notification " + EventNotification.class.getSimpleName());
						}
						
					}
					else
					{
						// store the received notification
						ParametricNotification receivedNotification = (ParametricNotification) eventContent;
						
						// the device uri
						String deviceURI = receivedNotification.getDeviceUri();
						
						// the notification measure
						Measure<?, Quantity> value = this.getNotificationValue(receivedNotification);
						
						// get the notification name from the topic
						String topic = event.getTopic();
						String notification = topic.substring(topic.lastIndexOf('/') + 1);
						// GetQFPARAM
						String qfParams = getNotificationQFParams(receivedNotification);
						
						// check the notification
						String iuuid = SensorDescriptor.generateInnerUUID(deviceURI, notification, qfParams);
						SensorDescriptor registeredSource = this.sourceDefinitions.get(iuuid);
						
						if (registeredSource != null)
						{
							// create the spChains event and post-it to the
							// incoming
							// queue
							RealEvent eventToPost = new RealEvent(registeredSource.getUid(), registeredSource.getUid(),
									GregorianCalendar.getInstance(), value.doubleValue(value.getUnit()), value
											.getUnit().toString());
							
							// post the event
							this.inQueue.addEvent(eventToPost);
							
							// debug
							this.logger.log(LogService.LOG_DEBUG, SpChainsOSGi.logId + "Received notification from "
									+ deviceURI + " value " + value + " notification " + notification);
						}
					}
				}
				
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private Measure<?, Quantity> getNotificationValue(ParametricNotification receivedNotification)
	{
		// the value, initially null
		Measure<?, Quantity> value = null;
		
		// get all the notification methods
		Method[] notificationMethods = receivedNotification.getClass().getDeclaredMethods();
		
		// extract the measure value...
		for (Method currentMethod : notificationMethods)
		{
			if (Measure.class.isAssignableFrom(currentMethod.getReturnType()))
			{
				try
				{
					// read the value
					value = (Measure<?, Quantity>) currentMethod.invoke(receivedNotification, new Object[] {});
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
	 * @return the eventAdmin
	 */
	public EventAdmin getEventAdmin()
	{
		return eventAdmin;
	}
	
	/**
	 * 
	 * @param spConfigXMLFile
	 * @param configRefreshMillis
	 */
	private void initProcessor(File spConfigXMLFile, int configRefreshMillis)
	{
		// get the processor specification
		StreamProcessingConfigurationType streamConfig = StreamProcessorSpecification
				.parseXMLSpecification(spConfigXMLFile);
		
		// get a stream processor factory
		EsperXMLStreamProcessorFactory factory = new EsperXMLStreamProcessorFactory();
		
		// get the stream processor
		this.sp = (EsperStreamProcessor) factory.getStreamProcessor(streamConfig);
		
		// set-up a chain update watch dog
		Timer processorWatchDog = new Timer();
		processorWatchDog.schedule(new UpdateChainTask(spConfigXMLFile, this.sp), configRefreshMillis,
				configRefreshMillis);
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
	
	private void initDrains()
	{
		if (this.sp != null)
		{
			// get the processor drains
			Collection<EventDrain> allDrains = this.sp.getAllDrains();
			
			// iterate over the drains
			for (EventDrain drain : allDrains)
			{
				// separate consumer per drain to maximize concurrency
				drain.addConsumer(this);
			}
		}
	}
	
	@Override
	public void connect()
	{
		// Not used
		
	}
	
	@Override
	public void disconnect()
	{
		// Not used
	}
	
	@Override
	public boolean isConnected()
	{
		// Not used, default value
		return true;
	}
	
	@Override
	public StreamProcessor getSP()
	{
		return this.sp;
	}
	
	@Override
	public boolean isReady()
	{
		return this.ready;
	}
	
	@Override
	public void start()
	{
		// plug the processor
		this.sp.plug();
		
		// start the queues
		this.inQueue.startQueue();
		this.outQueue.startQueue();
		
	}
	
	@Override
	public void stop()
	{
		// stop the queues
		this.inQueue.stopQueue();
		this.outQueue.stopQueue();
		
		// unplug
		this.sp.unPlug();
	}
	
	@Override
	public void newEvent(GenericEvent event)
	{
		if ((event.getValue() != null) && (event.getUnitOfMeasure() != null))
			// enqueue the event
			this.outQueue.addEvent(event);
	}
	
}
