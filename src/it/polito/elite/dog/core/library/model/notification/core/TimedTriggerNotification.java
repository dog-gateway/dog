/*
 * Dog - Core
 * 
 * Copyright (c) 2011-2013 Dario Bonino and Luigi De Russis
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

import it.polito.elite.dog.core.library.model.notification.ParametricNotification;

public class TimedTriggerNotification implements ParametricNotification
{
	/**
	 * The unique notification topic
	 */
	public static String notificationTopic = "it/polito/elite/domotics/model/notification/TimedTriggerNotification";
	
	/**
	 * The unique notification name
	 */
	public static String notificationName = "monitor";
	
	private String triggerId;

	private String deviceUri = "DogScheduler";
	
	/**
	 * @return the triggerId
	 */
	public String getTriggerId()
	{
		return triggerId;
	}

	/**
	 * @param triggerId the triggerId to set
	 */
	public void setTriggerId(String triggerId)
	{
		this.triggerId = triggerId;
	}

	public TimedTriggerNotification()
	{
		super();
	}
	
	@Override
	public String getDeviceUri()
	{
		return deviceUri;
	}

	@Override
	public void setDeviceUri(String deviceUri)
	{
		this.deviceUri = deviceUri;
		
	}

	@Override
	public String getNotificationTopic()
	{
		return TimedTriggerNotification.notificationTopic;
	}
	
	
	
}
