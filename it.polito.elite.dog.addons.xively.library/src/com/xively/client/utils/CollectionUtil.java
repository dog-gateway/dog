// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.utils;

import java.util.Collection;

/**
 * Utility class to supplement Collections
 * 
 * @author s0pau
 * 
 */
public class CollectionUtil
{
	public static <T extends Object> boolean deepEquals(Collection<T> one, Collection<T> two)
	{
		if (ObjectUtil.nullCheckEquals(one, two))
		{
			return true;
		}

		if (one == null || two == null)
		{
			return false;
		}

		if (one.size() != two.size())
		{
			return false;
		}

		if (one.containsAll(two))
		{
			return true;
		}

		return false;
	}
}
