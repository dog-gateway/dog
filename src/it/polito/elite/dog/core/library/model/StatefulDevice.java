/*
 * Dog - Core
 * 
 * Copyright (c) 2010-2013 Dario Bonino and Luigi De Russis
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
package it.polito.elite.dog.core.library.model;

/**
 * This interface should be implemented by the Driver created for Dog. It is not
 * strictly mandatory to implement since it is not included in the OSGi
 * specifications. Nevertheless this interface provides a minimum coherence
 * between different drivers.
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public interface StatefulDevice
{
	/**
	 * Get the current state(s)
	 * 
	 * @return a DeviceStatus object, storing the current state(s)
	 */
	public DeviceStatus getState();
}
