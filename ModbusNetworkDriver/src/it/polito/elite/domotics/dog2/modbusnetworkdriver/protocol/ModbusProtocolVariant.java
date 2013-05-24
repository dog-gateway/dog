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
package it.polito.elite.domotics.dog2.modbusnetworkdriver.protocol;

/**
 * An enumeration representing possible Modbus protocol variants
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>, Politecnico di Torino 
 *
 * @since May 24, 2013
 */
public enum ModbusProtocolVariant
{
	TCP,
	RTU,
	RTU_TCP,
	RTU_UDP
}
