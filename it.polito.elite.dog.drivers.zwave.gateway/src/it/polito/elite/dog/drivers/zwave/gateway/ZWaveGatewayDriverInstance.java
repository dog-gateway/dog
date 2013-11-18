/*
 * Dog  - Z-Wave
 * 
 * Copyright 2013 Davide Aimone  and Dario Bonino 
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
package it.polito.elite.dog.drivers.zwave.gateway;

import it.polito.elite.dog.core.devicefactory.api.DeviceFactory;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import it.polito.elite.dog.core.library.model.DeviceDescriptorFactory;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.HomeGateway;
import it.polito.elite.dog.core.library.model.devicecategory.ZWaveGateway;
import it.polito.elite.dog.core.library.model.state.DeviceAssociationState;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.AssociatingStateValue;
import it.polito.elite.dog.core.library.model.statevalue.DisassociatingStateValue;
import it.polito.elite.dog.core.library.model.statevalue.IdleStateValue;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.drivers.zwave.ZWaveAPI;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Controller;
import it.polito.elite.dog.drivers.zwave.model.zway.json.DataConst;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Device;
import it.polito.elite.dog.drivers.zwave.model.zway.json.DeviceData;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Instance;
import it.polito.elite.dog.drivers.zwave.network.ZWaveDriver;
import it.polito.elite.dog.drivers.zwave.network.info.ZWaveNodeInfo;
import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class ZWaveGatewayDriverInstance extends ZWaveDriver implements
		ZWaveGateway
{
	// the driver logger
	LogHelper logger;

	// the log identifier, unique for the class
	public static String LOG_ID = "[ZWaveGatewayDriverInstance]: ";

	// data controller associated with the gateway
	protected Controller controller;

	// the current list of devices for which dynamic creation can be done
	private ConcurrentHashMap<String, String> supportedDevices;

	// the device factory reference
	private DeviceFactory deviceFactory;

	// the device descriptor factory reference
	private DeviceDescriptorFactory descriptorFactory;

	// last included device;
	private int lastIncludedDevice = -1;

	// last excluded device
	private int lastExcludedDevice = -1;

	// enable dynamic device detection
	private boolean detectionEnabled = false;
	
	// the time to wait before attempting automatic device detection
	private long waitBeforeDeviceInstall = 0;

	public ZWaveGatewayDriverInstance(ZWaveNetwork network,
			DeviceFactory deviceFactory, ControllableDevice controllableDevice,
			int nodeId, Set<Integer> instancesId, BundleContext context)
	{
		// gateway driver node contains always multiple instanceId, but only the
		// one with Id = 0 contains interesting data
		// also updateTimeMillis = 0 is fixed to zero because we are not
		// interested din this kind of behavior fot the gateway
		super(network, controllableDevice, nodeId, instancesId, nodeId, 0,
				context);

		// store the device factory reference
		this.deviceFactory = deviceFactory;

		// create a logger
		logger = new LogHelper(context);

		// create the device descriptor factory
		try
		{
			this.descriptorFactory = new DeviceDescriptorFactory(context
					.getBundle().getEntry("/deviceTemplates"));
		}
		catch (Exception e)
		{

			this.logger.log(LogService.LOG_ERROR,
					"Error while creating DeviceDescriptorFactory ", e);
		}

		// create a new device state (according to the current DogOnt model, no
		// state is actually associated to a Modbus gateway)
		currentState = new DeviceStatus(device.getDeviceId());

		// connect this driver instance with the device
		device.setDriver(this);

		// initialize device states
		this.initializeStates();
	}

	@Override
	public void notifyStateChanged(State newState)
	{
		// update the current state
		this.currentState.setState(
				DeviceAssociationState.class.getSimpleName(), newState);

		// debug
		logger.log(
				LogService.LOG_DEBUG,
				ZWaveGatewayDriverInstance.LOG_ID + "Device "
						+ device.getDeviceId() + " is now "
						+ (newState).getCurrentStateValue()[0].getValue());

		// call the super method
		((HomeGateway) device).notifyStateChanged(newState);
	}

	/**
	 * starts inclusion process that lasts for 20 seconds
	 */
	@Override
	public void associate()
	{
		// start inclusion mode
		network.controllerWrite(ZWaveGatewayDriver.CMD_INCLUDE, "1");
	}

	/**
	 * starts exclusion process that lasts for 20 seconds
	 */
	@Override
	public void disassociate() // TODO: remove String nodeID
	{
		// start exclusion mode
		network.controllerWrite(ZWaveGatewayDriver.CMD_EXCLUDE, "1");
	}

	/**
	 * starts learn process that lasts for 20 seconds. Learn mode is equivalent
	 * to press the button on the device (not only gateway) to start include
	 * process. Due this means that the device is not yet included in the
	 * system, this method is left empty for future purposes
	 */
	// @Override TODO: add notation
	public void learn()
	{
		// Nothing to do...
	}

	/**
	 * reset Z-Wave controller to default. NB: will completely destroy all
	 * stored data about your network!
	 */
	// @Override TODO: add notation
	public void reset()
	{
		network.controllerWrite(ZWaveGatewayDriver.CMD_RESET, "");
	}

	@Override
	public synchronized DeviceStatus getState()
	{
		return this.currentState;
	}

	@Override
	protected void specificConfiguration()
	{
		// nothing to do...
	}

	@Override
	protected void addToNetworkDriver(ZWaveNodeInfo nodeInfo)
	{
		network.addDriver(nodeInfo, 0, this);
	}

	@Override
	public void newMessageFromHouse(Device deviceNode, Instance instanceNode,
			Controller controllerNode, String sValue)
	{
		this.deviceNode = deviceNode;
		controller = controllerNode;

		/*-------------- HANDLE ASSOCIATION ------------------------*/
		// check if dynamic device detection is enabled
		if (detectionEnabled)
		{
			// check if any new device has been recently associated
			int lastIncludedDeviceAtController = controller.getData()
					.getLastIncludedDevice();
			if ((lastIncludedDeviceAtController != -1)
					// checks that the device is not the last included before
					&& (lastIncludedDeviceAtController != this.lastIncludedDevice)
					// checks that the device is not already included and
					// running
					&& (this.network
							.getControllableDeviceURIFromNodeId(lastIncludedDeviceAtController) == null)
					// checks that there are supported devices
					&& (this.supportedDevices != null)
					&& (!this.supportedDevices.isEmpty()))
			{

				// get the device data
				Device newDeviceData = this.network
						.getRawDevice(lastIncludedDeviceAtController);

				// TODO: extract data for uniquely identifying the device

				// build the device descriptor
				DeviceDescriptor descriptorToAdd = this.buildDeviceDescriptor(
						newDeviceData, lastIncludedDeviceAtController);

				// check not null
				if (descriptorToAdd != null)
				{
					// create the device
					// cross the finger
					this.deviceFactory.addNewDevice(descriptorToAdd);

					// only when the device has been created update the
					// last included device
					this.lastIncludedDevice = lastIncludedDeviceAtController;

					// disable dynamic detection until a new association is
					// detected
					this.detectionEnabled = false;

				}
			}
		}

		/*-------------- HANDLE DISASSOCIATION ------------------------*/

		// check if any new device has been recently disassociated
		int lastExcludedDeviceAtController = controller.getData()
				.getLastExcludedDevice();
		if ((lastExcludedDeviceAtController != -1)
				&& (lastExcludedDeviceAtController != this.lastExcludedDevice))
		{
			// update the last included device
			this.lastExcludedDevice = lastExcludedDeviceAtController;

			// get the device URI
			String deviceId = this.network
					.getControllableDeviceURIFromNodeId(this.lastExcludedDevice);

			// remove the device (if not null)
			if ((deviceId != null) && (!deviceId.isEmpty()))
			{
				this.deviceFactory.removeDevice(deviceId);
			}

			// remove the device association
			// TODO: this should be done by the device driver, check how to
			this.network.removeDriver(this.lastExcludedDevice);
		}

		/*-------------- HANDLE STATE ----------------------------*/
		if (this.currentState != null)
		{
			int controllerState = this.controller.getData()
					.getControllerState();

			// handle controller states
			switch (controllerState)
			{
			case 0: // idle
			{
				State currentAssociationState = this.currentState
						.getState(DeviceAssociationState.class.getSimpleName());
				if ((currentAssociationState != null)
						&& (currentAssociationState.getCurrentStateValue()[0]
								.getClass().getName()
								.equals(AssociatingStateValue.class.getName())))
				{
					// enable dynamic device detection
					this.detectionEnabled = true;
				}

				this.notifyStateChanged(new DeviceAssociationState(
						new IdleStateValue()));
				break;
			}
			case 1: // associating
			{
				this.notifyStateChanged(new DeviceAssociationState(
						new AssociatingStateValue()));

				break;
			}
			case 5: // disassociating
			{
				this.notifyStateChanged(new DeviceAssociationState(
						new DisassociatingStateValue()));
				break;
			}
			default:
			{
				break;
			}
			}
		}
	}

	@Override
	protected boolean isController()
	{
		return true;
	}

	@Override
	protected ZWaveNodeInfo createNodeInfo(int deviceId,
			Set<Integer> instancesId, boolean isController)
	{
		HashMap<Integer, Set<Integer>> instanceCommand = new HashMap<Integer, Set<Integer>>();

		// this is the gateway so we are not really interested in command class
		// for sensor data update
		HashSet<Integer> ccSet = new HashSet<Integer>();
		ccSet.add(ZWaveAPI.COMMAND_CLASS_BASIC);

		for (Integer instanceId : instancesId)
		{
			instanceCommand.put(instanceId, ccSet);
		}
		ZWaveNodeInfo nodeInfo = new ZWaveNodeInfo(deviceId, instanceCommand,
				isController);

		return nodeInfo;
	}

	/**
	 * @return the supportedDevices
	 */
	public ConcurrentHashMap<String, String> getSupportedDevices()
	{
		return supportedDevices;
	}

	/**
	 * @param supportedDevices
	 *            the supportedDevices to set
	 */
	public void setSupportedDevices(
			ConcurrentHashMap<String, String> supportedDevices)
	{
		// simplest updated policy : replacement
		this.supportedDevices = supportedDevices;

		// debug
		this.logger.log(LogService.LOG_DEBUG,
				"Updated dynamic device creation db");
	}
	
	
	/**
	 * Gets the time to wait before automatic device detection, in milliseconds
	 * @return
	 */
	public long getWaitBeforeDeviceInstall()
	{
		return waitBeforeDeviceInstall;
	}

	/**
	 * Sets the time to wait before automatic device detection, in milliseconds
	 * @param waitBeforeDeviceInstall
	 */
	public void setWaitBeforeDeviceInstall(long waitBeforeDeviceInstall)
	{
		this.waitBeforeDeviceInstall = waitBeforeDeviceInstall;
	}

	// TODO: implement better.... just a trial
	private DeviceDescriptor buildDeviceDescriptor(Device device, int nodeId)
	{
		// the device descriptor to return
		DeviceDescriptor descriptor = null;

		if (this.descriptorFactory != null)
		{

			// get the new device data
			DeviceData deviceData = device.getData();

			// get the manufacturer id
			String manufacturerId = deviceData.getAllData()
					.get(DataConst.MANUFACTURER_ID).getValue().toString();
			String manufacturerProductType = deviceData.getAllData()
					.get(DataConst.MANUFACTURER_PRODUCT_TYPE).getValue()
					.toString();
			String manufacturerProductId = deviceData.getAllData()
					.get(DataConst.MANUFACTURER_PRODUCT_ID).getValue()
					.toString();

			// wait for instances to be read.... (may be read with a certain variable delay)
			try
			{
				Thread.sleep(this.waitBeforeDeviceInstall);
			}
			catch (InterruptedException e1)
			{
				this.logger
						.log(LogService.LOG_WARNING,
								"Instance wait time was less than necessary due to interrupted thread, device instantiation might not be accurate.",
								e1);
			}

			// build the 4th id (number of instances)
			int numberOfInstances = network.getRawDevice(nodeId).getInstances()
					.size();

			// build the device unique id
			String extendedDeviceUniqueId = manufacturerId + "-"
					+ manufacturerProductType + "-" + manufacturerProductId
					+ "-" + numberOfInstances;

			// build the device unique id
			String deviceUniqueId = manufacturerId + "-"
					+ manufacturerProductType + "-" + manufacturerProductId;

			// get the device class
			String deviceClass = this.supportedDevices
					.get(extendedDeviceUniqueId);

			// check if not extended
			if (deviceClass == null)
				deviceClass = this.supportedDevices.get(deviceUniqueId);

			// normal workflow...
			if ((deviceClass != null) && (!deviceClass.isEmpty()))
			{
				// create a descriptor definition map
				HashMap<String, Object> descriptorDefinitionData = new HashMap<String, Object>();

				// store the device name
				descriptorDefinitionData.put(DeviceDescriptorFactory.NAME,
						deviceClass + "_" + nodeId);

				// store the device description
				descriptorDefinitionData.put(
						DeviceDescriptorFactory.DESCRIPTION,
						"New Device of type " + deviceClass);

				// store the device gateway
				descriptorDefinitionData.put(DeviceDescriptorFactory.GATEWAY,
						this.device.getDeviceId());

				// store the device location
				descriptorDefinitionData.put(DeviceDescriptorFactory.LOCATION,
						"");

				// store the node id
				descriptorDefinitionData.put("nodeId", "" + nodeId);

				// handle multiple instances
				String instanceIds[] = new String[device.getInstances().size()];

				// mine instances
				int i = 0;
				for (Integer instanceId : device.getInstances().keySet())
				{
					instanceIds[i] = instanceId.toString();
					i++;
				}

				// store mined instances
				descriptorDefinitionData.put("instanceIds", instanceIds);

				// get the device descriptor
				try
				{
					descriptor = this.descriptorFactory.getDescriptor(
							descriptorDefinitionData, deviceClass);
				}
				catch (Exception e)
				{
					this.logger
							.log(LogService.LOG_ERROR,
									"Error while creating DeviceDescriptor for the just added device ",
									e);
				}

				// debug dump
				this.logger.log(LogService.LOG_INFO,
						"Detected new device: \n\tdeviceUniqueId: "
								+ deviceUniqueId + "\n\tnodeId: " + nodeId
								+ "\n\tdeviceClass: " + deviceClass);
			}
		}

		// return
		return descriptor;
	}

	/**
	 * Initializes the state asynchronously as required by OSGi
	 */
	private void initializeStates()
	{
		// initialize the state
		this.currentState.setState(
				DeviceAssociationState.class.getSimpleName(),
				new DeviceAssociationState(new IdleStateValue()));

		// get the initial state of the device
		Runnable worker = new Runnable()
		{
			public void run()
			{
				network.read(nodeInfo, true);
			}
		};

		Thread workerThread = new Thread(worker);
		workerThread.start();

	}
}
