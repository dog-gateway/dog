/*
 * Dog - Network Driver
 * 
 * Copyright (c) 2011-2013 Dario Bonino
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
package it.polito.elite.dog.drivers.knx.network.info;

/**
 * A final, empty class storing infos about KNX configuration data, in
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
public final class KnxIPInfo
{
	// static variables for "identifying" configuration parameters in the
	// ontology
	
	// TODO: possibly move these "magic" strings to an easy to update structure
	
	// the gateway address
	public static String GATEWAY_ADDRESS = "IPAddress";

	// the manufacturer identifier (KNXNetIP), implementing drivers should also
	// match the old KONNEX identifier, but with a lower match value
	public static String MANUFACTURER = "KNXNETIP";
	// TODO: since the manufacturer does not change, add (here, in the ontology,
	// in the XSD and in the old KNX driver) a parameter like "version" to
	// identify the KNX protocol versions.
	
	// the group address associated to any device
	public static String GROUP_ADDRESS = "groupAddress";
	
	// the notification address to any device
	public static String NOTIFICATION_ADDRESS = "notificationAddress";
	
	// the command name to which a specific configuration refers
	public static String COMMAND_NAME = "realCommandName";
	
	// the hexValue associated to a command (if any, this stems from the old KNX
	// driver and is probably not needed)
	public static String COMMAND_VALUE = "hexValue";
	
	// the notification name to which a specific configuration refers
	public static String NOTIFICATION_NAME = "notificationName";
	
	//the default port
	public static int DEFAULT_PORT = 3671;
 
}
