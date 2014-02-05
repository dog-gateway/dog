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

/**
 * Constants
 *
 * @author dmytro.pishchukhin
 */
public interface ConstantsMonitorAdmin {
    /**
     * <code>MonitorAdmin</code> events topic
     */
    public final static String TOPIC = "org/osgi/service/monitor/MonitorEvent"; //"org/osgi/service/monitor";
    /**
     * <code>Monitorable</code> ID
     */
    public final static String MON_MONITORABLE_PID = "mon.monitorable.pid";
    /**
     * <code>StatusVariable</code> name
     */
    public final static String MON_STATUSVARIABLE_NAME = "mon.statusvariable.name";
    /**
     * <code>StatusVariable</code> value
     */
    public final static String MON_STATUSVARIABLE_VALUE = "mon.statusvariable.value";
    /**
     * Initiator
     */
    public final static String MON_LISTENER_ID = "mon.listener.id";
}
