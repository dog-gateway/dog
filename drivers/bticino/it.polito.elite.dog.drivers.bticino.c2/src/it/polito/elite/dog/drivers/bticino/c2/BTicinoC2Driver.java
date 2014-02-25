/*
 * Dog - Device Driver
 * 
 * Copyright (c) 2010-2014 Emiliano Castellina and Luigi De Russis
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
package it.polito.elite.dog.drivers.bticino.c2;

import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.devicecategory.ShutterActuator;
import it.polito.elite.dog.core.library.model.devicecategory.ShutterButton;
import it.polito.elite.dog.drivers.bticino.interfaces.BTicinoNetworkDriver;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

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
 * 
 * @author <a href="mailto:castellina.emi@gmail.com">Emiliano Castellina</a>
 *         (original version)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class BTicinoC2Driver implements Driver, ServiceTrackerCustomizer<Object, Object>
{
	
	private LogHelper logger;
	
	static long time = 6000;
	/** OSGi framework */
	private BundleContext context;
	/** Keys: device address, value: driver instance that manages the device */
	private Set<BTicinoC2DriverInstance> drivers;
	
	/** it tracks the BticinoNetworkDriver */
	private ServiceTracker<?, ?> tracker;
	/** LDAP filter for listening all BTicnino Network Driver */
	private String filter = String.format("(&(%s=%s)(%s=%s))", Constants.OBJECTCLASS,
			BTicinoNetworkDriver.class.getName(), BTicinoNetworkDriver.CONNECTED, true);
	
	/** Service for access the BTcino network */
	private BTicinoNetworkDriver network;
	
	/**
	 * @return the network
	 */
	public BTicinoNetworkDriver getNetwork()
	{
		return network;
	}
	
	private ServiceRegistration<?> serviceReg;
	
	/**
	 * Class constructor
	 * 
	 * @param context
	 *            OSGi framework
	 */
	public BTicinoC2Driver(BundleContext context)
	{
		this.context = context;
		this.drivers = new HashSet<BTicinoC2DriverInstance>();
		this.logger = new LogHelper(context);
		try
		{
			this.tracker = new ServiceTracker<Object, Object>(context, context.createFilter(filter), this);
			this.tracker.open();
		}
		catch (InvalidSyntaxException e)
		{
			this.logger.log(LogService.LOG_ERROR, "Error while creating ServiceTracker filter", e);
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String attach(ServiceReference reference) throws Exception
	{
		ControllableDevice device = (ControllableDevice) this.context.getService(reference);
		BTicinoC2DriverInstance driverInst = new BTicinoC2DriverInstance(device, this);
		synchronized (this.drivers)
		{
			this.drivers.add(driverInst);
		}
		
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int match(ServiceReference reference) throws Exception
	{
		int matchValue = Device.MATCH_NONE;
		String deviceCategory = (String) reference.getProperty(DeviceCostants.DEVICE_CATEGORY);
		String manifacturer = (String) reference.getProperty(DeviceCostants.MANUFACTURER);
		if (deviceCategory != null)
		{
			if (manifacturer != null
					&& manifacturer.equals(BTicinoNetworkDriver.MANUFACTURER)
					&& (deviceCategory.equals(ShutterActuator.class.getName()) || deviceCategory
							.equals(ShutterButton.class.getName())))
			{
				matchValue = ShutterActuator.MATCH_MANUFACTURER + ShutterActuator.MATCH_TYPE + 30;
			}
			
		}
		
		return matchValue;
	}
	
	@Override
	public Object addingService(ServiceReference<Object> reference)
	{
		Boolean connected = (Boolean) reference.getProperty(BTicinoNetworkDriver.CONNECTED);
		if (connected != null && connected.booleanValue())
		{
			
			this.network = (BTicinoNetworkDriver) this.context.getService(reference);
			this.registerDriver();
		}
		
		return reference;
	}
	
	/** Register the driver Bticino C2 service */
	private void registerDriver()
	{
		Hashtable<String, Object> driverProp = new Hashtable<String, Object>();
		driverProp.put(org.osgi.service.device.Constants.DRIVER_ID, BTicinoC2Driver.class.getSimpleName());
		this.serviceReg = this.context.registerService(Driver.class.getName(), this, driverProp);
		
	}
	
	protected void unRegisterDriver()
	{
		this.unRegisterService();
		
		if (this.tracker != null)
			this.tracker.close();
	}
	
	@Override
	public void modifiedService(ServiceReference<Object> reference, Object service)
	{
		Boolean connected = (Boolean) reference.getProperty(BTicinoNetworkDriver.CONNECTED);
		if (connected != null && connected.booleanValue())
		{
			
			this.network = (BTicinoNetworkDriver) service;
			this.registerDriver();
			
		}
		else if (this.network != null)
		{
			this.unRegisterService();
		}
		
	}
	
	@Override
	public void removedService(ServiceReference<Object> reference, Object service)
	{
		this.unRegisterService();
		
	}
	
	public void unRegisterService()
	{
		if (this.serviceReg != null)
		{
			this.serviceReg.unregister();
			this.serviceReg = null;
		}
		this.network = null;
		
		for (BTicinoC2DriverInstance driver : this.drivers)
		{
			driver.unSet();
		}
		this.drivers.clear();
	}
	
	/**
	 * 
	 * @return the LogHelper reference
	 */
	public LogHelper getLogger()
	{
		return this.logger;
		
	}
	
}
