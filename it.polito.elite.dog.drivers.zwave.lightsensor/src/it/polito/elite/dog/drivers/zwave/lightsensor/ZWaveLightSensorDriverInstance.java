package it.polito.elite.dog.drivers.zwave.lightsensor;

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
import it.polito.elite.domotics.model.devicecategory.LightSensor;
import it.polito.elite.domotics.model.state.LightIntensityState;
import it.polito.elite.domotics.model.state.State;
import it.polito.elite.domotics.model.statevalue.LevelStateValue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.unit.SI;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class ZWaveLightSensorDriverInstance extends ZWaveDriver implements LightSensor
{
	public static final String SENSORTYPE_LIGHT = "Light";

	// the class logger
	private LogService logger;

	public ZWaveLightSensorDriverInstance(ZWaveNetwork network, ControllableDevice device, int deviceId,
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
		currentState.setState(LightIntensityState.class.getSimpleName(), new LightIntensityState(new LevelStateValue()));

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

		// Read the value for light intensity.
		CommandClasses ccInst = instanceNode.getCommandClass(ZWaveAPI.COMMAND_CLASS_SENSOR_MULTILEVEL);

		//first time we only save update time, no more
		if(lastUpdateTime == 0)
			lastUpdateTime = ccInst.getValUpdateTime();
		// Check if it is a real new value or if it is an old one.
		else if(lastUpdateTime < ccInst.getValUpdateTime())
		{
			//update last update time
			lastUpdateTime = ccInst.getValUpdateTime();
			nFailedUpdate = 0;

			//Reads values and sensorType 
			double measure = ccInst.getVal();

			//call the notify method 
			notifyNewLuminosityValue(DecimalMeasure.valueOf(measure, SI.LUX));
		}
		/*else
		{
			// increment counter
			nFailedUpdate++;

			//log a message after 5 failed update
			if(nFailedUpdate >= 5)
			{
				logger.log(LogService.LOG_WARNING, ZWaveLightSensorDriver.LOG_ID + "Device " + device.getDeviceId()
						+ " doesn't respond after 5 update requests");

				nFailedUpdate = 0;
			}
		}*/
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
	public void notifyNewLuminosityValue(Measure<?, ?> luminosityValue) 
	{
		// update the state
		LevelStateValue pValue = new LevelStateValue();
		pValue.setValue(luminosityValue);

		currentState.setState(LightIntensityState.class.getSimpleName(),
				new LightIntensityState(pValue));

		// debug
		logger.log(LogService.LOG_DEBUG, ZWaveLightSensorDriver.LOG_ID + "Device " + device.getDeviceId()
				+ " light intensity " + luminosityValue.toString());

		// notify the new measure
		((LightSensor) device).notifyNewLuminosityValue(luminosityValue);
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

	//TODO: aggiungere metodo per la getLuminosity quando sara' presente in dogOnt
}
