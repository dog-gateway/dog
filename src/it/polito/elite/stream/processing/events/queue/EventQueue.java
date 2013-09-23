/*
 * Dog - Core
 * 
 * Copyright (c) 2012-2013 Dario Bonino
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
package it.polito.elite.stream.processing.events.queue;

import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.stream.processing.events.GenericEvent;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.osgi.service.log.LogService;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public abstract class EventQueue extends Thread
{
	// the queue for handling event notifications
	protected ConcurrentLinkedQueue<GenericEvent> dispatchQueue;
	
	// the dispatching thread timing in milliseconds
	private int emptyQueueTime = 1;
	private int fullQueueTime = 0;
	
	// a flag for starting/stopping the event dispatch thread
	private boolean canDispatch;
	
	// the logger to use
	protected LogHelper logger;
	
	/**
	 * 
	 */
	public EventQueue(LogHelper logger)
	{
		this.logger = logger;
		this.canDispatch = false;
		this.dispatchQueue = new ConcurrentLinkedQueue<GenericEvent>();
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
			GenericEvent event = this.dispatchQueue.poll();
			if (event != null)
			{
				this.processEvent(event);
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
				this.logger.log(LogService.LOG_WARNING, "[EventQueue]: Interrupted:\n" + e);
			}
		}
		// if it comes here, then the dispatching thread is being stopped and
		// the remaining events must be handled
		GenericEvent remainingEvent;
		while ((remainingEvent = this.dispatchQueue.poll()) != null)
		{
			// try to dispatch the event to all consumers, without stopping
			this.processEvent(remainingEvent);
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
	 * @param event
	 */
	public void addEvent(GenericEvent event)
	{
		this.dispatchQueue.add(event);
	}
	
	
	protected abstract void processEvent(GenericEvent event);
}
