/*
 * Dog 2.0 - Network Driver
 * 
 * Copyright [2011] 
 * [Dario Bonino (dario.bonino@polito.it), Politecnico di Torino] 
 * [Luigi De Russis (luigi.derussis@polito.it), Politecnico di Torino]  
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package it.polito.elite.dog.drivers.knx.network;

import it.polito.elite.dog.drivers.knx.network.info.KnxIPDeviceInfo;
import it.polito.elite.dog.drivers.knx.network.info.KnxIPInfo;
import it.polito.elite.dog.drivers.knx.network.interfaces.KnxIPNetwork;
import it.polito.elite.domotics.dog2.doglibrary.util.DogLogInstance;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.log.LogService;

import tuwien.auto.calimero.DetachEvent;
import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.datapoint.CommandDP;
import tuwien.auto.calimero.datapoint.Datapoint;
import tuwien.auto.calimero.dptxlator.DPT;
import tuwien.auto.calimero.dptxlator.DPTXlator;
import tuwien.auto.calimero.dptxlator.DPTXlator4ByteFloat;
import tuwien.auto.calimero.dptxlator.DPTXlator4ByteInteger;
import tuwien.auto.calimero.dptxlator.TranslatorTypes;
import tuwien.auto.calimero.dptxlator.TranslatorTypes.MainType;
import tuwien.auto.calimero.exception.KNXException;
import tuwien.auto.calimero.exception.KNXFormatException;
import tuwien.auto.calimero.link.KNXNetworkLinkIP;
import tuwien.auto.calimero.link.medium.TPSettings;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessCommunicatorImpl;
import tuwien.auto.calimero.process.ProcessEvent;
import tuwien.auto.calimero.process.ProcessListener;

/**
 * The network driver for KNX networks using the KNXNet/IP protocol
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * 
 */
public class KnxIpDriverImpl implements KnxIPNetwork, ManagedService, ProcessListener
{
	// the number of connection trials
	private int nConnectionTrials;
	private int trialsDone;
	
	// the time that must occur between two subsequent trials
	private int betweenTrialTimeMillis;
	
	// the time for the watchdog to detect disconnections
	private int watchdogTime;
	// a reference to the watchdog timer
	private Timer watchdog;
	// a reference to the connection trials timer
	private Timer connectionTrialsTimer;
	
	// the dog ip address
	private Set<InetSocketAddress> dogIpAddr;
	
	// the netmask for comparing ip addresses
	private String dogMask;
	
	// the map groupAddress/DPT
	private Map<KnxIPDeviceInfo, DPT> knxDeviceInfo2DPT;
	
	// the map groupAddres/device-specific driver
	private Map<KnxIPDeviceInfo, KnxIPDriver> knxDeviceInfo2Driver;
	
	// the KnxIp gateway to device map
	private Map<InetSocketAddress, Set<KnxIPDeviceInfo>> gateway2Device;
	
	// the KnxIp gateway to process communicator map
	private Map<InetSocketAddress, ProcessCommunicator> gateway2ProcessCommunicator;
	
	// the KnxIp gateway to process communicator map
	private Map<InetSocketAddress, KNXNetworkLinkIP> gateway2IPLink;
	
	// the Ipaddress to IP socket map
	private Map<InetAddress, InetSocketAddress> ip2sock;
	
	// a reference to the bundle context
	private BundleContext bundleContext;
	
	// the service registration handle
	private ServiceRegistration<?> regServiceKnxIp;
	
	// the driver logger
	private LogService logger;
	
	// the log identifier, unique for the class
	public static String logId = "[KnxIpDriverImpl]: ";
	
	public KnxIpDriverImpl()
	{
		// intentionally left empty
	}
	
