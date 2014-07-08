/*
 * Dog - Core
 * 
 * Copyright (c) 2014 Luigi De Russis
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
package it.polito.elite.dog.core.housemodel.semantic.jaxb;

import it.polito.elite.dog.core.housemodel.semantic.owl.model.BuildingModel;
import it.polito.elite.dog.core.library.jaxb.Building;
import it.polito.elite.dog.core.library.jaxb.BuildingEnvironment;
import it.polito.elite.dog.core.library.jaxb.Ceiling;
import it.polito.elite.dog.core.library.jaxb.Flat;
import it.polito.elite.dog.core.library.jaxb.Floor;
import it.polito.elite.dog.core.library.jaxb.Garage;
import it.polito.elite.dog.core.library.jaxb.Garden;
import it.polito.elite.dog.core.library.jaxb.ObjectFactory;
import it.polito.elite.dog.core.library.jaxb.Room;
import it.polito.elite.dog.core.library.jaxb.Storey;
import it.polito.elite.dog.core.library.jaxb.Wall;
import it.polito.elite.dog.core.library.jaxb.WallOpening;
import it.polito.elite.dog.core.library.semantic.OWLWrapper;

import java.util.Map;
import java.util.Set;

/**
 * Create the {@link BuildingEnvironment} part of the Dog JAXB configuration by
 * querying the given ontology.
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class EnvironmentBuilder
{
	private BuildingModel eModel;
	
	/**
	 * Default constructor.
	 * 
	 * @param owlWrapper
	 *            the {@link OWLWrapper} instance containing all the needed
	 *            OWL-related information
	 */
	public EnvironmentBuilder(OWLWrapper owlWrapper)
	{
		this.eModel = new BuildingModel(owlWrapper);
	}
	
	/**
	 * Get all the building environment information.
	 * 
	 * @param factory
	 *            the {@link ObjectFactory} needed to build JAXB objects
	 * @return the {@link BuildingEnvironment}, i.e., the JAXB part related to
	 *         the building description
	 */
	public BuildingEnvironment getEnvironmentInformation(ObjectFactory factory)
	{
		// create the buildings container element
		BuildingEnvironment env = factory.createBuildingEnvironment();
		
		// get all building instances
		Set<String> allBuildings = this.eModel.getAllBuildingInstances();
		
		for (String singleBuilding : allBuildings)
		{
			// create the building
			Building building = factory.createBuilding();
			building.setId(singleBuilding);
			
			env.getBuilding().add(building);
			
			// get further information (rooms, flats, etc.)
			this.getBuildingInformation(factory, building);
		}
		
		return env;
	}
	
	/**
	 * Get the additional building information (rooms, flats, etc.)
	 * 
	 * @param factory
	 *            the {@link ObjectFactory} needed to build JAXB objects
	 * @param building
	 *            the {@link Building} for which get further information
	 */
	private void getBuildingInformation(ObjectFactory factory, Building building)
	{
		// set building name
		String buildingId = building.getId();
		
		// get all contained places
		Set<String> buildingContents = this.eModel.getContainedInstances(buildingId);
		
		if (!buildingContents.isEmpty())
		{
			for (String buildingContent : buildingContents)
			{
				// get the type of the content
				String buildingType = this.eModel.getBuildingContentType(buildingContent);
				if (buildingType.contains("Garden"))
				{
					// create a garden
					Garden garden = factory.createGarden();
					
					garden.setId(buildingContent);
					
					building.getGarden().add(garden);
				}
				else if (buildingType.contains("Garage"))
				{
					// create a garage
					Garage garage = factory.createGarage();
					
					garage.setId(buildingContent);
					
					building.getGarage().add(garage);
				}
				else if (buildingType.contains("Flat"))
				{
					// create a flat
					Flat flat = factory.createFlat();
					
					flat.setId(buildingContent);
					flat.setClazz("Flat");
					
					// get further information
					this.getFlatInformation(factory, flat);
					
					building.getFlat().add(flat);
				}
				else if (buildingType.contains("Storey"))
				{
					// create a storey
					Storey storey = factory.createStorey();
					
					storey.setId(buildingContent);
					storey.setClazz("Storey");
					
					// get further information
					this.getStoreyInformation(factory, storey);
					
					building.getStorey().add(storey);
				}
			}
		}
	}
	
	/**
	 * Get the storey information.
	 * 
	 * @param factory
	 *            the {@link ObjectFactory} needed to build JAXB objects
	 * @param storey
	 *            the {@link Storey} for which get further information
	 */
	private void getStoreyInformation(ObjectFactory factory, Storey storey)
	{
		// set the storey name
		String storeyId = storey.getId();
		
		// get level from ground
		Map<String, Set<String>> levelFromGround = this.eModel.getlevelFromGround(storeyId);
		
		if (!levelFromGround.isEmpty())
		{
			Set<String> levelFromGrounds = levelFromGround.get("levelFromGround");
			if (!levelFromGrounds.isEmpty())
			{
				storey.setLevelFromGround(new Integer(levelFromGrounds.iterator().next()));
			}
		}
		
		// get contained places
		Set<String> storeyContents = this.eModel.getContainedInstances(storeyId);
		
		for (String storeyContent : storeyContents)
		{
			String contentType = this.eModel.getStoreyContentType(storeyContent);
			if (contentType.contains("Room"))
			{
				// create a room
				Room room = factory.createRoom();
				room.setId(storeyContent);
				
				// get further information about the room (walls, ...)
				this.getRoomInformation(factory, room);
				
				storey.getRoom().add(room);
			}
			else if (contentType.contains("Flat"))
			{
				// create a flat
				Flat flat = factory.createFlat();
				
				flat.setId(storeyContent);
				flat.setClazz("Flat");
				
				// get further information about the flat
				this.getFlatInformation(factory, flat);
				
				storey.getFlat().add(flat);
			}
		}
	}
	
	/**
	 * Get the flat information.
	 * 
	 * @param factory
	 *            the {@link ObjectFactory} needed to build JAXB objects
	 * @param flat
	 *            the {@link Flat} for which get further information
	 */
	private void getFlatInformation(ObjectFactory factory, Flat flat)
	{
		// set the flat id
		String flatId = flat.getId();
		
		// get the SVG footprint name
		Map<String, Set<String>> svgName = this.eModel.getSVGName(flatId);
		
		if (!svgName.isEmpty())
		{
			Set<String> svgFootprints = svgName.get("svgFootprint");
			if (!svgFootprints.isEmpty())
			{
				flat.setSvgfootprint(svgFootprints.iterator().next());
			}
		}
		
		// get contained places
		Set<String> flatContents = this.eModel.getContainedInstances(flatId);
		
		for (String roomInstance : flatContents)
		{
			// create a room
			Room room = factory.createRoom();
			room.setId(roomInstance);
			
			// get further room information (walls, ...)
			this.getRoomInformation(factory, room);
			
			flat.getRoom().add(room);
		}
	}
	
	/**
	 * Get the room information.
	 * 
	 * @param factory
	 *            the {@link ObjectFactory} needed to build JAXB objects
	 * @param room
	 *            the {@link Room} for which get further information
	 */
	private void getRoomInformation(ObjectFactory factory, Room room)
	{
		// set the room name and type
		String roomId = room.getId();
		room.setClazz(this.eModel.getRoomType(roomId));
		
		// get ceiling
		String hasCeiling = this.eModel.getHasCeiling(roomId);
		if (hasCeiling != null)
		{
			// create the ceiling object
			Ceiling ceiling = factory.createCeiling();
			ceiling.setId(hasCeiling);
			
			// set the class of the ceiling object
			ceiling.setClazz(this.eModel.getArchitecturalType(hasCeiling));
			
			// add the ceiling to the room
			room.setCeiling(ceiling);
		}
		
		// get floor
		String hasFloor = this.eModel.getHasFloor(roomId);
		if (hasFloor != null)
		{
			// create the ceiling object
			Floor floor = factory.createFloor();
			floor.setId(hasFloor);
			
			// set the class of the ceiling object
			floor.setClazz(this.eModel.getArchitecturalType(hasFloor));
			
			// add the ceiling to the room
			room.setFloor(floor);
		}
		
		// get walls
		Set<String> walls = this.eModel.getHasWall(roomId);
		if ((walls != null) && (!walls.isEmpty()))
		{
			for (String wallName : walls)
			{
				// create the wall object
				Wall wall = factory.createWall();
				
				wall.setId(wallName);
				
				// set the class of the wall object
				wall.setClazz(this.eModel.getArchitecturalType(wallName));
				
				// get all needed data about the wall
				this.getWallInformation(factory, wall);
				
				// add the wall
				room.getWall().add(wall);
			}
		}
	}
	
	/**
	 * Get the walls information.
	 * 
	 * @param factory
	 *            the {@link ObjectFactory} needed to build JAXB objects
	 * @param wall
	 *            the {@link Wall} for which get further information
	 */
	private void getWallInformation(ObjectFactory factory, Wall wall)
	{
		// set the wall name
		String wallId = wall.getId();
		
		// get wall opening
		Set<String> wallOpenings = this.eModel.getHasWallOpening(wallId);
		if ((wallOpenings != null) && (!wallOpenings.isEmpty()))
		{
			for (String wallOpnInst : wallOpenings)
			{
				// create the wall opening
				WallOpening opening = factory.createWallOpening();
				
				// set the opening name
				opening.setId(wallOpnInst);
				
				// set the opening class
				opening.setClazz(this.eModel.getArchitecturalType(wallOpnInst));
				
				// add the opening
				wall.getHasWallOpening().add(opening);
			}
		}
	}
	
}
