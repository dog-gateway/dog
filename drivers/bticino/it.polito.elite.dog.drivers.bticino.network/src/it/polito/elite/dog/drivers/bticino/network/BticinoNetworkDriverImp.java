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

import it.polito.elite.dog.core.library.util.LogHelper;
import it.polito.elite.dog.drivers.bticino.interfaces.BTicinoNetworkDriver;
import it.polito.elite.dog.drivers.bticino.interfaces.BTicinoReader;
import it.polito.elite.dog.drivers.bticino.interfaces.BTicinoSpecificDriver;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.log.LogService;

import com.bticino.core.OpenWebNet;

/***
 * This class implements a basic network driver to access to MyOpen network
 * 
 * @author <a href="mailto:castellina.emi@gmail.com">Emiliano Castellina</a>
 *         (original version)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class BticinoNetworkDriverImp implements BTicinoReader, BTicinoNetworkDriver, ManagedService
{
	
	private static final String PORT = "port";
	private static final String HOUSEIP = "houseip";
	private static final String SLEEPTIME = "sleeptime";
	private static final String TIMEOUT = "timeout";
	
	// OSGi framework context
	private BundleContext context;
	
	// Thread for testing connection to the BTicino Network
	private Thread tConnectionTesting;
	
	// logger
	private LogHelper logger;
	
	private String houseIp = "";
	private int housePort;
	long sleepTime = 10000;
	private int timeout = 5000;
	
	private BTcinoConnection connection;
	private Hashtable<String, Object> serviceProps = new Hashtable<String, Object>();
	
	// table that store the information about the binding of devices (addresses)
	// and Specific Network Drivers
	private Hashtable<String, Set<BTicinoSpecificDriver>> routingTable;
	
	private ServiceRegistration<?> servReg;
	
	/**
	 * @return the context
	 */
	public BundleContext getContext()
	{
		return context;
	}
	
	/**
	 * @return the logger
	 */
	public LogHelper getLogger()
	{
		return logger;
	}
	
	/**
	 * @return the houseIp
	 */
	public String getHouseIp()
	{
		return houseIp;
	}
	
	/**
	 * @return the housePort
	 */
	public int getHousePort()
	{
		return housePort;
	}
	
	/**
	 * @return the sleepTime
	 */
	public long getSleepTime()
	{
		return sleepTime;
	}
	
	/**
	 * @return the timeout
	 */
	public int getTimeout()
	{
		return timeout;
	}
	
	/**
	 * @return the routingTable
	 */
	public Hashtable<String, Set<BTicinoSpecificDriver>> getRoutingTable()
	{
		return routingTable;
	}
	
	/**
	 * Class constructor
	 * 
	 * @param context
	 *            OSGi framework
	 */
	public BticinoNetworkDriverImp(BundleContext context)
	{
		this.context = context;
		this.logger = new LogHelper(context);
		
		this.routingTable = new Hashtable<String, Set<BTicinoSpecificDriver>>();
		this.connection = new BTcinoConnection(this);
		this.tConnectionTesting = new Thread(this.connection);
		this.registerService();
	}
	
	private void registerService()
	{
		
		serviceProps.put(Constants.SERVICE_PID, this.context.getBundle().getSymbolicName());
		this.context.registerService(ManagedService.class.getName(), this, serviceProps);
		this.servReg = this.context.registerService(BTicinoNetworkDriver.class.getName(), this, serviceProps);
	}
	
	void updateService(boolean connected)
	{
		
		serviceProps.put(BTicinoNetworkDriver.CONNECTED, connected);
		this.servReg.setProperties(serviceProps);
	}
	
	public void unregisterServices(boolean stillRunning)
	{
		
		this.updateService(false);
		if (this.connection.isRunning())
		{
			this.connection.stop();
		}
		
	}
	
	@Override
	public void bind(BTicinoSpecificDriver driver, String deviceAdress)
	{
		Set<BTicinoSpecificDriver> drivers = this.routingTable.get(deviceAdress);
		
		if (drivers == null)
		{
			drivers = new HashSet<BTicinoSpecificDriver>();
			
			synchronized (this.routingTable)
			{
				this.routingTable.put(deviceAdress, drivers);
			}
		}
		drivers.add(driver);
	}
	
	@Override
	public void unbind(BTicinoSpecificDriver driver, String deviceAdress)
	{
		if (this.routingTable.containsKey(deviceAdress))
		{
			this.routingTable.remove(deviceAdress);
		}
		
	}
	
	@Override
	public void sendMyOpenMessage(OpenWebNet message, int priority)
	{
		this.connection.sendMessage(message);
		
	}
	
	@Override
	public void read(OpenWebNet myOpenMessage)
	{
		String device = myOpenMessage.getDove();
		synchronized (this.routingTable)
		{
			if (this.routingTable.containsKey(device))
			{
				for (BTicinoSpecificDriver driver : this.routingTable.get(device))
				{
					try
					{
						driver.recieveLowLevelMessage(myOpenMessage);
					}
					catch (Exception e)
					{
						this.logger.log(LogService.LOG_ERROR, " Error while forwarding BTicino network message", e);
						
					}
				}
			}
		}
		
	}
	
	@Override
	public void networkDisconnected()
	{
		this.unregisterServices(true);
		
	}
	
	@Override
	public void updated(Dictionary<String, ?> properties) throws ConfigurationException
	{
		if (properties != null)
		{
			this.logger.log(LogService.LOG_DEBUG, " Received configuration file " + properties);
			// stop the thread
			
			this.logger.log(LogService.LOG_INFO, "Received new configuration");
			
			String port = (String) properties.get(PORT);
			String houseIp = (String) properties.get(HOUSEIP);
			String sleepTime = (String) properties.get(SLEEPTIME);
			String timeout = (String) properties.get(TIMEOUT);
			// the configuration is changed?
			boolean modified = false;
			if (port != null)
			{
				int portNumber = Integer.parseInt(port);
				if (this.housePort != portNumber)
				{
					modified = true;
				}
				this.housePort = portNumber;
			}
			if (sleepTime != null)
			{
				this.sleepTime = Integer.parseInt(sleepTime);
			}
			if (timeout != null)
			{
				this.timeout = Integer.parseInt(timeout);
			}
			if (houseIp != null)
			{
				if (!this.houseIp.equals(houseIp))
				{
					modified = true;
				}
				this.houseIp = houseIp;
			}
			
			if (modified)
			{
				
				this.connection.stop();
				
				try
				{
					this.connection = new BTcinoConnection(this);
					this.tConnectionTesting = new Thread(this.connection);
					this.tConnectionTesting.start();
					
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Override
	public String getIpAddress()
	{
		
		return this.houseIp;
	}
	
}
