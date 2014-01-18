/*
 * Dog 2.0 - Core
 * 
 * Copyright (c) [2011]
 * [Emiliano Castellina (emiliano.castellina@polito.it), Politecnico di Torino]
 * [Dario Bonino (dario.bonino@polito.it), Politecnico di Torino]
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package it.polito.elite.dog.core.housemodel.semantic.util;

/**
 * A class that describes an ontology reporting the ontology base URI, the
 * position of the local copy of the ontology (on the filesystem) and the
 * namespace prefix to be used for accessing the ontology-defined entities
 * (classes, individuals and properties).
 * 
 * @author bonino
 * 
 */
public class OntologyDescriptor
{
	/**
	 * the namespace prefix to use
	 */
	private String namespacePrefix;
	
	/**
	 * the ontology base URI (URL)
	 */
	private String url;
	
	/**
	 * the local copy of the ontology (location on the file system)
	 */
	private String localCopy;
	
	/**
	 * Creates a new ontology descriptor object.
	 * 
	 * @param namespace
	 *            The namespace prefix to use for accessing the entities defined
	 *            in the described ontology
	 * @param href
	 *            The base URI of the described ontology
	 * @param src
	 *            The position of the local copy of the ontology (location on
	 *            the file system)
	 */
	public OntologyDescriptor(String namespace, String href, String src)
	{
		this.namespacePrefix = namespace;
		this.url = href;
		this.localCopy = src;
	}
	
	/**
	 * Provides back the prefix to use for accessing entities defined in the
	 * described ontology
	 * 
	 * @return The ontology prefix as {@link String}
	 */
	public String getNamespacePrefix()
	{
		return this.namespacePrefix;
	}
	
	/**
	 * Provides back the base URI (URL) of the described ontology
	 * 
	 * @return The base URI (URL) of the described ontology as {@link String}
	 */
	public String getURL()
	{
		return this.url;
	}
	
	/**
	 * Provides back the position of the local copy of the ontology (location on
	 * the file system)
	 * 
	 * @return The position of the local copy of the ontology as a path
	 *         {@link String}
	 */
	public String getLocalCopy()
	{
		return this.localCopy;
	}
	
}
