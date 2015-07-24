/*
 * Dog - EnOcean Door Sensor Driver
 * 
 * Copyright 2015 Dario Bonino 
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
package org.doggateway.drivers.enocean.doorsensor;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.Controllable;
import it.polito.elite.dog.core.library.model.devicecategory.DoorSensor;
import it.polito.elite.dog.core.library.model.state.OpenCloseState;
import it.polito.elite.dog.core.library.model.statevalue.CloseStateValue;
import it.polito.elite.dog.core.library.model.statevalue.OpenStateValue;
import it.polito.elite.dog.core.library.model.statevalue.StateValue;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.enocean.enj.eep.EEPAttribute;
import it.polito.elite.enocean.enj.eep.eep26.attributes.EEP26Switching;
import it.polito.elite.enocean.enj.model.EnOceanDevice;

import org.doggateway.drivers.enocean.network.EnOceanDriverInstance;
import org.doggateway.drivers.enocean.network.info.EnOceanDeviceInfo;
import org.doggateway.drivers.enocean.network.interfaces.EnOceanNetwork;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * <p>
 * Implements the actual handling, in Dog, of EnOcean devices having an EEP in
 * the D50001 family. Takes care of registering needed listeners and hooks to
 * both the network and gateway driver and, handles status updates and
 * notifications for corresponding devices in Dog.
 * </p>
 *
 * @author <a href="mailto:dario.bonino@gmail.com">Dario Bonino</a>
 *
 */
public class EnOceanDoorSensorDriverInstance extends EnOceanDriverInstance
		implements DoorSensor
{

	// the class logger
	private LogHelper logger;

	/**
	 * Class constructor, builds a fully functional instance of Door Sensor
	 * driver
	 * 
	 * @param enOceanNetwork
	 *            The EnOcean network driver used to access the low-level
	 *            network infrastructure
	 * @param device
	 *            The Dog device to which this instance shall be connected
	 * @param updateTimeMillis
	 *            The required update time in millis (not needed inthis case)
	 * @param context
	 *            The bundle context to perform any needed operation involving
	 *            the OSGi Framework (e.g., logging)
	 */
	public EnOceanDoorSensorDriverInstance(EnOceanNetwork enOceanNetwork,
			ControllableDevice device, int updateTimeMillis,
			BundleContext context)
	{
		// call the superclass constructor
		super(enOceanNetwork, device);

		// create a logger
		this.logger = new LogHelper(context);

		// initialize the door sensor states
		this.initializeStates();

	}

	@Override
	public DeviceStatus getState()
	{
		// provides back the current state of the device
		return this.currentState;
	}

	@Override
	public void notifyOpen()
	{
		((DoorSensor) this.device).notifyOpen();

	}

	@Override
	public void notifyClose()
	{
		((DoorSensor) this.device).notifyClose();

	}

	@Override
	public void updateStatus()
	{
		((Controllable) this.device).updateStatus();
	}

	@Override
	protected void specificConfiguration()
	{
		// prepare the device state map
		this.currentState = new DeviceStatus(device.getDeviceId());
	}

	@Override
	protected void addToNetworkDriver(EnOceanDeviceInfo device)
	{
		// register this driver as handler for the device described by the given
		// device info.
		this.network.addDriver(device, this);
	}

	@Override
	protected void bindDevice(EnOceanDevice device)
	{
		// called when the given device is "attached" at the network level, used
		// for registering listeners

		// double check
		if (device.getDeviceUID() == this.theManagedDevice.getUid())
		{
			// store the low level device
			this.theLowLevelDevice = device;

			// register eep listeners
			this.theLowLevelDevice.getEEP().addEEP26AttributeListener(0,
					EEP26Switching.NAME, this);
		}

	}

	@Override
	protected void unBindDevice(EnOceanDevice device)
	{
		// double check
		if (device.getDeviceUID() == this.theManagedDevice.getUid())
		{
			// remove the listener
			this.theLowLevelDevice.getEEP().removeEEP26AttributeListener(0,
					EEP26Switching.NAME, this);

			// null the low level device
			this.theLowLevelDevice = null;
		}

	}

	@Override
	public void handleAttributeChange(int channelId, EEPAttribute<?> attribute)
	{
		// handle the attribute change
		if (attribute instanceof EEP26Switching)
		{
			this.updateAndNotify(((EEP26Switching) attribute).getValue());
		}

	}

	/**
	 * Prepare the data structures needed to model the device states, and fills
	 * it with the initial values.
	 */
	private void initializeStates()
	{
		// initially closed
		CloseStateValue value = new CloseStateValue();

		// create the door open/close state
		OpenCloseState state = new OpenCloseState(value);

		// set the state as current state
		this.currentState.setState(OpenCloseState.class.getSimpleName(), state);

	}

	/**
	 * Updates the current status of the device handled by this driver instance
	 * and notifies any change
	 * 
	 * @param open True if the sensor senses the door as open, false otherwise
	 */
	private void updateAndNotify(Boolean open)
	{
		// check for changes
		StateValue cStateValue = this.currentState.getState(
				OpenCloseState.class.getSimpleName()).getCurrentStateValue()[0];

		// if the status has changed
		if (((cStateValue instanceof CloseStateValue) && (open))
				|| ((cStateValue instanceof OpenStateValue) && (!open)))
		{
			// update the status
			if (open)
			{
				// set the status to open
				this.currentState.setState(
						OpenCloseState.class.getSimpleName(),
						new OpenCloseState(new OpenStateValue()));

				// notify
				this.notifyOpen();
			}
			else
			{
				// set the status to open
				this.currentState.setState(
						OpenCloseState.class.getSimpleName(),
						new OpenCloseState(new CloseStateValue()));

				// notify
				this.notifyClose();
			}

			// update the status (Monitor Admin)
			this.updateStatus();

			// log
			this.logger.log(LogService.LOG_INFO,
					"Device " + device.getDeviceId() + "is now "
							+ (open ? "open" : "closed"));
		}

	}

}
