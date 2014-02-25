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
package it.polito.elite.dog.admin.system.bundlemanager.util;

import java.util.Comparator;

import org.osgi.framework.Bundle;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 *
 */
public class BundleNameComparator implements Comparator<Bundle>
{
	
	/**
	 * 
	 */
	public BundleNameComparator()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Bundle b1, Bundle b2)
	{
		return (b1.getSymbolicName().compareTo(b2.getSymbolicName()));
	}
	
}
