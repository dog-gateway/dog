package it.polito.elite.dog.drivers.zwave.model;

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
