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
package it.polito.elite.dog.communication.rest.ruleengine.api;

import it.polito.elite.dog.addons.rules.schemalibrary.RuleList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 *
 */
@Path("/rest/ruleengine/api")
public interface RuleEngineRESTApi
{
	@POST
	@Path("/add")
	@Consumes({ MediaType.APPLICATION_XML })
	public void addRulesXML(RuleList xmlRules);
	
	@POST //TODO: check the right rest syntax
	@Path("/remove/{ruleId}")
	public void removeRule(@PathParam("ruleId") String ruleId);
	
	@POST
	@Path("/set")
	@Consumes({ MediaType.APPLICATION_XML })
	public void setRulesXML(RuleList xmlRules);
	
	@GET
	@Path("/rules/drl")
	@Produces({MediaType.TEXT_PLAIN})
	public String getDRLRules();
	
	@GET
	@Path("/rules/xml")
	@Produces({ MediaType.APPLICATION_XML })
	public String getXMLRules();
}
