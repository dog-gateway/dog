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

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.Buzzer;
import it.polito.elite.dog.core.library.model.devicecategory.Controllable;
import it.polito.elite.dog.core.library.model.devicecategory.Lamp;
import it.polito.elite.dog.core.library.model.devicecategory.LampHolder;
import it.polito.elite.dog.core.library.model.devicecategory.MainsPowerOutlet;
import it.polito.elite.dog.core.library.model.devicecategory.OnOffOutput;
import it.polito.elite.dog.core.library.model.devicecategory.SimpleLamp;
import it.polito.elite.dog.core.library.model.state.OnOffState;
import it.polito.elite.dog.core.library.model.state.State;
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

import org.osgi.framework.BundleContext;

public class ZWaveOnOffDeviceDriverInstance extends ZWaveDriverInstance implements Lamp, SimpleLamp, Buzzer,
		MainsPowerOutlet, LampHolder, OnOffOutput
{
	
	// the group set
	private HashSet<Integer> groups;
	
	// the scene set
	private HashSet<Integer> scenes;
	
	public ZWaveOnOffDeviceDriverInstance(ZWaveNetwork network, ControllableDevice device, int deviceId,
			Set<Integer> instancesId, int gatewayNodeId, int updateTimeMillis, BundleContext context)
	{
		super(network, device, deviceId, instancesId, gatewayNodeId, updateTimeMillis, context);
		
		// build inner data structures
		this.groups = new HashSet<Integer>();
		this.scenes = new HashSet<Integer>();
		
		new LogHelper(context);
		
		// initialize states
		this.initializeStates();
	}
	
	/**
	 * Initializes the state asynchronously as required by OSGi
	 */
	private void initializeStates()
	{
		// initialize the state
		this.currentState.setState(OnOffState.class.getSimpleName(), new OnOffState(new OffStateValue()));
		
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
	public void newMessageFromHouse(Device deviceNode, Instance instanceNode, Controller controllerNode, String sValue)
	{
		this.deviceNode = deviceNode;
		
		// Read the value associated with the right CommandClass.
		CommandClasses ccEntry = instanceNode.getCommandClass(ZWaveAPI.COMMAND_CLASS_SWITCH_BINARY);
		
		// Check if it is a real new value or if it is an old one
		if (ccEntry != null)
		{
			
			if (changeOnOffState((ccEntry.getLevelAsInt() > 0) ? OnOffState.ON : OnOffState.OFF))
				this.updateStatus();
			
		}
	}
	
	/**
	 * Check if the current state has been changed. In that case, fire a state
	 * change message, otherwise it does nothing
	 * 
	 * @param OnOffValue
	 *            OnOffState.ON or OnOffState.OFF
	 */
	private boolean changeOnOffState(String OnOffValue)
	{
		// state changed flag
		boolean stateChanged = false;
		
		// get the current state
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
				
				// notify on
				this.notifyOn();
			}
			else
			{
				newState = new OnOffState(new OffStateValue());
				
				// notify off
				this.notifyOff();
			}
			// ... then set the new state for the device and throw a state
			currentState.setState(newState.getStateName(), newState);
			
			// state changed
			stateChanged = true;
		}
		
		return stateChanged;
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
		// Store the given scene id
		this.scenes.add(sceneNumber);
		
		// notify
		this.notifyStoredScene(sceneNumber);
	}
	
	@Override
	public void deleteScene(Integer sceneNumber)
	{
		// Remove the given scene id
		this.scenes.remove(sceneNumber);
		
		// notify
		this.notifyDeletedScene(sceneNumber);
	}
	
	@Override
	public void deleteGroup(Integer groupID)
	{
		// remove the given group id
		this.groups.remove(groupID);
		
		// notify
		this.notifyLeftGroup(groupID);
	}
	
	@Override
	public void storeGroup(Integer groupID)
	{
		// Store the given group id
		this.groups.add(groupID);
		
		this.notifyJoinedGroup(groupID);
	}
	
	@Override
	public void notifyStoredScene(Integer sceneNumber)
	{
		// send the store scene notification
		((OnOffOutput) this.device).notifyStoredScene(sceneNumber);
		
	}
	
	@Override
	public void notifyDeletedScene(Integer sceneNumber)
	{
		// send the delete scene notification
		((OnOffOutput) this.device).notifyDeletedScene(sceneNumber);
		
	}
	
	@Override
	public void notifyJoinedGroup(Integer groupNumber)
	{
		// send the joined group notification
		((OnOffOutput) this.device).notifyJoinedGroup(groupNumber);
		
	}
	
	@Override
	public void notifyLeftGroup(Integer groupNumber)
	{
		// send the left group notification
		((OnOffOutput) this.device).notifyLeftGroup(groupNumber);
		
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
			network.write(nodeInfo.getDeviceNodeId(), instanceId, ZWaveAPI.COMMAND_CLASS_SWITCH_BINARY, "255");
	}
	
	@Override
	public void off()
	{
		// Sends off command to all instances, probably only one in this case
		for (Integer instanceId : nodeInfo.getInstanceSet())
			network.write(nodeInfo.getDeviceNodeId(), instanceId, ZWaveAPI.COMMAND_CLASS_SWITCH_BINARY, "0");
	}
	
	@Override
	protected ZWaveNodeInfo createNodeInfo(int deviceId, Set<Integer> instancesId, boolean isController)
	{
		HashMap<Integer, Set<Integer>> instanceCommand = new HashMap<Integer, Set<Integer>>();
		
		HashSet<Integer> ccSet = new HashSet<Integer>();
		ccSet.add(ZWaveAPI.COMMAND_CLASS_SWITCH_BINARY);
		
		// binary switch has its own command class
		for (Integer instanceId : instancesId)
		{
			instanceCommand.put(instanceId, ccSet);
		}
		ZWaveNodeInfo nodeInfo = new ZWaveNodeInfo(deviceId, instanceCommand, isController);
		return nodeInfo;
	}
	
	@Override
	public void notifyOn()
	{
		if (this.device instanceof Lamp)
		{
			((Lamp) this.device).notifyOn();
		}
		else if (this.device instanceof Buzzer)
		{
			((Buzzer) this.device).notifyOn();
		}
		else
			((OnOffOutput) this.device).notifyOn();
	}
	
	@Override
	public void notifyOff()
	{
		if (this.device instanceof Lamp)
		{
			((Lamp) this.device).notifyOff();
		}
		else if (this.device instanceof Buzzer)
		{
			((Buzzer) this.device).notifyOff();
		}
		else
			((OnOffOutput) this.device).notifyOff();
	}
	
	@Override
	public void updateStatus()
	{
		// update the monitor admin status snapshot
		((Controllable) this.device).updateStatus();
	}
}