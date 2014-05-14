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
package it.polito.elite.dog.drivers.echelon.ilon100.singlephasenergymeter;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.SinglePhaseEnergyMeter;
import it.polito.elite.dog.core.library.model.notification.SinglePhaseActiveEnergyMeasurementNotification;
import it.polito.elite.dog.core.library.model.notification.SinglePhaseReactiveEnergyMeasurementNotification;
import it.polito.elite.dog.core.library.model.state.SinglePhaseActiveEnergyState;
import it.polito.elite.dog.core.library.model.state.SinglePhaseReactiveEnergyState;
import it.polito.elite.dog.core.library.model.statevalue.ActiveEnergyStateValue;
import it.polito.elite.dog.core.library.model.statevalue.ReactiveEnergyStateValue;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.drivers.echelon.ilon100.network.EchelonIlon100DriverInstance;
import it.polito.elite.dog.drivers.echelon.ilon100.network.info.CmdNotificationInfo;
import it.polito.elite.dog.drivers.echelon.ilon100.network.info.DataPointInfo;
import it.polito.elite.dog.drivers.echelon.ilon100.network.interfaces.EchelonIlon100Network;

import java.lang.reflect.Method;
import java.util.Set;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.quantity.Power;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.measure.unit.UnitFormat;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * A class implementing the driver for SinglePhaseEnergyMeters based on the
 * Echelon iLon 100 web-service based network protocol
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class EchelonIlon100SinglePhaseEnergyMeterDriverInstance extends EchelonIlon100DriverInstance implements
		SinglePhaseEnergyMeter
{
	// the class logger
	private LogHelper logger;
	
	/**
	 * The class constructor, initializes all the data structures needed for the
	 * driver to successfully handle SinglePhaseEnergyMeter devices
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
	public EchelonIlon100SinglePhaseEnergyMeterDriverInstance(EchelonIlon100Network network, ControllableDevice device,
			String endpointAddress, BundleContext context)
	{
		super(network, device, endpointAddress);
		
		// create a logger
		this.logger = new LogHelper(context);
		
		// TODO: get the initial state of the device....(states can be updated
		// by reading notification group addresses)
		this.initializeStates();
	}
	
	@Override
	public Measure<?, ?> getReactiveEnergyValue()
	{
		// direct read on the network...
		
		// search the power notification...
		/*
		 * for (DataPointInfo dp : this.datapoint2Notification.keySet()) { if
		 * (this.datapoint2Notification.get(dp).contains(
		 * SinglePhaseReactiveEnergyMeasurementNotification.notificationName)) {
		 * this.network.readDP(dp); return dp.getValueWithUnitOfMeasure(); } }
		 */
		return (Measure<?, ?>) this.currentState.getState(SinglePhaseReactiveEnergyState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}
	
	@Override
	public Measure<?, ?> getActiveEnergyValue()
	{
		// direct read on the network...
		
		// search the power notification...
		/*
		 * for (DataPointInfo dp : this.datapoint2Notification.keySet()) { if
		 * (this.datapoint2Notification.get(dp).contains(
		 * SinglePhaseActiveEnergyMeasurementNotification.notificationName)) {
		 * this.network.readDP(dp); return dp.getValueWithUnitOfMeasure(); } }
		 * return null;
		 */
		return (Measure<?, ?>) this.currentState.getState(SinglePhaseActiveEnergyState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}
	
	@Override
	public DeviceStatus getState()
	{
		return this.currentState;
	}
	
	@Override
	public void notifyNewReactiveEnergyValue(Measure<?, ?> value)
	{
		// update the state
		ReactiveEnergyStateValue rValue = new ReactiveEnergyStateValue();
		rValue.setValue(value);
		this.currentState.setState(SinglePhaseReactiveEnergyState.class.getSimpleName(),
				new SinglePhaseReactiveEnergyState(rValue));
		
		// forward the notification to the framework
		((SinglePhaseEnergyMeter) this.device).notifyNewReactiveEnergyValue(value);
		
		// log
		this.logger.log(LogService.LOG_DEBUG, EchelonIlon100SinglePhaseEnergyMeterDriver.logId
				+ "Dispatched new reactive energy notification value for " + this.device.getDeviceId() + ": " + value);
		
	}
	
	@Override
	public void notifyNewActiveEnergyValue(Measure<?, ?> value)
	{
		// update the state
		ActiveEnergyStateValue aValue = new ActiveEnergyStateValue();
		aValue.setValue(value);
		this.currentState.setState(SinglePhaseActiveEnergyState.class.getSimpleName(),
				new SinglePhaseActiveEnergyState(aValue));
		
		// forward the notification to the framework
		((SinglePhaseEnergyMeter) this.device).notifyNewActiveEnergyValue(value);
		
		// log
		this.logger.log(LogService.LOG_DEBUG, EchelonIlon100SinglePhaseEnergyMeterDriver.logId
				+ "Dispatched new active energy notification value for " + this.device.getDeviceId() + ": " + value);
		
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
					Method notify = EchelonIlon100SinglePhaseEnergyMeterDriverInstance.class.getDeclaredMethod(
							notifyMethod, Measure.class);
					// invoke the method
					notify.invoke(this,
							DecimalMeasure.valueOf(dataPointInfo.getValue() + " " + dataPointInfo.getUnitOfMeasure()));
				}
				catch (Exception e)
				{
					// log the error
					this.logger.log(LogService.LOG_WARNING, EchelonIlon100SinglePhaseEnergyMeterDriver.logId
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
		// defined interface, we can get the unit of measure from all the
		// notifications handled by this device except from state notifications
		// and fall back to WattHour/Var if the
		// procedure fails...
		
		// create the var and va units
		Unit<Power> VAR = SI.WATT.alternate("var");
		
		// add unit of measure aliases (to fix notation problems...)
		UnitFormat uf = UnitFormat.getInstance();
		uf.alias(SI.WATT.times(NonSI.HOUR), "Wh");
		uf.label(SI.KILO(SI.WATT.times(NonSI.HOUR)), "kWh");
		uf.alias(VAR.times(NonSI.HOUR), "Varh");
		uf.label(SI.KILO(VAR.times(NonSI.HOUR)), "kVarh");
		
		String activeEnergyUOM = SI.WATT.times(NonSI.HOUR).toString();
		String reactiveEnergyUOM = VAR.times(NonSI.HOUR).toString();
		
		// search the energy unit of measures declared in the device
		// configuration
		for (DataPointInfo dp : this.datapoint2Notification.keySet())
		{
			if (this.datapoint2Notification.get(dp).contains(
					SinglePhaseActiveEnergyMeasurementNotification.notificationName))
			{
				activeEnergyUOM = dp.getUnitOfMeasure();
			}
			else if (this.datapoint2Notification.get(dp).contains(
					SinglePhaseReactiveEnergyMeasurementNotification.notificationName))
			{
				reactiveEnergyUOM = dp.getUnitOfMeasure();
			}
		}
		
		// create all the states
		ActiveEnergyStateValue aValue = new ActiveEnergyStateValue();
		aValue.setValue(DecimalMeasure.valueOf("0 " + activeEnergyUOM));
		this.currentState.setState(SinglePhaseActiveEnergyState.class.getSimpleName(),
				new SinglePhaseActiveEnergyState(aValue));
		
		ReactiveEnergyStateValue rValue = new ReactiveEnergyStateValue();
		rValue.setValue(DecimalMeasure.valueOf("0 " + reactiveEnergyUOM));
		this.currentState.setState(SinglePhaseReactiveEnergyState.class.getSimpleName(),
				new SinglePhaseReactiveEnergyState(rValue));
		
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
	 * @see
	 * it.polito.elite.dog.core.library.model.devicecategory.SinglePhaseEnergyMeter
	 * #updateStatus()
	 */
	@Override
	public void updateStatus()
	{
		((SinglePhaseEnergyMeter) this.device).updateStatus();
	}
	
}
