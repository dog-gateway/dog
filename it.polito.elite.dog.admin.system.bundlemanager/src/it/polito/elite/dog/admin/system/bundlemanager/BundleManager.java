/*
 * Dog - Admin
 * 
 * Copyright (c) 2013 Dario Bonino
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

import java.util.Arrays;

import it.polito.elite.dog.admin.system.bundlemanager.api.BundleManagerInterface;
import it.polito.elite.dog.admin.system.bundlemanager.util.BundleNameComparator;
import it.polito.elite.dog.core.library.util.LogHelper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
@Path("/admin/system/bundlemanager/")
public class BundleManager implements BundleManagerInterface
{
	// the service logger
	private LogHelper logger;
	
	// the number of columns
	public static final int nColumns = 2;
	
	// the full-row span
	public static final int spanPerRow = 12;
	
	// the bundle context reference to extract information on the entire Dog
	// status
	private BundleContext context;
	
	/**
		 * 
		 */
	public BundleManager()
	{
		// TODO Auto-generated constructor stub
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.admin.system.bundlemanager.BundleManagerInterface
	 * #getBundles()
	 */
	@Override
	@GET
	@Path("/bundles")
	@Produces(MediaType.TEXT_HTML)
	public String getBundles()
	{
		// get all the installed bundles
		Bundle allBundles[] = this.context.getBundles();
		Arrays.sort(allBundles, new BundleNameComparator());
		
		// the output buffer
		StringBuffer htmlOut = new StringBuffer();
		
		// append the unordered list header
		
		// generate a list of bundles
		for (int i = 0; i < allBundles.length; i++)
		{
			// column handling
			if (i == 0)
			{
				htmlOut.append("<div class=\"span" + (BundleManager.spanPerRow / nColumns)
						+ "\">\n<div class=\"well\">\n<ul class=\"unstyled\">\n");
			}
			else if (i == allBundles.length / nColumns)
			{
				htmlOut.append("</ul>\n</div>\n</div>");
				htmlOut.append("<div class=\"span" + (BundleManager.spanPerRow / nColumns)
						+ "\">\n<div class=\"well\">\n<ul class=\"unstyled\">\n");
			}
			
			// handle the current bundle rendering
			htmlOut.append("<li>" + allBundles[i].getSymbolicName() + " (" + allBundles[i].getVersion() + ") ");
			
			// render the bundle state
			switch (allBundles[i].getState())
			{
				case Bundle.ACTIVE:
				{
					htmlOut.append("<span class=\"label label-success pull-right\">Active</span>");
					break;
				}
				case Bundle.INSTALLED:
				{
					htmlOut.append("<span class=\"label label-info pull-right\">Installed</span>");
					break;
				}
				case Bundle.RESOLVED:
				{
					htmlOut.append("<span class=\"label label-warning pull-right\">Resolved</span>");
					break;
				}
			}
			
			htmlOut.append("</li>\n");
		}
		
		htmlOut.append("</ul>\n</div>\n</div>");
		
		return htmlOut.toString();
		
	}
	
	@Override
	@GET
	@Path("/bundles/statistics")
	@Produces(MediaType.TEXT_HTML)
	public String getOverallStatistics()
	{
		// get all the installed bundles
		Bundle allBundles[] = this.context.getBundles();
		
		// the output buffer
		StringBuffer htmlOut = new StringBuffer();
		
		// prepare counters
		int nActive = 0;
		int nInstalled = 0;
		int nResolved = 0;
		
		// generate a list of bundles
		for (int i = 0; i < allBundles.length; i++)
		{
			// render the bundle state
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
		
		// render statistics
		
		// compute the active ratio
		double activeRatio = (double)nActive / (double)(nActive+nResolved+nInstalled);
		
		// compute the label color
		String color = "";
		if (activeRatio < 0.5)
			color = "label-important";
		else if (activeRatio == 1)
			color = "label-success";
		else
			color = "label-warning";
		
		htmlOut.append("<span class=\"label pull-right " + color + "\">" + nActive + " Active / " + nInstalled +" Installed / "+nResolved+" Resolved </span>");
		
		return htmlOut.toString();
	}
}
