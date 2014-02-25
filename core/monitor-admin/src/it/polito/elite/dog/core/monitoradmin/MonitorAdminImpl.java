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
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.service.monitor.*;

import it.polito.elite.dog.core.monitoradmin.job.ScheduledMonitoringJob;
import it.polito.elite.dog.core.monitoradmin.job.SubscriptionMonitoringJob;
import it.polito.elite.dog.core.monitoradmin.util.StatusVariablePath;
import it.polito.elite.dog.core.monitoradmin.util.StatusVariablePathFilter;

import java.util.*;

/**
 * MonitorAdmin implementation
 *
 * @author dmytro.pishchukhin
 */
public class MonitorAdminImpl implements MonitorAdmin {
    private static final String STARTJOB_PERMISSION_PATTERN = String.format("%s:%%d", MonitorPermission.STARTJOB);

    private final LogVisitor logVisitor;
    private final MonitorAdminCommon common;
    private final Bundle consumer;

    /**
     * Initialize MonitorAdmin implementation instance
     *
     * @param logVisitor loggers visitor
     * @param common     commons actions
     * @param consumer   bundle-consumer
     */
    public MonitorAdminImpl(LogVisitor logVisitor, MonitorAdminCommon common, Bundle consumer) {
        this.logVisitor = logVisitor;
        this.common = common;
        this.consumer = consumer;
    }

    /**
     * Returns a <code>StatusVariable</code> addressed by its full path.
     * The entity which queries a <code>StatusVariable</code> needs to hold
     * <code>MonitorPermission</code> for the given target with the
     * <code>read</code> action present.
     *
     * @param path the full path of the <code>StatusVariable</code> in
     *             [Monitorable_ID]/[StatusVariable_ID] format
     * @return the <code>StatusVariable</code> object
     * @throws java.lang.IllegalArgumentException
     *                                     if <code>path</code> is
     *                                     <code>null</code> or otherwise invalid, or points to a
     *                                     non-existing <code>StatusVariable</code>
     * @throws java.lang.SecurityException if the caller does not hold a
     *                                     <code>MonitorPermission</code> for the
     *                                     <code>StatusVariable</code> specified by <code>path</code>
     *                                     with the <code>read</code> action present
     */
    public StatusVariable getStatusVariable(String path)
            throws IllegalArgumentException, SecurityException {
        logVisitor.debug("ENTRY: getStatusVariable: " + path, null);
        try {
            StatusVariablePath statusVariablePath = new StatusVariablePath(path);
            ServiceReference<?> serviceReference = common.findMonitorableReferenceById(statusVariablePath.getMonitorableId());

            checkPermissions(statusVariablePath, serviceReference, MonitorPermission.PUBLISH, MonitorPermission.READ);

            return common.getStatusVariable(serviceReference, statusVariablePath.getStatusVariableId());
        } finally {
            logVisitor.debug("EXIT: getStatusVariable: " + path, null);
        }
    }

    /**
     * Check permissions for StatusVariable path
     *
     * @param statusVariablePath path
     * @param serviceReference   Monitorable service reference
     * @param producerPermission producer permission action
     * @param consumerPermission consumer permission action
     * @throws java.lang.IllegalArgumentException
     *                                     if <code>path</code> is
     *                                     <code>null</code> or otherwise invalid, or points to a
     *                                     non-existing <code>StatusVariable</code> (SV is not published)
     * @throws java.lang.SecurityException if the caller does not hold a
     *                                     <code>MonitorPermission</code> for the
     *                                     <code>StatusVariable</code> specified by <code>path</code>
     *                                     with the <code>read</code> action present
     */
    private void checkPermissions(StatusVariablePath statusVariablePath, ServiceReference<?> serviceReference, String producerPermission, String consumerPermission) {
        String[] variableNames = common.getStatusVariableNames(statusVariablePath.getMonitorableId());

        Collection<String> producerVariables = filterVariableNames(statusVariablePath.getMonitorableId(), variableNames, serviceReference.getBundle(), producerPermission);
        if (!producerVariables.contains(statusVariablePath.getStatusVariableId())) {
            throw new IllegalArgumentException(statusVariablePath.getPath() + " StatusVariable is unavailable");
        }

        Collection<String> consumerVariables = filterVariableNames(statusVariablePath.getMonitorableId(), variableNames, consumer, consumerPermission);
        if (!consumerVariables.contains(statusVariablePath.getStatusVariableId())) {
            throw new SecurityException(consumerPermission + " permissions not set for StatusVariable: " + statusVariablePath.getPath());
        }
    }

