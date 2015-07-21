/*
 * Dog - EnOcean Gateway Driver
 * 
 * 
 * Copyright 2015 Dario Bonino 
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
package it.polito.elite.dog.core.library.model.technology;

public class ExplicitTeachInData
{
	// the EEP associated to the device to teach in
	private String deviceEEP;

	// the low level address of the device to teach in, in hexadecimal notation
	private String deviceHexAddress;

	/**
	 * Empty constructor implementing the bean instantiation pattern
	 */
	public ExplicitTeachInData()
	{
		// Intentionally left empty
	}

	/**
	 * Creates a new ExplicitTeachInData object holding the given device EEP and
	 * hexadecimal address.
	 * 
	 * @param deviceEEP
	 *            The EEP associated with the device to teach in
	 * @param deviceHexAddress
	 *            the hexadecimal address of the device to teach in
	 */
	public ExplicitTeachInData(String deviceEEP, String deviceHexAddress)
	{
		super();
		this.deviceEEP = deviceEEP;
		this.deviceHexAddress = deviceHexAddress;
	}

	/**
	 * Gets the EEP of the device to teach in (in the form XX-XX-XX)
	 * 
	 * @return the deviceEEP
	 */
	public String getDeviceEEP()
	{
		return deviceEEP;
	}

	/**
	 * Sets the EEP of the device to teach in (in the form XX-XX-XX)
	 * 
	 * @param deviceEEP
	 *            the deviceEEP to set
	 */
	public void setDeviceEEP(String deviceEEP)
	{
		this.deviceEEP = deviceEEP;
	}

	/**
	 * Gets the hexadecimal address of the device to teach in, in a string
	 * notation.
	 * 
	 * @return the deviceHexAddress
	 */
	public String getDeviceHexAddress()
	{
		return deviceHexAddress;
	}

	/**
	 * Sets the hexadecimal address of the device to teach in, in a string
	 * notation.
	 * 
	 * @param deviceHexAddress
	 *            the deviceHexAddress to set
	 */
	public void setDeviceHexAddress(String deviceHexAddress)
	{
		this.deviceHexAddress = deviceHexAddress;
	}
}
