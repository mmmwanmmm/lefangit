package com.lefanfs.base.dto;

import com.lefanfs.base.enums.ApiMsgEnum;

import java.io.Serializable;

public class ApiResponse<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1390658031289975172L;

	private ApiMsgEnum msgEnum;
	private Object[] userMsgArgs;
	private Integer count;
	private T results;

	public ApiResponse(ApiMsgEnum _msgEnum) {
		this.msgEnum = _msgEnum;
	}

	public ApiResponse(ApiMsgEnum _msgEnum, Object[] _userMsgArgs) {
		this.msgEnum = _msgEnum;
		this.userMsgArgs = _userMsgArgs;
	}

	public ApiResponse(ApiMsgEnum _msgEnum, Integer _count, T _results) {
		this.msgEnum = _msgEnum;
		this.count = _count;
		this.results = _results;
	}

	public ApiMsgEnum getMsgEnum() {
		return msgEnum;
	}

	public void setMsgEnum(ApiMsgEnum msgEnum) {
		this.msgEnum = msgEnum;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public T getResults() {
		return results;
	}

	public void setResults(T results) {
		this.results = results;
	}

	public Object[] getUserMsgArgs() {
		return userMsgArgs;
	}

	public void setUserMsgArgs(Object[] userMsgArgs) {
		this.userMsgArgs = userMsgArgs;
	}

}
