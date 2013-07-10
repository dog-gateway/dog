/**
 * 
 */
package it.polito.elite.dog.communication.rest.ruleengine;

import it.polito.elite.dog.addons.rules.api.RuleEngineApi;
import it.polito.elite.dog.addons.rules.schemalibrary.RuleList;
import it.polito.elite.dog.communication.rest.ruleengine.api.RuleEngineRESTApi;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * @author bonino
 * 
 *         TODO: check if string parameters are right or if JAXB objects are
 *         preferred, in such case amend the rules bundle
 */
@Path("/rest/ruleengine/api")
public class RuleEngineRESTEndpoint implements RuleEngineRESTApi
{
	// the service logger
	private LogService logger;
	
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
		System.out.println("Created RuleEngineRESTEndpoint");
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
		this.logger = new DogLogInstance(this.context);
		
		// log the activation
		this.logger.log(LogService.LOG_INFO, RuleEngineRESTEndpoint.logId + "Activated....");
	}
	
	/**
	 * Prepare the bundle to be deactivated...
	 */
	public void deactivate()
	{
		// null the context
		this.context = null;
		
		// log deactivation
		this.logger.log(LogService.LOG_INFO, RuleEngineRESTEndpoint.logId + "Deactivated...");
		
		// null the logger
		this.logger = null;
	}
	
	public void addedRuleService(RuleEngineApi ruleEngine)
	{
		// store a reference to the rule service
		this.ruleEngine = ruleEngine;
		
		// debug
		this.logger.log(LogService.LOG_DEBUG, RuleEngineRESTEndpoint.logId + "Connected to the RuleEngineApi");
	}
	
	public void removedRuleService(RuleEngineApi ruleEngine)
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
		//check not null
		if(this.ruleEngine!=null)
		{
			//add the received rules
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
	// TODO: check the right rest syntax
	@Path("/remove/{ruleId}")
	public void removeRule(String ruleId)
	{
		//check not null
		if(this.ruleEngine!=null)
		{
			//add received rules
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
		//check not null
		if(this.ruleEngine!=null)
		{
			//add received rules
			this.ruleEngine.setRules(xmlRules);
		}
		
	}
	
}
