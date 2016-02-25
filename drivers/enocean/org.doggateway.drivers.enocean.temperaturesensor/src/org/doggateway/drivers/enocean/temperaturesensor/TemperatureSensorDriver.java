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
package org.doggateway.drivers.enocean.temperaturesensor;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.devicecategory.SingleTemperatureSensor;

import org.doggateway.drivers.enocean.device.EnOceanDeviceDriver;
import org.doggateway.drivers.enocean.network.EnOceanDriverInstance;
import org.doggateway.drivers.enocean.network.interfaces.EnOceanNetwork;
import org.osgi.framework.BundleContext;

/**
 * <p>
 * This class implements the Single Temperature Sensor driver for the EnOcean
 * network. It takes care of matching and attaching devices with EEP in the
 * A502XX Family and to map them to {@link SingleTemperatureSensor} instances.
 * </p>
 * 
 * @author <a href="mailto:dario.bonino@gmail.com">Dario Bonino</a>
 *
 */
public class TemperatureSensorDriver extends EnOceanDeviceDriver
{

	/**
	 * Class constructor with no arguments to respect the bean instantiation pattern
	 */
	public TemperatureSensorDriver()
	{
		// call the superclass constructor
		super();

		// set the driver instance class
		this.driverInstanceClass = TemperatureSensorDriverInstance.class;

		// set the main device class
		this.deviceMainClass = SingleTemperatureSensor.class.getSimpleName();

		// add the supported eeps
		this.supportedEEPs.add("a5-02-01");
		this.supportedEEPs.add("a5-02-02");
		this.supportedEEPs.add("a5-02-03");
		this.supportedEEPs.add("a5-02-04");
		this.supportedEEPs.add("a5-02-05");
		this.supportedEEPs.add("a5-02-06");
		this.supportedEEPs.add("a5-02-07");
		this.supportedEEPs.add("a5-02-08");
		this.supportedEEPs.add("a5-02-09");
		this.supportedEEPs.add("a5-02-0A");
		this.supportedEEPs.add("a5-02-0B");
		this.supportedEEPs.add("a5-02-10");
		this.supportedEEPs.add("a5-02-11");
		this.supportedEEPs.add("a5-02-12");
		this.supportedEEPs.add("a5-02-13");
		this.supportedEEPs.add("a5-02-14");
		this.supportedEEPs.add("a5-02-15");
		this.supportedEEPs.add("a5-02-16");
		this.supportedEEPs.add("a5-02-17");
		this.supportedEEPs.add("a5-02-18");
		this.supportedEEPs.add("a5-02-19");
		this.supportedEEPs.add("a5-02-1A");
		this.supportedEEPs.add("a5-02-1B");
		this.supportedEEPs.add("a5-02-20");
		this.supportedEEPs.add("a5-02-30");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.doggateway.drivers.enocean.device.EnOceanDeviceDriver#
	 * createEnOceanDriverInstance
	 * (org.doggateway.drivers.enocean.network.interfaces.EnOceanNetwork,
	 * it.polito.elite.dog.core.library.model.ControllableDevice, int,
	 * org.osgi.framework.BundleContext)
	 */
	@Override
	public EnOceanDriverInstance createEnOceanDriverInstance(
			EnOceanNetwork enOceanNetwork, ControllableDevice device,
			int updateTimeMillis, BundleContext context)
	{
		// Creates a new instance of the actual implementation of the TemperatureSensor driver
		return new TemperatureSensorDriverInstance(enOceanNetwork, device,
				updateTimeMillis, context);
	}

}
