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

import it.polito.elite.dog.communication.rest.device.api.DeviceRESTApi;
import it.polito.elite.dog.communication.rest.device.command.ClimateSchedulePayload;
import it.polito.elite.dog.communication.rest.device.command.DailyClimateSchedulePayload;
import it.polito.elite.dog.communication.rest.device.command.DoublePayload;
import it.polito.elite.dog.communication.rest.device.command.MeasurePayload;
import it.polito.elite.dog.communication.rest.device.command.Payload;
import it.polito.elite.dog.communication.rest.device.status.AllDeviceStatesResponsePayload;
import it.polito.elite.dog.communication.rest.device.status.DeviceStateResponsePayload;
import it.polito.elite.dog.core.housemodel.api.HouseModel;
import it.polito.elite.dog.core.library.jaxb.Configcommand;
import it.polito.elite.dog.core.library.jaxb.Confignotification;
import it.polito.elite.dog.core.library.jaxb.Configparam;
import it.polito.elite.dog.core.library.jaxb.ControlFunctionality;
import it.polito.elite.dog.core.library.jaxb.Controllables;
import it.polito.elite.dog.core.library.jaxb.Device;
import it.polito.elite.dog.core.library.jaxb.DogHomeConfiguration;
import it.polito.elite.dog.core.library.jaxb.NotificationFunctionality;
import it.polito.elite.dog.core.library.jaxb.ObjectFactory;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.Controllable;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.StateValue;
import it.polito.elite.dog.core.library.util.Executor;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

import javax.measure.Measure;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;

/**
 * @author bonino
 * 
 */
@Path("/api/devices/")
public class DeviceRESTEndpoint implements DeviceRESTApi
{
	// the service logger
	private LogHelper logger;

	// the log id
	public static final String logId = "[DeviceRESTEndpoint]: ";

	// the bundle context reference to extract information on the entire Dog
	// status
	private BundleContext context;

	// reference for the HouseModel
	private AtomicReference<HouseModel> houseModel;

	// registered payloads
	private Vector<Class<? extends Payload<?>>> payloads;

	// the instance-level mapper
	private ObjectMapper mapper;

