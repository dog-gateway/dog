/*
 * Dog - Gateway Driver
 * 
 * Copyright (c) 2012-2014 Dario Bonino and Luigi De Russis
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
package it.polito.elite.dog.drivers.echelon.ilon100.gateway;

import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.devicecategory.EchelonIlon100Gateway;
import it.polito.elite.dog.drivers.echelon.ilon100.network.info.EchelonIlonInfo;
import it.polito.elite.dog.drivers.echelon.ilon100.network.interfaces.EchelonIlon100Network;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.device.Device;
import org.osgi.service.device.Driver;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * A class implementing the functionalities of an Echelon iLon 100 device, as
 * modeled in DogOnt. It offers ways to trace the number of currently managed
 * gateways and to access the corresponding end points, this permits
 * multiple-gateway operation in Dog. Currently no gateway-specific functions
 * are available, however in future releases functionalities offered by the
 * iLon100 real device will be modeled and implemented here.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class EchelonIlon100GatewayDriver implements Driver, ServiceTrackerCustomizer<Object, Object>
{
	
	// The OSGi framework context
	protected BundleContext context;
	
	// System logger
	LogHelper logger;
	
	// the log identifier, unique for the class
	public static String logId = "[EchelonIlon100GatewayDriver]: ";
	
	// a reference to the network driver (currently not used by this driver
	// version, in the future it will be used to implement gateway-specific
	// functionalities.
	private EchelonIlon100Network network;
	
	// the registration object needed to handle the lifespan of this bundle in
	// the OSGi framework (it is a ServiceRegistration object for use by the
	// bundle registering the service to update the service's properties or to
	// unregister the service).
	private ServiceRegistration<?> regDriver;
	
	// the registration object needed to handle the life span of this bundle in
	// the OSGi framework (it is a ServiceRegistration object for use by the
	// bundle registering the service to update the service's properties or to
	// unregister the service).
	// Defines this class as a EchelonIlon100Gateway
	private ServiceRegistration<?> regEchelonIlon100Gateway;
	
	// the set of currently connected gateways... indexed by their ids
	private Map<String, EchelonIlon100GatewayDriverInstance> connectedGateways;
	
	// the LDAP query used to match the EchelonIlon100NetworkDriver
	String filterQuery = String.format("(%s=%s)", Constants.OBJECTCLASS, EchelonIlon100Network.class.getName());
	
	/**
	 * The class constructor, builds a new instance of the
	 * EchelonIlon100GatewayDriver. It tracks the services needed to enable the
	 * driver in the OSGi framework.
	 * 
	 * @param context
	 *            The context for this bundle
	 */
	public EchelonIlon100GatewayDriver(BundleContext context)
	{
		// init the logger
		this.logger = new LogHelper(context);
		
		// store the context
		this.context = context;
		
		// initialize the map of connected gateways
		this.connectedGateways = new HashMap<String, EchelonIlon100GatewayDriverInstance>();
		
		// open the service tracker to get a reference to the
		// EchelonIlon100NetworkDriver bundle...
		try
		{
			ServiceTracker<?, ?> st = new ServiceTracker<Object, Object>(context,
					this.context.createFilter(filterQuery), this);
			st.open();
		}
		catch (InvalidSyntaxException e)
		{
			this.logger
					.log(LogService.LOG_ERROR,
							EchelonIlon100GatewayDriver.logId
									+ " wrong syntax in the LDAP filter specified for getting an instance of EchelonIlon100Network\nNested Exception: ",
							e);
		}
	}
	
	/**
	 * Un-registers the driver from the framework
	 */
	public void unRegister()
	{
		// un-registers this driver
		if (this.regDriver != null)
		{
			this.regDriver.unregister();
			this.regDriver = null;
		}
		
		// un-register the gateway service
		if (this.regEchelonIlon100Gateway != null)
		{
			this.regEchelonIlon100Gateway.unregister();
			this.regEchelonIlon100Gateway = null;
		}
	}
	
	@Override
	public Object addingService(ServiceReference<Object> reference)
	{
		this.network = (EchelonIlon100Network) this.context.getService(reference);
		this.registerDriver();
		return reference;
	}
	
	/**
	 * Registers this driver in the OSGi framework, making its services
	 * available to all the other bundles living in the same or in connected
	 * frameworks.
	 */
	private void registerDriver()
	{
		Hashtable<String, Object> propDriver = new Hashtable<String, Object>();
		propDriver.put(DeviceCostants.DRIVER_ID, "Echelon_EchelonIlon100Gateway_driver");
		propDriver.put(DeviceCostants.GATEWAY_COUNT, this.connectedGateways.size());
		this.regDriver = this.context.registerService(Driver.class.getName(), this, propDriver);
		this.regEchelonIlon100Gateway = this.context.registerService(EchelonIlon100GatewayDriver.class.getName(), this,
				null);
		
	}
	
	@Override
	public void modifiedService(ServiceReference<Object> reference, Object service)
	{
		// nothing to do
		
	}
	
	@Override
	public void removedService(ServiceReference<Object> reference, Object service)
	{
		// unregisters this driver from the OSGi framework
		this.unRegister();
		
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int match(ServiceReference reference) throws Exception
	{
		int matchValue = Device.MATCH_NONE;
		
		// get the given device category
		String deviceCategory = (String) reference.getProperty(DeviceCostants.DEVICE_CATEGORY);
		
		// get the given device manufacturer
		String manufacturer = (String) reference.getProperty(DeviceCostants.MANUFACTURER);
		
		// compute the matching score between the given device and this driver
		if (deviceCategory != null)
		{
			if (manufacturer != null && manufacturer.equals(EchelonIlonInfo.MANUFACTURER)
					&& (deviceCategory.equals(EchelonIlon100Gateway.class.getName())
					
					))
			{
				matchValue = EchelonIlon100Gateway.MATCH_MANUFACTURER + EchelonIlon100Gateway.MATCH_TYPE;
			}
			
		}
		return matchValue;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String attach(ServiceReference reference) throws Exception
	{
		// get the corresponding end point set
		Set<String> endpointAddressSet = ((ControllableDevice) this.context.getService(reference))
				.getDeviceDescriptor().getSimpleConfigurationParams().get(EchelonIlonInfo.ENDPOINT_ADDRESS);
		
		String deviceId = ((ControllableDevice) this.context.getService(reference)).getDeviceId();
		
		// if not null, it is a singleton
		if (endpointAddressSet != null)
		{
			// get the endpoint address of the connecting gateway
			String endpointAddress = endpointAddressSet.iterator().next();
			
			// check not null
			if ((endpointAddress != null) && (!endpointAddress.isEmpty()) && !this.isGatewayAvailable(deviceId))
			{
				// create a new instance of the gateway driver
				EchelonIlon100GatewayDriverInstance driver = new EchelonIlon100GatewayDriverInstance(this.network,
						(ControllableDevice) this.context.getService(reference), endpointAddress, this.context);
				
				synchronized (this.connectedGateways)
				{
					// store a reference to the gateway driver
					this.connectedGateways.put(deviceId, driver);
				}
				
				// modify the service description causing a forcing the
				// framework to send a modified service notification
				final Hashtable<String, Object> propDriver = new Hashtable<String, Object>();
				propDriver.put(DeviceCostants.DRIVER_ID, "Echelon_EchelonIlon100Gateway_driver");
				propDriver.put(DeviceCostants.GATEWAY_COUNT, this.connectedGateways.size());
				
				this.regDriver.setProperties(propDriver);
				
				return null;
			}
			else
			{
				// do not attach, log and throw exception
				this.logger
						.log(LogService.LOG_WARNING,
								EchelonIlon100GatewayDriver.logId
										+ "Unable to get the current gateway endpoint address (empty set), this prevents the device from being attached!");
				throw new Exception(
						EchelonIlon100GatewayDriver.logId
								+ "Unable to get the current gateway endpoint address, this prevents the device from being attached!");
			}
		}
		else
		{
			// do not attach, log and throw exception
			this.logger
					.log(LogService.LOG_WARNING,
							EchelonIlon100GatewayDriver.logId
									+ "Unable to get the current gateway endpoint address (missing parameter), this prevents the device from being attached!");
			throw new Exception(
					EchelonIlon100GatewayDriver.logId
							+ "Unable to get the current gateway endpoint address, this prevents the device from being attached!");
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
		return this.connectedGateways.containsKey(gatewayId);
	}
	
	/**
	 * Returns a live reference to the specific gateway driver instance
	 * associated with the echelon gateway device having the given id.
	 * 
	 * @param gatewayId
	 * @return
	 */
	public EchelonIlon100GatewayDriverInstance getSpecificGateway(String gatewayId)
	{
		return this.connectedGateways.get(gatewayId);
	}
	
	/**
	 * @return the network
	 */
	public EchelonIlon100Network getNetwork()
	{
		return network;
	}
	
}
