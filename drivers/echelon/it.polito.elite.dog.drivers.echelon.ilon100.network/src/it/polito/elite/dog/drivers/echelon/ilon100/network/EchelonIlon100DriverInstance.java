/*
 * Dog - Network Driver
 * 
 * Copyright (c) 2012-2014 Dario Bonino and Luigi De Russis
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
package it.polito.elite.dog.drivers.echelon.ilon100.network;

import it.polito.elite.dog.core.library.util.ElementDescription;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.StatefulDevice;
import it.polito.elite.dog.drivers.echelon.ilon100.network.info.CmdNotificationInfo;
import it.polito.elite.dog.drivers.echelon.ilon100.network.info.DataPointInfo;
import it.polito.elite.dog.drivers.echelon.ilon100.network.info.EchelonIlonInfo;
import it.polito.elite.dog.drivers.echelon.ilon100.network.interfaces.EchelonIlon100Network;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * An abstract class to be extended by device-specific network drivers, allows
 * driver developers to avoid dealing with common network operations.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public abstract class EchelonIlon100DriverInstance implements StatefulDevice
{
	// a reference to the network driver interface to allow network-level access
	// for sub-classes
	protected EchelonIlon100Network network;
	
	// the state of the device associated to this driver
	protected DeviceStatus currentState;
	
	// the device associated to the driver
	protected ControllableDevice device;
	
	// the endpoint address associated to this device by means of the gateway
	// attribute
	protected String endpointAddress;
	
	// the datapoints managed by this driver
	protected Set<DataPointInfo> managedDatapoints;
	
	// the datapoint to notifications map
	protected Map<DataPointInfo, Set<CmdNotificationInfo>> datapoint2Notification;
	
	// the command2datapoint map
	protected Map<CmdNotificationInfo, DataPointInfo> command2Datapoint;
	
	/**
	 * Notifies a device-specific driver of a new datapoint value coming from
	 * the underlying iLon100 network connection (either through polling or
	 * through direct read). The updated value is contained in the given
	 * {@link DataPointInfo} instance.
	 * 
	 * @param dataPointInfo
	 *            The {@link DataPointInfo} instance representing the data point
	 *            whose value has changed.
	 */
	public abstract void newMessageFromHouse(DataPointInfo dataPointInfo);
	
	/**
	 * The base class constructor, provides common initialization for all the
	 * needed data structures, must be called by sub-class constructors
	 * 
	 * @param network
	 *            the network driver to use (as described by the
	 *            {@link EchelonIlon100Network} interface.
	 * @param device
	 *            the device to which this driver is attached/associated
	 */
	public EchelonIlon100DriverInstance(EchelonIlon100Network network, ControllableDevice device, String endpointAddress)
	{
		// store a reference to the network driver
		this.network = network;
		
		// store a reference to the associate device
		this.device = device;
		
		// store the endpoint address for the attached device
		this.endpointAddress = endpointAddress;
		
		// create the map needed to associate datapoints to notifications
		this.datapoint2Notification = new ConcurrentHashMap<DataPointInfo, Set<CmdNotificationInfo>>();
		
		// create the map to associate commands and datapoints
		this.command2Datapoint = new ConcurrentHashMap<CmdNotificationInfo, DataPointInfo>();
		
		// create the set for storing the managed datapoints
		this.managedDatapoints = new HashSet<DataPointInfo>();
		
		// fill the data structures depending on the specific device
		// configuration parameters
		this.fillConfiguration();
		
		// call the specific configuration method, if needed
		this.specificConfiguration();
		
		// associate the device-specific driver to the network driver
		for (DataPointInfo dp : this.managedDatapoints)
			this.addToNetworkDriver(dp);
		
	}
	
	/**
	 * Extending classes might implement this method to provide driver-specific
	 * configurations to be done during the driver creation process, before
	 * associating the device-specific driver to the network driver
	 */
	protected abstract void specificConfiguration();
	
	/**
	 * Adds a device managed by a device-specific driver instance to the
	 * {@link EchelonIlon100Network} driver. It must be implemented by extending
	 * classes and it must take care of identifying any additional information
	 * needed to correctly specify the given datapoint and to associate the
	 * corresponding {@link DataPointInfo} with the proper
	 * {@link EchelonIlon100DriverImpl} instance.
	 * 
	 * @param dp
	 *            the data point to add as a {@link DataPointInfo} instance.
	 */
	protected abstract void addToNetworkDriver(DataPointInfo dp);
	
	/***
	 * Fills the inner data structures depending on the specific device
	 * configuration parameters, extracted from the device instance associated
	 * to this driver instance
	 */
	private void fillConfiguration()
	{
		// gets the properties shared by almost all EchelonDevices, i.e. the
		// datapointId and the datapoint unit of measure
		// It must be noticed that such informations are specified for each
		// command/notification while no common parameters are defined/handled
		
		// get parameters associated to each device command (if any)
		Set<ElementDescription> commandsSpecificParameters = this.device.getDeviceDescriptor()
				.getCommandSpecificParams();
		
		// get parameters associated to each device notification (if any)
		Set<ElementDescription> notificationsSpecificParameters = this.device.getDeviceDescriptor()
				.getNotificationSpecificParams();
		
		// --------------- Handle command specific parameters ----------------
		for (ElementDescription parameter : commandsSpecificParameters)
		{
			// get the real command name
			String realCommandName = parameter.getElementParams().get(EchelonIlonInfo.COMMAND_NAME);
			
			// get the iLon data point id
			String datapointId = parameter.getElementParams().get(EchelonIlonInfo.DATAPOINT_ID);
			
			// get the iLon data point unit of measure
			String unitOfMeasure = parameter.getElementParams().get(EchelonIlonInfo.DATAPOINT_UOM);
			
			// get the iLon data point alias
			String datapointAlias = parameter.getElementParams().get(EchelonIlonInfo.DATAPOINT_ALIAS);
			
			// if no data point id has been specified, then the command
			// cannot be handled
			if (datapointId != null)
			{
				// build the data point info instance to associate to this
				// command
				DataPointInfo dp = new DataPointInfo(datapointId, datapointAlias, unitOfMeasure, this.endpointAddress);
				
				CmdNotificationInfo cmdInfo = new CmdNotificationInfo(realCommandName, parameter.getElementParams());
				// add the command to data point entry
				this.command2Datapoint.put(cmdInfo, dp);
				
				// add the datapoint to the set of managed datapoints
				this.managedDatapoints.add(dp);
			}
		}
		
		// --------------- Handle notification specific parameters
		// ----------------
		for (ElementDescription parameter : notificationsSpecificParameters)
		{
			// get the real command name
			String notificationName = parameter.getElementParams().get(EchelonIlonInfo.NOTIFICATION_NAME);
			
			// get the iLon data point id
			String datapointId = parameter.getElementParams().get(EchelonIlonInfo.DATAPOINT_ID);
			
			// get the iLon data point unit of measure
			String unitOfMeasure = parameter.getElementParams().get(EchelonIlonInfo.DATAPOINT_UOM);
			
			// get the iLon data point alias
			String datapointAlias = parameter.getElementParams().get(EchelonIlonInfo.DATAPOINT_ALIAS);
			
			// if no data point id has been specified, then the notification
			// cannot be handled
			if (datapointId != null)
			{
				// build the data point info instance to associate to this
				// command
				DataPointInfo dp = new DataPointInfo(datapointId, datapointAlias, unitOfMeasure, this.endpointAddress);
				
				// fill the data point to notification map, if the data point
				// has
				// never been registered create a new entry in the map.
				Set<CmdNotificationInfo> notificationNames = this.datapoint2Notification.get(dp);
				
				if (notificationNames == null)
				{
					notificationNames = new HashSet<CmdNotificationInfo>();
					this.datapoint2Notification.put(dp, notificationNames);
				}
				// add the notification name to the set associated to the dp
				// datapoint
				CmdNotificationInfo nInfo = new CmdNotificationInfo(notificationName, parameter.getElementParams());
				notificationNames.add(nInfo);
				
				// add the datapoint to the set of managed datapoints
				this.managedDatapoints.add(dp);
			}
		}
		
	}
	
	/**
	 * Provides safe access to the end point address associated to the device
	 * connected to this driver...
	 * 
	 * @return the endpointAddress
	 */
	public String getEndpointAddress()
	{
		return endpointAddress;
	}
	
	/**
	 * Tries to retrieve the initial state of the device handled by this driver
	 */
	public void getInitialState()
	{
		// for each datapoint registered with this driver, call the read command
		for (DataPointInfo dp : this.managedDatapoints)
			this.network.read(dp);
	}
}
