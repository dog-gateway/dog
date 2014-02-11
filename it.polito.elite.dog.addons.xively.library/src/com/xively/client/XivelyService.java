// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client;

import com.xively.client.http.api.ActivationRequester;
import com.xively.client.http.api.ApiKeyRequester;
import com.xively.client.http.api.DatapointRequester;
import com.xively.client.http.api.DatastreamRequester;
import com.xively.client.http.api.FeedRequester;
import com.xively.client.http.api.TriggerRequester;
import com.xively.client.http.impl.ActivationRequesterImpl;
import com.xively.client.http.impl.ApiKeyRequesterImpl;
import com.xively.client.http.impl.DatapointRequesterImpl;
import com.xively.client.http.impl.DatastreamRequesterImpl;
import com.xively.client.http.impl.FeedRequesterImpl;
import com.xively.client.http.impl.TriggerRequesterImpl;

/**
 * Xively Service Locator <br/>
 * <br/>
 * For example: to create a feed: XivelyService.instance().feed().create(<Feed
 * object>);
 * 
 * @author s0pau
 */
public class XivelyService
{
	private static XivelyService instance;

	private XivelyService()
	{
	}

	public static XivelyService instance()
	{
		if (instance == null)
		{
			instance = new XivelyService();
		}
		return instance;
	}

	public FeedRequester feed()
	{
		return new FeedRequesterImpl();
	}

	public DatastreamRequester datastream(Integer feedId)
	{
		return new DatastreamRequesterImpl(feedId);
	}

	public DatapointRequester datapoint(Integer feedId, String datastreamId)
	{
		return new DatapointRequesterImpl(feedId, datastreamId);
	}

	public ApiKeyRequester apiKey()
	{
		return new ApiKeyRequesterImpl();

	}

	public TriggerRequester trigger()
	{
		return new TriggerRequesterImpl();
	}
	
	public ActivationRequester activation()
	{
		return new ActivationRequesterImpl();
	}
}
