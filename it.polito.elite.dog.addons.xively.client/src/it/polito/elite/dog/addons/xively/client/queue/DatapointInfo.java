package it.polito.elite.dog.addons.xively.client.queue;

import java.util.Set;
import java.util.TreeSet;

import com.xively.client.model.Datapoint;

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
