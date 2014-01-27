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
import it.polito.elite.dog.core.library.model.devicecategory.ElectricalSystem;
import it.polito.elite.dog.core.library.model.devicecategory.EnergyAndPowerMeteringLevelControllableOutput;
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
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
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

	public ZWavePowerMeteringLevelControllableOutputDriverInstance(
			ZWaveNetwork network, ControllableDevice device, int deviceId,
			Set<Integer> instancesId, int gatewayNodeId, int updateTimeMillis,
			int stepPercentage, BundleContext context)
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
			this.currentState.setState(SinglePhaseActiveEnergyState.class
					.getSimpleName(), new SinglePhaseActiveEnergyState(
					new ActiveEnergyStateValue()));
		}

		this.currentState.setState(SinglePhaseActivePowerMeasurementState.class
				.getSimpleName(), new SinglePhaseActivePowerMeasurementState(
				new ActivePowerStateValue()));

		this.currentState.setState(LevelState.class.getSimpleName(),
				new LevelState(new LevelStateValue()));

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
		logger.log(
				LogService.LOG_DEBUG,
				ZWavePowerMeteringLevelControllableOutputDriver.LOG_ID
						+ "Device "
						+ device.getDeviceId()
						+ " is now "
						+ ((OnOffState) newState).getCurrentStateValue()[0]
								.getValue());
		((ElectricalSystem) device).notifyStateChanged(newState);

	}

	@Override
	public void newMessageFromHouse(Device deviceNode, Instance instanceNode,
			Controller controllerNode, String sValue)
	{
		this.deviceNode = deviceNode;

		// Read the value associated with the right CommandClass.

		// switch multi-level
		int nLevel = 0;
		CommandClasses ccEntry = instanceNode
				.getCommandClass(ZWaveAPI.COMMAND_CLASS_SWITCH_MULTILEVEL);

		// Check if it is a real new value or if it is an old one
		if (ccEntry != null)
		{

			if (ccEntry != null)
			{
				nLevel = ccEntry.getLevelAsInt();

				if (nLevel > 0)
					changeCurrentState(OnOffState.ON, nLevel);
				else
					changeCurrentState(OnOffState.OFF, nLevel);
			}
		}

		// meter
		CommandClasses ccElectricityEntry = instanceNode.getCommandClasses()
				.get(ZWaveAPI.COMMAND_CLASS_METER);

		// Check if it is a real new value or if it is an old one. We can use
		// one of the cc available
		DataElemObject instance0 = ccElectricityEntry.get("0");

		if (instance0 != null)
		{
			// handle energy data if available
			DataElemObject energyEntry = ccElectricityEntry.get("0");
			if (energyEntry != null)
			{
				double activeEnergy = Double.valueOf(energyEntry
						.getDataElemValue("val").toString());
				notifyNewActiveEnergyValue(DecimalMeasure.valueOf(activeEnergy
						+ " " + SI.KILO(SI.WATT.times(NonSI.HOUR)).toString()));
			}

			// handle power data if available
			DataElemObject powerEntry = ccElectricityEntry.get("2");
			if (powerEntry != null)
			{
				double activePower = Double.valueOf(powerEntry
						.getDataElemValue("val").toString());
				notifyNewActivePowerValue(DecimalMeasure.valueOf(activePower
						+ " " + SI.WATT.toString()));
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
		// --------- update the on-off state ----------
		State state = currentState.getState(OnOffState.class.getSimpleName());

		if (state != null)
		{
			// get the current state value
			String currentStateValue = (String) state.getCurrentStateValue()[0]
					.getValue();

			// if the current states it is different from the new state
			if (!currentStateValue.equalsIgnoreCase(OnOffValue))
			{
				// set the new state to on or off...
				if (OnOffValue.equalsIgnoreCase(OnOffState.ON))
				{
					// update the state
					OnOffState onState = new OnOffState(new OnStateValue());
					currentState.setState(OnOffState.class.getSimpleName(),
							onState);

					logger.log(
							LogService.LOG_DEBUG,
							ZWavePowerMeteringLevelControllableOutputDriver.LOG_ID
									+ "Device "
									+ device.getDeviceId()
									+ " is now "
									+ ((OnOffState) onState)
											.getCurrentStateValue()[0]
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
							ZWavePowerMeteringLevelControllableOutputDriver.LOG_ID
									+ "Device "
									+ device.getDeviceId()
									+ " is now "
									+ ((OnOffState) offState)
											.getCurrentStateValue()[0]
											.getValue());

				}
			}

		}
		// -------- update the level state ---------

		// create the new state value
		LevelStateValue pValue = new LevelStateValue();
		pValue.setValue(nLevel);

		// get the current state
		LevelState currentLevelState = (LevelState) currentState
				.getState(LevelState.class.getSimpleName());

		// if not null update the state
		if (currentLevelState != null)
			currentLevelState.getCurrentStateValue()[0] = pValue;

		// debug
		logger.log(LogService.LOG_DEBUG,
				ZWavePowerMeteringLevelControllableOutputDriver.LOG_ID
						+ "Device " + device.getDeviceId() + " dimmer at "
						+ nLevel);

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
					ZWaveAPI.COMMAND_CLASS_SWITCH_MULTILEVEL, value.toString()+", 0");
	}

	@Override
	public void stepDown()
	{
		// get the current level value
		int currentValue = (Integer) currentState.getState(
				LevelState.class.getSimpleName()).getCurrentStateValue()[0]
				.getValue();

		//set to 100%
		if (currentValue == 255)
			currentValue = 100;
		
		// decrease by step percentage
		currentValue = Math.max(0,
				currentValue-this.stepPercentage);

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
		currentValue = Math.min(100,
				currentValue + this.stepPercentage);

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
		logger.log(LogService.LOG_DEBUG,
				ZWavePowerMeteringLevelControllableOutputDriver.LOG_ID
						+ "Device " + device.getDeviceId() + " active power "
						+ powerValue.toString());

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
		logger.log(LogService.LOG_DEBUG,
				ZWavePowerMeteringLevelControllableOutputDriver.LOG_ID
						+ "Device " + device.getDeviceId() + " active energy "
						+ value.toString());

		// notify the new measure
		((EnergyAndPowerMeteringLevelControllableOutput) device)
				.notifyNewActiveEnergyValue(value);

	}
}
