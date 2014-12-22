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

import it.polito.elite.dog.addons.mqtt.library.transport.tasks.ReconnectionTimerTask;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.util.Arrays;
import java.util.Timer;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.osgi.service.log.LogService;


/**
 * A wrapper class aimed at offering an easy interface to asynchronously
 * publishing data towards a given MQTT broker, with the desired QoS level. It
 * exploits a simple persistence layer based on temporary files.
 * 
 * @author <a href="dario.bonino@gmail.com">Dario Bonino</a>
 *
 */
public class MqttAsyncDispatcher implements MqttCallback
{	
	// the default MQTT QoS (deliver and forget)
	private static final MqttQos DEFAULT_QOS = MqttQos.AT_MOST_ONCE;
	
	// the default reconnection timeout
	private static final int RECONNECTION_TIMEOUT = 30000;
	
	// the class-level logger
	private LogHelper logger;
	
	// the MQTT asynchronous client
	private MqttAsyncClient asyncClient;
	
	// the MQTT connection options
	private MqttConnectOptions connectionOptions;
	
	// the MQTT broker endpoint as full url
	private String brokerUrl;
	
	// this client id
	private String clientId;
	
	// the username to use when setting up the connection
	private String username;
	
	// the password to use when setting up the connection
	private String password;
	
	// the connection flag
	private boolean connected;
	
	// the timer for reconnection
	private Timer reconnectionTimer;
	
	// the reconnection flag
	private boolean autoReconnect;
	
	/**
	 * The class constructor. Builds a new instance of
	 * {@link MqttAsyncDispatcher} pointing to the given brokerUrl and adopting
	 * the given username and password for connecting to the broker. It uses the
	 * default Quality of Service (QoS) defined in {@link MqttAsyncDispatcher}
	 * .DEFAULT_QOS or the QoS level defined through the instance setQoS method.
	 * Moreover it does not persist sessions across restarts, i.e., it does not
	 * provide durable subscriptions
	 * 
	 * Currently only "plain" connection is supported although future release
	 * may support ssl-encrypted connections.
	 * 
	 * @param brokerUrl
	 *            The full url of the broker, as a {String}.
	 * @param clientId
	 *            The client id to connect with.
	 * @param username
	 *            The username to adopt for connecting to the broker.
	 * @param password
	 *            The password to adopt for connecting to the broker.
	 */
	public MqttAsyncDispatcher(String brokerUrl, String clientId, String username, String password, LogHelper logger)
	{
		this.initCommon(brokerUrl, clientId, username, password, true, logger);
	}
	
	/**
	 * The class constructor. Builds a new instance of
	 * {@link MqttAsyncDispatcher} pointing to the given brokerUrl and adopting
	 * the given username and password for connecting to the broker. It uses the
	 * default Quality of Service (QoS) defined in {@link MqttAsyncDispatcher}
	 * .DEFAULT_QOS or the QoS level defined through the instance setQoS method.
	 * Moreover it does not persist sessions across restarts, i.e., it does not
	 * provide durable subscriptions
	 * 
	 * Currently only "plain" connection is supported although future release
	 * may support ssl-encrypted connections.
	 * 
	 * @param brokerUrl
	 *            The full url of the broker, as a {String}.
	 * @param clientId
	 *            The client id to connect with.
	 * @param username
	 *            The username to adopt for connecting to the broker.
	 * @param password
	 *            The password to adopt for connecting to the broker.
	 * @param autoReconnect
	 *            The auto-reconnection mode, is true the client automatically
	 *            attempts re-connection.
	 */
	public MqttAsyncDispatcher(String brokerUrl, String clientId, String username, String password,
			boolean autoReconnect, LogHelper logger)
	{
		this.initCommon(brokerUrl, clientId, username, password, autoReconnect, logger);
	}
	
	private void initCommon(String brokerUrl, String clientId, String username, String password, boolean autoReconnect, LogHelper logger)
	{
		this.brokerUrl = brokerUrl;
		this.clientId = clientId;
		this.username = username;
		this.password = password;
		this.logger = logger; //TODO handle context here
		
		// initialize the reconnection timer
		this.reconnectionTimer = new Timer();
		
		// store the auto-reconnection flag
		this.autoReconnect = autoReconnect;
		
		// initially disconnected
		this.connected = false;
		
		// get the default Java temporary files directory
		String tmpDir = System.getProperty("java.io.tmpdir");
		
		// prepare a simple persistence layer that stores data as files in the
		// temporary directory
		MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);
		
		// build the connection options
		this.connectionOptions = new MqttConnectOptions();
		
		// set the clean session parameters
		this.connectionOptions.setCleanSession(true);
		
		// set the password, if provided
		if ((this.password != null) && (!this.password.isEmpty()))
		{
			this.connectionOptions.setPassword(this.password.toCharArray());
		}
		
		// set the username, if provided
		if ((this.username != null) && (!this.username.isEmpty()))
		{
			this.connectionOptions.setUserName(username);
		}
		
