/**
 * 
 */
package it.polito.elite.dog.core.clock;

import java.util.Calendar;
import java.util.Timer;

import org.osgi.service.event.EventAdmin;

/**
 * @author bonino
 *
 */
public class SystemClock
{
	//the needed eventAdmin
	EventAdmin eventAdmin;
	
	//the clock
	Timer clockTimer;
	
	public void activate()
	{
		//create the clock timer
		this.clockTimer = new Timer();

		//compute next clock start
		Calendar now = Calendar.getInstance();
		
		//current millis
		long nowTime = now.getTimeInMillis();
		now.set(Calendar.SECOND, now.get(Calendar.SECOND)+1);
		now.set(Calendar.MILLISECOND,0);
		
		//millis to the next SS:000 instant
		long delay = now.getTimeInMillis()-nowTime;
		
		//start clock
		clockTimer.scheduleAtFixedRate(new ClockTask(eventAdmin), delay, 1000);
	}
	
	public void deactivate()
	{
		//cancle the clock timer
		this.clockTimer.cancel();
	}
}
