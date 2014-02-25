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
package it.polito.elite.dog.addons.xively.client.queue;

import it.polito.elite.dog.addons.xively.client.XivelyClient;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.util.concurrent.ConcurrentLinkedQueue;

import javax.ws.rs.core.MediaType;

import org.osgi.service.log.LogService;

import com.xively.client.AppConfig;
import com.xively.client.AppConfig.AcceptedMediaType;
import com.xively.client.XivelyService;
import com.xively.client.model.Datastream;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class RESTDeliveryQueue extends Thread
{
	// the log id
	public static final String logId = "[RESTDeliveryQueue]: ";
	
	// the queue for handling event notifications
	private ConcurrentLinkedQueue<DatapointInfo> dispatchQueue;
	
	// the warning level for the queue occupation (between 0 and 1)
	private double warningLevel = 0.5;
	private double severeLevel = 1.5;
	
	// the dispatching thread timing in milliseconds
	private int emptyQueueTime = 20;
	private int fullQueueTime = 0;
	
	// the maximum queue size
	private int maxSize = 0;
	
	// the self-tuning flag
	private boolean selfTune = false;
	
	// the self tune list size memory
	private int oldWaitingListSize = 0;
	
	// a flag for starting/stopping the event dispatch thread
	private boolean canDispatch;
	
	// the logger to use
	protected LogHelper logger;
	
	// the COSM key if needed
	private String cosmKey;
	
	// the COSMOutlet
	private XivelyClient outlet;
	
	// the media type
	private MediaType mediaType;
	
	/**
	 * 
	 */
	public RESTDeliveryQueue(LogHelper logger, XivelyClient outlet, MediaType format)
	{
		// store the logger
		this.logger = logger;
		
		// the dispatch flag
		this.canDispatch = false;
		
		// the dispatch queue
		this.dispatchQueue = new ConcurrentLinkedQueue<DatapointInfo>();
		
		// the optional cosm key
		this.cosmKey = outlet.getKey();
		
		// the media type
		this.mediaType = format;
		
		// store the outlet for self-tuning
		this.outlet = outlet;
	}
	
	public RESTDeliveryQueue(LogHelper logger)
	{
		// store the logger
		this.logger = logger;
		
		// the dispatch flag
		this.canDispatch = false;
		
		// the dispatch queue
		this.dispatchQueue = new ConcurrentLinkedQueue<DatapointInfo>();
	}
	
	@Override
	public void run()
	{
		// the event dispatching thread, dispatches events to registered
		// consumers if events are available in the queue...
		// when stopped tries to empty the dispatch queue
		while (this.canDispatch)
		{
			// get the oldest event in the queue
			int sleepTime = this.emptyQueueTime;
			DatapointInfo data = this.dispatchQueue.poll();
			if (data != null)
			{
				this.processData(data);
				sleepTime = this.fullQueueTime;
			}
			
			try
			{
				if (sleepTime == 0)
					Thread.yield();
				else
					Thread.sleep(sleepTime);
			}
			catch (InterruptedException e)
			{
				this.logger.log(LogService.LOG_WARNING, RESTDeliveryQueue.logId + "Interrupted:\n" + e);
			}
		}
		// if it comes here, then the dispatching thread is being stopped and
		// the remaining events must be handled
		DatapointInfo remainingData;
		while ((remainingData = this.dispatchQueue.poll()) != null)
		{
			// try to dispatch the event to all consumers, without stopping
			this.processData(remainingData);
		}
	}
	
	/**
	 * Stops the queue
	 */
	public void stopQueue()
	{
		this.canDispatch = false;
	}
	
	/**
	 * Starts the queue
	 */
	
	public void startQueue()
	{
		if (!this.canDispatch)
			this.canDispatch = true;
		
		this.start();
	}
	
	/**
	 * Inserts one event in the queue (FIFO behavior)
	 * 
	 * @param event
	 */
	public void addDatapoints(DatapointInfo data)
	{
		// self-Limit if needed
		this.selfLimit();
		
		// self-tunes only id enabled
		this.selfTune();
		
		// add the JSON data to the queue
		this.dispatchQueue.add(data);
	}
	
	public int getEmptyQueueTime()
	{
		return emptyQueueTime;
	}
	
	public void setEmptyQueueTime(int emptyQueueTime)
	{
		this.emptyQueueTime = emptyQueueTime;
	}
	
	public int getFullQueueTime()
	{
		return fullQueueTime;
	}
	
	public void setFullQueueTime(int fullQueueTime)
	{
		this.fullQueueTime = fullQueueTime;
	}
	
	public int getMaxSize()
	{
		return maxSize;
	}
	
	public void setMaxSize(int maxSize)
	{
		this.maxSize = maxSize;
	}
	
	public boolean isSelfTune()
	{
		return selfTune;
	}
	
	public void setSelfTune(boolean selfTune)
	{
		this.selfTune = selfTune;
	}
	
	public String getCosmKey()
	{
		return cosmKey;
	}
	
	public void setCosmKey(String cosmKey)
	{
		this.cosmKey = cosmKey;
	}
	
	public XivelyClient getOutlet()
	{
		return outlet;
	}
	
	public void setOutlet(XivelyClient outlet)
	{
		this.outlet = outlet;
	}
	
	/**
	 * @return the mediaType
	 */
	public MediaType getMediaType()
	{
		return mediaType;
	}
	
	/**
	 * @param mediaType
	 *            the mediaType to set
	 */
	public void setMediaType(MediaType mediaType)
	{
		this.mediaType = mediaType;
	}
	
	// --------------------------- PRIVATE METHODS
	// ------------------------------------------------------
	
	private void processData(DatapointInfo data)
	{
		try
		{
			// send the data
			if (this.mediaType.toString().equals(MediaType.APPLICATION_XML))
			{
				// cheng the app config
				AppConfig appConfig = AppConfig.getInstance();
				
				appConfig.setResponseMediaType(AcceptedMediaType.xml);
			}
			
			// debug
			this.logger.log(LogService.LOG_DEBUG, RESTDeliveryQueue.logId + "Sending to feed " + data.getFeed()
					+ ", datatstream" + data.getDatastreamId() + " data: " + data.toString());
			
			// create the data stream object representing the datastream to
			// updated
			Datastream ds = new Datastream();
			
			// set the id to match the datastream to update on xively
			ds.setId(data.getDatastreamId());
			
			// set the datapoints to insert
			ds.setDatapoints(data.getDatapoints());
			
			// set the latest value
			ds.setValue(data.getLast().getValue());
			
			// update on xively
			XivelyService.instance().datastream(data.getFeed()).update(ds);
			
		}
		catch (Exception e)
		{
			// catch any exception
			this.logger
					.log(LogService.LOG_WARNING, RESTDeliveryQueue.logId + "Error while sending cosm request..." + e);
		}
	}
	
	private void selfTune()
	{
		// setup self tuning
		if ((this.selfTune) && (this.maxSize > 0) && (this.dispatchQueue.size() > this.warningLevel * this.maxSize))
		{
			// log
			this.logger.log(LogService.LOG_WARNING, RESTDeliveryQueue.logId
					+ "Reached warning level, starting self-tuning...");
			
			// compute the "ideal" json data size
			double sizeRatio = (double) this.dispatchQueue.size()
					/ (double) this.outlet.getNEventsAlertsInWaitingList();
			double maxRatio = (double) this.maxSize / (double) this.outlet.getNEventsAlertsInWaitingList();
			
			// the ideal ratio would be the one for which there is one JSON
			// object per sensor in the dispatch queue. If the maximum
			// ration is < 1 then
			// the space needed for holding sensor data is not sufficient
			// and there might be a queue saturation problem (or event drop
			// in case of queues with limited size)
			int idealRatio = 1;
			
			if (maxRatio > 1)
			{
				idealRatio = (int) Math.ceil(sizeRatio);
			}
			else if (maxRatio > 0)
			{
				idealRatio = (int) Math.ceil((1.0d / maxRatio));
			}
			
			// tune the JSON data size
			if (this.outlet.getWaitingListSize() < idealRatio)
			{
				// log
				this.logger.log(LogService.LOG_INFO, RESTDeliveryQueue.logId
						+ "Auto-tuning the JSON object size, original size: " + this.outlet.getWaitingListSize()
						+ " new size:" + idealRatio);
				
				synchronized (outlet)
				{
					// store the old-ratio (avoid storing intermediate values)
					if (this.oldWaitingListSize == 0)
						this.oldWaitingListSize = this.outlet.getWaitingListSize();
					
					// update the waiting list size, with saturation on the
					// maximum allowed JSON data size, this of course may still
					// cause
					// data drop if the needed size exceeds the maximum possible
					this.outlet.setWaitingListSize(Math.min(idealRatio, this.outlet.getMaxListSize()));
				}
			}
			else
			{
				// check if there is any old-ratio that can be set
				// this allows keeping the "desired" size of the JSON data while
				// successfully handling overload situations
				if (idealRatio < this.oldWaitingListSize)
				{
					synchronized (outlet)
					{
						if (this.oldWaitingListSize > 0)
						{
							this.outlet.setWaitingListSize(this.oldWaitingListSize);
							this.oldWaitingListSize = 0;
						}
					}
				}
			}
		}
	}
	
	private void selfLimit()
	{
		// check for maximum size, if maxSize=0 the queue is un-limited
		if ((this.maxSize > 0) && (this.dispatchQueue.size() >= this.maxSize))
		{
			// check severe condition
			if (this.dispatchQueue.size() > this.severeLevel * this.maxSize)
			{
				// log
				this.logger.log(LogService.LOG_WARNING, RESTDeliveryQueue.logId
						+ "This delivery queue is over 50% overloaded, ALL events are being discarded...");
				
				// discard all events
				this.dispatchQueue.clear();
			}
			else
			{
				// log
				this.logger.log(LogService.LOG_WARNING, RESTDeliveryQueue.logId
						+ "This delivery queue is full, events are being discarded...");
				// drop elements exceeding the maximum size (the queue works as
				// a
				// circular fifo buffer)
				// ideally this should execute only one cycle
				synchronized (this.dispatchQueue)
				{
					for (int i = 0; i < (this.dispatchQueue.size() - this.maxSize + 1); i++)
						// drop oldest elements
						this.dispatchQueue.poll();
				}
			}
		}
	}
	
}