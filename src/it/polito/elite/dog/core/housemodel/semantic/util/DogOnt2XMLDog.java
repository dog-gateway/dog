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
package it.polito.elite.dog.core.housemodel.semantic.util;

import it.polito.elite.dog.core.housemodel.semantic.loader.QuickModelLoader;
import it.polito.elite.dog.core.housemodel.semantic.loader.QuickModelLoader.ModelTypes;
import it.polito.elite.dog.core.housemodel.semantic.query.SPARQLQueryWrapper;
import it.polito.elite.dog.core.library.jaxb.Building;
import it.polito.elite.dog.core.library.jaxb.BuildingEnvironment;
import it.polito.elite.dog.core.library.jaxb.Ceiling;
import it.polito.elite.dog.core.library.jaxb.Commands;
import it.polito.elite.dog.core.library.jaxb.Configcommand;
import it.polito.elite.dog.core.library.jaxb.Confignotification;
import it.polito.elite.dog.core.library.jaxb.Configparam;
import it.polito.elite.dog.core.library.jaxb.Configstate;
import it.polito.elite.dog.core.library.jaxb.ControlFunctionality;
import it.polito.elite.dog.core.library.jaxb.Controllables;
import it.polito.elite.dog.core.library.jaxb.Device;
import it.polito.elite.dog.core.library.jaxb.DogHomeConfiguration;
import it.polito.elite.dog.core.library.jaxb.Flat;
import it.polito.elite.dog.core.library.jaxb.Floor;
import it.polito.elite.dog.core.library.jaxb.Garage;
import it.polito.elite.dog.core.library.jaxb.Garden;
import it.polito.elite.dog.core.library.jaxb.NotificationFunctionality;
import it.polito.elite.dog.core.library.jaxb.Notifications;
import it.polito.elite.dog.core.library.jaxb.ObjectFactory;
import it.polito.elite.dog.core.library.jaxb.Room;
import it.polito.elite.dog.core.library.jaxb.Statevalue;
import it.polito.elite.dog.core.library.jaxb.Statevalues;
import it.polito.elite.dog.core.library.jaxb.Storey;
import it.polito.elite.dog.core.library.jaxb.Wall;
import it.polito.elite.dog.core.library.jaxb.WallOpening;
import it.polito.elite.utilities.cmd.Options;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.mindswap.pellet.jena.PelletInfGraph;

import com.hp.hpl.jena.ontology.OntModel;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import com.sun.xml.bind.v2.WellKnownNamespace;

