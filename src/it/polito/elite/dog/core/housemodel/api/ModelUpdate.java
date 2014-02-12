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
package it.polito.elite.dog.core.housemodel.api;

import it.polito.elite.dog.core.library.model.DeviceDescriptor;

/**
 * This interface describes the ability of a HouseModel bundle to accept and
 * model new devices, dynamically added at runtime. Dynamic management of
 * devices is particularly important for wireless networks such as Z-Wave and
 * ZigBee where devices may join (be associated) or leave (be dissociated) the
 * network with which Dog interacts.
 * 
 * Dynamically added devices shall be stored persistently in the model until an
 * explicit dissociation command is received. Such a requirement is strongly
 * related to the device location, which, once defined, must be retained even if
 * Dog or the Wireless network is shut down.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public interface ModelUpdate
{
	/**
	 * Requires to add a new device to the HouseModel. The {@link DogMessage}
	 * received as parameter contains a {@link DeviceDescriptor} describing the
	 * main characteristics of the device to be added. Such features include at
	 * least: the device unique name (URI), the device category and the device
	 * manufacturer.
	 * 
	 * @param msg
	 */
	public void addNewDevice(DeviceDescriptor descriptor);
	
	/**
	 * Requires to remove a device (and associated instances in the case of a
	 * Semantics-based House Model) from a HouseModel.
	 * 
	 * @param deviceURI
	 *            the URI of the device to be removed
	 */
	public void removeDevice(String deviceURI);
	
	/**
	 * Requires to change the location of a given device, identified by its URI.
	 * Whenever the addNewDevice message is called, without location, devices
	 * are added to the top most container instance available in the model
	 * (typically a Flat or Building instance). Afterwards, a GUI may require
	 * the user to specify the actual device location and this new location, by
	 * exploiting this method.
	 * 
	 * @param deviceURI
	 *            the URI of the device
	 * @param deviceLocation
	 *            the new location for the given device
	 */
	public void setDeviceLocation(String deviceURI, String deviceLocation);
}
