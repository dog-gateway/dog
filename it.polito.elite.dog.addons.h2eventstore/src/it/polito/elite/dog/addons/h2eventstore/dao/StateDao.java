package it.polito.elite.dog.addons.h2eventstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

import it.polito.elite.dog.addons.h2eventstore.db.H2Storage;
import it.polito.elite.dog.addons.storage.EventDataPoint;
import it.polito.elite.dog.addons.storage.EventDataStream;
import it.polito.elite.dog.addons.storage.EventDataStreamSet;
import it.polito.elite.dog.core.library.util.LogHelper;

public class StateDao
{

	// OSGi logger
	private LogHelper logger;

	// the device dao
	private DeviceDao devDao;

	// The storage layer
	private H2Storage storage;

	// ---- TABLE NAMES
	private final String continuousStateTableName = "ContinuousState";
	private final String discreteStateTableName = "DiscreteState";

	// ---- TABLE STRUCTURE
	private final String continuousStateTableCreateQuery = "CREATE TABLE "
			+ this.continuousStateTableName
			+ "(id int(11) NOT NULL AUTO_INCREMENT, timestamp TIMESTAMP, unit VARCHAR(5), "
			+ "value DOUBLE, name VARCHAR(100), params VARCHAR(255), deviceuri VARCHAR(255), "
			+ "PRIMARY KEY(id), FOREIGN KEY (deviceuri) REFERENCES Device(uri) ON DELETE CASCADE);";
	private final String discreteStateTableCreateQuery = "CREATE TABLE "
			+ this.discreteStateTableName
			+ "(id int(11) NOT NULL AUTO_INCREMENT, timestamp TIMESTAMP, value VARCHAR(100), "
			+ "name VARCHAR(255), deviceuri VARCHAR(255), PRIMARY KEY(id), "
			+ "FOREIGN KEY (deviceuri) REFERENCES Device(uri) ON DELETE CASCADE);";

	// --------- commonly used statements ------------
	private PreparedStatement insertContinuousStateStmt;
	private PreparedStatement insertDiscreteStateStmt;

	// ---- INSERTION QUERIES
	private final String insertContinuousStateQuery = "INSERT INTO "
			+ this.continuousStateTableName
			+ "(timestamp, unit, value, name, params, deviceuri) VALUES (?,?,?,?,?,?)";
	private final String insertDiscreteStateQuery = "INSERT INTO "
			+ this.discreteStateTableName
			+ "(timestamp, value, name, deviceuri) VALUES (?,?,?,?);";

	public StateDao(final DeviceDao devDao, final H2Storage storage,
			final BundleContext context)
	{
		// init logger
		this.logger = new LogHelper(context);

		// store the connection
		this.storage = storage;

		// store the device DAO instance
		this.devDao = devDao;

		// check and create tables if needed
		this.checkAndCreateTables();

		// prepare the commonly executed statements
		this.prepareCommonStatements();
	}

	private void checkAndCreateTables()
	{
		try
		{
			// check if the ContinuousState table exist
			ResultSet tableSet = this.storage
					.getConnection()
					.getMetaData()
					.getTables(this.storage.getConnection().getCatalog(), null,
							this.continuousStateTableName.toUpperCase(), null);

			if (!tableSet.next())
			{
				// missing event table: create it
				this.storage.getConnection()
						.prepareStatement(this.continuousStateTableCreateQuery)
						.executeUpdate();
				this.logger.log(LogService.LOG_INFO,
						"Schema creation has been successful!");
			}

			tableSet.close();

			// check if the DiscreteState table exist
			tableSet = this.storage
					.getConnection()
					.getMetaData()
					.getTables(this.storage.getConnection().getCatalog(), null,
							this.discreteStateTableName.toUpperCase(), null);

			if (!tableSet.next())
			{
				// missing event table: create it
				this.storage.getConnection()
						.prepareStatement(this.discreteStateTableCreateQuery)
						.executeUpdate();
				this.logger.log(LogService.LOG_INFO,
						"Schema creation has been successful!");
			}

			tableSet.close();
		}
		catch (SQLException e)
		{
			// Log the error
			this.logger.log(LogService.LOG_ERROR,
					"Unable to check / create state db tables");
		}
	}

