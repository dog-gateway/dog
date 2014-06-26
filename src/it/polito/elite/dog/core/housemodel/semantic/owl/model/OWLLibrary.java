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

import it.polito.elite.dog.core.housemodel.semantic.util.OWLWrapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import uk.ac.manchester.cs.owl.owlapi.OWLClassImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectPropertyImpl;

/**
 * Abstract class that provides common methods and facilities for handling with
 * OWL models
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public abstract class OWLLibrary
{
	protected OWLOntologyManager manager;
	protected DefaultPrefixManager prefixManager;
	protected OWLReasoner reasoner;
	protected OWLOntology ontModel;
	
	/**
	 * Default constructor.
	 * 
	 * @param owlWrapper
	 *            the {@link OWLWrapper} instance containing all the needed
	 *            OWL-related information
	 */
	protected OWLLibrary(OWLWrapper owlWrapper)
	{
		// get all the needed information for handling OWL data
		this.manager = owlWrapper.getOntModel().getOWLOntologyManager();
		this.prefixManager = owlWrapper.getPrefixManager();
		this.ontModel = owlWrapper.getOntModel();
		this.reasoner = owlWrapper.getReasoner();
	}
	
	/**
	 * Get all the individuals with a given suffix class.
	 * 
	 * @param suffix
	 *            the suffix representing the OWL individual class
	 * @return a set of {@link String} with all the individual names
	 */
	protected Set<String> getAllIndividual(String suffix)
	{
		// init
		Set<String> individuals = new HashSet<String>();
		// get the OWL data factory
		OWLDataFactory fac = this.manager.getOWLDataFactory();
		// get a OWL class given the default prefix and the suffix
		OWLClass owlControllables = fac.getOWLClass(IRI.create(this.prefixManager.getDefaultPrefix() + suffix));
		// get all the instances from the reasoner
		NodeSet<OWLNamedIndividual> individualsNodeSet = this.reasoner.getInstances(owlControllables, false);
		Set<OWLNamedIndividual> owlIndividuals = individualsNodeSet.getFlattened();
		
		for (OWLNamedIndividual ind : owlIndividuals)
		{
			// put in short form
			individuals.add(this.getShortFormWithoutPrefix(ind));
		}
		
		return individuals;
	}
	
	/**
	 * Get the specific type for a given {@link OWLNamedIndividual} object
	 * 
	 * @param individual
	 *            the {@link OWLNamedIndividual} to get the specific type
	 * @param suffix
	 *            the suffix representing the OWL individual class
	 * @param inverse
	 *            a boolean for expressing if the type is contained in the class
	 *            represented by the suffix or not
	 * @return the specific type as a {@link String}
	 */
	protected String getSpecificType(OWLNamedIndividual individual, String suffix, boolean inverse)
	{
		String specificType = "";
		
		// get types from the reasoner
		Set<OWLClass> types = this.reasoner.getTypes(individual, true).getFlattened();
		
		for (OWLClassExpression type : types)
		{
			// look in the superclasses, to get the specific type
			Set<OWLClass> superc = this.reasoner.getSuperClasses(type, false).getFlattened();
			if (inverse)
			{
				if (!superc.contains(new OWLClassImpl(IRI.create(this.prefixManager.getDefaultPrefix() + suffix))))
				{
					return this.getShortFormWithoutPrefix(type.asOWLClass());
				}
			}
			else
			{
				if (superc.contains(new OWLClassImpl(IRI.create(this.prefixManager.getDefaultPrefix() + suffix))))
				{
					return this.getShortFormWithoutPrefix(type.asOWLClass());
				}
			}
		}
		
		return specificType;
	}
	
	/**
	 * Get a single ObjectProperty for a given {@link OWlNamedIndividual}
	 * instance
	 * 
	 * @param individual
	 *            the {@link OWLNamedIndividual} to get the object property
	 * @param property
	 *            the property name
	 * @return the ObjectProperty as a {@link OWLNamedIndividual}
	 */
	protected OWLNamedIndividual getSingleObjectProperty(OWLNamedIndividual individual, String property)
	{
		Set<OWLNamedIndividual> multipleProperties = this.getMultipleObjectProperties(individual, property);
		if (multipleProperties != null && !multipleProperties.isEmpty())
			return multipleProperties.iterator().next();
		else
			return null;
	}
	
	/**
	 * Get a multiple ObjectProperty for a given {@link OWlNamedIndividual}
	 * instance
	 * 
	 * @param individual
	 *            the {@link OWLNamedIndividual} to get the object property
	 * @param property
	 *            the property name
	 * @return the ObjectProperty as a set of {@link OWLNamedIndividual}
	 */
	protected Set<OWLNamedIndividual> getMultipleObjectProperties(OWLNamedIndividual individual, String property)
	{
		OWLObjectPropertyImpl objProperty = new OWLObjectPropertyImpl(IRI.create(this.prefixManager.getDefaultPrefix()
				+ property));
		return this.reasoner.getObjectPropertyValues(individual, objProperty).getFlattened();
	}
	
	/**
	 * Get an annotation for a given {@link OWLNamedIndividual} instance
	 * 
	 * @param individual
	 *            the {@link OWLNamedIndividual} to get the annotation
	 * @return the requested {@link OWLAnnotation}
	 */
	protected OWLAnnotation getSingleAnnotation(OWLNamedIndividual individual)
	{
		Set<OWLAnnotation> annotations = individual.getAnnotations(this.ontModel);
		if (annotations != null && !annotations.isEmpty())
		{
			return annotations.iterator().next();
		}
		else
			return null;
	}
	
	/**
	 * Get a {@link OWLNamedIndividual} given its short name
	 * 
	 * @param name
	 *            the short name of the individual to get
	 * @return the {@link OWLNamedIndividual}
	 */
	protected OWLNamedIndividual getOWLIndividual(String name)
	{
		// get the entry ontology prefix
		String instanceOntPrefix = this.manager.getOntologyFormat(this.ontModel).asPrefixOWLOntologyFormat()
				.getDefaultPrefix();
		return this.manager.getOWLDataFactory().getOWLNamedIndividual(IRI.create(instanceOntPrefix + name));
	}
	
	/**
	 * Get the values of all the Data Property for a given
	 * {@link OWLNamedIndividual} instance
	 * 
	 * @param individual
	 *            the {@link OWLNamedIndividual} instance to get the data
	 *            property
	 * @return a {@link Map} containing the requested properties with their
	 *         values
	 */
	protected Map<String, Set<String>> getDataPropertyValues(OWLNamedIndividual individual)
	{
		Map<String, Set<String>> params = new HashMap<String, Set<String>>();
		Map<OWLDataPropertyExpression, Set<OWLLiteral>> properties = individual.getDataPropertyValues(this.ontModel);
		if (properties != null)
		{
			for (OWLDataPropertyExpression odp : properties.keySet())
			{
				Set<String> paramValues = new HashSet<String>();
				
				for (OWLLiteral value : properties.get(odp))
				{
					paramValues.add(value.getLiteral());
				}
				params.put(this.getShortFormWithoutPrefix(odp.asOWLDataProperty()), paramValues);
			}
		}
		return params;
	}
	
	/**
	 * Get the short form of a generic {@link OWLEntity} without any prefix
	 * 
	 * @param individual
	 *            the {@link OWLEntity} to get the short name
	 * @return the short name as a {@link String}
	 */
	protected String getShortFormWithoutPrefix(OWLEntity individual)
	{
		String shortForm = this.prefixManager.getShortForm(individual);
		return shortForm.substring(shortForm.indexOf(':') + 1);
	}
	
}
