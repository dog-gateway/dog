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
package it.polito.elite.dog.drivers.zwave.lightsensor;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.Controllable;
import it.polito.elite.dog.core.library.model.devicecategory.LightSensor;
import it.polito.elite.dog.core.library.model.state.LightIntensityState;
import it.polito.elite.dog.core.library.model.statevalue.LevelStateValue;
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
import javax.measure.unit.SI;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class ZWaveLightSensorDriverInstance extends ZWaveDriverInstance
		implements LightSensor
{
	// the class logger
	private LogHelper logger;

	// the group set
	private HashSet<Integer> groups;

	public ZWaveLightSensorDriverInstance(ZWaveNetwork network,
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
		// initialize the state value at 0.0 lx
		LevelStateValue value = new LevelStateValue();
		value.setValue(DecimalMeasure.valueOf(0.0, SI.LUX));
		currentState.setState(LightIntensityState.class.getSimpleName(),
				new LightIntensityState(value));

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

		// Read the value for light intensity.
		CommandClasses ccInst = instanceNode
				.getCommandClass(ZWaveAPI.COMMAND_CLASS_SENSOR_MULTILEVEL);

		// first time we only save update time, no more
		if (lastUpdateTime == 0)
			lastUpdateTime = ccInst.getValUpdateTime();

		// Check if it is a real new value or if it is an old one.
		else if (lastUpdateTime < ccInst.getValUpdateTime())
		{
			// update last update time
			lastUpdateTime = ccInst.getValUpdateTime();
			nFailedUpdate = 0;

			// Reads values and sensorType
			double measure = ccInst.getVal();

			this.changeLightIntensityState(measure, SI.LUX.getSymbol());

			// update the state
			this.updateStatus();

		}
	}

	private void changeLightIntensityState(double measure, String unitOfMeasure)
	{
		// build the luminosity value
		DecimalMeasure<?> luminosityValue = DecimalMeasure.valueOf(measure
				+ " " + unitOfMeasure);

		// if the given light intensity is null, than the network-level
		// value is not up-to-date
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
		((LightSensor) device).notifyNewLuminosityValue(luminosityValue);
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

		for (Integer instanceId : instancesId)
		{
			instanceCommand.put(instanceId, ccSet);
		}
		ZWaveNodeInfo nodeInfo = new ZWaveNodeInfo(deviceId, instanceCommand,
				isController);

		return nodeInfo;
	}

	@Override
	public void notifyJoinedGroup(Integer groupNumber)
	{
		// send the joined group notification
		((LightSensor) this.device).notifyJoinedGroup(groupNumber);

	}

	@Override
	public void notifyLeftGroup(Integer groupNumber)
	{
		// send the left group notification
		((LightSensor) this.device).notifyLeftGroup(groupNumber);

	}

	@Override
	public void updateStatus()
	{
		((Controllable) this.device).updateStatus();
	}
}
