/*
 * Dog - Addons - H2 EventStore
 * 
 * Copyright (c) 2014 Dario Bonino
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
package it.polito.elite.dog.addons.storage;

import java.util.HashSet;

/**
 * @author bonino
 *
 */
public class EventDataStreamSet
{
	private String deviceUri;
	
	private HashSet<EventDataStream> eventDataStreams;
	
	/**
	 * The class constructor, creates an {@link EventDataStreamSet} instance associated to the given device
	 * @param deviceUri
	 */
	public EventDataStreamSet(String deviceUri)
	{
		this.deviceUri = deviceUri;
		
		this.eventDataStreams = new HashSet<>();
	}
	
	/**
	 * The class constructor, creates an unbounded {@link EventDataStreamSet} instance.
	 */
	public EventDataStreamSet()
	{	
		this.eventDataStreams = new HashSet<>();
	}
	
	/**
	 * Add an event data stream to this set.
	 * @param stream
	 */
	public void addEventDataStream(EventDataStream stream)
	{
		this.eventDataStreams.add(stream);
	}

	/**
	 * Get the uri of the device generating this set of event data streams
	 * @return
	 */
	public String getDeviceUri()
	{
		return this.deviceUri;
	}

	/**
	 * Set the uri of the device generating this set of event data streams
	 * @param deviceUri
	 */
	public void setDeviceUri(String deviceUri)
	{
		this.deviceUri = deviceUri;
	}

	/**
	 * Get the live reference to all the event data streams stored in this set
	 * @return
	 */
	public HashSet<EventDataStream> getEventDataStreams()
	{
		return eventDataStreams;
	}

	@Override
	public String toString()
	{
		StringBuffer asStringBuffer = new StringBuffer();
		
		asStringBuffer.append("[Device URI:"+this.deviceUri+" , ");
		for(EventDataStream stream : this.eventDataStreams)
		{
			asStringBuffer.append("{");
			asStringBuffer.append(stream.toString());
			asStringBuffer.append("}");
		}
		asStringBuffer.append("]");
		return asStringBuffer.toString();
	}
	
	

}
