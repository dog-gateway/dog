package it.polito.elite.dog.addons.h2eventstore.test;

import it.polito.elite.dog.addons.storage.EventDataStreamSet;
import it.polito.elite.dog.addons.storage.EventStore;
import it.polito.elite.dog.addons.storage.EventStoreInfo;
import it.polito.elite.dog.core.library.model.ControllableDevice;
import it.polito.elite.dog.core.library.model.devicecategory.PelletHeater;
import it.polito.elite.dog.core.library.model.notification.CoolNotification;
import it.polito.elite.dog.core.library.model.notification.FiringUpNotification;
import it.polito.elite.dog.core.library.model.notification.HeatNotification;
import it.polito.elite.dog.core.library.model.notification.OffNotification;
import it.polito.elite.dog.core.library.model.notification.OnNotification;
import it.polito.elite.dog.core.library.model.notification.StandByNotification;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.device.Constants;
import org.osgi.service.log.LogService;

public class H2EventStoreTest
{

	// the logger
	private LogHelper logger;

	// the event store
	private AtomicReference<EventStore> theStore;

	// the creation date
	private Date creationDate;

	// the bundle context
	private BundleContext context;

	/**
	 * 
	 */
	public H2EventStoreTest()
	{
		// initialize the creation date
		this.creationDate = new Date(0);

		// intialize the atomic reference
		this.theStore = new AtomicReference<>();
	}

	/**
	 * Code performed when the bundle is activated, attaches the log service and
	 * performs the needed OSGi service house keeping.
	 * 
	 * @param context
	 */
	public void activate(BundleContext context)
	{
		// initialize the bundle context
		this.context = context;

		// initialize the logger
		this.logger = new LogHelper(this.context);

		// log the activation
		this.logger.log(LogService.LOG_DEBUG,
				"H2 Event Store Test has been activated...");

		// schedule the get data tests
		TimerTask getDataTestTask = new TimerTask()
		{

			@Override
			public void run()
			{
				getDataTest();
			}
		};

		// create the test timer
		Timer testTimer = new Timer();

		// run the test after 1 minute
		testTimer.schedule(getDataTestTask, 30000, 30000);
	}

	/**
	 * Code performed when the bundle is deactivated, detaches the log service
	 * and performs the needed OSGi service house keeping.
	 */
	public void deactivate()
	{
		// log the deativation
		this.logger.log(LogService.LOG_DEBUG,
				"H2 Event Store Test has been deactivated...");

		// detach the logger
		this.logger = null;
	}

	public void addedEventStore(EventStore store)
	{
		this.theStore.set(store);
	}

	public void removedEventStore(EventStore store)
	{
		this.theStore.compareAndSet(store, null);
	}

	protected void getDataTest()
	{
		EventStore store = this.theStore.get();

		if (store != null)
		{
			// create filter for getting the desired device
			String deviceFilter = String.format("(&(%s=*))",
					Constants.DEVICE_CATEGORY);

			// get the device service references
			ServiceReference<?>[] deviceService;
			try
			{
				deviceService = this.context.getAllServiceReferences(
						org.osgi.service.device.Device.class.getName(),
						deviceFilter);

				// only one device with the given deviceId can exists in the
				// framework
				if (deviceService.length >= 1)
				{
					for (int i = 0; i < deviceService.length; i++)
					{
						// get the OSGi service pointed by the current device
						// reference
						Object device = this.context
								.getService(deviceService[i]);

						if (device instanceof ControllableDevice)
						{
							EventDataStreamSet measures = store
									.getAllDeviceParametricNotifications(
											((ControllableDevice) device)
													.getDeviceId(),
											this.creationDate, new Date(), 0,
											EventStoreInfo.UNLIMITED_SIZE);

							// dump
							this.logger.log(LogService.LOG_DEBUG, "Measures: "
									+ measures);

							EventDataStreamSet events = store
									.getAllDeviceNonParametricNotifications(
											((ControllableDevice) device)
													.getDeviceId(),
											this.creationDate, new Date(), 0,
											EventStoreInfo.UNLIMITED_SIZE, true);

							// dump
							this.logger.log(LogService.LOG_DEBUG, "Events: "
									+ events);

							if (device instanceof PelletHeater)
							{
								// get the notifications grouped by
								// functionality
								HashSet<String> fireHeatCool = new HashSet<>();
								fireHeatCool.add(CoolNotification.class
										.getSimpleName());
								fireHeatCool.add(FiringUpNotification.class
										.getSimpleName());
								fireHeatCool.add(HeatNotification.class
										.getSimpleName());

								HashSet<String> onOffStandby = new HashSet<>();
								onOffStandby.add(OffNotification.class
										.getSimpleName());
								onOffStandby.add(OnNotification.class
										.getSimpleName());
								onOffStandby.add(StandByNotification.class
										.getSimpleName());

								Hashtable<String, Set<String>> notificationNames = new Hashtable<>();

								notificationNames.put("fireHeatCool",
										fireHeatCool);
								notificationNames.put("onOffStandby",
										onOffStandby);

								EventDataStreamSet groupedEvents = store
										.getSpecificDeviceNonParametricNotifications(
												((ControllableDevice) device)
														.getDeviceId(),
												notificationNames,
												this.creationDate, new Date(),
												0,
												EventStoreInfo.UNLIMITED_SIZE);

								// dump
								this.logger.log(LogService.LOG_DEBUG,
										"Grouped Events: " + groupedEvents);
							}
							
							EventDataStreamSet continuousStates = store
									.getAllDeviceContinuousStates(
											((ControllableDevice) device)
													.getDeviceId(),
											this.creationDate, new Date(), 0,
											EventStoreInfo.UNLIMITED_SIZE);

							// dump
							this.logger.log(LogService.LOG_DEBUG,
									"Continuous States: " + continuousStates);

							EventDataStreamSet discreteStates = store
									.getAllDeviceDiscreteStates(
											((ControllableDevice) device)
													.getDeviceId(),
											this.creationDate, new Date(), 0,
											EventStoreInfo.UNLIMITED_SIZE, true);

							// dump
							this.logger.log(LogService.LOG_DEBUG,
									"Discrete States: " + discreteStates);

						}

						this.context.ungetService(deviceService[0]);
					}
				}
			}
			catch (InvalidSyntaxException e)
			{
				this.logger.log(LogService.LOG_ERROR,
						"error while testing the H2 event store", e);
			}
		}

	}
}
