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
import com.xively.client.http.api.FeedRequester;
import com.xively.client.http.exception.HttpException;
import com.xively.client.http.util.exception.ParseToObjectException;
import com.xively.client.model.Feed;

/**
 * Class for making request for feed from XIVELY API
 * 
 * {@link https ://xively.com/docs/v2/feed/}
 * 
 * @author s0pau
 * 
 */
public class FeedRequesterImpl extends AbstractRequester<Integer, Feed> implements FeedRequester
{
	private static final String RESOURCES_PATH = "feeds";

	@Override
	public Collection<Feed> list() throws HttpException, ParseToObjectException
	{
		Response<Feed> response = DefaultRequestHandler.getInstance().doRequest(HttpMethod.GET, getResourcesPath());
		return response.getBodyAsObjects(Feed.class);
	}

	@Override
	public Feed getHistoryWithDatastreams(Boolean isShowUser, String... dataStreamIds) throws HttpException,
			ParseToObjectException
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("datastreams", Arrays.asList(dataStreamIds));
		params.put("show_user", isShowUser == null ? false : isShowUser);

		Response<Feed> response = DefaultRequestHandler.getInstance().doRequest(HttpMethod.GET, getResourcesPath(), params);
		return response.getBodyAsObject(Feed.class);
	}

	@Override
	public Collection<Feed> getByLocation(Double latitude, Double longitude, Double distance, String distanceUnits)
			throws HttpException, ParseToObjectException
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lat", latitude);
		params.put("lon", longitude);
		params.put("distance", distance);
		params.put("distance_unit", distanceUnits);

		Response<Feed> response = DefaultRequestHandler.getInstance().doRequest(HttpMethod.GET, getResourcesPath(), params);
		return response.getBodyAsObjects(Feed.class);
	}

	@Override
	public Collection<Feed> get(Map<String, Object> filterParam) throws HttpException, ParseToObjectException
	{
		Response<Feed> response = DefaultRequestHandler.getInstance().doRequest(HttpMethod.GET, getResourcesPath(), filterParam);
		return response.getBodyAsObjects(Feed.class);
	}

	@Override
	public Feed getHistory(Integer feedId, String startAt, String endAt, int samplingInterval) throws HttpException,
			ParseToObjectException
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", startAt);
		params.put("end", endAt);
		params.put("interval", samplingInterval);

		Response<Feed> response = DefaultRequestHandler.getInstance().doRequest(HttpMethod.GET, getResourcePath(feedId), params);

		return response.getBodyAsObject(Feed.class);
	}

	@Override
	protected String getResourcePath(Integer feedId)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getResourcesPath()).append("/").append(String.valueOf(feedId));
		return sb.toString();
	}

	@Override
	protected String getResourcesPath()
	{
		return RESOURCES_PATH;
	}

	@Override
	protected Class<Feed> getObjectClass()
	{
		return Feed.class;
	}

	@Override
	protected Integer getCreatedId(Response<Feed> response)
	{
		return Integer.valueOf(response.getIdFromResponse());
	}
}
