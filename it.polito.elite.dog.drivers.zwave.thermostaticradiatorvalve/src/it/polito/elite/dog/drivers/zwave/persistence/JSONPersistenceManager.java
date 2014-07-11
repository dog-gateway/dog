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
import java.io.FileWriter;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/**
 * @author bonino
 * 
 */
public class JSONPersistenceManager
{
	private static ObjectMapper theMapper;

	private static ObjectMapper getMapperInstance()
	{
		if (JSONPersistenceManager.theMapper == null)
		{
			JSONPersistenceManager.theMapper = new ObjectMapper();
			//set the date format
			JSONPersistenceManager.theMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		}

		return JSONPersistenceManager.theMapper;
	}

	private File persistentStore;

	public JSONPersistenceManager(String persistentStoreName) throws Exception
	{
		// build a File object pointing at the given location
		this.persistentStore = new File(persistentStoreName);

		// check writable
		if ((this.persistentStore.exists())
				&& (!this.persistentStore.canWrite()))
		{
			// unable to write, fail....
			throw new Exception("Unable to write the given persistence file ("
					+ persistentStoreName + "), please check permissions...");
		}
	}

	/**
	 * Loads a JSON persisted array of objects of type {@link T}
	 * 
	 * @param arrayClass
	 *            The class of objects to load
	 * @return The loaded array of T instances
	 */
	public <T> T[] load(Class<T[]> arrayClass)
	{
		ObjectMapper mapper = JSONPersistenceManager.getMapperInstance();
		T[] array = null;
		try
		{
			array = mapper.readValue(this.persistentStore, arrayClass);
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
		return array;
	}

	/**
	 * Checks if the persistence file associated to this persistence manager
	 * exists
	 * 
	 * @return true if the persistence file exists or false if the file does not
	 *         exist.
	 */
	public boolean exists()
	{
		return this.persistentStore.exists();
	}

	/**
	 * Replaces the content of the managed persistence file with the content of
	 * a given array of T values
	 * 
	 * @param values
	 *            The new values to sync with the persistence store
	 */
	public synchronized <T> void persist(T[] values) throws Exception
	{
		// serialize the values as JSON
		ObjectMapper mapper = JSONPersistenceManager.getMapperInstance();
		try
		{
			// render as JSON
			String updatedJSONPersistenceContent = mapper
					.writeValueAsString(values);

			// replace the file
			File tempFile = File.createTempFile(this.persistentStore.getName(),
					".bak");

			// copy the current file
			this.persistentStore.renameTo(tempFile);

			// write the new file
			try
			{
				FileWriter fw = new FileWriter(this.persistentStore, false);
				fw.write(updatedJSONPersistenceContent);
				fw.flush();
				fw.close();
			}
			catch (IOException e)
			{
				// unable to write the file...reset back the original file
				tempFile.renameTo(persistentStore);

				// re-throw the exception
				throw e;
			}
			finally
			{
				// delete on exit
				tempFile.delete();
			}

		}
		catch (JsonGenerationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JsonMappingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
