/**
 * 
 */
package com.xively.client.http.api;

import com.xively.client.model.DeviceActivation;

/**
 * @author bonino
 *
 */
public interface ActivationRequester
{
	public DeviceActivation activateDevice(String productSecret, String deviceSerial);
}
