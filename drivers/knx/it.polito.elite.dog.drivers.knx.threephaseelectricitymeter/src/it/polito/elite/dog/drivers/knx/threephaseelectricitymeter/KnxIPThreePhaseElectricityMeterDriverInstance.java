/*
 * Dog - Device Driver
 * 
 * Copyright (c) 2011-2013 Dario Bonino and Luigi De Russis
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
package it.polito.elite.dog.drivers.knx.threephaseelectricitymeter;

import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.ThreePhaseElectricityMeter;
import it.polito.elite.dog.core.library.model.notification.FrequencyMeasurementNotification;
import it.polito.elite.dog.core.library.model.notification.PowerFactorMeasurementNotification;
import it.polito.elite.dog.core.library.model.notification.SinglePhaseActiveEnergyMeasurementNotification;
import it.polito.elite.dog.core.library.model.notification.SinglePhaseActivePowerMeasurementNotification;
import it.polito.elite.dog.core.library.model.notification.SinglePhaseReactiveEnergyMeasurementNotification;
import it.polito.elite.dog.core.library.model.notification.StateChangeNotification;
import it.polito.elite.dog.core.library.model.notification.ThreePhaseActivePowerMeasurementNotification;
import it.polito.elite.dog.core.library.model.notification.ThreePhaseApparentPowerMeasurementNotification;
import it.polito.elite.dog.core.library.model.notification.ThreePhaseCurrentMeasurementNotification;
import it.polito.elite.dog.core.library.model.notification.ThreePhaseLLVoltageMeasurementNotification;
import it.polito.elite.dog.core.library.model.notification.ThreePhaseLNVoltageMeasurementNotification;
import it.polito.elite.dog.core.library.model.notification.ThreePhaseReactivePowerMeasurementNotification;
import it.polito.elite.dog.core.library.model.state.FrequencyMeasurementState;
import it.polito.elite.dog.core.library.model.state.PowerFactorMeasurementState;
import it.polito.elite.dog.core.library.model.state.SinglePhaseActiveEnergyState;
import it.polito.elite.dog.core.library.model.state.SinglePhaseReactiveEnergyState;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.state.ThreePhaseActivePowerMeasurementState;
import it.polito.elite.dog.core.library.model.state.ThreePhaseApparentPowerMeasurementState;
import it.polito.elite.dog.core.library.model.state.ThreePhaseCurrentState;
import it.polito.elite.dog.core.library.model.state.ThreePhaseReactivePowerMeasurementState;
import it.polito.elite.dog.core.library.model.state.ThreePhaseVoltageState;
import it.polito.elite.dog.core.library.model.statevalue.ActiveEnergyStateValue;
import it.polito.elite.dog.core.library.model.statevalue.ActivePowerStateValue;
import it.polito.elite.dog.core.library.model.statevalue.ApparentPowerStateValue;
import it.polito.elite.dog.core.library.model.statevalue.CurrentStateValue;
import it.polito.elite.dog.core.library.model.statevalue.FrequencyStateValue;
import it.polito.elite.dog.core.library.model.statevalue.PowerFactorStateValue;
import it.polito.elite.dog.core.library.model.statevalue.ReactiveEnergyStateValue;
import it.polito.elite.dog.core.library.model.statevalue.ReactivePowerStateValue;
import it.polito.elite.dog.core.library.model.statevalue.StateValue;
import it.polito.elite.dog.core.library.model.statevalue.VoltageStateValue;
import it.polito.elite.dog.drivers.knx.network.KnxIPDriverInstance;
import it.polito.elite.dog.drivers.knx.network.info.KnxIPDeviceInfo;
import it.polito.elite.dog.drivers.knx.network.interfaces.KnxIPNetwork;

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
import javax.measure.unit.UnitFormat;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

import tuwien.auto.calimero.dptxlator.DPT;
import tuwien.auto.calimero.dptxlator.DPTXlator4ByteFloat;
import tuwien.auto.calimero.dptxlator.DPTXlator4ByteInteger;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 *         TODO: Add support to total power (active, reactive, apparent...),
 *         must come from DogOnt2Dog
 * 
 */
