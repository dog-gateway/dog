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

import it.polito.elite.dog.core.library.model.notification.NonParametricNotification;


public class UpdatedModelNotification implements NonParametricNotification{
	
	/**
	 * The unique notification topic
	 */
	public static String notificationTopic = "it/polito/elite/domotics/dog2/doglibrary/corenotifications/UpdatedModelNotification";
	
	/**
	 * The unique notification name
	 */
	public static String notificationName = "updateModel";
	
	public UpdatedModelNotification(){
		
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
		return UpdatedModelNotification.notificationTopic;
	}
}
