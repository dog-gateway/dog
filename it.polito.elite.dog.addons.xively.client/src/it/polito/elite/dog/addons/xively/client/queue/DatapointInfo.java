/*
 * Dog - Addons
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
package it.polito.elite.dog.addons.xively.client.queue;

import java.util.Set;
import java.util.TreeSet;

import com.xively.client.model.Datapoint;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class DatapointInfo
{
	// the resource upon which sending data
	private int feedId;
	
	private String datastreamId;
	
	// the JSON data to be sent
	private TreeSet<Datapoint> datapoints;
	
	public DatapointInfo(int feed, String datastreamId, Set<Datapoint> data)
	{
		// store the web resource
		this.feedId = feed;
		
		// store the datastream id
		this.datastreamId = datastreamId;
		
		// store the JSON data
		this.datapoints = new TreeSet<Datapoint>();
		this.datapoints.addAll(data);
	}
	
	public int getFeed()
	{
		return this.feedId;
	}
	
	public void setFeed(int feed)
	{
		this.feedId = feed;
	}
	
	public String getDatastreamId()
	{
		return datastreamId;
	}
	
	public void setDatastreamId(String datastreamId)
	{
		this.datastreamId = datastreamId;
	}
	
	public Set<Datapoint> getDatapoints()
	{
		return datapoints;
	}
	
	public void setDatapoints(Set<Datapoint> jsonData)
	{
		this.datapoints.clear();
		this.datapoints.addAll(jsonData);
	}
	
	public Datapoint getLast()
	{
		return this.datapoints.last();
	}
	
}
