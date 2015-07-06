/*
 * Dog - EnOcean Gateway Driver
 * 
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
package org.doggateway.drivers.enocean.device;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.devicecategory.Controllable;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import org.doggateway.drivers.enocean.gateway.EnOceanGatewayDriver;
import org.doggateway.drivers.enocean.network.EnOceanDriver;
import org.doggateway.drivers.enocean.network.EnOceanDriverInstance;
import org.doggateway.drivers.enocean.network.info.EnOceanDriverInfo;
import org.doggateway.drivers.enocean.network.info.EnOceanInfo;
import org.doggateway.drivers.enocean.network.interfaces.EnOceanNetwork;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.device.Device;
import org.osgi.service.device.Driver;

/**
 * @author bonino
 *
 */
public abstract class EnOceanDeviceDriver extends EnOceanDriver implements
		Driver, ManagedService
{
	// The OSGi framework context
	protected BundleContext context;

	// System logger
	protected LogHelper logger;

	// a reference to the network driver
	private AtomicReference<EnOceanNetwork> network;

	// a reference to the gateway driver
	private AtomicReference<EnOceanGatewayDriver> gateway;

	// the list of instances controlled / spawned by this driver
	protected Hashtable<String, EnOceanDriverInstance> managedInstances;

	// the registration object needed to handle the life span of this bundle in
	// the OSGi framework (it is a ServiceRegistration object for use by the
	// bundle registering the service to update the service's properties or to
	// unregister the service).
	private ServiceRegistration<?> regDriver;

	// the filter query for listening to framework events relative to the
	// to the ZWave gateway driver
	String filterQuery = String.format("(%s=%s)", Constants.OBJECTCLASS,
			EnOceanGatewayDriver.class.getName());

	// what are the on/off device categories that can match with this driver?
	protected Set<String> deviceCategories;

	// the driver instance class from which extracting the supported device
	// categories
	protected Class<?> driverInstanceClass;

	// the device glass used for auto-configuration
	protected String deviceMainClass;

	// the set of supported server clusters
	protected Set<String> supportedEEPs;

	// milliseconds between two update of the device status, from configuration
	// file
	protected int updateTimeMillis;

	/**
	 * The class constructor, initializes needed data structures.
	 * rInstanceClass;
	 * 
	 * // the device glass used for auto-configuration protected String
	 * deviceMainClass;
	 * 
	 * // the set of supported server clusters protected Set<String>
	 * supportedEEPs;
	 * 
	 * // milliseconds between two update of the device status, from
	 * configuration // file protected int updateTimeMillis;
	 * 
	 * /** The class constructor, initializes n
	 */
	public EnOceanDeviceDriver()
	{
		// intialize atomic references
		this.gateway = new AtomicReference<EnOceanGatewayDriver>();
		this.network = new AtomicReference<EnOceanNetwork>();

		// initialize the connected drivers list
		this.managedInstances = new Hashtable<String, EnOceanDriverInstance>();

		// initialize the set of implemented device categories
		this.deviceCategories = new HashSet<String>();

		// initialize the set of supported server clusters
		this.supportedEEPs = new HashSet<String>();
	}

	/**
	 * Handle the bundle activation
	 */
	public void activate(BundleContext bundleContext)
	{
		// init the logger
		this.logger = new LogHelper(bundleContext);

		// store the context
		this.context = bundleContext;

		// store the driver info
		this.driverInfo = new EnOceanDriverInfo();
		this.driverInfo.setDriverName(context.getBundle().getSymbolicName());
		this.driverInfo.setDriverVersion(context.getBundle().getVersion()
				.toString());
		this.driverInfo.setMainDeviceClass(this.deviceMainClass);
		this.driverInfo.addSupportedEEPs(this.supportedEEPs);

		// fill the device categories
		this.properFillDeviceCategories(this.driverInstanceClass);

	}

	public void deactivate()
	{
		// remove the service from the OSGi framework
		this.unRegisterEnOceanDeviceDriver();
	}

	// ------- Handle dynamic service binding -------------------

	/**
	 * Called when an {@link EnOceanGatewayDriver} becomes available and can be
	 * exploited by this driver
	 * 
	 * @param gatewayDriver
	 */
	public void gatewayAdded(EnOceanGatewayDriver gatewayDriver)
	{
		this.gateway.set(gatewayDriver);
	}

	/**
	 * Called whe the given {@link EnOceanGatewayDriver} ceases to exist in the
	 * framework; it triggers a disposal of corresponding references
	 * 
	 * @param gatewayDriver
	 */
	public void gatewayRemoved(EnOceanGatewayDriver gatewayDriver)
	{
		if (this.gateway.compareAndSet(gatewayDriver, null))
			// unregisters this driver from the OSGi framework
			unRegisterEnOceanDeviceDriver();
	}

	/**
	 * Called when a {@link EnOceanNetwork} service becomes available and can be
	 * exploited by this driver.
	 * 
	 * @param networkDriver
	 */
	public void networkAdded(EnOceanNetwork networkDriver)
	{
		this.network.set(networkDriver);
	}

	/**
	 * Called when the given {@link EnOceanNetwork} services is no more
	 * available in the OSGi framework; triggers removal of any reference to the
	 * service.
	 * 
	 * @param networkDriver
	 */
	public void networkRemoved(EnOceanNetwork networkDriver)
	{
		if (this.network.compareAndSet(networkDriver, null))
			// unregisters this driver from the OSGi framework
			unRegisterEnOceanDeviceDriver();
	}

	/**
	 * Registers this driver in the OSGi framework, making its services
	 * available to all the other bundles living in the same or in connected
	 * frameworks.
	 */
	private void registerEnOceanDeviceDriver()
	{
		if ((this.gateway.get() != null) && (this.network.get() != null)
				&& (this.context != null) && (this.regDriver == null))
		{
			// create a new property object describing this driver
			Hashtable<String, Object> propDriver = new Hashtable<String, Object>();
			// add the id of this driver to the properties
			propDriver.put(DeviceCostants.DRIVER_ID, this.getClass().getName());
			// register this driver in the OSGi framework
			regDriver = context.registerService(Driver.class.getName(), this,
					propDriver);

			// register the driver capability on the gateway
			this.gateway.get().addActiveDriverDetails(this.driverInfo);
		}
	}

	/**
	 * Handle the bundle de-activation
	 */
	protected void unRegisterEnOceanDeviceDriver()
	{
		// TODO DETACH allocated Drivers
		if (regDriver != null)
		{
			regDriver.unregister();
			regDriver = null;

			// register the driver capability on the gateway
			this.gateway.get().removeActiveDriverDetails(this.driverInfo);
		}
	}

	/**
	 * Fill a set with all the device categories whose devices can match with
	 * this driver. Automatically retrieve the device categories list by reading
	 * the implemented interfaces of its DeviceDriverInstance class bundle.
	 */
	public void properFillDeviceCategories(Class<?> cls)
	{
		if (cls != null)
		{
			for (Class<?> devCat : cls.getInterfaces())
			{
				this.deviceCategories.add(devCat.getName());
			}
		}

	}

	@Override
	public void updated(Dictionary<String, ?> properties)
			throws ConfigurationException
	{
		if (properties != null)
		{
			// try to get the baseline polling time
			String updateTimeAsString = (String) properties
					.get(EnOceanInfo.UPDATE_TIME);

			// trim leading and trailing spaces
			updateTimeAsString = updateTimeAsString.trim();

			// check not null
			if (updateTimeAsString != null)
			{
				// parse the string
				updateTimeMillis = Integer.valueOf(updateTimeAsString);
			}
			else
			{
				throw new ConfigurationException(EnOceanInfo.UPDATE_TIME,
						EnOceanInfo.UPDATE_TIME
								+ " not defined in configuraton file for "
								+ EnOceanDeviceDriver.class.getName());
			}

			// register driver
			registerEnOceanDeviceDriver();
		}

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
		String manifacturer = (String) reference
				.getProperty(DeviceCostants.MANUFACTURER);

		// get the gateway to which the device is connected
		String gateway = (String) reference.getProperty(DeviceCostants.GATEWAY);

		// compute the matching score between the given device and
		// this driver
		if (deviceCategory != null)
		{
			if (manifacturer != null && (gateway != null)
					&& (manifacturer.equals(EnOceanInfo.MANUFACTURER))
					&& (this.deviceCategories.contains(deviceCategory))
					&& (this.gateway.get().isGatewayAvailable(gateway)))
			{
				matchValue = Controllable.MATCH_MANUFACTURER
						+ Controllable.MATCH_TYPE;
			}

		}

		return matchValue;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String attach(ServiceReference reference) throws Exception
	{
		// get the referenced device
		ControllableDevice device = ((ControllableDevice) context
				.getService(reference));

		// check if not already attached
		if (!this.managedInstances.containsKey(device.getDeviceId()))
		{
			// create a new driver instance
			EnOceanDriverInstance driverInstance = this
					.createEnOceanDriverInstance(this.network.get(), device,
							updateTimeMillis, context);

			// connect this driver instance with the device
			device.setDriver(driverInstance);

			// store a reference to the connected driver
			synchronized (this.managedInstances)
			{
				this.managedInstances.put(device.getDeviceId(), driverInstance);
			}
		}

		return null;

	}

	public abstract EnOceanDriverInstance createEnOceanDriverInstance(
			EnOceanNetwork enOceanNetwork, ControllableDevice device,
			int updateTimeMillis, BundleContext context);
}
