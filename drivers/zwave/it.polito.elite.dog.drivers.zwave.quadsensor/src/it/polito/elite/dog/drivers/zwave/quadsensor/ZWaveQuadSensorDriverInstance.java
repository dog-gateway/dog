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
package it.polito.elite.dog.drivers.zwave.quadsensor;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.Controllable;
import it.polito.elite.dog.core.library.model.devicecategory.QuadSensor;
import it.polito.elite.dog.core.library.model.state.HumidityMeasurementState;
import it.polito.elite.dog.core.library.model.state.LightIntensityState;
import it.polito.elite.dog.core.library.model.state.MovementState;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.state.TemperatureState;
import it.polito.elite.dog.core.library.model.statevalue.HumidityStateValue;
import it.polito.elite.dog.core.library.model.statevalue.LevelStateValue;
import it.polito.elite.dog.core.library.model.statevalue.MovingStateValue;
import it.polito.elite.dog.core.library.model.statevalue.NotMovingStateValue;
import it.polito.elite.dog.core.library.model.statevalue.TemperatureStateValue;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.drivers.zwave.ZWaveAPI;
import it.polito.elite.dog.drivers.zwave.model.SensorType;
import it.polito.elite.dog.drivers.zwave.model.zway.json.CommandClasses;
import it.polito.elite.dog.drivers.zwave.model.zway.json.CommandClassesData;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Controller;
import it.polito.elite.dog.drivers.zwave.model.zway.json.DataElemObject;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Device;
import it.polito.elite.dog.drivers.zwave.model.zway.json.Instance;
import it.polito.elite.dog.drivers.zwave.network.ZWaveDriverInstance;
import it.polito.elite.dog.drivers.zwave.network.info.ZWaveNodeInfo;
import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.measure.unit.UnitFormat;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class ZWaveQuadSensorDriverInstance extends ZWaveDriverInstance
		implements QuadSensor
{

	// the class logger
	private LogHelper logger;

	// sensor-level update times
	private long temperatureUpdateTime = 0;
	private long humidityUpdateTime = 0;
	private long luminiscenceUpdateTime = 0;

	// the group set
	private HashSet<Integer> groups;

	public ZWaveQuadSensorDriverInstance(ZWaveNetwork network,
			ControllableDevice device, int deviceId, Set<Integer> instancesId,
			int gatewayNodeId, int updateTimeMillis, BundleContext context)
	{
		super(network, device, deviceId, instancesId, gatewayNodeId,
				updateTimeMillis, context);

		// build inner data structures
		this.groups = new HashSet<Integer>();

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
		// set up unit of measures
		Unit.ONE.alternate("%");
		UnitFormat uf = UnitFormat.getInstance();
		uf.label(SI.CELSIUS, "C");
		uf.alias(SI.CELSIUS, "C");
		uf.alias(SI.LUX, "Lux");

		// initialize the state
		this.currentState.setState(TemperatureState.class.getSimpleName(),
				new TemperatureState(new TemperatureStateValue()));
		this.currentState.setState(
				HumidityMeasurementState.class.getSimpleName(),
				new HumidityMeasurementState(new HumidityStateValue()));
		this.currentState.setState(LightIntensityState.class.getSimpleName(),
				new LightIntensityState(new LevelStateValue()));
		this.currentState.setState(MovementState.class.getSimpleName(),
				new MovementState(new NotMovingStateValue()));

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
		// update deviceNode
		this.deviceNode = deviceNode;
		
		// the state change flags
		boolean measureUpdated = false;
		boolean movementUpdated = false;

		// Read the value for temperature, humidity or light intensity.
		CommandClasses ccInst = instanceNode
				.getCommandClass(ZWaveAPI.COMMAND_CLASS_SENSOR_MULTILEVEL);

		// Check if it is a real new value or if it is an old one
		long globalUpdateTime = ccInst.getValUpdateTime();

		// check if the instance contains only one value
		if (globalUpdateTime > 0)
		{
			// check if the values are up-to-date
			if (this.lastUpdateTime < globalUpdateTime)
			{
				// update last update time
				lastUpdateTime = ccInst.getValUpdateTime();
				nFailedUpdate = 0;

				// Reads values and sensorType
				double measure = ccInst.getVal();
				String sensorType = ccInst.getSensorType();

				// parse unit of measure
				String unitOfMeasure = (String) ccInst.getCommandClassesData()
						.getDataElemValue(CommandClassesData.FIELD_SCALESTRING);

				// forward to the right method
				measureUpdated = this.forwardMeasure(measure, unitOfMeasure, sensorType,
						this.lastUpdateTime);

			}

		}
		else if (ccInst.getValUpdateTime() < 0)
		{
			// handle the case in which instances are more complex than usual
			// (e.g. in the ST814 case) and data is hidden in numeric keys.
			// TODO: check if this is the best way (I have some doubt on it)

			// iterate over numeric keys
			Map<String, DataElemObject> cmdClassData = ccInst
					.getCommandClassesData().getAllData();

			for (String key : cmdClassData.keySet())
			{
				// check for numeric key
				try
				{
					// check if the key is a number, otherwise an exception will
					// be thrown and caught
					Integer.valueOf(key);

					// get the element data associated to the key
					DataElemObject sensorData = cmdClassData.get(key);

					// check the last update time
					long updateTime = sensorData.getUpdateTime();

					// Read value
					double measure = Double.valueOf(sensorData
							.getDataElemValue(CommandClassesData.FIELD_VAL)
							.toString());

					// Read sensorType
					String sensorType = (String) sensorData
							.getDataElemValue(CommandClassesData.FIELD_SENSORTYPE);

					// parse unit of measure
					String unitOfMeasure = (String) sensorData
							.getDataElemValue(CommandClassesData.FIELD_SCALESTRING);

					// forward to the right method
					measureUpdated = this.forwardMeasure(measure, unitOfMeasure, sensorType,
							updateTime);

				}
				catch (NumberFormatException ne)
				{
					// not a number, simply ignore it
				}
			}

		}

		// Read the value for the movement data.
		// Read the value associated with the right CommandClass.
		CommandClasses ccEntry = instanceNode
				.getCommandClass(ZWaveAPI.COMMAND_CLASS_SENSOR_BINARY);

		if (ccEntry != null)
			// notify open/close only if changed
			movementUpdated = changeMovementState((ccEntry.getLevelAsBoolean() ? MovementState.ISMOVING
					: MovementState.NOTMOVING));

		if(measureUpdated || movementUpdated)
			this.updateStatus();
	}

	/**
	 * Check if the current state has been changed. In that case, fire a state
	 * change message, otherwise it does nothing
	 * 
	 * @param movementState
	 *            .ISMOVING or MovementState.NOTMOVING
	 */
	private boolean changeMovementState(String movementState)
	{
		// the state change flag
		boolean stateChanged = false;

		// the current state
		String currentStateValue = "";
		State state = currentState
				.getState(MovementState.class.getSimpleName());

		if (state != null)
			currentStateValue = (String) state.getCurrentStateValue()[0]
					.getValue();

		// if the current states it is different from the new state
		if (!currentStateValue.equalsIgnoreCase(movementState))
		{
			// set the new state to open or close...
			if (movementState.equalsIgnoreCase(MovementState.ISMOVING))
			{
				// update the state
				MovementState movState = new MovementState(
						new MovingStateValue());
				currentState.setState(MovementState.class.getSimpleName(),
						movState);

				this.notifyStartedMovement(); // notify moving
			}
			else
			{
				// update the state
				MovementState movState = new MovementState(
						new NotMovingStateValue());
				currentState.setState(MovementState.class.getSimpleName(),
						movState);

				this.notifyCeasedMovement(); // notify not moving

			}

			logger.log(LogService.LOG_DEBUG, "Device " + device.getDeviceId()
					+ " value is now " + movementState);

			stateChanged = true;
		}

		return stateChanged;
	}

	private void changeTemperatureState(double measure, String unitOfMeasure)
	{
		// build the temperature measure
		DecimalMeasure<?> temperatureValue = DecimalMeasure.valueOf(measure
				+ " "
				+ (unitOfMeasure.contains("C") ? SI.CELSIUS.toString()
						: NonSI.FAHRENHEIT.toString()));

		// if the given temperature is null, than the network-level
		// value is not up-to-date
		if (temperatureValue != null)
		{
			// update the state
			TemperatureStateValue pValue = new TemperatureStateValue();
			pValue.setValue(temperatureValue);
			currentState.setState(TemperatureState.class.getSimpleName(),
					new TemperatureState(pValue));
		}

		// debug
		logger.log(LogService.LOG_DEBUG, "Device " + device.getDeviceId()
				+ " temperature " + temperatureValue.toString());

		this.notifyNewTemperatureValue(temperatureValue);
	}

	private void changeHumidityState(double measure, String unitOfMeasure)
	{
		// build the humidity measure
		DecimalMeasure<?> relativeHumidity = DecimalMeasure.valueOf(measure
				+ " " + unitOfMeasure);
		// if the given temperature is null, than the network-level
		// value is not up-to-date
		if (relativeHumidity != null)
		{
			// update the state
			HumidityStateValue pValue = new HumidityStateValue();
			pValue.setValue(relativeHumidity);
			currentState.setState(
					HumidityMeasurementState.class.getSimpleName(),
					new HumidityMeasurementState(pValue));

			this.notifyChangedRelativeHumidity(relativeHumidity);

			// debug
			logger.log(LogService.LOG_DEBUG, "Device " + device.getDeviceId()
					+ " humidity " + relativeHumidity.toString());
		}
	}

	private void changeLightIntensityState(double measure, String unitOfMeasure)
	{
		// build the luminosity value
		DecimalMeasure<?> luminosityValue = DecimalMeasure.valueOf(measure
				+ " " + unitOfMeasure);

		// if the given light intensity is null, than the network-level
		// value is
		// not
		// up-to-date
		// and the currently stored value should be notified
		if (luminosityValue != null)
		{
			// update the state
			LevelStateValue pValue = new LevelStateValue();
			pValue.setValue(luminosityValue);
			currentState.setState(LightIntensityState.class.getSimpleName(),
					new LightIntensityState(pValue));

			// debug
			logger.log(LogService.LOG_DEBUG, "Device " + device.getDeviceId()
					+ " light-intensity " + luminosityValue.toString());

			this.notifyNewLuminosityValue(luminosityValue);
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
	public DeviceStatus getState()
	{
		return currentState;
	}

	@Override
	public Measure<?, ?> getRelativeHumidity()
	{
		return (Measure<?, ?>) currentState.getState(
				HumidityMeasurementState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}

	@Override
	public Measure<?, ?> getTemperature()
	{
		return (Measure<?, ?>) currentState.getState(
				TemperatureState.class.getSimpleName()).getCurrentStateValue()[0]
				.getValue();
	}

	@Override
	public Measure<?, ?> getLuminance()
	{
		return (Measure<?, ?>) currentState.getState(
				LightIntensityState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}

	@Override
	public void notifyNewLuminosityValue(Measure<?, ?> luminosityValue)
	{
		// notify the new measure
		((QuadSensor) device).notifyNewLuminosityValue(luminosityValue);

	}

	@Override
	public void notifyCeasedMovement()
	{
		((QuadSensor) device).notifyCeasedMovement();
	}

	@Override
	public void notifyStartedMovement()
	{
		((QuadSensor) device).notifyStartedMovement();
	}

	@Override
	public void notifyNewTemperatureValue(Measure<?, ?> temperatureValue)
	{
		// notify the new measure
		((QuadSensor) device).notifyNewTemperatureValue(temperatureValue);
	}

	@Override
	public void notifyChangedRelativeHumidity(Measure<?, ?> relativeHumidity)
	{
		// notify the new measure
		((QuadSensor) device).notifyChangedRelativeHumidity(relativeHumidity);
	}

	@Override
	public void notifyJoinedGroup(Integer groupNumber)
	{
		// send the joined group notification
		((QuadSensor) this.device).notifyJoinedGroup(groupNumber);

	}

	@Override
	public void notifyLeftGroup(Integer groupNumber)
	{
		// send the left group notification
		((QuadSensor) this.device).notifyLeftGroup(groupNumber);

	}

	@Override
	public void updateStatus()
	{
		((Controllable) this.device).updateStatus();
	}

	@Override
	protected ZWaveNodeInfo createNodeInfo(int deviceId,
			Set<Integer> instancesId, boolean isController)
	{
		HashMap<Integer, Set<Integer>> instanceCommand = new HashMap<Integer, Set<Integer>>();

		// for this device the right Get command class is
		// COMMAND_CLASS_SENSOR_MULTILEVEL for each instance.
		HashSet<Integer> ccSet = new HashSet<Integer>();
		ccSet.add(ZWaveAPI.COMMAND_CLASS_SENSOR_MULTILEVEL);
		ccSet.add(ZWaveAPI.COMMAND_CLASS_SENSOR_BINARY);

		for (Integer instanceId : instancesId)
		{
			instanceCommand.put(instanceId, ccSet);
		}
		ZWaveNodeInfo nodeInfo = new ZWaveNodeInfo(deviceId, instanceCommand,
				isController);

		return nodeInfo;
	}

	private boolean forwardMeasure(double measure, String unitOfMeasure,
			String sensorType, long updateTime)
	{
		boolean stateChanged = false;
		
		// check which value has been read
		if (sensorType.equals(SensorType.SENSORTYPE_TEMPERATURE))
		{
			// check if and how manage the update time
			if (this.temperatureUpdateTime < updateTime)
			{
				this.temperatureUpdateTime = updateTime;

				// change the state
				this.changeTemperatureState(measure, unitOfMeasure);

				//changed
				stateChanged = true;
			}
		}
		else if (sensorType.equals(SensorType.SENSORTYPE_HUMIDITY))
		{
			// check if and how manage the update time
			if (this.humidityUpdateTime < updateTime)
			{
				this.humidityUpdateTime = updateTime;

				this.changeHumidityState(measure, unitOfMeasure);
				
				//changed
				stateChanged = true;
			}
		}
		else if (sensorType.equals(SensorType.SENSORTYPE_LUMINISCENCE))
		{
			// check if and how manage the update time
			if (this.luminiscenceUpdateTime < updateTime)
			{
				this.luminiscenceUpdateTime = updateTime;
				
				this.changeLightIntensityState(measure, unitOfMeasure);
				
				//changed
				stateChanged = true;
			}
		}
		
		return stateChanged;
	}

}
