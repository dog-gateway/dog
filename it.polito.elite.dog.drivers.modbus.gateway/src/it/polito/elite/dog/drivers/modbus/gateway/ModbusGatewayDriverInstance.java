/*
 * Dog 2.0 - Gateway Driver
 * 
 * Copyright [2012] 
 * [Dario Bonino (dario.bonino@polito.it), Politecnico di Torino] 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package it.polito.elite.dog.drivers.modbus.gateway;

import it.polito.elite.dog.drivers.modbus.network.ModbusDriver;
import it.polito.elite.dog.drivers.modbus.network.info.ModbusRegisterInfo;
import it.polito.elite.dog.drivers.modbus.network.interfaces.ModbusNetwork;
import it.polito.elite.domotics.model.DeviceStatus;
import it.polito.elite.domotics.dog2.doglibrary.devicecategory.ControllableDevice;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;
import it.polito.elite.domotics.model.devicecategory.ModbusGateway;
import it.polito.elite.domotics.model.state.State;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * @author bonino
 *
 */
public class ModbusGatewayDriverInstance extends ModbusDriver implements ModbusGateway
{
	// the driver logger
	LogService logger;
	
	// the log identifier, unique for the class
	public static String logId = "[ModbusGatewayDriverInstance]: ";

	public ModbusGatewayDriverInstance(ModbusNetwork network, ControllableDevice controllableDevice, String gatewayAddress, String gatewayPort, String protocolVariant,
			BundleContext context)
	{
		super(network, controllableDevice, gatewayAddress, gatewayPort, protocolVariant);
		
		// create a logger
		this.logger = new DogLogInstance(context);
		
		// create a new device state (according to the current DogOnt model, no
		// state is actually associated to a Modbus gateway)
		this.currentState = new DeviceStatus(device.getDeviceId());
		
		// connect this driver instance with the device
		this.device.setDriver(this);
	}
	
	@Override
	public void notifyStateChanged(State newState)
	{
		// intentionally left empty
		
	}
	
	@Override
	public synchronized DeviceStatus getState()
	{
		return this.currentState;
	}
	
	//getGatewayAddress already implemented by the superclass...

	@Override
	public void detachDriver(String deviceID)
	{
		// do nothing, shall remove the driver reference from the
		// EchelonIlon100GatewayDriver hashtable
		// TODO: check when this method is called by the OSGi framework
		
	}

	

	@Override
	public void newMessageFromHouse(ModbusRegisterInfo registerInfo, String string)
	{
		// currently no functionalities are associated to echelon ilon gateways
		// therefore they do not use any datapoint and they do not listen to the
		// house messages...
		
		// just log
		this.logger.log(LogService.LOG_INFO, ModbusGatewayDriverInstance.logId
				+ "Received new message from house involving the register:\n " + registerInfo
				+ "\n No operation is currently supported");
		
	}

	@Override
	protected void specificConfiguration()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addToNetworkDriver(ModbusRegisterInfo register)
	{
		// TODO Auto-generated method stub
		
	}	
}
