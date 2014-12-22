/**
 * 
 */
package it.polito.elite.dog.addons.mqtt.bridge;

import it.polito.elite.dog.addons.mqtt.bridge.tasks.MqttNotificationDeliveryTask;
import it.polito.elite.dog.addons.mqtt.bridge.tasks.MqttStateDeliveryTask;
import it.polito.elite.dog.addons.mqtt.bridge.translators.NotificationTranslator;
import it.polito.elite.dog.addons.mqtt.bridge.translators.SimpleNotificationTranslator;
import it.polito.elite.dog.addons.mqtt.bridge.translators.SimpleStateTranslator;
import it.polito.elite.dog.addons.mqtt.bridge.translators.StateTranslator;
import it.polito.elite.dog.addons.mqtt.library.transport.MqttAsyncDispatcher;
import it.polito.elite.dog.addons.mqtt.library.transport.MqttQos;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.notification.Notification;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.osgi.service.log.LogService;

/**
 * @author bonino
 *
 */
public class MqttBridge implements EventHandler, ManagedService
{
	// ------- static key definitions for configuration properties --------
	private static final String MQTT_BROKER = "mqtt_broker";
	private static final String MQTT_ROOT_TOPIC = "mqtt_root_topic";
	private static final String MQTT_NOTIFICATION_ROOT_TOPIC = "mqtt_notification_root_topic";
	private static final String MQTT_STATE_ROOT_TOPIC = "mqtt_state_root_topic";
	private static final String MQTT_BRIDGE_NOTIFICATIONS_FLAG = "bridge_notifications";
	private static final String MQTT_BRIDGE_STATES_FLAG = "bridge_states";
	private static final String MQTT_NOTIFICATION_TRANSLATOR = "notification2mqtt";
	private static final String MQTT_STATE_TRANSLATOR = "state2mqtt";
	private static final String MQTT_NOTIFICATION_TRANSLATOR_SUFFIX = "-notification2mqtt";
	private static final String MQTT_STATE_TRANSLATOR_SUFFIX = "-state2mqtt";
	private static final String MQTT_QOS = "mqtt_qos";

	// the class logger
	private LogHelper logger;

	// the context associated to this bundle
	private BundleContext context;

	// the MQTT broker addresses, might be more than one...
	// each single broker address is in the form **host:port**
	private Set<String> brokerAddresses;

	// the MQTT qos to use
	private MqttQos mqttQos;

	// the MQTT root topic
	private String mqttRootTopic;

	// the MQTT notification topic
	private String mqttNotificationRootTopic;

	// the MQTT state topic
	private String mqttStateRootTopic;

	// the flags indicating what to bridge
	private boolean bridgeNotifications = false;
	private boolean bridgeStates = false;

	// the base event translator classes, typically external services
	private String baseNotificationTranslatorClass;
	private String baseStateTranslatorClass;

	// the default translators to use before failing
	private NotificationTranslator defaultNotificationTranslator;
	private StateTranslator defaultStateTranslator;

	// the map of broker-specific translators
	private Map<String, String> brokerSpecificNotificationTranslatorClasses;
	private Map<String, String> brokerSpecificStateTranslatorClasses;

	// maps a broker-address to the corresponding MQTT client
	private Map<String, MqttAsyncDispatcher> broker2Client;

	// the service registration handler
	private ServiceRegistration<EventHandler> eventHandler;
	
	// the message delivery service
	private ExecutorService messageDeliveryService;

	/**
	 * The class constructor, called before activation, initializes common
	 * datastructure
	 * 
	 * This implementation keeps on translator instance per broker, per type of
	 * event and tries to parallelize as much as possible the event delivery
	 * over MQTT process. This is clearly not efficient in terms of memeory
	 * occupation, in case problems arise, a shared translator approach can be
	 * exploited.
	 */
	public MqttBridge()
	{
		// build the needed data structures
		this.brokerAddresses = new HashSet<String>();
		this.brokerSpecificNotificationTranslatorClasses = new HashMap<String, String>();
		this.brokerSpecificStateTranslatorClasses = new HashMap<String, String>();
		this.broker2Client = new HashMap<String, MqttAsyncDispatcher>();

		// initialize the default translators
		this.defaultNotificationTranslator = new SimpleNotificationTranslator();
		this.defaultStateTranslator = new SimpleStateTranslator();
		
		// initialize the message dispatching thread pool
		this.messageDeliveryService = Executors.newFixedThreadPool(4);
	}

