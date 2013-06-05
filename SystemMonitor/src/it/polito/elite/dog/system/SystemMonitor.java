/**
 * 
 */
package it.polito.elite.dog.system;

import it.polito.elite.dog.system.api.SystemMonitorInterface;
import it.polito.elite.dog.system.util.BundleNameComparator;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;

import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * @author bonino
 * 
 */
@Path("/system/")
public class SystemMonitor implements SystemMonitorInterface
{
	// the service logger
	private LogService logger;
	
	// the log id
	public static final String logId = "[SystemManager]: ";
	
	// the bundle context reference to extract information on the entire Dog
	// status
	private BundleContext context;
	
	/**
	 * 
	 */
	public SystemMonitor()
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
		this.logger = new DogLogInstance(this.context);
		
		// log the activation
		this.logger.log(LogService.LOG_INFO, SystemMonitor.logId + "Activated....");
	}
	
	/**
	 * Prepare the bundle to be deactivated...
	 */
	public void deactivate()
	{
		// null the context
		this.context = null;
		
		// log deactivation
		this.logger.log(LogService.LOG_INFO, SystemMonitor.logId+"Deactivated...");
		
		// null the logger
		this.logger = null;
	}
	@Override
	@GET
	@Path("/bundles")
	@Produces(MediaType.TEXT_HTML)
	public String getBundles()
	{
		//get all the installed bundles
		Bundle allBundles[] = this.context.getBundles();
		Arrays.sort(allBundles, new BundleNameComparator());
		
		//the output buffer
		StringBuffer htmlOut = new StringBuffer();
		
		//append the unordered list header
		htmlOut.append("<ul class=\"unstyled\">\n");
		
		//generate a list of bundles
		for(int i=0; i<allBundles.length; i++)
		{
			htmlOut.append("<li>"+allBundles[i].getSymbolicName()+" ("+allBundles[i].getVersion()+") ");
			switch(allBundles[i].getState())
			{
				case Bundle.ACTIVE:
				{
					htmlOut.append("<span class=\"label label-success pull-right\">Active</span>");
					break;
				}
				case Bundle.INSTALLED:
				{
					htmlOut.append("<span class=\"label label-info pull-right\">Installed</span>");
					break;
				}
				case Bundle.RESOLVED:
				{
					htmlOut.append("<span class=\"label label-warning pull-right\">Resolved</span>");
					break;
				}
			}
			
			htmlOut.append("</li>\n");
		}
		
		htmlOut.append("</ul>\n");
		
		return htmlOut.toString();
		
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
			response.append(" label-error\">");
		}
	
		response.append(+freeMemory+" MBytes</span>");
		
		
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
	
		response.append(+freeMemory+" MBytes</span>");
		
		
		return response.toString();
	}
	
}
