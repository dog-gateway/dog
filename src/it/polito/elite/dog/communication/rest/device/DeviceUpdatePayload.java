/*
 * Dog - Device Rest Endpoint
 * 
 * Copyright (c) 2013 Luigi De Russis
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
package it.polito.elite.dog.communication.rest.device;

/**
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class DeviceUpdatePayload
{
	private String isIn;
	private String description;

	/**
	 * 
	 */
	public DeviceUpdatePayload()
	{
		// intentionally left empty
	}

	/**
	 * @return the isIn
	 */
	public String getIsIn()
	{
		return this.isIn;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setIsIn(String isIn)
	{
		this.isIn = isIn;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
}
