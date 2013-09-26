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
package it.polito.elite.dog.drivers.zwave.model;


import it.polito.elite.dog.drivers.zwave.ZWaveAPI;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Device
{
	private DeviceData data;
	private Map<Integer, Instance> instances;
	//private int deviceId;

	@JsonCreator
    public Device(@JsonProperty("data") DeviceData data,
    		@JsonProperty("instances") Map<Integer, Instance> instances)
    {
		this.data = data;
		this.instances = instances;
		
		//Set unique id for each element
		for(Entry<Integer, Instance> instance : this.instances.entrySet())
		{
			instance.getValue().setInstanceId(instance.getKey());
		}
    }
	
//	/**
//	 * @return the deviceId
//	 */
//	public int getDeviceId() 
//	{
//		return deviceId;
//	}
//	
//	/**
//	 * @param deviceId the deviceId to set
//	 */
//	public void setDeviceId(int deviceId) 
//	{
//		this.deviceId = deviceId;
//	}
	
	public DeviceData getData()
	{
		return data;
	}
	public void setData(DeviceData data)
	{
		this.data = data;
	}
	public Map<Integer, Instance> getInstances()
	{
		return instances;
	}
	public void setInstances(Map<Integer, Instance> instances)
	{
		this.instances = instances;
	}
	
	/**
	 * Returns the {@link Instance} associated to instanceId
	 */
	public Instance getInstance(int instanceId)
	{
		return instances.get(instanceId);
	}

	public Instance getZeroInstance()
	{
		return instances.get(ZWaveAPI.ROOT_ELEMENT);
	}

	public int getBasicType()
	{
		Integer ii = (Integer) getData().get(DataConst.BASIC_TYPE).getValue();

		if(ii != null)
			return ii.intValue();
		else 
			return DataConst.INVALID;
	}

	public int getGenericType()
	{
		Integer ii = (Integer) getData().get(DataConst.GENERIC_TYPE).getValue();

		if(ii != null)
			return ii.intValue();
		else 
			return DataConst.INVALID;
	}

	public int getSpecificType()
	{
		Integer ii = (Integer) getData().get(DataConst.SPECIFIC_TYPE).getValue();

		if(ii != null)
			return ii.intValue();
		else 
			return DataConst.INVALID;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getNodeInfoFrame()
	{
		return (List<Integer>) getData().get(DataConst.NODE_INFO_FRAME).getValue();
	}

	public Date getLastComunication()
	{
		long nLastReceived = 0;
		long nLastSend = 0;
		try{
			nLastReceived = getData().get(DataConst.LAST_RECEIVED).getUpdateTime();
		}
		catch(NumberFormatException e){
		}
		
		try{
		nLastSend = getData().get(DataConst.LAST_SEND).getUpdateTime();
		}
		catch(NumberFormatException e){
		}
		long nTime = nLastReceived > nLastSend ?  nLastReceived : nLastSend;
		
		return new Date((long)nTime * 1000);
	}

	public boolean isListening()
	{
		Boolean ii = (Boolean) getData().get(DataConst.IS_LISTENING).getValue();

		if(ii != null)
			return ii.booleanValue();
		else 
			return false;
	}

	public boolean isFLiRS()
	{
		if(!isListening() && (isSensor250() || isSensor1000()))
			return true;
		else
			return false;
	}

	public boolean isAwake()
	{
		Boolean ii = (Boolean) getData().get(DataConst.IS_AWAKE).getValue();

		if(ii != null)
			return ii.booleanValue();
		else 
			return false;
	}

	public boolean isFailed()
	{
		Boolean ii = (Boolean) getData().get(DataConst.IS_FAILED).getValue();

		if(ii != null)
			return ii.booleanValue();
		else 
			return false;
	}

	public boolean isSensor250()
	{
		Boolean ii = (Boolean) getData().get(DataConst.SENSOR250).getValue();

		if(ii != null)
			return ii.booleanValue();
		else 
			return false;
	}

	public boolean isSensor1000()
	{
		Boolean ii = (Boolean) getData().get(DataConst.SENSOR1000).getValue();

		if(ii != null)
			return ii.booleanValue();
		else 
			return false;
	}

	public boolean hasCommandClass(int commandClass)
	{
		if(getInstances() != null)
			return getZeroInstance().getCommandClasses().containsKey(commandClass);
		else
			return false;
	}

	public boolean hasWakeup()
	{
		return hasCommandClass(ZWaveAPI.COMMAND_CLASS_WAKE_UP);
	}

	public boolean hasBattery()
	{
		return hasCommandClass(ZWaveAPI.COMMAND_CLASS_BATTERY);
	}
}
