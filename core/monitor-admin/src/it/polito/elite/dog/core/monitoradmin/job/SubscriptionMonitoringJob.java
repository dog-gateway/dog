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

import org.osgi.service.monitor.StatusVariable;

import it.polito.elite.dog.core.monitoradmin.LogVisitor;
import it.polito.elite.dog.core.monitoradmin.util.StatusVariablePath;

import java.util.HashMap;
import java.util.Map;

/**
 * Subscription MonitoringJob
 *
 * @author dmytro.pishchukhin
 */
public class SubscriptionMonitoringJob extends AbstractMonitoringJob {
    private Map<String, Integer> countStatesMap = new HashMap<String, Integer>();

    public SubscriptionMonitoringJob(MonitoringJobVisitor visitor, LogVisitor logVisitor, String initiator,
                                     String[] statusVariablePaths, int count) {
        super(visitor, logVisitor, initiator, statusVariablePaths, count);
        // initialize counts map
        for (String statusVariablePath : statusVariablePaths) {
            countStatesMap.put(statusVariablePath, 0);
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
        logVisitor.info("Job Canceled: " + this, null);
    }

    @Override
    public boolean isHandleUpdateEvent(String path) {
        return isRunning() && statusVariablePaths.contains(path);
    }

    @Override
    public void handleUpdateEvent(String monitorableId, StatusVariable statusVariable) {
        StatusVariablePath path = new StatusVariablePath(monitorableId, statusVariable.getID());
        int statusVariableChangesCount = countStatesMap.get(path.getPath());
        if ((statusVariableChangesCount + 1) == count) {
            visitor.fireEvent(monitorableId, statusVariable, getInitiator());
            countStatesMap.put(path.getPath(), 0);
        } else {
            countStatesMap.put(path.getPath(), statusVariableChangesCount + 1);
        }
    }
}