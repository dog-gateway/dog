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
package it.polito.elite.dog.drivers.zwave.meteringpoweroutlet;

import it.polito.elite.dog.drivers.zwave.ZWaveAPI;
import it.polito.elite.dog.drivers.zwave.model.zway.json.CommandClasses;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Controller;
import it.polito.elite.dog.drivers.zwave.model.zway.json.DataElemObject;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Device;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Instance;
import it.polito.elite.dog.drivers.zwave.network.ZWaveDriverInstance;
import it.polito.elite.dog.drivers.zwave.network.info.ZWaveNodeInfo;
import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.ElectricalSystem;
import it.polito.elite.dog.core.library.model.devicecategory.EnergyMeteringPowerOutlet;
import it.polito.elite.dog.core.library.model.devicecategory.MeteringPowerOutlet;
import it.polito.elite.dog.core.library.model.devicecategory.PowerMeteringPowerOutlet;
import it.polito.elite.dog.core.library.model.state.OnOffState;
import it.polito.elite.dog.core.library.model.state.SinglePhaseActiveEnergyState;
import it.polito.elite.dog.core.library.model.state.SinglePhaseActivePowerMeasurementState;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.ActiveEnergyStateValue;
import it.polito.elite.dog.core.library.model.statevalue.ActivePowerStateValue;
import it.polito.elite.dog.core.library.model.statevalue.OffStateValue;
import it.polito.elite.dog.core.library.model.statevalue.OnStateValue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.quantity.Power;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.measure.unit.UnitFormat;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class ZWaveMeteringPowerOutletInstance extends ZWaveDriverInstance implements
		MeteringPowerOutlet
{
	// the class logger
	private LogHelper logger;

	public ZWaveMeteringPowerOutletInstance(ZWaveNetwork network,
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
		// create the var and va units
		Unit<Power> VAR = SI.WATT.alternate("var");

		// add unit of measure aliases (to fix notation problems...)
		UnitFormat uf = UnitFormat.getInstance();
		uf.alias(SI.WATT.times(NonSI.HOUR), "Wh");
		uf.label(SI.KILO(SI.WATT.times(NonSI.HOUR)), "kWh");
		uf.alias(VAR.times(NonSI.HOUR), "Varh");
		uf.label(SI.KILO(VAR.times(NonSI.HOUR)), "kVarh");

		// initialize the state
		this.currentState.setState(OnOffState.class.getSimpleName(),
				new OnOffState(new OffStateValue()));
		this.currentState.setState(
				SinglePhaseActiveEnergyState.class.getSimpleName(),
				new SinglePhaseActiveEnergyState(new ActiveEnergyStateValue()));
		this.currentState.setState(SinglePhaseActivePowerMeasurementState.class
				.getSimpleName(), new SinglePhaseActivePowerMeasurementState(
				new ActivePowerStateValue()));

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
	public void notifyStateChanged(State newState)
	{
		// debug
		logger.log(LogService.LOG_DEBUG, ZWaveMeteringPowerOutletDriver.LOG_ID
				+ "Device " + device.getDeviceId() + " is now "
				+ ((OnOffState) newState).getCurrentStateValue()[0].getValue());
		((ElectricalSystem) device).notifyStateChanged(newState);

	}

	@Override
	public void newMessageFromHouse(Device deviceNode, Instance instanceNode,
			Controller controllerNode, String sValue)
	{
		this.deviceNode = deviceNode;

		// Read the value associated with the right CommandClass:
		// meter has only one instance(0) and meter values are in
		// devices.X.instances.0.commandClasses.50.data.0 (KwH) and
		// devices.X.instances.0.commandClasses.50.data.2 (W)
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
					double activeEnergy = Double.valueOf(energyEntry
							.getDataElemValue("val").toString());
					notifyNewActiveEnergyValue(DecimalMeasure
							.valueOf(activeEnergy
									+ " "
									+ SI.KILO(SI.WATT.times(NonSI.HOUR))
											.toString()));
				}

				// handle power data if available
				DataElemObject powerEntry = ccElectricityEntry.get("2");
				if (powerEntry != null)
				{
					double activePower = Double.valueOf(powerEntry
							.getDataElemValue("val").toString());
					notifyNewActivePowerValue(DecimalMeasure
							.valueOf(activePower + " " + SI.WATT.toString()));
				}
			}
			/*
			 * else { // increment counter nFailedUpdate++;
			 * 
			 * // log a message after 5 failed update if (nFailedUpdate >= 5) {
			 * logger.log(LogService.LOG_WARNING,
			 * ZWaveMeteringPowerOutletDriver.LOG_ID + "Device " +
			 * device.getDeviceId() +
			 * " doesn't respond after 5 update requests");
			 * 
			 * nFailedUpdate = 0; } }
			 */

			// always update on-off state
			int nLevel = 0;
			CommandClasses ccEntryOnOff = instanceNode.getCommandClasses().get(
					ZWaveAPI.COMMAND_CLASS_SWITCH_BINARY);
			if (ccEntryOnOff != null)
				nLevel = ccEntryOnOff.getLevelAsInt();

			if (nLevel > 0)
				changeCurrentState(OnOffState.ON);
			else
				changeCurrentState(OnOffState.OFF);
		}
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
			} else
			{
				newState = new OnOffState(new OffStateValue());
			}
			// ... then set the new state for the device and throw a state
			// changed notification
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
	public void on()
	{
		// Sends on command to all instances, probably only one in this case
		for (Integer instanceId : nodeInfo.getInstanceSet())
			network.write(nodeInfo.getDeviceNodeId(), instanceId,
					ZWaveAPI.COMMAND_CLASS_SWITCH_BINARY, "255");
	}

	@Override
	public void off()
	{
		// Sends off command to all instances, probably only one in this case
		for (Integer instanceId : nodeInfo.getInstanceSet())
			network.write(nodeInfo.getDeviceNodeId(), instanceId,
					ZWaveAPI.COMMAND_CLASS_SWITCH_BINARY, "0");
	}

	@Override
	public Measure<?, ?> getActiveEnergyValue()
	{
		return (Measure<?, ?>) currentState.getState(
				SinglePhaseActiveEnergyState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}

	@Override
	public Measure<?, ?> getActivePower()
	{
		return (Measure<?, ?>) currentState.getState(
				SinglePhaseActivePowerMeasurementState.class.getSimpleName())
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
		logger.log(
				LogService.LOG_DEBUG,
				ZWaveMeteringPowerOutletDriver.LOG_ID + "Device "
						+ device.getDeviceId() + " active energy "
						+ value.toString());

		// notify the new measure
		((EnergyMeteringPowerOutlet) device).notifyNewActiveEnergyValue(value);

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
		logger.log(LogService.LOG_DEBUG, ZWaveMeteringPowerOutletDriver.LOG_ID
				+ "Device " + device.getDeviceId() + " active power "
				+ powerValue.toString());

		// notify the new measure
		((PowerMeteringPowerOutlet) device)
				.notifyNewActivePowerValue(powerValue);
	}

	@Override
	protected ZWaveNodeInfo createNodeInfo(int deviceId,
			Set<Integer> instancesId, boolean isController)
	{
		HashMap<Integer, Set<Integer>> instanceCommand = new HashMap<Integer, Set<Integer>>();

		// meter has only one instance(0) and meter values are in
		// devices.X.instances.0.commandClasses.50.data.0 (KwH) and
		// devices.X.instances.0.commandClasses.50.data.2 (W)
		HashSet<Integer> ccSet = new HashSet<Integer>();
		ccSet.add(ZWaveAPI.COMMAND_CLASS_METER);
		ccSet.add(ZWaveAPI.COMMAND_CLASS_SWITCH_BINARY);
		for (Integer instanceId : instancesId)
		{
			instanceCommand.put(instanceId, ccSet);
		}
		ZWaveNodeInfo nodeInfo = new ZWaveNodeInfo(deviceId, instanceCommand,
				isController);

		return nodeInfo;
	}

	@Override
	public void notifyNewPowerFactorValue(Measure<?, ?> powerFactor)
	{
		// Nothing to do: not supported by device...
	}

	@Override
	public void notifyNewReactiveEnergyValue(Measure<?, ?> value)
	{
		// Nothing to do: not supported by device...
	}

	@Override
	public Measure<?, ?> getReactiveEnergyValue()
	{
		// Nothing to do: not supported by device...
		return null;
	}

	@Override
	public Measure<?, ?> getPowerFactor()
	{
		// Nothing to do: not supported by device...
		return null;
	}

}
