/*
 * Dog  - Z-Wave
 * 
 * Copyright 2013 Davide Aimone  and Dario Bonino 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package it.polito.elite.dog.drivers.zwave.dimmerdevice;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.drivers.zwave.device.ZWaveDeviceDriver;
import it.polito.elite.dog.drivers.zwave.network.ZWaveDriverInstance;
import it.polito.elite.dog.drivers.zwave.network.info.ZWaveInfo;
import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;

import java.util.Dictionary;
import java.util.HashSet;

import org.osgi.framework.BundleContext;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.log.LogService;

public class ZWaveDimmerDeviceDriver extends ZWaveDeviceDriver
{
	// the step entity in percentage
	protected int stepPercentage;

	public ZWaveDimmerDeviceDriver()
	{
		super();
		this.driverInstanceClass = ZWaveDimmerDeviceDriverInstance.class;
	}

	@Override
	public ZWaveDriverInstance createZWaveDriverInstance(
			ZWaveNetwork zWaveNetwork, ControllableDevice device, int nodeId,
			HashSet<Integer> instancesId, int gatewayNodeId,
			int updateTimeMillis, BundleContext context)
	{

		return new ZWaveDimmerDeviceDriverInstance(zWaveNetwork, device,
				nodeId, instancesId, gatewayNodeId, updateTimeMillis,
				stepPercentage, context);
	}
	
	@Override
	public void updated(Dictionary<String, ?> properties) throws ConfigurationException
	{
		if (properties != null)
		{			
			// try to get the step entity
			String stepEntityAsString = (String) properties.get(ZWaveInfo.PROPERTY_STEP_PERCENT);
			
			// check not null
			if (stepEntityAsString != null)
			{
				// trim leading and trailing spaces
				stepEntityAsString = stepEntityAsString.trim();
				// parse the string
				this.stepPercentage = Integer.valueOf(stepEntityAsString);
			}
			else
			{
				this.logger.log(LogService.LOG_WARNING, ZWaveInfo.PROPERTY_STEP_PERCENT
						+ " not defined in configuraton file for "
						+ ZWaveDimmerDeviceDriver.class.getName());
			}
			
			// call the normal updated method
			super.updated(properties);
		}
	}
}
