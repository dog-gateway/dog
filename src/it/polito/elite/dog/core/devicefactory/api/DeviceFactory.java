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
package it.polito.elite.dog.core.devicefactory.api;

import it.polito.elite.dog.core.library.model.DeviceDescriptor;

/**
 * This interface describes the ability of a DeviceFactory to handle new
 * devices, dynamically added at runtime. Dynamic management of devices is
 * particularly important for wireless networks such as Z-Wave and ZigBee where
 * devices may join (be associated) or leave (be dissociated) the network with
 * which Dog interacts.
 * 
 * Dynamically added devices shall be also stored persistently in the model
 * until an explicit 'remove' command is received. Such a requirement is
 * strongly related to the device location, which, once defined, must be
 * retained even if Dog or the wireless network is shut down.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public interface DeviceFactory
{
	/**
	 * Requires to add a new device to the framework. The {@link DogMessage}
	 * received as parameter contains a {@link DeviceDescriptor} describing the
	 * main characteristics of the device to be added. Such features include at
	 * least: the device unique name (URI), the device category and the device
	 * manufacturer.
	 * 
	 * @param msg
	 */
	/**
	 * Add a new device to the framework and communicate the adding to the
	 * referred HouseModel. The {@link DeviceDescriptor} received as parameter
	 * describes the main characteristics of the device to be added. Such
	 * features include, at least: the device unique name (URI), the device
	 * category and the device manufacturer.
	 * 
	 * @param descriptor
	 *            the {@link DeviceDescriptor} representing the device to add
	 */
	public void addNewDevice(DeviceDescriptor descriptor);
	
	/**
	 * Remove a device from the framework and communicate the deletion to the
	 * referred HouseModel.
	 * 
	 * @param deviceURI
	 *            the URI of the device to be removed
	 */
	public void removeDevice(String deviceURI);
	
	/**
	 * Update the characteristic of an existing device (e.g., its location
	 * inside the environment).
	 * 
	 * @param descriptor
	 *            the {@link DeviceDescriptor} representing the device to update
	 */
	public void updateDevice(DeviceDescriptor descriptor);
}
