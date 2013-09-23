/*
 * 
 * Copyright (c) 2011-2013 Dario Bonino
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
package tuwien.auto.calimero.dptxlator;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import javax.measure.quantity.Power;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.measure.unit.UnitFormat;

import tuwien.auto.calimero.exception.KNXFormatException;
import tuwien.auto.calimero.exception.KNXIllegalArgumentException;
import tuwien.auto.calimero.log.LogLevel;

/**
 * Translator for KNX DPTs with main number 13, type 4-byte signed.
 * 
 * The KNX data type width is 4 bytes.
 * 
 * This type is a two byte floating format with a maximum usable range of
 * {@link Integer}.MIN_VALUE to +{@link Integer}.MAX_VALUE.
 * 
 * This translator will check and enforce DPT specific limits in all methods
 * working with java values (e.g. setValue(float)).
 * 
 * Data methods for KNX data (e.g. DPTXlator.setData(byte[]) accept all data
 * within the maximum usable range.
 * 
 * In value methods expecting a string type, the value is a float type
 * representation.
 * 
 * The default return value after creation is 0.0.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino </a>
 * 
 */
public class DPTXlator4ByteInteger extends DPTXlator
{
	public static Unit<Power> VAR = SI.WATT.alternate("var");
	public static Unit<Power> VA = SI.WATT.alternate("VA");
	static
	{
		// create the var and va units
		VAR.alternate("Var");		
		// add unit of measure aliases (to fix notation problems...)
		UnitFormat uf = UnitFormat.getInstance();
		uf.alias(SI.WATT.times(NonSI.HOUR), "Wh");
		uf.label(SI.KILO(SI.WATT.times(NonSI.HOUR)), "kWh");
		uf.alias(VAR.times(NonSI.HOUR), "Varh");
		uf.label(SI.KILO(VAR.times(NonSI.HOUR)), "kVarh");
		uf.alias(VA.times(NonSI.HOUR), "VAh");
		uf.label(SI.KILO(VA.times(NonSI.HOUR)), "kVAh");
	}
	
	public static final DPT DPT_COUNTER = new DPT("13.001", "Counter pulses", "" + Integer.MIN_VALUE, ""
			+ Integer.MAX_VALUE, Unit.ONE.toString());
	public static final DPT DPT_ACTIVE_ENERGY = new DPT("13.010", "Active Energy", "" + Integer.MIN_VALUE, ""
			+ Integer.MAX_VALUE, SI.WATT.times(NonSI.HOUR).toString());
	public static final DPT DPT_APPARENT_ENERGY = new DPT("13.011", "Apparent Energy", "" + Integer.MIN_VALUE, ""
			+ Integer.MAX_VALUE, VA.times(NonSI.HOUR).toString());
	public static final DPT DPT_REACTIVE_ENERGY = new DPT("13.012", "Reactive Energy", "" + Integer.MIN_VALUE, ""
			+ Integer.MAX_VALUE, VAR.times(NonSI.HOUR).toString());
	public static final DPT DPT_ACTIVE_ENERGY_K = new DPT("13.013", "Active Energy", "" + Integer.MIN_VALUE, ""
			+ Integer.MAX_VALUE, SI.KILO(SI.WATT.times(NonSI.HOUR)).toString());
	public static final DPT DPT_APPARENT_ENERGY_K = new DPT("13.014", "Apparent Energy", "" + Integer.MIN_VALUE, ""
			+ Integer.MAX_VALUE, SI.KILO(VA.times(NonSI.HOUR)).toString());
	public static final DPT DPT_REACTIVE_ENERGY_K = new DPT("13.015", "Reactive Energy", "" + Integer.MIN_VALUE, ""
			+ Integer.MAX_VALUE, SI.KILO(VAR.times(NonSI.HOUR)).toString());
	public static final DPT DPT_TIME_LAG = new DPT("13.100", "Time Lag", "" + Integer.MIN_VALUE, ""
			+ Integer.MAX_VALUE, SI.SECOND.toString());
	

	
	private static final Map<String, DPT> types;
	
	static
	{
		types = new HashMap<String, DPT>();
		types.put(DPT_COUNTER.getID(), DPT_COUNTER);
		types.put(DPT_ACTIVE_ENERGY.getID(), DPT_ACTIVE_ENERGY);
		types.put(DPT_APPARENT_ENERGY.getID(), DPT_APPARENT_ENERGY);
		types.put(DPT_REACTIVE_ENERGY.getID(), DPT_REACTIVE_ENERGY);
		types.put(DPT_ACTIVE_ENERGY_K.getID(), DPT_ACTIVE_ENERGY_K);
		types.put(DPT_APPARENT_ENERGY_K.getID(), DPT_APPARENT_ENERGY_K);
		types.put(DPT_REACTIVE_ENERGY_K.getID(), DPT_REACTIVE_ENERGY_K);
		types.put(DPT_TIME_LAG.getID(), DPT_TIME_LAG);
		
		
		
	}
	
	private final int min;
	private final int max;
	
