package com.tcw.common;

import java.io.IOException;
import java.util.Arrays;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 对象转换成JSON
 * @author MrH
 *
 */
public class JSONUtil {
	
	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 将对象转换成JSON字符串
	 * 
	 * @param object
	 * @param includesProperties
	 *            需要转换的属性
	 * @param excludesProperties
	 *            不需要转换的属性
	 */
	public static String toJsonByFilter(Object object, String[] includesProperties, String[] excludesProperties) {
			FastjsonFilter filter = new FastjsonFilter();// excludes优先于includes
			if (excludesProperties != null && excludesProperties.length > 0) {
				filter.getExcludes().addAll(Arrays.<String> asList(excludesProperties));
			}
			if (includesProperties != null && includesProperties.length > 0) {
				filter.getIncludes().addAll(Arrays.<String> asList(includesProperties));
			}
			return JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
	}

	/**
	 * 将对象转换成JSON字符串
	 * 
	 * @param object
	 * @throws IOException
	 */
	public static String toJson(Object object) {
		return toJsonByFilter(object, null, null);
	}

	/**
	 * 将对象转换成JSON字符串
	 * 
	 * @param object
	 * @param includesProperties
	 *            需要转换的属性
	 */
	public static String toJsonByIncludesProperties(Object object, String[] includesProperties) {
		return toJsonByFilter(object, includesProperties, null);
	}

	/**
	 * 将对象转换成JSON字符串
	 * 
	 * @param object
	 * @param excludesProperties
	 *            不需要转换的属性
	 */
	public static String toJsonByExcludesProperties(Object object, String[] excludesProperties) {
		return toJsonByFilter(object, null, excludesProperties);
	}
	
	public static <T> String entity2Json(T t){
		String json = null;
		try {
			json = objectMapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public static <T> T json2Entity(String json,Class<T> clazz) {
		T t = null;
		try {
			t = objectMapper.readValue(json,clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}
	@SuppressWarnings("unchecked")
	public static <T> T json2Entity(String json,String reqName,boolean isReq) {
		T t = null;
		try {
			Class<T> clazz = (Class<T>) Class.forName(reqName);
			t = json2Entity(json,clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return t;
	}
	/**
	 * 返回类型自定义，可返回数组
	 * @param jsonVal
	 * @param clazz
	 * @return
	 */
	public static <T> Object json2Object(String jsonVal,Class<T> clazz){
		try {
			return  objectMapper.readValue(jsonVal, clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  return null;
		}
}