	public void activate(BundleContext bundleContext)
	{
		// create a logger
		this.logger = new DogLogInstance(bundleContext);
		
		// set the number of done trials to 0
		this.trialsDone = 0;
		
		// store the reference to the bundle context
		this.bundleContext = bundleContext;
		
		// create the groupAddress to DPT map
		this.knxDeviceInfo2DPT = new HashMap<KnxIPDeviceInfo, DPT>();
		
		// create the groupAddress to KnxIPDriver map
		this.knxDeviceInfo2Driver = new HashMap<KnxIPDeviceInfo, KnxIPDriver>();
		
		// initialize the gateway to device map
		this.gateway2Device = new HashMap<InetSocketAddress, Set<KnxIPDeviceInfo>>();
		
		// initialize the gateway to process communicator map
		this.gateway2ProcessCommunicator = new HashMap<InetSocketAddress, ProcessCommunicator>();
		
		// initialize the gateway to iplink map
		this.gateway2IPLink = new HashMap<InetSocketAddress, KNXNetworkLinkIP>();
		
		// initialize the ipaddress - ipsocket map
		this.ip2sock = new HashMap<InetAddress, InetSocketAddress>();
		
		// initialize the set of dog ip addresses
		this.dogIpAddr = new HashSet<InetSocketAddress>();
		
		// add the missing dptxlator
		this.addAdditionalDPTXlators();
		
	}
	
	public void deactivate()
	{
		// unregister the service and closes connections
		this.unRegister();
		
		// empty the inner data structures
		this.dogIpAddr.clear();
		this.gateway2Device.clear();
		this.gateway2IPLink.clear();
		this.gateway2ProcessCommunicator.clear();
		this.ip2sock.clear();
		this.knxDeviceInfo2DPT.clear();
		this.knxDeviceInfo2Driver.clear();
		
		// stop the network watchdog
		if (this.watchdog != null)
			this.watchdog.cancel();
		
		// stop the connection trial timer
		if (this.connectionTrialsTimer != null)
			this.connectionTrialsTimer.cancel();
		
		// set everything at null?
		this.dogIpAddr = null;
		this.betweenTrialTimeMillis = 0;
		this.bundleContext = null;
		this.connectionTrialsTimer = null;
		this.dogIpAddr = null;
		this.dogMask = null;
		this.gateway2Device = null;
		this.gateway2IPLink = null;
		this.gateway2ProcessCommunicator = null;
		this.ip2sock = null;
		this.knxDeviceInfo2DPT = null;
		this.knxDeviceInfo2Driver = null;
		this.logger = null;
		this.nConnectionTrials = 0;
		this.regServiceKnxIp = null;
		this.trialsDone = 0;
		this.watchdog = null;
		this.watchdogTime = 0;
		
	}
	
	/**
	 * Unregisters this driver from the OSGi framework and stops the active
	 * threads (Timers, in this case)
	 */
	public void unRegister()
	{
		if (this.regServiceKnxIp != null)
		{
			this.regServiceKnxIp.unregister();
		}
		if (this.connectionTrialsTimer != null)
		{
			this.connectionTrialsTimer.cancel();
		}
		if (this.watchdog != null)
		{
			this.watchdog.cancel();
		}
		
		// check if any process communicators are open... and close them
		for (ProcessCommunicator pc : this.gateway2ProcessCommunicator.values())
		{
			if (pc != null)
			{
				pc.detach();
			}
		}
		
		// check if any ip link is open... and close them
		for (KNXNetworkLinkIP ipLink : this.gateway2IPLink.values())
		{
			if (ipLink != null && ipLink.isOpen())
			{
				ipLink.close();
			}
		}
	}
	
