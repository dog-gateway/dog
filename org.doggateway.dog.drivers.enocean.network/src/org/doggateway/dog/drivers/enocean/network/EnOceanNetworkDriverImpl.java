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
import it.polito.elite.enocean.enj.communication.EnJDeviceListener;
import it.polito.elite.enocean.enj.link.EnJLink;
import it.polito.elite.enocean.enj.model.EnOceanDevice;
import it.polito.elite.enocean.enj.util.ByteUtils;

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
public class EnOceanNetworkDriverImpl implements EnOceanNetwork,
		ManagedService, EnJDeviceListener
{
	// -------- the configuration parameters ---------

	// serial port
	public static final String SERIAL_PORT = "serialPort";

	// persistent low-level device db
	public static final String DEVICE_DB = "deviceDB";

	// ------------------------------------------------

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
	private EnJConnection enOceanConnection;

	// the lowest-level EnOcean link
	private EnJLink enOceanLink;

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
		// "register" the driver for the given device
		if (this.availableDevices.containsKey(devInfo.getUid()))
		{
			// the device is already known, just add the driver
			this.connectedDrivers.put(devInfo, driver);

			// get the low-level device described by the given device info
			EnOceanDevice device = this.enOceanConnection.getDevice(devInfo
					.getUid());

			// connect the driver instance with the low-level device to enable
			// direct attachment of EEPListeners and easier configuration set-up
			driver.setEnoceanDevice(device);
		}
		else
		{
			// the device does not exist, check if information is complete and
			// in such a case, add the device,
			// this will in principle trigger a "discovery cycle, to avoid such
			// a trigger, register the device in the list of available devices.
			String address = devInfo.getAddress();
			String eep = devInfo.getEep();

			if ((address != null) && (!address.isEmpty()) && (eep != null)
					&& (!eep.isEmpty()))
			{
				// add the device to the set of available devices
				this.availableDevices.put(devInfo.getUid(), devInfo);

				// add the driver
				this.connectedDrivers.put(devInfo, driver);

				// create the device
				this.enOceanConnection.addNewDevice(address, eep);
			}

		}

	}

	@Override
	public void removeDriver(EnOceanDriverInstance driver)
	{
		// removes all subscriptions related to this driver (not the device as
		// it might still be reachable on the network)
		HashSet<EnOceanDeviceInfo> keysToRemove = new HashSet<EnOceanDeviceInfo>();

		// find lines to remove
		for (EnOceanDeviceInfo device : this.connectedDrivers.keySet())
		{
			if (this.connectedDrivers.get(device).equals(driver))
				keysToRemove.add(device);
		}

		// remove
		for (EnOceanDeviceInfo key : keysToRemove)
		{
			this.connectedDrivers.remove(key);
		}
	}

	@Override
	public void addDeviceDiscoveryListener(
			EnOceanDeviceDiscoveryListener listener)
	{
		// add the listener to the set of device discovery listeners
		if (this.deviceDiscoveryListeners != null)
			this.deviceDiscoveryListeners.add(listener);
		else
			this.logger
					.log(LogService.LOG_ERROR,
							"The device discovery listener set has not been initialized.");

	}

	@Override
	public void removeDeviceDiscoveryListener(
			EnOceanDeviceDiscoveryListener listener)
	{
		// remove the listener from the list, if exists
		if ((this.deviceDiscoveryListeners != null)
				&& (!this.deviceDiscoveryListeners.isEmpty()))
			this.deviceDiscoveryListeners.remove(listener);
	}

	@Override
	public void enableTeachIn(int timeoutMillis, boolean smart)
	{
		// forward the command to the low-level library
		this.enOceanConnection.setSmartTeachIn(smart);
		this.enOceanConnection.enableTeachIn(timeoutMillis);

	}

	@Override
	public void enableExplicitTeachIn(String deviceLowAddress,
			String deviceEEP, int timeoutMillis)
	{
		// forward the command to the low-level library
		this.enOceanConnection.enableTeachIn(deviceLowAddress, deviceEEP,
				timeoutMillis);

	}

	@Override
	public void addDevice(String deviceLowAddress, String deviceEEP)
	{
		// this actually should be performed as part of the device-specific
		// driver "registration", if the given device is not yet available at
		// the network level, however we decided to leave an open stub for any
		// possible unforeseen cases in which device addition should be
		// performed apart.
		if ((deviceLowAddress != null) && (!deviceLowAddress.isEmpty())
				&& (deviceEEP != null) && (!deviceEEP.isEmpty()))
		{
			// create the device
			this.enOceanConnection.addNewDevice(deviceLowAddress, deviceEEP);

			// this in turn should trigger a call to the registered
			// EnJDeviceListener, i.e. this class instance.
		}
	}
	
	

	/* (non-Javadoc)
	 * @see org.doggateway.dog.drivers.enocean.network.interfaces.EnOceanNetwork#getConnection()
	 */
	@Override
	public EnJConnection getConnection()
	{
		return this.enOceanConnection;
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
			// debug log
			logger.log(LogService.LOG_DEBUG,
					"Received configuration properties");

			// get the serial port to which the physical gateway is connected
			// TODO: implement a serial port scanner process to remove the need
			// to
			// explicitly set the serial port to connect to
			String serialPort = (String) properties
					.get(EnOceanNetworkDriverImpl.SERIAL_PORT);

			// get the device db filename, can be empty
			String deviceDB = (String) properties
					.get(EnOceanNetworkDriverImpl.DEVICE_DB);

			// check if needed parameters are available
			if ((serialPort != null) && (!serialPort.isEmpty()))
			{
				try
				{
					// create the lowest link layer
					this.enOceanLink = new EnJLink(serialPort);

					// check the low-level device db filename
					if ((deviceDB != null) && (!deviceDB.isEmpty()))
					{
						this.enOceanConnection = new EnJConnection(
								this.enOceanLink, deviceDB, this);
					}
					else
					{
						this.enOceanConnection = new EnJConnection(
								this.enOceanLink, null, this);
					}

					// connect to the serial port, i.e. to the EnOcean gateway
					this.enOceanLink.connect();

					// update the service registration
					this.registerNetworkService();
				}
				catch (Exception e)
				{
					this.logger
							.log(LogService.LOG_ERROR,
									"Unable to connect to the EnOcean serial interface.",
									e);
				}
			}

		}

	}

	/*************************************************
	 * 
	 * EnOcean EnJDeviceListener
	 * 
	 ************************************************/

	@Override
	public void addedEnOceanDevice(EnOceanDevice device)
	{
		// check if the device has already been seen
		if (!this.availableDevices.containsKey(device.getDeviceUID()))
		{
			// build the corresponding device info
			final EnOceanDeviceInfo devInfo = new EnOceanDeviceInfo(
					device.getDeviceUID(), ByteUtils.toHexString(device
							.getAddress()), device.getEEP().getEEPIdentifier()
							.asEEPString());

			// store the device info
			this.availableDevices.put(device.getDeviceUID(), devInfo);

			// trigger device discovery (separate thread to avoid locking)

			// *****************************
			// enable discovery only if the new device was created upon a
			// teach-in request. Remains to check if it is sufficient to verify
			// the teach-in status of the lower-level library, and if does not
			// generates issues at some point.
			// *****************************
			if (this.enOceanConnection.isTeachInEnabled()
					|| this.enOceanConnection.isSmartTeachInEnabled())
			{

				// the triggering task
				Runnable discoveryTask = new Runnable()
				{

					@Override
					public void run()
					{
						// iterate over all discovery listeners
						for (EnOceanDeviceDiscoveryListener listener : deviceDiscoveryListeners)
						{
							// notify the addition
							listener.addedEnOceanDevice(devInfo);
						}

					}
				};

				// the worker thread
				Thread worker = new Thread(discoveryTask);

				// start the task
				worker.start();
			}

		}
		/*
		 * else { //handle device re-connection }
		 */

	}

	@Override
	public void modifiedEnOceanDevice(EnOceanDevice changedDevice)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void removedEnOceanDevice(EnOceanDevice changedDevice)
	{
		// handle device removal

		// check if the device is "registered"
		if (this.availableDevices.containsKey(changedDevice.getDeviceUID()))
		{
			// check if any driver is "connected to the device"
			EnOceanDriverInstance driverInstance = this.connectedDrivers
					.get(this.availableDevices.get(changedDevice.getDeviceUID()));

			// update the binding
			// here the driver shall be informed that the device is no more
			// connected
			driverInstance.unsetEnOceanDevice(changedDevice);
			
			// possible actions: remove subscription fro network and "trigger" a
			// device status change (more in the DAL view), do nothing

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
