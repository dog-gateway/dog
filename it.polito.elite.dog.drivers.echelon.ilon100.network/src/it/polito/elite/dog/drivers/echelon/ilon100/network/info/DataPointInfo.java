/*
 * Dog - Network Driver
 * 
 * Copyright (c) 2012-2014 Dario Bonino
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
package it.polito.elite.dog.drivers.echelon.ilon100.network.info;

import javax.measure.DecimalMeasure;
import javax.measure.unit.Unit;

/**
 * A class for representing information about datapoints exposed by an iLon100
 * server.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino</a>
 * @see <a href="http://elite.polito.it">http://elite.polito.it</a>
 * 
 */
public class DataPointInfo
{
	// the full iLon id associated with this data point
	private String iLonId;
	
	// the iLonAlias associated with this data point
	private String iLonAlias;
	
	// the short id generated as the last string occurring after the last
	// '/'character in the full id
	private String idShort;
	
	// the unit of measure of the data associated to this data point
	private String unitOfMeasure;
	
	// the current value of the data point
	private double value = Double.NEGATIVE_INFINITY;
	
	// the url of the iLon server to which this datapoint is attached
	private String iLonServerEndpoint;
	
	/**
	 * The class constructor, given a data point id,alias, unitOfMeasure and
	 * endpointAddress builds a corresponding DataPointInfo instance.
	 * 
	 * @param iLonId
	 *            The data point id on the iLon100 server
	 * @param iLonAlias
	 *            The human readable alias associated to the data point
	 * @param unitOfMeasure
	 *            The unit of measure of quantities measured through the given
	 *            datapoint, if any.
	 * @param iLonServerEndpoint
	 *            The address of the endpoint at which the iLon100 server is
	 *            listening
	 */
	public DataPointInfo(String iLonId, String iLonAlias, String unitOfMeasure, String iLonServerEndpoint)
	{
		// store available information
		this.iLonId = iLonId;
		this.iLonAlias = iLonAlias;
		this.unitOfMeasure = unitOfMeasure;
		this.iLonServerEndpoint = iLonServerEndpoint;
		
		// generate the endpoint short id
		this.idShort = this.iLonId.substring(this.iLonId.lastIndexOf('/') + 1, this.iLonId.length());
	}
	
	/**
	 * Returns the iLon id associated to this datapoint
	 * 
	 * @return the iLonId
	 */
	public String getiLonId()
	{
		return iLonId;
	}
	
	/**
	 * Sets the ilon id associated to this data point
	 * 
	 * @param iLonId
	 *            the iLonId to set
	 */
	public void setiLonId(String iLonId)
	{
		this.iLonId = iLonId;
	}
	
	/**
	 * Returns the iLon alias associated to this datapoint
	 * 
	 * @return the iLonAlias
	 */
	public String getiLonAlias()
	{
		return iLonAlias;
	}
	
	/**
	 * Sets the iLonAlias associated to this datapoint
	 * 
	 * @param iLonAlias
	 *            the iLonAlias to set
	 */
	public void setiLonAlias(String iLonAlias)
	{
		this.iLonAlias = iLonAlias;
	}
	
	/**
	 * Returns the short identifier associated to this data point
	 * 
	 * @return the idShort
	 */
	public String getIdShort()
	{
		return idShort;
	}
	
	/**
	 * Sets the short identifier associated to this datapoint
	 * 
	 * @param idShort
	 *            the idShort to set
	 */
	public void setIdShort(String idShort)
	{
		this.idShort = idShort;
	}
	
	/**
	 * Returns the unit of measure for values read/kept by this data point
	 * 
	 * @return the unitOfMeasure
	 */
	public String getUnitOfMeasure()
	{
		return unitOfMeasure;
	}
	
	/**
	 * Sets the unit of measures for the values associated to this datapoint
	 * 
	 * @param unitOfMeasure
	 *            the unitOfMeasure to set
	 */
	public void setUnitOfMeasure(String unitOfMeasure)
	{
		this.unitOfMeasure = unitOfMeasure;
	}
	
	/**
	 * Returns the value associated to the iLon data point identified by this
	 * data point info instance.
	 * 
	 * @return the value
	 */
	public double getValue()
	{
		return value;
	}
	
	/**
	 * Returns the current value assumed by the iLon datapoint represented by
	 * this data point info object, as a real-valued measure
	 * 
	 * @return
	 */
	public DecimalMeasure<?> getValueWithUnitOfMeasure()
	{
		if (this.unitOfMeasure != null)
		{
			return DecimalMeasure.valueOf(this.value + " " + this.unitOfMeasure);
		}
		else
		{
			return DecimalMeasure.valueOf(this.value + " " + Unit.ONE);
		}
	}
	
	/**
	 * Sets the value associated to the data point represented by this datapoint
	 * info instance
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(double value)
	{
		this.value = value;
	}
	
	/**
	 * Returns the endpoint address (URL as String) of the iLon 100 Server on
	 * which the data point represented by this object is configured.
	 * 
	 * @return the iLonServerEndpoint
	 */
	public String getiLonServerEndpoint()
	{
		return iLonServerEndpoint;
	}
	
	/**
	 * Sets the endpoint address (URL as String) of the iLon 100 Server on
	 * which the data point represented by this object is configured.
	 * 
	 * @param iLonServerEndpoint
	 *            the iLonServerEndpoint to set
	 */
	public void setiLonServerEndpoint(String iLonServerEndpoint)
	{
		this.iLonServerEndpoint = iLonServerEndpoint;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof DataPointInfo)
		{
			DataPointInfo objInfo = (DataPointInfo)obj;
			
			return (objInfo.iLonId.equals(this.iLonId));
		}
		else
			return super.equals(obj);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return this.iLonId.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DataPoint [iLonId=" + iLonId + ", iLonAlias=" + iLonAlias + ", idShort=" + idShort + ", unitOfMeasure="
		+ unitOfMeasure + ", value=" + value + "]";
	}
	
	
	
	
}
