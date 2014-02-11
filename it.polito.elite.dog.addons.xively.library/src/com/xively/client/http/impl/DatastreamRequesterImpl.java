// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.xively.client.http.DefaultRequestHandler;
import com.xively.client.http.DefaultRequestHandler.HttpMethod;
import com.xively.client.http.Response;
import com.xively.client.http.api.DatastreamRequester;
import com.xively.client.http.exception.HttpException;
import com.xively.client.http.util.exception.ParseToObjectException;
import com.xively.client.model.Datastream;

/**
 * Class for making request for datastream from XIVELY API
 * 
 * {@link https ://xively.com/docs/v2/datastream/}
 * 
 * 
 * @author s0pau
 * 
 */
public class DatastreamRequesterImpl extends AbstractRequester<String, Datastream> implements DatastreamRequester
{
	private int feedId;

	public DatastreamRequesterImpl(int feedId)
	{
		this.feedId = feedId;
	}

	@Override
	public Collection<Datastream> create(Datastream... toCreate) throws HttpException
	{
		DefaultRequestHandler.getInstance().doRequest(HttpMethod.POST, getResourcesPath(), toCreate);
		return Arrays.asList(toCreate);
	}

	@Override
	public Datastream getHistoryWithDatapoints(String dataStreamId, String startAt, String endAt, int samplingInterval)
			throws HttpException, ParseToObjectException
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", startAt);
		params.put("end", endAt);
		params.put("interval", samplingInterval);

		Response<Datastream> response = DefaultRequestHandler.getInstance().doRequest(HttpMethod.GET,
				getResourcePath(dataStreamId), params);
		return response.getBodyAsObject(Datastream.class);
	}

	@Override
	protected String getResourcePath(String dataStreamId)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getResourcesPath()).append("/").append(dataStreamId);
		return sb.toString();
	}

	@Override
	protected String getResourcesPath()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("feeds").append("/").append(String.valueOf(feedId)).append("/").append("datastreams");
		return sb.toString();
	}

	@Override
	protected Class<Datastream> getObjectClass()
	{
		return Datastream.class;
	}
}
