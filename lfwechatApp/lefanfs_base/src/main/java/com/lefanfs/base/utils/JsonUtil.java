package com.lefanfs.base.utils;

import com.google.gson.GsonBuilder;
import com.lefanfs.base.componets.GsonDateDeserializer;
import com.lefanfs.base.componets.GsonDateSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;

/**
 * 实现json字符串与java对象之间的互相转换等功能
 * 
 * @author daniel
 * 
 */
public class JsonUtil {

	/**
	 * 将json字符串转换成java对象（支持泛型）
	 * 
	 * @param json
	 *            json字符串
	 * @param type
	 *            对象类型
	 * @return
	 */

	public static <T> T jsonToObject(String json, Type type) {
		T obj = null;
		try {
			GsonBuilder gb = new GsonBuilder().serializeNulls();
			gb.registerTypeAdapter(java.util.Date.class, new GsonDateDeserializer()).setDateFormat(DateFormat.LONG);
			obj = gb.create().fromJson(json, type);
			// obj = new Gson().fromJson(json, type);
			// obj = JSON.parseObject(json, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 将java对象转换成json字符串
	 * 
	 * @param obj
	 *            对象
	 * @return
	 */
	public static String objectToJson(Object obj) {
		return objectToJson(obj, null);
	}

	// /**
	// * java对象－〉fastjson
	// *
	// * @param obj
	// * @return
	// * @author Daniel
	// */
	// public static String object2Fastjson(Object obj) {
	// String json = null;
	// try {
	// json = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return json;
	// }

	/**
	 * 将java对象转换成json字符串（支持泛型）
	 * 
	 * @param obj
	 *            对象
	 * @param type
	 *            对象类型
	 * @return
	 */
	public static String objectToJson(Object obj, Type type) {
		return objectToJson(obj, type, null);
	}

	public static String objectToJson(Object obj, Type type, String dateFormat) {
		String json = null;
		try {
			GsonBuilder gb = new GsonBuilder().serializeNulls();
			if (dateFormat != null && !"".equals(dateFormat)) {
				gb.setDateFormat(dateFormat);
			} else {
				gb.registerTypeAdapter(java.util.Date.class, new GsonDateSerializer()).setDateFormat(DateFormat.LONG);
			}
			if (type != null) {
				json = gb.create().toJson(obj, type);
			} else {
				json = gb.create().toJson(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
