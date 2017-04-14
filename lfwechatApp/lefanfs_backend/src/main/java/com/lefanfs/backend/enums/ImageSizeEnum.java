package com.lefanfs.backend.enums;

import java.util.ArrayList;
import java.util.List;

public enum ImageSizeEnum {
	// SIZE_100("100X100"), SIZE_80("80X80"), SIZE_50("50X50"),
	// SIZE_30("30X30"), SIZE_DEFAULT("default"), SIZE_1280("1280X960"),
	// SIZE_1024("1024X768"), SIZE_800("800X600"), SIZE_640("640X480"),
	// SIZE_480(
	// "480X270");

	/**
	 * 默认原始大小
	 */
	SIZE_DEFAULT("default"),
	/**
	 * 用户头像大小
	 */
	SIZE_100("100X100");

	private String value;

	private ImageSizeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static List<String> getValues() {
		List<String> list = new ArrayList<String>();
		for (ImageSizeEnum img : ImageSizeEnum.values()) {
			if (!"default".equals(img.getValue())) {
				list.add(img.getValue());
			}
		}
		return list;
	}
}
