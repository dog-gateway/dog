/**
 * 
 */
package it.polito.elite.dog.communication.rest.device.command;

import it.polito.elite.dog.core.library.model.climate.DailyClimateSchedule;

/**
 * @author bonino
 *
 */
public class DailyClimateSchedulePayload extends Payload<DailyClimateSchedule[]>
{
	//private DailyClimateSchedule value[];
	
	public DailyClimateSchedulePayload()
	{
		//empty constructor implementing the bean instantiation pattern
	}
	
	//setters for "registered types"
	public void setValue(DailyClimateSchedule[] schedules)
	{
		this.value = schedules;
	}

	public DailyClimateSchedule[] getValue()
	{
		return value;
	}
}
