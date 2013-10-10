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
package it.polito.elite.dog.drivers.zwave.onoffdevice;

import it.polito.elite.dog.drivers.zwave.ZWaveAPI;
import it.polito.elite.dog.drivers.zwave.model.zway.json.CommandClasses;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Controller;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Device;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Instance;
import it.polito.elite.dog.drivers.zwave.network.ZWaveDriver;
import it.polito.elite.dog.drivers.zwave.network.info.ZWaveNodeInfo;
import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.Buzzer;
import it.polito.elite.dog.core.library.model.devicecategory.Lamp;
import it.polito.elite.dog.core.library.model.devicecategory.MainsPowerOutlet;
import it.polito.elite.dog.core.library.model.devicecategory.ElectricalSystem;
import it.polito.elite.dog.core.library.model.devicecategory.SimpleLamp;
import it.polito.elite.dog.core.library.model.state.OnOffState;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.OffStateValue;
import it.polito.elite.dog.core.library.model.statevalue.OnStateValue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class ZWaveOnOffDeviceDriverInstance extends ZWaveDriver implements Lamp, SimpleLamp, Buzzer, MainsPowerOutlet
{
	// the class logger
	private LogHelper logger;

	public ZWaveOnOffDeviceDriverInstance(ZWaveNetwork network, ControllableDevice device, int deviceId,
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
		// debug
		logger.log(LogService.LOG_DEBUG, ZWaveOnOffDeviceDriver.LOG_ID + "Device " + device.getDeviceId()
				+ " is now " + ((OnOffState) newState).getCurrentStateValue()[0].getValue());
		((ElectricalSystem) device).notifyStateChanged(newState);

	}

	@Override
	public void newMessageFromHouse(Device deviceNode, Instance instanceNode, 
			Controller controllerNode, String sValue)
	{
		this.deviceNode = deviceNode;

		// Read the value associated with the right CommandClass.
		int nLevel = 0;
		CommandClasses ccEntry = instanceNode.getCommandClass(ZWaveAPI.COMMAND_CLASS_SWITCH_BINARY);

		// Check if it is a real new value or if it is an old one
		if(ccEntry!=null)
		{
			//update last update time
			lastUpdateTime = ccEntry.getLevelUpdateTime();
			nFailedUpdate = 0;

			if (ccEntry != null)
				nLevel = ccEntry.getLevelAsInt();

			if (nLevel > 0)
				changeCurrentState(OnOffState.ON);
			else
				changeCurrentState(OnOffState.OFF);
		}
		/*else
		{
			// increment counter
			nFailedUpdate++;

			//log a message after 5 failed update
			if(nFailedUpdate >= 5)
			{
				logger.log(LogService.LOG_WARNING, ZWaveOnOffDeviceDriver.LOG_ID + "Device " + device.getDeviceId()
						+ " doesn't respond after 5 update requests");

				nFailedUpdate = 0;
			}
		}*/
	}

	/**
	 * Check if the current state has been changed. In that case, fire a state
	 * change message, otherwise it does nothing
	 * 
	 * @param OnOffValue
	 *            OnOffState.ON or OnOffState.OFF
	 */
	private void changeCurrentState(String OnOffValue)
	{
		String currentStateValue = "";
		State state = currentState.getState(OnOffState.class.getSimpleName());

		if (state != null)
			currentStateValue = (String) state.getCurrentStateValue()[0].getValue();

		// if the current states it is different from the new state
		if (!currentStateValue.equalsIgnoreCase(OnOffValue))
		{
			State newState;
			// set the new state to on or off...
			if (OnOffValue.equalsIgnoreCase(OnOffState.ON))
			{
				newState = new OnOffState(new OnStateValue());
			}
			else
			{
				newState = new OnOffState(new OffStateValue());
			}
			// ... then set the new state for the device and throw a state
			currentState.setState(newState.getStateName(), newState);
            notifyStateChanged(newState);
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
	public void storeScene(Integer sceneNumber)
	{
		// intentionally left empty
	}

	@Override
	public void deleteScene(Integer sceneNumber)
	{
		// intentionally left empty
	}

	@Override
	public void deleteGroup(String groupID)
	{
		// intentionally left empty
	}

	@Override
	public void storeGroup(String groupID)
	{
		// intentionally left empty
	}

	@Override
	public DeviceStatus getState()
	{
		return currentState;
	}

	@Override
	public void on()
	{
		//Sends on command to all instances, probably only one in this case
		for(Integer instanceId : nodeInfo.getInstanceSet())
			network.write(nodeInfo.getDeviceNodeId(), instanceId, ZWaveAPI.COMMAND_CLASS_SWITCH_BINARY, "255");
	}

	@Override
	public void off()
	{
		//Sends off command to all instances, probably only one in this case
		for(Integer instanceId : nodeInfo.getInstanceSet())
			network.write(nodeInfo.getDeviceNodeId(), instanceId, ZWaveAPI.COMMAND_CLASS_SWITCH_BINARY, "0");
	}

	@Override
	protected ZWaveNodeInfo createNodeInfo(int deviceId, Set<Integer> instancesId, boolean isController) 
	{
		HashMap<Integer,Set<Integer>> instanceCommand = new HashMap<Integer, Set<Integer>>();

		HashSet<Integer> ccSet = new HashSet<Integer>();
		ccSet.add(ZWaveAPI.COMMAND_CLASS_SWITCH_BINARY);

		//binary switch has its own command class 
		for(Integer instanceId : instancesId)
		{
			instanceCommand.put(instanceId, ccSet);
		}
		ZWaveNodeInfo nodeInfo = new ZWaveNodeInfo(deviceId, instanceCommand, isController);
		return nodeInfo;
	}
}