/*
 * Dog - EnOcean Network Driver
 * 
 * Copyright 2015 Dario Bonino 
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
package org.doggateway.dog.drivers.enocean.network;

import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.StatefulDevice;

/**
 * @author bonino
 *
 */
public abstract class EnOceanDriverInstance implements StatefulDevice
{

	/**
	 * 
	 */
	public EnOceanDriverInstance()
	{
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see it.polito.elite.dog.core.library.model.StatefulDevice#getState()
	 */
	@Override
	public DeviceStatus getState()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
