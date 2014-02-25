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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Scheduled MonitoringJob
 *
 * @author dmytro.pishchukhin
 */
public class ScheduledMonitoringJob extends AbstractMonitoringJob implements Runnable {
    private int measurementsTaken = 0;
    private ExecutorService executorService;

    public ScheduledMonitoringJob(MonitoringJobVisitor visitor, LogVisitor logVisitor, String initiator,
                                  String[] statusVariablePaths, int schedule, int count) {
        super(visitor, logVisitor, initiator, statusVariablePaths, schedule, count);
        executorService = Executors.newSingleThreadExecutor();
        executorService.submit(this);
    }

    @Override
    public void cancel() {
        isRunning = false;
        executorService.shutdownNow();
        logVisitor.info("Job Canceled: " + this, null);
    }

    @Override
    public boolean isHandleUpdateEvent(String path) {
        return false;
    }

    @Override
    public void handleUpdateEvent(String monitorableId, StatusVariable statusVariable) {
        // do nothing
    }

    public void run() {
        while (isRunning()) {
            // run loop to fetch StatusVariables values and fire events
            if (count == 0 || ++measurementsTaken < count) {
                try {
                    for (String path : statusVariablePaths) {
                        StatusVariablePath statusVariablePath = new StatusVariablePath(path);
                        StatusVariable statusVariable = visitor.getStatusVariable(statusVariablePath.getPath());
                        visitor.fireEvent(statusVariablePath.getMonitorableId(), statusVariable, getInitiator());
                    }
                    try {
                        TimeUnit.SECONDS.sleep(schedule);
                    } catch (InterruptedException e) {
                        return;
                    }
                } catch (IllegalArgumentException e) {
                    // it seems that one StatusVariable is unregistered
                    stop();
                }
            } else {
                stop();
            }
        }
    }
}
