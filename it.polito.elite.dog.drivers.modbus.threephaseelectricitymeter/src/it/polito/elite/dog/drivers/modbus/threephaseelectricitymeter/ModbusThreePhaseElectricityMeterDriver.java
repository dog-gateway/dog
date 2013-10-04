/*
 * Dog - Device Driver
 * 
 * Copyright (c) 2012-2013 Dario Bonino
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
package it.polito.elite.dog.drivers.modbus.threephaseelectricitymeter;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.devicecategory.ThreePhaseElectricityMeter;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.drivers.modbus.gateway.ModbusGatewayDriver;
import it.polito.elite.dog.drivers.modbus.network.info.ModbusInfo;
import it.polito.elite.dog.drivers.modbus.network.interfaces.ModbusNetwork;

import java.util.Hashtable;
import java.util.Vector;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.device.Device;
import org.osgi.service.device.Driver;
import org.osgi.service.log.LogService;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class ModbusThreePhaseElectricityMeterDriver implements Driver
{
	// The OSGi framework context
	protected BundleContext context;
	
	// System logger
	LogHelper logger;
	
	// the log identifier, unique for the class
	public static String logId = "[ModbusThreePhaseElectricityMeterDriver]: ";
	
	// a reference to the network driver
	private ModbusNetwork network;
	
	// a reference to the gateway driver
	private ModbusGatewayDriver gateway;
	
	// the list of driver instances currently connected to a device
	private Vector<ModbusThreePhaseElectricityMeterDriverInstance> connectedDrivers;
	
	// the registration object needed to handle the life span of this bundle in
	// the OSGi framework (it is a ServiceRegistration object for use by the
	// bundle registering the service to update the service's properties or to
	// unregister the service).
	private ServiceRegistration<?> regDriver;
	
	/**
	 * The class constructor, creates an instance of the
	 * {@link ModbusThreePhaseElectricityMeterDriver} given the OSGi context to
	 * which the bundle must be linked.
	 * 
	 * @param context
	 */
	public ModbusThreePhaseElectricityMeterDriver()
	{
		
	}
	
	public void activate(BundleContext context)
	{
		// init the logger
		this.logger = new LogHelper(context);
		
		// store the context
		this.context = context;
		
		// initialize the connected drivers list
		this.connectedDrivers = new Vector<ModbusThreePhaseElectricityMeterDriverInstance>();
		
		// try to register the service
		this.register();
	}
	
	public void deactivate()
	{
		// log deactivation
		this.logger.log(LogService.LOG_DEBUG, ModbusThreePhaseElectricityMeterDriver.logId + " Deactivation required");
		
		// unregister from the network driver
		for (ModbusThreePhaseElectricityMeterDriverInstance instance : this.connectedDrivers)
			this.network.removeDriver(instance);
		
		// unregister
		this.unRegister();
		
		// null the inner data structures
		this.context = null;
		this.logger = null;
		this.network = null;
		this.gateway = null;
		this.connectedDrivers = null;
	}
	
	/**
	 * Handles the "availability" of a Modbus network driver (store a reference
	 * to the driver and try to start).
	 * 
	 * @param netDriver
	 *            The available {@link ModbusNetwork} driver service.
	 */
	public void addedNetworkDriver(ModbusNetwork netDriver)
	{
		// store a reference to the network driver
		this.network = netDriver;
		
		// try to start service offering
		this.register();
	}
	
	/**
	 * Handles the removal of the connected network driver by unregistering the
	 * services provided by this driver
	 */
	public void removedNetworkDriver(ModbusNetwork netDriver)
	{
		// un-register this service
		this.unRegister();
		
		// null the reference to the network driver
		this.network = null;
	}
	
	/**
	 * Handles the "availability" of a Modbus gateway driver (store a reference
	 * to the driver and try to start).
	 * 
	 * @param gwDriver
	 *            The available {@link ModbusGatewayDriver} service.
	 */
	public void addedGatewayDriver(ModbusGatewayDriver gwDriver)
	{
		// store a reference to the gateway driver
		this.gateway = gwDriver;
		
		// try to start service offering
		this.register();
	}
	
	/**
	 * Handles the removal of the connected network driver by unregistering the
	 * services provided by this driver
	 */
	public void removedGatewayDriver(ModbusGatewayDriver gwDriver)
	{
		// un-register this service
		this.unRegister();
		
		// null the reference to the network driver
		this.gateway = null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int match(ServiceReference reference) throws Exception
	{
		int matchValue = Device.MATCH_NONE;
		
		if (this.regDriver != null)
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
					if (manifacturer != null
							&& (gateway != null)
							&& (this.gateway != null)
							&& (manifacturer.equals(ModbusInfo.MANUFACTURER))
							&& (deviceCategory.equals(ThreePhaseElectricityMeter.class.getName()) && (this.gateway
									.isGatewayAvailable(gateway))
							
							))
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
		if (this.regDriver != null)
		{
			// get the gateway to which the device is connected
			String gateway = (String) ((ControllableDevice) this.context.getService(reference)).getDeviceDescriptor()
					.getGateway();
			
			// create a new driver instance
			ModbusThreePhaseElectricityMeterDriverInstance driverInstance = new ModbusThreePhaseElectricityMeterDriverInstance(
					network, (ControllableDevice) this.context.getService(reference), this.gateway.getSpecificGateway(
							gateway).getGatewayAddress(), this.gateway.getSpecificGateway(gateway).getGatewayPort(),
					this.gateway.getSpecificGateway(gateway).getGwProtocol(), this.context);
			
			((ControllableDevice) context.getService(reference)).setDriver(driverInstance);
			
			synchronized (this.connectedDrivers)
			{
				this.connectedDrivers.add(driverInstance);
			}
		}
		return null;
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
			propDriver.put(DeviceCostants.DRIVER_ID, ModbusThreePhaseElectricityMeterDriver.class.getName());
			
			// register this driver in the OSGi framework
			this.regDriver = this.context.registerService(Driver.class.getName(), this, propDriver);
		}
		
	}
	
	/**
	 * Unregisters this driver from the OSGi framework...
	 */
	public void unRegister()
	{
		// un-registers this driver, if registered
		if (this.regDriver != null)
		{
			this.regDriver.unregister();
			this.regDriver = null;
		}
	}
	
}
