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

import java.io.File;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * An extension of a simple {@link HashSet} designed for holding ontology
 * descriptor objects, i.e., objects that store the information needed to
 * identify and locate an ontology document. An ontology set is a simple set of
 * ontology descriptors enriched by an entry point identifier (namespace) that
 * allows identifying which ontology in the set is the one importing all the
 * others, either directly or indirectly. Typical usage of this set is
 * illustrated in the followinf example where an ontology descriptor set is read
 * from file and used to create a Jena ontModel
 * 
 * <pre>
 * 	{@link File} ontDescSetFile = new {@link File}("ontologyset.xml");
 * 	{@link OntologyDescriptorSet} ontSet = new {@link OntologyDescriptorSet}();
 * 	ontSet.parse(ontDescSetFile);
 * 
 * 	//the Jena ontology manager
 * 	{@link OntDocumentManager} ontManager = {@link OntDocumentManager}.getInstance();
 * 
 * 	//the file object to check that the local copies actually exist
 * 	File fCheck = null;
 * 
 * 	//add alternative locations for all ontologies
 * 	for({@link OntologyDescriptor} cDesc : ontSet)
 * 	{
 * 		// check the file existence
 * 	fCheck = new File(cDesc.getLocalCopy());
 * 				
 * 	if (fCheck.exists())
 * 	{
 * 		// ok, add the alias
 * 		this.manager.addAltEntry(cDesc.getURL(), "file:///" + fCheck.getAbsolutePath());
 * 	}
 * 	}
 * 
 * 	//load the model (and automatically process all the import clauses)
 * OntologyDescriptor entryPoint = ontSet.getEntryPoint();
 * File fOnto = new File(entryPoint.getLocalCopy());
 * 			
 * // read the saved reasoning information
 * final FileInputStream reasonedModelStream = new FileInputStream(fOnto.getPath());
 * 			
 * 	// create a pellet-based model 
 * this.model = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC);
 * this.model.setStrictMode(true);
 * 			
 * // read the model
 * this.model.read(reasonedModelStream, entryPoint.getURL());
 * this.model.prepare();
 * 
 * </pre>
 * 
 * @author bonino
 * 
 */
public class OntologyDescriptorSet extends HashSet<OntologyDescriptor> implements Serializable
{
	
	/**
	 * needed for extending the HashSet class
	 */
	private static final long serialVersionUID = 4803321050319451654L;
	
	/**
	 * The entry point of the ontology set, i.e., the ontology that directly or
	 * indirectly imports all the other ontologies in the set
	 */
	private OntologyDescriptor entryPoint;
	
	/**
	 * Creates a new ontology descriptor set with the given
	 * {@link OntologyDescriptor} instance as entry point. The entry point of an
	 * ontology descriptor set is the ontology descriptor that specifies the
	 * ontology file which directly or indirectly imports all the other ontology
	 * files
	 * 
	 * @param entryPoint
	 *            the {@link OntologyDescriptor} that will be used as entry
	 *            point for this set.
	 */
	public OntologyDescriptorSet(OntologyDescriptor entryPoint)
	{
		super();
		this.entryPoint = entryPoint;
		this.add(entryPoint);
	}
	
	/**
	 * Creates a new, empty, ontology descriptor set
	 * 
	 */
	public OntologyDescriptorSet()
	{
		super();
	}
	
	/**
	 * @return the entryPoint
	 */
	public OntologyDescriptor getEntryPoint()
	{
		return entryPoint;
	}
	
	/**
	 * @param entryPoint
	 *            the {@link OntologyDescriptor} that will be used as entry
	 *            point for this set.
	 */
	public void setEntryPoint(OntologyDescriptor entryPoint)
	{
		this.entryPoint = entryPoint;
	}
	
	/**
	 * Serializes this set of ontology descriptors as an XML string respecting
	 * the ontologyDescriptorSet.xsd schema
	 * 
	 * @return The XML representation ({@link String}) of this object or
	 *         <em>null</em> if some error occurs during the object
	 *         serialization.
	 */
	public String toXMLString()
	{
		// builds the XML representation of this set of ontology descriptors
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document xmlRendering = null;
		try
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
			xmlRendering = builder.newDocument();
		}
		catch (Exception e)
		{
		} // do nothing
		
