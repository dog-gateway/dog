/**
 * 
 */
package it.polito.elite.dog.addons.rules.api;

import it.polito.elite.dog.addons.rules.schemalibrary.RuleList;
import it.polito.elite.domotics.dog2.doglibrary.interfaces.DogRulesService;

/**
 * @author bonino
 * TODO: remove DogRulesService from DogLibrary when the core will be migrated to Dog2.5
 */
public interface RuleEngineApi extends DogRulesService
{
	/**
	 * Add rules using JAXB objects representing them
	 * @param rules
	 */
	public void addRule(RuleList rules);
	
	/**
	 * Set/replace rules using JAXB objects representing them
	 * @param rules
	 */
	public void setRules(RuleList rules);
	
	/**
	 * Get the locakl rule base encoded as DRL
	 * 
	 * @return A {@link String} representation of the local rule base using the
	 *         DRL language (DSL)
	 */
	public String getDRLRules();
	
	/**
	 * Get the local rule base encoded as XML
	 * 
	 * @return an XML {@link String} encoding the local rule base according to
	 *         the Dog rule schema.
	 */
	public String getXMLRules();
}
