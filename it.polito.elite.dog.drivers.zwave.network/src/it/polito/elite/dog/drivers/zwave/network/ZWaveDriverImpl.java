/*
 * Dog - Network Driver
 * 
 * Copyright 2013-2014 Davide Aimone and Dario Bonino 
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
package it.polito.elite.dog.drivers.zwave.network;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.drivers.zwave.ZWaveAPI;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Controller;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Device;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Instance;
import it.polito.elite.dog.drivers.zwave.model.zway.json.ZWaveModelTree;
import it.polito.elite.dog.drivers.zwave.network.info.ZWaveNodeInfo;
import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;
import it.polito.elite.dog.drivers.zwave.util.ConnessionManager;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.Version;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.log.LogService;

public class ZWaveDriverImpl implements ZWaveNetwork, ManagedService
{
	// the log identifier, unique for the class
	public static String LOG_ID = "[ZWaveDriverImpl]: ";

	// the bundle context
	private BundleContext bundleContext;

	// the service registration handle
	private ServiceRegistration<?> regServiceZWaveDriverImpl;

	// the driver logger
	private LogHelper logger;

	// connession maanger used to deal with zway server
	private ConnessionManager conManager;

	// model tree representing the system
	private ZWaveModelTree modelTree;

	// the register to driver map
	private ConcurrentHashMap<ZWaveNodeInfo, ZWaveDriverInstance> nodeInfo2Driver;

	// the inverse map
	private ConcurrentHashMap<ZWaveDriverInstance, Set<ZWaveNodeInfo>> driver2NodeInfo;

	// the zwave poller
	private ZWavePoller poller;

	// the baseline pollingTime adopted if no server-specific setting is given
	private int pollingTimeMillis = 5000; // default value

	private Version version;

	public ZWaveDriverImpl()
	{

	}

	@Override
	public void updated(Dictionary<String, ?> properties)
			throws ConfigurationException
	{
		// get the bundle configuration parameters
		if (properties != null)
		{
			logger.log(LogService.LOG_DEBUG,
					"Received configuration properties");

			// Get credentials for connection
			String sUrl = (String) properties.get("loginUrl");

			// try to get the baseline polling time
			String pollingTimeAsString = (String) properties
					.get("pollingTimeMillis");

			// trim leading and trailing spaces
			pollingTimeAsString = pollingTimeAsString.trim();

			// check not null
			if (pollingTimeAsString != null)
			{
				// parse the string
				pollingTimeMillis = Integer.valueOf(pollingTimeAsString);
			}

			// Get the connession manager and try to connect to the server
			conManager = ConnessionManager.get(sUrl, // sUsername, sPassword,
					bundleContext);

			// in any case, as the polling time has a default, init the poller
			// thread and start it
			poller = new ZWavePoller(this);

			// start the poller
			poller.start();

			// log the driver start
			logger.log(LogService.LOG_INFO, ZWaveDriverImpl.LOG_ID
					+ "Started the driver poller thread...");

			// register the driver service if not already registered
			if (regServiceZWaveDriverImpl == null)
				regServiceZWaveDriverImpl = bundleContext.registerService(
						ZWaveNetwork.class.getName(), this, null);

		}
	}

	/**
	 * Handle the bundle activation
	 */
	protected void activate(BundleContext bundleContext)
	{
		// create a logger
		logger = new LogHelper(bundleContext);

		// store the bundle context
		this.bundleContext = bundleContext;

		logger.log(LogService.LOG_DEBUG, "Activated: ZWave NetworkDriver...");

		// create the register to driver map
		nodeInfo2Driver = new ConcurrentHashMap<ZWaveNodeInfo, ZWaveDriverInstance>();

		// create the driver to register map
		driver2NodeInfo = new ConcurrentHashMap<ZWaveDriverInstance, Set<ZWaveNodeInfo>>();
	}

	/**
	 * Handle the bundle de-activation
	 */
	protected void deactivate()
	{
		// stop the poller
		if (poller != null)
		{
			poller.setRunnable(false);
		}

		// unregister service
		if (regServiceZWaveDriverImpl != null)
			regServiceZWaveDriverImpl.unregister();
	}

	@Override
	public void read(ZWaveNodeInfo nodeInfo, boolean bRequery)
	{
		if (conManager != null)
		{
			try
			{
				// if needed update tree model
				if (bRequery || modelTree == null)
					modelTree = conManager.updateDevices();

				Device deviceNode = null;
				Instance instanceNode = null;
				Controller controllerNode = null;

				// if node is the controller (gateway) we have to put also
				// controller data.
				if (nodeInfo.isController())
					controllerNode = modelTree.getController();

				deviceNode = modelTree.getDevices().get(
						nodeInfo.getDeviceNodeId());
				Device device = modelTree.getDevices().get(
						nodeInfo.getDeviceNodeId());
				// device can be null if house xml configuration is wrong
				if (device != null)
				{
					for (Integer instanceId : nodeInfo.getInstanceSet())
					{
						instanceNode = device.getInstances().get(instanceId);

						// instance can be null if house xml configuration is
						// wrong
						if (instanceNode != null)
						{
							ZWaveDriverInstance driver = nodeInfo2Driver
									.get(nodeInfo);
							driver.newMessageFromHouse(deviceNode,
									instanceNode, controllerNode, null);
						}
						else
						{
							logger.log(
									LogService.LOG_ERROR,
									LOG_ID + "Device id: "
											+ nodeInfo.getDeviceNodeId()
											+ " instance id: " + instanceId
											+ " does not exists!");
						}
					}
				}
				else
				{
					logger.log(LogService.LOG_ERROR, LOG_ID + "Device id: "
							+ nodeInfo.getDeviceNodeId() + " does not exists!");
				}
			}
			catch (Exception e)
			{
				logger.log(LogService.LOG_ERROR, LOG_ID, e);
				e.printStackTrace();
			}
		}
	}

	@Override
	public void readAll(boolean bRequery)
	{
		if (conManager != null)
		{
			try
			{
				// if needed update tree model
				if (bRequery || modelTree == null)
					modelTree = conManager.updateDevices();

				// if version still not available
				if (this.version == null)
				{
					String versionAsString = (String) this.modelTree.getController()
							.getData().getAllData()
							.get("softwareRevisionVersion").getValue();
					versionAsString = versionAsString.trim().substring(1);
					this.version = new Version(versionAsString);
					logger.log(LogService.LOG_INFO, "ZWay version:" + this.version);
				}

				for (Set<ZWaveNodeInfo> nodeInfos : driver2NodeInfo.values())
				{
					for (ZWaveNodeInfo nodeInfo : nodeInfos)
					{
						read(nodeInfo, false);
						// yield to other processes
						Thread.yield();
					}
				}
			}
			catch (Exception e)
			{
				logger.log(LogService.LOG_ERROR, LOG_ID, e);

			}
		}
	}

	@Override
	public void updateSensor(ZWaveNodeInfo nodeInfo)
	{
		// check if the node info is registered...
		if (this.nodeInfo2Driver.containsKey(nodeInfo))
		{
			// send one update command per each command class
			for (Entry<Integer, Set<Integer>> instanceCC : nodeInfo
					.getInstanceSensorCC().entrySet())
			{
				for (Integer ccToTrigger : instanceCC.getValue())
				{
					try
					{
						// check if the model still contains the device: in some
						// cases device removal at the z-wave network-level may
						// happen before than the time in which the gateway
						// driver detects it.
						if (modelTree.getDevices().containsKey(
								nodeInfo.getDeviceNodeId()))
						{
							conManager.sendCommand("devices["
									+ nodeInfo.getDeviceNodeId()
									+ "].instances[" + instanceCC.getKey()
									+ "].commandClasses[" + ccToTrigger
									+ "].Get()");
						}
					}
					catch (Exception e)
					{
						logger.log(LogService.LOG_ERROR, LOG_ID
								+ "Can't send command", e);

					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.drivers.zwave.networkdriver.interfaces.ZWaveNetwork
	 * #addDriver(it.polito.elite.dog.drivers.zwave.networkdriver.info.
	 * ZWaveNodeInfo)
	 */
	@Override
	public void addDriver(ZWaveNodeInfo nodeInfo, int updateTimeMillis,
			ZWaveDriverInstance driver)
	{
		// get the register gateway address
		int deviceNodeId = nodeInfo.getDeviceNodeId();
		Set<Integer> lstInstanceNodeId = nodeInfo.getInstanceSet();

		// info on port usage
		logger.log(LogService.LOG_INFO,
				LOG_ID + "Using deviceId: " + deviceNodeId + " instancesId: "
						+ lstInstanceNodeId.toString());

		// adds a given register-driver association
		nodeInfo2Driver.put(nodeInfo, driver);

		// fills the reverse map
		Set<ZWaveNodeInfo> driverRegisters = driver2NodeInfo.get(driver);
		if (driverRegisters == null)
		{
			// create the new set of registers associated to the given driver
			driverRegisters = new HashSet<ZWaveNodeInfo>();
			driver2NodeInfo.put(driver, driverRegisters);
		}
		driverRegisters.add(nodeInfo);

		// add a new device to the thread that is the responsible for device
		// update
		poller.addDeviceToQueue(nodeInfo, updateTimeMillis);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.drivers.zwave.networkdriver.interfaces.ZWaveNetwork
	 * #removeDriver(it.polito.elite.dog.drivers.zwave.networkdriver.info.
	 * ZWaveNodeInfo)
	 */
	@Override
	public void removeDriver(ZWaveNodeInfo nodeInfo)
	{
		// removes a given register-driver association
		ZWaveDriverInstance drv = nodeInfo2Driver.remove(nodeInfo);

		if (drv != null)
		{
			driver2NodeInfo.remove(drv);
		}
	}

	@Override
	public void removeDriver(int nodeId)
	{
		ZWaveNodeInfo infoToRemove = null;
		// look for nodeinfos
		for (ZWaveNodeInfo info : this.nodeInfo2Driver.keySet())
		{
			// compare the node id, if they are equal, get the driver and ask
			// for the device uri
			if (info.getDeviceNodeId() == nodeId)
			{
				infoToRemove = info;
				break;
			}
		}

		if (infoToRemove != null)
		{
			this.poller.removeDeviceFromQueue(nodeId);
			this.removeDriver(infoToRemove);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.drivers.zwave.networkdriver.interfaces.ZWaveNetwork
	 * #removeDriver(it.polito.elite.dog.drivers.zwave.networkdriver.info.
	 * ZWaveDriver)
	 */
	@Override
	public void removeDriver(ZWaveDriverInstance driver)
	{
		// removes a given driver-register association
		Set<ZWaveNodeInfo> driverNodeInfo = driver2NodeInfo.remove(driver);

		// remove the register-to-driver and the server-to-register associations
		if (driverNodeInfo != null)
		{
			for (ZWaveNodeInfo register : driverNodeInfo)
			{
				// remove the datapoint-to-driver associations
				nodeInfo2Driver.remove(register);
			}
		}
	}

	@Override
	public void write(int deviceId, int instanceId, int nCommandClass,
			String commandValue)
	{
		try
		{
			//version > 1.3.1 patch
			if(this.version.compareTo(new Version("1.3.1")) > 0)
			{
				if(nCommandClass == ZWaveAPI.GENERIC_TYPE_SWITCH_BINARY)
				{
					if(commandValue.equals("255"))
						commandValue = "true";
					else
						commandValue = "false";
				}
			}

			conManager.sendCommand("devices[" + deviceId + "].instances["
					+ instanceId + "].commandClasses[" + nCommandClass
					+ "].Set(" + commandValue + ")");
		}
		catch (Exception e)
		{
			logger.log(LogService.LOG_ERROR, LOG_ID + "Can't send command", e);

		}

	}

	@Override
	public void controllerWrite(String sCommand, String commandValue)
	{
		try
		{
			conManager.sendCommand(sCommand + "(" + commandValue + ")");
		}
		catch (Exception e)
		{
			logger.log(LogService.LOG_ERROR, LOG_ID + "Can't send command", e);

		}
	}

	/**
	 * Provides a reference to the {@link LogService} instance used by this
	 * class to log messages...
	 * 
	 * @return
	 */
	public LogHelper getLogger()
	{
		return logger;
	}

	/**
	 * Provides the polling time to be used by Poller threads connect to this
	 * driver instance...
	 * 
	 * @return
	 */
	@Override
	public long getPollingTimeMillis()
	{
		return pollingTimeMillis;
	}

	/**
	 * Get all devices currently available on the network, including devices
	 * that still have to be configured in Dog
	 */
	@Override
	public Map<Integer, Device> getRawDevices()
	{
		return this.modelTree.getDevices();
	}

	/**
	 * Get raw device data on the basis of the given nodeId
	 * 
	 * @return
	 */
	public Device getRawDevice(int nodeId)
	{
		return this.modelTree.getDevices().get(new Integer(nodeId));
	}

	/**
	 * checks among currently handled device whether the given node id exists,
	 * and in the case, extracts the corresponding device URI
	 * 
	 * @param nodeId
	 * @return The URI of the corresponding {@link ControllableDevice}
	 */
	@Override
	public String getControllableDeviceURIFromNodeId(int nodeId)
	{
		String deviceId = null;

		// look for nodeinfos
		for (ZWaveNodeInfo info : this.nodeInfo2Driver.keySet())
		{
			// compare the node id, if they are equal, get the driver and ask
			// for the device uri
			if (info.getDeviceNodeId() == nodeId)
			{
				// the driver currently connected to the device
				ZWaveDriverInstance driver = this.nodeInfo2Driver.get(info);

				// get the device uri
				deviceId = driver.getDevice().getDeviceId();

				// break the cycle if the needed information has been found
				break;
			}
		}

		return deviceId;
	}

}
