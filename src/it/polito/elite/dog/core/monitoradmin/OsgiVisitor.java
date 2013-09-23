/*
 * Dog - Core
 * 
 * Copyright (c) 2013 Dario Bonino and Luigi De Russis
 * Copyright (c) 2012 Dmytro Pishchukhin
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

package it.polito.elite.dog.core.monitoradmin;

import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.monitor.Monitorable;

/**
 * OSGi visitor interface. Is used to decouple BundleContext
 *
 * @author dpishchukhin
 * @see org.osgi.framework.BundleContext
 */
public interface OsgiVisitor {
    /**
     * Get <code>Monitorable</code> service by <code>ServiceReference</code>
     * @param reference <code>ServiceReference</code>
     * @return <code>Monitorable</code> service
     */
    Monitorable getService(ServiceReference<?> reference);

    /**
     * Get list of <code>Monitorable</code> <code>ServiceReference</code> by monitorableId
     * @param monitorableId monitorable Id
     * @return Array of <code>ServiceReference</code>s or <code>null</code>
     */
    ServiceReference<?>[] findMonitorableReferences(String monitorableId);

    /**
     * Post <code>Event</code> via <code>EventAdmin</code>
     * @param event event
     */
    void postEvent(Event event);
}
