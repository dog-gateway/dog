// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.model;

import java.util.Collection;

import com.xively.client.utils.CollectionUtil;
import com.xively.client.utils.ObjectUtil;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * Datastream resource/model
 * 
 * @see https://xively.com/docs/v2/datastream/
 * 
 * @author s0pau
 * 
 */
@JsonRootName(value = "datastream")
public class Datastream implements DomainObject
{
	private String id;

	@JsonProperty("at")
	private String updatedAt;

	/**
	 * Current datapoint's value // TODO somewhere said this should be read
	 * only?
	 */
	@JsonProperty("current_value")
	private String value;

	/**
	 * Min of all datapoint's value since the last reset
	 */
	@JsonProperty("min_value")
	private String minValue;

	/**
	 * Max of all datapoint's value since the last reset
	 */
	@JsonProperty("max_value")
	private String maxValue;

	private Collection<String> tags;

	private Unit unit;

	private Collection<Datapoint> datapoints;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt)
	{
		this.updatedAt = updatedAt;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getMinValue()
	{
		return minValue;
	}

	public void setMinValue(String minValue)
	{
		this.minValue = minValue;
	}

	public String getMaxValue()
	{
		return maxValue;
	}

	public void setMaxValue(String maxValue)
	{
		this.maxValue = maxValue;
	}

	public Collection<String> getTags()
	{
		return tags;
	}

	public void setTags(Collection<String> tags)
	{
		this.tags = tags;
	}

	public Unit getUnit()
	{
		return unit;
	}

	public void setUnit(Unit unit)
	{
		this.unit = unit;
	}

	public Collection<Datapoint> getDatapoints()
	{
		return datapoints;
	}

	public void setDatapoints(Collection<Datapoint> datapoints)
	{
		this.datapoints = datapoints;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (this == obj)
		{
			return true;
		}

		if (!(obj instanceof Datastream))
		{
			return false;
		}

		Datastream other = (Datastream) obj;

		if (!ObjectUtil.nullCheckEquals(this.id, other.id))
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int retval = 1;
		retval += (id == null ? 0 : id.hashCode() * 37);
		return retval;
	}

	@JsonIgnore
	@Override
	public boolean memberEquals(DomainObject obj)
	{
		if (!equals(obj))
		{
			return false;
		}

		Datastream other = (Datastream) obj;
		if (!ObjectUtil.nullCheckEquals(this.updatedAt, other.updatedAt))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.value, other.value))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.minValue, other.minValue))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.maxValue, other.maxValue))
		{
			return false;
		}

		if (!CollectionUtil.deepEquals(this.getTags(), other.getTags()))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.getUnit(), other.getUnit()))
		{
			return false;
		}

		return true;
	}
}
