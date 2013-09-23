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

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.measure.DecimalMeasure;
import javax.measure.unit.Unit;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * @since Sep 27, 2011
 *
 */
public class MeasureEventComparator
{
	private static Logger logger = Logger.getLogger(MeasureEventComparator.class.getPackage().getName());
	/**
	 * Compares two real events, with possibly different unit of measure and, if
	 * they are compatible (same quantity), performs the needed unit conversions
	 * and compares them according to the given comparison operator.
	 * 
	 * @param event1 The first event to compare
	 * @param event2 The second event to compare
	 * @param operator the operator to apply: event1 operator event2
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean compare(RealEvent event1, RealEvent event2, EventComparisonEnum operator)
	{
		boolean comparisonRes = false;
		
		//get both events as a measure
		DecimalMeasure<?> event1M =event1.getValueAsMeasure();
		DecimalMeasure<?> event2M =event2.getValueAsMeasure();
		
		Unit unit1 = event1M.getUnit();
		Unit unit2 = event2M.getUnit();
		
		if(unit1.isCompatible(unit2))
		{
			switch(operator)
			{
				case GREATER_THAN:
				{
					comparisonRes = (event1M.doubleValue(unit1)>event2M.doubleValue(unit1));
					break;
				}
				case GREATER_THAN_OR_EQUAL:
				{
					comparisonRes = (event1M.doubleValue(unit1)>= event2M.doubleValue(unit1));
					break;
				}
				case LESS_THAN:
				{
					comparisonRes = (event1M.doubleValue(unit1)<event2M.doubleValue(unit1));
					break;
				}
				case LESS_THAN_OR_EQUAL:
				{
					comparisonRes = (event1M.doubleValue(unit1)<=event2M.doubleValue(unit1));
					break;
				}
				case EQUAL:
				{
					comparisonRes = (event1M.doubleValue(unit1)==event2M.doubleValue(unit1));
					break;
				}
			}
		}
		
		logger.log(Level.FINER,"[MeasureEventComparator]: checked if "+event1M.doubleValue(unit1)+" is "+operator+" "+event2M.doubleValue(unit1)+" result: "+comparisonRes);
		return comparisonRes;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean compareToMeasure(RealEvent event1, String measureAsString, EventComparisonEnum operator)
	{
		boolean comparisonRes = false;
		
		//get both events as a measure
		DecimalMeasure<?> event1M =event1.getValueAsMeasure();
		DecimalMeasure<?> measure = DecimalMeasure.valueOf(measureAsString);
		Unit unit1 = event1M.getUnit();
		Unit unit2 = measure.getUnit();
		
		if(unit1.isCompatible(unit2))
		{
			switch(operator)
			{
				case GREATER_THAN:
				{
					comparisonRes = (event1M.doubleValue(unit1)>measure.doubleValue(unit1));
					break;
				}
				case GREATER_THAN_OR_EQUAL:
				{
					comparisonRes = (event1M.doubleValue(unit1)>= measure.doubleValue(unit1));
					break;
				}
				case LESS_THAN:
				{
					comparisonRes = (event1M.doubleValue(unit1)<measure.doubleValue(unit1));
					break;
				}
				case LESS_THAN_OR_EQUAL:
				{
					comparisonRes = (event1M.doubleValue(unit1)<=measure.doubleValue(unit1));
					break;
				}
				case EQUAL:
				{
					comparisonRes = (event1M.doubleValue(unit1)==measure.doubleValue(unit1));
					break;
				}
			}
		}
		
		logger.log(Level.FINER,"[MeasureEventComparator]: checked if "+event1M.doubleValue(unit1)+" is "+operator+" "+measure.doubleValue(unit1)+" result: "+comparisonRes);
		return comparisonRes;
	}
}