	/****************** Managed Service ****************/
	@SuppressWarnings("rawtypes")
	@Override
	public void updated(Dictionary properties) throws ConfigurationException
	{
		if (properties != null)
		{
			try
			{
				// get the dog ip
				String dogIp = (String) properties.get("dogIp");
				
				// split over the commas
				String ipAddresses[] = dogIp.split(",");
				
				int dogPort = Integer.parseInt((String) properties.get("dogPort"));
				
				// store all the dog IP addresses
				for (int i = 0; i < ipAddresses.length; i++)
				{
					// check not null
					if ((dogIp != null) && (!dogIp.isEmpty()) && (dogPort > 0) && (dogPort < 65536))
					{
						this.dogIpAddr.add(new InetSocketAddress(ipAddresses[i].trim(), dogPort++));
					}
				}
				
				// get the dog netmask
				this.dogMask = (String) properties.get("dogMask");
				
				// store the time between subsequent connection trials
				this.betweenTrialTimeMillis = Integer.parseInt((String) properties.get("betweenTrialTimeMillis"));
				
				// store the maximum number of connection trials
				this.nConnectionTrials = Integer.parseInt((String) properties.get("numTry"));
				
				// store the watchdog time
				this.watchdogTime = Integer.parseInt((String) properties.get("watchdogTimeSeconds")) * 1000;
				
				// register the driver service if not already registered
				if (this.regServiceKnxIp == null)
					this.regServiceKnxIp = this.bundleContext.registerService(KnxIPNetwork.class.getName(), this, null);
				
			}
			catch (NumberFormatException nfe)
			{
				this.logger.log(LogService.LOG_ERROR, nfe.getMessage());
			}
			catch (Exception e)
			{
				this.logger.log(LogService.LOG_ERROR, e.getMessage());
			}
		}
		
	}
	
	/****************** KnxIpNetwork *******************/
	
	@Override
	public void read(KnxIPDeviceInfo deviceInfo)
	{
		// get the device datapoint, if available
		DPT deviceDPT = this.knxDeviceInfo2DPT.get(deviceInfo);
		// gete the device driver if available
		KnxIPDriver deviceDriver = this.knxDeviceInfo2Driver.get(deviceInfo);
		
		// if both the device DPT and Driver are available, send the read
		// command
		if ((deviceDPT != null) && (deviceDriver != null))
		{
			// read a value from the given device address
			Datapoint dpt;
			try
			{
				// create the command datapoitn needed to send the read command
				dpt = new CommandDP(new GroupAddress(deviceInfo.getGroupAddress()), "");
				// set the datapoint translator
				dpt.setDPT(0, deviceDPT.getID());
				
				// read and send back the read result by calling the new message
				// from house command
				this.read(dpt, deviceDriver, this.ip2sock.get(deviceInfo.getGatewayIPAddress()));
			}
			catch (KNXFormatException e)
			{
				this.logger
						.log(LogService.LOG_WARNING,
								KnxIpDriverImpl.logId
										+ "Unable to correctly interpret the given device address, please check if the given group address ["
										+ deviceInfo + "] has the right group address form\n" + "Nested exception:" + e);
			}
			
		}
		
	}
	
	@Override
	public void write(KnxIPDeviceInfo deviceInfo, String commandValue)
	{
		// get the device datapoint, if available
		DPT deviceDPT = this.knxDeviceInfo2DPT.get(deviceInfo);
		// gete the device driver if available
		KnxIPDriver deviceDriver = this.knxDeviceInfo2Driver.get(deviceInfo);
		
		// if both the device DPT and Driver are available, send the read
		// command
		if ((deviceDPT != null) && (deviceDriver != null))
		{
			// read a value from the given device address
			Datapoint dpt;
			try
			{
				// create the command datapoint needed to send the read command
				dpt = new CommandDP(new GroupAddress(deviceInfo.getGroupAddress()), "");
				// set the datapoint translator
				dpt.setDPT(0, deviceDPT.getID());
				
				// send the command to the given group address (and do not wait
				// for response...)
				this.write(dpt, commandValue, this.ip2sock.get(deviceInfo.getGatewayIPAddress()));
			}
			catch (KNXFormatException e)
			{
				this.logger
						.log(LogService.LOG_WARNING,
								KnxIpDriverImpl.logId
										+ "Unable to correctly interpret the given device address, please check if the given group address ["
										+ deviceInfo + "] has the right group address form\n" + "Nested exception:" + e);
			}
			
		}
		
	}
	
