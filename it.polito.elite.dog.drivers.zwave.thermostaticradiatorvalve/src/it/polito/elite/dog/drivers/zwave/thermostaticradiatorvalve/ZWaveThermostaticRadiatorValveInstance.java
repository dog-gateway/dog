/*
 * Dog  - Z-Wave
 * 
 * Copyright 2013 Dario Bonino 
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
package it.polito.elite.dog.drivers.zwave.thermostaticradiatorvalve;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.ThermostaticRadiatorValve;
import it.polito.elite.dog.core.library.model.state.ClimateScheduleState;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.state.TemperatureState;
import it.polito.elite.dog.core.library.model.statevalue.ClimateScheduleStateValue;
import it.polito.elite.dog.core.library.model.statevalue.TemperatureStateValue;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.drivers.zwave.ZWaveAPI;
import it.polito.elite.dog.drivers.zwave.model.DailyClimateSchedule;
import it.polito.elite.dog.drivers.zwave.model.commandclasses.ClimateSchedule;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Controller;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Device;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Instance;
import it.polito.elite.dog.drivers.zwave.network.ZWaveDriver;
import it.polito.elite.dog.drivers.zwave.network.info.ZWaveNodeInfo;
import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.measure.Measure;

import org.osgi.framework.BundleContext;

/**
 * @author bonino
 * 
 */
public class ZWaveThermostaticRadiatorValveInstance extends ZWaveDriver
		implements ThermostaticRadiatorValve
{

	// the class logger
	private LogHelper logger;

	public ZWaveThermostaticRadiatorValveInstance(ZWaveNetwork network,
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

	@Override
	public void setDaySchedule(Object[] switchPoints, Integer weekDay)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getDaySchedule(Integer weekDay)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cool()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void stopHeatingOrCooling()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setTemperatureAt(Measure<?, ?> temperature)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void heat()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public DeviceStatus getState()
	{
		return currentState;
	}

	@Override
	public void notifyStateChanged(State newState)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void newMessageFromHouse(Device deviceNode, Instance instanceNode,
			Controller controllerNode, String sValue)
	{
		// TODO Auto-generated method stub

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
	protected ZWaveNodeInfo createNodeInfo(int deviceId,
			Set<Integer> instancesId, boolean isController)
	{
		HashMap<Integer, Set<Integer>> instanceCommand = new HashMap<Integer, Set<Integer>>();

		HashSet<Integer> ccSet = new HashSet<Integer>();
		ccSet.add(ZWaveAPI.COMMAND_CLASS_CLIMATE_CONTROL_SCHEDULE);
		ccSet.add(ZWaveAPI.COMMAND_CLASS_THERMOSTAT_SETPOINT);

		for (Integer instanceId : instancesId)
		{
			instanceCommand.put(instanceId, ccSet);
		}
		
		ZWaveNodeInfo nodeInfo = new ZWaveNodeInfo(deviceId, instanceCommand,
				isController);

		return nodeInfo;
	}

	private void initializeStates()
	{
		// initialize the inner state

		// ----------- initialize the climate state values

		// Monday
		ClimateScheduleStateValue mondayScheduleStateValue = new ClimateScheduleStateValue();
		mondayScheduleStateValue.setFeature("weekDay", ClimateSchedule.MONDAY);
		mondayScheduleStateValue.setValue(new DailyClimateSchedule());

		// Tuesday
		ClimateScheduleStateValue tuesdayScheduleStateValue = new ClimateScheduleStateValue();
		tuesdayScheduleStateValue
				.setFeature("weekDay", ClimateSchedule.TUESDAY);
		tuesdayScheduleStateValue.setValue(new DailyClimateSchedule());

		// Wednesday
		ClimateScheduleStateValue wednesdayScheduleStateValue = new ClimateScheduleStateValue();
		wednesdayScheduleStateValue.setFeature("weekDay",
				ClimateSchedule.WEDNESDAY);
		wednesdayScheduleStateValue.setValue(new DailyClimateSchedule());

		// Thursday
		ClimateScheduleStateValue thursdayScheduleStateValue = new ClimateScheduleStateValue();
		thursdayScheduleStateValue.setFeature("weekDay",
				ClimateSchedule.THURSDAY);
		thursdayScheduleStateValue.setValue(new DailyClimateSchedule());

		// Friday
		ClimateScheduleStateValue fridayScheduleStateValue = new ClimateScheduleStateValue();
		fridayScheduleStateValue.setFeature("weekDay", ClimateSchedule.MONDAY);
		fridayScheduleStateValue.setValue(new DailyClimateSchedule());

		// Saturday
		ClimateScheduleStateValue saturdayScheduleStateValue = new ClimateScheduleStateValue();
		saturdayScheduleStateValue
				.setFeature("weekDay", ClimateSchedule.MONDAY);
		saturdayScheduleStateValue.setValue(new DailyClimateSchedule());

		// Sunday
		ClimateScheduleStateValue sundayScheduleStateValue = new ClimateScheduleStateValue();
		sundayScheduleStateValue.setFeature("weekDay", ClimateSchedule.MONDAY);
		sundayScheduleStateValue.setValue(new DailyClimateSchedule());

		// set the climate schedule state (covers all the week)
		this.currentState.setState(ClimateScheduleState.class.getSimpleName(),
				new ClimateScheduleState(mondayScheduleStateValue,
						tuesdayScheduleStateValue, wednesdayScheduleStateValue,
						thursdayScheduleStateValue, fridayScheduleStateValue,
						saturdayScheduleStateValue, sundayScheduleStateValue));

		// initialize the state
		this.currentState.setState(TemperatureState.class.getSimpleName(),
				new TemperatureState(new TemperatureStateValue()));

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
