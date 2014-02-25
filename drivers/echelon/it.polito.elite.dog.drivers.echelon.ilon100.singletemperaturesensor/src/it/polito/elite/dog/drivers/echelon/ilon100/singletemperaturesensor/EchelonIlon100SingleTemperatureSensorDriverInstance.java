/*
 * Dog - Device Driver
 * 
 * Copyright (c) 2012-2014 Dario Bonino
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
package it.polito.elite.dog.drivers.echelon.ilon100.singletemperaturesensor;

import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.SingleTemperatureSensor;
import it.polito.elite.dog.core.library.model.notification.TemperatureMeasurementNotification;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.state.TemperatureState;
import it.polito.elite.dog.core.library.model.statevalue.TemperatureStateValue;
import it.polito.elite.dog.drivers.echelon.ilon100.network.EchelonIlon100DriverInstance;
import it.polito.elite.dog.drivers.echelon.ilon100.network.info.CmdNotificationInfo;
import it.polito.elite.dog.drivers.echelon.ilon100.network.info.DataPointInfo;
import it.polito.elite.dog.drivers.echelon.ilon100.network.interfaces.EchelonIlon100Network;

import java.lang.reflect.Method;
import java.util.Set;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.unit.SI;
import javax.measure.unit.UnitFormat;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class EchelonIlon100SingleTemperatureSensorDriverInstance extends EchelonIlon100DriverInstance implements
		SingleTemperatureSensor
{
	// the class logger
	private LogHelper logger;
	
	/**
	 * The class constructor, initializes all the data structures needed for the
	 * driver to successfully handle TemperatureSensor devices
	 * 
	 * @param network
	 *            The network driver supporting communication towards the
	 *            real-world iLon100 gateway
	 * @param device
	 *            The device managed by this meter driver
	 * @param endpointAddress
	 *            The endpoint address of the real iLon 100 gateway handling
	 *            this device
	 * @param context
	 *            The bundle context needed to instantiate the driver logger
	 */
	public EchelonIlon100SingleTemperatureSensorDriverInstance(EchelonIlon100Network network,
			ControllableDevice device, String endpointAddress, BundleContext context)
	{
		super(network, device, endpointAddress);
		
		// create a logger
		this.logger = new LogHelper(context);
		
		// TODO: get the initial state of the device....(states can be updated
		// by reading notification group addresses)
		this.initializeStates();
	}
	
	@Override
	public Measure<?, ?> getTemperature()
	{
		// direct read on the network...
		
		// search the power notification...
		/*
		 * for (DataPointInfo dp : this.datapoint2Notification.keySet()) { if
		 * (this.datapoint2Notification.get(dp).contains(
		 * TemperatureMeasurementNotification.notificationName)) {
		 * this.network.readDP(dp); return dp.getValueWithUnitOfMeasure(); } }
		 * return null;
		 */
		
		return (Measure<?, ?>) this.currentState.getState(TemperatureState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}
	
	@Override
	public void deleteGroup(String groupID)
	{
		// nothing to do by now... will be handled in the future... may be...
		
	}
	
	@Override
	public void storeGroup(String groupID)
	{
		// nothing to do by now... will be handled in the future... may be...
		
	}
	
	@Override
	public void notifyNewTemperatureValue(Measure<?, ?> temperatureValue)
	{
		// update the state
		TemperatureStateValue tValue = new TemperatureStateValue();
		tValue.setValue(temperatureValue);
		this.currentState.setState(TemperatureState.class.getSimpleName(), new TemperatureState(tValue));
		
		// forward the notification to the framework
		((SingleTemperatureSensor) this.device).notifyNewTemperatureValue(temperatureValue);
		
		// log
		this.logger.log(LogService.LOG_DEBUG, EchelonIlon100SingleTemperatureSensorDriver.logId
				+ "Dispatched new temperature notification value for " + this.device.getDeviceId() + ": "
				+ temperatureValue.getValue() + ((temperatureValue.getUnit().equals(SI.CELSIUS)) ? " C" : ""));
		
	}
	
	@Override
	public DeviceStatus getState()
	{
		return this.currentState;
	}
	
	@Override
	public void notifyStateChanged(State newState)
	{
		// probably unused...
		((SingleTemperatureSensor) this.device).notifyStateChanged(newState);
	}
	
	@Override
	public void newMessageFromHouse(DataPointInfo dataPointInfo)
	{
		// check value
		if ((dataPointInfo.getValue() != Double.NaN) && (dataPointInfo.getValue() != Double.NEGATIVE_INFINITY)
				&& (dataPointInfo.getValue() != Double.POSITIVE_INFINITY))
		{
			// gets the corresponding notification set...
			Set<CmdNotificationInfo> notificationInfos = this.datapoint2Notification.get(dataPointInfo);
			
			// handle the notifications
			for (CmdNotificationInfo notificationInfo : notificationInfos)
			{
				// black magic here...
				String notificationName = notificationInfo.getName();
				
				// black magic here...
				
				// get the hypothetical class method name
				String notifyMethod = "notify" + Character.toUpperCase(notificationName.charAt(0))
						+ notificationName.substring(1);
				
				// search the method and execute it
				try
				{
					// get the method
					Method notify = EchelonIlon100SingleTemperatureSensorDriverInstance.class.getDeclaredMethod(
							notifyMethod, Measure.class);
					// invoke the method
					notify.invoke(this,
							DecimalMeasure.valueOf(dataPointInfo.getValue() + " " + dataPointInfo.getUnitOfMeasure()));
				}
				catch (Exception e)
				{
					// log the error
					this.logger.log(LogService.LOG_WARNING, EchelonIlon100SingleTemperatureSensorDriver.logId
							+ "Unable to find a suitable notification method for the datapoint: " + dataPointInfo
							+ ":\n" + e);
				}
				
			}
		}
		
	}
	
	@Override
	protected void specificConfiguration()
	{
		// prepare the device state map
		this.currentState = new DeviceStatus(device.getDeviceId());
		
	}
	
	private void initializeStates()
	{
		// Since this driver handles the device according to a well
		// defined interface, we can get the unit of measure from the only
		// notification handled by this device and fall back to Celsius degrees
		// if the procedure fails...
		
		// add unit of measure aliases (to fix notation problems...)
		UnitFormat uf = UnitFormat.getInstance();
		uf.alias(SI.CELSIUS, "C");
		uf.alias(SI.CELSIUS, "Celsius");
		
		String temperatureUOM = SI.CELSIUS.toString();
		
		// search the power notification...
		for (DataPointInfo dp : this.datapoint2Notification.keySet())
		{
			if (this.datapoint2Notification.get(dp).contains(TemperatureMeasurementNotification.notificationName))
			{
				temperatureUOM = dp.getUnitOfMeasure();
				break; // stop the cycle as data has been found
			}
		}
		
		// create all the states
		TemperatureStateValue tValue = new TemperatureStateValue();
		tValue.setValue(DecimalMeasure.valueOf("0 " + temperatureUOM));
		this.currentState.setState(TemperatureState.class.getSimpleName(), new TemperatureState(tValue));
		
		// read the current state (initial)
		for (DataPointInfo dp : this.datapoint2Notification.keySet())
		{
			this.network.read(dp);
		}
		
	}
	
	@Override
	protected void addToNetworkDriver(DataPointInfo dp)
	{
		// add the datapoint to the network driver, no further operation needed
		this.network.addDriver(dp, this);
		
	}
	
}
