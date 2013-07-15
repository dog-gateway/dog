/*
 * Dog 2.0 - Gateway Driver
 * 
 * Copyright [2012] 
 * [Dario Bonino (dario.bonino@polito.it), Politecnico di Torino] 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package it.polito.elite.dog.drivers.knx.gateway;

import it.polito.elite.dog.drivers.knx.network.info.KnxIPInfo;
import it.polito.elite.dog.drivers.knx.network.interfaces.KnxIPNetwork;
import it.polito.elite.domotics.dog2.doglibrary.DogDeviceCostants;
import it.polito.elite.domotics.dog2.doglibrary.devicecategory.ControllableDevice;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;
import it.polito.elite.domotics.model.devicecategory.KNXNetIPGateway;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.device.Device;
import org.osgi.service.device.Driver;
import org.osgi.service.log.LogService;

/**
 * A class implementing the functionalities of a generic KnxIPNet gateway, as
 * modeled in DogOnt. It offers ways to trace the number of currently managed
 * gateways, this permits multiple-gateway operation in Dog.
 * 
 * Currently no gateway-specific functions are available, however in future
 * releases functionalities offered by the real devices will be modeled and
 * implemented here.
 * 
 * @author bonino
 * 
 */
public class KnxIPGatewayDriver implements Driver
{
	// The OSGi framework context
	protected BundleContext context;
	
	// System logger
	LogService logger;
	
	// the log identifier, unique for the class
	public static String logId = "[KnxIPGatewayDriver]: ";
	
	// a reference to the network driver (currently not used by this driver
	// version, in the future it will be used to implement gateway-specific
	// functionalities).
	private KnxIPNetwork network;
	
	// the registration object needed to handle the life span of this bundle in
	// the OSGi framework (it is a ServiceRegistration object for use by the
	// bundle registering the service to update the service's properties or to
	// unregister the service).
	private ServiceRegistration<?> regDriver;
	
	// the registration object needed to handle the life span of this bundle in
	// the OSGi framework (it is a ServiceRegistration object for use by the
	// bundle registering the service to update the service's properties or to
	// unregister the service).
	// Defines this class as a KnxIPGateway
	private ServiceRegistration<?> regKnxIPGateway;
	
	// the set of currently connected gateways... indexed by their ids
	private Map<String, KnxIPGatewayDriverInstance> connectedGateways;
	
	public KnxIPGatewayDriver()
	{
		// initialize the map of connected gateways
		this.connectedGateways = new ConcurrentHashMap<String, KnxIPGatewayDriverInstance>();
	}
	
	public void activate(BundleContext bundleContext)
	{
		// init the logger
		this.logger = new DogLogInstance(context);
		
		// store the context
		this.context = bundleContext;
		
		// if ready, try to register offered services
		if ((this.network != null) && (this.regDriver == null) && (this.context != null))
			this.registerDriver();
	}
	
	public void unRegister()
	{
		// un-registers this driver
		if (this.regDriver != null)
		{
			this.regDriver.unregister();
			this.regDriver = null;
		}
		
		// un-register the gateway service
		if (this.regKnxIPGateway != null)
		{
			this.regKnxIPGateway.unregister();
			this.regKnxIPGateway = null;
		}
		
	}
	
	public void addedNetworkService(KnxIPNetwork network)
	{
		// store a reference to the network service
		this.network = network;
		
		// if ready, try to register offered services
		this.registerDriver();
	}
	
	public void removedNetworkService(KnxIPNetwork network)
	{
		// unregisters this driver from the OSGi framework
		this.unRegister();
		
		// null the network reference
		this.network = null;
	}
	
