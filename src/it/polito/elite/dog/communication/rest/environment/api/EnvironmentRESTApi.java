/*
 * Dog - Environment REST Endpoint
 * 
 * Copyright (c) 2013-2014 Luigi De Russis and Teodoro Montanaro
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
package it.polito.elite.dog.communication.rest.environment.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * The interface defining the API for the environment, it permits to:
 * <ul>
 * <li>insert, update or delete rooms;</li>
 * <li>insert, update, or delete flats.</li>
 * </ul>
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
@Path("/api/v1/environment/")
public interface EnvironmentRESTApi
{
	/**
	 * Get the environment (i.e., the building) configured in Dog.
	 * 
	 * @return a JSON representation of the building
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getBuildingInJson();
	
	/**
	 * Get the environment (i.e., the building) configured in Dog.
	 * 
	 * @return a XML representation of the building
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String getBuildingInXml();
	
	/**
	 * List all the flats present in the environment (i.e., the building).
	 * 
	 * @return a JSON representation of all the flats
	 */
	@GET
	@Path("/flats")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFlatsInJson();
	
	/**
	 * Add a new flat to the environment configured in Dog.
	 * 
	 * @param addedFlat
	 *            the JSON representation of the flat to add
	 */
	@POST
	@Path("/flats")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addNewFlat(String addedFlat);
	
	/**
	 * Return the details of the flat identified by the given flat-id.
	 * 
	 * @param flatId
	 *            the unique identifier of an existing flat
	 * @return the JSON representation of the flat with all its room
	 */
	@GET
	@Path("/flats/{flat-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFlat(@PathParam("flat-id") String flatId);
	
	/**
	 * Update the flat identified by the given flat-id.
	 * 
	 * @param flatId
	 *            the unique identifier of an existing flat
	 * @param updatedFlat
	 *            the updated flat in JSON format
	 */
	@PUT
	@Path("/flats/{flat-id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateFlat(@PathParam("flat-id") String flatId, String updatedFlat);
	
	/**
	 * List all the rooms present in the flat identified by the given flat-id.
	 * 
	 * @param flatId
	 *            the unique identifier of an existing flat
	 * @return the JSON representation of all the rooms present in the given
	 *         flat
	 */
	@GET
	@Path("/flats/{flat-id}/rooms")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRoomsInFlat(@PathParam("flat-id") String flatId);
	
	/**
	 * Add a new room to the flat identified by the given flat-id.
	 * 
	 * @param flatId
	 *            the unique identifier of an existing flat
	 * @param addedRoom
	 *            the JSON representation of a room to add to the given flat
	 */
	@POST
	@Path("/flats/{flat-id}/rooms")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addNewRoomInFlat(@PathParam("flat-id") String flatId, String addedRoom);
	
	/**
	 * Get the details of the room identified by the given room-id and located
	 * in the given flat.
	 * 
	 * @param flatId
	 *            the unique identifier of an existing flat
	 * @param roomId
	 *            the unique identifier of an existing room
	 * @return the room properties in JSON format
	 */
	@GET
	@Path("/flats/{flat-id}/rooms/{room-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSingleRoomInFlat(@PathParam("flat-id") String flatId, @PathParam("room-id") String roomId);
	
	/**
	 * Update the room identified by the given room-id and located in the given
	 * flat.
	 * 
	 * @param flatId
	 *            the unique identifier of an existing flat
	 * @param roomId
	 *            the unique identifier of an existing room
	 * @param updatedRoom
	 *            the updated room in JSON format
	 */
	@PUT
	@Path("/flats/{flat-id}/rooms/{room-id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateRoomInFlat(@PathParam("flat-id") String flatId, @PathParam("room-id") String roomId,
			String updatedRoom);
}
