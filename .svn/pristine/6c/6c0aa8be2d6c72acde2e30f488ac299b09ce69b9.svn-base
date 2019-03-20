package com.tenly.common.systools;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class PropetiesInfo {

	public final static String CONFIG = "message.properties";

	public static Object getKeyInfo(String filePath, String keyName) throws UnsupportedEncodingException {
		InputStream proIn = PropetiesInfo.class.getClassLoader()
				.getResourceAsStream(filePath);
		Properties pro = new Properties();
		try {
			pro.load(proIn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String back = pro.getProperty(keyName);
		String resultName = new String(back.getBytes("ISO-8859-1"), "utf-8");
		return resultName;
	}

}
