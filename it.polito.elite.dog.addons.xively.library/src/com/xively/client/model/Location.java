// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.model;

import com.xively.client.utils.ObjectUtil;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * Location that describes the {@link Feed}.
 * 
 * @author s0pau
 * 
 */
@JsonRootName("location")
public class Location
{
	@JsonRootName("exposure")
	public enum Exposure
	{
		indoor, outdoor
	}

	@JsonRootName("disposition")
	public enum Disposition
	{
		fixed, mobile
	}

	@JsonRootName("domain")
	public enum Domain
	{
		physical, virtual
	}

	private String name;

	@JsonProperty("lat")
	private double latitiude;

	@JsonProperty("lon")
	private double longitute;

	@JsonProperty("ele")
	private double elevation;

	private Exposure exposure;

	private Domain domain;

	private Disposition disposition;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getLatitiude()
	{
		return latitiude;
	}

	public void setLatitiude(double latitiude)
	{
		this.latitiude = latitiude;
	}

	public double getLongitute()
	{
		return longitute;
	}

	public void setLongitute(double longitute)
	{
		this.longitute = longitute;
	}

	public double getElevation()
	{
		return elevation;
	}

	public void setElevation(double elevation)
	{
		this.elevation = elevation;
	}

	public Exposure getExposure()
	{
		return exposure;
	}

	public void setExposure(Exposure exposure)
	{
		this.exposure = exposure;
	}

	public Domain getDomain()
	{
		return domain;
	}

	public void setDomain(Domain domain)
	{
		this.domain = domain;
	}

	public Disposition getDisposition()
	{
		return disposition;
	}

	public void setDisposition(Disposition disposition)
	{
		this.disposition = disposition;
	}

	@JsonIgnore
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

		if (!(obj instanceof Location))
		{
			return false;
		}

		Location other = (Location) obj;

		if (!ObjectUtil.nullCheckEquals(this.disposition, other.getDisposition()))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.domain, other.getDomain()))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.elevation, other.getElevation()))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.latitiude, other.getLatitiude()))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.longitute, other.getLongitute()))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.name, other.getName()))
		{
			return false;
		}

		return true;
	}

	@JsonIgnore
	@Override
	public int hashCode()
	{
		int retval = 1;
		retval += disposition == null ? 0 : disposition.hashCode() * 37;
		retval += domain == null ? 0 : domain.hashCode() * 37;
		retval += elevation * 37;
		retval += exposure == null ? 0 : exposure.hashCode() * 37;
		retval += latitiude * 37;
		retval += longitute * 37;
		retval += name == null ? 0 : name.hashCode() * 37;

		return retval;
	}
}
