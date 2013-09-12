package it.polito.elite.dog.drivers.zwave.gateway;

import it.polito.elite.dog.drivers.zwave.ZWaveAPI;
import it.polito.elite.dog.drivers.zwave.model.Controller;
import it.polito.elite.dog.drivers.zwave.model.Device;
import it.polito.elite.dog.drivers.zwave.model.Instance;
import it.polito.elite.dog.drivers.zwave.network.ZWaveDriver;
import it.polito.elite.dog.drivers.zwave.network.info.ZWaveNodeInfo;
import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;
import it.polito.elite.domotics.dog2.doglibrary.devicecategory.ControllableDevice;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;
import it.polito.elite.domotics.model.DeviceStatus;
import it.polito.elite.domotics.model.devicecategory.ZWaveGateway;
import it.polito.elite.domotics.model.state.State;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;


public class ZWaveGatewayDriverInstance extends ZWaveDriver implements ZWaveGateway
{
	// the driver logger
	LogService logger;

	// the log identifier, unique for the class
	public static String LOG_ID = "[ZWaveGatewayDriverInstance]: ";
	
	// data controller associated with the gateway
	protected Controller controller;
	
	public ZWaveGatewayDriverInstance(ZWaveNetwork network,
			ControllableDevice controllableDevice, int nodeId, Set<Integer> instancesId, BundleContext context) 
	{
		//gateway driver node contains always multiple instanceId, but only the one with Id = 0 contains interesting data
		//also updateTimeMillis = 0 is fixed to zero because we are not interested din this kind of behavior fot the gateway
		super(network, controllableDevice, nodeId, instancesId, nodeId, 0, context);

		// create a logger
		logger = new DogLogInstance(context);

		// create a new device state (according to the current DogOnt model, no
		// state is actually associated to a Modbus gateway)
		currentState = new DeviceStatus(device.getDeviceId());

		// connect this driver instance with the device
		device.setDriver(this);
		
		// ask for update controller data
		//updateDeviceData();
	}

	@Override
	public void notifyStateChanged(State newState) {
		// TODO Auto-generated method stub

	}

	/**
	 *  starts inclusion process that lasts for 20 seconds
	 */
	@Override
	public void associate() 
	{
		// start associate process and, after 20 sec reset controller status to normal
		ZWavePairUnpairThreads pairUnpairThreads = new ZWavePairUnpairThreads(network, logger, true, 20000);
		pairUnpairThreads.start();
	}

	/**
	 * starts exclusion process that lasts for 20 seconds
	 */
	@Override
	public void disassociate(String nodeID)  //TODO: remove String nodeID
	{
		// start disassociate process and, after 20 sec reset controller status to normal
		ZWavePairUnpairThreads pairUnpairThreads = new ZWavePairUnpairThreads(network, logger, false, 20000);
		pairUnpairThreads.start();
	}
	
	/**
	 * starts learn process that lasts for 20 seconds.
	 * Learn mode is equivalent to press the button on the device (not only gateway) to start include process.
	 * Due this means that the device is not yet included in the system, this method is left empty for future purposes
	 */
	//@Override TODO: add notation
	public void learn() 
	{
		//Nothing to do...
	}
	
	/**
	 * reset Z-Wave controller to default. NB: will completely destroy all stored data about your network!
	 */
	//@Override TODO: add notation
	public void reset() 
	{
		network.controllerWrite(ZWaveGatewayDriver.CMD_RESET, "");
	}
	
	@Override
	public synchronized DeviceStatus getState()
	{
		return this.currentState;
	}

	@Override
	protected void specificConfiguration() 
	{
		// nothing to do...
	}

	@Override
	protected void addToNetworkDriver(ZWaveNodeInfo nodeInfo) 
	{
		network.addDriver(nodeInfo, 0, this);
	}

	@Override
	public void newMessageFromHouse(Device deviceNode, Instance instanceNode, 
			Controller controllerNode, String sValue)
	{
		this.deviceNode = deviceNode;
		controller = controllerNode;
	}

	@Override
	protected boolean isController() {
		return true;
	}

	@Override
	protected ZWaveNodeInfo createNodeInfo(int deviceId, Set<Integer> instancesId, boolean isController) 
	{
		HashMap<Integer,Set<Integer>> instanceCommand = new HashMap<Integer, Set<Integer>>();
		
		//this is the gateway so we are not really interested in command class for sensor data update
		HashSet<Integer> ccSet = new HashSet<Integer>();
		ccSet.add(ZWaveAPI.COMMAND_CLASS_BASIC);
		
		for(Integer instanceId : instancesId)
		{
			instanceCommand.put(instanceId, ccSet);
		}
		ZWaveNodeInfo nodeInfo = new ZWaveNodeInfo(deviceId, instanceCommand, isController);
		
		return nodeInfo;
	}
}
