/*
 * Dog - Core
 * 
 * Copyright (c) 2013 Dario Bonino and Luigi De Russis
 * 
 * This software is based on a bundle of the Apache Felix project.
 * See the NOTICE file distributed with this work for additional information.
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
package it.polito.elite.dog.core.devicemanager.util;

import it.polito.elite.dog.core.devicemanager.DriverAttributes;
import it.polito.elite.dog.core.devicemanager.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.device.Constants;
import org.osgi.service.device.DriverLocator;

/**
 * This class load the proper driver, during the attach phase.
 * 
 * @author <a href="mailto:dev@felix.apache.org">Felix Project Team</a>
 *         (original version)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class DriverLoader
{
	
	private final BundleContext bundleContext;
	
	private final Log logger;
	
	public final static String DRIVER_LOCATION_PREFIX = "_DD_";
	
	/**
	 * to keep track of all loaded drivers
	 */
	private final List<ServiceReference<?>> loadedDrivers;
	
	public DriverLoader(Log log, BundleContext context)
	{
		this.logger = log;
		this.bundleContext = context;
		this.loadedDrivers = new ArrayList<ServiceReference<?>>();
	}
	
	public Set<String> findDrivers(Collection<DriverLocator> locators, Dictionary<?,?> dict)
	{
		final Set<String> list = new HashSet<String>();
		for (DriverLocator locator : locators)
		{
			list.addAll(findDrivers(locator, dict));
		}
		
		return list;
	}
	
	private List<String> findDrivers(DriverLocator locator, Dictionary<?,?> dict)
	{
		final List<String> list = new ArrayList<String>();
		try
		{
			String[] ids = locator.findDrivers(dict);
			if (ids != null)
			{
				list.addAll(Arrays.asList(ids));
			}
		}
		catch (Exception e)
		{
			// ignore, will also frequently happen (?)
			// m_log.error("findDrivers failed for: " + locator, e);
		}
		
		return list;
	}
	
	/**
	 * Load all the drivers that belong to the given driver Ids
	 * 
	 * @param locator
	 * @param driverIds
	 */
	public List<ServiceReference<?>> loadDrivers(List<DriverLocator> locators, String[] driverIds)
	{
		List<ServiceReference<?>> driverRefs = new ArrayList<ServiceReference<?>>();
		
		for (String driverId : driverIds)
		{
			driverRefs.addAll(loadDriver(locators, driverId));
		}
		
		return driverRefs;
	}
	
	private List<ServiceReference<?>> loadDriver(List<DriverLocator> locators, String driverId)
	{
		List<ServiceReference<?>> driverRefs = new ArrayList<ServiceReference<?>>();
		
		for (DriverLocator driverLocator : locators)
		{
			List<ServiceReference<?>> drivers = loadDriver(driverLocator, driverId);
			driverRefs.addAll(drivers);
			if (drivers.size() > 0)
			{
				break;
			}
		}
		
		return driverRefs;
	}
	
	private List<ServiceReference<?>> loadDriver(DriverLocator locator, String driverId)
	{
		List<ServiceReference<?>> driverRefs = new ArrayList<ServiceReference<?>>();
		try
		{
			InputStream in = locator.loadDriver(driverId);
			// System.out.println(driverId + ", " + locator + " returned: " +
			// in);
			Bundle driverBundle = this.bundleContext.installBundle(DRIVER_LOCATION_PREFIX + driverId, in);
			
			driverBundle.start();
			
			ServiceReference<?>[] refs = driverBundle.getRegisteredServices();
			
			driverRefs.addAll(Arrays.asList(refs));
			// keep track of them locally
			this.loadedDrivers.addAll(Arrays.asList(refs));
			
		}
		catch (Throwable t)
		{
			// ignore, this will happen frequently, if there are multiple
			// locators
		}
		return driverRefs;
	}
	
	public void unload(DriverAttributes finalDriver)
	{
		
		ServiceReference<?> finalRef = null;
		if (finalDriver != null)
		{
			finalRef = finalDriver.getReference();
			this.logger.debug("unloading all except: " + finalRef.getProperty(Constants.DRIVER_ID));
		}
		for (ServiceReference<?> ref : this.loadedDrivers)
		{
			if (!ref.equals(finalRef))
			{
				try
				{
					this.logger.debug("uninstalling: " + ref.getProperty(Constants.DRIVER_ID));
					ref.getBundle().uninstall();
				}
				catch (BundleException e)
				{
					this.logger.warning("unable to uninstall: " + ref.getProperty(Constants.DRIVER_ID));
				}
			}
		}
	}
}
