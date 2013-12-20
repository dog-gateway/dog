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
package it.polito.elite.dog.communication.rest.device.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * The interface defining the API for the devices, it permits to:
 * <ul>
 * <li>query the gateway about installed devices, their location,
 * functionalities and configurations;</li>
 * <li>require execution of commands to existing devices;</li>
 * <li>monitor device statuses and measures in real-time;</li>
 * <li>add, modify or update the set of devices controlled through the gateway;</li>
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
@Path("/api/devices/")
public interface DeviceRESTApi
{
	/**
	 * Represents domotic devices handled by Dog and "controllable" applications
	 * using this API.
	 * 
	 * @return the JSON representation of the configured devices
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllDevicesInJson();
	
	/**
	 * Represents domotic devices handled by Dog and "controllable" applications
	 * using this API.
	 * 
	 * @return the XML representation of the configured devices
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String getAllDevicesInXml();
	
	/**
	 * Represents a single domotic device handled by Dog, identified by a unique
	 * device-id, and "controllable" by applications using this API.
	 * 
	 * @param deviceId
	 *            the device unique identifier
	 * @return the JSON representation of the required device
	 */
	@GET
	@Path("/{device-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDeviceInJson(@PathParam("device-id") String deviceId);
	
	/**
	 * Represents a single domotic device handled by Dog, identified by a unique
	 * device-id, and "controllable" by applications using this API.
	 * 
	 * @param deviceId
	 *            the device unique identifier
	 * @return the XML representation of the required device
	 */
	@GET
	@Path("/{device-id}")
	@Produces(MediaType.APPLICATION_XML + "; qs=0.9")
	public String getDeviceInXml(@PathParam("device-id") String deviceId);
	
	/**
	 * Update the location of a single domotic device handled by Dog, identified
	 * by a unique device-id.
	 * 
	 * @param deviceId
	 *            the device unique identifier
	 */
	@PUT
	@Path("/{device-id}/location")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateDeviceLocation(@PathParam("device-id") String deviceId, String location);
	
	/**
	 * Update the description (i.e., the long name) of a single domotic device
	 * handled by Dog, identified by a unique device-id.
	 * 
	 * @param deviceId
	 *            the device unique identifier
	 */
	@PUT
	@Path("/{device-id}/description")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateDeviceDescription(@PathParam("device-id") String deviceId, String description);
	
	/**
	 * Represents the status of the device identified by the given device-id and
	 * registered in the Dog gateway runtime, i.e., defined in the Dog
	 * configuration and successfully registered within the gateway runtime.
	 * 
	 * @return the JSON description of the current device status
	 */
	@GET
	@Path("/{device-id}/status")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDeviceStatus(@PathParam("device-id") String deviceId);
	
	/**
	 * Represents the status of devices registered in the Dog gateway runtime,
	 * i.e., defined in the Dog configuration and successfully registered within
	 * the gateway runtime.
	 * 
	 * @return The JSON description of the current device status
	 */
	@GET
	@Path("/status")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllDeviceStatus();
	
	/**
	 * TODO: For testing purpose only
	 * 
	 * Represents a command, identified by a command-name, to be sent to the
	 * device identified by the given device-id. Commands are idempotent: the
	 * same command always results in the same behavior of the selected device.
	 * If the command brings the device in same state in which the device is, no
	 * differences will be appreciable
	 * 
	 * @param deviceId
	 *            The device unique identifier (URI)
	 * @param commandName
	 *            The command to be executed
	 * @return
	 */
	@GET
	@Path("{device-id}/commands/{command-name}")
	public String executeCommandGet(@PathParam("device-id") String deviceId,
			@PathParam("command-name") String commandName);
	
	/**
	 * Represents a command, identified by a command-name, to be sent to the
	 * device identified by the given device-id. Commands are idempotent: the
	 * same command always results in the same behavior of the selected device.
	 * If the command brings the device in same state in which the device is, no
	 * differences will be appreciable
	 * 
	 * @param deviceId
	 *            The device unique identifier (URI)
	 * @param commandName
	 *            The command to be executed
	 * @param commandParameters
	 *            Any possible command parameter (one in this version)
	 */
	@POST
	@Path("{device-id}/commands/{command-name}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void executeCommandPost(@PathParam("device-id") String deviceId,
			@PathParam("command-name") String commandName, String commandParameters);
	
	/**
	 * Represents a command, identified by a command-name, to be sent to the
	 * device identified by the given device-id. Commands are idempotent: the
	 * same command always results in the same behavior of the selected device.
	 * If the command brings the device in same state in which the device is, no
	 * differences will be appreciable
	 * 
	 * @param deviceId
	 *            The device unique identifier (URI)
	 * @param commandName
	 *            The command to be executed
	 * @param commandParameters
	 *            Any possible command parameter (one in this version)
	 */
	@PUT
	@Path("{device-id}/commands/{command-name}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void executeCommandPut(@PathParam("device-id") String deviceId,
			@PathParam("command-name") String commandName, String commandParameters);
	
}