	@Override
	public void addDriver(KnxIPDeviceInfo device, int mainNumber, DPT deviceDPT, KnxIPDriver driver)
	{
		// store the associations group address / DPT
		this.knxDeviceInfo2DPT.put(device, deviceDPT);
		
		// store the associations group address / driver
		this.knxDeviceInfo2Driver.put(device, driver);
		
		// get the gateway address
		InetSocketAddress gwAddress = new InetSocketAddress(device.getGatewayIPAddress(), KnxIPInfo.DEFAULT_PORT);
		
		// if the gateway is not available, open a connection to it
		if (!this.gateway2Device.containsKey(gwAddress))
		{
			this.openKnxIPTunnel(gwAddress);
			this.gateway2Device.put(gwAddress, new HashSet<KnxIPDeviceInfo>());
			this.ip2sock.put(gwAddress.getAddress(), gwAddress);
		}
		
		this.gateway2Device.get(gwAddress).add(device);
		
	}
	
	@Override
	public void removeDriver(KnxIPDeviceInfo device)
	{
		// removes the given device address from the entries of the groupAddress
		// to DPT and to Driver tables
		this.knxDeviceInfo2DPT.remove(device);
		this.knxDeviceInfo2Driver.remove(device);
	}
	
	/************************* KnxIp Communication Stuff ***********************/
	private void openKnxIPTunnel(final InetSocketAddress gwIpAddress)
	{
		// create a tp settings object for setting up the TP throughput
		TPSettings settings = new TPSettings(true);
		
		// open an ipLink towards the gateway
		try
		{
			// find the right ip, if any available
			for (InetSocketAddress currentDogIP : this.dogIpAddr)
			{
				if (this.isSameNetwork(currentDogIP.getAddress(), gwIpAddress.getAddress(), this.dogMask))
				{
					
					KNXNetworkLinkIP ipLink = new KNXNetworkLinkIP(KNXNetworkLinkIP.TUNNEL, currentDogIP, gwIpAddress,
							false, settings);
					
					// create an ip link towards the given gateway
					this.gateway2IPLink.put(gwIpAddress, ipLink);
					
					// open the process communicator
					ProcessCommunicator pc = new ProcessCommunicatorImpl(ipLink);
					
					// register this instance as listener for network
					// notifications
					pc.addProcessListener(this);
					
					// store the pointer to the process communicator
					this.gateway2ProcessCommunicator.put(gwIpAddress, pc);
					
					// log the successful connection
					this.logger.log(LogService.LOG_INFO, KnxIpDriverImpl.logId
							+ "Successfully connected to the KNX gateway...");
					
					this.startWatchdog(gwIpAddress);
					break;
				}
			}
			
		}
		catch (KNXException e)
		{
			// TODO: remove the fixed number of trial definition in the .config
			// file and delegate management of re-connection policies to the Dog
			// error management bundle
			if ((this.trialsDone < this.nConnectionTrials) || (this.nConnectionTrials == 0))
			{
				// log a warning
				this.logger.log(LogService.LOG_WARNING, KnxIpDriverImpl.logId
						+ "Unable to connect to the given KNX gateway, retrying in " + this.betweenTrialTimeMillis
						+ " ms");
				// schedule a new timer to re-call the open function after the
				// given trial timeout...
				connectionTrialsTimer = new Timer();
				connectionTrialsTimer.schedule(new TimerTask() {
					
					@Override
					public void run()
					{
						openKnxIPTunnel(gwIpAddress);
					}
				}, this.betweenTrialTimeMillis);
				
				// avoid incrementing the number of trials if the
				// nConnectionTrials is equal to 0 (i.e. infinite re-trial)
				if (this.nConnectionTrials != 0)
					this.trialsDone++;
			}
			else
			{
				// log a fatal error
				this.logger.log(LogService.LOG_ERROR, KnxIpDriverImpl.logId
						+ "Unable to connect to the given KNX gateway");
			}
		}
		
	}
	
