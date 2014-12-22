/*
 * Dog - Device Driver
 * 
 * Copyright (c) 2012-2014 Dario Bonino and Luigi De Russis
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
package it.polito.elite.dog.drivers.echelon.ilon100.singlephaseactivepowermeter;

import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.SinglePhaseActivePowerMeter;
import it.polito.elite.dog.core.library.model.notification.SinglePhaseActivePowerMeasurementNotification;
import it.polito.elite.dog.core.library.model.state.SinglePhaseActivePowerMeasurementState;
import it.polito.elite.dog.core.library.model.statevalue.ActivePowerStateValue;
import it.polito.elite.dog.drivers.echelon.ilon100.network.EchelonIlon100DriverInstance;
import it.polito.elite.dog.drivers.echelon.ilon100.network.info.CmdNotificationInfo;
import it.polito.elite.dog.drivers.echelon.ilon100.network.info.DataPointInfo;
import it.polito.elite.dog.drivers.echelon.ilon100.network.interfaces.EchelonIlon100Network;

import java.lang.reflect.Method;
import java.util.Set;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.unit.SI;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * A class implementing the driver for SinglePhaseActivePowerMeters based on the
 * Echelon iLon 100 web-service based network protocol
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class EchelonIlon100SinglePhaseActivePowerMeterDriverInstance extends EchelonIlon100DriverInstance implements
		SinglePhaseActivePowerMeter
{
	// the class logger
	private LogHelper logger;
	
	/**
	 * The class constructor, initializes all the data structures needed for the
	 * driver to successfully handle SinglePhaseActivePowerMeter devices
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
	public EchelonIlon100SinglePhaseActivePowerMeterDriverInstance(EchelonIlon100Network network,
			final ControllableDevice device, String endpointAddress, BundleContext context)
	{
		super(network, device, endpointAddress);
		
		// create a logger
		this.logger = new LogHelper(context);
		
		// TODO: get the initial state of the device....(states can be updated
		// by reading notification group addresses)
		this.initializeStates();
	}
	
	@Override
	public Measure<?, ?> getActivePower()
	{
		// read the current state
		return (Measure<?, ?>) this.currentState.getState(SinglePhaseActivePowerMeasurementState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}
	
	@Override
	public DeviceStatus getState()
	{
		return this.currentState;
	}
	
	@Override
	public void notifyNewActivePowerValue(Measure<?, ?> powerValue)
	{
		// update the state
		ActivePowerStateValue pValue = new ActivePowerStateValue();
		pValue.setValue(powerValue);
		this.currentState.setState(SinglePhaseActivePowerMeasurementState.class.getSimpleName(),
				new SinglePhaseActivePowerMeasurementState(pValue));
		
		// forward the notification to the framework
		((SinglePhaseActivePowerMeter) this.device).notifyNewActivePowerValue(powerValue);
		
		// log
		this.logger
				.log(LogService.LOG_DEBUG, EchelonIlon100SinglePhaseActivePowerMeterDriver.logId
						+ "Dispatched new active power notification value for " + this.device.getDeviceId() + ": "
						+ powerValue);
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
					Method notify = EchelonIlon100SinglePhaseActivePowerMeterDriverInstance.class.getDeclaredMethod(
							notifyMethod, Measure.class);
					// invoke the method
					notify.invoke(this,
							DecimalMeasure.valueOf(dataPointInfo.getValue() + " " + dataPointInfo.getUnitOfMeasure()));
				}
				catch (Exception e)
				{
					// log the error
					this.logger.log(LogService.LOG_WARNING, EchelonIlon100SinglePhaseActivePowerMeterDriver.logId
							+ "Unable to find a suitable notification method for the datapoint: " + dataPointInfo
							+ ":\n" + e);
				}
				
				// notify the monitor admin
				this.updateStatus();
				
			}
		}
		
	}
	
	@Override
	protected void specificConfiguration()
	{
		// prepare the device state map
		this.currentState = new DeviceStatus(device.getDeviceId());
		
	}
	
	/**
	 * Initializes the inner states of the handled device...
	 */
	private void initializeStates()
	{
		// Since this driver handles the device metering according to a well
		// defined interface, we can get the unit of measure from the only
		// notification handled by this device and fall back to Watt if the
		// procedure fails...
		String unitOfMeasure = SI.WATT.toString();
		
		// search the power notification...
		for (DataPointInfo dp : this.datapoint2Notification.keySet())
		{
			if (this.datapoint2Notification.get(dp).contains(
					SinglePhaseActivePowerMeasurementNotification.notificationName))
			{
				unitOfMeasure = dp.getUnitOfMeasure();
				break; // stop the cycle as data has been found
			}
		}
		
		// create all the states
		ActivePowerStateValue pValue = new ActivePowerStateValue();
		pValue.setValue(DecimalMeasure.valueOf("0 " + unitOfMeasure));
		this.currentState.setState(SinglePhaseActivePowerMeasurementState.class.getSimpleName(),
				new SinglePhaseActivePowerMeasurementState(pValue));
		
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.dog.core.library.model.devicecategory.
	 * SinglePhaseActivePowerMeter#updateStatus()
	 */
	@Override
	public void updateStatus()
	{
		((SinglePhaseActivePowerMeter) this.device).updateStatus();
	}
	
}
