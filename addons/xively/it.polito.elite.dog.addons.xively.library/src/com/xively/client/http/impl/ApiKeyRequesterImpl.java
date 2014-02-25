// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.xively.client.http.DefaultRequestHandler;
import com.xively.client.http.DefaultRequestHandler.HttpMethod;
import com.xively.client.http.Response;
import com.xively.client.http.api.ApiKeyRequester;
import com.xively.client.http.exception.HttpException;
import com.xively.client.http.exception.RequestInvalidException;
import com.xively.client.http.util.exception.ParseToObjectException;
import com.xively.client.model.ApiKey;

/**
 * Class for making request for apikey from XIVELY API
 * 
 * {@link https ://xively.com/docs/v2/keys/}
 * 
 * 
 * @author s0pau
 * 
 */
public class ApiKeyRequesterImpl extends AbstractRequester<String, ApiKey> implements ApiKeyRequester
{
	private static final String RESOURCES_PATH = "keys";

	@Override
	public ApiKey update(ApiKey toUpdate) throws HttpException
	{
		throw new RequestInvalidException("This operation is currently unsupported.");
	}

	@Override
	public Collection<ApiKey> getByFeedId(int feedId) throws HttpException, ParseToObjectException
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("feed_id", feedId);

		Response<ApiKey> response = DefaultRequestHandler.getInstance().doRequest(HttpMethod.GET, getResourcesPath(), params);
		return response.getBodyAsObjects(ApiKey.class);
	}

	protected String getResourcePath(String apiKey)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(RESOURCES_PATH).append("/").append(apiKey);
		return sb.toString();
	}

	protected String getResourcesPath()
	{
		return RESOURCES_PATH;
	}

	@SuppressWarnings("rawtypes")
	protected Class getObjectClass()
	{
		return ApiKey.class;
	}

	@Override
	protected String getCreatedId(Response<ApiKey> response)
	{
		return response.getIdFromResponse();
	}
}
