/*
 * Dog  - Z-Wave
 * 
 * Copyright 2013 Davide Aimone  and Dario Bonino 
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
package it.polito.elite.dog.drivers.zwave.model;

public class DataConst
{
	//Device
	public static final String BASIC_TYPE = "basicType";
	public static final String GENERIC_TYPE = "genericType";
	public static final String SPECIFIC_TYPE = "specificType";
	public static final String IS_LISTENING = "isListening";
	public static final String IS_FAILED = "isFailed";
	public static final String IS_AWAKE = "isAwake";
	public static final String HAS_WAKEUP = "hasWakeup";
	public static final String HAS_BATTERY = "hasBattery";
	public static final String SENSOR250 = "sensor250";
	public static final String SENSOR1000 = "sensor1000";
	public static final String LAST_RECEIVED = "lastReceived";
	public static final String LAST_SEND = "lastSend";
	public static final String NODE_INFO_FRAME = "nodeInfoFrame";
	public static final String LAST_INCLUDED_DEVICE = "lastIncludedDevice";
	public static final String LAST_EXCLUDED_DEVICE = "lastExcludedDevice";
	
	//utils
	public static final int INVALID = -1;
}
