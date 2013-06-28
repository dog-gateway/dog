package it.polito.elite.dog.admin.system.devicemonitor.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * The interrace defining the API for the device monitor bundle which allows to
 * get information about installed, active and idle devices.
 * 
 * @author bonino
 * 
 */
@Path("/system/devicemonitor/")
public interface DeviceMonitorInterface
{
	@GET
	@Path("/devices")
	@Produces(MediaType.TEXT_HTML)
	public String getInstalledDevices();
	
}
