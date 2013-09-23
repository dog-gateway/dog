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
package it.polito.elite.dog.core.devicemanager.util;

import it.polito.elite.dog.core.devicemanager.DriverAttributes;
import it.polito.elite.dog.core.devicemanager.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;
import org.osgi.service.device.Constants;
import org.osgi.service.device.DriverSelector;
import org.osgi.service.device.Match;

/**
 * This class perform the match phase for a given driver.
 * 
 * @author <a href="mailto:dev@felix.apache.org">Felix Project Team</a>
 *         (original version)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 *         (successive modifications)
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class DriverMatcher
{
	
	private final Log logger;
	
	SortedMap<Integer, List<DriverAttributes>> map = new TreeMap<Integer, List<DriverAttributes>>();
	
	List<Match> matches = new ArrayList<Match>();
	
	public DriverMatcher(Log log)
	{
		this.logger = log;
	}
	
	// we keep track of the driver attributes in two
	// lists, one to aid us if there is no driver selector, one
	// if there is...
	public void add(Integer match, DriverAttributes value)
	{
		List<DriverAttributes> da = get(match);
		da.add(value);
		this.matches.add(new MatchImpl(value.getReference(), match));
	}
	
	private List<DriverAttributes> get(Integer key)
	{
		List<DriverAttributes> da = map.get(key);
		if (da == null)
		{
			this.map.put((Integer) key, new ArrayList<DriverAttributes>());
		}
		return this.map.get(key);
	}
	
	public Match getBestMatch()
	{
		if (this.map.isEmpty())
		{
			return null;
		}
		
		int matchValue = this.map.lastKey();
		
		// these are the matches that
		// got the highest match value
		List<DriverAttributes> das = this.map.get(matchValue);
		if (das.size() == 1)
		{
			// a shortcut: there's only one with the highest match
			return new MatchImpl(das.get(0).getReference(), matchValue);
		}
		
		// get the highest ranking driver
		final SortedMap<ServiceReference<?>, Match> matches = new TreeMap<ServiceReference<?>, Match>(new ServicePriority());
		
		for (DriverAttributes da : das)
		{
			matches.put(da.getReference(), new MatchImpl(da.getReference(), matchValue));
		}
		
		ServiceReference<?> last = matches.lastKey();
		return matches.get(last);
	}
	
	public Match selectBestMatch(ServiceReference<?> deviceRef, DriverSelector selector)
	{
		// Match[] matches = m_matches.toArray( new Match[0] );
		
		// (re)check bundle status
		List<Match> activeMatches = new ArrayList<Match>();
		for (Match match : this.matches)
		{
			if (match.getDriver().getBundle().getState() == Bundle.ACTIVE)
			{
				activeMatches.add(match);
			}
			else
			{
				this.logger.debug("skipping: " + match + ", it's bundle is: " + match.getDriver().getBundle().getState());
			}
		}
		try
		{
			Match[] matches = activeMatches.toArray(new Match[0]);
			int index = selector.select(deviceRef, matches);
			if (index != DriverSelector.SELECT_NONE && index >= 0 && index < matches.length)
			{
				return matches[index];
			}
		}
		catch (Exception e)
		{
			this.logger.error("exception thrown in DriverSelector.select()", e);
		}
		return null;
	}
	
	private class MatchImpl implements Match
	{
		
		private final ServiceReference<?> ref;
		private final int match;
		
		public MatchImpl(ServiceReference<?> ref, int match)
		{
			this.ref = ref;
			this.match = match;
		}
		
		public ServiceReference<?> getDriver()
		{
			return this.ref;
		}
		
		public int getMatchValue()
		{
			return this.match;
		}
		
		public String toString()
		{
			return "[MatchImpl: DRIVER_ID=" + ref.getProperty(Constants.DRIVER_ID) + ", match=" + this.match + "]";
		}
		
	}
	
	private class ServicePriority implements Comparator<ServiceReference<?>>
	{
		
		private int getValue(ServiceReference<?> ref, String key, int defaultValue)
		{
			Object obj = ref.getProperty(key);
			if (obj == null)
			{
				return defaultValue;
			}
			try
			{
				return Integer.class.cast(obj);
			}
			catch (Exception e)
			{
				return defaultValue;
			}
		}
		
		public int compare(ServiceReference<?> o1, ServiceReference<?> o2)
		{
			int serviceRanking1 = getValue(o1, org.osgi.framework.Constants.SERVICE_RANKING, 0);
			int serviceRanking2 = getValue(o2, org.osgi.framework.Constants.SERVICE_RANKING, 0);
			
			if (serviceRanking1 != serviceRanking2)
			{
				return (serviceRanking1 - serviceRanking2);
			}
			int serviceId1 = getValue(o1, org.osgi.framework.Constants.SERVICE_ID, Integer.MAX_VALUE);
			int serviceId2 = getValue(o2, org.osgi.framework.Constants.SERVICE_ID, Integer.MAX_VALUE);
			
			return (serviceId2 - serviceId1);
		}
	}
}
