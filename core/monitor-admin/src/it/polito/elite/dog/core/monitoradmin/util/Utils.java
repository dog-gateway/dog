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

import org.osgi.framework.Constants;

import java.util.regex.Pattern;

/**
 * Utils class
 *
 * @author dmytro.pishchukhin
 */
public class Utils {
    /**
     * MonitorableId and Status variable name validate pattern (OSGi core 1.3.2: symbolic-name)
     */
    private static final Pattern PATH_ID_VALIDATE_PATTERN = Pattern.compile("((\\w|_|-)+)(\\.(\\w|_|-)+)*");
    /**
     * MonitorableId and Status variable name validate pattern (OSGi CMPN 119.6.1: wildcard-pid)
     */
    private static final Pattern FILTER_ID_VALIDATE_PATTERN = Pattern.compile("(\\*)|(((\\w|_|-)+)(\\.(\\w|_|-)*)*)(\\*)?");

    /**
     * Create service filter for given monitorable Id
     * @param monitorableId monitorable id
     * @return filter
     */
    public static String createServicePidFilter(String monitorableId) {
        StringBuilder builder = new StringBuilder();
        builder.append('(');
        builder.append(Constants.SERVICE_PID);
        builder.append('=');
        builder.append(monitorableId);
        builder.append(')');
        return builder.toString();
    }

    /**
     * Validate Path Id
     * @param id id
     * @return validation result
     */
    public static boolean validatePathId(String id) {
        return PATH_ID_VALIDATE_PATTERN.matcher(id).matches();
    }

    /**
     * Validate Path Filter Id
     * @param id id
     * @return validation result
     */
    public static boolean validatePathFilterId(String id) {
        return FILTER_ID_VALIDATE_PATTERN.matcher(id).matches();
    }
}
