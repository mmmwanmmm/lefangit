package com.lefanfs.base.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * API服务器列表
 * 
 * @author Daniel
 */
public enum ApiServerEnum {
	/**
	 * API中心
	 */
	apicenter("API中心"),

	// /**
	// * 用户api
	// */
	// user_api("用户api"),
	//
	// /**
	// * 内容api
	// */
	// content_api("内容api"),
	//
	// /**
	// * 公用api
	// */
	// common_api("公用api"),
	//
	// /**
	// * 日志api
	// */
	// log_api("日志api"),

	;
	private String descript;

	ApiServerEnum(String _descript) {
		this.descript = _descript;
	}

	public String getDescript() {
		return descript;
	}

	public static Map<String, String> getApiServerEnumMap() {
		Map<String, String> retMap = new LinkedHashMap<String, String>();
		ApiServerEnum[] enumArr = ApiServerEnum.values();
		for (ApiServerEnum aEnum : enumArr) {
			retMap.put(aEnum.name(), aEnum.getDescript());
		}
		return retMap;
	}

	public static ApiServerEnum getApiServerEnum(String name) {
		try {
			return ApiServerEnum.valueOf(name);
		} catch (Exception e) {
			return null;
		}
	}
}
