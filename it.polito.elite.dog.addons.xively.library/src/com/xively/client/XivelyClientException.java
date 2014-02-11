// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client;

/**
 * Parent exception from which all exception classes in this library extends.
 * 
 * @author s0pau
 * 
 */
public class XivelyClientException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XivelyClientException(String msg, Throwable e)
	{
		super(msg, e);
	}

	public XivelyClientException(String msg)
	{
		super(msg);
	}

	@Override
	public String getLocalizedMessage()
	{
		return String.format("%s: exception: %s", super.getMessage(), super.getCause() == null ? "" : super.getCause()
				.getLocalizedMessage());
	}

	@Override
	public synchronized Throwable getCause()
	{
		return super.getCause();
	}
}
