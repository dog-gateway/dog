/**
 * 
 */
package org.doggateway.drivers.enocean.temperaturesensor;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.devicecategory.SingleTemperatureSensor;

import org.doggateway.drivers.enocean.device.EnOceanDeviceDriver;
import org.doggateway.drivers.enocean.network.EnOceanDriverInstance;
import org.doggateway.drivers.enocean.network.interfaces.EnOceanNetwork;
import org.osgi.framework.BundleContext;

/**
 * @author bonino
 *
 */
public class TemperatureSensorDriver extends EnOceanDeviceDriver
{

	/**
	 * 
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
		// TODO Auto-generated method stub
		return new TemperatureSensorDriverInstance(enOceanNetwork, device, updateTimeMillis, context);
	}

}
