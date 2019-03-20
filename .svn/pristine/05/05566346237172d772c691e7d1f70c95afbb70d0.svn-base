package com.tenly.common.systools;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class StringTools {
	
	public static Random random = new Random();
	
	private static final byte HEX[] = {
		48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 
		97, 98, 99, 100, 101, 102
	};
	
	public static String md5(String string)
	{
		if (string == null)
			throw new NullPointerException("Argument is null.");
		MessageDigest digest = null;
		try
		{
			digest = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e) { }
		byte md5[] = null;
		try
		{
			md5 = digest.digest(string.getBytes("UTF-8"));
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		byte str[] = new byte[32];
		int k = 0;
		for (int i = 0; i < 16; i++)
		{
			byte byte0 = md5[i];
			str[k++] = HEX[byte0 >>> 4 & 0xf];
			str[k++] = HEX[byte0 & 0xf];
		}

		return new String(str);
	}
	
	public static boolean isEmpty(String string)
	{
		return string == null || string.trim().equals("") || string.trim().equals("null");
	}
	
	public static final String randomHZ(int len)
	{
		if (len <= 0)
			return null;
		char cs[] = new char[len];
		for (int i = 0; i < len; i++)
			cs[i] = (char)(int)(random.nextDouble() * 18938D + 21834D);

		return new String(cs);
	}
	
}
