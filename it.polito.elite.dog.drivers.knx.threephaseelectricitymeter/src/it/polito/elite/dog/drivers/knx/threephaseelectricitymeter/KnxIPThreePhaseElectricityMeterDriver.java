/*
 * Dog - Device Driver
 * 
 * Copyright (c) 2011-2013 Dario Bonino and Luigi De Russis
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
package it.polito.elite.dog.drivers.knx.threephaseelectricitymeter;

import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.devicecategory.ThreePhaseElectricityMeter;
import it.polito.elite.dog.drivers.knx.gateway.KnxIPGatewayDriver;
import it.polito.elite.dog.drivers.knx.gateway.KnxIPGatewayDriverInstance;
import it.polito.elite.dog.drivers.knx.network.info.KnxIPInfo;
import it.polito.elite.dog.drivers.knx.network.interfaces.KnxIPNetwork;

import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.device.Device;
import org.osgi.service.device.Driver;

/**
 * The driver for KNX 3-phase electricity multi-meters
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class KnxIPThreePhaseElectricityMeterDriver implements Driver
{
	// a reference to the bundle context
	BundleContext context;
	
	// the driver logger
	LogHelper logger;
	
	// the log identifier, unique for the class
	public static String logId = "[KnxIPThreePhaseElectricityMeterDriver]: ";
	
	// the LDAP query for selecting services implementing the KnxIPNetwork
	// interface
	String filterQuery = String.format("(%s=%s)", Constants.OBJECTCLASS, KnxIPGatewayDriver.class.getName());
	
	// a reference to the network driver
	private AtomicReference<KnxIPNetwork> network;
	
	// a reference to the gateway driver
	private AtomicReference<KnxIPGatewayDriver> gateway;
	
	// the service registration handle
	private ServiceRegistration<?> regDriver;
	
	// the set of connected drivers
	private Vector<KnxIPThreePhaseElectricityMeterDriverInstance> connectedDrivers;
	
	/**
	 * Class constructor, creates a new driver for Multiple-Tariffs 3 phase
	 * energy meters
	 * 
	 * @param bundleContext
	 *            a reference to the OSGi context associated to this bundle
	 */
	public KnxIPThreePhaseElectricityMeterDriver()
	{
		// initialize atomic references
		this.network = new AtomicReference<KnxIPNetwork>();
		this.gateway = new AtomicReference<KnxIPGatewayDriver>();
	}
	
	public void activate(BundleContext bundleContext)
	{
		// store the context reference
		this.context = bundleContext;
		
		// create the vector holding the instances of this driver that are
		// connected to a device
		this.connectedDrivers = new Vector<KnxIPThreePhaseElectricityMeterDriverInstance>();
		
		// create a logger
		this.logger = new LogHelper(bundleContext);
		
		// register the service
		this.register();
	}
	
	public void deactivate()
	{
		// remove the service from the OSGi framework
		this.unRegister();
		
		for (KnxIPThreePhaseElectricityMeterDriverInstance driver : this.connectedDrivers)
			this.network.get().removeDriver(driver);
		
		this.connectedDrivers.clear();
		this.connectedDrivers = null;
		this.logger = null;
	}
	
	/**
	 * Unregister the service-related part of the driver from the OSGi framework
	 */
	public void unRegister()
	{
		// TODO DETACH allocated Drivers
		if (this.regDriver != null)
		{
			this.regDriver.unregister();
			this.regDriver = null;
		}
	}
	
	/**
	 * Registers this driver in the OSGi framework making its services available
	 * for all the other Dog bundles
	 */
	private void register()
	{
		// create a new property object describing this driver
		Hashtable<String, Object> propDriver = new Hashtable<String, Object>();
		
		// add the id of this driver to the properties
		propDriver.put(DeviceCostants.DRIVER_ID, "KnxIP_ThreePhaseElectricityMeter_driver");
		
		// register this driver in the OSGi framework
		this.regDriver = this.context.registerService(Driver.class.getName(), this, propDriver);
		
	}
	
	/**
	 * Handles the "availability" of a KnxIP network driver (store a reference
	 * to the driver and try to start).
	 * 
	 * @param netDriver
	 *            The available {@link KnxIPNetwork} driver service.
	 */
	public void addedNetworkDriver(KnxIPNetwork netDriver)
	{
		// store a reference to the network driver
		this.network.set(netDriver);
		
		// try to start service offering
		// this.register();
	}
	
	/**
	 * Handles the removal of the connected KnxIP network driver by
	 * unregistering the services provided by this driver
	 */
	public void removedNetworkDriver(KnxIPNetwork network)
	{
		// un-register this service
		this.unRegister();
		
		// null the reference to the network driver
		this.network.compareAndSet(network, null);
	}
	
	/**
	 * Handles the "availability" of a KnxIP gateway driver (store a reference
	 * to the driver and try to start).
	 * 
	 * @param gwDriver
	 *            The available {@link KnxIPGatewayDriver} service.
	 */
	public void addedGatewayDriver(KnxIPGatewayDriver gwDriver)
	{
		// store a reference to the gateway driver
		this.gateway.set(gwDriver);
		
		// try to start service offering
		// this.register();
	}
	
	/**
	 * Handles the removal of the connected KnxIP gateway driver by
	 * unregistering the services provided by this driver
	 */
	public void removedGatewayDriver(KnxIPGatewayDriver gateway)
	{
		// un-register this service
		this.unRegister();
		
		// null the reference to the network driver
		this.gateway.compareAndSet(gateway, null);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int match(ServiceReference reference) throws Exception
	{
		int matchValue = Device.MATCH_NONE;
		
		if ((this.network != null) && (this.gateway != null) && (this.regDriver != null))
		{
			if (this.context.getService(reference) instanceof ControllableDevice)
			{
				// get the given device category
				String deviceCategory = (String) reference.getProperty(DeviceCostants.DEVICE_CATEGORY);
				
				// get the given device manufacturer
				String manifacturer = (String) reference.getProperty(DeviceCostants.MANUFACTURER);
				
				// get the gateway to which the device is connected
				String gateway = (String) ((ControllableDevice) this.context.getService(reference))
						.getDeviceDescriptor().getGateway();
				
				// compute the matching score between the given device and this
				// driver
				if (deviceCategory != null)
				{
					if (manifacturer != null && manifacturer.equals(KnxIPInfo.MANUFACTURER)
							&& (deviceCategory.equals(ThreePhaseElectricityMeter.class.getName()))
							&& (this.gateway.get().isGatewayAvailable(gateway)))
					{
						matchValue = ThreePhaseElectricityMeter.MATCH_MANUFACTURER
								+ ThreePhaseElectricityMeter.MATCH_TYPE;
					}
					
				}
			}
		}
		return matchValue;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String attach(ServiceReference reference) throws Exception
	{
		if ((this.network != null) && (this.gateway != null) && (this.regDriver != null))
		{
			// get the gateway to which the device is connected
			String gateway = (String) ((ControllableDevice) this.context.getService(reference)).getDeviceDescriptor()
					.getGateway();
			
			KnxIPGatewayDriverInstance gwInstance = this.gateway.get().getSpecificGateway(gateway);
			
			KnxIPThreePhaseElectricityMeterDriverInstance instance = new KnxIPThreePhaseElectricityMeterDriverInstance(
					this.network.get(), (ControllableDevice) this.context.getService(reference),
					gwInstance.getGatewayAddress(), this.context);
			synchronized (this.connectedDrivers)
			{
				
				this.connectedDrivers.add(instance);
			}
		}
		return null;
	}
	
}
