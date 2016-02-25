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

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

/**
 * @author bonino
 *
 */
public class MqttDisconnectionListener implements IMqttActionListener
{
	private MqttAsyncDispatcher theDispatcher;
	
	public MqttDisconnectionListener(MqttAsyncDispatcher dispatcher)
	{
		this.theDispatcher = dispatcher;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.paho.client.mqttv3.IMqttActionListener#onFailure(org.eclipse
	 * .paho.client.mqttv3.IMqttToken, java.lang.Throwable)
	 */
	@Override
	public void onFailure(IMqttToken arg0, Throwable arg1)
	{
		this.theDispatcher.setConnected(true);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.paho.client.mqttv3.IMqttActionListener#onSuccess(org.eclipse
	 * .paho.client.mqttv3.IMqttToken)
	 */
	@Override
	public void onSuccess(IMqttToken arg0)
	{
		this.theDispatcher.setConnected(false);
	}
	
}
