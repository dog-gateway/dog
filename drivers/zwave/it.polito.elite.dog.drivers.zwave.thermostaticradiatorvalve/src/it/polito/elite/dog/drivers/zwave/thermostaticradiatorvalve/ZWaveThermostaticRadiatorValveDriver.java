/*
 * Dog  - Z-Wave
 * 
 * Copyright 2013 Dario Bonino 
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
package it.polito.elite.dog.drivers.zwave.thermostaticradiatorvalve;

import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.notification.core.ClockTimeNotification;
import it.polito.elite.dog.drivers.zwave.device.ZWaveDeviceDriver;
import it.polito.elite.dog.drivers.zwave.network.ZWaveDriverInstance;
import it.polito.elite.dog.drivers.zwave.network.info.ZWaveInfo;
import it.polito.elite.dog.drivers.zwave.network.interfaces.ZWaveNetwork;

import java.io.File;
import java.util.Dictionary;
import java.util.HashSet;

import org.osgi.framework.BundleContext;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

/**
 * @author bonino
 * 
 */
public class ZWaveThermostaticRadiatorValveDriver extends ZWaveDeviceDriver implements EventHandler
{

	// the persistence store associated to this driver
	private String persistenceStoreDirectory;
		
	/**
	 * 
	 */
	public ZWaveThermostaticRadiatorValveDriver()
	{
		super();
		this.driverInstanceClass = ZWaveThermostaticRadiatorValveInstance.class;
	}

	@Override
	public ZWaveDriverInstance createZWaveDriverInstance(
			ZWaveNetwork zWaveNetwork, ControllableDevice device, int nodeId,
			HashSet<Integer> instancesId, int gatewayNodeId,
			int updateTimeMillis, BundleContext context)
	{
		return new ZWaveThermostaticRadiatorValveInstance(zWaveNetwork, device, nodeId, instancesId, gatewayNodeId, updateTimeMillis, this.persistenceStoreDirectory, context);
	}

	@Override
	public void updated(Dictionary<String, ?> properties)
			throws ConfigurationException
	{
		if (properties != null)
		{
			// try to get the persistence store directory
			String persistenceDir = (String) properties
					.get(ZWaveInfo.PROPERTY_PERSISTENT_STORE);

			// trim leading and trailing spaces
			persistenceDir = persistenceDir.trim();

			// check not null
			if (persistenceDir != null)
			{
				// parse the string
				this.persistenceStoreDirectory = persistenceDir;

				// check absolute vs relative
				File persistenceStoreDir = new File(
						this.persistenceStoreDirectory);
				if (!persistenceStoreDir.isAbsolute())
					this.persistenceStoreDirectory = System
							.getProperty("configFolder")
							+ "/"
							+ this.persistenceStoreDirectory;
			}

			// call the normal updated method
			super.updated(properties);
		}
	}

	@Override
	public void handleEvent(Event event)
	{
		// check that the received event is a clock notification
		final Object eventContent = event.getProperty(EventConstants.EVENT);

		if (eventContent instanceof ClockTimeNotification)
		{
			// handle the clock notification asynchronously?
			Runnable clockNotificationTask = new Runnable()
			{

				@Override
				public void run()
				{
					synchronized (managedInstances)
					{
						// dispatch the event content
						for (ZWaveDriverInstance driverInstance : managedInstances.values())
						{
							((ZWaveThermostaticRadiatorValveInstance)driverInstance)
									.checkTime(((ClockTimeNotification) eventContent)
											.getClockTick());
						}
					}

				}
			};

			// create an execution thread for event handling
			Thread taskRunner = new Thread(clockNotificationTask);

			// start the task
			taskRunner.start();

		}

	}



}
