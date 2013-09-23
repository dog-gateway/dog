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

import javax.measure.DecimalMeasure;

/**
 * A class representing simple boolean events. Boolean event values are mapped
 * to integer values (true = 1, false = 0) to allow event processing in blocks
 * having real ports.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * @since September, 2011
 * 
 */
public class BooleanEvent extends GenericEvent
{
	
	/**
	 * @param type
	 * @param src
	 * @param timestamp
	 * @param value
	 * @param unitOfMeasure
	 */
	public BooleanEvent(String src, String streamName, Calendar timestamp, Boolean value)
	{
		super(EventTypeEnum.BOOLEAN, src, streamName, timestamp, value ? 1.0 : 0.0, null);
	}
	
	public BooleanEvent()
	{
		super();
	}
	
	public BooleanEvent(String src, String streamName, Boolean value)
	{
		super(EventTypeEnum.BOOLEAN, src, streamName, Calendar.getInstance(), value ? 1.0 : 0.0, null);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.stream.processing.events.GenericEvent#isValueMeasurable()
	 */
	@Override
	public boolean isValueMeasurable()
	{
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.stream.processing.events.GenericEvent#getValueAsMeasure()
	 */
	@Override
	public DecimalMeasure<?> getValueAsMeasure()
	{
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.stream.processing.events.GenericEvent#getValueAsBoolean()
	 */
	@Override
	public Boolean getValueAsBoolean()
	{
		return (this.value.intValue() == 1);
	}

	/* (non-Javadoc)
	 * @see it.polito.elite.stream.processing.events.GenericEvent#setValue(java.lang.Double)
	 */
	public void setBooleanValue(boolean value)
	{
		// TODO Auto-generated method stub
		super.setValue(value ? 1.0 : 0.0);
	}
	
}
