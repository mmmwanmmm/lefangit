package com.lefanfs.apicenter.dto;

import java.io.Serializable;

public class OauthAccessTokenDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6684744247146470088L;

	private String access_token;
	private Long expires_in;
	private String refresh_token;
	private String openid;
	private String scope;
	private String remind_in;
	private String uid;
	private String douban_user_id;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

	public String getRemind_in() {
		return remind_in;
	}

	public void setRemind_in(String remind_in) {
		this.remind_in = remind_in;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDouban_user_id() {
		return douban_user_id;
	}

	public void setDouban_user_id(String douban_user_id) {
		this.douban_user_id = douban_user_id;
	}

}
