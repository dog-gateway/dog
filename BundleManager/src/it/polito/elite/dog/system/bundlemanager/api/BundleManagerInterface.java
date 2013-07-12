/**
 * 
 */
package it.polito.elite.dog.system.bundlemanager.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author bonino
 *
 */
@Path("/system/bundlemanager/")
public interface BundleManagerInterface
{
	@GET
	@Path("/bundles")
	@Produces(MediaType.TEXT_HTML)
	public String getBundles();
}
