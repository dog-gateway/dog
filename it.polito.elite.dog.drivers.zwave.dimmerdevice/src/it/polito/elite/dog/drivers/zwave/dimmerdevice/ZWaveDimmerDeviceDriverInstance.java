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
import it.polito.elite.dog.core.library.model.devicecategory.Controllable;
import it.polito.elite.dog.core.library.model.devicecategory.DimmerLamp;
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
import it.polito.elite.dog.drivers.zwave.network.ZWaveDriverInstance;
import it.polito.elite.dog.drivers.zwave.network.info.ZWaveNodeInfo;
import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.unit.Unit;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class ZWaveDimmerDeviceDriverInstance extends ZWaveDriverInstance
		implements DimmerLamp, LevelControllableOutput
{
	// the class logger
	private LogHelper logger;

	// the step increase / decrease
	private int stepPercentage = 5; // default
	
	// the group set
	private HashSet<Integer> groups;
	
	// the scene set
	private HashSet<Integer> scenes;

	public ZWaveDimmerDeviceDriverInstance(ZWaveNetwork network,
			ControllableDevice device, int deviceId, Set<Integer> instancesId,
			int gatewayNodeId, int updateTimeMillis, int stepPercentage,
			BundleContext context)
	{
		super(network, device, deviceId, instancesId, gatewayNodeId,
				updateTimeMillis, context);

		this.stepPercentage = stepPercentage;
		
		//build inner data structures
		this.groups = new HashSet<Integer>();
		this.scenes = new HashSet<Integer>();

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
				{
					changeCurrentState(OnOffState.ON, nLevel);

					// notify on
					this.notifyOn();

				} else
				{
					changeCurrentState(OnOffState.OFF, nLevel);

					// notify off
					this.notifyOff();
				}

				// update the monitor admin
				this.updateStatus();
			}
		}
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
						"Device "
								+ device.getDeviceId()
								+ " is now "
								+ ((OnOffState) onState).getCurrentStateValue()[0]
										.getValue());
			} else
			{
				// update the state
				OnOffState offState = new OnOffState(new OffStateValue());
				currentState.setState(OnOffState.class.getSimpleName(),
						offState);

				logger.log(
						LogService.LOG_DEBUG,
						"Device "
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

		// send the changed level notification
		this.notifyChangedLevel(DecimalMeasure.valueOf(nLevel, Unit.ONE));

		// debug
		logger.log(LogService.LOG_DEBUG, "Device " + device.getDeviceId()
				+ " dimmer at " + nLevel);

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

		// notify for the step down event
		this.notifyStepDown();
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

		// notify step up
		this.notifyStepUp();
	}

	@Override
	public void storeScene(Integer sceneNumber)
	{
		// Store the given scene id
		this.scenes.add(sceneNumber);
		
		//notify
		this.notifyStoredScene(sceneNumber);
	}

	@Override
	public void deleteScene(Integer sceneNumber)
	{
		// Remove the given scene id
		this.scenes.remove(sceneNumber);
		
		//notify
		this.notifyDeletedScene(sceneNumber);
	}

	@Override
	public void deleteGroup(String groupID)
	{
		// remove the given group id
		this.groups.remove(groupID);
		
		//notify
		this.notifyLeftGroup(groupNumber);
	}

	@Override
	public void storeGroup(String groupID)
	{
		// Store the given group id
		this.groups.add(groupID);
		
		this.notifyLeftGroup(groupID);
	}

	@Override
	public void notifyStoredScene(Integer sceneNumber)
	{
		// send the store scene notification
		((LevelControllableOutput)this.device).notifyStoredScene(sceneNumber);

	}

	@Override
	public void notifyDeletedScene(Measure<?, ?> sceneNumber)
	{
		// send the delete scene notification
		((LevelControllableOutput)this.device).notifyDeletedScene(sceneNumber);

	}

	@Override
	public void notifyJoinedGroup(Integer groupNumber)
	{
		// send the joined group notification
		((LevelControllableOutput)this.device).notifyJoinedGroup(groupNumber);

	}

	@Override
	public void notifyBelongToGroup(Integer groupNumber)
	{
		// TODO Auto-generated method stub
		

	}

	@Override
	public void notifyLeftGroup(Integer groupNumber)
	{
		// send the left group notification
		((LevelControllableOutput)this.device).notifyLeftGroup(groupNumber);

	}

	@Override
	public void notifyStepUp()
	{
		// send the step up notification corresponding to the step-up action
		// carried (externally or internally) on the device.
		((LevelControllableOutput) this.device).notifyStepUp();

	}

	@Override
	public void notifyOn()
	{
		// send the on notification corresponding to the on action carried
		// (internally or externally) on the device.
		((LevelControllableOutput) this.device).notifyOn();

	}

	@Override
	public void notifyChangedLevel(Measure<?, ?> newLevel)
	{
		// send the changed level notification corresponding to the new state of
		// the device.
		((LevelControllableOutput) this.device).notifyChangedLevel(newLevel);

	}

	@Override
	public void notifyStepDown()
	{
		// send the step down notification corresponding to the step-up action
		// carried (externally or internally) on the device.
		((LevelControllableOutput) this.device).notifyStepDown();

	}

	@Override
	public void notifyOff()
	{
		// send the off notification corresponding to the on action carried
		// (internally or externally) on the device.
		((LevelControllableOutput) this.device).notifyOff();

	}

	@Override
	public void updateStatus()
	{
		// update the monitor admin status snapshot
		((Controllable) this.device).updateStatus();
	}
}
