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

import javax.ws.rs.Path;
import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;

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
@Path("/api/rules/")
public class RuleEngineRESTEndpoint implements RuleEngineRESTApi
{
	// the service logger
	private LogHelper logger;
	
	// the log id used for fallback
	public static final String logId = "[RuleEngineRESTApi]: ";
	
	// the bundle context reference
	private BundleContext context;
	
	// the rule service for which this bundle offers a rest interface
	private RuleEngineApi ruleEngine;
	
	/**
	 * 
	 */
	public RuleEngineRESTEndpoint()
	{
		System.out.println(RuleEngineRESTEndpoint.logId + "Creating RuleEngineRESTEndpoint...");
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
		this.ruleEngine = ruleEngine;
		
		// debug
		if (this.logger != null)
			this.logger.log(LogService.LOG_DEBUG, "Connected to the RuleEngineApi");
		else
			System.out.println(RuleEngineRESTEndpoint.logId + "Connected to the RuleEngineApi");
	}
	
	public void removedRuleEngine(RuleEngineApi ruleEngine)
	{
		// remove the reference to the rule service
		this.ruleEngine = null;
		
		// debug
		this.logger.log(LogService.LOG_DEBUG, RuleEngineRESTEndpoint.logId + "Disconnected from the RuleEngineApi");
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
			drlRules = this.ruleEngine.getDRLRules();
		
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
			xmlRules = this.generateXML(this.ruleEngine.getRules());
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
		if (this.ruleEngine != null)
		{
			// add the received rules
			RuleList rules = JAXB.unmarshal(new StringReader(xmlRules), RuleList.class);
			this.ruleEngine.addRule(rules);
		}
		
	}
	
	@Override
	public void updateRuleXML(String ruleId, String ruleContent)
	{
		// check not null
		if (this.ruleEngine != null)
		{
			// unmarshall the rule to update
			RuleList updatedRule = JAXB.unmarshal(new StringReader(ruleContent), RuleList.class);
			// update received rules
			this.ruleEngine.updateRule(ruleId, updatedRule);
			
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
			this.ruleEngine.removeRule(ruleId);
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
			JAXB.marshal(rules, output);
			
			rulesXML = output.getBuffer().toString();
		}
		catch (DataBindingException e)
		{
			// the exception can be throw by the JAXB.marshal method...
			this.logger.log(LogService.LOG_ERROR, "Exception in JAXB Marshalling...", e);
		}
		
		return rulesXML;
	}
	
}
