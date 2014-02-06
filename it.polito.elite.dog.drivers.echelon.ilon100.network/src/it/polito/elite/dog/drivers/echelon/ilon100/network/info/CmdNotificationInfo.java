/*
 * Dog - Network Driver
 * 
 * Copyright (c) 2012-2014 Dario Bonino
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
package it.polito.elite.dog.drivers.echelon.ilon100.network.info;

import java.util.Collections;
import java.util.Map;

/**
 * A class for representing associations between a Modbus register and a
 * specific notification, identified by its simple name
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
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
