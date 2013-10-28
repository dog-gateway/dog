/*
 * Dog - Admin
 * 
 * Copyright (c) 2013 Dario Bonino
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

import it.polito.elite.dog.admin.system.frameworkmonitor.api.FrameworkMonitorInterface;
import it.polito.elite.dog.core.library.util.LogHelper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
@Path("/admin/framework/")
public class FrameworkMonitor implements FrameworkMonitorInterface
{
	// the service logger
	private LogHelper logger;
	
	// the bundle context reference to extract information on the entire Dog
	// status
	private BundleContext context;
	
	/**
	 * 
	 */
	public FrameworkMonitor()
	{
		// TODO Auto-generated constructor stub
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
	

	@Override
	@GET
	@Path("/memory/runtime")
	@Produces("text/html")
	public String getRuntimeMemory()
	{
		return "<span class=\"label label-info pull-right\">"+(Runtime.getRuntime().totalMemory() / (1024 * 1024))+" MBytes</span>";
	}

	@Override
	@GET
	@Path("/memory/free")
	@Produces("text/html")
	public String getFreeMemory()
	{
		StringBuffer response = new StringBuffer();
		response.append("<span class=\"label pull-right");
		
		//get the free and total memory
		long systemMemory = (Runtime.getRuntime().totalMemory() / (1024 * 1024));
		long freeMemory = (Runtime.getRuntime().freeMemory() / (1024 * 1024));
		
		//compute the percentage of free memory
		double percentMemory = (double)freeMemory/(double)systemMemory;
		
		//color the memory value
		if(percentMemory>.66)
		{
			response.append(" label-success\">");
		}
		else if(percentMemory>.33)
		{
			response.append(" label-warning\">");
		}
		else
		{
			response.append(" label-important\">");
		}
	
		response.append(freeMemory+" MBytes</span>");
		
		
		return response.toString();
	}

	@Override
	@GET
	@Path("/memory/used")
	@Produces("text/html")
	public String getUsedMemory()
	{
		StringBuffer response = new StringBuffer();
		response.append("<span class=\"label pull-right");
		
		//get the free and total memory
		long systemMemory = (Runtime.getRuntime().totalMemory() / (1024 * 1024));
		long freeMemory = (Runtime.getRuntime().freeMemory() / (1024 * 1024));
		long usedMemory = systemMemory-freeMemory;
		
		//compute the percentage of free memory
		double percentMemory = (double)usedMemory/(double)systemMemory;
		
		//color the memory value
		if(percentMemory>.66)
		{
			response.append(" label-error\">");
		}
		else if(percentMemory>.33)
		{
			response.append(" label-warning\">");
		}
		else
		{
			response.append(" label-success\">");
		}
	
		response.append(usedMemory+" MBytes</span>");
		
		
		return response.toString();
	}
	
}
