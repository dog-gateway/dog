/*
 * Dog 2.0 - Modbus Network Driver
 * 
 * Copyright [2012] 
 * [Dario Bonino (dario.bonino@polito.it), Politecnico di Torino] 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package it.polito.elite.dog.drivers.modbus.network.info;

import it.polito.elite.dog.drivers.modbus.network.regxlators.RegXlator;
import it.polito.elite.dog.drivers.modbus.network.regxlators.RegXlatorTypes;

import java.net.InetAddress;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>, Politecnico
 *         di Torino
 * @author <a href="mailto:muhammad.sanaullah@polito.it">Muhammad Sanaullah</a>,
 *         Politecnico di Torino
 * 
 * @since Jan 13, 2012
 */
public class ModbusRegisterInfo
{
	// the IP address of the gateway to which a specific device is connected
	private InetAddress gatewayIPAddress;
	
	// the modbus port exposed by the gateway
	private String gatewayPort;
	
	// the protocol variant used by the gateway
	private String gatewayProtocol;
	
	// the slave ID of a specific device when connected to the given gateway
	private int slaveId;
	
	// the register address
	private int address;
	
	//the register xlator
	private RegXlator xlator;
	
	// the register length
	private int registerLenghtInBits;
	private int registerLenghtInWords;
	
	/**
	 * The class constructor, given a register address and type, creates a new
	 * register instance.
	 * 
	 * @param address
	 *            the register address
	 */
	public ModbusRegisterInfo(int address, int typeId)
	{
		// store the address
		this.address = address;
		
		//create the suitable xlator to handle translations to/from this register
		this.xlator = RegXlatorTypes.createRegXlator(typeId);
		
		//set the register size..
		this.setRegisterLenghtInWords(this.xlator.getTypeSize()/2);
		
	}
	
	/**
	 * Provides the address of the represented Modbus register
	 * 
	 * @return the address
	 */
	public int getAddress()
	{
		return address;
	}
	
	/**
	 * Sets the address of the modbus register represented by this instance
	 * 
	 * @param address
	 *            the address to set
	 */
	public void setAddress(int address)
	{
		this.address = address;
	}
	
	
	/**
	 * Provide the register length in bits (integer number)
	 * 
	 * @return the registerLenghtInBits
	 */
	public int getRegisterLenghtInBits()
	{
		return registerLenghtInBits;
	}
	
	/**
	 * Sets the register length in bits (integer number), updates the register
	 * length in words accordingly.
	 * 
	 * @param registerLenghtInBits
	 *            the registerLenghtInBits to set
	 */
	public void setRegisterLenghtInBits(int registerLenghtInBits)
	{
		// fill the register length in bits
		this.registerLenghtInBits = registerLenghtInBits;
		
		// convert the bit amount to the corresponding word amount and store it
		this.registerLenghtInWords = (int) Math.ceil((double) this.registerLenghtInBits / 16);
	}
	
	/**
	 * Provides the register length in words(number of words required to
	 * represent the register length in bits, i.e. ceiling).
	 * 
	 * @return the registerLenghtInWords
	 */
	public int getRegisterLenghtInWords()
	{
		return registerLenghtInWords;
	}
	
	/**
	 * Sets the register length in words. Updates the register length in bits
	 * accordingly.
	 * 
	 * @param registerLenghtInWords
	 *            the registerLenghtInWords to set
	 */
	public void setRegisterLenghtInWords(int registerLenghtInWords)
	{
		// fill the register length in words
		this.registerLenghtInWords = registerLenghtInWords;
		
		// convert the word amount to bits
		this.registerLenghtInBits = 16 * this.registerLenghtInWords;
	}
	
	/**
	 * Get the IP address of the gateway handling this register (device
	 * connected to the gateway and abstracted as a register value)
	 * 
	 * @return the gatewayIPAddress
	 */
	public InetAddress getGatewayIPAddress()
	{
		return gatewayIPAddress;
	}
	
	/**
	 * Set the IP address of the gateway handling this register (device
	 * connected to the gateway and abstracted as a register value)
	 * @param gatewayIPAddress
	 *            the gatewayIPAddress to set
	 */
	public void setGatewayIPAddress(InetAddress gatewayIPAddress)
	{
		this.gatewayIPAddress = gatewayIPAddress;
	}
	
	/**
	 * Get the SlaveId of the device
	 * connected to the gateway and abstracted as a register value
	 * @return the slaveId
	 */
	public int getSlaveId()
	{
		return slaveId;
	}
	
	/**
	 * Set the SlaveId of the device
	 * connected to the gateway and abstracted as a register value
	 * @param slaveId
	 *            the slaveId to set
	 */
	public void setSlaveId(int slaveId)
	{
		this.slaveId = slaveId;
	}

	/**
	 * @return the xlator
	 */
	public RegXlator getXlator()
	{
		return xlator;
	}

	/**
	 * @param xlator the xlator to set
	 */
	public void setXlator(RegXlator xlator)
	{
		this.xlator = xlator;
	}

	/**
	 * 
	 * @param gwPort
	 */
	public void setGatewayPort(String gwPort)
	{
		this.gatewayPort = gwPort;
		
	}

	/**
	 * @return the gatewayPort
	 */
	public String getGatewayPort()
	{
		return gatewayPort;
	}

	/**
	 * @return the gatewayProtocol
	 */
	public String getGatewayProtocol()
	{
		return gatewayProtocol;
	}

	/**
	 * @param gatewayProtocol the gatewayProtocol to set
	 */
	public void setGatewayProtocol(String gatewayProtocol)
	{
		this.gatewayProtocol = gatewayProtocol;
	}
		
}
