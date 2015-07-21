/*
 * Dog - EnOcean Gateway Driver
 * 
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
package org.doggateway.drivers.enocean.gateway;

import it.polito.elite.dog.core.devicefactory.api.DeviceFactory;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import it.polito.elite.dog.core.library.model.DeviceDescriptorFactory;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.EnOceanGateway;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.state.TeachInState;
import it.polito.elite.dog.core.library.model.statevalue.IdleStateValue;
import it.polito.elite.dog.core.library.model.statevalue.TeachingInStateValue;
import it.polito.elite.dog.core.library.model.technology.ExplicitTeachInData;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.enocean.enj.eep.EEPAttribute;
import it.polito.elite.enocean.enj.model.EnOceanDevice;

import java.util.HashMap;
import java.util.Set;

import org.doggateway.drivers.enocean.network.EnOceanDriverInstance;
import org.doggateway.drivers.enocean.network.info.EnOceanDeviceInfo;
import org.doggateway.drivers.enocean.network.info.EnOceanDriverInfo;
import org.doggateway.drivers.enocean.network.interfaces.EnOceanDeviceDiscoveryListener;
import org.doggateway.drivers.enocean.network.interfaces.EnOceanNetwork;
import org.doggateway.drivers.enocean.network.interfaces.EnOceanTeachInActivationListener;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * @author <a href="mailto:dario.bonino@gmail.com">Dario Bonino</a>
 *
 */