/**
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class DogOnt2XMLDog
{
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		// a utility object for managing command line arguments...
		Options opt = new Options(new String[] { "-i ontologyDescriptor", "The ontology descriptor file", "-o output",
				"The output file" }, "java DogOnt2Dog", args);
		
		// the ontology file name holder
		String ontologyDescriptorFileName = null;
		String outputFileName = null;
		File ontologyDescriptorSetFile = null;
		File outputFile = null;
		
		// check if all the needed parameters are available otherwise exit
		// with a fail code
		if ((opt.getValue('o') != null) && (opt.getValue('i') != null))
		{
			// the ontology files
			ontologyDescriptorFileName = opt.getValue('i');
			outputFileName = opt.getValue('o');
			
			ontologyDescriptorSetFile = new File(ontologyDescriptorFileName);
			outputFile = new File(outputFileName);
			
			if (!ontologyDescriptorSetFile.exists())
			{
				System.err.println("The specified ontology descriptor file: " + ontologyDescriptorFileName
						+ " does not exist");
				System.exit(-1);
			}
			if (!outputFile.createNewFile())
			{
				System.err.print("The specified output file already exists... overwrite?[yes/no]:");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String answer = reader.readLine();
				if (answer.contains("yes"))
				{
					outputFile.delete();
					outputFile.createNewFile();
				}
				else
					System.exit(-1);
			}
		}
		else
		{
			// output the Error
			System.err.println("Error: not all the required parameters have been specified");
			opt.usage(System.err, "java DogOnt2XMLDog");
			System.exit(-1);
		}
		
		// create the DogOnt2XMLDog object
		DogOnt2XMLDog xmlBuilder = new DogOnt2XMLDog(OntologyDescriptorSet.parse(ontologyDescriptorSetFile));
		
		// get the xml string
		try
		{
			String xmlDog = xmlBuilder.getXMLDog();
			System.out.print(xmlDog);
			FileWriter fw = new FileWriter(outputFile);
			fw.write(xmlDog);
			fw.flush();
			fw.close();
		}
		catch (Exception e)
		{
			System.err.println("Error while extracting the xmldog representation: " + e);
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/****************************************************************************************************
	 * 
	 * 
	 * The Class, to be easily reused
	 * 
	 * 
	 ****************************************************************************************************/
	
	private OntModel ontology;
	private SPARQLQueryWrapper qWrapper;
	private Map<String, String> namespaces;
	private String modelToLoadNS;
	private QuickModelLoader loader;
	private Map<String, Object> ids2JaxbObjects;
	private Map<String, Set<Configcommand>> candidateOrphans;
	private JAXBContext jc;
	
	public DogOnt2XMLDog(OntologyDescriptorSet ontologyDesc) throws JAXBException
	{
		// load the ontology and prepare the inner objects to perform the
		// conversion
		this.loader = new QuickModelLoader(ontologyDesc, ModelTypes.REASONED_WITH_PELLET);
		this.loader.loadOntology();
		
		// store the model
		this.ontology = loader.getModel();
		// loader.getpModel();
		this.namespaces = loader.getNamespaces();
		
		// get the namespace of the model to load
		this.modelToLoadNS = this.loader.getModelToLoadNS();
		
		// create the queryWrapper
		this.qWrapper = new SPARQLQueryWrapper(namespaces, this.ontology);
		
		// create the IDs map
		this.ids2JaxbObjects = new HashMap<String, Object>();
		this.candidateOrphans = new HashMap<String, Set<Configcommand>>();
		
		// init the JAXB context
		this.jc = JAXBContext.newInstance(DogHomeConfiguration.class.getPackage().getName());
		
		// ready to perform the conversion...
	}
	
	public DogOnt2XMLDog(OntModel ontologyModel, Map<String, String> namespaces, String modelToLoadNS) throws JAXBException
	{
		// store the model
		this.ontology = ontologyModel;
		// loader.getpModel();
		this.namespaces = namespaces;
		this.modelToLoadNS = modelToLoadNS;
		
		// create the queryWrapper
		this.qWrapper = new SPARQLQueryWrapper(namespaces, this.ontology);
		
		// create the IDs map
		this.ids2JaxbObjects = new HashMap<String, Object>();
		this.candidateOrphans = new HashMap<String, Set<Configcommand>>();
		
		// init the JAXB context
		this.jc = JAXBContext.newInstance(DogHomeConfiguration.class.getPackage().getName());
		
		// ready to perform the conversion...
	}
	
	public String getXMLDog() throws Exception
	{
		// prepare the model and disable the model listener
		// disable reasoned model change detection (works only if the ontology
		// is loaded with Pellet
		
		
		PelletInfGraph infG = ((PelletInfGraph) this.ontology.getGraph());
		infG.classify();
		infG.realize();
		infG.setAutoDetectChanges(false);
		
		// create the xmldog docum
		ObjectFactory factory = new ObjectFactory();
		DogHomeConfiguration xmldog = factory.createDogHomeConfiguration();
		
		// handle environment information (only one environment per ontology)
		xmldog.getBuildingEnvironment().add(this.getEnvironmentInformation(factory));
		
		// handle information about controllable devices
		xmldog.getControllables().add(this.getControllablesInformation(factory));
		
		// handle orphan commands i.e., commands that shall be inserted only as
		// a consequence of being
		// pointed by a
		// generatesCommand relation
		// TODO check if is already necessary. Now all commands are generated in
		// explicit way
		// this.handleOrphans();
		
		// marshall as string
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
				"http://elite.polito.it/dogHomeConfiguration home_configuration.xsd ");
		
		StringWriter sw = new StringWriter();
		// Object that maps prefixes
		PreferredMapper pm = new PreferredMapper();
		m.setProperty("com.sun.xml.bind.namespacePrefixMapper", pm);
		m.marshal(xmldog, sw);// xmlStreamWriter);
		
		return sw.toString();
	}
	
	public DogHomeConfiguration getJAXBXMLDog() throws Exception
	{
		// prepare the model and disable the model listener
		// disable reasoned model change detection (works only if the ontology
		// is loaded with Pellet
	
		
		PelletInfGraph infG = ((PelletInfGraph) this.ontology.getGraph());
		infG.classify();
		infG.realize();
		infG.setAutoDetectChanges(false);
		
		// create the xmldog document
		ObjectFactory factory = new ObjectFactory();
		DogHomeConfiguration xmldog = factory.createDogHomeConfiguration();
		
		// handle environment information (only one environment per ontology)
		xmldog.getBuildingEnvironment().add(this.getEnvironmentInformation(factory));
		
		// handle information about controllable devices
		xmldog.getControllables().add(this.getControllablesInformation(factory));
		
		// return the JAXB object representing the current dog configuration as
		// extracted from the ontology
		return xmldog;
	}
	
	/**************************************************************************************************************
	 * 
	 * Environment Handling
	 * 
	 *************************************************************************************************************/
	
	/**
	 * 
	 * @param factory
	 * @return
	 */
	private BuildingEnvironment getEnvironmentInformation(ObjectFactory factory)
	{
		// gather information about the environment composition
		BuildingEnvironment env = factory.createBuildingEnvironment();
		
		// list all the home environments
		Set<String> allBuildings = this.qWrapper.getAllBuildingInstances(true);
		
		for (String cBuilding : allBuildings)
		{
			// create a building object
			Building building = factory.createBuilding();
			building.setId(cBuilding);
			
			// store the element reference in the reference map in order to
			// possibly link other
			// elements to this element
			this.ids2JaxbObjects.put(cBuilding, building);
			
			// add the building element
			env.getBuilding().add(building);
			
			// get the single building information
			// this.getBuildingInformation(factory, building);
			this.getBuildingInstanceInformation(factory, building);
		}
		
		return env;
	}
	
	private void getBuildingInstanceInformation(ObjectFactory factory, Building building)
	{
		// compose the uri of the building
		String buildingUri = this.modelToLoadNS + "#" + building.getId();
		
		Set<String> buildingContents = this.qWrapper.getContainedInstanceUri(buildingUri);
		for (String buildingContent : buildingContents)
		{
			String instanceName = this.qWrapper.toShortForm(buildingContent);
			
			String buildingContentType = this.qWrapper.getType(buildingContent, null);
			if (buildingContentType.contains("Garden"))
			{
				Garden garden = factory.createGarden();
				
				garden.setId(instanceName);
				
				// store the element reference in the reference map in order to
				// possibly link other
				// elements to this element
				this.ids2JaxbObjects.put(instanceName, garden);
				
				// add the garden to the building
				building.getGarden().add(garden);
				
			}
			else if (buildingContentType.contains("Garage"))
			{
				// create the garage object
				Garage garage = factory.createGarage();
				garage.setId(instanceName);
				
				// store the element reference in the reference map in order to
				// possibly link other
				// elements to this element
				this.ids2JaxbObjects.put(instanceName, garage);
				
				// add the garage to the building
				building.getGarage().add(garage);
				
			}
			else if (buildingContentType.contains("Flat"))
			{
				Flat flat = factory.createFlat();
				flat.setId(instanceName);
				flat.setClazz("Flat");
				// store the element reference in the reference map in order to
				// possibly link other
				// elements to this element
				this.ids2JaxbObjects.put(instanceName, flat);
				
				// gather flat information
				this.getFlatInformation(factory, flat);
				
				// add the flat to the building
				building.getFlat().add(flat);
				
			}
			else if (buildingContentType.contains("Storey"))
			{
				Storey storey = factory.createStorey();
				storey.setId(instanceName);
				storey.setClazz("Storey");
				
				// store the element reference in the reference map in order to
				// possibly link other
				// elements to this element
				this.ids2JaxbObjects.put(instanceName, storey);
				
				// gather flat information
				this.getStoreyInformation(factory, storey);
				
				// add the flat to the building
				building.getStorey().add(storey);
				
			}
			
		}
		
	}
	
	private void getStoreyInformation(ObjectFactory factory, Storey storey)
	{
		String storeyUri = this.modelToLoadNS + "#" + storey.getId();
		// first, get the svg footpring attribute
		
		Map<String, Set<String>> datatypes = this.qWrapper.getDatatypePropertyValuesOf(storeyUri, "Storey");
		
		// search for the svgfootprint
		if (!datatypes.isEmpty())
		{
			Set<String> levelFromGruonds = datatypes.get("levelFromGround");
			if (!levelFromGruonds.isEmpty())
			{
				
				try
				{
					storey.setLevelFromGround(new Integer(levelFromGruonds.iterator().next()));
				}
				catch (NumberFormatException e)
				{
					e.printStackTrace();
					storey.setLevelFromGround(new Integer(0));
					
				}
			}
		}
		
		// get all the rooms
		Set<String> allInst = this.qWrapper.getContainedInstanceUri(storeyUri);
		
		// add the rooms
		for (String content : allInst)
		{
			Set<String> contentType = this.qWrapper.getTypes(content);
			String contentInstanceName = this.qWrapper.toShortForm(content);
			
			if (contentType.contains("Room"))
			{
				// create the room object
				Room room = factory.createRoom();
				room.setId(contentInstanceName);
				
				// store the element reference in the reference map in order to
				// possibly link other
				// elements to this element
				this.ids2JaxbObjects.put(contentInstanceName, room);
				
				// gather room information
				this.getRoomInformation(factory, room);
				
				// add the room to the flat
				storey.getRoom().add(room);
			}
			else if (contentType.contains("Flat"))
			{
				Flat flat = factory.createFlat();
				flat.setId(contentInstanceName);
				
				// store the element reference in the reference map in order to
				// possibly link other
				// elements to this element
				this.ids2JaxbObjects.put(contentInstanceName, flat);
				
				// gather flat information
				this.getFlatInformation(factory, flat);
				
				// add the flat to the building
				storey.getFlat().add(flat);
				
			}
		}
		
	}
	
	private void getFlatInformation(ObjectFactory factory, Flat flat)
	{
		String flatUri = this.modelToLoadNS + "#" + flat.getId();
		// first, get the svg footpring attribute
		
		Map<String, Set<String>> datatypes = this.qWrapper.getDatatypePropertyValuesOf(flatUri, "Flat");
		
		// search for the svgfootprint
		if (!datatypes.isEmpty())
		{
			Set<String> svgFootprints = datatypes.get("svgFootprint");
			if (!svgFootprints.isEmpty())
			{
				flat.setSvgfootprint(svgFootprints.iterator().next());
			}
		}
		
		// get all the rooms
		Set<String> allInst = this.qWrapper.getContainedInstanceUri(flatUri);
		
		// add the rooms
		for (String roomInstance : allInst)
		{
			String roomInstanceName = this.qWrapper.toShortForm(roomInstance);
			// create the room object
			Room room = factory.createRoom();
			room.setId(roomInstanceName);
			
			// store the element reference in the reference map in order to
			// possibly link other
			// elements to this element
			this.ids2JaxbObjects.put(roomInstanceName, room);
			
			// gather room information
			this.getRoomInformation(factory, room);
			
			// add the room to the flat
			flat.getRoom().add(room);
		}
	}
	
	private void getRoomInformation(ObjectFactory factory, Room room)
	{
		// first set the room class
		String roomURI = this.modelToLoadNS + "#" + room.getId();
		String clazz = this.qWrapper.getType(roomURI, null);
		room.setClazz(clazz);
		
		// get the room ceiling
		String ceilingName = this.qWrapper.getCeilingOf(roomURI);
		if (ceilingName != null)
		{
			// create the ceiling object
			Ceiling ceiling = factory.createCeiling();
			ceiling.setId(ceilingName);
			
			// store the element reference in the reference map in order to
			// possibly link other
			// elements to this element
			this.ids2JaxbObjects.put(ceilingName, ceiling);
			
			// set the class of the ceiling object
			ceiling.setClazz(this.qWrapper.getType(this.modelToLoadNS + "#" + ceilingName, null));
			
			// add the ceiling to the room
			room.setCeiling(ceiling);
		}
		
		// get the room floor
		String floorName = this.qWrapper.getFloorOf(roomURI);
		if (floorName != null)
		{
			// create the floor object
			Floor floor = factory.createFloor();
			floor.setId(floorName);
			
			// store the element reference in the reference map in order to
			// possibly link other
			// elements to this element
			this.ids2JaxbObjects.put(floorName, floor);
			
			// set the class of the floor object
			floor.setClazz(this.qWrapper.getType(this.modelToLoadNS + "#" + floorName, null));
			
			// add the floor to the room
			room.setFloor(floor);
		}
		
		// get the room walls
		Set<String> wallInstances = this.qWrapper.getAllWallsOf(roomURI);
		if ((wallInstances != null) && (!wallInstances.isEmpty()))
		{
			for (String wallName : wallInstances)
			{
				// create the wall object
				Wall wall = factory.createWall();
				
				// if the wall already exists, then this room shall only refer
				// to it, otherwise the wall
				// shall be created
				// from scratch
				Object wallObj = this.ids2JaxbObjects.get(wallName);
				
				if (wallObj != null)
				{
					wall.setRef(wallObj);
				}
				else
				{
					wall.setId(wallName);
					// store the element reference in the reference map in order
					// to possibly link other
					// elements to this element
					this.ids2JaxbObjects.put(wallName, wall);
					
					// set the class of the wall object
					wall.setClazz(this.qWrapper.getType(this.modelToLoadNS + "#" + wallName, null));
					
					// get all needed data about the wall
					this.getWallInformation(factory, wall);
				}
				
				// add the wall
				room.getWall().add(wall);
				
			}
		}
		else
		{
			System.err.println("[Error!!] A room without walls has been encountered, generated XML will NOT be valid!");
		}
		
	}
	
	private void getWallInformation(ObjectFactory factory, Wall wall)
	{
		// regenerate the wall URI
		String wallURI = this.modelToLoadNS + "#" + wall.getId();
		
		// get all the wallOpenings inside the wall
		Set<String> wallOpnInstances = this.qWrapper.getAllWallOpeningsIn(wallURI);
		
		if ((wallOpnInstances != null) && (!wallOpnInstances.isEmpty()))
		{
			// iterate over all the wall openings
			for (String wallOpnInst : wallOpnInstances)
			{
				// create the wall opening
				WallOpening opening = factory.createWallOpening();
				
				// if the wall opening already exists, then this room shall only
				// refer to it, otherwise
				// the wall shall be created
				// from scratch
				Object openingObj = this.ids2JaxbObjects.get(wallOpnInst);
				
				if (openingObj != null)
				{
					opening.setRef(openingObj);
				}
				else
				{
					// set the opening name
					opening.setId(wallOpnInst);
					
					// store the element reference in the reference map in order
					// to possibly link other
					// elements to this element
					this.ids2JaxbObjects.put(wallOpnInst, opening);
					
					// set the opening class
					opening.setClazz(this.qWrapper.getType(this.modelToLoadNS + "#" + wallOpnInst, null));
				}
				
				// add the opening
				wall.getHasWallOpening().add(opening);
			}
		}
	}
	
	/**************************************************************************************************************
	 * 
	 * Controllables Handling
	 * 
	 *************************************************************************************************************/
	
	private Controllables getControllablesInformation(ObjectFactory factory)
	{
		// create the controllables container element
		Controllables controllables = factory.createControllables();
		
		// get all the controllable devices
		Set<String> allControllableInstances = this.qWrapper.getAllControllableInstances();
		
		// create a device object for each controllable and then fill it
		for (String cControllableInst : allControllableInstances)
		{
			// set the device name
			String devName = this.qWrapper.toShortForm(cControllableInst);
			
			// if the device already exists, then it only needs to be filled,
			// otherwise the wall shall
			// be created
			// from scratch
			Object obj = this.ids2JaxbObjects.get(devName);
			
			Device device;
			if (obj != null)
				device = (Device) obj;
			else
			{
				// creates the device
				device = factory.createDevice();
				device.setId(devName);
				
				// store the element reference in the reference map in order to
				// possibly link other
				// elements to this element
				this.ids2JaxbObjects.put(devName, device);
			}
			
			// get the device class filtering network components
			String devType = this.qWrapper.getType(cControllableInst, this.namespaces.get("dogOnt")
					+ "#DomoticNetworkComponent");
			if ((devType != null) && (!devType.isEmpty()))
				device.setClazz(devType);
			else
				device.setClazz(this.qWrapper.getType(cControllableInst, null));
			
			// get the device network type, if exists
			String networkType = this.qWrapper.getType(cControllableInst, this.namespaces.get("dogOnt")
					+ "#BuildingThing");
			
			if(networkType==null)
			{
				networkType = this.qWrapper.getType(cControllableInst, null);
			}
			
			if ((networkType != null) && (!networkType.isEmpty()))
			{
				// extracts the domotic system by "handling" names with string
				// operations, may be weak
				int cIndex = -1;
				if (networkType.endsWith("Component"))
					cIndex = networkType.indexOf("Component");
				else if (networkType.endsWith("Gateway"))
					cIndex = networkType.indexOf("Gateway");
				
				// sets the domotic system of the device
				device.setDomoticSystem(networkType.substring(0, cIndex).toUpperCase());
				
			}
				
			// get all other device infos
			this.getDeviceInformation(factory, device);
			
			// add the device
			controllables.getDevice().add(device);
		}
		
		return controllables;
	}
	
	private void getDeviceInformation(ObjectFactory factory, Device device)
	{
		String deviceURI = this.modelToLoadNS + "#" + device.getId();
		
		// get the device location
		Set<String> isInLocations = this.qWrapper.getIsIn(deviceURI);
		if ((isInLocations != null) && (!isInLocations.isEmpty()))
		{
			for (String location : isInLocations)
			{
				// get the location reference
				Object locationObj = this.ids2JaxbObjects.get(location);
				
				if (locationObj != null)
					// add the location
					device.setIsIn(((Room) locationObj).getId());
			}
		}
		
		// get the gateway
		String gateway = this.qWrapper.getHasGateway(deviceURI);
		if (gateway != null)
		{
			device.setGateway(gateway);
		}
		
		// get the meter
		String meter = this.qWrapper.getHasMeter(deviceURI);
		if ((meter != null) && (!meter.isEmpty()))
		{
			Object meterObj = this.ids2JaxbObjects.get(meter);
			
			if (meterObj != null)
				device.setHasMeter(((Device) meterObj).getId());
			else
			{
				Device cntrlDevice = factory.createDevice();
				cntrlDevice.setId(meter);
				// store the element reference in the reference map in order
				// to possibly link other
				// elements to this element
				this.ids2JaxbObjects.put(meter, cntrlDevice);
				device.setHasMeter(meter);
			}
		}
		
		// get meterOf
		Set<String> meteredDevices = this.qWrapper.getMeterOf(deviceURI);
		if ((meteredDevices != null) && (!meteredDevices.isEmpty()))
		{
			for (String meteredDevice : meteredDevices)
			{
				Object meteredDeviceObj = this.ids2JaxbObjects.get(meteredDevice);
				
				if (meteredDeviceObj != null)
					device.getMeterOf().add((((Device) meteredDeviceObj).getId()));
				else
				{
					Device cntrlDevice = factory.createDevice();
					cntrlDevice.setId(meteredDevice);
					// store the element reference in the reference map in order
					// to possibly link other
					// elements to this element
					this.ids2JaxbObjects.put(meteredDevice, cntrlDevice);
					device.getMeterOf().add(meteredDevice);
				}
			}
		}
		
		// get sensorOf
		String sensor = this.qWrapper.getSensorOf(deviceURI);
		if ((sensor != null) && (!sensor.isEmpty()))
		{
			Object sensorObj = this.ids2JaxbObjects.get(sensor);
			
			if (sensorObj != null)
				device.setSensorOf(((Device) sensorObj).getId());
			else
			{
				Device cntrlDevice = factory.createDevice();
				cntrlDevice.setId(sensor);
				// store the element reference in the reference map in order
				// to possibly link other
				// elements to this element
				this.ids2JaxbObjects.put(sensor, cntrlDevice);
				device.setSensorOf(sensor);
			}
		}
		
		// pluggedIn
		String plug = this.qWrapper.getPluggedIn(deviceURI);
		if ((plug != null) && (!plug.isEmpty()))
		{
			Object plugObj = this.ids2JaxbObjects.get(plug);
			
			if (plugObj != null)
				device.setPluggedIn(((Device) plugObj).getId());
			else
			{
				Device cntrlDevice = factory.createDevice();
				cntrlDevice.setId(plug);
				// store the element reference in the reference map in order
				// to possibly link other
				// elements to this element
				this.ids2JaxbObjects.put(plug, cntrlDevice);
				device.setPluggedIn(plug);
			}
		}
		
		// first get the device description
		String desc = this.qWrapper.getDescriptionOf(deviceURI);
		if ((desc != null) && (!desc.isEmpty()))
			device.setDescription(desc);
		else
			device.setDescription("A " + device.getClazz() + " instance named " + device.getId());
		
		// get the device parameters
		Map<String, Set<String>> parameters = this.qWrapper.getInstanceLevelDeviceNetworkParameters(deviceURI);
		
		for (String parameterName : parameters.keySet())
		{
			Set<String> paramValues = parameters.get(parameterName);
			
			for (String value : paramValues)
			{
				Configparam parameter = factory.createConfigparam();
				parameter.setName(parameterName);
				String valueType[] = value.split("\\^\\^");
				parameter.setValue(valueType[0]);
				if (valueType.length == 2)
				{
					parameter.setType(valueType[1]);
				}
				device.getParam().add(parameter);
			}
		}
		
		// try to set the actuatorOf property
		String actuatedObject = this.qWrapper.getActuatorOf(deviceURI);
		if ((actuatedObject != null) && (!actuatedObject.isEmpty()))
		{
			Object actObj = this.ids2JaxbObjects.get(actuatedObject);
			
			if (actObj != null)
				device.setActuatorOf(((WallOpening) actObj).getId());
			
		}
		
		// try to set the controlledObject property
		Set<String> controlledObjects = this.qWrapper.getControlledObjects(deviceURI);
		if ((controlledObjects != null) && (!controlledObjects.isEmpty()))
		{
			for (String controlledObject : controlledObjects)
			{
				Object cntrlObj = this.ids2JaxbObjects.get(controlledObject);
				
				if (cntrlObj != null)
					device.getControls().add((((Device) cntrlObj).getId()));
				else
				{
					Device cntrlDevice = factory.createDevice();
					cntrlDevice.setId(controlledObject);
					// store the element reference in the reference map in order
					// to possibly link other
					// elements to this element
					this.ids2JaxbObjects.put(controlledObject, cntrlDevice);
					device.getControls().add(controlledObject);
				}
			}
		}
		
		this.getSpecificCommandInformation(factory, device);
		this.getSpecificNotificationInformation(factory, device);
		this.getSpecificStateInformation(factory, device);
	}
	
	/***
	 * Retrieves from ontology the information relative to the states of the
	 * device
	 * 
	 * @param factory
	 * @param device
	 */
	private void getSpecificStateInformation(ObjectFactory factory, Device device)
	{
		String deviceURI = this.modelToLoadNS + "#" + device.getId();
		Set<String> states = this.qWrapper.getDeviceInstanceLevelStates(deviceURI);
		if (states != null)
		{
			// for each state uri
			for (String state : states)
			{
				// get the state class
				String stateClass = this.qWrapper.getType(state, null);
				Configstate stateElement = factory.createConfigstate();
				stateElement.setClazz(stateClass);
				
				Statevalues stateValues = factory.createStatevalues();
				Map<String, String> stateValueMap = this.qWrapper.getDeviceInstanceLevelStatesValues(state);
				for (String stateValueUri : stateValueMap.keySet())
				{
					String stateValueClass = this.qWrapper.getType(stateValueUri, null);
					String stateValueName = stateValueMap.get(stateValueUri);
					Statevalue statevalueElement = factory.createStatevalue();
					statevalueElement.setClazz(stateValueClass);
					statevalueElement.setName(stateValueName);
					stateValues.getStatevalue().add(statevalueElement);
				}
				stateElement.setStatevalues(stateValues);
				
				device.getState().add(stateElement);
				
			}
		}
		
	}
	
	private void getSpecificCommandInformation(ObjectFactory factory, Device device)
	{
		String deviceURI = this.modelToLoadNS + "#" + device.getId();
		
		Map<String, Set<String>> functionalities = this.qWrapper
				.getDeviceInstanceSpecificFunctionalitiesCommands(deviceURI);
		
		for (String functionality : functionalities.keySet())
		{
			Set<String> cmds = functionalities.get(functionality);
			ControlFunctionality controlFunctionality = factory.createControlFunctionality();
			controlFunctionality.setClazz(this.qWrapper.getType(functionality, null));
			Commands commands = factory.createCommands();
			// get all information about network specific commands associated to
			// this device
			for (String cmdInstanceURI : cmds)
			{
				String commandName = this.qWrapper.toShortForm(cmdInstanceURI);
				
				// if the command has already been created, then it only needs
				// to be filled, otherwise
				// it needs to be created from scratch
				Object commandObj = this.ids2JaxbObjects.get(commandName);
				Configcommand cmd;
				
				if (commandObj != null)
				{
					cmd = (Configcommand) commandObj;
					// handle candidate orphans commands, i.e., command which
					// are already linked by a
					// generatesCommand relation but that
					// still need to be included in the jaxb model
					Set<Configcommand> cOrphans = this.candidateOrphans.get(device.getId());
					if (cOrphans != null)
					{
						if (cOrphans.contains(cmd))
							cOrphans.remove(cmd);
						if (cOrphans.isEmpty())
							this.candidateOrphans.remove(device.getId());
					}
				}
				else
				{
					cmd = factory.createConfigcommand();
					cmd.setName(commandName);
					cmd.setId(commandName);
					// store the element reference in the reference map in order
					// to possibly link other
					// elements to this element
					this.ids2JaxbObjects.put(cmd.getName(), cmd);
				}
				
				// get the command type
				/*
				 * cmd.setClazz(this.qWrapper.getType(cmdInstanceURI,
				 * this.namespaces.get("dogOnt") + "#NetworkSpecificCommand"));
				 */
				cmd.setClazz(this.qWrapper.getType(cmdInstanceURI, null));
				// get the device parameters
				Map<String, Set<String>> parameters = this.qWrapper.getInstanceLevelCommandParameters(cmdInstanceURI);
				parameters.putAll(this.qWrapper.getInstanceLevelCommandNetworkParameters(cmdInstanceURI));
				
				for (String parameterName : parameters.keySet())
				{
					Set<String> paramValues = parameters.get(parameterName);
					
					for (String value : paramValues)
					{
						Configparam parameter = factory.createConfigparam();
						parameter.setName(parameterName);
						String valueType[] = value.split("\\^\\^");
						parameter.setValue(valueType[0]);
						if (valueType.length == 2)
						{
							parameter.setType(valueType[1]);
						}
						cmd.getParam().add(parameter);
					}
				}
				
				commands.getCommand().add(cmd);
			}
			
			controlFunctionality.setCommands(commands);
			device.getControlFunctionality().add(controlFunctionality);
			
		}
	}
	
	private void getSpecificNotificationInformation(ObjectFactory factory, Device device)
	{
		String deviceURI = this.modelToLoadNS + "#" + device.getId();
		Map<String, Set<String>> functionalitiesNotifications = this.qWrapper
				.getDeviceInstanceSpecificFunctionalitiesNotifications(deviceURI);
		// for each notification functionality
		for (String functionality : functionalitiesNotifications.keySet())
		{
			Set<String> notifications = functionalitiesNotifications.get(functionality);
			NotificationFunctionality notificationFunctionality = factory.createNotificationFunctionality();
			notificationFunctionality.setClazz(this.qWrapper.getType(functionality, null));
			
			Notifications notificationsElement = factory.createNotifications();
			
			for (String notificationInstanceURI : notifications)
			{
				Confignotification notification = factory.createConfignotification();
				notification.setId(this.qWrapper.toShortForm(notificationInstanceURI));
				this.ids2JaxbObjects.put(notification.getId(), notification);
				
				// get the notification type
				notification.setClazz(this.qWrapper.getType(notificationInstanceURI, null));
				
				// get the notification parameters
				Map<String, Set<String>> parameters = this.qWrapper
						.getInstanceLevelNotificationParameters(notificationInstanceURI);
				parameters.putAll(this.qWrapper.getInstanceLevelNotificationNetworkParameters(notificationInstanceURI));
				
				for (String parameterName : parameters.keySet())
				{
					Set<String> paramValues = parameters.get(parameterName);
					
					for (String value : paramValues)
					{
						Configparam parameter = factory.createConfigparam();
						parameter.setName(parameterName);
						
						String valueType[] = value.split("\\^\\^");
						parameter.setValue(valueType[0]);
						if (valueType.length == 2)
						{
							parameter.setType(valueType[1]);
						}
						notification.getParam().add(parameter);
					}
				}
				
				// try to set the generatesCommand property
				Set<String> allGeneratedCommands = this.qWrapper.getGeneratesCommands(notificationInstanceURI);
				if (allGeneratedCommands != null)
				{
					for (String generatedCommand : allGeneratedCommands)
					{
						Object cmdObj = this.ids2JaxbObjects.get(generatedCommand);
						
						if (cmdObj == null)
						{
							Configcommand genCmd = factory.createConfigcommand();
							genCmd.setName(generatedCommand);
							genCmd.setId(generatedCommand);
							this.ids2JaxbObjects.put(generatedCommand, genCmd);
							
							// in this case the command may remain "undeclared"
							// in the JAXB model, e.g.,
							// everytime the command has no
							// network specific parameters, in such a case we
							// want to add the command to
							// the
							// right device, anyway
							// therefore we keep a list of possibly orphan
							// commands and of the relative
							// devices to which they
							// shall be associated
							String deviceName = this.qWrapper.getCommandOf(this.modelToLoadNS + "#" + generatedCommand);
							Set<Configcommand> orphans = this.candidateOrphans.get(deviceName);
							if (orphans != null)
								orphans.add(genCmd);
							else
							{
								orphans = new HashSet<Configcommand>();
								orphans.add(genCmd);
								this.candidateOrphans.put(deviceName, orphans);
							}
							
						}
						notification.getGeneratesCommand().add(generatedCommand);
						
					}
					
				}
				notificationsElement.getNotification().add(notification);
				
			}
			notificationFunctionality.setNotifications(notificationsElement);
			device.getNotificationFunctionality().add(notificationFunctionality);
		}
	}
	
	/*
	 * TODO check if is really necessary
	 * 
	 * private void handleOrphans() { // handle all the orphan commands by
	 * directly adding them to the corresponding device for (String device :
	 * this.candidateOrphans.keySet()) { Device dev = (Device)
	 * this.ids2JaxbObjects.get(device); for (Command cmd :
	 * this.candidateOrphans.get(device)) dev.getCommand().add(cmd); } }
	 */
	
	/**
	 * Inner Class for setting the correct prefixes and namespaces
	 * 
	 * @author Castellina Emiliano
	 * 
	 */
	public static class PreferredMapper extends NamespacePrefixMapper
	{
		@Override
		public String[] getPreDeclaredNamespaceUris()
		{
			return new String[] { WellKnownNamespace.XML_SCHEMA_INSTANCE };
		}
		
		@Override
		public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix)
		{
			String prefix = "xmldog";
			if (namespaceUri.equals(WellKnownNamespace.XML_SCHEMA_INSTANCE))
				prefix = "xsi";
			else if (namespaceUri.equals(WellKnownNamespace.XML_SCHEMA))
				prefix = "xs";
			else if (namespaceUri.equals(WellKnownNamespace.XML_MIME_URI))
				prefix = "xmime";
			else if (namespaceUri.equals("http://elite.polito.it/domotics/dog2/DogMessage"))
				prefix = "dm";
			
			return prefix;
			
		}
		
	}

	/**
	 * @return the jc
	 */
	public JAXBContext getJAXBContext()
	{
		return jc;
	}
	
}
