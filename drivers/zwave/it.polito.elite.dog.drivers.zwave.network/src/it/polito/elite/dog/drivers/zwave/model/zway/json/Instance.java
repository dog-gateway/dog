/*
 * Dog  - Z-Wave
 * 
 * Copyright 2013 Davide Aimone  and Dario Bonino 
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
package it.polito.elite.dog.drivers.zwave.model.zway.json;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;


public class Instance
{
	@JsonProperty("commandClasses") private Map<Integer, CommandClasses> commandClasses;
	@JsonProperty("data") private InstanceData data;
	private int instanceId;
	
	/**
	 * @return the instanceId
	 */
	public int getInstanceId() 
	{
		return instanceId;
	}

	/**
	 * @param instanceId the instanceId to set
	 */
	public void setInstanceId(int instanceId) 
	{
		this.instanceId = instanceId;
	}

	public Map<Integer, CommandClasses> getCommandClasses()
	{
		return commandClasses;
	}
	
	public void setCommandClasses(Map<Integer, CommandClasses> commandClasses)
	{
		this.commandClasses = commandClasses;
	}
	
	/**
	 * Returns the {@link CommandClasses} associated with commandClass
	 */
	public CommandClasses getCommandClass(int commandClass)
	{
		return commandClasses.get(commandClass);
	}
	
	public InstanceData getData()
	{
		return data;
	}
	public void setData(InstanceData data)
	{
		this.data = data;
	}
}