public class KnxIPThreePhaseElectricityMeterDriverInstance extends KnxIPDriverInstance implements ThreePhaseElectricityMeter
{
	// the notification name to DPT map
	private Map<String, DPT> notification2DPT;
	
	// the driver logger
	LogHelper logger;
	
	// the log identifier, unique for the class
	public static String logId = "[KnxIPThreePhaseElectricityMeterDriverInstance]: ";
	
	/**
	 * The class constructor, initializes all the needed data structures.
	 * 
	 * @param network
	 *            The network driver to use for communicating with the KNX
	 *            network.
	 * @param device
	 *            The device to manage.
	 * @param gatewayAddress
	 */
	public KnxIPThreePhaseElectricityMeterDriverInstance(KnxIPNetwork network, ControllableDevice device,
			String gatewayAddress, BundleContext context)
	{
		// call the super constructor...
		super(network, device, gatewayAddress);
		
		// create a logger
		this.logger = new LogHelper(context);		
	}
	
	/**
	 * initializes the map for getting the right DPT given a notification name
	 */
	private void initializeNotification2DPT()
	{
		this.notification2DPT.put(FrequencyMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteFloat.DPT_FREQUENCY);
		this.notification2DPT.put(ThreePhaseReactivePowerMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteFloat.DPT_REACTIVE_POWER);
		this.notification2DPT.put(SinglePhaseReactiveEnergyMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteInteger.DPT_REACTIVE_ENERGY);
		this.notification2DPT.put(SinglePhaseActiveEnergyMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteInteger.DPT_ACTIVE_ENERGY);
		this.notification2DPT.put(ThreePhaseLNVoltageMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteFloat.DPT_ELECTRIC_POTENTIAL_DIFFERENCE);
		this.notification2DPT.put(ThreePhaseLLVoltageMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteFloat.DPT_ELECTRIC_POTENTIAL_DIFFERENCE);
		this.notification2DPT.put(ThreePhaseApparentPowerMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteFloat.DPT_APPARENT_POWER);
		this.notification2DPT.put(PowerFactorMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteFloat.DPT_POWER_FACTOR);
		this.notification2DPT.put(ThreePhaseActivePowerMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteFloat.DPT_POWER);
		this.notification2DPT.put(SinglePhaseActivePowerMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteFloat.DPT_POWER);
		this.notification2DPT.put(ThreePhaseCurrentMeasurementNotification.class.getSimpleName(),
				DPTXlator4ByteFloat.DPT_ELECTRIC_CURRENT);
		
	}
	
