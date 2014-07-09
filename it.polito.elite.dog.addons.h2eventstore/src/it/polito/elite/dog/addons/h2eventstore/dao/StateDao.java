package it.polito.elite.dog.addons.h2eventstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

import it.polito.elite.dog.addons.h2eventstore.db.H2Storage;
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
			+ "name VARCHAR(100), deviceuri VARCHAR(255), PRIMARY KEY(id), "
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isClosed;
	}
}
