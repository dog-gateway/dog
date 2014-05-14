/*
 * Dog - Core
 *
 * Copyright (c) 2011-2014 Dario Bonino and Luigi De Russis
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
// Automatically generated by the DogOnt2Dog utility
//

package it.polito.elite.dog.core.library.model.devicemodel;


 
/**
* DogQuadSensorModel - automatically generated by DogOnt2Dog
*/

import it.polito.elite.dog.core.library.model.devicecategory.QuadSensor;
import it.polito.elite.dog.core.library.model.AbstractDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import org.osgi.framework.BundleContext;
import org.osgi.service.device.Device;
import javax.measure.Measure;

import it.polito.elite.dog.core.library.model.notification.*;
public class DogQuadSensor extends AbstractDevice implements QuadSensor
{

	public DogQuadSensor(BundleContext context, DeviceDescriptor deviceDescriptor)
	{
		super(context);


		this.setDeviceProperties(deviceDescriptor);


		this.registerDevice(Device.class.getName());
	}


	public Measure<?,?>  getRelativeHumidity()
	{
		if(this.driver!=null)
		{
			return ((QuadSensor) this.driver).getRelativeHumidity();
		}
		 return null;
	}

	public Measure<?,?>  getTemperature()
	{
		if(this.driver!=null)
		{
			return ((QuadSensor) this.driver).getTemperature();
		}
		 return null;
	}

	public DeviceStatus getState()
	{
		if(this.driver!=null)
		{
			return ((QuadSensor) this.driver).getState();
		}
		 return null;
	}

	public Measure<?,?>  getLuminance()
	{
		if(this.driver!=null)
		{
			return ((QuadSensor) this.driver).getLuminance();
		}
		 return null;
	}

	public void deleteGroup(String groupID)
	{
		if(this.driver!=null)
		{
			((QuadSensor) this.driver).deleteGroup(groupID);
		}
	}

	public void storeGroup(String groupID)
	{
		if(this.driver!=null)
		{
			((QuadSensor) this.driver).storeGroup(groupID);
		}
	}



	/*Generated Notifications*/

	/*Notification: LuminosityMeasurementNotification*/
	public void notifyNewLuminosityValue(Measure<?,?>  luminosityValue){
		LuminosityMeasurementNotification notificationEvent=new LuminosityMeasurementNotification(luminosityValue );
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: TemperatureMeasurementNotification*/
	public void notifyNewTemperatureValue(Measure<?,?>  temperatureValue){
		TemperatureMeasurementNotification notificationEvent=new TemperatureMeasurementNotification(temperatureValue );
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: SimpleNoMovementNotification*/
	public void notifyCeasedMovement(){
		SimpleNoMovementNotification notificationEvent=new SimpleNoMovementNotification();
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: JoinGroupNotification*/
	public void notifyJoinedGroup(Integer groupNumber){
		JoinGroupNotification notificationEvent=new JoinGroupNotification(groupNumber );
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: HumidityMeasurementNotification*/
	public void notifyChangedRelativeHumidity(Measure<?,?>  relativeHumidity){
		HumidityMeasurementNotification notificationEvent=new HumidityMeasurementNotification(relativeHumidity );
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: SimpleMovementNotification*/
	public void notifyStartedMovement(){
		SimpleMovementNotification notificationEvent=new SimpleMovementNotification();
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: GroupNotification*/
	public void notifyBelongToGroup(Integer groupNumber){
		GroupNotification notificationEvent=new GroupNotification(groupNumber );
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: LeaveGroupNotification*/
	public void notifyLeftGroup(Integer groupNumber){
		LeaveGroupNotification notificationEvent=new LeaveGroupNotification(groupNumber );
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	@Override
	public void updateStatus()
	{
		super.updateStatus();
	}
}
