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
package it.polito.elite.dog.drivers.zwave.dimmerdevice;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.DimmerLamp;
import it.polito.elite.dog.core.library.model.devicecategory.ElectricalSystem;
import it.polito.elite.dog.core.library.model.devicecategory.LevelControllableOutput;
import it.polito.elite.dog.core.library.model.state.LevelState;
import it.polito.elite.dog.core.library.model.state.OnOffState;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.LevelStateValue;
import it.polito.elite.dog.core.library.model.statevalue.OffStateValue;
import it.polito.elite.dog.core.library.model.statevalue.OnStateValue;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.drivers.zwave.ZWaveAPI;
import it.polito.elite.dog.drivers.zwave.model.zway.json.CommandClasses;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Controller;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Device;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Instance;
import it.polito.elite.dog.drivers.zwave.network.ZWaveDriver;
import it.polito.elite.dog.drivers.zwave.network.info.ZWaveNodeInfo;
import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class ZWaveDimmerDeviceDriverInstance extends ZWaveDriver implements
		DimmerLamp, LevelControllableOutput
{
	// the class logger
	private LogHelper logger;

	// the step increase / decrease
	private int stepPercentage = 5; // default

	public ZWaveDimmerDeviceDriverInstance(ZWaveNetwork network,
			ControllableDevice device, int deviceId, Set<Integer> instancesId,
			int gatewayNodeId, int updateTimeMillis, int stepPercentage, BundleContext context)
	{
		super(network, device, deviceId, instancesId, gatewayNodeId,
				updateTimeMillis, context);
		
		this.stepPercentage = stepPercentage;

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
		// debug
		logger.log(LogService.LOG_DEBUG, ZWaveDimmerDeviceDriver.LOG_ID
				+ "Device " + device.getDeviceId() + " is now "
				+ ((OnOffState) newState).getCurrentStateValue()[0].getValue());
		((ElectricalSystem) device).notifyStateChanged(newState);

	}

	@Override
	public void newMessageFromHouse(Device deviceNode, Instance instanceNode,
			Controller controllerNode, String sValue)
	{
		this.deviceNode = deviceNode;

		// Read the value associated with the right CommandClass.
		int nLevel = 0;
		CommandClasses ccEntry = instanceNode
				.getCommandClass(ZWaveAPI.COMMAND_CLASS_SWITCH_MULTILEVEL);

		// Check if it is a real new value or if it is an old one
		if (ccEntry != null)
		{
			// update last update time
			lastUpdateTime = ccEntry.getLevelUpdateTime();
			nFailedUpdate = 0;

			if (ccEntry != null)
			{
				nLevel = ccEntry.getLevelAsInt();

				if (nLevel > 0)
					changeCurrentState(OnOffState.ON, nLevel);
				else
					changeCurrentState(OnOffState.OFF, nLevel);
			}
		}
		/*
		 * else { // increment counter nFailedUpdate++;
		 * 
		 * //log a message after 5 failed update if(nFailedUpdate >= 5) {
		 * logger.log(LogService.LOG_WARNING, ZWaveDimmerDeviceDriver.LOG_ID +
		 * "Device " + device.getDeviceId() +
		 * " doesn't respond after 5 update requests");
		 * 
		 * nFailedUpdate = 0; } }
		 */
	}

	/**
	 * Check if the current state has been changed. In that case, fire a state
	 * change message, otherwise it does nothing
	 * 
	 * @param OnOffValue
	 *            OnOffState.ON or OnOffState.OFF
	 */
	private void changeCurrentState(String OnOffValue, int nLevel)
	{
		String currentStateValue = "";
		State state = currentState.getState(OnOffState.class.getSimpleName());

		if (state != null)
			currentStateValue = (String) state.getCurrentStateValue()[0]
					.getValue();

		// if the current states it is different from the new state
		if (!currentStateValue.equalsIgnoreCase(OnOffValue))
		{
			// set the new state to on or off...
			if (OnOffValue.equalsIgnoreCase(OnOffState.ON))
			{
				// update the state
				OnOffState onState = new OnOffState(new OnStateValue());
				currentState
						.setState(OnOffState.class.getSimpleName(), onState);

				logger.log(
						LogService.LOG_DEBUG,
						ZWaveDimmerDeviceDriver.LOG_ID
								+ "Device "
								+ device.getDeviceId()
								+ " is now "
								+ ((OnOffState) onState).getCurrentStateValue()[0]
										.getValue());
			}
			else
			{
				// update the state
				OnOffState offState = new OnOffState(new OffStateValue());
				currentState.setState(OnOffState.class.getSimpleName(),
						offState);

				logger.log(
						LogService.LOG_DEBUG,
						ZWaveDimmerDeviceDriver.LOG_ID
								+ "Device "
								+ device.getDeviceId()
								+ " is now "
								+ ((OnOffState) offState)
										.getCurrentStateValue()[0].getValue());

			}
		}

		// update the state
		LevelStateValue pValue = new LevelStateValue();
		pValue.setValue(nLevel);

		currentState.setState(LevelState.class.getSimpleName(), new LevelState(
				pValue));

		// debug
		logger.log(LogService.LOG_DEBUG, ZWaveDimmerDeviceDriver.LOG_ID
				+ "Device " + device.getDeviceId() + " dimmer at " + nLevel);

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
	public void on()
	{
		// Sends on command to all instances, probably only one in this case
		for (Integer instanceId : nodeInfo.getInstanceSet())
			network.write(nodeInfo.getDeviceNodeId(), instanceId,
					ZWaveAPI.COMMAND_CLASS_SWITCH_MULTILEVEL, "255");
	}

	@Override
	public void off()
	{
		// Sends off command to all instances, probably only one in this case
		for (Integer instanceId : nodeInfo.getInstanceSet())
			network.write(nodeInfo.getDeviceNodeId(), instanceId,
					ZWaveAPI.COMMAND_CLASS_SWITCH_MULTILEVEL, "0");
	}

	@Override
	protected ZWaveNodeInfo createNodeInfo(int deviceId,
			Set<Integer> instancesId, boolean isController)
	{
		HashMap<Integer, Set<Integer>> instanceCommand = new HashMap<Integer, Set<Integer>>();

		HashSet<Integer> ccSet = new HashSet<Integer>();
		ccSet.add(ZWaveAPI.COMMAND_CLASS_SWITCH_MULTILEVEL);

		// binary switch has its own command class
		for (Integer instanceId : instancesId)
		{
			instanceCommand.put(instanceId, ccSet);
		}
		ZWaveNodeInfo nodeInfo = new ZWaveNodeInfo(deviceId, instanceCommand,
				isController);
		return nodeInfo;
	}

	@Override
	public void set(Object value)
	{
		// Sends on command to all instances, probably only one in this case
		for (Integer instanceId : nodeInfo.getInstanceSet())
			network.write(nodeInfo.getDeviceNodeId(), instanceId,
					ZWaveAPI.COMMAND_CLASS_SWITCH_MULTILEVEL, value.toString());
	}

	@Override
	public void stepDown()
	{
		// get the current level value
		int currentValue = (Integer) currentState.getState(
				LevelState.class.getSimpleName()).getCurrentStateValue()[0]
				.getValue();

		// set to 100%
		if (currentValue == 255)
			currentValue = 100;

		// decrease by step percentage
		currentValue = Math.max(0, currentValue - this.stepPercentage);

		// set the value
		this.set(currentValue);

	}

	@Override
	public void stepUp()
	{
		// get the current level value
		int currentValue = (Integer) currentState.getState(
				LevelState.class.getSimpleName()).getCurrentStateValue()[0]
				.getValue();

		// increase by step percentage
		currentValue = Math.min(100, currentValue + this.stepPercentage);

		// set the value
		this.set(currentValue);

	}

	@Override
	public void storeScene(Integer sceneNumber)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteScene(Integer sceneNumber)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteGroup(String groupID)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void storeGroup(String groupID)
	{
		// TODO Auto-generated method stub

	}
}
