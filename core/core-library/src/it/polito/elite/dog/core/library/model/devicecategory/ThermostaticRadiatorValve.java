/*
    _/_/_/                              _/_/          _/          _/_/                _/      
   _/    _/    _/_/      _/_/_/      _/    _/      _/  _/      _/    _/  _/_/_/    _/_/_/_/   
  _/    _/  _/    _/  _/    _/          _/        _/  _/      _/    _/  _/    _/    _/        
 _/    _/  _/    _/  _/    _/        _/          _/  _/      _/    _/  _/    _/    _/      
_/_/_/      _/_/      _/_/_/      _/_/_/_/  _/    _/          _/_/    _/    _/      _/_/
                         _/
                    _/_/

WEBSITE: http://elite.polito.it/dogont-tools-80
Copyright [2013] [Dario Bonino, Luigi De Russis, Emiliano Castellina (dario.bonino, luigi.derussis, emiliano.castellina{@polito.it}), Politecnico di Torino]
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and limitations under the License.
 *//*Automatically generated byit.polito.elite.domotics.ontologies.dogont.utilities.DogOnt2Dog */

package it.polito.elite.dog.core.library.model.devicecategory;

/**
 * ThermostaticRadiatorValveCategory - automatically generated by DogOnt2Dog
 */

import it.polito.elite.dog.core.library.model.climate.DailyClimateSchedule;
import it.polito.elite.dog.core.library.model.state.*;
import it.polito.elite.dog.core.library.model.DeviceStatus;

import javax.measure.Measure;

public interface ThermostaticRadiatorValve extends Actuator, HVACSystem
{
	public static int MATCH_TYPE = 100;
	public static int MATCH_SUB_TYPE = 50;
	public static int MATCH_MANUFACTURER = 0;

	public void setDailyClimateSchedule(DailyClimateSchedule daySchedule);
	public DeviceStatus getState();
	public Object[] getDaySchedule(Integer weekDay);
	public void cool();
	public void stopHeatingOrCooling();
	public void setTemperatureAt(Measure<?,?>  temperature);
	public void heat();
	public void setClimateSchedule(DailyClimateSchedule[] dailySchedules);


	/*Generated Notifications*/

	/*Notification: StateChangeNotification*/
	public void notifyStateChanged(State newState);
}