    /**
     * Returns a human readable description of the given
     * <code>StatusVariable</code>. The <code>null</code> value may be returned
     * if there is no description for the given <code>StatusVariable</code>.
     * <p/>
     * The entity that queries a <code>StatusVariable</code> needs to hold
     * <code>MonitorPermission</code> for the given target with the
     * <code>read</code> action present.
     *
     * @param path the full path of the <code>StatusVariable</code> in
     *             [Monitorable_ID]/[StatusVariable_ID] format
     * @return the human readable description of this
     *         <code>StatusVariable</code> or <code>null</code> if it is not
     *         set
     * @throws java.lang.IllegalArgumentException
     *                                     if <code>path</code> is
     *                                     <code>null</code> or otherwise invalid, or points to a
     *                                     non-existing <code>StatusVariable</code>
     * @throws java.lang.SecurityException if the caller does not hold a
     *                                     <code>MonitorPermission</code> for the
     *                                     <code>StatusVariable</code> specified by <code>path</code>
     *                                     with the <code>read</code> action present
     */
    public String getDescription(String path)
            throws IllegalArgumentException, SecurityException {
        logVisitor.debug("ENTRY: getDescription: " + path, null);
        try {
            StatusVariablePath statusVariablePath = new StatusVariablePath(path);
            ServiceReference<?> serviceReference = common.findMonitorableReferenceById(statusVariablePath.getMonitorableId());

            checkPermissions(statusVariablePath, serviceReference, MonitorPermission.PUBLISH, MonitorPermission.READ);

            return common.getDescription(serviceReference, statusVariablePath.getStatusVariableId());
        } finally {
            logVisitor.debug("EXIT: getDescription: " + path, null);
        }
    }

    /**
     * Returns the names of the <code>Monitorable</code> services that are
     * currently registered. The <code>Monitorable</code> instances are not
     * accessible through the <code>MonitorAdmin</code>, so that requests to
     * individual status variables can be filtered with respect to the
     * publishing rights of the <code>Monitorable</code> and the reading
     * rights of the caller.
     * <p/>
     * The returned array contains the names in alphabetical order. It cannot be
     * <code>null</code>, an empty array is returned if no
     * <code>Monitorable</code> services are registered.
     *
     * @return the array of <code>Monitorable</code> names
     */
    public String[] getMonitorableNames() {
        logVisitor.debug("ENTRY: getMonitorableNames", null);
        try {
            ServiceReference<?>[] serviceReferences = common.getMonitorableReferences();
            SortedSet<String> names = new TreeSet<String>();
            for (ServiceReference<?> serviceReference : serviceReferences) {
                String pid = (String) serviceReference.getProperty(Constants.SERVICE_PID);
                String[] variableNames = common.getStatusVariableNames(pid);
                // monitorable contains status variable - check permissions
                if (variableNames.length > 0) {
                    Collection<String> producerPublishedVariables = filterVariableNames(pid, variableNames, serviceReference.getBundle(), MonitorPermission.PUBLISH);
                    Collection<String> consumerReadVariables = filterVariableNames(pid, variableNames, consumer, MonitorPermission.READ);
                    if (!Collections.disjoint(producerPublishedVariables, consumerReadVariables)) {
                        names.add(pid);
                    }
                } else {
                    // monitorable does not contain status variable - just add it to the list
                    names.add(pid);
                }
            }
            return names.toArray(new String[names.size()]);
        } finally {
            logVisitor.debug("EXIT: getMonitorableNames", null);
        }
    }

