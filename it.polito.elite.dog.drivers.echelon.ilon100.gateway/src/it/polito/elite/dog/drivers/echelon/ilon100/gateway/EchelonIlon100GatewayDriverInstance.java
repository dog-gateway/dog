/*
 * Dog - Gateway Driver
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
package it.polito.elite.dog.drivers.echelon.ilon100.gateway;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.EchelonIlon100Gateway;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.drivers.echelon.ilon100.network.EchelonIlon100DriverInstance;
import it.polito.elite.dog.drivers.echelon.ilon100.network.info.DataPointInfo;
import it.polito.elite.dog.drivers.echelon.ilon100.network.interfaces.EchelonIlon100Network;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class EchelonIlon100GatewayDriverInstance extends EchelonIlon100DriverInstance implements EchelonIlon100Gateway
{
	// the driver logger
	LogHelper logger;
	
	// the log identifier, unique for the class
	public static String logId = "[EchelonIlon100GatewayDriverInstance]: ";
	
	public EchelonIlon100GatewayDriverInstance(EchelonIlon100Network network, ControllableDevice controllableDevice,
			String endpointAddress, BundleContext context)
	{
		
		super(network, controllableDevice, endpointAddress);
		
		// create a logger
		this.logger = new LogHelper(context);
		
		// create a new device state (according to the current DogOnt model, no
		// state is actually associated to an EchelonIlon100 gateway)
		this.currentState = new DeviceStatus(device.getDeviceId());
		
		// connect this driver instance with the device
		this.device.setDriver(this);
		
		// try datapoint discovery.... just to log available datapoints
		//this.network.discoverDatapoints(this.endpointAddress);
	}
	
	@Override
	public void notifyStateChanged(State newState)
	{
		// intentionally left empty
		
	}
	
	@Override
	public DeviceStatus getState()
	{
		return this.currentState;
	}
	
	/**
	 * Provides the end point address of the EchelonILon100Gateway connected to
	 * this driver.
	 * 
	 * @return
	 */
	public String getEndpointAddress()
	{
		return this.endpointAddress;
	}
	
	@Override
	public void newMessageFromHouse(DataPointInfo dataPointInfo)
	{
		// currently no functionalities are associated to echelon ilon gateways
		// therefore they do not use any datapoint and they do not listen to the
		// house messages...
		
		// just log
		this.logger.log(LogService.LOG_INFO, EchelonIlon100GatewayDriverInstance.logId
				+ "Received new message from house involving the datapoint:\n " + dataPointInfo
				+ "\n No operation is currently supported");
	}
	
	@Override
	protected void specificConfiguration()
	{
		// no specific configuration is needed
		// (at the mmoment)
	}
	
	@Override
	protected void addToNetworkDriver(DataPointInfo dp)
	{
		// at the moment no datapoints are managed by this driver....
	}
	
}
