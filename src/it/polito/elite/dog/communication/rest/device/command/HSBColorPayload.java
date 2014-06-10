package it.polito.elite.dog.communication.rest.device.command;

import it.polito.elite.dog.core.library.model.color.HSBColor;

public class HSBColorPayload extends CommandPayload<HSBColor>
{
	public HSBColorPayload()
	{
		//intentionally left empty
	}

	/* (non-Javadoc)
	 * @see it.polito.elite.dog.communication.rest.device.command.CommandPayload#getValue()
	 */
	@Override
	public HSBColor getValue()
	{
		// TODO Auto-generated method stub
		return super.getValue();
	}

	/* (non-Javadoc)
	 * @see it.polito.elite.dog.communication.rest.device.command.CommandPayload#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(HSBColor value)
	{
		// TODO Auto-generated method stub
		super.setValue(value);
	}
}
