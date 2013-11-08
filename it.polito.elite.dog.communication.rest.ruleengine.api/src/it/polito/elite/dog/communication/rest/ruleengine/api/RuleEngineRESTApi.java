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
package it.polito.elite.dog.communication.rest.ruleengine.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 *
 */
@Path("/api/rules/")
public interface RuleEngineRESTApi
{
	@GET
	@Produces({MediaType.TEXT_PLAIN})
	public String getDRLRules();
	
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public String getXMLRules();
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML })
	public void addRulesXML(String xmlRules);
	
	@PUT
	@Path("/{ruleId}")
	@Consumes({ MediaType.APPLICATION_XML })
	public void updateRuleXML(@PathParam("ruleId") String ruleId, String ruleContent);
	
	@DELETE
	@Path("/{ruleId}")
	public void removeRule(@PathParam("ruleId") String ruleId);
}
