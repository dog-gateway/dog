/*
 * Dog 2.0 - Network Driver
 * 
 * Copyright [2009] 
 * [Dario Bonino (dario.bonino@polito.it), Politecnico di Torino] 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */

package it.polito.elite.dog.drivers.knx.network.interfaces;

import it.polito.elite.dog.drivers.knx.network.KnxIPDriver;
import it.polito.elite.dog.drivers.knx.network.info.KnxIPDeviceInfo;
import tuwien.auto.calimero.dptxlator.DPT;

/**
 * An interface defining primitives that must be offered by classes implementing
 * a KNXnet/IP driver, and typical constants used for handling KNX network data
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * 
 */
public interface KnxIPNetwork
{
	/**
	 * Read the current value associated to the given device address...
	 * 
	 * @param deviceAddress
	 *            the KNX group address of the device.
	 */
	public void read(KnxIPDeviceInfo deviceAddress);
	
	/**
	 * Writes a given command to a given device
	 * 
	 * @param deviceAddress
	 *            the KNX group address of the device.
	 * @param commandValue
	 *            the command value to send.
	 */
	public void write(KnxIPDeviceInfo deviceAddress, String commandValue);
	
	/**
	 * Adds a new device-specific driver for the given device
	 * @param device the KnxIPDeviceInfo group address of the device.
	 * @param driver the {@link KnxIPDriver} instance to add.
	 */
	public void addDriver(KnxIPDeviceInfo device, int mainNumber, DPT deviceDPT, KnxIPDriver driver);
	
	/**
	 * Removes a device-specific driver for the given device
	 * @param device device the KnxIPDeviceInfo describing the device.
	 * @param driver driver the {@link KnxIPDriver} instance to remove.
	 */
	public void removeDriver(KnxIPDeviceInfo device);
}
