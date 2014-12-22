/**
 * 
 */
package it.polito.elite.dog.addons.mqtt.bridge.tasks;

import it.polito.elite.dog.addons.mqtt.bridge.translators.NotificationTranslator;
import it.polito.elite.dog.addons.mqtt.library.transport.MqttAsyncDispatcher;
import it.polito.elite.dog.addons.mqtt.library.transport.MqttQos;
import it.polito.elite.dog.core.library.model.notification.Notification;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * @author bonino
 *
 */
public class MqttNotificationDeliveryTask implements Runnable
{
	private MqttAsyncDispatcher dispatcher;
	private MqttQos qos;
	private String topic;
	private String translatorClass;
	private Notification notification;
	private BundleContext ctx;
	private NotificationTranslator defaultTranslator;

	/**
	 * 
	 */
	public MqttNotificationDeliveryTask(MqttAsyncDispatcher dispatcher,
			MqttQos qos, String topic, String translatorClass,
			NotificationTranslator defaultTranslator,
			Notification notification, BundleContext ctx)
	{
		// store instance variables
		this.dispatcher = dispatcher;
		this.qos = qos;
		this.topic = topic;
		this.translatorClass = translatorClass;
		this.notification = notification;
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
			ServiceReference<NotificationTranslator> references[] = (ServiceReference<NotificationTranslator>[]) this.ctx
					.getServiceReferences(
							NotificationTranslator.class.getName(), filter);
			if ((references != null) && (references.length > 0))
			{
				// greedy approach, the first wins
				NotificationTranslator translator = (NotificationTranslator) this.ctx
						.getService(references[0]);

				// call the service
				messageToSend = translator
						.translateNotification(this.notification);

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
			messageToSend = this.defaultTranslator
					.translateNotification(this.notification);

		// send the message if not null (tin pants)
		if ((messageToSend != null) && (messageToSend.length > 0))
			synchronized (this.dispatcher)
			{
				this.dispatcher.publish(topic, messageToSend, qos);
			}

	}

}
