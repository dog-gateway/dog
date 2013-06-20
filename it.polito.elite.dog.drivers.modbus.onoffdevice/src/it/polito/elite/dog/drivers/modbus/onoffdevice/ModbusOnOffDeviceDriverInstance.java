/**
 * 
 */
package it.polito.elite.dog.drivers.modbus.onoffdevice;

import it.polito.elite.dog.drivers.modbus.network.ModbusDriver;
import it.polito.elite.dog.drivers.modbus.network.info.ModbusRegisterInfo;
import it.polito.elite.dog.drivers.modbus.network.interfaces.ModbusNetwork;
import it.polito.elite.domotics.model.DeviceStatus;
import it.polito.elite.domotics.dog2.doglibrary.devicecategory.ControllableDevice;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;
import it.polito.elite.domotics.model.devicecategory.Buzzer;
import it.polito.elite.domotics.model.devicecategory.ElectricalSystem;
import it.polito.elite.domotics.model.devicecategory.Lamp;
import it.polito.elite.domotics.model.devicecategory.MainsPowerOutlet;
import it.polito.elite.domotics.model.devicecategory.SimpleLamp;
import it.polito.elite.domotics.model.state.OnOffState;
import it.polito.elite.domotics.model.state.State;
import it.polito.elite.domotics.model.statevalue.OffStateValue;
import it.polito.elite.domotics.model.statevalue.OnStateValue;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * @author bonino
 * 
 */
public class ModbusOnOffDeviceDriverInstance extends ModbusDriver implements Lamp, SimpleLamp, Buzzer, MainsPowerOutlet
{
	
	// the class logger
	private LogService logger;
	
	public ModbusOnOffDeviceDriverInstance(ModbusNetwork network, ControllableDevice device, String gatewayAddress,
			String gatewayPort, String gatewayProtocol, BundleContext context)
	{
		super(network, device, gatewayAddress, gatewayPort, gatewayProtocol);
		
		// create a logger
		this.logger = new DogLogInstance(context);
		
		//read the initial state of devices
		this.initializeStates();
	}
	
	private void initializeStates()
	{
		this.currentState.setState(OnOffState.class.getSimpleName(), new OnOffState(new OffStateValue()));
		
		// read the initial state (should be just one...)
		for (ModbusRegisterInfo register : this.register2Notification.keySet())
		{
			this.network.read(register);
		}
	}
	
	@Override
	public void detachDriver(String deviceID)
	{
		// nothing to do by now... will be handled in the future... may be...
	}
	
	@Override
	public void storeScene(Integer sceneNumber)
	{
		// intentionally left empty
	}
	
	@Override
	public void deleteScene(Integer sceneNumber)
	{
		// intentionally left empty
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
	public void on()
	{
		this.network.write(this.managedRegisters.iterator().next(), "true");
	}
	
	@Override
	public void off()
	{
		this.network.write(this.managedRegisters.iterator().next(), "false");
	}
	
	@Override
	public DeviceStatus getState()
	{
		return this.currentState;
	}
	
	@Override
	public void notifyStateChanged(State newState)
	{
		// debug
		this.logger.log(LogService.LOG_DEBUG, ModbusOnOffDeviceDriver.logId + "Device " + this.device.getDeviceId()
				+ " is now " + ((OnOffState) newState).getCurrentStateValue()[0].getValue());
		((ElectricalSystem) this.device).notifyStateChanged(newState);
		
	}
	
	@Override
	public void newMessageFromHouse(ModbusRegisterInfo dataPointInfo, String value)
	{
		if ((value != null) && (!value.isEmpty()))
		{
			if (value.equalsIgnoreCase("true"))
			{
				this.changeCurrentState(OnOffState.ON);
			}
			else if (value.equalsIgnoreCase("false"))
			{
				this.changeCurrentState(OnOffState.OFF);
			}
		}
		
	}
	
	@Override
	protected void specificConfiguration()
	{
		// prepare the device state map
		this.currentState = new DeviceStatus(device.getDeviceId());
	}
	
	@Override
	protected void addToNetworkDriver(ModbusRegisterInfo register)
	{
		this.network.addDriver(register, this);
	}
	
	/**
	 * Check if the current state has been changed. In that case, fire a state
	 * change message, otherwise it does nothing
	 * 
	 * @param OnOffValue
	 *            OnOffState.ON or OnOffState.OFF
	 */
	private void changeCurrentState(String OnOffValue)
	{
		String currentStateValue = (String) this.currentState.getState(OnOffState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
		// if the current states it is different from the new state
		if (!currentStateValue.equalsIgnoreCase(OnOffValue))
		{
			State newState;
			// set the new state to on or off...
			if (OnOffValue.equalsIgnoreCase(OnOffState.ON))
			{
				newState = new OnOffState(new OffStateValue());
			}
			else
			{
				newState = new OnOffState(new OnStateValue());
			}
			// ... then set the new state for the device and throw a state
			// changed notification
			this.currentState.setState(newState.getStateName(), newState);
			this.notifyStateChanged(newState);
		}
		
	}
	
}