	/**
	 * Prepares commonly used statements to improve db operation performance.
	 */
	private void prepareCommonStatements()
	{
		// prepare commonly used statements to improve statement execution
		// performance
		try
		{

			this.insertContinuousStateStmt = this.storage.getConnection()
					.prepareStatement(this.insertContinuousStateQuery);

			this.insertDiscreteStateStmt = this.storage.getConnection()
					.prepareStatement(this.insertDiscreteStateQuery);

		}
		catch (SQLException e)
		{
			// Log the error
			this.logger.log(LogService.LOG_ERROR,
					"Unable to create commonly used prepared statements");
		}

	}

	public boolean close()
	{
		boolean isClosed = false;
		// close db connection
		try
		{
			this.insertContinuousStateStmt.close();
			this.insertDiscreteStateStmt.close();
			isClosed = true;
		}
		catch (SQLException e)
		{
			// Log the error
			this.logger.log(LogService.LOG_ERROR,
					"Unable to create commonly used prepared statements");
		}

		return isClosed;
	}

	public boolean insertContinuousState(String deviceURI, Date eventTimestamp,
			Measure<?, ?> eventValue, String stateName, String stateParams)
	{
		boolean inserted = false;

		try
		{

			if (!this.devDao.isDevicePresent(deviceURI))
				this.devDao.insertDevice(deviceURI);

			// Insert the real event in the right table

			// fill the prepared statement
			this.insertContinuousStateStmt.setTimestamp(1, new Timestamp(
					eventTimestamp.getTime()));
			DecimalMeasure<? extends Quantity> measure = DecimalMeasure
					.valueOf(eventValue.toString());
			this.insertContinuousStateStmt.setString(2, eventValue.getUnit()
					.toString());
			this.insertContinuousStateStmt.setDouble(3, measure.getValue()
					.doubleValue());
			this.insertContinuousStateStmt.setString(4, stateName);
			this.insertContinuousStateStmt.setString(5, stateParams);
			this.insertContinuousStateStmt.setString(6, deviceURI);

			// execute the insert query
			this.insertContinuousStateStmt.executeUpdate();
			this.storage.getConnection().commit();

			// turn the insertion flag to true
			inserted = true;
		}
		catch (SQLException e)
		{
			// log the error
			this.logger.log(LogService.LOG_ERROR,
					"Error while storing event data", e);
		}

		return inserted;
	}

	public boolean insertDiscreteState(String deviceURI, Date eventTimestamp,
			String eventValue, String name)
	{
		boolean inserted = false;

		try
		{
			// check if the device is already available
			if (!this.devDao.isDevicePresent(deviceURI))
				this.devDao.insertDevice(deviceURI);

			// Insert the real event in the right table

			// fill the prepared statement
			this.insertDiscreteStateStmt.setTimestamp(1, new Timestamp(
					eventTimestamp.getTime()));
			this.insertDiscreteStateStmt.setString(2, eventValue);
			this.insertDiscreteStateStmt.setString(3, name);
			this.insertDiscreteStateStmt.setString(4, deviceURI);

			// execute the insert query
			this.insertDiscreteStateStmt.executeUpdate();
			this.storage.getConnection().commit();

			// turn the insertion flag to true
			inserted = true;
		}
		catch (SQLException e)
		{
			// log the error
			this.logger.log(LogService.LOG_ERROR,
					"Error while storing event data", e);
		}

		return inserted;
	}

	/**
	 * Gets all the events carrying a measure generated by a given device in the
	 * time frame between startDate and endDate using pagination
	 * 
	 * @param deviceURI
	 *            the deviceURI as a{@link String}
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @param startCount
	 *            the starting count
	 * @param nResults
	 *            the number of results to provide back
	 */

