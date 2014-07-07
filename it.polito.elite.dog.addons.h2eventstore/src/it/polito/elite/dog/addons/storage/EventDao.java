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

import java.util.Date;
import javax.measure.DecimalMeasure;

/**
 * An interface defining common methods for accessing data stored in Dog event
 * stores, i.e., bundles offering the {@link EventStore} service.
 * 
 * @author <a href="mailto:claudiodegio@gmail.com">Claudio Degioanni</a> (first
 *         version)
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a> (minor
 *         editing)
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a> (second
 *         version)
 * 
 */
public interface EventDao
{
	/**
	 * Insert the event in the persistent storage. If the given device URI
	 * identifies a device seen for the first time, create a new entry in the
	 * proper db table.
	 * 
	 * @param deviceURI
	 *            The URI of the Device generating the event
	 * @param eventTimestamp
	 *            The event time stamp as a {@link Date} instance.
	 * @param eventValue
	 *            The event value as a {@link DecimalMeasure} instance.
	 * @param eventType
	 *            The event type, i.e., notification,...
	 * @param notificationName
	 *            The notification name as a {@link String} instance.
	 * @param notificationParams
	 *            The notification params as a post-like encoded
	 *            (name=value&name=value...) {@link String} instance.
	 * @throws SQLException
	 */
	public boolean insertRealEvent(String deviceURI, Date eventTimestamp,
			DecimalMeasure<?> eventValue, String eventType,
			String notificationName, String notificationParams);

	public boolean close();
}
