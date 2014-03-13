/*
                                     
 ####  #####  ####### #       ####   ####  #    #  ####  
#      #    # #     # #      #    # #    # #   #  #      
 ####  #    # ######  #      #    # #      ####    ####  
     # #####  #     # #      #    # #      #  #        # 
#    # #      #     # #      #    # #    # #   #  #    # 
 ####  #      ######  ######  ####   ####  #    #  #### 
 
 (c) Dario Bonino, e-Lite research group, http://elite.polito.it
 
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and limitations under the License. 
 
 */
package it.polito.elite.stream.processing.events;

import java.util.logging.Logger;

import javax.measure.unit.Unit;

/**
 * @author Dario Bonino <a
 *         href="mailto:dario.bonino@gmail.com">dario.bonino@gmail.com</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * @since Dic 12, 2011
 * @version 0.1
 * 
 */
public class UnitOfMeasureHelper
{
    // the class logger
    @SuppressWarnings("unused")
	private static Logger logger = Logger
	    .getLogger(MeasureEventComparator.class.getPackage().getName());

    public static String unitTimes(String unit1, String unit2)
    {
	// init the new unit at nothing
	String newUnit = "";

	if ((unit1 != null) && (unit2 != null))
	{
	    // convert given units to a JScience unit of measure
	    Unit<?> unit1AsUnit = Unit.valueOf(unit1);
	    Unit<?> unit2AsUnit = Unit.valueOf(unit2);

	    // if both units are not null, multiply the units
	    if ((unit1AsUnit != null) && (unit2AsUnit != null))
		newUnit = unit1AsUnit.times(unit2AsUnit).toString();

	}
	// TODO: check if at this point it is better to return the unit of the
	// not null measure, if one of the two is null.
	// return the new unit
	return newUnit;
    }
    
    public static String unitDivide(String unit1, String unit2)
    {
	// init the new unit at nothing
	String newUnit = "";

	if ((unit1 != null) && (unit2 != null))
	{
	    // convert given units to a JScience unit of measure
	    Unit<?> unit1AsUnit = Unit.valueOf(unit1);
	    Unit<?> unit2AsUnit = Unit.valueOf(unit2);

	    // if both units are not null, multiply the units
	    if ((unit1AsUnit != null) && (unit2AsUnit != null))
		newUnit = unit1AsUnit.divide(unit2AsUnit).toString();

	}
	// TODO: check if at this point it is better to return the unit of the
	// not null measure, if one of the two is null.
	// return the new unit
	return newUnit;
    }
}
