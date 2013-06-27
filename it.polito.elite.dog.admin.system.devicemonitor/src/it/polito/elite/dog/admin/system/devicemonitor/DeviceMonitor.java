/**
 * 
 */
package it.polito.elite.dog.admin.system.devicemonitor;

import java.util.Map;

import it.polito.elite.dog.admin.system.devicemonitor.api.DeviceMonitorInterface;
import it.polito.elite.domotics.model.DeviceStatus;
import it.polito.elite.domotics.model.devicecategory.Controllable;
import it.polito.elite.domotics.model.state.State;
import it.polito.elite.domotics.model.statevalue.StateValue;
import it.polito.elite.domotics.dog2.doglibrary.DogDeviceCostants;
import it.polito.elite.domotics.dog2.doglibrary.devicecategory.ControllableDevice;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.device.Device;
import org.osgi.service.log.LogService;

/**
 * @author bonino
 * 
 */
@Path("/system/devices/")
public class DeviceMonitor implements DeviceMonitorInterface
{
	// the service logger
	private LogService logger;
	
	// the log id
	public static final String logId = "[DeviceMonitor]: ";
	
	// the bundle context reference to extract information on the entire Dog
	// status
	private BundleContext context;
	
	/**
	 * 
	 */
	public DeviceMonitor()
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
		this.logger.log(LogService.LOG_INFO, DeviceMonitor.logId + "Activated....");
	}
	
	/**
	 * Prepare the bundle to be deactivated...
	 */
	public void deactivate()
	{
		// null the context
		this.context = null;
		
		// log deactivation
		this.logger.log(LogService.LOG_INFO, DeviceMonitor.logId + "Deactivated...");
		
		// null the logger
		this.logger = null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.admin.system.devicemonitor.api.DeviceMonitorInterface
	 * #getInstalledDevices()
	 */
	@Override
	@GET
	@Path("/")
	@Produces("text/html")
	public String getInstalledDevices()
	{
		// the output buffer
		StringBuffer htmlOut = new StringBuffer();
		
		// append the unordered list header
		//htmlOut.append("<ul class=\"unstyled\">\n");
		
		// get all the installed device services
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
						ControllableDevice currentDevice = (ControllableDevice) device;
						
						// start the list item for the current device
						htmlOut.append("<div class=\"row-fluid\"><div class=\"well\"");
						
						// get the device icon...
						String category = currentDevice.getDeviceDescriptor().getDevCategory();
						htmlOut.append("<p><i class=\"device-"+category.toLowerCase() + "\"></i>");
						
						// print the device identifier
						htmlOut.append(currentDevice.getDeviceId() + " ");
						
						
						
						// get the device activation status
						String active = (String) allDevices[i].getProperty(DogDeviceCostants.ACTIVE);
						
						// render the activation status
						if ((active != null) && (!active.isEmpty()))
						{
							if (active.equals("true"))
							{
								htmlOut.append("<span class=\"label label-success pull-right\">Active</span></p>");
								htmlOut.append("<ul style=\"list-style-type: none\"><li> Current State: "+this.getDeviceState(currentDevice)+"</li></ul>");
							}
							else
							{
								htmlOut.append("<span class=\"label label-warning pull-right\">Not Active</span>");
							}
						}
						
						// close the device-related list item
						htmlOut.append("</div></div>");
					}
				}
			}
		}
		catch (InvalidSyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		htmlOut.append("</ul>\n");
		
		return htmlOut.toString();
	}

	private String getDeviceState(ControllableDevice currentDevice)
	{
		StringBuffer stateAsString = new StringBuffer();
		DeviceStatus state = ((Controllable)currentDevice).getState();
		Map<String,State> allStates = state.getStates();
		
		//iterate over all states
		for(String stateKey : allStates.keySet())
		{
			//get the current state
			State currentState = allStates.get(stateKey);
			
			//get the values associate to the current state
			StateValue currentStateValues[] = currentState.getCurrentStateValue();
			
			//iterate over the values
			int i;
			for(i=0; i<currentStateValues.length; i++)
			{
				stateAsString.append("<span class=\"label label-info\">"+currentStateValues[i].getValue()+"</span>");
			}
			
			if(i>1)
				stateAsString.append("<br/>");
		}
		return stateAsString.toString();
	}
}
