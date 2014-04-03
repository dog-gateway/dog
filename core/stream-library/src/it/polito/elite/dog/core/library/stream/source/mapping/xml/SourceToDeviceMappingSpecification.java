/*
(c) Dario Bonino, e-Lite research group, http://elite.polito.it
 
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and limitations under the License. 
 
 */
package it.polito.elite.dog.core.library.stream.source.mapping.xml;

import java.io.File;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * @author bonino
 *
 */
public class SourceToDeviceMappingSpecification
{
	public static SensorCollectionType parseXMLSpecification(File xmlSpecificationFile)
	{
		SensorCollectionType sensorCollectionSpec = null;
		if (xmlSpecificationFile != null)
		{
			// if it exists, try to parse the file
			try
			{
				// create a jaxb context working on the classes used to parse
				// the stream processor
				JAXBContext jc = JAXBContext.newInstance("it.polito.elite.dog.core.library.stream.source.mapping.xml");
				Unmarshaller unMarshaller = jc.createUnmarshaller();
				
				@SuppressWarnings("unchecked")
				JAXBElement<SensorCollectionType> jaxbSpecObject = (JAXBElement<SensorCollectionType>) unMarshaller.unmarshal(xmlSpecificationFile);
				sensorCollectionSpec = jaxbSpecObject.getValue();
				
			}
			catch (JAXBException e)
			{
				Logger logger = Logger.getAnonymousLogger();
				logger.severe("Unable to parse the sourceMappingXML, nested exception:\n "+e);
			}
		}
		
		return sensorCollectionSpec;
	}
	
	public static SensorCollectionType parseXMLSpecification(String xmlSpecification)
	{
		SensorCollectionType sensorCollectionSpec = null;
		if ((xmlSpecification != null)&&(!xmlSpecification.isEmpty()))
		{
			// if it exists, try to parse the file
			try
			{
				// create a jaxb context working on the classes used to parse
				// the stream processor
				JAXBContext jc = JAXBContext.newInstance("it.polito.elite.stream.processing.addon.event.source.dog.xmlrpc.xml");
				Unmarshaller unMarshaller = jc.createUnmarshaller();
				
				@SuppressWarnings("unchecked")
				JAXBElement<SensorCollectionType> jaxbSpecObject = (JAXBElement<SensorCollectionType>) unMarshaller.unmarshal(new File(xmlSpecification));
				sensorCollectionSpec = jaxbSpecObject.getValue();
				
			}
			catch (JAXBException e)
			{
				Logger logger = Logger.getAnonymousLogger();
				logger.severe("Unable to parse the sourceMappingXML, nested exception:\n "+e);
			}
		}
		
		return sensorCollectionSpec;
	}
}
