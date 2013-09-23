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
 * Logger interface. Is used to decouple loggers.
 *
 * @author dpishchukhin
 */
public interface LogVisitor {
    /**
     * Publish DEBUG message
     *
     * @param message   message
     * @param throwable exception
     */
    void debug(String message, Throwable throwable);

    /**
     * Publish INFO message
     *
     * @param message   message
     * @param throwable exception
     */
    void info(String message, Throwable throwable);

    /**
     * Publish WARNING message
     *
     * @param message   message
     * @param throwable exception
     */
    void warning(String message, Throwable throwable);

    /**
     * Publish ERROR message
     *
     * @param message   message
     * @param throwable exception
     */
    void error(String message, Throwable throwable);
}
