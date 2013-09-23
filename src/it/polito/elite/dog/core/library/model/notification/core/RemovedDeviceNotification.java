/*
 * Dog - Core
 * 
 * Copyright (c) 2010-2013 Dario Bonino, Luigi De Russis and Emiliano Castellina
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


import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import it.polito.elite.dog.core.library.model.notification.Notification;

/**
 * The notification for notifying all the other DogBundles that a device has been removed in the model
 * 
 * @author bonino
 *
 */
public class RemovedDeviceNotification implements Notification
{
	/**
	 * The unique notification topic
	 */
	public static String notificationTopic = "it/polito/elite/domotics/dog2/doglibrary/corenotifications/RemovedDeviceNotification";
	
	/**
	 * The unique notification name
	 */
	public static String notificationName = "removeDevice";	
	
	// the device descriptor specifying the characteristics of the added device
	private DeviceDescriptor removedDeviceDescriptor;
	
	/**
	 * The class constructor, accepts a description ({@link DeviceDescriptor}) of the newly added device
	 * @param descriptor The newly added {@link DeviceDescriptor}
	 */
	public RemovedDeviceNotification(DeviceDescriptor descriptor)
	{
		
		this.removedDeviceDescriptor = descriptor;
	}
	
	/**
	 * Provides back a description of the removed device
	 * 
	 * @return a {@link DeviceDescriptor} instance describing the added device
	 */
	public DeviceDescriptor getRemovedDeviceDescriptor()
	{
		return this.removedDeviceDescriptor;
	}
	
	/**
	 * Provides back the full URI of the removed device, as a String	
	 * @return The full URI of the removed device
	 */
	public String getRemovedDeviceURI()
	{
		return this.removedDeviceDescriptor.getDevURI();
	}
	
	/**
	 * Provides back the type of the removed device, as a String	
	 * @return The type of the removed device
	 */
	public String getRemovedDeviceCategory()
	{
		return this.removedDeviceDescriptor.getDevCategory();
	}

   @Override
   public String getDeviceUri()
   {
	 // intentionally left empty
	 return null;
   }

   @Override
   public void setDeviceUri(String deviceUri)
   {
	// intentionally left empty
	 
   }

	@Override
	public String getNotificationTopic()
	{
		return RemovedDeviceNotification.notificationTopic;
	}
}
