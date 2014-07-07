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
import it.polito.elite.dog.core.library.util.LogHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.measure.DecimalMeasure;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class EventDaoImpl implements EventDao
{
	// OSGi logger
	private LogHelper logger;

	// database connection
	private Connection connection;

	// --------- commonly used statements ------------
	private PreparedStatement insertRealEventStmt;
	private PreparedStatement insertEventStmt;
	private PreparedStatement insertDeviceStmt;
	private PreparedStatement selectDeviceStmt;

	// --------- commonly used queries ---------------

	// ---- INSERTION -----------
	private final String insertRealEventQuery = "INSERT INTO RealEvent(timestamp, unit, value, name, params, deviceuri) VALUES (?,?,?,?,?)";
	private final String insertEventQuery = "INSERT INTO Event(timestamp, value, name, deviceuri) VALUES (?,?,?);";
	private final String insertDeviceQuery = "INSERT INTO Device(uri, class, name) VALUES (?,?,?);";

	// ---- DELETION ------------

	private final String selectDeviceQuery = "SELECT * FROM Device WHERE Device.uri = ?;";

	// ---- TABLE STRUCTURE
	private final String realEventTable = "CREATE TABLE RealEvent(id int(11) NOT NULL AUTO_INCREMENT, timestamp TIMESTAMP, unit VARCHAR(5), value DECIMAL, name VARCHAR(100), params varchar(255), deviceuri VARCHAR(255), PRIMARY KEY(id), KEY fk_real_event_device_uri (deviceuri), CONSTRAINT fk_real_event_device_uri FOREIGN KEY (deviceuri) REFERENCES Device(uri) ON DELETE CASCADE);";
	private final String eventTable = "CREATE TABLE Event(id int(11) NOT NULL AUTO_INCREMENT, timestamp TIMESTAMP, value DECIMAL, name VARCHAR(100), deviceuri VARCHAR(255), PRIMARY KEY(id), KEY fk_real_event_device_uri (deviceuri), CONSTRAINT fk_real_event_device_uri FOREIGN KEY (deviceuri) REFERENCES Device(uri) ON DELETE CASCADE);";
	private final String deviceTable = "CREATE TABLE Device(uri VARCHAR(255), name VARCHAR(100), class varchar(255), PRIMARY KEY(uri));";

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
			// check if the Event table exist
			ResultSet tableSet = connection.getMetaData().getTables(
					connection.getCatalog(), null, "Event", null);

			if (!tableSet.next())
			{
				// missing event table: create it
				this.connection.prepareStatement(this.eventTable)
						.executeUpdate();
				this.logger.log(LogService.LOG_INFO,
						"Schema creation has been successful!");
			}

			tableSet.close();

			// check if the RealEvent table exist
			tableSet = connection.getMetaData().getTables(
					connection.getCatalog(), null, "RealEvent", null);

			if (!tableSet.next())
			{
				// missing event table: create it
				this.connection.prepareStatement(this.realEventTable)
						.executeUpdate();
				this.logger.log(LogService.LOG_INFO,
						"Schema creation has been successful!");
			}

			tableSet.close();

			// check if the Device table exist
			tableSet = connection.getMetaData().getTables(
					connection.getCatalog(), null, "Device", null);

			if (!tableSet.next())
			{
				// missing event table: create it
				this.connection.prepareStatement(this.deviceTable)
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
			this.insertRealEventStmt = this.connection
					.prepareStatement(this.insertRealEventQuery);

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
			connection.close();
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
	public boolean insertRealEvent(String deviceURI, Date eventTimestamp,
			DecimalMeasure<?> eventValue, String eventType,
			String notificationName, String notificationParams)
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
			}

			// Insert the real event in the right table
			this.insertRealEventStmt.setTimestamp(1, new Timestamp(
					eventTimestamp.getTime()));
			this.insertRealEventStmt.setString(2, eventValue.getUnit()
					.toString());
			this.insertRealEventStmt.setBigDecimal(3, eventValue.getValue());
			this.insertRealEventStmt.setString(4, notificationName);
			this.insertRealEventStmt.setString(5, notificationParams);
			this.insertRealEventStmt.setString(6, deviceURI);

			this.insertRealEventStmt.executeUpdate();
			this.connection.commit();

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

}
