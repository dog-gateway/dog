/*
 * Dog - Device Rest Endpoint
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
package it.polito.elite.dog.communication.rest.device;

import java.util.HashMap;
import java.util.Map;

import it.polito.elite.dog.communication.rest.device.api.DeviceRESTApi;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.Controllable;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.StateValue;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.util.Executor;
import it.polito.elite.dog.core.library.util.LogHelper;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.device.Device;
import org.osgi.service.log.LogService;

/**
 * @author bonino
 * 
 */
@Path("/devices/")
public class DeviceRESTEndpoint implements DeviceRESTApi
{
	// the service logger
	private LogHelper logger;

	// the log id
	public static final String logId = "[DeviceRESTEndpoint]: ";

	// the bundle context reference to extract information on the entire Dog
	// status
	private BundleContext context;

	/**
	 * Empty constructor
	 */
	public DeviceRESTEndpoint()
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
		this.logger.log(LogService.LOG_INFO, DeviceRESTEndpoint.logId
				+ "Activated....");
	}

	/**
	 * Prepare the bundle to be deactivated...
	 */
	public void deactivate()
	{
		// null the context
		this.context = null;

		// log deactivation
		this.logger.log(LogService.LOG_INFO, DeviceRESTEndpoint.logId
				+ "Deactivated...");

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
	@Path("/status")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllDeviceStatus()
	{
		// the response
		String responseAsString = "";

		// The JSON Object to return
		JSONObject responseBody = new JSONObject();

		// The JSONArray containing the single device renderings as JSON
		JSONArray devices = new JSONArray();

		// get all the installed device services
		try
		{
			// get the device service references
			ServiceReference<?>[] allDevices = this.context
					.getAllServiceReferences(Device.class.getName(), null);

			// check not null
			if (allDevices != null)
			{
				// iterate over all devices
				for (int i = 0; i < allDevices.length; i++)
				{
					// get the OSGi service pointed by the current device
					// reference
					Object device = this.context.getService(allDevices[i]);

					// check if the service belongs to the set of dog devices
					if (device instanceof ControllableDevice)
					{
						// prepare the device JSON entry
						JSONObject deviceJSON = new JSONObject();

						// get the device instance
						ControllableDevice currentDevice = (ControllableDevice) device;

						// get the device descriptor
						DeviceDescriptor deviceDescriptor = currentDevice
								.getDeviceDescriptor();

						// save device data
						deviceJSON.put("uri", deviceDescriptor.getDevURI());
						deviceJSON.put("description",
								deviceDescriptor.getDevDescription());
						deviceJSON.put("active", Boolean
								.valueOf((String) allDevices[i]
										.getProperty(DeviceCostants.ACTIVE)));

						// get the device status
						DeviceStatus state = ((Controllable) currentDevice)
								.getState();

						// check if the device state is available, i.e., not
						// null
						if (state != null)
						{
							// prepare the device status array
							JSONArray deviceStatusAsJSON = new JSONArray();

							// get the states composing the overall device
							// status
							Map<String, State> allStates = state.getStates();

							// iterate over all states
							for (String stateKey : allStates.keySet())
							{
								// get the current state
								State currentState = allStates.get(stateKey);

								// get the values associate to the current state
								StateValue currentStateValues[] = currentState
										.getCurrentStateValue();

								// prepare the state holding JSON object
								JSONObject deviceStateAsJSON = new JSONObject();

								// change behavior depending on single vs
								// multiple states
								if (currentStateValues.length > 1)
								{
									// populate the state values
									JSONArray valuesAsJSON = new JSONArray();

									for (int j = 0; j < currentStateValues.length; j++)
									{
										// create the JSONObject to store the
										// current state value
										JSONObject valueAsJSON = new JSONObject();

										// get state value features
										HashMap<String, Object> features = currentStateValues[j]
												.getFeatures();

										// iterate over the features
										for (String featureKey : features
												.keySet())
										{
											// filter out anything containing
											// "value" inside
											// dirt, should be improved
											if (!featureKey.contains("Value"))
												valueAsJSON
														.put(featureKey,
																features.get(featureKey));
										}

										// store the value
										valueAsJSON.put("value",
												currentStateValues[j]
														.getValue());

										// add the current state value to the
										// corresponding state
										valuesAsJSON.put(valueAsJSON);
									}

									// store the current state
									deviceStateAsJSON.put(
											currentState.getStateName(),
											valuesAsJSON);

								} else
								{
									// store the current state
									deviceStateAsJSON.put(
											currentState.getStateName(),
											currentStateValues[0].getValue());

								}
								// add it to the overall device status
								deviceStatusAsJSON.put(deviceStateAsJSON);
							}

							deviceJSON.put("status", deviceStatusAsJSON);
						}

						// add the current device to the list of devices
						devices.put(deviceJSON);
					}

				}

				responseBody.put("devices", devices);
			}

			responseAsString = responseBody.toString(4);
		} catch (InvalidSyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e)
		{
			this.logger.log(LogService.LOG_ERROR, DeviceRESTEndpoint.logId
					+ "Error while composing the response");
		}

		return responseAsString;
	}

	@Override
	@GET
	@Path("{device-id}/commands/{command-name}")
	public String executeCommandGet(@PathParam("device-id") String deviceId, @PathParam("command-name") String commandName)
	{
		this.executeCommand(deviceId, commandName, null);
		return "Ok";
	}
	
	@Override
	@POST
	@Path("{device-id}/commands/{command-name}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void executeCommandPost(@PathParam("device-id") String deviceId, @PathParam("command-name") String commandName,
			String commandParameters)
	{
		this.executeCommand(deviceId, commandName, commandParameters);
	}
	
	@Override
	@PUT
	@Path("{device-id}/commands/{command-name}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void executeCommandPut(@PathParam("device-id") String deviceId, @PathParam("command-name") String commandName,
			String commandParameters)
	{
		this.executeCommand(deviceId, commandName, commandParameters);
	}
	
	/**
	 * 
	 * @param deviceId
	 * @param commandName
	 * @param commandParameters
	 */
	private void executeCommand(String deviceId, String commandName,
			String commandParameters)
	{
		// get the executor instance
		Executor executor = Executor.getInstance();

		// check if a post/put body is given and convert it into an array of
		// parameters
		// TODO: check if commands can have more than 1 parameter
		if (commandParameters != null)
		{
			Object param;
			try
			{
				//convert the received JSON String to a JSON object
				JSONObject commandParametersJSON = new JSONObject(commandParameters);
				
				//extract the parameter value./
				param = commandParametersJSON.get("value");

				// exec the command
				executor.execute(context, deviceId, commandName,
						new Object[] { param });

			} catch (JSONException e)
			{
				//log the error 
				this.logger.log(LogService.LOG_WARNING, "Error while parsing the command parameters", e);
			}
		} 
		else
		{
			// exec the command
			executor.execute(context, deviceId, commandName, new Object[] {});
		}

	}
}
