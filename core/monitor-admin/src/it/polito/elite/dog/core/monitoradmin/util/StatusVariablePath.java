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

package it.polito.elite.dog.core.monitoradmin.util;

/**
 * Status Variable path object
 *
 * @author dmytro.pishchukhin
 */
public class StatusVariablePath {
    protected String monitorableId;
    protected String statusVariableId;
    protected String path;

    /**
     * Initialize object and parse input path value
     *
     * @param path path value
     * @throws IllegalArgumentException path is <code>null</code> or invalid
     */
    public StatusVariablePath(String path) throws IllegalArgumentException {
        String[] ids = parseIds(path);
        this.path = path;
        monitorableId = ids[0];
        statusVariableId = ids[1];
    }

    /**
     * Initialize object from monitorableId and status variable Id
     *
     * @param monitorableId    monitorableId value
     * @param statusVariableId status variable Id value
     * @throws IllegalArgumentException ids are <code>null</code> or invalid;
     */
    public StatusVariablePath(String monitorableId, String statusVariableId) throws IllegalArgumentException {
        if (monitorableId == null) {
            throw new IllegalArgumentException("MonitorableId is null");
        }
        if (statusVariableId == null) {
            throw new IllegalArgumentException("StatusVariableId is null");
        }
        if (!validateId(monitorableId)) {
            throw new IllegalArgumentException("MonitorableId is invalid");
        }
        if (!validateId(statusVariableId)) {
            throw new IllegalArgumentException("StatusVariableId is invalid");
        }
        this.monitorableId = monitorableId;
        this.statusVariableId = statusVariableId;
        path = this.monitorableId + '/' + this.statusVariableId;
    }

    public StatusVariablePath() {
    }

    /**
     * Get monitorable Id
     * @return monitorable Id
     */
    public String getMonitorableId() {
        return monitorableId;
    }

    /**
     * Get StatusVariable Id
     * @return StatusVariable Id
     */
    public String getStatusVariableId() {
        return statusVariableId;
    }

    /**
     * Get full path
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * Parse path and return non-nullable array (lenght = 2) with non-empty IDs values:
     * <li>resultArray[0] = monitorableId
     * <li>resultArray[1] = statusVariableId
     *
     * @param path status variable path
     * @return non-nullable array with ids
     *
     * @throws IllegalArgumentException path is <code>null</code> or invalid
     *                                  (contains more or less than one separator '/' or parsed IDs are empty or invalid)
     */
    protected String[] parseIds(String path) throws IllegalArgumentException {
        if (path == null) {
            throw new IllegalArgumentException("Path is null");
        }
        String[] parts = path.split("/");
        if (parts.length != 2 || !validateId(parts[0]) || !validateId(parts[1])) {
            throw new IllegalArgumentException("Path value is invalid: " + path);
        }
        return parts;
    }

    protected boolean validateId(String id) {
        return Utils.validatePathId(id);
    }
}
