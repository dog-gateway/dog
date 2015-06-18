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

import it.polito.elite.enocean.enj.communication.EnJConnection;

import org.doggateway.drivers.enocean.network.EnOceanDriverInstance;
import org.doggateway.drivers.enocean.network.info.EnOceanDeviceInfo;

/**
 * @author bonino
 *
 */
public interface EnOceanNetwork
{
	/**
	 * Adds a new device-specific driver for the device identified by the given
	 * {@link EnOceanDeviceInfo} instance.
	 * 
	 * @param devInfo
	 *            The device info instance providing data for unique device
	 *            identification
	 * @param driver
	 *            The driver to which dispatch device updates (listener)
	 */
	public void addDriver(EnOceanDeviceInfo devInfo,
			EnOceanDriverInstance driver);

	/**
	 * Removes a given device-specific driver from the set of drivers
	 * "connected" to the network driver. This implies that all devices being
	 * connected to the removed driver only will also be "removed" from the set
	 * managed by the network driver and will not be reachable anymore by other
	 * platform bundles.
	 * 
	 * @param driver
	 *            The driver to remove.
	 */
	public void removeDriver(EnOceanDriverInstance driver);

	/**
	 * Adds a listener for handling the logic related to new device discovery,
	 * and to carry the corresponding tasks, including steps needed to register
	 * the new device in the OSGi framework.
	 * 
	 * @param listener
	 *            The device discovery listener to be added.
	 */
	public void addDeviceDiscoveryListener(
			EnOceanDeviceDiscoveryListener listener);

	/**
	 * Removes the given device discovery listener from the set of listeners
	 * being notified when a new device is discovered at the network level.
	 * 
	 * @param listener
	 */
	public void removeDeviceDiscoveryListener(
			EnOceanDeviceDiscoveryListener listener);
	
	/**
	 * Adds a listener for handling the logic related to new device discovery,
	 * and to carry the corresponding tasks, including steps needed to register
	 * the new device in the OSGi framework.
	 * 
	 * @param listener
	 *            The device discovery listener to be added.
	 */
	public void addTeachInActivationListener(
			EnOceanTeachInActivationListener listener);

	/**
	 * Removes the given device discovery listener from the set of listeners
	 * being notified when a new device is discovered at the network level.
	 * 
	 * @param listener
	 */
	public void removeTeachInActivationListener(
			EnOceanTeachInActivationListener listener);

	/**
	 * Sets the EnOcean gateway device in "Teach In" mode which enables learning
	 * of new devices available on the physical network. Teach-in can either be
	 * simple, i.e., expecting that all information needed to interface the
	 * device is provided by the device itself during the learning phase, or can
	 * be smart, i.e., trying to infer possibly missing information such as the
	 * device EEP, etc. As long as a device provides the full information during
	 * the learning phase, the "Smart Teach In" and the "Teach In" are
	 * completely equivalent.
	 * 
	 * @param timeoutMillis
	 *            The time for which the EnOcean physical gateway should remain
	 *            in the "learning" mode.
	 * @param smart
	 *            The smart teach-in flag: false means disabled.
	 */
	public void enableTeachIn(int timeoutMillis, boolean smart);

	/**
	 * Sets the EnOcean gateway device in the "Explicit Teach In" mode. This
	 * mode is required for simpler devices, such as rocker switches and
	 * temperature sensors, that do not provide any information during the
	 * learning phase (and only set a learning bit at 1). In such a case the
	 * information needed to identify and correctly handle the device should be
	 * specified "a priori" by the application interfacing the devices.
	 * 
	 * @param deviceLowAddress
	 *            The low-level address of the device in hexadecimal notation
	 *            (typically reported on the device case).
	 * @param deviceEEP
	 *            The device EEP either reported on the device case or on the
	 *            device user manual.
	 * @param timeoutMillis
	 *            The time for which the EnOcean physical gateway should remain
	 *            in the "learning" mode.
	 */
	public void enableExplicitTeachIn(String deviceLowAddress,
			String deviceEEP, int timeoutMillis);

	/**
	 * Permits the programmatic addition of "devices" to the set of devices
	 * currently handled by the underlying EnOcean gateway library. It is
	 * typically used in configuration-based set ups, where information about
	 * devices currently installed is persisted at the application level.
	 * 
	 * @param deviceLowAddress
	 *            The low-level address of the device in hexadecimal notation
	 *            (typically reported on the device case).
	 * @param deviceEEP
	 *            The device EEP either reported on the device case or on the
	 *            device user manual.
	 */
	public void addDevice(String deviceLowAddress, String deviceEEP);

	/**
	 * Provides a "direct" pointer to the EnJConnection object managed by the
	 * driver, permits single driver instances to send device-specific commands.
	 * 
	 * @return
	 */
	public EnJConnection getConnection();
}
