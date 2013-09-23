/*
 * Dog - Core
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
package it.polito.elite.dog.core.housemodel.api;

import it.polito.elite.dog.core.library.model.DeviceDescriptor;

import java.util.Vector;

/**
 * The API for providing the HouseModel service, i.e., the configuration of the
 * environment.
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public interface HouseModel
{
	/**
	 * Get the list of the devices configured in the real environment.
	 * 
	 * @return a Vector of DeviceDescriptor containing all the devices
	 *         information
	 */
	public Vector<DeviceDescriptor> getConfiguration();
	
	/**
	 * Get the SVG house plan.
	 * 
	 * @return the SVG representing the current environment
	 */
	public String getSVGPlan();
}
