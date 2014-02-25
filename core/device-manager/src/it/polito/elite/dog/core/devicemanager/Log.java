/*
 * Dog - Core
 * 
 * Copyright (c) 2013 Dario Bonino and Luigi De Russis
 * 
 * This software is based on a bundle of the Apache Felix project.
 * See the NOTICE file distributed with this work for additional information.
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
package it.polito.elite.dog.core.devicemanager;

/**
 * Shortcuts for logger methods, according to the different log levels.
 * 
 * @author <a href="mailto:dev@felix.apache.org">Felix Project Team</a>
 *         (original version)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 */
public interface Log
{
	/**
	 * Perform logging with the LOG_DEBUG level.
	 * 
	 * @param message
	 *            the message to log
	 */
	public void debug(String message);
	
	/**
	 * Perform logging with the LOG_INFO level.
	 * 
	 * @param message
	 *            the message to log
	 */
	public void info(String message);
	
	/**
	 * Perform logging with the LOG_WARNING level.
	 * 
	 * @param message
	 *            the message to log
	 */
	public void warning(String message);
	
	/**
	 * Perform logging with the LOG_ERROR level (and reporting the eventual
	 * exception).
	 * 
	 * @param message
	 *            the message to log
	 * @param e
	 *            the exception to log
	 */
	public void error(String message, Throwable e);
	
}