/*
 * Dog - Device Rest Endpoint
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
package it.polito.elite.dog.communication.rest.device;

import it.polito.elite.dog.communication.rest.device.api.DeviceRESTApi;
import it.polito.elite.dog.communication.rest.device.command.ClimateSchedulePayload;
import it.polito.elite.dog.communication.rest.device.command.CommandPayload;
import it.polito.elite.dog.communication.rest.device.command.DailyClimateSchedulePayload;
import it.polito.elite.dog.communication.rest.device.command.DoublePayload;
import it.polito.elite.dog.communication.rest.device.command.MeasurePayload;
import it.polito.elite.dog.communication.rest.device.status.AllDeviceStatesResponsePayload;
import it.polito.elite.dog.communication.rest.device.status.DeviceStateResponsePayload;
import it.polito.elite.dog.core.devicefactory.api.DeviceFactory;
import it.polito.elite.dog.core.housemodel.api.HouseModel;
import it.polito.elite.dog.core.library.jaxb.Controllables;
import it.polito.elite.dog.core.library.jaxb.Device;
import it.polito.elite.dog.core.library.jaxb.DogHomeConfiguration;
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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.device.Constants;
import org.osgi.service.log.LogService;

/**
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
@Path("/api/v1/devices/")
public class DeviceRESTEndpoint implements DeviceRESTApi
{
	// the service logger
	private LogHelper logger;
	
	// the bundle context reference to extract information on the entire Dog
	// status
	private BundleContext context;
	
	// reference for the HouseModel
	private AtomicReference<HouseModel> houseModel;
	
	// reference for the DeviceFactory
	private AtomicReference<DeviceFactory> deviceFactory;
	
	// registered payloads
	private Vector<Class<? extends CommandPayload<?>>> payloads;
	
	// the instance-level mapper
	private ObjectMapper mapper;
	
	// the JAXB context
	private JAXBContext jaxbContext;
	
	/**
	 * Constructor
	 */
	public DeviceRESTEndpoint()
	{
		// init the house model atomic reference
		this.houseModel = new AtomicReference<HouseModel>();
		
		// init the device factory atomic reference
		this.deviceFactory = new AtomicReference<DeviceFactory>();
		
		// init JAXB Context
		try
		{
			this.jaxbContext = JAXBContext.newInstance(DogHomeConfiguration.class.getPackage().getName());
		}
		catch (JAXBException e)
		{
			this.logger.log(LogService.LOG_ERROR, "JAXB Init Error", e);
		}
		
		// init the set of allowed payloads
		this.payloads = new Vector<Class<? extends CommandPayload<?>>>();
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
		// avoid empty arrays and null values
		this.mapper.configure(SerializationConfig.Feature.WRITE_EMPTY_JSON_ARRAYS, false);
		this.mapper.setSerializationInclusion(Inclusion.NON_NULL);
		
		// create an introspector for parsing both Jackson and JAXB annotations
		AnnotationIntrospector jackson = new JacksonAnnotationIntrospector();
		AnnotationIntrospector jaxb = new JaxbAnnotationIntrospector();
		AnnotationIntrospector fullIntrospector = new AnnotationIntrospector.Pair(jackson, jaxb);
		// make deserializer use both Jackson and JAXB annotations
		this.mapper.getDeserializationConfig().withAnnotationIntrospector(fullIntrospector);
		// make serializer use both Jackson and JAXB annotations
		this.mapper.getSerializationConfig().withAnnotationIntrospector(fullIntrospector);
		
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
	
	/**
	 * Bind the DeviceFactory service (before the bundle activation)
	 * 
	 * @param deviceFactory
	 *            the DeviceFactory service to add
	 */
	public void addedDeviceFactory(DeviceFactory deviceFactory)
	{
		// store a reference to the HouseModel service
		this.deviceFactory.set(deviceFactory);
	}
	
	/**
	 * Unbind the DeviceFactory service
	 * 
	 * @param deviceFactory
	 *            the DeviceFactory service to remove
	 */
	public void removedDeviceFactory(DeviceFactory deviceFactory)
	{
		this.deviceFactory.compareAndSet(deviceFactory, null);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.dog.communication.rest.device.api.DeviceRESTApi#
	 * getAllDevicesInJson()
	 */
	@Override
	public String getAllDevicesInJson()
	{
		String devicesJSON = "";
		
		// get the JAXB object containing all the configured devices
		DogHomeConfiguration dhc = this.getAllDevices();
		
		try
		{
			devicesJSON = this.mapper.writeValueAsString(dhc.getControllables().get(0));
		}
		catch (Exception e)
		{
			this.logger.log(LogService.LOG_ERROR, "Error in creating the JSON representing all the configured devices",
					e);
		}
		
		return devicesJSON;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.dog.communication.rest.device.api.DeviceRESTApi#
	 * getAllDevicesInXml ()
	 */
	@Override
	public String getAllDevicesInXml()
	{
		String devicesXML = "";
		
		// get the JAXB object containing all the configured devices
		DogHomeConfiguration dhc = this.getAllDevices();
		
		// create the XML for replying the request
		devicesXML = this.generateXML(dhc);
		
		return devicesXML;
	}
	
	/**
	 * Get all the devices configured in Dog from the {@link HouseModel} and
	 * perform some "cleaning" operations, such as removing all the
	 * network-related information and removing unneeded tabs or newlines
	 * 
	 * @return a {@link DogHomeConfiguration} object with all the devices
	 *         information
	 */
	private DogHomeConfiguration getAllDevices()
	{
		// create a JAXB Object Factory for adding the proper header...
		ObjectFactory factory = new ObjectFactory();
		DogHomeConfiguration dhc = factory.createDogHomeConfiguration();
		
		// check if the HouseModel service is available
		if (this.houseModel.get() != null)
		{
			// get all the devices from the HouseModel
			Controllables controllables = this.houseModel.get().getSimpleDevices().get(0);
			
			dhc.getControllables().add(controllables);
		}
		
		return dhc;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.dog.communication.rest.device.api.DeviceRESTApi#
	 * getDeviceInJson(java.lang.String)
	 */
	@Override
	public String getDeviceInJson(String deviceId)
	{
		String deviceJSON = "";
		
		// get the requested device configuration, in JAXB
		DogHomeConfiguration dhc = this.getDevice(deviceId);
		
		if (dhc.getControllables().get(0).getDevice() != null)
		{
			// get the JAXB representation of the desired device
			Device requestedDevice = dhc.getControllables().get(0).getDevice().get(0);
			
			try
			{
				deviceJSON = this.mapper.writeValueAsString(requestedDevice);
			}
			catch (Exception e)
			{
				this.logger.log(LogService.LOG_ERROR,
						"Error in creating the JSON representing all the configured devices", e);
			}
		}
		
		return deviceJSON;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.dog.communication.rest.device.api.DeviceRESTApi#
	 * getDeviceInXml (java.lang.String)
	 */
	@Override
	public String getDeviceInXml(String deviceId)
	{
		String deviceXML = "";
		
		// get the requested device configuration
		DogHomeConfiguration dhc = this.getDevice(deviceId);
		
		// create the XML for replying the request
		deviceXML = this.generateXML(dhc);
		
		return deviceXML;
	}
	
	/**
	 * 
	 * Get the configuration of the device identified by the parameter deviceId
	 * from the {@link HouseModel} and perform some "cleaning" operations, such
	 * as removing all the network-related information and removing unneeded
	 * tabs or newlines
	 * 
	 * @param deviceId
	 *            the device unique identifier
	 * @return a {@link DogHomeConfiguration} object with the required device
	 *         information
	 */
	private DogHomeConfiguration getDevice(String deviceId)
	{
		ObjectFactory factory = new ObjectFactory();
		DogHomeConfiguration dhc = factory.createDogHomeConfiguration();
		
		// check if the HouseModel service is available
		if (this.houseModel.get() != null)
		{
			// create a JAXB Object Factory for adding the proper header...
			
			Controllables controllables = factory.createControllables();
			
			// get the desired device from the HouseModel service
			for (Device device : this.houseModel.get().getSimpleDevices().get(0).getDevice())
			{
				if (device.getId().equalsIgnoreCase(deviceId))
				{
					// add the device to its container
					controllables.getDevice().add(device);
				}
			}
			
			dhc.getControllables().add(controllables);
		}
		return dhc;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.dog.communication.rest.device.api.DeviceRESTApi#
	 * updateDeviceLocation(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateDeviceLocation(String deviceId, String location)
	{
		if (location != null && !location.isEmpty())
		{
			// create filter for getting the desired device
			String deviceFilter = String.format("(&(%s=*)(%s=%s))", Constants.DEVICE_CATEGORY,
					DeviceCostants.DEVICEURI, deviceId);
			
			try
			{
				// try to read the value from the JSON
				Device deviceLocation = this.mapper.readValue(location, Device.class);
				
				// get the device service references
				ServiceReference<?>[] deviceService = this.context.getAllServiceReferences(
						org.osgi.service.device.Device.class.getName(), deviceFilter);
				
				// only one device with the given deviceId can exists in the
				// framework...
				if (deviceService != null && deviceService.length == 1)
				{
					// get the OSGi service pointed by the current device
					// reference
					Object device = this.context.getService(deviceService[0]);
					
					if ((device != null) && (device instanceof ControllableDevice))
					{
						// get the device instance
						ControllableDevice currentDevice = (ControllableDevice) device;
						// get the associated device descriptor
						DeviceDescriptor currentDeviceDescr = currentDevice.getDeviceDescriptor();
						
						// update the device location, if available
						if ((deviceLocation.getIsIn() != null) && (!deviceLocation.getIsIn().isEmpty()))
						{
							currentDeviceDescr.setLocation(deviceLocation.getIsIn());
							
							// check if the DeviceFactory service is available
							if (this.deviceFactory.get() != null)
							{
								// update the device configuration
								this.deviceFactory.get().updateDevice(currentDeviceDescr);
							}
							else
							{
								this.logger
										.log(LogService.LOG_WARNING,
												"Impossible to update the device location: the Device Factory is not available!");
							}
						}
					}
				}
			}
			catch (Exception e)
			{
				this.logger.log(LogService.LOG_ERROR, "Error in updating the location of device " + deviceId, e);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.dog.communication.rest.device.api.DeviceRESTApi#
	 * updateDeviceDescription(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateDeviceDescription(String deviceId, String description)
	{
		if (description != null && !description.isEmpty())
		{
			// create filter for getting the desired device
			String deviceFilter = String.format("(&(%s=*)(%s=%s))", Constants.DEVICE_CATEGORY,
					DeviceCostants.DEVICEURI, deviceId);
			
			try
			{
				// try to read the value from the JSON
				Device deviceDescription = this.mapper.readValue(description, Device.class);
				
				// get the device service references
				ServiceReference<?>[] deviceService = this.context.getAllServiceReferences(
						org.osgi.service.device.Device.class.getName(), deviceFilter);
				
				// only one device with the given deviceId can exists in the
				// framework...
				if (deviceService != null && deviceService.length == 1)
				{
					// get the OSGi service pointed by the current device
					// reference
					Object device = this.context.getService(deviceService[0]);
					
					if ((device != null) && (device instanceof ControllableDevice))
					{
						// get the device instance
						ControllableDevice currentDevice = (ControllableDevice) device;
						// get the associated device descriptor
						DeviceDescriptor currentDeviceDescr = currentDevice.getDeviceDescriptor();
						
						// update the device description, if available
						if ((deviceDescription.getDescription() != null)
								&& (!deviceDescription.getDescription().isEmpty()))
						{
							currentDeviceDescr.setDescription(deviceDescription.getDescription());
							
							// check if the DeviceFactory service is available
							if (this.deviceFactory.get() != null)
							{
								// update the device configuration
								this.deviceFactory.get().updateDevice(currentDeviceDescr);
							}
							else
							{
								this.logger
										.log(LogService.LOG_WARNING,
												"Impossible to update the device description: the Device Factory is not available!");
							}
						}
					}
				}
			}
			catch (Exception e)
			{
				this.logger.log(LogService.LOG_ERROR, "Error in updating the description of device " + deviceId, e);
			}
		}
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
			ServiceReference<?>[] allDevices = this.context.getAllServiceReferences(
					org.osgi.service.device.Device.class.getName(), null);
			
			// check not null
			if (allDevices != null)
			{
				// create an AllDeviceStatesResponsePayload
				AllDeviceStatesResponsePayload responsePayload = new AllDeviceStatesResponsePayload();
				
				// create an array of DeviceStateResponsePayloads
				DeviceStateResponsePayload[] deviceStateResponsePayload = new DeviceStateResponsePayload[allDevices.length];
				
				// set the array as part of the response payload
				responsePayload.setDevicesStatus(deviceStateResponsePayload);
				
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
						
						// get the response payload for the current device
						deviceStateResponsePayload[i] = this.getControllableStatus(currentDevice, allDevices[i]);
					}
				}
				// store the device
				responsePayload.setDevicesStatus(deviceStateResponsePayload);
				
				// convert the response body to json
				responseAsString = this.mapper.writeValueAsString(responsePayload);
			}
			
		}
		catch (Exception e)
		{
			this.logger.log(LogService.LOG_ERROR, "Error while composing the response", e);
		}
		
		return responseAsString;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polito.elite.dog.communication.rest.device.api.DeviceRESTApi#
	 * getDeviceStatus(java.lang.String)
	 */
	@Override
	public String getDeviceStatus(String deviceId)
	{
		// the response
		String responseAsString = "";
		
		// create filter for getting the desired device
		String deviceFilter = String.format("(&(%s=*)(%s=%s))", Constants.DEVICE_CATEGORY, DeviceCostants.DEVICEURI,
				deviceId);
		
		try
		{
			// get the device service references
			ServiceReference<?>[] deviceService = this.context.getAllServiceReferences(
					org.osgi.service.device.Device.class.getName(), deviceFilter);
			if (deviceService != null)
			{
				// create a DeviceStateResponsePayload
				DeviceStateResponsePayload deviceStateResponsePayload = new DeviceStateResponsePayload();
				
				// only one device with the given deviceId can exists in the
				// framework
				if (deviceService.length == 1)
				{
					// get the OSGi service pointed by the current device
					// reference
					Object device = this.context.getService(deviceService[0]);
					
					if (device instanceof ControllableDevice)
					{
						// get the device instance
						ControllableDevice currentDevice = (ControllableDevice) device;
						
						// get the response payload
						deviceStateResponsePayload = this.getControllableStatus(currentDevice, deviceService[0]);
					}
					
				}
				
				// convert the response body to json
				responseAsString = this.mapper.writeValueAsString(deviceStateResponsePayload);
			}
		}
		catch (Exception e)
		{
			this.logger
					.log(LogService.LOG_ERROR, "Error while composing the response for the status of " + deviceId, e);
		}
		
		return responseAsString;
	}
	
	/**
	 * Build the Jackson representation for the status of a given
	 * {@link ControllableDevice} object.
	 * 
	 * @param device
	 *            the {@link ControllableDevice} to query for the status
	 * @param deviceService
	 *            the OSGi service reference for the given
	 *            {@link ControllableDevice}
	 * @return a {@link DeviceStateResponsePayload} containing the proper
	 *         response to the status API
	 */
	private DeviceStateResponsePayload getControllableStatus(ControllableDevice device,
			ServiceReference<?> deviceService)
	{
		// init
		DeviceStateResponsePayload deviceStateResponsePayload = null;
		
		// get the device descriptor
		DeviceDescriptor deviceDescriptor = device.getDeviceDescriptor();
		
		// create the response payload
		deviceStateResponsePayload = new DeviceStateResponsePayload();
		
		// set the device id
		deviceStateResponsePayload.setId(deviceDescriptor.getDeviceURI());
		
		// set the activation status of the device
		deviceStateResponsePayload
				.setActive(Boolean.valueOf((String) deviceService.getProperty(DeviceCostants.ACTIVE)));
		
		// get the device status
		Map<String, State> allStates = null;
		DeviceStatus state = ((Controllable) device).getState();
		if (state != null)
		{
			allStates = state.getStates();
		}
		
		// check if the device state is available, i.e., not
		// null
		if (allStates != null)
		{
			// iterate over all states
			for (String stateKey : allStates.keySet())
			{
				// get the current state
				State currentState = allStates.get(stateKey);
				
				// get the values associate to the current state
				StateValue currentStateValues[] = currentState.getCurrentStateValue();
				
				// create the response-level state values
				Object responseBodyStateValues[] = new Object[currentStateValues.length];
				
				// iterate over the state values
				for (int j = 0; j < currentStateValues.length; j++)
				{
					// get state value features
					HashMap<String, Object> features = currentStateValues[j].getFeatures();
					
					// prepare the map to store in the response
					// body
					HashMap<String, Object> responseBodyFeatures = new HashMap<String, Object>();
					
					// iterate over the features
					for (String featureKey : features.keySet())
					{
						// check the "value" feature and, if it
						// is an instance of measure, serialize
						// it as a String
						if (featureKey.contains("Value"))
						{
							if (features.get(featureKey) instanceof Measure<?, ?>)
								responseBodyFeatures.put("value", features.get(featureKey).toString());
							else
								responseBodyFeatures.put("value", features.get(featureKey));
							
						}
						else
						{
							Object value = features.get(featureKey);
							
							if ((!(value instanceof String))
									|| ((value instanceof String) && (!((String) value).isEmpty())))
								responseBodyFeatures.put(featureKey, features.get(featureKey));
						}
						
					}
					
					// store the current state value
					responseBodyStateValues[j] = responseBodyFeatures;
				}
				
				// store the state
				deviceStateResponsePayload.getStatus().put(currentState.getClass().getSimpleName(),
						responseBodyStateValues);
			}
		}
		
		return deviceStateResponsePayload;
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
			@PathParam("command-name") String commandName, String commandParameters)
	{
		this.executeCommand(deviceId, commandName, commandParameters);
	}
	
	@Override
	@PUT
	@Path("{device-id}/commands/{command-name}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void executeCommandPut(@PathParam("device-id") String deviceId,
			@PathParam("command-name") String commandName, String commandParameters)
	{
		this.executeCommand(deviceId, commandName, commandParameters);
	}
	
	/**
	 * 
	 * @param deviceId
	 * @param commandName
	 * @param commandParameters
	 */
	private void executeCommand(String deviceId, String commandName, String commandParameters)
	{
		// get the executor instance
		Executor executor = Executor.getInstance();
		
		// --- Use Jackson to interpret the type of data passed as value ---
		
		// check if a post/put body is given, it is not an empty JSON object,
		// and convert it into an array of parameters
		// TODO: check if commands can have more than 1 parameter
		if ((commandParameters != null) && (!commandParameters.isEmpty()) && (!commandParameters.equals("{}")))
		{
			// try to read the payload
			for (int i = 0; i < this.payloads.size(); i++)
			{
				try
				{
					// try to read the value
					CommandPayload<?> payload = this.mapper.readValue(commandParameters, this.payloads.get(i));
					
					// if payload !=null
					executor.execute(context, deviceId, commandName, new Object[] { payload.getValue() });
					
					break;
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					// do nothing and proceed to the next trial
					// e.printStackTrace();
				}
			}
			
		}
		else
		{
			// exec the command
			executor.execute(context, deviceId, commandName, new Object[] {});
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
		
		if (this.jaxbContext != null)
		{
			try
			{
				StringWriter output = new StringWriter();
				
				// marshall the DogHomeConfiguration...
				Marshaller marshaller = jaxbContext.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				
				marshaller.marshal(dhc, output);
				
				devicesXML = output.getBuffer().toString();
			}
			catch (JAXBException e)
			{
				// the exception can be throw by the JAXB.marshal method...
				this.logger.log(LogService.LOG_ERROR, "Exception in JAXB Marshalling...", e);
			}
		}
		
		return devicesXML;
	}
	
}