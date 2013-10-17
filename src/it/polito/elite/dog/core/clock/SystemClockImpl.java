/**
 * 
 */
package it.polito.elite.dog.core.clock;

import it.polito.elite.dog.core.clock.api.SystemClock;

import java.util.Calendar;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicReference;

import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventAdmin;

/**
 * @author bonino
 *
 */
public class SystemClockImpl implements SystemClock
{
	//the needed eventAdmin
	private AtomicReference<EventAdmin> eventAdmin;
	
	//the clock timer
	private Timer clockTimer;
	
	//the clock
	private ClockTask clock;
	
	public SystemClockImpl()
	{
		this.eventAdmin = new AtomicReference<EventAdmin>();
	}
	
	public void activate(BundleContext ctx)
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
		this.clock = new ClockTask(eventAdmin.get(), ctx.getBundle().getSymbolicName());
		clockTimer.scheduleAtFixedRate(clock, delay, 1000);
	}
	
	public void deactivate()
	{
		//cancle the clock timer
		this.clockTimer.cancel();
	}
	
	public void bindEventAdmin(EventAdmin eventAdmin)
	{
		this.eventAdmin.set(eventAdmin);
	}
	
	public void unbindEventAdmin(EventAdmin eventAdmin)
	{
		this.eventAdmin.compareAndSet(eventAdmin, null);
	}

	@Override
	public Calendar getSystemTime()
	{
		return this.clock.getLastTick();
	}

	@Override
	public Calendar setSystemTime()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
