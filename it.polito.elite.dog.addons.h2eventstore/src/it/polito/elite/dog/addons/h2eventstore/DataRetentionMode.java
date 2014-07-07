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

package it.polito.elite.dog.addons.h2eventstore;

/**
 * A public enumeration used to set the data retention mode of the
 * {@link H2EventStore} bundle. It can either assume the value
 * <code>DataRetentionMode.DROP</code>, which requires the bundle to drop
 * incoming events once the maximum size of the persistent storage (expressed in
 * rows) is reached, or the value <code>DataRetentionMode.REPLACE</code> that,
 * once reached the maximum size of the persistent storage replaces older events
 * with newer ones.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a> (second
 *         version)
 * 
 */
public enum DataRetentionMode
{
	DROP, // drops new events keeping the database state once filled
	REPLACE // drops older events in favor of newer ones.
}
