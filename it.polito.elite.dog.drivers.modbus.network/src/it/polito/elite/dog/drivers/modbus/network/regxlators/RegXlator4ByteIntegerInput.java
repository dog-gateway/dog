/*
 * Dog - Network Driver
 * 
 * Copyright (c) 2013 Dario Bonino
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
package it.polito.elite.dog.drivers.modbus.network.regxlators;

import java.nio.ByteBuffer;
import java.util.Locale;

import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.procimg.InputRegister;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.procimg.SimpleRegister;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 * @since May 28, 2013
 */
public class RegXlator4ByteIntegerInput extends RegXlator
{
	
	/**
	 * 
	 */
	public RegXlator4ByteIntegerInput()
	{
		super();
		this.typeSize = 4;
	}
	
	/**
	 * Translates a couple of 2-byte registers into a 4-byte register and
	 * extracts the corresponding integer value
	 * 
	 * TODO: check if it works...
	 * 
	 * @param registers
	 * @return
	 */
	public int fromRegister(InputRegister[] registers)
	{
		byte bytes[] = new byte[this.typeSize];
		
		// swap bytes
		byte highWord[] = registers[0].toBytes();
		byte lowWord[] = registers[1].toBytes();
		
		// copy the two byte in the response byte array
		bytes[3] = highWord[1];
		bytes[2] = highWord[0];
		bytes[1] = lowWord[1];
		bytes[0] = lowWord[0];
		
		// wrap the byte array
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		
		return buffer.getInt();
	}
	
	/**
	 * Translates an integer value into a couple of 2-byte registers
	 * 
	 * @param value
	 * @return
	 */
	public Register[] toRegister(int value)
	{
		byte bytes[] = new byte[this.typeSize];
		
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		
		buffer.putInt(value);
		
		Register registers[] = new SimpleRegister[2];
		registers[0] = new SimpleRegister(bytes[0], bytes[1]);
		registers[1] = new SimpleRegister(bytes[2], bytes[3]);
		return registers;
	}
	
	@Override
	public String getValue()
	{
		String value = null;
		if ((this.readResponse != null) && (this.readResponse instanceof ReadInputRegistersResponse))
		{
			// get the integer value contained in the received readResponse
			int valueAsInt = this.fromRegister(((ReadInputRegistersResponse) this.readResponse).getRegisters());
			
			// format and scale the value according to the inner scaling
			// parameter
			value = String.format(Locale.US, "%f", valueAsInt * this.scaleFactor);
			
			// add the unit of measure if needed
			if ((this.unitOfMeasure != null) && (!this.unitOfMeasure.isEmpty()))
				value += " " + this.unitOfMeasure;
		}
		return value;
	}
	
	@Override
	public ModbusRequest getWriteRequest(int address, String value)
	{
		// not supported for input registers
		return null;
	}
	
	@Override
	public ModbusRequest getReadRequest(int address)
	{
		this.readRequest = new ReadInputRegistersRequest(address, this.typeSize / 2);
		return this.readRequest;
	}
	
}
