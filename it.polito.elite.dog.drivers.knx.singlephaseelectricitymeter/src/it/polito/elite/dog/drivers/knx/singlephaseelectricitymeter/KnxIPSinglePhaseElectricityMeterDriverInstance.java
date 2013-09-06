/*
 * Dog 2.0 - Device Driver
 * 
 * Copyright [2012]
 * [Luigi De Russis (luigi.derussis@polito.it), Politecnico di Torino]  
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package it.polito.elite.dog.drivers.knx.singlephaseelectricitymeter;

import it.polito.elite.dog.drivers.knx.network.KnxIPDriver;
import it.polito.elite.dog.drivers.knx.network.info.KnxIPDeviceInfo;
import it.polito.elite.dog.drivers.knx.network.interfaces.KnxIPNetwork;
import it.polito.elite.domotics.model.DeviceStatus;
import it.polito.elite.domotics.dog2.doglibrary.devicecategory.ControllableDevice;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;
import it.polito.elite.domotics.model.devicecategory.SinglePhaseElectricityMeter;
import it.polito.elite.domotics.model.notification.FrequencyMeasurementNotification;
import it.polito.elite.domotics.model.notification.PowerFactorMeasurementNotification;
import it.polito.elite.domotics.model.notification.SinglePhaseActiveEnergyMeasurementNotification;
import it.polito.elite.domotics.model.notification.SinglePhaseActivePowerMeasurementNotification;
import it.polito.elite.domotics.model.notification.SinglePhaseApparentPowerMeasurementNotification;
import it.polito.elite.domotics.model.notification.SinglePhaseCurrentMeasurementNotification;
import it.polito.elite.domotics.model.notification.SinglePhaseReactiveEnergyMeasurementNotification;
import it.polito.elite.domotics.model.notification.SinglePhaseReactivePowerMeasurementNotification;
import it.polito.elite.domotics.model.notification.SinglePhaseVoltageMeasurementNotification;
import it.polito.elite.domotics.model.notification.StateChangeNotification;
import it.polito.elite.domotics.model.state.FrequencyMeasurementState;
import it.polito.elite.domotics.model.state.PowerFactorMeasurementState;
import it.polito.elite.domotics.model.state.SinglePhaseActiveEnergyState;
import it.polito.elite.domotics.model.state.SinglePhaseActivePowerMeasurementState;
import it.polito.elite.domotics.model.state.SinglePhaseApparentPowerMeasurementState;
import it.polito.elite.domotics.model.state.SinglePhaseCurrentState;
import it.polito.elite.domotics.model.state.SinglePhaseReactiveEnergyState;
import it.polito.elite.domotics.model.state.SinglePhaseReactivePowerMeasurementState;
import it.polito.elite.domotics.model.state.SinglePhaseVoltageState;
import it.polito.elite.domotics.model.state.State;
import it.polito.elite.domotics.model.state.VoltageMeasurementState;
import it.polito.elite.domotics.model.statevalue.ActiveEnergyStateValue;
import it.polito.elite.domotics.model.statevalue.ActivePowerStateValue;
import it.polito.elite.domotics.model.statevalue.ApparentPowerStateValue;
import it.polito.elite.domotics.model.statevalue.CurrentStateValue;
import it.polito.elite.domotics.model.statevalue.FrequencyStateValue;
import it.polito.elite.domotics.model.statevalue.PowerFactorStateValue;
import it.polito.elite.domotics.model.statevalue.ReactiveEnergyStateValue;
import it.polito.elite.domotics.model.statevalue.ReactivePowerStateValue;
import it.polito.elite.domotics.model.statevalue.VoltageStateValue;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.quantity.Power;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

import tuwien.auto.calimero.dptxlator.DPT;
import tuwien.auto.calimero.dptxlator.DPTXlator4ByteFloat;
import tuwien.auto.calimero.dptxlator.DPTXlator4ByteInteger;

/**
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * 
 */
public class KnxIPSinglePhaseElectricityMeterDriverInstance extends KnxIPDriver implements SinglePhaseElectricityMeter
{
	// the device state
	private DeviceStatus innerState;
	
	// the notification name to DPT map
	private Map<String, DPT> notification2DPT;
	
	// the driver logger
	LogService logger;
	
	// the log identifier, unique for the class
	public static String logId = "[KnxIPSinglePhaseElectricityMeterDriverInstance]: ";
	
