// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.model;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.xively.client.utils.ObjectUtil;

/**
 * Datapoint resource/model
 * 
 * @see https://xively.com/docs/v2/datapoint/
 * 
 * @author s0pau
 * 
 */
@JsonRootName(value = "datapoints")
public class Datapoint implements DomainObject, Comparable<Datapoint>
{
	/**
	 * id of the datapoint
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
	String at;
	String value;

	public String getAt()
	{
		return at;
	}

	public void setAt(String at)
	{
		this.at = at;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	@JsonIgnore
	@Override
	public String getId()
	{
		return getAt();
	}

	@JsonIgnore
	@Override
	public boolean memberEquals(DomainObject obj)
	{
		if (!equals(obj))
		{
			return false;
		}

		Datapoint other = (Datapoint) obj;
		if (!ObjectUtil.nullCheckEquals(this.value, other.value))
		{
			return false;
		}

		return true;
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

		if (!(obj instanceof Datapoint))
		{
			return false;
		}

		Datapoint other = (Datapoint) obj;

		if (!ObjectUtil.nullCheckEquals(this.at, other.at))
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int retval = 1;
		retval += (at == null ? 0 : at.hashCode() * 37);
		return retval;
	}

	@Override
	public int compareTo(Datapoint o)
	{
		//
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		try
		{
			Date localAt = sd.parse(this.at);
			Date toCompareDate = sd.parse(o.at);
			return localAt.compareTo(toCompareDate);
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			// do nothing
		}
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
