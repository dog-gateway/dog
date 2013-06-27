/*
 * 
 * Copyright [2013] [Claudio Degioanni claudiodegio@gmail.com]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *              http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.polito.elite.dog.addons.h2eventstore.dao;

import java.sql.SQLException;
import java.util.Date;

import javax.measure.Measure;

public interface MeasureDao {

	void insert(String name, Measure<?, ?> value, Date timestamp) throws SQLException;

	void close() throws SQLException;
}
