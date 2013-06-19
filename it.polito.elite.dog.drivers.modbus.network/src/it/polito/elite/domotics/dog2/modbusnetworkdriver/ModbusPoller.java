/*
 * Dog 2.0 - Modbus Network Driver
 * 
 * Copyright [2012] 
 * [Dario Bonino (dario.bonino@polito.it), Politecnico di Torino] 
 * [Muhammad Sanaullah (muhammad.sanaullah@polito.it), Politecnico di Torino] 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package it.polito.elite.domotics.dog2.modbusnetworkdriver;

import it.polito.elite.domotics.dog2.modbusnetworkdriver.info.ModbusRegisterInfo;

import java.net.InetAddress;
import java.util.Set;

import org.osgi.service.log.LogService;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>, Politecnico di Torino 
 * @author <a href="mailto:muhammad.sanaullah@polito.it">Muhammad Sanaullah</a>, Politecnico di Torino 
 *
 * @since Feb 24, 2012
 */
public class ModbusPoller extends Thread
{
	// reference to the EchelonIlon100DriverImpl currently using this poller
	private ModbusDriverImpl driver;
	
	// the runnable flag
	private boolean runnable = true;
	
	// the poller logger
	private LogService logger;
	
	// the log identifier, unique for the class
	public static String logId = "[ModbusPoller]: ";
	
	public ModbusPoller(ModbusDriverImpl modbusDriverImpl)
	{		
		// store a reference to the poller driver
		this.driver = modbusDriverImpl;
		
		// init the logger
		this.logger = this.driver.getLogger();
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		// run until the thread is runnable
		while (this.runnable)
		{
			// log
			this.logger.log(LogService.LOG_DEBUG, ModbusPoller.logId + "Starting a new polling cycle...");
			// get the list of endpoints to poll
			Set<InetAddress> gatewayAddresses = this.driver.getConnectedGateways();
			
			//check not null
			if (gatewayAddresses != null)
			{
				// for every gateway address get the set of registers and trigger a
				// read...
				for (InetAddress gwAddress : gatewayAddresses)
				{
					// get the set of datapoints and read
					Set<ModbusRegisterInfo> registers= this.driver.getGatewayRegisters(gwAddress);
					
					// check not null
					if (registers != null)
					{
						this.driver.readAll(registers);
						
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
				this.logger.log(LogService.LOG_WARNING, ModbusPoller.logId + "Interrupted exception: " + e);
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
