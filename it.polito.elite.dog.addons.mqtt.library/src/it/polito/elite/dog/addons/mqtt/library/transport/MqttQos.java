/*
 * Dog - MQTT Asynchronous Dispatcher Transport
 * 
 * Copyright (c) 2014 Dario Bonino
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
package it.polito.elite.dog.addons.mqtt.library.transport;

/**
 * An enumeration representing valid QoS values as defined by the MQTT standard.
 * 
 * @author <a href="mailto:dario.bonino@gmail.com">Dario Bonino</a>
 *
 */
public enum MqttQos
{
	AT_MOST_ONCE ((int)0),
	AT_LEAST_ONCE ((int)1),
	EXACTLY_ONCE ((int)2);
	
	
	private int qos;
	
	private MqttQos(int qos)
	{
		this.qos = qos;
	}
	
	public int getQoS()
	{
		return this.qos;
	}
	
}
