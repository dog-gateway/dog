package it.polito.elite.dog.drivers.zwave.model;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

//@JsonIgnoreProperties(ignoreUnknown=true)
public class ZWaveModelTree {
	private Areas areas;	
	private Controller controller;
	private Map<Integer, Device> devices;
	private long updateTime;

	@JsonCreator
	public ZWaveModelTree(@JsonProperty("areas") Areas areas,
			@JsonProperty("controller") Controller controller,
			@JsonProperty("devices") Map<Integer, Device> devices,
			@JsonProperty("updateTime") long updateTime)
	{
		this.areas = areas;
		this.controller = controller;
		this.devices = devices;
		this.updateTime = updateTime;

		//Set unique id for each element 
//		for(Entry<Integer, Device> device : this.devices.entrySet())
//		{
//			device.getValue().setDeviceId(device.getKey());
//		}
	}

	public Areas getAreas() {
		return areas;
	}

	public void setAreas(Areas areas) {
		this.areas = areas;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public Map<Integer, Device> getDevices() {
		return devices;
	}

	public void setDevices(Map<Integer, Device> devices) {
		this.devices = devices;
	}

	@Override
	public String toString() 
	{
		return (areas != null ? areas.toString() + "\n" : "") 
				+ (controller != null ? controller.toString()  + "\n" : "")
				+ (devices != null ? devices.toString()  + "\n" : "") 
				+ "updateTime: " + updateTime; 
	}
}
