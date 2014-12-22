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
package it.polito.elite.dog.core.library.semantic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.semanticweb.HermiT.Configuration;
import org.semanticweb.HermiT.Reasoner;
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
import org.semanticweb.owlapi.reasoner.ConsoleProgressMonitor;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.vocab.PrefixOWLOntologyFormat;

import uk.ac.manchester.cs.owl.owlapi.OWLClassImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLDataPropertyImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectPropertyImpl;

/**
 * The OWL Wrapper class init all the needed structures and provides common
 * methods and facilities to successfully handle DogOnt-related ontologies.
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class OWLWrapper
{
	// the default DogOnt IRI
	private final String dogontIRI;
	// needed structure
	private DefaultPrefixManager prefixManager;
	private OWLOntology ontModel;
	private OWLReasoner reasoner;
	private OWLOntologyManager manager;
	
	/**
	 * Default constructor.
	 * 
	 * @param model
	 *            the {@link OWLOntology} instance to use
	 * @param prefixes
	 *            the {@link DefaultPrefixManager} to complete
	 */
	public OWLWrapper(OWLOntology model, DefaultPrefixManager prefixes)
	{
		// set default IRI
		this.dogontIRI = "http://elite.polito.it/ontologies/dogont.owl#";
		// set the entry ontology
		this.ontModel = model;
		// complete the prefix manager
		this.prefixManager = this.setRequiredPrefixes(model, prefixes);
		// create a reasoner (HermiT)
		this.reasoner = this.setReasoner(model);
		// set the instance-level ontology manager
		this.manager = this.ontModel.getOWLOntologyManager();
	}
	
	/**
	 * Create a new HermiT reasoner. It will be buffered and will employ a
	 * progress monitor.
	 * 
	 * @param model
	 *            the {@link OWLOntology} instance for which a reasoner will be
	 *            created
	 * @return a {@link OWLReasoner} instance
	 * @throws InterruptedException
	 */
	private OWLReasoner setReasoner(OWLOntology model)
	{
		// get a reasoner (hermit)
		OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
		ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor();
		// set some properties (like in Protégé)
		Configuration config = new Configuration();
		config.ignoreUnsupportedDatatypes = true;
		config.reasonerProgressMonitor = progressMonitor;
		
		// create the reasoner instance
		return reasonerFactory.createReasoner(model, config);
	}
	
	/**
	 * Given the prefixes and namespaces read from the
	 * {@link OntologyDescriptionSet}, add the prefixes declared in the loaded
	 * ontologies.
	 * 
	 * @param ontModel
	 *            the {@link OWLOntology} instance to use for get declared
	 *            prefixes
	 * @param basePrefixes
	 *            the {@link DefaultPrefixManager} to complete
	 * @return a {@link DefaultPrefixManager} with all the needed prefixes
	 */
	private DefaultPrefixManager setRequiredPrefixes(OWLOntology ontModel, DefaultPrefixManager basePrefixes)
	{
		// init
		DefaultPrefixManager allPrefixes = new DefaultPrefixManager();
		
		// get all the prefixes from the given prefix manager
		for (String basePrefix : basePrefixes.getPrefixNames())
		{
			allPrefixes.setPrefix(basePrefix, basePrefixes.getPrefix(basePrefix));
		}
		
		// set the prefixes of the declared ontologies
		for (OWLOntology ont : ontModel.getImports())
		{
			PrefixOWLOntologyFormat pf = ontModel.getOWLOntologyManager().getOntologyFormat(ont)
					.asPrefixOWLOntologyFormat();
			for (String prefix : pf.getPrefixNames())
			{
				if (!allPrefixes.containsPrefixMapping(prefix)
						&& !allPrefixes.getPrefixName2PrefixMap().containsValue(pf.getPrefix(prefix)))
					allPrefixes.setPrefix(prefix, pf.getPrefix(prefix));
			}
		}
		
		// set DogOnt as default prefix
		String dogont = allPrefixes.getPrefix("dogont:");
		if (dogont != null)
		{
			this.setDogOntAsDefaultPrefix(allPrefixes, dogont);
		}
		else
		{
			// we need dogont in any way!
			this.setDogOntAsDefaultPrefix(allPrefixes, this.dogontIRI);
		}
		
		return allPrefixes;
	}
	
	/**
	 * Set DogOnt as default prefix
	 * 
	 * @param prefixes
	 *            the {@link DefaultPrefixManager} to operate on
	 * @param prefix
	 *            the already existing DogOnt prefix
	 */
	private void setDogOntAsDefaultPrefix(DefaultPrefixManager prefixes, String prefix)
	{
		prefixes.unregisterNamespace(prefix);
		prefixes.setDefaultPrefix(prefix);
	}
	
	/**
	 * Get all the individuals with a given IRI.
	 * 
	 * @param prefixName
	 *            the name of the prefix representing the OWL individual class
	 * @param suffix
	 *            the suffix representing the OWL individual class
	 * @return a set of {@link String} with all the individual names
	 */
	public Set<String> getAllIndividual(String prefixName, String suffix)
	{
		// init
		Set<String> individuals = new HashSet<String>();
		// get the OWL data factory
		OWLDataFactory fac = this.manager.getOWLDataFactory();
		// get a OWL class given the prefix and the suffix
		OWLClass owlIndividualClass = fac.getOWLClass(IRI.create(this.prefixManager.getPrefix(prefixName) + suffix));
		// get all the instances from the reasoner
		NodeSet<OWLNamedIndividual> individualsNodeSet = this.reasoner.getInstances(owlIndividualClass, false);
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
	 * @param prefixName
	 *            the name of the prefix representing the OWL individual class
	 * @param suffix
	 *            the suffix representing the OWL individual class
	 * @param inverse
	 *            a boolean for expressing if the type is contained in the class
	 *            represented by the suffix or not
	 * @return the specific type as a {@link String}
	 */
	public String getSpecificType(OWLNamedIndividual individual, String prefixName, String suffix, boolean inverse)
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
				if (!superc.contains(new OWLClassImpl(IRI.create(this.prefixManager.getPrefix(prefixName) + suffix))))
				{
					return this.getShortFormWithoutPrefix(type.asOWLClass());
				}
			}
			else
			{
				if (superc.contains(new OWLClassImpl(IRI.create(this.prefixManager.getPrefix(prefixName) + suffix))))
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
	 * @param prefixName
	 *            the name of the prefix for the given property
	 * @param property
	 *            the property name
	 * @return the ObjectProperty as a {@link OWLNamedIndividual}
	 */
	public OWLNamedIndividual getSingleObjectProperty(OWLNamedIndividual individual, String prefixName, String property)
	{
		Set<OWLNamedIndividual> multipleProperties = this.getMultipleObjectProperties(individual, prefixName, property);
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
	 * @param prefixName
	 *            the name of the prefix for the given property
	 * @param property
	 *            the property name
	 * @return the ObjectProperty as a set of {@link OWLNamedIndividual}
	 */
	public Set<OWLNamedIndividual> getMultipleObjectProperties(OWLNamedIndividual individual, String prefixName,
			String property)
	{
		OWLObjectPropertyImpl objProperty = new OWLObjectPropertyImpl(IRI.create(this.prefixManager
				.getPrefix(prefixName) + property));
		return this.reasoner.getObjectPropertyValues(individual, objProperty).getFlattened();
	}
	
	/**
	 * Get an annotation for a given {@link OWLNamedIndividual} instance
	 * 
	 * @param individual
	 *            the {@link OWLNamedIndividual} to get the annotation
	 * @return the requested {@link OWLAnnotation}
	 */
	public OWLAnnotation getSingleAnnotation(OWLNamedIndividual individual)
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
	public OWLNamedIndividual getOWLIndividual(String name)
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
	public Map<String, Set<String>> getDataPropertyValues(OWLNamedIndividual individual)
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
	 * Get the data property values of a specific {@link OWLNamedIndividual} and
	 * a given property IRI. This method uses the reasoner for getting the
	 * needed information.
	 * 
	 * @param individual
	 *            the {@link OWLNamedIndividual} to ask for the data property
	 * @param prefixName
	 *            the name of the prefix for the given property
	 * @param property
	 *            the property name
	 * @return a set of {@link OWLLiteral} with all the retrieved values
	 */
	public Set<OWLLiteral> getSpecificDataPropertyValues(OWLNamedIndividual individual, String prefixName,
			String property)
	{
		return this.reasoner.getDataPropertyValues(individual,
				new OWLDataPropertyImpl(IRI.create(this.prefixManager.getPrefix(prefixName) + property)));
	}
	
	/**
	 * Get the short form of a generic {@link OWLEntity} without any prefix
	 * 
	 * @param individual
	 *            the {@link OWLEntity} to get the short name
	 * @return the short name as a {@link String}
	 */
	public String getShortFormWithoutPrefix(OWLEntity individual)
	{
		String shortForm = this.prefixManager.getShortForm(individual);
		if (shortForm.equals(individual.toString()))
		{
			// the prefix mapping goes wrong... alternative way...
			shortForm = individual.getIRI().getFragment();
		}
		return shortForm.substring(shortForm.indexOf(':') + 1);
	}
	
	/**
	 * Get the "complete" {@link DefaultPrefixManager}
	 * 
	 * @return the prefixManager a {@link DefaultPrefixManager}
	 */
	public DefaultPrefixManager getPrefixManager()
	{
		return prefixManager;
	}
	
	/**
	 * Get the instances ontology
	 * 
	 * @return the ontModel a {@link OWLOntology}
	 */
	public OWLOntology getOntModel()
	{
		return ontModel;
	}
	
	/**
	 * Get the reasoner
	 * 
	 * @return the reasoner a {@link OWLReasoner}
	 */
	public OWLReasoner getReasoner()
	{
		return reasoner;
	}
}
