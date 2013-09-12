package it.polito.elite.dog.drivers.zwave.doorwindowsensor;

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
import it.polito.elite.domotics.model.devicecategory.DoorSensor;
import it.polito.elite.domotics.model.devicecategory.WindowSensor;
import it.polito.elite.domotics.model.state.OpenCloseState;
import it.polito.elite.domotics.model.state.State;
import it.polito.elite.domotics.model.statevalue.CloseStateValue;
import it.polito.elite.domotics.model.statevalue.OpenStateValue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class ZWaveDoorWindowSensorDriverInstance extends ZWaveDriver implements DoorSensor, WindowSensor
{
	// the class logger
	private LogService logger;

	public ZWaveDoorWindowSensorDriverInstance(ZWaveNetwork network, ControllableDevice device, int deviceId,
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
		this.currentState.setState(OpenCloseState.class.getSimpleName(), new OpenCloseState(new CloseStateValue()));

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
		this.deviceNode = deviceNode;

		// Read the value associated with the right CommandClass.
		boolean bState = false;
		CommandClasses ccEntry = instanceNode.getCommandClass(ZWaveAPI.COMMAND_CLASS_SENSOR_BINARY);

		if (ccEntry != null)
			bState = ccEntry.getLevelAsBoolean();

		if (bState)
			changeCurrentState(OpenCloseState.OPEN);
		else
			changeCurrentState(OpenCloseState.CLOSE);
	}

	/**
	 * Check if the current state has been changed. In that case, fire a state
	 * change message, otherwise it does nothing
	 * 
	 * @param openCloseValue
	 *            OpenCloseState.CLOSE or OpenCloseState.OPEN
	 */
	private void changeCurrentState(String openCloseValue)
	{
		String currentStateValue = "";
		State state = currentState.getState(OpenCloseState.class.getSimpleName());

		if (state != null)
			currentStateValue = (String) state.getCurrentStateValue()[0].getValue();

		// if the current states it is different from the new state
		if (!currentStateValue.equalsIgnoreCase(openCloseValue))
		{
			// set the new state to open or close...
			if (openCloseValue.equalsIgnoreCase(OpenCloseState.CLOSE))
				notifyClose();
			else
				notifyOpen();
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
	public DeviceStatus getState()
	{
		return currentState;
	}

	@Override
	public void notifyOpen() 
	{
		// update the state
		OpenCloseState openState = new OpenCloseState(new OpenStateValue());
		currentState.setState(OpenCloseState.class.getSimpleName(), openState);

		logger.log(LogService.LOG_DEBUG, ZWaveDoorWindowSensorDriver.LOG_ID + "Device " + device.getDeviceId()
				+ " is now " + ((OpenCloseState) openState).getCurrentStateValue()[0].getValue());

		((DoorSensor) device).notifyOpen();
	}

	@Override
	public void notifyClose() 
	{
		// update the state
		OpenCloseState closeState = new OpenCloseState(new CloseStateValue());
		currentState.setState(OpenCloseState.class.getSimpleName(), closeState);

		logger.log(LogService.LOG_DEBUG, ZWaveDoorWindowSensorDriver.LOG_ID + "Device " + device.getDeviceId()
				+ " is now " + ((OpenCloseState) closeState).getCurrentStateValue()[0].getValue());

		((DoorSensor) device).notifyClose();
	}

	@Override
	protected ZWaveNodeInfo createNodeInfo(int deviceId, Set<Integer> instancesId, boolean isController) 
	{
		HashMap<Integer,Set<Integer>> instanceCommand = new HashMap<Integer, Set<Integer>>();

		//binary switch has its own command class 
		HashSet<Integer> ccSet = new HashSet<Integer>();
		ccSet.add(ZWaveAPI.COMMAND_CLASS_SENSOR_BINARY);

		for(Integer instanceId : instancesId)
		{
			instanceCommand.put(instanceId, ccSet);
		}
		ZWaveNodeInfo nodeInfo = new ZWaveNodeInfo(deviceId, instanceCommand, isController);
		return nodeInfo;
	}
}
