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

package it.polito.elite.dog.core.library.model.notification;



/**
* GasM3MeasurementNotification - automatically generated by DogOnt2Dog
*/

import javax.measure.Measure;

public class GasM3MeasurementNotification implements ParametricNotification
{
	public static String notificationName = "newConsumedM3GasValue";

	public static String notificationTopic="it/polito/elite/domotics/model/notification/GasM3MeasurementNotification";

private String deviceUri;
public String getDeviceUri(){return this.deviceUri;}
public void setDeviceUri(String deviceUri){ this.deviceUri=deviceUri;}
public String getNotificationTopic(){ return GasM3MeasurementNotification.notificationTopic; };
	private Measure<?,?>  consumedGasCubeMeters;

public GasM3MeasurementNotification(Measure<?,?>  consumedGasCubeMeters){

		this.consumedGasCubeMeters=consumedGasCubeMeters;
	}
	public Measure<?,?>  getConsumedGasCubeMeters(){
			return this.consumedGasCubeMeters;
}

}
