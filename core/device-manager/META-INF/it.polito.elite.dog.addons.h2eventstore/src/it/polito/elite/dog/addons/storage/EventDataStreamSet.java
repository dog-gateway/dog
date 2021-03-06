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
	private HashSet<EventDataStream> eventDataStreams;

	/**
	 * The class constructor, creates an {@link EventDataStreamSet} instance
	 * associated to the given device
	 * 
	 * @param deviceUri
	 */
	public EventDataStreamSet(String deviceUri)
	{
		this.eventDataStreams = new HashSet<>();
	}

	/**
	 * The class constructor, creates an unbounded {@link EventDataStreamSet}
	 * instance.
	 */
	public EventDataStreamSet()
	{
		this.eventDataStreams = new HashSet<>();
	}

	/**
	 * Add an event data stream to this set.
	 * 
	 * @param stream
	 */
	public void addDatastream(EventDataStream stream)
	{
		this.eventDataStreams.add(stream);
	}

	/**
	 * Get the live reference to all the event data streams stored in this set
	 * 
	 * @return
	 */
	public HashSet<EventDataStream> getDatastreams()
	{
		return eventDataStreams;
	}

	@Override
	public String toString()
	{
		StringBuffer asStringBuffer = new StringBuffer();
		asStringBuffer.append("[");
		boolean first = true;
		for (EventDataStream stream : this.eventDataStreams)
		{
			if (!first)
				asStringBuffer.append(",");
			else
				first = false;

			asStringBuffer.append(stream.toString());

		}
		asStringBuffer.append("]");
		return asStringBuffer.toString();
	}

}