		// if comes here the document is a proper dom object and can be used to
		// create
		// the XML serialization of this set
		xmlRendering.setXmlVersion("1.0");
		xmlRendering.setXmlStandalone(true);
		xmlRendering.setStrictErrorChecking(true);
		
		// create the root element
		Element root = xmlRendering.createElementNS("http://elite.polito.it/ontologyDescriptorSet",
				"ontdesc:ontologies");
		root.setAttribute("entryPoint", this.entryPoint.getNamespacePrefix());
		root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		root.setAttribute("xsi:schemaLocation",
				"http://elite.polito.it/ontologyDescriptorSet ontologyDescriptorSet.xsd ");
		
		// add the root element
		xmlRendering.appendChild(root);
		
		// for each of the set entries.... add the corresponding element
		Element ontologyDescElement = null;
		for (OntologyDescriptor item : this)
		{
			ontologyDescElement = xmlRendering.createElement("ontology");
			ontologyDescElement.setAttribute("namespace", item.getNamespacePrefix());
			ontologyDescElement.setAttribute("href", item.getURL());
			ontologyDescElement.setAttribute("src", item.getLocalCopy());
			
			// add the element to the document
			root.appendChild(ontologyDescElement);
		}
		
		// write the DOM document to a simple XML string by using a proper
		// transformer
		String xmlString = "";
		
		try
		{
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource domSource = new DOMSource(xmlRendering);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer serializer = tf.newTransformer();
			serializer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.transform(domSource, result);
			xmlString = sw.toString();
		}
		catch (Exception e)
		{
		}// do nothing
		
		return xmlString;
	}
	
	/**
	 * Parses an XML serialization of an OntologyDescriptorSet and rebuilds the
	 * corresponding object. It can work both on {@link String} and {@link File}
	 * objects
	 * 
	 * @param xmlSetDescriptor
	 */
	public static OntologyDescriptorSet parse(Object xmlSetDescriptor)
	{
		// create a new descriptor set object
		OntologyDescriptorSet ontSet = null;
		
		// the DOM representation of the given XML document
		Document domRepresentation = null;
		try
		{
			// prepare the DOM parser
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			// applies the correct parse method depending on the type of object
			// received as parameter
			if (xmlSetDescriptor instanceof String)
				domRepresentation = builder.parse(new InputSource(new StringReader((String) xmlSetDescriptor)));
			else if (xmlSetDescriptor instanceof File)
				domRepresentation = builder.parse((File) xmlSetDescriptor);
			
		}
		catch (Exception e)
		{
		} // no display
		
		// if no exceptions have been raised until now, than the parsing process
		// can start
		
		// build the ontology descriptor set object
		ontSet = new OntologyDescriptorSet();
		
		// get the DOM root node
		Element root = (Element) domRepresentation.getElementsByTagName("ontdesc:ontologies").item(0);
		
		// extract the entry point of the ontology descriptor set
		String entryPointPrefix = root.getAttribute("entryPoint");
		
		// list the root children and iterate over them to build the proper
		// ontology
		// descriptor objects
		NodeList ontologyNodes = root.getElementsByTagName("ontdesc:ontology");
		
		// support variable one-shot declaration
		Element currentOntologyDesc = null;
		String href, src, namespace = null;
		OntologyDescriptor ontoDesc = null;
		
		for (int i = 0; i < ontologyNodes.getLength(); i++)
		{
			// parse the current node and get the namespace, src and href
			// attributes
			currentOntologyDesc = (Element) ontologyNodes.item(i);
			namespace = currentOntologyDesc.getAttribute("namespace");
			href = currentOntologyDesc.getAttribute("href");
			src = currentOntologyDesc.getAttribute("src");
			
			// create the new ontology descriptor object
			ontoDesc = new OntologyDescriptor(namespace, href, src);
			
			if (entryPointPrefix.equalsIgnoreCase(namespace))
				ontSet.setEntryPoint(ontoDesc);
			
			// add the ontology descriptor to the set
			ontSet.add(ontoDesc);
		}
		
		// return the descriptor object
		return ontSet;
	}
}