	/**
	 * Empty constructor
	 */
	public DeviceRESTEndpoint()
	{
		// init the house model atomic ref
		this.houseModel = new AtomicReference<HouseModel>();

		// init the set of allowed payloads
		this.payloads = new Vector<Class<? extends Payload<?>>>();
		this.payloads.add(ClimateSchedulePayload.class);
		this.payloads.add(DailyClimateSchedulePayload.class);
		// it is really mandatory that double payload precedes measure payload
		// to avoid matching pure doubles to measures with no unit.
		this.payloads.add(DoublePayload.class);
		this.payloads.add(MeasurePayload.class);

		// initialize the instance-wide object mapper
		this.mapper = new ObjectMapper();
		// set the mapper pretty printing
		this.mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
		this.mapper.enable(SerializationConfig.Feature.SORT_PROPERTIES_ALPHABETICALLY);

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

	/**
	 * Bind the HouseModel service (before the bundle activation)
	 * 
	 * @param houseModel
	 *            the HouseModel service to add
	 */
	public void addedHouseModel(HouseModel houseModel)
	{
		// store a reference to the HouseModel service
		this.houseModel.set(houseModel);
	}

	/**
	 * Unbind the HouseModel service
	 * 
	 * @param houseModel
	 *            the HouseModel service to remove
	 */
	public void removedHouseModel(HouseModel houseModel)
	{
		this.houseModel.compareAndSet(houseModel, null);
	}

	@Override
	public String getAllDevices()
	{
		String devicesXML = "";

		// check if the HouseModel service is available
		if (this.houseModel.get() != null)
		{
			// create a JAXB Object Factory for adding the proper header...
			ObjectFactory factory = new ObjectFactory();
			DogHomeConfiguration dhc = factory.createDogHomeConfiguration();

			// get all the devices from the HouseModel
			Controllables controllables = this.houseModel.get()
					.getJAXBDevices().get(0);

			for (Device device : controllables.getDevice())
			{
				// create the JAXB representation to be sent to external
				// applications: it removes all the network-specific
				// parameters
				this.cleanJaxbDevice(device);
			}

			dhc.getControllables().add(controllables);

			// create the XML for replying the request
			devicesXML = this.generateXML(dhc);
		}

		return devicesXML;
	}

	@Override
	public String getDevice(String deviceId)
	{
		String deviceXML = "";

		// check if the HouseModel service is available
		if (this.houseModel.get() != null)
		{
			// create a JAXB Object Factory for adding the proper header...
			ObjectFactory factory = new ObjectFactory();
			DogHomeConfiguration dhc = factory.createDogHomeConfiguration();
			Controllables controllables = factory.createControllables();

			// get the desired device from the HouseModel service
			for (Device device : this.houseModel.get().getJAXBDevices().get(0)
					.getDevice())
			{
				if (device.getId().equalsIgnoreCase(deviceId))
				{
					// create the JAXB representation to be sent to external
					// applications: it removes all the network-specific
					// parameters
					this.cleanJaxbDevice(device);

					// add the device to its container
					controllables.getDevice().add(device);
				}
			}

			dhc.getControllables().add(controllables);

			// create the XML for replying the request
			deviceXML = this.generateXML(dhc);
		}

		return deviceXML;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.dog.communication.rest.device.api.DeviceRESTApi#
	 * getAllDeviceStatus()
	 */
	@Override
	@GET
	@Path("/status")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllDeviceStatus()
	{
		// the response
		String responseAsString = "";

		// get all the installed device services
		try
		{
			// get the device service references
			ServiceReference<?>[] allDevices = this.context
					.getAllServiceReferences(
							org.osgi.service.device.Device.class.getName(),
							null);

			// check not null
			if (allDevices != null)
			{
				// create an AllDeviceStatesResponsePayload
				AllDeviceStatesResponsePayload responsePayload = new AllDeviceStatesResponsePayload();

				// create an array of DeviceStateResponsePayloads
				DeviceStateResponsePayload[] deviceStateResponsePayload = new DeviceStateResponsePayload[allDevices.length];

				// set the array as part of the response payload
				responsePayload.setDevices(deviceStateResponsePayload);

				// iterate over all devices
				for (int i = 0; i < allDevices.length; i++)
				{
					// get the OSGi service pointed by the current device
					// reference
					Object device = this.context.getService(allDevices[i]);

					// check if the service belongs to the set of dog devices
					if (device instanceof ControllableDevice)
					{
						// get the device instance
						ControllableDevice currentDevice = (ControllableDevice) device;

						// get the device descriptor
						DeviceDescriptor deviceDescriptor = currentDevice
								.getDeviceDescriptor();

						// create the response payload
						deviceStateResponsePayload[i] = new DeviceStateResponsePayload();

						// set the device id
						deviceStateResponsePayload[i].setId(deviceDescriptor
								.getDeviceURI());

						// get the device description
						String deviceDescription = deviceDescriptor
								.getDescription();

						// clean the description
						deviceDescription = deviceDescription.trim();
						deviceDescription = deviceDescription.replaceAll("\t",
								"");
						deviceDescription = deviceDescription.replaceAll("\n",
								" ");

						// set the description
						deviceStateResponsePayload[i]
								.setDescription(deviceDescription);

						// set the activation status of the device
						deviceStateResponsePayload[i].setActive(Boolean
								.valueOf((String) allDevices[i]
										.getProperty(DeviceCostants.ACTIVE)));

						// get the device status
						DeviceStatus state = ((Controllable) currentDevice)
								.getState();

						// check if the device state is available, i.e., not
						// null
						if (state != null)
						{
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

								// create the response-level state values
								Object responseBodyStateValues[] = new Object[currentStateValues.length];

								// iterate over the state values
								for (int j = 0; j < currentStateValues.length; j++)
								{
									// get state value features
									HashMap<String, Object> features = currentStateValues[j]
											.getFeatures();

									// prepare the map to store in the response
									// body
									HashMap<String, Object> responseBodyFeatures = new HashMap<>();

									// iterate over the features
									for (String featureKey : features.keySet())
									{
										// check the "value" feature and, if it
										// is an instance of measure, serialize
										// it as a String
										if (featureKey.contains("Value"))
										{
											if (features.get(featureKey) instanceof Measure<?, ?>)
												responseBodyFeatures
														.put("value",
																features.get(
																		featureKey)
																		.toString());
											else
												responseBodyFeatures
														.put("value",
																features.get(featureKey));

										} else
										{
											Object value = features
													.get(featureKey);

											if ((!(value instanceof String))
													|| ((value instanceof String) && (!((String) value)
															.isEmpty())))
												responseBodyFeatures
														.put(featureKey,
																features.get(featureKey));
										}

									}

									// store the current state value
									responseBodyStateValues[j] = responseBodyFeatures;
								}

								// store the state
								deviceStateResponsePayload[i].getStatus()
										.put(currentState.getClass()
												.getSimpleName(),
												responseBodyStateValues);

							}

						}
					}
				}
				// store the device
				responsePayload.setDevices(deviceStateResponsePayload);

				// convert the response body to json
				responseAsString = this.mapper
						.writeValueAsString(responsePayload);
			}

		} catch (Exception e)
		{
			this.logger.log(LogService.LOG_ERROR, DeviceRESTEndpoint.logId
					+ "Error while composing the response", e);
		}

		return responseAsString;
	}

	@Override
	@GET
	@Path("{device-id}/commands/{command-name}")
	public String executeCommandGet(@PathParam("device-id") String deviceId,
			@PathParam("command-name") String commandName)
	{
		this.executeCommand(deviceId, commandName, null);
		return "Ok";
	}

	@Override
	@POST
	@Path("{device-id}/commands/{command-name}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void executeCommandPost(@PathParam("device-id") String deviceId,
			@PathParam("command-name") String commandName,
			String commandParameters)
	{
		this.executeCommand(deviceId, commandName, commandParameters);
	}

	@Override
	@PUT
	@Path("{device-id}/commands/{command-name}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void executeCommandPut(@PathParam("device-id") String deviceId,
			@PathParam("command-name") String commandName,
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

		// ------------- Use Jackson to interpret the type of data passed as
		// value ---------

		// check if a post/put body is given and convert it into an array of
		// parameters
		// TODO: check if commands can have more than 1 parameter
		if ((commandParameters != null) && (!commandParameters.isEmpty()))
		{
			// try to read the payload
			for (int i = 0; i < this.payloads.size(); i++)
			{
				try
				{
					// try to read the value
					Payload<?> payload = this.mapper.readValue(
							commandParameters, this.payloads.get(i));

					// if payload !=null
					executor.execute(context, deviceId, commandName,
							new Object[] { payload.getValue() });

					break;
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					// do nothing and proceed to the next trial
					// e.printStackTrace();
				}
			}

		} else
		{
			// exec the command
			executor.execute(context, deviceId, commandName, new Object[] {});
		}

	}

	/**
	 * Prepare the JAXB Device to contain the proper information for external
	 * applications. It removes all the network-related properties...
	 * 
	 * @param device
	 *            the "full" JAXB Device to clean
	 */
	private void cleanJaxbDevice(Device device)
	{
		// store the parameters to be removed from the current device
		Vector<Configparam> paramsToRemove = new Vector<Configparam>();

		// remove all the "first-level" params, since they are network-related
		device.getParam().clear();

		// get all the control functionalites...
		List<ControlFunctionality> controlFunctionalities = device
				.getControlFunctionality();
		for (ControlFunctionality controlFunctionality : controlFunctionalities)
		{
			// get all the commands
			for (Configcommand command : controlFunctionality.getCommands()
					.getCommand())
			{
				for (Configparam param : command.getParam())
				{
					// get all the command parameters to remove
					// (network-related), i.e., preserve only the
					// "realCommandName" prop
					if (!param.getName().equalsIgnoreCase("realCommandName"))
					{
						paramsToRemove.add(param);
					}
				}
				// effectively remove the parameters
				for (Configparam param : paramsToRemove)
				{
					command.getParam().remove(param);
				}
				paramsToRemove.clear();
			}
		}

		// get all the notification functionalities...
		List<NotificationFunctionality> notificationsFunctionalities = device
				.getNotificationFunctionality();
		for (NotificationFunctionality notificationFunctionality : notificationsFunctionalities)
		{
			// get all the notifications...
			for (Confignotification notification : notificationFunctionality
					.getNotifications().getNotification())
			{
				for (Configparam param : notification.getParam())
				{
					// get all the notification parameters to remove
					// (network-related), i.e., preserve only the
					// "notificationName" and "notificationParamName" props
					if ((!param.getName().equalsIgnoreCase("notificationName"))
							&& (!param.getName().equalsIgnoreCase(
									"notificationParamName")))
					{
						paramsToRemove.add(param);
					}
				}
				// effectively remove the parameters
				for (Configparam param : paramsToRemove)
				{
					notification.getParam().remove(param);
				}
				paramsToRemove.clear();
			}
		}

	}

	/**
	 * Generate the XML to be sent
	 * 
	 * @param dhc
	 *            the {@link DogHomeConfiguration} object to marshall
	 * @return the corresponding XML
	 */
	private String generateXML(DogHomeConfiguration dhc)
	{
		String devicesXML = "";

		try
		{
			StringWriter output = new StringWriter();

			// marshall the DogHomeConfiguration...
			JAXB.marshal(dhc, output);

			devicesXML = output.getBuffer().toString();
		} catch (DataBindingException e)
		{
			// the exception can be throw by the JAXB.marshal method...
			this.logger.log(LogService.LOG_ERROR,
					"Exception in JAXB Marshalling...", e);
		}

		return devicesXML;
	}

}