/*
 * Dog - Core
 * 
 * Copyright (c) 2013 Dario Bonino, Luigi De Russis and Luca Semprini
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import it.polito.elite.dog.core.library.model.state.State;
import it.polito.elite.dog.core.library.model.statevalue.StateValue;

/**
 * This class stores information about the states of a device.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @author Luca Semprini
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 */
public class DeviceStatus implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Device URI
	protected String deviceURI;
	/**
	 * list of the devices states. Key -> state name, e.g. OnOffState ; value -> State object, e.g. OnState
	 */
	protected Map<String, State> states;
	
	/**
	 * Class constructor
	 * 
	 * @param deviceURI
	 */
	public DeviceStatus(String deviceURI)
	{
		this.deviceURI = deviceURI;
		this.states = new HashMap<String, State>();
	}
	
	/**
	 * Class constructor
	 * 
	 * @param deviceURI
	 * @param states
	 */
	public DeviceStatus(String deviceURI, Map<String, State> states)
	{
		this(deviceURI);
		this.states.putAll(states);
	}
	
	/**
	 * @return the deviceURI
	 */
	public String getDeviceURI()
	{
		return this.deviceURI;
	}
	
	/**
	 * @param deviceURI
	 *            the deviceURI to set
	 */
	public void setDeviceURI(String deviceURI)
	{
		this.deviceURI = deviceURI;
	}
	
	/**
	 * @return the states
	 */
	public Map<String, State> getStates()
	{
		return this.states;
	}
	
	/**
	 * It does not modify all the state, but only the ones passed as parameters.
	 * 
	 * @param states
	 *            the states to set
	 */
	public void setStates(Map<String, State> states)
	{
		this.states.putAll(states);
	}
	
	/**
	 * Provide a specific State of the device
	 * 
	 * @param stateName
	 *            name of the State
	 * @return specific State
	 */
	public State getState(String stateName)
	{
		if (this.states != null)
		{
			return this.states.get(stateName);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Set the value of a specific state
	 * 
	 * @param stateName
	 *            name of the state to change
	 * @param state
	 *            new State
	 * @return previous State if exists, otherwise null
	 */
	public State setState(String stateName, State state)
	{
		State previousState = null;
		if (this.states != null)
		{
			previousState = this.states.put(stateName, state);
		}
		
		return previousState;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		DeviceStatus deviceState = new DeviceStatus(this.deviceURI, this.states);
		return deviceState;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("DEVICE URI " + this.deviceURI + " \n");
		for (State state : this.states.values())
		{
			sb.append(state.getStateName() + " ");
			for (StateValue value : state.getCurrentStateValue())
			{
				sb.append(value.getName() + " " + value.getValue().toString());
			}
			sb.append("\n");
			// debug
			// sb.append(state.getStateName() + " " + state.getCurrentState().toString() + "\n");
			
		}
		return sb.toString();
	}

	/**
	 * Serialize a DeviceStatus into a String using a byte encoding
	 * @param object
	 * 			a DeviceStatus ready for serialization
	 * @return a byte encoded String of a DeviceStatus
	 * @throws IOException 
	 */
	public static String serializeToString(DeviceStatus object) throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream encoder = new ObjectOutputStream(baos);
		//serialize the DeviceStatus
		encoder.writeObject(object);
		//flush and close everything
		encoder.flush();
		baos.flush();
		encoder.close(); 
		baos.close();
		return new String(Base64.encodeBase64(baos.toByteArray()));
	}
	
	/**
	 * Deserialize a DeviceStatus from a byte encoded String 
	 * @param serialization
	 * 			a byte encoded String of a DeviceStatus
	 * @return a deserialized DeviceStatus
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static DeviceStatus deserializeFromString(String serialization) throws IOException, ClassNotFoundException{
		byte[] data = Base64.decodeBase64(serialization);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ObjectInputStream decoder = new ObjectInputStream(bais);
		//deserialize the DeviceStatus
		DeviceStatus o = (DeviceStatus) decoder.readObject();
		decoder.close(); 
		return o;
	}

	
}
