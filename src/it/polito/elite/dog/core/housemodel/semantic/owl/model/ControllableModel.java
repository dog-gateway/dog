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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataExactCardinality;
import org.semanticweb.owlapi.model.OWLDataHasValue;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import uk.ac.manchester.cs.owl.owlapi.OWLClassImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLDataExactCardinalityImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLDataHasValueImpl;

/**
 * The DogOnt Controllable model
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class ControllableModel
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
	public ControllableModel(OWLWrapper owlWrapper)
	{
		this.owlWrapper = owlWrapper;
	}
	
	/**
	 * Get all controllables
	 * 
	 * @return a set of {@link String} representing all the controllables
	 */
	public Set<String> getAllControllableInstances()
	{
		return this.owlWrapper.getAllIndividual("Controllable");
	}
	
	/**
	 * Get the network component
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return the network component as a {@link String}
	 */
	public String getNetworkComponent(String individualName)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(individualName);
		return this.owlWrapper.getSpecificType(individual, "NetworkComponent", false);
	}
	
	/**
	 * Get the device type
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return the device type as a {@link String}
	 */
	public String getDeviceType(String individualName)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(individualName);
		String deviceType = this.owlWrapper.getSpecificType(individual, "NetworkComponent", true);
		// it is a gateway
		if (deviceType.isEmpty())
			deviceType = this.owlWrapper.getSpecificType(individual, "NetworkComponent", false);
		
		return deviceType;
	}
	
	/**
	 * Get the isIn property
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return the isIn property value as a {@link String}
	 */
	public String getIsIn(String individualName)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(individualName);
		
		OWLIndividual isIn = this.owlWrapper.getSingleObjectProperty(individual, "isIn");
		if (isIn != null)
			return this.owlWrapper.getShortFormWithoutPrefix((OWLEntity) isIn);
		else
			return null;
	}
	
	/**
	 * Get the hasGateway property
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return the hasGateway property value as a {@link String}
	 */
	public String getHasGateway(String individualName)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(individualName);
		
		OWLIndividual hasGateway = this.owlWrapper.getSingleObjectProperty(individual, "hasGateway");
		if (hasGateway != null)
			return this.owlWrapper.getShortFormWithoutPrefix((OWLEntity) hasGateway);
		else
			return null;
	}
	
	/**
	 * Get the hasMeter property
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return the hasMeter property value as a {@link String}
	 */
	public String getHasMeter(String individualName)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(individualName);
		
		OWLIndividual hasMeter = this.owlWrapper.getSingleObjectProperty(individual, "hasMeter");
		if (hasMeter != null)
			return this.owlWrapper.getShortFormWithoutPrefix((OWLEntity) hasMeter);
		else
			return null;
	}
	
	/**
	 * Get the sensorOf property
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return the sensorOf property value as a {@link String}
	 */
	public String getSensorOf(String individualName)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(individualName);
		
		OWLIndividual sensorOf = this.owlWrapper.getSingleObjectProperty(individual, "sensorOf");
		if (sensorOf != null)
			return this.owlWrapper.getShortFormWithoutPrefix((OWLEntity) sensorOf);
		else
			return null;
	}
	
	/**
	 * Get the pluggedIn property
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return the pluggedIn property value as a {@link String}
	 */
	public String getPluggedIn(String individualName)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(individualName);
		
		OWLIndividual pluggedIn = this.owlWrapper.getSingleObjectProperty(individual, "pluggedIn");
		if (pluggedIn != null)
			return this.owlWrapper.getShortFormWithoutPrefix((OWLEntity) pluggedIn);
		else
			return null;
	}
	
	/**
	 * Get the actuatorOf property
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return the actuatorOf property value as a {@link String}
	 */
	public String getActuatorOf(String individualName)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(individualName);
		
		OWLIndividual actuatorOf = this.owlWrapper.getSingleObjectProperty(individual, "actuatorOf");
		if (actuatorOf != null)
			return this.owlWrapper.getShortFormWithoutPrefix((OWLEntity) actuatorOf);
		else
			return null;
	}
	
	/**
	 * Get the meterOf property
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return a set of {@link String} with the meterOf property values
	 */
	public Set<String> getMeterOf(String individualName)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(individualName);
		Set<String> meterOfValues = new HashSet<String>();
		
		Set<OWLNamedIndividual> meterOf = this.owlWrapper.getMultipleObjectProperties(individual, "meterOf");
		if (meterOf != null)
		{
			for (OWLNamedIndividual singleProp : meterOf)
			{
				meterOfValues.add(this.owlWrapper.getShortFormWithoutPrefix((OWLEntity) singleProp));
			}
		}
		
		return meterOfValues;
	}
	
	/**
	 * Get the controlledObject property
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return a set of {@link String} with the controlledObject property values
	 */
	public Set<String> getControlledObject(String individualName)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(individualName);
		Set<String> controlledObjectValues = new HashSet<String>();
		
		Set<OWLNamedIndividual> controlledObject = this.owlWrapper.getMultipleObjectProperties(individual, "controlledObject");
		if (controlledObject != null)
		{
			for (OWLNamedIndividual singleProp : controlledObject)
			{
				controlledObjectValues.add(this.owlWrapper.getShortFormWithoutPrefix((OWLEntity) singleProp));
			}
		}
		
		return controlledObjectValues;
	}
	
	/**
	 * Get all the device states
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return a {@link Map} with all the state classes and IRIs
	 */
	public Map<String, String> getAllStates(String individualName)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(individualName);
		Map<String, String> states = new HashMap<String, String>();
		
		Set<OWLNamedIndividual> hasState = this.owlWrapper.getMultipleObjectProperties(individual, "hasState");
		if (hasState != null)
		{
			for (OWLNamedIndividual singleState : hasState)
			{
				String stateClass = this.owlWrapper.getShortFormWithoutPrefix((OWLEntity) singleState.getTypes(this.owlWrapper.getOntModel())
						.iterator().next());
				String stateIRI = this.owlWrapper.getShortFormWithoutPrefix((OWLEntity) singleState);
				states.put(stateClass, stateIRI);
			}
		}
		
		return states;
	}
	
	/**
	 * Get all the state values for a given state
	 * 
	 * @param stateName
	 *            the short name of a {@link OWLIndividual}
	 * @return a {@link Map} with all the state values classes and effective
	 *         value
	 */
	public Map<String, String> getAllStateValues(String stateName)
	{
		OWLNamedIndividual state = this.owlWrapper.getOWLIndividual(stateName);
		Map<String, String> allStateValues = new HashMap<String, String>();
		
		if (state != null)
		{
			// get state values
			Set<OWLNamedIndividual> hasStateValue = this.owlWrapper.getMultipleObjectProperties(state, "hasStateValue");
			for (OWLIndividual stateValue : hasStateValue)
			{
				OWLClass stateValueClass = stateValue.getTypes(this.owlWrapper.getOntModel()).iterator().next().asOWLClass();
				
				OWLDataHasValueImpl realStateValue = null;
				Set<OWLClassExpression> superc = stateValueClass.getSuperClasses(this.owlWrapper.getOntModel().getDirectImports());
				for (OWLClassExpression restriction : superc)
				{
					if (restriction instanceof OWLDataHasValue)
					{
						realStateValue = (OWLDataHasValueImpl) restriction;
					}
				}
				String stateValueName = "";
				if (realStateValue != null)
					stateValueName = realStateValue.getValue().getLiteral();
				
				allStateValues.put(this.owlWrapper.getShortFormWithoutPrefix(stateValueClass), stateValueName);
			}
			
		}
		
		return allStateValues;
	}
	
	/**
	 * Get all the control and query functionalities of a given device
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return a {@link Map} with all the requested functionalities class and
	 *         command names
	 */
	public Map<String, Set<String>> getAllControlQueryFunctionalities(String individualName)
	{
		return this.getFunctionalities(individualName, "hasCommand", true);
	}
	
	/**
	 * Get all the notification functionalities of a given device
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return a {@link Map} with all the requested functionalities class and
	 *         notification names
	 */
	public Map<String, Set<String>> getAllNotificationFunctionalities(String individualName)
	{
		return this.getFunctionalities(individualName, "hasNotification", false);
	}
	
	/**
	 * Implementation of getAllControlQueryFunctionalities and
	 * getAllNotificationFunctionalities
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @param suffix
	 *            the suffix of a object property
	 * @param isCommand
	 *            a boolean to identify if the request is for a command or not
	 * @return a {@link Map} with all the requested functionalities class and
	 *         sub-functionalities names
	 */
	private Map<String, Set<String>> getFunctionalities(String individualName, String suffix, boolean isCommand)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(individualName);
		Map<String, Set<String>> functionalities = new HashMap<String, Set<String>>();
		
		Set<OWLNamedIndividual> hasFunctionality = this.owlWrapper.getMultipleObjectProperties(individual, "hasFunctionality");
		if (hasFunctionality != null)
		{
			for (OWLNamedIndividual singleFunc : hasFunctionality)
			{
				String functionalityClass = this.owlWrapper.getSpecificType(singleFunc, "NotificationFunctionality", isCommand);
				
				Set<String> names = new HashSet<String>();
				Set<OWLNamedIndividual> hasSomething = this.owlWrapper.getMultipleObjectProperties(singleFunc, suffix);
				if (hasSomething != null && !hasSomething.isEmpty())
				{
					for (OWLIndividual something : hasSomething)
					{
						names.add(this.owlWrapper.getShortFormWithoutPrefix((OWLEntity) something));
					}
					
				}
				functionalities.put(functionalityClass, names);
			}
		}
		
		return functionalities;
	}
	
	/**
	 * Get the device description.
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return the device description as a {@link String}
	 */
	public String getDeviceDescription(String individualName)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(individualName).asOWLNamedIndividual();
		OWLAnnotation devDescription = this.owlWrapper.getSingleAnnotation(individual);
		if (devDescription != null)
			return devDescription.toString();
		else
			return null;
	}
	
	/**
	 * Get all the device parameters
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @return a {@link Map} containing all the device parameters
	 */
	public Map<String, Set<String>> getDeviceParameters(String individualName)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(individualName).asOWLNamedIndividual();
		return this.owlWrapper.getDataPropertyValues(individual);
	}
	
	/**
	 * Get the single notification or command OWL class
	 * 
	 * @param name
	 *            the short name of a {@link OWLIndividual}
	 * @return the requested class as a {@link String}
	 */
	public String getSubFunctionalityClass(String name)
	{
		OWLNamedIndividual individual = this.owlWrapper.getOWLIndividual(name);
		return this.owlWrapper.getSpecificType(individual, "NetworkSpecificNotification", true);
	}
	
	/**
	 * Get the restrictions for a command or a notification
	 * 
	 * @param individualName
	 *            the short name of a {@link OWLIndividual}
	 * @param individualClass
	 *            the class of the previous {@link OWLIndividual}
	 * @return a {@link Map} with all the needed restriction names and values
	 */
	public Map<String, Set<String>> getParameters(String individualName, String individualClass)
	{
		// init
		OWLIndividual individual = this.owlWrapper.getOWLIndividual(individualName);
		Map<OWLDataPropertyExpression, Set<OWLLiteral>> properties = new HashMap<OWLDataPropertyExpression, Set<OWLLiteral>>();
		Set<OWLDataHasValueImpl> hasValueRestrictions = new HashSet<OWLDataHasValueImpl>();
		Set<OWLDataExactCardinalityImpl> exactCardinalityRestrictions = new HashSet<OWLDataExactCardinalityImpl>();
		OWLClass parameterClass = new OWLClassImpl(IRI.create(this.owlWrapper.getPrefixManager().getDefaultPrefix() + individualClass));
		OWLOntology ontModel = this.owlWrapper.getOntModel();
		// get all the superclasses from all the direct import (dogont)
		Set<OWLClassExpression> superc = parameterClass.getSuperClasses(ontModel.getDirectImports());
		for (OWLClassExpression clazz : superc)
		{
			// look for a hasValue restriction
			if (clazz instanceof OWLDataHasValue)
			{
				hasValueRestrictions.add((OWLDataHasValueImpl) clazz);
			}
			else if (clazz instanceof OWLClass)
			{
				// look for a exactCardinality restriction
				for (OWLClassExpression ancestorClass : clazz.asOWLClass().getSuperClasses(
						this.owlWrapper.getOntModel().getDirectImports()))
				{
					if (ancestorClass instanceof OWLDataExactCardinality)
					{
						exactCardinalityRestrictions.add((OWLDataExactCardinalityImpl) ancestorClass);
					}
				}
			}
		}
		
		// set the previously got information
		if (!hasValueRestrictions.isEmpty())
		{
			for (OWLDataHasValueImpl hasValueRestriction : hasValueRestrictions)
			{
				Set<OWLLiteral> temp = new HashSet<OWLLiteral>();
				temp.add(hasValueRestriction.getValue());
				properties.put(hasValueRestriction.getProperty(), temp);
			}
		}
		if (!exactCardinalityRestrictions.isEmpty())
		{
			for (OWLDataExactCardinalityImpl exactCardinalityRestriction : exactCardinalityRestrictions)
			{
				Set<OWLLiteral> literalSet = new HashSet<OWLLiteral>();
				literalSet.add(ontModel.getOWLOntologyManager().getOWLDataFactory().getOWLLiteral(
						exactCardinalityRestriction.getCardinality()));
				properties.put(exactCardinalityRestriction.getProperty().asOWLDataProperty(), literalSet);
			}
		}
		
		// get the parameters from the entry ontology
		properties.putAll(individual.getDataPropertyValues(ontModel));
		
		// return only the needed values in the proper format
		HashMap<String, Set<String>> parameters = new HashMap<String, Set<String>>();
		for (OWLDataPropertyExpression property : properties.keySet())
		{
			Set<String> paramValue = new HashSet<String>();
			for (OWLLiteral propertyValue : properties.get(property))
			{
				paramValue.add(propertyValue.getLiteral());
			}
			parameters.put(this.owlWrapper.getShortFormWithoutPrefix(property.asOWLDataProperty()), paramValue);
		}
		
		return parameters;
	}
	
}
