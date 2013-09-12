package it.polito.elite.dog.drivers.zwave.gateway;

import it.polito.elite.dog.drivers.zwave.network.info.ZWaveInfo;
import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;
import it.polito.elite.domotics.dog2.doglibrary.DogDeviceCostants;
import it.polito.elite.domotics.dog2.doglibrary.devicecategory.ControllableDevice;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;
import it.polito.elite.domotics.model.devicecategory.ZWaveGateway;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.device.Device;
import org.osgi.service.device.Driver;
import org.osgi.service.log.LogService;

public class ZWaveGatewayDriver implements Driver
{
	// List of available command on controller
	public static final String CMD_INCLUDE = "controller.AddNodeToNetwork";
	public static final String CMD_EXCLUDE = "controller.RemoveNodeFromNetwork";
	public static final String CMD_LEARN = "controller.SetLearnMode";
	public static final String CMD_RESET = "controller.SetDefault";
	
	// The OSGi framework context
	protected BundleContext context;
	
	// System logger
	LogService logger;
	
	// the log identifier, unique for the class
	public static final String LOG_ID = "[ZWaveGatewayDriver]: ";
	
	// String identifier for driver id
	public static final String DRIVER_ID = "ZWave_ZWaveGateway_driver";
	
	// a reference to the network driver (currently not used by this driver
	// version, in the future it will be used to implement gateway-specific
	// functionalities.
	private AtomicReference<ZWaveNetwork> network;
	
	// the registration object needed to handle the life span of this bundle in
	// the OSGi framework (it is a ServiceRegistration object for use by the
	// bundle registering the service to update the service's properties or to
	// unregister the service).
	private ServiceRegistration<?> regDriver;
	
	// register this driver as a gateway used by device-specific drivers
	private ServiceRegistration<?> regZWaveGateway;
	
	// the set of currently connected gateways... indexed by their ids
	private Map<String, ZWaveGatewayDriverInstance> connectedGateways;
	
	// the LDAP query used to match the ModbusNetworkDriver
	String filterQuery = String.format("(%s=%s)", Constants.OBJECTCLASS, ZWaveNetwork.class.getName());
	
	public ZWaveGatewayDriver()
	{
		// initialize the map of connected gateways
		connectedGateways = new ConcurrentHashMap<String, ZWaveGatewayDriverInstance>();
		network = new AtomicReference<ZWaveNetwork>();
	}
	
	/**
	 * Handle the bundle activation
	 */
	public void activate(BundleContext bundleContext)
	{
		// store the context
		context = bundleContext;
		
		// init the logger
		logger = new DogLogInstance(context);
		
		registerDriver();
	}
	
	public void deactivate()
	{
		// remove the service from the OSGi framework
		this.unRegister();
	}
	
	public void addingService(ZWaveNetwork networkDriver)
	{
		network.set(networkDriver);
		
	}
	
