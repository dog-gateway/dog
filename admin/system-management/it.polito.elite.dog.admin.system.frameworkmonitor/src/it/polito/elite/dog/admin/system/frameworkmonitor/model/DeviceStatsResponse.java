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
package it.polito.elite.dog.admin.system.frameworkmonitor.model;

/**
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class DeviceStatsResponse
{
	private int active;
	private int idle;
	private double activeRatio;
	
	public DeviceStatsResponse()
	{
		// intentionally left empty
	}
	
	/**
	 * @return the active
	 */
	public int getActive()
	{
		return this.active;
	}
	
	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(int active)
	{
		this.active = active;
	}
	
	
	/**
	 * @return the idle devices
	 */
	public int getIdle()
	{
		return this.idle;
	}
	
	/**
	 * @param idle
	 *            the idle device number to set
	 */
	public void setIdle(int idle)
	{
		this.idle = idle;
	}
	
	/**
	 * @return the activeRatio
	 */
	public double getActiveRatio()
	{
		return this.activeRatio;
	}
	
	/**
	 * @param activeRatio
	 *            the activeRatio to set
	 */
	public void setActiveRatio(double activeRatio)
	{
		this.activeRatio = activeRatio;
	}
	
}
