/*
 * Dog - Core
 * 
 * Copyright (c) 2011-2013 Dario Bonino
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
import java.util.logging.Level;

import javax.measure.DecimalMeasure;

/**
 * A class representing real-valued events, which might be measurable (i.e.,
 * they might be referring to physical quantities measured in a given SI or
 * non-SI unit).
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * @since September, 2011
 * @version 0.1
 * 
 */
public class RealEvent extends GenericEvent
{
	public static final String logID = "[RealEvent]: ";
	
	/**
	 * @param type
	 * @param src
	 * @param timestamp
	 * @param value
	 * @param unitOfMeasure
	 */
	public RealEvent(String src, String streamName, Calendar timestamp, Double value, String unitOfMeasure)
	{
		super(EventTypeEnum.REAL, src, streamName, timestamp, value, unitOfMeasure);
	}
	
	public RealEvent()
	{
		super();
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
		return (this.unitOfMeasure!=null);
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
		DecimalMeasure<?> valueAsMeasure = null;
		try
		{
			 valueAsMeasure = DecimalMeasure.valueOf(this.value.doubleValue()+" "+this.unitOfMeasure);
		}
		catch (IllegalArgumentException e)
		{
			this.logger.log(Level.SEVERE, RealEvent.logID
					+ "Unable to convert the event value into a measure, error while parsing the unit of measure");
		}
		
		return valueAsMeasure;
	}
	
	@Override
	public Boolean getValueAsBoolean()
	{
		return null;
	}
}
