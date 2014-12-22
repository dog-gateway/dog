/**
 * 
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
 * @author bonino
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
