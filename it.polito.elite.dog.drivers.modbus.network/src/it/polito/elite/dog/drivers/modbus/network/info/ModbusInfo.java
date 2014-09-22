/*
 * Dog - Network Driver
 * 
 * Copyright (c) 2012-2013 Dario Bonino
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
package it.polito.elite.dog.drivers.modbus.network.info;

import it.polito.elite.dog.core.library.model.ConfigurationConstants;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a> 
 *
 * @since Mar 1, 2012
 */
public class ModbusInfo extends ConfigurationConstants
{
	// the manufacturer identifier (Modbus)
	public static String MANUFACTURER = "Modbus";	
	
	// the gateway address
	public static String GATEWAY_ADDRESS = "IPAddress";
	
	// the gateway port
	public static String GATEWAY_PORT = "port";
	
	// the gateway variant
	public static String PROTO_ID = "protocolVariant";
	
	// the register address
	public static String REGISTER_ADDRESS = "registerAddress";
	
	// the register type
	public static String REGISTER_TYPE = "registerType";
	
	// the  slave identifier for the register
	public static String SLAVE_ID = "slaveId";
	
	//the unit of measure associated to the register value
	public static String REGISTER_UOM = "measuredIn";

	//the scale factor
	public static String SCALE_FACTOR = "scaleFactor";
	
}
