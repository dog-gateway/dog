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

import java.util.Set;

import org.mindswap.pellet.jena.PelletInfGraph;
import org.osgi.service.log.LogService;

import it.polito.elite.dog.core.housemodel.semantic.SemanticHouseModel;
import it.polito.elite.dog.core.housemodel.semantic.query.SPARQLQueryWrapper;
import it.polito.elite.dog.core.library.model.DeviceDescriptor;
import it.polito.elite.dog.core.library.util.LogHelper;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.shared.Lock;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class ThreadedDeviceAdder extends Thread
{
	// instance variable: the description of the device to add
	private DeviceDescriptor deviceDesc;
	
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
	private volatile Property isIn;
	private volatile OntClass notificationFunctionality;
	private volatile String prefix;
	
	private LogHelper logger;
	
	public ThreadedDeviceAdder(DeviceDescriptor deviceDesc, OntModel dogOntModel, OntModel plainDogOntModel,
			SemanticHouseModel parentModel)
	{
		// fill the instance variables
		this.deviceDesc = deviceDesc;
		this.theHome = dogOntModel;
		this.thePlainHome = plainDogOntModel;
		this.sHouseModel = parentModel;
		this.qWrapper = parentModel.getQWrapper();
		
		// initialize the properties pointers
		this.prefix = this.sHouseModel.getNamespaces().get("dogOnt") + "#";
		this.hasCommand = this.theHome.getProperty(prefix + "hasCommand");
		this.hasNotification = this.theHome.getProperty(prefix + "hasNotification");
		this.hasFunctionality = this.theHome.getProperty(prefix + "hasFunctionality");
		this.hasState = this.theHome.getProperty(prefix + "hasState");
		this.hasStateValue = this.theHome.getProperty(prefix + "hasStateValue");
		this.isIn = this.theHome.getProperty(prefix + "isIn");
		this.notificationFunctionality = this.theHome.getOntClass(prefix + "NotificationFunctionality");
		
		// get the logger
		this.logger = this.sHouseModel.getLogger();
	}
	
	public void run()
	{
		// first disable reasoned model change detection
		((PelletInfGraph) this.theHome.getGraph()).setAutoDetectChanges(false);
		
		// here the new device shall be added, so first we need to understand to
		// which class the device belongs
		// therefore we need to first get the device category
		String devCategory = this.deviceDesc.getDeviceCategory();
		
		// change it into a short form containing only the device class name,
		// since we do not know if its provided as either a full URI, as a
		// namespace:name string
		// or as a simple class name
		devCategory = this.qWrapper.toShortForm(devCategory);
		
		// log
		this.logger.log(LogService.LOG_INFO, "Loading specification for a new " + devCategory);
		
		// check if the class exists, otherwise exit (here some clever device
		// type discovery can take place)
		OntClass deviceClass = this.theHome.getOntClass(prefix + devCategory);
		
		if (deviceClass != null)
		{
			// first we create an instance for this device class
			String deviceIndName = this.sHouseModel.getEntryPoint() + "#" + // this.qWrapper.toShortForm(devCategory)+
					// "_"+
					this.deviceDesc.getDeviceURI();
			
			this.thePlainHome.enterCriticalSection(Lock.WRITE);
			Individual deviceInd = this.thePlainHome.createIndividual(deviceIndName, deviceClass);
			this.thePlainHome.leaveCriticalSection();
			
			// here we need to understand which functionalities and states are
			// associated to the given device class
			// so that we can start building the corresponding instances...
			
			/*************************** FUNCTIONALITIES ****************************************************/
			
			// search for the device class functionalities
			Set<String> functionalities = qWrapper.getDevFunctionalities(devCategory);
			
			// log
			this.logger.log(LogService.LOG_INFO, "Found " + functionalities.size() + " functionalities: "
					+ functionalities);
			
			// iterate over each functionality for getting the associated
			// commands
			Individual currentDevFunctionality;
			for (String functionality : functionalities)
			{
				// create the proper functionality instance
				currentDevFunctionality = this.createFunctionalityInstance(prefix + functionality);
				
				// add the functionality...
				this.thePlainHome.enterCriticalSection(Lock.WRITE);
				deviceInd.setPropertyValue(this.hasFunctionality, currentDevFunctionality);
				this.thePlainHome.leaveCriticalSection();
			}
			
			/*************************** STATES ****************************************************/
			
			// search for the device class states
			Set<String> states = qWrapper.getDevStates(devCategory);
			
			// log
			this.logger.log(LogService.LOG_INFO, "Found " + states.size() + " states: " + states);
			
			// iterate over the found states
			Individual currentDevState;
			for (String state : states)
			{
				// create the proper state instance
				currentDevState = this.createStateInstance(prefix + state);
				
				// add the state
				// add the functionality...
				this.thePlainHome.enterCriticalSection(Lock.WRITE);
				deviceInd.setPropertyValue(this.hasState, currentDevState);
				this.thePlainHome.leaveCriticalSection();
			}
			
			/*************************** ISIN ****************************************************/
			
			// get the most general building environment instance
			String topMostContainerURI = this.qWrapper.getTopMostRoomContainerInstance();
			
			// get the corresponding Individual
			Individual topMostContainer = this.theHome.getIndividual(topMostContainerURI);
			
			// add the isIn
			this.thePlainHome.enterCriticalSection(Lock.WRITE);
			deviceInd.setPropertyValue(this.isIn, topMostContainer);
			this.thePlainHome.leaveCriticalSection();
			
			/*************************** The network technology *********************************/
			// get the device technology
			String tech = this.deviceDesc.getTechnology();
			
			// if it si not null, compose the component name
			if ((tech != null) && (!tech.isEmpty()))
				tech = tech + "Component";
			else
				tech = "EliteComponent";
			
			// get all the component classes and verify the name
			Set<String> componentClasses = this.qWrapper.getAllComponentClasses();
			
			// iterate to find the correct one, if no correspondence is found no
			// class is added
			for (String cComponent : componentClasses)
			{
				if (tech.equalsIgnoreCase(cComponent))
				{
					// add the component as super class
					OntClass componentClass = this.theHome.getOntClass(prefix + cComponent);
					
					// set the just created device as an instance of the
					// currently identified component class
					this.thePlainHome.enterCriticalSection(Lock.WRITE);
					deviceInd.addOntClass(componentClass);
					this.thePlainHome.leaveCriticalSection();
				}
			}
			this.theHome.prepare();
			this.logger.log(LogService.LOG_DEBUG, "Added device: " + deviceIndName);
			
			// re-enable automatic change detection
			// first disable reasoned model change detection
			((PelletInfGraph) this.theHome.getGraph()).setAutoDetectChanges(true);
			
		}
		
	}
	
	/******************************************************************************************************************
	 * 
	 * Methods for creating new Functionalities
	 * 
	 *****************************************************************************************************************/
	
	/**
	 * 
	 * @param functionalityURI
	 * @return
	 */
	
	private Individual createFunctionalityInstance(String functionalityURI)
	{
		Individual functionalityInd = null;
		
		// create the functionality individual
		OntClass functionalityClass = this.theHome.getOntClass(functionalityURI);
		if (functionalityClass != null)
		{
			String functionalityName = this.sHouseModel.getEntryPoint() + "#" + this.deviceDesc.getDeviceURI() + "_"
					+ this.qWrapper.toShortForm(functionalityURI);
			
			// create the functionality individual
			this.thePlainHome.enterCriticalSection(Lock.WRITE);
			functionalityInd = this.thePlainHome.createIndividual(functionalityName, functionalityClass);
			this.thePlainHome.leaveCriticalSection();
			
			// decide which method to call
			if (((OntClass) functionalityClass).hasSuperClass(this.notificationFunctionality))
			{
				createNotificationFunctionalityRelatedInstances(functionalityInd, functionalityURI);
			}
			else
			{
				createQueryAndControlFunctionalityRelatedInstances(functionalityInd, functionalityURI);
			}
		}
		return functionalityInd;
	}
	
	private void createNotificationFunctionalityRelatedInstances(Individual functionalityInd, String functionalityURI)
	{
		// get the notifications associated to the given functionality
		Set<String> notificationClasses = this.qWrapper.getFunctionalityNotifications(functionalityURI);
		
		// for all the notifications...create a corresponding instance
		String indName;
		Resource cmdRes;
		Individual notificationInd;
		for (String currentNotification : notificationClasses)
		{
			// get the notification resource
			cmdRes = this.theHome.getResource(currentNotification);
			
			if (cmdRes != null)
			{
				// if the notification class exists, create the individual
				indName = this.sHouseModel.getEntryPoint() + "#" + this.deviceDesc.getDeviceURI() + "_"
						+ this.qWrapper.toShortForm(currentNotification);
				
				this.thePlainHome.enterCriticalSection(Lock.WRITE);
				notificationInd = this.thePlainHome.createIndividual(indName, cmdRes);
				
				// associate the notification to the functionality
				functionalityInd.setPropertyValue(hasNotification, notificationInd);
				this.thePlainHome.leaveCriticalSection();
			}
		}
		
	}
	
	private void createQueryAndControlFunctionalityRelatedInstances(Individual functionalityInd, String functionalityURI)
	{
		
		// get the commands associated to the given functionality
		Set<String> commandClasses = this.qWrapper.getFunctionalityCommands(functionalityURI);
		
		// for all the commands...create a corresponding instance
		String indName;
		Resource cmdRes;
		Individual commandInd;
		for (String currentCommand : commandClasses)
		{
			// get the command resource
			cmdRes = this.theHome.getResource(currentCommand);
			
			if (cmdRes != null)
			{
				// if the command class exists, create the individual
				indName = this.sHouseModel.getEntryPoint() + "#" + this.deviceDesc.getDeviceURI() + "_"
						+ this.qWrapper.toShortForm(currentCommand);
				
				this.thePlainHome.enterCriticalSection(Lock.WRITE);
				commandInd = this.thePlainHome.createIndividual(indName, cmdRes);
				
				// associate the command to the functionality
				functionalityInd.setPropertyValue(hasCommand, commandInd);
				this.thePlainHome.leaveCriticalSection();
			}
		}
		
	}
	
	/******************************************************************************************************************
	 * 
	 * Methods for creating new States
	 * 
	 *****************************************************************************************************************/
	
	/**
	 * 
	 */
	private Individual createStateInstance(String stateURI)
	{
		Individual stateInd = null;
		
		// get the state class
		OntClass stateClass = this.theHome.getOntClass(stateURI);
		if (stateClass != null)
		{
			String stateName = this.sHouseModel.getEntryPoint() + "#" + this.deviceDesc.getDeviceURI() + "_"
					+ this.qWrapper.toShortForm(stateURI);
			
			// create the state individual
			this.thePlainHome.enterCriticalSection(Lock.WRITE);
			stateInd = this.thePlainHome.createIndividual(stateName, stateClass);
			this.thePlainHome.leaveCriticalSection();
			
			// create the state values
			this.createStateRelatedInstances(stateInd, stateURI);
			
		}
		
		return stateInd;
	}
	
	private void createStateRelatedInstances(Individual stateInd, String stateURI)
	{
		// get all the state values associated to this state
		Set<String> stateValueClasses = qWrapper.getStateValues(stateURI);
		
		// for all the state values...create a corresponding instance
		String indName;
		Resource stateValueRes;
		Individual stateValueInd;
		for (String stateValueClass : stateValueClasses)
		{
			// get the command resource
			stateValueRes = this.theHome.getResource(stateValueClass);
			
			if (stateValueRes != null)
			{
				// if the command class exists, create the individual
				indName = this.sHouseModel.getEntryPoint() + "#" + this.deviceDesc.getDeviceURI() + "_"
						+ this.qWrapper.toShortForm(stateValueClass);
				
				this.thePlainHome.enterCriticalSection(Lock.WRITE);
				stateValueInd = this.thePlainHome.createIndividual(indName, stateValueRes);
				
				// associate the command to the functionality
				stateInd.setPropertyValue(hasStateValue, stateValueInd);
				this.thePlainHome.leaveCriticalSection();
			}
		}
		
	}
}
