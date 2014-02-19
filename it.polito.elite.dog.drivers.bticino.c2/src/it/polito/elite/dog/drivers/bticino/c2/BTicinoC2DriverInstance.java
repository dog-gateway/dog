/*
 * Dog - Device Driver
 * 
 * Copyright (c) 2010-2014 Emiliano Castellina and Luigi De Russis
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
package it.polito.elite.dog.drivers.bticino.c2;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.ElectricalSystem;
import it.polito.elite.dog.core.library.model.devicecategory.ShutterActuator;
import it.polito.elite.dog.core.library.model.devicecategory.ShutterButton;
import it.polito.elite.dog.core.library.model.state.ShutterState;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.state.UpDownRestState;
import it.polito.elite.dog.core.library.model.statevalue.DownTripleStateValue;
import it.polito.elite.dog.core.library.model.statevalue.LoweringStateValue;
import it.polito.elite.dog.core.library.model.statevalue.RaisingStateValue;
import it.polito.elite.dog.core.library.model.statevalue.RestTripleStateValue;
import it.polito.elite.dog.core.library.model.statevalue.UpTripleStateValue;
import it.polito.elite.dog.drivers.bticino.interfaces.BTicinoNetworkDriver;
import it.polito.elite.dog.drivers.bticino.interfaces.BTicinoSpecificDriver;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.bticino.core.OpenWebNet;

/**
 * 
 * @author <a href="mailto:castellina.emi@gmail.com">Emiliano Castellina</a>
 *         (original version)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class BTicinoC2DriverInstance implements BTicinoSpecificDriver, ShutterActuator, ShutterButton
{
	private final String type = "2";
	private String address;
	private BTicinoNetworkDriver network;
	private ControllableDevice device;
	
	private final String up = "1";
	private final String down = "2";
	private final String rest = "0";
	
	private BTicinoC2Driver driver;
	// Current States of the device
	private DeviceStatus deviceState;
	private Timer timedAction;
	
	/***
	 * Class constructor
	 * 
	 * @param device
	 *            device that must be connected to the driver instance
	 * @param driver
	 *            main driver reference, used to get the BTicno Network
	 */
	public BTicinoC2DriverInstance(ControllableDevice device, BTicinoC2Driver driver)
	{
		
		Set<String> addressList = (Set<String>) device.getDeviceDescriptor().getSimpleConfigurationParams()
				.get(BTicinoNetworkDriver.ADDRESS);
		
		this.address = addressList.iterator().next();
		this.device = device;
		this.network = driver.getNetwork();
		this.driver = driver;
		this.deviceState = new DeviceStatus(this.device.getDeviceId());
		this.timedAction = new Timer();
		
		// default state
		if (this.device instanceof ShutterButton)
		{
			this.deviceState.setState(UpDownRestState.class.getSimpleName(), new UpDownRestState(
					new RestTripleStateValue()));
		}
		if (this.device instanceof ShutterActuator)
		{
			this.deviceState.setState(ShutterState.class.getSimpleName(), new ShutterState(new RestTripleStateValue()));
		}
		this.readStatus();
		if (this.address != null)
		{
			this.device.setDriver(this);
			this.network.bind(this, address);
			if (address.length() > 1)
			{
				this.network.bind(this, address.substring(0, 1));
			}
		}
		
	}
	
	private void sendLowMessage(String lMessage)
	{
		OpenWebNet message = new OpenWebNet();
		message.createMsgOpen(type, lMessage, address, "");
		network.sendMyOpenMessage(message, 50);
		
	}
	
	private void readStatus()
	{
		OpenWebNet message = new OpenWebNet();
		message.CreateStateMsgOpen(type, address);
		network.sendMyOpenMessage(message, 50);
	}
	
	@Override
	public void recieveLowLevelMessage(OpenWebNet message)
	{
		this.timedAction.cancel();
		this.timedAction = new Timer();
		String what = message.getCosa();
		State state = null;
		State currentMovementState = this.deviceState.getState(ShutterState.class.getSimpleName());
		
		if (what.equals("0"))
		{
			// get the current state
			if (currentMovementState != null)
			{
				String currentMovementStateValue = (String) currentMovementState.getCurrentStateValue()[0].getValue();
				if (this.device instanceof ShutterActuator && currentMovementStateValue.equals(ShutterState.RAISING)
						|| currentMovementStateValue.equals(ShutterState.LOWERING))
				{
					state = new ShutterState(new RestTripleStateValue());
				}
			}
			if (this.device instanceof ShutterButton)
			{
				State currentState = this.deviceState.getState(UpDownRestState.class.getSimpleName());
				if (!currentState.getCurrentStateValue()[0].getValue().equals(UpDownRestState.REST))
				{
					state = new UpDownRestState(new RestTripleStateValue());
				}
			}
		}
		else if (what.equals("2"))
		{
			
			if (this.device instanceof ShutterActuator)
			{
				state = new ShutterState(new LoweringStateValue());
				this.timedAction.schedule(new BTicinoUtilTimer(this, ShutterState.DOWN), BTicinoC2Driver.time);
			}
			else
			{
				this.notifyPressedDown();
				state = new UpDownRestState(new DownTripleStateValue());
				this.timedAction.schedule(new BTicinoUtilTimer(this, UpDownRestState.REST), 20);
			}
		}
		else if (what.equals("1"))
		{
			
			if (this.device instanceof ShutterActuator)
			{
				state = new ShutterState(new RaisingStateValue());
				this.timedAction.schedule(new BTicinoUtilTimer(this, ShutterState.UP), BTicinoC2Driver.time);
			}
			else
			{
				this.notifyPressedUp();
				state = new UpDownRestState(new UpTripleStateValue());
				this.timedAction.schedule(new BTicinoUtilTimer(this, UpDownRestState.REST), 20);
			}
		}
		
		if (state != null)
		{
			
			this.deviceState.setState(state.getStateName(), state);
			((ElectricalSystem) this.device).notifyStateChanged(state);
		}
		
	}
	
	public void unSet()
	{
		if (this.device != null)
		{
			this.device.unSetDriver(driver);
		}
		
	}
	
	@Override
	public void down()
	{
		this.sendLowMessage(down);
		
	}
	
	@Override
	public void rest()
	{
		this.sendLowMessage(rest);
		
	}
	
	@Override
	public void up()
	{
		this.sendLowMessage(up);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.domotics.model.devicecategory.ShutterActuator#getState()
	 */
	@Override
	public DeviceStatus getState()
	{
		
		return this.deviceState;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.domotics.model.devicecategory.ShutterActuator#
	 * notifyStateChanged(it.polito .elite.domotics.model.state.State)
	 */
	@Override
	public void notifyStateChanged(State newState)
	{
		// intentionally left empty
		
	}
	
	class BTicinoUtilTimer extends TimerTask
	{
		
		private BTicinoC2DriverInstance instance;
		private String action;
		
		public BTicinoUtilTimer(BTicinoC2DriverInstance instance, String action)
		{
			this.instance = instance;
			this.action = action;
		}
		
		@Override
		public void run()
		{
			this.instance.changeCurrentState(action);
			
		}
		
	}
	
	/**
	 * Change the current state
	 * */
	public void changeCurrentState(String action)
	{
		
		State state = null;
		if (this.device instanceof ShutterActuator)
		{
			if (action.equalsIgnoreCase(ShutterState.DOWN))
			{
				state = new ShutterState(new DownTripleStateValue());
			}
			else if (action.equalsIgnoreCase(ShutterState.LOWERING))
			{
				state = new ShutterState(new LoweringStateValue());
			}
			else if (action.equalsIgnoreCase(ShutterState.RAISING))
			{
				state = new ShutterState(new RaisingStateValue());
			}
			else if (action.equalsIgnoreCase(ShutterState.REST))
			{
				state = new ShutterState(new RestTripleStateValue());
			}
			else if (action.equalsIgnoreCase(ShutterState.UP))
			{
				state = new ShutterState(new UpTripleStateValue());
			}
		}
		else
		{
			if (action.equalsIgnoreCase(UpDownRestState.DOWN))
			{
				state = new UpDownRestState(new DownTripleStateValue());
			}
			else if (action.equalsIgnoreCase(UpDownRestState.UP))
			{
				state = new UpDownRestState(new UpTripleStateValue());
			}
			else if (action.equalsIgnoreCase(UpDownRestState.REST))
			{
				state = new UpDownRestState(new RestTripleStateValue());
			}
			
		}
		this.deviceState.setState(state.getStateName(), state);
		((ElectricalSystem) this.device).notifyStateChanged(state);
		
	}
	
	@Override
	public void notifyPressedUp()
	{
		((ShutterButton) this.device).notifyPressedUp();
		
	}
	
	@Override
	public void notifyPressedDown()
	{
		((ShutterButton) this.device).notifyPressedDown();
		
	}
}
