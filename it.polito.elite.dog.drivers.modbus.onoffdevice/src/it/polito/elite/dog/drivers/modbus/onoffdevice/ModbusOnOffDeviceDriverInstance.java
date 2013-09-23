/*
 * Dog - Device Driver
 * 
 * Copyright (c) 2012-2013 Dario Bonino
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
package it.polito.elite.dog.drivers.modbus.onoffdevice;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.Buzzer;
import it.polito.elite.dog.core.library.model.devicecategory.ElectricalSystem;
import it.polito.elite.dog.core.library.model.devicecategory.Lamp;
import it.polito.elite.dog.core.library.model.devicecategory.MainsPowerOutlet;
import it.polito.elite.dog.core.library.model.devicecategory.SimpleLamp;
import it.polito.elite.dog.core.library.model.state.OnOffState;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.OffStateValue;
import it.polito.elite.dog.core.library.model.statevalue.OnStateValue;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.drivers.modbus.network.ModbusDriver;
import it.polito.elite.dog.drivers.modbus.network.info.ModbusRegisterInfo;
import it.polito.elite.dog.drivers.modbus.network.interfaces.ModbusNetwork;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class ModbusOnOffDeviceDriverInstance extends ModbusDriver implements Lamp, SimpleLamp, Buzzer, MainsPowerOutlet
{
	
	// the class logger
	private LogHelper logger;
	
	public ModbusOnOffDeviceDriverInstance(ModbusNetwork network, ControllableDevice device, String gatewayAddress,
			String gatewayPort, String gatewayProtocol, BundleContext context)
	{
		super(network, device, gatewayAddress, gatewayPort, gatewayProtocol);
		
		// create a logger
		this.logger = new LogHelper(context);
		
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
