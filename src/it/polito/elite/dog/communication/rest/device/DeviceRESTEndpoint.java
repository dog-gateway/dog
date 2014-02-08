/*
 * Dog - Device Rest Endpoint
 * 
 * Copyright (c) 2013 Dario Bonino and Luigi De Russis
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
import it.polito.elite.dog.communication.rest.device.command.CommandPayload;
import it.polito.elite.dog.communication.rest.device.status.AllDeviceStatesResponsePayload;
import it.polito.elite.dog.communication.rest.device.status.DeviceStateResponsePayload;
import it.polito.elite.dog.core.devicefactory.api.DeviceFactory;
import it.polito.elite.dog.core.housemodel.api.HouseModel;
import it.polito.elite.dog.core.library.jaxb.Configcommand;
import it.polito.elite.dog.core.library.jaxb.Confignotification;
import it.polito.elite.dog.core.library.jaxb.Configparam;
import it.polito.elite.dog.core.library.jaxb.Configstate;
import it.polito.elite.dog.core.library.jaxb.ControlFunctionality;
import it.polito.elite.dog.core.library.jaxb.Controllables;
import it.polito.elite.dog.core.library.jaxb.Device;
import it.polito.elite.dog.core.library.jaxb.DogHomeConfiguration;
import it.polito.elite.dog.core.library.jaxb.NotificationFunctionality;
import it.polito.elite.dog.core.library.jaxb.ObjectFactory;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import it.polito.elite.dog.core.library.model.devicecategory.Controllable;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.StateValue;
import it.polito.elite.dog.core.library.util.Executor;
import it.polito.elite.dog.core.library.util.LogHelper;

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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;

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
@Path("/api/devices/")
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
	
	/**
	 * Constructor
	 */
	public DeviceRESTEndpoint()
	{
		// init the house model atomic reference
		this.houseModel = new AtomicReference<HouseModel>();
		
		// init the device factory atomic reference
		this.deviceFactory = new AtomicReference<DeviceFactory>();
		
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
		
		// if there are not Devices in the list we have to
		// send an HTTP response 404 Not found
		List<Controllables> controllablesList = dhc.getControllables();
		boolean nonEmptyList = false;
		for (Controllables singleControllables : controllablesList)
		{
			if (!singleControllables.getDevice().isEmpty())
				nonEmptyList = true;
		}
		if (devicesJSON.isEmpty() || nonEmptyList != true)
		{
			// launch the exception responsible for sending the HTTP response
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		else
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
		
		// if there are not Devices in the list we have to
		// send an HTTP response 404 Not found
		List<Controllables> controllablesList = dhc.getControllables();
		boolean nonEmptyList = false;
		for (Controllables singleControllables : controllablesList)
		{
			if (!singleControllables.getDevice().isEmpty())
				nonEmptyList = true;
		}
		if (devicesXML.isEmpty() || nonEmptyList != true)
		{
			// launch the exception responsible for sending the HTTP response
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		else
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
			Controllables controllables = this.houseModel.get().getJAXBDevices().get(0);
			
			for (Device device : controllables.getDevice())
			{
				// create the JAXB representation to be sent to external
				// applications: it removes all the network-specific
				// parameters
				this.cleanJaxbDevice(device);
			}
			
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
		
		// if there are not Devices in the list we have to
		// send an HTTP response 404 Not found
		List<Controllables> controllablesList = dhc.getControllables();
		boolean nonEmptyList = false;
		for (Controllables singleControllables : controllablesList)
		{
			if (!singleControllables.getDevice().isEmpty())
				nonEmptyList = true;
		}
		if (deviceJSON.isEmpty() || nonEmptyList != true)
		{
			// launch the exception responsible for sending the HTTP response
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		else
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
		
		// if there are not Devices in the list we have to
		// send an HTTP response 404 Not found
		List<Controllables> controllablesList = dhc.getControllables();
		boolean nonEmptyList = false;
		for (Controllables singleControllables : controllablesList)
		{
			if (!singleControllables.getDevice().isEmpty())
				nonEmptyList = true;
		}
		if (deviceXML.isEmpty() || nonEmptyList != true)
		{
			// launch the exception responsible for sending the HTTP response
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		else
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
			for (Device device : this.houseModel.get().getJAXBDevices().get(0).getDevice())
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
		// set and init the variable used to store the HTTP response that will
		// be sent by exception to the client
		Status response = null;
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
								// set the variable used to store the HTTP
								// response by the right value: OK (The device
								// location was successfully updated)
								response = Response.Status.OK;
								
							}
							else
							{
								this.logger
										.log(LogService.LOG_WARNING,
												"Impossible to update the device location: the Device Factory is not available!");
								// set the variable used to store the HTTP
								// response by the right value:
								// PRECONDITION_FAILED (Impossible to update the
								// device location since the Device Factory is
								// not available)
								// it was the best response status available
								response = Response.Status.PRECONDITION_FAILED;
							}
						}
					}
					
					// Releases all the services object referenced at the
					// beginning of the method
					for (ServiceReference<?> singleServiceReference : deviceService)
					{
						this.context.ungetService(singleServiceReference);
					}
				}
			}
			catch (Exception e)
			{
				this.logger.log(LogService.LOG_ERROR, "Error in updating the location of device " + deviceId, e);
				// set the variable used to store the HTTP response by the right
				// value: NOT_MODIFIED (Impossible to update the location of
				// device)
				response = Response.Status.NOT_MODIFIED;
			}
			
			// launch the exception responsible for sending the HTTP response
			throw new WebApplicationException(response);
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
		// set and init the variable used to store the HTTP response that will
		// be sent by exception to the client
		Status response = null;
		
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
								// set the variable used to store the HTTP
								// response by the right value: OK (The
								// description was successfully updated)
								response = Response.Status.OK;
							}
							else
							{
								this.logger
										.log(LogService.LOG_WARNING,
												"Impossible to update the device description: the Device Factory is not available!");
								// set the variable used to store the HTTP
								// response by the right value:
								// PRECONDITION_FAILED (Impossible to update the
								// device description since the Device Factory
								// is not available)
								// it was the best response status available
								response = Response.Status.PRECONDITION_FAILED;
							}
						}
					}
					
					// Releases all the services object referenced at the
					// beginning of the method
					for (ServiceReference<?> singleServiceReference : deviceService)
					{
						this.context.ungetService(singleServiceReference);
					}
				}
			}
			catch (Exception e)
			{
				this.logger.log(LogService.LOG_ERROR, "Error in updating the description of device " + deviceId, e);
				// set the variable used to store the HTTP response by the right
				// value: NOT_MODIFIED (Impossible to update the description of
				// the device)
				// it was the best response status available
				response = Response.Status.NOT_MODIFIED;
			}
			
			// launch the exception responsible for sending the HTTP response
			throw new WebApplicationException(response);
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
		boolean listIsEmpty = true;
		
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
						//if we are here it means that the list will not be empty
						listIsEmpty = false;
					}
				}
				// store the device
				responsePayload.setDevicesStatus(deviceStateResponsePayload);
				
				// convert the response body to json
				responseAsString = this.mapper.writeValueAsString(responsePayload);
				
				// Releases all the services object referenced at the beginning
				// of the method
				for (ServiceReference<?> singleServiceReference : allDevices)
				{
					this.context.ungetService(singleServiceReference);
				}
			}
			
		}
		catch (Exception e)
		{
			this.logger.log(LogService.LOG_ERROR, "Error while composing the response", e);
		}
		
		// if the responseAsString variable is empty we have to send an HTTP
		// response
		// 404 Not found
		if (responseAsString.isEmpty() || listIsEmpty == true)
		{
			// launch the exception responsible for sending the HTTP response
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		else
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
		boolean listIsEmpty = true;
		
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
						//if we are here it means that the list will not be empty
						listIsEmpty = false;
					}
					
				}
				
				// convert the response body to json
				responseAsString = this.mapper.writeValueAsString(deviceStateResponsePayload);
				
				// Releases all the services object referenced at the beginning
				// of the method
				for (ServiceReference<?> singleServiceReference : deviceService)
				{
					this.context.ungetService(singleServiceReference);
				}
			}
		}
		catch (Exception e)
		{
			this.logger
					.log(LogService.LOG_ERROR, "Error while composing the response for the status of " + deviceId, e);
		}
		
		// if the responseAsString variable is empty we have to send an HTTP
		// response
		// 404 Not found
		if (responseAsString.isEmpty() || listIsEmpty == true)
		{
			// launch the exception responsible for sending the HTTP response
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		else
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
		Map<String, State> allStates = ((Controllable) device).getState().getStates();
		
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
		
		// set default value for the variable used to store the HTTP response by
		// the right value: EXPECTATION_FAILED (If something goes wrong we will
		// say to user that the command was not executed successfully)
		// it was the best response status available
		Status response = Response.Status.EXPECTATION_FAILED;
		
		// get the executor instance
		Executor executor = Executor.getInstance();
		
		// --- Use Jackson to interpret the type of data passed as value ---
		
		// check if a post/put body is given, it is not an empty JSON object,
		// and convert it into an array of
		// parameters
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
					// set the variable used to store the HTTP response by the
					// right value: OK (The command was executed without
					// exception)
					response = Response.Status.OK;
					break;
				}
				catch (Exception e)
				{
					// set the variable used to store the HTTP response by the
					// right value: EXPECTATION_FAILED (An exception occured so
					// the command was not executed as expected)
					// it was the best response status available
					response = Response.Status.EXPECTATION_FAILED;
					// then proceed to the next trial
				}
			}
			
		}
		else
		{
			// exec the command
			try
			{
				executor.execute(context, deviceId, commandName, new Object[] {});
				// set the variable used to store the HTTP response by the right
				// value: OK (The command was executed without exception)
				response = Response.Status.OK;
			}
			catch (Exception e)
			{
				// set the variable used to store the HTTP response by the right
				// value: EXPECTATION_FAILED (An exception occured so the
				// command was not executed as expected)
				response = Response.Status.EXPECTATION_FAILED;
			}
		}
		
		// launch the exception responsible for sending the HTTP response
		throw new WebApplicationException(response);
		
	}
	
	/**
	 * Prepare the JAXB Device to contain the proper information for external
	 * applications. It removes all the network-related properties and hides
	 * some redundant arrays for the JSON serialization.
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
		
		// clean the description field
		String description = device.getDescription().trim();
		description = description.replaceAll("\t", "");
		description = description.replaceAll("\n", " ");
		device.setDescription(description);
		
		// get all the control functionalites...
		List<ControlFunctionality> controlFunctionalities = device.getControlFunctionality();
		for (ControlFunctionality controlFunctionality : controlFunctionalities)
		{
			// get all the commands
			for (Configcommand command : controlFunctionality.getCommands().getCommand())
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
			
			// improve JSON rendering by hiding a redundant array
			controlFunctionality.setCommandList(controlFunctionality.getCommands().getCommand());
		}
		
		// get all the notification functionalities...
		List<NotificationFunctionality> notificationsFunctionalities = device.getNotificationFunctionality();
		for (NotificationFunctionality notificationFunctionality : notificationsFunctionalities)
		{
			// get all the notifications...
			for (Confignotification notification : notificationFunctionality.getNotifications().getNotification())
			{
				for (Configparam param : notification.getParam())
				{
					// get all the notification parameters to remove
					// (network-related), i.e., preserve only the
					// "notificationName" and "notificationParamName" props
					if ((!param.getName().equalsIgnoreCase("notificationName"))
							&& (!param.getName().equalsIgnoreCase("notificationParamName")))
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
			
			// improve JSON rendering by hiding a redundant array
			notificationFunctionality.setNotificationList(notificationFunctionality.getNotifications()
					.getNotification());
		}
		
		// improve JSON rendering by hiding a redundant array for states
		for (Configstate status : device.getState())
			status.setStatevalueList(status.getStatevalues().getStatevalue());
		
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
		}
		catch (DataBindingException e)
		{
			// the exception can be throw by the JAXB.marshal method...
			this.logger.log(LogService.LOG_ERROR, "Exception in JAXB Marshalling...", e);
		}
		
		return devicesXML;
	}
	
}