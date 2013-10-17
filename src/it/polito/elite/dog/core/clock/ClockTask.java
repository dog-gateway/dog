/**
 * 
 */
package it.polito.elite.dog.core.clock;

import it.polito.elite.dog.core.library.model.notification.core.ClockTimeNotification;
import it.polito.elite.dog.core.library.util.EventFactory;

import java.util.TimerTask;

import org.osgi.service.event.EventAdmin;

/**
 * @author bonino
 *
 */
public class ClockTask extends TimerTask
{
	EventAdmin eventAdmin;
	
	public ClockTask(EventAdmin eventAdmin)
	{
		this.eventAdmin = eventAdmin;
	}
	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run()
	{
		//create the clock tick notification
		ClockTimeNotification tick = new ClockTimeNotification();
		
		EventFactory.createEvent(tick,"");
	}

}
