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

import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.drivers.echelon.ilon100.network.info.DataPointInfo;
import it.polito.elite.dog.drivers.echelon.ilon100.network.interfaces.EchelonIlon100Network;

import java.rmi.RemoteException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.log.LogService;

import com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_Data;
import com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString;
import com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_xSelect;
import com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item;
import com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll;
import com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Data;
import com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_DataColl;
import com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl.ILON100PortTypeProxy;

/**
 * The network driver for Echelon networks using the iLon 100 gateway as access
 * point.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class EchelonIlon100DriverImpl implements ManagedService, EchelonIlon100Network
{
	/** the default getAll xSelect to use for discovery **/
	public static final String XSELECT_ALL = "//Item[@xsi:type=\"Dp_Cfg\"]";
	
	// a reference to the bundle context
	private BundleContext bundleContext;
	
	// the service registration handle
	private ServiceRegistration<?> regServiceEchelonIlon100;
	
	// the driver logger
	private LogHelper logger;
	
	// the log identifier, unique for the class
	public static String logId = "[EchelonIlon100DriverImpl]: ";
	
	// map associating data points to drivers
	private Map<DataPointInfo, EchelonIlon100DriverInstance> datapoint2Driver;
	
	// the inverse map...for polling
	private Map<EchelonIlon100DriverInstance, Map<String, DataPointInfo>> driver2Datapoint;
	
	// the endpoint-to-datapoint association used for polling...
	private Map<String, Map<String, DataPointInfo>> endpoint2Datapoint;
	
	// the baseline pollingTime adopted if no endpoint-specific setting is given
	private int pollingTimeMillis = 5000; // default value
	
	// the driver poller...
	private EchelonIlon100Poller poller;
	
	public EchelonIlon100DriverImpl(BundleContext bundleContext)
	{
		// init the logger
		this.logger = new LogHelper(bundleContext);
		
		// store a reference to the bundle context
		this.bundleContext = bundleContext;
		
		// init the map between datapoints and drivers
		this.datapoint2Driver = new ConcurrentHashMap<DataPointInfo, EchelonIlon100DriverInstance>();
		
		// init the reverse map
		this.driver2Datapoint = new ConcurrentHashMap<EchelonIlon100DriverInstance, Map<String, DataPointInfo>>();
		
		// init the endpoint2Datapoint map
		this.endpoint2Datapoint = new ConcurrentHashMap<String, Map<String, DataPointInfo>>();
		
		// register this bundle as a managed service, i.e. as a bundle able to
		// be configured through a proper configuration file
		this.registerManagedService();
	}
	
	/**
	 * Unregisters the driver from the OSGi framework
	 */
	public void unRegister()
	{
		// stop the poller
		if (this.poller != null)
		{
			this.poller.setRunnable(false);
		}
		
		// unregister
		if (this.regServiceEchelonIlon100 != null)
		{
			this.regServiceEchelonIlon100.unregister();
		}
		
	}
	
	/***
	 * Register this class as a Managed Service
	 */
	private void registerManagedService()
	{
		Hashtable<String, Object> propManagedService = new Hashtable<String, Object>();
		propManagedService.put(Constants.SERVICE_PID, this.bundleContext.getBundle().getSymbolicName());
		this.bundleContext.registerService(ManagedService.class.getName(), this, propManagedService);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void updated(Dictionary properties) throws ConfigurationException
	{
		// get the bundle configuration parameters
		if (properties != null)
		{
			// try to get the baseline polling time
			String pollingTimeAsString = (String) properties.get("pollingTimeMillis");
			
			// trim leading and trailing spaces
			pollingTimeAsString = pollingTimeAsString.trim();
			
			// check not null
			if (pollingTimeAsString != null)
			{
				// parse the string
				this.pollingTimeMillis = Integer.valueOf(pollingTimeAsString);
			}
		}
		
		// in any case, as the polling time has a default, init the poller
		// thread and start it
		this.poller = new EchelonIlon100Poller(this);
		
		// start the poller
		poller.start();
		
		// log the driver start
		this.logger.log(LogService.LOG_INFO, EchelonIlon100DriverImpl.logId
				+ "Started the driver poller thread, ready to handle datapoint sampling, reading and setting...");
		
		// register the service
		// register the driver service if not already registered
		if (this.regServiceEchelonIlon100 == null)
			this.regServiceEchelonIlon100 = this.bundleContext.registerService(EchelonIlon100Network.class.getName(),
					this, null);
	}
	
	/**
	 * @return the datapoint2Driver
	 */
	public Map<DataPointInfo, EchelonIlon100DriverInstance> getDatapoint2Driver()
	{
		return datapoint2Driver;
	}
	
	/**
	 * @return the driver2Datapoint
	 */
	public Map<EchelonIlon100DriverInstance, Map<String, DataPointInfo>> getDriver2Datapoint()
	{
		return driver2Datapoint;
	}
	
	/**
	 * Returns the set of connected end point addresses.
	 * 
	 * @return a {@link Set}<{@link String}> of end point addresses to which the
	 *         driver is currently connected
	 */
	public Set<String> getConnectedEndpoints()
	{
		return this.endpoint2Datapoint.keySet();
	}
	
	/**
	 * Returns the set of datapoints connected to the given endpoint address.
	 * 
	 * @param endpoint
	 *            The endpoint address.
	 * @return The connected datapoints as a {@link Set} of
	 *         {@link DataPointInfo} instances.
	 */
	public Map<String, DataPointInfo> getEndpointDatapoints(String endpoint)
	{
		return this.endpoint2Datapoint.get(endpoint);
	}
	
	/**
	 * Returns the polling time, in milliseconds, currently set for this driver.
	 * 
	 * @return The polling time in milliseconds.
	 */
	public int getPollingTimeMillis()
	{
		return this.pollingTimeMillis;
	}
	
	/**
	 * @return the logger
	 */
	protected LogHelper getLogger()
	{
		return logger;
	}
	
	/******************************************************************************
	 * 
	 * 
	 * Echelon Ilon 100 Network implementation
	 * 
	 * 
	 *******************************************************************************/
	
	@Override
	public void read(DataPointInfo dataPointInfo)
	{
		this.readDP(dataPointInfo);
		
		// get the driver currently handling the datapoint
		EchelonIlon100DriverInstance driver = this.datapoint2Driver.get(dataPointInfo);
		
		// dispatch the new datapoint value
		driver.newMessageFromHouse(dataPointInfo);
	}
	
	public void readDP(DataPointInfo dataPointInfo)
	{
		// Create a proxy towards the DataPoint endpoint
		ILON100PortTypeProxy proxy = new ILON100PortTypeProxy(dataPointInfo.getiLonServerEndpoint());
		
		// instantiate the member object
		Item_Coll itemColl = new Item_Coll();
		// the item to query
		Item itemToQuery = new Item();
		// set the DP name
		itemToQuery.setUCPTname(dataPointInfo.getiLonId());
		// add the item to the item query
		itemColl.setItem(new Item[] { itemToQuery });
		
		// read the datapoint value
		try
		{
			Item_DataColl dataPointValueC = proxy.read(itemColl);
			
			// get the only value
			if (dataPointValueC != null)
			{
				// get the item data of this datapoint
				Dp_Data itemData = (Dp_Data) dataPointValueC.getItem(0);
				
				// fill the return value
				try
				{
					dataPointInfo.setValue(Double.parseDouble(itemData.getUCPTvalue(0).get_value()));
				}
				catch (NumberFormatException e)
				{
					// log the error
					this.logger.log(LogService.LOG_WARNING, EchelonIlon100DriverImpl.logId
							+ "Attempt to read currently not supported data, skipping...");
				}
				
			}
			else
				// log the error
				this.logger.log(LogService.LOG_WARNING, EchelonIlon100DriverImpl.logId + "No data available for: "
						+ dataPointInfo.getiLonId());
		}
		catch (RemoteException e)
		{
			// log the error
			this.logger.log(LogService.LOG_ERROR, EchelonIlon100DriverImpl.logId
					+ "Unable to retrieve data for the given datapoints:" + e);
		}
	}
	
	@Override
	public void readAll(Map<String, DataPointInfo> allDatapoints)
	{
		// Prepare a variable to store the end point associated to the given
		// group, if more than one end point is found, only the first occurrence
		// will be considered.
		String endpoint = null;
		
		// instantiate the member object
		Item_Coll itemColl = new Item_Coll();
		
		// the array of data points to get value from
		Item[] itemsToQuery = new Item[allDatapoints.size()];
		
		int i = 0;
		for (String dpIlonId : allDatapoints.keySet())
		{
			// the item to query
			Item itemToQuery = new Item();
			
			// set the DP name
			itemToQuery.setUCPTname(dpIlonId);
			
			// add the item to query
			itemsToQuery[i] = itemToQuery;
			
			// add the first endpoint
			if (i == 0)
				endpoint = allDatapoints.get(dpIlonId).getiLonServerEndpoint();
			
			i++;
		}
		
		itemColl.setItem(itemsToQuery);
		
		if (endpoint != null)
		{
			// Create a proxy towards the DataPoint endpoint
			ILON100PortTypeProxy proxy = new ILON100PortTypeProxy(endpoint);
			
			// read the datapoint value
			try
			{
				
				Item_DataColl dataPointValueC = proxy.read(itemColl);
				
				// get the datapoint values
				if (dataPointValueC != null)
				{
					// convert the data values collection into a more "usable"
					// array
					Item_Data[] dataPointValues = dataPointValueC.getItem();
					
					// iterate over the available data point values, consider
					// the
					// one-valued only variables
					// TODO: improve the data point "extraction" to address also
					// multiple-valued data points
					for (i = 0; i < dataPointValues.length; i++)
					{
						// get the item data of this datapoint
						Dp_Data itemData = (Dp_Data) dataPointValueC.getItem(i);
						
						try
						{
							// extract the corresponding data point from the
							// given list
							DataPointInfo dp = allDatapoints.get(itemData.getUCPTname());
							if (dp != null)
							{
								// try to convert the value to a double
								double value = Double.parseDouble(itemData.getUCPTvalue(0).get_value());
								
								// update the datapoint value
								dp.setValue(value);
								
								// log the update
								logger.log(LogService.LOG_DEBUG, EchelonIlon100DriverImpl.logId + "Updated datapoint "
										+ dp);
								
								// get the specific driver associated to the
								// current datapoint
								EchelonIlon100DriverInstance drv = this.datapoint2Driver.get(dp);
								
								// notify the new datapoint value
								drv.newMessageFromHouse(dp);
								
							}
							else
							{
								logger.log(LogService.LOG_WARNING, EchelonIlon100DriverImpl.logId
										+ "Found unmatched data from ILon server...");
							}
						}
						catch (NumberFormatException e)
						{
							// log the error
							this.logger.log(LogService.LOG_WARNING, EchelonIlon100DriverImpl.logId
									+ "Attempt to read currently not supported data, skipping...");
						}
					}
					
				}
				else
					// log the error
					this.logger.log(LogService.LOG_WARNING, EchelonIlon100DriverImpl.logId
							+ "No data available for the given datapoints ");
			}
			catch (RemoteException e)
			{
				// log the error
				this.logger.log(LogService.LOG_ERROR, EchelonIlon100DriverImpl.logId
						+ "Unable to retrieve data for the given datapoints:" + e);
			}
		}
		else
		{
			this.logger.log(LogService.LOG_WARNING, "No endpoint given for readAll, gracefully failing...");
		}
	}
	
	@Override
	// TODO: Must be tested!!!
	public void write(DataPointInfo dataPointInfo)
	{
		// Create a proxy towards the DataPoint endpoint
		ILON100PortTypeProxy proxy = new ILON100PortTypeProxy(dataPointInfo.getiLonServerEndpoint());
		
		// instantiate the member object
		Item_DataColl itemColl = new Item_DataColl();
		// the item to query
		Dp_Data itemToWrite = new Dp_Data();
		// set the DP name
		itemToWrite.setUCPTname(dataPointInfo.getiLonId());
		
		itemToWrite.setUCPTvalue(new E_LonString[] { new E_LonString("" + dataPointInfo.getValue()) });
		// add the item to the item query
		itemColl.setItem(new Item_Data[] { itemToWrite });
		
		// read the datapoint value
		try
		{
			proxy.write(itemColl);
		}
		catch (RemoteException e)
		{
			// log the error
			this.logger.log(LogService.LOG_ERROR, EchelonIlon100DriverImpl.logId
					+ "Unable to write data for the given datapoints:" + e);
		}
		
	}
	
	@Override
	public Set<DataPointInfo> discoverDatapoints(String endpointAddress)
	{
		
		// init the data point set
		HashSet<DataPointInfo> dataPoints = new HashSet<DataPointInfo>();
		
		// if xSelect is null, fall back to the default getAll query and log a
		// warning
		String xSelect = EchelonIlon100DriverImpl.XSELECT_ALL;
		
		// Create a proxy towards the DataPoint endpoint
		ILON100PortTypeProxy proxy = new ILON100PortTypeProxy(endpointAddress);
		
		// prepare the list query to the iLon100 server
		E_xSelect dataPointSelectionMsg = new E_xSelect();
		dataPointSelectionMsg.setXSelect(xSelect);
		
		// ------------- List the data points
		try
		{
			// list all the available data points (needed for requesting data
			// about datapoints)
			Item_Coll allDataPointsCollection = proxy.list(dataPointSelectionMsg);
			
			// read data on the available data points
			Item_DataColl allDataPointValuesCollection = proxy.read(allDataPointsCollection);
			
			// convert the data values collection into a more "usable" array
			Item_Data[] allDatapointValues = allDataPointValuesCollection.getItem();
			
			// iterate over the available data point values, consider the
			// one-valued only variables
			// TODO: improve the data point "extraction" to address also
			// multiple-valued data points
			for (int i = 0; i < allDatapointValues.length; i++)
			{
				// get the data point data
				Dp_Data itemData = (Dp_Data) allDatapointValues[i];
				
				// get the datapoint data value
				E_LonString[] itemDataValues = itemData.getUCPTvalue();
				
				// check if monoValued otherwise log a warning
				if (itemDataValues.length > 1)
				{
					// log the warning and skip
					this.logger.log(LogService.LOG_WARNING, EchelonIlon100DriverImpl.logId
							+ "Detected un-supported multiple-valued datapoint, skipping...");
				}
				else
				{
					// valid data point, create a corresponding DataPoint object
					DataPointInfo dp = new DataPointInfo(itemData.getUCPTname(), itemData.getUCPTaliasName(),
							itemDataValues[0].getUnit(), endpointAddress);
					
					// add the new data point
					dataPoints.add(dp);
					
					// log the addition
					this.logger.log(LogService.LOG_INFO, EchelonIlon100DriverImpl.logId + "Found new datapoint " + dp);
				}
				
			}
		}
		catch (RemoteException e)
		{
			// log the error
			this.logger.log(LogService.LOG_ERROR, EchelonIlon100DriverImpl.logId
					+ "Unable to retrieve data for the given datapoints:" + e);
		}
		
		return dataPoints;
	}
	
	@Override
	public void addDriver(DataPointInfo datapoint, EchelonIlon100DriverInstance driver)
	{
		// adds a given datapoint-driver association
		this.datapoint2Driver.put(datapoint, driver);
		
		// fills the reverse map
		Map<String, DataPointInfo> driverDatapoints = this.driver2Datapoint.get(driver);
		if (driverDatapoints == null)
		{
			// create the new set of data points associated to the given driver
			driverDatapoints = new HashMap<String, DataPointInfo>();
			this.driver2Datapoint.put(driver, driverDatapoints);
			
		}
		driverDatapoints.put(datapoint.getiLonId(), datapoint);
		
		// fill the endpoint to datapoint map
		Map<String, DataPointInfo> endpointDatapoints = this.endpoint2Datapoint.get(datapoint.getiLonServerEndpoint());
		if (endpointDatapoints == null)
		{
			// create the new entry
			endpointDatapoints = new HashMap<String, DataPointInfo>();
			this.endpoint2Datapoint.put(datapoint.getiLonServerEndpoint(), endpointDatapoints);
		}
		
		// add the datapoint entry
		endpointDatapoints.put(datapoint.getiLonId(), datapoint);
		
	}
	
	@Override
	public void removeDriver(DataPointInfo datapoint)
	{
		// removes a given datapoint-driver association
		EchelonIlon100DriverInstance drv = this.datapoint2Driver.remove(datapoint);
		
		if (drv != null)
		{
			// removes the datapoint from the corresponding set
			Map<String, DataPointInfo> driverDatapoints = this.driver2Datapoint.get(drv);
			driverDatapoints.remove(datapoint.getiLonId());
			
			// if after removal the set is empty, removes the reverse map entry
			if (driverDatapoints.isEmpty())
				this.driver2Datapoint.remove(drv);
		}
		
		// remove the datapoint entry from the endpoint to datapoint map
		Map<String, DataPointInfo> endpointDatapoints = this.endpoint2Datapoint.get(datapoint.getiLonServerEndpoint());
		if (endpointDatapoints != null)
		{
			// create the new entry
			endpointDatapoints.remove(datapoint.getiLonId());
			
			// if it is the last entry in the set remove the map entry
			if (endpointDatapoints.isEmpty())
				this.endpoint2Datapoint.remove(datapoint.getiLonServerEndpoint());
		}
		
	}
	
	@Override
	public void removeDriver(EchelonIlon100DriverInstance driver)
	{
		// removes a given driver-datapoint association
		Map<String, DataPointInfo> driverDatapoints = this.driver2Datapoint.remove(driver);
		
		// remove the datpoint-to-driver and the endpoint-to-datapoint
		// associations
		if (driverDatapoints != null)
		{
			for (DataPointInfo dp : driverDatapoints.values())
			{
				// remove the datapoint-to-driver associations
				this.datapoint2Driver.remove(dp);
				
				// remove the datapoints from the endpoint/datapoint association
				Map<String, DataPointInfo> endpointDatapoints = this.endpoint2Datapoint.get(dp.getiLonServerEndpoint());
				if (endpointDatapoints != null)
				{
					// create the new entry
					endpointDatapoints.remove(dp.getiLonId());
					
					// if it is the last entry in the set remove the map entry
					if (endpointDatapoints.isEmpty())
						this.endpoint2Datapoint.remove(dp.getiLonServerEndpoint());
				}
			}
		}
	}
	
}
