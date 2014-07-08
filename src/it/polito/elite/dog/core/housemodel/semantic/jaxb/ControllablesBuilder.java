/*
 * Dog - Core
 * 
 * Copyright (c) 2014 Luigi De Russis
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
package it.polito.elite.dog.core.housemodel.semantic.jaxb;

import it.polito.elite.dog.core.housemodel.semantic.owl.model.ControllableModel;
import it.polito.elite.dog.core.library.jaxb.Commands;
import it.polito.elite.dog.core.library.jaxb.Configcommand;
import it.polito.elite.dog.core.library.jaxb.Confignotification;
import it.polito.elite.dog.core.library.jaxb.Configparam;
import it.polito.elite.dog.core.library.jaxb.Configstate;
import it.polito.elite.dog.core.library.jaxb.ControlFunctionality;
import it.polito.elite.dog.core.library.jaxb.Controllables;
import it.polito.elite.dog.core.library.jaxb.Device;
import it.polito.elite.dog.core.library.jaxb.NotificationFunctionality;
import it.polito.elite.dog.core.library.jaxb.Notifications;
import it.polito.elite.dog.core.library.jaxb.ObjectFactory;
import it.polito.elite.dog.core.library.jaxb.Statevalue;
import it.polito.elite.dog.core.library.jaxb.Statevalues;
import it.polito.elite.dog.core.library.semantic.OWLWrapper;

import java.util.Map;
import java.util.Set;

/**
 * Create the {@link Controllables} part of the Dog JAXB configuration by
 * querying the given ontology.
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class ControllablesBuilder
{
	private ControllableModel cModel;
	
	/**
	 * Default constructor.
	 * 
	 * @param owlWrapper
	 *            the {@link OWLWrapper} instance containing all the needed
	 *            OWL-related information
	 */
	public ControllablesBuilder(OWLWrapper owlWrapper)
	{
		this.cModel = new ControllableModel(owlWrapper);
	}
	
	/**
	 * Get all the controllables information.
	 * 
	 * @param factory
	 *            the {@link ObjectFactory} needed to build JAXB objects
	 * @return the {@link Controllables}, i.e., the JAXB part related to the
	 *         device description
	 */
	public Controllables getControllablesInformation(ObjectFactory factory)
	{
		// create the controllables container element
		Controllables controllables = factory.createControllables();
		
		// start time
		long time = System.currentTimeMillis();
		
		// get all controllables from the ontology
		Set<String> allControllables = this.cModel.getAllControllableInstances();
		
		for (String deviceName : allControllables)
		{
			// creates the device
			Device device = factory.createDevice();
			// set the device name
			device.setId(deviceName);
			
			// get the network type (domotic system)
			String networkType = this.cModel.getNetworkComponent(deviceName);
			int cIndex = -1;
			if (networkType.endsWith("Component"))
				cIndex = networkType.indexOf("Component");
			else if (networkType.endsWith("Gateway"))
				cIndex = networkType.indexOf("Gateway");
			if (cIndex != -1)
				device.setDomoticSystem(networkType.substring(0, cIndex).toUpperCase());
			
			// get the device type
			device.setClazz(this.cModel.getDeviceType(deviceName));
			
			// get all the needed device info
			this.getDeviceInfo(factory, device);
			
			// add the device to the JAXB
			controllables.getDevice().add(device);
		}
		
		// debug
		System.err.println("All controllables extracted in " + ((float) (System.currentTimeMillis() - time) / 1000)
				+ " seconds");
		
		return controllables;
	}
	
	/**
	 * Get the additional device information (location, parameters, etc.)
	 * 
	 * @param factory
	 *            the {@link ObjectFactory} needed to build JAXB objects
	 * @param device
	 *            the {@link Device} for which get further information
	 */
	private void getDeviceInfo(ObjectFactory factory, Device device)
	{
		// set the device name
		String devName = device.getId();
		
		// get isIn
		String isIn = this.cModel.getIsIn(devName);
		if (isIn != null)
			device.setIsIn(isIn);
		
		// get the gateway
		String hasGateway = this.cModel.getHasGateway(devName);
		if (hasGateway != null)
			device.setGateway(hasGateway);
		
		// get the meter
		String hasMeter = this.cModel.getHasMeter(devName);
		if (hasMeter != null)
			device.setHasMeter(hasMeter);
		
		// get meterOf
		Set<String> meterOf = this.cModel.getMeterOf(devName);
		if (!meterOf.isEmpty())
			device.getMeterOf().addAll(meterOf);
		
		// get sensorOf
		String sensorOf = this.cModel.getSensorOf(devName);
		if (sensorOf != null)
			device.setSensorOf(sensorOf);
		
		// get plugged in
		String pluggedIn = this.cModel.getPluggedIn(devName);
		if (pluggedIn != null)
			device.setPluggedIn(pluggedIn);
		
		// get the device description
		String devDescription = this.cModel.getDeviceDescription(devName);
		if (devDescription != null)
			device.setDescription(devDescription);
		else
			device.setDescription("A " + device.getClazz() + " instance named " + device.getId());
		
		// get the device parameters
		Map<String, Set<String>> deviceParams = this.cModel.getDeviceParameters(devName);
		if (!deviceParams.isEmpty())
		{
			for (String paramName : deviceParams.keySet())
			{
				for (String value : deviceParams.get(paramName))
				{
					Configparam parameter = this.createConfigParams(factory, paramName, value);
					device.getParam().add(parameter);
				}
			}
		}
		
		// get actuatorOf
		String actuatorOf = this.cModel.getActuatorOf(devName);
		if (actuatorOf != null)
			device.setActuatorOf(actuatorOf);
		
		// get controlledObject
		Set<String> controlledObject = this.cModel.getControlledObject(devName);
		if (!controlledObject.isEmpty())
		{
			device.getControls().addAll(controlledObject);
		}
		
		// get states, commands, and notifications
		this.getSpecificStateInformation(factory, device);
		this.getSpecificCommandInformation(factory, device);
		this.getSpecificNotificationInformation(factory, device);
	}
	
	/**
	 * Get the state information.
	 * 
	 * @param factory
	 *            the {@link ObjectFactory} needed to build JAXB objects
	 * @param device
	 *            the {@link Device} for which get the states
	 */
	private void getSpecificStateInformation(ObjectFactory factory, Device device)
	{
		// set the device name
		String devName = device.getId();
		
		// get all states for the given device
		Map<String, String> deviceStates = this.cModel.getAllStates(devName);
		
		if (!deviceStates.isEmpty())
		{
			for (String state : deviceStates.keySet())
			{
				Configstate stateElement = factory.createConfigstate();
				stateElement.setClazz(state);
				
				// get all state values
				Map<String, String> allStateValues = this.cModel.getAllStateValues(deviceStates.get(state));
				
				if (!allStateValues.isEmpty())
				{
					Statevalues stateValues = factory.createStatevalues();
					for (String stateValue : allStateValues.keySet())
					{
						Statevalue statevalueElement = factory.createStatevalue();
						statevalueElement.setClazz(stateValue);
						statevalueElement.setName(allStateValues.get(stateValue));
						stateValues.getStatevalue().add(statevalueElement);
					}
					// add the state value
					stateElement.setStatevalues(stateValues);
				}
				// add the state
				device.getState().add(stateElement);
			}
			
		}
	}
	
	/**
	 * Get the command-related information.
	 * 
	 * @param factory
	 *            the {@link ObjectFactory} needed to build JAXB objects
	 * @param device
	 *            the {@link Device} for which get the commands
	 */
	private void getSpecificCommandInformation(ObjectFactory factory, Device device)
	{
		// set the device name
		String devName = device.getId();
		
		// get all control and query functionalities
		Map<String, Set<String>> functionalities = this.cModel.getAllControlQueryFunctionalities(devName);
		
		if (functionalities != null)
		{
			for (String functionality : functionalities.keySet())
			{
				ControlFunctionality controlFunctionality = factory.createControlFunctionality();
				controlFunctionality.setClazz(functionality);
				
				Set<String> associatedCommands = functionalities.get(functionality);
				Commands commands = null;
				if (associatedCommands != null && !associatedCommands.isEmpty())
				{
					commands = factory.createCommands();
					
					for (String associatedCommand : associatedCommands)
					{
						Configcommand configCmd = factory.createConfigcommand();
						configCmd.setName(associatedCommand);
						configCmd.setId(associatedCommand);
						// get single command class
						String commandClass = this.cModel.getSubFunctionalityClass(associatedCommand);
						if (commandClass != null)
							configCmd.setClazz(commandClass);
						
						// set additional properties
						Map<String, Set<String>> properties = this.cModel.getParameters(associatedCommand,
								commandClass);
						if (properties != null && !properties.isEmpty())
						{
							for (String property : properties.keySet())
							{
								Set<String> params = properties.get(property);
								for (String param : params)
								{
									Configparam parameter = this.createConfigParams(factory, property, param);
									configCmd.getParam().add(parameter);
								}
							}
						}
						// store the command
						commands.getCommand().add(configCmd);
					}
				}
				if (commands != null)
				{
					// add the commands and the relative functionality
					controlFunctionality.setCommands(commands);
					device.getControlFunctionality().add(controlFunctionality);
				}
			}
		}
		
	}
	/**
	 * Get the notification information.
	 * 
	 * @param factory
	 *            the {@link ObjectFactory} needed to build JAXB objects
	 * @param device
	 *            the {@link Device} for which get the notifications
	 */
	private void getSpecificNotificationInformation(ObjectFactory factory, Device device)
	{
		// set the device name
		String devName = device.getId();
		
		// get the notification functionalities
		Map<String, Set<String>> functionalities = this.cModel.getAllNotificationFunctionalities(devName);
		
		if (functionalities != null)
		{
			for (String functionality : functionalities.keySet())
			{
				NotificationFunctionality notificationFunctionality = factory.createNotificationFunctionality();
				notificationFunctionality.setClazz(functionality);
				
				Set<String> associatedNotifications = functionalities.get(functionality);
				Notifications notifications = null;
				if (associatedNotifications != null && !associatedNotifications.isEmpty())
				{
					notifications = factory.createNotifications();
					
					for (String associatedNotification : associatedNotifications)
					{
						Confignotification configNotification = factory.createConfignotification();
						configNotification.setId(associatedNotification);
						// get single notification class
						String notificationClass = this.cModel.getSubFunctionalityClass(associatedNotification);
						if (notificationClass != null)
							configNotification.setClazz(notificationClass);
						
						// set additional properties
						Map<String, Set<String>> properties = this.cModel.getParameters(associatedNotification,
								notificationClass);
						if (properties != null && !properties.isEmpty())
						{
							for (String property : properties.keySet())
							{
								Set<String> params = properties.get(property);
								for (String param : params)
								{
									Configparam parameter = this.createConfigParams(factory, property, param);
									configNotification.getParam().add(parameter);
								}
							}
						}
						// store the notification
						notifications.getNotification().add(configNotification);
					}
				}
				if (notifications != null)
				{
					// add the notifications and the relative functionality
					notificationFunctionality.setNotifications(notifications);
					device.getNotificationFunctionality().add(notificationFunctionality);
				}
			}
		}
	}
	
	/**
	 * Create the {@link Configparam} in the proper way.
	 * 
	 * @param factory
	 *            the {@link ObjectFactory} needed to build JAXB objects
	 * @param paramName
	 *            the param name as extracted from the ontology
	 * @param paramValue
	 *            the param value as extracted from the ontology
	 * @return a {@link Configparam}
	 */
	private Configparam createConfigParams(ObjectFactory factory, String paramName, String paramValue)
	{
		Configparam parameter = factory.createConfigparam();
		parameter.setName(paramName);
		String valueType[] = paramValue.split("\\^\\^");
		parameter.setValue(valueType[0]);
		if (valueType.length == 2)
		{
			parameter.setType(valueType[1]);
		}
		
		return parameter;
	}
	
}