    /**
     * Filter <code>StatusVariable</code> names by <code>MonitorPermission</code> action and for given <code>Bundle</code>
     *
     * @param pid              <code>Monitorable</code> service PID
     * @param variableNames    list of <code>StatusVariable</code> names
     * @param bundle           <code>Bundle</code> for permission check
     * @param permissionAction <code>MonitorPermission</code> action
     * @return filtered collection of <code>StatusVariable</code> names
     */
    private Collection<String> filterVariableNames(String pid, String[] variableNames, Bundle bundle, String permissionAction) {
        List<String> result = new ArrayList<String>();
        if (bundle != null) {
            for (String variableName : variableNames) {
                try {
                    if (bundle.hasPermission(new MonitorPermission(String.format(MonitorAdminCommon.PATH_PATERN, pid, variableName), permissionAction))) {
                        result.add(variableName);
                    }
                } catch (IllegalArgumentException e) {
                    logVisitor.debug("Unable to check permission", e);
                }
            }
        } else {
            result.addAll(Arrays.asList(variableNames));
        }
        return result;
    }

    /**
     * Returns the <code>StatusVariable</code> objects published by a
     * <code>Monitorable</code> instance. The <code>StatusVariables</code>
     * will hold the values taken at the time of this method call. Only those
     * status variables are returned where the following two conditions are met:
     * <ul>
     * <li>the specified <code>Monitorable</code> holds a
     * <code>MonitorPermission</code> for the status variable with the
     * <code>publish</code> action present
     * <li>the caller holds a <code>MonitorPermission</code> for the status
     * variable with the <code>read</code> action present
     * </ul>
     * All other status variables are silently ignored, they are omitted from
     * the result.
     * <p/>
     * The elements in the returned array are in no particular order. The return
     * value cannot be <code>null</code>, an empty array is returned if no
     * (authorized and readable) Status Variables are provided by the given
     * <code>Monitorable</code>.
     *
     * @param monitorableId the identifier of a <code>Monitorable</code>
     *                      instance
     * @return a list of <code>StatusVariable</code> objects published
     *         by the specified <code>Monitorable</code>
     * @throws java.lang.IllegalArgumentException
     *          if <code>monitorableId</code>
     *          is <code>null</code> or otherwise invalid, or points to a
     *          non-existing <code>Monitorable</code>
     */
    public StatusVariable[] getStatusVariables(String monitorableId)
            throws IllegalArgumentException {
        logVisitor.debug("ENTRY: getStatusVariables: " + monitorableId, null);
        try {
            List<StatusVariable> result = new ArrayList<StatusVariable>();

            Set<String> availableNames = new TreeSet<String>();

            ServiceReference<?> serviceReference = common.findMonitorableReferenceById(monitorableId);
            String[] variableNames = common.getStatusVariableNames(monitorableId);
            Collection<String> producerPublishedVariables = filterVariableNames(monitorableId, variableNames, serviceReference.getBundle(), MonitorPermission.PUBLISH);
            Collection<String> consumerReadVariables = filterVariableNames(monitorableId, variableNames, consumer, MonitorPermission.READ);

            availableNames.addAll(producerPublishedVariables);
            availableNames.retainAll(consumerReadVariables);

            for (String availableName : availableNames) {
                result.add(common.getStatusVariable(serviceReference, availableName));
            }

            return result.toArray(new StatusVariable[result.size()]);
        } finally {
            logVisitor.debug("EXIT: getStatusVariables: " + monitorableId, null);
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
    public String[] getStatusVariableNames(String monitorableId)
            throws IllegalArgumentException {
        logVisitor.debug("ENTRY: getStatusVariableNames: " + monitorableId, null);
        try {
            Set<String> result = new TreeSet<String>();

            ServiceReference<?> serviceReference = common.findMonitorableReferenceById(monitorableId);
            String[] variableNames = common.getStatusVariableNames(monitorableId);
            Collection<String> producerPublishedVariables = filterVariableNames(monitorableId, variableNames, serviceReference.getBundle(), MonitorPermission.PUBLISH);
            Collection<String> consumerReadVariables = filterVariableNames(monitorableId, variableNames, consumer, MonitorPermission.READ);

            result.addAll(producerPublishedVariables);
            result.retainAll(consumerReadVariables);

            return result.toArray(new String[result.size()]);
        } finally {
            logVisitor.debug("EXIT: getStatusVariableNames: " + monitorableId, null);
        }
    }

    /**
     * Issues a request to reset a given <code>StatusVariable</code>.
     * Depending on the semantics of the <code>StatusVariable</code> this call
     * may or may not succeed: it makes sense to reset a counter to its starting
     * value, but e.g. a <code>StatusVariable</code> of type String might not
     * have a meaningful default value. Note that for numeric
     * <code>StatusVariable</code>s the starting value may not necessarily be
     * 0. Resetting a <code>StatusVariable</code> triggers a monitor event if
     * the <code>StatusVariable</code> supports update notifications.
     * <p/>
     * The entity that wants to reset the <code>StatusVariable</code> needs to
     * hold <code>MonitorPermission</code> with the <code>reset</code>
     * action present. The target field of the permission must match the
     * <code>StatusVariable</code> name to be reset.
     *
     * @param path the identifier of the <code>StatusVariable</code> in
     *             [Monitorable_id]/[StatusVariable_id] format
     * @return <code>true</code> if the <code>Monitorable</code> could
     *         successfully reset the given <code>StatusVariable</code>,
     *         <code>false</code> otherwise
     * @throws java.lang.IllegalArgumentException
     *                                     if <code>path</code> is
     *                                     <code>null</code> or otherwise invalid, or points to a
     *                                     non-existing <code>StatusVariable</code>
     * @throws java.lang.SecurityException if the caller does not hold
     *                                     <code>MonitorPermission</code> with the <code>reset</code>
     *                                     action or if the specified <code>StatusVariable</code> is not
     *                                     allowed to be reset as per the target field of the permission
     */
    public boolean resetStatusVariable(String path)
            throws IllegalArgumentException, SecurityException {
        logVisitor.debug("ENTRY: resetStatusVariable: " + path, null);
        try {
            StatusVariablePath statusVariablePath = new StatusVariablePath(path);
            ServiceReference<?> serviceReference = common.findMonitorableReferenceById(statusVariablePath.getMonitorableId());

            checkPermissions(statusVariablePath, serviceReference, MonitorPermission.PUBLISH, MonitorPermission.RESET);

            return common.resetStatusVariable(serviceReference, statusVariablePath.getStatusVariableId());
        } finally {
            logVisitor.debug("EXIT: resetStatusVariable: " + path, null);
        }
    }

    /**
     * Switches event sending on or off for the specified
     * <code>StatusVariable</code>s. When the <code>MonitorAdmin</code> is
     * notified about a <code>StatusVariable</code> being updated it sends an
     * event unless this feature is switched off. Note that events within a
     * monitoring job can not be switched off. The event sending state of the
     * <code>StatusVariables</code> must not be persistently stored. When a
     * <code>StatusVariable</code> is registered for the first time in a
     * framework session, its event sending state is set to ON by default.
     * <p/>
     * Usage of the "*" wildcard is allowed in the path argument of this method
     * as a convenience feature. The wildcard can be used in either or both path
     * fragments, but only at the end of the fragments.  The semantics of the
     * wildcard is that it stands for any matching <code>StatusVariable</code>
     * at the time of the method call, it does not affect the event sending
     * status of <code>StatusVariable</code>s which are not yet registered. As
     * an example, when the <code>switchEvents("MyMonitorable/*", false)</code>
     * method is executed, event sending from all <code>StatusVariables</code>
     * of the MyMonitorable service are switched off. However, if the
     * MyMonitorable service starts to publish a new <code>StatusVariable</code>
     * later, it's event sending status is on by default.
     *
     * @param path the identifier of the <code>StatusVariable</code>(s) in
     *             [Monitorable_id]/[StatusVariable_id] format, possibly with the
     *             "*" wildcard at the end of either path fragment
     * @param on   <code>false</code> if event sending should be switched off,
     *             <code>true</code> if it should be switched on for the given path
     * @throws java.lang.SecurityException if the caller does not hold
     *                                     <code>MonitorPermission</code> with the
     *                                     <code>switchevents</code> action or if there is any
     *                                     <code>StatusVariable</code> in the <code>path</code> field for
     *                                     which it is not allowed to switch event sending on or off as per
     *                                     the target field of the permission
     * @throws java.lang.IllegalArgumentException
     *                                     if <code>path</code> is
     *                                     <code>null</code> or otherwise invalid, or points to a
     *                                     non-existing <code>StatusVariable</code>
     */
    public void switchEvents(String path, boolean on)
            throws IllegalArgumentException, SecurityException {
        logVisitor.debug("ENTRY: switchEvents: " + path + ", " + on, null);
        try {
            StatusVariablePathFilter filter = new StatusVariablePathFilter(path);

            Set<String> paths = new TreeSet<String>();

            ServiceReference<?>[] monitorableReferences = common.getMonitorableReferences(filter.getMonitorableIdFilter());
            for (ServiceReference<?> monitorableReference : monitorableReferences) {
                String pid = (String) monitorableReference.getProperty(Constants.SERVICE_PID);
                String[] statusVariableNames = common.getStatusVariableNames(pid);
                for (String statusVariableName : statusVariableNames) {
                    if (filter.match(pid, statusVariableName)) {
                        checkPermissions(new StatusVariablePath(pid, statusVariableName), monitorableReference,
                                MonitorPermission.PUBLISH, MonitorPermission.SWITCHEVENTS);
                        paths.add(String.format(MonitorAdminCommon.PATH_PATERN, pid, statusVariableName));
                    }
                }
            }

            if (paths.isEmpty()) {
                throw new IllegalArgumentException(String.format("%s does not point any existing StatusVariables", path));
            }
            common.switchEvents(paths, on);
        } finally {
            logVisitor.debug("EXIT: switchEvents: " + path + ", " + on, null);
        }
    }

    /**
     * Starts a time based <code>MonitoringJob</code> with the parameters
     * provided. Monitoring events will be sent according to the specified
     * schedule. All specified <code>StatusVariable</code>s must exist when the
     * job is started. The initiator string is used in the
     * <code>mon.listener.id</code> field of all events triggered by the job,
     * to allow filtering the events based on the initiator.
     * <p/>
     * The <code>schedule</code> parameter specifies the time in seconds
     * between two measurements, it must be greater than 0.  The first
     * measurement will be taken when the timer expires for the first time, not
     * when this method is called.
     * <p/>
     * The <code>count</code> parameter defines the number of measurements to be
     * taken, and must either be a positive integer, or 0 if the measurement is
     * to run until explicitly stopped.
     * <p/>
     * The entity which initiates a <code>MonitoringJob</code> needs to hold
     * <code>MonitorPermission</code> for all the specified target
     * <code>StatusVariable</code>s with the <code>startjob</code> action
     * present. If the permission's action string specifies a minimal sampling
     * interval then the <code>schedule</code> parameter should be at least as
     * great as the value in the action string.
     *
     * @param initiator       the identifier of the entity that initiated the job
     * @param statusVariables the list of <code>StatusVariable</code>s to be
     *                        monitored, with each <code>StatusVariable</code> name given in
     *                        [Monitorable_PID]/[StatusVariable_ID] format
     * @param schedule        the time in seconds between two measurements
     * @param count           the number of measurements to be taken, or 0 for the
     *                        measurement to run until explicitly stopped
     * @return the successfully started job object, cannot be <code>null</code>
     * @throws java.lang.IllegalArgumentException
     *                                     if the list of
     *                                     <code>StatusVariable</code> names contains an invalid or
     *                                     non-existing <code>StatusVariable</code>; if
     *                                     <code>initiator</code> is <code>null</code> or empty; or if the
     *                                     <code>schedule</code> or <code>count</code> parameters are
     *                                     invalid
     * @throws java.lang.SecurityException if the caller does not hold
     *                                     <code>MonitorPermission</code> for all the specified
     *                                     <code>StatusVariable</code>s, with the <code>startjob</code>
     *                                     action present, or if the permission does not allow starting the
     *                                     job with the given frequency
     */
    public MonitoringJob startScheduledJob(String initiator, String[] statusVariables, int schedule, int count)
            throws IllegalArgumentException, SecurityException {
        logVisitor.debug("ENTRY: startScheduledJob: " + initiator, null);
        try {
            if (initiator == null) {
                throw new IllegalArgumentException("Initiator is null");
            }
            if (statusVariables == null) {
                throw new IllegalArgumentException("StatusVariables are null");
            }
            if (schedule <= 0) {
                throw new IllegalArgumentException("Schedule is invalid: " + count);
            }
            if (count < 0) {
                throw new IllegalArgumentException("Count is invalid: " + count);
            }
            for (String path : statusVariables) {
                StatusVariablePath statusVariablePath = new StatusVariablePath(path);
                ServiceReference<?> monitorableReference = common.findMonitorableReferenceById(statusVariablePath.getMonitorableId());
                String pid = (String) monitorableReference.getProperty(Constants.SERVICE_PID);

                checkPermissions(new StatusVariablePath(pid, statusVariablePath.getStatusVariableId()), monitorableReference,
                        MonitorPermission.PUBLISH, String.format(STARTJOB_PERMISSION_PATTERN, schedule));
            }
            ScheduledMonitoringJob job = new ScheduledMonitoringJob(common, logVisitor, initiator,
                    statusVariables, schedule, count);
            common.addJob(job);
            logVisitor.info("New Scheduled Job is started: " + initiator, null);
            return job;
        } finally {
            logVisitor.debug("EXIT: startScheduledJob: " + initiator, null);
        }
    }

    /**
     * Starts a change based <code>MonitoringJob</code> with the parameters
     * provided. Monitoring events will be sent when the
     * <code>StatusVariable</code>s of this job are updated. All specified
     * <code>StatusVariable</code>s must exist when the job is started, and
     * all must support update notifications. The initiator string is used in
     * the <code>mon.listener.id</code> field of all events triggered by the
     * job, to allow filtering the events based on the initiator.
     * <p/>
     * The <code>count</code> parameter specifies the number of changes that
     * must happen to a <code>StatusVariable</code> before a new notification is
     * sent, this must be a positive integer.
     * <p/>
     * The entity which initiates a <code>MonitoringJob</code> needs to hold
     * <code>MonitorPermission</code> for all the specified target
     * <code>StatusVariable</code>s with the <code>startjob</code> action
     * present.
     *
     * @param initiator       the identifier of the entity that initiated the job
     * @param statusVariables the list of <code>StatusVariable</code>s to be
     *                        monitored, with each <code>StatusVariable</code> name given in
     *                        [Monitorable_PID]/[StatusVariable_ID] format
     * @param count           the number of changes that must happen to a
     *                        <code>StatusVariable</code> before a new notification is sent
     * @return the successfully started job object, cannot be <code>null</code>
     * @throws java.lang.IllegalArgumentException
     *                                     if the list of
     *                                     <code>StatusVariable</code> names contains an invalid or
     *                                     non-existing <code>StatusVariable</code>, or one that does not
     *                                     support notifications; if the <code>initiator</code> is
     *                                     <code>null</code> or empty; or if <code>count</code> is invalid
     * @throws java.lang.SecurityException if the caller does not hold
     *                                     <code>MonitorPermission</code> for all the specified
     *                                     <code>StatusVariable</code>s, with the <code>startjob</code>
     *                                     action present
     */
    public MonitoringJob startJob(String initiator, String[] statusVariables, int count)
            throws IllegalArgumentException, SecurityException {
        logVisitor.debug("ENTRY: startJob: " + initiator, null);
        try {
            if (initiator == null) {
                throw new IllegalArgumentException("Initiator is null");
            }
            if (statusVariables == null) {
                throw new IllegalArgumentException("StatusVariables are null");
            }
            if (count <= 0) {
                throw new IllegalArgumentException("Count is invalid: " + count);
            }
            for (String path : statusVariables) {
                StatusVariablePath statusVariablePath = new StatusVariablePath(path);
                ServiceReference<?> monitorableReference = common.findMonitorableReferenceById(statusVariablePath.getMonitorableId());
                String pid = (String) monitorableReference.getProperty(Constants.SERVICE_PID);

                if (!common.notifiesOnChange(monitorableReference, statusVariablePath.getStatusVariableId())) {
                    throw new IllegalArgumentException("StatusVariable: " + path + " does not support notifications");
                }

                checkPermissions(new StatusVariablePath(pid, statusVariablePath.getStatusVariableId()), monitorableReference,
                        MonitorPermission.PUBLISH, MonitorPermission.STARTJOB);
            }
            SubscriptionMonitoringJob job = new SubscriptionMonitoringJob(common, logVisitor, initiator, statusVariables, count);

            common.addJob(job);

            logVisitor.info("New Subscription Job is started: " + initiator, null);
            return job;
        } finally {
            logVisitor.debug("EXIT: startJob: " + initiator, null);
        }
    }

    /**
     * Returns the list of currently running <code>MonitoringJob</code>s.
     * Jobs are only visible to callers that have the necessary permissions: to
     * receive a Monitoring Job in the returned list, the caller must hold all
     * permissions required for starting the job.  This means that if the caller
     * does not have <code>MonitorPermission</code> with the proper
     * <code>startjob</code> action for all the Status Variables monitored by a
     * job, then that job will be silently omitted from the results.
     * <p/>
     * The returned array cannot be <code>null</code>, an empty array is
     * returned if there are no running jobs visible to the caller at the time
     * of the call.
     *
     * @return the list of running jobs visible to the caller
     */
    public MonitoringJob[] getRunningJobs() {
        logVisitor.debug("ENTRY: getRunningJobs", null);
        try {
            List<MonitoringJob> runningJobs = common.getRunningJobs();
            List<MonitoringJob> result = new ArrayList<MonitoringJob>();
            for (MonitoringJob runningJob : runningJobs) {
                String[] statusVariableNames = runningJob.getStatusVariableNames();
                String action = String.format(STARTJOB_PERMISSION_PATTERN, runningJob.getSchedule());
                boolean hasPermissions = true;
                if (consumer != null) {
                    for (String statusVariableName : statusVariableNames) {
                        if (!consumer.hasPermission(new MonitorPermission(statusVariableName, action))) {
                            hasPermissions = false;
                            break;
                        }
                    }
                }
                if (hasPermissions) {
                    result.add(runningJob);
                }
            }
            return result.toArray(new MonitoringJob[result.size()]);
        } finally {
            logVisitor.debug("EXIT: getRunningJobs", null);
        }
    }
}
