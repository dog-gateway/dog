/*
 * Dog - EnOcean Temperature Sensor Driver
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
package org.doggateway.drivers.enocean.doorsensor;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.devicecategory.DoorSensor;

import org.doggateway.drivers.enocean.device.EnOceanDeviceDriver;
import org.doggateway.drivers.enocean.network.EnOceanDriverInstance;
import org.doggateway.drivers.enocean.network.interfaces.EnOceanNetwork;
import org.osgi.framework.BundleContext;

/**
 * <p>
 * This class implements the Door Sensor driver for the EnOcean network. It
 * takes care of matching and attaching devices with EEP in the D50001 Family
 * and to map them to {@link DoorSensor} instances.
 * </p>
 * 
 * @author <a href="mailto:dario.bonino@gmail.com">Dario Bonino</a>
 *
 */
public class EnOceanDoorSensorDriver extends EnOceanDeviceDriver
{

	/**
	 * 
	 */
	public EnOceanDoorSensorDriver()
	{
		// call the superclass constructor
		super();

		// set the driver instance class
		this.driverInstanceClass = EnOceanDoorSensorDriverInstance.class;

		// set the main device class
		this.deviceMainClass = DoorSensor.class.getSimpleName();

		// add the supported eeps
		this.supportedEEPs.add("d5-00-01");
	}

	@Override
	public EnOceanDriverInstance createEnOceanDriverInstance(
			EnOceanNetwork enOceanNetwork, ControllableDevice device,
			int updateTimeMillis, BundleContext context)
	{
		// TODO Auto-generated method stub
		return new EnOceanDoorSensorDriverInstance(enOceanNetwork, device,
				updateTimeMillis, context);
	}

}
