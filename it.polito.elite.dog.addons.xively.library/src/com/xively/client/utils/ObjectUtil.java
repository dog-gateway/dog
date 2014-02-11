// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.utils;

import java.util.Collection;

import com.xively.client.model.DomainObject;

/**
 * Utility class to supplement Object
 * 
 * @author s0pau
 * 
 */
public class ObjectUtil
{
	/**
	 * @param one
	 * @param two
	 * @return if both collection are deeply equal - i.e. when both collection
	 *         is the same size and all objects in the collecion is equal by the
	 *         equal() method.
	 */
	public static <T extends DomainObject> boolean deepEquals(Collection<T> one, Collection<T> two)
	{
		if (!CollectionUtil.deepEquals(one, two))
		{
			return false;
		} else if (one == null)
		{
			// if deep equal is true and one of them is null, they are both null
			return true;
		}

		int matchedCounts = 0;
		int i = 1;
		int quitEarlyThreshold = (int) Math.round(one.size() / 2 + 0.5);
		for (T obj1 : one)
		{
			if (i >= quitEarlyThreshold && matchedCounts < quitEarlyThreshold)
			{
				// optimisation - quit early over half of the collection objects
				// does not match
				return false;
			}

			for (@SuppressWarnings("unused") T obj2 : two)
			{
				if (obj1.memberEquals((DomainObject) two))
				{
					matchedCounts++;
				}
			}
			i++;
		}

		return matchedCounts == one.size();
	}

	/**
	 * @param one
	 * @param two
	 * @return true if both objects are null or both equals(); false otherwise
	 */
	public static <T extends Object> boolean nullCheckEquals(T one, T two)
	{
		if (one == null)
		{
			if (two == null)
			{
				return true;
			}
		} else if (one.equals(two))
		{
			return true;
		}

		return false;
	}
}
