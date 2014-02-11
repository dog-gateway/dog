// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonValue;

import com.xively.client.utils.ObjectUtil;


/**
 * Unit model
 * 
 * @author s0pau
 * 
 */
public class Unit
{
	/**
	 * http://www.eeml.org/#units
	 */
	public enum IFCClassification
	{
		BASIC_SI("basicSI"),
		DERIVED_SI("derivedSI"),
		CONVERSION_BASED_UNITS("conservationBasedUnits"),
		DERIVED_UNITS("derivedUnits"),
		CONTEXT_DEPENDENT_UNITS("contextDependentUnits");

		private String jsonVal;

		private IFCClassification(String jsonVal)
		{
			this.jsonVal = jsonVal;
		}

		@JsonValue
		public String getJsonVal()
		{
			return jsonVal;
		}
	}

	/**
	 * This is the unit of the datastream, e.g. Celsius
	 */
	private String label;

	/**
	 * Symbolic representation of this unit - e.g. C is the symbol for Celcius
	 */
	private String symbol;

	/**
	 * This attribute is supported according to the API but is very much unused,
	 * consider deprecated.
	 */
	@Deprecated
	// @JsonProperty("type") TODO putting this on the member doesnt seem to have
	// effect for enum, investigate if feature needs to be switched on
	private IFCClassification unitType;

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public String getSymbol()
	{
		return symbol;
	}

	public void setSymbol(String symbol)
	{
		this.symbol = symbol;
	}

	@JsonProperty("type")
	public IFCClassification getUnitType()
	{
		return unitType;
	}

	public void setUnitType(IFCClassification unitType)
	{
		this.unitType = unitType;
	}

	@JsonIgnore
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (this == obj)
		{
			return true;
		}

		if (!(obj instanceof Unit))
		{
			return false;
		}

		Unit other = (Unit) obj;

		if (!ObjectUtil.nullCheckEquals(this.label, other.label))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.label, other.label))
		{
			return false;
		}

		if (!ObjectUtil.nullCheckEquals(this.unitType, other.unitType))
		{
			return false;
		}

		return true;
	}

	@JsonIgnore
	@Override
	public int hashCode()
	{
		int retval = 1;
		retval += label == null ? 0 : label.hashCode() * 37;
		retval += symbol == null ? 0 : symbol.hashCode() * 37;
		retval += unitType == null ? 0 : unitType.hashCode() * 37;
		return retval;
	}
}
