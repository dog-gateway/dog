// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http.impl;

import com.xively.client.http.DefaultRequestHandler;
import com.xively.client.http.DefaultRequestHandler.HttpMethod;
import com.xively.client.http.Response;
import com.xively.client.http.exception.HttpException;
import com.xively.client.http.util.exception.ParseToObjectException;
import com.xively.client.model.DomainObject;

public abstract class AbstractRequester<I extends Object, T extends DomainObject>
{
	@SuppressWarnings("unchecked")
	public T create(T toCreate) throws HttpException
	{
		Response<T> response = DefaultRequestHandler.getInstance().doRequest(HttpMethod.POST, getResourcesPath(), toCreate);
		I id = (I) (toCreate.getId() == null ? getCreatedId(response) : toCreate.getId());
		return get(id);
	}

	@SuppressWarnings("unchecked")
	public T get(I id) throws HttpException, ParseToObjectException
	{
		Response<T> response = DefaultRequestHandler.getInstance().doRequest(HttpMethod.GET, getResourcePath(id));
		return response.getBodyAsObject(getObjectClass());
	}

	public void delete(I id) throws HttpException
	{
		DefaultRequestHandler.getInstance().doRequest(HttpMethod.DELETE, getResourcePath(id));
	}

	@SuppressWarnings("unchecked")
	public T update(T toUpdate) throws HttpException
	{
		DefaultRequestHandler.getInstance().doRequest(HttpMethod.PUT, getResourcePath((I) toUpdate.getId()), toUpdate);
		return toUpdate;
	}

	/**
	 * Override this method if the id provided in the response after create.
	 * 
	 * @param response
	 * @return id from the headers of response, in the correct type
	 */
	protected I getCreatedId(Response<T> response)
	{
		return null;
	}

	/**
	 * @return the restful path to resources, e.g. http://url/resources
	 */
	abstract protected String getResourcesPath();

	/**
	 * @return the restful path to a resource given the id, e.g.
	 *         http://url/resources/id
	 */
	abstract protected String getResourcePath(I id);

	/**
	 * @return the class of object handled by this requester
	 */
	@SuppressWarnings("rawtypes")
	abstract protected Class getObjectClass();
}
