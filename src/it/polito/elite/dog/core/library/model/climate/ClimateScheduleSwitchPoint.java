/**
 * 
 */
package it.polito.elite.dog.core.library.model.climate;

import java.io.Serializable;
import java.util.Calendar;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;

/**
 * @author bonino
 * 
 */
public class ClimateScheduleSwitchPoint implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// the time at which the temperature should change
	private Calendar timeAt;

	// the new temperature value
	private Measure<?, ?> desiredTemperature;

	/**
	 * Build a new switch point instance modeling a desired temperature value at
	 * a given time of the day
	 * 
	 * @param timeAt
	 *            time at which the temperature should change, only day-relative
	 *            values are considered with a second resolution.
	 * @param desiredTemperature
	 *            the desired temperature
	 */
	public ClimateScheduleSwitchPoint(Calendar timeAt,
			Measure<?, ?> desiredTemperature)
	{
		// store the time instant
		this.timeAt = timeAt;

		// store the temperature
		this.desiredTemperature = desiredTemperature;
	}

	public ClimateScheduleSwitchPoint()
	{
		// empty constructor for Jackson instantiation
	}

	/**
	 * @return the timeAt
	 */
	public Calendar getTimeAt()
	{
		return timeAt;
	}

	/**
	 * @param timeAt
	 *            the timeAt to set
	 */
	public void setTimeAt(Calendar timeAt)
	{
		this.timeAt = timeAt;
	}

	/**
	 * @return the desiredTemperature
	 */
	public Measure<?, ?> getDesiredTemperature()
	{
		return desiredTemperature;
	}

	/**
	 * @param desiredTemperature
	 *            the desiredTemperature to set
	 */
	public void setDesiredTemperatureAsMeasure(Measure<?, ?> desiredTemperature)
	{
		this.desiredTemperature = desiredTemperature;
	}

	/**
	 * Sets the desired temperature given the String representation of it. Used
	 * by jackson mapping.
	 * 
	 * @param desiredTemperatureAsString
	 */
	public void setDesiredTemperature(String desiredTemperatureAsString)
	{
		this.desiredTemperature = DecimalMeasure
				.valueOf(desiredTemperatureAsString);
	}

	/**
	 * Get a unique key identifying the time associated to a {@link Calendar}
	 * instance. The key value can be used to access
	 * {@link ClimateScheduleSwitchPoint} stored using a time-based index
	 * obtained through
	 * 
	 * <pre>
	 * getTimeAtKey(ClimateScheduleSwitchPoint point}
	 * </pre>
	 * 
	 * @return
	 */
	public static String getTimeAtKey(Calendar timeAt)
	{
		StringBuffer keyBuffer = new StringBuffer();

		keyBuffer.append(String.format("%02d", timeAt.get(Calendar.HOUR_OF_DAY)));
		keyBuffer.append(String.format("%02d", timeAt.get(Calendar.MINUTE)));
		keyBuffer.append(String.format("%02d", timeAt.get(Calendar.SECOND)));

		return keyBuffer.toString();
	}

	/**
	 * Get a unique key identifying the time associated to a
	 * {@link ClimateScheduleSwitchPoint} value
	 * 
	 * @return
	 */
	public static String getTimeAtKey(ClimateScheduleSwitchPoint point)
	{
		return ClimateScheduleSwitchPoint.getTimeAtKey(point.timeAt);
	}

	@Override
	public String toString()
	{
		return 	this.desiredTemperature + "("
				+ this.timeAt.get(Calendar.HOUR_OF_DAY) + ":"
				+ this.timeAt.get(Calendar.MINUTE) + ":"
				+ this.timeAt.get(Calendar.SECOND) + ")";
	}

}
