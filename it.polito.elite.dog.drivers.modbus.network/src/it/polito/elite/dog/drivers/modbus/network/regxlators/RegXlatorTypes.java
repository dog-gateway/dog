/*
 * Dog 2.0 - Modbus Network Driver
 * 
 * Copyright [2012] 
 * [Dario Bonino (dario.bonino@polito.it), Politecnico di Torino] 
  * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package it.polito.elite.dog.drivers.modbus.network.regxlators;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A class for managing RegTranslator types, naming is partially derived from
 * the KNX specification
 * 
 * <table>
 * <tr>
 * <th>RegXLatorId</th>
 * <th>RegXlatorType</th>
 * </tr>
 * <tr>
 * <td>0</td>
 * <td>RegXlatorBooleanCoil</td>
 * </tr>
 * <tr>
 * <td>2</td>
 * <td>RegXlator2ByteInteger</td>
 * </tr>
 * <tr>
 * <td>4</td>
 * <td>RegXlator4ByteIntegerHolding</td>
 * </tr>
 * <tr>
 * </table>
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>, Politecnico
 *         di Torino
 * @author <a href="mailto:muhammad.sanaullah@polito.it">Muhammad Sanaullah</a>,
 *         Politecnico di Torino
 * 
 * @since Feb 28, 2012
 */
public class RegXlatorTypes
{
	// the already defined types
	// TODO: updated this set of static variables to a Java Enumeration 
	// TODO: major version change as definitions will no more be backward compatible
	public static int TYPE_BOOLEAN_COIL = 0;
	public static int TYPE_2BYTE_INTEGER_INPUT = 1;
	public static int TYPE_2BYTE_INTEGER_HOLDING = 2;
	public static int TYPE_4BYTE_INTEGER_HOLDING = 4;
	public static int TYPE_4BYTE_INTEGER_HOLDING_BE = 3;
	public static int TYPE_4BYTE_FLOAT_HOLDING = 5;
	public static int TYPE_4BYTE_FLOAT_HOLDING_BE = 9;
	public static int TYPE_4BYTE_FLOAT_INPUT = 6;
	public static int TYPE_4BYTE_FLOAT_INPUT_BE = 6;
	public static int TYPE_4BYTE_INTEGER_INPUT = 7;
	public static int TYPE_4BYTE_INTEGER_INPUT_BE = 8;
	
	// the class for representing RegXlators and corresponding IDs
	private static ConcurrentHashMap<Integer, RegXlatorType> types = new ConcurrentHashMap<Integer, RegXlatorType>();
	
	static
	{
		types.put(TYPE_BOOLEAN_COIL, new RegXlatorType(TYPE_BOOLEAN_COIL, RegXlatorBooleanCoil.class,
				"Register value translator for coil registers"));
		types.put(TYPE_2BYTE_INTEGER_INPUT, new RegXlatorType(TYPE_2BYTE_INTEGER_INPUT, RegXlator2ByteIntegerInput.class,
				"Register value translator for 2 Bytes integer input registers"));
		types.put(TYPE_4BYTE_INTEGER_HOLDING, new RegXlatorType(TYPE_4BYTE_INTEGER_HOLDING, RegXlator4ByteIntegerHolding.class,
				"Register value translator for 4 Bytes little-endian unsigned registers"));
		types.put(TYPE_4BYTE_INTEGER_HOLDING_BE, new RegXlatorType(TYPE_4BYTE_INTEGER_HOLDING_BE, RegXlator4ByteIntegerHoldingBE.class,
				"Register value translator for 4 Bytes big-endian unsigned registers"));
		types.put(TYPE_4BYTE_FLOAT_HOLDING, new RegXlatorType(TYPE_4BYTE_FLOAT_HOLDING, RegXlator4ByteFloatHolding.class,
				"Register value translator for 4 Bytes little-endian float holding registers"));
		types.put(TYPE_4BYTE_FLOAT_HOLDING_BE, new RegXlatorType(TYPE_4BYTE_FLOAT_HOLDING_BE, RegXlator4ByteFloatHoldingBE.class,
				"Register value translator for 4 Bytes big-endian float holding registers"));
		types.put(TYPE_4BYTE_FLOAT_INPUT, new RegXlatorType(TYPE_4BYTE_FLOAT_INPUT, RegXlator4ByteFloatInput.class,
				"Register value translator for 4 Bytes little-endian float input registers"));
		types.put(TYPE_4BYTE_FLOAT_INPUT_BE, new RegXlatorType(TYPE_4BYTE_FLOAT_INPUT_BE, RegXlator4ByteFloatInputBE.class,
				"Register value translator for 4 Bytes big-endian float input registers"));
		types.put(TYPE_4BYTE_INTEGER_INPUT, new RegXlatorType(TYPE_4BYTE_INTEGER_INPUT, RegXlator4ByteIntegerInput.class,
				"Register value translator for 4 Bytes little-endian integer input registers"));
		types.put(TYPE_4BYTE_INTEGER_INPUT_BE, new RegXlatorType(TYPE_4BYTE_INTEGER_INPUT_BE, RegXlator4ByteIntegerInputBE.class,
				"Register value translator for 4 Bytes big-endian integer input registers"));
		
	}
	
	/**
	 * Adds a Register translator associated to the given type id (might be one
	 * of the types already defined in this class or a new type).
	 * 
	 * @param type The translator type id
	 * @param regXlator The translator class
	 * @param description The translator description
	 */
	public static void addTranslator(int type, Class<? extends RegXlator> regXlator, String description)
	{
		types.put(type, new RegXlatorType(type, regXlator, description));
	}
	
	/**
	 * Returns the translator type object representing the {@link RegXlator} instance having the given translator type. id
	 * @param id The id of the translator to retrieve
	 * @return The {@link RegXlatorType} corresponding to the given type id or null if no translator has been registered with the corresponding type id.
	 */
	public static RegXlatorType getRegXlatorType(int id)
	{
		return types.get(id);
	}
	
	/**
	 * Returns the whole map of registered RegXlators together with their corresponding translator type ids.
	 * @return 
	 */
	public static Map<Integer,RegXlatorType> getAllTypes()
	{
		return types;
	}
	
	/**
	 * Creates an instance of {@link RegXlator} of type corresponding to the given type id.
	 * @param typeId the id of the {@link RegXlator} type to be created.
	 * @return the new instance of {@link RegXlator}, specific for the given type.
	 */
	public static RegXlator createRegXlator(int typeId)
	{
		RegXlator xlator = null;
		RegXlatorType type = types.get(typeId);
		
		if(type!=null)
		{
			xlator = type.createRegXlator();
		}
		
		return xlator;
	}
}
