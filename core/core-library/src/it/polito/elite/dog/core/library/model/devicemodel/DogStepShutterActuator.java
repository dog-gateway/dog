/*
 * Dog - Core
 *
 * Copyright (c) 2011-2016 Dario Bonino and Luigi De Russis
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
* DogStepShutterActuatorModel - automatically generated by DogOnt2Dog
*/

import it.polito.elite.dog.core.library.model.devicecategory.StepShutterActuator;
import it.polito.elite.dog.core.library.model.AbstractDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import org.osgi.framework.BundleContext;
import org.osgi.service.device.Device;
import it.polito.elite.dog.core.library.model.notification.*;
public class DogStepShutterActuator extends AbstractDevice implements StepShutterActuator
{

	public DogStepShutterActuator(BundleContext context, DeviceDescriptor deviceDescriptor)
	{
		super(context);


		this.setDeviceProperties(deviceDescriptor);


		this.registerDevice(Device.class.getName());
	}


	public void stepDown()
	{
		if(this.driver!=null)
		{
			((StepShutterActuator) this.driver).stepDown();
		}
	}

	public void stepUp()
	{
		if(this.driver!=null)
		{
			((StepShutterActuator) this.driver).stepUp();
		}
	}

	public DeviceStatus getState()
	{
		if(this.driver!=null)
		{
			return ((StepShutterActuator) this.driver).getState();
		}
		 return null;
	}

	public void rest()
	{
		if(this.driver!=null)
		{
			((StepShutterActuator) this.driver).rest();
		}
	}

	public void down()
	{
		if(this.driver!=null)
		{
			((StepShutterActuator) this.driver).down();
		}
	}

	public void up()
	{
		if(this.driver!=null)
		{
			((StepShutterActuator) this.driver).up();
		}
	}



	/*Generated Notifications*/

	/*Notification: RestNotification*/
	public void notifyResting(){
		RestNotification notificationEvent=new RestNotification();
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: RestDownNotification*/
	public void notifyRestingDown(){
		RestDownNotification notificationEvent=new RestDownNotification();
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: RestUpNotification*/
	public void notifyRestingUp(){
		RestUpNotification notificationEvent=new RestUpNotification();
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: MovingDownNotification*/
	public void notifyMovingDown(){
		MovingDownNotification notificationEvent=new MovingDownNotification();
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: MovingUpNotification*/
	public void notifyMovingUp(){
		MovingUpNotification notificationEvent=new MovingUpNotification();
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
