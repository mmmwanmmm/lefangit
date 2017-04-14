package com.lefanfs.base.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 平台枚举
 * 
 * @author Daniel
 */
public enum PlatformEnum {
	android("android"), ios("ios");

	private String description;

	PlatformEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static Map<String, String> getPlatformMap() {
		Map<String, String> retMap = new LinkedHashMap<String, String>();
		for (PlatformEnum aEnum : PlatformEnum.values()) {
			retMap.put(aEnum.name(), aEnum.getDescription());
		}
		return retMap;
	}
}
