/*
 * 
 * Copyright (c) [2013] [Claudio Degioanni claudiodegio@gmail.com]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *              http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.polito.elite.dog.addons.h2eventstore.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.measure.Measure;

import it.polito.elite.dog.core.library.util.LogHelper;

import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class MeasureDaoImp implements MeasureDao
{	
	// System logger
	private LogHelper logger;
	
	// database connection
	private Connection connection;
	
	public MeasureDaoImp(final String url, final String user, final String password, final BundleContext context)
			throws SQLException
	{
		// init logger
		this.logger = new LogHelper(context);
		
		// open database connection
		connection = DriverManager.getConnection(url, user, password);
		
		// check table exist
		final ResultSet tableSet = connection.getMetaData().getTables(connection.getCatalog(), null, "EVENT", null);
		
		if (!tableSet.next())
		{
			// missing event table create it
			connection.prepareStatement(Contants.SCHEMA).executeUpdate();
			logger.log(LogService.LOG_INFO, " schema creation success");
		}
		
		tableSet.close();
	}
	
	@Override
	public void insert(String name, Measure<?, ?> value, Date timestamp) throws SQLException
	{
		logger.log(LogService.LOG_INFO, "insert event name: " + name + " value: " + value + " timestamp: "
				+ timestamp);
		
		final PreparedStatement stm = connection.prepareStatement(Contants.INSERT);
		
		stm.setString(1, name);
		stm.setBigDecimal(2, (BigDecimal) value.getValue());
		stm.setString(3, value.getUnit().toString());
		
		java.sql.Date sqlDate = new java.sql.Date(timestamp.getTime());
		stm.setDate(4, sqlDate);
		
		stm.executeUpdate();
		stm.close();
		connection.commit();
	}
	
	@Override
	public void close() throws SQLException
	{
		// close db connection
		connection.close();
	}
	
}
