/*
 * Dog - Device Driver
 * 
 * Copyright (c) 2010-2014 Emiliano Castellina and Luigi De Russis
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
package it.polito.elite.dogdrivers.bticino.c8;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @author <a href="mailto:castellina.emi@gmail.com">Emiliano Castellina</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class Activator implements BundleActivator
{
	
	private BTicinoC8Driver driver;
	
	@Override
	public void start(BundleContext context) throws Exception
	{
		
		this.driver = new BTicinoC8Driver(context);
	}
	
	@Override
	public void stop(BundleContext context) throws Exception
	{
		
		this.driver.unRegisterDriver();
	}
	
}
