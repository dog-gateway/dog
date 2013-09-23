/*
 * Dog - Core
 * 
 * Copyright (c) 2009-2013 Emiliano Castellina, Dario Bonino and Luigi De Russis
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
package it.polito.elite.dog.core.library.model;

import org.osgi.service.device.Constants;

/**
 * A utility class that stores Constants used by most of the Dog bundles.
 * 
 * @author <a href="mailto:emiliano.castellina@polito.it">Emiliano
 *         Castellina</a> (original implementation)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public interface DeviceCostants extends Constants
{
	final static public String MANUFACTURER = "MANUFACTURER";
	final static public String DEVICEURI = "DEVICEURI";
	final public static String ACTIVE = "ACTIVE";
	final public static String ROOM = "ROOM";
	final public static String CONFIGPARAM = "CONFIG_PARAM";
	final public static String DEVICEFUNCTIONALITY = "DEVICEFUNCTIONALITY";
	final public static String DEVICESTATE = "DEVICESTATE";
	final public static String DEVICENOTIFICATION = "DEVICENOTIFICATION";
	public static final String TYPE = "TYPE";
	public static final String DEVICESTATEVALUE = "DEVICESTATEVALUE";
	// gateway-specific property used to trigger modified services notifications
	// to re-start attachment of idle devices every time a new gateway joins its
	// driver.
	public static final String GATEWAY_COUNT = "GATEWAY_COUNT";
	/** Xml data that stores informations about the devices configuration */
	public static final String XMLCONFIGURATION = "XMLCONFIGURATION";
	public static final String DEVICELOCATION = "DEVICELOCATION";
	/**
	 * Constant String that rapresent the property name of the INTELLIGENCE
	 * required for the HouseModel device.
	 */
	public static final String INTELLIGENCE = "intelligence";
	public static final String ONTOLOGY = "ontology";
	public static final String SVGPLAN = "SVGPLAN";
	public static final String RULES = "RULES";
	public static final String DEVICES = "DEVICES";
	public static final String DEVICESCONSUMPTION = "DEVICESCONSUMPTION";
	/**
	 * It is uses as property key inside a notification fired directly by a
	 * device
	 */
	public static final String INNER_STATE_NOTIFICATION = "INNERSTATENOTIFICATION";
	public static final String RID = "rid";
	public static final String DEVICEGATEWAY = "gateway";
	public static final String NOTIFICATIONS_PREFIX = "it/polito/elite/domotics/model/notification/";
	
	/** Constants used for TI USB key */
	public static final String TIPORT = "SERIALPORT";
	public static final String BAUD = "BAUDRATE";
	public static final String DATABITS = "DATABITS";
	public static final String STOPBITS = "STOPBITS";
	public static final String PARITYBITS = "PARITY";
	public static final String TIPORT_TIMEOUT = "TIMEOUT";
	
	/** Constants used for TI watch */
	public static final String BETWEENPRESSING = "BETWEENBUTTONPRESSING";
	public static final String ACCTIMESTAMP = "ACCTIMESTAMP";
	
	/** Constants for ZWaveDevice Polling */
	public static final String ENABLE_POLLING = "EnablePolling";
	public static final String POLLING_TIME = "PollingTime";
	
}
