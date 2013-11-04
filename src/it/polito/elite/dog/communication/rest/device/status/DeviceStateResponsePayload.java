/**
 * 
 */
package it.polito.elite.dog.communication.rest.device.status;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bonino
 * 
 */
public class DeviceStateResponsePayload
{
	private String id;
	private String description;
	private boolean active;
	private Map<String,Object> status;

	/**
	 * 
	 */
	public DeviceStateResponsePayload()
	{
		//create the status map
		this.status = new HashMap<String, Object>();
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
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

	/**
	 * @return the active
	 */
	public boolean isActive()
	{
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active)
	{
		this.active = active;
	}

	/**
	 * @return the status
	 */
	public Map<String, Object> getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Map<String, Object> status)
	{
		this.status = status;
	}
}
