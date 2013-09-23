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

import org.osgi.framework.ServiceReference;
import org.osgi.service.device.Constants;
import org.osgi.service.log.LogService;

/**
 * This class performs validation upon drivers.
 * 
 * @author <a href="mailto:dev@felix.apache.org">Felix Project Team</a>
 *         (original version)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
//TODO Apparently, this class is not used, right now...
public class DriverAnalyzer
{
	// the logger
	private LogService logger;
	
	/**
	 * Used to analyze invalid drivers
	 * 
	 * @param ref
	 *            the service reference
	 */
	public void driverAdded(ServiceReference<?> ref)
	{
		Object driverId = ref.getProperty(Constants.DRIVER_ID);
		if (driverId == null || !String.class.isInstance(driverId))
		{
			this.logger.log(LogService.LOG_ERROR, "invalid driver: no driver id: " + Util.showDriver(ref));
			return;
		}
		if (String.class.isInstance(driverId))
		{
			String value = (String) (driverId);
			if (value.length() == 0)
			{
				this.logger.log(LogService.LOG_ERROR, "invalid driver: empty driver id: " + Util.showDriver(ref));
			}
		}
	}
}
