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
* DogWaterConsumptionMeterModel - automatically generated by DogOnt2Dog
*/

import it.polito.elite.dog.core.library.model.devicecategory.WaterConsumptionMeter;
import it.polito.elite.dog.core.library.model.AbstractDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import org.osgi.framework.BundleContext;
import org.osgi.service.device.Device;
import javax.measure.Measure;

import it.polito.elite.dog.core.library.model.notification.*;
public class DogWaterConsumptionMeter extends AbstractDevice implements WaterConsumptionMeter
{

	public DogWaterConsumptionMeter(BundleContext context, DeviceDescriptor deviceDescriptor)
	{
		super(context);


		this.setDeviceProperties(deviceDescriptor);


		this.registerDevice(Device.class.getName());
	}


	public Measure<?,?>  getAmountOfColdWaterInM3()
	{
		if(this.driver!=null)
		{
			return ((WaterConsumptionMeter) this.driver).getAmountOfColdWaterInM3();
		}
		 return null;
	}

	public DeviceStatus getState()
	{
		if(this.driver!=null)
		{
			return ((WaterConsumptionMeter) this.driver).getState();
		}
		 return null;
	}

	public Measure<?,?>  getAmountOfHotWaterInM3()
	{
		if(this.driver!=null)
		{
			return ((WaterConsumptionMeter) this.driver).getAmountOfHotWaterInM3();
		}
		 return null;
	}



	/*Generated Notifications*/

	/*Notification: ColdWaterM3MeasurementNotification*/
	public void notifyNewColdWaterAmountInM3(Measure<?,?>  m3OfColdWater){
		ColdWaterM3MeasurementNotification notificationEvent=new ColdWaterM3MeasurementNotification(m3OfColdWater );
		notificationEvent.setDeviceUri(this.deviceId);
		// Send the notification through the EventAdmin
		notifyEventAdmin(notificationEvent);
	}
	/*Notification: HotWaterM3MeasurementNotification*/
	public void notifyNewHotWaterAmountInM3(Measure<?,?>  m3OfHotWater){
		HotWaterM3MeasurementNotification notificationEvent=new HotWaterM3MeasurementNotification(m3OfHotWater );
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
