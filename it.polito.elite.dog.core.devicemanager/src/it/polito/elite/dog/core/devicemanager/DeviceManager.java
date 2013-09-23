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

import it.polito.elite.dog.core.devicemanager.util.DriverLoader;
import it.polito.elite.dog.core.devicemanager.util.DriverMatcher;
import it.polito.elite.dog.core.devicemanager.util.Util;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkEvent;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.device.Constants;
import org.osgi.service.device.Device;
import org.osgi.service.device.Driver;
import org.osgi.service.device.DriverLocator;
import org.osgi.service.device.DriverSelector;
import org.osgi.service.device.Match;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * This class represents the implementation of the device access specification.
 * It is based on version 1.1 of the spec.
 * 
 * 
 * @author <a href="mailto:dev@felix.apache.org">Felix Project Team</a>
 *         (original version)
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a> (successive
 *         modifications)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class DeviceManager implements Log, ServiceTrackerCustomizer<Object, Object>
{
	
	private final long DEFAULT_TIMEOUT_SEC = 1;
	
	// the logger
	private volatile LogHelper logger;
	
	// the bundle context
	private BundleContext bundleContext;
	
	// the driver selector
	private volatile DriverSelector driverSelector;
	
	// the driver locators
	private List<DriverLocator> driverLocators;
	
	// the devices
	private Map<ServiceReference<Object>, Object> devices;
	
	// the drivers
	private Map<ServiceReference<Object>, DriverAttributes> drivers;
	
	// performs all the background actions
	private ExecutorService worker;
	
	// used to add delayed actions
	private ScheduledExecutorService delayed;
	
	// the devices filter
	private Filter deviceImplFilter;
	
	// the drivers filter
	private Filter driverImplFilter;
	
	/**
	 * Public constructor. Used by the Activator in this <code>Bundle</code> to
	 * instantiate one instance.
	 * 
	 * @param context
	 *            the <code>BundleContext</code>
	 */
	public DeviceManager()
	{
		try
		{
			this.init();
		}
		catch (InvalidSyntaxException e)
		{
			// the logger is still null at this point...
			System.err.println("Exception during the service tracker creation: " + e);
		}
	}
	
	public void activate(BundleContext context)
	{
		this.bundleContext = context;
		
		this.logger = new LogHelper(context);
		
		this.start();
		
		ServiceTracker<Object, Object> devTracker = new ServiceTracker<Object, Object>(context, this.deviceImplFilter,
				this);
		devTracker.open();
		ServiceTracker<Object, Object> driverTracker = new ServiceTracker<Object, Object>(context, this.driverImplFilter,
				this);
		driverTracker.open();
		
	}
	
	public void debug(String message)
	{
		if (this.logger != null)
			this.logger.log(LogService.LOG_DEBUG, message);
	}
	
	public void info(String message)
	{
		if (this.logger != null)
			this.logger.log(LogService.LOG_INFO, message);
	}
	
	public void warning(String message)
	{
		if (this.logger != null)
			this.logger.log(LogService.LOG_WARNING, message);
	}
	
	public void error(String message, Throwable e)
	{
		System.err.println(message);
		if (e != null)
		{
			e.printStackTrace();
		}
		if (this.logger != null)
			this.logger.log(LogService.LOG_ERROR, message, e);
	}
	
	private void init() throws InvalidSyntaxException
	{
		this.driverLocators = Collections.synchronizedList(new ArrayList<DriverLocator>());
		this.worker = Executors.newSingleThreadExecutor(new NamedThreadFactory("Device Manager"));
		this.delayed = Executors.newScheduledThreadPool(1, new NamedThreadFactory("Device Manager - delayed"));
		this.driverImplFilter = Util.createFilter("(%s=%s)", new String[] { Constants.DRIVER_ID, "*" });
		
		this.deviceImplFilter = Util.createFilter("(|(%s=%s)(&(%s=%s)(%s=%s)))", new String[] {
				org.osgi.framework.Constants.OBJECTCLASS, Device.class.getName(),
				org.osgi.framework.Constants.OBJECTCLASS, "*", Constants.DEVICE_CATEGORY, "*" });
	}
	
	private void start()
	{
		this.drivers = new ConcurrentHashMap<ServiceReference<Object>, DriverAttributes>();
		this.devices = new ConcurrentHashMap<ServiceReference<Object>, Object>();
		this.submit(new WaitForStartFramework());
	}
	
	public void stop()
	{
		// nothing to do ?
	}
	
	public void destroy()
	{
		this.worker.shutdownNow();
		this.delayed.shutdownNow();
	}
	
	// callback methods
	
	public void selectorAdded(DriverSelector selector)
	{
		this.driverSelector = selector;
		this.debug("driver selector appeared");
	}
	
	public void selectorRemoved(DriverSelector selector)
	{
		this.driverSelector = null;
		this.debug("driver selector lost");
	}
	
	public void locatorAdded(DriverLocator locator)
	{
		this.driverLocators.add(locator);
		this.debug("driver locator appeared");
	}
	
	public void locatorRemoved(DriverLocator locator)
	{
		this.driverLocators.remove(locator);
		this.debug("driver locator lost");
	}
	
	public void driverAdded(ServiceReference<Object> ref, Object obj)
	{
		final Driver driver = Driver.class.cast(obj);
		this.drivers.put(ref, new DriverAttributes(ref, driver));
		
		this.debug("driver appeared: " + Util.showDriver(ref));
		
		// immediately check for idle devices
		this.submit(new CheckForIdleDevices());
	}
	
	public void driverModified(ServiceReference<Object> ref, Object obj)
	{
		final Driver driver = Driver.class.cast(obj);
		
		this.debug("driver modified: " + Util.showDriver(ref));
		this.drivers.remove(ref);
		this.drivers.put(ref, new DriverAttributes(ref, driver));
		
		// check if devices have become idle after some time
		this.schedule(new CheckForIdleDevices());
	}
	
	public void driverRemoved(ServiceReference<Object> ref)
	{
		this.debug("driver lost: " + Util.showDriver(ref));
		this.drivers.remove(ref);
		
		// check if devices have become idle
		// after some time
		this.schedule(new CheckForIdleDevices());
		
	}
	
	public void deviceAdded(ServiceReference<Object> ref, Object device)
	{
		this.devices.put(ref, device);
		this.debug("device appeared: " + Util.showDevice(ref));
		this.submit(new DriverAttachAlgorithm(ref, device));
	}
	
	public void deviceModified(ServiceReference<Object> ref, Object device)
	{
		this.debug("device modified: " + Util.showDevice(ref));
		// nothing further to do ?
		// DeviceAttributes da = this.devices.get(ref);
		// submit(new DriverAttachAlgorithm(da));
	}
	
	public void deviceRemoved(ServiceReference<Object> ref)
	{
		this.debug("device removed: " + Util.showDevice(ref));
		this.devices.remove(ref);
		// nothing further to do ?
		// the services that use this
		// device should track it.
	}
	
	/**
	 * perform this task as soon as possible.
	 * 
	 * @param task
	 *            the task
	 */
	private void submit(Callable<Object> task)
	{
		this.worker.submit(new LoggedCall(task));
	}
	
	/**
	 * perform this task after the default delay.
	 * 
	 * @param task
	 *            the task
	 */
	private void schedule(Callable<Object> task)
	{
		this.delayed.schedule(new DelayedCall(task), DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS);
	}
	
	// worker callables
	
	/**
	 * Callable used to start the DeviceManager. It either waits (blocking the
	 * worker thread) for the framework to start, or if it has already started,
	 * returns immediately, freeing up the worker thread.
	 * 
	 * @author <a href="mailto:dev@felix.apache.org">Felix Project Team</a>
	 */
	private class WaitForStartFramework implements Callable<Object>, FrameworkListener
	{
		
		private final CountDownLatch latch = new CountDownLatch(1);
		
		public Object call() throws Exception
		{
			boolean addedAsListener = false;
			if (bundleContext.getBundle(0).getState() == Bundle.ACTIVE)
			{
				this.latch.countDown();
				debug("Starting Device Manager immediately");
			}
			else
			{
				bundleContext.addFrameworkListener(this);
				addedAsListener = true;
				debug("Waiting for framework to start");
			}
			
			this.latch.await();
			for (Map.Entry<ServiceReference<Object>, Object> entry : devices.entrySet())
			{
				submit(new DriverAttachAlgorithm(entry.getKey(), entry.getValue()));
			}
			// cleanup
			if (addedAsListener)
			{
				bundleContext.removeFrameworkListener(this);
			}
			return null;
		}
		
		// FrameworkListener method
		public void frameworkEvent(FrameworkEvent event)
		{
			switch (event.getType())
			{
				case FrameworkEvent.STARTED:
					debug("Framework has started");
					this.latch.countDown();
					break;
			}
		}
		
		@Override
		public String toString()
		{
			return getClass().getSimpleName();
		}
	}
	
	private class LoggedCall implements Callable<Object>
	{
		
		private final Callable<Object> call;
		
		public LoggedCall(Callable<Object> call)
		{
			this.call = call;
		}
		
		private String getName()
		{
			return this.call.getClass().getSimpleName();
		}
		
		public Object call() throws Exception
		{
			
			try
			{
				return this.call.call();
			}
			catch (Exception e)
			{
				error("call failed: " + getName(), e);
				throw e;
			}
			catch (Throwable e)
			{
				error("call failed: " + getName(), e);
				throw new RuntimeException(e);
			}
		}
		
	}
	
	private class DelayedCall implements Callable<Object>
	{
		
		private final Callable<Object> call;
		
		public DelayedCall(Callable<Object> call)
		{
			this.call = call;
		}
		
		private String getName()
		{
			return this.call.getClass().getSimpleName();
		}
		
		public Object call() throws Exception
		{
			info("Delayed call: " + getName());
			return worker.submit(this.call);
		}
	}
	
	/**
	 * Checks for Idle devices, and attaches them
	 * 
	 * @author <a href="mailto:dev@felix.apache.org">Felix Project Team</a>
	 */
	private class CheckForIdleDevices implements Callable<Object>
	{
		
		public Object call() throws Exception
		{
			debug("START - check for idle devices");
			for (ServiceReference<?> ref : getIdleDevices())
			{
				info("IDLE: " + ref.getBundle().getSymbolicName());
				submit(new DriverAttachAlgorithm(ref, devices.get(ref)));
			}
			
			submit(new IdleDriverUninstallAlgorithm());
			debug("STOP - check for idle devices");
			return null;
		}
		
		/**
		 * get a list of all idle devices.
		 * 
		 * @return
		 */
		private List<ServiceReference<?>> getIdleDevices()
		{
			List<ServiceReference<?>> list = new ArrayList<ServiceReference<?>>();
			
			for (ServiceReference<?> ref : devices.keySet())
			{
				info("checking if idle: " + ref.getBundle().getSymbolicName());
				
				final Bundle[] usingBundles = ref.getUsingBundles();
				for (Bundle bundle : usingBundles)
				{
					if (isDriverBundle(bundle))
					{
						info("used by driver: " + bundle.getSymbolicName());
						debug("not idle: " + ref.getBundle().getSymbolicName());
						break;
					}
					
					list.add(ref);
					
				}
			}
			return list;
		}
	}
	
	private boolean isDriverBundle(Bundle bundle)
	{
		ServiceReference<?>[] refs = bundle.getRegisteredServices();
		
		if (refs == null)
		{
			return false;
		}
		
		for (ServiceReference<?> ref : refs)
		{
			if (driverImplFilter.match(ref))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * Used to uninstall unused drivers
	 * 
	 * @author <a href="mailto:dev@felix.apache.org">Felix Project Team</a>
	 */
	private class IdleDriverUninstallAlgorithm implements Callable<Object>
	{
		
		public Object call() throws Exception
		{
			
			info("cleaning driver cache");
			for (DriverAttributes da : drivers.values())
			{
				// just call the tryUninstall; the da itself
				// will know if it should really uninstall the driver.
				try
				{
					da.tryUninstall();
				}
				catch (Exception e)
				{
					debug(da.getDriverId() + " uninstall failed");
				}
			}
			
			return null;
		}
	}
	
	private class DriverAttachAlgorithm implements Callable<Object>
	{
		
		private final ServiceReference<?> ref;
		
		private final Device device;
		
		private List<DriverAttributes> included;
		
		private List<DriverAttributes> excluded;
		
		private final DriverLoader driverLoader;
		
		private DriverAttributes finalDriver;
		
		public DriverAttachAlgorithm(ServiceReference<?> ref, Object obj)
		{
			this.ref = ref;
			if (deviceImplFilter.match(ref))
			{
				this.device = Device.class.cast(obj);
			}
			else
			{
				this.device = null;
			}
			
			this.driverLoader = new DriverLoader(DeviceManager.this, bundleContext);
		}
		
		private Dictionary<?,?> createDictionary(ServiceReference<?> ref)
		{
			final Properties p = new Properties();
			
			for (String key : ref.getPropertyKeys())
			{
				p.put(key, ref.getProperty(key));
			}
			return p;
		}
		
		public Object call() throws Exception
		{
			info("finding suitable driver for: " + Util.showDevice(this.ref));
			
			final Dictionary<?,?> dict = createDictionary(this.ref);
			
			// first create a copy of all the drivers that are already there.
			// during the process, drivers will be added, but also excluded.
			this.included = new ArrayList<DriverAttributes>(drivers.values());
			this.excluded = new ArrayList<DriverAttributes>();
			
			// first find matching driver bundles
			// if there are no driver locators
			// we'll have to do with the drivers that were
			// added 'manually'
			Set<String> driverIds = this.driverLoader.findDrivers(driverLocators, dict);
			
			// remove the driverIds that are already available
			for (DriverAttributes da : drivers.values())
			{
				driverIds.remove(da.getDriverId());
			}
			driverIds.removeAll(drivers.keySet());
			try
			{
				debug("entering attach phase for " + Util.showDevice(this.ref));
				return driverAttachment(dict, driverIds.toArray(new String[0]));
			}
			finally
			{
				// unload loaded drivers
				// that were unnecessarily loaded
				this.driverLoader.unload(this.finalDriver);
			}
		}
		
		private Object driverAttachment(Dictionary<?,?> dict, String[] driverIds) throws Exception
		{
			this.finalDriver = null;
			
			// remove the excluded drivers
			this.included.removeAll(this.excluded);
			
			// now load the drivers
			List<ServiceReference<?>> driverRefs = this.driverLoader.loadDrivers(driverLocators, driverIds);
			// these are the possible driver references that have been added
			// add them to the list of included drivers
			for (ServiceReference<?> serviceReference : driverRefs)
			{
				DriverAttributes da = drivers.get(serviceReference);
				if (da != null)
				{
					this.included.add(da);
				}
			}
			
			// now start matching all drivers
			final DriverMatcher mi = new DriverMatcher(DeviceManager.this);
			
			for (DriverAttributes driver : this.included)
			{
				try
				{
					int match = driver.match(this.ref);
					if (match <= Device.MATCH_NONE)
					{
						continue;
					}
					mi.add(match, driver);
				}
				catch (Throwable t)
				{
					error("match threw an exception", new Exception(t));
				}
			}
			
			// get the best match
			Match bestMatch = null;
			
			// local copy
			final DriverSelector selector = driverSelector;
			
			if (selector != null)
			{
				bestMatch = mi.selectBestMatch(this.ref, selector);
				if (bestMatch != null)
				{
					debug(String.format("DriverSelector (%s) found best match: %s", selector.getClass().getName(),
							Util.showDriver(bestMatch.getDriver())));
				}
			}
			
			if (bestMatch == null)
			{
				bestMatch = mi.getBestMatch();
			}
			
			if (bestMatch == null)
			{
				noDriverFound();
				// really return
				return null;
			}
			
			String driverId = String.class.cast(bestMatch.getDriver().getProperty(Constants.DRIVER_ID));
			
			debug("best match: " + driverId);
			this.finalDriver = drivers.get(bestMatch.getDriver());
			
			if (this.finalDriver == null)
			{
				error("we found a driverId, but not the corresponding driver: " + driverId, null);
				noDriverFound();
				return null;
			}
			
			// here we get serious...
			try
			{
				debug("attaching to: " + driverId);
				String newDriverId = this.finalDriver.attach(this.ref);
				if (newDriverId == null)
				{
					// successful attach
					return null;
				}
				// its a referral
				info("attach led to a referral to: " + newDriverId);
				this.excluded.add(this.finalDriver);
				return driverAttachment(dict, new String[] { newDriverId });
			}
			catch (Throwable t)
			{
				error("attach failed due to an exception", t);
			}
			this.excluded.add(this.finalDriver);
			return driverAttachment(dict, driverIds);
		}
		
		private void noDriverFound()
		{
			debug("no suitable driver found for: " + Util.showDevice(this.ref));
			if (this.device != null)
			{
				this.device.noDriverFound();
			}
		}
		
		@Override
		public String toString()
		{
			return getClass().getSimpleName();// + ": " +
			// Util.showDevice(this.ref);
		}
		
	}
	
	@Override
	public Object addingService(ServiceReference<Object> reference)
	{
		Object service = this.bundleContext.getService(reference);
		if (service instanceof Device)
			this.deviceAdded(reference, service);
		else if (service instanceof Driver)
			this.driverAdded(reference, service);
		return reference;
	}
	
	@Override
	public void modifiedService(ServiceReference<Object> reference, Object service)
	{
		Object modifiedService = this.bundleContext.getService(reference);
		if (modifiedService instanceof Device)
			this.deviceModified(reference, modifiedService);
		else if (modifiedService instanceof Driver)
			this.driverModified(reference, modifiedService);
	}
	
	@Override
	public void removedService(ServiceReference<Object> reference, Object service)
	{
		if (service instanceof Device)
			this.deviceRemoved(reference);
		else if (service instanceof Driver)
			this.driverRemoved(reference);
		
	}
	
}
