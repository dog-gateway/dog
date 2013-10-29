/**
 * 
 */
package it.polito.elite.dog.communication.rest.device.command;

/**
 * @author bonino
 *
 */
public class Payload <T>
{
	protected T value;

	public Payload()
	{
		// TODO Auto-generated constructor stub
	}

	public T getValue()
	{
		return value;
	}

	public void setValue(T value)
	{
		this.value = value;
	}
	
	
}