	/**
	 * Creates a translator for the given datapoint type.
	 * <p>
	 * 
	 * @param dpt
	 *            the requested datapoint type
	 * @throws KNXFormatException
	 *             on not supported or not available DPT
	 */
	public DPTXlator4ByteInteger(DPT dpt) throws KNXFormatException
	{
		this(dpt.getID());
	}
	
	/**
	 * Creates a translator for <code>dptID</code>.
	 * <p>
	 * 
	 * @param dptID
	 *            available implemented datapoint type ID
	 * @throws KNXFormatException
	 *             on wrong formatted or not expected (available) DPT
	 */
	public DPTXlator4ByteInteger(String dptID) throws KNXFormatException
	{
		super(4);
		setTypeID(types, dptID);
		min = getLimit(dpt.getLowerValue());
		max = getLimit(dpt.getUpperValue());
		data = new short[4];
	}
	
	/**
	 * Sets the translation value from a float.
	 * <p>
	 * If succeeded, any other items in the translator are discarded.
	 * 
	 * @param value
	 *            the int value
	 * @throws KNXFormatException
	 *             if <code>value</code>doesn't fit into KNX data type
	 */
	public void setValue(int value) throws KNXFormatException
	{
		final short[] buf = new short[4];
		toDPT(value, buf, 0);
		data = buf;
	}
	
	/**
	 * Returns the first translation item formatted as float.
	 * <p>
	 * 
	 * @return value as int
	 */
	public final int getValueInt()
	{
		return fromDPT(0);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see tuwien.auto.calimero.dptxlator.DPTXlator#getAllValues()
	 */
	public String[] getAllValues()
	{
		final String[] buf = new String[data.length / 2];
		for (int i = 0; i < buf.length; ++i)
			buf[i] = makeString(i);
		return buf;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see tuwien.auto.calimero.dptxlator.DPTXlator#setData(byte[], int)
	 */
	public void setData(byte[] data, int offset)
	{
		if (offset < 0 || offset > data.length)
			throw new KNXIllegalArgumentException("illegal offset " + offset);
		final int size = (data.length - offset) & ~0x01;
		if (size == 0)
			throw new KNXIllegalArgumentException("data length " + size + " < KNX data type width "
					+ Math.max(1, getTypeSize()));
		this.data = new short[size];
		for (int i = 0; i < size; ++i)
			this.data[i] = ubyte(data[offset + i]);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see tuwien.auto.calimero.dptxlator.DPTXlator#getData(byte[], int)
	 */
	public byte[] getData(byte[] dst, int offset)
	{
		final int end = Math.min(data.length, dst.length - offset) & ~0x01;
		for (int i = 0; i < end; ++i)
			dst[offset + i] = (byte) data[i];
		return dst;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see tuwien.auto.calimero.dptxlator.DPTXlator#getSubTypes()
	 */
	public Map<String, DPT> getSubTypes()
	{
		return types;
	}
	
	/**
	 * @return the subtypes of the 2-byte float translator type
	 * @see DPTXlator#getSubTypesStatic()
	 */
	protected static Map<String, DPT> getSubTypesStatic()
	{
		return types;
	}
	
	private String makeString(int index)
	{
		return appendUnit(String.valueOf(fromDPT(index)));
	}
	
	private int fromDPT(int index)
	{
		// convert data into a buffer array
		byte[] bData = new byte[data.length];
		for (int j = 0; j < data.length; j++)
			bData[j] = (byte) data[j];
		
		// wrap the byte array with a byte buffer object
		ByteBuffer bBuffer = ByteBuffer.wrap(bData);
		
		// extract an IEEE754 float
		return bBuffer.getInt();
	}
	
	private void toDPT(int value, short[] dst, int index) throws KNXFormatException
	{
		if (value < min || value > max)
			throw logThrow(LogLevel.WARN, "translation error for " + value,
					"value out of range [" + dpt.getLowerValue() + ".." + dpt.getUpperValue() + "]",
					Integer.toString(value));
		// encoding
		byte[] bData = new byte[4];
		
		// wrap an empty byte buffer
		ByteBuffer bBuffer = ByteBuffer.wrap(bData);
		
		// encode a float
		bBuffer.putInt(value);
		
		// extract the byte buffer and convert to an array of short...
		for (int i = 0; i < bData.length; i++)
			dst[i] = ubyte(bData[i]);
	}
	
	protected void toDPT(String value, short[] dst, int index) throws KNXFormatException
	{
		try
		{
			toDPT(Integer.parseInt(removeUnit(value)), dst, index);
		}
		catch (final NumberFormatException e)
		{
			throw logThrow(LogLevel.WARN, "wrong value format " + value, null, value);
		}
	}
	
	private int getLimit(String limit) throws KNXFormatException
	{
		try
		{
			final int f = Integer.parseInt(limit);
			if (f >= Integer.MIN_VALUE && f <= Integer.MAX_VALUE)
				return f;
		}
		catch (final NumberFormatException e)
		{
		}
		throw logThrow(LogLevel.ERROR, "limit " + limit, "invalid DPT range", limit);
	}
}
