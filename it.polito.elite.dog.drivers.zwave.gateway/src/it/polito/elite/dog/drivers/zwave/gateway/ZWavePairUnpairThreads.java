/*
 * Dog  - Z-Wave
 * 
 * Copyright [2013] 
 * [Davide Aimone (aimone.dav@gmail.com)]
 * [Dario Bonino (dario.bonino@polito.it), Politecnico di Torino] 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package it.polito.elite.dog.drivers.zwave.gateway;

import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;

import org.osgi.service.log.LogService;

public class ZWavePairUnpairThreads extends Thread
{
	// reference to the ZWaveGatewayDriver currently using this thread
	private ZWaveNetwork network;

	// the logger
	private LogService logger;

	// bAssociate = true, starts pair process. bAssociate = false, start unpair process
	private boolean bAssociationMode;
	
	//duration of pair/unpair process
	private long duration;

	// the log identifier, unique for the class
	public static final String LOG_ID = "[ZWavePairingThreads]: ";

	/**
	 * bAssociate = true, starts pair process. bAssociate = false, start unpair process
	 * @param network
	 * @param logger
	 * @param bAssociate
	 * @param duration how long the thread lasts in milliseconds
	 */
	public ZWavePairUnpairThreads(ZWaveNetwork network, LogService logger, boolean bAssociate, long duration)
	{		
		// store a reference to the network driver
		this.network = network;

		// init the logger
		this.logger = logger;

		//opertating mode
		this.bAssociationMode = bAssociate;
		
		//duration of pair/unpair process
		this.duration = duration;
	}


	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		// log
		logger.log(LogService.LOG_DEBUG, LOG_ID + "Starting include process...");

		//if bAssociationMode is true starts inclusion mode, else starts exclusion
		if(bAssociationMode)
		{
			//start inclusion mode
			network.controllerWrite(ZWaveGatewayDriver.CMD_INCLUDE, "1");
		}
		else
		{
			//start exclusion mode
			network.controllerWrite(ZWaveGatewayDriver.CMD_EXCLUDE, "1");
		}

		// put thread to sleep than stop the include process
		try
		{
			Thread.sleep(duration);
		}
		catch (InterruptedException e)
		{
			// log the error
			logger.log(LogService.LOG_WARNING, LOG_ID + "Interrupted exception: " + e);
		}

		//return to normal state
		if(bAssociationMode)
		{
			//stop inclusion mode
			network.controllerWrite(ZWaveGatewayDriver.CMD_INCLUDE, "0");
		}
		else
		{
			//stop exclusion mode
			network.controllerWrite(ZWaveGatewayDriver.CMD_EXCLUDE, "1");
		}

		// log
		logger.log(LogService.LOG_DEBUG, LOG_ID + "Stopping include process...");
	}
}
