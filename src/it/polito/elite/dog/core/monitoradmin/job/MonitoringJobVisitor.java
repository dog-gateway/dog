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

/**
 * Interface to access some internal MonitorAdminImpl from Jobs
 *
 * @author dmytro.pishchukhin
 */
public interface MonitoringJobVisitor {
    /**
     * Get status variable by path
     *
     * @param path path
     * @return StatusVariable by path
     *
     * @throws IllegalArgumentException path is invalid
     * @throws SecurityException        some problems with security permissions
     * @see org.osgi.service.monitor.Monitorable#getStatusVariable(String)
     */
    StatusVariable getStatusVariable(String path) throws IllegalArgumentException, SecurityException;

    /**
     * Cancel Monitoring job
     * @param job job
     */
    void cancelJob(AbstractMonitoringJob job);

    /**
     * Fire event with given parameters
     * @param monitorableId monitorable id
     * @param statusVariable StatusVariable value
     * @param initiator initiator
     */
    void fireEvent(String monitorableId, StatusVariable statusVariable, String initiator);
}
