// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.model;

import java.net.URI;
import java.util.Collection;

import com.xively.client.utils.CollectionUtil;
import com.xively.client.utils.ObjectUtil;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Feed resource/model
 * 
 * @see https://xively.com/docs/v2/feed/
 * 
 * @author s0pau
 * 
 */
@JsonRootName(value = "feeds")
public class Feed implements DomainObject
{
	public enum Status
	{
		frozen, live
	}

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
	private Integer id;
	private String title;
	private String description;

	@JsonProperty("updated")
	private String updatedAt;

	@JsonProperty("created")
	private String createdAt;

	/**
	 * A link to the creator of the feed
	 */
	@JsonProperty("creator")
	private URI creatorUri;

	@JsonProperty("feed")
	private URI feedUri;

	private Status status;

	// private String email;
	// private URI icon;

	@JsonProperty("website")
	private URI website;

	private Collection<String> tags;

	private Location location;

	// TODO where is this on the API doc?
	@JsonProperty("private")
	private boolean isPrivate;

	// TODO where is this on the API doc?
	// @JsonProperty("owner")
	// private String login;

	private Collection<Datastream> datastreams;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt)
	{
		this.updatedAt = updatedAt;
	}

	public String getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(String createdAt)
	{
		this.createdAt = createdAt;
	}

	public URI getCreatorUri()
	{
		return creatorUri;
	}

	public void setCreatorUri(URI creatorUri)
	{
		this.creatorUri = creatorUri;
	}

	public URI getFeedUri()
	{
		return feedUri;
	}

	public void setFeedUri(URI feedUri)
	{
		this.feedUri = feedUri;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public URI getWebsite()
	{
		return website;
	}

	public void setWebsite(URI website)
	{
		this.website = website;
	}

	public Collection<String> getTags()
	{
		return tags;
	}

	public void setTags(Collection<String> tags)
	{
		this.tags = tags;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	public boolean isPrivate()
	{
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate)
	{
		this.isPrivate = isPrivate;
	}

	public Collection<Datastream> getDatastreams()
	{
		return datastreams;
	}

	public void setDatastreams(Collection<Datastream> datastreams)
	{
		this.datastreams = datastreams;
	}

	@JsonIgnore
	@Override
	public boolean memberEquals(DomainObject obj)
	{
		if (!equals(obj))
		{
			return false;
		}

		Feed other = (Feed) obj;
		if (!ObjectUtil.nullCheckEquals(this.getTitle(), other.getTitle()))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.getDescription(), other.getDescription()))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.getUpdatedAt(), other.getUpdatedAt()))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.getCreatedAt(), other.getCreatedAt()))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.getCreatorUri(), other.getCreatorUri()))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.getFeedUri(), other.getFeedUri()))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.getStatus(), other.getStatus()))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.getWebsite(), other.getWebsite()))
		{
			return false;
		}

		if (!CollectionUtil.deepEquals(this.getTags(), other.getTags()))
		{
			return false;
		}

		// TODO
		// if (!CollectionUtil.memberEquals(this.getLocation(),
		// other.getLocation()))
		// {
		// return false;
		// }

		if (this.isPrivate != other.isPrivate)
		{
			return false;
		}

		if (!CollectionUtil.deepEquals(this.getDatastreams(), other.getDatastreams()))
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

		if (!(obj instanceof Feed))
		{
			return false;
		}

		Feed other = (Feed) obj;

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
}
