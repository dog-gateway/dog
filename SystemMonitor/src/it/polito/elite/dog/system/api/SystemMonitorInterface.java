/**
 * 
 */
package it.polito.elite.dog.system.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author bonino
 *
 */
@Path("/system/")
public interface SystemMonitorInterface
{
	@GET
	@Path("/bundles")
	@Produces(MediaType.TEXT_HTML)
	public String getBundles();
}
