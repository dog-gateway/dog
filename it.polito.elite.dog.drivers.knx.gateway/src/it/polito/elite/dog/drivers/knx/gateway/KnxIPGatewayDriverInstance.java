package it.polito.elite.dog.drivers.knx.gateway;

import it.polito.elite.dog.drivers.knx.network.KnxIPDriver;
import it.polito.elite.dog.drivers.knx.network.info.KnxIPDeviceInfo;
import it.polito.elite.dog.drivers.knx.network.interfaces.KnxIPNetwork;
import it.polito.elite.domotics.model.DeviceStatus;
import it.polito.elite.domotics.dog2.doglibrary.devicecategory.ControllableDevice;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;
import it.polito.elite.domotics.model.devicecategory.KNXNetIPGateway;
import it.polito.elite.domotics.model.state.State;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class KnxIPGatewayDriverInstance extends KnxIPDriver implements KNXNetIPGateway
{
	
	// the driver logger
	LogService logger;
	
	// the log identifier, unique for the class
	public static String logId = "[KnxIPGatewayDriverInstance]: ";
	
	public KnxIPGatewayDriverInstance(KnxIPNetwork network, ControllableDevice controllableDevice, String gatewayAddress,
			BundleContext context)
	{
		super(network, controllableDevice, gatewayAddress);
		
		// create a logger
		this.logger = new DogLogInstance(context);
		
		// create a new device state (according to the current DogOnt model, no
		// state is actually associated to a KnxIP gateway)
		this.currentState = new DeviceStatus(device.getDeviceId());
		
		// connect this driver instance with the device
		this.device.setDriver(this);
	}

	@Override
	public DeviceStatus getState()
	{
		return this.currentState;
	}

	@Override
	public void notifyStateChanged(State newState)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newMessageFromHouse(String source, String destination, String value)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addToNetworkDriver(KnxIPDeviceInfo devInfo)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void specificConfiguration()
	{
		// TODO Auto-generated method stub
		
	}
	
}
