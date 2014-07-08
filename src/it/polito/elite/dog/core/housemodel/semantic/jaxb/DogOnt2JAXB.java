/*
 * Dog - Core
 * 
 * Copyright (c) 2014 Luigi De Russis
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
package it.polito.elite.dog.core.housemodel.semantic.jaxb;

import org.semanticweb.owlapi.reasoner.InferenceType;

import it.polito.elite.dog.core.library.jaxb.DogHomeConfiguration;
import it.polito.elite.dog.core.library.jaxb.ObjectFactory;
import it.polito.elite.dog.core.library.semantic.OWLWrapper;

/**
 * Translate an ontology containing instances of DogOnt classes into the JAXB
 * representation used in Dog.
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class DogOnt2JAXB
{
	// builders
	private ControllablesBuilder cBuilder;
	private EnvironmentBuilder eBuilder;
	
	/**
	 * Default constructor.
	 * 
	 * @param owlWrapper
	 *            the {@link OWLWrapper} instance containing all the needed
	 *            OWL-related information
	 */
	public DogOnt2JAXB(OWLWrapper owlWrapper)
	{
		// init
		this.eBuilder = new EnvironmentBuilder(owlWrapper);
		this.cBuilder = new ControllablesBuilder(owlWrapper);
		
		// precompute all the possible inferences
		owlWrapper.getReasoner().precomputeInferences(InferenceType.values());
	}
	
	/**
	 * Get the JAXB!
	 * 
	 * @return a {@link DogHomeConfiguration} object representing the ontology
	 *         in JAXB format
	 */
	public DogHomeConfiguration getJAXBXMLDog()
	{
		// init
		ObjectFactory factory = new ObjectFactory();
		DogHomeConfiguration jaxbConfiguration = factory.createDogHomeConfiguration();
		
		// handle information about the building environment
		jaxbConfiguration.getBuildingEnvironment().add(this.eBuilder.getEnvironmentInformation(factory));
		
		// handle information about controllable devices
		jaxbConfiguration.getControllables().add(this.cBuilder.getControllablesInformation(factory));
		
		return jaxbConfiguration;
	}
}
