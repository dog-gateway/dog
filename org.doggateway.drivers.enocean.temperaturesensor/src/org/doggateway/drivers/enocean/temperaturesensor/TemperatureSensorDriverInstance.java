/**
 * 
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
 * @author bonino
 *
 */
public class TemperatureSensorDriverInstance extends EnOceanDriverInstance
		implements SingleTemperatureSensor
{

	// the class logger
	private LogHelper logger;

	public TemperatureSensorDriverInstance(EnOceanNetwork enOceanNetwork,
			ControllableDevice device, int updateTimeMillis,
			BundleContext context)
	{
		// call the superclass constructor
		super(enOceanNetwork, device);

		// create a logger
		this.logger = new LogHelper(context);

		// initialize the rocker switch states
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteGroup(Integer groupID)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void storeGroup(Integer groupID)
	{
		// TODO Auto-generated method stub

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
	 * and notfies any change
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
			//treat the temperature as a measure
			DecimalMeasure<?> temperature = DecimalMeasure.valueOf(value + " "
					+ SI.CELSIUS);
			
			//update the current state
			this.currentState.getState(TemperatureState.class.getSimpleName())
					.getCurrentStateValue()[0].setValue(temperature);

			// update the status (Monitor Admin)
			this.updateStatus();

			// notify the change
			this.notifyNewTemperatureValue(temperature);
			
			// log
			this.logger.log(LogService.LOG_INFO, "Device " + device.getDeviceId()
				+ " temperature " + temperature.toString());

		}
	}

}
