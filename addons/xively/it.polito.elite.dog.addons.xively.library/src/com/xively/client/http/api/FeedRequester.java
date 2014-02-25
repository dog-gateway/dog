// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http.api;

import java.util.Collection;
import java.util.Map;

import com.xively.client.http.exception.HttpException;
import com.xively.client.http.util.exception.ParseToObjectException;
import com.xively.client.model.Feed;

public interface FeedRequester
{
	/**
	 * @param toCreate
	 *            the feed to be created over the API
	 * @return the feed created, on successful operation
	 * @throws HttpException
	 *             if failed to create feed over the API
	 * @throws ParseToObjectException
	 *             if failed to parse the returned json to feed
	 */
	public Feed create(Feed toCreate) throws HttpException;

	/**
	 * @param feedId
	 *            the id of the feed to be retrieved
	 * @return a feed object parsed from the json returned from the API
	 * @throws HttpException
	 *             if failed to get feed over the API
	 * @throws ParseToObjectException
	 *             if failed to parse the returned json to feed
	 */
	public Feed get(Integer feedId) throws HttpException, ParseToObjectException;

	/**
	 * @return all feed for this authenticated account
	 * @throws HttpException
	 *             if failed to get feed over the API
	 * @throws ParseToObjectException
	 *             if failed to parse the returned json to feed
	 */
	public Collection<Feed> list() throws HttpException, ParseToObjectException;

	/**
	 * @param isShowUser
	 * @param dataStreamIds
	 * @return a collection of feed objects matching the params, parsed from the
	 *         json returned from the API
	 * @throws HttpException
	 *             if failed to get feed over the API
	 * @throws ParseToObjectException
	 *             if failed to parse the returned json to feed
	 */
	public Feed getHistoryWithDatastreams(Boolean isShowUser, String... dataStreamIds) throws HttpException,
			ParseToObjectException;

	/**
	 * @param latitude
	 * @param longitude
	 * @param distance
	 * @param distanceUnits
	 * @return a collection of feed objects matching the params, parsed from the
	 *         json returned from the API
	 * @throws HttpException
	 *             if failed to get feed over the API
	 * @throws ParseToObjectException
	 *             if failed to parse the returned json to feed
	 */
	public Collection<Feed> getByLocation(Double latitude, Double longitude, Double distance, String distanceUnits)
			throws HttpException, ParseToObjectException;

	/**
	 * @param filterParam
	 * @return a collection of feed objects matching the parameters given,
	 *         parsed from the json returned from the API
	 * @throws HttpException
	 *             if failed to get feed over the API
	 * @throws ParseToObjectException
	 *             if failed to parse the returned json to feed
	 */
	public Collection<Feed> get(Map<String, Object> filterParam) throws HttpException, ParseToObjectException;

	/**
	 * @param feedId
	 * @param startAt
	 * @param endAt
	 * @param samplingInterval
	 * @return a feed with history on datastreams and datapoints that matches
	 *         the given parameters
	 * @throws HttpException
	 *             if failed to get feed over the API
	 * @throws ParseToObjectException
	 *             if failed to parse the returned json to feed
	 */
	public Feed getHistory(Integer feedId, String startAt, String endAt, int samplingInterval) throws HttpException,
			ParseToObjectException;

	/**
	 * @param toUpdate
	 *            the feed to be updated over the API
	 * @return the feed that was passed in, on successful operation
	 * @throws HttpException
	 *             if failed to get feed over the API
	 */
	public Feed update(Feed toUpdate) throws HttpException;

	/**
	 * @param feedId
	 *            the id of the feed to be deleted
	 * @throws HttpException
	 *             if failed to delete the feed over the API
	 */
	public void delete(Integer feedId) throws HttpException;
}
