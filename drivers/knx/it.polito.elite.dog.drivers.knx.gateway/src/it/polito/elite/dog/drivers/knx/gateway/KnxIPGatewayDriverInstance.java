/*
 * Dog - Gateway Driver
 * 
 * Copyright (c) 2012-2013 Dario Bonino
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
package it.polito.elite.dog.drivers.knx.gateway;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.DeviceStatus;
import it.polito.elite.dog.core.library.model.devicecategory.KNXNetIPGateway;
import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.drivers.knx.network.KnxIPDriverInstance;
import it.polito.elite.dog.drivers.knx.network.info.KnxIPDeviceInfo;
import it.polito.elite.dog.drivers.knx.network.interfaces.KnxIPNetwork;

import org.osgi.framework.BundleContext;

/**
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 *
 */
public class KnxIPGatewayDriverInstance extends KnxIPDriverInstance implements KNXNetIPGateway
{
	
	// the driver logger
	LogHelper logger;
	
	// the log identifier, unique for the class
	public static String logId = "[KnxIPGatewayDriverInstance]: ";
	
	public KnxIPGatewayDriverInstance(KnxIPNetwork network, ControllableDevice controllableDevice, String gatewayAddress,
			BundleContext context)
	{
		super(network, controllableDevice, gatewayAddress);
		
		// create a logger
		this.logger = new LogHelper(context);
		
		// create a new device state (according to the current DogOnt model, no
		// state is actually associated to a KnxIP gateway)
		this.currentState = new DeviceStatus(device.getDeviceId());
	}

	@Override
	public DeviceStatus getState()
	{
		return this.currentState;
	}

	@Override
	public void notifyStateChanged(State newState)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newMessageFromHouse(String source, String destination, String value)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addToNetworkDriver(KnxIPDeviceInfo devInfo)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void specificConfiguration()
	{
		// TODO Auto-generated method stub
		
	}
	
}
