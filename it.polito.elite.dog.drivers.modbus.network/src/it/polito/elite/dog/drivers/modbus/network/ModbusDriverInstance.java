/*
 * Dog - Network Driver
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
package it.polito.elite.dog.drivers.modbus.network;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.StatefulDevice;
import it.polito.elite.dog.core.library.util.ElementDescription;
import it.polito.elite.dog.drivers.modbus.network.info.CmdNotificationInfo;
import it.polito.elite.dog.drivers.modbus.network.info.ModbusInfo;
import it.polito.elite.dog.drivers.modbus.network.info.ModbusRegisterInfo;
import it.polito.elite.dog.drivers.modbus.network.interfaces.ModbusNetwork;
import it.polito.elite.dog.drivers.modbus.network.protocol.ModbusProtocolVariant;
import it.polito.elite.dog.drivers.modbus.network.regxlators.RegXlator;

import java.net.InetAddress;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 * @since Jan 18, 2012
 */
public abstract class ModbusDriverInstance implements StatefulDevice
{
	// a reference to the network driver interface to allow network-level access
	// for sub-classes
	protected ModbusNetwork network;
	
	// the state of the device associated to this driver
	protected DeviceStatus currentState;
	
	// the device associated to the driver
	protected ControllableDevice device;
	
	// the endpoint address associated to this device by means of the gateway
	// attribute
	protected String gwAddress;
	
	// the port of the endpoint address
	protected String gwPort;
	
	// the protocol variant (can be null)
	protected String gwProtocol;
	
	// the datapoints managed by this driver
	protected Set<ModbusRegisterInfo> managedRegisters;
	
	// the datapoint to notifications map
	protected Map<ModbusRegisterInfo, Set<CmdNotificationInfo>> register2Notification;
	
	// the command2datapoint map
	protected Map<CmdNotificationInfo, ModbusRegisterInfo> command2Register;
	
	/**
	 * Notifies a device-specific driver of a new register value coming from the
	 * underlying modbus network connection (either through polling or through
	 * direct read). The updated value is contained in the given
	 * {@link ModbusRegisterInfo} instance.
	 * 
	 * @param dataPointInfo
	 *            The {@link DataPointInfo} instance representing the data point
	 *            whose value has changed.
	 * @param string
	 */
	public abstract void newMessageFromHouse(ModbusRegisterInfo dataPointInfo, String string);
	
	/**
	 * The base class constructor, provides common initialization for all the
	 * needed data structures, must be called by sub-class constructors
	 * 
	 * @param network
	 *            the network driver to use (as described by the
	 *            {@link ModbusNetwork} interface.
	 * @param device
	 *            the device to which this driver is attached/associated
	 */
	public ModbusDriverInstance(ModbusNetwork network, ControllableDevice device, String gatewayAddress, String gatewayPort, String gatewayProtocol)
	{
		// store a reference to the network driver
		this.network = network;
		
		// store a reference to the associate device
		this.device = device;
		
		// store the endpoint address for the attached device
		this.gwAddress = gatewayAddress;
		
		// store the port associated to the gateway address
		this.gwPort = gatewayPort;
		
		// store the protocol type for the gateway
		this.gwProtocol = (gatewayProtocol!=null)? gatewayProtocol : ModbusProtocolVariant.TCP.toString();
		
		// create the map needed to associate datapoints to notifications
		this.register2Notification = new ConcurrentHashMap<ModbusRegisterInfo, Set<CmdNotificationInfo>>();
		
		// create the map to associate commands and datapoints
		this.command2Register = new ConcurrentHashMap<CmdNotificationInfo, ModbusRegisterInfo>();
		
		// create the set for storing the managed datapoints
		this.managedRegisters = new HashSet<ModbusRegisterInfo>();
		
		// fill the data structures depending on the specific device
		// configuration parameters
		this.fillConfiguration();
		
		// call the specific configuration method, if needed
		this.specificConfiguration();
		
		// associate the device-specific driver to the network driver
		for (ModbusRegisterInfo register : this.managedRegisters)
			this.addToNetworkDriver(register);
	}
	
	/**
	 * Extending classes might implement this method to provide driver-specific
	 * configurations to be done during the driver creation process, before
	 * associating the device-specific driver to the network driver
	 */
	protected abstract void specificConfiguration();
	
	/**
	 * Adds a device managed by a device-specific driver instance to the
	 * {@link ModbusNetwork} driver. It must be implemented by extending classes
	 * and it must take care of identifying any additional information needed to
	 * correctly specify the given register and to associate the corresponding
	 * {@link ModbusRegisterInfo} with the proper {@link ModbusDriverImpl}
	 * instance.
	 * 
	 * @param register
	 *            the register to add as a {@link ModbusRegisterInfo} instance.
	 */
	protected abstract void addToNetworkDriver(ModbusRegisterInfo register);
	
