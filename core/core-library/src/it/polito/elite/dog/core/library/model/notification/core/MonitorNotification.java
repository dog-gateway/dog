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

import java.util.HashSet;

import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.notification.Notification;


/**
 *  The notification for Scheduler StateMonitorJob (see DogScheduler)
 *    
 * @author Luca Semprini
 *
 */
public class MonitorNotification implements Notification {

	/**
	 * The unique notification topic
	 */
	public static String notificationTopic = "it/polito/elite/domotics/dog2/doglibrary/corenotifications/MonitorNotification";
	
	/**
	 * The unique notification name
	 */
	public static String notificationName = "monitor";
	
	// The device status set
	private HashSet<DeviceStatus> deviceStatusSet;
	// The notification listeners
	private String listener;
	
	/**
	 * A notification for Scheduler StateMonitorJob
	 * 
	 * @param deviceStatus
	 * 				the device status set
	 */
	public MonitorNotification(HashSet<DeviceStatus> deviceStatusSet) {
		super();
		this.deviceStatusSet = deviceStatusSet;
	}	
	
	/**
	 * A notification for Scheduler StateMonitorJob
	 * with explicit listeners
	 * 
	 * @param deviceStatus
	 * 				the device status set
	 */
	public MonitorNotification(HashSet<DeviceStatus> deviceStatusSet, String listener) {
		super();
		this.deviceStatusSet = deviceStatusSet;
		this.listener = listener;
	}	
	
	/**
	 * @return the device status set
	 */
	public HashSet<DeviceStatus> getDeviceStatusSet() {
		return this.deviceStatusSet;
	}
	
	/**
	 * @return the notification listeners
	 */
	public String  getListener() {
		return this.listener;
	}

	@Override
	public String getNotificationTopic() {
		return MonitorNotification.notificationTopic;
	}

	@Override
	public String getDeviceUri() {
		// intentionally left empty
		return null;
	}

	@Override
	public void setDeviceUri(String deviceUri) {
		// intentionally left empty
		
	}

}
