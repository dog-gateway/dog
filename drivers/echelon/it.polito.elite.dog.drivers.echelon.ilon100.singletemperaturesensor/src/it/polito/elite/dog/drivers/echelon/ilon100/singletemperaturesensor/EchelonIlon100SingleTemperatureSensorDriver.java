/*
 * Dog - Device Driver
 * 
 * Copyright (c) 2012-2014 Dario Bonino
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
package it.polito.elite.dog.drivers.echelon.ilon100.singletemperaturesensor;

import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.devicecategory.SingleTemperatureSensor;
import it.polito.elite.dog.drivers.echelon.ilon100.gateway.EchelonIlon100GatewayDriver;
import it.polito.elite.dog.drivers.echelon.ilon100.network.info.EchelonIlonInfo;
import it.polito.elite.dog.drivers.echelon.ilon100.network.interfaces.EchelonIlon100Network;

import java.util.Hashtable;
import java.util.Vector;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.device.Device;
import org.osgi.service.device.Driver;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;


/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class EchelonIlon100SingleTemperatureSensorDriver implements Driver, ServiceTrackerCustomizer<Object, Object>
{
	// The OSGi framework context
	protected BundleContext context;
	
	// System logger
	LogHelper logger;
	
	// the log identifier, unique for the class
	public static String logId = "[EchelonIlon100SingleTemperatureSensorDriver]: ";
	
	// a reference to the network driver
	private EchelonIlon100Network network;
	
	// a reference to the gateway driver
	private EchelonIlon100GatewayDriver gateway;
	
	// the list of driver instances currently connected to a device
	private Vector<EchelonIlon100SingleTemperatureSensorDriverInstance> connectedDrivers;
	
	// the registration object needed to handle the lifespan of this bundle in
	// the OSGi framework (it is a ServiceRegistration object for use by the
	// bundle registering the service to update the service's properties or to
	// unregister the service).
	private ServiceRegistration<?> regDriver;
	
	// the filter query for listening to framework events relative to the
	// Echelon network driver
	// and to the Echelon gateway driver
	// String filterQuery1 = String.format("(%s=%s)", Constants.OBJECTCLASS,
	// EchelonIlon100Network.class.getName());
	String filterQuery = String.format("(%s=%s)", Constants.OBJECTCLASS, EchelonIlon100GatewayDriver.class.getName());
	
	public EchelonIlon100SingleTemperatureSensorDriver(BundleContext context)
	{
		// init the logger
		this.logger = new LogHelper(context);
		
		// store the context
		this.context = context;
		
		// initialize the connected drivers list
		this.connectedDrivers = new Vector<EchelonIlon100SingleTemperatureSensorDriverInstance>();
		
		// start the service tracker...
		try
		{
			// create the service tracker
			ServiceTracker<?,?> st = new ServiceTracker<Object, Object>(context, this.context.createFilter(filterQuery), this);
			
			// open the tracker
			st.open();
		}
		catch (InvalidSyntaxException e)
		{
			this.logger
					.log(LogService.LOG_ERROR,
							EchelonIlon100SingleTemperatureSensorDriver.logId
									+ " wrong syntax in the LDAP filter specified for getting an instance of EchelonIlon100Network and of EchelonIlon100GatewayDriver\nNested Exception: ",
							e);
		}
	}
	
	@Override
	public Object addingService(ServiceReference<Object> reference)
	{
		// chek the reference type
		this.gateway = (EchelonIlon100GatewayDriver) this.context.getService(reference);
		
		this.network = this.gateway.getNetwork();
		if ((this.gateway != null) && (this.network != null))
			this.register();
		
		return null;
	}
	
	@Override
	public void modifiedService(ServiceReference<Object> reference, Object service)
	{
		// empty - modification of tracked services are not handled
		
	}
	
	@Override
	public void removedService(ServiceReference<Object> reference, Object service)
	{
		// remove the service from the OSGi framework
		this.unRegister();
		
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int match(ServiceReference reference) throws Exception
	{
		int matchValue = Device.MATCH_NONE;
		
		// get the given device category
		String deviceCategory = (String) reference.getProperty(DeviceCostants.DEVICE_CATEGORY);
		
		// get the given device manufacturer
		String manifacturer = (String) reference.getProperty(DeviceCostants.MANUFACTURER);
		
		// get the gateway to which the device is connected
		@SuppressWarnings("unchecked")
		String gateway = (String) ((ControllableDevice) this.context.getService(reference)).getDeviceDescriptor()
				.getGateway();
		
		// compute the matching score between the given device and this driver
		if (deviceCategory != null)
		{
			if (manifacturer != null
					&& (gateway != null)
					&& (manifacturer.equals(EchelonIlonInfo.MANUFACTURER))
					&& (deviceCategory.equals(SingleTemperatureSensor.class.getName()) && (this.gateway
							.isGatewayAvailable(gateway))

					))
			{
				matchValue = SingleTemperatureSensor.MATCH_MANUFACTURER + SingleTemperatureSensor.MATCH_TYPE;
			}
			
		}
		return matchValue;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String attach(final ServiceReference reference) throws Exception
	{
		// get the gateway to which the device is connected
		String gateway = (String) ((ControllableDevice) this.context.getService(reference)).getDeviceDescriptor()
				.getGateway();
		
		// create a new driver instance
		final EchelonIlon100SingleTemperatureSensorDriverInstance driverInstance = new EchelonIlon100SingleTemperatureSensorDriverInstance(
				network, (ControllableDevice) this.context.getService(reference), this.gateway.getSpecificGateway(
						gateway).getEndpointAddress(), this.context);
		
		((ControllableDevice)context.getService(reference)).setDriver(driverInstance);
		
		synchronized (this.connectedDrivers)
		{
			this.connectedDrivers.add(driverInstance);
		}
		return null;
	}
	
	/**
	 * Registers this driver in the OSGi framework making its services available
	 * for all the other Dog bundles
	 */
	private void register()
	{
		// create a new property object describing this driver
		Hashtable<String, Object> propDriver = new Hashtable<String, Object>();
		
		// add the id of this driver to the properties
		propDriver.put(DeviceCostants.DRIVER_ID, "EchelonIlon100_SingleTemperatureSensor_driver");
		
		// register this driver in the OSGi framework
		this.regDriver = this.context.registerService(Driver.class.getName(), this, propDriver);
		
	}
	
	/**
	 * Unregisters this driver from the OSGi framework...
	 */
	public void unRegister()
	{
		// un-registers this driver, if registered
		if (this.regDriver != null)
		{
			this.regDriver.unregister();
			this.regDriver = null;
		}
		
	}
	
}
