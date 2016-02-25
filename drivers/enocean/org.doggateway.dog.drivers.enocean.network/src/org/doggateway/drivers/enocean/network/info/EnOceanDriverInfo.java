/*
 * Dog - EnOcean Network Driver
 * 
 * Copyright 2015 Dario Bonino 
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
package org.doggateway.drivers.enocean.network.info;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A class representing minimal information about an EnOceanDevice driver,
 * including the driver name, version, the main device class handled by the
 * driver and the set of supported EnOcean EEPs.
 * 
 * @author <a href="mailto:dario.bonino@gmail.com">Dario Bonino</a>
 *
 */
public class EnOceanDriverInfo
{
	// the driver name
	private String driverName;

	// the driver version;
	private String driverVersion;

	// the "main" class
	private String mainDeviceClass;

	// the EnOcean EEPs supported by the driver
	private HashSet<String> supportedEEPs;

	/**
	 * The class constructor, inizializes the inner data structure, provides an
	 * empty instance of EnOceanDriverInfo.
	 */
	public EnOceanDriverInfo()
	{
		// initialize the inner EEP set
		this.supportedEEPs = new HashSet<String>();
	}

	/**
	 * Provides the full class name of the driver described by this driver info
	 * 
	 * @return the driverName
	 */
	public String getDriverName()
	{
		return driverName;
	}

	/**
	 * Sets the class name of the driver described by this driver info instance
	 * 
	 * @param driverName
	 *            the driverName to set
	 */
	public void setDriverName(String driverName)
	{
		this.driverName = driverName;
	}

	/**
	 * Provides the version of the driver described by this driver info instance
	 * 
	 * @return the driverVersion
	 */
	public String getDriverVersion()
	{
		return driverVersion;
	}

	/**
	 * Sets the version of the driver described by this driver info instance
	 * 
	 * @param driverVersion
	 *            the driverVersion to set
	 */
	public void setDriverVersion(String driverVersion)
	{
		this.driverVersion = driverVersion;
	}

	/**
	 * Provides the class name of the dog device handled by the driver described
	 * by this driver info instance
	 * 
	 * @return the mainDeviceClass
	 */
	public String getMainDeviceClass()
	{
		return mainDeviceClass;
	}

	/**
	 * Sets the class name of the dog device handled by the driver described by
	 * this driver info instance
	 * 
	 * @param mainDeviceClass
	 *            the mainDeviceClass to set
	 */
	public void setMainDeviceClass(String mainDeviceClass)
	{
		this.mainDeviceClass = mainDeviceClass;
	}

	/**
	 * Provides the set of EEPs supported by the driver described by this driver
	 * info instance
	 * 
	 * @return the supportedEEPs
	 */
	public HashSet<String> getSupportedEEPs()
	{
		return supportedEEPs;
	}

	/**
	 * Adds the given EEPs description strings to the set of EEPs supported by
	 * the driver described by this driver info instance
	 * 
	 * @param eeps
	 */
	public void addSupportedEEPs(String... eeps)
	{
		Collections.addAll(this.supportedEEPs, eeps);
	}

	/**
	 * Adds the given EEPs description strings to the set of EEPs supported by
	 * the driver described by this driver info instance
	 * 
	 * @param eeps
	 */
	public void addSupportedEEPs(Set<String> eeps)
	{
		this.supportedEEPs.addAll(eeps);
	}

	/**
	 * Adds the given EEP description string to the set of EEPs supported by the
	 * driver described by this driver info instance
	 * 
	 * @param eep
	 */
	public void addSupportedEEP(String eep)
	{
		this.supportedEEPs.add(eep);
	}

}
