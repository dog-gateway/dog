/*
 * Dog - Addons
 * 
 * Copyright (c) 2011-2013 Dario Bonino and Luigi De Russis
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
package it.polito.elite.dog.addons.rules.api;

import java.net.URI;

import it.polito.elite.dog.addons.rules.schemalibrary.RuleList;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 */
public interface RuleEngineApi
{
	/** Sets and replaces all rules (first deleting them all) */
	void loadRules(URI location);
	
	void loadRules(String location);
	
	void saveRules(URI location);
	
	void saveRules(String location);
	
	/**
	 * Add rules using JAXB objects representing them
	 * 
	 * @param rules
	 */
	public void addRule(RuleList rules);
	
	/**
	 * Set/replace a rule using JAXB objects representing them
	 * 
	 * @param rules
	 */
	public void updateRule(String ruleId, RuleList xmlRule);
	
	/**
	 * Get the local rule base encoded as DRL
	 * 
	 * @return A {@link String} representation of the local rule base using the
	 *         DRL language (DSL)
	 */
	public String getDRLRules();
	
	/**
	 * Get the local rule base encoded as a JAXB object
	 * 
	 * @return the {@link JAXB} encoding the local rule base according to the
	 *         Dog rule schema.
	 */
	public RuleList getRules();
	
	/**
	 * Remove the rule with the given id
	 * 
	 * @param ruleName
	 */
	public boolean removeRule(String ruleName);
}
