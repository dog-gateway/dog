// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.model;

import java.util.Collection;
import java.util.Collections;

import com.xively.client.utils.CollectionUtil;
import com.xively.client.utils.ObjectUtil;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * Permission model, accessible from {@link ApiKey}.
 * 
 * Value object.
 * 
 * @author spau
 * 
 */
@JsonRootName("permissions")
public class Permission
{
	public enum AccessMethod
	{
		delete, get, post, put;
	}

	@JsonProperty("source_ip")
	private String sourceIp;
	// private String referer;
	// private Integer minimumInterval;
	// private String label;

	@JsonProperty("access_methods")
	private Collection<AccessMethod> accessMethods;

	@JsonProperty("resources")
	private Collection<Resource> resources;

	@JsonCreator
	public Permission(@JsonProperty("source_ip") String sourceIp,
			@JsonProperty("access_methods") Collection<AccessMethod> accessMethods,
			@JsonProperty("resources") Collection<Resource> resources)
	{
		this.sourceIp = sourceIp;
		this.accessMethods = accessMethods == null ? null : Collections.unmodifiableCollection(accessMethods);
		this.resources = resources == null ? null : Collections.unmodifiableCollection(resources);
	}

	public Collection<AccessMethod> getAccessMethods()
	{
		return accessMethods;
	}

	public String getSourceIp()
	{
		return sourceIp;
	}

	public Collection<Resource> getResources()
	{
		return resources;
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

		if (!(obj instanceof Permission))
		{
			return false;
		}

		Permission other = (Permission) obj;

		if (!ObjectUtil.nullCheckEquals(this.sourceIp, other.sourceIp))
		{
			return false;
		}

		if (!CollectionUtil.deepEquals(this.accessMethods, other.accessMethods))
		{
			return false;
		}

		if (!CollectionUtil.deepEquals(this.resources, other.resources))
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
		retval += sourceIp == null ? 0 : sourceIp.hashCode() * 37;
		retval += accessMethods == null ? 0 : accessMethods.hashCode() * 37;
		retval += resources == null ? 0 : resources.hashCode() * 37;
		return retval;
	}
}
