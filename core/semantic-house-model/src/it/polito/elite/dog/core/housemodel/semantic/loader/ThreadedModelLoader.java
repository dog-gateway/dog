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
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.osgi.service.log.LogService;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

/**
 * A Thread class for loading the ontology-based model, automatically deals with
 * all the imported ontologies...
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class ThreadedModelLoader extends Thread
{
	private SemanticHouseModel sHouseModel;
	private OntModel model;
	private OntModel pModel;
	private HashMap<String, String> namespaces;
	private OntDocumentManager manager;
	private String modelToLoad;
	private String modelToLoadNS;
	private LogHelper logger;
	
	/**
	 * This public {@link enum} defines the behavior of the threaded model
	 * loader. Two operating modes are currently provided:
	 * <ul>
	 * <li>LOAD_REPLACE which loads a model and replaces the model used by the
	 * semantic house model bundle calling this loader (results in a call to the
	 * <em>setModel()</em> method of the SemanticHouseModel)</li>
	 * <li>LOAD_MERGE which loads a model and adds it to the one held by the
	 * semantic house model using this threaded loader (results in a call to the
	 * <em>addModel()</em> method of the SemanticHouseModel)
	 */
	public enum LoadingModes
	{
		LOAD_REPLACE, LOAD_MERGE
	};
	
	private LoadingModes loadingMode = LoadingModes.LOAD_REPLACE;
	
	public enum ModelTypes
	{
		REASONED_WITH_PELLET, REASONED_WITH_JENA, PLAIN_IN_MEMORY
	};
	
	private ModelTypes modelType = ModelTypes.REASONED_WITH_PELLET;
	
	/**
	 * * A model loader for the semantic house model so that ontology loading
	 * and time consuming consistency checks can be accomplished in parallel to
	 * the loading and start-up of all the other DOG bundles.
	 * 
	 * @param sHouseModel
	 *            The SemanticHouseModel bundle using this helper thread
	 */
	public ThreadedModelLoader(SemanticHouseModel sHouseModel)
	{
		super();
		
		// store the context information
		this.sHouseModel = sHouseModel;
		
		// init the logger
		this.logger = this.sHouseModel.getLogger();
		
		// the global manager to manage redirection of ontology imports
		this.manager = OntDocumentManager.getInstance();
		
		// init the namespace map
		this.namespaces = new HashMap<String, String>();
	}
	
	
	public void setModelToLoad(OntologyDescriptorSet models, LoadingModes loadingMode, ModelTypes modelType)
	{
		// parses the set of ontology descriptors and
		// fill the Jena document manager and the inner namespaces map
		
		// set the current operating mode, synchronized so as to avoid
		// concurrent
		// modifications between
		synchronized (this.loadingMode)
		{
			this.loadingMode = loadingMode;
			this.modelType = modelType;
		}
		// System.err.println("IMPORTANT Model" + modelType.toString());
		// System.err.println("IMPORTANT" + this.modelType.toString());
		
		// the file object to check that the local copies actually exist
		File fCheck = null;
		
		// add alternative locations for all ontologies
		for (OntologyDescriptor model : models)
		{
			OntologyDescriptor entryDesc = models.getEntryPoint();
			
			if (!model.getLocalCopy().isEmpty())
			{
				// check if the file is specified as an absolute reference or
				// not
				fCheck = new File(model.getLocalCopy());
				if (!fCheck.isAbsolute())
					fCheck = new File(System.getProperty("configFolder", ".") + "/" + model.getLocalCopy());
				
				// check the file existence
				if (fCheck.exists())
				{
					synchronized (this.manager)
					{
						// ok, add the alias
						this.manager.addAltEntry(model.getURL(), "file:///" + fCheck.getAbsolutePath());
					}
					
					// debug
					logger.log(LogService.LOG_INFO,
							"loaded: " + model.getURL() + "\n\tfrom local copy " + model.getLocalCopy()
									+ "\n\twith namespace " + model.getNamespacePrefix());
				}
				
				// extract data about the model to load
				File entryCheck = new File(entryDesc.getLocalCopy());
				if (!entryCheck.isAbsolute())
				{
					this.modelToLoad = this.manager.doAltURLMapping(entryDesc.getURL());
				}
				else
				{
					this.modelToLoad = entryCheck.toURI().toString();
				}
			}
			else
			{
				this.modelToLoad = entryDesc.getURL();
			}
			
			synchronized (this.namespaces)
			{
				// add the namespace
				this.namespaces.put(model.getNamespacePrefix(), model.getURL());
			}
			
			this.modelToLoadNS = entryDesc.getURL();
		}
		
	}
	
	// the running code, will run only once and then it will exit. This is
	// actually a simple worker thread
	public void run()
	{
		// System.err.println("IMPORTANT" + this.modelType.toString());
		switch (this.modelType)
		{
			case REASONED_WITH_PELLET:
			{
				try
				{
					//create a URL for the ontology file to open
					URL fOnto = new URL(this.modelToLoad);
					
					//open the connection
					URLConnection connection = fOnto.openConnection();
					
					// read the saved reasoning information
					final InputStream reasonedModelStream = connection.getInputStream();
					
					this.logger.log(LogService.LOG_INFO, "PATH:" + fOnto.toString());
					this.pModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
					this.pModel.setStrictMode(false);
					
					// profiling
					long elapsedTime = System.currentTimeMillis();
					
					// read the model
					this.pModel.read(reasonedModelStream, this.modelToLoadNS);
					
					// create a pellet-based model over the plain model
					this.model = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC, pModel);
					this.model.setStrictMode(false);
					
					this.model.prepare();
					
					elapsedTime = System.currentTimeMillis() - elapsedTime;
					
					// ended loading... die...
					this.logger.log(LogService.LOG_DEBUG, dumpClassCheck());
					this.logger.log(LogService.LOG_INFO, "N Triples: " + this.model.size());
					this.logger.log(LogService.LOG_INFO, "Loaded the ontology after " + elapsedTime
							+ "ms........now dying");
					
					// left here to check problems when logger is not working
					// System.err.println("N Triples pellet: " +
					// this.model.size());
					// System.err.println("Loaded the ontology after " +
					// elapsedTime + "ms........now dying");
					
				}
				catch (Exception e)
				{
					System.err.println("The ontology file (" + this.modelToLoad
							+ ") to load does not exist or is not readable!\n" + e);
				}
				
				break;
			}
			case REASONED_WITH_JENA:
			{
				try
				{
					
					//create a URL for the ontology file to open
					URL fOnto = new URL(this.modelToLoad);
					
					//open the connection
					URLConnection connection = fOnto.openConnection();
					
					// read the saved reasoning information
					final InputStream reasonedModelStream = connection.getInputStream();
					
					this.pModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
					this.pModel.setStrictMode(false);
					
					// profiling
					long elapsedTime = System.currentTimeMillis();
					
					// read the model
					this.pModel.read(reasonedModelStream, this.modelToLoadNS);
					
					// create a pellet-based model over the plain model
					this.model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF, pModel); //OWL_MEM_MICRO_RULE_INF, pModel);
					this.model.setStrictMode(false);
					
					this.model.prepare();
					
					elapsedTime = System.currentTimeMillis() - elapsedTime;
					
					// ended loading... die...
					this.logger.log(LogService.LOG_DEBUG, dumpClassCheck());
					this.logger.log(LogService.LOG_INFO, "N Triples: " + this.model.size());
					this.logger.log(LogService.LOG_INFO, "Loaded the ontology after " + elapsedTime
							+ "ms........now dying");
					
					// left here to check problems when logger is not working
					// System.err.println("N Triples Jena: " +
					// this.model.size());
					// System.err.println("Loaded the ontology after " +
					// elapsedTime + "ms........now dying");
					
				}
				catch (Exception e)
				{
					System.err.println("The ontology file (" + this.modelToLoad
							+ ") to load does not exist or is not readable!\n" + e);
				}
				
				break;
			}
			case PLAIN_IN_MEMORY:
			{
				try
				{
					//create a URL for the ontology file to open
					URL fOnto = new URL(this.modelToLoad);
					
					//open the connection
					URLConnection connection = fOnto.openConnection();
					
					// read the saved reasoning information
					final InputStream reasonedModelStream = connection.getInputStream();
					
					// create an in-memory model
					this.model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
					this.model.setStrictMode(true);
					
					// profiling
					long elapsedTime = System.currentTimeMillis();
					
					// read the model
					this.model.read(reasonedModelStream, this.modelToLoadNS);
					this.pModel = this.model;
					
					elapsedTime = System.currentTimeMillis() - elapsedTime;
					
					// ended loading... die...
					this.logger.log(LogService.LOG_DEBUG, dumpClassCheck());
					this.logger.log(LogService.LOG_INFO, "N Triples: " + this.model.size());
					this.logger.log(LogService.LOG_INFO, "Loaded the ontology after " + elapsedTime
							+ "ms........now dying");
					
					// left here to check problems when logger is not working
					// System.err.println("N Triples Simple: " +
					// this.model.size());
					// System.err.println("Loaded the ontology after " +
					// elapsedTime + "ms........now dying");
					
				}
				catch (Exception e)
				{
					this.logger.log(LogService.LOG_ERROR, "The ontology file (" + this.modelToLoad
							+ ") to load does not exist or is not readable!\n" + e);
				}
				
				break;
			}
		}
		
		// the model has been read.... (debug/log and return it to the
		// main HouseModel object;
		switch (this.loadingMode)
		{
			case LOAD_REPLACE:
			{
				this.sHouseModel.setModel(this.pModel, this.model, this.namespaces, this.modelToLoadNS);
				break;
			}
			case LOAD_MERGE:
			{
				this.sHouseModel.addModel(this.model, this.namespaces, this.modelToLoadNS);
				break;
			}
		}
		
	}
	
	/**
	 * Private class for dumping all the classes of the loaded ontology on a
	 * string...it allows checking for effective import management...
	 * 
	 * @return A {@link String} instance containing the dump of all loaded
	 *         classes...
	 */
	private String dumpClassCheck()
	{
		StringBuffer bf = new StringBuffer();
		ExtendedIterator<OntClass> it = this.model.listClasses();
		int i = 0;
		while (it.hasNext())
		{
			bf.append(it.next().getLocalName());
			bf.append("\n");
			i++;
		}
		bf.append("Total classes: " + i);
		return bf.toString();
	}
}
