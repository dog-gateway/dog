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

package it.polito.elite.dog.core.monitoradmin.job;

import org.osgi.service.monitor.MonitoringJob;
import org.osgi.service.monitor.StatusVariable;

import it.polito.elite.dog.core.monitoradmin.LogVisitor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Abstract MonitoringJob
 *
 * @author dmytro.pishchukhin
 */
public abstract class AbstractMonitoringJob implements MonitoringJob {
    protected MonitoringJobVisitor visitor;
    protected LogVisitor logVisitor;
    // job initiator
    private String initiator;
    // list of monitoring StatusVariables
    protected Set<String> statusVariablePaths = new HashSet<String>();
    // job state
    protected boolean isRunning;
    protected int schedule = 0;
    protected int count = 0;

    protected AbstractMonitoringJob(MonitoringJobVisitor visitor, LogVisitor logVisitor, String initiator,
                                    String[] statusVariablePaths, int schedule, int count) {
        this.visitor = visitor;
        this.logVisitor = logVisitor;
        this.initiator = initiator;
        this.statusVariablePaths.addAll(Arrays.asList(statusVariablePaths));
        this.schedule = schedule;
        this.count = count;
        this.isRunning = true;
    }

    protected AbstractMonitoringJob(MonitoringJobVisitor visitor, LogVisitor logVisitor, String initiator,
                                    String[] statusVariablePaths, int count) {
        this.visitor = visitor;
        this.logVisitor = logVisitor;
        this.initiator = initiator;
        this.statusVariablePaths.addAll(Arrays.asList(statusVariablePaths));
        this.count = count;
        this.isRunning = true;
    }

    public synchronized void stop() {
        if (isRunning) {
            isRunning = false;
            visitor.cancelJob(this);
        }
    }

    public String getInitiator() {
        return initiator;
    }

    public String[] getStatusVariableNames() {
        return statusVariablePaths.toArray(new String[statusVariablePaths.size()]);
    }

    public int getSchedule() {
        return schedule;
    }

    public int getReportCount() {
        return count;
    }

    public boolean isLocal() {
        return true;
    }

    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Cancel job internaly
     */
    public abstract void cancel();

    /**
     * Does job handle StatusVariable update event
     *
     * @param path StatusVariable path
     * @return <code>true</code> - handles, otherwise - <code>false</code>
     */
    public abstract boolean isHandleUpdateEvent(String path);

    /**
     * Handle StatusVariable update event
     *
     * @param monitorableId  monitorableId
     * @param statusVariable statusVariable
     */
    public abstract void handleUpdateEvent(String monitorableId, StatusVariable statusVariable);

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("{initiator='").append(initiator).append('\'');
        sb.append(", statusVariablePaths=").append(statusVariablePaths);
        sb.append(", schedule=").append(schedule);
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}
