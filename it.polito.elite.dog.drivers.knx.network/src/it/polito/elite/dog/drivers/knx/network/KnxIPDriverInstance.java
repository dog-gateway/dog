/*
 * Dog - Network Driver
 * 
 * Copyright (c) 2011-2013 Dario Bonino and Luigi De Russis
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
package it.polito.elite.dog.drivers.knx.network;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.StatefulDevice;
import it.polito.elite.dog.core.library.util.ElementDescription;
import it.polito.elite.dog.drivers.knx.network.info.KnxIPDeviceInfo;
import it.polito.elite.dog.drivers.knx.network.info.KnxIPInfo;
import it.polito.elite.dog.drivers.knx.network.interfaces.KnxIPNetwork;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * An abstract class to be extended by device-specific network drivers, allows
 * driver developers to avoid dealing with common network operations.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public abstract class KnxIPDriverInstance implements StatefulDevice
{
	// a reference to the network driver interface to allow network-level access
	// for sub-classes
	protected KnxIPNetwork network;
	
	// the state of the device associated to this driver
	protected DeviceStatus currentState;
	
	// the device associated to the driver
	protected ControllableDevice device;
	
	// the gateway data...
	protected String gwAddress;
	protected int gwPort;
	
	// the List of group addresses pertaining to the device to which this driver
	// instance is attached
	protected Set<String> groupAddressSet;
	
	// the global group address to use for generic commands
	protected String globalGroupAddress;
	
	// a map associating groupAddresses with commands as KnxIPDeviceInfo
	// instances
	// protected Map<String, KnxIPDeviceInfo> groupAddress2CommandMap;
	
	// a map associating command names with the corresponding KnxIPDeviceInfo
	// objects (needed to map command names given by the executor to group
	// addresses)
	protected Map<String, KnxIPDeviceInfo> commandName2CommandMap;
	
	// a map associating groupAddresses with notifications as KnxIPDeviceInfo
	// instances
	protected HashMap<String, Set<KnxIPDeviceInfo>> groupAddress2NotificationMap;
	
	/***
	 * Notifies a device-specific driver of a new message coming from the
	 * KNXNet/IP connection (through the network driver), provides the decoded
	 * message content, together with the message sender (i.e., the individual
	 * address of the device that generated the message) and the message
	 * destination (i.e., the group address to which the message was sent).
	 * Device-specific drivers must implement this method to receive messages
	 * from the network driver.
	 * 
	 * @param source
	 *            The message sender (Individual address in the form x.x.x)
	 * @param destination
	 *            The message destination (Group address in the form x/x/x)
	 * @param value
	 *            The decoded message value.
	 */
	public abstract void newMessageFromHouse(String source, String destination, String value);
	
	/**
	 * The base class constructor, provides common initialization for all the
	 * needed data structures, must be called by sub-class constructors
	 * 
	 * @param network
	 *            the network driver to use (as described by the
	 *            {@link KnxIPNetwork} interface.
	 * @param device
	 *            the device to which this driver is attached/associated
	 */
	public KnxIPDriverInstance(KnxIPNetwork network, ControllableDevice device, String gatewayAddress)
	{
		// store a reference to the network driver
		this.network = network;
		
		// store a reference to the associate device
		this.device = device;
		
		// store the gateway information for the attached device
		this.gwAddress = gatewayAddress;
		this.gwPort = KnxIPInfo.DEFAULT_PORT;
		
		// create the maps needed to associate group addresses to command and
		// notification descriptions.
		this.commandName2CommandMap = new HashMap<String, KnxIPDeviceInfo>();
		this.groupAddress2NotificationMap = new HashMap<String, Set<KnxIPDeviceInfo>>();
		
		// create the set of group addresses handled by this driver
		this.groupAddressSet = new HashSet<String>();
		
		// fill the data structures depending on the specific device
		// configuration parameters
		this.fillConfiguration();
		
		// call the specific configuration method, if needed
		this.specificConfiguration();
		
		// associate the device-specific driver to the network driver
		for (String groupAddress : this.groupAddressSet)
		{
			
			try
			{
				KnxIPDeviceInfo devInfo = new KnxIPDeviceInfo(this.device.getDeviceId(), groupAddress);
				devInfo.setGatewayIPAddress(InetAddress.getByName(this.gwAddress));
				this.addToNetworkDriver(devInfo);
			}
			catch (Exception e)
			{
				// handle exception here
			}
			
		}
		
	}
	
	/**
	 * Adds a device managed by a device-specific driver instance to the
	 * KnxIPNetwork driver. It must be implemented by extending classes and it
	 * must take care of identifying the datapoint type associated to the given
	 * group address and to register the proper datapoint information in the
	 * network-level driver.
	 * 
	 * @param groupAddress
	 *            the group address of the device to add to the network driver
	 */
	protected abstract void addToNetworkDriver(KnxIPDeviceInfo deviceInfo);
	
	/***
	 * Fills the inner data structures depending on the specific device
	 * configuration parameters, extracted from the device instance associated
	 * to this driver instance
	 */
	protected void fillConfiguration()
	{
		// fill the list of group addresses associated to the given device
		Map<String, Set<String>> simpleConfigParams = this.device.getDeviceDescriptor()
				.getSimpleConfigurationParams();
		
		// check not null
		if (simpleConfigParams != null)
		{
			Set<String> groupAddresses = simpleConfigParams.get(KnxIPInfo.GROUP_ADDRESS);
			if (groupAddresses != null)
				this.groupAddressSet.addAll(groupAddresses);
		}
		
		// extract the general group address (to strengthen...)
		if (this.groupAddressSet.size() > 0)
			this.globalGroupAddress = this.groupAddressSet.iterator().next();
		
		// get parameters associated to each device command (if any)
		Set<ElementDescription> commandsSpecificParameters = this.device.getDeviceDescriptor()
				.getCommandSpecificParams();
		
		// get parameters associated to each device notification (if any)
		Set<ElementDescription> notificationsSpecificParameters = this.device.getDeviceDescriptor()
				.getNotificationSpecificParams();
		
		/*************** HANDLE COMMAND SPECIFICATIONS **************************/
		
		// fill the groupAddress2Command map, adding command details whenever
		// needed
		for (ElementDescription commandSpec : commandsSpecificParameters)
		{
			try
			{
				// get the command name
				String realName = commandSpec.getElementParams().get(KnxIPInfo.COMMAND_NAME);
				
				// get the command address
				String groupAddress = commandSpec.getElementParams().get(KnxIPInfo.GROUP_ADDRESS);
				
				// use the global group address if the specific is not
				// specified...
				if (groupAddress == null)
					groupAddress = this.globalGroupAddress;
				
				// check not null, otherwise ignore the current command
				if (realName != null && groupAddress != null)
				{
					// create the command details given the current command
					// specification
					KnxIPDeviceInfo command = new KnxIPDeviceInfo(realName, groupAddress);
					
					// set the gateway to be used to send this command to the
					// real device
					command.setGatewayIPAddress(InetAddress.getByName(this.gwAddress));
					
					// set any parameter associated to the given command
					// (extracting
					// them from the current command specification)
					command.setParameters(commandSpec.getElementParams());
					
					// add the command to the maps
					this.commandName2CommandMap.put(realName, command);
					
					// add the group address to the set of handled group
					// addresses
					// (if duplicate it will be ignored)
					this.groupAddressSet.add(groupAddress);
				}
			}
			catch (Exception e)
			{
				// do not handle the commands...
			}
		}
		
		/*************** HANDLE NOTIFICATION SPECIFICATIONS **************************/
		
		// fill the groupAddress2NotificationMap
		for (ElementDescription notificationSpec : notificationsSpecificParameters)
		{
			try
			{
				// get the notification name
				String realName = notificationSpec.getElementType();
				
				// get the notification group address
				String groupAddress = notificationSpec.getElementParams().get(KnxIPInfo.GROUP_ADDRESS);
				
				if (realName != null && groupAddress != null)
				{
					// create the notification details given the current
					// notification specification
					KnxIPDeviceInfo notification = new KnxIPDeviceInfo(realName, groupAddress);
					
					// set the gateway to be used to send this notification to
					// the
					// real device
					notification.setGatewayIPAddress(InetAddress.getByName(this.gwAddress));
					
					// set any parameter associated to the given notification
					// (extracting them from the current notification
					// specification)
					notification.setParameters(notificationSpec.getElementParams());
					
					// add the notification to the groupAddress2NotificationMap
					this.addNotificationToMap(this.groupAddress2NotificationMap, notification, groupAddress);
					
					// add the group address to the set of handled group
					// addresses
					// (if duplicate it will be ignored)
					this.groupAddressSet.add(groupAddress);
				}
			}
			
			catch (Exception e)
			{
				// do not handle the exception
			}
		}
		
	}
	
	/**
	 * Extending classes might implement this method to provide driver-specific
	 * configurations to be done during the driver creation process, before
	 * associating the device-specific driver to the network driver
	 */
	protected abstract void specificConfiguration();
	
	/**
	 * Handles the groupAddress2NotificationMap filling where multiple
	 * notifications may be associated to the same group address.
	 * 
	 * @param notificationMap
	 *            the notification map to be filled
	 * @param notification
	 *            the notification to add
	 * @param key
	 *            the corresponding group address
	 */
	protected void addNotificationToMap(HashMap<String, Set<KnxIPDeviceInfo>> notificationMap,
			KnxIPDeviceInfo notification, String key)
	{
		if (key != null)
		{
			Set<KnxIPDeviceInfo> notificationSet = notificationMap.get(key);
			if (notificationSet == null)
			{
				notificationSet = new HashSet<KnxIPDeviceInfo>();
				notificationMap.put(key, notificationSet);
			}
			notificationSet.add(notification);
		}
	}
	
	/**
	 * Tries to retrieve the initial state of the device handled by this driver
	 */
	public void getInitialState()
	{
		for (String groupAddress : this.groupAddress2NotificationMap.keySet())
		{
			try
			{
				KnxIPDeviceInfo devInfo = new KnxIPDeviceInfo(this.device.getDeviceId(), groupAddress);
				devInfo.setGatewayIPAddress(InetAddress.getByName(this.gwAddress));
				
				this.network.read(devInfo);
			}
			
			catch (Exception e)
			{
				// handle exception here
			}
		}
		
	}
	
	/**
	 * @return the gwAddress
	 */
	public String getGatewayAddress()
	{
		return gwAddress;
	}
	
}