	private void registerDriver()
	{
		if ((this.network != null) && (this.regDriver == null) && (this.context != null))
		{
			Hashtable<String, Object> propDriver = new Hashtable<String, Object>();
			propDriver.put(DogDeviceCostants.DRIVER_ID, "KnxIP_KnxIPGateway_driver");
			propDriver.put(DogDeviceCostants.GATEWAY_COUNT, this.connectedGateways.size());
			this.regDriver = this.context.registerService(Driver.class.getName(), this, propDriver);
			this.regKnxIPGateway = this.context.registerService(KnxIPGatewayDriver.class.getName(), this, null);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int match(ServiceReference reference) throws Exception
	{
		int matchValue = Device.MATCH_NONE;
		
		if ((this.regDriver != null) && (this.regKnxIPGateway != null))
		{
			// get the given device category
			String deviceCategory = (String) reference.getProperty(DogDeviceCostants.DEVICE_CATEGORY);
			
			// get the given device manufacturer
			String manufacturer = (String) reference.getProperty(DogDeviceCostants.MANUFACTURER);
			
			// compute the matching score between the given device and this
			// driver
			if (deviceCategory != null)
			{
				if (manufacturer != null && manufacturer.equals(KnxIPInfo.MANUFACTURER)
						&& (deviceCategory.equals(KNXNetIPGateway.class.getName())
						
						))
				{
					matchValue = KNXNetIPGateway.MATCH_MANUFACTURER + KNXNetIPGateway.MATCH_TYPE;
				}
				
			}
		}
		return matchValue;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String attach(ServiceReference reference) throws Exception
	{
		if ((this.regDriver != null) && (this.regKnxIPGateway != null))
		{
			// get the corresponding end point set
			
			Set<String> gatewayAddressSet = ((ControllableDevice) this.context.getService(reference))
					.getDeviceDescriptor().getDevSimpleConfigurationParams().get(KnxIPInfo.GATEWAY_ADDRESS);
			
			String deviceId = ((ControllableDevice) this.context.getService(reference)).getDeviceId();
			
			// if not null, it is a singleton
			if (gatewayAddressSet != null)
			{
				// get the endpoint address of the connecting gateway
				String gatewayAddress = gatewayAddressSet.iterator().next();
				
				// check not null
				if ((gatewayAddress != null) && (!gatewayAddress.isEmpty()) && !this.isGatewayAvailable(deviceId))
				{
					// create a new instance of the gateway driver
					KnxIPGatewayDriverInstance driver = new KnxIPGatewayDriverInstance(this.network,
							(ControllableDevice) this.context.getService(reference), gatewayAddress, this.context);
					
					synchronized (this.connectedGateways)
					{
						// store a reference to the gateway driver
						this.connectedGateways.put(deviceId, driver);
					}
					
					// modify the service description causing a forcing the
					// framework to send a modified service notification
					final Hashtable<String, Object> propDriver = new Hashtable<String, Object>();
					propDriver.put(DogDeviceCostants.DRIVER_ID, "KnxIP_KnxIPGateway_driver");
					propDriver.put(DogDeviceCostants.GATEWAY_COUNT, this.connectedGateways.size());
					
					this.regDriver.setProperties(propDriver);
				}
				else
				{
					// do not attach, log and throw exception
					this.logger
							.log(LogService.LOG_WARNING,
									KnxIPGatewayDriver.logId
											+ "Unable to get the current gateway address (empty set), this prevents the device from being attached!");
					throw new Exception(
							KnxIPGatewayDriver.logId
									+ "Unable to get the current gateway address, this prevents the device from being attached!");
				}
			}
			else
			{
				// do not attach, log and throw exception
				this.logger
						.log(LogService.LOG_WARNING,
								KnxIPGatewayDriver.logId
										+ "Unable to get the current gateway address (missing parameter), this prevents the device from being attached!");
				throw new Exception(KnxIPGatewayDriver.logId
						+ "Unable to get the current gateway address, this prevents the device from being attached!");
			}
		}
		
		return null;
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
	 * associated with the KnxIP gateway device having the given id.
	 * 
	 * @param gatewayId
	 * @return
	 */
	public KnxIPGatewayDriverInstance getSpecificGateway(String gatewayId)
	{
		return this.connectedGateways.get(gatewayId);
	}
	
	/**
	 * @return the network
	 */
	public KnxIPNetwork getNetwork()
	{
		return network;
	}
	
}
