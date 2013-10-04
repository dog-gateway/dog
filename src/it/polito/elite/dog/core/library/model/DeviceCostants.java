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
 * A utility class that stores Constants used by most of the Dog bundles. It
 * extends the OSGi constants class.
 * 
 * @author <a href="mailto:emiliano.castellina@polito.it">Emiliano
 *         Castellina</a> (original implementation)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a> (successive
 *         modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public interface DeviceCostants extends Constants
{
	/* Constants that represent a device */
	public static final String MANUFACTURER = "MANUFACTURER";
	public static final String DEVICEURI = "DEVICEURI";
	public static final String ACTIVE = "ACTIVE";
	public static final String DEVICESTATE = "DEVICESTATE";
	public static final String TYPE = "TYPE";
	public static final String DEVICELOCATION = "DEVICELOCATION";
	// gateway-specific property used to trigger modified services
	// to re-start attachment of idle devices every time a new gateway joins its
	// driver.
	public static final String GATEWAY_COUNT = "GATEWAY_COUNT";
	
	/* Constants that represent properties needed by the HouseModel */
	// Simple House Model
	public static final String DEVICES = "DEVICES";
	public static final String SVGPLAN = "SVGPLAN";
	// Semantic House Model
	public static final String INTELLIGENCE = "intelligence";
	public static final String ONTOLOGY = "ontology";
	
	/* Constant used by the Rule Engine */
	public static final String RULES = "RULES";
	
	/* Constants used for TI USB key */
	public static final String TIPORT = "SERIALPORT";
	public static final String BAUD = "BAUDRATE";
	public static final String DATABITS = "DATABITS";
	public static final String STOPBITS = "STOPBITS";
	public static final String PARITYBITS = "PARITY";
	public static final String TIPORT_TIMEOUT = "TIMEOUT";
	
	/* Constants used for TI watch */
	public static final String BETWEENPRESSING = "BETWEENBUTTONPRESSING";
	public static final String ACCTIMESTAMP = "ACCTIMESTAMP";
	
	/* Constants for ZWaveDevice Polling */
	public static final String ENABLE_POLLING = "EnablePolling";
	public static final String POLLING_TIME = "PollingTime";
	
}