	private void startWatchdog(final InetSocketAddress gwIpAddress)
	{
		if (this.watchdogTime > 0)
		{
			// start the watchdog timer
			this.watchdog = new Timer();
			this.watchdog.schedule(new TimerTask() {
				
				@Override
				public void run()
				{
					// log the connection end
					logger.log(LogService.LOG_WARNING, KnxIpDriverImpl.logId
							+ "Detected network loss, trying to reconnect...");
					// close the open connections
					gateway2IPLink.get(gwIpAddress).close();
					
					// this detach operation leads to a new connection
					// opening...
					gateway2ProcessCommunicator.get(gwIpAddress).detach();
					
				}
			}, this.watchdogTime);
		}
		
	}
	
	/**
	 * Adds the additional {@link DPTXlator}s needed for the driver to work
	 */
	private void addAdditionalDPTXlators()
	{
		// get the initial state of the device
		Runnable worker = new Runnable() {
			public void run()
			{
				
				@SuppressWarnings("unchecked")
				Map<Integer, MainType> allMainTypes = TranslatorTypes.getAllMainTypes();
				allMainTypes.put(new Integer(14), new TranslatorTypes.MainType(14, DPTXlator4ByteFloat.class,
						"4-byte float DPTXlator"));
				allMainTypes.put(new Integer(13), new TranslatorTypes.MainType(13, DPTXlator4ByteInteger.class,
						"4-byte int DPTXlator"));
				
				logger.log(LogService.LOG_DEBUG, KnxIpDriverImpl.logId + "Loaded additional DPTXlators...");
				
			}
		};
		
		Thread workerThread = new Thread(worker);
		workerThread.start();
		
	}
	
	/**
	 * Reads a value from a given datapoint and dispatches back the value to the
	 * given driver.
	 * 
	 * @param dpt
	 *            the datapoint to read.
	 * @param deviceDriver
	 *            the driver to which the read value shall be sent.
	 */
	private void read(Datapoint dpt, KnxIPDriver deviceDriver, InetSocketAddress gwAddress)
	{
		try
		{
			ProcessCommunicator pc = this.gateway2ProcessCommunicator.get(gwAddress);
			if (pc != null)
			{
				// read the value from the network connection
				String readValue = pc.read(dpt);
				
				// dispatch the value through the asynchronous message
				// dispatching mechanism
				deviceDriver.newMessageFromHouse("", dpt.getMainAddress().toString(), readValue);
			}
		}
		catch (KNXException e)
		{
			this.logger.log(LogService.LOG_WARNING,
					KnxIpDriverImpl.logId + "Unable to read value from " + dpt.getMainAddress() + " exception: " + e);
		}
	}
	
	/**
	 * writes a value on the given device, represented by a command dpt
	 * 
	 * @param dpt
	 *            the command dpt representing the device.
	 * @param commandValue
	 *            the value of the command to send.
	 */
	private void write(Datapoint dpt, String commandValue, InetSocketAddress gwAddress)
	{
		try
		{
			ProcessCommunicator pc = this.gateway2ProcessCommunicator.get(gwAddress);
			if (pc != null)
			{
				pc.write(dpt, commandValue);
			}
		}
		catch (KNXException e)
		{
			this.logger.log(LogService.LOG_WARNING,
					KnxIpDriverImpl.logId + "Unable to write value to " + dpt.getMainAddress() + " exception: " + e);
		}
	}
	
	/*********************************** Process Listener Events ************************/
	@Override
	public void detached(DetachEvent arg0)
	{
		
		// TODO: check when this event is sent and why...
		this.logger.log(LogService.LOG_WARNING, KnxIpDriverImpl.logId
				+ "IP connection was lost.... retrying to connect in " + this.betweenTrialTimeMillis + "ms");
		
		// look for detached process communicators and try to re-attach...
		for (Entry<InetSocketAddress, KNXNetworkLinkIP> entry : this.gateway2IPLink.entrySet())
		{
			if (!entry.getValue().isOpen())
			{
				this.openKnxIPTunnel(entry.getKey());
			}
		}
	}
	
