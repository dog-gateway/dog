/*
 * Dog - Core
 * 
 * Copyright (c) 2013 Dario Bonino, Luigi De Russis and Luca Semprini
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
package it.polito.elite.dog.core.library.model.notification.core;

import java.util.Calendar;

import it.polito.elite.dog.core.library.model.notification.ParametricNotification;

/**
 * @author bonino
 * 
 */
public class ClockTimeNotification implements ParametricNotification
{
	// the notification name
	public static String notificationName = "clockTick";

	// the notification topic
	public static String notificationTopic = "it/polito/elite/dog/core/library/model/notification/core/ClockTimeNotification";

	// the uri of the device sending the notification
	private String deviceUri;

	// the time instant represented by this clock notification
	private Calendar clockTick;

	/**
	 * Creates a clock time notification encapsulating the time in which the
	 * notification was created.
	 */
	public ClockTimeNotification()
	{
		// gets a calendar object representing the instance in which the object
		// was created, using the default time zone and locale of the jvm of the
		// host machine running Dog
		this.clockTick = Calendar.getInstance();
	}

	/**
	 * Creates a clock tick notification encapsulating a given time instant
	 * represented as a {@link Calendar} instance.
	 * 
	 * @param clockTick
	 */
	public ClockTimeNotification(Calendar clockTick)
	{
		this.clockTick = clockTick;
	}

	/**
	 * Get the clock tick hosted by this notification
	 * 
	 * @return
	 */
	public Calendar getClockTick()
	{
		return this.clockTick;
	}

	@Override
	public String getDeviceUri()
	{
		// in principle should always be the inner clock bundle, however in a
		// near future it may be leveraged by other devices...
		return deviceUri;
	}

	@Override
	public void setDeviceUri(String deviceUri)
	{
		// set the URI of the device generating this notification
		this.deviceUri = deviceUri;
	}

	@Override
	public String getNotificationTopic()
	{
		// TODO Auto-generated method stub
		return ClockTimeNotification.notificationTopic;
	}

}
