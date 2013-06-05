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
public interface FrameworkMonitorInterface
{	
	@GET
	@Path("/memory/runtime")
	@Produces(MediaType.TEXT_HTML)
	public String getRuntimeMemory();
	
	@GET
	@Path("/memory/free")
	@Produces(MediaType.TEXT_HTML)
	public String getFreeMemory();
	
	@GET
	@Path("/memory/used")
	@Produces(MediaType.TEXT_HTML)
	public String getUsedMemory();
}
