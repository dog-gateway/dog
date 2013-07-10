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
package it.polito.elite.dog.addons.rules;

import org.osgi.framework.BundleContext;

import it.polito.elite.domotics.dog2.doglibrary.message.DogMessage;
import it.polito.elite.domotics.dog2.doglibrary.message.DogMessageHandler;
import it.polito.elite.domotics.dog2.doglibrary.message.MessageRulesRequest;
import it.polito.elite.domotics.dog2.doglibrary.message.DogMessage.MessageTypes;

/**
 * @author Emiliano Castellina
 * 
 */
public class DogMessageRulesQueue extends DogMessageHandler
{
	
	/** Reference to the RuleEngine instance */
	RuleEngine ruleCore;
	
	public DogMessageRulesQueue(BundleContext context, RuleEngine ruleCore)
	{
		super(context);
		this.ruleCore = ruleCore;
	}
	
	@Override
	protected void processMessage(DogMessage message)
	{
		// Is it a correct message?
		if (message.getType() == MessageTypes.RULES)
		{
			// get the content of the message
			MessageRulesRequest ruleRequest = (MessageRulesRequest) message.getContent();
			// get the inner xml or a file name
			String ruleContent = ruleRequest.getMessageRuleContent();
			// in according with the type call the corresponding method on the
			// RuleEngine instance
			switch (ruleRequest.getType())
			{
				case ADD:
					this.ruleCore.addRule(ruleContent);
					break;
				case SET:
					this.ruleCore.setRules(ruleContent);
					break;
				case LOAD:
					this.ruleCore.loadRules(ruleContent);
					break;
				case SAVE:
					this.ruleCore.saveRules(ruleContent);
					break;
				case REMOVE:
					this.ruleCore.removeRule(ruleContent);
					break;
				
			}
			
		}
		
	}
	
}
