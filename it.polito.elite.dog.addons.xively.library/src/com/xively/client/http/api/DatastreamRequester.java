// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http.api;

import java.util.Collection;

import com.xively.client.http.exception.HttpException;
import com.xively.client.http.impl.DatastreamRequesterImpl;
import com.xively.client.http.util.exception.ParseToObjectException;
import com.xively.client.model.Datastream;

public interface DatastreamRequester
{
	/**
	 * @param toCreate
	 *            datastream to be created datapoint to be created over the API
	 * @return the datastream with created fields populated, on successful
	 *         operation
	 * @throws HttpException
	 *             if failed to create datastream over the API
	 */
	public Datastream create(Datastream toCreate) throws HttpException;

	/**
	 * @param toCreate
	 *            one or more datastreams to be created datapoint to be created
	 *            over the API
	 * @return a collection of datastream with created fields populated, on
	 *         successful operation
	 * @throws HttpException
	 *             if failed to create datastream over the API
	 * @see DatastreamRequesterImpl#create(int, Datastream) for creating and
	 *      returning one datastream
	 */
	public Collection<Datastream> create(Datastream... toCreate) throws HttpException;

	/**
	 * @param dataStreamId
	 *            the id of the datastream to be retrieved
	 * @return a datastream object parsed from the json returned from the API
	 * @throws HttpException
	 *             if failed to get datastream over the API
	 * @throws ParseToObjectException
	 *             if failed to parse the returned json to datastream
	 */
	public Datastream get(String dataStreamId) throws HttpException, ParseToObjectException;

	/**
	 * @param dataStreamId
	 * @param startAt
	 * @param endAt
	 * @param samplingInterval
	 * @return datastream with dataStreamId contain a collection of datapoints
	 *         that has an at value between the startAt and endAt
	 * @throws HttpException
	 *             if failed to get datastream over the API
	 * @throws ParseToObjectException
	 *             if failed to parse the returned json to datastream
	 */
	public Datastream getHistoryWithDatapoints(String dataStreamId, String startAt, String endAt, int samplingInterval)
			throws HttpException, ParseToObjectException;

	/**
	 * @param toUpdate
	 *            datastream to be updated over the API
	 * @return the datastream that was passed in, on successful operation
	 * @throws HttpException
	 *             if failed to create datastream over the API
	 */
	public Datastream update(Datastream toUpdate) throws HttpException;

	/**
	 * @param dataStreamId
	 *            the datastream to be deleted over the API
	 * @throws HttpException
	 *             if failed to delete the datastream over the API
	 */
	public void delete(String dataStreamId) throws HttpException;
}
