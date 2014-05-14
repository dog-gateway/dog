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
* DogEnergyMeteringPowerOutletModel - automatically generated by DogOnt2Dog
*/

import it.polito.elite.dog.core.library.model.devicecategory.EnergyMeteringPowerOutlet;
import it.polito.elite.dog.core.library.model.AbstractDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import org.osgi.framework.BundleContext;
import org.osgi.service.device.Device;
import javax.measure.Measure;

import it.polito.elite.dog.core.library.model.notification.*;
public class DogEnergyMeteringPowerOutlet extends AbstractDevice implements EnergyMeteringPowerOutlet
{

	public DogEnergyMeteringPowerOutlet(BundleContext context, DeviceDescriptor deviceDescriptor)
	{
		super(context);


		this.setDeviceProperties(deviceDescriptor);


		this.registerDevice(Device.class.getName());
	}


	public Measure<?,?>  getReactiveEnergyValue()
	{
		if(this.driver!=null)
		{
			return ((EnergyMeteringPowerOutlet) this.driver).getReactiveEnergyValue();
		}
		 return null;
	}

	public DeviceStatus getState()
	{
		if(this.driver!=null)
		{
			return ((EnergyMeteringPowerOutlet) this.driver).getState();
		}
		 return null;
	}

	public void storeScene(Integer sceneNumber)
	{
		if(this.driver!=null)
		{
			((EnergyMeteringPowerOutlet) this.driver).storeScene(sceneNumber);
		}
	}

	public void deleteScene(Integer sceneNumber)
	{
		if(this.driver!=null)
		{
			((EnergyMeteringPowerOutlet) this.driver).deleteScene(sceneNumber);
		}
	}

	public void on()
	{
		if(this.driver!=null)
		{
			((EnergyMeteringPowerOutlet) this.driver).on();
		}
	}

	public Measure<?,?>  getActiveEnergyValue()
	{
		if(this.driver!=null)
		{
			return ((EnergyMeteringPowerOutlet) this.driver).getActiveEnergyValue();
		}
		 return null;
	}

	public void deleteGroup(String groupID)
	{
		if(this.driver!=null)
		{
			((EnergyMeteringPowerOutlet) this.driver).deleteGroup(groupID);
		}
	}

	public void storeGroup(String groupID)
	{
		if(this.driver!=null)
		{
			((EnergyMeteringPowerOutlet) this.driver).storeGroup(groupID);
		}
	}

	public void off()
	{
		if(this.driver!=null)
		{
			((EnergyMeteringPowerOutlet) this.driver).off();
		}
	}



	/*Generated Notifications*/

	/*Notification: StoreSceneNotification*/
	public void notifyStoredScene(Integer sceneNumber){
		StoreSceneNotification notificationEvent=new StoreSceneNotification(sceneNumber );
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: DeleteSceneNotification*/
	public void notifyDeletedScene(Measure<?,?>  sceneNumber){
		DeleteSceneNotification notificationEvent=new DeleteSceneNotification(sceneNumber );
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
	/*Notification: OnNotification*/
	public void notifyOn(){
		OnNotification notificationEvent=new OnNotification();
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: SinglePhaseReactiveEnergyMeasurementNotification*/
	public void notifyNewReactiveEnergyValue(Measure<?,?>  value){
		SinglePhaseReactiveEnergyMeasurementNotification notificationEvent=new SinglePhaseReactiveEnergyMeasurementNotification(value );
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: SinglePhaseActiveEnergyMeasurementNotification*/
	public void notifyNewActiveEnergyValue(Measure<?,?>  value){
		SinglePhaseActiveEnergyMeasurementNotification notificationEvent=new SinglePhaseActiveEnergyMeasurementNotification(value );
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
	/*Notification: OffNotification*/
	public void notifyOff(){
		OffNotification notificationEvent=new OffNotification();
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
