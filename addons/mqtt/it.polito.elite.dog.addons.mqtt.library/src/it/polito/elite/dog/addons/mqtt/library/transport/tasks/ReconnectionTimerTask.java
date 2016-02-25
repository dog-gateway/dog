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
package it.polito.elite.dog.addons.mqtt.library.transport.tasks;

import it.polito.elite.dog.addons.mqtt.library.transport.MqttAsyncDispatcher;

import java.util.TimerTask;

/**
 * @author bonino
 *
 */
public class ReconnectionTimerTask extends TimerTask
{
	
	//the dispatcher instance
	private MqttAsyncDispatcher dispatcher;
	
	//the sync connection flag
	private boolean sync = false;
	
	/**
	 * 
	 */
	public ReconnectionTimerTask(MqttAsyncDispatcher dispatcher, boolean sync)
	{
		// store a reference to the dispatcher
		this.dispatcher = dispatcher;
		
		//store the synchronous connection flag
		this.sync = sync;
	}
	
	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run()
	{
		if(this.sync)
		{
			//connect
			this.dispatcher.syncConnect();
		}
		else
		{
			this.dispatcher.connect();
		}
		
		
	}
	
}
