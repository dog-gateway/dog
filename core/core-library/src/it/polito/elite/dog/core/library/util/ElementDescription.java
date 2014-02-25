/*
 * Dog - Core
 * 
 * Copyright (c) 2010-2013 Dario Bonino, Luigi De Russis and Emiliano Castellina
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
package it.polito.elite.dog.core.library.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A class for representing device-specific element (e.g. command) in a more
 * Java-like way
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:emiliano.castellina@polito.it">Emiliano
 *         Castellina</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class ElementDescription implements Serializable
{
	
	/**
	 * The serial version identifier
	 */
	private static final long serialVersionUID = 1L;
	
	// the command name
	private String name;
	
	// the command type
	private String type;
	
	// the command-specific parameters
	private Map<String, String> elementParams;
	
	/**
	 * Creates a new Command description object describing the low-level
	 * properties of a given command, associated to a given device...
	 * 
	 * @param name
	 *            The name of the command (URI trimmed after the # character)
	 * @param type
	 *            The DogOnt type of the command (with namespace)
	 * @param elementParams
	 *            The command specifc parameters as a opaque {@link Map}
	 *            reporting couples of param name and param value
	 */
	public ElementDescription(String name, String type, Map<String, String> elementParams)
	{
		super();
		this.name = name;
		this.type = type;
		this.elementParams = elementParams;
	}
	
	/**
	 * Creates a new Command description object describing the low-level
	 * properties of a given command, associated to a given device...
	 * 
	 * @param name
	 *            The name of the command (URI trimmed after the # character)
	 * @param type
	 *            The DogOnt type of the command (with namespace)
	 */
	public ElementDescription(String name, String type)
	{
		super();
		this.name = name;
		this.type = type;
		this.elementParams = new HashMap<String, String>();
	}
	
	/**
	 * Returns the command-specific parameter {@link Map} holding couples param
	 * name - param value as {@link String} objects.
	 * 
	 * @return The params {@link Map}
	 */
	public Map<String, String> getElementParams()
	{
		return elementParams;
	}
	
	/**
	 * command-specific parameter {@link Map} holding couples param name - param
	 * value as {@link String} objects.
	 * 
	 * @param elementParams
	 *            The {@link Map}<{@link String},{@link String}> object
	 *            containing the couples to set as command-specific parameters.
	 */
	public void setElementParams(Map<String, String> elementParams)
	{
		this.elementParams = elementParams;
	}
	
	/**
	 * Adds one param name - param value couple to this command description
	 * object
	 * 
	 * @param paramName
	 *            The name of the parameter to add
	 * @param paramValue
	 *            The value of the parameter to add
	 * @return
	 */
	public boolean addElementParam(String paramName, String paramValue)
	{
		if (this.elementParams != null)
		{
			this.elementParams.put(paramName, paramValue);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Provides back the command name, i.e. the command URI starting from the #
	 * character
	 * 
	 * @return The command name.
	 */
	public String getElementName()
	{
		return name;
	}
	
	/**
	 * Provides back the command type as a qualified type name in DogOnt, e.g.,
	 * dogont:SimpleLamp
	 * 
	 * @return The command type.
	 */
	public String getElementType()
	{
		return type;
	}
}
