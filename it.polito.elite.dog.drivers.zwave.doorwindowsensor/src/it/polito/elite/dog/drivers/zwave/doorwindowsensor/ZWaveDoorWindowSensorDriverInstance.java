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
package it.polito.elite.dog.drivers.zwave.doorwindowsensor;

import it.polito.elite.dog.drivers.zwave.ZWaveAPI;
import it.polito.elite.dog.drivers.zwave.model.zway.json.CommandClasses;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Controller;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Device;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Instance;
import it.polito.elite.dog.drivers.zwave.network.ZWaveDriverInstance;
import it.polito.elite.dog.drivers.zwave.network.info.ZWaveNodeInfo;
import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.DoorSensor;
import it.polito.elite.dog.core.library.model.devicecategory.ElectricalSystem;
import it.polito.elite.dog.core.library.model.devicecategory.WindowSensor;
import it.polito.elite.dog.core.library.model.state.OpenCloseState;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.CloseStateValue;
import it.polito.elite.dog.core.library.model.statevalue.OpenStateValue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class ZWaveDoorWindowSensorDriverInstance extends ZWaveDriverInstance
		implements DoorSensor, WindowSensor
{
	// the class logger
	private LogHelper logger;

	public ZWaveDoorWindowSensorDriverInstance(ZWaveNetwork network,
			ControllableDevice device, int deviceId, Set<Integer> instancesId,
			int gatewayNodeId, int updateTimeMillis, BundleContext context)
	{
		super(network, device, deviceId, instancesId, gatewayNodeId,
				updateTimeMillis, context);

		// create a logger
		logger = new LogHelper(context);

		// initialize states
		this.initializeStates();
	}

	/**
	 * Initializes the state asynchronously as required by OSGi
	 */
	private void initializeStates()
	{
		// initialize the state
		this.currentState.setState(OpenCloseState.class.getSimpleName(),
				new OpenCloseState(new CloseStateValue()));

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

	@Override
	public void notifyStateChanged(State newState)
	{
		((ElectricalSystem) device).notifyStateChanged(newState);
	}

	@Override
	public void newMessageFromHouse(Device deviceNode, Instance instanceNode,
			Controller controllerNode, String sValue)
	{
		this.deviceNode = deviceNode;

		// Read the value associated with the right CommandClass.
		boolean bState = false;
		CommandClasses ccEntry = instanceNode
				.getCommandClass(ZWaveAPI.COMMAND_CLASS_SENSOR_BINARY);

		if (ccEntry != null)
			bState = ccEntry.getLevelAsBoolean();

		if (bState)
			changeCurrentState(OpenCloseState.OPEN);
		else
			changeCurrentState(OpenCloseState.CLOSE);
		
		//notify state changed
		this.notifyStateChanged(null);
	}

	/**
	 * Check if the current state has been changed. In that case, fire a state
	 * change message, otherwise it does nothing
	 * 
	 * @param openCloseValue
	 *            OpenCloseState.CLOSE or OpenCloseState.OPEN
	 */
	private void changeCurrentState(String openCloseValue)
	{
		String currentStateValue = "";
		State state = currentState.getState(OpenCloseState.class
				.getSimpleName());

		if (state != null)
			currentStateValue = (String) state.getCurrentStateValue()[0]
					.getValue();

		// if the current states it is different from the new state
		if (!currentStateValue.equalsIgnoreCase(openCloseValue))
		{
			// set the new state to open or close...
			if (openCloseValue.equalsIgnoreCase(OpenCloseState.CLOSE))
				notifyClose();
			else
				notifyOpen();
		}
	}

	@Override
	protected void specificConfiguration()
	{
		// prepare the device state map
		currentState = new DeviceStatus(device.getDeviceId());
	}

	@Override
	protected void addToNetworkDriver(ZWaveNodeInfo nodeInfo)
	{
		network.addDriver(nodeInfo, updateTimeMillis, this);
	}

	@Override
	protected boolean isController()
	{
		return false;
	}

	@Override
	public DeviceStatus getState()
	{
		return currentState;
	}

	@Override
	public void notifyOpen()
	{
		// update the state
		OpenCloseState openState = new OpenCloseState(new OpenStateValue());
		currentState.setState(OpenCloseState.class.getSimpleName(), openState);

		logger.log(
				LogService.LOG_DEBUG,
				"Device "
						+ device.getDeviceId()
						+ " is now "
						+ ((OpenCloseState) openState).getCurrentStateValue()[0]
								.getValue());

		((DoorSensor) device).notifyOpen();
	}

	@Override
	public void notifyClose()
	{
		// update the state
		OpenCloseState closeState = new OpenCloseState(new CloseStateValue());
		currentState.setState(OpenCloseState.class.getSimpleName(), closeState);

		logger.log(
				LogService.LOG_DEBUG,
				"Device "
						+ device.getDeviceId()
						+ " is now "
						+ ((OpenCloseState) closeState).getCurrentStateValue()[0]
								.getValue());

		((DoorSensor) device).notifyClose();
	}

	@Override
	protected ZWaveNodeInfo createNodeInfo(int deviceId,
			Set<Integer> instancesId, boolean isController)
	{
		HashMap<Integer, Set<Integer>> instanceCommand = new HashMap<Integer, Set<Integer>>();

		// binary switch has its own command class
		HashSet<Integer> ccSet = new HashSet<Integer>();
		ccSet.add(ZWaveAPI.COMMAND_CLASS_SENSOR_BINARY);

		for (Integer instanceId : instancesId)
		{
			instanceCommand.put(instanceId, ccSet);
		}
		ZWaveNodeInfo nodeInfo = new ZWaveNodeInfo(deviceId, instanceCommand,
				isController);
		return nodeInfo;
	}
}
