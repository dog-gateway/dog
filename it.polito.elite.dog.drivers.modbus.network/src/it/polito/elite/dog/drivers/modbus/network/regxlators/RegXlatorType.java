/*
 * Dog - Network Driver
 * 
 * Copyright (c) 2012-2013 Dario Bonino
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
package it.polito.elite.dog.drivers.modbus.network.regxlators;

import org.osgi.service.log.LogService;

/**
 * A class for representing/describing a RegXlatorClass...
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 * @since Feb 28, 2012
 */
public class RegXlatorType
{
	private int xlatorId;
	private Class<? extends RegXlator> xlatorClass;
	private String xlatorDescription;
	
	public static String logId = "[RegXlatorType]: ";
	
	/**
	 * @param xlatorId
	 * @param xlatorClass
	 * @param xlatorDescription
	 */
	public RegXlatorType(int xlatorId, Class<? extends RegXlator> xlatorClass, String xlatorDescription)
	{
		this.xlatorId = xlatorId;
		this.xlatorClass = xlatorClass;
		this.xlatorDescription = xlatorDescription;
	}
	
	/**
	 * @return the xlatorId
	 */
	public int getXlatorId()
	{
		return xlatorId;
	}
	
	/**
	 * @param xlatorId
	 *            the xlatorId to set
	 */
	public void setXlatorId(int xlatorId)
	{
		this.xlatorId = xlatorId;
	}
	
	/**
	 * @return the xlatorClass
	 */
	public Class<?> getXlatorClass()
	{
		return xlatorClass;
	}
	
	/**
	 * @param xlatorClass
	 *            the xlatorClass to set
	 */
	public void setXlatorClass(Class<? extends RegXlator> xlatorClass)
	{
		this.xlatorClass = xlatorClass;
	}
	
	/**
	 * @return the xlatorDescription
	 */
	public String getXlatorDescription()
	{
		return xlatorDescription;
	}
	
	/**
	 * @param xlatorDescription
	 *            the xlatorDescription to set
	 */
	public void setXlatorDescription(String xlatorDescription)
	{
		this.xlatorDescription = xlatorDescription;
	}
	
	/**
	 * Creates a RegXlator instance depending of the type it represents
	 * @return A RgeXlator instance...
	 */
	public RegXlator createRegXlator()
	{
		RegXlator xlator = null;
		try
		{
			xlator = (RegXlator) this.xlatorClass.newInstance();
		}
		catch (Exception e)
		{
			RegXlator.logger.log(LogService.LOG_ERROR, RegXlatorType.logId
					+ "Unable to create the required RegXlator instance\n" + e);
			
		}
		
		return xlator;
	}
	
}
