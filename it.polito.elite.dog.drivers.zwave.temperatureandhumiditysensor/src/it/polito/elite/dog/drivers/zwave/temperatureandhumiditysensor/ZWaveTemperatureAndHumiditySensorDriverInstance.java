/*
 * Dog  - Z-Wave
 * 
 * Copyright [2013] 
 * [Davide Aimone (aimone.dav@gmail.com)]
 * [Dario Bonino (dario.bonino@polito.it), Politecnico di Torino] 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package it.polito.elite.dog.drivers.zwave.temperatureandhumiditysensor;

import it.polito.elite.dog.drivers.zwave.ZWaveAPI;
import it.polito.elite.dog.drivers.zwave.model.CommandClasses;
import it.polito.elite.dog.drivers.zwave.model.Controller;
import it.polito.elite.dog.drivers.zwave.model.Device;
import it.polito.elite.dog.drivers.zwave.model.Instance;
import it.polito.elite.dog.drivers.zwave.network.ZWaveDriver;
import it.polito.elite.dog.drivers.zwave.network.info.ZWaveNodeInfo;
import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;
import it.polito.elite.domotics.dog2.doglibrary.devicecategory.ControllableDevice;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;
import it.polito.elite.domotics.model.DeviceStatus;
import it.polito.elite.domotics.model.devicecategory.TemperatureAndHumiditySensor;
import it.polito.elite.domotics.model.state.HumidityMeasurementState;
import it.polito.elite.domotics.model.state.State;
import it.polito.elite.domotics.model.state.TemperatureState;
import it.polito.elite.domotics.model.statevalue.HumidityStateValue;
import it.polito.elite.domotics.model.statevalue.TemperatureStateValue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class ZWaveTemperatureAndHumiditySensorDriverInstance extends ZWaveDriver implements TemperatureAndHumiditySensor
{
	public static final String SENSORTYPE_TEMPERATURE = "Temperature";
	public static final String SENSORTYPE_HUMIDITY = "Humidity";

	// the class logger
	private LogService logger;

	public ZWaveTemperatureAndHumiditySensorDriverInstance(ZWaveNetwork network, ControllableDevice device, int deviceId,
			Set<Integer> instancesId, int gatewayNodeId, int updateTimeMillis, BundleContext context)
	{
		super(network, device, deviceId, instancesId, gatewayNodeId, updateTimeMillis, context);

		// create a logger
		logger = new DogLogInstance(context);

		// initialize states
		this.initializeStates();
	}

	/**
	 * Initializes the state asynchronously as required by OSGi
	 */
	private void initializeStates()
	{
		// initialize the state
		this.currentState.setState(TemperatureState.class.getSimpleName(), new TemperatureState(new TemperatureStateValue()));
		this.currentState.setState(HumidityMeasurementState.class.getSimpleName(), new HumidityMeasurementState(new HumidityStateValue()));

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
		//probably not used...
	}

	@Override
	public void newMessageFromHouse(Device deviceNode, Instance instanceNode, 
			Controller controllerNode, String sValue)
	{
		//update deviceNode
		this.deviceNode = deviceNode;

		//instance 0 doesn't contains updated value //TODO: non e' vero: problema delle 3 istanze!!!
		if(instanceNode.getInstanceId() != 0)
		{
			// Read the value for temperature or humidity.
			CommandClasses ccInst = instanceNode.getCommandClass(ZWaveAPI.COMMAND_CLASS_SENSOR_MULTILEVEL);

			//first time we only save update time, no more
			if(lastUpdateTime == 0)
				lastUpdateTime = ccInst.getValUpdateTime();
			// Check if it is a real new value or if it is an old one
			else if(lastUpdateTime < ccInst.getValUpdateTime())
			{
				//update last update time
				lastUpdateTime = ccInst.getValUpdateTime();
				nFailedUpdate = 0;

				//Reads values and sensorType 
				double measure = ccInst.getVal();
				String sensorType = ccInst.getSensorType();

				//call the right method 
				if(sensorType.equals(SENSORTYPE_TEMPERATURE))
				{

					//TODO: controllare l'unita' di misura ???
					notifyNewTemperatureValue(DecimalMeasure.valueOf(measure, SI.CELSIUS));
				}
				else if(sensorType.equals(SENSORTYPE_HUMIDITY))
				{
					Unit<Dimensionless> percentage = Unit.ONE.alternate("%");
					notifyChangedRelativeHumidity(DecimalMeasure.valueOf(measure, percentage));
				}
			}
			/*else
			{
				// increment counter
				nFailedUpdate++;

				//log a message after 5 failed update
				if(nFailedUpdate >= 5)
				{
					logger.log(LogService.LOG_WARNING, ZWaveTemperatureAndHumiditySensorDriver.LOG_ID + "Device " + device.getDeviceId()
							+ " doesn't respond after 5 update requests");

					nFailedUpdate = 0;
				}
			}*/
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
	public Measure<?, ?> getRelativeHumidity() 
	{
		return (Measure<?, ?>) currentState.getState(HumidityMeasurementState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}

	@Override
	public Measure<?, ?> getTemperature() 
	{
		return (Measure<?, ?>) currentState.getState(TemperatureState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}

	@Override
	public void notifyNewTemperatureValue(Measure<?, ?> temperatureValue) 
	{
		// update the state
		TemperatureStateValue pValue = new TemperatureStateValue();
		pValue.setValue(temperatureValue);
		currentState.setState(TemperatureState.class.getSimpleName(),
				new TemperatureState(pValue));

		// debug
		logger.log(LogService.LOG_DEBUG, ZWaveTemperatureAndHumiditySensorDriver.LOG_ID + "Device " + device.getDeviceId()
				+ " temperature " + temperatureValue.toString());

		// notify the new measure
		((TemperatureAndHumiditySensor) device).notifyNewTemperatureValue(temperatureValue);
	}

	@Override
	public void notifyChangedRelativeHumidity(Measure<?, ?> relativeHumidity) 
	{
		// update the state
		HumidityStateValue pValue = new HumidityStateValue();
		pValue.setValue(relativeHumidity);
		currentState.setState(HumidityMeasurementState.class.getSimpleName(),
				new HumidityMeasurementState(pValue));

		// debug
		logger.log(LogService.LOG_DEBUG, ZWaveTemperatureAndHumiditySensorDriver.LOG_ID + "Device " + device.getDeviceId()
				+ " humidity " + relativeHumidity.toString());

		// notify the new measure
		((TemperatureAndHumiditySensor) device).notifyChangedRelativeHumidity(relativeHumidity);
	}

	@Override
	protected ZWaveNodeInfo createNodeInfo(int deviceId, Set<Integer> instancesId, boolean isController) 
	{
		HashMap<Integer,Set<Integer>> instanceCommand = new HashMap<Integer, Set<Integer>>();

		//for this device the right Get command class is COMMAND_CLASS_SENSOR_MULTILEVEL for each instance.
		HashSet<Integer> ccSet = new HashSet<Integer>();
		ccSet.add(ZWaveAPI.COMMAND_CLASS_SENSOR_MULTILEVEL);

		for(Integer instanceId : instancesId)
		{
			instanceCommand.put(instanceId, ccSet);
		}
		ZWaveNodeInfo nodeInfo = new ZWaveNodeInfo(deviceId, instanceCommand, isController);

		return nodeInfo;
	}
}
