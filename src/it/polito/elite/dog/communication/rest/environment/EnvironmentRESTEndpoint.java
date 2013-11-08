/*
 * Dog - Environment REST Endpoint
 * 
 * Copyright (c) 2013 Luigi De Russis
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
package it.polito.elite.dog.communication.rest.environment;

import it.polito.elite.dog.communication.rest.environment.api.EnvironmentRESTApi;
import it.polito.elite.dog.core.housemodel.api.EnvironmentModel;
import it.polito.elite.dog.core.library.jaxb.BuildingEnvironment;
import it.polito.elite.dog.core.library.jaxb.DogHomeConfiguration;
import it.polito.elite.dog.core.library.jaxb.ObjectFactory;
import it.polito.elite.dog.core.library.util.LogHelper;

import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicReference;

import javax.ws.rs.Path;
import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

/**
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
@Path("/api/environment/")
public class EnvironmentRESTEndpoint implements EnvironmentRESTApi
{
	// the service logger
	private LogHelper logger;
	
	// the bundle context reference to extract information on the entire Dog
	// status
	private BundleContext context;
	
	// reference for the HouseModel
	private AtomicReference<EnvironmentModel> environmentModel;
	
	// the instance-level mapper
	private ObjectMapper mapper;
	
	/**
	 * Constructor
	 */
	public EnvironmentRESTEndpoint()
	{
		// init the house model atomic reference
		this.environmentModel = new AtomicReference<EnvironmentModel>();
		
		// initialize the instance-wide object mapper
		this.mapper = new ObjectMapper();
		// set the mapper pretty printing
		this.mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
		// avoid empty arrays and null values
		this.mapper.configure(SerializationConfig.Feature.WRITE_EMPTY_JSON_ARRAYS, false);
		this.mapper.setSerializationInclusion(Inclusion.NON_NULL);
		
		// create an introspector for parsing both Jackson and JAXB annotations
		AnnotationIntrospector jackson = new JacksonAnnotationIntrospector();
		AnnotationIntrospector jaxb = new JaxbAnnotationIntrospector();
		AnnotationIntrospector fullIntrospector = new AnnotationIntrospector.Pair(jackson, jaxb);
		// make deserializer use both Jackson and JAXB annotations
		this.mapper.getDeserializationConfig().withAnnotationIntrospector(fullIntrospector);
		// make serializer use both Jackson and JAXB annotations
		this.mapper.getSerializationConfig().withAnnotationIntrospector(fullIntrospector);
	}
	
	/**
	 * Bundle activation, stores a reference to the context object passed by the
	 * framework to get access to system data, e.g., installed bundles, etc.
	 * 
	 * @param context
	 */
	public void activate(BundleContext context)
	{
		// store the bundle context
		this.context = context;
		
		// init the logger with a null logger
		this.logger = new LogHelper(this.context);
		
		// log the activation
		this.logger.log(LogService.LOG_INFO, "Activated....");
	}
	
	/**
	 * Prepare the bundle to be deactivated...
	 */
	public void deactivate()
	{
		// null the context
		this.context = null;
		
		// log deactivation
		this.logger.log(LogService.LOG_INFO, "Deactivated...");
		
		// null the logger
		this.logger = null;
	}
	
	/**
	 * Bind the EnvironmentModel service (before the bundle activation)
	 * 
	 * @param environmentModel
	 *            the EnvironmentModel service to add
	 */
	public void addedEnvironmentModel(EnvironmentModel environmentModel)
	{
		// store a reference to the HouseModel service
		this.environmentModel.set(environmentModel);
	}
	
	/**
	 * Unbind the EnvironmentModel service
	 * 
	 * @param environmentModel
	 *            the EnvironmentModel service to remove
	 */
	public void removedEnvironmentModel(EnvironmentModel environmentModel)
	{
		this.environmentModel.compareAndSet(environmentModel, null);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.communication.rest.environment.api.EnvironmentRESTApi
	 * #getBuildingInJson()
	 */
	@Override
	public String getBuildingInJson()
	{
		String environmentJSON = "";
		
		// get the JAXB object containing all the building information
		DogHomeConfiguration dhc = this.getBuilding();
		
		try
		{
			environmentJSON = this.mapper.writeValueAsString(dhc.getBuildingEnvironment().get(0));
		}
		catch (Exception e)
		{
			this.logger.log(LogService.LOG_ERROR, "Error in creating the JSON representing all the configured devices",
					e);
		}
		
		return environmentJSON;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.communication.rest.environment.api.EnvironmentRESTApi
	 * #getBuildingInXml()
	 */
	@Override
	public String getBuildingInXml()
	{
		String environmentXML = "";
		
		// get the JAXB object containing all the configured devices
		DogHomeConfiguration dhc = this.getBuilding();
		
		// create the XML for replying the request
		environmentXML = this.generateXML(dhc);
		
		return environmentXML;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.communication.rest.environment.api.EnvironmentRESTApi
	 * #getFlatsInJson()
	 */
	@Override
	public String getFlatsInJson()
	{
		String flatsJSON = "";
		
		// get the JAXB object containing all the building information
		DogHomeConfiguration dhc = this.getBuilding();
		
		try
		{
			flatsJSON = this.mapper.writeValueAsString(dhc.getBuildingEnvironment().get(0).getBuilding().get(0)
					.getFlat());
		}
		catch (Exception e)
		{
			this.logger.log(LogService.LOG_ERROR, "Error in creating the JSON representing all the configured devices",
					e);
		}
		
		return flatsJSON;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.communication.rest.environment.api.EnvironmentRESTApi
	 * #addNewFlat(java.lang.String)
	 */
	@Override
	public void addNewFlat(String addedFlat)
	{
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.communication.rest.environment.api.EnvironmentRESTApi
	 * #getFlat(java.lang.String)
	 */
	@Override
	public String getFlat(String flatId)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.communication.rest.environment.api.EnvironmentRESTApi
	 * #updateFlat(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateFlat(String flatId, String propertiesToUpdate)
	{
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.communication.rest.environment.api.EnvironmentRESTApi
	 * #getRoomsInFlat(java.lang.String)
	 */
	@Override
	public String getRoomsInFlat(String flatId)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.communication.rest.environment.api.EnvironmentRESTApi
	 * #addNewRoomInFlat(java.lang.String, java.lang.String)
	 */
	@Override
	public void addNewRoomInFlat(String flatId, String addedRoom)
	{
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.communication.rest.environment.api.EnvironmentRESTApi
	 * #getSingleRoomInFlat(java.lang.String, java.lang.String)
	 */
	@Override
	public String getSingleRoomInFlat(String flatId, String roomId)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polito.elite.dog.communication.rest.environment.api.EnvironmentRESTApi
	 * #updateRoomInFlat(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateRoomInFlat(String flatId, String roomId, String propertiesToUpdate)
	{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Get all the building environments configured in Dog from the
	 * {@link EnvironmentModel}
	 * 
	 * @return a {@link DogHomeConfiguration} object with all the building
	 *         information
	 */
	private DogHomeConfiguration getBuilding()
	{
		// create a JAXB Object Factory for adding the proper header...
		ObjectFactory factory = new ObjectFactory();
		DogHomeConfiguration dhc = factory.createDogHomeConfiguration();
		
		// check if the HouseModel service is available
		if (this.environmentModel.get() != null)
		{
			// get all the building environment from the HouseModel
			BuildingEnvironment buildingEnv = this.environmentModel.get().getJAXBBuildingEnvironment().get(0);
			
			dhc.getBuildingEnvironment().add(buildingEnv);
		}
		
		return dhc;
	}
	
	/**
	 * Generate the XML to be sent
	 * 
	 * @param dhc
	 *            the {@link DogHomeConfiguration} object to marshall
	 * @return the corresponding XML
	 */
	private String generateXML(DogHomeConfiguration dhc)
	{
		String devicesXML = "";
		
		try
		{
			StringWriter output = new StringWriter();
			
			// marshall the DogHomeConfiguration...
			JAXB.marshal(dhc, output);
			
			devicesXML = output.getBuffer().toString();
		}
		catch (DataBindingException e)
		{
			// the exception can be throw by the JAXB.marshal method...
			this.logger.log(LogService.LOG_ERROR, "Exception in JAXB Marshalling...", e);
		}
		
		return devicesXML;
	}
	
}
