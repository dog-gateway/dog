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
* DogThreePhaseEnergyMeterModel - automatically generated by DogOnt2Dog
*/

import it.polito.elite.dog.core.library.model.devicecategory.ThreePhaseEnergyMeter;
import it.polito.elite.dog.core.library.model.AbstractDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import org.osgi.framework.BundleContext;
import org.osgi.service.device.Device;
import javax.measure.Measure;

import it.polito.elite.dog.core.library.model.notification.*;
public class DogThreePhaseEnergyMeter extends AbstractDevice implements ThreePhaseEnergyMeter
{

	public DogThreePhaseEnergyMeter(BundleContext context, DeviceDescriptor deviceDescriptor)
	{
		super(context);


		this.setDeviceProperties(deviceDescriptor);


		this.registerDevice(Device.class.getName());
	}


	public Measure<?,?>  getReactiveEnergyValue(String phaseID)
	{
		if(this.driver!=null)
		{
			return ((ThreePhaseEnergyMeter) this.driver).getReactiveEnergyValue(phaseID);
		}
		 return null;
	}

	public DeviceStatus getState()
	{
		if(this.driver!=null)
		{
			return ((ThreePhaseEnergyMeter) this.driver).getState();
		}
		 return null;
	}

	public Measure<?,?>  getActiveEnergyValue(String phaseID)
	{
		if(this.driver!=null)
		{
			return ((ThreePhaseEnergyMeter) this.driver).getActiveEnergyValue(phaseID);
		}
		 return null;
	}



	/*Generated Notifications*/

	/*Notification: ThreePhaseReactiveEnergyMeasurementNotification*/
	public void notifyNewReactiveEnergyValue(String phaseID, Measure<?,?>  value){
		ThreePhaseReactiveEnergyMeasurementNotification notificationEvent=new ThreePhaseReactiveEnergyMeasurementNotification(phaseID , value );
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: ThreePhaseActiveEnergyMeasurementNotification*/
	public void notifyNewActiveEnergyValue(String phaseID, Measure<?,?>  value){
		ThreePhaseActiveEnergyMeasurementNotification notificationEvent=new ThreePhaseActiveEnergyMeasurementNotification(phaseID , value );
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
