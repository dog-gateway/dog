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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import it.polito.elite.dog.core.housemodel.semantic.SemanticHouseModel;
import it.polito.elite.dog.core.housemodel.semantic.query.SPARQLQueryWrapper;
import it.polito.elite.dog.core.library.util.LogHelper;

import org.mindswap.pellet.jena.PelletInfGraph;
import org.osgi.service.log.LogService;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.impl.OntResourceImpl;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class ThreadedDeviceRemover extends Thread
{
	// the device(s) to remove
	private String deviceToRemove;
	
	// the OntModel object to which the device must be added
	private OntModel theHome;
	private OntModel thePlainHome;
	
	// the SemanticHouseModel object for issuing the notify change event
	private SemanticHouseModel sHouseModel;
	
	// the query wrapper to use for performing queries on the model
	private SPARQLQueryWrapper qWrapper;
	
	// the properties to be used
	private volatile Property hasCommand;
	private volatile Property hasNotification;
	private volatile Property hasFunctionality;
	private volatile Property hasState;
	private volatile Property hasStateValue;
	
	private volatile String prefix;
	
	// The logger
	private LogHelper logger;
	
	public ThreadedDeviceRemover(String deviceURI, OntModel dogOntModel, OntModel plainDogOntModel,
			SemanticHouseModel parentModel)
	{
		// fill the instance variables
		this.deviceToRemove = deviceURI;
		this.theHome = dogOntModel;
		this.thePlainHome = plainDogOntModel;
		this.sHouseModel = parentModel;
		this.qWrapper = parentModel.getQWrapper();
		
		this.prefix = this.sHouseModel.getNamespaces().get("dogOnt") + "#";
		
		// get the main relations involving the device
		this.hasCommand = this.theHome.getProperty(prefix + "hasCommand");
		this.hasNotification = this.theHome.getProperty(prefix + "hasNotification");
		this.hasFunctionality = this.theHome.getProperty(prefix + "hasFunctionality");
		this.hasState = this.theHome.getProperty(prefix + "hasState");
		this.hasStateValue = this.theHome.getProperty(prefix + "hasStateValue");
		// init the logger
		this.logger = this.sHouseModel.getLogger();
	}
	
	// TODO: check if it works or not....
	public void run()
	{
		// first disable reasoned model change detection
		((PelletInfGraph) this.theHome.getGraph()).setAutoDetectChanges(false);
		
		// here the given device shall be removed, including all the related
		// instances
		
		// first, get the ontology individual representing the device,
		// please notice that here there is no "safe" notion on the correctness
		// of
		// the given device URI. Therefore we adopt the following policy: if the
		// given device URI
		// is in the short form (only the device unique name) the model entry
		// point URI is used for
		// expanding the given name to a full URI format. If no individual can
		// be found the process
		// is aborted and the error is logged
		
		// the Jena individual representing the individual to remove
		Individual deviceToRemoveInd;
		
		// check if it is a real URI
		URI devUri = null;
		try
		{
			devUri = new URI(deviceToRemove);
		}
		catch (URISyntaxException e)
		{
			logger.log(LogService.LOG_WARNING, "[SemanticHouseModel] The given device has not a valid URI format.\n" + e);
		}
		if ((devUri!=null) && (devUri.isAbsolute()))
			deviceToRemoveInd = this.thePlainHome.getIndividual(this.deviceToRemove);
		else
			deviceToRemoveInd = this.thePlainHome.getIndividual(this.sHouseModel.getEntryPoint() + "#"
					+ this.qWrapper.toShortForm(this.deviceToRemove));
		
		// check if an individual has been found, otherwise log the error and
		// close the task
		if (deviceToRemoveInd != null)
		{
			// Create a set of OntResources to remove
			ArrayList<OntResourceImpl> toRemove = new ArrayList<OntResourceImpl>();
			
			// if here the individual is not null, then we can start to harvest
			// the related instances so as to
			// remove them in a clean way...
			// TODO: check if removal shall be open or can be a little bit
			// hard-wired (open is actually difficult...
			
			// get all the functionality instances
			NodeIterator fIter = deviceToRemoveInd.listPropertyValues(this.hasFunctionality);
			
			// iterate over the functionalities associated to the device
			while (fIter.hasNext())
			{
				// the RDF node representing the functionality individual
				RDFNode fNode = fIter.next();
				
				// check individual
				// if(fNode instanceof Individual)
				// {
				// get the property values
				NodeIterator cIter = ((OntResourceImpl) fNode).listPropertyValues(this.hasCommand);
				while (cIter.hasNext())
				{
					RDFNode cNode = cIter.next();
					
					// if(cNode instanceof Individual)
					// {
					toRemove.add((OntResourceImpl) cNode);
					// }
				}
				
				// get the property values
				NodeIterator nIter = ((OntResourceImpl) fNode).listPropertyValues(this.hasNotification);
				while (nIter.hasNext())
				{
					RDFNode nNode = nIter.next();
					
					// if(nNode instanceof Individual)
					// {
					toRemove.add((OntResourceImpl) nNode);
					// }
				}
				// }
				
				toRemove.add((OntResourceImpl) fNode);
			}
			
			// get all the state instances
			NodeIterator sIter = deviceToRemoveInd.listPropertyValues(this.hasState);
			
			// iterate over the states associated to the device
			while (sIter.hasNext())
			{
				// the RDF node representing the state individual
				RDFNode sNode = sIter.next();
				
				// check individual
				// if(sNode instanceof Individual)
				// {
				// get the property values
				NodeIterator svIter = ((OntResourceImpl) sNode).listPropertyValues(this.hasStateValue);
				while (svIter.hasNext())
				{
					RDFNode svNode = svIter.next();
					
					// if(svNode instanceof Individual)
					// {
					toRemove.add((OntResourceImpl) svNode);
					// }
				}
				
				toRemove.add((OntResourceImpl) sNode);
				// }
			}
			
			toRemove.add((OntResourceImpl) deviceToRemoveInd);
			
			// remove all
			this.thePlainHome.enterCriticalSection(true);
			for (OntResourceImpl toRemoveNow : toRemove)
			{
				toRemoveNow.remove();
			}
			this.thePlainHome.leaveCriticalSection();
			
			// here the device has been completely removed,
			// send the updated model notification and log removal
			this.logger.log(LogService.LOG_INFO, "[ThreadedDeviceRemover] removed: " + this.deviceToRemove);
			
			// re-enable automatic change detection
			// first disable reasoned model change detection
			((PelletInfGraph) this.theHome.getGraph()).setAutoDetectChanges(true);
			
			// send notification
			//this.sHouseModel.notifyRemovedDevice(new DeviceDescriptor(this.deviceToRemove));
			
		}
		else
		{
			// log the error...
			this.logger.log(LogService.LOG_WARNING,
					"[ThreadedDeviceRemover] failed to locate the individual to remove.");
		}
	}
}
