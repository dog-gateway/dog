/**
 * 
 */
package it.polito.elite.dog.drivers.zwave.model;

import java.util.Calendar;

import javax.measure.Measure;

/**
 * @author bonino
 * 
 */
public class ClimateScheduleSwitchPoint
{
	private Calendar timeAt;
	private Measure<?, ?> desiredTemperature;
	// this is an int between -128 and 120 as each step represent a 1/10 degrees
	// in Kelvin. So if the current setpoint on the device is 22Â°C then a
	// setBack of -128 will set the actual setpoint to 22 - 12.8 = 9.2 C at the
	// time represented by this SwitchPoint. It is automatically computed given
	// the desired temperature and the base set point (TODO: check how to handle manual set point)
	private int setBack;
}