	public void removedService(ZWaveNetwork networkDriver)
	{
		if (network.compareAndSet(networkDriver, null))
			// unregisters this driver from the OSGi framework
			unRegister();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int match(ServiceReference reference) throws Exception
	{
		int matchValue = Device.MATCH_NONE;
		
		// get the given device category
		String deviceCategory = (String) reference.getProperty(DogDeviceCostants.DEVICE_CATEGORY);
		
		// get the given device manufacturer
		String manufacturer = (String) reference.getProperty(DogDeviceCostants.MANUFACTURER);
		
		// compute the matching score between the given device and this driver
		if (deviceCategory != null)
		{
			if (manufacturer != null && manufacturer.equals(ZWaveInfo.MANUFACTURER)
					&& (deviceCategory.equals(ZWaveGateway.class.getName())))
			{
				matchValue = ZWaveGateway.MATCH_MANUFACTURER + ZWaveGateway.MATCH_TYPE;
			}
			
		}
		return matchValue;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public String attach(ServiceReference reference) throws Exception
	{
		// get the corresponding end point set
		@SuppressWarnings("unchecked")
		Set<String> gatewayNodeIdSet = ((ControllableDevice) context.getService(reference)).getDeviceDescriptor()
				.getDevSimpleConfigurationParams().get(ZWaveInfo.NODE_ID);
		
		@SuppressWarnings("unchecked")
		String deviceId = ((ControllableDevice) context.getService(reference)).getDeviceId();
		
		// if not null, it is a singleton
		if (gatewayNodeIdSet != null)
		{
			// get the endpoint address of the connecting gateway
			String sNodeID = gatewayNodeIdSet.iterator().next();
			
			// check not null
			if ((sNodeID != null) && (!sNodeID.isEmpty()))
			{
				if (!this.isGatewayAvailable(deviceId))
				{
					// gateway has only one instance and its id is 0
					Set<Integer> instancesId = new HashSet<Integer>();
					instancesId.add(0);
					
					// create a new instance of the gateway driver
					@SuppressWarnings("unchecked")
					ZWaveGatewayDriverInstance driver = new ZWaveGatewayDriverInstance(network.get(),
							(ControllableDevice) context.getService(reference), Integer.parseInt(sNodeID),
							instancesId, context);
					
					synchronized (connectedGateways)
					{
						// store a reference to the gateway driver
						connectedGateways.put(deviceId, driver);
					}
					
					// modify the service description causing a forcing the
					// framework to send a modified service notification
					final Hashtable<String, Object> propDriver = new Hashtable<String, Object>();
					propDriver.put(DogDeviceCostants.DRIVER_ID, DRIVER_ID);
					propDriver.put(DogDeviceCostants.GATEWAY_COUNT, connectedGateways.size());
					
					regDriver.setProperties(propDriver);
				}
			}
			else
			{
				// do not attach, log and throw exception
				logger.log(
						LogService.LOG_WARNING,
						LOG_ID
								+ "Unable to get the current gateway node id (empty set), this prevents the device from being attached!");
				throw new Exception(LOG_ID
						+ "Unable to get the current gateway node id, this prevents the device from being attached!");
			}
		}
		else
		{
			// do not attach, log and throw exception
			logger.log(
					LogService.LOG_WARNING,
					LOG_ID
							+ "Unable to get the current gateway node id (missing parameter), this prevents the device from being attached!");
			throw new Exception(LOG_ID
					+ "Unable to get the current gateway node id, this prevents the device from being attached!");
		}
		
		return null;
	}
	
	/**
	 * Handle the bundle de-activation
	 */
	protected void unRegister()
	{
		// un-registers this driver
		if (regDriver != null)
		{
			regDriver.unregister();
			regDriver = null;
		}
		
		// un-register the gateway service
		if (regZWaveGateway != null)
		{
			regZWaveGateway.unregister();
			regZWaveGateway = null;
		}
	}
	
	/**
	 * Registers this driver in the OSGi framework, making its services
	 * available to all the other bundles living in the same or in connected
	 * frameworks.
	 */
	private void registerDriver()
	{
		if ((network.get() != null) && (this.context != null) && (this.regDriver == null))
		{
			Hashtable<String, Object> propDriver = new Hashtable<String, Object>();
			propDriver.put(DogDeviceCostants.DRIVER_ID, DRIVER_ID);
			propDriver.put(DogDeviceCostants.GATEWAY_COUNT, connectedGateways.size());
			regDriver = context.registerService(Driver.class.getName(), this, propDriver);
			regZWaveGateway = context.registerService(ZWaveGatewayDriver.class.getName(), this, null);
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
		return connectedGateways.containsKey(gatewayId);
	}
	
	/**
	 * Returns a live reference to the specific gateway driver instance
	 * associated with the ZWave gateway device having the given id.
	 * 
	 * @param gatewayId
	 * @return
	 */
	public ZWaveGatewayDriverInstance getSpecificGateway(String gatewayId)
	{
		return connectedGateways.get(gatewayId);
	}
	
	public ZWaveNetwork getNetwork()
	{
		return network.get();
	}
}
