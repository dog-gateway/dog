/*
 * Dog - Network Driver
 * 
 * Copyright (c) 2011-2013 Dario Bonino
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
package it.polito.elite.dog.drivers.knx.network.info;

import java.net.InetAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A utility class for storing data about a KNXNet/IP command or notification
 * (maps a group address to a given command name or notification name)
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class KnxIPDeviceInfo
{
	// the name
	private String name;
	
	// the device group address
	private String groupAddress;
	
	//the additional parameters which may be optionally needed to better specify a command/notification
	private Map <String,String> params;
	
	// the IP address of the gateway to which a specific device is connected
	private InetAddress gatewayIPAddress;
	
	/**
	 * The class constructor, stores the given name and group address
	 * association into a new KnxIPDeviceInfo instance.
	 * 
	 * @param realName the command/notification name.
	 * @param groupAddress the device group address.
	 */
	public KnxIPDeviceInfo(String name, String groupAddress)
	{
		//store the name
		this.name = name;
		
		//store the group address
		this.groupAddress = groupAddress;
		
		//init the parameters map to store any additional parameter for the given command/notification
		this.params = new HashMap<String, String>();
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
	 * Provides the group address associated to this KnxIPDeviceInfo instance.
	 * @return the device group address as String.
	 */
	public String getGroupAddress()
	{
		return this.groupAddress;
	}
	
	/**
	 * Provides the command/notification name associated to the KnxIPDeviceInfo instance.
	 * @return 
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Get the IP address of the gateway handling this register (device
	 * connected to the gateway and abstracted as a register value)
	 * 
	 * @return the gatewayIPAddress
	 */
	public InetAddress getGatewayIPAddress()
	{
		return gatewayIPAddress;
	}
	
	/**
	 * Set the IP address of the gateway handling this register (device
	 * connected to the gateway and abstracted as a register value)
	 * @param gatewayIPAddress
	 *            the gatewayIPAddress to set
	 */
	public void setGatewayIPAddress(InetAddress gatewayIPAddress)
	{
		this.gatewayIPAddress = gatewayIPAddress;
	}

	@Override
	public boolean equals(Object arg0)
	{
		boolean equals = false;
		
		if(arg0 instanceof KnxIPDeviceInfo)
		{
			//equals if name, group address and gatewayIP address are equal
			equals = ((this.gatewayIPAddress.getHostAddress().equals(((KnxIPDeviceInfo) arg0).gatewayIPAddress.getHostAddress()))&&
					(this.name.equals(((KnxIPDeviceInfo) arg0).name))&&
					(this.groupAddress.equals(((KnxIPDeviceInfo) arg0).groupAddress)));
		}
		
		return equals;
	}

	@Override
	public int hashCode()
	{
		// hash code forces equals check for instances representing the same device
		return (this.gatewayIPAddress.getHostAddress()+this.name+this.groupAddress).hashCode();
	}
	
	
	
	
}
