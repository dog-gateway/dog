/*
 * Dog - Network Driver
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
package it.polito.elite.dog.drivers.bticino.interfaces;

import com.bticino.core.OpenWebNet;

/**
 * 
 * @author <a href="mailto:castellina.emi@gmail.com">Emiliano Castellina</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public interface BTicinoNetworkDriver
{
	public final String CONNECTED = "CONNECTED";
	public final String MANUFACTURER = "BTICINO";
	public final String ADDRESS = "physicalAddress"; // check for integrity!!!
	
	public void bind(BTicinoSpecificDriver driver, String deviceAdress);
	
	public void unbind(BTicinoSpecificDriver driver, String deviceAdress);
	
	public void sendMyOpenMessage(OpenWebNet message, int priority);
	
	public String getIpAddress();
}