	@Override
	public void groupWrite(final ProcessEvent arg0)
	{
		// search the corresponding gateway
		InetSocketAddress gwAddress = null;
		
		for (Entry<InetSocketAddress, ProcessCommunicator> entry : this.gateway2ProcessCommunicator.entrySet())
		{
			if (entry.getValue().equals((ProcessCommunicator) arg0.getSource()))
			{
				gwAddress = entry.getKey();
			}
		}
		
		if (gwAddress != null)
		{
			// reset the watchdog
			if (this.watchdog != null)
			{
				this.watchdog.cancel();
				this.startWatchdog(gwAddress);
			}
			
			// get the corresponding DPT if available
			Set<KnxIPDeviceInfo> possibleDevices = this.gateway2Device.get(gwAddress);
			
			if (!possibleDevices.isEmpty())
			{
				// search the right device
				for (final KnxIPDeviceInfo deviceInfo : possibleDevices)
				{
					if (deviceInfo.getGroupAddress().equals(arg0.getDestination().toString()))
					{
						
						final DPT currentDPT = this.knxDeviceInfo2DPT.get(deviceInfo);
						
						Runnable workerRunnable = new Runnable() {
							
							@Override
							public void run()
							{
								// if a DPT has been found, decode the message
								// and pass-it back to the driver
								if (currentDPT != null)
								{
									
									try
									{
										// get the right DPTXlator
										DPTXlator translator = TranslatorTypes.createTranslator(currentDPT);
										
										// pass the raw data to the DPXlator
										translator.setData(arg0.getASDU());
										
										// get the value back
										String value = translator.getValue();
										
										// try to find a driver to dispatch to
										KnxIPDriver driver = knxDeviceInfo2Driver.get(deviceInfo);
										
										// if not null dispatch the message,
										// otherwise log the
										// missing driver
										if (driver != null)
										{
											// dispatch the decoded message...
											driver.newMessageFromHouse(arg0.getSourceAddr().toString(), arg0
													.getDestination().toString(), value);
											// log the value receipt
											logger.log(LogService.LOG_DEBUG,
													KnxIpDriverImpl.logId
															+ "Received low level KNX notification with destination "
															+ arg0.getDestination() + " and Value: " + value
															+ "\n Dispatched to the registered driver...");
										}
										else
										{
											logger.log(LogService.LOG_DEBUG, KnxIpDriverImpl.logId
													+ "Received low level KNX notification with destination "
													+ arg0.getDestination().toString() + " and value " + value
													+ " for which no driver has registered");
										}
										
									}
									catch (KNXFormatException e1)
									{
										
										e1.printStackTrace();
									}
									catch (KNXException e)
									{
										e.printStackTrace();
									}
								}
								else
								{
									// log the unknown message arrival
									logger.log(LogService.LOG_DEBUG, KnxIpDriverImpl.logId
											+ "Received low level KNX notification with unknown destination "
											+ arg0.getDestination().toString());
								}
								
							}
						};
						
						Thread workerThread = new Thread(workerRunnable);
						workerThread.start();
						
						// we assume only one DPT per group address
						break;
					}
				}
			}
		}
		
	}
	
	/**
	 * Checks if the given two addresses are the same according to the given
	 * netmask
	 * 
	 * @param ip1
	 * @param ip2
	 * @param mask
	 * @return
	 * @throws Exception
	 */
	private boolean isSameNetwork(InetAddress ip1, InetAddress ip2, String mask)
	{
		// get the byte representation of the inet address 1
		byte[] a1 = ip1.getAddress();
		
		// get the byte representation of the inet address 2
		byte[] a2 = ip2.getAddress();
		
		byte[] m = null;
		try
		{
			// get the byte of the mask inet address
			m = InetAddress.getByName(mask).getAddress();
		}
		catch (Exception e)
		{
			// handle the exception
			return false;
		}
		
		// if all the representations are available, check the addresses against
		// the netmask
		for (int i = 0; i < a1.length; i++)
			if ((a1[i] & m[i]) != (a2[i] & m[i]))
				return false;
		
		return true;
		
	}
	
}
