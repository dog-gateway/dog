/*
 * Dog - Core
 * 
 * Copyright (c) 2009-2014 Dario Bonino and Luigi De Russis
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
package it.polito.elite.dog.core.housemodel.semantic.loader;

import it.polito.elite.dog.core.housemodel.semantic.SemanticHouseModel;
import it.polito.elite.dog.core.library.semantic.util.OntologyDescriptor;
import it.polito.elite.dog.core.library.semantic.util.OntologyDescriptorSet;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.io.File;

import org.osgi.service.log.LogService;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.SimpleIRIMapper;

/**
 * Open and load the requested set of {@link OWLOntology} objects.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class ThreadedModelLoader implements Runnable
{
	// the HouseModel instance
	private SemanticHouseModel houseModelInstance;
	// the logger
	private LogHelper logger;
	// the ontology manager
	private OWLOntologyManager manager;
	// available loading modes
	private LoadingModes loadingMode;
	// the name of the model to load
	private String modelToLoad;
	// the ontology to load
	private OWLOntology ontModel;
	// OWL prefix manager
	private DefaultPrefixManager prefixes;
	
	/**
	 * Default constructor.
	 * 
	 * @param houseModel
	 *            the {@link SemanticHouseModel} instance to use
	 */
	public ThreadedModelLoader(SemanticHouseModel houseModel)
	{
		this.houseModelInstance = houseModel;
		this.logger = houseModel.getLogger();
		this.manager = OWLManager.createOWLOntologyManager();
		// default loading mode: replace the current model (if any)
		this.loadingMode = LoadingModes.LOAD_REPLACE;
		this.prefixes = new DefaultPrefixManager();
	}
	
	/**
	 * Set the models to load.
	 * 
	 * @param models
	 *            an {@link OntologyDescriptorSet} containing the references to
	 *            the ontologies to load
	 * @param loadingMode
	 *            the requested loading mode
	 */
	public synchronized void setModelToLoad(OntologyDescriptorSet models, LoadingModes loadingMode)
	{
		this.loadingMode = loadingMode;
		
		// get the entry point, i.e., the ontology that imports all the other
		// ontologies in the set
		OntologyDescriptor entryDesc = models.getEntryPoint();
		
		// the file object to check that the local copies actually exist
		File fCheck = null;
		
		for (OntologyDescriptor model : models)
		{
			if (!model.getLocalCopy().isEmpty())
			{
				// local copy
				fCheck = new File(model.getLocalCopy());
				if (!fCheck.isAbsolute())
					fCheck = new File(System.getProperty("configFolder", ".") + "/" + model.getLocalCopy());
				
				// check the file existence
				if (fCheck.exists())
				{
					// use the local copy
					this.manager.addIRIMapper(new SimpleIRIMapper(IRI.create(model.getURL()), IRI.create(fCheck)));
					
					// debug
					logger.log(LogService.LOG_DEBUG,
							"loaded: " + model.getURL() + "\n\tfrom local copy " + model.getLocalCopy()
									+ "\n\twith namespace " + model.getNamespacePrefix());
				}
			}
			
			// store all the ontologies prefixes and namespaces
			this.prefixes.setPrefix(model.getNamespacePrefix().toLowerCase() + ":", model.getURL() + "#");
		}
		
		// set the entry point ontology URI
		this.modelToLoad = entryDesc.getURL();
	}
	
	@Override
	public void run()
	{
		try
		{
			// load the ontology
			this.ontModel = this.manager.loadOntology(IRI.create(this.modelToLoad));
			
			this.logger.log(
					LogService.LOG_INFO,
					"Loaded ontology: " + this.ontModel.getOntologyID().getOntologyIRI() + " ["
							+ this.ontModel.getAxiomCount() + " axioms]\nfrom "
							+ this.manager.getOntologyDocumentIRI(this.ontModel));
		}
		catch (OWLOntologyCreationException e)
		{
			// log the error and stop the process
			this.logger.log(LogService.LOG_ERROR, "Impossible to load the ontology... interrupting...", e);
			this.loadingMode = LoadingModes.UNDEFINED;
		}
		
		switch (this.loadingMode)
		{
			case LOAD_REPLACE:
			{
				this.houseModelInstance.setModel(this.ontModel, this.prefixes);
				break;
			}
			case LOAD_MERGE:
			{
				// TODO fill
				break;
			}
			default:
				// do nothing, an error occurs
				break;
		}
	}
	
}
