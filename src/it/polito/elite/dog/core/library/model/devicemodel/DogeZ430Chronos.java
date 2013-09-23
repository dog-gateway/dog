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
//
//Automatically generated by it.polito.elite.domotics.ontologies.dogont.utilities.DogOnt2Dog
//

package it.polito.elite.dog.core.library.model.devicemodel;


 
 /**
* DogeZ430ChronosModel - automatically generated by DogOnt2Dog
*/

import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.AbstractDevice;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import it.polito.elite.dog.core.library.model.devicecategory.eZ430Chronos;
import it.polito.elite.dog.core.library.model.notification.*;
import it.polito.elite.dog.core.library.model.state.*;

import org.osgi.framework.BundleContext;
import org.osgi.service.device.Device;
public class DogeZ430Chronos extends AbstractDevice implements eZ430Chronos
{

	public DogeZ430Chronos(BundleContext context, DeviceDescriptor dogDeviceDescriptor)
	{
		super(context);


		this.setDeviceProperties(dogDeviceDescriptor);


		this.registerDevice(Device.class.getName());
	}


	public void stop()
	{
		if(this.driver!=null){
((eZ430Chronos) this.driver).stop();
		}
	}

	public DeviceStatus getState()
	{
		if(this.driver!=null){
		return ((eZ430Chronos) this.driver).getState();
		}
		 return null;
	}

	public void display(String msg)
	{
		if(this.driver!=null){
((eZ430Chronos) this.driver).display(msg);
		}
	}

	public void play()
	{
		if(this.driver!=null){
((eZ430Chronos) this.driver).play();
		}
	}



	/*Generated Notifications*/

	/*Notification: PressedMNotification*/
	public void notifyPressed(String buttonID){
		PressedMNotification notificationEvent=new PressedMNotification(buttonID );
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: TridimensionalAccelerationNotification*/
	public void notifyNew3DAccelerationValue(Double accX, Double accY, Double accZ){
		TridimensionalAccelerationNotification notificationEvent=new TridimensionalAccelerationNotification(accX , accY , accZ );
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/**
	/* Implements the DeviceCategory interface requirement 
	**/
	@Override
	public void notifyStateChanged(State newState)
	{
		super.notifyStateChanged(newState);
	}
	/*Notification: ReleasedMNotification*/
	public void notifyReleased(String buttonID){
		ReleasedMNotification notificationEvent=new ReleasedMNotification(buttonID );
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
}
