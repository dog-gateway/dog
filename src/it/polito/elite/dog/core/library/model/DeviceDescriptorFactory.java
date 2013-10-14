/*
 * Dog - Core
 * 
 * Copyright (c) 2010-2013 Dario Bonino
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
package it.polito.elite.dog.core.library.model;

import it.polito.elite.dog.core.library.jaxb.Device;
import it.polito.elite.dog.core.library.jaxb.DogHomeConfiguration;

import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.NullLogChute;
import org.apache.velocity.runtime.resource.loader.URLResourceLoader;

/**
 * 1) given a valid descriptor xml (jaxb Device object) generates a descriptor
 * instance (by simply calling the constructor)
 * 
 * 2) given an hashmap of device-specific parameters and a template
 * "folder or classpath" generates a descriptor instance by filling the
 * template, parsing it into a jaxb Device and finally calling the constructor.
 * 
 * @author bonino
 * 
 */
public class DeviceDescriptorFactory
{
	// the velocity engine used to generate the descriptor XML
	private VelocityEngine vtEngine;
	
	// the jaxb unmarshaller for inner parsing of the just generated descriptor
	private Unmarshaller unmarshaller;

	/**
	 * Common variables for templates
	 */
	public static String NAME = "name";
	public static String GATEWAY = "gateway";
	public static String LOCATION = "location";
	public static String DESCRIPTION = "description";

	/**
	 * 
	 */
	public DeviceDescriptorFactory(URL url) throws JAXBException
	{
		// set the template directory
		Properties p = new Properties();
		p.put(RuntimeConstants.RESOURCE_LOADER, "url");
		p.put("url.resource.loader.class", URLResourceLoader.class.getName());
		p.put("url.resource.loader.root", url.toString());
		p.put(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, NullLogChute.class.getName());

		// create the engine
		this.vtEngine = new VelocityEngine(p);

		// initialize the engine
		this.vtEngine.init();

		// initialize the JAXB context
		JAXBContext jaxbContext = JAXBContext.newInstance(DogHomeConfiguration.class
				.getPackage().getName());
		
		unmarshaller = jaxbContext.createUnmarshaller();
	}

	public DeviceDescriptor getDescriptor(
			Map<String, Object> descriptorDefinitionData, String deviceClass) throws JAXBException

	{
		// the device descriptor to return
		DeviceDescriptor descriptor = null;

		// get the device template if any
		Template template = this.vtEngine.getTemplate(deviceClass + ".vm");

		// check not null
		if (template != null)
		{
			// create the context
			VelocityContext context = new VelocityContext();

			// populate the context
			for (String key : descriptorDefinitionData.keySet())
			{
				context.put(key, descriptorDefinitionData.get(key));
			}

			// get the descriptor xml
			// the needed StringWriter
			StringWriter sw = new StringWriter();

			// merge
			template.merge(context, sw);

			// get the descriptor as XML string
			String descriptorAsXMLString = sw.toString();

			// parse the descriptor XML into a jaxb object
			DogHomeConfiguration jaxbHomeDescription = (DogHomeConfiguration) (unmarshaller
					.unmarshal(new StreamSource(new StringReader(
							descriptorAsXMLString)), DogHomeConfiguration.class)).getValue();
			
			Device jaxbDeviceDescription = jaxbHomeDescription.getControllables().get(0).getDevice().get(0);

			//create the device descriptor
			descriptor = new DeviceDescriptor(jaxbDeviceDescription);
		}

		return descriptor;
	}

}
