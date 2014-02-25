/**
 * 
 */
package com.xively.client.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author bonino
 * 
 */
public class DeviceActivation implements DomainObject
{
	@JsonProperty("apikey")
	private String apikey;

	@JsonProperty("feed_id")
	private Integer feedId = 0;

	/**
	 * 
	 */
	public DeviceActivation()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean memberEquals(DomainObject other)
	{
		boolean equals = false;
		if (other instanceof DeviceActivation)
		{
			equals = this.apikey.equals(((DeviceActivation) other).apikey)
					&& this.feedId.equals(((DeviceActivation) other).feedId);
		}

		return equals;
	}

	@Override
	public Object getId()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String getApikey()
	{
		return apikey;
	}

	public void setApikey(String apikey)
	{
		this.apikey = apikey;
	}

	public Integer getFeedId()
	{
		return feedId;
	}

	public void setFeedId(int feedId)
	{
		this.feedId = new Integer(feedId);
	}
}
