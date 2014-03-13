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

import it.polito.elite.dog.core.housemodel.semantic.util.OntologyDescriptor;
import it.polito.elite.dog.core.housemodel.semantic.util.OntologyDescriptorSet;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class QuickModelLoader
{
	private OntModel model;
	private OntModel pModel;
	private HashMap<String, String> namespaces;
	private OntDocumentManager manager;
	private String modelToLoad;
	private String modelToLoadNS;
	private Logger logger;
	
	public enum ModelTypes
	{
		REASONED_WITH_PELLET, REASONED_WITH_JENA, PLAIN_IN_MEMORY
	};
	
	private ModelTypes modelType = ModelTypes.REASONED_WITH_PELLET;
	
	public QuickModelLoader(OntologyDescriptorSet models, ModelTypes modelType)
	{
		this.modelType = modelType;
		
		this.manager = OntDocumentManager.getInstance();
		
		// init the namespace map
		this.namespaces = new HashMap<String, String>();
		
		this.logger = Logger.getAnonymousLogger();
		
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
					this.logger.log(Level.INFO,
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
	
	public void loadOntology()
	{
		switch (this.modelType)
		{
			case REASONED_WITH_PELLET:
			{
				try
				{
					// create a URL for the ontology file to open
					URL fOnto = new URL(this.modelToLoad);
					
					// open the connection
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
					this.model = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC, pModel);
					this.model.setStrictMode(false);
					
					this.model.prepare();
					
					elapsedTime = System.currentTimeMillis() - elapsedTime;
					
					// ended loading... die...
					this.logger.log(Level.INFO, dumpClassCheck());
					this.logger.log(Level.INFO, "N Triples: " + this.model.size());
					this.logger.log(Level.INFO, "Loaded the ontology after " + elapsedTime + "ms........now dying");
					
					// left here to check problems when logger is not working
					System.err.println("N Triples: " + this.model.size());
					System.err.println("Loaded the ontology after " + elapsedTime + "ms........now dying");
					
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
					
					File fOnto = new File(this.modelToLoad);
					
					// read the saved reasoning information
					final FileInputStream reasonedModelStream = new FileInputStream(fOnto);
					
					this.pModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
					this.pModel.setStrictMode(false);
					
					// profiling
					long elapsedTime = System.currentTimeMillis();
					
					// read the model
					this.pModel.read(reasonedModelStream, this.modelToLoadNS);
					
					// create a pellet-based model over the plain model
					this.model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF, pModel);
					this.model.setStrictMode(false);
					
					this.model.prepare();
					
					elapsedTime = System.currentTimeMillis() - elapsedTime;
					
					// ended loading... die...
					this.logger.log(Level.INFO, dumpClassCheck());
					this.logger.log(Level.INFO, "N Triples: " + this.model.size());
					this.logger.log(Level.INFO, "Loaded the ontology after " + elapsedTime + "ms........now dying");
					
					// left here to check problems when logger is not working
					System.err.println("N Triples: " + this.model.size());
					System.err.println("Loaded the ontology after " + elapsedTime + "ms........now dying");
					
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
					File fOnto = new File(this.modelToLoad);
					
					// read the saved reasoning information
					final FileInputStream reasonedModelStream = new FileInputStream(fOnto);
					
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
					this.logger.log(Level.INFO, dumpClassCheck());
					this.logger.log(Level.INFO, "N Triples: " + this.model.size());
					this.logger.log(Level.INFO, "Loaded the ontology after " + elapsedTime + "ms........now dying");
					
					// left here to check problems when logger is not working
					System.err.println("N Triples: " + this.model.size());
					System.err.println("Loaded the ontology after " + elapsedTime + "ms........now dying");
					
				}
				catch (Exception e)
				{
					System.err.println("The ontology file (" + this.modelToLoad
							+ ") to load does not exist or is not readable!\n" + e);
				}
				
				break;
			}
		}
		
		// the model has been read....
	}
	
	/**
	 * @return the namespaces
	 */
	public HashMap<String, String> getNamespaces()
	{
		return namespaces;
	}
	
	/**
	 * @return the modelToLoadNS
	 */
	public String getModelToLoadNS()
	{
		return modelToLoadNS;
	}
	
	/**
	 * @return the reasoned model
	 */
	public OntModel getModel()
	{
		return model;
	}
	
	/**
	 * @return the plain model
	 */
	public OntModel getpModel()
	{
		return pModel;
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
