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
package it.polito.elite.dog.core.housemodel.semantic.owl.model;

import it.polito.elite.dog.core.library.semantic.OWLWrapper;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

/**
 * The DogOnt BuildingEnvironment model
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class BuildingModel
{
	// the OWL Wrapper
	private OWLWrapper owlWrapper;
	
	/**
	 * Default constructor
	 * 
	 * @param owlWrapper
	 *            the {@link OWLWrapper} instance containing all the needed
	 *            OWL-related information
	 */
	public BuildingModel(OWLWrapper owlWrapper)
	{
		this.owlWrapper = owlWrapper;
	}
	
	/**
	 * Get all the buildings
	 * 
	 * @return a set of {@link String} representing all the buildings
	 */
	public Set<String> getAllBuildingInstances()
	{
		return this.owlWrapper.getAllIndividual("Building");
	}
	
	/**
	 * Get the places contained in the given building
	 * 
	 * @param buildingId
	 *            the short name of a {@link OWLIndividual}
	 * @return a set of {@link String} with the requested places
	 */
	public Set<String> getContainedInstances(String buildingId)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(buildingId);
		Set<String> containedInstances = new HashSet<String>();
		Set<OWLNamedIndividual> contains = this.owlWrapper.getMultipleObjectProperties(individual, "contains");
		
		if (contains != null)
		{
			for (OWLIndividual singleProp : contains)
			{
				containedInstances.add(this.owlWrapper.getShortFormWithoutPrefix((OWLEntity) singleProp));
			}
		}
		
		return containedInstances;
	}
	
	/**
	 * Get the building content type
	 * 
	 * @param buildingId
	 *            the short name of a {@link OWLIndividual}
	 * @return the type of the building content as a {@link String}
	 */
	public String getBuildingContentType(String buildingId)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(buildingId);
		return this.owlWrapper.getSpecificType(individual, "Room", true);
	}
	
	/**
	 * Get the SVG footprint(s)
	 * 
	 * @param flatId
	 *            the short name of a {@link OWLIndividual}
	 * @return a {@link Map} with the requested object names and values
	 */
	public Map<String, Set<String>> getSVGName(String flatId)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(flatId).asOWLNamedIndividual();
		return this.owlWrapper.getDataPropertyValues(individual);
	}
	
	/**
	 * Get the room type
	 * 
	 * @param roomId
	 *            the short name of a {@link OWLIndividual}
	 * @return the room type as a {@link String}
	 */
	public String getRoomType(String roomId)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(roomId);
		return this.owlWrapper.getSpecificType(individual, "Room", false);
	}
	
	/**
	 * Get the hasCeiling property
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return the hasCeiling property value as a {@link String}
	 */
	public String getHasCeiling(String roomId)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(roomId);
		
		OWLIndividual hasCeiling = this.owlWrapper.getSingleObjectProperty(individual, "hasCeiling");
		if (hasCeiling != null)
			return this.owlWrapper.getShortFormWithoutPrefix((OWLEntity) hasCeiling);
		else
			return null;
	}
	
	/**
	 * Get the architectural element type
	 * 
	 * @param architecturalElement
	 *            the short name of a {@link OWLIndividual}
	 * @return the architectural element type as a {@link String}
	 */
	public String getArchitecturalType(String architecturalElement)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(architecturalElement);
		return this.owlWrapper.getSpecificType(individual, "Architectural", false);
	}
	
	/**
	 * Get the hasFloor property
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return the hasFloor property value as a {@link String}
	 */
	public String getHasFloor(String roomId)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(roomId);
		
		OWLIndividual hasFloor = this.owlWrapper.getSingleObjectProperty(individual, "hasFloor");
		if (hasFloor != null)
			return this.owlWrapper.getShortFormWithoutPrefix((OWLEntity) hasFloor);
		else
			return null;
	}
	
	/**
	 * Get the hasWall property
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return a set of {@link String} with the hasWall property values
	 */
	public Set<String> getHasWall(String individualName)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(individualName);
		Set<String> hasWallValues = new HashSet<String>();
		
		Set<OWLNamedIndividual> hasWall = this.owlWrapper.getMultipleObjectProperties(individual, "hasWall");
		if (hasWall != null)
		{
			for (OWLIndividual singleProp : hasWall)
			{
				hasWallValues.add(this.owlWrapper.getShortFormWithoutPrefix((OWLEntity) singleProp));
			}
		}
		
		return hasWallValues;
	}
	
	/**
	 * Get the hasWallOpening property
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return a set of {@link String} with the hasWallOpening property values
	 */
	public Set<String> getHasWallOpening(String individualName)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(individualName);
		Set<String> hasWallOpeningValues = new HashSet<String>();
		
		Set<OWLNamedIndividual> hasWallOpening = this.owlWrapper.getMultipleObjectProperties(individual, "hasWallOpening");
		if (hasWallOpening != null)
		{
			for (OWLIndividual singleProp : hasWallOpening)
			{
				hasWallOpeningValues.add(this.owlWrapper.getShortFormWithoutPrefix((OWLEntity) singleProp));
			}
		}
		
		return hasWallOpeningValues;
	}
	
	/**
	 * Get the levelFromGround properties for a given storey
	 * 
	 * @param storeyId
	 *            the short name of a {@link OWLIndividual}
	 * @return a {@link Map} containing the requested property names and values
	 */
	public Map<String, Set<String>> getlevelFromGround(String storeyId)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(storeyId).asOWLNamedIndividual();
		return this.owlWrapper.getDataPropertyValues(individual);
	}
	
	/**
	 * Get the storey content type
	 * 
	 * @param innerId
	 *            the short name of a {@link OWLIndividual} contained in the
	 *            storey
	 * @return the storey content as a {@link String}
	 */
	public String getStoreyContentType(String innerId)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(innerId);
		String type = this.owlWrapper.getSpecificType(individual, "Room", true);
		if (type.isEmpty())
			type = this.owlWrapper.getSpecificType(individual, "Flat", true);
		
		return type;
		
	}
	
}