	/**
	 * Handle the bundle activation
	 */
	protected void activate(BundleContext ctx)
	{
		// init the logger with a null logger
		this.logger = new LogHelper(ctx);

		// log the activation
		this.logger.log(LogService.LOG_INFO,
				"Activated Notification to MQTT bridge");

		// store the bundle context
		this.context = ctx;
	}

	/**
	 * Handle the bundle de-activation
	 */
	protected void deactivate()
	{
		// log the de-activation
		if (this.logger != null)
			this.logger.log(LogService.LOG_INFO,
					"Deactivated Notification to MQTT bridge");

		// de-register the event handler
		this.unRegisterService();
	}

	/**
	 * register this service as an EventHandler
	 */
	@SuppressWarnings("unchecked")
	private void registerService()
	{
		// register the EventHandler service
		Hashtable<String, Object> p = new Hashtable<String, Object>();

		// Add this bundle as a listener of the MonitorAdmin events and of all
		// notifications
		p.put(EventConstants.EVENT_TOPIC, new String[] {
				"org/osgi/service/monitor/MonitorEvent",
				"it/polito/elite/dog/core/library/model/notification/*" });

		// TODO filter upon ClockNotification, EventNotification, etc. with the
		// event_filter constant
		this.eventHandler = (ServiceRegistration<EventHandler>) this.context
				.registerService(EventHandler.class.getName(), this, p);
	}

	/**
	 * remove this service from the framework
	 */
	private void unRegisterService()
	{
		if (this.eventHandler != null)
			this.eventHandler.unregister();
	}

	@Override
	public void handleEvent(Event event)
	{
		// handle states only if enabled
		if ((this.bridgeStates)
				&& (event.getTopic()
						.equals("org/osgi/service/monitor/MonitorEvent")))
		{
			// handle states only
			if (event.getProperty("mon.listener.id") == null)
			{
				// handle states

				DeviceStatus currentDeviceState = null;
				try
				{
					// Try the deserialization of the DeviceStatus
					// (property mon.statusvariable.value)
					currentDeviceState = DeviceStatus
							.deserializeFromString((String) event
									.getProperty("mon.statusvariable.value"));
				}
				catch (Exception e)
				{
					this.logger.log(LogService.LOG_ERROR,
							"Device status deserialization error "
									+ e.getClass().getSimpleName());
				}

				// handle
				this.handleStates(currentDeviceState);

			}
		}

		// handle notifications
		if ((this.bridgeNotifications)
				&& (event.getTopic()
						.startsWith("it/polito/elite/dog/core/library/model/notification/")))
		{
			// handle notifications
			Object eventContent = event.getProperty(EventConstants.EVENT);

			if (eventContent instanceof Notification)
				this.handleNotifications((Notification) eventContent);
		}
	}

	private void handleNotifications(Notification eventContent)
	{
		// iterate over brokers
		for (String brokerAddress : this.brokerAddresses)
		{
			// ------- prepare dispatching to the given broker

			// notification name (type)
			String notificationType = eventContent.getClass().getSimpleName()
					.toLowerCase();

			// device name
			String deviceUri = eventContent.getDeviceUri();

			// topic
			String topic = this.mqttRootTopic + "/"
					+ this.mqttNotificationRootTopic + "/" + notificationType
					+ "/" + deviceUri;

			// dispatcher
			MqttAsyncDispatcher dispatcher = this.broker2Client
					.get(brokerAddress);
			
			// get the translator class
			String translatorClass = this.brokerSpecificNotificationTranslatorClasses.get(brokerAddress);
			
			//use default if null
			if(translatorClass == null)
				translatorClass = this.baseNotificationTranslatorClass;

			// check that all needed information is available
			if ((dispatcher != null) && (notificationType != null)
					&& (deviceUri != null) && (topic != null) && (translatorClass!=null))
			{
				//create the delivery task
				MqttNotificationDeliveryTask task = new MqttNotificationDeliveryTask(
						dispatcher, this.mqttQos, topic, translatorClass,
						this.defaultNotificationTranslator, eventContent,
						this.context);
				
				//submit for delivery
				this.messageDeliveryService.execute(task);
			}

		}

	}

