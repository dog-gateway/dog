/**
 * 
 */
package it.polito.elite.dog.communication.rest.device.command;


/**
 * @author bonino
 *
 */
public class DoublePayload extends Payload <Double>
{

	public DoublePayload()
	{
		// TODO Auto-generated constructor stub
	}

	public Double getValue()
	{
		return value;
	}

	public void setValue(Double value)
	{
		this.value = value;
	}
	
}
