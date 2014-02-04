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
package it.polito.elite.dog.drivers.zwave.meteringpoweroutlet;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.drivers.zwave.device.ZWaveDeviceDriver;
import it.polito.elite.dog.drivers.zwave.network.ZWaveDriverInstance;
import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;

import java.util.HashSet;

import org.osgi.framework.BundleContext;

public class ZWaveMeteringPowerOutletDriver extends ZWaveDeviceDriver
{
	public ZWaveMeteringPowerOutletDriver()
	{
		super();
	}

	@Override
	public ZWaveDriverInstance createZWaveDriverInstance(
			ZWaveNetwork zWaveNetwork, ControllableDevice device, int nodeId,
			HashSet<Integer> instancesId, int gatewayNodeId,
			int updateTimeMillis, BundleContext context)
	{
		return new ZWaveMeteringPowerOutletInstance(zWaveNetwork, device, nodeId, instancesId, gatewayNodeId, updateTimeMillis, context);
	}

	@Override
	public void properFillDeviceCategories()
	{
		{
			for (Class<?> devCat : ZWaveMeteringPowerOutletInstance.class
					.getInterfaces())
			{
				this.deviceCategories.add(devCat.getName());
			}
		}
		
	}
	
	
	
}
