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
package it.polito.elite.dog.communication.rest.device.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
 * 
 * @author bonino
 * 
 */
@Path("/devices/")
public interface DeviceRESTApi
{
	/**
	 * Represents the status of devices registered in the Dog gateway runtime,
	 * i.e., defined in the Dog configuration and
	 * successfully registered within the gateway runtime.
	 * 
	 * @return The JSON description of the current device status
	 */
	@GET
	@Path("/status")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllDeviceStatus();
	
}
