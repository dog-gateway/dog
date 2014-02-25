/*
 * Dog - Core
 * 
 * Copyright (c) 2011-2013 Dario Bonino
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
package it.polito.elite.dog.core.library.semantic.util;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

/**
 * This class provides a utility for simplifying the execution of SPARQL queries.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 *
 */
public class SimplifiedQueryExecutor
{
	/**
	 * Executes a given SPARQL query against the given model
	 * 
	 * @param queryText
	 *            the sparql query text
	 * @return the Jena ResultSet holding the query results
	 */
	public static ResultSet execQuery(String queryText, OntModel model)
	{
		// sparql query handler
		Query query = QueryFactory.create(queryText);
		
		// execution handler
		QueryExecution queryExec = QueryExecutionFactory.create(query, model);
		
		// exec the query and gather results
		long time = System.currentTimeMillis();
		ResultSet queryResult = queryExec.execSelect();
		time = System.currentTimeMillis() - time;
		
		return queryResult;
	}
	
	/**
	 * Executes a given SPARQL query against the given ontology model. Dumps the
	 * query results inside an XML string
	 * 
	 * @param queryText
	 *            the sparql query to be executed
	 * @return the query result in XML format
	 */
	public static String dumpQuery(String queryText, OntModel model)
	{
		// sparql query handler
		Query query = QueryFactory.create(queryText);
		
		// execution handler
		QueryExecution queryExec = QueryExecutionFactory.create(query, model);
		
		// exec the query and gather results
		long time = System.currentTimeMillis();
		ResultSet queryResult = queryExec.execSelect();
		time = System.currentTimeMillis() - time;
		
		System.out.println("Query: \n" + queryText);
		System.out.println("Elapsed time: " + time);
		
		// return results
		String out = ResultSetFormatter.asXMLString(queryResult);
		
		return out;
	}
	
	/**
	 * Execute an ASK query.
	 * 
	 * @param queryText
	 *            the sparql query to be executed
	 * @return true or false
	 */
	public static boolean executeASKQuery(String queryText, OntModel model)
	{
		// sparql query handler
		Query query = QueryFactory.create(queryText);
		
		// execution handler
		QueryExecution queryExec = QueryExecutionFactory.create(query, model);
		
		// exec the query and gather results
		long time = System.currentTimeMillis();
		boolean queryResult = queryExec.execAsk();
		time = System.currentTimeMillis() - time;
		
		System.out.println("Query: \n" + queryText);
		System.out.println("Elapsed time: " + time);
		
		// return results
		return queryResult;
	}
	
}
