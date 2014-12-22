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
package it.polito.elite.dog.drivers.zwave.powermeteringlevelcontrollableoutput;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.Controllable;
import it.polito.elite.dog.core.library.model.devicecategory.EnergyAndPowerMeteringLevelControllableOutput;
import it.polito.elite.dog.core.library.model.devicecategory.LevelControllableOutput;
import it.polito.elite.dog.core.library.model.devicecategory.PowerMeteringLevelControllableOutput;
import it.polito.elite.dog.core.library.model.state.LevelState;
import it.polito.elite.dog.core.library.model.state.OnOffState;
import it.polito.elite.dog.core.library.model.state.SinglePhaseActiveEnergyState;
import it.polito.elite.dog.core.library.model.state.SinglePhaseActivePowerMeasurementState;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.ActiveEnergyStateValue;
import it.polito.elite.dog.core.library.model.statevalue.ActivePowerStateValue;
import it.polito.elite.dog.core.library.model.statevalue.LevelStateValue;
import it.polito.elite.dog.core.library.model.statevalue.OffStateValue;
import it.polito.elite.dog.core.library.model.statevalue.OnStateValue;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.drivers.zwave.ZWaveAPI;
import it.polito.elite.dog.drivers.zwave.model.zway.json.CommandClasses;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Controller;
import it.polito.elite.dog.drivers.zwave.model.zway.json.DataElemObject;
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
import javax.measure.quantity.Dimensionless;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.measure.unit.UnitFormat;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class ZWavePowerMeteringLevelControllableOutputDriverInstance extends
		ZWaveDriverInstance implements PowerMeteringLevelControllableOutput,
		EnergyAndPowerMeteringLevelControllableOutput
{
	// the class logger
	private LogHelper logger;

	// the step increase / decrease
	private int stepPercentage = 5; // default

	// the group set
	private HashSet<Integer> groups;

	// the scene set
	private HashSet<Integer> scenes;

	public ZWavePowerMeteringLevelControllableOutputDriverInstance(
			ZWaveNetwork network, ControllableDevice device, int deviceId,
			Set<Integer> instancesId, int gatewayNodeId, int updateTimeMillis,
			int stepPercentage, BundleContext context)
	{
		super(network, device, deviceId, instancesId, gatewayNodeId,
				updateTimeMillis, context);

		this.stepPercentage = stepPercentage;

		// build inner data structures
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
		// add unit of measure aliases (to fix notation problems...)
		UnitFormat uf = UnitFormat.getInstance();
		uf.alias(SI.WATT.times(NonSI.HOUR), "Wh");
		uf.label(SI.KILO(SI.WATT.times(NonSI.HOUR)), "kWh");

		// initialize the state
		this.currentState.setState(OnOffState.class.getSimpleName(),
				new OnOffState(new OffStateValue()));

		// only if MeteringDimmablePowerOutlet
		if (this.device instanceof EnergyAndPowerMeteringLevelControllableOutput)
		{
			// initialize the energy state value
			ActiveEnergyStateValue energyStateValue = new ActiveEnergyStateValue();
			energyStateValue.setValue(DecimalMeasure.valueOf("0.0 "
					+ SI.KILO(SI.WATT.times(NonSI.HOUR)).toString()));
			this.currentState.setState(
					SinglePhaseActiveEnergyState.class.getSimpleName(),
					new SinglePhaseActiveEnergyState(energyStateValue));
		}

		// initialize the power state value
		ActivePowerStateValue powerStateValue = new ActivePowerStateValue();
		powerStateValue.setValue(DecimalMeasure.valueOf("0.0 "
				+ SI.WATT.toString()));
		this.currentState.setState(
				SinglePhaseActivePowerMeasurementState.class.getSimpleName(),
				new SinglePhaseActivePowerMeasurementState(powerStateValue));

		LevelStateValue levelValue = new LevelStateValue();
		levelValue.setValue(DecimalMeasure.valueOf(0, Unit.ONE));
		this.currentState.setState(LevelState.class.getSimpleName(),
				new LevelState(levelValue));

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

		// the on/off updated flag
		boolean updatedOnOff = false;

		// the level updated flag
		boolean updatedLevel = false;

		// the energy updated flag
		boolean energyUpdated = false;

		// the power updated flag
		boolean powerUpdated = false;

		// Read the value associated with the right CommandClass.

		// switch multi-level
		int nLevel = 0;
		CommandClasses ccEntry = instanceNode
				.getCommandClass(ZWaveAPI.COMMAND_CLASS_SWITCH_MULTILEVEL);

		// Check if it is a real new value or if it is an old one
		if (ccEntry != null)
		{

			nLevel = ccEntry.getLevelAsInt();

			// change the state and update corresponding flags
			updatedOnOff = changeOnOffState((nLevel > 0) ? OnOffState.ON
					: OnOffState.OFF);
			updatedLevel = changeLevelState(nLevel);

		}

		// meter
		CommandClasses ccElectricityEntry = instanceNode.getCommandClasses()
				.get(ZWaveAPI.COMMAND_CLASS_METER);

		// Check if it is a real new value or if it is an old one. We can use
		// one of the cc available
		DataElemObject instance0 = ccElectricityEntry.get("0");

		if (instance0 != null)
		{
			long updateTime = instance0.getDataElem("val").getUpdateTime();

			// first time we only save update time, no more
			if (lastUpdateTime == 0)
				lastUpdateTime = updateTime;

			else if (lastUpdateTime < updateTime)
			{
				// update last update time
				lastUpdateTime = updateTime;
				nFailedUpdate = 0;

				// handle energy data if available
				DataElemObject energyEntry = ccElectricityEntry.get("0");
				if (energyEntry != null)
				{
					this.changeActiveEnergyState(Double.valueOf(energyEntry
							.getDataElemValue("val").toString()));
					energyUpdated = true;
				}

				// handle power data if available
				DataElemObject powerEntry = ccElectricityEntry.get("2");
				if (powerEntry != null)
				{
					this.changeActivePowerState(Double.valueOf(powerEntry
							.getDataElemValue("val").toString()));
					powerUpdated = true;

				}
			}
		}

		if (updatedLevel || updatedOnOff || powerUpdated || energyUpdated)
			this.updateStatus();
	}

	/**
	 * Manages the power state update (only updates if the current state value
	 * is different from the given one)
	 * 
	 * @param activeEnergy
	 * @return
	 */
	private void changeActiveEnergyState(double activeEnergy)
	{

		// build the energy measure
		DecimalMeasure<?> value = DecimalMeasure.valueOf(activeEnergy + " "
				+ SI.KILO(SI.WATT.times(NonSI.HOUR)).toString());

		// update the state
		ActiveEnergyStateValue pValue = new ActiveEnergyStateValue();
		pValue.setValue(value);
		currentState.setState(
				SinglePhaseActiveEnergyState.class.getSimpleName(),
				new SinglePhaseActiveEnergyState(pValue));

		// debug
		logger.log(LogService.LOG_DEBUG, "Device " + device.getDeviceId()
				+ " active energy " + value.toString());

		// notify energy change
		this.notifyNewActiveEnergyValue(value);
	}

	/**
	 * Manages the energy state update (only updates if the current state value
	 * is different from the given one)
	 * 
	 * @param activePower
	 * @return
	 */
	private void changeActivePowerState(double activePower)
	{

		// build the power measure
		DecimalMeasure<?> powerValue = DecimalMeasure.valueOf(activePower + " "
				+ SI.WATT.toString());

		// update the state
		ActivePowerStateValue pValue = new ActivePowerStateValue();
		pValue.setValue(powerValue);
		currentState.setState(
				SinglePhaseActivePowerMeasurementState.class.getSimpleName(),
				new SinglePhaseActivePowerMeasurementState(pValue));

		// debug
		logger.log(LogService.LOG_DEBUG, "Device " + device.getDeviceId()
				+ " active power " + powerValue.toString());

		// notify the state change
		this.notifyNewActivePowerValue(powerValue);

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

		// get the current state value
		String currentStateValue = "";
		State state = currentState.getState(OnOffState.class.getSimpleName());

		if (state != null)
			currentStateValue = (String) state.getCurrentStateValue()[0]
					.getValue();

		// if the current states it is different from the new state
		if (!currentStateValue.equalsIgnoreCase(OnOffValue))
		{
			State newState;
			// set the new state to on or off...
			if (OnOffValue.equalsIgnoreCase(OnOffState.ON))
			{
				newState = new OnOffState(new OnStateValue());

				// send the on notification
				this.notifyOn();
			}
			else
			{
				newState = new OnOffState(new OffStateValue());

				// send the off notification
				this.notifyOff();
			}

			// ... then set the new state for the device and throw a state
			// changed notification
			currentState.setState(newState.getStateName(), newState);

			// state changed
			stateChanged = true;
		}

		return stateChanged;
	}

	/**
	 * Manages the state change operations for the level state
	 * 
	 * @param nLevel
	 * @return
	 */

	@SuppressWarnings("unchecked")
	private boolean changeLevelState(int nLevel)
	{
		// flag for state changes
		boolean stateChanged = false;

		// get the current state
		// get the current state value

		Measure<?, Dimensionless> currentStateValue = null;
		State state = currentState.getState(LevelState.class.getSimpleName());

		if (state != null)
			currentStateValue = (Measure<?, Dimensionless>) state
					.getCurrentStateValue()[0].getValue();

		// check if the state is changed or not
		if ((currentStateValue != null)
				&& (currentStateValue.intValue(Unit.ONE) != nLevel))
		{
			// update the state
			LevelStateValue pValue = new LevelStateValue();
			pValue.setValue(DecimalMeasure.valueOf(nLevel, Unit.ONE));

			// change the current level state
			currentState.setState(LevelState.class.getSimpleName(),
					new LevelState(pValue));

			// send the changed level notification
			this.notifyChangedLevel(DecimalMeasure.valueOf(nLevel, Unit.ONE));

			// the state is changed
			stateChanged = true;
		}

		// debug
		logger.log(LogService.LOG_DEBUG, "Device " + device.getDeviceId()
				+ " dimmer at " + nLevel);

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
		ccSet.add(ZWaveAPI.COMMAND_CLASS_METER);

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
					ZWaveAPI.COMMAND_CLASS_SWITCH_MULTILEVEL, value.toString()
							+ ", 0");
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
		((LevelControllableOutput) this.device).notifyStoredScene(sceneNumber);

	}

	@Override
	public void notifyDeletedScene(Integer sceneNumber)
	{
		// send the delete scene notification
		((LevelControllableOutput) this.device).notifyDeletedScene(sceneNumber);

	}

	@Override
	public void notifyJoinedGroup(Integer groupNumber)
	{
		// send the joined group notification
		((LevelControllableOutput) this.device).notifyJoinedGroup(groupNumber);

	}

	@Override
	public void notifyLeftGroup(Integer groupNumber)
	{
		// send the left group notification
		((LevelControllableOutput) this.device).notifyLeftGroup(groupNumber);

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

	@Override
	public Measure<?, ?> getActivePower()
	{
		return (Measure<?, ?>) currentState.getState(
				SinglePhaseActivePowerMeasurementState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}

	@Override
	public void notifyNewActivePowerValue(Measure<?, ?> powerValue)
	{
		// update the state
		ActivePowerStateValue pValue = new ActivePowerStateValue();
		pValue.setValue(powerValue);
		currentState.setState(
				SinglePhaseActivePowerMeasurementState.class.getSimpleName(),
				new SinglePhaseActivePowerMeasurementState(pValue));

		// debug
		logger.log(LogService.LOG_DEBUG, "Device " + device.getDeviceId()
				+ " active power " + powerValue.toString());

		// notify the new measure
		((EnergyAndPowerMeteringLevelControllableOutput) device)
				.notifyNewActivePowerValue(powerValue);

	}

	@Override
	public Measure<?, ?> getActiveEnergyValue()
	{
		return (Measure<?, ?>) currentState.getState(
				SinglePhaseActiveEnergyState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}

	@Override
	public void notifyNewActiveEnergyValue(Measure<?, ?> value)
	{
		// update the state
		ActiveEnergyStateValue pValue = new ActiveEnergyStateValue();
		pValue.setValue(value);
		currentState.setState(
				SinglePhaseActiveEnergyState.class.getSimpleName(),
				new SinglePhaseActiveEnergyState(pValue));

		// debug
		logger.log(LogService.LOG_DEBUG, "Device " + device.getDeviceId()
				+ " active energy " + value.toString());

		// notify the new measure
		((EnergyAndPowerMeteringLevelControllableOutput) device)
				.notifyNewActiveEnergyValue(value);

	}
}