	@Override
	public Measure<?, ?> getReactiveEnergyValue()
	{
		return (Measure<?, ?>) this.currentState.getState(SinglePhaseReactiveEnergyState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}
	
	@Override
	public Measure<?, ?> getReactivePower(String phaseID)
	{
		return this.getThreePhaseStateValue(ThreePhaseReactivePowerMeasurementState.class.getSimpleName(), phaseID);
	}
	
	@Override
	public Measure<?, ?> getFrequency()
	{
		return (Measure<?, ?>) this.currentState.getState(FrequencyMeasurementState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}
	
	@Override
	public Measure<?, ?> getPowerFactor()
	{
		return (Measure<?, ?>) this.currentState.getState(PowerFactorMeasurementState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}
	
	@Override
	public Measure<?, ?> getActiveEnergyValue()
	{
		return (Measure<?, ?>) this.currentState.getState(SinglePhaseActiveEnergyState.class.getSimpleName())
				.getCurrentStateValue()[0].getValue();
	}
	
	@Override
	public Measure<?, ?> getLNVoltageValue(String phaseID)
	{
		return this.getThreePhaseStateValue(ThreePhaseVoltageState.class.getSimpleName(), phaseID);
	}
	
	@Override
	public Measure<?, ?> getLLVoltageValue(String phaseID1, String phaseID2)
	{
		// TODO: fix this....
		String phaseID = phaseID1 + phaseID2.substring(1);
		
		return this.getThreePhaseStateValue(ThreePhaseVoltageState.class.getSimpleName() + "LL", phaseID);
	}
	
	@Override
	public Measure<?, ?> getElectricCurrentValue(String phaseID)
	{
		return this.getThreePhaseStateValue(ThreePhaseCurrentState.class.getSimpleName(), phaseID);
	}
	
	@Override
	public Measure<?, ?> getApparentPower(String phaseID)
	{
		return this.getThreePhaseStateValue(ThreePhaseApparentPowerMeasurementState.class.getSimpleName(), phaseID);
	}
	
	@Override
	public Measure<?, ?> getActivePower(String phaseID)
	{
		return this.getThreePhaseStateValue(ThreePhaseActivePowerMeasurementState.class.getSimpleName(), phaseID);
	}
	
	@Override
	public synchronized DeviceStatus getState()
	{
		return this.currentState;
	}
	
	@Override
	public void notifyNewFrequencyValue(Measure<?, ?> frequency)
	{
		// update the state
		((FrequencyMeasurementState) this.currentState.getState(FrequencyMeasurementState.class.getSimpleName()))
				.getCurrentStateValue()[0].setValue(frequency);
		
		// notify the new measure
		((ThreePhaseElectricityMeter) this.device).notifyNewFrequencyValue(frequency);
		
	}
	
	@Override
	public void notifyStateChanged(State newState)
	{
		// probably unused...
		((ThreePhaseElectricityMeter) this.device).notifyStateChanged(newState);
		
	}
	
	@Override
	public void notifyNewReactivePowerValue(String phaseID, Measure<?, ?> value)
	{
		// update the state....
		this.updateThreePhaseStateValue(ThreePhaseReactivePowerMeasurementState.class.getSimpleName(), phaseID, value);
		
		((ThreePhaseElectricityMeter) this.device).notifyNewReactivePowerValue(phaseID, value);
		
	}
	
	@Override
	public void notifyNewReactiveEnergyValue(Measure<?, ?> value)
	{
		// update the state
		this.currentState.getState(SinglePhaseReactiveEnergyState.class.getSimpleName()).getCurrentStateValue()[0]
				.setValue(value);
		
		// notify the new measure
		((ThreePhaseElectricityMeter) this.device).notifyNewReactiveEnergyValue(value);
		
	}
	
	@Override
	public void notifyNewActiveEnergyValue(Measure<?, ?> value)
	{
		// update the state
		((SinglePhaseActiveEnergyState) this.currentState.getState(SinglePhaseActiveEnergyState.class.getSimpleName()))
				.getCurrentStateValue()[0].setValue(value);
		
		// notify the new measure
		((ThreePhaseElectricityMeter) this.device).notifyNewActiveEnergyValue(value);
	}
	
	@Override
	public void notifyNewPhaseNeutralVoltageValue(String phaseID, Measure<?, ?> value)
	{
		// update the state....
		this.updateThreePhaseStateValue(ThreePhaseVoltageState.class.getSimpleName(), phaseID, value);
		
		((ThreePhaseElectricityMeter) this.device).notifyNewPhaseNeutralVoltageValue(phaseID, value);
		
	}
	
	@Override
	public void notifyNewPhasePhaseVoltageValue(String phaseID, Measure<?, ?> value)
	{
		
		this.updateThreePhaseStateValue(ThreePhaseVoltageState.class.getSimpleName() + "LL", phaseID, value);
		
		((ThreePhaseElectricityMeter) this.device).notifyNewPhasePhaseVoltageValue(phaseID, value);
		
	}
	
	@Override
	public void notifyNewApparentPowerValue(String phaseID, Measure<?, ?> value)
	{
		// update the state....
		
		this.updateThreePhaseStateValue(ThreePhaseApparentPowerMeasurementState.class.getSimpleName(), phaseID, value);
		
		((ThreePhaseElectricityMeter) this.device).notifyNewApparentPowerValue(phaseID, value);
		
	}
	
	@Override
	public void notifyNewPowerFactorValue(Measure<?, ?> powerFactor)
	{
		// update the state
		this.currentState.getState(PowerFactorMeasurementState.class.getSimpleName()).getCurrentStateValue()[0]
				.setValue(powerFactor);
		
		// notify the new measure
		((ThreePhaseElectricityMeter) this.device).notifyNewPowerFactorValue(powerFactor);
		
	}
	
	@Override
	public void notifyNewActivePowerValue(String phaseID, Measure<?, ?> value)
	{
		// update the state....
		this.updateThreePhaseStateValue(ThreePhaseActivePowerMeasurementState.class.getSimpleName(), phaseID, value);
		
		((ThreePhaseElectricityMeter) this.device).notifyNewActivePowerValue(phaseID, value);
	}
	
	@Override
	public void notifyNewCurrentValue(String phaseID, Measure<?, ?> value)
	{
		// update the state....
		this.updateThreePhaseStateValue(ThreePhaseCurrentState.class.getSimpleName(), phaseID, value);
		
		((ThreePhaseElectricityMeter) this.device).notifyNewCurrentValue(phaseID, value);
		
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
			else if (notificationName.equals(ThreePhaseReactivePowerMeasurementNotification.class.getSimpleName()))
			{
				this.notifyNewReactivePowerValue(cInfo.getParameters().get("phaseID"), DecimalMeasure.valueOf(value));
			}
			else if (notificationName.equals(ThreePhaseLNVoltageMeasurementNotification.class.getSimpleName()))
			{
				this.notifyNewPhaseNeutralVoltageValue(cInfo.getParameters().get("phaseID"),
						DecimalMeasure.valueOf(value));
			}
			else if (notificationName.equals(ThreePhaseLLVoltageMeasurementNotification.class.getSimpleName()))
			{
				this.notifyNewPhasePhaseVoltageValue(cInfo.getParameters().get("phaseID"),
						DecimalMeasure.valueOf(value));
			}
			else if (notificationName.equals(ThreePhaseApparentPowerMeasurementNotification.class.getSimpleName()))
			{
				this.notifyNewApparentPowerValue(cInfo.getParameters().get("phaseID"), DecimalMeasure.valueOf(value));
			}
			else if (notificationName.equals(ThreePhaseActivePowerMeasurementNotification.class.getSimpleName()))
			{
				this.notifyNewActivePowerValue(cInfo.getParameters().get("phaseID"), DecimalMeasure.valueOf(value));
			}
			else if (notificationName.equals(ThreePhaseCurrentMeasurementNotification.class.getSimpleName()))
			{
				this.notifyNewCurrentValue(cInfo.getParameters().get("phaseID"), DecimalMeasure.valueOf(value));
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
							this.logger.log(
									LogService.LOG_ERROR,
									KnxIPThreePhaseElectricityMeterDriverInstance.logId
											+ "Found Incompatible DPTs for the same group address "
											+ deviceInfo.getGroupAddress() + "ignoring the corresponding notification");
							cDpt = null;
							break;
						}
					}
				}
			}
			
			try
			{
				KnxIPDeviceInfo devInfo = new KnxIPDeviceInfo(this.device.getDeviceId(), deviceInfo.getGroupAddress());
				devInfo.setGatewayIPAddress(InetAddress.getByName(this.gwAddress));
				this.network.addDriver(devInfo, 14, cDpt, this);
			}
			catch (Exception e)
			{
				// TODO handle exception here
			}
		}
	}
	
	@Override
	protected void specificConfiguration()
	{
		// prepare the device state map
		this.currentState = new DeviceStatus(device.getDeviceId());
		
		// TODO: get the initial state of the device....(states can be updated
		// by reading notification group addresses)
		this.initializeStates();
		
		// initialize the notification to DPXlator conversion map
		this.notification2DPT = new HashMap<String, DPT>();
		this.initializeNotification2DPT();
	}
	
	private void initializeStates()
	{
		// Since this driver handles the device metering according to a well
		// defined interface, we can get the unit of measure from all the
		// notifications handled by this device except from state notifications
		// and fall back to WattHour/VarHour if the
		// procedure fails...
		
		// create the var and va units
		Unit<Power> VAR = SI.WATT.alternate("var");
		VAR.alternate("Var");
		Unit<Power> VA = SI.WATT.alternate("VA");
		
		// add unit of measure aliases (to fix notation problems...)
		UnitFormat uf = UnitFormat.getInstance();
		uf.alias(SI.WATT.times(NonSI.HOUR), "Wh");
		uf.label(SI.KILO(SI.WATT.times(NonSI.HOUR)), "kWh");
		uf.alias(VAR.times(NonSI.HOUR), "Varh");
		uf.label(SI.KILO(VAR.times(NonSI.HOUR)), "kVarh");
		uf.label(SI.KILO(VA), "kVA");
		
		String activeEnergyUOM = SI.WATT.times(NonSI.HOUR).toString();
		String reactiveEnergyUOM = VAR.times(NonSI.HOUR).toString();
		String activePowerUOM = SI.WATT.toString();
		String reactivePowerUOM = VAR.toString();
		String frequencyUOM = SI.HERTZ.toString();
		String currentUOM = SI.AMPERE.toString();
		String apparentPowerUOM = VA.toString();
		String voltageUOM = SI.VOLT.toString();
		
		// create all the states
		
		// ------------ Three Phase Active Power
		// --------------------------------
		ActivePowerStateValue activePowerStateL1 = new ActivePowerStateValue();
		activePowerStateL1.setFeature("phaseID", "L1");
		activePowerStateL1.setValue(DecimalMeasure.valueOf("0 " + activePowerUOM));
		
		ActivePowerStateValue activePowerStateL2 = new ActivePowerStateValue();
		activePowerStateL2.setFeature("phaseID", "L2");
		activePowerStateL2.setValue(DecimalMeasure.valueOf("0 " + activePowerUOM));
		
		ActivePowerStateValue activePowerStateL3 = new ActivePowerStateValue();
		activePowerStateL3.setFeature("phaseID", "L3");
		activePowerStateL3.setValue(DecimalMeasure.valueOf("0 " + activePowerUOM));
		
		this.currentState.setState(ThreePhaseActivePowerMeasurementState.class.getSimpleName(),
				new ThreePhaseActivePowerMeasurementState(activePowerStateL1, activePowerStateL2, activePowerStateL3));
		// --------------------------------------------------------------------------
		
		// ------------- Frequency -----------------------------------------
		FrequencyStateValue frequencyStateValue = new FrequencyStateValue();
		frequencyStateValue.setValue(DecimalMeasure.valueOf("0 " + frequencyUOM));
		this.currentState.setState(FrequencyMeasurementState.class.getSimpleName(), new FrequencyMeasurementState(
				frequencyStateValue));
		// -----------------------------------------------------------------
		
		// -------------- SinglePhaseActivePower ---------------------------
		ActiveEnergyStateValue activeEnergyStateValue = new ActiveEnergyStateValue();
		activeEnergyStateValue.setValue(DecimalMeasure.valueOf("0 " + activeEnergyUOM));
		this.currentState.setState(SinglePhaseActiveEnergyState.class.getSimpleName(),
				new SinglePhaseActiveEnergyState(activeEnergyStateValue));
		// ------------------------------------------------------------------
		
		// -------------- ThreePhaseCurrent ---------------------------------
		CurrentStateValue currentStateL1 = new CurrentStateValue();
		currentStateL1.setFeature("phaseID", "L1");
		currentStateL1.setValue(DecimalMeasure.valueOf("0 " + currentUOM));
		
		CurrentStateValue currentStateL2 = new CurrentStateValue();
		currentStateL2.setFeature("phaseID", "L2");
		currentStateL2.setValue(DecimalMeasure.valueOf("0 " + currentUOM));
		
		CurrentStateValue currentStateL3 = new CurrentStateValue();
		currentStateL3.setFeature("phaseID", "L3");
		currentStateL3.setValue(DecimalMeasure.valueOf("0 " + currentUOM));
		
		this.currentState.setState(ThreePhaseCurrentState.class.getSimpleName(), new ThreePhaseCurrentState(
				currentStateL1, currentStateL2, currentStateL3));
		// -------------------------------------------------------------------
		
		// ------------- ThreePhaseReactivePower -----------------------------
		ReactivePowerStateValue reactivePowerStateL1 = new ReactivePowerStateValue();
		reactivePowerStateL1.setFeature("phaseID", "L1");
		reactivePowerStateL1.setValue(DecimalMeasure.valueOf("0 " + reactivePowerUOM));
		
		ReactivePowerStateValue reactivePowerStateL2 = new ReactivePowerStateValue();
		reactivePowerStateL2.setFeature("phaseID", "L2");
		reactivePowerStateL2.setValue(DecimalMeasure.valueOf("0 " + reactivePowerUOM));
		
		ReactivePowerStateValue reactivePowerStateL3 = new ReactivePowerStateValue();
		reactivePowerStateL3.setFeature("phaseID", "L3");
		reactivePowerStateL3.setValue(DecimalMeasure.valueOf("0 " + reactivePowerUOM));
		
		this.currentState.setState(ThreePhaseReactivePowerMeasurementState.class.getSimpleName(),
				new ThreePhaseReactivePowerMeasurementState(reactivePowerStateL1, reactivePowerStateL2,
						reactivePowerStateL3));
		// --------------------------------------------------------------------
		
		// ---------------------- Power Factor --------------------------------
		PowerFactorStateValue powerFactorStateValue = new PowerFactorStateValue();
		powerFactorStateValue.setValue(DecimalMeasure.valueOf("0 " + Unit.ONE));
		
		this.currentState.setState(PowerFactorMeasurementState.class.getSimpleName(),
				new PowerFactorMeasurementState(powerFactorStateValue));
		// --------------------------------------------------------------------
		
		// ---------------------- ThreePhaseApparentPower ---------------------
		ApparentPowerStateValue apparentPowerStateL1 = new ApparentPowerStateValue();
		apparentPowerStateL1.setFeature("phaseID", "L1");
		apparentPowerStateL1.setValue(DecimalMeasure.valueOf("0 " + apparentPowerUOM));
		
		ApparentPowerStateValue apparentPowerStateL2 = new ApparentPowerStateValue();
		apparentPowerStateL2.setFeature("phaseID", "L2");
		apparentPowerStateL2.setValue(DecimalMeasure.valueOf("0 " + apparentPowerUOM));
		
		ApparentPowerStateValue apparentPowerStateL3 = new ApparentPowerStateValue();
		apparentPowerStateL3.setFeature("phaseID", "L3");
		apparentPowerStateL3.setValue(DecimalMeasure.valueOf("0 " + apparentPowerUOM));
		
		this.currentState.setState(ThreePhaseApparentPowerMeasurementState.class.getSimpleName(),
				new ThreePhaseApparentPowerMeasurementState(apparentPowerStateL1, apparentPowerStateL2,
						apparentPowerStateL3));
		// ----------------------------------------------------------------------
		
		// ------------------------ Single Phase Reactive Energy
		ReactiveEnergyStateValue reactiveEnergyStateValue = new ReactiveEnergyStateValue();
		reactiveEnergyStateValue.setValue(DecimalMeasure.valueOf("0 " + reactiveEnergyUOM));
		this.currentState.setState(SinglePhaseReactiveEnergyState.class.getSimpleName(),
				new SinglePhaseReactiveEnergyState(reactiveEnergyStateValue));
		// -----------------------------------------------------------------------
		
		// ------------------------ ThreePhaseLNVoltage
		VoltageStateValue voltageStateL1N = new VoltageStateValue();
		voltageStateL1N.setFeature("phaseID", "L1");
		voltageStateL1N.setValue(DecimalMeasure.valueOf("0 " + voltageUOM));
		
		VoltageStateValue voltageStateL2N = new VoltageStateValue();
		voltageStateL2N.setFeature("phaseID", "L2");
		voltageStateL2N.setValue(DecimalMeasure.valueOf("0 " + voltageUOM));
		
		VoltageStateValue voltageStateL3N = new VoltageStateValue();
		voltageStateL3N.setFeature("phaseID", "L3");
		voltageStateL3N.setValue(DecimalMeasure.valueOf("0 " + voltageUOM));
		
		this.currentState.setState(ThreePhaseVoltageState.class.getSimpleName(), new ThreePhaseVoltageState(
				voltageStateL1N, voltageStateL2N, voltageStateL3N));
		// ----------------------------------------------------------
		
		// ------------------------ ThreePhaseLLVoltage
		VoltageStateValue voltageStateL12 = new VoltageStateValue();
		voltageStateL12.setFeature("phaseID", "L12");
		voltageStateL12.setValue(DecimalMeasure.valueOf("0 " + voltageUOM));
		
		VoltageStateValue voltageStateL23 = new VoltageStateValue();
		voltageStateL23.setFeature("phaseID", "L23");
		voltageStateL23.setValue(DecimalMeasure.valueOf("0 " + voltageUOM));
		
		VoltageStateValue voltageStateL31 = new VoltageStateValue();
		voltageStateL31.setFeature("phaseID", "L31");
		voltageStateL31.setValue(DecimalMeasure.valueOf("0 " + voltageUOM));
		
		this.currentState.setState(ThreePhaseVoltageState.class.getSimpleName() + "LL", new ThreePhaseVoltageState(
				voltageStateL12, voltageStateL23, voltageStateL31));
		
		// read the initial state
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
			catch (Exception e)
			{
				// TODO handle exception here
			}
		}
		
	}
	
	private Measure<?, ?> getThreePhaseStateValue(String stateClass, String phaseID)
	{
		// the measure
		Measure<?, ?> value = null;
		
		// get the current state value
		StateValue currentStateValue[] = ((State) this.currentState.getState(stateClass)).getCurrentStateValue();
		
		// check which state value matches the given phase ID
		for (int i = 0; i < currentStateValue.length; i++)
		{
			// find the matching state value
			HashMap<String, Object> features = currentStateValue[i].getFeatures();
			
			// if matches, extract the power value and break the cycle
			if ((features.containsKey("phaseID")) && (((String) features.get("phaseID")).equalsIgnoreCase(phaseID)))
			{
				// extract the power value
				value = (Measure<?, ?>) currentStateValue[i].getValue();
				
				// break
				i = currentStateValue.length;
			}
		}
		return value;
	}
	
	private void updateThreePhaseStateValue(String stateClass, String phaseID, Measure<?, ?> value)
	{
		// update the state....
		StateValue currentStateValue[] = ((State) this.currentState.getState(stateClass)).getCurrentStateValue();
		for (int i = 0; i < currentStateValue.length; i++)
		{
			// find the matching state value
			HashMap<String, Object> features = currentStateValue[i].getFeatures();
			
			if ((features.containsKey("phaseID")) && (((String) features.get("phaseID")).equalsIgnoreCase(phaseID)))
			{
				// set the new value
				currentStateValue[i].setValue(DecimalMeasure.valueOf(value.toString()));
			}
		}
	}
	
}