	public EventDataStreamSet getAllDeviceContinuousStates(String deviceUri,
			Date startDate, Date endDate, int startCount, int nResults)
	{
		// the event data stream set to return
		EventDataStreamSet streamSet = new EventDataStreamSet();

		// the select query
		String allRealEventsQuery = "SELECT * FROM "
				+ this.continuousStateTableName
				+ " WHERE deviceuri=? AND timestamp>=? and timestamp<=? ORDER BY name,params ASC LIMIT ? OFFSET ?;";

		// the select statement
		try
		{
			// prepare the select statement
			PreparedStatement allRealEventsStmt = this.storage.getConnection()
					.prepareStatement(allRealEventsQuery);

			// fill the statement data
			allRealEventsStmt.setString(1, deviceUri);
			Timestamp startTimestamp = new Timestamp(startDate.getTime());
			allRealEventsStmt.setTimestamp(2, startTimestamp);
			Timestamp endTimestamp = new Timestamp(endDate.getTime());
			allRealEventsStmt.setTimestamp(3, endTimestamp);
			allRealEventsStmt.setInt(4, nResults);
			allRealEventsStmt.setInt(5, startCount);

			// exec the query
			ResultSet result = allRealEventsStmt.executeQuery();

			// compose the EventStreamDataSet
			String currentName = "";
			String currentParams = "";
			String previousName = "";
			String previousParams = "";

			EventDataStream currentStream = null;
			EventDataPoint currentPoint = null;

			while (result.next())
			{
				currentName = result.getString("name");
				currentParams = result.getString("params");

				// check if a new event stream should be created
				if (((currentName.equals(previousName)) && (!currentParams
						.equals(previousParams)))
						|| (!currentName.equals(previousName)))
				{
					// check if stream exists
					currentStream = new EventDataStream(currentName,
							currentParams, deviceUri);

					// add the stream to the event set
					streamSet.addDatastream(currentStream);

					// update the previous values
					previousName = currentName;
					previousParams = currentParams;
				}

				// create the single event data
				currentPoint = new EventDataPoint(new Date(result.getTimestamp(
						"timestamp").getTime()),
						"" + result.getDouble("value"),
						result.getString("unit"));

				// store the event data
				currentStream.addDatapoint(currentPoint);
			}
		}
		catch (SQLException e)
		{
			// log the error
			this.logger.log(LogService.LOG_ERROR,
					"Unable to retrieve sensor events carrying measures", e);
		}

		return streamSet;

	}

	/**
	 * Gets all the events carrying a discrete value generated by a given device
	 * in the time frame between startDate and endDate using pagination
	 * 
	 * @param deviceURI
	 *            the deviceURI as a{@link String}
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @param startCount
	 *            the starting count
	 * @param nResults
	 *            the number of results to provide back
	 */

	public EventDataStreamSet getAllDeviceDiscreteStates(String deviceUri,
			Date startDate, Date endDate, int startCount, int nResults,
			boolean aggregated)
	{

		// the event data stream set to return
		EventDataStreamSet streamSet = new EventDataStreamSet();

		// the select query
		String allRealEventsQuery = "SELECT * FROM "
				+ this.discreteStateTableName
				+ " WHERE deviceuri=? AND timestamp>=? and timestamp<=?";
		if (aggregated)
			allRealEventsQuery = allRealEventsQuery
					+ " ORDER BY timestamp ASC LIMIT ? OFFSET ?;";
		else
			allRealEventsQuery = allRealEventsQuery
					+ " ORDER BY name,timestamp ASC LIMIT ? OFFSET ?;";

		// the select statement
		try
		{
			// prepare the select statement
			PreparedStatement allRealEventsStmt = this.storage.getConnection()
					.prepareStatement(allRealEventsQuery);

			// fill the statement data
			allRealEventsStmt.setString(1, deviceUri);
			Timestamp startTimestamp = new Timestamp(startDate.getTime());
			allRealEventsStmt.setTimestamp(2, startTimestamp);
			Timestamp endTimestamp = new Timestamp(endDate.getTime());
			allRealEventsStmt.setTimestamp(3, endTimestamp);
			allRealEventsStmt.setInt(4, nResults);
			allRealEventsStmt.setInt(5, startCount);

			// exec the query
			ResultSet result = allRealEventsStmt.executeQuery();

			// compose the EventStreamDataSet
			String currentName = "";
			String previousName = "";

			EventDataStream currentStream = null;
			EventDataPoint currentPoint = null;

			while (result.next())
			{
				if (aggregated)
					currentName = "events";
				else
					currentName = result.getString("name");

				// check if a new event stream should be created
				if (!currentName.equals(previousName))
				{
					// check if stream exists
					currentStream = new EventDataStream(currentName, "",
							deviceUri);

					// add the stream to the event set
					streamSet.addDatastream(currentStream);

					// update the previous values only if not aggregated
					previousName = currentName;
				}

				// create the single event data
				currentPoint = new EventDataPoint(new Date(result.getTimestamp(
						"timestamp").getTime()), result.getString("value"), "");

				// store the event data
				currentStream.addDatapoint(currentPoint);
			}
		}
		catch (SQLException e)
		{
			// log the error
			this.logger.log(LogService.LOG_ERROR,
					"Unable to retrieve sensor events", e);
		}

		return streamSet;

	}

