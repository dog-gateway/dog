/*
 * Dog - Core
 * 
 * Copyright (c) 2013- Dario Bonino
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
package it.polito.elite.dog.core.clock;

import it.polito.elite.dog.core.library.model.notification.core.ClockTimeNotification;
import it.polito.elite.dog.core.library.util.EventFactory;

import java.util.Calendar;
import java.util.TimerTask;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

/**
 * @author bonino
 *
 */
public class ClockTask extends TimerTask
{
	private EventAdmin eventAdmin;
	private String ownerSymbolicName;
	private Calendar lastTick;
	
	public ClockTask(EventAdmin eventAdmin, String ownerSymbolicName)
	{
		this.eventAdmin = eventAdmin;
		this.ownerSymbolicName = ownerSymbolicName;
	}
	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run()
	{
		//create the clock tick notification
		ClockTimeNotification tick = new ClockTimeNotification();
		
		//create the event to send
		Event clockTickEvent = EventFactory.createEvent(tick,this.ownerSymbolicName);
		
		//send the event
		this.eventAdmin.postEvent(clockTickEvent);
		
		//store the last tick
		this.lastTick = tick.getClockTick();
	}
	
	public Calendar getLastTick()
	{
		return lastTick;
	}
	
}
