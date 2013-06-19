/**
 * 
 */
package modbus.temperaturesensor.driver;

import it.polito.elite.domotics.model.DeviceStatus;
import it.polito.elite.domotics.dog2.doglibrary.devicecategory.ControllableDevice;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;
import it.polito.elite.domotics.dog2.modbusnetworkdriver.ModbusDriver;
import it.polito.elite.domotics.dog2.modbusnetworkdriver.info.CmdNotificationInfo;
import it.polito.elite.domotics.dog2.modbusnetworkdriver.info.ModbusRegisterInfo;
import it.polito.elite.domotics.dog2.modbusnetworkdriver.interfaces.ModbusNetwork;
import it.polito.elite.domotics.model.devicecategory.SingleTemperatureSensor;
import it.polito.elite.domotics.model.notification.TemperatureMeasurementNotification;
import it.polito.elite.domotics.model.state.State;
import it.polito.elite.domotics.model.state.TemperatureState;
import it.polito.elite.domotics.model.statevalue.TemperatureStateValue;

import java.lang.reflect.Method;
import java.util.Set;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.unit.SI;
import javax.measure.unit.UnitFormat;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * @author bonino
 * 
 */
public class ModbusSingleTemperatureSensorDriverInstance extends ModbusDriver implements SingleTemperatureSensor
{
	// the class logger
	private LogService logger;
	
	public ModbusSingleTemperatureSensorDriverInstance(ModbusNetwork network, ControllableDevice device,
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
	 * it.polito.elite.domotics.model.devicecategory.SingleTemperatureSensor
	 * #getTemperature()
	 */
	@Override
	public Measure<?, ?> getTemperature()
	{
		return (Measure<?, ?>) this.currentState.getState(TemperatureState.class.getName()).getCurrentStateValue()[0]
				.getValue();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.domotics.model.devicecategory.SingleTemperatureSensor
	 * #deleteGroup(java.lang.String)
	 */
	@Override
	public void deleteGroup(String groupID)
	{
		// nothing to do by now... will be handled in the future... may be...
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.domotics.model.devicecategory.SingleTemperatureSensor
	 * #storeGroup(java.lang.String)
	 */
	@Override
	public void storeGroup(String groupID)
	{
		// nothing to do by now... will be handled in the future... may be...
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.domotics.model.devicecategory.SingleTemperatureSensor
	 * #notifyNewTemperatureValue(javax.measure.Measure)
	 */
	@Override
	public void notifyNewTemperatureValue(Measure<?, ?> temperatureValue)
	{
		// update the state
		TemperatureStateValue tValue = new TemperatureStateValue();
		tValue.setValue(temperatureValue);
		this.currentState.setState(TemperatureState.class.getName(), new TemperatureState(tValue));
		
		// notify the new measure
		((SingleTemperatureSensor) this.device).notifyNewTemperatureValue(temperatureValue);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.domotics.model.devicecategory.TemperatureSensor#getState
	 * ()
	 */
	@Override
	public DeviceStatus getState()
	{
		return this.currentState;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.domotics.model.devicecategory.TemperatureSensor#
	 * notifyStateChanged(it.polito.elite.domotics.model.state.State)
	 */
	@Override
	public void notifyStateChanged(State newState)
	{
		// probably unused...
		((SingleTemperatureSensor) this.device).notifyStateChanged(newState);
		
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
					this.logger.log(LogService.LOG_DEBUG, ModbusSingleTemperatureSensorDriver.logId + "Device: "
							+ this.device.getDeviceId() + " is notifying " + notificationName + " value:"
							+ register.getXlator().getValue());
					// get the method
					
					Method notify = ModbusSingleTemperatureSensorDriverInstance.class.getDeclaredMethod(notifyMethod,
							Measure.class);
					// invoke the method
					notify.invoke(this, DecimalMeasure.valueOf(register.getXlator().getValue()));
				}
				catch (Exception e)
				{
					// log the error
					this.logger.log(LogService.LOG_WARNING, ModbusSingleTemperatureSensorDriver.logId
							+ "Unable to find a suitable notification method for the datapoint: " + register + ":\n"
							+ e);
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
		// Since this driver handles the device metering according to a well
		// defined interface, we can get the unit of measure from all the
		// notifications handled by this device except from state notifications
		
		// add unit of measure aliases (to fix notation problems...)
		UnitFormat uf = UnitFormat.getInstance();
		uf.alias(SI.CELSIUS, "C");
		
		String temperatureUOM = SI.CELSIUS.toString();
		
		// search the energy unit of measures declared in the device
		// configuration
		for (ModbusRegisterInfo register : this.register2Notification.keySet())
		{
			Set<CmdNotificationInfo> notificationInfos = this.register2Notification.get(register);
			
			for (CmdNotificationInfo notificationInfo : notificationInfos)
			{
				
				if (notificationInfo.getName().equalsIgnoreCase(TemperatureMeasurementNotification.notificationName))
				{
					temperatureUOM = register.getXlator().getUnitOfMeasure();
				}
			}
		}
		
		// create all the states
		TemperatureStateValue tValue = new TemperatureStateValue();
		tValue.setValue(DecimalMeasure.valueOf("0 " + temperatureUOM));
		this.currentState.setState(TemperatureState.class.getName(), new TemperatureState(tValue));
		
		// read the initial state
		this.network.readAll(this.register2Notification.keySet());
	}
	
}
