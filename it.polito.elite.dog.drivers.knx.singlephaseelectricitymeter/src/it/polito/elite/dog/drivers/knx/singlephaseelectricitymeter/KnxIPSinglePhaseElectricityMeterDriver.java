/*
 * Dog 2.0 - Device Driver
 * 
 * Copyright [2012]
 * [Luigi De Russis (luigi.derussis@polito.it), Politecnico di Torino]  
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package it.polito.elite.dog.drivers.knx.singlephaseelectricitymeter;

import it.polito.elite.dog.drivers.knx.gateway.KnxIPGatewayDriver;
import it.polito.elite.dog.drivers.knx.gateway.KnxIPGatewayDriverInstance;
import it.polito.elite.dog.drivers.knx.network.info.KnxIPInfo;
import it.polito.elite.dog.drivers.knx.network.interfaces.KnxIPNetwork;
import it.polito.elite.domotics.dog2.doglibrary.DogDeviceCostants;
import it.polito.elite.domotics.dog2.doglibrary.devicecategory.ControllableDevice;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;
import it.polito.elite.domotics.model.devicecategory.SinglePhaseElectricityMeter;

import java.util.Hashtable;
import java.util.Vector;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.device.Device;
import org.osgi.service.device.Driver;
import org.osgi.service.log.LogService;

/**
 * The driver for KNX 1-phase electricity multi-meters
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * 
 */
public class KnxIPSinglePhaseElectricityMeterDriver implements Driver
{
	// a reference to the bundle context
	BundleContext context;
	
	// the driver logger
	LogService logger;
	
	// the log identifier, unique for the class
	public static String logId = "[KnxIPSinglePhaseElectricityMeterDriver]: ";
	
	// the LDAP query for selecting services implementing the KnxIPNetwork
	// interface
	String filterQuery = String.format("(%s=%s)", Constants.OBJECTCLASS, KnxIPGatewayDriver.class.getName());
	
	// a reference to the network driver
	private KnxIPNetwork network;
	
	// a reference to the gateway driver
	private KnxIPGatewayDriver gateway;
	
	// the service registration handle
	private ServiceRegistration<?> regDriver;
	
	// the set of connected drivers
	private Vector<KnxIPSinglePhaseElectricityMeterDriverInstance> connectedDrivers;
	
	/**
	 * Class constructor, creates a new driver for single phase electricity
	 * meters
	 */
	public KnxIPSinglePhaseElectricityMeterDriver()
	{
		// intentionally left empty
	}
	
	public void activate(BundleContext bundleContext)
	{
		// store the context reference
		this.context = bundleContext;
		
		// create the vector holding the instances of this driver that are
		// connected to a device
		this.connectedDrivers = new Vector<KnxIPSinglePhaseElectricityMeterDriverInstance>();
		
		// create a logger
		this.logger = new DogLogInstance(bundleContext);
		
		// try to register the service
		this.register();
	}
	
	public void deactivate()
	{
		// remove the service from the OSGi framework
		this.unRegister();
		
		for (KnxIPSinglePhaseElectricityMeterDriverInstance driver : this.connectedDrivers)
			this.network.removeDriver(driver);
		
		this.network = null;
		this.context = null;
		this.connectedDrivers.clear();
		this.connectedDrivers = null;
		this.logger = null;
	}
	
	/**
	 * Unregister the service-related part of the driver from the OSGi framework
	 */
	public void unRegister()
	{
		if (this.regDriver != null)
		{
			this.regDriver.unregister();
			this.regDriver = null;
		}
	}
	
	/**
	 * Handles the "availability" of a KnxIP network driver (store a reference
	 * to the driver and try to start).
	 * 
	 * @param netDriver
	 *            The available {@link ModbusNetwork} driver service.
	 */
	public void addedNetworkDriver(KnxIPNetwork netDriver)
	{
		// store a reference to the network driver
		this.network = netDriver;
		
		// try to start service offering
		this.register();
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
		this.network = null;
	}
	
	/**
	 * Handles the "availability" of a KnxIP gateway driver (store a reference
	 * to the driver and try to start).
	 * 
	 * @param gwDriver
	 *            The available {@link ModbusGatewayDriver} service.
	 */
	public void addedGatewayDriver(KnxIPGatewayDriver gwDriver)
	{
		// store a reference to the gateway driver
		this.gateway = gwDriver;
		
		// try to start service offering
		this.register();
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
		this.gateway = null;
	}
	
	/**
	 * Registers this driver in the OSGi framework making its services available
	 * for all the other Dog bundles
	 */
	private void register()
	{
		if ((this.network != null) && (this.gateway != null) && (this.context != null) && (this.regDriver == null))
		{
			// create a new property object describing this driver
			Hashtable<String, Object> propDriver = new Hashtable<String, Object>();
			
			// add the id of this driver to the properties
			propDriver.put(DogDeviceCostants.DRIVER_ID, "KnxIP_SinglePhaseElectricityMeter_driver");
			
			// register this driver in the OSGi framework
			this.regDriver = this.context.registerService(Driver.class.getName(), this, propDriver);
		}
	}
	
	public void removedService(ServiceReference<Object> reference, Object service)
	{
		this.unRegister();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int match(ServiceReference reference) throws Exception
	{
		int matchValue = Device.MATCH_NONE;
		
		if ((this.network != null) && (this.gateway != null) && (this.regDriver != null))
		{
			// get the given device category
			String deviceCategory = (String) reference.getProperty(DogDeviceCostants.DEVICE_CATEGORY);
			
			// get the given device manufacturer
			String manifacturer = (String) reference.getProperty(DogDeviceCostants.MANUFACTURER);
			
			// get the gateway to which the device is connected
			@SuppressWarnings("unchecked")
			String gateway = (String) ((ControllableDevice) this.context.getService(reference)).getDeviceDescriptor()
					.getGateway();
			
			// compute the matching score between the given device and this
			// driver
			if (deviceCategory != null)
			{
				if (manifacturer != null && manifacturer.equals(KnxIPInfo.MANUFACTURER)
						&& (deviceCategory.equals(SinglePhaseElectricityMeter.class.getName()))
						&& (this.gateway.isGatewayAvailable(gateway)))
				{
					matchValue = SinglePhaseElectricityMeter.MATCH_MANUFACTURER
							+ SinglePhaseElectricityMeter.MATCH_TYPE;
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
			
			KnxIPGatewayDriverInstance gwInstance = this.gateway.getSpecificGateway(gateway);
			
			KnxIPSinglePhaseElectricityMeterDriverInstance instance = new KnxIPSinglePhaseElectricityMeterDriverInstance(
					this.network, (ControllableDevice) this.context.getService(reference),
					gwInstance.getGatewayAddress(), this.context);
			synchronized (this.connectedDrivers)
			{
				this.connectedDrivers.add(instance);
			}
		}
		return null;
	}
	
}
