/*
 * Dog 2.0 - Device Driver
 * 
 * Copyright [2011] 
 * [Dario Bonino (dario.bonino@polito.it), Politecnico di Torino] 
 * [Luigi De Russis (luigi.derussis@polito.it), Politecnico di Torino] 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package it.polito.elite.dog.drivers.knx.onoffdevice;

import it.polito.elite.dog.drivers.knx.gateway.KnxIPGatewayDriver;
import it.polito.elite.dog.drivers.knx.gateway.KnxIPGatewayDriverInstance;
import it.polito.elite.dog.drivers.knx.network.info.KnxIPInfo;
import it.polito.elite.dog.drivers.knx.network.interfaces.KnxIPNetwork;
import it.polito.elite.domotics.dog2.doglibrary.DogDeviceCostants;
import it.polito.elite.domotics.dog2.doglibrary.devicecategory.ControllableDevice;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;
import it.polito.elite.domotics.model.devicecategory.Lamp;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.device.Device;
import org.osgi.service.device.Driver;
import org.osgi.service.log.LogService;

/**
 * The (standard) class for creating and handling the registration/removal
 * process of the driver in the OSGi framework.
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * 
 */
public class KnxIPOnOffDeviceDriver implements Driver
{
	// OSGi framework context
	BundleContext context;
	
	// System logger
	LogService logger;
	
	// the log identifier, unique for the class
	public static String logId = "[KnxIpOnOffDeviceDriver]: ";
	
	// a reference to the network driver
	private AtomicReference<KnxIPNetwork> network;
	
	// a reference to the gateway driver
	private AtomicReference<KnxIPGatewayDriver> gateway;
	
	// the registration object needed to handle the lifespan of this bundle in
	// the OSGi framework (it is a ServiceRegistration object for use by the
	// bundle registering the service to update the service's properties or to
	// unregister the service).
	private ServiceRegistration<?> regDriver;
	
	// a vector to store all the connected On/Off device drivers
	private Vector<KnxIPOnOffDeviceDriverInstance> connectedDriver;
	
	// what are the on/off device categories that can match with this driver?
	private Set<String> OnOffDeviceCategories;
	
	/**
	 * The (standard) constructor for the On/Off device driver. I
	 */
	public KnxIPOnOffDeviceDriver()
	{
		// initialize atomic references
		this.network = new AtomicReference<KnxIPNetwork>();
		this.gateway = new AtomicReference<KnxIPGatewayDriver>();
	}
	
	public void activate(BundleContext context)
	{
		// init the logger
		this.logger = new DogLogInstance(context);
		
		// store the context
		this.context = context;
		
		// initialize the connected drivers list
		this.connectedDriver = new Vector<KnxIPOnOffDeviceDriverInstance>();
		
		// initialize the set of implemented device categories
		this.OnOffDeviceCategories = new HashSet<String>();
		
		// fill the categories
		this.properFillDeviceCategories();
		
		// try to register the service
		this.register();
		
	}
	
	public void deactivate()
	{
		this.unRegister();
		this.context = null;
		this.connectedDriver.clear();
		this.connectedDriver = null;
		this.OnOffDeviceCategories.clear();
		this.OnOffDeviceCategories = null;
		this.logger = null;
	}
	
	/**
	 * Fill a set with all the device categories whose devices can match with
	 * this driver. Automatically retrieve the device categories list by reading
	 * the implemented interfaces of its DeviceDriverInstance class bundle.
	 */
	private void properFillDeviceCategories()
	{
		for (Class<?> devCat : KnxIPOnOffDeviceDriverInstance.class.getInterfaces())
		{
			this.OnOffDeviceCategories.add(devCat.getName());
		}
	}
	
	/**
	 * Try to unregister the service-related part of the driver from the OSGi
	 * framework
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
	
	/**
	 * Register the driver in the OSGi framework.
	 */
	private void register()
	{
		if ((this.network != null) && (this.gateway != null) && (this.context != null) && (this.regDriver == null))
		{
			// create a new property object describing this driver
			Hashtable<String, Object> propDriver = new Hashtable<String, Object>();
			
			// add the id of this driver to the properties
			propDriver.put(DogDeviceCostants.DRIVER_ID, "KnxIP_ONOFF_driver");
			
			// register this driver in the OSGi framework
			this.regDriver = this.context.registerService(Driver.class.getName(), this, propDriver);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.service.device.Driver#match(org.osgi.framework.ServiceReference)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int match(ServiceReference reference) throws Exception
	{
		int matchValue = Device.MATCH_NONE;
		
		if ((this.network != null) && (this.gateway != null) && (this.regDriver != null))
		{
			if (this.context.getService(reference) instanceof ControllableDevice)
			{
				// the device category for this device
				String deviceCategory = (String) reference.getProperty(DogDeviceCostants.DEVICE_CATEGORY);
				// the manufacturer
				String manufacturer = (String) reference.getProperty(DogDeviceCostants.MANUFACTURER);
				
				// get the gateway to which the device is connected
				String gateway = (String) ((ControllableDevice) this.context.getService(reference))
						.getDeviceDescriptor().getGateway();
				
				if (deviceCategory != null)
				{
					if ((manufacturer != null) && (gateway != null) && (manufacturer.equals(KnxIPInfo.MANUFACTURER))
							&& (OnOffDeviceCategories.contains(deviceCategory))
							&& (this.gateway.get().isGatewayAvailable(gateway)))
					{
						// use Lamp as a generic on/off device, for its match
						// values...
						// TODO: evaluate if it is better to store this
						// information
						// in
						// DogDeviceConstant, to be general...
						matchValue = Lamp.MATCH_MANUFACTURER + Lamp.MATCH_TYPE;
					}
					
				}
			}
		}
		return matchValue;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.service.device.Driver#attach(org.osgi.framework.ServiceReference
	 * )
	 */
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
			
			KnxIPOnOffDeviceDriverInstance instance = new KnxIPOnOffDeviceDriverInstance(this.network.get(),
					(ControllableDevice) this.context.getService(reference), gwInstance.getGatewayAddress(),
					this.context);
			
			synchronized (this.connectedDriver)
			{
				this.connectedDriver.add(instance);
			}
		}
		
		return null;
	}
	
}
