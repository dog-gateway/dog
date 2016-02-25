/*
 * Dog - EnOcean Network Driver
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
package org.doggateway.drivers.enocean.network.info;

/**
 * Describes the information needed to uniquely identify a device
 * @author <a href="mailto:dario.bonino@gmail.com">Dario Bonino</a>
 *
 */
public class EnOceanDeviceInfo
{
	private static final int UNDEFINED_UID = 0;

	// the device high-level UID
	// (mandatory unless for explicit teach-in)
	private int uid;

	// the device low-level address as string
	// (optional unless for explicit teach-in)
	// format: 0xhhhhhhhh (4bytes)
	private String address;

	// the device eep as string
	// (optional unless for explicit teach-in)
	// format: hh-hh-hh (rorg-func-type, e.g., A5-02-05)
	private String eep;

	/**
	 * 
	 */
	public EnOceanDeviceInfo(int uid)
	{
		this.init(uid, null, null);
	}

	public EnOceanDeviceInfo(String address, String eep)
	{
		this.init(EnOceanDeviceInfo.UNDEFINED_UID, address, eep);
	}

	public EnOceanDeviceInfo(int uid, String address, String eep)
	{
		this.init(uid, address, eep);
	}

	/**
	 * Initializes the instance variables with the given values
	 * 
	 * @param uid
	 *            The device high-level UID, not valid if sero or negative
	 * @param address
	 *            The device address as an hexadecimal string, might be null
	 * @param eep
	 *            The device eep, might be null
	 */
	private void init(int uid, String address, String eep)
	{
		// store the uid
		if (uid > 0)
			this.uid = uid;
		else
			this.uid = EnOceanDeviceInfo.UNDEFINED_UID;

		// store the address
		this.address = address;

		// store the eep
		this.eep = eep;
	}

	/**
	 * Returns the high-level unique identifier of the device represented by
	 * this device info object
	 * 
	 * @return the uid
	 */
	public int getUid()
	{
		return uid;
	}

	/**
	 * Sets the high-level unique identifier of the device represented by this
	 * device info object.
	 * 
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(int uid)
	{
		this.uid = uid;
	}

	/**
	 * Returns the low-level address of the device represented by this device
	 * info object. The address format is an hexadecimal string in the
	 * =xhhhhhhhh form (4bytes).
	 * 
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * Sets the low-level address of the device represented by this device info
	 * object. The address format is an hexadecimal String in the =xhhhhhhhh
	 * form (4bytes).
	 * 
	 * @param address
	 *            . the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * Returns the EnOcean Equipment Profile identifier of the device
	 * represented by this device info object. The EEP identifier is provided in
	 * the hh-hh-hh (rorg-func-type, e.g., A5-02-05) format.
	 * 
	 * @return the eep
	 */
	public String getEep()
	{
		return eep;
	}

	/**
	 * Sets the EnOcean Equipment Profile identifier of the device represented
	 * by this device info object. The EEP identifier must be provided as a
	 * String in the hh-hh-hh (rorg-func-type, e.g., A5-02-05) format.
	 * 
	 * @param eep
	 *            the eep to set
	 */
	public void setEep(String eep)
	{
		this.eep = eep;
	}

}
