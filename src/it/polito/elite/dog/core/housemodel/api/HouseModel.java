/*
 * Dog - Core
 * 
 * Copyright (c) 2013 Luigi De Russis
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
package it.polito.elite.dog.core.housemodel.api;

import it.polito.elite.dog.core.library.jaxb.BuildingEnvironment;
import it.polito.elite.dog.core.library.jaxb.Controllables;
import it.polito.elite.dog.core.library.jaxb.DogHomeConfiguration;
import it.polito.elite.dog.core.library.jaxb.Flat;
import it.polito.elite.dog.core.library.jaxb.Room;
import it.polito.elite.dog.core.library.jaxb.Storey;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;

import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * The API for providing the HouseModel service, i.e., the configuration of the
 * environment and its update.
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public interface HouseModel
{
	/**
	 * Update the list of devices configured in the real environment (only in
	 * the HouseModel, not in the framework)
	 * 
	 * @param descriptors
	 *            a {@link Vector} of {@link DeviceDescriptor} containing the
	 *            information about the devices to update
	 */
	public void updateConfiguration(Vector<DeviceDescriptor> updatedDescriptors);
	
	/**
	 * Update a device configured in the real environment (only in the
	 * HouseModel, not in the framework)
	 * 
	 * @param updatedDescriptor
	 *            the information about the device to update
	 */
	public void updateConfiguration(DeviceDescriptor updatedDescriptor);
	
	/**
	 * Add a list of devices to the current configuration (only in the
	 * HouseModel, not in the framework)
	 * 
	 * @param newDescriptors
	 *            a {@link Vector} of {@link DeviceDescriptor} containing the
	 *            information about the devices to add
	 */
	public void addToConfiguration(Vector<DeviceDescriptor> newDescriptors);
	
	/**
	 * Add a device to the current configuration (only in the HouseModel, not in
	 * the framework)
	 * 
	 * @param newDescriptor
	 *            the information about the device to add
	 */
	public void addToConfiguration(DeviceDescriptor newDescriptor);
	
	/**
	 * Remove a list of devices from the current configuration (only in the
	 * HouseModel, not in the framework)
	 * 
	 * @param removedDescriptors
	 *            a {@link Set} of {@link String} representing the device URIs
	 */
	public void removeFromConfiguration(Set<String> deviceURIs);
	
	/**
	 * Remove a device from the current configuration (only in the HouseModel,
	 * not in the framework)
	 * 
	 * @param removedDescriptor
	 *            the URI of the device to remove
	 */
	public void removeFromConfiguration(String deviceURI);
	
	/**
	 * Get the list of the devices configured in the real environment.
	 * 
	 * @return a {@link Vector} of {@link DeviceDescriptor} containing the
	 *         information about the configured devices
	 */
	public Vector<DeviceDescriptor> getConfiguration();
	
	/**
	 * Get the JAXB representation of the environment: a list of devices, with
	 * their properties, and a list of rooms/places.
	 * 
	 * @return {@link DogHomeConfiguration}
	 */
	public DogHomeConfiguration getJAXBConfiguration();
	
	/**
	 * Get the JAXB representation of the devices configured in the environment,
	 * with their properties
	 * 
	 * @return a {@link List} of {@link Controllables}, i.e., all the configured
	 *         devices
	 */
	public List<Controllables> getJAXBDevices();
	
	/**
	 * Get the JAXB representation of the building environment (flats, rooms,
	 * etc.)
	 * 
	 * @return a {@link List} of {@link BuildingEnvironment}, i.e., all the
	 *         building-related environments (flats, rooms, etc.)
	 */
	public List<BuildingEnvironment> getJAXBBuildingEnvironment();
	
	/**
	 * Get the SVG house plan.
	 * 
	 * @return a SVG image representing the current environment
	 */
	public String getSVGPlan();
	
	/**
	 * Update the properties of an existing room
	 * 
	 * @param roomToUpdate
	 *            the JAXB room to update
	 * @param containerURI
	 *            the unique name representing where the room is located
	 */
	public void updateBuildingConfiguration(Room roomToUpdate, String containerURI);
	
	/**
	 * Update the properties of an existing flat placed in the main building
	 * 
	 * @param flatToUpdate
	 *            the JAXB flat to update
	 */
	public void updateBuildingConfiguration(Flat flatToUpdate);
	
	/**
	 * Update the properties of an existing flat placed in a storey
	 * 
	 * @param flatToUpdate
	 *            the JAXB flat to update
	 * @param storeyURI
	 *            the unique name representing the storey where the flat is
	 *            placed
	 */
	public void updateBuildingConfiguration(Flat flatToUpdate, String storeyURI);
	
	/**
	 * Update the properties of an existing storey
	 * 
	 * @param storeyToUpdate
	 *            the JAXB storey to update
	 */
	public void updateBuildingConfiguration(Storey storeyToUpdate);
	
	/**
	 * Add a room to an existing flat or storey present in the configuration
	 * 
	 * @param roomToAdd
	 *            the JAXB room object to add
	 * @param containerURI
	 *            the unique name representing where the room is located
	 */
	public void addRoomToBuilding(Room roomToAdd, String containerURI);
	
	/**
	 * Add a flat to the building environment
	 * 
	 * @param flatToAdd
	 *            the JAXB flat object to add
	 */
	public void addFlatToBuilding(Flat flatToAdd);
	
	/**
	 * Add a storey to the building environment
	 * 
	 * @param storeyToAdd
	 *            the JAXB storey object to add
	 */
	public void addStoreyToBuilding(Storey storeyToAdd);
	
	/**
	 * Add a flat to an existing storey
	 * 
	 * @param flatToAdd
	 *            the JAXB flat object to add
	 * @param storeyURI
	 *            the unique name representing the storey where the flat is
	 *            placed
	 */
	public void addFlatToStorey(Flat flatToAdd, String storeyURI);
	
	/**
	 * Remove an existing room from the configuration
	 * 
	 * @param roomURI
	 *            the unique name of the room to remove
	 * @param containerURI
	 *            the unique name representing where the room is located
	 */
	public void removeRoomFromBuilding(String roomURI, String containerURI);
	
	/**
	 * Remove an existing flat from the configuration with all its children
	 * (i.e., all the rooms present in the flat are removed)
	 * 
	 * @param flatURI
	 *            the unique name of the flat to remove
	 */
	public void removeFlatFromBuilding(String flatURI);
	
	/**
	 * Remove an existing storey from the configuration with all its children
	 * (i.e., all the rooms present in the storey are removed)
	 * 
	 * @param storeyURI
	 *            the unique name of the storey to remove
	 */
	public void removeStoreyFromBuilding(String storeyURI);
}
