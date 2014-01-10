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
package it.polito.elite.dog.addons.streamprocessor.queue;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.log.LogService;

import it.polito.elite.dog.addons.streamprocessor.SpChainsOSGi;
import it.polito.elite.dog.core.library.model.notification.AlertNotification;
import it.polito.elite.dog.core.library.model.notification.EventNotification;
import it.polito.elite.dog.core.library.util.EventFactory;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.stream.processing.events.BooleanEvent;
import it.polito.elite.stream.processing.events.GenericEvent;
import it.polito.elite.stream.processing.events.RealEvent;
import it.polito.elite.stream.processing.events.queue.EventQueue;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a> (original
 *         version)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a> (minor
 *         editing)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class OutgoingEventQueue extends EventQueue
{
	// a reference to the event admin service to which events must be delivered
	private EventAdmin eventAdmin;
	
	/**
	 * 
	 */
	public OutgoingEventQueue(LogHelper logger, EventAdmin eventAdmin)
	{
		super(logger);
		this.eventAdmin = eventAdmin;
	}
	
	@Override
	protected void processEvent(GenericEvent event)
	{
		//debug
		this.logger.log(LogService.LOG_DEBUG, SpChainsOSGi.logId+"Outgoing queue size: "+this.dispatchQueue.size());
		
		if (this.eventAdmin != null)
		{
			if(event instanceof RealEvent)
			{
			// create the event notification
			EventNotification notification = new EventNotification(event);
			
			// set the device uri to the name of the output stream
			notification.setDeviceUri(event.getStreamName());
			
			// create the OSGi event
			Event notificationEvent = EventFactory.createEvent(notification, SpChainsOSGi.class.getSimpleName());
			
			// post the event
			this.eventAdmin.postEvent(notificationEvent);
			
			// log debug
			this.logger.log(
					LogService.LOG_DEBUG,
					SpChainsOSGi.logId + "Posted processed event: " + event.getStreamName() + "["
							+ event.getValue() +" "+event.getUnitOfMeasure()+"]");
			}
			else if(event instanceof BooleanEvent)
			{
				// create the event notification
				AlertNotification notification = new AlertNotification(event);
				
				// set the device uri to the name of the output stream
				notification.setDeviceUri(event.getStreamName());
				
				// create the OSGi event
				Event notificationEvent = EventFactory.createEvent(notification, SpChainsOSGi.class.getSimpleName());
				
				// post the event
				this.eventAdmin.postEvent(notificationEvent);
				
				// log debug
				this.logger.log(
						LogService.LOG_DEBUG,
						SpChainsOSGi.logId + "Posted processed alert: " + event.getStreamName() + "["
								+ event.getValue() + "]");
			}
				
		}
	}
	
}
