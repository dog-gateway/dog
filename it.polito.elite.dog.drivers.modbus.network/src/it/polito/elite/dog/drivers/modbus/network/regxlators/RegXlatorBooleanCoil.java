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
package it.polito.elite.dog.drivers.modbus.network.regxlators;

import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ReadCoilsRequest;
import net.wimpi.modbus.msg.ReadCoilsResponse;
import net.wimpi.modbus.msg.WriteCoilRequest;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>, Politecnico di Torino 
 *
 * @since Feb 28, 2012
 */
public class RegXlatorBooleanCoil extends RegXlator
{
	
	/**
	 * 
	 */
	public RegXlatorBooleanCoil()
	{
		super();
		this.typeSize = 0;
	}
	
	/* (non-Javadoc)
	 * @see it.polito.elite.dog.drivers.modbus.network.regxlators.RegXlator#getValue()
	 */
	@Override
	public String getValue()
	{
		Boolean value = null;
		
		if((this.readResponse!=null)&&(this.readResponse instanceof ReadCoilsResponse))
		{
			 value =((ReadCoilsResponse)this.readResponse).getCoilStatus(0);
		}
		return (value!=null)?value.toString():null;
	}

	@Override
	public ModbusRequest getWriteRequest(int address, String value)
	{
		this.writeRequest = new WriteCoilRequest(address, Boolean.parseBoolean(value));
		return this.writeRequest;
	}

	@Override
	public ModbusRequest getReadRequest(int address)
	{
		this.readRequest = new ReadCoilsRequest(address, 1);
		return this.readRequest;
	}
	
}
