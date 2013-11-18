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
package it.polito.elite.dog.drivers.zwave.gateway;

import it.polito.elite.dog.core.devicefactory.api.DeviceFactory;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.devicecategory.ZWaveGateway;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.drivers.zwave.network.info.ZWaveInfo;
import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.device.Device;
import org.osgi.service.device.Driver;
import org.osgi.service.log.LogService;

public class ZWaveGatewayDriver implements Driver, ManagedService
{
	// List of available command on controller
	public static final String CMD_INCLUDE = "controller.AddNodeToNetwork";
	public static final String CMD_EXCLUDE = "controller.RemoveNodeFromNetwork";
	public static final String CMD_LEARN = "controller.SetLearnMode";
	public static final String CMD_RESET = "controller.SetDefault";

	// the key to identify the proper configuration value
	private static final String WAIT_BEFORE_DEVICE_INSTALL = "waitBeforeDeviceInstall";
	private static final String DEVICE_DB = "deviceDB";

	// The OSGi framework context
	protected BundleContext context;

	// System logger
	LogHelper logger;

	// the log identifier, unique for the class
	public static final String LOG_ID = "[ZWaveGatewayDriver]: ";

	// String identifier for driver id
	public static final String DRIVER_ID = "ZWave_ZWaveGateway_driver";

	// a reference to the network driver (currently not used by this driver
	// version, in the future it will be used to implement gateway-specific
	// functionalities.
	private AtomicReference<ZWaveNetwork> network;

	// a reference to the network driver (currently not used by this driver
	// version, in the future it will be used to implement gateway-specific
	// functionalities.
	private AtomicReference<DeviceFactory> deviceFactory;

	// the registration object needed to handle the life span of this bundle in
	// the OSGi framework (it is a ServiceRegistration object for use by the
	// bundle registering the service to update the service's properties or to
	// unregister the service).
	private ServiceRegistration<?> regDriver;

	// register this driver as a gateway used by device-specific drivers
	private ServiceRegistration<?> regZWaveGateway;

	// the set of currently connected gateways... indexed by their ids
	private ConcurrentHashMap<String, ZWaveGatewayDriverInstance> connectedGateways;

	// the dictionary containing the current snapshot of the driver
	// configuration, i.e., the list of supported devices and the corresponding
	// DogOnt types.
	private ConcurrentHashMap<String, String> supportedDevices;

	// the time to wait before auto-installing devices (to enable complete
	// command class reporting)
	private long waitBeforeDeviceInstall;

	// the device database location
	private String deviceDBLocation;

	// the LDAP query used to match the ModbusNetworkDriver
	String filterQuery = String.format("(%s=%s)", Constants.OBJECTCLASS,
			ZWaveNetwork.class.getName());

	public ZWaveGatewayDriver()
	{
		// initialize the map of connected gateways
		this.connectedGateways = new ConcurrentHashMap<String, ZWaveGatewayDriverInstance>();

		// initialize the supported devices map
		this.supportedDevices = new ConcurrentHashMap<String, String>();

		// initialize the network driver reference
		this.network = new AtomicReference<ZWaveNetwork>();

		// initialize the device factory reference
		this.deviceFactory = new AtomicReference<DeviceFactory>();

		// init the wait before install time at 0
		this.waitBeforeDeviceInstall = 0;
	}

	/**
	 * Handle the bundle activation
	 */
	public void activate(BundleContext bundleContext)
	{
		// store the context
		context = bundleContext;

		// init the logger
		logger = new LogHelper(context);
	}

	public void deactivate()
	{
		// remove the service from the OSGi framework
		this.unRegister();
	}

	public void addingService(ZWaveNetwork networkDriver)
	{
		this.network.set(networkDriver);

	}

	public void removedService(ZWaveNetwork networkDriver)
	{
		if (this.network.compareAndSet(networkDriver, null))
			// unregisters this driver from the OSGi framework
			unRegister();
	}

	public void addedDeviceFactory(DeviceFactory deviceFactory)
	{
		this.deviceFactory.set(deviceFactory);

	}

