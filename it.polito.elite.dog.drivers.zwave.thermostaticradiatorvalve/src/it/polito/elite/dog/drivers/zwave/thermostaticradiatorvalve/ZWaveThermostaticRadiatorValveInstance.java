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
import it.polito.elite.dog.core.library.model.climate.ClimateScheduleSwitchPoint;
import it.polito.elite.dog.core.library.model.climate.DailyClimateSchedule;
import it.polito.elite.dog.core.library.model.devicecategory.Controllable;
import it.polito.elite.dog.core.library.model.devicecategory.ThermostaticRadiatorValve;
import it.polito.elite.dog.core.library.model.state.ClimateScheduleState;
import it.polito.elite.dog.core.library.model.state.TemperatureState;
import it.polito.elite.dog.core.library.model.statevalue.ClimateScheduleStateValue;
import it.polito.elite.dog.core.library.model.statevalue.StateValue;
import it.polito.elite.dog.core.library.model.statevalue.TemperatureStateValue;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.drivers.zwave.ZWaveAPI;
import it.polito.elite.dog.drivers.zwave.model.commandclasses.ThermostatMode;
import it.polito.elite.dog.drivers.zwave.model.zway.json.CommandClasses;
import it.polito.elite.dog.drivers.zwave.model.zway.json.CommandClassesData;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Controller;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Device;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Instance;
import it.polito.elite.dog.drivers.zwave.network.ZWaveDriverInstance;
import it.polito.elite.dog.drivers.zwave.network.info.ZWaveNodeInfo;
import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;
import it.polito.elite.dog.drivers.zwave.persistence.JSONPersistenceManager;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * @author bonino
 * 
 */
