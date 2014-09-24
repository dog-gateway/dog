/*
 * Dog - Network Driver
 * 
 * Copyright (c) 2012-2014 Dario Bonino
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
package it.polito.elite.dog.drivers.echelon.ilon100.network.info;

/**
 * A final, empty class storing infos about Echelon configuration data, in
 * particular identifiers to extract such data from a given device/gateway
 * specification
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 *         TODO: Possibly remove this class and find a better solution to handle
 *         configuration data.
 * 
 */
public final class EchelonIlonInfo
{
	// the manufacturer identifier (EchelonIlon100)
	public static String MANUFACTURER = "EchelonIlon100";	
	
	// the command name to which a specific configuration refers
	public static String COMMAND_NAME = "realCommandName";
	
	// the notification name to which a specific configuration refers
	public static String NOTIFICATION_NAME = "notificationName";
	
	//the datapoint id
	public static String DATAPOINT_ID = "datapointId";
	
	//the datapoint unit of measure
	public static String DATAPOINT_UOM = "unitOfMeasure";
	
	//the datapoint alias
	public static String DATAPOINT_ALIAS = "datapointAlias";
	
	//the endpoint address
	public static String ENDPOINT_ADDRESS = "endpointAddress";
}
