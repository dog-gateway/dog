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

import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.monitor.MonitorListener;
import org.osgi.service.monitor.Monitorable;
import org.osgi.service.monitor.MonitoringJob;
import org.osgi.service.monitor.StatusVariable;

import it.polito.elite.dog.core.monitoradmin.job.AbstractMonitoringJob;
import it.polito.elite.dog.core.monitoradmin.job.MonitoringJobVisitor;
import it.polito.elite.dog.core.monitoradmin.util.StatusVariablePath;
import it.polito.elite.dog.core.monitoradmin.util.Utils;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * MonitorAdmin common actions that are not related on Permissions
 *
 * @author dpishchukhin
 */
public class MonitorAdminCommon implements MonitorListener, MonitoringJobVisitor {
    private static final String SYMBOLIC_NAME_CHARACTERS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" +
                    "-_.";   // a subset of the characters allowed in DMT URIs

    private static final int MAX_ID_LENGTH = 32;

    public static final String PATH_PATERN = "%s/%s";

    /**
     * Set of StatusVariable paths for which events are disabled
     */
    private final Set<String> disabledPaths = new HashSet<String>();
    /**
     * List of run jobs
     */
    private final List<AbstractMonitoringJob> jobs = new ArrayList<AbstractMonitoringJob>();

    private final OsgiVisitor osgiVisitor;
    private final LogVisitor logVisitor;

    public MonitorAdminCommon(OsgiVisitor osgiVisitor, LogVisitor logVisitor) {
        this.osgiVisitor = osgiVisitor;
        this.logVisitor = logVisitor;
    }

    /**
     * Callback for notification of a <code>StatusVariable</code> change.
     *
     * @param monitorableId  the identifier of the <code>Monitorable</code>
     *                       instance reporting the change
     * @param statusVariable the <code>StatusVariable</code> that has changed
     * @throws java.lang.IllegalArgumentException
     *          if the specified monitorable
     *          ID is invalid (<code>null</code>, empty, or contains illegal
     *          characters) or points to a non-existing <code>Monitorable</code>,
     *          or if <code>statusVariable</code> is <code>null</code>
     */
    public void updated(String monitorableId, StatusVariable statusVariable) throws IllegalArgumentException {
        // validate monitorableId
        findMonitorableById(monitorableId);
        if (statusVariable == null) {
            throw new IllegalArgumentException("StatusVariable is null");
        }
        StatusVariablePath path = new StatusVariablePath(monitorableId, statusVariable.getID());
        if (isEventEnabled(path.getPath())) {
            fireEvent(monitorableId, statusVariable, null);
            logVisitor.info("Fire new SV update Event: " + path.getPath(), null);
        }
        // find jobs that handle this StatusVariable update event
        if (!jobs.isEmpty()) {
            synchronized (jobs) {
                for (AbstractMonitoringJob job : jobs) {
                    if (job.isHandleUpdateEvent(path.getPath())) {
                        job.handleUpdateEvent(monitorableId, statusVariable);
                    }
                }
            }
        }
    }

    /**
     * Get array with paths that are disabled for notificatios with switchEvents() method
     *
     * @return array with StatusVariable paths
     */
    public String[] getDisabledNotificationPaths() {
        return disabledPaths.toArray(new String[disabledPaths.size()]);
    }

    /**
     * Check if events are disabled for given path
     *
     * @param path path to check
     * @return if events are disabled - <code>true</code>, otherwise - <code>false</code>
     */
    private boolean isEventEnabled(String path) {
        return !disabledPaths.contains(path);
    }

    /**
     * Find Monitorable service by monitorable Id. Returns Monitorable service or
     * throws exception.
     * If multiple services exist for the same monitorableId,
     * the service with the highest ranking (as specified in its Constants.SERVICE_RANKING property)
     * is returned.
     * If there is a tie in ranking, the service with the lowest service ID (as specified
     * in its Constants.SERVICE_ID property); that is, the service that was regis-
     * tered first is returned.
     *
     * @param monitorableId id that is userd to filter services
     * @return Monitorable service with specified monitorableId.
     * @throws IllegalArgumentException monitorableId is <code>null</code> or monitorableId points
     *                                  to non-existing service or monitorableId is invalid
     */
    private Monitorable findMonitorableById(String monitorableId) throws IllegalArgumentException {
        if (monitorableId == null) {
            throw new IllegalArgumentException("MonitorableId is null");
        }

        if (!Utils.validatePathId(monitorableId)) {
            throw new IllegalArgumentException("MonitorableId is invalid");
        }

        ServiceReference<?> mostSuitableMonitorable = null;
        ServiceReference<?>[] serviceReferences = osgiVisitor.findMonitorableReferences(monitorableId);

        if (serviceReferences != null) {
            for (ServiceReference<?> serviceReference : serviceReferences) {
                if (mostSuitableMonitorable == null ||
                        mostSuitableMonitorable.compareTo(serviceReference) < 0) {
                    mostSuitableMonitorable = serviceReference;
                }
            }
        }
        if (mostSuitableMonitorable == null) {
            throw new IllegalArgumentException("Monitorable ID: " + monitorableId + " points to non-existing service");
        }
        return osgiVisitor.getService(mostSuitableMonitorable);
    }

