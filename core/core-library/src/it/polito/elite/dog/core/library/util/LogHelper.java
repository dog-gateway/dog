/*
 * Dog - Core
 * 
 * Copyright (c) 2010-2013 Dario Bonino, Luigi De Russis and Emiliano Castellina
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
package it.polito.elite.dog.core.library.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;

/**
 * A utility class that helps lessen the lines of code needed to log a
 * message by using the OSGi LogService. If the OSGi LogService is not
 * available, it provides a basic log on the standard output.
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class LogHelper
{
	private BundleContext context;
	
	/**
	 * Default constructor
	 * 
	 * @param context
	 *            the bundle context
	 */
	public LogHelper(BundleContext context)
	{
		this.context = context;
	}
	
	/**
	 * Get the reference to the active LogService (if any)
	 * 
	 * @return the current, active, logger
	 */
	private LogService getLogger()
	{
		LogService logger = null;
		// get the logger if exists and the context is still valid
		try
		{
			ServiceReference<?> sr = this.context.getServiceReference(LogService.class.getName());
			if (sr != null)
			{
				logger = (LogService) this.context.getService(sr);
			}
		}
		// the context is no longer valid...
		catch (IllegalStateException e)
		{
			// intentionally left empty: the IllegalStateException should happen
			// when a bundle in the stopping phase tries to print a log
		}
		
		return logger;
	}
	
	/**
	 * Logs a message.
	 * 
	 * @param level
	 *            The severity of the message. This should be one of the defined
	 *            log levels but may be any integer that is interpreted in a
	 *            user defined way.
	 * @param message
	 *            Human readable string describing the condition or null.
	 */
	public void log(int level, String message)
	{
		LogService logger = this.getLogger();
		if (logger != null)
		{
			logger.log(level, message);
		}
		else
		{
			// no OSGi LogService available...
			System.out.println("BASIC LOG " + this.getLogLevel(level) + ": " + message);
		}
		
	}
	
	/**
	 * Logs a message with an exception.
	 * 
	 * @param level
	 *            The severity of the message. This should be one of the defined
	 *            log levels but may be any integer that is interpreted in a
	 *            user defined way.
	 * @param message
	 *            The human readable string describing the condition or null.
	 * @param exception
	 *            The exception that reflects the condition or null.
	 */
	public void log(int level, String message, Throwable exception)
	{
		LogService logger = this.getLogger();
		if (logger != null)
		{
			logger.log(level, message, exception);
			
		}
		else
		{
			// no OSGi LogService available...
			System.err.println("BASIC LOG " + this.getLogLevel(level) + ": " + message + " exception <<"
					+ exception.getMessage() + " >>");
		}
		
	}
	
	/**
	 * Logs a message associated with a specific ServiceReference object.
	 * 
	 * @param sr
	 *            The ServiceReference object of the service that this message
	 *            is associated with or null.
	 * @param level
	 *            The severity of the message. This should be one of the defined
	 *            log levels but may be any integer that is interpreted in a
	 *            user defined way.
	 * @param message
	 *            Human readable string describing the condition or null.
	 */
	public void log(ServiceReference<?> sr, int level, String message)
	{
		LogService logger = this.getLogger();
		if (logger != null)
		{
			logger.log(sr, level, message);
		}
		else
		{
			// no OSGi LogService available...
			System.out.println("BASIC LOG " + this.getLogLevel(level) + ": " + message + " sr = {" + sr.toString()
					+ "}");
		}
		
	}
	
	/**
	 * Logs a message with an exception associated and a ServiceReference
	 * object.
	 * 
	 * @param sr
	 *            The ServiceReference object of the service that this message
	 *            is associated with.
	 * @param level
	 *            The severity of the message. This should be one of the defined
	 *            log levels but may be any integer that is interpreted in a
	 *            user defined way.
	 * @param message
	 *            Human readable string describing the condition or null.
	 * @param exception
	 *            The exception that reflects the condition or null.
	 */
	public void log(ServiceReference<?> sr, int level, String message, Throwable exception)
	{
		LogService logger = this.getLogger();
		if (logger != null)
		{
			logger.log(sr, level, message, exception);
		}
		else
		{
			// no OSGi LogService available...
			System.err.println("BASIC LOG " + this.getLogLevel(level) + ": " + message + " sr = {" + sr.toString()
					+ "} " + "exception <<" + exception.getMessage() + " >>");
		}
		
	}
	
	/**
	 * Convert OSGi log levels to custom log levels (and add the current
	 * timestamp)
	 * 
	 * @param level
	 *            the log level received from a bundle
	 * @return the human readable log level information (plus a timestamp)
	 */
	private String getLogLevel(int level)
	{
		// get the time
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
		
		sb.append(sdf.format(Calendar.getInstance().getTime()));
		
		// log levels
		switch (level)
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
	
}
