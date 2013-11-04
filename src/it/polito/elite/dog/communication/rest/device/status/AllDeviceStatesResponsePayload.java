package it.polito.elite.dog.communication.rest.device.status;

public class AllDeviceStatesResponsePayload
{
	private DeviceStateResponsePayload[] devices;

	/**
	 * 
	 */
	public AllDeviceStatesResponsePayload()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the devices
	 */
	public DeviceStateResponsePayload[] getDevices()
	{
		return devices;
	}

	/**
	 * @param devices the devices to set
	 */
	public void setDevices(DeviceStateResponsePayload[] devices)
	{
		this.devices = devices;
	}
}
