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

import java.util.Map;
import java.util.Set;

import org.osgi.service.log.LogService;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class EchelonIlon100Poller extends Thread
{
	
	// reference to the EchelonIlon100DriverImpl currently using this poller
	private EchelonIlon100DriverImpl driver;
	
	// the runnable flag
	private boolean runnable = true;
	
	// the poller logger
	private LogHelper logger;
	
	// the log identifier, unique for the class
	public static String logId = "[EchelonIlon100Poller]: ";
	
	public EchelonIlon100Poller(EchelonIlon100DriverImpl echelonIlon100DriverImpl)
	{		
		// store a reference to the poller driver
		this.driver = echelonIlon100DriverImpl;
		
		// init the logger
		this.logger = this.driver.getLogger();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		// run until the thread is runnable
		while (this.runnable)
		{
			// log
			this.logger.log(LogService.LOG_DEBUG, EchelonIlon100Poller.logId + "Starting a new polling cycle...");
			// get the list of endpoints to poll
			Set<String> endpoints = this.driver.getConnectedEndpoints();
			
			//check not null
			if (endpoints != null)
			{
				// for every endpoint get the set of dtapoints and trigger a
				// read...
				for (String endpoint : endpoints)
				{
					// get the set of datapoints and read
					Map<String,DataPointInfo> dps = this.driver.getEndpointDatapoints(endpoint);
					
					// check not null
					if (dps != null)
					{
						this.driver.readAll(dps);
						
						// since this can be a time intensive inner task, yield
						// to other processes
						Thread.yield();
					}
					
				}
			}
			// ok now the polling cycle has ended and the poller can sleep for
			// the given polling time
			try
			{
				Thread.sleep(this.driver.getPollingTimeMillis());
			}
			catch (InterruptedException e)
			{
				// log the error
				this.logger.log(LogService.LOG_WARNING, EchelonIlon100Poller.logId + "Interrupted exception: " + e);
			}
			
		}
		
		// auto-reset the state at runnable...
		this.runnable = true;
	}
	
	/**
	 * Sets the thread state at runnable (true) or not runnable(false)
	 * 
	 * @param runnable
	 */
	public void setRunnable(boolean runnable)
	{
		this.runnable = runnable;
	}
	
}
