package com.lefanfs.base.enums;

/**
 * 内容实体类型<br>
 * 每一张表对应一个枚举
 * 
 * @author Daniel
 */
public enum ContentTypeEnum {

	sys_user(100),

	sys_menu(101),

	sys_role(102),

	sys_role_menu(103),

	sys_user_role(104),

	append_service(105),

	payment_channel(106),

	country_region(107),

	pricing_info(108),

	driver_info(109),

	agent_info(110),

	customer_info(111), order_info(112), car_type(113), countryRegion_info(114), ;

	Integer id;

	ContentTypeEnum(Integer _id) {
		this.id = _id;
	}

	public Integer getId() {
		return id;
	}

	public static ContentTypeEnum getContentTypeEnum(Integer id) {
		ContentTypeEnum[] enumArr = ContentTypeEnum.values();
		for (ContentTypeEnum aEnum : enumArr) {
			if (aEnum.getId().intValue() == id.intValue()) {
				return aEnum;
			}
		}
		return null;
	}

	public static String getContentType(Integer id) {
		ContentTypeEnum aEnum = getContentTypeEnum(id);
		if (aEnum != null) {
			return aEnum.name().toLowerCase();
		}
		return null;
	}
}
