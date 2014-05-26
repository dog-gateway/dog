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

package it.polito.elite.dog.core.library.model.notification;



/**
* MultiTariffThreePhaseActiveEnergyMeasurementNotification - automatically generated by DogOnt2Dog
*/

import javax.measure.Measure;

public class MultiTariffThreePhaseActiveEnergyMeasurementNotification implements ParametricNotification
{
	public static String notificationName = "newActiveEnergyValue";

	public static String notificationTopic="it/polito/elite/dog/core/library/model/notification/MultiTariffThreePhaseActiveEnergyMeasurementNotification";

	private String deviceUri;
	public String getDeviceUri()
	{
		return this.deviceUri;
	}
	public void setDeviceUri(String deviceUri)
	{
		 this.deviceUri=deviceUri;
	}
	public String getNotificationTopic()
	{
		 return MultiTariffThreePhaseActiveEnergyMeasurementNotification.notificationTopic; 
	}
	private String phaseID;
	private String tariffID;
	private Measure<?,?>  value;

	public MultiTariffThreePhaseActiveEnergyMeasurementNotification(String phaseID, String tariffID, Measure<?,?>  value)
	{

		this.phaseID=phaseID;
		this.tariffID=tariffID;
		this.value=value;
	}
	public String getPhaseID(){
		return this.phaseID;
}
	public String getTariffID(){
		return this.tariffID;
}
	public Measure<?,?>  getValue(){
		return this.value;
}

}
