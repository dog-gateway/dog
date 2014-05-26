/*
 * Dog - Admin
 * 
 * Copyright (c) 2013-2014 Dario Bonino and Luigi De Russis
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
package it.polito.elite.dog.admin.system.frameworkmonitor;

import it.polito.elite.dog.admin.system.frameworkmonitor.api.FrameworkMonitor;
import it.polito.elite.dog.admin.system.frameworkmonitor.model.DeviceStatsResponse;
import it.polito.elite.dog.admin.system.frameworkmonitor.model.FrameworkMonitorResponse;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.util.LogHelper;

import javax.ws.rs.Path;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.device.Device;
import org.osgi.service.log.LogService;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
@Path("/admin/framework/")
public class FrameworkMonitorEndpoint implements FrameworkMonitor
{
	// the service logger
	private LogHelper logger;
	
	// the bundle context reference to extract information on the framework
	// status
	private BundleContext context;
	
	// the instance-level mapper
	private ObjectMapper mapper;
	
	/**
	 * Default constructor
	 */
	public FrameworkMonitorEndpoint()
	{
		// initialize the instance-wide object mapper
		this.mapper = new ObjectMapper();
		// set the mapper pretty printing
		this.mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
		// avoid empty arrays and null values
		this.mapper.configure(SerializationConfig.Feature.WRITE_EMPTY_JSON_ARRAYS, false);
		this.mapper.setSerializationInclusion(Inclusion.NON_NULL);
	}
	
	/**
	 * Bundle activation, stores a reference to the context object passed by the
	 * framework to get access to system data, e.g., installed bundles, etc.
	 * 
	 * @param context
	 */
	public void activate(BundleContext context)
	{
		// store the bundle context
		this.context = context;
		
		// init the logger with a null logger
		this.logger = new LogHelper(this.context);
		
		// log the activation
		this.logger.log(LogService.LOG_INFO, "Activated....");
	}
	
	/**
	 * Prepare the bundle to be deactivated...
	 */
	public void deactivate()
	{
		// null the context
		this.context = null;
		
		// log deactivation
		this.logger.log(LogService.LOG_INFO, "Deactivated...");
		
		// null the logger
		this.logger = null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.admin.system.frameworkmonitor.api.FrameworkMonitor
	 * #getRuntimeMemory()
	 */
	@Override
	public String getRuntimeMemory()
	{
		// init
		FrameworkMonitorResponse current = new FrameworkMonitorResponse();
		String runtimeMemory = "";
		
		// get the current runtime memory
		long totalMemory = Runtime.getRuntime().totalMemory() / (1024 * 1024);
		
		// store the value
		current.setValue(totalMemory + " MB");
		
		try
		{
			// try to create the JSON object
			runtimeMemory = this.mapper.writeValueAsString(current);
		}
		catch (Exception e)
		{
			this.logger.log(LogService.LOG_ERROR, "Error in creating the JSON object for the runtime memory", e);
		}
		
		return runtimeMemory;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.admin.system.frameworkmonitor.api.FrameworkMonitor
	 * #getFreeMemory()
	 */
	@Override
	public String getFreeMemory()
	{
		// init
		FrameworkMonitorResponse current = new FrameworkMonitorResponse();
		String freeMem = "";
		
		// get the current free memory
		long freeMemory = Runtime.getRuntime().freeMemory() / (1024 * 1024);
		
		// compute the percentage of free memory
		long systemMemory = Runtime.getRuntime().totalMemory() / (1024 * 1024);
		double percentMemory = Math.round(((double) freeMemory / (double) systemMemory) * 100.0) / 100.0;
		
		// store the values
		current.setValue(freeMemory + " MB");
		current.setRatio(String.valueOf(percentMemory));
		
		try
		{
			// try to create the JSON object
			freeMem = this.mapper.writeValueAsString(current);
		}
		catch (Exception e)
		{
			this.logger.log(LogService.LOG_ERROR, "Error in creating the JSON object for the free memory", e);
		}
		
		return freeMem;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.admin.system.frameworkmonitor.api.FrameworkMonitor
	 * #getUsedMemory()
	 */
	@Override
	public String getUsedMemory()
	{
		// init
		FrameworkMonitorResponse current = new FrameworkMonitorResponse();
		String occupiedMemory = "";
		
		// get the free and total memory
		long systemMemory = Runtime.getRuntime().totalMemory() / (1024 * 1024);
		long freeMemory = Runtime.getRuntime().freeMemory() / (1024 * 1024);
		long usedMemory = systemMemory - freeMemory;
		
		// compute the percentage of free memory
		double percentMemory = Math.round(((double) usedMemory / (double) systemMemory) * 100.0) / 100.0;
		
		// store the values
		current.setValue(usedMemory + " MB");
		current.setRatio(String.valueOf(percentMemory));
		
		try
		{
			// try to create the JSON object
			occupiedMemory = this.mapper.writeValueAsString(current);
		}
		catch (Exception e)
		{
			this.logger.log(LogService.LOG_ERROR, "Error in creating the JSON object for the used memory", e);
		}
		
		return occupiedMemory;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.admin.system.frameworkmonitor.api.FrameworkMonitor
	 * #getDeviceStats()
	 */
	@Override
	public String getDeviceStats()
	{
		// init
		DeviceStatsResponse devStats = new DeviceStatsResponse();
		String statistics = "";
		
		// prepare device counters
		int nDevices = 0;
		int nActiveDevices = 0;
		try
		{
			ServiceReference<?>[] allDevices = this.context.getAllServiceReferences(Device.class.getName(), null);
			
			if (allDevices != null)
			{
				for (int i = 0; i < allDevices.length; i++)
				{
					Object device = this.context.getService(allDevices[i]);
					if (device instanceof ControllableDevice)
					{
						// get the device activation status
						String active = (String) allDevices[i].getProperty(DeviceCostants.ACTIVE);
						
						// parse the boolean value hold by the active string
						boolean isDeviceActive = Boolean.valueOf(active);
						
						// updated device counts
						nDevices++;
						if (isDeviceActive)
							nActiveDevices++;
					}
					
					this.context.ungetService(allDevices[i]);
				}
			}
		}
		catch (InvalidSyntaxException e)
		{
			this.logger.log(LogService.LOG_ERROR, "Error in getting the device from the OSGi framework", e);
		}
		
		// compute the active ratio
		double activeRatio = (double) nActiveDevices / (double) nDevices;
		
		devStats.setActive(nActiveDevices);
		devStats.setIdle(nDevices - nActiveDevices);
		devStats.setActiveRatio(activeRatio);
		
		try
		{
			// try to create the JSON object
			statistics = this.mapper.writeValueAsString(devStats);
		}
		catch (Exception e)
		{
			this.logger.log(LogService.LOG_ERROR, "Error in creating the JSON object for the device statistics", e);
		}
		
		return statistics;
	}
	
}
