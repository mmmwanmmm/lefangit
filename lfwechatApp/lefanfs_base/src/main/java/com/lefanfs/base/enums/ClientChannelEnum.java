package com.lefanfs.base.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 客户端渠道枚举
 * 
 * @author Daniel
 */
public enum ClientChannelEnum {
	android_client("android_client", "android_client"), ios_client("ios_client", "ios_client");

	private String channelCode;
	private String description;

	ClientChannelEnum(String channelCode, String description) {
		this.channelCode = channelCode;
		this.description = description;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public String getDescription() {
		return description;
	}

	public static ClientChannelEnum[] getClientChannels() {
		return ClientChannelEnum.values();
	}

	public static Map<String, String> getClientChannelMap() {
		Map<String, String> retMap = new LinkedHashMap<String, String>();
		for (ClientChannelEnum aEnum : ClientChannelEnum.values()) {
			retMap.put(aEnum.getChannelCode(), aEnum.getDescription());
		}
		return retMap;
	}
}
