// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.model;

import java.util.Collection;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.xively.client.utils.CollectionUtil;
import com.xively.client.utils.ObjectUtil;


/**
 * ApiKey resource/model
 * 
 * @author s0pau
 * 
 */
@JsonRootName(value = "keys")
public class ApiKey implements DomainObject
{
	/**
	 * Actual string of the api key. It is also the id.
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
	@JsonProperty("api_key")
	private String apiKey;

	/**
	 * Name of the api key
	 */
	private String label;

	// String expiresAt;

	@JsonProperty("private_access")
	private boolean isPrivateAccess;

	/**
	 * Mappings to {@link Resource}, if not empty, this api key is mapped to
	 * specific {@link feed}/{@link datastream}
	 */
	private Collection<Permission> permissions;

	public String getApiKey()
	{
		return apiKey;
	}

	public void setApiKey(String apiKey)
	{
		this.apiKey = apiKey;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	@JsonProperty("private_access")
	public boolean isPrivateAccess()
	{
		return isPrivateAccess;
	}

	@JsonProperty("private_access")
	public void setPrivateAccess(boolean isPrivateAccess)
	{
		this.isPrivateAccess = isPrivateAccess;
	}

	public Collection<Permission> getPermissions()
	{
		return permissions;
	}

	public void setPermissions(Collection<Permission> permissions)
	{
		this.permissions = permissions;
	}

	@JsonIgnore
	public String getIdString()
	{
		return apiKey;
	}

	@JsonIgnore
	@Override
	public String getId()
	{
		return getApiKey();
	}

	@Override
	public boolean memberEquals(DomainObject obj)
	{
		if (!equals(obj))
		{
			return false;
		}

		ApiKey other = (ApiKey) obj;
		if (!ObjectUtil.nullCheckEquals(this.apiKey, other.apiKey))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.label, other.label))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.isPrivateAccess, other.isPrivateAccess))
		{
			return false;
		}

		if (!CollectionUtil.deepEquals(this.permissions, other.permissions))
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

		if (!(obj instanceof ApiKey))
		{
			return false;
		}

		ApiKey other = (ApiKey) obj;

		if (!ObjectUtil.nullCheckEquals(this.apiKey, other.apiKey))
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int retval = 1;
		retval += (apiKey == null ? 0 : apiKey.hashCode() * 37);
		return retval;
	}
}
