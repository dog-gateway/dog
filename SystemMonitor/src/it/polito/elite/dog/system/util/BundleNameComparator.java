/**
 * 
 */
package it.polito.elite.dog.system.util;

import java.util.Comparator;

import org.osgi.framework.Bundle;

/**
 * @author bonino
 *
 */
public class BundleNameComparator implements Comparator<Bundle>
{
	
	/**
	 * 
	 */
	public BundleNameComparator()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Bundle b1, Bundle b2)
	{
		return (b1.getSymbolicName().compareTo(b2.getSymbolicName()));
	}
	
}
