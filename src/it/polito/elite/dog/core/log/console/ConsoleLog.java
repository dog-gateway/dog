/*
 * Dog - Core
 * 
 * Copyright (c) 2013 Luigi De Russis
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
package it.polito.elite.dog.core.log.console;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogListener;
import org.osgi.service.log.LogReaderService;
import org.osgi.service.log.LogService;

/**
 * This class implements a Console Logger, by using the OSGi LogService. It can
 * also receive configuration data for enabling/disabling the output of the
 * standard OSGi log levels (error, warning, debug, and info).
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * @since September, 2013
 * 
 */
public class ConsoleLog implements LogListener, ManagedService
{
	// the registered log readers
	private AtomicReference<LogReaderService> registeredLogReaders;
	
	// the service registration handle
	private ServiceRegistration<?> serviceRegManagedService;
	
	// log types
	public static String[] logTypes = new String[] { "ERROR", "WARNING", "DEBUG", "INFO" };
	
	// enable or disable the output
	public static enum LogWriter
	{
		Console, None
	};
	
	// the table for setting the desired log levels
	private HashMap<Integer, Vector<LogWriter>> loggerWriterTable;
	
	/**
	 * Default constructor
	 */
	public ConsoleLog()
	{
		// initialize atomic references
		this.registeredLogReaders = new AtomicReference<LogReaderService>();
	}
	
	/**
	 * Activate this component (after its bind)
	 * 
	 * @param bundleContext
	 *            the bundle context
	 */
	public void activate(BundleContext bundleContext)
	{
		// init the table for setting the desired log levels
		this.loggerWriterTable = new HashMap<Integer, Vector<LogWriter>>();
		this.createDefaultWriterTable();
	}
	
	/**
	 * Deactivate this component (before its unbind)
	 */
	public void deactivate()
	{
		// unregister the services
		this.unRegister();
		
		// clear everything
		this.loggerWriterTable.clear();
		
		// set everything to null
		this.loggerWriterTable = null;
		this.serviceRegManagedService = null;
	}
	
	/**
	 * Initialize the table for setting the desired log levels: by default, it
	 * shows all the log in the console
	 */
	private void createDefaultWriterTable()
	{
		Vector<LogWriter> vectWriter = new Vector<LogWriter>();
		vectWriter.add(LogWriter.Console);
		this.loggerWriterTable.put(LogService.LOG_INFO, vectWriter);
		this.loggerWriterTable.put(LogService.LOG_DEBUG, vectWriter);
		this.loggerWriterTable.put(LogService.LOG_ERROR, vectWriter);
		this.loggerWriterTable.put(LogService.LOG_WARNING, vectWriter);
		
	}
	
	/**
	 * Unregister all the services...
	 */
	public void unRegister()
	{
		if (this.serviceRegManagedService != null)
		{
			this.serviceRegManagedService.unregister();
		}
		
	}
	
	/**
	 * Bind the component (before its activation)
	 * 
	 * @param reader
	 *            the required LogReaderService
	 */
	public void addedLogReaderService(LogReaderService reader)
	{
		// store the registered log reader service
		this.registeredLogReaders.set(reader);
		
		// add the current class as log listener
		reader.addLogListener(this);
	}
	
	/**
	 * Unbind the component (after its deactivation)
	 * 
	 * @param reader
	 *            the LogReaderService to unbind
	 */
	public void removedLogReaderService(LogReaderService reader)
	{
		// remove the current class as log listener
		reader.removeLogListener(this);
		
		// remove the previous registered log reader service
		this.registeredLogReaders.compareAndSet(reader, null);
	}
	
	/**
	 * Perform the effective log action
	 */
	@Override
	public void logged(LogEntry entry)
	{
		// get the log level
		int logEntryType = entry.getLevel();
		
		// show logs according to the configured log levels
		if (this.loggerWriterTable != null && this.loggerWriterTable.containsKey(logEntryType))
		{
			Vector<LogWriter> writers = this.loggerWriterTable.get(logEntryType);
			if (writers != null)
			{
				for (LogWriter writer : writers)
				{
					switch (writer)
					{
						case Console:
							System.out.println("LOG " + this.getLogLevel(entry) + " ["
									+ entry.getBundle().getSymbolicName() + "] " + entry.getMessage() + "\n");
							break;
						default:
							break;
					
					}
				}
			}
		}
		
	}
	
	/**
	 * Convert OSGi log levels to bundle-related log levels (and add the current
	 * timestamp)
	 * 
	 * @param entry
	 *            the log entry to display
	 * @return the human readable log level information (plus a timestamp)
	 */
	private String getLogLevel(LogEntry entry)
	{
		// get the time
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
		
		sb.append(sdf.format(Calendar.getInstance().getTime()));
		
		// log levels
		switch (entry.getLevel())
		{
			case LogService.LOG_DEBUG:
				sb.append(" DEBUG");
				break;
			case LogService.LOG_ERROR:
				sb.append(" ERROR");
				break;
			case LogService.LOG_INFO:
				sb.append(" INFO");
				break;
			case LogService.LOG_WARNING:
				sb.append(" WARNING");
				break;
			default:
				break;
		}
		
		return sb.toString();
	}
	
	/**
	 * Configuration data received from the Configuration Admin
	 */
	@Override
	public void updated(Dictionary<String, ?> properties) throws ConfigurationException
	{
		// maybe the received configuration is not for me...
		if (properties != null)
		{
			// clear the table for setting the desired log levels
			this.loggerWriterTable.clear();
			
			// set each log level to console or none, according to configuration
			// data
			String errorWriters = (String) properties.get("ERROR");
			if (errorWriters != null)
			{
				this.setWriters(LogService.LOG_ERROR, errorWriters);
			}
			String debugWriters = (String) properties.get("DEBUG");
			if (debugWriters != null)
			{
				this.setWriters(LogService.LOG_DEBUG, debugWriters);
			}
			String wariningWriters = (String) properties.get("WARNING");
			if (wariningWriters != null)
			{
				this.setWriters(LogService.LOG_WARNING, wariningWriters);
			}
			String infoWriters = (String) properties.get("INFO");
			if (infoWriters != null)
			{
				this.setWriters(LogService.LOG_INFO, infoWriters);
			}
		}
		
	}
	
	/**
	 * Set the given log level to console or none, according to configuration
	 * data received
	 * 
	 * @param logLevel
	 *            the log level to set
	 * @param writers
	 *            console or none, i.e., show or not show the log associated to
	 *            the given level
	 */
	private void setWriters(int logLevel, String writers)
	{
		String ws[] = writers.split(",");
		Vector<LogWriter> vect = new Vector<LogWriter>();
		for (String writer : ws)
		{
			try
			{
				LogWriter lw = LogWriter.valueOf(writer);
				vect.add(lw);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		this.loggerWriterTable.put(logLevel, vect);
		
	}
	
}