		// build the MqttClient instance
		try
		{
			// build the client instance
			this.asyncClient = new MqttAsyncClient(this.brokerUrl, this.clientId, dataStore);
			
			// set this call instance as callback for the asynchronous delivery
			// task
			this.asyncClient.setCallback(this);
		}
		catch (MqttException e)
		{
			// log the error
			this.logger.log(LogService.LOG_ERROR,"Error while creating the MQTT asynchronous client", e);
		}
	}
	
	public void connectionLost(Throwable t)
	{
		// Called when the connection to the server has been lost.
		// An application may choose to implement reconnection
		// logic at this point. This preliminary implementation simply logs the
		// error.
		this.logger.log(LogService.LOG_WARNING,"Lost connection with the given MQTT broker...Reconnecting in 30s", t);
		
		// set the connection status
		this.setConnected(false);
		
	}
	
	public void deliveryComplete(IMqttDeliveryToken token)
	{
		// Called when a message has been delivered to the
		// server. The token passed in here is the same one
		// that was returned from the original call to publish.
		
		this.logger.log(LogService.LOG_INFO,"Delivery complete callback: Publish Completed " + Arrays.toString(token.getTopics()));
		
	}
	
	public void messageArrived(String arg0, MqttMessage mqttMessage) throws Exception
	{
		// // Called when a message arrives from the server that matches any
		// subscription made by the client
		
		// empty in this case as this dispatcher only publishes data...
		this.logger.log(LogService.LOG_INFO,"Unknow arg0:"+arg0);
		this.logger.log(LogService.LOG_INFO,"MqttMessage: "+new String(mqttMessage.getPayload()));
	}
	
	/**
	 * Checks if the dispatcher is currently connected or not
	 * 
	 * @return the current connection status
	 */
	public boolean isConnected()
	{
		return connected;
	}
	
	/**
	 * Sets the dispatcher status to either connected or not.
	 * 
	 * @param connected
	 *            the connection status to be set
	 */
	public void setConnected(boolean connected)
	{
		this.connected = connected;
		
		// if disconnected, and auto-reconnection is enabled, try to reconnect
		if ((!this.connected) && (this.autoReconnect))
		{
			// start the timer
			this.reconnectionTimer.schedule(new ReconnectionTimerTask(this, true),
					MqttAsyncDispatcher.RECONNECTION_TIMEOUT);
		}
	}
	
	/**
	 * Connect asynchronously to the given MQTT broker, when connected sets the
	 * connection flag at true;
	 * 
	 * The returned IMqttToken might be used to wrap the asynchronous call to a
	 * synchronous invocation.
	 * 
	 * @return The {@link IMqttToken} related to the connection request
	 */
	public IMqttToken connect()
	{
		IMqttToken token = null;
		try
		{
			token = this.asyncClient.connect(this.connectionOptions, "", new MqttConnectionListener(this));
		}
		catch (MqttException e)
		{
			this.logger.log(LogService.LOG_WARNING,"Error while performing connection to the given MQTT broker", e);
		}
		
		return token;
	}
	
	/**
	 * Connect synchronously with the given MQTT broker
	 */
	public void syncConnect()
	{
		try
		{
			this.connect().waitForCompletion();
		}
		catch (MqttException e)
		{
			this.logger.log(LogService.LOG_WARNING,"Error while performing synchronous connection to the given MQTT broker", e);
		}
	}
	
	/**
	 * Publishes the given message payload to the given topic using the
	 * class-level default QoS
	 * 
	 * @param topic
	 *            The topic to which the event should be published
	 * @param payload
	 *            The payload to publish
	 */
	public void publish(String topic, byte[] payload)
	{
		
		// asynchronously publishes the given payload to the given topic
		this.publish(topic, payload, MqttAsyncDispatcher.DEFAULT_QOS);
		
	}
	
	/**
	 * Publishes the given message payload to the given topic using the given
	 * QoS level
	 * 
	 * @param topic
	 *            The topic to which the event should be published
	 * @param payload
	 *            The payload to publish
	 * @param qos
	 *            The QoS to use
	 */
	public void publish(String topic, byte[] payload, MqttQos qos)
	{
		if (this.connected)
		{
			try
			{
				// asynchronously publishes the given payload to the given topic
				this.asyncClient.publish(topic, payload, qos.getQoS(), false);
			}
			catch (MqttException e)
			{
				// TODO can be checked in a more refined manner
				this.logger.log(LogService.LOG_WARNING,"Error while delivering message", e);
			}
		}
	}
	
	/**
	 * Asynchronously disconnects from the given message broker when
	 * disconnected sets the connection flag at false;
	 * 
	 * The returned IMqttToken might be used to wrap the asynchronous call to a
	 * synchronous invocation.
	 * 
	 * @return The {@link IMqttToken} related to the connection request
	 */
	public IMqttToken disconnect()
	{
		IMqttToken token = null;
		
		try
		{
			token = this.asyncClient.disconnect("", new MqttDisconnectionListener(this));
		}
		catch (MqttException e)
		{
			this.logger.log(LogService.LOG_WARNING,"Error while performing connection to the given MQTT broker", e);
		}
		
		return token;
	}
	
	/**
	 * Disconnects synchronously from the given MQTT broker
	 */
	public void syncDisconnect()
	{
		try
		{
			this.disconnect().waitForCompletion();
		}
		catch (MqttException e)
		{
			this.logger.log(LogService.LOG_ERROR,"Error while disconnecting");
		}
	}
	
	public void subscribe(String topicFilter, int qos) // , IMqttActionListener
														// listener)
	{
		try
		{
			this.asyncClient.subscribe(topicFilter, qos, null, (IMqttActionListener) this);
		}
		catch (MqttException e)
		{
			this.logger.log(LogService.LOG_ERROR,"Mqtt subscribe exception: ", e);
		}
	}
	
}
