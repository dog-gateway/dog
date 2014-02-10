/*
 * Dog - Admin
 * 
 * Copyright (c) 2013-2014 Dario Bonino and Luigi De Russis
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
package it.polito.elite.dog.admin.system.bundlemanager.model;

/**
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class BundleStateResponse
{
	private String name;
	private String state;
	
	public BundleStateResponse()
	{
		// intentionally left empty
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String bundleName)
	{
		this.name = bundleName;
	}
	
	public String getState()
	{
		return this.state;
	}
	
	public void setState(String bundleState)
	{
		this.state = bundleState;
	}
	
}
