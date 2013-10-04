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
package it.polito.elite.dog.admin.system.devicemonitor;

import java.lang.reflect.Method;
import java.util.Map;

import it.polito.elite.dog.admin.system.devicemonitor.api.DeviceMonitorInterface;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.devicecategory.Controllable;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.StateValue;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.device.Device;
import org.osgi.service.log.LogService;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
@Path("/system/devicemonitor/")
public class DeviceMonitor implements DeviceMonitorInterface
{
	// the service logger
	private LogHelper logger;

	// devices per row
	public static final int devicesPerRow = 3;
	public static final int spanPerRow = 12;

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
	 * it.polito.elite.dog.admin.system.devicemonitor.api.DeviceMonitorInterface
	 * #getInstalledDevices()
	 */
	@Override
	@GET
	@Path("/devices")
	@Produces("text/html")
	public String getInstalledDevices()
	{
		// the output buffer
		StringBuffer htmlOut = new StringBuffer();

		// append the unordered list header
		// htmlOut.append("<ul class=\"unstyled\">\n");

		// get all the installed device services
		try
		{
			ServiceReference<?>[] allDevices = this.context
					.getAllServiceReferences(Device.class.getName(), null);

			if (allDevices != null)
			{
				for (int i = 0; i < allDevices.length; i++)
				{
					Object device = this.context.getService(allDevices[i]);
					if (device instanceof ControllableDevice)
					{
						ControllableDevice currentDevice = (ControllableDevice) device;

						// start the list item for the current device
						if ((i % DeviceMonitor.devicesPerRow) == 0)
						{
							// close div the last opened div, except for the
							// first row
							if (i != 0)
								htmlOut.append("</div>");
							htmlOut.append("<div class=\"row-fluid\">");
						}

						// format as a grid
						htmlOut.append("<div class=\"span"
								+ (DeviceMonitor.spanPerRow / DeviceMonitor.devicesPerRow)
								+ "\">");
						htmlOut.append("<div class=\"well\"");

						// get the device icon...
						String category = currentDevice.getDeviceDescriptor()
								.getDevCategory();
						htmlOut.append("<p><i class=\"device-"
								+ category.toLowerCase() + "\"></i>");

						// print the device identifier
						htmlOut.append(currentDevice.getDeviceId() + " ");

						// get the device activation status
						String active = (String) allDevices[i]
								.getProperty(DeviceCostants.ACTIVE);

						// render the activation status
						if ((active != null) && (!active.isEmpty()))
						{
							if (active.equals("true"))
							{
								htmlOut.append("<span class=\"label label-success pull-right\">Active</span></p>");
								htmlOut.append("<ul style=\"list-style-type: none\"><li>"
										+ this.getDeviceState(currentDevice)
										+ "</li><li>"
										+ this.getCommands(currentDevice)
										+ "</li></ul>");
							} else
							{
								htmlOut.append("<span class=\"label label-warning pull-right\">Not Active</span>");
							}
						}

						// close the device-related list item
						htmlOut.append("</div></div>");
					}
				}

				// close orphan divs
				if ((allDevices.length % DeviceMonitor.devicesPerRow) > 0)
					htmlOut.append("</div>");
			}
		} catch (InvalidSyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		htmlOut.append("</ul>\n");

		return htmlOut.toString();
	}

	@Override
	@GET
	@Path("/devices/statistics")
	@Produces(MediaType.TEXT_HTML)
	public String getOverallStatistics()
	{
		// the output buffer for storing incrementally built html
		StringBuffer htmlOut = new StringBuffer();

		// device counts
		int nDevices = 0;
		int nActiveDevices = 0;
		try
		{
			ServiceReference<?>[] allDevices = this.context
					.getAllServiceReferences(Device.class.getName(), null);

			if (allDevices != null)
			{
				for (int i = 0; i < allDevices.length; i++)
				{
					Object device = this.context.getService(allDevices[i]);
					if (device instanceof ControllableDevice)
					{
						// get the device activation status
						String active = (String) allDevices[i]
								.getProperty(DeviceCostants.ACTIVE);

						// parse the boolean value hold by the active string
						boolean isDeviceActive = Boolean.valueOf(active);

						// updated device counts
						nDevices++;
						if (isDeviceActive)
							nActiveDevices++;
					}
				}
			}
		} catch (InvalidSyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// the ratio
		double activeRatio = (double) nActiveDevices / (double) nDevices;

		// compute the label color
		String color = "";
		if (activeRatio < 0.33)
			color = "label-important";
		else if (activeRatio > 0.66)
			color = "label-success";
		else
			color = "label-warning";

		htmlOut.append("<span class=\"label pull-right " + color + "\">"
				+ nActiveDevices + " Active / " + (nDevices - nActiveDevices)
				+ " Not Active</span>");

		// return the statistics
		return htmlOut.toString();
	}

	private String getDeviceState(ControllableDevice currentDevice)
	{
		StringBuffer stateAsString = new StringBuffer();
		DeviceStatus state = ((Controllable) currentDevice).getState();

		// check not null
		if (state != null)
		{
			Map<String, State> allStates = state.getStates();

			// iterate over all states
			for (String stateKey : allStates.keySet())
			{
				// get the current state
				State currentState = allStates.get(stateKey);

				// get the values associate to the current state
				StateValue currentStateValues[] = currentState
						.getCurrentStateValue();

				// iterate over the values
				stateAsString.append("<p>" + currentState.getStateName() + " ");
				for (int i = 0; i < currentStateValues.length; i++)
				{
					stateAsString
							.append("<span class=\"label label-info pull-right\">"
									+ currentStateValues[i].getValue()
									+ "</span>");
				}
				stateAsString.append("<p/>");
			}
		}
		return stateAsString.toString();
	}

	private String getCommands(ControllableDevice device)
	{
		// prepare the string buffer for holding the HTML string representation
		// of the device commands
		StringBuffer commandsAsHTMLString = new StringBuffer();

		Method availableCommands[] = device.getClass().getDeclaredMethods();

		for (int i = 0; i < availableCommands.length; i++)
		{
			String commandName = availableCommands[i].getName();

			if ((!commandName.contains("get"))&&(!commandName.contains("notify")))
			{
				if (availableCommands[i].getParameterTypes().length == 0)
				{
					// render the command
					commandsAsHTMLString
							.append("<input type=\"button\" class=\"btn\" command=\"true\" command-dst=\"/services/devices/"
									+ device.getDeviceId()
									+ "/commands/"
									+ commandName +"\" value=\""+commandName+"\"/>");
				}
			}
		}

		// returns the string representation
		return commandsAsHTMLString.toString();
	}
}