	/**
	 * Gets all the events corresponding to the given notification (continuous)
	 * including any restricting parameter, e.g., phaseId=1, in the time frame
	 * between startDate and endDate, using pagination.
	 * 
	 * @param deviceURI
	 *            The deviceURI as a{@link String}
	 * @param notificationName
	 *            The name of the notification for which events must be
	 *            retrieved
	 * @param notificationParams
	 *            The parameter values needed to further specify which
	 *            notification must be matched, in a post-like encoding
	 *            <code>name1=value1&name2=value2&...</code>;
	 * @param startDate
	 *            The start date.
	 * @param endDate
	 *            The end date.
	 * @param startCount
	 *            The starting count
	 * @param nResults
	 *            The number of results to provide back
	 */

	public EventDataStream getSpecificDeviceContinuousStates(String deviceURI,
			String notificationName, String notificationParams, Date startDate,
			Date endDate, int startCount, int nResults)
	{
		// The event stream to return
		EventDataStream stream = new EventDataStream(notificationName,
				notificationParams, deviceURI);

		// the select query
		// the select query
		String realEventsQuery = "SELECT * FROM "
				+ this.continuousStateTableName
				+ " WHERE deviceuri=? AND name=? AND params=? AND timestamp>=? and timestamp<=? ORDER BY name,params LIMIT ? OFFSET ?;";

		// the select statement
		try
		{
			// prepare the select statement
			PreparedStatement realEventsStmt = this.storage.getConnection()
					.prepareStatement(realEventsQuery);

			// fill the statement data
			realEventsStmt.setString(1, deviceURI);
			realEventsStmt.setString(2, notificationName);
			realEventsStmt.setString(3, notificationParams);
			Timestamp startTimestamp = new Timestamp(startDate.getTime());
			realEventsStmt.setTimestamp(4, startTimestamp);
			Timestamp endTimestamp = new Timestamp(endDate.getTime());
			realEventsStmt.setTimestamp(5, endTimestamp);
			realEventsStmt.setInt(6, nResults);
			realEventsStmt.setInt(7, startCount);

			// exec the query
			ResultSet result = realEventsStmt.executeQuery();

			// the current data point
			EventDataPoint currentPoint = null;

			while (result.next())
			{
				currentPoint = new EventDataPoint(new Date(result.getTimestamp(
						"timestamp").getTime()),
						"" + result.getDouble("value"),
						result.getString("unit"));

				stream.addDatapoint(currentPoint);
			}

		}
		catch (SQLException e)
		{
			// log the error
			this.logger.log(LogService.LOG_ERROR,
					"Unable to retrieve sensor data", e);
		}

		return stream;
	}

	/**
	 * Gets all the events corresponding to the given notification (discrete)
	 * with no parameters in the time frame between startDate and endDate, using
	 * pagination.
	 * 
	 * @param deviceURI
	 *            The deviceURI as a{@link String}
	 * @param stateName
	 *            The name of the notification for which events must be
	 *            retrieved
	 * @param notificationParams
	 *            The parameter values needed to further specify which
	 *            notification must be matched, in a post-like encoding
	 *            <code>name1=value1&name2=value2&...</code>;
	 * @param startDate
	 *            The start date.
	 * @param endDate
	 *            The end date.
	 * @param startCount
	 *            The starting count
	 * @param nResults
	 *            The number of results to provide back
	 */

	public EventDataStream getSpecificDeviceDiscreteStates(String deviceURI,
			String stateName, Date startDate, Date endDate, int startCount,
			int nResults)
	{
		EventDataStream stream = new EventDataStream(stateName, "", deviceURI);

		// the select query
		// the select query
		String realEventsQuery = "SELECT * FROM "
				+ this.discreteStateTableName
				+ " WHERE deviceuri=? AND name=? AND timestamp>=? and timestamp<=? ORDER BY name LIMIT ? OFFSET ?;";

		// the select statement
		try
		{
			// prepare the select statement
			PreparedStatement realEventsStmt = this.storage.getConnection()
					.prepareStatement(realEventsQuery);

			// fill the statement data
			realEventsStmt.setString(1, deviceURI);
			realEventsStmt.setString(2, stateName);
			Timestamp startTimestamp = new Timestamp(startDate.getTime());
			realEventsStmt.setTimestamp(3, startTimestamp);
			Timestamp endTimestamp = new Timestamp(endDate.getTime());
			realEventsStmt.setTimestamp(4, endTimestamp);
			realEventsStmt.setInt(5, nResults);
			realEventsStmt.setInt(6, startCount);

			// exec the query
			ResultSet result = realEventsStmt.executeQuery();

			// the current data point
			EventDataPoint currentPoint = null;

			while (result.next())
			{
				currentPoint = new EventDataPoint(new Date(result.getTimestamp(
						"timestamp").getTime()), result.getString("value"), "");

				stream.addDatapoint(currentPoint);
			}

		}
		catch (SQLException e)
		{
			// log the error
			this.logger.log(LogService.LOG_ERROR,
					"Unable to retrieve sensor data", e);
		}

		return stream;
	}

