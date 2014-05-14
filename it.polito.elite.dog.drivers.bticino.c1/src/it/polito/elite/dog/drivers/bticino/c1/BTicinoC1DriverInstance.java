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
package it.polito.elite.dog.drivers.bticino.c1;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.Button;
import it.polito.elite.dog.core.library.model.devicecategory.DimmerLamp;
import it.polito.elite.dog.core.library.model.devicecategory.ElectricalSystem;
import it.polito.elite.dog.core.library.model.devicecategory.Lamp;
import it.polito.elite.dog.core.library.model.devicecategory.OnOffOutput;
import it.polito.elite.dog.core.library.model.devicecategory.OnOffSwitch;
import it.polito.elite.dog.core.library.model.devicecategory.SimpleLamp;
import it.polito.elite.dog.core.library.model.state.OnOffState;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.OffStateValue;
import it.polito.elite.dog.core.library.model.statevalue.OnStateValue;
import it.polito.elite.dog.drivers.bticino.interfaces.BTicinoNetworkDriver;
import it.polito.elite.dog.drivers.bticino.interfaces.BTicinoSpecificDriver;

import java.util.Set;

import javax.measure.Measure;

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
public class BTicinoC1DriverInstance implements BTicinoSpecificDriver, Lamp, SimpleLamp, DimmerLamp, OnOffSwitch, Button
{
	private final String type = "1";
	private String address;
	
	/**
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}
	
	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	BTicinoNetworkDriver network;
	ControllableDevice device;
	
	private final String on = "1";
	private final String off = "0";
	
	private final String stepUp = "30";
	private final String stepDown = "31";
	private BTicinoC1Driver driver;
	
	private DeviceStatus deviceInnerState;
	
	public BTicinoC1DriverInstance(ControllableDevice device, BTicinoC1Driver driver)
	{
		this.device = device;
		this.network = driver.getNetwork();
		this.driver = driver;
		this.deviceInnerState = new DeviceStatus(this.device.getDeviceId());
		this.deviceInnerState.setState(OnOffState.class.getSimpleName(), new OnOffState(new OffStateValue()));
		
		Set<String> addressList = (Set<String>) device.getDeviceDescriptor().getSimpleConfigurationParams()
				.get(BTicinoNetworkDriver.ADDRESS);
		
		this.address = addressList.iterator().next();
		
		if (address != null)
		{
			this.network.bind(this, this.address);
			if (address.length() > 1)
			{
				this.network.bind(this, address.substring(0, 1));
			}
			this.readStatus();
			this.device.setDriver(this);
		}
	}
	
	@Override
	public void off()
	{
		this.sendLowMessage(off);
		
	}
	
	@Override
	public void on()
	{
		this.sendLowMessage(on);
	}
	
	private void sendLowMessage(String lMessage)
	{
		OpenWebNet message = new OpenWebNet();
		message.createMsgOpen(type, lMessage, address, "");
		network.sendMyOpenMessage(message, 50);
	}
	
	@Override
	public void set(Object value)
	{
		float valFloat = (Float) value;
		if (valFloat > 10 && valFloat <= 100)
		{
			int valInt = (int) (valFloat / 10.0) + 1;
			String lowCommand = "" + valInt;
			this.sendLowMessage(lowCommand);
		}
		
	}
	
	@Override
	public void stepDown()
	{
		this.sendLowMessage(stepDown);
	}
	
	@Override
	public void stepUp()
	{
		this.sendLowMessage(stepUp);
	}
	
	@Override
	public void recieveLowLevelMessage(OpenWebNet message)
	{
		String what = message.getCosa();
		
		State state = null;
		if (what.equals("0"))
		{
			state = new OnOffState(new OffStateValue());
			if (this.device instanceof Button)
			{
				this.notifyReleased();
			}
			else
			{
				this.notifyOff();
			}
			
		}
		else if (what.equals("1"))
		{
			state = new OnOffState(new OnStateValue());
			if (this.device instanceof Button)
			{
				this.notifyPressed();
			}
			else
			{
				this.notifyOn();
			}
		}
		
		if (state != null)
		{
			this.deviceInnerState.setState(state.getStateName(), state);
			this.updateStatus();
			
		}
	}
	
	public void unSet()
	{
		if (this.device != null)
		{
			this.device.unSetDriver(driver);
		}
		
	}
	
	private void readStatus()
	{
		OpenWebNet message = new OpenWebNet();
		message.CreateStateMsgOpen(type, address);
		network.sendMyOpenMessage(message, 50);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.domotics.model.devicecategory.Lamp#getState()
	 */
	@Override
	public DeviceStatus getState()
	{
		return this.deviceInnerState;
	}
	
	@Override
	public void notifyPressed()
	{
		((Button) this.device).notifyPressed();
		
	}
	
	@Override
	public void notifyReleased()
	{
		((Button) this.device).notifyReleased();
		
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
	public void notifyStepUp()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyChangedLevel(Measure<?, ?> newLevel)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void notifyStepDown()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateStatus()
	{
		((ElectricalSystem) this.device).updateStatus();
	}
	
}
