package it.polito.elite.dog.communication.rest.device.command;

import it.polito.elite.dog.core.library.model.color.RGBColor;

public class RGBColorPayload extends CommandPayload<RGBColor>
{
	public RGBColorPayload()
	{
		//intentionally left empty
	}

	/* (non-Javadoc)
	 * @see it.polito.elite.dog.communication.rest.device.command.CommandPayload#getValue()
	 */
	@Override
	public RGBColor getValue()
	{
		// TODO Auto-generated method stub
		return super.getValue();
	}

	/* (non-Javadoc)
	 * @see it.polito.elite.dog.communication.rest.device.command.CommandPayload#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(RGBColor value)
	{
		// TODO Auto-generated method stub
		super.setValue(value);
	}
}

