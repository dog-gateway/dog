/*
 * Dog - EnOcean Network Driver
 * 
 * Copyright 2015 Dario Bonino 
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
package org.doggateway.drivers.enocean.network.interfaces;

import org.doggateway.drivers.enocean.network.info.EnOceanDeviceInfo;

/**
 * Listener for reacting to the discovery of new EnOcean devices, typically
 * triggered after a teach-in procedure
 * 
 * @author <a href="mailto:dario.bonino@gmail.com">Dario Bonino</a>
 *
 */
public interface EnOceanDeviceDiscoveryListener
{
	/**
	 * Handles the addition of a new enOcean device at the low-level, this might
	 * correspond to the "generation" of a new device in Dog.
	 * 
	 * @param devInfo
	 *            The {@link EnOceanDeviceInfo} describing the newly added
	 *            device.
	 */
	public void addedEnOceanDevice(EnOceanDeviceInfo devInfo);

}
