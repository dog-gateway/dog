/*
 * Dog - Communication
 * 
 * Copyright (c) 2013 Dario Bonino
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

import it.polito.elite.dog.addons.rules.api.RuleEngineApi;
import it.polito.elite.dog.addons.rules.schemalibrary.RuleList;
import it.polito.elite.dog.communication.rest.ruleengine.api.RuleEngineRESTApi;
import it.polito.elite.dog.core.library.util.LogHelper;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 *         TODO: check if string parameters are right or if JAXB objects are
 *         preferred, in such case amend the rules bundle
 */
@Path("/rest/ruleengine/api")
public class RuleEngineRESTEndpoint implements RuleEngineRESTApi
{
	// the service logger
	private LogHelper logger;
	
	// the log id
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
		// TODO Auto-generated constructor stub
		System.out.println(RuleEngineRESTEndpoint.logId + "Created RuleEngineRESTEndpoint");
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
	 * #addRulesXML()
	 */
	@Override
	@POST
	@Path("/add")
	@Consumes({ MediaType.APPLICATION_XML })
	public void addRulesXML(RuleList xmlRules)
	{
		// check not null
		if (this.ruleEngine != null)
		{
			// add the received rules
			this.ruleEngine.addRule(xmlRules);
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
	@POST
	@Path("/remove/{ruleId}")
	public void removeRule(String ruleId)
	{
		// check not null
		if (this.ruleEngine != null)
		{
			// add received rules
			this.ruleEngine.removeRule(ruleId);
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.communication.rest.ruleengine.api.RuleEngineRESTApi
	 * #setRulesXML()
	 */
	@Override
	@POST
	@Path("/set")
	@Consumes({ MediaType.APPLICATION_XML })
	public void setRulesXML(RuleList xmlRules)
	{
		// check not null
		if (this.ruleEngine != null)
		{
			// add received rules
			this.ruleEngine.setRules(xmlRules);
		}
		
	}
	
	@Override
	@GET
	@Path("/rules/drl")
	@Produces({ MediaType.TEXT_PLAIN })
	public String getDRLRules()
	{
		// no rules at the beginning
		String drlRules = "";
		
		// extract the rule from the rule engine in the DRL format
		if (this.ruleEngine != null)
			drlRules = this.ruleEngine.getDRLRules();
		
		//return existing rules
		return drlRules;
	}
	
	@Override
	@GET
	@Path("/rules/xml")
	@Produces({ MediaType.APPLICATION_XML })
	public String getXMLRules()
	{
		// no rules at the beginning
		String drlRules = "";
		
		// extract the rule from the rule engine in the DRL format
		if (this.ruleEngine != null)
			drlRules = this.ruleEngine.getXMLRules();
		
		//return existing rules
		return drlRules;
	}
	
}