	/***
	 * Fills the inner data structures depending on the specific device
	 * configuration parameters, extracted from the device instance associated
	 * to this driver instance
	 */
	private void fillConfiguration()
	{
		// gets the properties shared by almost all Modbus devices, i.e. the
		// register address, the slave id, the register type and the unit of measure
		// It must be noticed that such informations are specified for each
		// command/notification while no common parameters are defined/handled
		
		// get parameters associated to each device command (if any)
		Set<ElementDescription> commandsSpecificParameters = this.device.getDeviceDescriptor()
				.getCommandSpecificParams();
		
		// get parameters associated to each device notification (if any)
		Set<ElementDescription> notificationsSpecificParameters = this.device.getDeviceDescriptor()
				.getNotificationSpecificParams();
		
		// --------------- Handle command specific parameters ----------------
		for (ElementDescription parameter : commandsSpecificParameters)
		{
			try
			{
			//the parameter map
			Map<String,String> params = parameter.getElementParams();
			
			// get the real command name
			String realCommandName = params.get(ModbusInfo.COMMAND_NAME);
			
			// get the Modbus register address
			String registerAddress = params.get(ModbusInfo.REGISTER_ADDRESS);
			
			// get the Modbus register unit of measure
			String unitOfMeasure = params.get(ModbusInfo.REGISTER_UOM);
			
			// get the Modbus register type
			String registerType = params.get(ModbusInfo.REGISTER_TYPE);
			
			// get the Modbus register slave id
			String registerSlaveId = params.get(ModbusInfo.SLAVE_ID);
			
			// get the Modbus register scale factor if needed
			String scaleFactor = params.get(ModbusInfo.SCALE_FACTOR);
			
			// if no data point id has been specified, then the command
			// cannot be handled
			if ((registerAddress !=null)&&(registerType !=null)&&(registerSlaveId!=null)&&(scaleFactor!=null))
			{
				// build the data point info instance to associate to this
				// command
				ModbusRegisterInfo register = new ModbusRegisterInfo(Integer.valueOf(registerAddress.trim()), Integer.valueOf(registerType.trim()));
				
				//fill the register gateway address
				register.setGatewayIPAddress(InetAddress.getByName(this.gwAddress));
				
				//fill the register gateway port
				register.setGatewayPort(this.gwPort);
				
				//fill the protocol variant associated to the gateway
				register.setGatewayProtocol(this.gwProtocol);
				
				//fill the slave id
				register.setSlaveId(Integer.valueOf(registerSlaveId));
				
				//fill the translator properties
				RegXlator xlator = register.getXlator();
				
				//the unit of measure
				xlator.setUnitOfMeasure(unitOfMeasure);
				xlator.setScaleFactor(Double.valueOf(scaleFactor.trim()));
				
				CmdNotificationInfo cmdInfo = new CmdNotificationInfo(realCommandName, parameter.getElementParams());
				// add the command to data point entry
				this.command2Register.put(cmdInfo, register);
				
				// add the datapoint to the set of managed datapoints
				this.managedRegisters.add(register);
			}
			}
			catch(Exception e)
			{
				//do not handle the register commands...
			}
		}
		
		// --------------- Handle notification specific parameters
		// ----------------
		for (ElementDescription parameter : notificationsSpecificParameters)
		{
			try
			{
			//the parameter map
			Map<String,String> params = parameter.getElementParams();
			
			// get the real command name
			String notificationName = params.get(ModbusInfo.NOTIFICATION_NAME);
			
			// get the Modbus register address
			String registerAddress = params.get(ModbusInfo.REGISTER_ADDRESS);
			
			// get the Modbus register unit of measure
			String unitOfMeasure = params.get(ModbusInfo.REGISTER_UOM);
			
			// get the Modbus register type
			String registerType = params.get(ModbusInfo.REGISTER_TYPE);
			
			// get the Modbus register slave id
			String registerSlaveId = params.get(ModbusInfo.SLAVE_ID);
			
			// get the Modbus register scale factor if needed
			String scaleFactor = params.get(ModbusInfo.SCALE_FACTOR);
			
			// if no data point id has been specified, then the command
			// cannot be handled
			if ((registerAddress !=null)&&(registerType !=null)&&(registerSlaveId!=null)&&(scaleFactor!=null))
			{
				// build the data point info instance to associate to this
				// command
				ModbusRegisterInfo register = new ModbusRegisterInfo(Integer.valueOf(registerAddress.trim()), Integer.valueOf(registerType.trim()));
				
				//fill the register gateway address
				register.setGatewayIPAddress(InetAddress.getByName(this.gwAddress));
				
				//fill the register gateway port
				register.setGatewayPort(this.gwPort);
				
				//fill the protocol variant associated to the gateway
				register.setGatewayProtocol(this.gwProtocol);
				
				//fill the slave id
				register.setSlaveId(Integer.valueOf(registerSlaveId));
				
				//fill the translator properties
				RegXlator xlator = register.getXlator();
				
				//the unit of measure
				xlator.setUnitOfMeasure(unitOfMeasure);
				xlator.setScaleFactor(Double.valueOf(scaleFactor.trim()));
				
				// fill the data point to notification map, if the data point
				// has
				// never been registered create a new entry in the map.
				Set<CmdNotificationInfo> notificationNames = this.register2Notification.get(register);
				
				if (notificationNames == null)
				{
					notificationNames = new HashSet<CmdNotificationInfo>();
					this.register2Notification.put(register, notificationNames);
				}
				// add the notification name to the set associated to the dp
				// datapoint
				CmdNotificationInfo nInfo = new CmdNotificationInfo(notificationName, parameter.getElementParams());
				notificationNames.add(nInfo);
				
				// add the datapoint to the set of managed datapoints
				this.managedRegisters.add(register);
			}
			}
			catch(Exception e)
			{
				//do not handle the register commands...
			}
		}
		
	}

	/**
	 * Tries to retrieve the initial state of the device handled by this driver
	 */
	public void getInitialState()
	{
		// for each datapoint registered with this driver, call the read command
		for (ModbusRegisterInfo register : this.managedRegisters)
			this.network.read(register);
	}


	/**
	 * @return the gwAddress
	 */
	public String getGatewayAddress()
	{
		return gwAddress;
	}
	
	/**
	 * 
	 * @return the gwPort
	 */
	public String getGatewayPort()
	{
		return gwPort;
	}

	/**
	 * @return the gwProtocol
	 */
	public String getGwProtocol()
	{
		return gwProtocol;
	}	
}
