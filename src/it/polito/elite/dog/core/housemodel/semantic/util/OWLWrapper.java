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
package it.polito.elite.dog.core.housemodel.semantic.util;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.reasoner.ConsoleProgressMonitor;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.vocab.PrefixOWLOntologyFormat;


/**
 * The OWL Wrapper class init all the needed structures and build all the needed
 * information to successfully handle DogOnt-related ontologies.
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
	}
	
	/**
	 * Create a new HermiT reasoner. It will be buffered and will employ a
	 * progress monitor.
	 * 
	 * @param model
	 *            the {@link OWLOntology} instance for which a reasoner will be
	 *            created
	 * @return a {@link OWLReasoner} instance
	 */
	private OWLReasoner setReasoner(OWLOntology model)
	{
		// get a reasoner (hermit)
		OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
		ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor();
		OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
		
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
		for (OWLOntology ont : ontModel.getDirectImports())
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
