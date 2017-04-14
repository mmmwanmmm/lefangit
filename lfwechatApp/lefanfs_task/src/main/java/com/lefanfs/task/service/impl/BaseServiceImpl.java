package com.lefanfs.task.service.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.lefanfs.base.constants.Constant;
import com.lefanfs.base.enums.ApiServerEnum;
import com.lefanfs.base.enums.AppApiMethodEnum;
import com.lefanfs.base.enums.BackendApiMethodEnum;
import com.lefanfs.base.utils.HttpClientUtil;
import com.lefanfs.base.utils.JsonUtil;
import com.lefanfs.base.web.SuperDispatcherServlet;

public abstract class BaseServiceImpl {

	@Value("${api.server}")
	private String apiServer;

	@Value("${api.key}")
	private String apiKey;

	@Value("${api.secert}")
	private String apiSecert;

	protected String callApi(AppApiMethodEnum apiMethodEnum, Map<String, Object> appendMap) {
		return this._callApi(apiMethodEnum.getCode(), apiMethodEnum.getApiServer(), appendMap);
	}

	protected String callApi(BackendApiMethodEnum apiMethodEnum, Map<String, Object> appendMap) {
		return this._callApi(apiMethodEnum.getCode(), apiMethodEnum.getApiServer(), appendMap);
	}

	private String _callApi(String apiCode, ApiServerEnum apiServer, Map<String, Object> appendMap) {
		Map<String, Object> reqParamMap = new HashMap<String, Object>();
		if (appendMap != null && appendMap.size() > 0) {
			reqParamMap.putAll(appendMap);
		}
		reqParamMap.put(Constant.API_CODE, apiCode);
		reqParamMap.put(Constant.API_KEY, apiKey);
		reqParamMap.put(Constant.API_SIGN, Constant.INNER_API_SIGN);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		String dataJson = JsonUtil.objectToJson(reqParamMap, Map.class);
		paramMap.put(Constant.DATA, dataJson);

		HttpClientUtil clientUtil = new HttpClientUtil();
		Map<String, String> head = new HashMap<String, String>();
		int port = SuperDispatcherServlet.getlocalPort();
		head.put("referer", "task/" + port);
		clientUtil.setHttpSetting(head);
		clientUtil.setTimeOut(30000);
		String retJson = clientUtil.doHttpPost(MessageFormat.format(this.apiServer, apiCode, Constant.INNER_API_SIGN), paramMap);
		return retJson;
	}

}
