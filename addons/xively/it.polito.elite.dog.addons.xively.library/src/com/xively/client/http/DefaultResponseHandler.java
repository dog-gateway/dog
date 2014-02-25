// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.log4j.Logger;

import com.xively.client.http.exception.HttpException;
import com.xively.client.model.DomainObject;

/**
 * Class for handling the http response
 * 
 * @author s0pau
 * @param <T>
 * 
 */
public class DefaultResponseHandler<T extends DomainObject> implements ResponseHandler<Response<T>>
{
	private static Logger log = Logger.getLogger(DefaultResponseHandler.class);

	@Override
	public Response<T> handleResponse(HttpResponse response) throws ClientProtocolException, IOException
	{
		int statusCode = response.getStatusLine().getStatusCode();
		log.info(String.format("Handling response [%s]", statusCode));

		if (!isHttpStatusOK(response.getStatusLine().getStatusCode()))
		{
			String errorDetail = null;
			try
			{
				errorDetail = parseHttpEntity(response.getEntity());
			} catch (IOException swallow)
			{
				log.warn("Unable to parse response error detail");
			}

			throw new HttpException("Http response status indicates unsuccessful operation", statusCode, errorDetail);
		}

		Response<T> retval = new Response<T>();

		retval.setStatusCode(statusCode);

		try
		{
			retval.setBody(parseHttpEntity(response.getEntity()));
		} catch (IOException e)
		{
			throw new HttpException("Http response [%s] but body cannot be parsed.", e);
		}

		Map<String, String> headers = new HashMap<String, String>();
		for (Header header : response.getAllHeaders())
		{
			headers.put(header.getName(), header.getValue());
		}
		retval.setHeaders(headers);

		return retval;
	}

	private String parseHttpEntity(HttpEntity entity) throws IOException
	{
		StringBuilder bodyBuilder = null;
		try 
		{
			InputStream contentStream = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(contentStream));
			bodyBuilder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null)
			{
				bodyBuilder.append(line + "\n");
			}
		}
		catch(Exception e)
		{
			
		}
		return bodyBuilder.toString();
	}

	private boolean isHttpStatusOK(int statusCode)
	{
		if (statusCode >= 300)
		{
			return false;
		}

		return true;
	}
}
