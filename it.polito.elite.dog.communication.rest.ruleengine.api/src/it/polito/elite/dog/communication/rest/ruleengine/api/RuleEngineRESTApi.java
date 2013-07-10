/**
 * 
 */
package it.polito.elite.dog.communication.rest.ruleengine.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
/**
 * @author bonino
 *
 */
@Path("/rest/ruleengine/api")
public interface RuleEngineRESTApi
{
	@POST
	@Path("/add")
	@Consumes({ MediaType.APPLICATION_XML })
	public void addRulesXML(String xmlRules);
	
	@POST //TODO: check the right rest syntax
	@Path("/remove/{ruleId}")
	public void removeRule(@PathParam("ruleId") String ruleId);
	
	@POST
	@Path("/set")
	@Consumes({ MediaType.APPLICATION_XML })
	public void setRulesXML(String xmlRules);
}
