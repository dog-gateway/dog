/*
 * Dog - Addons - Mqtt
 * 
 * Copyright (c) 2013-2014 Dario Bonino
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
package it.polito.elite.dog.addons.mqtt.bridge.tasks;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import it.polito.elite.dog.addons.mqtt.bridge.translators.StateTranslator;
import it.polito.elite.dog.addons.mqtt.library.transport.MqttAsyncDispatcher;
import it.polito.elite.dog.addons.mqtt.library.transport.MqttQos;
import it.polito.elite.dog.core.library.model.DeviceStatus;

/**
 * An Mqtt notification delivery task. Implements the logic needed to deliver a
 * given {@link DeviceStatus} instance to a given Mqtt broker with the given QOS, by using the
 * provided translator to convert the monitor event into an Mqtt payload.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 *
 */
public class MqttStateDeliveryTask implements Runnable
{
	private MqttAsyncDispatcher dispatcher;
	private MqttQos qos;
	private String topic;
	private String translatorClass;
	private DeviceStatus status;
	private BundleContext ctx;
	private StateTranslator defaultTranslator;

	/**
	 * 
	 */
	public MqttStateDeliveryTask(MqttAsyncDispatcher dispatcher, MqttQos qos,
			String topic, String translatorClass,
			StateTranslator defaultTranslator, DeviceStatus status,
			BundleContext ctx)
	{
		// store instance variables
		this.dispatcher = dispatcher;
		this.qos = qos;
		this.topic = topic;
		this.translatorClass = translatorClass;
		this.status = status;
		this.ctx = ctx;
		this.defaultTranslator = defaultTranslator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		// the raw mqtt message to send
		byte[] messageToSend = null;

		String filter = String.format("(%s=%s)", Constants.OBJECTCLASS,
				translatorClass);

		// get the translator class through the osgi framework
		try
		{
			@SuppressWarnings("unchecked")
			ServiceReference<StateTranslator> references[] = (ServiceReference<StateTranslator>[]) this.ctx
					.getServiceReferences(StateTranslator.class.getName(),
							filter);
			if ((references != null) && (references.length > 0))
			{
				// greedy approach, the first wins
				StateTranslator translator = (StateTranslator) this.ctx
						.getService(references[0]);

				// call the service
				messageToSend = translator.translateState(this.status);

				// unget the service
				this.ctx.ungetService(references[0]);
			}
		}
		catch (InvalidSyntaxException e)
		{
			// do nothing...
		}

		// check not null, otherwise use the default handler
		if ((messageToSend == null) || (messageToSend.length == 0))
			messageToSend = this.defaultTranslator.translateState(this.status);

		// send the message if not null (tin pants)
		if ((messageToSend != null) && (messageToSend.length > 0))
			synchronized (this.dispatcher)
			{
				this.dispatcher.publish(topic, messageToSend, qos);
			}
	}

}