public class ZWaveThermostaticRadiatorValveInstance extends ZWaveDriverInstance
		implements ThermostaticRadiatorValve
{

	// the class logger
	private LogHelper logger;

	// the schedule persistent store
	private JSONPersistenceManager persistentStore;

	// a flag for signaling that this instance is ready to be updated on
	// schedules
	private boolean isReady;

	public ZWaveThermostaticRadiatorValveInstance(ZWaveNetwork network,
			ControllableDevice device, int deviceId, Set<Integer> instancesId,
			int gatewayNodeId, int updateTimeMillis,
			String persistenceStoreDir, BundleContext context)
	{
		super(network, device, deviceId, instancesId, gatewayNodeId,
				updateTimeMillis, context);

		// create a logger
		this.logger = new LogHelper(context);

		// not ready
		this.isReady = false;

		// create the persistent store given the schedule directory
		File scheduleDirFile = new File(persistenceStoreDir);

		if (scheduleDirFile.exists())
		{
			// persistence can be managed

			// check if a file associated to the current thermostatic valve
			// exists
			String persistentStoreName = persistenceStoreDir + File.separator
					+ deviceId + "-schedule.json";

			try
			{
				// build a file object pointing at the persistence store
				this.persistentStore = new JSONPersistenceManager(
						persistentStoreName);
			}
			catch (Exception e)
			{
				// explicitly set the persistent store at null
				this.persistentStore = null;

				// warning
				this.logger
						.log(LogService.LOG_WARNING,
								"Unable to create/acquire the persistent store for climate schedules, running in-memory only: all changes will be lost upon restart.",
								e);
			}
		}
		else
		{
			// explicitly set the persistent store at null
			this.persistentStore = null;

			// warning
			this.logger
					.log(LogService.LOG_WARNING,
							"Unable to find the persistent store for climate schedules, running in-memory only: all changes will be lost upon restart.");
		}

		// initialize states
		this.initializeStates();
	}

	@Override
	public void setDailyClimateSchedule(DailyClimateSchedule daySchedule)
	{
		// get the state values
		ClimateScheduleStateValue stateValues[] = (ClimateScheduleStateValue[]) this.currentState
				.getState(ClimateScheduleState.class.getSimpleName())
				.getCurrentStateValue();

		// prepare the array of schedules to sync on file
		DailyClimateSchedule schedules[] = new DailyClimateSchedule[stateValues.length];

		// iterate over state values to find which value should be replaced
		for (int i = 0; i < stateValues.length; i++)
		{
			// get the value
			schedules[i] = (DailyClimateSchedule) stateValues[i].getValue();

			// check if the current one must be replaced
			if (schedules[i].getWeekDay() == daySchedule.getWeekDay())
			{
				// replace
				stateValues[i].setValue(daySchedule);
				schedules[i] = daySchedule;
			}
		}

		// notify the change
		this.notifyChangedDailyClimateSchedule(daySchedule);

		// update the status
		this.updateStatus();

		// persist the schedules
		try
		{
			this.persistentStore.persist(schedules);
		}
		catch (Exception e)
		{
			this.logger.log(LogService.LOG_ERROR,
					"Unable to save schedule data.", e);
		}

	}

	@Override
	public void setClimateSchedule(DailyClimateSchedule[] dailySchedules)
	{
		// create the climate state values placeholder
		ClimateScheduleStateValue values[] = new ClimateScheduleStateValue[dailySchedules.length];

		// store the various state values
		for (int i = 0; i < dailySchedules.length; i++)
		{
			values[i] = new ClimateScheduleStateValue();
			values[i].setValue(dailySchedules[i]);
		}

		// set the climate schedule state (covers all the week)
		currentState.setState(ClimateScheduleState.class.getSimpleName(),
				new ClimateScheduleState(values));

		// notify the change
		this.notifyChangedWeeklyClimateSchedule(dailySchedules);

		// update the status
		this.updateStatus();

		// persist the schedules
		try
		{
			this.persistentStore.persist(dailySchedules);
		}
		catch (Exception e)
		{
			this.logger.log(LogService.LOG_ERROR,
					"Unable to save schedule data.", e);
		}

	}

	@Override
	public Object[] getDaySchedule(Integer weekDay)
	{
		// prepare the array to return
		Object switchPoints[] = null;

		// get the current state value registered as values of the climate
		// schedule state
		StateValue[] states = this.currentState.getState(
				ClimateScheduleState.class.getSimpleName())
				.getCurrentStateValue();

		// look for the state value associated to the given weekday
		for (int i = 0; i < states.length; i++)
		{
			// get the current daily schedule
			DailyClimateSchedule schedule = (DailyClimateSchedule) ((ClimateScheduleStateValue) states[i])
					.getValue();

			// if the schedule is the one searched for
			if (schedule.getWeekDay() == weekDay)
			{
				// get the switch points
				switchPoints = schedule.getSwitchPoints();

				// stop the cycle
				break;
			}
		}
		return switchPoints;
	}

	@Override
	/**
	 * Cool down the current set point by 0.5 degrees
	 */
	public void cool()
	{
		// update the mode
		for (Integer instanceId : nodeInfo.getInstanceSet())
			network.write(nodeInfo.getDeviceNodeId(), instanceId,
					ZWaveAPI.COMMAND_CLASS_THERMOSTAT_MODE, ""
							+ ThermostatMode.MODE_COOL);

		// notify
		this.notifyCool();

	}

	@Override
	public void stopHeatingOrCooling()
	{
		// updated the mode
		for (Integer instanceId : nodeInfo.getInstanceSet())
			network.write(nodeInfo.getDeviceNodeId(), instanceId,
					ZWaveAPI.COMMAND_CLASS_THERMOSTAT_MODE, ""
							+ ThermostatMode.MODE_OFF);

		// notify
		this.notifyStoppedHeatingOrCooling();

	}

	@Override
	public void setTemperatureAt(Measure<?, ?> temperature)
	{
		// get the value in celsius
		String celsius = String.format("%02.02f", temperature.getValue());
		for (Integer instanceId : nodeInfo.getInstanceSet())
			network.write(nodeInfo.getDeviceNodeId(), instanceId,
					ZWaveAPI.COMMAND_CLASS_THERMOSTAT_SETPOINT,
					ThermostatMode.MODE_HEAT + "," + celsius);
	}

	@Override
	/*
	 * 
	 */
	public void heat()
	{

		// updated the setPoint
		for (Integer instanceId : nodeInfo.getInstanceSet())
			network.write(nodeInfo.getDeviceNodeId(), instanceId,
					ZWaveAPI.COMMAND_CLASS_THERMOSTAT_MODE, ""
							+ ThermostatMode.MODE_HEAT);

		// notify
		this.notifyHeat();

	}

	@Override
	public DeviceStatus getState()
	{
		return currentState;
	}

	@Override
	public void newMessageFromHouse(Device deviceNode, Instance instanceNode,
			Controller controllerNode, String sValue)
	{
		// check ready
		if (this.isReady)
		{
			// update deviceNode
			this.deviceNode = deviceNode;

			// get the thermostat set point command class data
			CommandClasses thermostatCC = instanceNode
					.getCommandClass(ZWaveAPI.COMMAND_CLASS_THERMOSTAT_SETPOINT);

			// get the last update time if any
			long globalUpdateTime = thermostatCC.getUpdateTime();

			// check if the instance contains only one value
			if (globalUpdateTime > 0)
			{
				// check if the values are up-to-date
				if (this.lastUpdateTime < globalUpdateTime)
				{
					// update the last update time
					this.lastUpdateTime = globalUpdateTime;

					// read the current set point
					double setPoint = ((Number) thermostatCC
							.getCommandClassesData().getAllData().get("1")
							.getDataElemValue(CommandClassesData.FIELD_VAL))
							.doubleValue();

					// read the current unit of measure
					String unitOfMeasure = (String) thermostatCC
							.getCommandClassesData()
							.getAllData()
							.get("1")
							.getDataElemValue(
									CommandClassesData.FIELD_SCALESTRING);

					// trim the scale string
					unitOfMeasure = unitOfMeasure.replace("grd", "");
					unitOfMeasure = unitOfMeasure.trim();

					// convert to a decimal measure
					DecimalMeasure<?> setPointTemperature = DecimalMeasure
							.valueOf(setPoint + " " + unitOfMeasure);

					TemperatureStateValue setPointStateValue = new TemperatureStateValue();
					setPointStateValue.setValue(setPointTemperature);
					TemperatureState setPointState = new TemperatureState(
							setPointStateValue);

					// update the inner set point state
					this.currentState.setState(
							TemperatureState.class.getSimpleName(),
							setPointState);

					// notify the temperature change
					this.notifyChangedDesiredTemperatureSetting(setPointTemperature);

					// notify the new state
					this.updateStatus();
				}

			}

		}

	}

	@Override
	public void updateStatus()
	{
		// update the monitor admin status snapshot
		((Controllable) this.device).updateStatus();
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
		// ccSet.add(ZWaveAPI.COMMAND_CLASS_CLIMATE_CONTROL_SCHEDULE);
		ccSet.add(ZWaveAPI.COMMAND_CLASS_THERMOSTAT_SETPOINT);
		// ccSet.add(ZWaveAPI.COMMAND_CLASS_THERMOSTAT_MODE);

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
		// get the initial state of the device in a separate thread to avoid
		// blocking the attachment algorithm
		Runnable worker = new Runnable()
		{
			public void run()
			{
				DailyClimateSchedule[] schedules;

				// check if the persistent store is available or not, and if it
				// can be
				// used/created or not
				if ((persistentStore != null) && (persistentStore.exists()))
				{

					// if exists, load the stored schedules
					schedules = persistentStore
							.load(DailyClimateSchedule[].class);

				}
				else
				{
					// create the schedules
					schedules = new DailyClimateSchedule[7];

					// create empty schedules
					for (int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++)
						schedules[i - 1] = new DailyClimateSchedule(i);

					// store the schedules, if empty? no at the moment
				}

				// ----------- initialize the climate state values ----

				// create the climate state values placeholder
				ClimateScheduleStateValue values[] = new ClimateScheduleStateValue[schedules.length];

				// store the various state values
				for (int i = 0; i < schedules.length; i++)
				{
					values[i] = new ClimateScheduleStateValue();
					values[i].setValue(schedules[i]);
				}

				// set the climate schedule state (covers all the week)
				currentState.setState(
						ClimateScheduleState.class.getSimpleName(),
						new ClimateScheduleState(values));

				// initialize the temperature state
				currentState.setState(TemperatureState.class.getSimpleName(),
						new TemperatureState(new TemperatureStateValue()));

				// read the current state
				network.read(nodeInfo, true);

				// set the is ready flag at true
				isReady = true;
			}
		};

		Thread workerThread = new Thread(worker);
		workerThread.start();
	}

	public void checkTime(Calendar clockTick)
	{
		if (this.isReady)
		{
			// given a time instant expressed as a calendar object, check if
			// there
			// is any switch point that needs activation

			// get the stored switch points
			ClimateScheduleStateValue[] allScheduledSwitchPoints = (ClimateScheduleStateValue[]) this.currentState
					.getState(ClimateScheduleState.class.getSimpleName())
					.getCurrentStateValue();

			// iterate over the switch points
			for (int i = 0; i < allScheduledSwitchPoints.length; i++)
			{
				// get the day schedule
				DailyClimateSchedule schedule = (DailyClimateSchedule) allScheduledSwitchPoints[i]
						.getValue();

				if (schedule.getWeekDay() == clockTick
						.get(Calendar.DAY_OF_WEEK))
				{

					// get the switch point
					ClimateScheduleSwitchPoint switchPoint = schedule
							.getSwitchPoint(clockTick);

					// set the new set point if available
					if (switchPoint != null)
						this.setTemperatureAt(switchPoint
								.desiredTemperatureAsMeasure());

					// break as no more switch points can be activated for this
					// instant of time
					break;
				}
			}

		}
	}

	@Override
	public void notifyChangedDailyClimateSchedule(
			DailyClimateSchedule daySchedule)
	{
		((ThermostaticRadiatorValve) this.device)
				.notifyChangedDailyClimateSchedule(daySchedule);
	}

	@Override
	public void notifyChangedDesiredTemperatureSetting(
			Measure<?, ?> newTemperatureValue)
	{
		((ThermostaticRadiatorValve) this.device)
				.notifyChangedDesiredTemperatureSetting(newTemperatureValue);
	}

	@Override
	public void notifyCool()
	{
		((ThermostaticRadiatorValve) this.device).notifyCool();
	}

	@Override
	public void notifyHeat()
	{
		((ThermostaticRadiatorValve) this.device).notifyHeat();
	}

	@Override
	public void notifyChangedWeeklyClimateSchedule(
			DailyClimateSchedule[] dailySchedules)
	{
		((ThermostaticRadiatorValve) this.device)
				.notifyChangedWeeklyClimateSchedule(dailySchedules);
	}

	@Override
	public void notifyStoppedHeatingOrCooling()
	{
		((ThermostaticRadiatorValve) this.device)
				.notifyStoppedHeatingOrCooling();
	}

}