	public void insertContinuousStates(EventDataStreamSet stateSet)
	{
		// iterate over the stream sets
		for (EventDataStream currentStream : stateSet.getDatastreams())
		{
			try
			{
				// the insert counter
				int i = 0;

				// iterate over the data points
				for (EventDataPoint currentDataPoint : currentStream
						.getDatapoints())
				{
					if (i % H2Storage.MAX_BATCH_SIZE == 0)
					{
						// exclude the first time
						if (i > 0)
						{
							// execute the insertion batch
							// TODO: check if synchronization is required
							// (should be carried by the db)
							this.storage.getConnection().setAutoCommit(false);
							this.insertContinuousStateStmt.executeBatch();
							this.storage.getConnection().setAutoCommit(true);
						}
					}

					// add the notification to the batch
					// fill the prepared statement
					this.insertContinuousStateStmt.setTimestamp(1,
							new Timestamp(currentDataPoint.getAt().getTime()));
					this.insertContinuousStateStmt.setString(2,
							currentDataPoint.getUnit());
					this.insertContinuousStateStmt.setDouble(3,
							Double.valueOf(currentDataPoint.getValue()));
					this.insertContinuousStateStmt.setString(4,
							currentStream.getName());
					this.insertContinuousStateStmt.setString(5,
							currentStream.getParameters());
					this.insertContinuousStateStmt.setString(6,
							currentStream.getDeviceUri());

					// execute the insert query
					this.insertContinuousStateStmt.addBatch();
					// increments the insert counter
					i++;
				}

				// execute the remaining batch
				if (i % H2Storage.MAX_BATCH_SIZE > 0)
				{
					this.storage.getConnection().setAutoCommit(false);
					this.insertContinuousStateStmt.executeBatch();
					this.storage.getConnection().setAutoCommit(true);
				}
			}
			catch (SQLException e)
			{
				this.logger.log(LogService.LOG_ERROR,
						"Unable to store event stream of parametric notifications for the device: "
								+ currentStream.getDeviceUri(), e);
			}
		}
	}

	public void insertDiscreteStates(EventDataStreamSet stateSet)
	{
		// iterate over the stream sets
		for (EventDataStream currentStream : stateSet.getDatastreams())
		{
			try
			{
				// the insert counter
				int i = 0;

				// iterate over the data points
				for (EventDataPoint currentDataPoint : currentStream
						.getDatapoints())
				{
					if (i % H2Storage.MAX_BATCH_SIZE == 0)
					{
						// exclude the first time
						if (i > 0)
						{
							// execute the insertion batch
							// TODO: check if synchronization is required
							// (should be carried by the db)
							this.storage.getConnection().setAutoCommit(false);
							this.insertDiscreteStateStmt.executeBatch();
							this.storage.getConnection().setAutoCommit(true);
						}
					}

					// add the notification to the batch
					// fill the prepared statement
					this.insertDiscreteStateStmt.setTimestamp(1, new Timestamp(
							currentDataPoint.getAt().getTime()));
					this.insertDiscreteStateStmt.setString(2,
							currentDataPoint.getValue());
					this.insertDiscreteStateStmt.setString(3,
							currentStream.getName());
					this.insertDiscreteStateStmt.setString(4,
							currentStream.getDeviceUri());

					// execute the insert query
					this.insertDiscreteStateStmt.addBatch();
					// increments the insert counter
					i++;
				}

				// execute the remaining batch
				if (i % H2Storage.MAX_BATCH_SIZE > 0)
				{
					this.storage.getConnection().setAutoCommit(false);
					this.insertDiscreteStateStmt.executeBatch();
					this.storage.getConnection().setAutoCommit(true);
				}
			}
			catch (SQLException e)
			{
				this.logger.log(LogService.LOG_ERROR,
						"Unable to store event stream of non parametric notifications for the device: "
								+ currentStream.getDeviceUri(), e);
			}
		}
	}
}
