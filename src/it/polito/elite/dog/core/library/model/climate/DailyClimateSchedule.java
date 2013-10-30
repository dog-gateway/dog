/**
 * 
 */
package it.polito.elite.dog.core.library.model.climate;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Hashtable;

/**
 * @author bonino
 * 
 */
public class DailyClimateSchedule implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// the set of switch points associated to a given week day
	private Hashtable<String, ClimateScheduleSwitchPoint> switchPoints;

	// the week day
	private int weekDay;

	public DailyClimateSchedule(int weekDay)
	{
		// store the week day
		this.weekDay = weekDay;

		// initialize the switch points table
		this.switchPoints = new Hashtable<String, ClimateScheduleSwitchPoint>();
	}

	public DailyClimateSchedule()
	{
		// initialize the switch points table
		this.switchPoints = new Hashtable<String, ClimateScheduleSwitchPoint>();
	}

	/**
	 * Add a new switch point, if a point already existed at the same time,
	 * overwrites the old value
	 * 
	 * @param point
	 *            the {@link ClimateScheduleSwitchPoint} instance to add.
	 */
	public void addSwitchPoint(ClimateScheduleSwitchPoint point)
	{
		// add the new point, if a point already exist for the same time,
		// overwrites the old value
		this.switchPoints.put(ClimateScheduleSwitchPoint.getTimeAtKey(point),
				point);
	}

	/**
	 * Add a set/array of switch point to this daily schedule
	 * 
	 * @param points
	 */
	public void addAllSwitchPoints(Iterable<ClimateScheduleSwitchPoint> points)
	{
		for (ClimateScheduleSwitchPoint point : points)
			this.addSwitchPoint(point);
	}
	
	/**
	 * Add a set/array of switch point to this daily schedule
	 * 
	 * @param points
	 */
	public void replaceAllSwitchPoints(ClimateScheduleSwitchPoint[] points)
	{
		//remove current switch Points
		this.switchPoints.clear();
		
		//add the new switch points
		for (ClimateScheduleSwitchPoint point : points)
			this.addSwitchPoint(point);
	}

	/**
	 * Sets the switch points for this climate schedule object. Used by jackson
	 * mapping.
	 * 
	 * @param points
	 */
	public void setSwitchPoints(ClimateScheduleSwitchPoint[] points)
	{
		for (int i = 0; i < points.length; i++)
		{
			this.addSwitchPoint(points[i]);
		}
	}

	public ClimateScheduleSwitchPoint removeSwitchPoint(Calendar timeAt)
	{
		return this.switchPoints.remove(ClimateScheduleSwitchPoint
				.getTimeAtKey(timeAt));
	}

	/**
	 * Remove a set/array of switch point to this daily schedule
	 * 
	 * @param points
	 */
	public void removeAllSwitchPoints(
			Iterable<ClimateScheduleSwitchPoint> points)
	{
		for (ClimateScheduleSwitchPoint point : points)
			this.switchPoints.remove(ClimateScheduleSwitchPoint
					.getTimeAtKey(point));
	}

	/**
	 * Offers quick access to switch points given a time instant represented by
	 * a Calendar instance.
	 * 
	 * @param timeAt
	 *            the instant for which a switch must be retrieved if existing.
	 * @return the {@link ClimateScheduleSwitchPoint} associated to the given
	 *         time instant or null if no switch points are available for the
	 *         given instant.
	 */
	public ClimateScheduleSwitchPoint getSwitchPoint(Calendar timeAt)
	{
		return this.switchPoints.get(ClimateScheduleSwitchPoint
				.getTimeAtKey(timeAt));
	}
	
	/**
	 * Gets all switch points associated to this schedule as an Array of {@link ClimateScheduleSwitchPoint} instances
	 * @return an Array of {@link ClimateScheduleSwitchPoint} instances
	 */
	public ClimateScheduleSwitchPoint[] getSwitchPoints()
	{
		//prepare the array
		ClimateScheduleSwitchPoint[] allSwitchPoints = new ClimateScheduleSwitchPoint[this.switchPoints.size()];
		
		//convert the currently stored values to an array
		allSwitchPoints = this.switchPoints.values().toArray(allSwitchPoints);
		
		//return the array
		return allSwitchPoints;
	}

	/**
	 * Returns the weekDay for which this instance stores the temperature switch
	 * points
	 * 
	 * @return the weekDay
	 */
	public int getWeekDay()
	{
		return weekDay;
	}

	@Override
	public String toString()
	{
		return "weekDay=" + weekDay + ","+switchPoints.values()+"";
	}

	
}
