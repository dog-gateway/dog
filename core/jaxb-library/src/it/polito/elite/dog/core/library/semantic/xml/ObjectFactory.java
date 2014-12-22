/*
 * Dog - Core
 * 
 * Copyright (c) 2009-2014 Dario Bonino and Luigi De Russis
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
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.03 at 05:05:17 PM CEST 
//

package it.polito.elite.dog.core.library.semantic.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the it.polito.elite.dog.core.library.semantic
 * package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory
{
	
	private final static QName _Ontologies_QNAME = new QName("http://elite.polito.it/ontologyDescriptorSet",
			"ontologies");
	
	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * it.polito.elite.dog.core.library.semantic
	 * 
	 */
	public ObjectFactory()
	{
	}
	
	/**
	 * Create an instance of {@link Ontologies }
	 * 
	 */
	public Ontologies createOntologies()
	{
		return new Ontologies();
	}
	
	/**
	 * Create an instance of {@link Ontology }
	 * 
	 */
	public Ontology createOntology()
	{
		return new Ontology();
	}
	
	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Ontologies }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://elite.polito.it/ontologyDescriptorSet", name = "ontologies")
	public JAXBElement<Ontologies> createOntologies(Ontologies value)
	{
		return new JAXBElement<Ontologies>(_Ontologies_QNAME, Ontologies.class, null, value);
	}
	
}
