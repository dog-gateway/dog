/*
 * Dog - EnOcean Gateway Driver
 * 
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
package it.polito.elite.dog.communication.rest.device.command;

import it.polito.elite.dog.core.library.model.technology.ExplicitTeachInData;



/**
 * A payload implementation which allows parsing data about explicit teach in,
 * namely the eep and serial number of the device to teach in.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 *
 */
public class ExplicitTeachInPayload extends CommandPayload<ExplicitTeachInData>
{
	public ExplicitTeachInPayload()
	{
		// empty constructor implementing the bean instantiation pattern
	}

	// setters for "registered types"
	public void setValue(ExplicitTeachInData teachInData)
	{
		this.value = teachInData;
	}

	public ExplicitTeachInData getValue()
	{
		return value;
	}
}
