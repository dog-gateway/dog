/**
 * 
 */
package org.doggateway.drivers.enocean.rockerswitch;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.Controllable;
import it.polito.elite.dog.core.library.model.devicecategory.RockerSwitch;
import it.polito.elite.dog.core.library.model.state.MultipleOnOffState;
import it.polito.elite.dog.core.library.model.statevalue.DiscreteValue;
import it.polito.elite.dog.core.library.model.statevalue.OffStateValue;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.enocean.enj.eep.EEPAttribute;
import it.polito.elite.enocean.enj.eep.eep26.attributes.EEP26RockerSwitch2RockerAction;
import it.polito.elite.enocean.enj.model.EnOceanDevice;

import org.doggateway.drivers.enocean.network.EnOceanDriverInstance;
import org.doggateway.drivers.enocean.network.info.EnOceanDeviceInfo;
import org.doggateway.drivers.enocean.network.interfaces.EnOceanNetwork;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * @author bonino
 *
 */
public class RockerSwitchDriverInstance extends EnOceanDriverInstance implements
		RockerSwitch
{
	// the class logger
	private LogHelper logger;

	public RockerSwitchDriverInstance(EnOceanNetwork enOceanNetwork,
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

	@Override
	public DeviceStatus getState()
	{
		// provides back the current state of the device
		return this.currentState;
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
					EEP26RockerSwitch2RockerAction.NAME, this);
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
					EEP26RockerSwitch2RockerAction.NAME, this);

			// null the low level device
			this.theLowLevelDevice = null;
		}
	}

	@Override
	public void handleAttributeChange(int channelId, EEPAttribute<?> attribute)
	{
		// handle the attribute change
		if (attribute instanceof EEP26RockerSwitch2RockerAction)
		{
			EEP26RockerSwitch2RockerAction action = (EEP26RockerSwitch2RockerAction) attribute;

			// get the button states
			boolean a1 = action
					.getButtonValue(EEP26RockerSwitch2RockerAction.AI);
			boolean a0 = action
					.getButtonValue(EEP26RockerSwitch2RockerAction.AO);
			boolean b1 = action
					.getButtonValue(EEP26RockerSwitch2RockerAction.BI);
			boolean b0 = action
					.getButtonValue(EEP26RockerSwitch2RockerAction.BO);

			// log the states
			this.logger.log(LogService.LOG_INFO, "A0: " + a0 + " A1: " + a1
					+ " B0: " + b0 + " B1: " + b1);

			// update the status and notify only if different
			this.updateAndNotify(new boolean[] { a0, a1, b0, b1 });
		}

	}

	@Override
	public void notifyPressed(String buttonID)
	{
		((RockerSwitch) this.device).notifyPressed(buttonID);
	}

	@Override
	public void notifyReleased(String buttonID)
	{
		((RockerSwitch) this.device).notifyReleased(buttonID);
	}

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
		// TODO: check if the resulting API and operation model is consistent or
		// not
		DiscreteValue value[] = new DiscreteValue[4];

		for (int i = 0; i < 4; i++)
		{
			// handle multiple structurally equal states

			// build the default off state value
			OffStateValue offValue = new OffStateValue();

			// set the button id parameter
			offValue.setFeature("buttonID", i);

			value[i] = offValue;
		}

		// build the i-th button state
		MultipleOnOffState offState = new MultipleOnOffState(value);

		// store the state
		this.currentState.setState(MultipleOnOffState.class.getSimpleName(),
				offState);
	}

	/**
	 * Checks the current state against the received state vector, if
	 * differences are present, generate needed notifications.
	 * 
	 * @param bs
	 *            The current state of buttons, as an array of boolean values
	 */
	private void updateAndNotify(boolean[] buttonStates)
	{
		// status change flag
		boolean stateChanged = false;

		// get the corresponding state object
		MultipleOnOffState state = (MultipleOnOffState) this.currentState
				.getState(MultipleOnOffState.class.getSimpleName());

		// iterate over the array elements
		for (int i = 0; i < buttonStates.length; i++)
		{

			// check if the state has changed
			if (((state.getCurrentStateValue()[i].getValue() == MultipleOnOffState.OFF) && (buttonStates[i]))
					|| ((state.getCurrentStateValue()[i].getValue() == MultipleOnOffState.ON) && (!buttonStates[i])))
			{
				// state changed, update the state and notify
				if (buttonStates[i])
				{
					// update the i-th state
					state.getCurrentStateValue()[i]
							.setValue(MultipleOnOffState.ON);

					// notify
					this.notifyPressed("" + (i));
				}
				else
				{
					// update the i-th state
					state.getCurrentStateValue()[i]
							.setValue(MultipleOnOffState.OFF);

					// notify
					this.notifyReleased("" + (i));
				}

				// set the changed flag
				stateChanged = true;
			}

		}

		if (stateChanged)
			this.updateStatus();
	}

}
