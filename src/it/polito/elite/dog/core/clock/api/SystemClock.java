/**
 * 
 */
package it.polito.elite.dog.core.clock.api;

import java.util.Calendar;

/**
 * @author bonino
 *
 */
public interface SystemClock
{
	public Calendar getSystemTime();
	public Calendar setSystemTime();
}
