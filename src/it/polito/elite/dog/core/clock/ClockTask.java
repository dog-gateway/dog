/**
 * 
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
