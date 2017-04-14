/**
 * 
 */
package com.lefanfs.base.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * map的一些工具类
 * 
 * @author jack
 */
public class MapUtil {
	public static void main(String args[]) throws Exception {
		// CircleInfo circleInfo = new CircleInfo();
		// circleInfo.setCircleName("test");
		// circleInfo.setTypeCode("testcode");
		// circleInfo.setCreateUser(1L);
		// circleInfo.setUserCount(1L);
		// MapUtil.objectToMap(circleInfo);
	}

	/**
	 * 返回由对象的属性为key,值为map的value的Map集合
	 * 
	 * @param obj
	 *            Object
	 * @return mapValue Map<String,String>
	 * @throws Exception
	 */
	public static Map<String, Object> objectToMap(Object object) {
		return objectToMap(object, false);
	}

	public static Map<String, Object> objectToMap(Object object, boolean ignoreNullValue) {
		Map<String, Object> mapValue = new LinkedHashMap<String, Object>();
		try {
			Class<?> cls = object.getClass();
			Field[] superField = cls.getSuperclass().getDeclaredFields();
			Field[] fields = cls.getDeclaredFields();
			List<Field[]> fieldList = new ArrayList<Field[]>();
			fieldList.add(superField);
			fieldList.add(fields);
			for (Field[] fieldArray : fieldList) {
				for (Field field : fieldArray) {
					String name = field.getName();
					String temp = name.toUpperCase();
					if (!"serialVersionUID".equals(name) && !name.equals(temp)) {
						String strGet = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
						Method method = cls.getMethod(strGet);
						if (method != null) {
							Object obj = method.invoke(object);
							if (ignoreNullValue && obj == null) {
								continue;
							}
							mapValue.put(name, obj);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapValue;
	}
}
