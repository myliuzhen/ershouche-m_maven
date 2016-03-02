package com.tcw.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class DicCache {  
	private static Logger log = Logger.getLogger(DicCache.class);
	
	/**报名城市字典数据Map，存储方式 ID:城市简写编码，如 10:bj */
	public static Map<String,String> regCityCodeMap = new HashMap<String,String>();
	/**报名城市字典数据Map，存储方式 ID:城市简写编码，如 10:北京 */
	public static Map<String,String> regCityNameMap = new HashMap<String,String>();
	
	/**
	 * 报名城市map
	 * 存储方式 ID:城市简写编码，如 10:bj
	 * @param registerCityList
	 */
	public static void initCityCodeMap(List<Map<String,String>> registerCityList){
		log.info("Start Init regCityCodeMap Data...");
		regCityCodeMap.clear();
		for(Map<String,String> desc: registerCityList){
			regCityCodeMap.put(desc.get("TEXT"),desc.get("VALUE"));
		}
		log.info("Init regCityCodeMap Data End");
	}
	/**
	 * 报名城市map
	 * 存储方式 ID:城市简写编码，如 10:北京
	 * @param registerCityList
	 */
	public static void initCityNameMap(List<Map<String,String>> registerCityList){
		log.info("Start Init regCityNameMap Data...");
		regCityNameMap.clear();
		for(Map<String,String> desc: registerCityList){
			regCityNameMap.put(desc.get("VALUE"),desc.get("NAME"));
		}
		log.info("Init regCityNameMap Data End");
	}
	/**
	 * 通过城市编码获取城市ID
	 * @param cityCode 城市简写编码
	 * @return
	 */
	public static String getCityId(String cityCode){
		return regCityCodeMap.get(cityCode);
	}
	/**
	 * 获取城市名称
	 * @param cityId
	 * @return
	 */
	public static String getCityName(String cityId){
		return regCityNameMap.get(cityId);
	}
}