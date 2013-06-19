/*
 * Dog 2.0 - Modbus Network Driver
 * 
 * Copyright [2012] 
 * [Dario Bonino (dario.bonino@polito.it), Politecnico di Torino] 
 * [Muhammad Sanaullah (muhammad.sanaullah@polito.it), Politecnico di Torino] 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package it.polito.elite.dog.drivers.modbus.network.interfaces;

import java.util.Set;

import it.polito.elite.dog.drivers.modbus.network.ModbusDriver;
import it.polito.elite.dog.drivers.modbus.network.info.ModbusRegisterInfo;



/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>, Politecnico di Torino 
 * @author <a href="mailto:muhammad.sanaullah@polito.it">Muhammad Sanaullah</a>, Politecnico di Torino 
 *
 * @since Jan 18, 2012
 */
public interface ModbusNetwork
{
	/**
	 * Read the current value associated to the given register...
	 * 
	 * @param register
	 *            the register unique identifier.
	 */
	public void read(ModbusRegisterInfo register);
	
	/**
	 * Read the current value of all the registers included in the given set
	 * 
	 * @param registers The {@link Set}<{@link ModbusRegisterInfo}> of register to read.
	 */
	public void readAll(Set<ModbusRegisterInfo> registers);
	
	/**
	 * Writes a given command to a given Modbus register
	 * 
	 * @param register
	 *            the register unique identifier.
	 * @param commandValue
	 *            the command value to send.
	 */
	public void write(ModbusRegisterInfo register, String commandValue);
	
	/**
	 * Adds a new device-specific driver for the given register
	 * @param register
	 *            the register unique identifier.
	 * @param driver the {@link ModbusDriver} instance to add.
	 */
	public void addDriver(ModbusRegisterInfo register, ModbusDriver driver);
	
	/**
	 * Removes a device-specific driver for the given register
	 * @param register
	 *            the register unique identifier.
	 */
	public void removeDriver(ModbusRegisterInfo register);
	
	/**
	 * Removes the driver-register associations for the given driver. To be called when a specific driver disconnects
	 * 
	 * @param datapoint
	 */
	public void removeDriver(ModbusDriver driver);
}
