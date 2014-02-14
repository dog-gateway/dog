/*
 * Dog - Addons
 * 
 * Copyright (c) 2013-2014 Claudio Degioanni, Luigi De Russis
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
package it.polito.elite.dog.addons.h2eventstore.dao;

public class DaoConstants {

	final static String SCHEMA = "CREATE TABLE EVENT(NAME VARCHAR(100), VALUE DECIMAL, UNIT VARCHAR(5), TIMESTAMP TIMESTAMP);";

	final static String INSERT = "INSERT INTO EVENT (NAME, VALUE, UNIT, TIMESTAMP) VALUES (?, ?, ?, ?);";
}
