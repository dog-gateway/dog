/*
 * Dog - Core
 * 
 * Copyright (c) 2010-2013 Dario Bonino, Emiliano Castellina and Luigi De Russis
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
package it.polito.elite.dog.core.library.model;

import it.polito.elite.dog.core.library.util.ElementDescription;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A descriptor for a Dog/DogOnt device instance, it allows representing
 * together a set of low level device features such as the network parameters,
 * the device-specific command configurations, etc.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 *         (original implementation)
 * @author <a href="mailto:emiliano.castellina@polito.it">Emiliano
 *         Castellina</a> (successive modifications)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class DeviceDescriptor
{	
	private String devURI;
	private String devCategory;
	private String devDescription;
	private String devTechnology;
	private String gateway;
	private String hasMeter;
	private Set<String> meterOf;
	private Set<String> controlledObjects;
	private String pluggedIn;
	private String sensorOf;
	private String actuatorOf;
	private String devLocation;
	private Map<String, Set<String>> devSimpleConfigurationParams;
	private Set<ElementDescription> devCommandSpecificParams;
	private Set<ElementDescription> devNotificationSpecificParams;
	
	/**
	 * Creates a descriptor for a Dog/DogOnt device instance, it allows
	 * representing together a set of low level device features such as the
	 * network parameters, the device-specific command configurations, etc.
	 * 
	 * @param devURI
	 *            The device URI, which uniquely identifies it inside a given
	 *            home representation in DogOnt
	 * @param devCategory
	 *            The device category, i.e. the DogOnt class from which this
	 *            device inherits (descending from dogont:Controllable)
	 * @param devDescription
	 *            The human-readable description of the device as extracted from
	 *            the configuration
	 * @param devTechnology
	 *            The technology with which the device is realized either
	 *            BTICINO, KNX or ELITE
	 * @param devLocation
	 *            The architectural space in which the device is located.
	 * @param devSimpleConfigurationParams
	 *            The configuration parameters of the given device, e.g., the
	 *            group address or the physical type, etc.
	 * @param devCommandSpecificParams
	 *            The command specific parameters such as the group address or
	 *            the command hex value (at the moment it is needed only for KNX
	 *            plants)
	 */
	public DeviceDescriptor(String devURI, String devCategory, String devDescription, String devTechnology,
			String devLocation, Map<String, Set<String>> devSimpleConfigurationParams,
			Set<ElementDescription> devCommandSpecificParams,
			Set<ElementDescription> devNotificationSpecificParams)
	{
		this.devURI = devURI;
		this.devCategory = devCategory;
		this.devDescription = devDescription;
		this.devTechnology = devTechnology;
		this.devLocation = devLocation;
		this.devSimpleConfigurationParams = devSimpleConfigurationParams;
		this.devCommandSpecificParams = devCommandSpecificParams;
		this.devNotificationSpecificParams = devNotificationSpecificParams;
	}
	
	/**
	 * Creates a descriptor for a Dog/DogOnt device instance, it allows
	 * representing together a set of low level device features such as the
	 * network parameters, the device-specific command configurations, etc.
	 * 
	 * @param devURI
	 *            The device URI, which uniquely identifies it inside a given
	 *            home representation in DogOnt
	 * @param devCategory
	 *            The device category, i.e. the DogOnt class from which this
	 *            device inherits (descending from dogont:Controllable)
	 * @param devDescription
	 *            The human-readable description of the device as extracted from
	 *            the DogOnt ontology
	 * @param devTechnology
	 *            The technology with which the device is realized either
	 *            BTICINO, KNX or ELITE
	 * @param devLocations
	 *            The architectural spaces in which the device is located, it
	 *            might be in 2 or more places... For example a door isIn all
	 *            rooms that are connected through it.
	 */
	public DeviceDescriptor(String devURI, String devCategory, String devDescription, String devTechnology,
			String devLocation)
	{
		super();
		this.devURI = devURI;
		this.devCategory = devCategory;
		this.devDescription = devDescription;
		this.devTechnology = devTechnology;
		this.devLocation = devLocation;
		this.devSimpleConfigurationParams = new HashMap<String, Set<String>>();
		this.devCommandSpecificParams = new HashSet<ElementDescription>();
		this.devNotificationSpecificParams = new HashSet<ElementDescription>();
	}
	
	/**
	 * Creates a descriptor for a Dog/DogOnt device instance, it allows
	 * representing together a set of low level device features such as the
	 * network parameters, the device-specific command configurations, etc.
	 * 
	 * @param devURI
	 *            The device URI, which uniquely identifies it inside a given
	 *            home representation in DogOnt
	 * @param devCategory
	 *            The device category, i.e. the DogOnt class from which this
	 *            device inherits (descending from dogont:Controllable)
	 * @param devDescription
	 *            The human-readable description of the device as extracted from
	 *            the DogOnt ontology
	 * @param devTechnology
	 *            The technology with which the device is realized either
	 *            BTICINO, KNX or ELITE
	 */
	public DeviceDescriptor(String devURI, String devCategory, String devDescription, String devTechnology)
	{
		super();
		this.devURI = devURI;
		this.devCategory = devCategory;
		this.devDescription = devDescription;
		this.devTechnology = devTechnology;
		this.devLocation = "";
		this.devSimpleConfigurationParams = new HashMap<String, Set<String>>();
		this.devCommandSpecificParams = new HashSet<ElementDescription>();
		this.devNotificationSpecificParams = new HashSet<ElementDescription>();
	}
	
	/***
	 * Dummy class constructor, with only a parameter
	 * 
	 * @param devURI
	 *            The device URI, which uniquely identifies it inside a given
	 *            home representation in DogOnt
	 */
	public DeviceDescriptor(String devURI)
	{
		this(devURI, "", "", "");
	}
	
	/**
	 * @param devURI
	 *            the devURI to set
	 */
	public void setDevURI(String devURI)
	{
		this.devURI = devURI;
	}
	
	/**
	 * Provides back the URI of the device represented by this description
	 * 
	 * @return The device URI as {@link String}
	 */
	public String getDevURI()
	{
		return devURI;
	}
	
	/**
	 * @param devCategory
	 *            the devCategory to set
	 */
	public void setDevCategory(String devCategory)
	{
		this.devCategory = devCategory;
	}
	
	/**
	 * Provides back the DogOnt class of the device represented by this
	 * description
	 * 
	 * @return The class URI as {@link String}
	 */
	public String getDevCategory()
	{
		return devCategory;
	}
	
	/**
	 * @param devDescription
	 *            the devDescription to set
	 */
	public void setDevDescription(String devDescription)
	{
		this.devDescription = devDescription;
	}
	
	/**
	 * Provides back the description of the device represented by this
	 * description
	 * 
	 * @return The device description as {@link String}
	 */
	public String getDevDescription()
	{
		return devDescription;
	}
	
	/**
	 * @param gateway
	 *            the gateway to set
	 */
	public void setGateway(String gateway)
	{
		this.gateway = gateway;
	}
	
	public String getGateway()
	{
		return gateway;
	}
	
	/**
	 * @return the meterOf
	 */
	public Set<String> getMeterOf()
	{
		return meterOf;
	}
	
	/**
	 * @param meterOf
	 *            the meterOf to set
	 */
	public void setMeterOf(Set<String> meterOf)
	{
		this.meterOf = meterOf;
	}
	
	
	
	/**
	 * @return the hasMeter
	 */
	public String getHasMeter()
	{
		return hasMeter;
	}

	/**
	 * @param hasMeter the hasMeter to set
	 */
	public void setHasMeter(String hasMeter)
	{
		this.hasMeter = hasMeter;
	}

	/**
	 * @return the controlledObjects
	 */
	public Set<String> getControlledObjects()
	{
		return controlledObjects;
	}

	/**
	 * @param controlledObjects the controlledObjects to set
	 */
	public void setControlledObjects(Set<String> controlledObjects)
	{
		this.controlledObjects = controlledObjects;
	}

	/**
	 * @return the pluggedIn
	 */
	public String getPluggedIn()
	{
		return pluggedIn;
	}

	/**
	 * @param pluggedIn the pluggedIn to set
	 */
	public void setPluggedIn(String pluggedIn)
	{
		this.pluggedIn = pluggedIn;
	}

	/**
	 * @return the sensorOf
	 */
	public String getSensorOf()
	{
		return sensorOf;
	}

	/**
	 * @param sensorOf the sensorOf to set
	 */
	public void setSensorOf(String sensorOf)
	{
		this.sensorOf = sensorOf;
	}

	/**
	 * @return the actuatorOf
	 */
	public String getActuatorOf()
	{
		return actuatorOf;
	}

	/**
	 * @param actuatorOf the actuatorOf to set
	 */
	public void setActuatorOf(String actuatorOf)
	{
		this.actuatorOf = actuatorOf;
	}

	/**
	 * @param devNotificationSpecificParams
	 *            the devNotificationSpecificParams to set
	 */
	public void setDevNotificationSpecificParams(Set<ElementDescription> devNotificationSpecificParams)
	{
		this.devNotificationSpecificParams = devNotificationSpecificParams;
	}
	
	public Set<ElementDescription> getDevNotificationSpecificParams()
	{
		// return a copy of the element
		return new HashSet<ElementDescription>(this.devNotificationSpecificParams);
	}
	
	/**
	 * Provides the Locations in which the device described by this class is
	 * placed
	 * 
	 * @return A {@link Set} of location URIs indicating the rooms in which the
	 *         device is located
	 */
	public String getDevLocation()
	{
		return devLocation;
	}
	
	/**
	 * Sets the Locations in which the device described by this class is placed
	 * 
	 * @param devLocations
	 *            A {@link Set} of location URIs indicating the rooms in which
	 *            the device is located
	 */
	public void setDevLocation(String devLocation)
	{
		this.devLocation = devLocation;
	}
	
	/**
	 * Provide the {@link Map} containing all device configuration parameters in
	 * name - value couples
	 * 
	 * @return A {@link Map}<{@link String},{@link Set}<{@link String}>>
	 *         containing all device configuration parameters in name - value
	 *         couples
	 */
	public Map<String, Set<String>> getDevSimpleConfigurationParams()
	{
		return devSimpleConfigurationParams;
	}
	
	/**
	 * Sets the {@link Map} containing all device configuration parameters in
	 * name - value couples
	 * 
	 * @param devSimpleConfigurationParams
	 *            A {@link Map}<{@link String},{@link String}> containing all
	 *            device configuration parameters in name - value couples
	 */
	public void setDevSimpleConfigurationParams(Map<String, Set<String>> devSimpleConfigurationParams)
	{
		this.devSimpleConfigurationParams = devSimpleConfigurationParams;
	}
	
	/**
	 * Add one device configuration parameter and its corresponding value
	 * 
	 * @param name
	 *            The device configuration parameter name
	 * @param value
	 *            The device configuration parameter value
	 * @return true if the addition is successful, false otherwise
	 */
	public boolean addDevSimpleConfigurationParam(String name, String value)
	{
		if ((this.devSimpleConfigurationParams != null) && (name != null) && (!name.isEmpty()) && (value != null)
				&& (!value.isEmpty()))
		{
			Set<String> values = this.devSimpleConfigurationParams.get(name);
			if (values != null)
				values.add(value);
			else
				this.devSimpleConfigurationParams.put(name, new HashSet<String>(Collections.singleton(value)));
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Provides a {@link Set} of command specific configurations associated the
	 * device represented by this description
	 * 
	 * @return A {@link Set}<{@link ElementDescription}> of command
	 *         descriptions detailing command specific configurations associated
	 *         to this device
	 */
	public Set<ElementDescription> getDevCommandSpecificParams()
	{
		return devCommandSpecificParams;
	}
	
	/**
	 * Sets the {@link Set} of command specific configurations associated to the
	 * device represented by this description
	 * 
	 * @param devCommandSpecificParams
	 *            A {@link Set}<{@link ElementDescription}> of command
	 *            descriptions detailing command specific configurations
	 *            associated to this device
	 */
	public void setDevCommandSpecificParams(Set<ElementDescription> devCommandSpecificParams)
	{
		this.devCommandSpecificParams = devCommandSpecificParams;
	}
	
	/**
	 * Adds one command specific configuration parameter, represented by a
	 * {@link ElementDescription} instance
	 * 
	 * @param param
	 *            The command specific configuration parameter to add.
	 * @return true if the addition is successful, false otherwise
	 */
	public boolean addDevCommandSpecificParam(ElementDescription param)
	{
		if ((this.devCommandSpecificParams != null) && (param != null))
		{
			this.devCommandSpecificParams.add(param);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Adds one notification specific configuration parameter, represented by a
	 * {@link ElementDescription} instance
	 * 
	 * @param param
	 *            The notification specific configuration parameter to add.
	 * @return true if the addition is successful, false otherwise
	 */
	public boolean addDevNotificationSpecificParam(ElementDescription param)
	{
		if ((this.devNotificationSpecificParams != null) && (param != null))
		{
			this.devNotificationSpecificParams.add(param);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Provides back the technology with which the device represented by this
	 * description is built
	 * 
	 * @return The device-building technology as {@link String}
	 */
	public String getDevTechnology()
	{
		return devTechnology;
	}
	
	/**
	 * Sets the technology with which which the device represented by this
	 * description is built
	 * 
	 * @param devTechnology
	 *            The technology as a String (in upper case)
	 */
	public void setDevTechnology(String devTechnology)
	{
		this.devTechnology = devTechnology;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{ ");
		sb.append("LOCATION = " + this.devLocation + ", ");
		sb.append("DESCRIPTION = " + this.devDescription + ", ");
		sb.append("DEVICE_CATEGORY = " + this.devCategory + ", ");
		sb.append("MANUFACTURER = " + this.devTechnology + ", ");
		sb.append("GATEWAY = " + this.gateway + " }\n");
		return sb.toString();
	}
	
}
