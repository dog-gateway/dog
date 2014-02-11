package it.polito.elite.dog.addons.xively.client.queue;

import java.util.Set;

import com.xively.client.model.Datapoint;

public class DatapointInfo
{
	// the resource upon which sending data
	private int feedId;

	private String datastreamId;
	
	private Datapoint last;

	// the JSON data to be sent
	private Set<Datapoint> datapoints;

	public DatapointInfo(int feed, String datastreamId, Set<Datapoint> data, Datapoint last)
	{
		// store the web resource
		this.feedId = feed;

		// store the datastream id
		this.datastreamId = datastreamId;
		
		//store the last value
		this.last = last;		
		
		// store the JSON data
		this.datapoints = data;
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
		this.datapoints = jsonData;
	}

	public Datapoint getLast()
	{
		return last;
	}

	public void setLast(Datapoint last)
	{
		this.last = last;
	}
	
	
}