    /**
     * Find Monitorable service reference by monitorable Id. Returns Monitorable service reference or
     * throws exception.
     * If multiple services exist for the same monitorableId,
     * the service with the highest ranking (as specified in its Constants.SERVICE_RANKING property)
     * is returned.
     * If there is a tie in ranking, the service with the lowest service ID (as specified
     * in its Constants.SERVICE_ID property); that is, the service that was regis-
     * tered first is returned.
     *
     * @param monitorableId id that is userd to filter services
     * @return Monitorable service reference with specified monitorableId.
     * @throws IllegalArgumentException monitorableId is <code>null</code> or monitorableId points
     *                                  to non-existing service or monitorableId is invalid
     */
    public ServiceReference<?> findMonitorableReferenceById(String monitorableId) throws IllegalArgumentException {
        if (monitorableId == null) {
            throw new IllegalArgumentException("MonitorableId is null");
        }

        if (!Utils.validatePathId(monitorableId)) {
            throw new IllegalArgumentException("MonitorableId is invalid");
        }

        ServiceReference<?> mostSuitableMonitorable = null;
        ServiceReference<?>[] serviceReferences = osgiVisitor.findMonitorableReferences(monitorableId);

        if (serviceReferences != null) {
            for (ServiceReference<?> serviceReference : serviceReferences) {
                if (mostSuitableMonitorable == null ||
                        mostSuitableMonitorable.compareTo(serviceReference) < 0) {
                    mostSuitableMonitorable = serviceReference;
                }
            }
        }
        if (mostSuitableMonitorable == null) {
            throw new IllegalArgumentException("Monitorable ID: " + monitorableId + " points to non-existing service");
        }
        return mostSuitableMonitorable;
    }

    /**
     * Returns array of the <code>Monitorable</code> <code>ServiceReference</code>s that are
     * currently registered.
     * <p/>
     * The returned array contains the <code>ServiceTeference</code>s in alphabetical order by service PID.
     * It cannot be <code>null</code>, an empty array is returned if no
     * <code>Monitorable</code> services are registered.
     *
     * @return the array of <code>Monitorable</code> names
     */
    public ServiceReference<?>[] getMonitorableReferences() {
        return getMonitorableReferences(null);
    }

    /**
     * Returns array of the <code>Monitorable</code> <code>ServiceReference</code>s that are
     * currently registered.
     * <p/>
     * The returned array contains the <code>ServiceTeference</code>s in alphabetical order by service PID.
     * It cannot be <code>null</code>, an empty array is returned if no
     * <code>Monitorable</code> services are registered.
     *
     * @param monitorableIdFilter <code>Monitorable</code> SERVICE_PID filter
     * @return the array of <code>Monitorable</code> names
     */
    public ServiceReference<?>[] getMonitorableReferences(String monitorableIdFilter) {
        // sorted set that contains Monitorable ServiceReferences
        SortedSet<ServiceReference<?>> names = new TreeSet<ServiceReference<?>>(new ServiceReferencePidComparator());
        ServiceReference<?>[] serviceReferences = osgiVisitor.findMonitorableReferences(monitorableIdFilter);
        if (serviceReferences != null) {
            for (ServiceReference<?> serviceReference : serviceReferences) {
                String pid = (String) serviceReference.getProperty(Constants.SERVICE_PID);
                if (pid != null && isValidId(pid)) {
                    names.add(serviceReference);
                }
            }
        }
        return names.toArray(new ServiceReference[names.size()]);
    }

    /**
     * Add a new <code>MonitoringJob</code> to the list
     *
     * @param job MonitoringJob
     */
    public void addJob(AbstractMonitoringJob job) {
        synchronized (jobs) {
            jobs.add(job);
        }
    }

    /**
     * Get list of all running <code>MonitoringJob</code>s
     *
     * @return list of running jobs
     */
    public List<MonitoringJob> getRunningJobs() {
        List<MonitoringJob> runningJobs = new ArrayList<MonitoringJob>();
        synchronized (jobs) {
            for (AbstractMonitoringJob job : jobs) {
                if (job.isRunning()) {
                    runningJobs.add(job);
                }
            }
        }
        return runningJobs;
    }

