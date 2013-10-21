/*
 * Dog  - Z-Wave
 * 
 * Copyright 2013 Dario Bonino 
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
package it.polito.elite.dog.drivers.zwave.persistence;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * @author bonino
 *
 */
public class JSONPersistenceManager
{
	private static ObjectMapper theMapper;
	
	private static ObjectMapper getMapperInstance()
	{
		if(JSONPersistenceManager.theMapper == null)
			JSONPersistenceManager.theMapper = new ObjectMapper();
		
		return JSONPersistenceManager.theMapper;
	}

	
	private File persistentStore;
	
	public JSONPersistenceManager(String persistentStoreName)
	{
		// TODO Auto-generated constructor stub
	}

	public <T> T[] load(Class<T[]> arrayClass)
	{
		ObjectMapper mapper = JSONPersistenceManager.getMapperInstance();
		try
		{
			mapper.readValue(this.persistentStore, arrayClass);
		}
		catch (JsonParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JsonMappingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}

	public boolean exists()
	{
		// TODO Auto-generated method stub
		return false;
	}

}
