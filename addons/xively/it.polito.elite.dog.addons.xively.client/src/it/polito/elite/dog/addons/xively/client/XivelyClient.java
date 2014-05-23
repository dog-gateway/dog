/*
 * Dog - Addons
 * 
 * Copyright (c) 2012-2014 Dario Bonino
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
package it.polito.elite.dog.addons.xively.client;

import it.polito.elite.dog.addons.xively.client.queue.DatapointInfo;
import it.polito.elite.dog.addons.xively.client.queue.RESTDeliveryQueue;
import it.polito.elite.dog.core.library.model.notification.AlertNotification;
import it.polito.elite.dog.core.library.model.notification.EventNotification;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.stream.processing.events.GenericEvent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.osgi.service.log.LogService;

import com.xively.client.AppConfig;
import com.xively.client.AppConfig.AcceptedMediaType;
import com.xively.client.XivelyService;
import com.xively.client.http.exception.HttpException;
import com.xively.client.model.Datapoint;
import com.xively.client.model.Datastream;
import com.xively.client.model.DeviceActivation;
import com.xively.client.model.Feed;
import com.xively.client.model.Unit;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class XivelyClient implements ManagedService, EventHandler
{
	// the class configuration properties
	public static final String XIVELY_KEY = "xively.Key";
	public static final String XIVELY_DEFAULT_EVENT_FEED = "xively.events.default";
	public static final String XIVELY_DEFAULT_ALERT_FEED = "xively.alerts.default";
	public static final String XIVELY_WAITING_LIST_SIZE = "xively.waitingList.size";
	public static final String XIVELY_DELIVERY_QUEUE_SIZE = "xively.deliveryQueue.size";
	public static final String XIVELY_DELIVERY_QUEUE_SELFTUNE = "xively.deliveryQueue.selfTune";
	public static final String XIVELY_DELIVERY_QUEUE_TUNING_STEP = "xively.deliveryQueue.tuningStep";
	private static final String XIVELY_MEDIA_TYPE = "xively.mediaType";
	private static final String XIVELY_BASE_URI = "xively.baseURI";
	private static final String XIVELY_SERIAL = "xively.serial";
	private static final String XIVELY_PRODUCT_SECRET = "xively.productSecret";

	// the bundle context
	private BundleContext context;

	// the default size for event delivery through REST
	public static final int defaultListSize = 1;

	// the service logger
	private LogHelper logger;

	// the log id
	public static final String logId = "[XIVELYOutlet]: ";

	// the API key if present
	private String key;

	// the product secret
	private String productSecret;

	// the dog serial number
	private String serial;

	// the supported media type
	private MediaType mediaType;

	// the base uri
	private String baseURI = "api.xively.com/v2/";

	// the waiting list maximum size
	private final int maxListSize = 500; // defined by the XIVELY api

	// the waiting list size
	private int waitingListSize;

	// the delivery queue size
	private int deliveryQueueSize = 0;
	
	// the tuning step
	private double tuningStep = 2.0;

	// the queue self-tuning flag
	private boolean selfTune = false;

	// ---------------- EVENTS -------------------------------

	// the default event feed
	private Integer defaultEventFeed;

	// the event delivery queue
	private RESTDeliveryQueue eventQueue;

	// the temporary cache of events
	private Hashtable<String, Set<Datapoint>> waitingEventList;

	// the existing channels
	private HashSet<String> existingChannels;

	// ---------------- ALERTS -------------------------------

	// the default alert feed
	private Integer defaultAlertFeed;

	// the alert delivery queue
	private RESTDeliveryQueue alertQueue;

	// the temporary cache of events
	private Hashtable<String, Set<Datapoint>> waitingAlertList;

	/**
	 * 
	 */
	public XivelyClient()
	{
		// create the waiting list
		this.waitingEventList = new Hashtable<String, Set<Datapoint>>();
		this.waitingAlertList = new Hashtable<String, Set<Datapoint>>();
		this.existingChannels = new HashSet<String>();
	}

	/**
	 * Handle the bundle activation
	 */
	protected void activate(BundleContext ctx)
	{
		// init the logger with a null logger
		this.logger = new LogHelper(ctx);

		// log the activation
		this.logger.log(LogService.LOG_INFO, XivelyClient.logId
				+ "Activated XIVELYOutlet");

		// store the bundle context
		this.context = ctx;
	}

	/**
	 * Handle the bundle de-activation
	 */
	protected void deactivate()
	{
		if ((this.alertQueue != null) && (this.eventQueue != null))
		{
			// stop queues
			this.alertQueue.stopQueue();
			this.eventQueue.stopQueue();
		}

		this.logger.log(LogService.LOG_INFO, XivelyClient.logId
				+ "Deactivated XIVELYOutlet, queues are shutting down.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.service.event.EventHandler#handleEvent(org.osgi.service.event
	 * .Event)
	 */
	@Override
	public void handleEvent(Event event)
	{

		// debug
		this.logger.log(LogService.LOG_DEBUG, XivelyClient.logId
				+ "Received new measure " + event.getTopic());

		// handle Notification
		Object eventContent = event.getProperty(EventConstants.EVENT);

		// check the notification type
		if (eventContent instanceof EventNotification)
		{
			// extract the inner event
			GenericEvent innerEvent = (GenericEvent) ((EventNotification) eventContent)
					.getEvent();

			// get the base event feed
			Integer eventFeed = this.defaultEventFeed;

			// check if the feed name refers to a cosm feed
			String streamName = innerEvent.getStreamName();
			if (streamName
					.matches("(feed[.][^.^-]+[-]stream[.][^.^-]++)|(stream[.][^.^-]++[-]feed[.][^.^-]+)"))
				eventFeed = this.getFeedFrom(innerEvent, this.defaultEventFeed);

			// handle event notifications
			this.handleRestNotification(eventFeed, innerEvent,
					this.waitingEventList, this.eventQueue);

		}
		else if (eventContent instanceof AlertNotification)
		{
			// get the base event feed
			Integer alertFeed = this.defaultAlertFeed;

			if (((AlertNotification) eventContent).getAlert() instanceof GenericEvent)
			{
				// extract the inner event
				GenericEvent innerEvent = (GenericEvent) ((AlertNotification) eventContent)
						.getAlert();

				// check if the feed name refers to a cosm feed
				String streamName = innerEvent.getStreamName();
				if (streamName
						.matches("(feed[.][^.^-]+[-]stream[.][^.^-]++)|(stream[.][^.^-]++[-]feed[.][^.^-]+)"))
					alertFeed = this.getFeedFrom(innerEvent,
							this.defaultAlertFeed);

				// handle event notifications
				this.handleRestNotification(alertFeed, innerEvent,
						this.waitingEventList, this.eventQueue);
			}
			else
			{
				// handle alert notifications not based on events
				this.handleRestNotification(alertFeed,
						(GenericEvent) ((AlertNotification) eventContent)
								.getAlert(), this.waitingAlertList,
						this.alertQueue);
			}
		}

	}

	/**
	 * Extracts the feed url given a base url and a {@link GenericEvent} with a
	 * stream name encoded as sensorId@feedId
	 * 
	 * @param innerEvent
	 *            The event whose stream name is encoded as sensorId@feedId
	 * @param baseFeedURL
	 *            The base/default eventFeed
	 * @return
	 */
	private Integer getFeedFrom(GenericEvent innerEvent, Integer baseFeedURL)
	{
		// get the feed name
		String feedData[] = innerEvent.getStreamName().split("-");

		// the base url, will be changed if the event refers to a cosm feed
		Integer feedId = baseFeedURL;

		if (feedData.length > 1)
		{

			if (feedData[0].contains("feed."))
			{
				// add the feed id
				feedId = Integer.valueOf(feedData[0].substring(
						feedData[0].indexOf(".") + 1, feedData[0].length()));
				// remove the feed id from the event stream name
				innerEvent.setStreamName(feedData[1].substring(
						feedData[1].indexOf(".") + 1, feedData[1].length()));
			}
			else
			{
				// add the feed id
				feedId = Integer.valueOf(feedData[1].substring(
						feedData[1].indexOf(".") + 1, feedData[1].length()));
				// remove the feed id from the event stream name
				innerEvent.setStreamName(feedData[0].substring(
						feedData[0].indexOf(".") + 1, feedData[0].length()));
			}
		}

		return feedId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.service.cm.ManagedService#updated(java.util.Dictionary)
	 */
	@Override
	public void updated(Dictionary<String, ?> properties)
			throws ConfigurationException
	{
		if (properties != null)
		{
			// get the serial number
			this.serial = (String) properties.get(XivelyClient.XIVELY_SERIAL);
			this.logger.log(LogService.LOG_INFO, "Serial: " + serial);

			// get the product secret
			this.productSecret = (String) properties
					.get(XivelyClient.XIVELY_PRODUCT_SECRET);

			// get the api key, if needed
			this.key = (String) properties.get(XivelyClient.XIVELY_KEY);

			// get the media type
			this.mediaType = MediaType.valueOf((String) properties
					.get(XivelyClient.XIVELY_MEDIA_TYPE));

			// get the base uri
			String uri = (String) properties.get(XivelyClient.XIVELY_BASE_URI);

			if ((uri != null) && (!uri.isEmpty()))
				this.baseURI = uri;

			// get the base feed, if needed
			Object eventFeed = properties
					.get(XivelyClient.XIVELY_DEFAULT_EVENT_FEED);
			this.defaultEventFeed = (eventFeed instanceof String) ? Integer
					.valueOf(((String) eventFeed).trim()) : (Integer) eventFeed;

			// get the base feed, if needed
			Object alertFeed = (String) properties
					.get(XivelyClient.XIVELY_DEFAULT_ALERT_FEED);
			this.defaultAlertFeed = (alertFeed instanceof String) ? Integer
					.valueOf(((String) alertFeed).trim()) : (Integer) alertFeed;

			try
			{
				// get the waiting list size, i.e., the number of events to be
				// aggregated in the same JSON object
				this.waitingListSize = Integer.valueOf((String) properties
						.get(XivelyClient.XIVELY_WAITING_LIST_SIZE));
				this.waitingListSize = Math.min(this.waitingListSize,
						this.maxListSize);

				// get the queue size, if any
				this.deliveryQueueSize = Integer.valueOf((String) properties
						.get(XivelyClient.XIVELY_DELIVERY_QUEUE_SIZE));

				// get the tuning step, if any
				this.tuningStep = Double.valueOf((String) properties
						.get(XivelyClient.XIVELY_DELIVERY_QUEUE_TUNING_STEP));
				
				// get the self-tune flag
				this.selfTune = Boolean.valueOf((String) properties
						.get(XivelyClient.XIVELY_DELIVERY_QUEUE_SELFTUNE));
			}
			catch (NumberFormatException e)
			{
				// default value
				this.waitingListSize = XivelyClient.defaultListSize;
				this.deliveryQueueSize = 0; // unlimited queue
				this.selfTune = false;
			}

			// avoid zero or negative values
			if (this.waitingListSize <= 0)
				this.waitingListSize = XivelyClient.defaultListSize;

			// create the delivery queue
			this.alertQueue = new RESTDeliveryQueue(this.logger, this,
					this.mediaType);
			this.eventQueue = new RESTDeliveryQueue(this.logger, this,
					this.mediaType);

			// setup the queues
			this.alertQueue.setSelfTune(this.selfTune);
			this.alertQueue.setMaxSize(this.deliveryQueueSize);
			this.alertQueue.setTuningStep(this.tuningStep);

			this.eventQueue.setSelfTune(this.selfTune);
			this.eventQueue.setMaxSize(this.deliveryQueueSize);
			this.eventQueue.setTuningStep(this.tuningStep);

			// start the queues
			this.alertQueue.startQueue();
			this.eventQueue.startQueue();

			// update the appConfig
			AppConfig appConfig = AppConfig.getInstance();
			appConfig.setConnectionTimeout(5000);
			appConfig.setResponseMediaType(AcceptedMediaType.json);
			appConfig.setBaseUri(this.baseURI);

			// check the key
			if (((this.key == null) || (this.key.isEmpty()))
					&& ((this.serial != null) && (!this.serial.isEmpty()))
					&& ((this.productSecret != null) && (!this.productSecret
							.isEmpty())))
			{
				// activate the device
				this.activateXivelyDevice();

				// if the key is ok
				if (this.key != null)
					appConfig.setApiKey(this.key);
			}
			else
			{
				appConfig.setApiKey(this.key);
			}
		}
	}

	/**
	 * Activates this dog instance as a xively device
	 */
	private void activateXivelyDevice()
	{
		// ask for activation
		DeviceActivation activationData = XivelyService.instance().activation()
				.activateDevice(this.productSecret, this.serial);

		// check the activation data
		if (activationData != null)
		{
			// get the api key
			String apiKey = activationData.getApikey();
			Integer feedId = activationData.getFeedId();

			if ((apiKey != null) && (!apiKey.isEmpty()) && (feedId != 0))
			{
				// store the data and update the configuration
				this.key = apiKey;
				this.defaultEventFeed = feedId;
				this.defaultAlertFeed = feedId;

				// update the bundle configuration
				ServiceReference<?> configurationAdminReference = this.context
						.getServiceReference(ConfigurationAdmin.class.getName());

				if (configurationAdminReference != null)
				{
					ConfigurationAdmin confAdmin = (ConfigurationAdmin) this.context
							.getService(configurationAdminReference);

					try
					{
						Configuration configuration = confAdmin
								.getConfiguration(XivelyClient.class
										.getPackage().getName());

						Dictionary<String, Object> properties = configuration
								.getProperties();

						properties.put(XivelyClient.XIVELY_DEFAULT_ALERT_FEED,
								feedId.toString());
						properties.put(XivelyClient.XIVELY_DEFAULT_EVENT_FEED,
								feedId.toString());
						properties.put(XivelyClient.XIVELY_KEY, apiKey);

						configuration.update(properties);
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}

	}

	/**
	 * Handles an event notification adding it to the right delivery queue, and
	 * grouping events belonging to the same datastream
	 * 
	 * @param eventFeed
	 * @param innerEvent
	 * @param waitingList
	 * @param queue
	 */
	private void handleRestNotification(Integer eventFeed,
			GenericEvent innerEvent,
			Hashtable<String, Set<Datapoint>> waitingList,
			RESTDeliveryQueue queue)
	{
		// handle event notifications, avoid events with null value
		if ((eventFeed != null) && (innerEvent.getValue() != null))
		{

			// get the corresponding JSONObject if available
			Set<Datapoint> datapointSet = waitingList.get(innerEvent
					.getStreamName());

			if (datapointSet == null)
			{
				// create the object and add it to the waiting list
				datapointSet = new HashSet<Datapoint>();

				// add the datapoint set to the waiting list
				waitingList.put(innerEvent.getStreamName(), datapointSet);
			}

			Datapoint dp = new Datapoint();

			// the date formatter
			SimpleDateFormat sdf;

			if (this.key == null)
				sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			else
				sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

			// add the timestamp
			dp.setAt(sdf.format(innerEvent.getTimestamp().getTime()));

			if (this.key == null)
				// add the value
				dp.setValue(innerEvent.getValueAsMeasure().toString());
			else
				dp.setValue(innerEvent.getValue().toString());

			// add the datapoint
			datapointSet.add(dp);

			// debug
			this.logger.log(LogService.LOG_DEBUG, XivelyClient.logId
					+ "Event count for " + innerEvent.getStreamName() + "="
					+ datapointSet.size() + "\n" + datapointSet);
			// check the list size
			if (datapointSet.size() >= this.waitingListSize)
			{
				// check if the channel exists
				if (!this.existingChannels.contains(innerEvent.getStreamName()))
				{
					try
					{
						this.logger.log(LogService.LOG_DEBUG, "ApiKey"
								+ AppConfig.getInstance().getApiKey());
						XivelyService.instance().datastream(eventFeed)
								.get(innerEvent.getStreamName());

						// update the existing channel
						this.existingChannels.add(innerEvent.getStreamName());
					}
					catch (HttpException e)
					{
						int codeFamily = e.getStatusCode() / 100;
						if (codeFamily == 4)
						{
							// prepare the new data stream
							Datastream ds = new Datastream();

							// set the data stream id
							ds.setId(innerEvent.getStreamName());

							// set the data stream unit
							Unit unit = new Unit();
							unit.setSymbol(innerEvent.getUnitOfMeasure());
							unit.setLabel(innerEvent.getUnitOfMeasure());
							ds.setUnit(unit);

							Feed feed = new Feed();
							feed.setId(eventFeed);
							feed.setDatastreams(Collections.singleton(ds));

							// create the data stream
							XivelyService.instance().feed().update(feed);
						}
					}
				}

				// enqueue the data to be sent via rest
				DatapointInfo data = new DatapointInfo(new Integer(eventFeed),
						innerEvent.getStreamName(), datapointSet);
				queue.addDatapoints(data);

				// remove the data from the waiting list
				waitingList.remove(innerEvent.getStreamName());
			}

		}
	}

	/**
	 * Provide the xively key currently used by this client
	 * 
	 * @return
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * Set the xively key currently used by this client
	 * 
	 * @param key
	 */
	public void setKey(String key)
	{
		this.key = key;
	}

	/**
	 * Provides the current size of the event waiting list, i.e., the number of
	 * events delivered at once.
	 * 
	 * @return
	 */
	public int getWaitingListSize()
	{
		return waitingListSize;
	}

	/**
	 * Sets the current size of the event waiting list, i.e., the number of
	 * events delivered at once.
	 * 
	 * @param waitingListSize
	 */
	public void setWaitingListSize(int waitingListSize)
	{
		this.waitingListSize = waitingListSize;
	}

	/**
	 * Provides the maximum allowed size for data delivery.
	 * 
	 * @return
	 */
	public int getMaxListSize()
	{
		return maxListSize;
	}

	/**
	 * Provides thue number of events in the waiting list, i.e., the number of
	 * events currently stored for the next data delivery.
	 * 
	 * @return
	 */
	public int getNEventsAlertsInWaitingList()
	{
		return Math.max(this.waitingEventList.size(),
				this.waitingAlertList.size());
	}

}
