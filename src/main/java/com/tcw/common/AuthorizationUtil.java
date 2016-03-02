package com.tcw.common;

import java.io.IOException;
import java.util.Properties;

public class AuthorizationUtil {
	private static AuthorizationUtil me;
	public static String  IMG_URL_150x113 = "";//图片地址
	public static String  IMG_URL_632x436 = "";//图片地址

	public static AuthorizationUtil getInstance() {
		if (me == null) {
			me = new AuthorizationUtil();
		}
		return me;
	}

	private Properties command = new Properties();
	private long lastLoaded;

	private long refreshInterval;

	public AuthorizationUtil() {
		init();
	}

	/**
	 * 读取属性值
	 * 
	 * @param key
	 * @return
	 */
	public synchronized String getString(String key) {
		if (System.currentTimeMillis() - lastLoaded >= refreshInterval) {
			init();
		}

		return command.getProperty(key);
	}

	/**
	 * 装载command属性信息
	 */
	private synchronized void init() {
		try {
			command.clear();
			command.load(getClass().getResource("/ershouche.properties").openStream());
			lastLoaded = System.currentTimeMillis();
			IMG_URL_150x113 = command.getProperty("IMG_URL_150x113");
			IMG_URL_632x436 = command.getProperty("IMG_URL_632x436");
			if (refreshInterval == 0L) {
				/** 默认十分钟 */
				refreshInterval = 10 * 60 * 1000;
			}
		} catch (IOException ex) {
		}
	}

	/**
	 * 读取key值
	 * 
	 * @param key
	 * @return
	 */
	private String getKeyValue(String key) {
		return command.getProperty(key);
	}
}
