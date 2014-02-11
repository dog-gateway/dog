/**
 * 
 */
package com.xively.client.http.impl;

import com.xively.client.http.DefaultRequestHandler;
import com.xively.client.http.DefaultRequestHandler.HttpMethod;
import com.xively.client.http.Response;
import com.xively.client.http.api.ActivationRequester;
import com.xively.client.model.DeviceActivation;
import com.xively.client.utils.HmacUtils;

/**
 * @author bonino
 * @param <T>
 * @param <I>
 * 
 */
public class ActivationRequesterImpl extends
		AbstractRequester<String, DeviceActivation> implements
		ActivationRequester
{

	private static final String RESOURCES_PATH = "devices/%s/activate";

	@Override
	protected String getResourcesPath()
	{
		return ActivationRequesterImpl.RESOURCES_PATH;
	}

	@Override
	protected String getResourcePath(String id)
	{
		return String.format(ActivationRequesterImpl.RESOURCES_PATH, id);
	}

	@Override
	protected Class<ActivationRequesterImpl> getObjectClass()
	{
		return ActivationRequesterImpl.class;
	}

	@Override
	public DeviceActivation activateDevice(String productSecret,
			String deviceSerial)
	{
		DeviceActivation toReturn = null;

		// compute the activation code
		String activationCode = HmacUtils.hmacSha1(productSecret, deviceSerial);

		// get the response
		Response<DeviceActivation> response = DefaultRequestHandler
				.getInstance().doRequest(HttpMethod.GET,
						getResourcePath(activationCode));

		// extract the activation object
		toReturn = response.getBodyAsObject(DeviceActivation.class);

		return toReturn;
	}
}
