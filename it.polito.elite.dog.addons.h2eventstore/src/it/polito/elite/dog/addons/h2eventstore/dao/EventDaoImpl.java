/*
 * Dog - Addons
 * 
 * Copyright (c) 2013-2014 Claudio Degioanni, Luigi De Russis, Dario Bonino
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
package it.polito.elite.dog.addons.h2eventstore.dao;

import it.polito.elite.dog.addons.storage.EventDao;
import it.polito.elite.dog.addons.storage.EventDataPoint;
import it.polito.elite.dog.addons.storage.EventDataStream;
import it.polito.elite.dog.addons.storage.EventDataStreamSet;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.sql.Connection;
import java.sql.DriverManager;
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

public class EventDaoImpl implements EventDao
{
	// OSGi logger
	private LogHelper logger;

	// database connection
	private Connection connection;

	// --------- commonly used statements ------------
	private PreparedStatement insertMeasureStmt;
	private PreparedStatement insertEventStmt;
	private PreparedStatement insertDeviceStmt;
	private PreparedStatement selectDeviceStmt;

	// --------- commonly used queries ---------------

	// ---- INSERTION -----------
	private final String insertMeasureQuery = "INSERT INTO Measure(timestamp, unit, value, name, params, deviceuri) VALUES (?,?,?,?,?,?)";
	private final String insertEventQuery = "INSERT INTO Event(timestamp, value, name, deviceuri) VALUES (?,?,?,?);";
	private final String insertDeviceQuery = "INSERT INTO Device(uri, class, name) VALUES (?,?,?);";

	// ---- DELETION ------------

	private final String selectDeviceQuery = "SELECT * FROM Device WHERE Device.uri = ?;";

	// ---- TABLE STRUCTURE
	private final String measuresTableName = "Measure";
	private final String eventTableName = "Event";
	private final String deviceTableName = "Device";
	private final String measuresTableCreateQuery = "CREATE TABLE "
			+ this.measuresTableName
			+ "(id int(11) NOT NULL AUTO_INCREMENT, timestamp TIMESTAMP, unit VARCHAR(5), value DOUBLE, name VARCHAR(100), params VARCHAR(255), deviceuri VARCHAR(255), PRIMARY KEY(id), FOREIGN KEY (deviceuri) REFERENCES Device(uri) ON DELETE CASCADE);";
	private final String eventTableCreateQuery = "CREATE TABLE "
			+ this.eventTableName
			+ "(id int(11) NOT NULL AUTO_INCREMENT, timestamp TIMESTAMP, value VARCHAR(100), name VARCHAR(100), deviceuri VARCHAR(255), PRIMARY KEY(id), FOREIGN KEY (deviceuri) REFERENCES Device(uri) ON DELETE CASCADE);";
	private final String deviceTableCreateQuery = "CREATE TABLE "
			+ this.deviceTableName
			+ "e(uri VARCHAR(255), name VARCHAR(100), class varchar(255), PRIMARY KEY(uri));";

	public EventDaoImpl(final String url, final String user,
			final String password, final BundleContext context)
			throws SQLException
	{
		// init logger
		this.logger = new LogHelper(context);

		// open database connection
		this.connection = DriverManager.getConnection(url, user, password);

		// check and create tables if needed
		this.checkAndCreateTables();

		// prepare the commonly executed statements
		this.prepareCommonStatements();
	}

	/**
	 * Checks if the needed tables exist and if not creates them.
	 */
	private void checkAndCreateTables()
	{
		try
		{
			// check if the Device table exist
			ResultSet tableSet = connection.getMetaData().getTables(
					connection.getCatalog(), null,
					this.deviceTableName.toUpperCase(), null);

			if (!tableSet.next())
			{
				// missing event table: create it
				this.connection.prepareStatement(this.deviceTableCreateQuery)
						.executeUpdate();
				this.logger.log(LogService.LOG_INFO,
						"Schema creation has been successful!");
			}

			tableSet.close();

			// check if the Event table exist
			tableSet = connection.getMetaData().getTables(
					connection.getCatalog(), null,
					this.eventTableName.toUpperCase(), null);

			if (!tableSet.next())
			{
				// missing event table: create it
				this.connection.prepareStatement(this.eventTableCreateQuery)
						.executeUpdate();
				this.logger.log(LogService.LOG_INFO,
						"Schema creation has been successful!");
			}

			tableSet.close();

			// check if the RealEvent table exist
			tableSet = connection.getMetaData().getTables(
					connection.getCatalog(), null,
					this.measuresTableName.toUpperCase(), null);

			if (!tableSet.next())
			{
				// missing event table: create it
				this.connection.prepareStatement(this.measuresTableCreateQuery)
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
					"Unable to check / create db tables");
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
			this.insertMeasureStmt = this.connection
					.prepareStatement(this.insertMeasureQuery);

			this.insertEventStmt = this.connection
					.prepareStatement(this.insertEventQuery);
			this.insertDeviceStmt = this.connection
					.prepareStatement(this.insertDeviceQuery);
			this.selectDeviceStmt = this.connection
					.prepareStatement(selectDeviceQuery);
		}
		catch (SQLException e)
		{
			// Log the error
			this.logger.log(LogService.LOG_ERROR,
					"Unable to create commonly used prepared statements");
		}

	}

	@Override
	public boolean close()
	{
		boolean isClosed = false;
		// close db connection
		try
		{
			this.insertDeviceStmt.close();
			this.insertEventStmt.close();
			this.insertMeasureStmt.close();
			this.selectDeviceStmt.close();

			this.connection.close();
			isClosed = true;
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isClosed;
	}

	@Override
	public boolean insertMeasure(String deviceURI, Date eventTimestamp,
			Measure<?, ?> eventValue, String notificationName,
			String notificationParams)
	{
		boolean inserted = false;

		try
		{
			// check if the device is already available
			this.selectDeviceStmt.setString(1, deviceURI);

			// exec the query
			ResultSet result = this.selectDeviceStmt.executeQuery();

			if (result.next())
			{
				// device exists
				// do nothing
			}
			else
			{
				// create the device
				this.insertDeviceStmt.setString(1, deviceURI);
				this.insertDeviceStmt.setString(2, "");
				this.insertDeviceStmt.setString(3, "");

				this.insertDeviceStmt.executeUpdate();
				this.connection.commit();
			}

			// Insert the real event in the right table

			// fill the prepared statement
			this.insertMeasureStmt.setTimestamp(1,
					new Timestamp(eventTimestamp.getTime()));
			DecimalMeasure<? extends Quantity> measure = DecimalMeasure
					.valueOf(eventValue.toString());
			this.insertMeasureStmt
					.setString(2, eventValue.getUnit().toString());
			this.insertMeasureStmt.setDouble(3, measure.getValue()
					.doubleValue());
			this.insertMeasureStmt.setString(4, notificationName);
			this.insertMeasureStmt.setString(5, notificationParams);
			this.insertMeasureStmt.setString(6, deviceURI);

			// execute the insert query
			this.insertMeasureStmt.executeUpdate();
			this.connection.commit();

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

	@Override
	public boolean insertEvent(String deviceURI, Date eventTimestamp,
			String eventValue, String name)
	{
		boolean inserted = false;

		try
		{
			// check if the device is already available
			this.selectDeviceStmt.setString(1, deviceURI);

			// exec the query
			ResultSet result = this.selectDeviceStmt.executeQuery();

			if (result.next())
			{
				// device exists
				// do nothing
			}
			else
			{
				// create the device
				this.insertDeviceStmt.setString(1, deviceURI);
				this.insertDeviceStmt.setString(2, "");
				this.insertDeviceStmt.setString(3, "");

				this.insertDeviceStmt.executeUpdate();
				this.connection.commit();
			}

			// Insert the real event in the right table

			// fill the prepared statement
			this.insertEventStmt.setTimestamp(1,
					new Timestamp(eventTimestamp.getTime()));
			this.insertEventStmt.setString(2, eventValue);
			this.insertEventStmt.setString(3, name);
			this.insertEventStmt.setString(4, deviceURI);

			// execute the insert query
			this.insertEventStmt.executeUpdate();
			this.connection.commit();

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
	@Override
	public EventDataStreamSet getAllDeviceMeasures(String deviceUri,
			Date startDate, Date endDate, int startCount, int nResults)
	{
		// the event data stream set to return
		EventDataStreamSet streamSet = new EventDataStreamSet();

		// the select query
		String allRealEventsQuery = "SELECT * FROM "
				+ this.measuresTableName
				+ " WHERE deviceuri=? AND timestamp>=? and timestamp<=? ORDER BY name,params ASC LIMIT ? OFFSET ?;";

		// the select statement
		try
		{
			// prepare the select statement
			PreparedStatement allRealEventsStmt = this.connection
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
					streamSet.addEventDataStream(currentStream);

					// update the previous values
					previousName = currentName;
					previousParams = currentParams;
				}

				// create the single event data
				currentPoint = new EventDataPoint(result.getDate("timestamp"),
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
	@Override
	public EventDataStreamSet getAllDeviceEvents(String deviceUri,
			Date startDate, Date endDate, int startCount, int nResults,
			boolean aggregated)
	{

		// the event data stream set to return
		EventDataStreamSet streamSet = new EventDataStreamSet();

		// the select query
		String allRealEventsQuery = "SELECT * FROM Event WHERE deviceuri=? AND timestamp>=? and timestamp<=?";
		if(aggregated)
			allRealEventsQuery = allRealEventsQuery +" ORDER BY timestamp ASC LIMIT ? OFFSET ?;";
		else
			allRealEventsQuery = allRealEventsQuery +" ORDER BY name,timestamp ASC LIMIT ? OFFSET ?;";

		// the select statement
		try
		{
			// prepare the select statement
			PreparedStatement allRealEventsStmt = this.connection
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
				if(aggregated)
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
					streamSet.addEventDataStream(currentStream);

					// update the previous values only if not aggregated
					previousName = currentName;
				}

				// create the single event data
				currentPoint = new EventDataPoint(result.getDate("timestamp"),
						result.getString("value"), "");

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
	public EventDataStream getSpecificDeviceMeasure(String deviceURI,
			String notificationName, String notificationParams, Date startDate,
			Date endDate, int startCount, int nResults)
	{
		// The event stream to return
		EventDataStream stream = new EventDataStream(notificationName,
				notificationParams, deviceURI);

		// the select query
		// the select query
		String realEventsQuery = "SELECT * FROM RealEvent WHERE deviceuri=? AND name=? AND params=? AND timestamp>=? and timestamp<=? ORDER BY name,params LIMIT ? OFFSET ?;";

		// the select statement
		try
		{
			// prepare the select statement
			PreparedStatement realEventsStmt = this.connection
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
				currentPoint = new EventDataPoint(result.getDate("timestamp"),
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
	private EventDataStream getDeviceEvents(String deviceURI,
			String notificationName, Date startDate, Date endDate,
			int startCount, int nResults)
	{
		EventDataStream stream = new EventDataStream(notificationName, "",
				deviceURI);

		// the select query
		// the select query
		String realEventsQuery = "SELECT * FROM Event WHERE deviceuri=? AND name=? AND timestamp>=? and timestamp<=? ORDER BY name LIMIT ? OFFSET ?;";

		// the select statement
		try
		{
			// prepare the select statement
			PreparedStatement realEventsStmt = this.connection
					.prepareStatement(realEventsQuery);

			// fill the statement data
			realEventsStmt.setString(1, deviceURI);
			realEventsStmt.setString(2, notificationName);
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
				currentPoint = new EventDataPoint(result.getDate("timestamp"),
						result.getString("value"), "");

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

}
