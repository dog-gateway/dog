/*
 * Dog - Device Driver
 * 
 * Copyright (c) 2011-2014 Luigi De Russis and Dario Bonino
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
package it.polito.elite.dog.drivers.knx.onoffdevice;

import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.Buzzer;
import it.polito.elite.dog.core.library.model.devicecategory.ElectricalSystem;
import it.polito.elite.dog.core.library.model.devicecategory.Lamp;
import it.polito.elite.dog.core.library.model.devicecategory.MainsPowerOutlet;
import it.polito.elite.dog.core.library.model.devicecategory.OnOffOutput;

import javax.measure.Measure;

import it.polito.elite.dog.core.library.model.devicecategory.SimpleLamp;
import it.polito.elite.dog.core.library.model.state.OnOffState;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.OffStateValue;
import it.polito.elite.dog.core.library.model.statevalue.OnStateValue;
import it.polito.elite.dog.drivers.knx.network.KnxIPDriverInstance;
import it.polito.elite.dog.drivers.knx.network.info.KnxIPDeviceInfo;
import it.polito.elite.dog.drivers.knx.network.interfaces.KnxIPNetwork;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

import tuwien.auto.calimero.dptxlator.DPTXlatorBoolean;

/**
 * The driver for KNX on/off devices (Lamps, MainsPowerOutlet, etc.) using the KNXNet/IP protocol. It extends the
 * KNXIPDriver abstract class that performs common network operations for such a driver.
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class KnxIPOnOffDeviceDriverInstance extends KnxIPDriverInstance implements Lamp, SimpleLamp, Buzzer, MainsPowerOutlet
{
	// the driver logger
	LogHelper logger;
	
	// the log identifier, unique for the class
	public static String logId = "[KnxIPOnOffDriverInstance]: ";
	
	/**
	 * The class constructor. It calls the super-class constructor to obtain common initialization variables and
	 * methods.
	 * 
	 * @param network
	 *            the network driver to use (as described by the {@link KnxIPNetwork} interface.
	 * @param device
	 *            the device to which this driver is attached/associated
	 * @param gwIpAddress
	 */
	public KnxIPOnOffDeviceDriverInstance(KnxIPNetwork network, ControllableDevice device, String gatewayAddress,
			BundleContext context)
	{
		// the super-call constructor
		super(network, device, gatewayAddress);
		
		// init the logger
		this.logger = new LogHelper(context);
		
		// set default initial state to off...
		currentState = new DeviceStatus(device.getDeviceId());
		currentState.setState(OnOffState.class.getSimpleName(), new OnOffState(new OffStateValue()));
		
		// ... then ask the (real) initial state to the KNX gateway
		this.getInitialState();
	}
	
	/**
	 * Handle the on command
	 */
	@Override
	public void on()
	{
		this.sendKnxCommand("on");
	}
	
	/**
	 * Handle the off command
	 */
	@Override
	public void off()
	{
		this.sendKnxCommand("off");
	}
	
	/**
	 * Send a command (on or off, in this case) by performing a write on the KNX network
	 * 
	 * @param commandName
	 *            The command name (i.e., on or off)
	 */
	private void sendKnxCommand(String commandName)
	{
		// get the KNX IP Device Info (group address, etc.) associated to the
		// required command name
		KnxIPDeviceInfo command = this.commandName2CommandMap.get(commandName);
		
		String commandAddress = null;
		
		if (command != null)
		{
			// get the corresponding group address
			commandAddress = command.getGroupAddress();
		}
		else
		{
			commandAddress = this.globalGroupAddress;
		}
		
		if (commandAddress != null)
		{
			try
			{
				KnxIPDeviceInfo devInfo = new KnxIPDeviceInfo(this.device.getDeviceId(), commandAddress);
				devInfo.setGatewayIPAddress(InetAddress.getByName(this.gwAddress));
				
				// write the command on the network
				this.network.write(devInfo, commandName);
			}
			catch (Exception e)
			{
				//TODO handle exception
			}
		}
		
	}
	
	/**
	 * Get the current state from DogState (not querying the "real" KNX device)
	 */
	@Override
	public DeviceStatus getState()
	{
		return this.currentState;
	}
	
	/*
	 * Handle the driver behavior when it received a new message from the KNX IP network: typically it involves a state
	 * change.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.dog.drivers.knx.network.KnxIPDriver# newMessageFromHouse(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void newMessageFromHouse(String source, String destination, String value)
	{
		
		if ((value != null) && (!value.isEmpty()))
		{
			// TODO Check if source and destination are allowed?
			if (value.equalsIgnoreCase(OnOffState.ON))
			{
				this.changeCurrentState(OnOffState.ON);
			}
			else if (value.equalsIgnoreCase(OnOffState.OFF))
			{
				this.changeCurrentState(OnOffState.OFF);
			}
		}
		
	}
	
	/**
	 * Check if the current state has been changed. In that case, fire a state change message, otherwise it does nothing
	 * 
	 * @param OnOffValue
	 *            KnxOnOffDeviceDriver.ON or KnxOnOffDeviceDriver.OFF
	 */
	private void changeCurrentState(String OnOffValue)
	{
		String currentStateValue = (String) this.currentState.getState(OnOffState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
		
		// debug
		this.logger.log(LogService.LOG_INFO, KnxIPOnOffDeviceDriverInstance.logId + "Received new state value: "
				+ OnOffValue + " old: " + currentStateValue);
		
		// if the current states it is different from the new state
		if (!currentStateValue.equalsIgnoreCase(OnOffValue))
		{
			State newState;
			// set the new state to on or off...
			if (OnOffValue.equalsIgnoreCase(OnOffState.ON))
			{
				newState = new OnOffState(new OnStateValue());
				this.notifyOn();
			}
			else
			{
				newState = new OnOffState(new OffStateValue());
				this.notifyOff();
			}
			// ... then set the new state for the device and throw a state
			// changed notification
			this.currentState.setState(newState.getStateName(), newState);
			this.updateStatus();
			
			// log
			this.logger.log(LogService.LOG_INFO, KnxIPOnOffDeviceDriverInstance.logId + "Notified new state: "
					+ newState.getCurrentStateValue()[0].getValue());
		}
		
	}
	
	@Override
	public void updateStatus()
	{
		((ElectricalSystem) this.device).updateStatus();
	}
	

	@Override
	public void notifyOn()
	{
		((OnOffOutput) this.device).notifyOn();
	}

	@Override
	public void notifyOff()
	{
		((OnOffOutput) this.device).notifyOff();
	}
	
	@Override
	protected void addToNetworkDriver(KnxIPDeviceInfo deviceInfo)
	{
		try
		{
			KnxIPDeviceInfo devInfo = new KnxIPDeviceInfo(this.device.getDeviceId(), deviceInfo.getGroupAddress());
			devInfo.setGatewayIPAddress(InetAddress.getByName(this.gwAddress));
			this.network.addDriver(devInfo, 1, DPTXlatorBoolean.DPT_SWITCH, this);
		}
		catch (UnknownHostException e)
		{
			// TODO handle exception here
		}
	}
	
	@Override
	protected void specificConfiguration()
	{
		// intentionally left empty, no specific configuration for this driver
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
	public void notifyStoredScene(Integer sceneNumber)
	{
		// intentionally left empty
	}

	@Override
	public void notifyDeletedScene(Measure<?, ?> sceneNumber)
	{
		// intentionally left empty
	}

	@Override
	public void notifyJoinedGroup(Integer groupNumber)
	{
		// intentionally left empty
	}

	@Override
	public void notifyBelongToGroup(Integer groupNumber)
	{
		// intentionally left empty
	}

	@Override
	public void notifyLeftGroup(Integer groupNumber)
	{
		// intentionally left empty
	}
	
}
