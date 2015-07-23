/*
 * Dog - EnOcean RockerSwitch Driver
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
package org.doggateway.drivers.enocean.rockerswitch;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.devicecategory.RockerSwitch;

import org.doggateway.drivers.enocean.device.EnOceanDeviceDriver;
import org.doggateway.drivers.enocean.network.EnOceanDriverInstance;
import org.doggateway.drivers.enocean.network.interfaces.EnOceanNetwork;
import org.osgi.framework.BundleContext;

/**
 * <p>
 * This class implements the RockerSwitch driver for the EnOcean network. It
 * takes care of matching and attaching devices of type {@link RockerSwitch} and
 * of delegating their management to suitable driver instances (
 * {@link RockerSwitchDriverInstance}).
 * </p>
 * 
 * @author <a href="mailto:dario.bonino@gmail.com">Dario Bonino</a>
 *
 */
public class RockerSwitchDriver extends EnOceanDeviceDriver
{

	/**
	 * Class constructor, defines the EEP managed by this driver and the
	 * corresponding DogOnt class. Furthermore, it inizializes the inner data
	 * structures.
	 */
	public RockerSwitchDriver()
	{
		// call the superclass constructor
		super();

		// set the driver instance class
		this.driverInstanceClass = RockerSwitchDriverInstance.class;

		// set the main device class
		this.deviceMainClass = RockerSwitch.class.getSimpleName();

		// add the supported eeps
		this.supportedEEPs.add("f6-02-01");
		this.supportedEEPs.add("f6-02-02");
	}

	@Override
	public EnOceanDriverInstance createEnOceanDriverInstance(
			EnOceanNetwork enOceanNetwork, ControllableDevice device,
			int updateTimeMillis,
			BundleContext context)
	{
		//create a new driver instance for handling an attaching device
		return new RockerSwitchDriverInstance(enOceanNetwork, device, updateTimeMillis, context);
	}

}