	private void handleStates(DeviceStatus currentDeviceState)
	{
		// iterate over brokers
		for (String brokerAddress : this.brokerAddresses)
		{
			// ------- prepare dispatching to the given broker

			// device name
			String deviceUri = currentDeviceState.getDeviceURI();

			// topic
			String topic = this.mqttRootTopic + "/"
					+ this.mqttStateRootTopic + "/"+ deviceUri;

			// dispatcher
			MqttAsyncDispatcher dispatcher = this.broker2Client
					.get(brokerAddress);
			
			// get the translator class
			String translatorClass = this.brokerSpecificStateTranslatorClasses.get(brokerAddress);
			
			//use default if null
			if(translatorClass == null)
				translatorClass = this.baseStateTranslatorClass;

			// check that all needed information is available
			if ((dispatcher != null) && (deviceUri != null) && (topic != null) && (translatorClass!=null))
			{
				//create the delivery task
				MqttStateDeliveryTask task = new MqttStateDeliveryTask(
						dispatcher, this.mqttQos, topic, translatorClass,
						this.defaultStateTranslator, currentDeviceState,
						this.context);
				
				//submit for delivery
				this.messageDeliveryService.execute(task);
			}

		}
	}

	/**
	 * Handles the bundle configuration updates, used to get the MQTT broker
	 * data
	 */
	@Override
	public void updated(Dictionary<String, ?> properties)
			throws ConfigurationException
	{
		// check not null
		if ((properties != null) && (!properties.isEmpty()))
		{
			// configuration data is supposed to be present...

			// ----- HANDLE broker addresses -------------
			String brokerAddressConfig = (String) properties
					.get(MqttBridge.MQTT_BROKER);

			// check not null / empty
			if ((brokerAddressConfig != null)
					&& (!brokerAddressConfig.isEmpty()))
			{
				// prepare the set of addresses
				if (!this.brokerAddresses.isEmpty())
					this.brokerAddresses.clear();

				// check if more than one address has been specified
				if (brokerAddressConfig.contains(","))
				{
					// split over the comma
					String addresses[] = brokerAddressConfig.split(",");

					// check the broker address
					for (int i = 0; i < addresses.length; i++)
					{
						// clean the address from leading and trailing spaces
						String cleanAddress = addresses[i].trim();

						// add the address
						this.brokerAddresses.add(cleanAddress);
					}
				}
				else
				{
					// just add a single address
					this.brokerAddresses.add(brokerAddressConfig.trim());
				}
			}

			// ----- HANDLE MQTT qos --------------------
			String mqttQos = (String) properties.get(MqttBridge.MQTT_QOS);

			if ((mqttQos != null) && (!mqttQos.isEmpty()))
				this.mqttQos = MqttQos.valueOf(mqttQos);
			else
				this.mqttQos = MqttQos.AT_MOST_ONCE;

			// ----- HANDLE MQTT topics ------------------

			// root topic
			String mqttRootTopic = (String) properties
					.get(MqttBridge.MQTT_ROOT_TOPIC);
			if ((mqttRootTopic != null) && (!mqttRootTopic.isEmpty()))
				this.mqttRootTopic = mqttRootTopic;

			// notification root topic
			String mqttNotificationRootTopic = (String) properties
					.get(MqttBridge.MQTT_NOTIFICATION_ROOT_TOPIC);
			if ((mqttNotificationRootTopic != null)
					&& (!mqttNotificationRootTopic.isEmpty()))
				this.mqttNotificationRootTopic = mqttNotificationRootTopic;

			// state root topic
			String mqttStateRootTopic = (String) properties
					.get(MqttBridge.MQTT_STATE_ROOT_TOPIC);
			if ((mqttStateRootTopic != null) && (!mqttStateRootTopic.isEmpty()))
				this.mqttStateRootTopic = mqttStateRootTopic;

			// ----- HANDLE publish flag

			// notifications
			Boolean bridgeNotifications = Boolean.valueOf((String) properties
					.get(MqttBridge.MQTT_BRIDGE_NOTIFICATIONS_FLAG));
			if (bridgeNotifications != null)
				this.bridgeNotifications = bridgeNotifications.booleanValue();

			// states
			Boolean bridgeStates = Boolean.valueOf((String) properties
					.get(MqttBridge.MQTT_BRIDGE_STATES_FLAG));
			if (bridgeStates != null)
				this.bridgeStates = bridgeStates.booleanValue();

			// ----- HANDLE default translators

			// default notification translator
			String defaultNotificationTranslatorClass = (String) properties
					.get(MqttBridge.MQTT_NOTIFICATION_TRANSLATOR);
			if ((defaultNotificationTranslatorClass != null)
					&& (!defaultNotificationTranslatorClass.isEmpty()))
				this.baseNotificationTranslatorClass = defaultNotificationTranslatorClass;

			// default state translator
			String defaultStateTranslatorClass = (String) properties
					.get(MqttBridge.MQTT_STATE_TRANSLATOR);
			if ((defaultStateTranslatorClass != null)
					&& (!defaultStateTranslatorClass.isEmpty()))
				this.baseStateTranslatorClass = defaultStateTranslatorClass;

			// ----- HANDLE broker-specific translators
			String notificationTranslator = null;
			String stateTranslator = null;
			for (String brokerAddress : this.brokerAddresses)
			{
				notificationTranslator = (String) properties.get(brokerAddress.replaceAll(":", "-")
						+ MqttBridge.MQTT_NOTIFICATION_TRANSLATOR_SUFFIX);
				stateTranslator = (String) properties.get(brokerAddress.replaceAll(":", "-")
						+ MqttBridge.MQTT_STATE_TRANSLATOR_SUFFIX);

				// check not null nor empty
				if ((notificationTranslator != null)
						&& (!notificationTranslator.isEmpty()))
					// store the broker-specifc notification translator
					this.brokerSpecificNotificationTranslatorClasses.put(
							brokerAddress, notificationTranslator);

				// check not null or empty
				if ((stateTranslator != null) && (!stateTranslator.isEmpty()))
					// store the broker-specific state translator
					this.brokerSpecificStateTranslatorClasses.put(
							brokerAddress, stateTranslator);
			}

			// once all configurations have been successfully handled, and
			// mandatory parameters filled, the service can register as an
			// EventHandler.
			if ((this.mqttRootTopic != null)
					&& (this.mqttNotificationRootTopic != null)
					&& (this.mqttStateRootTopic != null)
					&& (!this.brokerAddresses.isEmpty()))
			{
				// propagate / handle configuration changes
				this.initClients();

				// register the service if not already registered
				if (this.eventHandler == null)
					this.registerService();
			}

		}
	}

