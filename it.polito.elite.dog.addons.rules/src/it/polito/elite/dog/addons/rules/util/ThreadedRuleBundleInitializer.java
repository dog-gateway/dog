/*
 * Dog 2.0 - Addons
 * 
 * Copyright [2011]
 * [Emiliano Castellina (emiliano.castellina@polito.it), Politecnico di Torino]
 * [Dario Bonino (dario.bonino@polito.it), Politecnico di Torino]
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package it.polito.elite.dog.addons.rules.util;

import java.io.File;
import java.io.StringReader;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.osgi.service.log.LogService;

import it.polito.elite.dog.addons.rules.RuleEngine;
import it.polito.elite.dog.addons.rules.schemalibrary.RuleList;
import it.polito.elite.domotics.dog2.doglibrary.message.DogMessage;

public class ThreadedRuleBundleInitializer extends Thread
{
	// the pointer to the rule bundle that this thread should initialize
	private RuleEngine bundleToInitialize;
	private String ruleBasePath;
	
	// the logger service needed for logging the Thread operations
	private LogService logger;
	
	// an XML2DRL
	private Xml2DrlTranslator xml2drl;
	
	// the pre processor needed to transform time-based events in e-blocks into
	// timed notifications
	private TimedNotificationsPreProcessor tProcessor;
	
	/**
	 * The Initializer constructor, takes the rule bundle to initialize as
	 * parameter
	 * 
	 * @param bundleToInitialize
	 */
	public ThreadedRuleBundleInitializer(RuleEngine bundleToInitialize, String ruleBasePath)
	{
		this.bundleToInitialize = bundleToInitialize;
		this.logger = bundleToInitialize.getLogger();
		this.xml2drl = new Xml2DrlTranslator();
		this.tProcessor = new TimedNotificationsPreProcessor(this.logger);
		this.ruleBasePath = ruleBasePath;
	}
	
	/**
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		// init the rule bundle
		
		// ------------ PHASE 1: load and parse the local rule store
		// -------------------------------------------
		this.logger.log(LogService.LOG_DEBUG, "[DogRulesBundle] Starting to parse the local rule store....");
		long eta = System.currentTimeMillis();
		// parse the rule store XML file into a JAXB class tree
		RuleList localRules = this.parseLocalRuleStore(this.ruleBasePath);
		// set the JAXB rule base of the bundle to initialize
		this.bundleToInitialize.setLocalRuleBaseJAXB(localRules);
		eta = System.currentTimeMillis() - eta;
		this.logger.log(LogService.LOG_DEBUG, "[DogRulesBundle] parsed the local rule store..... ETA:" + eta);
		this.logger.log(LogService.LOG_DEBUG, "[DogRulesBundle] Found " + localRules.getRule().size() + " rules");
		// ------------------------------------------------------------------------------------------------------
		
		// ------------ PHASE 2: extract timed events from e-blocks in order to
		// schedule the corresponding timedOn notifications
		// TODO: implement event scheduling
		Set<DogMessage> timedEvents = this.tProcessor.preProcess(localRules);
		
		// ------------ PHASE 3: extract thresholds in order to set up the
		// threshold bundle to send threshold traversal notifications
		// TODO: implement threshold traversal
		
		// ------------ PHASE 4: write the DRL text corresponding to the
		// XML-defined rules with timed and threshold notifications
		// TODO: implement rule translation
		String drlRules = this.xml2drl.xml2drl(localRules);
		this.logger.log(LogService.LOG_DEBUG, "[DogRulesBundle] Translated local store to DRL:\n" + drlRules);
		
		// ------------ PHASE 5: initialize the rule bundle working memory
		
		// here we parse the just created DRL specification and load it into the
		// rules bundle knowledge base
		KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		
		// the reader for accessing the dynamically created rules
		StringReader drlReader = new StringReader(drlRules);
		
		// the knowledge builder is now used to read the created DRL
		kBuilder.add(ResourceFactory.newReaderResource(drlReader), ResourceType.DRL);
		
		if (kBuilder.hasErrors())
			this.logger.log(LogService.LOG_ERROR, "[DogRulesBundle]: Error while loading DRL from local rule store:"
					+ kBuilder.getErrors());
		
		// create the knowledge base to which rules shall be added
		KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
		
		// add the just read DRL rules
		kBase.addKnowledgePackages(kBuilder.getKnowledgePackages());
		
		// set the knowledge base of the rule bundle
		this.bundleToInitialize.setRuleBase(kBase);
		
		// schedule all the timed triggers
		this.bundleToInitialize.scheduleTimedEvents(timedEvents);
		
		// debug
		this.logger.log(LogService.LOG_DEBUG, "[DogRulesBundle]: created knowledge base and session");
		
		// ------------ END
		this.bundleToInitialize.initEnded();
	}
	
	/**
	 * 
	 * @param fullPathToRuleStore
	 * @return
	 */
	private RuleList parseLocalRuleStore(String fullPathToRuleStore)
	{
		RuleList localRules = null;
		try
		{
			// access the local rule store and check if it exists and can be
			// read
			File localRuleStoreFile = new File(fullPathToRuleStore);
			
			// check if the local rule store exists
			if (localRuleStoreFile.exists() && localRuleStoreFile.canRead())
			{
				// create the JAXB context for parsing the local rule store (in
				// xml)
				JAXBContext ctx = JAXBContext.newInstance("it.polito.elite.dog.addons.rules.schemalibrary");
				
				// create the corresponding un-marshaler for getting the list of
				// JAXB classes modeling the local rule store
				Unmarshaller um = ctx.createUnmarshaller();
				
				// unmarshal the xml local rule store
				Object umThing = um.unmarshal(localRuleStoreFile);
				if (umThing instanceof JAXBElement)
					localRules = (RuleList) ((JAXBElement<?>) umThing).getValue();
				else if (umThing instanceof RuleList)
					localRules = (RuleList) umThing;
				else
					this.logger.log(LogService.LOG_DEBUG,
							"[ThreadedRuleBundleInitializer]: Unable to parse the given rule store");
			}
		}
		
		catch (JAXBException e)
		{
			this.logger.log(LogService.LOG_ERROR, "[DogRulesBundle]: Exception " + e);
		}
		
		return localRules;
	}
	
}
