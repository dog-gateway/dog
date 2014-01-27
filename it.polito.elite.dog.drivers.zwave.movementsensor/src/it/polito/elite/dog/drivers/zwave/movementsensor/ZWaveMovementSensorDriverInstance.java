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
package it.polito.elite.dog.drivers.zwave.movementsensor;

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
import it.polito.elite.dog.core.library.model.devicecategory.MovementSensor;
import it.polito.elite.dog.core.library.model.state.MovementState;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.MovingStateValue;
import it.polito.elite.dog.core.library.model.statevalue.NotMovingStateValue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class ZWaveMovementSensorDriverInstance extends ZWaveDriverInstance implements MovementSensor
{
	// the class logger
	private LogHelper logger;

	public ZWaveMovementSensorDriverInstance(ZWaveNetwork network, ControllableDevice device, int deviceId,
			Set<Integer> instancesId, int gatewayNodeId, int updateTimeMillis, BundleContext context)
	{
		super(network, device, deviceId, instancesId, gatewayNodeId, updateTimeMillis, context);

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
		this.currentState.setState(MovementState.class.getSimpleName(), new MovementState(new NotMovingStateValue()));

		// get the initial state of the device
		Runnable worker = new Runnable() {
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
		//probably not used...
	}

	@Override
	public void newMessageFromHouse(Device deviceNode, Instance instanceNode, 
			Controller controllerNode, String sValue)
	{
		this.deviceNode = deviceNode;

		// Read the value associated with the right CommandClass.
		boolean bState = false;
		CommandClasses ccEntry = instanceNode.getCommandClass(ZWaveAPI.COMMAND_CLASS_SENSOR_BINARY);

		if (ccEntry != null)
			bState = ccEntry.getLevelAsBoolean();

		if (bState)
			changeCurrentState(MovementState.ISMOVING);
		else
			changeCurrentState(MovementState.NOTMOVING);
	}

	/**
	 * Check if the current state has been changed. In that case, fire a state
	 * change message, otherwise it does nothing
	 * 
	 * @param movementState.ISMOVING or MovementState.NOTMOVING
	 */
	private void changeCurrentState(String movementState)
	{
		String currentStateValue = "";
		State state = currentState.getState(MovementState.class.getSimpleName());

		if (state != null)
			currentStateValue = (String) state.getCurrentStateValue()[0].getValue();

		// if the current states it is different from the new state
		if (!currentStateValue.equalsIgnoreCase(movementState))
		{
			// set the new state to open or close...
			if (movementState.equalsIgnoreCase(MovementState.ISMOVING))
				notifyDetectedMovement();
			else
				notifyCeasedMovement();
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
	protected ZWaveNodeInfo createNodeInfo(int deviceId, Set<Integer> instancesId, boolean isController) 
	{
		HashMap<Integer,Set<Integer>> instanceCommand = new HashMap<Integer, Set<Integer>>();

		HashSet<Integer> ccSet = new HashSet<Integer>();
		ccSet.add(ZWaveAPI.COMMAND_CLASS_SENSOR_BINARY);

		//binary switch has its own command class 
		for(Integer instanceId : instancesId)
		{
			instanceCommand.put(instanceId, ccSet);
		}
		ZWaveNodeInfo nodeInfo = new ZWaveNodeInfo(deviceId, instanceCommand, isController);
		return nodeInfo;
	}

	@Override
	public void notifyCeasedMovement() 
	{
		// update the state
		MovementState movState = new MovementState(new NotMovingStateValue());
		currentState.setState(MovementState.class.getSimpleName(), movState);

		logger.log(LogService.LOG_DEBUG, ZWaveMovementSensorDriver.LOG_ID + "Device " + device.getDeviceId()
				+ " value is now " + ((MovementState) movState).getCurrentStateValue()[0].getValue());

		((MovementSensor) device).notifyCeasedMovement();

	}

	@Override
	public void notifyDetectedMovement() 
	{
		// update the state
		MovementState movState = new MovementState(new MovingStateValue());
		currentState.setState(MovementState.class.getSimpleName(), movState);

		logger.log(LogService.LOG_DEBUG, ZWaveMovementSensorDriver.LOG_ID + "Device " + device.getDeviceId()
				+ " value is now " + ((MovementState) movState).getCurrentStateValue()[0].getValue());

		((MovementSensor) device).notifyDetectedMovement();
	}
}