public class EnOceanGatewayDriverInstance extends EnOceanDriverInstance
		implements EnOceanGateway, EnOceanDeviceDiscoveryListener,
		EnOceanTeachInActivationListener
{

	// The default teach in timeout
	public static int DEFAULT_TEACH_IN_TIMEOUT = 20000;

	// the driver logger
	private LogHelper logger;

	// the set of currently connected drivers
	private Set<EnOceanDriverInfo> activeDrivers;

	// the device factory reference
	private DeviceFactory deviceFactory;

	// the device descriptor factory reference
	private DeviceDescriptorFactory descriptorFactory;

	public EnOceanGatewayDriverInstance(EnOceanNetwork enOceanNetwork,
			DeviceFactory deviceFactory, Set<EnOceanDriverInfo> activeDrivers,
			ControllableDevice device, BundleContext context)
	{
		// call the superclass constructor
		super(enOceanNetwork, device);

		// store the device factory reference
		this.deviceFactory = deviceFactory;

		// create a logger
		this.logger = new LogHelper(context);

		// store the active drivers
		this.activeDrivers = activeDrivers;

		// create the device descriptor factory
		try
		{
			this.descriptorFactory = new DeviceDescriptorFactory(context
					.getBundle().getEntry("/deviceTemplates"));
		}
		catch (Exception e)
		{

			this.logger.log(LogService.LOG_ERROR,
					"Error while creating DeviceDescriptorFactory ", e);
		}

		// create a new device state (according to the current DogOnt model, no
		// state is actually associated to a Modbus gateway)
		this.currentState = new DeviceStatus(device.getDeviceId());

		// initialize the current state
		this.initializeStates();
	}

	@Override
	public void addedEnOceanDevice(EnOceanDeviceInfo devInfo)
	{
		// log
		this.logger
				.log(LogService.LOG_INFO,
						"Device discovered at the network level, starting the device discovery process");

		// check if the device is already known
		if (!this.isKnownDevice(devInfo))
		{
			// new device
			this.logger.log(LogService.LOG_INFO,
					"Found new device: " + devInfo.getAddress() + " with "
							+ devInfo.getEep() + " EEP");

			// search for the best match with available drivers.
			// currently the case in which multiple drivers match the same EEP
			// is assumed to be "sporadic"
			// and drivers are simply selected on the basis of the first match.
			// This can be improved with better heuristics in subsequent
			// versions of this driver.
			for (EnOceanDriverInfo driverInfo : this.activeDrivers)
			{
				// this performs an "equals" match, it might be worth verifying
				// if it is sufficient
				if (driverInfo.getSupportedEEPs().contains(devInfo.getEep()))
				{
					this.createDevice(devInfo, driverInfo.getMainDeviceClass());

					// break
					break;
				}
			}
		}

	}

	@Override
	public void teachIn()
	{
		// enable teach in for the default timeout
		// TODO: make timeout configurable in the gateway configuration file
		this.network.enableTeachIn(
				EnOceanGatewayDriverInstance.DEFAULT_TEACH_IN_TIMEOUT, false);
	}

	@Override
	public void explicitTeachIn(ExplicitTeachInData teachingData)
	{
		// enable explicit teach-in
		// TODO: make timeout configurable in the gateway configuration file
		this.network.enableExplicitTeachIn(teachingData.getDeviceHexAddress(), teachingData.getDeviceEEP(),
				EnOceanGatewayDriverInstance.DEFAULT_TEACH_IN_TIMEOUT);
	}

	@Override
	public void smartTeachIn()
	{
		// enable teach in for the default timeout
		// TODO: make timeout configurable in the gateway configuration file
		this.network.enableTeachIn(
				EnOceanGatewayDriverInstance.DEFAULT_TEACH_IN_TIMEOUT, true);
	}

	@Override
	public DeviceStatus getState()
	{
		return this.currentState;
	}

	@Override
	public void notifyDeactivatedTeachIn()
	{
		((EnOceanGateway) this.device).notifyDeactivatedTeachIn();
	}

	@Override
	public void notifyActivatedTeachIn()
	{
		((EnOceanGateway) this.device).notifyActivatedTeachIn();
	}

	@Override
	public void updateStatus()
	{
		((EnOceanGateway) this.device).updateStatus();
	}

	@Override
	protected void specificConfiguration()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void addToNetworkDriver(EnOceanDeviceInfo device)
	{
		// do nothing as there is no real device corresponding to the gateway

		// register the gateway driver as a device discovery listener
		this.network.addDeviceDiscoveryListener(this);
		this.network.addTeachInActivationListener(this);
	}

	@Override
	protected void bindDevice(EnOceanDevice device)
	{
		// do nothing as there is no real device corresponding to the gateway

	}

	@Override
	protected void unBindDevice(EnOceanDevice device)
	{
		// do nothing as there is no real device corresponding to the gateway

	}

	@Override
	public void handleAttributeChange(int channelId, EEPAttribute<?> attribute)
	{
		// do nothing as there is no real device corresponding to the gateway
	}

	@Override
	public void teachInEnabled()
	{
		// update the status and notify
		boolean changed = this.changeState(new TeachInState(
				new TeachingInStateValue()));

		if (changed)
			// notify
			this.notifyActivatedTeachIn();
	}

	@Override
	public void teachInDisabled()
	{

		// update the status and notify
		boolean changed = this.changeState(new TeachInState(
				new IdleStateValue()));

		if (changed)
			// notify
			this.notifyDeactivatedTeachIn();
	}

	private boolean changeState(State newState)
	{
		// the state changed flag
		boolean stateChanged = false;

		// get the current state
		String currentStateValue = "";
		State state = currentState.getState(TeachInState.class.getSimpleName());

		if (state != null)
			currentStateValue = (String) state.getCurrentStateValue()[0]
					.getValue();

		// check that the state has changed
		if (!currentStateValue.equals(newState.getCurrentStateValue()[0]
				.getValue()))
		{
			// update the current state
			this.currentState.setState(TeachInState.class.getSimpleName(),
					newState);

			// debug
			logger.log(
					LogService.LOG_DEBUG,
					"Device " + device.getDeviceId() + " is now "
							+ (newState).getCurrentStateValue()[0].getValue());

			// update the status
			this.updateStatus();

			// updated the state changed flag
			stateChanged = true;
		}

		return stateChanged;
	}

	/**
	 * Initializes the state asynchronously as required by OSGi
	 */
	private void initializeStates()
	{
		// initialize the state
		this.currentState.setState(TeachInState.class.getSimpleName(),
				new TeachInState(new IdleStateValue()));
	}

	/**
	 * Checks if the device represented by the given {@link EnOceanDeviceInfo}
	 * instance is already registered in the framework or not.
	 * 
	 * @param devInfo
	 *            The device to check
	 * @return true if the device is already known in the framework, false
	 *         otherwise.
	 */
	private boolean isKnownDevice(EnOceanDeviceInfo devInfo)
	{
		// TODO: should check if no device exists having the same properties
		return false;
	}

	/**
	 * Builds a device descriptor representing the device modeled by the given
	 * {@link EnOceanDeviceInfo} instance.
	 * 
	 * @param mainDeviceClass
	 *            The Dog device class discovered for the device.
	 * @param devInfo
	 *            The {@link EnOceanDeviceInfo} instance representing the device
	 * @return
	 */
	private DeviceDescriptor buildDeviceDescriptor(String deviceClass,
			EnOceanDeviceInfo devInfo)
	{
		// the device descriptor to return
		DeviceDescriptor descriptor = null;

		if (this.descriptorFactory != null)
		{

			// normal workflow...
			if ((deviceClass != null) && (!deviceClass.isEmpty()))
			{
				// create a descriptor definition map
				HashMap<String, Object> descriptorDefinitionData = new HashMap<String, Object>();

				// store the device name
				descriptorDefinitionData.put(DeviceDescriptorFactory.NAME,
						deviceClass + "_" + devInfo.getUid());

				// store the device description
				descriptorDefinitionData.put(
						DeviceDescriptorFactory.DESCRIPTION,
						"New Device of type " + deviceClass);

				// store the device gateway
				descriptorDefinitionData.put(DeviceDescriptorFactory.GATEWAY,
						this.device.getDeviceId());

				// store the device location
				descriptorDefinitionData.put(DeviceDescriptorFactory.LOCATION,
						"");

				// store the device address
				descriptorDefinitionData.put("address", devInfo.getAddress());

				// store the device uid
				descriptorDefinitionData.put("uid", devInfo.getUid());

				// store the device eep
				descriptorDefinitionData.put("eep", devInfo.getEep());

				// get the device descriptor
				try
				{
					descriptor = this.descriptorFactory.getDescriptor(
							descriptorDefinitionData, deviceClass);
				}
				catch (Exception e)
				{
					this.logger
							.log(LogService.LOG_ERROR,
									"Error while creating DeviceDescriptor for the just added device ",
									e);
				}

				// debug dump
				this.logger.log(
						LogService.LOG_INFO,
						"Detected new device: \n\tdeviceUniqueId: "
								+ devInfo.getUid() + "\n\tdeviceClass: "
								+ deviceClass);
			}
		}

		// return
		return descriptor;

	}

	/**
	 * Creates a new Dog device given the EnOceanDevice info representing the
	 * device and the main Dog device class to be used for modeling such a
	 * device.
	 * 
	 * @param devInfo The device information.
	 * @param mainClass The class to use for representing the device inside Dog.
	 */
	private void createDevice(EnOceanDeviceInfo devInfo, String mainClass)
	{
		// match found, build the device descriptor
		DeviceDescriptor descriptorToAdd = this.buildDeviceDescriptor(
				mainClass, devInfo);

		// check not null
		if (descriptorToAdd != null)
		{
			// create the device
			// cross the finger
			this.deviceFactory.addNewDevice(descriptorToAdd);

			// log the new appliance installation
			this.logger.log(LogService.LOG_INFO,
					"New appliance successfully identified...");
		}
	}
}
