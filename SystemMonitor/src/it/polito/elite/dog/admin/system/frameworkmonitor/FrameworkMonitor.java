/**
 * 
 */
package it.polito.elite.dog.admin.system.frameworkmonitor;

import it.polito.elite.dog.admin.system.frameworkmonitor.api.FrameworkMonitorInterface;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * @author bonino
 * 
 */
@Path("/framework/")
public class FrameworkMonitor implements FrameworkMonitorInterface
{
	// the service logger
	private LogService logger;
	
	// the log id
	public static final String logId = "[FrameworkMonitor]: ";
	
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
		this.logger = new DogLogInstance(this.context);
		
		// log the activation
		this.logger.log(LogService.LOG_INFO, FrameworkMonitor.logId + "Activated....");
	}
	
	/**
	 * Prepare the bundle to be deactivated...
	 */
	public void deactivate()
	{
		// null the context
		this.context = null;
		
		// log deactivation
		this.logger.log(LogService.LOG_INFO, FrameworkMonitor.logId+"Deactivated...");
		
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
			response.append(" label-error\">");
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