	/**
	 * The class constructor, initializes all the needed data structures.
	 * 
	 * @param network
	 *            The network driver to use for communicating with the KNX
	 *            network.
	 * @param device
	 *            The device to manage.
	 * @param gwAddress
	 */
	public KnxIPSinglePhaseElectricityMeterDriverInstance(KnxIPNetwork network, ControllableDevice device,
			String gatewayAddress, BundleContext context)
	{
		// call the super constructor...
		super(network, device, gatewayAddress);
		
		// create a logger
		this.logger = new DogLogInstance(context);
		
		// connect this driver instance with the device
		this.device.setDriver(this);
	}
	
	/**
	 * Initializes the map for getting the right DPT given a notification name
	 */
	private void initializeNotification2DPT()
	{
		this.notification2DPT.put(FrequencyMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteFloat.DPT_FREQUENCY);
		this.notification2DPT.put(SinglePhaseReactivePowerMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteFloat.DPT_REACTIVE_POWER);
		this.notification2DPT.put(SinglePhaseReactiveEnergyMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteInteger.DPT_REACTIVE_ENERGY);
		this.notification2DPT.put(SinglePhaseActiveEnergyMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteInteger.DPT_ACTIVE_ENERGY);
		this.notification2DPT.put(SinglePhaseVoltageMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteFloat.DPT_ELECTRIC_POTENTIAL_DIFFERENCE);
		this.notification2DPT.put(SinglePhaseApparentPowerMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteFloat.DPT_APPARENT_POWER);
		this.notification2DPT.put(PowerFactorMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteFloat.DPT_POWER_FACTOR);
		this.notification2DPT.put(SinglePhaseActivePowerMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteFloat.DPT_POWER);
		this.notification2DPT.put(SinglePhaseActivePowerMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteFloat.DPT_POWER);
		this.notification2DPT.put(SinglePhaseCurrentMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteFloat.DPT_ELECTRIC_CURRENT);
	}
	
	/**
	 * Initializes the device states and performs an initial read to update
	 * them, if available
	 */
	// TODO Check if it is correct!
	private void initializeStates()
	{
		// create the var and va units
		Unit<Power> VAR = SI.WATT.alternate("var");
		Unit<Power> VA = SI.WATT.alternate("VA");
		
		// create all the states...
		// active power state
		ActivePowerStateValue initialActivePowerValue = new ActivePowerStateValue();
		initialActivePowerValue.setValue(DecimalMeasure.valueOf(0, SI.WATT));
		this.innerState.setState(SinglePhaseActivePowerMeasurementState.class.getSimpleName(),
				new SinglePhaseActivePowerMeasurementState(initialActivePowerValue));
		
		// frequency state
		FrequencyStateValue initialFrequenceValue = new FrequencyStateValue();
		initialFrequenceValue.setValue(DecimalMeasure.valueOf(0, SI.HERTZ));
		this.innerState.setState(FrequencyMeasurementState.class.getSimpleName(), new FrequencyMeasurementState(
				initialFrequenceValue));
		
		// active energy state
		ActiveEnergyStateValue initialActiveEnergyValue = new ActiveEnergyStateValue();
		initialActiveEnergyValue.setValue(DecimalMeasure.valueOf(0, SI.WATT.times(NonSI.HOUR)));
		this.innerState.setState(SinglePhaseActiveEnergyState.class.getSimpleName(), new SinglePhaseActiveEnergyState(
				initialActiveEnergyValue));
		
		// current state
		CurrentStateValue initialCurrentValue = new CurrentStateValue();
		initialCurrentValue.setValue(DecimalMeasure.valueOf(0, SI.AMPERE));
		this.innerState.setState(SinglePhaseCurrentState.class.getSimpleName(), new SinglePhaseCurrentState(
				initialCurrentValue));
		
		// reactive power state
		ReactivePowerStateValue initialReactivePowerValue = new ReactivePowerStateValue();
		initialReactivePowerValue.setValue(DecimalMeasure.valueOf(0, VAR));
		this.innerState.setState(SinglePhaseReactivePowerMeasurementState.class.getSimpleName(),
				new SinglePhaseReactivePowerMeasurementState(initialReactivePowerValue));
		
		// power factor state
		PowerFactorStateValue initialPowerFactorValue = new PowerFactorStateValue();
		initialPowerFactorValue.setValue(DecimalMeasure.valueOf("0 " + Unit.ONE));
		this.innerState.setState(PowerFactorMeasurementState.class.getCanonicalName(), new PowerFactorMeasurementState(
				initialPowerFactorValue));
		
		// apparent power state
		ApparentPowerStateValue initialApparentPowerValue = new ApparentPowerStateValue();
		initialApparentPowerValue.setValue(DecimalMeasure.valueOf("0 " + VA));
		this.innerState.setState(SinglePhaseApparentPowerMeasurementState.class.getSimpleName(),
				new SinglePhaseApparentPowerMeasurementState(initialApparentPowerValue));
		
		// reactive energy state
		ReactiveEnergyStateValue initialReactiveEnergyValue = new ReactiveEnergyStateValue();
		initialReactiveEnergyValue.setValue(DecimalMeasure.valueOf(0, VAR.times(NonSI.HOUR)));
		this.innerState.setState(SinglePhaseReactiveEnergyState.class.getSimpleName(), new SinglePhaseReactiveEnergyState(
				initialReactiveEnergyValue));
		
		// voltage state
		VoltageStateValue initialVoltageValue = new VoltageStateValue();
		initialVoltageValue.setValue(DecimalMeasure.valueOf("0 " + SI.VOLT));
		this.innerState.setState(SinglePhaseVoltageState.class.getSimpleName(), new SinglePhaseVoltageState(
				initialVoltageValue));
		
		// iterate over all the notifications
		for (String groupAddress : this.groupAddress2NotificationMap.keySet())
		{
			// read states (asynchronously)
			try
			{
			KnxIPDeviceInfo devInfo = new KnxIPDeviceInfo(this.device.getDeviceId(), groupAddress);
			devInfo.setGatewayIPAddress(InetAddress.getByName(this.gwAddress));
			this.network.read(devInfo);
			}
			catch(Exception e)
			{
				//TODO handle exception here
			}
		}
	}
	
