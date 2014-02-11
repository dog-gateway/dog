/*
 * Dog - Admin
 * 
 * Copyright (c) 2013-2014 Dario Bonino and Luigi De Russis
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
package it.polito.elite.dog.admin.system.bundlemanager;

import it.polito.elite.dog.admin.system.bundlemanager.api.BundleManager;
import it.polito.elite.dog.admin.system.bundlemanager.model.BundleStateResponse;
import it.polito.elite.dog.admin.system.bundlemanager.model.BundleStatsResponse;
import it.polito.elite.dog.admin.system.bundlemanager.util.BundleNameComparator;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Path;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
@Path("/admin/system/bundlemanager/")
public class BundleManagerEndpoint implements BundleManager
{
	// the service logger
	private LogHelper logger;
	
	// the bundle context reference to extract information on the bundle list
	private BundleContext context;
	
	// the instance-level mapper
	private ObjectMapper mapper;
	
	/**
	 * Default constructor
	 */
	public BundleManagerEndpoint()
	{
		// initialize the instance-wide object mapper
		this.mapper = new ObjectMapper();
		// set the mapper pretty printing
		this.mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
		// avoid empty arrays and null values
		this.mapper.configure(SerializationConfig.Feature.WRITE_EMPTY_JSON_ARRAYS, false);
		this.mapper.setSerializationInclusion(Inclusion.NON_NULL);
	}
	
	/**
	 * Bundle activation, stores a reference to the context object passed by the
	 * framework to get access to system data, e.g., installed bundles, etc.
	 * 
	 * @param context
	 */
	public void activate(BundleContext context)
	{
		// store the bundle context
		this.context = context;
		
		// init the logger with a null logger
		this.logger = new LogHelper(this.context);
		
		// log the activation
		this.logger.log(LogService.LOG_INFO, "Activated....");
	}
	
	/**
	 * Prepare the bundle to be deactivated...
	 */
	public void deactivate()
	{
		// null the context
		this.context = null;
		
		// log deactivation
		this.logger.log(LogService.LOG_INFO, "Deactivated...");
		
		// null the logger
		this.logger = null;
	}
	
	@Override
	public String getBundles()
	{
		// init
		List<BundleStateResponse> bundleList = new ArrayList<BundleStateResponse>();
		String bundles = "";
		
		// get all the installed bundles
		Bundle allBundles[] = this.context.getBundles();
		Arrays.sort(allBundles, new BundleNameComparator());
		
		// generate a list of bundles to be returned as JSON array
		for (int i = 0; i < allBundles.length; i++)
		{
			BundleStateResponse currentBundle = new BundleStateResponse();
			currentBundle.setName(allBundles[i].getSymbolicName() + " (" + allBundles[i].getVersion() + ")");
			switch (allBundles[i].getState())
			{
				case Bundle.ACTIVE:
				{
					currentBundle.setState("Active");
					break;
				}
				case Bundle.INSTALLED:
				{
					currentBundle.setState("Installed");
					break;
				}
				case Bundle.RESOLVED:
				{
					currentBundle.setState("Resolved");
					break;
				}
			}
			bundleList.add(currentBundle);
		}
		
		try
		{
			bundles = this.mapper.writeValueAsString(bundleList);
		}
		catch (Exception e)
		{
			this.logger.log(LogService.LOG_ERROR, "Error in creating the JSON object for the bundle list", e);
		}
		
		return bundles;
	}
	
	@Override
	public String getOverallStatistics()
	{
		// init
		BundleStatsResponse statistics = new BundleStatsResponse();
		String bundleStats = "";
		
		// get all the installed bundles
		Bundle allBundles[] = this.context.getBundles();
		
		// prepare counters
		int nActive = 0;
		int nInstalled = 0;
		int nResolved = 0;
		
		// generate a list of bundles
		for (int i = 0; i < allBundles.length; i++)
		{
			// get the bundle state
			switch (allBundles[i].getState())
			{
				case Bundle.ACTIVE:
				{
					nActive++;
					break;
				}
				case Bundle.INSTALLED:
				{
					nInstalled++;
					break;
				}
				case Bundle.RESOLVED:
				{
					nResolved++;
					break;
				}
			}
		}
		
		// compute the active ratio
		double activeRatio = (double) nActive / (double) (nActive + nResolved + nInstalled);
		
		// fill the response object
		statistics.setActive(nActive);
		statistics.setInstalled(nInstalled);
		statistics.setResolved(nResolved);
		statistics.setActiveRatio(activeRatio);
		
		try
		{
			bundleStats = this.mapper.writeValueAsString(statistics);
		}
		catch (Exception e)
		{
			this.logger.log(LogService.LOG_ERROR, "Error in creating the JSON object for the bundle statistics", e);
		}
		
		return bundleStats;
		
		
	}
}