    /**
     * Returns a <code>StatusVariable</code> addressed by its full path.
     *
     * @param path the full path of the <code>StatusVariable</code> in
     *             [Monitorable_ID]/[StatusVariable_ID] format
     * @return the <code>StatusVariable</code> object
     * @throws java.lang.IllegalArgumentException
     *          if <code>path</code> is
     *          <code>null</code> or otherwise invalid, or points to a
     *          non-existing <code>StatusVariable</code>
     */
    public StatusVariable getStatusVariable(String path)
            throws IllegalArgumentException {
        logVisitor.debug("ENTRY: getStatusVariable: " + path, null);
        try {
            StatusVariablePath statusVariablePath = new StatusVariablePath(path);
            Monitorable monitorable = findMonitorableById(statusVariablePath.getMonitorableId());

            return monitorable.getStatusVariable(statusVariablePath.getStatusVariableId());
        } finally {
            logVisitor.debug("EXIT: getStatusVariable: " + path, null);
        }
    }

    /**
     * Returns a <code>StatusVariable</code> addressed by Monitorable service reference and its id.
     *
     * @param serviceReference <code>Monitorable</code> service reference
     * @param statusVariableId <code>StatusVariable</code> id
     * @return the <code>StatusVariable</code> object
     * @throws java.lang.IllegalArgumentException
     *          if points to a
     *          non-existing <code>StatusVariable</code>
     */
    public StatusVariable getStatusVariable(ServiceReference<?> serviceReference, String statusVariableId) {
        return osgiVisitor.getService(serviceReference).getStatusVariable(statusVariableId);
    }

    /**
     * Returns a <code>StatusVariable</code> description addressed by
     * Monitorable service reference and its id.
     *
     * @param serviceReference <code>Monitorable</code> service reference
     * @param statusVariableId <code>StatusVariable</code> id
     * @return the <code>StatusVariable</code> description
     * @throws java.lang.IllegalArgumentException
     *          if points to a
     *          non-existing <code>StatusVariable</code>
     */
    public String getDescription(ServiceReference<?> serviceReference, String statusVariableId) {
        return osgiVisitor.getService(serviceReference).getDescription(statusVariableId);
    }

    /**
     * Returns a <code>StatusVariable</code> notification flag addressed by
     * Monitorable service reference and its id.
     *
     * @param serviceReference <code>Monitorable</code> service reference
     * @param statusVariableId <code>StatusVariable</code> id
     * @return the <code>StatusVariable</code> notification flag
     * @throws java.lang.IllegalArgumentException
     *          if points to a
     *          non-existing <code>StatusVariable</code>
     */
    public boolean notifiesOnChange(ServiceReference<?> serviceReference, String statusVariableId) {
        return osgiVisitor.getService(serviceReference).notifiesOnChange(statusVariableId);
    }

    /**
     * Reset <code>StatusVariable</code> description addressed by
     * Monitorable service reference and its id.
     *
     * @param serviceReference <code>Monitorable</code> service reference
     * @param statusVariableId <code>StatusVariable</code> id
     * @return the <code>StatusVariable</code> description
     * @throws java.lang.IllegalArgumentException
     *          if points to a
     *          non-existing <code>StatusVariable</code>
     */
    public boolean resetStatusVariable(ServiceReference<?> serviceReference, String statusVariableId) {
        return osgiVisitor.getService(serviceReference).resetStatusVariable(statusVariableId);
    }


    /**
     * Cancel Job and remove it from jobs list
     *
     * @param job job to cancel
     */
    public void cancelJob(AbstractMonitoringJob job) {
        synchronized (jobs) {
            jobs.remove(job);
            job.cancel();
        }
    }

    /**
     * Cancel all jobs and clear the list
     */
    public void cancelAllJobs() {
        logVisitor.debug("ENTRY: cancelJobs", null);
        try {
            if (!jobs.isEmpty()) {
                synchronized (jobs) {
                    Iterator<AbstractMonitoringJob> iterator = jobs.iterator();
                    while (iterator.hasNext()) {
                        AbstractMonitoringJob job = iterator.next();
                        job.cancel();
                        iterator.remove();
                    }
                }
            }
        } finally {
            logVisitor.debug("ENTRY: cancelJobs", null);
        }
    }

