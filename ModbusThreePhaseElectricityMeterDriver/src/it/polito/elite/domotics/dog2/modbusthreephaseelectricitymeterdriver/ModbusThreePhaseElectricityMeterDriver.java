/*
 * Dog 2.0 - Modbus Device Driver
 * 
 * Copyright [2012] 
 * [Dario Bonino (dario.bonino@polito.it), Politecnico di Torino] 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package it.polito.elite.domotics.dog2.modbusthreephaseelectricitymeterdriver;

import it.polito.elite.domotics.dog2.doglibrary.DogDeviceCostants;
import it.polito.elite.domotics.dog2.doglibrary.devicecategory.ControllableDevice;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;
import it.polito.elite.domotics.dog2.modbusgatewaydriver.ModbusGatewayDriver;
import it.polito.elite.domotics.dog2.modbusnetworkdriver.info.ModbusInfo;
import it.polito.elite.domotics.dog2.modbusnetworkdriver.interfaces.ModbusNetwork;
import it.polito.elite.domotics.model.devicecategory.ThreePhaseElectricityMeter;

import java.util.Hashtable;
import java.util.Vector;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
//import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.device.Device;
import org.osgi.service.device.Driver;
import org.osgi.service.log.LogService;
//import org.osgi.util.tracker.ServiceTracker;
//import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author bonino
 * 
 */
public class ModbusThreePhaseElectricityMeterDriver implements Driver// ,
																		// ServiceTrackerCustomizer<Object,
																		// Object>
{
	// The OSGi framework context
	protected BundleContext context;
	
	// System logger
	LogService logger;
	
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
	
	// the filter query for listening to framework events relative to the
	// to the Modbus gateway driver
	String filterQuery = String.format("(%s=%s)", Constants.OBJECTCLASS, ModbusGatewayDriver.class.getName());
	
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
		this.logger = new DogLogInstance(context);
		
		// store the context
		this.context = context;
		
		// initialize the connected drivers list
		this.connectedDrivers = new Vector<ModbusThreePhaseElectricityMeterDriverInstance>();
		
		//try to register the service
		this.register();
		
		// start the service tracker...
		/*
		 * try { // create the service tracker ServiceTracker<?,?> st = new
		 * ServiceTracker<Object, Object>(context,
		 * this.context.createFilter(filterQuery), this);
		 * 
		 * // open the tracker st.open(); } catch (InvalidSyntaxException e) {
		 * this.logger .log(LogService.LOG_ERROR,
		 * ModbusThreePhaseElectricityMeterDriver.logId +
		 * " wrong syntax in the LDAP filter specified for getting an instance of ModbusNetwork and of ModbusGatewayDriver\nNested Exception: "
		 * , e); }
		 */
	}
	
	public void deactivate()
	{
		this.unRegister();
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
	public void removedNetworkDriver()
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
	public void removedGatewayDriver()
	{
		// un-register this service
		this.unRegister();
		
		// null the reference to the network driver
		this.gateway = null;
	}
	
	/*
	 * @Override public Object addingService(ServiceReference<Object> reference)
	 * { // chek the reference type this.gateway = (ModbusGatewayDriver)
	 * this.context.getService(reference);
	 * 
	 * this.network = this.gateway.getNetwork(); if ((this.gateway != null) &&
	 * (this.network != null)) this.register();
	 * 
	 * return null; }
	 * 
	 * @Override public void modifiedService(ServiceReference<Object> reference,
	 * Object service) { // empty - modification of tracked services are not
	 * handled }
	 * 
	 * @Override public void removedService(ServiceReference<Object> reference,
	 * Object service) { // remove the service from the OSGi framework
	 * this.unRegister(); }
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public int match(ServiceReference reference) throws Exception
	{
		int matchValue = Device.MATCH_NONE;
		
		// get the given device category
		String deviceCategory = (String) reference.getProperty(DogDeviceCostants.DEVICE_CATEGORY);
		
		// get the given device manufacturer
		String manifacturer = (String) reference.getProperty(DogDeviceCostants.MANUFACTURER);
		
		// get the gateway to which the device is connected
		@SuppressWarnings("unchecked")
		String gateway = (String) ((ControllableDevice) this.context.getService(reference)).getDeviceDescriptor()
				.getGateway();
		
		// compute the matching score between the given device and this driver
		if (deviceCategory != null)
		{
			if (manifacturer != null
					&& (gateway != null)
					&& (manifacturer.equals(ModbusInfo.MANUFACTURER))
					&& (deviceCategory.equals(ThreePhaseElectricityMeter.class.getName()) && (this.gateway
							.isGatewayAvailable(gateway))
					
					))
			{
				matchValue = ThreePhaseElectricityMeter.MATCH_MANUFACTURER + ThreePhaseElectricityMeter.MATCH_TYPE;
			}
			
		}
		return matchValue;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String attach(ServiceReference reference) throws Exception
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
			propDriver.put(DogDeviceCostants.DRIVER_ID, "Modbus_ModbusThreePhaseElectricityMeter_driver");
			
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
