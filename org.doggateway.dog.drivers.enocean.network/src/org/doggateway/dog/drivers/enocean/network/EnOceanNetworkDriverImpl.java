/*
 * Dog - EnOcean Network Driver
 * 
 * Copyright 2015 Dario Bonino 
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
package org.doggateway.dog.drivers.enocean.network;

import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.enocean.enj.communication.EnJConnection;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

import org.doggateway.dog.drivers.enocean.network.info.EnOceanDeviceInfo;
import org.doggateway.dog.drivers.enocean.network.interfaces.EnOceanDeviceDiscoveryListener;
import org.doggateway.dog.drivers.enocean.network.interfaces.EnOceanNetwork;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.log.LogService;

/**
 * @author bonino
 *
 */
public class EnOceanNetworkDriverImpl implements EnOceanNetwork, ManagedService
{
	// the bundle context
	private BundleContext bundleContext;

	// the service registration handle
	private ServiceRegistration<?> regServiceEnOceanDriverImpl;

	// the driver logger
	private LogHelper logger;

	// the set of currently available devices (TODO: check if this is not a
	// little bit overkill)
	// which might not yet be "assigned" to a specific driver.
	private ConcurrentHashMap<Integer, EnOceanDeviceInfo> availableDevices;

	// the set of devices associated to a given driver
	private ConcurrentHashMap<EnOceanDeviceInfo, EnOceanDriverInstance> connectedDrivers;

	// the set of device discovery listeners
	private HashSet<EnOceanDeviceDiscoveryListener> deviceDiscoveryListeners;

	// the low-level EnOcean communication library
	private EnJConnection enOcean;

	/**
	 * Initializes the inner data structures, while not instantiating the
	 * underlying EnJConnection object, which instead can be prepared only after
	 * actual configuration occurs.
	 */
	public EnOceanNetworkDriverImpl()
	{
		// initialize the set of available devices
		this.availableDevices = new ConcurrentHashMap<Integer, EnOceanDeviceInfo>();

		// initialize the set of connected drivers
		this.connectedDrivers = new ConcurrentHashMap<EnOceanDeviceInfo, EnOceanDriverInstance>();

		// initialize the set of device discovery listeners
		this.deviceDiscoveryListeners = new HashSet<EnOceanDeviceDiscoveryListener>();
	}

	/**
	 * Called when the bundle is activated by the OSGi framework
	 * 
	 * @param context
	 *            The bundle context to use for activation and registration of
	 *            bundle services.
	 */
	public void activate(BundleContext context)
	{
		// store the bundle context
		this.bundleContext = context;

		// initialize the class logger...
		this.logger = new LogHelper(context);

		// debug: signal activation...
		this.logger.log(LogService.LOG_DEBUG, "Activated...");

		// register the service
		this.registerNetworkService();
	}

	/**
	 * Called upon bundle deactivation, enables to accomplish all tasks needed
	 * to perform a clean shutdown of the bundle and of relative services.
	 */
	public void deactivate()
	{
		// unregister the service
		this.unregisterNetworkService();

		// TODO: perform house keeping stuff here...

		// log
		this.logger.log(LogService.LOG_INFO, "Deactivated...");
	}

	@Override
	public void addDriver(EnOceanDeviceInfo devInfo,
			EnOceanDriverInstance driver)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void removeDriver(EnOceanDriverInstance driver)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void addDeviceDiscoveryListener(
			EnOceanDeviceDiscoveryListener listener)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void removeDeviceDiscoveryListener(
			EnOceanDeviceDiscoveryListener listener)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void enableTeachIn(int timeoutMillis, boolean smart)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void enableExplicitTeachIn(String deviceLowAddress,
			String deviceEEP, int timeoutMillis)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void addDevice(String deviceLowAddress, String deviceEEP)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void updated(Dictionary<String, ?> properties)
			throws ConfigurationException
	{
		// get the bundle configuration parameters, e.g., the serial port to
		// which "connect" and the location for the low-level persistent device
		// database, if needed.
		if (properties != null)
		{
			//debug log
			logger.log(LogService.LOG_DEBUG,
					"Received configuration properties");
		}

	}

	/*************************************************
	 *
	 * PRIVATE METHODS
	 *
	 ************************************************/

	/**
	 * Registers the services described by the {@link EnOceanNetwork} interface
	 * and provided by this class as "available" in the OSGi framework.
	 */
	private void registerNetworkService()
	{
		// simple registration stuff

		// avoid multiple registrations
		if (this.regServiceEnOceanDriverImpl == null)
		{
			// register the service, with no properties
			this.regServiceEnOceanDriverImpl = this.bundleContext
					.registerService(EnOceanNetwork.class.getName(), this, null);
		}

	}

	/**
	 * Unregisters the services provided by this class from the OSGi framework
	 */
	private void unregisterNetworkService()
	{
		// performs service de-registration from the framework
		if (this.regServiceEnOceanDriverImpl != null)
		{
			// de-register
			this.regServiceEnOceanDriverImpl.unregister();
		}

	}

}