    /**
     * Fire StatusVariable update event
     *
     * @param monitorableId  monitorableId
     * @param statusVariable status variable
     * @param initiator      initiator. if <code>null</code> - is not added to event
     */
    public void fireEvent(String monitorableId, StatusVariable statusVariable, String initiator) {
        Dictionary<String, String> eventProperties = new Hashtable<String, String>();
        eventProperties.put(ConstantsMonitorAdmin.MON_MONITORABLE_PID, monitorableId);
        eventProperties.put(ConstantsMonitorAdmin.MON_STATUSVARIABLE_NAME, statusVariable.getID());

        String value = null;
        switch (statusVariable.getType()) {
            case StatusVariable.TYPE_BOOLEAN:
                value = Boolean.toString(statusVariable.getBoolean());
                break;
            case StatusVariable.TYPE_FLOAT:
                value = Float.toString(statusVariable.getFloat());
                break;
            case StatusVariable.TYPE_INTEGER:
                value = Integer.toString(statusVariable.getInteger());
                break;
            case StatusVariable.TYPE_STRING:
                value = statusVariable.getString();
                break;
        }
        eventProperties.put(ConstantsMonitorAdmin.MON_STATUSVARIABLE_VALUE, value);
        if (initiator != null) {
            eventProperties.put(ConstantsMonitorAdmin.MON_LISTENER_ID, initiator);
        }

        Event event = new Event(ConstantsMonitorAdmin.TOPIC, eventProperties);
        try {
            osgiVisitor.postEvent(event);
        } catch (SecurityException e) {
            logVisitor.error("MonitorAdmin bundle does not have TopicPermission", e);
        }
    }

    /**
     * Switch on/off events
     *
     * @param paths <code>StatusVariable</code> paths
     * @param on     <code>false</code> if event sending should be switched off,
     *               <code>true</code> if it should be switched on for the given path
     */
    public void switchEvents(Set<String> paths, boolean on) {
        if (on) {
            disabledPaths.removeAll(paths);
        } else {
            disabledPaths.addAll(paths);
        }
    }


    /**
     * Returns the list of <code>StatusVariable</code> names published by a
     * <code>Monitorable</code> instance. Only those status variables are
     * listed where the following two conditions are met:
     * <ul>
     * <li>the specified <code>Monitorable</code> holds a
     * <code>MonitorPermission</code> for the status variable with the
     * <code>publish</code> action present
     * <li>the caller holds a <code>MonitorPermission</code> for
     * the status variable with the <code>read</code> action present
     * </ul>
     * All other status variables are silently ignored, their names are omitted
     * from the list.
     * <p/>
     * The returned array does not contain duplicates, and the elements are in
     * alphabetical order. It cannot be <code>null</code>, an empty array is
     * returned if no (authorized and readable) Status Variables are provided
     * by the given <code>Monitorable</code>.
     *
     * @param monitorableId the identifier of a <code>Monitorable</code>
     *                      instance
     * @return a list of <code>StatusVariable</code> objects names
     *         published by the specified <code>Monitorable</code>
     * @throws java.lang.IllegalArgumentException
     *          if <code>monitorableId</code>
     *          is <code>null</code> or otherwise invalid, or points to a
     *          non-existing <code>Monitorable</code>
     */
    public String[] getStatusVariableNames(String monitorableId) {
        Monitorable monitorable = findMonitorableById(monitorableId);
        String[] statusVariableNames = monitorable.getStatusVariableNames();

        List<String> result = new ArrayList<String>();

        for (String statusVariableName : statusVariableNames) {
            if (isValidId(statusVariableName)) {
                result.add(statusVariableName);
            }
        }
        return result.toArray(new String[result.size()]);
    }

    private static class ServiceReferencePidComparator implements Comparator<ServiceReference<?>> {
        public int compare(ServiceReference<?> o1, ServiceReference<?> o2) {
            String pid1 = (String) o1.getProperty(Constants.SERVICE_PID);
            String pid2 = (String) o2.getProperty(Constants.SERVICE_PID);
            return pid1.compareTo(pid2);
        }
    }

    /**
     * Validate <code>Monitorable</code> Id or <code>StatusVariable</code> name
     * @param id id
     * @return <code>false</code> - id is invalid, otherwise - <code>true</code>
     */
    public static boolean isValidId(String id) {

        byte[] nameBytes;
        try {
            nameBytes = id.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // never happens, "UTF-8" must always be supported
            return false;
        }
        if (nameBytes.length > MAX_ID_LENGTH) {
            return false;
        }


        if (id.equals(".") || id.equals("..")) {
            return false;
        }

        char[] chars = id.toCharArray();
        for (char aChar : chars) {
            if (SYMBOLIC_NAME_CHARACTERS.indexOf(aChar) == -1) {
                return false;
            }
        }
        return true;
    }

}
