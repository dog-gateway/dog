/*
 * Dog - Core
 * 
 * Copyright (c) (c) 2011-2013 Dario Bonino
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
package it.polito.elite.stream.processing.events;

import java.util.Calendar;
import java.util.logging.Logger;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;

/**
 * The base class for events, it basically offers a unified access to events for
 * uniform handling by Esper. Subclasses of generic events shall handle all
 * issues related to measure conversion and computation when needed.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * @since September, 2011
 * 
 */
public abstract class GenericEvent
{
	// the event type
	protected EventTypeEnum type;
	
	// the event source (typically a unique identifier handled by calling
	// applications)
	protected String streamName;
	
	//the source of the event (might be a block along a chain)
	protected String src;
	
	// the event time stamp
	protected Calendar timestamp;
	
	// the value of the event
	protected Double value;
	
	// the unitOfMeasure (if any)
	protected String unitOfMeasure;
	
	//the class logger
	protected Logger logger;
	
	//the class logID
	public static final String logID = "[GenericEvent]: ";
	
	/**
	 * The generic event constructor.
	 * 
	 * @param type
	 * @param src
	 * @param timestamp
	 * @param value
	 * @param unitOfMeasure
	 */
	public GenericEvent(EventTypeEnum type, String src, String streamName, Calendar timestamp, Double value, String unitOfMeasure)
	{
		//init the logger
		this.logger = Logger.getLogger(GenericEvent.class.getPackage().getName());
		
		// the event type
		this.type = type;
		
		// the event source
		this.src = src;
		
		//the event origin
		this.streamName = streamName;
		
		// the event time stamp
		this.timestamp = timestamp;
		
		// the value of the event (either a Double or a Boolean object)
		this.value = value;
		
		// the unit of measure of the above value
		this.unitOfMeasure = unitOfMeasure;
	}
	
	public GenericEvent(EventTypeEnum type, String src, String streamName, Double value, String unitOfMeasure)
	{
		//init the logger
		this.logger = Logger.getLogger(GenericEvent.class.getPackage().getName());
		
		// the event type
		this.type = type;
		
		// the event source
		this.src = src;
		
		//the event origin
		this.streamName = streamName;
		
		// the event time stamp
		this.timestamp = Calendar.getInstance();
		
		// the value of the event (either a Double or a Boolean object)
		this.value = value;
		
		// the unit of measure of the above value
		this.unitOfMeasure = unitOfMeasure;
	}
	
	
	/**
	 * 
	 */
	public GenericEvent()
	{
		this.timestamp = Calendar.getInstance();
	}



	/**
	 * check if the event represented by this object is measurable
	 * 
	 * @return true if the object is measurable, false otherwise
	 */
	public abstract boolean isValueMeasurable();
	
	/**
	 * If the event value is measurable return the JScience measure object
	 * representing the event value.
	 * 
	 * @return The event value as a {@link Measure} or null;
	 */
	public abstract DecimalMeasure<?> getValueAsMeasure();
	
	/**
	 * If the event is of type boolean return the event value as a native boolean value
	 * @return the event value
	 */
	public abstract Boolean getValueAsBoolean();
	
	/**
	 * Returns the type of this event as an {@link EventTypeEnum} value
	 * 
	 * @return the type of this event instance.
	 */
	public EventTypeEnum getType()
	{
		return type;
	}
	
	/**
	 * Returns the identifier of the event source, i.e. a unique identifier
	 * allowing to trace the last source of the event.
	 * 
	 * @return the event source identifier.
	 */
	public String getSrc()
	{
		return src;
	}
	
	public void setSrc(String src)
	{
		this.src = src;
	}
	
	/**
	 * Returns the identifier of the event origin, i.e. a unique identifier
	 * allowing to trace the original source of the event.
	 * 
	 * @return the event source identifier.
	 */
	public String getStreamName()
	{
		return streamName;
	}
	
	/**
	 * Returns the time stamp at which the event has been generated.
	 * @return the time stamp as a {@link Calendar} object.
	 */
	public Calendar getTimestamp()
	{
		return timestamp;
	}
	
	/**
	 * Returns the event value (always a number)
	 * @return the value
	 */
	public Double getValue()
	{
		return value;
	}
	
	/**
	 * Returns the value unit of measure as a {@link String} if available, null otherwise
	 * @return the unit of measure, as a String.
	 */
	public String getUnitOfMeasure()
	{
		return unitOfMeasure;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(EventTypeEnum type)
	{
		this.type = type;
	}

	/**
	 * @param streamName the streamName to set
	 */
	public void setStreamName(String streamName)
	{
		this.streamName = streamName;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Calendar timestamp)
	{
		this.timestamp = timestamp;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Double value)
	{
		this.value = value;
	}

	/**
	 * @param unitOfMeasure the unitOfMeasure to set
	 */
	public void setUnitOfMeasure(String unitOfMeasure)
	{
		this.unitOfMeasure = unitOfMeasure;
	}
	
}
