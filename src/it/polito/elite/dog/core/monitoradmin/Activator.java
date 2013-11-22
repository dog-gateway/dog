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

import org.osgi.framework.*;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.log.LogService;
import org.osgi.service.monitor.MonitorAdmin;
import org.osgi.service.monitor.MonitorListener;
import org.osgi.service.monitor.Monitorable;
import org.osgi.util.tracker.ServiceTracker;

import it.polito.elite.dog.core.monitoradmin.util.Utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Monitor Admin activator
 *
 * @author dmytro.pishchukhin
 * @see org.osgi.framework.BundleActivator
 */
public class Activator implements BundleActivator, OsgiVisitor, LogVisitor {
    /**
     * Default logger
     */
    private static final Logger LOG = Logger.getLogger(Activator.class.getName());

    /**
     * <code>MonitorAdmin</code> <code>ServiceFactory</code> instance
     */
    private MonitorAdminFactory monitorAdminFactory;
    /**
     * MonitorAdmin commons actions
     */
    private MonitorAdminCommon common;

    /**
     * BundleContext
     */
    private BundleContext bc;
    /**
     * MonitorAdmin ServiceFactory registration
     */
    private ServiceRegistration<?> monitorAdminRegistration;
    /**
     * MonitorListener registration
     */
    private ServiceRegistration<?> monitorListenerRegistration;

    /**
     * EventAdmin service tracker
     */
    private ServiceTracker<?, ?> eventAdminTracker;
    /**
     * LogService service tracker
     */
    private ServiceTracker<?, ?> logServiceTracker;


    public void start(BundleContext bundleContext) {
        bc = bundleContext;

        // init LogService tracker
        logServiceTracker = new ServiceTracker<Object, Object>(bc, LogService.class.getName(), null);
        logServiceTracker.open();

        // init EventAdmin tracker
        eventAdminTracker = new ServiceTracker<Object, Object>(bc, EventAdmin.class.getName(), null);
        eventAdminTracker.open();

        // init commons
        common = new MonitorAdminCommon(this, this);
        // init factory
        monitorAdminFactory = new MonitorAdminFactory(this, common);

        // register MonitorAdmin ServiceFactory
        monitorAdminRegistration = bundleContext.registerService(MonitorAdmin.class.getName(), monitorAdminFactory, null);
        // register MonitorListener
        monitorListenerRegistration = bundleContext.registerService(MonitorListener.class.getName(), common, null);

        info("MonitorAdmin started", null);
    }

    public void stop(BundleContext bundleContext) {
        // unregister MonitorAdmin service
        if (monitorAdminRegistration != null) {
            monitorAdminRegistration.unregister();
            monitorAdminRegistration = null;
        }

        // unregister MonitorListener service
        if (monitorListenerRegistration != null) {
            monitorListenerRegistration.unregister();
            monitorListenerRegistration = null;
        }

        if (common != null) {
            // cancel started jobs
            common.cancelAllJobs();
            monitorAdminFactory = null;
        }

        if (eventAdminTracker != null) {
            eventAdminTracker.close();
            eventAdminTracker = null;
        }

        info("MonitorAdmin stoppped", null);

        if (logServiceTracker != null) {
            logServiceTracker.close();
            logServiceTracker = null;
        }

        bc = null;
    }

    /**
     * Publish DEBUG message. If <code>LogService</code> in unavailable message is published to default JUL logger
     *
     * @param message   message
     * @param throwable exception
     */
    public void debug(String message, Throwable throwable) {
        LogService logService = (LogService) logServiceTracker.getService();
        if (logService != null) {
            logService.log(LogService.LOG_DEBUG, message, throwable);
        } else {
            LOG.log(Level.FINE, message, throwable);
        }
    }

    /**
     * Publish INFO message. If <code>LogService</code> in unavailable message is published to default JUL logger
     *
     * @param message   message
     * @param throwable exception
     */
    public void info(String message, Throwable throwable) {
        LogService logService = (LogService) logServiceTracker.getService();
        if (logService != null) {
            logService.log(LogService.LOG_INFO, message, throwable);
        } else {
            LOG.log(Level.INFO, message, throwable);
        }
    }

    /**
     * Publish WARNING message. If <code>LogService</code> in unavailable message is published to default JUL logger
     *
     * @param message   message
     * @param throwable exception
     */
    public void warning(String message, Throwable throwable) {
        LogService logService = (LogService) logServiceTracker.getService();
        if (logService != null) {
            logService.log(LogService.LOG_WARNING, message, throwable);
        } else {
            LOG.log(Level.WARNING, message, throwable);
        }
    }

    /**
     * Publish ERROR message. If <code>LogService</code> in unavailable message is published to default JUL logger
     *
     * @param message   message
     * @param throwable exception
     */
    public void error(String message, Throwable throwable) {
        LogService logService = (LogService) logServiceTracker.getService();
        if (logService != null) {
            logService.log(LogService.LOG_ERROR, message, throwable);
        } else {
            LOG.log(Level.SEVERE, message, throwable);
        }
    }

    public Monitorable getService(ServiceReference<?> reference) {
        return (Monitorable) bc.getService(reference);
    }

    public ServiceReference<?>[] findMonitorableReferences(String monitorableId) {
        String filter = null;
        if (monitorableId != null) {
            filter = Utils.createServicePidFilter(monitorableId);
        }
        try {
            return bc.getServiceReferences(Monitorable.class.getName(), filter);
        } catch (InvalidSyntaxException e) {
            warning("Unable to find Monitorable References", e);
            return null;
        }
    }

    public void postEvent(Event event) {
        EventAdmin eventAdmin = (EventAdmin) eventAdminTracker.getService();
        if (eventAdmin != null) {
            eventAdmin.postEvent(event);
        } else {
            warning("EventAdmin is unavailable", null);
        }
    }
}
