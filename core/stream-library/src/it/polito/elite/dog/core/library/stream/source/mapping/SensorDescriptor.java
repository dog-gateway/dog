/*
(c) Dario Bonino, e-Lite research group, http://elite.polito.it
 
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and limitations under the License. 
 
 */
package it.polito.elite.dog.core.library.stream.source.mapping;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author bonino
 * 
 */
public class SensorDescriptor
{
	// the URI of the sensor described by this object
	private String sensorURI;
	
	// the query functionality from which data is extracted
	private String sensorQFunctionality;
	
	// the parameters given to further specify the functionality used to gather
	// data from the field
	private String sensorQFParams;
	
	// the uid corresponding to the "virtual" sensor identified by the above
	// information
	private String uid;
	
	// the inner UUId generated on the basis of the URI, functionality and
	// params
	private String innerUUId;
	
	/**
	 * @param sensorURI
	 * @param sensorQFunctionality
	 * @param sensorQFParams
	 * @param uid
	 */
	public SensorDescriptor(String sensorURI, String sensorQFunctionality, String sensorQFParams, String uid)
	{
		this.sensorURI = sensorURI;
		this.sensorQFunctionality = sensorQFunctionality;
		this.sensorQFParams = sensorQFParams;
		this.uid = uid;
		this.innerUUId = SensorDescriptor.hash(sensorURI + sensorQFunctionality + sensorQFParams);
	}
	
	/**
	 * 
	 */
	public SensorDescriptor()
	{
		// empty constructor
	}
	
	/**
	 * @return the sensorURI
	 */
	public java.lang.String getSensorURI()
	{
		return sensorURI;
	}
	
	/**
	 * @param sensorURI
	 *            the sensorURI to set
	 */
	public void setSensorURI(java.lang.String sensorURI)
	{
		this.sensorURI = sensorURI;
	}
	
	/**
	 * @return the sensorQFunctionality
	 */
	public java.lang.String getSensorQFunctionality()
	{
		return sensorQFunctionality;
	}
	
	/**
	 * @param sensorQFunctionality
	 *            the sensorQFunctionality to set
	 */
	public void setSensorQFunctionality(java.lang.String sensorQFunctionality)
	{
		this.sensorQFunctionality = sensorQFunctionality;
	}
	
	/**
	 * @return the sensorQFParams
	 */
	public java.lang.String getSensorQFParams()
	{
		return sensorQFParams;
	}
	
	/**
	 * @param sensorQFParams
	 *            the sensorQFParams to set
	 */
	public void setSensorQFParams(java.lang.String sensorQFParams)
	{
		this.sensorQFParams = sensorQFParams;
	}
	
	/**
	 * @return the uid
	 */
	public java.lang.String getUid()
	{
		return uid;
	}
	
	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(java.lang.String uid)
	{
		this.uid = uid;
	}
	
	/**
	 * Returns the inner unique identifier associated to this sensor descriptor
	 * 
	 * @return
	 */
	public String getIUUID()
	{
		return this.innerUUId;
	}
	
	/**
	 * Generates the inner UUID of a block having the given sensorURI,
	 * functionality and functionality params
	 * 
	 * @param sensorURI
	 *            the uri of the represented sensor
	 * @param sensorFunctionality
	 *            the query functionality being monitored ("polled")
	 * @param functionalityParams
	 *            the paramters used for monitoring the functionality
	 * @return the corresponding iUUID, generated using the same algorithm used
	 *         for generating the iUUID of real SensorDescriptors
	 */
	public static String generateInnerUUID(String sensorURI, String sensorFunctionality, String functionalityParams)
	{
		return SensorDescriptor.hash(sensorURI+sensorFunctionality+functionalityParams);
	}
	
	private static String hash(String text)
	{
		byte[] hash = null;
		try
		{
			hash = MessageDigest.getInstance("MD5").digest(text.getBytes());
		}
		catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigInteger bi = new BigInteger(1, hash);
		return String.format("%0" + (hash.length << 1) + "x", bi);
	}
	
}
