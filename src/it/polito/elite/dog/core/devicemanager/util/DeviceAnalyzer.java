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

import it.polito.elite.dog.core.library.util.LogHelper;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.device.Constants;
import org.osgi.service.device.Device;
import org.osgi.service.log.LogService;

/**
 * This class performs validation upon devices.
 * 
 * @author <a href="mailto:dev@felix.apache.org">Felix Project Team</a>
 *         (original version)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
// TODO Apparently, this class is not used, right now...
public class DeviceAnalyzer
{
	// the logger
	private LogHelper logger;
	
	// filters for trackers
	private Filter deviceImpl;
	private Filter validCategory;
	
	// the bundle context
	private final BundleContext bundleContext;
	
	/**
	 * Default constructor
	 * 
	 * @param context
	 *            the bundle context
	 */
	public DeviceAnalyzer(BundleContext context)
	{
		this.bundleContext = context;
		this.logger = new LogHelper(context);
	}
	
	@SuppressWarnings("unused")
	private void start() throws InvalidSyntaxException
	{
		String deviceString = Util.createFilterString("(%s=%s)", new Object[] {
				org.osgi.framework.Constants.OBJECTCLASS, Device.class.getName() });
		
		this.deviceImpl = this.bundleContext.createFilter(deviceString);
		
		String categoryString = Util.createFilterString("(%s=%s)", new Object[] { Constants.DEVICE_CATEGORY, "*" });
		
		this.validCategory = this.bundleContext.createFilter(categoryString);
	}
	
	/**
	 * Used to analyze invalid devices
	 * 
	 * @param ref
	 *            the service reference
	 */
	public void deviceAdded(ServiceReference<?> ref)
	{
		
		if (this.deviceImpl.match(ref))
		{
			return;
		}
		if (this.validCategory.match(ref))
		{
			Object cat = ref.getProperty(Constants.DEVICE_CATEGORY);
			if (!String[].class.isInstance(cat))
			{
				logger.log(LogService.LOG_ERROR, "invalid device: invalid device category: " + Util.showDevice(ref));
				return;
			}
			if (String[].class.cast(cat).length == 0)
			{
				logger.log(LogService.LOG_ERROR, "invalid device: empty device category: " + Util.showDevice(ref));
			}
		}
		else
		{
			this.logger.log(LogService.LOG_ERROR, "invalid device: no device category: " + Util.showDevice(ref));
		}
	}
}
