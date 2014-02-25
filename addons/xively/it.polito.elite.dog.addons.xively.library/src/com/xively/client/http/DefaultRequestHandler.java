// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.log4j.Logger;

import com.xively.client.AppConfig;
import com.xively.client.AppConfig.AcceptedMediaType;
import com.xively.client.http.exception.HttpException;
import com.xively.client.http.exception.RequestInvalidException;
import com.xively.client.http.util.ParserUtil;
import com.xively.client.model.DomainObject;
import com.xively.client.utils.StringUtil;

/**
 * Handler for building and making requests, uses {@link DefaultResponseHandler}
 * to process the response
 *
 * @author s0pau
 */
public class DefaultRequestHandler
{
	private static Logger log = Logger.getLogger(DefaultRequestHandler.class);

	private static final String HEADER_KEY_API = "X-ApiKey";
	private static final String HEADER_USER_AGENT = "User Agent";
	private static final String XIVELY_USER_AGENT = "Xively-Java-Lib/0.1.0-SNAPSHOT";

	private String baseURI;

	private HttpClient httpClient;

	private static DefaultRequestHandler instance;

	public enum HttpMethod
	{
		DELETE, GET, POST, PUT;
	}

	private DefaultRequestHandler()
	{
		// singleton
	}

	private DefaultRequestHandler(String baseURI)
	{
		this.baseURI = baseURI;
	}

	public static DefaultRequestHandler getInstance()
	{
		if (instance == null)
		{
			instance = new DefaultRequestHandler(AppConfig.getInstance().getBaseURI());
		}
		return instance;
	}

	public <T extends DomainObject> Response<T> doRequest(HttpMethod requestMethod, String appPath)
	{
		return doRequest(requestMethod, appPath, (Map<String, Object>) null);
	}

	public <T extends DomainObject> Response<T> doRequest(HttpMethod requestMethod, String appPath, T... bodyObjects)
	{
		return doRequest(requestMethod, appPath, null, bodyObjects);
	}

	public <T extends DomainObject> Response<T> doRequest(HttpMethod requestMethod, String appPath, Map<String, Object> params)
	{
		return doRequest(requestMethod, appPath, params, (T[])null);
	}

	/**
	 * * Make the request to Xively API and return the response string
	 *
	 * @param <T extends ConnectedObject>
	 *
	 * @param requestMethod
	 *            http request methods
	 * @param appPath
	 *            restful app path
	 * @param bodyObjects
	 *            objects to be parsed as body for api call
	 * @param params
	 *            key-value of params for api call
	 *
	 * @return response string
	 */

	private <T extends DomainObject> Response<T> doRequest(HttpMethod requestMethod, String appPath,
			Map<String, Object> params, T... bodyObjects)
	{
		Response<T> response = null;
		HttpRequestBase request = buildRequest(requestMethod, appPath, params, bodyObjects);

		DefaultRequestHandler.log.debug(String.format("Requesting %s", request.getURI()));

		try
		{
			DefaultResponseHandler<T> responseHandler = new DefaultResponseHandler<T>();
			response = getClient().execute(request, responseHandler);
		} catch (HttpException e)
		{
			throw e;
		} catch (IOException e)
		{
			throw new HttpException("Http request did not return successfully.", e);
		} catch (RuntimeException e)
		{
			// release resources manually on unexpected exceptions
			request.abort();
			throw new HttpException("Http request did not return successfully.", e);
		}

		return response;
	}

	private HttpClient getClient()
	{
		if (httpClient == null)
		{
			httpClient = HttpClientBuilder.getInstance().getHttpClient();
		}
		return httpClient;
	}

	private <T extends DomainObject> HttpRequestBase buildRequest(HttpMethod requestMethod, String appPath,
			Map<String, Object> params, T... bodyObjects)
	{
		AcceptedMediaType mediaType = AppConfig.getInstance().getResponseMediaType();

		HttpRequestBase request = null;
		switch (requestMethod)
		{
			case DELETE:
				request = new HttpDelete();
				break;

			case GET:
				request = new HttpGet();
				break;

			case POST:
				request = new HttpPost();
				StringEntity postEntity = getEntity(false, bodyObjects);
				((HttpPost) request).setEntity(postEntity);
				break;

			case PUT:
				request = new HttpPut();
				StringEntity putEntity = getEntity(true, bodyObjects);
				((HttpPut) request).setEntity(putEntity);
				break;

			default:
				return null;
		}

		URIBuilder uriBuilder = buildUri(requestMethod, appPath, params, mediaType);
		try
		{
			request.setURI(uriBuilder.build());
		} catch (URISyntaxException e)
		{
			throw new RequestInvalidException("Invalid URI requested.", e);
		}

		request.addHeader("accept", mediaType.getMediaType());
		request.addHeader(HEADER_KEY_API, AppConfig.getInstance().getApiKey());
		request.addHeader(HEADER_USER_AGENT, XIVELY_USER_AGENT);

		if (log.isDebugEnabled())
		{
			StringBuilder sb = new StringBuilder();
			for (Header header : request.getAllHeaders())
			{
				sb.append(header.getName()).append(",").append(header.getValue()).append(";");
			}
			log.debug(String.format("Constructed request with uri [%s], header [%s]", uriBuilder.toString(), sb.toString()));
		}

		return request;
	}

	private URIBuilder buildUri(HttpMethod requestMethod, String appPath, Map<String, Object> params, AcceptedMediaType mediaType)
	{
		URIBuilder uriBuilder = new URIBuilder();
		String path = appPath;
		if (HttpMethod.GET == requestMethod)
		{
			path = appPath.concat(".").concat(mediaType.name());
		}
		uriBuilder.setScheme("http").setHost(baseURI).setPath(path);
		if (params != null && !params.isEmpty())
		{
			for (Entry<String, Object> param : params.entrySet())
			{
				uriBuilder.addParameter(param.getKey(), StringUtil.toString(param.getValue()));
			}
		}
		return uriBuilder;
	}

	private <T extends DomainObject> StringEntity getEntity(boolean isUpdate, T... bodyObjects)
	{
		AcceptedMediaType mediaType = AppConfig.getInstance().getResponseMediaType();
		String json = ParserUtil.toJson(isUpdate, bodyObjects);
		DefaultRequestHandler.log.debug("Sending: \n"+ json);
		StringEntity body = null;
		try
		{
			body = new StringEntity(json);
			body.setContentType(mediaType.getMediaType());
		} catch (UnsupportedEncodingException e)
		{
			throw new RequestInvalidException("Unable to encode json string for making request.", e);
		}

		return body;
	}
}
