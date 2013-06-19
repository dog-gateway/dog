/**
 * 
 */
package it.polito.elite.domotics.dog2.modbussinglephaseactivepowermeterdriver;

import it.polito.elite.domotics.model.DeviceStatus;
import it.polito.elite.domotics.dog2.doglibrary.devicecategory.ControllableDevice;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;
import it.polito.elite.domotics.dog2.modbusnetworkdriver.ModbusDriver;
import it.polito.elite.domotics.dog2.modbusnetworkdriver.info.CmdNotificationInfo;
import it.polito.elite.domotics.dog2.modbusnetworkdriver.info.ModbusRegisterInfo;
import it.polito.elite.domotics.dog2.modbusnetworkdriver.interfaces.ModbusNetwork;
import it.polito.elite.domotics.model.devicecategory.SinglePhaseActivePowerMeter;
import it.polito.elite.domotics.model.notification.SinglePhaseActivePowerMeasurementNotification;
import it.polito.elite.domotics.model.state.SinglePhaseActivePowerMeasurementState;
import it.polito.elite.domotics.model.state.State;
import it.polito.elite.domotics.model.statevalue.ActivePowerStateValue;

import java.lang.reflect.Method;
import java.util.Set;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.unit.SI;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * @author bonino
 * 
 */
public class ModbusSinglePhaseActivePowerMeterDriverInstance extends ModbusDriver implements
		SinglePhaseActivePowerMeter
{
	// the class logger
	private LogService logger;
	
	/**
	 * @param network
	 * @param device
	 * @param gatewayAddress
	 * @param context
	 */
	public ModbusSinglePhaseActivePowerMeterDriverInstance(ModbusNetwork network, ControllableDevice device,
			String gatewayAddress, String gatewayPort, String gatewayProtocol, BundleContext context)
	{
		super(network, device, gatewayAddress, gatewayPort, gatewayProtocol);
		
		// create a logger
		this.logger = new DogLogInstance(context);
		
		// TODO: get the initial state of the device....(states can be updated
		// by reading notification group addresses)
		this.initializeStates();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.domotics.dog2.doglibrary.DogDriver#detachDriver(java.
	 * lang.String)
	 */
	@Override
	public void detachDriver(String deviceID)
	{
		// nothing to do by now... will be handled in the future... may be...
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.domotics.model.devicecategory.SinglePhaseActivePowerMeter
	 * #getActivePower()
	 */
	@Override
	public Measure<?, ?> getActivePower()
	{
		return (Measure<?, ?>) this.currentState.getState(SinglePhaseActivePowerMeasurementState.class.getName())
				.getCurrentStateValue()[0].getValue();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.domotics.model.devicecategory.SinglePhaseActivePowerMeter
	 * #getState()
	 */
	@Override
	public DeviceStatus getState()
	{
		return this.currentState;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.domotics.model.devicecategory.SinglePhaseActivePowerMeter
	 * #notifyNewActivePowerValue(javax.measure.Measure)
	 */
	@Override
	public void notifyNewActivePowerValue(Measure<?, ?> powerValue)
	{
		// update the state
		ActivePowerStateValue pValue = new ActivePowerStateValue();
		pValue.setValue(powerValue);
		this.currentState.setState(SinglePhaseActivePowerMeasurementState.class.getName(),
				new SinglePhaseActivePowerMeasurementState(pValue));
		
		// notify the new measure
		((SinglePhaseActivePowerMeter) this.device).notifyNewActivePowerValue(powerValue);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.domotics.model.devicecategory.SinglePhaseActivePowerMeter
	 * #notifyStateChanged(it.polito.elite.domotics.model.state.State)
	 */
	@Override
	public void notifyStateChanged(State newState)
	{
		((SinglePhaseActivePowerMeter) this.device).notifyStateChanged(newState);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.domotics.dog2.modbusnetworkdriver.ModbusDriver#
	 * newMessageFromHouse
	 * (it.polito.elite.domotics.dog2.modbusnetworkdriver.info
	 * .ModbusRegisterInfo, java.lang.String)
	 */
	@Override
	public void newMessageFromHouse(ModbusRegisterInfo register, String value)
	{
		if (value != null)
		{
			// gets the corresponding notification set...
			Set<CmdNotificationInfo> notificationInfos = this.register2Notification.get(register);
			
			// handle the notifications
			for (CmdNotificationInfo notificationInfo : notificationInfos)
			{
				// black magic here...
				String notificationName = notificationInfo.getName();
				
				// get the hypothetical class method name
				String notifyMethod = "notify" + Character.toUpperCase(notificationName.charAt(0))
						+ notificationName.substring(1);
				
				// search the method and execute it
				try
				{
					// log notification
					this.logger.log(LogService.LOG_DEBUG, ModbusSinglePhaseActivePowerMeterDriver.logId + "Device: "
							+ this.device.getDeviceId() + " is notifying " + notificationName + " value:"
							+ register.getXlator().getValue());
					// get the method
					
					Method notify = ModbusSinglePhaseActivePowerMeterDriverInstance.class.getDeclaredMethod(
							notifyMethod, Measure.class);
					// invoke the method
					notify.invoke(this, DecimalMeasure.valueOf(register.getXlator().getValue()));
					
				}
				catch (Exception e)
				{
					// log the error
					this.logger.log(LogService.LOG_WARNING, ModbusSinglePhaseActivePowerMeterDriver.logId
							+ "Unable to find a suitable notification method for the datapoint: " + register + ":\n"
							+ e);
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.domotics.dog2.modbusnetworkdriver.ModbusDriver#
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
	 * @see it.polito.elite.domotics.dog2.modbusnetworkdriver.ModbusDriver#
	 * addToNetworkDriver
	 * (it.polito.elite.domotics.dog2.modbusnetworkdriver.info.
	 * ModbusRegisterInfo)
	 */
	@Override
	protected void addToNetworkDriver(ModbusRegisterInfo register)
	{
		this.network.addDriver(register, this);
	}
	
	private void initializeStates()
	{
		// The power unit
		String activePowerUOM = SI.WATT.toString();
		
		// search the energy unit of measures declared in the device
		// configuration
		for (ModbusRegisterInfo register : this.register2Notification.keySet())
		{
			Set<CmdNotificationInfo> notificationInfos = this.register2Notification.get(register);
			
			for (CmdNotificationInfo notificationInfo : notificationInfos)
			{
				
				if (notificationInfo.getName().equalsIgnoreCase(
						SinglePhaseActivePowerMeasurementNotification.notificationName))
				{
					activePowerUOM = register.getXlator().getUnitOfMeasure();
				}
			}
		}
		
		// create all the states
		ActivePowerStateValue pValue = new ActivePowerStateValue();
		pValue.setValue(DecimalMeasure.valueOf("0 " + activePowerUOM));
		this.currentState.setState(SinglePhaseActivePowerMeasurementState.class.getName(),
				new SinglePhaseActivePowerMeasurementState(pValue));
		
		// read the initial state
		this.network.readAll(this.register2Notification.keySet());
		
	}
	
}
