/*
 * Dog - Addons
 * 
 * Copyright (c) 2011-2012 Dario Bonino and Emiliano Castellina
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
package it.polito.elite.dog.addons.rules.util;

import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.Duration;

/**
 * 
 * @author Castellina Emiliano (skeleton)
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 *
 */
public class TimeConversion
{
	public TimeConversion()
	{
		// empty constructor
	}
	
	public long hmsToS(int hours, int minutes, int seconds)
	{
		long allSeconds = hours * 3600 + minutes * 60 + seconds;
		return allSeconds;
	}
	
	public long[] sToHMS(long seconds)
	{
		long hms[] = new long[3];
		
		// hours
		hms[0] = seconds / 3600;
		// minutes
		hms[1] = (seconds % 3600) / 60;
		// seconds
		hms[2] = seconds % 60;
		
		return hms;
	}
	
	public long durationToS(Duration d)
	{
		long allSeconds = d.getTimeInMillis(new Date()) / 1000;
		return allSeconds;
	}
	
	public Calendar dayTimeToCalendar(long seconds)
	{
		Calendar today = Calendar.getInstance();
		
		long hms[] = this.sToHMS(seconds);
		today.set(Calendar.HOUR_OF_DAY, (int) hms[0]);
		today.set(Calendar.MINUTE, (int) hms[1]);
		today.set(Calendar.SECOND, (int) hms[2]);
		
		return today;
	}
	
	public boolean isCurrentTimeIn(long startTime, long endTime, String weekdays)
	{
		boolean in = false;
		
		// get the current time
		Calendar now = Calendar.getInstance();
		
		// convert the weekdays into an array of weekday identifier
		String weekdayIDs[] = weekdays.split(",");
		
		for (int i = 0; (i < weekdayIDs.length) && (!in); i++)
		{
			// if the day of week is the correct one than the daytime may be
			// compared
			if (now.get(Calendar.DAY_OF_WEEK) == Integer.parseInt(weekdayIDs[i]))
			{
				// check the time
				long nowS = this.hmsToS(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE),
						now.get(Calendar.SECOND));
				
				if ((nowS >= startTime) && (nowS <= endTime))
					in = true;
			}
		}
		
		return in;
		
	}
}
