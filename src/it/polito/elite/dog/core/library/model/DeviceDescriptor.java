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

import it.polito.elite.dog.core.library.jaxb.Configcommand;
import it.polito.elite.dog.core.library.jaxb.Confignotification;
import it.polito.elite.dog.core.library.jaxb.Configparam;
import it.polito.elite.dog.core.library.jaxb.ControlFunctionality;
import it.polito.elite.dog.core.library.jaxb.Device;
import it.polito.elite.dog.core.library.jaxb.NotificationFunctionality;
import it.polito.elite.dog.core.library.util.ElementDescription;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A descriptor for a Dog/DogOnt device instance. It allows representing a set
 * of low level device features such as the network parameters, the
 * device-specific command configurations, etc., and it contains the JAXB device
 * representation.
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class DeviceDescriptor
{
	private String deviceURI;
	private String deviceCategory;
	private String description;
	private String technology;
	private String gateway;
	private String hasMeter;
	private Set<String> meterOf;
	private Set<String> controlledObjects;
	private String pluggedIn;
	private String sensorOf;
	private String actuatorOf;
	private String location;
	private Map<String, Set<String>> simpleConfigurationParams;
	private Set<ElementDescription> commandSpecificParams;
	private Set<ElementDescription> notificationSpecificParams;
	private Device jaxbDevice;
	
	/**
	 * /** Creates a descriptor for a Dog/DogOnt device instance, it allows
	 * representing together a set of low level device features such as the
	 * network parameters, the device-specific command configurations, etc.
	 * 
	 * @param jaxbDevice
	 *            the JAXB representation of a device
	 */
	public DeviceDescriptor(Device jaxbDevice)
	{
		// init
		this.simpleConfigurationParams = new HashMap<String, Set<String>>();
		this.location = "";
		
		// the device unique URI (mandatory)
		this.deviceURI = jaxbDevice.getId();
		
		// manufacturer (mandatory)
		this.technology = jaxbDevice.getDomoticSystem();
		
		// device category (mandatory)
		this.deviceCategory = jaxbDevice.getClazz();
		
		// location in the environment
		if (jaxbDevice.getIsIn() != null)
			this.location = jaxbDevice.getIsIn();
		
		// description (long name)
		this.description = jaxbDevice.getDescription();
		
		// gateway
		this.gateway = jaxbDevice.getGateway();
		
		// actuator
		this.actuatorOf = jaxbDevice.getActuatorOf();
		
		// controlled objects
		List<String> allControls = jaxbDevice.getControls();
		if ((allControls != null) && (!allControls.isEmpty()))
			this.controlledObjects = new HashSet<String>(allControls);
		
		// has meter
		this.hasMeter = jaxbDevice.getHasMeter();
		
		// meter of
		List<String> allMeterOf = jaxbDevice.getMeterOf();
		if ((allMeterOf != null) && (!allMeterOf.isEmpty()))
			this.meterOf = new HashSet<String>(allMeterOf);
		
		// plugged in
		this.pluggedIn = jaxbDevice.getPluggedIn();
		
		// sensor of
		this.sensorOf = jaxbDevice.getSensorOf();
		
		// set general parameters
		for (Configparam param : jaxbDevice.getParam())
		{
			this.addSimpleConfigurationParam(param.getName(), param.getValue());
		}
		
		// set command-specific parameters
		HashSet<ElementDescription> commandsParameter = new HashSet<ElementDescription>();
		
		for (ControlFunctionality controlF : jaxbDevice.getControlFunctionality())
		{
			for (Configcommand command : controlF.getCommands().getCommand())
			{
				ElementDescription dogElementDescription = new ElementDescription(command.getName(), command.getClazz());
				for (Configparam param : command.getParam())
				{
					dogElementDescription.addElementParam(param.getName(), param.getValue());
				}
				commandsParameter.add(dogElementDescription);
				
			}
		}
		this.commandSpecificParams = commandsParameter;
		
		// set notification-specific parameters
		HashSet<ElementDescription> notificationsParameter = new HashSet<ElementDescription>();
		for (NotificationFunctionality notificatinF : jaxbDevice.getNotificationFunctionality())
		{
			for (Confignotification notification : notificatinF.getNotifications().getNotification())
			{
				ElementDescription dogElementDescription = new ElementDescription(notification.getId(),
						notification.getClazz());
				
				for (Configparam param : notification.getParam())
				{
					dogElementDescription.addElementParam(param.getName(), param.getValue());
				}
				notificationsParameter.add(dogElementDescription);
				
			}
		}
		this.notificationSpecificParams = notificationsParameter;
		
		// store the JAXB representation of the device
		this.setJaxbDevice(jaxbDevice);
	}
	
	/**
	 * Provide the URI of the device represented by this descriptor
	 * 
	 * @return The device URI as {@link String}
	 */
	public String getDeviceURI()
	{
		return this.deviceURI;
	}
	
	/**
	 * Provide the DogOnt class of the device represented by this descriptor
	 * 
	 * @return The class name as {@link String}
	 */
	public String getDeviceCategory()
	{
		return this.deviceCategory;
	}
	
	/**
	 * @param deviceCategory
	 *            the deviceCategory to set
	 */
	public void setDeviceCategory(String deviceCategory)
	{
		this.deviceCategory = deviceCategory;
		
		// update the JAXB device
		this.jaxbDevice.setClazz(deviceCategory);
	}
	
	/**
	 * @param deviceDescription
	 *            the description (long name) of the device to set
	 */
	public void setDescription(String deviceDescription)
	{
		this.description = deviceDescription;
		
		// update the JAXB device
		this.jaxbDevice.setDescription(deviceDescription);
	}
	
	/**
	 * Provide the description (long name) of the device represented by this
	 * descriptor
	 * 
	 * @return the device description as {@link String}
	 */
	public String getDescription()
	{
		return this.description;
	}
	
	/**
	 * Get the gateway associated to this device
	 * 
	 * @return the gateway device URI
	 */
	public String getGateway()
	{
		return this.gateway;
	}
	
	/**
	 * @param gateway
	 *            the gateway to set
	 */
	public void setGateway(String gateway)
	{
		this.gateway = gateway;
		
		// update the JAXB device
		this.jaxbDevice.setGateway(gateway);
	}
	
	/**
	 * @return the meterOf information
	 */
	public Set<String> getMeterOf()
	{
		return this.meterOf;
	}
	
	/**
	 * @param meterOf the meterOf to set
	 */
	public void setMeterOf(Set<String> meterOf)
	{
		this.meterOf = meterOf;
		
		// update the JAXB device
		this.jaxbDevice.getMeterOf().addAll(meterOf);
	}

	/**
	 * @return the hasMeter information
	 */
	public String getHasMeter()
	{
		return this.hasMeter;
	}
	
	/**
	 * @param hasMeter
	 *            the hasMeter to set
	 */
	public void setHasMeter(String hasMeter)
	{
		this.hasMeter = hasMeter;
		
		// update the JAXB device
		this.setHasMeter(hasMeter);
	}
	
	/**
	 * @return the controlledObjects
	 */
	public Set<String> getControlledObjects()
	{
		return this.controlledObjects;
	}
	
	/**
	 * @param controlledObjects the controlledObjects to set
	 */
	public void setControlledObjects(Set<String> controlledObjects)
	{
		this.controlledObjects = controlledObjects;
		
		// update the JAXB device
		this.jaxbDevice.getControls().addAll(controlledObjects);
	}

	/**
	 * @return the pluggedIn device URI
	 */
	public String getPluggedIn()
	{
		return this.pluggedIn;
	}
	
	/**
	 * @param pluggedIn
	 *            the pluggedIn to set
	 */
	public void setPluggedIn(String pluggedIn)
	{
		this.pluggedIn = pluggedIn;
		
		// update the JAXB device
		this.jaxbDevice.setPluggedIn(pluggedIn);
	}
	
	/**
	 * @return the sensorOf information
	 */
	public String getSensorOf()
	{
		return this.sensorOf;
	}
	
	/**
	 * @param sensorOf
	 *            the sensorOf to set
	 */
	public void setSensorOf(String sensorOf)
	{
		this.sensorOf = sensorOf;
		
		// update the JAXB device
		this.jaxbDevice.setSensorOf(sensorOf);
	}
	
	/**
	 * @return the actuatorOf device URI
	 */
	public String getActuatorOf()
	{
		return this.actuatorOf;
	}
	
	/**
	 * @param actuatorOf
	 *            the actuatorOf to set
	 */
	public void setActuatorOf(String actuatorOf)
	{
		this.actuatorOf = actuatorOf;
		
		// update the JAXB device
		this.jaxbDevice.setActuatorOf(actuatorOf);
	}
	
	/**
	 * Get the notification-related parameter store for the current device
	 * descriptor
	 * 
	 * @return the notification-related parameters as a {@link Set} of
	 *         {@link ElementDescription}
	 */
	public Set<ElementDescription> getNotificationSpecificParams()
	{
		// return a copy of the element
		return new HashSet<ElementDescription>(this.notificationSpecificParams);
	}
	
	/**
	 * Provide the location in which the device described is placed
	 * 
	 * @return the location indicating the room in which the device is located
	 */
	public String getLocation()
	{
		return this.location;
	}
	
	/**
	 * Set the location in which the device described is placed
	 * 
	 * @param deviceLocation
	 *            A location as a {@link String}, indicating the room in which
	 *            the device is located
	 */
	public void setLocation(String deviceLocation)
	{
		this.location = deviceLocation;
		
		// update the JAXB device
		this.jaxbDevice.setIsIn(deviceLocation);
	}
	
	/**
	 * Provide the {@link Map} containing all device configuration parameters in
	 * name - value couples
	 * 
	 * @return A {@link Map}<{@link String},{@link Set}<{@link String}>>
	 *         containing all device configuration parameters in name - value
	 *         couples
	 */
	public Map<String, Set<String>> getSimpleConfigurationParams()
	{
		return this.simpleConfigurationParams;
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
	private boolean addSimpleConfigurationParam(String name, String value)
	{
		if ((this.simpleConfigurationParams != null) && (name != null) && (!name.isEmpty()) && (value != null)
				&& (!value.isEmpty()))
		{
			Set<String> values = this.simpleConfigurationParams.get(name);
			if (values != null)
				values.add(value);
			else
				this.simpleConfigurationParams.put(name, new HashSet<String>(Collections.singleton(value)));
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Provides a {@link Set} of command specific configurations associated the
	 * device represented by this description
	 * 
	 * @return A {@link Set}<{@link ElementDescription}> of command descriptions
	 *         detailing command specific configurations associated to this
	 *         device
	 */
	public Set<ElementDescription> getCommandSpecificParams()
	{
		return this.commandSpecificParams;
	}
	
	/**
	 * Provide the technology with which the device represented by this
	 * descriptor is built
	 * 
	 * @return The device-building technology as {@link String}
	 */
	public String getTechnology()
	{
		return this.technology;
	}
	
	/**
	 * @param technology
	 *            the technology to set
	 */
	public void setTechnology(String technology)
	{
		this.technology = technology;
		
		// update the JAXB device
		this.jaxbDevice.setDomoticSystem(technology);
	}
	
	/**
	 * @return jaxbDevice the JAXB representation of the described device
	 */
	public Device getJaxbDevice()
	{
		return this.jaxbDevice;
	}
	
	/**
	 * @param jaxbDevice
	 *            the jaxbDevice to set
	 */
	public void setJaxbDevice(Device jaxbDevice)
	{
		this.jaxbDevice = jaxbDevice;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{ ");
		sb.append("LOCATION = " + this.location + ", ");
		sb.append("DESCRIPTION = " + this.description + ", ");
		sb.append("DEVICE_CATEGORY = " + this.deviceCategory + ", ");
		sb.append("MANUFACTURER = " + this.technology + ", ");
		sb.append("GATEWAY = " + this.gateway + " }\n");
		return sb.toString();
	}
	
}
