/**
 * 
 */
package it.polito.elite.dog.drivers.zwave.network.info;

import java.util.Collections;
import java.util.Map;

/**
 * A class for representing associations between a Modbus register and a
 * specific notification, identified by its simple name
 * 
 * @author bonino
 * 
 */
public class CmdNotificationInfo
{
	// the notification name
	private String name;
	
	//the additional parameters which may be optionally needed to better specify a command/notification
	private Map <String,String> params;
	
	
	
	/**
	 * @param name
	 * @param params
	 */
	public CmdNotificationInfo(String name, Map<String, String> params)
	{
		this.name = name;
		this.params = params;
	}

	/**
	 * Replaces the current parameter map with the given one.
	 * @param elementParams the parameter Map to store.
	 */
	public void setParameters(Map<String, String> elementParams)
	{
		this.params = elementParams;		
	}
	
	/**
	 * Provides back a read-only copy of the parameter map used by this object.
	 * @return the read only parameter map.
	 */
	public Map<String, String> getParameters()
	{
		return Collections.unmodifiableMap(this.params);
	}

	/**
	 * Provides the command/notification name associated to the KnxIPDeviceInfo instance.
	 * @return 
	 */
	public String getName()
	{
		return this.name;
	}
}
