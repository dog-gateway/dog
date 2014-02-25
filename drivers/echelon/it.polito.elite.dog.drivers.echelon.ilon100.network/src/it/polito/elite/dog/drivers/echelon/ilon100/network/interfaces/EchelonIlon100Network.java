/*
 * Dog - Network Driver
 * 
 * Copyright (c) 2012-2014 Dario Bonino
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
package it.polito.elite.dog.drivers.echelon.ilon100.network.interfaces;

import java.util.Map;
import java.util.Set;

import it.polito.elite.dog.drivers.echelon.ilon100.network.EchelonIlon100DriverInstance;
import it.polito.elite.dog.drivers.echelon.ilon100.network.info.DataPointInfo;

/**
 * A public interface defining the standard functionalities offered by an
 * EchelonIlon100NetworkDriver
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public interface EchelonIlon100Network
{
	
	/**
	 * Read the value of the data point identified by the given
	 * {@link DataPointInfo} instance
	 * 
	 * @param dataPointInfo
	 *            The data point to read.
	 */
	public void read(DataPointInfo dataPointInfo);
	
	/**
	 * Reads the values of all the data points belonging to the given list.
	 * Implements an optimized query mechanism for data points located on the
	 * same iLon server.
	 * 
	 * @param allDatapoints
	 */
	public void readAll(Map<String,DataPointInfo> allDatapoints);
	
	/**
	 *Read the value of the data point identified by the given
	 * {@link DataPointInfo} instance
	 * 
	 * @param dataPointInfo
	 *            The data point to read.
	 * @return the read data point value
	 */
	public void readDP(DataPointInfo dataPointInfo);
	
	/**
	 * Writes on the iLon 100 Server the value contained in the given
	 * {@link DataPointInfo} instance
	 * 
	 * @param datapointInfo
	 *            The data point to write.
	 */
	public void write(DataPointInfo datapointInfo);
	
	/**
	 * Discovers th endpoints currently available on the iLon server responding
	 * at the given endpoint address.
	 * 
	 * @param endpointAddress
	 *            The iLon 100 endpoint address
	 * @return The discovered endpoints
	 */
	public Set<DataPointInfo> discoverDatapoints(String endpointAddress);
	
	/**
	 * Associates the given DataPointInfo to the given Echelon device driver
	 * 
	 * @param datapoint
	 *            The DataPointInfo object.
	 * @param driver
	 *            The EchelonIlon100Driver to which the DataPointInfo is
	 *            associated (managed by)
	 */
	public void addDriver(DataPointInfo datapoint, EchelonIlon100DriverInstance driver);
	
	/**
	 * Removes the driver association for the given datapoint. To be called when a data point disconnects
	 * 
	 * @param datapoint
	 */
	public void removeDriver(DataPointInfo datapoint);
	
	/**
	 * Removes the driver association for the given datapoint. To be called when a specific driver disconnects
	 * 
	 * @param datapoint
	 */

	public void removeDriver(EchelonIlon100DriverInstance driver);
}
