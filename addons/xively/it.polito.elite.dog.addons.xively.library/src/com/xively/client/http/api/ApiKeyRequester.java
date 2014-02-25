// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http.api;

import java.util.Collection;

import com.xively.client.http.exception.HttpException;
import com.xively.client.http.util.exception.ParseToObjectException;
import com.xively.client.model.ApiKey;

public interface ApiKeyRequester
{
	/**
	 * @param toCreate
	 *            apikey to be created over the API
	 * @return an apiKey object with created fields populated
	 * @throws HttpException
	 *             if failed to create apikey over the API
	 */
	public ApiKey create(ApiKey toCreate) throws HttpException;

	/**
	 * @param apiKey
	 *            the id of the apiKey to be retrieved
	 * @return a apiKey object parsed from the json returned from the API
	 * @throws HttpException
	 *             if failed to get apiKey over the API
	 * @throws ParseToObjectException
	 *             if failed to parse the returned json to apiKey
	 */
	public ApiKey get(String apiKey) throws HttpException, ParseToObjectException;

	/**
	 * @param triggerId
	 *            the id of the trigger to be retrieved
	 * @param feedId
	 * @return a collection of trigger objects matching the params, parsed from
	 *         the json returned from the API
	 * @throws HttpException
	 *             if failed to get trigger over the API
	 * @throws ParseToObjectException
	 *             if failed to parse the returned json to trigger
	 */
	public Collection<ApiKey> getByFeedId(int feedId) throws HttpException, ParseToObjectException;

	/**
	 * @param apiKey
	 *            the id of the apikey to be deleted over the API
	 * @throws HttpException
	 *             if failed to delete the apikey over the API
	 */
	public void delete(String apiKey) throws HttpException;
}
