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
* DogTvModel - automatically generated by DogOnt2Dog
*/

import it.polito.elite.dog.core.library.model.devicecategory.Tv;
import it.polito.elite.dog.core.library.model.AbstractDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import org.osgi.framework.BundleContext;
import org.osgi.service.device.Device;
import it.polito.elite.dog.core.library.model.notification.*;
import javax.measure.Measure;

public class DogTv extends AbstractDevice implements Tv
{

	public DogTv(BundleContext context, DeviceDescriptor deviceDescriptor)
	{
		super(context);


		this.setDeviceProperties(deviceDescriptor);


		this.registerDevice(Device.class.getName());
	}


	public void setVolume(Integer volume)
	{
		if(this.driver!=null)
		{
			((Tv) this.driver).setVolume(volume);
		}
	}

	public void increaseVolume()
	{
		if(this.driver!=null)
		{
			((Tv) this.driver).increaseVolume();
		}
	}

	public DeviceStatus getState()
	{
		if(this.driver!=null)
		{
			return ((Tv) this.driver).getState();
		}
		 return null;
	}

	public void decreaseVolume()
	{
		if(this.driver!=null)
		{
			((Tv) this.driver).decreaseVolume();
		}
	}

	public void set(Object value)
	{
		if(this.driver!=null)
		{
			((Tv) this.driver).set(value);
		}
	}

	public void on()
	{
		if(this.driver!=null)
		{
			((Tv) this.driver).on();
		}
	}

	public void setChannel(Integer channel)
	{
		if(this.driver!=null)
		{
			((Tv) this.driver).setChannel(channel);
		}
	}

	public void down()
	{
		if(this.driver!=null)
		{
			((Tv) this.driver).down();
		}
	}

	public void up()
	{
		if(this.driver!=null)
		{
			((Tv) this.driver).up();
		}
	}

	public void standBy()
	{
		if(this.driver!=null)
		{
			((Tv) this.driver).standBy();
		}
	}

	public void off()
	{
		if(this.driver!=null)
		{
			((Tv) this.driver).off();
		}
	}



	/*Generated Notifications*/

	/*Notification: TuningStepDownNotification*/
	public void notifyChannelStepUp(){
		TuningStepDownNotification notificationEvent=new TuningStepDownNotification();
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: LevelStepUpNotification*/
	public void notifyStepUp(){
		LevelStepUpNotification notificationEvent=new LevelStepUpNotification();
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
	/*Notification: TuningStepUpNotification*/
	public void notifyChannelStepDown(){
		TuningStepUpNotification notificationEvent=new TuningStepUpNotification();
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: LevelControlNotification*/
	public void notifyChangedLevel(Measure<?,?>  newLevel){
		LevelControlNotification notificationEvent=new LevelControlNotification(newLevel );
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: LevelStepDownNotification*/
	public void notifyStepDown(){
		LevelStepDownNotification notificationEvent=new LevelStepDownNotification();
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: StandByNotification*/
	public void notifyStandby(){
		StandByNotification notificationEvent=new StandByNotification();
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
	/*Notification: ChannelControlNotification*/
	public void notifyChangedChannel(String channelId){
		ChannelControlNotification notificationEvent=new ChannelControlNotification(channelId );
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
