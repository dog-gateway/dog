/*
 * Dog - Core
 * 
 * Copyright (c) 2011-2014 Dario Bonino and Luigi De Russis
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
package it.polito.elite.dog.core.housemodel.semantic.query;

import it.polito.elite.dog.core.library.model.DeviceCostants;
import it.polito.elite.dog.core.library.semantic.util.SimplifiedQueryExecutor;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.UnionClass;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class SPARQLQueryWrapper
{
	// the inner namespaces collection
	protected Map<String, String> namespaces;
	
	// name spaces declaration
	protected String namespaceDecl;
	
	// the model to query
	protected OntModel model;
	
	/**
	 * The SPARQLQueryWrapper constructor. It creates and instance of
	 * SPARQLQueryWrapper which allows to query the given ontology model by
	 * simply calling wrapper Java methods
	 * 
	 * @param namespaces
	 *            the different namespaces currently loaded in the ontology
	 * @param model
	 *            the ontology model (Jena {@link OntModel}).
	 */
	public SPARQLQueryWrapper(Map<String, String> namespaces, OntModel model)
	{
		// store the namespaces
		this.namespaces = namespaces;
		
		// store the model
		this.model = model;
		
		// generate the namespaceDecl
		this.namespaceDecl = this.genNamespaceDecl();
		
		// debug TODO: change this to logger...
		System.err.println("SPARQL Prefixes: " + this.namespaceDecl);
	}
	
	/**
	 * Given the map of currently loaded namespaces, generate the corresponding
	 * prefixes
	 * 
	 * @return the prefix declaration as {@link String}
	 */
	protected String genNamespaceDecl()
	{
		// The string buffer for holding the namespace declaration
		StringBuffer bf = new StringBuffer();
		
		// "PREFIX simple_home:<http://elite.polito.it/ontologies/simplehome.owl#>\n"+
		for (String namespace : this.namespaces.keySet())
		{
			bf.append("PREFIX " + namespace + ": <" + this.namespaces.get(namespace) + "#>\n");
		}
		
		// append the standard prefixes
		bf.append("PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\n");
		bf.append("PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n");
		bf.append("PREFIX owl:<http://www.w3.org/2002/07/owl#>\n");
		bf.append("PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\n");
		bf.append("PREFIX list:<http://jena.hpl.hp.com/ARQ/list#>\n");
		
		return bf.toString();
	}
	
	/**
	 * Wraps a general SPARQL query which provides back a list of all
	 * controllable device instances...as a {@link Set} of URIs that point to
	 * the corresponding ontology definition
	 * 
	 * @return a {@link Set} of URIs that point to the corresponding ontology
	 *         definition
	 */
	public Set<String> getAllControllableInstances()
	{
		// initially empty device set
		Set<String> controllables = new HashSet<String>();
		
		// build the query body
		String queryBody = "SELECT DISTINCT ?cntrl WHERE" + "{" + "?cntrl a dogOnt:Controllable"
				+ ". FILTER (?cntrl!=owl:Nothing)" + "}" + "ORDER by ?cntrl";
		
		// send the query
		ResultSet queryResults = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + queryBody, this.model);
		
		// iterate over the result set
		QuerySolution cSolution;
		long eTime = System.currentTimeMillis();
		boolean first = true;
		while (queryResults.hasNext())
		{
			// get the current solution
			cSolution = queryResults.nextSolution();
			
			if (first)
			{
				eTime = System.currentTimeMillis();
				first = false;
			}
			
			// extract the device name
			String devName = cSolution.getResource("cntrl").getURI();
			
			// add the device to the set
			controllables.add(devName);
		}
		eTime = System.currentTimeMillis() - eTime;
		
		// debug TODO: insert logger here
		System.err.println("All controllables (" + controllables.size() + ") extracted in: " + eTime + "ms");
		
		// return all controllable devices
		return controllables;
	}
	
	/**
	 * Wraps a general SPARQL query which provides back a list of all
	 * controllable device classes...as a {@link Set} of class names that point
	 * to the corresponding ontology definition
	 * 
	 * @return a {@link Set} of class names that point to the corresponding
	 *         ontology definition
	 */
	public Set<String> getAllControllableClasses()
	{
		// initially empty device set
		Set<String> controllables = new HashSet<String>();
		
		// build the query body
		String queryBody = "SELECT DISTINCT ?cntrl WHERE" + "{" + "?cntrl rdfs:subClassOf dogOnt:Controllable"
				+ ". FILTER (?cntrl!=owl:Nothing)" + "}" + "ORDER by ?cntrl";
		
		// send the query
		ResultSet queryResults = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + queryBody, this.model);
		
		// iterate over the result set
		QuerySolution cSolution;
		long eTime = System.currentTimeMillis();
		boolean first = true;
		while (queryResults.hasNext())
		{
			// get the current solution
			cSolution = queryResults.nextSolution();
			
			if (first)
			{
				eTime = System.currentTimeMillis();
				first = false;
			}
			
			// extract the device name
			String devName = cSolution.getResource("cntrl").getLocalName();
			
			if (devName != null)
			{
				// add the device to the set
				controllables.add(devName);
			}
			
		}
		eTime = System.currentTimeMillis() - eTime;
		
		// debug TODO: insert logger here
		System.err.println("All controllables (" + controllables.size() + ") extracted in: " + eTime + "ms\n"
				+ controllables.toString());
		
		// return all controllable devices
		return controllables;
	}
	
	/**
	 * Wraps a general SPARQL query which provides back a list of all
	 * controllable device instances...as a {@link Set} of URIs that point to
	 * the corresponding ontology definition
	 * 
	 * @return a {@link Set} of URIs that point to the corresponding ontology
	 *         definition
	 */
	public Set<String> getCategoryFilteredControllableInstances(Set<String> categories)
	{
		// initially empty device set
		Set<String> controllables = new HashSet<String>();
		
		// build the query body
		boolean first = true;
		String queryBody = "SELECT DISTINCT ?cntrl WHERE" + "{";
		for (String category : categories)
		{
			if (!first)
				queryBody += " UNION ";
			else
				first = false;
			
			if (category.indexOf(':') == -1)
				category = "dogOnt:" + category;
			
			queryBody += "{" + "?cntrl a " + category + "}";
			
		}
		queryBody += ". FILTER (?cntrl!=owl:Nothing)" + "}" + "ORDER by ?cntrl";
		
		// send the query
		ResultSet queryResults = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + queryBody, this.model);
		
		// iterate over the result set
		QuerySolution cSolution;
		long eTime = System.currentTimeMillis();
		
		first = true;
		while (queryResults.hasNext())
		{
			// get the current solution
			cSolution = queryResults.nextSolution();
			
			if (first)
			{
				eTime = System.currentTimeMillis();
				first = false;
			}
			
			// extract the device name
			String devName = cSolution.getResource("cntrl").getURI();
			
			// add the device to the set
			controllables.add(devName);
		}
		eTime = System.currentTimeMillis() - eTime;
		
		// debug TODO: insert logger here
		System.err.println("All controllables belonging to " + categories + " (" + controllables.size()
				+ ") extracted in: " + eTime + "ms");
		
		// return all controllable devices
		return controllables;
	}
	
	/**
	 * Wraps the complex ontology navigation process needed to extract the low
	 * level features of the given DogOnt device instances (represented by its
	 * full URI). It provides back a {@link Map} containing the device type (key
	 * {@link DeviceCostants.TYPE}, the device uri (key
	 * {@link DeviceCostants.DEVICEURI}, the device description (key
	 * {@link DeviceCostants.DEVICE_DESCRIPTION} and the device manufacturer
	 * (key {@link DeviceCostants.MANUFACTURER}). Please be aware that device
	 * description and manufacturer might be empty, i.e., null.
	 * 
	 * @param deviceURI
	 *            the device URI as a {@link String}
	 * @return the {@link Map}<{@link String},{@link String}> containing the
	 *         device features
	 */
	public Map<String, String> getControllableInstanceBasicData(String deviceURI)
	{
		// create the map to fill with basic device data
		Map<String, String> devData = new HashMap<String, String>();
		
		// store the device id
		devData.put(DeviceCostants.DEVICEURI, deviceURI.substring(deviceURI.indexOf('#') + 1));
		
		// all direct classes
		Individual devInd = this.model.getIndividual(deviceURI);
		Set<OntClass> devClasses = devInd.listOntClasses(true).toSet();
		
		// iterate over the classes
		String clsURI;
		Set<String> possibleClasses = new HashSet<String>();
		for (OntClass devCls : devClasses)
		{
			// check the current direct class
			clsURI = devCls.getURI();
			
			// filter null and component
			if ((clsURI != null) && (!clsURI.endsWith("Component")) && (!clsURI.endsWith("Gateway")))
			{
				possibleClasses.add(clsURI);
			}
			else if (clsURI != null)
			{
				int cIndex = -1;
				if (clsURI.endsWith("Component"))
					cIndex = clsURI.indexOf("Component");
				else if (clsURI.endsWith("Gateway"))
				{
					cIndex = clsURI.indexOf("Gateway");
					
					// dirt... in the gateway case it must be also inserted in
					// the possibleClasses since
					// a gateway has only one direct type which both identifies
					// the network and the
					// device type
					possibleClasses.add(clsURI);
				}
				// split the name
				int sharpIndex = clsURI.indexOf('#');
				clsURI = clsURI.substring(sharpIndex + 1, cIndex);
				// store the name
				devData.put(DeviceCostants.MANUFACTURER, clsURI.toUpperCase());
			}
		}
		
		// if no manufacturer has been inserted...then no manufacturer is
		// specified
		if (!devData.containsKey(DeviceCostants.MANUFACTURER))
			devData.put(DeviceCostants.MANUFACTURER, null);
		
		// if the possible classes set has only one entry (most cases)
		// directly add the entry as device type, otherwise search the most
		// specific
		// class and add it
		if (possibleClasses.size() == 1)
		{
			// get the device name purging the common namespace URI
			String devType = possibleClasses.iterator().next();
			devType = devType.substring(devType.indexOf('#') + 1);
			
			// directly add the device name
			devData.put(DeviceCostants.TYPE, devType);
		}
		else if (possibleClasses.size() > 1)
		{
			// get the most specific class...
			String devType = this.getMostSpecificType(possibleClasses);
			
			// purge the common namespace URI
			devType = devType.substring(devType.indexOf('#') + 1);
			
			// directly add the device name
			devData.put(DeviceCostants.TYPE, devType);
		}
		
		// add the device description if available
		String comment = devInd.getComment(null);
		
		// if not null add the comment, else....maybe in the future
		// we can define a comment based on the relative location
		// if(comment!=null)
		// {
		devData.put(DeviceCostants.DEVICE_DESCRIPTION, comment);
		// }
		
		// return the device data map
		return devData;
	}
	
	/**
	 * Wraps a query for getting all the Locations (typically Rooms) in Which
	 * the given entity is located. Although this method supports multiple
	 * locations for the same device, this can never happen with the current
	 * DogOnt as it will make the ontology inconsistent.
	 * 
	 * @param entityURI
	 *            The URI of the object to locate
	 * @return A {@link String} representing the location
	 */
	public String getEntityLocationInRoom(String entityURI)
	{
		// get the location if possible
		String location = "";
		
		// the query
		String qBody = "SELECT DISTINCT ?loc WHERE {<" + entityURI + "> dogOnt:isIn ?loc}";
		
		// exec the query
		ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + qBody, this.model);
		
		// iterate over the results
		QuerySolution sol;
		while (res.hasNext())
		{
			// get the current location
			sol = res.next();
			if (sol != null)
				// if the location is not null....store it
				location = sol.getResource("loc").getLocalName();
		}
		
		// return found locations
		return location;
	}
	
	/**
	 * Wraps a query for getting all the network parameters associated to a
	 * given device instance, it does not retrieve those parameters associated
	 * to the device commands. Parameters are retrieved opaquely, i.e., they are
	 * extracted by listing all the data type properties associated to the
	 * network nature of the device (i.e., the properties having a
	 * NetworkComponent subclass as domain).
	 * 
	 * @param deviceURI
	 *            the device URI as {@link String}
	 * @return A {@link Map}<{@link String},{@link String}> containing couples
	 *         of parameter name and value.
	 */
	public Map<String, Set<String>> getInstanceProperties(String deviceURI, Set<String> properties)
	{
		// the map for holding the entity parameters (data type properties
		// captured without prior knowledge
		Map<String, Set<String>> params = new HashMap<String, Set<String>>();
		
		// get the device
		Individual devInd = this.model.getIndividual(deviceURI);
		
		// iterate over the properties
		for (String prop : properties)
		{
			// get the model representation of the property
			OntProperty cProp = this.model.getOntProperty(this.namespaces.get("dogOnt") + "#" + prop);
			if (cProp != null)
			{
				// get the devcie property if available
				RDFNode resValue = devInd.getPropertyValue(cProp);
				
				if ((resValue != null) && (resValue.isLiteral()))
				{
					
					// if there is already a value...add a new one, else just
					// add the new set
					Set<String> propValues = params.get(prop);
					if (propValues != null)
						propValues.add(((Literal) resValue).getString());
					
					// if it is a literal and it is available, store the
					// property
					params.put(prop, (Collections.singleton(((Literal) resValue).getString())));
				}
			}
		}
		return params;
	}
	
	public Set<String> getDatatypePropertiesOf(String cls)
	{
		// the set for holding the network component datatype properties
		Set<String> props = new HashSet<String>();
		
		// prepare the query (TODO: check if the distinct clause is right, wrong
		// or not influent)
		String qBody = "SELECT DISTINCT ?p WHERE{" + "{?c rdfs:subClassOf " + cls + " . ?p rdfs:domain ?c }"
				+ " UNION {?c rdfs:subClassOf " + cls + " . " + "?p rdfs:domain [owl:unionOf [list:member ?c]]}"
				+ " UNION {" + cls + " rdfs:subClassOf ?c . ?p rdfs:domain ?c }" + " UNION {" + cls
				+ " rdfs:subClassOf ?c . ?p rdfs:domain [owl:unionOf [list:member ?c]]}" + " UNION {"
				+ " ?p rdfs:domain " + cls + "} UNION { ?p rdfs:domain [owl:unionOf [list:member " + cls + "]]}" +
				// "?p rdfs:subPropertyOf owl:DatatypeProperty" +
				". FILTER(?p!=owl:bottomDataProperty)} ORDER BY ?p";
		
		// exec the query
		ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + qBody, this.model);
		
		// iterate over the results
		QuerySolution cSol;
		while (res.hasNext())
		{
			// the current query solution
			cSol = res.next();
			
			// if not null work on it
			if (cSol != null)
			{
				Resource pRes = cSol.getResource("p");
				
				OntProperty pProp = this.model.getOntProperty(pRes.getURI());
				
				if ((pProp != null) && (pProp.isDatatypeProperty()))
				{
					// get the parameter name
					String pName = pRes.getLocalName();
					
					props.add(pName);
				}
			}
		}
		
		return props;
	}
	
	/**
	 * Shall check the domain of the given property, gives a warning from pellet
	 * and it is really slow however it will be left here for future uses
	 * ...need to be checked / verified
	 * 
	 * @param propURI
	 * @param classURI
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected boolean checkDomain(String propURI, String classURI)
	{
		boolean found = false;
		
		OntClass networkCls = this.model.getOntClass(classURI);
		
		DatatypeProperty pProp = this.model.getDatatypeProperty(propURI);
		
		if (pProp != null)
		{
			Set<OntResource> domainClasses = (Set<OntResource>) pProp.listDomain().toSet();
			
			// dump
			System.err.println(domainClasses);
			
			for (OntResource propRes : domainClasses)
			{
				
				// tODO deal with intersections and unions (the operands....i.e.
				// the classes taking part in the
				// boolean combination
				if (((OntClass) propRes).isUnionClass())
				{
					UnionClass union = ((OntClass) propRes).asUnionClass();
					// get the binary operation operands
					Set<OntClass> bClasses = (Set<OntClass>) union.listOperands().toSet();
					
					// check if one of the operands is subclass of a network
					// class
					for (OntClass bClass : bClasses)
					{
						if (bClass.hasSuperClass(networkCls))
						{
							found = true;
							break;
						}
					}
					
					if (found)
						break;
				}
				else if (((OntClass) propRes).hasSuperClass(networkCls))
				{
					found = true;
					break;
				}
				
			}
		}
		return found;
	}
	
	/**
	 * Wraps a query for getting all the specific commands, grouped by
	 * controlFunctionality associate to the given device instance
	 * 
	 * @param deviceURI
	 *            the device URI as {@link String}
	 * @return A {@link Map}<{@link String}{@link Set}<{@link String}>> keys->
	 *         functionalities , values commands
	 */
	public Map<String, Set<String>> getDeviceInstanceSpecificFunctionalitiesCommands(String deviceURI)
	{
		// the returned map
		Map<String, Set<String>> functionalitiesCommands = new HashMap<String, Set<String>>();
		
		// the query
		String qBody = "SELECT ?c ?f WHERE {" + "<" + deviceURI + "> dogOnt:hasFunctionality ?f ."
				+ "?f dogOnt:hasCommand ?c }";
		// exec the query
		ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + qBody, this.model);
		
		// iterate over the results to fill the set
		QuerySolution cSol;
		// the set containing commands uri
		Set<String> commands = null;
		
		while (res.hasNext())
		{
			// the current query solution
			cSol = res.next();
			// if not null work on it
			if (cSol != null)
			{
				
				String functionality = cSol.getResource("f").getURI();
				// get the command set if exist the key functinality
				commands = functionalitiesCommands.get(functionality);
				
				if (commands == null)
				{ // the functionality does not exist
					commands = new HashSet<String>();
					functionalitiesCommands.put(functionality, commands);
				}
				// get the command name and add it the list
				commands.add(cSol.getResource("c").getURI());
			}
		}
		
		return functionalitiesCommands;
	}
	
	/**
	 * Wraps a query for getting all the network specific commands associate to
	 * the given device instance
	 * 
	 * @param deviceURI
	 *            the device URI as {@link String}
	 * @return A {@link Set}<{@link String}> of command URIs
	 */
	public Set<String> getDeviceInstanceNetworkSpecificCommands(String deviceURI)
	{
		// the set that will contain the URIs of the device commands
		Set<String> commands = new HashSet<String>();
		
		// the query
		String qBody = "SELECT ?c WHERE {" + "<" + deviceURI + "> dogOnt:hasFunctionality ?f ."
				+ "?f dogOnt:hasCommand ?c ." + "?c a ?cType . ?cType rdfs:subClassOf dogOnt:NetworkSpecificCommand }";
		// exec the query
		ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + qBody, this.model);
		
		// iterate over the results to fill the set
		QuerySolution cSol;
		while (res.hasNext())
		{
			// the current query solution
			cSol = res.next();
			
			// if not null work on it
			if (cSol != null)
			{
				// get the command name
				commands.add(cSol.getResource("c").getURI());
			}
		}
		
		return commands;
	}
	
	/**
	 * Wraps a query for getting the device associated to the given command
	 * instance
	 * 
	 * @param commandURI
	 *            the command URI as {@link String}
	 * @return A {@link String} representing the URI of the device instance
	 *         having this command
	 */
	public String getCommandOf(String cmdURI)
	{
		// the set that will contain the URIs of the device commands
		Set<String> device = new HashSet<String>();
		
		// the query
		String qBody = "SELECT ?d WHERE {?d dogOnt:hasFunctionality ?f ." + "?f dogOnt:hasCommand <" + cmdURI + "> }";
		// exec the query
		ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + qBody, this.model);
		
		// iterate over the results to fill the set
		QuerySolution cSol;
		while (res.hasNext())
		{
			// the current query solution
			cSol = res.next();
			
			// if not null work on it
			if (cSol != null)
			{
				// get the command name
				device.add(cSol.getResource("d").getLocalName());
			}
		}
		
		// only one result is allowed
		if (device.size() > 0)
			return device.iterator().next();
		else
			return null;
	}
	
	/**
	 * Wraps a query for getting all the network specific commands associate to
	 * the given device instance
	 * 
	 * @param deviceURI
	 *            the device URI as {@link String}
	 * @return A {@link Set}<{@link String}> of command URIs
	 */
	public Set<String> getDeviceInstanceNetworkSpecificNotifications(String deviceURI)
	{
		// the set that will contain the URIs of the device commands
		Set<String> notifications = new HashSet<String>();
		
		// the query
		String qBody = "SELECT ?n WHERE {" + "<" + deviceURI + "> dogOnt:hasFunctionality ?f ."
				+ "?f dogOnt:hasNotification ?n ."
				+ "?n a ?nType . ?nType rdfs:subClassOf dogOnt:NetworkSpecificNotification }";
		// exec the query
		ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + qBody, this.model);
		
		// iterate over the results to fill the set
		QuerySolution cSol;
		while (res.hasNext())
		{
			// the current query solution
			cSol = res.next();
			
			// if not null work on it
			if (cSol != null)
			{
				// get the command name
				notifications.add(cSol.getResource("n").getURI());
			}
		}
		
		return notifications;
	}
	
	/**
	 * Wraps a query for getting all the specific notifications, grouped by
	 * notificationFunctionality associate to the given device instance
	 * 
	 * @param deviceURI
	 *            the device URI as {@link String}
	 * @return A {@link Map}<{@link String}{@link Set}<{@link String}>> keys->
	 *         functionalities , values notifications
	 */
	public Map<String, Set<String>> getDeviceInstanceSpecificFunctionalitiesNotifications(String deviceURI)
	{
		// the returned map
		Map<String, Set<String>> functionalitiesNotifications = new HashMap<String, Set<String>>();
		
		// the query
		String qBody = "SELECT ?n ?f WHERE {" + "<" + deviceURI + "> dogOnt:hasFunctionality ?f ."
				+ "?f dogOnt:hasNotification ?n .}";
		// exec the query
		ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + qBody, this.model);
		
		// iterate over the results to fill the set
		QuerySolution cSol;
		// the set containing commands uri
		Set<String> notifications = null;
		
		while (res.hasNext())
		{
			// the current query solution
			cSol = res.next();
			// if not null work on it
			if (cSol != null)
			{
				
				String functionality = cSol.getResource("f").getURI();
				// get the command set if exist the key functionality
				notifications = functionalitiesNotifications.get(functionality);
				
				if (notifications == null)
				{ // the functionality does not exist
					notifications = new HashSet<String>();
					functionalitiesNotifications.put(functionality, notifications);
				}
				// get the command name and add it the list
				notifications.add(cSol.getResource("n").getURI());
			}
		}
		
		return functionalitiesNotifications;
	}
	
	/*
	 * Given a set of ontology classes provides back the most specific one. (or
	 * the first type for which no descendants are found and having the maximum
	 * sized set of superclasses (WARNING: this might sometimes lead to wrong
	 * results! therefore this method has to be considered as "risky"and its use
	 * is deprecated)
	 * 
	 * @param deviceClasses - The "candidate" classes.
	 * 
	 * @return - The most specific class.
	 */
	@Deprecated
	private String getMostSpecificType(Set<String> deviceClasses)
	{
		// the minimum number of subclasses found until now
		int subClassNumber = Integer.MAX_VALUE;
		int superClassNumber = Integer.MIN_VALUE;
		
		// the found type
		String mostSpecificType = null;
		
		// iterate over device classes
		for (String currentCls : deviceClasses)
		{
			// get the class resources
			OntClass currentDeviceType = this.model.getOntClass(currentCls);
			ExtendedIterator<OntClass> it = currentDeviceType.listSubClasses();
			ExtendedIterator<OntClass> itS = currentDeviceType.listSuperClasses();
			if (it != null)
			{
				// get the direct subclass of the current device
				Set<OntClass> subclasses = it.toSet();
				Set<OntClass> superclasses = itS.toSet();
				if ((subclasses.size() < subClassNumber)
						|| ((subclasses.size() == subClassNumber) && (superclasses.size() > superClassNumber)))
				{
					subClassNumber = subclasses.size();
					superClassNumber = superclasses.size();
					mostSpecificType = currentDeviceType.getLocalName();
				}
			}
			else
			{
				mostSpecificType = currentDeviceType.getLocalName();
				subClassNumber = 0;
			}
		}
		
		return mostSpecificType;
	}
	
	/**
	 * Given a set of ontology classes provides back the most specific one. (or
	 * the first type for which no descendants are found and having the maximum
	 * sized set of superclasses (WARNING: this might sometimes lead to wrong
	 * results! therefore this method has to be considered as "risky"and its use
	 * is deprecated)
	 * 
	 * @param deviceClasses
	 *            - The "candidate" classes.
	 * 
	 * @return - The most specific class.
	 */
	@Deprecated
	private String getMostSpecificTypeO(Set<OntClass> deviceClasses, OntClass filter)
	{
		// the minimum number of subclasses found until now
		int subClassNumber = Integer.MAX_VALUE;
		int superClassNumber = Integer.MIN_VALUE;
		
		// the found type
		String mostSpecificType = null;
		
		// iterate over device classes
		for (OntClass currentCls : deviceClasses)
		{
			if(!currentCls.isAnon() && !currentCls.getURI().equals("http://www.w3.org/2002/07/owl#NamedIndividual")){
			// get the class resources
			if ((filter == null) || ((filter != null) && (!currentCls.hasSuperClass(filter))))
			{
				ExtendedIterator<OntClass> it = currentCls.listSubClasses();
				ExtendedIterator<OntClass> itS = currentCls.listSuperClasses();
				if (it != null)
				{
					// get the direct subclass of the current device
					Set<OntClass> subclasses = it.toSet();
					Set<OntClass> superclasses = itS.toSet();
					if ((subclasses.size() < subClassNumber)
							|| ((subclasses.size() == subClassNumber) && (superclasses.size() > superClassNumber)))
					{
						subClassNumber = subclasses.size();
						superClassNumber = superclasses.size();
						mostSpecificType = currentCls.getLocalName();
					}
					
				}
				else
				{
					mostSpecificType = currentCls.getLocalName();
					subClassNumber = 0;
				}
			}
		}
		}
		
		return mostSpecificType;
	}
	
	/**
	 * Gets the "most specific" type of a device given its uri, and optionally
	 * accepts a filter class specifying that the found type must not be a
	 * descendant of the given filter class.
	 * 
	 * This class is currently a dirt way of identifying the specific type of a
	 * device and might be subject to changes in the upcoming revisions of the
	 * SemanticHouseModel
	 * 
	 * @param devURI
	 *            The URI of the device for which the most specific class shall
	 *            be retrieved
	 * @param filterURI
	 *            The class defining the hierarchy branch that shall be avoided
	 *            in finding the most specific type (might not be sufficient,
	 *            and in future implementations this might become a set of
	 *            classes to avoid)
	 * 
	 * @return the name of the most specific type (Ontology Class) associated to
	 *         the given device
	 */
	public String getType(String devURI, String filterURI)
	{
		// all direct classes
		Individual devInd = this.model.getIndividual(devURI);
		Set<OntClass> devClasses = devInd.listOntClasses(true).toSet();
		
		// the filter (not subclass of)
		OntClass devFilter = null;
		if (filterURI != null)
			devFilter = this.model.getOntClass(filterURI);
		String type = this.getMostSpecificTypeO(devClasses, devFilter);
		return type;
	}
	
	public String checkAndRepairDogOntNameSpace(String name)
	{
		if (name.indexOf(':') == -1)
		{
			name += this.namespaces.get("dogOnt") + "#" + name;
		}
		return name;
	}
	
	/**
	 * Given a device class name, provides back a {@link Set} of functionalities
	 * associated to the device class
	 * 
	 * @param devClass
	 * @return A {@link Set} containing all the functionalities associated to a
	 *         given device class.
	 */
	public Set<String> getDevFunctionalities(String devClass)
	{
		Set<String> functionalities = new HashSet<String>();
		
		String allDevFunctionality = "SELECT ?device WHERE {dogOnt:" + devClass + " rdfs:subClassOf ?device ."
				+ "?device rdfs:subClassOf dogOnt:Controllable}";
		
		// exec the query
		ResultSet resSet = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + allDevFunctionality, this.model);
		
		// the class itself
		this.addFunctionalities(functionalities, devClass);
		
		// cycle over results, i.e. over all the class ancestors
		while (resSet.hasNext())
		{
			
			String deviceClass = resSet.next().getResource("device").getLocalName();
			
			if (deviceClass != null)
			{
				// System.err.println("Class: " + deviceClass);
				this.addFunctionalities(functionalities, deviceClass);
			}
		}
		
		return functionalities;
	}
	
	private void addFunctionalities(Set<String> functionalities, String deviceClass)
	{
		
		// prepare the query
		String qBody = "SELECT ?f WHERE {" + "dogOnt:" + deviceClass + " rdfs:subClassOf [rdf:type owl:Restriction;"
				+ "owl:onProperty dogOnt:hasFunctionality;" + "owl:someValuesFrom ?f] " + "}";
		
		// execute the query
		ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + qBody, this.model);
		
		// iterate over the result set
		QuerySolution cSol;
		while (res.hasNext())
		{
			// the current query solution...
			cSol = res.next();
			
			// store the names
			Resource functionalityR = cSol.getResource("f");
			
			// if not null store the local name in the functionality set
			if (functionalityR != null)
			{
				functionalities.add(functionalityR.getLocalName());
			}
			
		}
	}
	
	/**
	 * Given a device class name, provides back a {@link Set} of states
	 * associated to the device class
	 * 
	 * @param devClass
	 * @return A {@link Set} containing all the states associated to a given
	 *         device class.
	 */
	public Set<String> getDevStates(String devClassName)
	{
		Set<String> states = new HashSet<String>();
		
		Set<String> allSuperClasses = this.getAllSuperClasses(this.namespaces.get("dogOnt") + "#" + devClassName);
		
		for (String devClass : allSuperClasses)
		{
			devClass = this.toShortForm(devClass);
			
			// prepare the query
			String qBody = "SELECT ?s WHERE {" + "dogOnt:" + devClass + " rdfs:subClassOf [rdf:type owl:Restriction;"
					+ "owl:onProperty dogOnt:hasState;" + "owl:someValuesFrom ?s]}";
			
			// execute the query
			ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + qBody, this.model);
			
			// iterate over the result set
			QuerySolution cSol;
			while (res.hasNext())
			{
				// the current query solution...
				cSol = res.next();
				
				// store the names
				Resource stateR = cSol.getResource("s");
				
				// if not null store the local name in the functionality set
				if (stateR != null)
				{
					states.add(stateR.getLocalName());
				}
				
			}
		}
		return states;
	}
	
	/**
	 * Provides back all the ontology-declared functionalities and the relative
	 * commands (only the names...), it is only aimed at supporting the horrible
	 * way of providing such information to external apps defined in the
	 * DOG2.0.8 version.
	 * 
	 * @return A {@link Map}<{@link String},<{@link Set}<{@link String}>>
	 *         containing the found functionalities as keys and the relative
	 *         commands as a set of {@link String}s
	 */
	@Deprecated
	public Map<String, Set<String>> getFunctionalitiesAndCommands()
	{
		
		// create the map for storing the command associated to each
		// functionality
		Map<String, Set<String>> functionalities = new HashMap<String, Set<String>>();
		
		// do not know if it works...
		// first get the functionalities
		String qBody1 = "SELECT ?functionality WHERE {" + "?functionality rdfs:subClassOf dogOnt:Functionality}";
		
		// exec the query
		ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + qBody1, this.model);
		
		// iterate over functionalities and extract the commands (implementing
		// restriction inheritance)
		QuerySolution cSol1;
		while (res.hasNext())
		{
			cSol1 = res.next();
			
			String functionality = cSol1.getResource("functionality").getLocalName();
			String functionalityURI = cSol1.getResource("functionality").getURI();
			
			Set<String> allSuperFunctionalities = this.getAllSuperClasses(functionalityURI);
			
			for (String cFunctionality : allSuperFunctionalities)
			{
				cFunctionality = this.toShortForm(cFunctionality);
				// the query
				String qBody = "SELECT ?commandValue ?cmdParamValue WHERE {" + "dogOnt:" + cFunctionality
						+ " rdfs:subClassOf dogOnt:Functionality ." + "dogOnt:" + cFunctionality
						+ " rdfs:subClassOf [rdf:type owl:Restriction;" + "owl:onProperty dogOnt:hasCommand;"
						+ "owl:someValuesFrom ?command] ." + "?command rdfs:subClassOf dogOnt:Command ."
						+ "?command rdfs:subClassOf [rdf:type owl:Restriction;"
						+ "owl:onProperty dogOnt:realCommandName;" + "owl:hasValue ?commandValue] . OPTIONAL"
						+ "{?command rdfs:subClassOf [rdf:type owl:Restriction;"
						+ "owl:onProperty dogOnt:commandParamName;" + "owl:hasValue ?cmdParamValue]}}";
				
				// exec the query
				ResultSet res2 = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + qBody, this.model);
				
				// iterate over the results
				QuerySolution cSol;
				while (res2.hasNext())
				{
					cSol = res2.next();
					
					// String functionality =
					// cSol.getResource("functionality").getLocalName();
					String command = cSol.getLiteral("commandValue").getString();
					Literal paramValueR = cSol.getLiteral("cmdParamValue");
					
					// deal with the param...
					if (paramValueR != null)
					{
						// split...
						String typeAndName[] = paramValueR.getString().split("\\^\\^");
						
						// store...
						command += "(" + typeAndName[1] + "[])";
					}
					
					// if not null add them
					if (((functionality != null) && (!functionality.isEmpty()))
							&& ((command != null) && (!command.isEmpty())))
					{
						// check if the functionality is already there
						Set<String> cmds = functionalities.get(functionality);
						
						if (cmds == null)
						{
							// create the command set
							cmds = new HashSet<String>();
							cmds.add(command);
							functionalities.put(functionality, cmds);
						}
						else
						{
							// just add the command
							cmds.add(command);
						}
					}
				}
			}
		}
		return functionalities;
	}
	
	/**
	 * Provides back all the ontology-declared notification functionalities and
	 * the relative notifications (only the names...), it is only aimed at
	 * supporting the horrible way of providing such information to external
	 * apps defined in the DOG2.0.8 version.
	 * 
	 * @return A {@link Map}<{@link String},<{@link Set}<{@link String}>>
	 *         containing the found functionalities as keys and the relative
	 *         notifications as a set of {@link String}s
	 */
	@Deprecated
	public Map<String, Set<String>> getFunctionalitiesAndNotifications()
	{
		
		// create the map for storing the command associated to each
		// functionality
		Map<String, Set<String>> allNotifications = new HashMap<String, Set<String>>();
		
		// do not know if it works...
		// first get the functionalities
		String qBody1 = "SELECT ?functionality WHERE {" + "?functionality rdfs:subClassOf dogOnt:Functionality}";
		
		// exec the query
		ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + qBody1, this.model);
		
		// iterate over functionalities and extract the commands (implementing
		// restriction inheritance)
		QuerySolution cSol1;
		while (res.hasNext())
		{
			cSol1 = res.next();
			
			String functionality = cSol1.getResource("functionality").getLocalName();
			String functionalityURI = cSol1.getResource("functionality").getURI();
			
			Set<String> allSuperFunctionalities = this.getAllSuperClasses(functionalityURI);
			
			for (String cFunctionality : allSuperFunctionalities)
			{
				cFunctionality = this.toShortForm(cFunctionality);
				// the query
				String qBody = "SELECT ?notifValue ?notifParamValue WHERE {" + "dogOnt:" + cFunctionality
						+ " rdfs:subClassOf dogOnt:NotificationFunctionality ." + "dogOnt:" + cFunctionality
						+ " rdfs:subClassOf [rdf:type owl:Restriction;" + "owl:onProperty dogOnt:hasNotification;"
						+ "owl:someValuesFrom ?notification] ." + "?notification rdfs:subClassOf dogOnt:Notification ."
						+ "?notification rdfs:subClassOf [rdf:type owl:Restriction;"
						+ "owl:onProperty dogOnt:notificationName;" + "owl:hasValue ?notifValue] . OPTIONAL"
						+ "{?notification rdfs:subClassOf [rdf:type owl:Restriction;"
						+ "owl:onProperty dogOnt:notificationParamName;" + "owl:hasValue ?notifParamValue]}}";
				
				// exec the query
				ResultSet res2 = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + qBody, this.model);
				
				// iterate over the results
				QuerySolution cSol;
				while (res2.hasNext())
				{
					cSol = res2.next();
					
					// String functionality =
					// cSol.getResource("functionality").getLocalName();
					String notif = cSol.getLiteral("notifValue").getString();
					Literal paramValueR = cSol.getLiteral("?notifParamValue");
					
					// deal with the param...
					if (paramValueR != null)
					{
						// split...
						String typeAndName[] = paramValueR.getString().split("\\^\\^");
						
						// store...
						notif += "(" + typeAndName[1] + "[])";
					}
					
					// if not null add them
					if (((functionality != null) && (!functionality.isEmpty()))
							&& ((notif != null) && (!notif.isEmpty())))
					{
						// check if the functionality is already there
						Set<String> notifications = allNotifications.get(functionality);
						
						if (notifications == null)
						{
							// create the command set
							notifications = new HashSet<String>();
							notifications.add(notif);
							allNotifications.put(functionality, notifications);
						}
						else
						{
							// just add the command
							notifications.add(notif);
							// allNotifications.put(functionality,
							// notifications);
						}
					}
				}
			}
		}
		return allNotifications;
	}
	
	/**
	 * Provides back all the ontology-declared states and the relative values
	 * (only the names...), it is only aimed at supporting the horrible way of
	 * providing such information to external apps defined in the DOG2.0.8
	 * version.
	 * 
	 * @return A {@link Map}<{@link String},<{@link Set}<{@link String}>>
	 *         containing the found states as keys and the relative values as a
	 *         set of {@link String}s
	 */
	@Deprecated
	public Map<String, Set<String>> getStatesAndStateValues()
	{
		
		// create the map for storing the command associated to each
		// functionality
		Map<String, Set<String>> states = new HashMap<String, Set<String>>();
		
		// the query
		String qBody = "SELECT ?state ?value WHERE {" + "?state rdfs:subClassOf dogOnt:State ."
				+ "?state rdfs:subClassOf [rdf:type owl:Restriction;" + "owl:onProperty dogOnt:hasStateValue;"
				+ "owl:someValuesFrom ?stateValue] ." + "?stateValue rdfs:subClassOf dogOnt:StateValue ."
				+ "OPTIONAL {?stateValue rdfs:subClassOf [rdf:type owl:Restriction;"
				+ "owl:onProperty dogOnt:realStateValue;" + "owl:hasValue ?value]}} ";
		
		// exec the query
		ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + qBody, this.model);
		
		// iterate over the results
		QuerySolution cSol;
		while (res.hasNext())
		{
			cSol = res.next();
			
			String state = cSol.getResource("state").getLocalName();
			Literal valueR = cSol.getLiteral("value");
			
			// if not null add them
			if ((state != null) && (!state.isEmpty()))
			{
				// check if the functionality is already there
				Set<String> values = states.get(state);
				
				if (values == null)
				{
					// create the value set, will be null for continuous
					// states... (though it's an
					// horrible trick)
					if (valueR != null)
					{
						values = new HashSet<String>();
						values.add(valueR.getString());
					}
					
					states.put(state, values);
				}
				else
				{
					if (valueR != null)
						// just add the command
						values.add(valueR.getString());
				}
			}
		}
		
		return states;
	}
	
	public Set<String> getFunctionalityCommands(String functionalityURI)
	{
		// the set containing the names of the commands associated to the given
		// functionality
		HashSet<String> commands = new HashSet<String>();
		
		// trick for avoiding pellet loops
		Set<String> allFunctionalitiesURI = this.getAllSuperClasses(functionalityURI);
		
		for (String cFunctionalityURI : allFunctionalitiesURI)
		{
			
			// the query for extracting the functionality commands
			String cmdQueryBody = "SELECT DISTINCT ?command WHERE {" + "<" + cFunctionalityURI
					+ "> rdfs:subClassOf dogOnt:Functionality ." + "<" + cFunctionalityURI
					+ "> rdfs:subClassOf [rdf:type owl:Restriction;" + "owl:onProperty dogOnt:hasCommand;"
					+ "owl:someValuesFrom ?command] ." + "?command rdfs:subClassOf dogOnt:Command}";
			
			// execute the query
			ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + cmdQueryBody, this.model);
			
			// iterate over results
			QuerySolution cSol;
			while (res.hasNext())
			{
				// extract the current solution
				cSol = res.next();
				
				// get the command name
				String cmdName = cSol.getResource("command").getURI();
				
				// if the command is not null, add it to the command set
				if ((cmdName != null) && (!cmdName.isEmpty()))
				{
					commands.add(cmdName);
				}
				
			}
		}
		return commands;
	}
	
	public Set<String> getAllSuperClasses(String classURI)
	{
		HashSet<String> allSuperClassURIs = new HashSet<String>();
		allSuperClassURIs.add(classURI);
		OntClass cls = this.model.getOntClass(classURI);
		ExtendedIterator<OntClass> it = cls.listSuperClasses();
		if (it != null)
		{
			Set<OntClass> allSuperClasses = it.toSet();
			
			for (OntClass superClass : allSuperClasses)
			{
				String superURI = superClass.getURI();
				if ((superURI != null) && (!superURI.isEmpty()))
					allSuperClassURIs.add(superURI);
			}
		}
		return allSuperClassURIs;
	}
	
	public Set<String> getFunctionalityNotifications(String functionalityURI)
	{
		// the set containing the names of the commands associated to the given
		// functionality
		HashSet<String> notifications = new HashSet<String>();
		
		// trick for avoiding pellet loops
		Set<String> allFunctionalitiesURI = this.getAllSuperClasses(functionalityURI);
		
		for (String cFunctionalityURI : allFunctionalitiesURI)
		{
			
			// the query for extracting the functionality commands
			String notificationQueryBody = "SELECT ?notification WHERE {" + "<" + cFunctionalityURI
					+ "> rdfs:subClassOf dogOnt:Functionality ." + "<" + cFunctionalityURI
					+ "> rdfs:subClassOf [rdf:type owl:Restriction;" + "owl:onProperty dogOnt:hasNotification;"
					+ "owl:someValuesFrom ?notification] ." + "?notification rdfs:subClassOf dogOnt:Notification}";
			
			// execute the query
			ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + notificationQueryBody, this.model);
			
			// iterate over results
			QuerySolution cSol;
			while (res.hasNext())
			{
				// extract the current solution
				cSol = res.next();
				
				// get the command name
				String notificationName = cSol.getResource("notification").getURI();
				
				// if the command is not null, add it to the command set
				if ((notificationName != null) && (!notificationName.isEmpty()))
				{
					notifications.add(notificationName);
				}
				
			}
		}
		return notifications;
	}
	
	public Set<String> getStateValues(String stateURI)
	{
		// the set containing the names of the state values associated to the
		// given
		// functionality
		HashSet<String> stateValues = new HashSet<String>();
		
		// the query for extracting the functionality commands
		String stateValueQueryBody = "SELECT ?stateValue WHERE {" + "<" + stateURI + "> rdfs:subClassOf dogOnt:State ."
				+ "<" + stateURI + "> rdfs:subClassOf [rdf:type owl:Restriction;"
				+ "owl:onProperty dogOnt:hasStateValue;" + "owl:someValuesFrom ?stateValue] ."
				+ "?stateValue rdfs:subClassOf dogOnt:StateValue}";
		
		// execute the query
		ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + stateValueQueryBody, this.model);
		
		// iterate over results
		QuerySolution cSol;
		while (res.hasNext())
		{
			// extract the current solution
			cSol = res.next();
			
			// get the command name
			String stateValueName = cSol.getResource("stateValue").getURI();
			
			// if the command is not null, add it to the command set
			if ((stateValueName != null) && (!stateValueName.isEmpty()))
			{
				stateValues.add(stateValueName);
			}
			
		}
		return stateValues;
	}
	
	/**
	 * Retrieve the uri of the instances contained in the container passed as
	 * parameter
	 * 
	 * @param container
	 *            uri of the instance
	 * @return Set of uris of instances
	 */
	public Set<String> getContainedInstanceUri(String container)
	{
		HashSet<String> contents = new HashSet<String>();
		String contetQuery = "SELECT ?content WHERE { " + " <" + container + "> dogOnt:contains ?content" + "}";
		
		ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + contetQuery, this.model);
		
		// iterate over results
		QuerySolution cSol;
		while (res.hasNext())
		{
			// extract the current solution
			cSol = res.next();
			
			// get the command name
			String contentUri = cSol.getResource("content").getURI();
			
			// if the command is not null, add it to the command set
			if ((contentUri != null) && (!contentUri.isEmpty()))
			{
				contents.add(contentUri);
			}
			
		}
		return contents;
	}
	
	public String getTopMostRoomContainerInstance()
	{
		// the top most instance
		String topMostInstance = null;
		
		// the building environment query
		String containerQuery = "SELECT DISTINCT ?container WHERE {{" + "?container a dogOnt:BuildingEnvironment."
				+ "?container dogOnt:contains ?room." + "?room a dogOnt:Room}" + "UNION"
				+ "{?container a dogOnt:BuildingEnvironment." + "?container dogOnt:contains ?container2."
				+ "?container2 dogOnt:contains ?room." + "?room a dogOnt:Room}"
				+ " FILTER (?container!=owl:Nothing)} ORDER BY ?container";
		
		// execute the query
		ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + containerQuery, this.model);
		
		// iterate over the results
		QuerySolution cSol;
		Individual container = null;
		boolean found = false;
		while ((res.hasNext()) && (!found))
		{
			// extract the current solution
			cSol = res.next();
			
			// get the command name
			container = this.model.getIndividual(cSol.getResource("container").getURI());
			
			RDFNode p = container.getPropertyValue(this.model.getProperty(this.namespaces.get("dogOnt") + "#isIn"));
			
			// if p is null then the topmost container is found
			if (p == null)
				found = true;
		}
		
		// if found is true, than container store sthe toMostInstance containing
		// rooms
		if (found)
			topMostInstance = container.getURI();
		
		return topMostInstance;
	}
	
	public Set<String> getAllComponentClasses()
	{
		// the set holding the component class names
		HashSet<String> componentClasses = new HashSet<String>();
		
		// the root class for components
		OntClass superComponent = this.model.getOntClass(this.namespaces.get("dogOnt") + "#DomoticNetworkComponent");
		
		// list the direct subclasses
		ExtendedIterator<OntClass> componentOntClasses = superComponent.listSubClasses(true);
		
		// iterate over the component classes
		OntClass currentClass;
		while (componentOntClasses.hasNext())
		{
			// the current component OntClass
			currentClass = componentOntClasses.next();
			
			// store the component name
			componentClasses.add(currentClass.getLocalName());
			
		}
		
		return componentClasses;
	}
	
	/******************************************************************************************************************
	 * 
	 * Environment-related queries...
	 * 
	 *****************************************************************************************************************/
	
	/**
	 * Returns all the direct Building instances define in the model
	 * 
	 * @return a @link{Set}<@link{String}> containing the labels of all Building
	 *         instances
	 */
	public Set<String> getAllBuildingInstances(boolean direct)
	{
		return this.getAllInstancesOfType("Building", direct);
	}
	
	/**
	 * Returns all the direct Garden Instances
	 * 
	 * @return a @link{Set}<@link{String}> containing the labels of all Garden
	 *         instances
	 */
	public Set<String> getAllGardenInstances(boolean direct)
	{
		return this.getAllInstancesOfType("Garden", direct);
	}
	
	/**
	 * Returns all the direct Garage instances
	 * 
	 * @return a @link{Set}<@link{String}> containing the labels of all Garage
	 *         instances
	 */
	public Set<String> getAllGarageInstances(boolean direct)
	{
		return this.getAllInstancesOfType("Garage", direct);
	}
	
	/**
	 * Returns all the direct Flat instances
	 * 
	 * @return a @link{Set}<@link{String}> containing the labels of all Flat
	 *         instances
	 */
	public Set<String> getAllFlatInstances(boolean direct)
	{
		return this.getAllInstancesOfType("Flat", direct);
	}
	
	/**
	 * Returns all the direct Flat instances
	 * 
	 * @return a @link{Set}<@link{String}> containing the labels of all Flat
	 *         instances
	 */
	public Set<String> getAllStoreyInstances(boolean direct)
	{
		return this.getAllInstancesOfType("Storey", direct);
	}
	
	/**
	 * Returns all the direct Room instances
	 * 
	 * @return a @link{Set}<@link{String}> containing the labels of all Room
	 *         instances
	 */
	public Set<String> getAllRoomInstances(boolean direct)
	{
		return this.getAllInstancesOfType("Room", direct);
	}
	
	public Set<String> getAllInstancesOfType(String type, boolean direct)
	{
		// initially empty device set
		Set<String> instances = new HashSet<String>();
		
		OntClass instanceClass = this.model.getOntClass(this.namespaces.get("dogOnt") + "#" + type);
		
		ExtendedIterator<? extends OntResource> allInstances = instanceClass.listInstances(direct);
		
		while (allInstances.hasNext())
		{
			OntResource cInstance = allInstances.next();
			
			if (cInstance instanceof Individual)
			{
				instances.add(cInstance.getLocalName());
			}
		}
		// return all controllable devices
		return instances;
	}
	
	public String getCeilingOf(String roomURI)
	{
		return getSingleObjPropertyValueOf(roomURI, this.namespaces.get("dogOnt") + "#hasCeiling");
	}
	
	public String getFloorOf(String roomURI)
	{
		return getSingleObjPropertyValueOf(roomURI, this.namespaces.get("dogOnt") + "#hasFloor");
	}
	
	public String getActuatorOf(String actuatorURI)
	{
		return getSingleObjPropertyValueOf(actuatorURI, this.namespaces.get("dogOnt") + "#actuatorOf");
	}
	
	public String getSensorOf(String sensorURI)
	{
		return getSingleObjPropertyValueOf(sensorURI, this.namespaces.get("dogOnt") + "#isSensorOf");
	}
	
	public Set<String> getControlledObjects(String deviceURI)
	{
		return getMultipleObjPropertyValueOf(deviceURI, this.namespaces.get("dogOnt") + "#controlledObject");
	}
	
	public Set<String> getGeneratesCommands(String notificationURI)
	{
		return getMultipleObjPropertyValueOf(notificationURI, this.namespaces.get("dogOnt") + "#generateCommand");
	}
	
	public Set<String> getAllWallsOf(String roomURI)
	{
		return this.getMultipleObjPropertyValueOf(roomURI, this.namespaces.get("dogOnt") + "#hasWall");
	}
	
	public Set<String> getAllWallOpeningsIn(String wallURI)
	{
		return this.getMultipleObjPropertyValueOf(wallURI, this.namespaces.get("dogOnt") + "#hasWallOpening");
	}
	
	public Set<String> getIsIn(String devURI)
	{
		return getMultipleObjPropertyValueOf(devURI, this.namespaces.get("dogOnt") + "#isIn");
	}
	
	/**
	 * Retrieve the id of the meter if exists
	 * 
	 * @param devUri
	 *            device uri
	 * @return uri of the meter or null
	 */
	public String getHasMeter(String devUri)
	{
		return getSingleObjPropertyValueOf(devUri, this.namespaces.get("dogOnt") + "#hasMeter");
	}
	
	/**
	 * Retrieve the device "metered" by the given meter instance
	 * 
	 * @param devUri
	 *            device uri
	 * @return A {@link Set}<{@link String}> of URIs of devices metered by the given meter instance
	 */
	public Set<String> getMeterOf(String devUri)
	{
		return getMultipleObjPropertyValueOf(devUri, this.namespaces.get("dogOnt") + "#meterOf");
	}
	
	/**
	 * Retrieve the plug in which the device identified by the given URI is plugged
	 * 
	 * @param devUri
	 *            device uri
	 * @return the URI( {@link String}) of the plug in which the device identified by the given URI is plugged, if any
	 */
	public String getPluggedIn(String devUri)
	{
		return getSingleObjPropertyValueOf(devUri, this.namespaces.get("dogOnt") + "#pluggedIn");
	}
	
	/**
	 * Retrieve the id of the gate if exists
	 * 
	 * @param devUri
	 *            device uri
	 * @return uri of the meter or null
	 */
	public String getHasGateway(String devUri)
	{
		return getSingleObjPropertyValueOf(devUri, this.namespaces.get("dogOnt") + "#hasGateway");
	}
	
	public String getSingleObjPropertyValueOf(String instURI, String relationURI)
	{
		
		String value = null;
		Individual roomInd = this.model.getIndividual(instURI);
		Property prop = this.model.getProperty(relationURI);
		RDFNode objNode = roomInd.getPropertyValue(prop);
		if (objNode != null)
		{
			if (objNode.canAs(Resource.class))
			{
				value = ((Resource) objNode).getLocalName();
			}
		}
		return value;
	}
	
	public Set<String> getMultipleObjPropertyValueOf(String instURI, String relationURI)
	{
		Set<String> allValues = null;
		
		// get the individual represented by the given instance uri
		Individual roomInd = this.model.getIndividual(instURI);
		
		// get the property for which values must be gathered
		Property prop = this.model.getProperty(relationURI);
		
		// list all the values of the given property, for the given instance
		NodeIterator nodes = roomInd.listPropertyValues(prop);
		
		// convert the iterator to a set for easier navigation (less efficient)
		Set<RDFNode> nodesSet = nodes.toSet();
		if (!nodesSet.isEmpty())
		{
			// if the set is not empty init the result set holding the string
			// representations of the
			// property values
			allValues = new HashSet<String>();
			
			for (RDFNode node : nodesSet)
			{
				if (node.canAs(Resource.class))
				{
					allValues.add(((Resource) node).getLocalName());
				}
			}
		}
		return allValues;
	}
	
	/**
	 * Get all the values associated to the datatype properties of a given
	 * instance
	 * 
	 * @param instance
	 *            the instance for which the datatype properties values shall be
	 *            retrieved
	 * @return A @link{Map}<@link{String},@link{Set}<@link{String}>> containing
	 *         the datatype property name as key and the set of values as value
	 */
	public Map<String, Set<String>> getDatatypePropertyValuesOf(String instance, String type)
	{
		Map<String, Set<String>> datatypePropertyValues = new HashMap<String, Set<String>>();
		
		// get the instance class, for the given type (avoid ambiguities due to
		// multiple inheritance)
		OntClass instanceClass = this.model.getOntClass(this.namespaces.get("dogOnt") + "#" + type);
		
		// get all the datatype properties
		Set<String> datatypeProperties = this.getDatatypePropertiesOf("dogOnt:" + instanceClass.getLocalName());
		
		// for all the datatype properties retrieve the values
		Individual instanceInd = this.model.getIndividual(instance);
		for (String property : datatypeProperties)
		{
			Property prop = this.model.getProperty(this.namespaces.get("dogOnt") + "#" + property);
			NodeIterator nodeIt = instanceInd.listPropertyValues(prop);
			
			// iterate over results and fill the set of values
			Set<String> values = new HashSet<String>();
			
			while (nodeIt.hasNext())
			{
				values.add(nodeIt.next().asLiteral().getString());
			}
			
			if (!values.isEmpty())
				datatypePropertyValues.put(this.toShortForm(property), values);
		}
		
		return datatypePropertyValues;
	}
	
	/**
	 * Returns the comment associated to a given ontology instance
	 * 
	 * @param instanceURI
	 * @return the associated comment or null if the comment does not exist
	 */
	public String getDescriptionOf(String instanceURI)
	{
		Individual ind = this.model.getIndividual(instanceURI);
		return ind.getComment(null);
	}
	
	/***
	 * Provide the list of the states of the Device
	 * 
	 * @param deviceURI
	 *            uri of the device
	 * @return list of state belonging to the device
	 */
	public Set<String> getDeviceInstanceLevelStates(String deviceURI)
	{
		Set<String> states = new HashSet<String>();
		// the query
		String qBody = "SELECT ?s WHERE {" + "<" + deviceURI + "> dogOnt:hasState ?s}";
		// exec the query
		ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + qBody, this.model);
		
		// iterate over the results to fill the set
		QuerySolution cSol;
		
		while (res.hasNext())
		{
			// the current query solution
			cSol = res.next();
			// if not null work on it
			if (cSol != null)
			{
				
				// get the state uri and add it the list
				states.add(cSol.getResource("s").getURI());
			}
		}
		return states;
	}
	
	/***
	 * Provide the list of the states of the Device
	 * 
	 * @param deviceURI
	 *            uri of the device
	 * @return list of state belonging to the device
	 */
	public Map<String, String> getDeviceInstanceLevelStatesValues(String stateURI)
	{
		Map<String, String> states = new HashMap<String, String>();
		// the query
		String qBody = "SELECT ?sv WHERE {" + "<" + stateURI + "> dogOnt:hasStateValue ?sv}";
		// exec the query
		ResultSet res = SimplifiedQueryExecutor.execQuery(this.namespaceDecl + qBody, this.model);
		
		// iterate over the results to fill the set
		QuerySolution cSol;
		
		while (res.hasNext())
		{
			// the current query solution
			cSol = res.next();
			// if not null work on it
			if (cSol != null)
			{
				
				Resource stateValue = cSol.getResource("sv");
				if (stateValue != null)
				{
					Property prop = this.model.getProperty(this.namespaces.get("dogOnt") + "#" + "realStateValue");
					String propValue = "";
					try
					{
						propValue = stateValue.getProperty(prop).getString();
					}
					catch (Exception e)
					{
						
					}
					// get the state uri and add it the list
					states.put(stateValue.getURI(), propValue);
				}
			}
		}
		return states;
	}
	
	public Map<String, Set<String>> getInstanceLevelDeviceNetworkParameters(String devURI)
	{
		return this.getDatatypePropertyValuesOf(devURI, "DomoticNetworkComponent");
	}
	
	public Map<String, Set<String>> getInstanceLevelCommandNetworkParameters(String cmdURI)
	{
		return this.getDatatypePropertyValuesOf(cmdURI, "NetworkSpecificCommand");
	}
	
	public Map<String, Set<String>> getInstanceLevelCommandParameters(String cmdURI)
	{
		return this.getDatatypePropertyValuesOf(cmdURI, this.getType(cmdURI, null));
	}
	
	public Map<String, Set<String>> getInstanceLevelNotificationNetworkParameters(String notifURI)
	{
		return this.getDatatypePropertyValuesOf(notifURI, "NetworkSpecificNotification");
	}
	
	public Map<String, Set<String>> getInstanceLevelNotificationParameters(String notifURI)
	{
		return this.getDatatypePropertyValuesOf(notifURI, this.getType(notifURI, null));
	}
	
	/******************************************************************************************************************
	 * 
	 * Common utility methods
	 * 
	 * @param devCategory
	 *            TODO complete
	 * 
	 *****************************************************************************************************************/
	
	public String toShortForm(String devCategory)
	{
		String shortForm = "";
		
		// check if the string contains is a full ontology URI or not (quick and
		// dirt)
		if (devCategory.contains("#"))
		{
			// we assume the devCategory is a full URI
			shortForm = devCategory.substring(devCategory.indexOf('#') + 1);
		}
		else if (devCategory.contains(":"))
		{
			// we assume that the devCategory is in the namespace:name form
			shortForm = devCategory.substring(devCategory.indexOf(':') + 1);
		}
		else
		{
			// it is already in the shortForm
			shortForm = devCategory;
		}
		
		return shortForm;
	}
	
	public Set<String> getTypes(String content)
	{
		// all direct classes
		Individual devInd = this.model.getIndividual(content);
		Set<OntClass> devClasses = devInd.listOntClasses(false).toSet();
		Set<String> returnedClasses = new HashSet<String>();
		for (OntClass devClass : devClasses)
		{
			returnedClasses.add(this.toShortForm(devClass.getURI()));
		}
		return returnedClasses;
	}
	
}
