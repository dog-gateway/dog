/**
 * 
 */
package it.polito.elite.dog.drivers.zwave.model;

import java.util.Calendar;

import javax.measure.Measure;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author bonino
 * 
 */
public class ClimateScheduleSwitchPoint
{
	// the time at which the temperature should change
	@JsonProperty
	private Calendar timeAt;

	// the new temperature value
	@JsonProperty
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
	public void setDesiredTemperature(Measure<?, ?> desiredTemperature)
	{
		this.desiredTemperature = desiredTemperature;
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

		keyBuffer.append(timeAt.get(Calendar.DAY_OF_WEEK));
		keyBuffer.append(timeAt.get(Calendar.HOUR));
		keyBuffer.append(timeAt.get(Calendar.MINUTE));
		keyBuffer.append(timeAt.get(Calendar.SECOND));

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

}
