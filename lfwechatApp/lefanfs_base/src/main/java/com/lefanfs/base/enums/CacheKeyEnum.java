package com.lefanfs.base.enums;

/**
 * 缓存key枚举
 * 
 * @author Daniel
 */
public enum CacheKeyEnum {
	/**
	 * 配置项
	 */
	setting("SETTING_"),

	/**
	 * 短信间隔
	 */
	sms_interval("SMS_INTERVAL_"),

	/**
	 * 短信验证码
	 */
	sms_validatecode("SMS_VALIDATECODE_"),

	/**
	 * 邮件验证码
	 */
	email_validatecode("EMAIL_VALIDATECODE_"),

	/**
	 * 注册登录名
	 */
	register_loginname("REGISTER_LOGINNAME_"),

	/**
	 * 最近获得收益的用户信息
	 */
	latest_income_list("LATEST_INCOME_LIST"),

	/**
	 * 新手任务标识
	 */
	newcomer_task_flag("NEWCOMER_TASK_FLAG_"),;

	String code;

	CacheKeyEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
