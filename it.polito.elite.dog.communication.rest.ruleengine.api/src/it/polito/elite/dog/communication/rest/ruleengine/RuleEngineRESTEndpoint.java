/*
 * Dog - Communication
 * 
 * Copyright (c) 2013 Dario Bonino and Luigi De Russis
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
package it.polito.elite.dog.communication.rest.ruleengine;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import javax.ws.rs.Path;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import it.polito.elite.dog.addons.rules.api.RuleEngineApi;
import it.polito.elite.dog.addons.rules.schemalibrary.RuleList;
import it.polito.elite.dog.communication.rest.ruleengine.api.RuleEngineRESTApi;
import it.polito.elite.dog.core.library.util.LogHelper;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
@Path("/api/v1/rules/")
public class RuleEngineRESTEndpoint implements RuleEngineRESTApi
{
	// the service logger
	private LogHelper logger;
	
	// the bundle context reference
	private BundleContext context;
	
	// the rule service for which this bundle offers a rest interface
	private AtomicReference<RuleEngineApi> ruleEngine;
	
	// the JAXB context
	private JAXBContext jaxbContext;
	// the JAXB unmarshaller
	private AtomicReference<Unmarshaller> unmarshaller;
	
	/**
	 * Default constructor
	 */
	public RuleEngineRESTEndpoint()
	{
		// init
		this.ruleEngine = new AtomicReference<RuleEngineApi>();
		this.unmarshaller = new AtomicReference<Unmarshaller>();
		
		try
		{
			// int here JAXB objects (hoping to improve the performance of
			// the bundle)
			this.jaxbContext = JAXBContext.newInstance(RuleList.class.getPackage().getName());
			this.unmarshaller.set(this.jaxbContext.createUnmarshaller());
		}
		catch (JAXBException e)
		{
			System.out.println("[RuleEngineRESTEndpoint] Error creating the JAXB Context" + e);
		}
	}
	
	/**
	 * Bundle activation, stores a reference to the context object passed by the
	 * framework to get access to system data, e.g., installed bundles, etc.
	 * 
	 * @param context
	 */
	public void activate(BundleContext context)
	{
		// store the bundle context
		this.context = context;
		
		// init the logger with a null logger
		this.logger = new LogHelper(this.context);
		
		// log the activation
		this.logger.log(LogService.LOG_INFO, "Activated....");
	}
	
	/**
	 * Prepare the bundle to be deactivated...
	 */
	public void deactivate()
	{
		// null the context
		this.context = null;
		
		// log deactivation
		this.logger.log(LogService.LOG_INFO, "Deactivated...");
		
		// null the logger
		this.logger = null;
	}
	
	public void addedRuleEngine(RuleEngineApi ruleEngine)
	{
		// store a reference to the rule service
		this.ruleEngine.set(ruleEngine);
		
		// debug
		if (this.logger != null)
			this.logger.log(LogService.LOG_DEBUG, "Connected to the RuleEngineApi");
		else
			System.out.println("[RuleEngineRESTEndpoint] Connected to the RuleEngineApi");
	}
	
	public void removedRuleEngine(RuleEngineApi ruleEngine)
	{
		// remove the reference to the rule service
		this.ruleEngine = null;
		
		// debug
		this.logger.log(LogService.LOG_DEBUG, "Disconnected from the RuleEngineApi");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.communication.rest.ruleengine.api.RuleEngineRESTApi
	 * #getDRLRules()
	 */
	@Override
	public String getDRLRules()
	{
		// no rules at the beginning
		String drlRules = "";
		
		// extract the rule from the rule engine in the DRL format
		if (this.ruleEngine != null)
			drlRules = this.ruleEngine.get().getDRLRules();
		
		// return existing rules
		return drlRules;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.communication.rest.ruleengine.api.RuleEngineRESTApi
	 * #getXMLRules()
	 */
	@Override
	public String getXMLRules()
	{
		// no rules at the beginning
		String xmlRules = "";
		
		// extract the rule from the rule engine in the JAXB format
		if (this.ruleEngine != null)
		{
			xmlRules = this.generateXML(this.ruleEngine.get().getRules());
		}
		
		// return existing rules
		return xmlRules;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.communication.rest.ruleengine.api.RuleEngineRESTApi
	 * #addRulesXML(it.polito.elite.dog.addons.rules.schemalibrary.RuleList)
	 */
	@Override
	public void addRulesXML(String xmlRules)
	{
		// check not null
		if (this.ruleEngine != null && this.unmarshaller != null)
		{
			try
			{
				// add the received rules
				final RuleList rules = (RuleList) this.unmarshaller.get().unmarshal(new StringReader(xmlRules));
				// thread for asynchronous call
				ExecutorService executor = Executors.newSingleThreadExecutor();
				executor.execute(new Runnable() {
					
					@Override
					public void run()
					{
						ruleEngine.get().addRule(rules);
					}
				});
			}
			catch (JAXBException e)
			{
				this.logger.log(LogService.LOG_ERROR, "JAXB Error", e);
			}
			
		}
		else
		{
			this.logger.log(LogService.LOG_ERROR, "Error in adding a new rule");
		}
		
	}
	
	@Override
	public void updateRuleXML(String ruleId, String ruleContent)
	{
		// check not null
		if (this.ruleEngine != null && this.unmarshaller != null)
		{
			try
			{
				// unmarshall the rule to update
				RuleList updatedRule = (RuleList) this.unmarshaller.get().unmarshal(
						new StringReader(ruleContent));
				// update received rules
				this.ruleEngine.get().updateRule(ruleId, updatedRule);
			}
			catch (Exception e)
			{
				this.logger.log(LogService.LOG_ERROR, "JAXB Error", e);
			}
		}
		else
		{
			this.logger.log(LogService.LOG_ERROR, "Error in updating the rule " + ruleId);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.communication.rest.ruleengine.api.RuleEngineRESTApi
	 * #removeRule(java.lang.String)
	 */
	@Override
	public void removeRule(String ruleId)
	{
		// check not null
		if (this.ruleEngine != null)
		{
			// add received rules
			this.ruleEngine.get().removeRule(ruleId);
		}
		
	}
	
	/**
	 * Generate the XML to be sent
	 * 
	 * @param rules
	 *            the {@link RuleList} object to marshall
	 * @return the corresponding XML
	 */
	private String generateXML(RuleList rules)
	{
		String rulesXML = "";
		
		try
		{
			StringWriter output = new StringWriter();
			
			// marshall the RuleList...
			this.jaxbContext.createMarshaller().marshal(rules, output);
			
			rulesXML = output.getBuffer().toString();
		}
		catch (Exception e)
		{
			// the exception can be throw by the JAXB.marshal method...
			this.logger.log(LogService.LOG_ERROR, "Exception in JAXB Marshalling...", e);
		}
		
		return rulesXML;
	}
	
}
