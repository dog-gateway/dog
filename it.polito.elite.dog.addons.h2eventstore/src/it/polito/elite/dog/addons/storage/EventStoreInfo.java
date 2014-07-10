/*
 * Dog - Addons
 * 
 * Copyright (c) 2013-2014 Claudio Degioanni, Luigi De Russis, Dario Bonino
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
package it.polito.elite.dog.addons.storage;

public class EventStoreInfo
{
	// the database location
	public final static String DB_LOCATION = "database.location";
	public final static String DB_RETENTION_MODE = "database.retentionmode";
	public final static String DB_MAX_SIZE = "database.maxsize";
	public final static String NOTIFICATIONS_ENABLED = "enablenotifications";
	public final static String STATES_ENABLED = "enablestates";
	
	// the event store event managemet policy
	public final static String EVENT_ATTACHMENT = "eventhandling.auto";
	
	// the default persistent storage size
	public final static int UNLIMITED_SIZE = -1;
}
