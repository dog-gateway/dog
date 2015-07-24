/*
 * Dog - EnOcean Temperature Sensor Driver
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
package org.doggateway.drivers.enocean.temperaturesensor;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.Controllable;
import it.polito.elite.dog.core.library.model.devicecategory.SingleTemperatureSensor;
import it.polito.elite.dog.core.library.model.state.TemperatureState;
import it.polito.elite.dog.core.library.model.statevalue.TemperatureStateValue;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.enocean.enj.eep.EEPAttribute;
import it.polito.elite.enocean.enj.eep.eep26.attributes.EEP26TemperatureLinear;
import it.polito.elite.enocean.enj.model.EnOceanDevice;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.unit.SI;
import javax.measure.unit.UnitFormat;

import org.doggateway.drivers.enocean.network.EnOceanDriverInstance;
import org.doggateway.drivers.enocean.network.info.EnOceanDeviceInfo;
import org.doggateway.drivers.enocean.network.interfaces.EnOceanNetwork;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * <p>
 * Implements the actual handling, in Dog, of EnOcean devices having an EEP in
 * the A502XX family. Takes care of registering needed listeners and hooks to
 * both the network and gateway driver and, handles status updates and
 * notifications for corresponding devices in Dog.
 * </p>
 *
 * @author <a href="mailto:dario.bonino@gmail.com">Dario Bonino</a>
 *
 */
public class TemperatureSensorDriverInstance extends EnOceanDriverInstance
		implements SingleTemperatureSensor
{

	// the class logger
	private LogHelper logger;

	/**
	 * Class constructor, builds a fully functional instance of Single
	 * Temperature Sensor driver
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
	public TemperatureSensorDriverInstance(EnOceanNetwork enOceanNetwork,
			ControllableDevice device, int updateTimeMillis,
			BundleContext context)
	{
		// call the superclass constructor
		super(enOceanNetwork, device);

		// create a logger
		this.logger = new LogHelper(context);

		// initialize the temperature sensor states
		this.initializeStates();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.core.library.model.devicecategory.TemperatureSensor
	 * #getState()
	 */
	@Override
	public DeviceStatus getState()
	{
		// provides back the current state of the device
		return this.currentState;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.doggateway.drivers.enocean.network.EnOceanDriverInstance#
	 * specificConfiguration()
	 */
	@Override
	protected void specificConfiguration()
	{
		// prepare the device state map
		this.currentState = new DeviceStatus(device.getDeviceId());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.doggateway.drivers.enocean.network.EnOceanDriverInstance#
	 * addToNetworkDriver
	 * (org.doggateway.drivers.enocean.network.info.EnOceanDeviceInfo)
	 */
	@Override
	protected void addToNetworkDriver(EnOceanDeviceInfo device)
	{
		// register this driver as handler for the device described by the given
		// device info.
		this.network.addDriver(device, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.doggateway.drivers.enocean.network.EnOceanDriverInstance#bindDevice
	 * (it.polito.elite.enocean.enj.model.EnOceanDevice)
	 */
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
					EEP26TemperatureLinear.NAME, this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.doggateway.drivers.enocean.network.EnOceanDriverInstance#unBindDevice
	 * (it.polito.elite.enocean.enj.model.EnOceanDevice)
	 */
	@Override
	protected void unBindDevice(EnOceanDevice device)
	{
		// double check
		if (device.getDeviceUID() == this.theManagedDevice.getUid())
		{
			// remove the listener
			this.theLowLevelDevice.getEEP().removeEEP26AttributeListener(0,
					EEP26TemperatureLinear.NAME, this);

			// null the low level device
			this.theLowLevelDevice = null;
		}
	}

	@Override
	public Measure<?, ?> getTemperature()
	{
		// provides back the current temperature
		return (Measure<?, ?>) this.currentState.getState(
				TemperatureState.class.getSimpleName()).getCurrentStateValue()[0]
				.getValue();
	}

	@Override
	public void deleteGroup(Integer groupID)
	{
		// Currently not supported
	}

	@Override
	public void storeGroup(Integer groupID)
	{
		// Currently not supported
	}

	@Override
	public void notifyNewTemperatureValue(Measure<?, ?> temperatureValue)
	{
		((SingleTemperatureSensor) this.device)
				.notifyNewTemperatureValue(temperatureValue);
	}

	@Override
	public void notifyJoinedGroup(Integer groupNumber)
	{
		((SingleTemperatureSensor) this.device).notifyJoinedGroup(groupNumber);
	}

	@Override
	public void notifyLeftGroup(Integer groupNumber)
	{
		((SingleTemperatureSensor) this.device).notifyLeftGroup(groupNumber);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.enocean.enj.eep.EEPAttributeChangeListener#
	 * handleAttributeChange(int, it.polito.elite.enocean.enj.eep.EEPAttribute)
	 */
	@Override
	public void handleAttributeChange(int channelId, EEPAttribute<?> attribute)
	{
		// handle the attribute change
		if (attribute instanceof EEP26TemperatureLinear)
		{
			// get the attribute vakue
			EEP26TemperatureLinear temperature = (EEP26TemperatureLinear) attribute;

			// the temperature value as a double
			Double value = (Double) temperature.getValue();

			// the unit of measure (Celsius)
			String unit = temperature.getUnit(); // always Celsius

			// update and notify
			this.updateAndNotify(value, unit);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.core.library.model.devicecategory.TemperatureSensor
	 * #updateStatus()
	 */
	@Override
	public void updateStatus()
	{
		((Controllable) this.device).updateStatus();
	}

	/**
	 * Prepare the data structures needed to model the device states, and fills
	 * it with the initial values.
	 */
	private void initializeStates()
	{
		// update the CELSIUS notation
		UnitFormat uf = UnitFormat.getInstance();
		uf.label(SI.CELSIUS, "C");
		uf.alias(SI.CELSIUS, "C");

		// the initial temperature value
		TemperatureStateValue tValue = new TemperatureStateValue();
		tValue.setValue(DecimalMeasure.valueOf("0 " + SI.CELSIUS.toString()));

		// the initial state
		TemperatureState tState = new TemperatureState(tValue);

		// set the current state
		this.currentState.setState(TemperatureState.class.getSimpleName(),
				tState);
	}

	/**
	 * Updates the current status of the device handled by this driver instance
	 * and notifies any change
	 * 
	 * @param value
	 *            The temperature value to use for updating the state
	 * @param unit
	 *            The unit of measure, "Celsius" in all currently implemented
	 *            profiles
	 */
	private void updateAndNotify(Double value, String unit)
	{
		// check the unit
		if ((unit != null)
				&& (!unit.isEmpty())
				&& ((unit.equalsIgnoreCase("Celsius")
						|| unit.equalsIgnoreCase("°C") || unit
							.equalsIgnoreCase("C"))))
		{
			// treat the temperature as a measure
			DecimalMeasure<?> temperature = DecimalMeasure.valueOf(String.format("%.2f", value) + " "
					+ SI.CELSIUS);

			// update the current state
			this.currentState.getState(TemperatureState.class.getSimpleName())
					.getCurrentStateValue()[0].setValue(temperature);

			// update the status (Monitor Admin)
			this.updateStatus();

			// notify the change
			this.notifyNewTemperatureValue(temperature);

			// log
			this.logger.log(LogService.LOG_INFO,
					"Device " + device.getDeviceId() + " temperature "
							+ temperature.toString());

		}
	}

}