	@Override
	public Measure<?, ?> getReactiveEnergyValue()
	{
		// TODO Check if it works... and then extend to multiple state values
		return (Measure<?, ?>) this.innerState.getState(SinglePhaseReactiveEnergyState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}
	
	@Override
	public Measure<?, ?> getReactivePower()
	{
		// TODO Check if it works... and then extend to multiple state values
		return (Measure<?, ?>) this.innerState.getState(SinglePhaseReactivePowerMeasurementState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}
	
	@Override
	public Measure<?, ?> getFrequency()
	{
		// TODO Check if it works... and then extend to multiple state values
		return (Measure<?, ?>) this.innerState.getState(FrequencyMeasurementState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}
	
	@Override
	public Measure<?, ?> getPowerFactor()
	{
		// TODO Check if it works... and then extend to multiple state values
		return (Measure<?, ?>) this.innerState.getState(PowerFactorMeasurementState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}
	
	@Override
	public Measure<?, ?> getActiveEnergyValue()
	{
		// TODO Check if it works... and then extend to multiple state values
		return (Measure<?, ?>) this.innerState.getState(SinglePhaseActiveEnergyState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}
	
	@Override
	public Measure<?, ?> getPhaseNeutralVoltageValue()
	{
		// TODO Check if it works... and then extend to multiple state values
		return (Measure<?, ?>) this.innerState.getState(VoltageMeasurementState.class.getSimpleName()).getCurrentStateValue()[0]
				.getValue();
	}
	
	@Override
	public Measure<?, ?> getElectricCurrentValue()
	{
		// TODO Check if it works... and then extend to multiple state values
		return (Measure<?, ?>) this.innerState.getState(SinglePhaseCurrentState.class.getSimpleName()).getCurrentStateValue()[0]
				.getValue();
	}
	
	@Override
	public Measure<?, ?> getApparentPower()
	{
		// TODO Check if it works... and then extend to multiple state values
		return (Measure<?, ?>) this.innerState.getState(SinglePhaseApparentPowerMeasurementState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}
	
	@Override
	public Measure<?, ?> getActivePower()
	{
		// TODO Check if it works... and then extend to multiple state values
		return (Measure<?, ?>) this.innerState.getState(SinglePhaseActivePowerMeasurementState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}
	
	@Override
	public DeviceStatus getState()
	{
		return this.innerState;
	}
	
	@Override
	public void notifyNewFrequencyValue(Measure<?, ?> frequency)
	{
		// update the state
		FrequencyStateValue newFrequency = new FrequencyStateValue();
		newFrequency.setValue(frequency);
		this.innerState
				.setState(FrequencyMeasurementState.class.getSimpleName(), new FrequencyMeasurementState(newFrequency));
		
		// notify the new measure
		((SinglePhaseElectricityMeter) this.device).notifyNewFrequencyValue(frequency);
	}
	
	@Override
	public void notifyNewActivePowerValue(Measure<?, ?> powerValue)
	{
		// update the state
		ActivePowerStateValue newActivePowerValue = new ActivePowerStateValue();
		newActivePowerValue.setValue(powerValue);
		this.innerState.setState(SinglePhaseActivePowerMeasurementState.class.getSimpleName(),
				new SinglePhaseActivePowerMeasurementState(newActivePowerValue));
		
		// notify the new measure
		((SinglePhaseElectricityMeter) this.device).notifyNewActivePowerValue(powerValue);
	}
	
	@Override
	public void notifyNewReactivePowerValue(Measure<?, ?> powerValue)
	{
		// update the state
		ReactivePowerStateValue newReactivePowerValue = new ReactivePowerStateValue();
		newReactivePowerValue.setValue(powerValue);
		this.innerState.setState(SinglePhaseReactivePowerMeasurementState.class.getSimpleName(),
				new SinglePhaseReactivePowerMeasurementState(newReactivePowerValue));
		
		// notify the new measure
		((SinglePhaseElectricityMeter) this.device).notifyNewReactivePowerValue(powerValue);
	}
	
	@Override
	public void notifyStateChanged(State newState)
	{
		// probably unused...
		((SinglePhaseElectricityMeter) this.device).notifyStateChanged(newState);
	}
	
	@Override
	public void notifyNewApparentPowerValue(Measure<?, ?> powerValue)
	{
		// update the state
		ApparentPowerStateValue newApparentPowerValue = new ApparentPowerStateValue();
		newApparentPowerValue.setValue(powerValue);
		this.innerState.setState(SinglePhaseApparentPowerMeasurementState.class.getSimpleName(),
				new SinglePhaseApparentPowerMeasurementState(newApparentPowerValue));
		
		// notify the new measure
		((SinglePhaseElectricityMeter) this.device).notifyNewApparentPowerValue(powerValue);
	}
	
	@Override
	public void notifyNewReactiveEnergyValue(Measure<?, ?> value)
	{
		// update the state
		ReactiveEnergyStateValue newReactiveEnergyValue = new ReactiveEnergyStateValue();
		newReactiveEnergyValue.setValue(value);
		this.innerState.setState(SinglePhaseReactiveEnergyState.class.getSimpleName(), new SinglePhaseReactiveEnergyState(
				newReactiveEnergyValue));
		
		// notify the new measure
		((SinglePhaseElectricityMeter) this.device).notifyNewReactiveEnergyValue(value);
	}
	
	@Override
	public void notifyNewActiveEnergyValue(Measure<?, ?> value)
	{
		// update the state
		ActiveEnergyStateValue newActiveEnergyValue = new ActiveEnergyStateValue();
		newActiveEnergyValue.setValue(value);
		this.innerState.setState(SinglePhaseActiveEnergyState.class.getSimpleName(), new SinglePhaseActiveEnergyState(
				newActiveEnergyValue));
		
		// notify the new measure
		((SinglePhaseElectricityMeter) this.device).notifyNewActiveEnergyValue(value);
	}
	
	@Override
	public void notifyNewPowerFactorValue(Measure<?, ?> powerFactor)
	{
		// update the state
		PowerFactorStateValue newPowerFactorValue = new PowerFactorStateValue();
		newPowerFactorValue.setValue(powerFactor);
		this.innerState.setState(PowerFactorMeasurementState.class.getSimpleName(), new PowerFactorMeasurementState(
				newPowerFactorValue));
		
		// notify the new measure
		((SinglePhaseElectricityMeter) this.device).notifyNewPowerFactorValue(powerFactor);
	}
	
	@Override
	public void notifyNewPhaseNeutralVoltageValue(Measure<?, ?> lnVoltage)
	{
		// update the state
		VoltageStateValue newVoltageValue = new VoltageStateValue();
		newVoltageValue.setValue(lnVoltage);
		this.innerState.setState(VoltageMeasurementState.class.getSimpleName(), new VoltageMeasurementState(newVoltageValue));
		
		// notify the new measure
		((SinglePhaseElectricityMeter) this.device).notifyNewPhaseNeutralVoltageValue(lnVoltage);
	}
	
	@Override
	public void notifyNewCurrentValue(Measure<?, ?> value)
	{
		// update the state
		CurrentStateValue newCurrentValue = new CurrentStateValue();
		newCurrentValue.setValue(value);
		this.innerState.setState(SinglePhaseCurrentState.class.getSimpleName(), new SinglePhaseCurrentState(newCurrentValue));
		
		// notify the new measure
		((SinglePhaseElectricityMeter) this.device).notifyNewCurrentValue(value);
	}
	
	@Override
	public void newMessageFromHouse(String source, String destination, String value)
	{
		// re-map back to notifications and state updates...
		Set<KnxIPDeviceInfo> allCompatibleNotifications = this.groupAddress2NotificationMap.get(destination);
		
		// on the basis of the notification call the right notify method
		for (KnxIPDeviceInfo cInfo : allCompatibleNotifications)
		{
			// get the notification name...
			String notificationName = cInfo.getName();
			
			// call the right notify method depending on the notification name
			// TODO find a better solution because this is HORRIBLE!!!!
			if (notificationName.equals(FrequencyMeasurementNotification.class.getSimpleName()))
			{
				this.notifyNewFrequencyValue(DecimalMeasure.valueOf(value));
			}
			else if (notificationName.equals(SinglePhaseReactivePowerMeasurementNotification.class.getSimpleName()))
			{
				this.notifyNewReactivePowerValue(DecimalMeasure.valueOf(value));
			}
			else if (notificationName.equals(SinglePhaseVoltageMeasurementNotification.class.getSimpleName()))
			{
				this.notifyNewPhaseNeutralVoltageValue(DecimalMeasure.valueOf(value));
			}
			else if (notificationName.equals(SinglePhaseApparentPowerMeasurementNotification.class.getSimpleName()))
			{
				this.notifyNewApparentPowerValue(DecimalMeasure.valueOf(value));
			}
			else if (notificationName.equals(SinglePhaseActivePowerMeasurementNotification.class.getSimpleName()))
			{
				this.notifyNewActivePowerValue(DecimalMeasure.valueOf(value));
			}
			else if (notificationName.equals(SinglePhaseCurrentMeasurementNotification.class.getSimpleName()))
			{
				this.notifyNewCurrentValue(DecimalMeasure.valueOf(value));
			}
			else if (notificationName.equals(SinglePhaseReactiveEnergyMeasurementNotification.class.getSimpleName()))
			{
				this.notifyNewReactiveEnergyValue(DecimalMeasure.valueOf(value));
			}
			else if (notificationName.equals(SinglePhaseActiveEnergyMeasurementNotification.class.getSimpleName()))
			{
				this.notifyNewActiveEnergyValue(DecimalMeasure.valueOf(value));
			}
			else if (notificationName.equals(PowerFactorMeasurementNotification.class.getSimpleName()))
			{
				this.notifyNewPowerFactorValue(DecimalMeasure.valueOf(value));
			}
		}
	}
	
	@Override
	protected void addToNetworkDriver(KnxIPDeviceInfo deviceInfo)
	{
		// get the notification set associated to the given group address
		Set<KnxIPDeviceInfo> notificationsSet = this.groupAddress2NotificationMap.get(deviceInfo.getGroupAddress());
		
		// prepare the DPT variable
		DPT cDpt = null;
		
		// if the set of device notifications is not null
		if (notificationsSet != null)
		{
			// try to detect the right DPT...
			for (KnxIPDeviceInfo cInfo : notificationsSet)
			{
				// ignore state change notifications...
				if (!cInfo.getName().equals(StateChangeNotification.class.getSimpleName()))
				{
					if (cDpt == null)
					{
						cDpt = this.notification2DPT.get(cInfo.getName());
					}
					else
					{
						if (!this.notification2DPT.get(cInfo.getName()).getUnit().equals(cDpt.getUnit()))
						{
							// do nothing and log the error...
							this.logger.log(DogLogInstance.LOG_ERROR,
									KnxIPSinglePhaseElectricityMeterDriverInstance.logId
											+ "Found Incompatible DPTs for the same group address " + deviceInfo.getGroupAddress()
											+ " ignoring the corresponding notification");
							cDpt = null;
							break;
						}
					}
				}
			}
			
			// add the dpt if not null
			if (cDpt != null)
			{
				try
				{
				KnxIPDeviceInfo devInfo = new KnxIPDeviceInfo(this.device.getDeviceId(), deviceInfo.getGroupAddress());
				devInfo.setGatewayIPAddress(InetAddress.getByName(this.gwAddress));
				this.network.addDriver(devInfo, 14, cDpt, this); // TODO Is 14
																	// correct?
				}
				catch(Exception e)
				{
					//TODO handle exception here
				}
			}
		}
	}
	
	@Override
	protected void specificConfiguration()
	{
		// prepare the device state map
		this.innerState = new DeviceStatus(device.getDeviceId());
		
		// TODO: get the initial state of the device....(states can be updated
		// by reading notification group addresses)
		this.initializeStates();
		
		// initialize the notification to DPXlator conversion map
		this.notification2DPT = new HashMap<String, DPT>();
		this.initializeNotification2DPT();
	}
	
}
