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

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

/**
 * MonitorAdmin ServiceFactory implementation
 *
 * @author dpishchukhin
 */
public class MonitorAdminFactory implements ServiceFactory<Object> {
    private final LogVisitor logVisitor;
    private final MonitorAdminCommon common;

    public MonitorAdminFactory(LogVisitor logVisitor, MonitorAdminCommon common) {
        this.logVisitor = logVisitor;
        this.common = common;
    }

    public Object getService(Bundle bundle, ServiceRegistration<Object> serviceRegistration) {
        logVisitor.debug(String.format("Bind MonitorAdmin instance to %s bundle", bundle.getSymbolicName()), null);
        return new MonitorAdminImpl(logVisitor, common, bundle);
    }

    public void ungetService(Bundle bundle, ServiceRegistration<Object> serviceRegistration, Object o) {
        logVisitor.debug(String.format("Unbind MonitorAdmin instance from %s bundle", bundle.getSymbolicName()), null);
    }
}
