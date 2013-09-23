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

package it.polito.elite.dog.core.library.model.devicecategory;


 
 /**
* MeteringDimmablePowerOutletCategory - automatically generated by DogOnt2Dog
*/


import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.state.*;

import javax.measure.Measure;

public interface MeteringDimmablePowerOutlet extends SinglePhaseEnergyMeter, LevelControllableOutput, SinglePhaseActivePowerMeter
{
	public static int MATCH_TYPE=100;
	public static int MATCH_SUB_TYPE=50;
	public static int MATCH_MANUFACTURER=0;

	public void stepDown();
	public DeviceStatus getState();
	public void on();
	public void deleteGroup(String groupID);
	public Measure<?,?>  getActivePower();
	public void storeGroup(String groupID);
	public void off();
	public Measure<?,?>  getReactiveEnergyValue();
	public void stepUp();
	public void storeScene(Integer sceneNumber);
	public void deleteScene(Integer sceneNumber);
	public void set(Object value);
	public Measure<?,?>  getActiveEnergyValue();


	/*Generated Notifications*/

	/*Notification: SinglePhaseActivePowerMeasurementNotification*/
	public void notifyNewActivePowerValue(Measure<?,?>  powerValue);
	/*Notification: StateChangeNotification*/
	public void notifyStateChanged(State newState);
	/*Notification: SinglePhaseReactiveEnergyMeasurementNotification*/
	public void notifyNewReactiveEnergyValue(Measure<?,?>  value);
	/*Notification: SinglePhaseActiveEnergyMeasurementNotification*/
	public void notifyNewActiveEnergyValue(Measure<?,?>  value);
}
