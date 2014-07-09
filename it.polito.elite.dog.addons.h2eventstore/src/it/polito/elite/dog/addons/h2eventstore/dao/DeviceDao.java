package it.polito.elite.dog.addons.h2eventstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

import it.polito.elite.dog.addons.h2eventstore.H2EventStore;
import it.polito.elite.dog.addons.h2eventstore.db.H2Storage;
import it.polito.elite.dog.core.library.util.LogHelper;

public class DeviceDao
{

	// OSGi logger
	private LogHelper logger;

	// The storage layer
	private H2Storage storage;

	// ---- TABLE NAMES
	private final String deviceTableName = "Device";

	// ---- TABLE STRUCTURE
	private final String deviceTableCreateQuery = "CREATE TABLE "
			+ this.deviceTableName
			+ "(uri VARCHAR(255), name VARCHAR(100), class varchar(255), PRIMARY KEY(uri));";

	// --------- commonly used statements ------------
	private PreparedStatement insertDeviceStmt;
	private PreparedStatement selectDeviceStmt;

	// ---- INSERTION QUERIES
	private final String insertDeviceQuery = "INSERT INTO "
			+ this.deviceTableName + "(uri, class, name) VALUES (?,?,?);";

	// ---- SELECT QUERIES
	private final String selectDeviceQuery = "SELECT * FROM Device WHERE Device.uri = ?;";

	public DeviceDao(final H2Storage storage, final BundleContext context)
	{
		// init logger
		this.logger = new LogHelper(context);

		// store the connection
		this.storage = storage;

		// check and create tables if needed
		this.checkAndCreateTables();

		// prepare the commonly executed statements
		this.prepareCommonStatements();
	}

	private void checkAndCreateTables()
	{
		try
		{
			// check if the Device table exist
			ResultSet tableSet = this.storage
					.getConnection()
					.getMetaData()
					.getTables(this.storage.getConnection().getCatalog(), null,
							this.deviceTableName.toUpperCase(), null);

			if (!tableSet.next())
			{
				// missing event table: create it
				this.storage.getConnection()
						.prepareStatement(this.deviceTableCreateQuery)
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
					"Unable to check / create device db tables");
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
			this.insertDeviceStmt = this.storage.getConnection()
					.prepareStatement(this.insertDeviceQuery);
			this.selectDeviceStmt = this.storage.getConnection()
					.prepareStatement(selectDeviceQuery);
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
			this.insertDeviceStmt.close();
			this.selectDeviceStmt.close();
			isClosed = true;
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isClosed;
	}

	public boolean isDevicePresent(String deviceUri)
	{
		boolean isPresent = false;

		// check if the device is already available
		try
		{
			this.selectDeviceStmt.setString(1, deviceUri);

			// exec the query
			ResultSet result = this.selectDeviceStmt.executeQuery();

			if (result.next())
				isPresent = true;
		}
		catch (SQLException e)
		{
			// log the error
			this.logger.log(LogService.LOG_ERROR,
					"Unable to check device presence in the db");
		}

		return isPresent;
	}

	public void insertDevice(String deviceURI)
	{
		try
		{
		// create the device
		this.insertDeviceStmt.setString(1, deviceURI);
		this.insertDeviceStmt.setString(2, "");
		this.insertDeviceStmt.setString(3, "");

		this.insertDeviceStmt.executeUpdate();
		this.storage.getConnection().commit();
		}
		catch (SQLException e)
		{
			// log the error
			this.logger.log(LogService.LOG_ERROR,
					"Unable to create device in the db");
		}
		
	}
}
