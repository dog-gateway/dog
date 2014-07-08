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

import java.util.ArrayList;

/**
 * @author bonino
 * 
 */
public class EventDataStream
{
	// the stream metadata

	// the notification name associated to the stream
	private String notificationName;

	// the notification parameter associated to the stream
	private String notificationParameters;

	// the uri of the device generating the stream
	private String deviceUri;

	// the data points
	private ArrayList<EventDataPoint> datapoints;

	/**
	 * Basic constructor, provides an empty {@link EventDataStream} instance not
	 * bounded to any device and notification
	 */
	public EventDataStream()
	{
		// simply intialize the arrayList of datapoints
		this.datapoints = new ArrayList<>();
	}

	/**
	 * Full constructor, provides a {@link EventDataStream} instance bound to
	 * the given device and notification data.
	 * 
	 * @param notificationName
	 *            The name of the notification to which this stream refers.
	 * @param notificationParameters
	 *            The name of the notification parameters, in case of parametric
	 *            notifications such as
	 *            ThreePhaseActivePowerMeasurementNotification.
	 * @param deviceUri
	 *            The uri of the device generating the event stream
	 */
	public EventDataStream(String notificationName,
			String notificationParameters, String deviceUri)
	{
		super();
		this.notificationName = notificationName;
		this.notificationParameters = notificationParameters;
		this.deviceUri = deviceUri;
		this.datapoints = new ArrayList<>();
	}

	/**
	 * Basic constructor, provides an empty {@link EventDataStream} instance not
	 * bounded to any device and notification
	 * 
	 * @param initialSize
	 *            The initial capacity of the inner set of
	 *            {@link EventDataPoint)s.
	 */
	public EventDataStream(int initialSize)
	{
		// simply intialize the arrayList of datapoints
		this.datapoints = new ArrayList<>(initialSize);
	}

	/**
	 * Full constructor, provides a {@link EventDataStream} instance bound to
	 * the given device and notification data.
	 * 
	 * @param notificationName
	 *            The name of the notification to which this stream refers.
	 * @param notificationParameters
	 *            The name of the notification parameters, in case of parametric
	 *            notifications such as
	 *            ThreePhaseActivePowerMeasurementNotification.
	 * @param deviceUri
	 *            The uri of the device generating the event stream
	 * @param initialSize
	 *            The initial capacity of the inner set of
	 *            {@link EventDataPoint)s.
	 */
	public EventDataStream(String notificationName,
			String notificationParameters, String deviceUri, int initialSize)
	{
		super();
		this.notificationName = notificationName;
		this.notificationParameters = notificationParameters;
		this.deviceUri = deviceUri;
		this.datapoints = new ArrayList<>(initialSize);
	}

	/**
	 * Get the name of the notification to which this event stream is
	 * associated.
	 * 
	 * @return
	 */
	public String getNotificationName()
	{
		return this.notificationName;
	}

	/**
	 * Set the name of the notification to which this event stream is
	 * associated.
	 * 
	 * @param notificationName
	 */
	public void setNotificationName(String notificationName)
	{
		this.notificationName = notificationName;
	}

	/**
	 * Get the parameters needed to better identify the notification generating this event stream
	 * @return The parameters encoded in a post-like manner, e.g., name1=value1&name2=value2&...
	 */
	public String getNotificationParameters()
	{
		return this.notificationParameters;
	}

	/**
	 * Set the parameters needed to better identify the notification generating this event stream
	 * @param notificationParameters The parameters encoded in a post-like manner, e.g., name1=value1&name2=value2&...
	 */
	public void setNotificationParameters(String notificationParameters)
	{
		this.notificationParameters = notificationParameters;
	}

	/**
	 * Get the URI of the device generating this stream
	 * @return
	 */
	public String getDeviceUri()
	{
		return this.deviceUri;
	}

	/**
	 * Set the URI of the device generating this stream
	 * @param deviceUri
	 */
	public void setDeviceUri(String deviceUri)
	{
		this.deviceUri = deviceUri;
	}

	/**
	 * Get the list of {@link EventDataPoint}s composing this stream
	 * @return
	 */
	public ArrayList<EventDataPoint> getDatapoints()
	{
		return this.datapoints;
	}

	/**
	 * Sets  the list of {@link EventDataPoint}s composing this stream
	 * @param datapoints
	 */
	public void setDatapoints(ArrayList<EventDataPoint> datapoints)
	{
		this.datapoints = datapoints;
	}
	
	/**
	 * Adds the given data point to this stream
	 * @param datapoint The data point to add
	 * @return true if successfully added, false otherwise
	 */
	public boolean addDatapoint(EventDataPoint datapoint)
	{
		return this.datapoints.add(datapoint);
	}

	@Override
	public String toString()
	{
		StringBuffer asStringBuffer = new StringBuffer();
		
		asStringBuffer.append(" [Notification Name: "+this.notificationName);
		asStringBuffer.append(" , Notification Params: "+this.notificationParameters+", ");
		for(EventDataPoint datapoint : this.datapoints)
		{
			asStringBuffer.append("{");
			asStringBuffer.append(datapoint.toString());
			asStringBuffer.append("}");
		}
		asStringBuffer.append("]");
		return asStringBuffer.toString();
	}

	
}
