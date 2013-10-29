/**
 * 
 */
package it.polito.elite.dog.communication.rest.device.command;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;

/**
 * @author bonino
 *
 */
public class MeasurePayload extends Payload<Measure<?,?>>
{

	public MeasurePayload()
	{
		// TODO Auto-generated constructor stub
	}

	public Measure<?, ?> getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = DecimalMeasure.valueOf(value);
	}
	
	
}
