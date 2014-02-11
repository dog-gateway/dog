// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.utils;

import java.math.BigInteger;
import java.security.GeneralSecurityException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacUtils
{

	/**
	 * General hmac method - can pass in any crypto string recognised by
	 * {@link Mac.getInstance}.
	 * 
	 * @param crypto
	 * @param key
	 * @param value
	 * @return
	 */
	public static String hmacSha(String crypto, String key, String value)
	{
		try
		{
			byte[] keyBytes = hexStr2Bytes(key);

			Mac hmac;
			hmac = Mac.getInstance(crypto);
			SecretKeySpec macKey = new SecretKeySpec(keyBytes, "RAW");
			hmac.init(macKey);
			return bytesToHex(hmac.doFinal(value.getBytes()));
		}
		catch (GeneralSecurityException gse)
		{
			throw new RuntimeException(gse);
		}
	}

	/**
	 * Convenience method to generate the HMAC-SHA1 signatures we are using in
	 * this application.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String hmacSha1(String key, String value)
	{
		return hmacSha("HmacSHA1", key, value);
	}

	/**
	 * Convert hex string into byte array.
	 * 
	 * @param hex
	 * @return
	 */
	private static byte[] hexStr2Bytes(String hex)
	{
		// Adding one byte to get the right conversion
		// Values starting with "0" can be converted
		byte[] bArray = new BigInteger("10" + hex, 16).toByteArray();

		// Copy all the REAL bytes, not the "first"
		byte[] ret = new byte[bArray.length - 1];
		for (int i = 0; i < ret.length; i++)
			ret[i] = bArray[i + 1];
		return ret;
	}

	/**
	 * 
	 * Convert a byte array into a hex string
	 * 
	 * @param bytes
	 * @return
	 */
	private static String bytesToHex(byte[] bytes)
	{
		final char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] hexChars = new char[bytes.length * 2];
		int v;
		for (int j = 0; j < bytes.length; j++)
		{
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
}
