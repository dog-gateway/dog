/**
 * 
 */
package it.polito.elite.dog.communication.rest.device;

/**
 * @author de russis
 * 
 */
public class DeviceUpdatePayload
{
	private String isIn;
	private String description;

	/**
	 * 
	 */
	public DeviceUpdatePayload()
	{
		// intentionally left empty
	}

	/**
	 * @return the isIn
	 */
	public String getIsIn()
	{
		return this.isIn;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setIsIn(String isIn)
	{
		this.isIn = isIn;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
}