	/**
	 * Initialize and keep up-to-date the set of MQTT clients used by the bridge
	 */
	private void initClients()
	{
		// differential approach

		// search for removed brokers
		List<String> toRemove = new Vector<String>();
		for (String brokerAddress : this.broker2Client.keySet())
		{
			if (!this.brokerAddresses.contains(brokerAddress))
				toRemove.add(brokerAddress);
		}

		// remove brokers
		for (String brokerAddress : toRemove)
		{
			// get the dispatcher associated to the missing broker
			MqttAsyncDispatcher dispatcher = this.broker2Client
					.get(brokerAddress);

			// if connected, disconnect it
			if (dispatcher.isConnected())
				dispatcher.disconnect();

			// remove the dispatcher
			this.broker2Client.remove(dispatcher);
		}

		// add new brokers
		for (String brokerAddress : this.brokerAddresses)
		{
			// check if not exists already
			if (this.broker2Client.get(brokerAddress) == null)
			{
				// create the auto-reconnecting client
				MqttAsyncDispatcher client = new MqttAsyncDispatcher("tcp://"
						+ brokerAddress, UUID.randomUUID().toString(), null,
						null, true, this.logger);

				// connect the client
				client.connect();

				// store the client
				this.broker2Client.put(brokerAddress, client);
			}
		}
	}
}
