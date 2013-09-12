package it.polito.elite.dog.drivers.zwave.network.info;

import java.util.HashMap;
import java.util.Set;


public class ZWaveNodeInfo
{
	// the IP address of the gateway to which a specific device is connected
	private int sDeviceNodeId;
	
	// HashMap of instancesId available on this node and a set of Get command class associated to update sensor value
	private HashMap<Integer,Set<Integer>> hmInstanceGetSensorCC;
	
	// true if it is the controller device
	private boolean bIsController;
	
	/**
	 * The class constructor, givensDeviceNodeId and sInstanceNodeId, creates a new
	 * ZWaveNodeInfo instance.
	 * 
	 * @param sDeviceNodeId the device node id
	 * @param hmInstanceNodeId HashMap of instancesId available on this node and a set of Get command class associated to update sensor value
	 * @param bIsController true if this is the controller device
	 */
	public ZWaveNodeInfo(int sDeviceNodeId, HashMap<Integer,Set<Integer>> hmInstanceGetSensorCC, boolean bIsController)
	{
		this.sDeviceNodeId = sDeviceNodeId;
		this.hmInstanceGetSensorCC = hmInstanceGetSensorCC;
		this.bIsController = bIsController;
	}
	
	/**
	 * @return the sDeviceNodeId
	 */
	public int getDeviceNodeId() {
		return sDeviceNodeId;
	}

	/**
	 * @param sDeviceNodeId the sDeviceNodeId to set
	 */
	public void setDeviceNodeId(int sDeviceNodeId) {
		this.sDeviceNodeId = sDeviceNodeId;
	}

	/**
	 * @return HashMap of instancesId available on this node and a set of Get command class associated to update sensor value.
	 * Key is instanceId, value is a set of commandClass to call to trigger sensor data
	 */
	public HashMap<Integer,Set<Integer>> getInstanceSensorCC() {
		return hmInstanceGetSensorCC;
	}

	/**
	 * @param HashMap of instancesId available on this node and a set of Get command class associated to update sensor value
	 */
	public void setInstanceSensorCC(HashMap<Integer,Set<Integer>> instancesNodeId) {
		hmInstanceGetSensorCC = instancesNodeId;
	}	
	
	public Set<Integer> getInstanceSet()
	{
		return hmInstanceGetSensorCC.keySet();
	}
	
	/**
	 * @return IsController
	 */
	public boolean isController() {
		return bIsController;
	}

	/**
	 * @param isController the isController to set
	 */
	public void setIsController(boolean bIsController) {
		this.bIsController = bIsController;
	}
}
