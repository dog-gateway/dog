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
package it.polito.elite.dogdrivers.bticino.c8;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.devicecategory.SnapshotCamera;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.drivers.bticino.interfaces.BTicinoNetworkDriver;

import java.util.Hashtable;

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
public class BTicinoC8Driver implements Driver, ServiceTrackerCustomizer<Object, Object>
{
	
	// OSGi framework
	private BundleContext context;
	// Keys: device address, value: driver instance that manages the device
	private Hashtable<String, BTicinoC8DriverInstance> routingTable;
	
	private LogHelper logger;
	
	// it tracks the BticinoNetworkDriver
	private ServiceTracker<?, ?> tracker;
	// LDAP filter for listening all BTicino Network Driver
	private String filter = String.format("(&(%s=%s)(%s=%s))", Constants.OBJECTCLASS,
			BTicinoNetworkDriver.class.getName(), BTicinoNetworkDriver.CONNECTED, true);
	
	// Service for access the BTcino network
	private BTicinoNetworkDriver network;
	private ServiceRegistration<?> serviceReg;
	
	public BTicinoC8Driver(BundleContext context)
	{
		this.context = context;
		this.logger = new LogHelper(context);
		this.routingTable = new Hashtable<String, BTicinoC8DriverInstance>();
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
		Object service = this.context.getService(reference);
		if (service instanceof ControllableDevice)
		{
			ControllableDevice device = (ControllableDevice) service;
			BTicinoC8DriverInstance driverInst = new BTicinoC8DriverInstance(device, this, this.network);
			this.routingTable.put(driverInst.getAddress(), driverInst);
		}
		
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int match(ServiceReference reference) throws Exception
	{
		
		int matchValue = Device.MATCH_NONE;
		String manufacturer = (String) reference.getProperty(DeviceCostants.MANUFACTURER);
		String category = (String) reference.getProperty(DeviceCostants.DEVICE_CATEGORY);
		
		if (manufacturer != null)
		{
			if (manufacturer.equals(BTicinoNetworkDriver.MANUFACTURER))
				if (category != null)
				{
					if (category.equals(SnapshotCamera.class.getName()))
						matchValue = SnapshotCamera.MATCH_MANUFACTURER + SnapshotCamera.MATCH_TYPE;
				}
		}
		
		return matchValue;
	}
	
	@Override
	public Object addingService(ServiceReference<Object> reference)
	{
		
		Object service = this.context.getService(reference);
		if (service instanceof BTicinoNetworkDriver)
		{
			Boolean connected = (Boolean) reference.getProperty(BTicinoNetworkDriver.CONNECTED);
			if (connected != null && connected)
			{
				this.network = (BTicinoNetworkDriver) service;
				this.registerDriver();
			}
		}
		
		return reference;
	}
	
	private void registerDriver()
	{
		Hashtable<String, Object> driverProp = new Hashtable<String, Object>();
		driverProp.put(org.osgi.service.device.Constants.DRIVER_ID, BTicinoC8Driver.class.getSimpleName());
		this.serviceReg = this.context.registerService(Driver.class.getName(), this, driverProp);
	}
	
	@Override
	public void modifiedService(ServiceReference<Object> reference, Object service)
	{
		
		Boolean connected = (Boolean) reference.getProperty(BTicinoNetworkDriver.CONNECTED);
		if (connected != null && connected)
		{
			this.network = (BTicinoNetworkDriver) service;
			this.registerDriver();
		}
		else if (this.network != null)
		{
			this.unRegisterService();
		}
	}
	
	public void unRegisterDriver()
	{
		this.unRegisterService();
		
		if (this.tracker != null)
			this.tracker.close();
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
		
		for (BTicinoC8DriverInstance driver : this.routingTable.values())
		{
			driver.unSetDriver();
		}
		this.routingTable.clear();
	}
	
}