	public void removedDeviceFactory(DeviceFactory deviceFactory)
	{
		if (this.deviceFactory.compareAndSet(deviceFactory, null))
			// unregisters this driver from the OSGi framework
			unRegister();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int match(ServiceReference reference) throws Exception
	{
		int matchValue = Device.MATCH_NONE;

		// get the given device category
		String deviceCategory = (String) reference
				.getProperty(DeviceCostants.DEVICE_CATEGORY);

		// get the given device manufacturer
		String manufacturer = (String) reference
				.getProperty(DeviceCostants.MANUFACTURER);

		// compute the matching score between the given device and this driver
		if (deviceCategory != null)
		{
			if (manufacturer != null
					&& manufacturer.equals(ZWaveInfo.MANUFACTURER)
					&& (deviceCategory.equals(ZWaveGateway.class.getName())))
			{
				matchValue = ZWaveGateway.MATCH_MANUFACTURER
						+ ZWaveGateway.MATCH_TYPE;
			}

		}
		return matchValue;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String attach(ServiceReference reference) throws Exception
	{
		// get the corresponding end point set
		@SuppressWarnings("unchecked")
		Set<String> gatewayNodeIdSet = ((ControllableDevice) context
				.getService(reference)).getDeviceDescriptor()
				.getSimpleConfigurationParams().get(ZWaveInfo.NODE_ID);

		@SuppressWarnings("unchecked")
		String deviceId = ((ControllableDevice) context.getService(reference))
				.getDeviceId();

		// if not null, it is a singleton
		if (gatewayNodeIdSet != null)
		{
			// get the endpoint address of the connecting gateway
			String sNodeID = gatewayNodeIdSet.iterator().next();

			// check not null
			if ((sNodeID != null) && (!sNodeID.isEmpty()))
			{
				if (!this.isGatewayAvailable(deviceId))
				{
					// gateway has only one instance and its id is 0
					Set<Integer> instancesId = new HashSet<Integer>();
					instancesId.add(0);

					// create a new instance of the gateway driver
					@SuppressWarnings("unchecked")
					ZWaveGatewayDriverInstance driver = new ZWaveGatewayDriverInstance(
							this.network.get(), this.deviceFactory.get(),
							(ControllableDevice) context.getService(reference),
							Integer.parseInt(sNodeID), instancesId, context);

					// set the supported devices
					if ((this.supportedDevices != null)
							&& (!this.supportedDevices.isEmpty()))
						driver.setSupportedDevices(this.supportedDevices);
				
					// set the time to wait before automatic device detection / installation
					driver.setWaitBeforeDeviceInstall(this.waitBeforeDeviceInstall);

					//store the just created gateway instance
					synchronized (connectedGateways)
					{
						// store a reference to the gateway driver
						connectedGateways.put(deviceId, driver);
					}

					// modify the service description causing a forcing the
					// framework to send a modified service notification
					final Hashtable<String, Object> propDriver = new Hashtable<String, Object>();
					propDriver.put(DeviceCostants.DRIVER_ID, DRIVER_ID);
					propDriver.put(DeviceCostants.GATEWAY_COUNT,
							connectedGateways.size());

					regDriver.setProperties(propDriver);
				}
			}
			else
			{
				// do not attach, log and throw exception
				logger.log(
						LogService.LOG_WARNING,
						LOG_ID
								+ "Unable to get the current gateway node id (empty set), this prevents the device from being attached!");
				throw new Exception(
						LOG_ID
								+ "Unable to get the current gateway node id, this prevents the device from being attached!");
			}
		}
		else
		{
			// do not attach, log and throw exception
			logger.log(
					LogService.LOG_WARNING,
					LOG_ID
							+ "Unable to get the current gateway node id (missing parameter), this prevents the device from being attached!");
			throw new Exception(
					LOG_ID
							+ "Unable to get the current gateway node id, this prevents the device from being attached!");
		}

		return null;
	}

	/**
	 * Handle the bundle de-activation
	 */
	protected void unRegister()
	{
		// un-registers this driver
		if (regDriver != null)
		{
			regDriver.unregister();
			regDriver = null;
		}

		// un-register the gateway service
		if (regZWaveGateway != null)
		{
			regZWaveGateway.unregister();
			regZWaveGateway = null;
		}
	}

	/**
	 * Registers this driver in the OSGi framework, making its services
	 * available to all the other bundles living in the same or in connected
	 * frameworks.
	 */
	private void registerDriver()
	{
		if ((network.get() != null) && (this.context != null)
				&& (this.regDriver == null))
		{
			Hashtable<String, Object> propDriver = new Hashtable<String, Object>();
			propDriver.put(DeviceCostants.DRIVER_ID, DRIVER_ID);
			propDriver.put(DeviceCostants.GATEWAY_COUNT,
					connectedGateways.size());
			regDriver = context.registerService(Driver.class.getName(), this,
					propDriver);
			regZWaveGateway = context.registerService(
					ZWaveGatewayDriver.class.getName(), this, null);
		}
	}

	/**
	 * check if the gateway identified by the given gateway id is currently
	 * registered with this driver
	 * 
	 * @param gatewayId
	 * @return true if the gateway corresponding to the given id is already
	 *         registered, false otherwise.
	 */
	public boolean isGatewayAvailable(String gatewayId)
	{
		return connectedGateways.containsKey(gatewayId);
	}

	/**
	 * Returns a live reference to the specific gateway driver instance
	 * associated with the ZWave gateway device having the given id.
	 * 
	 * @param gatewayId
	 * @return
	 */
	public ZWaveGatewayDriverInstance getSpecificGateway(String gatewayId)
	{
		return connectedGateways.get(gatewayId);
	}

	/**
	 * Returns a live reference to the specific network driver service to which
	 * this {@link ZWaveGatewayDriver} is connected.
	 * 
	 * @return
	 */
	public ZWaveNetwork getNetwork()
	{
		return network.get();
	}

	/**
	 * Returns the number of milliseconds to wait before attempting automatic
	 * device recognition
	 * 
	 * @return
	 */
	public long getWaitBeforeDeviceInstall()
	{
		return waitBeforeDeviceInstall;
	}

	@Override
	public void updated(Dictionary<String, ?> config)
			throws ConfigurationException
	{
		// check if configuration is not null, if null... dynamic device
		// creation will be disabled
		if (config != null)
		{
			// get the time to wait before auto-installation of devices
			String waitBeforeDeviceInstallAsString = (String) config
					.get(ZWaveGatewayDriver.WAIT_BEFORE_DEVICE_INSTALL);

			// try to convert it to a number
			try
			{
				this.waitBeforeDeviceInstall = Long
						.valueOf(waitBeforeDeviceInstallAsString);
			}
			catch (NumberFormatException e)
			{
				// use the network polling time if available
				ZWaveNetwork networkDriver = this.network.get();
				if (networkDriver != null)
					this.waitBeforeDeviceInstall = networkDriver
							.getPollingTimeMillis();
				else
					// no wait
					this.waitBeforeDeviceInstall = 0;
			}

			// get the device db
			// try to get the persistence store directory
			this.deviceDBLocation = (String) config
					.get(ZWaveGatewayDriver.DEVICE_DB);

			// trim leading and trailing spaces
			this.deviceDBLocation = deviceDBLocation.trim();

			// check not null
			if (deviceDBLocation != null)
			{
				// check absolute vs relative
				File deviceDBLocationFile = new File(deviceDBLocation);
				if (!deviceDBLocationFile.isAbsolute())
					this.deviceDBLocation = System.getProperty("configFolder")
							+ "/" + this.deviceDBLocation;

				// load the device db
				Properties deviceDB = new Properties();

				try
				{
					deviceDB.load(new FileReader(this.deviceDBLocation));

					// update the device databse
					this.updateDeviceDatabase(deviceDB);

					// sync the gateway instances
					this.syncDeviceDBs();
				}
				catch (IOException e)
				{
					this.logger
							.log(LogService.LOG_ERROR,
									"Error while opening the device database.... dynamic device creation will not be supported!");
				}

			}
			
			//register the gateway only when it is fully configured
			registerDriver();
		}
	}

	/**
	 * Updates the inner device database data structure on the basis of the
	 * given device db as Java properties
	 * 
	 * @param config
	 */
	private void updateDeviceDatabase(Properties config)
	{
		// debug
		if (this.supportedDevices.isEmpty())
			this.logger.log(LogService.LOG_DEBUG,
					"Creating dynamic device creation db...");
		else
			this.logger.log(LogService.LOG_DEBUG,
					"Updating dynamic device creation db...");
		// store the configuration (deep copy, check if needed)
		Enumeration<?> keys = config.propertyNames();

		// iterate over the keys
		while (keys.hasMoreElements())
		{
			// get the device unique id
			// (manufacturer-productseries-productid)
			String deviceId = (String) keys.nextElement();
			String deviceType = (String) config.get(deviceId);

			// store the couple
			this.supportedDevices.put(deviceId, deviceType);
		}

		// debug
		this.logger.log(LogService.LOG_DEBUG,
				"Completed dynamic device creation db");
	}

	/**
	 * sync the current device database for all gateway instances
	 */
	private void syncDeviceDBs()
	{
		// synchronize over the connected gateways list
		synchronized (this.connectedGateways)
		{
			// update connected drivers
			for (String key : this.connectedGateways.keySet())
			{
				this.connectedGateways.get(key).setSupportedDevices(
						this.supportedDevices);
			}
		}
	}
}
