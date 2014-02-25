/*
 * Dog - Network Driver
 * 
 * Copyright (c) 2010-2014 Emiliano Castellina and Luigi De Russis
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
package it.polito.elite.dog.drivers.bticino.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.osgi.service.log.LogService;

import com.bticino.core.CommandsSocket;
import com.bticino.core.OpenWebNet;
import com.bticino.core.SocketMonitor;
import com.bticino.core.StartCommandCycle;

/**
 * 
 * @author <a href="mailto:castellina.emi@gmail.com">Emiliano Castellina</a>
 *         (original version)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class BTcinoConnection implements Runnable
{
	
	BticinoNetworkDriverImp driver;
	SocketMonitor monitorSocket;
	CommandsSocket commandSocket;
	StartCommandCycle commandCycle;
	
	volatile boolean running;
	volatile boolean connected;
	
	public BTcinoConnection(BticinoNetworkDriverImp driver)
	{
		this.driver = driver;
		this.running = false;
		this.connected = false;
	}
	
	public void stop()
	{
		this.running = false;
		if (this.commandCycle != null)
		{
			this.commandCycle.stopCommandCycle();
		}
		if (this.monitorSocket != null)
		{
			monitorSocket.close();
		}
	}
	
	/**
	 * @return the running
	 */
	public boolean isRunning()
	{
		return running;
	}
	
	/**
	 * @param running
	 *            the running to set
	 */
	public void setRunning(boolean running)
	{
		this.running = running;
	}
	
	/**
	 * @return the connected
	 */
	public boolean isConnected()
	{
		return connected;
	}
	
	/**
	 * @param connected
	 *            the connected to set
	 */
	public void setConnected(boolean connected)
	{
		this.connected = connected;
	}
	
	@Override
	public void run()
	{
		this.running = true;
		while (this.running)
		{
			this.monitorSocket = new SocketMonitor(this.driver);
			this.commandSocket = new CommandsSocket(this.driver);
			
			while (this.running && !this.connected)
			{
				this.connected = this.testHouse(false);
				if (!this.connected)
				{
					try
					{
						Thread.sleep(this.driver.sleepTime);
					}
					catch (InterruptedException e)
					{
						this.driver.getLogger().log(LogService.LOG_ERROR, "BTcino Driver exception ", e);
					}
				}
			}
			
			// now it is connected or the bundle is stopping
			if (this.connected)
			{ // is connected
				this.startCommandCylcle();
				this.driver.updateService(true);
			}
			
			while (this.running && this.connected)
			{
				this.connected = this.testHouse(true);
				if (this.connected)
				{
					try
					{
						Thread.sleep(this.driver.sleepTime);
					}
					catch (InterruptedException e)
					{
						this.driver.getLogger().log(LogService.LOG_ERROR, "BTcino Driver exception ", e);
					}
				}
			}
			if (running)
			{
				// network not connected
				this.driver.getLogger().log(LogService.LOG_INFO, "connection BTCINO interrupted");
				this.commandSocket.close();
				this.monitorSocket.close();
				if (this.commandCycle != null)
				{
					this.commandCycle.stopCommandCycle();
				}
				
				this.driver.networkDisconnected();
			}
		}
		
	}
	
	/***
	 * BTcino method to send a message that allows to start a command cycle.
	 */
	private void startCommandCylcle()
	{
		this.commandCycle = new StartCommandCycle("command cycle", "*#13**1##", 15000, commandSocket);
		commandCycle.start();
		
	}
	
	/**
	 * Tests if the house is reachable or not
	 * 
	 * @return <b>true</b> if the house is reachable, <b>false</b> otherwise
	 */
	private boolean testHouse(boolean onlyConnection)
	{
		
		this.driver.getLogger()
				.log(LogService.LOG_INFO, "Testing if " + this.driver.getHouseIp() + " is reachable\n\n");
		
		InetAddress houseAddress = null;
		try
		{
			houseAddress = InetAddress.getByName(this.driver.getHouseIp());
		}
		catch (UnknownHostException e)
		{
			this.driver.getLogger().log(LogService.LOG_ERROR, "Unknown Host:", e);
		}
		try
		{
			if (houseAddress.isReachable(this.driver.getTimeout()))
			{
				if (onlyConnection)
				{
					return true;
				}
				this.driver.getLogger().log(LogService.LOG_INFO, this.driver.getHouseIp() + " REACHABLE!");
				return commandSocket.connect(this.driver.getHouseIp(), this.driver.getHousePort(), 0)
						&& monitorSocket.connect(this.driver.getHouseIp(), this.driver.getHousePort(), 0);
			}
			else
			{
				return false;
			}
		}
		catch (IOException e)
		{
			this.driver.getLogger().log(LogService.LOG_ERROR, e.getMessage(), e);
		}
		return false;
		
	}
	
	public void sendMessage(OpenWebNet message)
	{
		try
		{
			this.commandSocket.invia(message);
		}
		catch (Exception e)
		{
			this.commandSocket.close();
			e.printStackTrace();
		}
		
	}
	
}
