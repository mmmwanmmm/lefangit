package com.lefanfs.apicenter.web;

import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.constants.Constant;
import com.lefanfs.base.dto.ApiKeyDto;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import com.lefanfs.base.utils.ApiSignUtil;
import com.lefanfs.base.utils.JsonUtil;
import com.lefanfs.base.utils.MemcachedUtil;
import com.lefanfs.base.web.SpringBeanProxy;
import com.lefanfs.base.web.SuperDispatcherServlet;
import com.lefanfs.base.web.WebHelper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lefanfs.apicenter.dto.UserSessionDto;
import com.lefanfs.apicenter.service.RedisService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * API中心
 * 
 * @author Daniel
 */
@Controller
@RequestMapping(value = "/apicenter")
public class ApiCenterController extends BaseController {
	private static final Logger loger = Logger.getLogger(ApiCenterController.class);

	private static final Map<String, ApiKeyDto> apiKeyMap = new HashMap<String, ApiKeyDto>();
	private static final Map<Long, ApiRequest> currentReqeust = new HashMap<Long, ApiRequest>();

	static {
		apiKeyMap.put(Constant.INNER_API_KEY, new ApiKeyDto(Constant.INNER_API_KEY, Constant.INNER_API_SIGN, "ios", "inner-api"));
		apiKeyMap.put("ca89e65c77be0d3f0d732cc3134edaf4", new ApiKeyDto("ca89e65c77be0d3f0d732cc3134edaf4", "32f48148e40fcf5586c269f65e6045b5", "ios", "test"));

		apiKeyMap.put("98dba382bc9de1579f2e84d575608eda", new ApiKeyDto("98dba382bc9de1579f2e84d575608eda", "95cecbfa4fb491957186a5a609ec25fa", "backend", "backend"));
		apiKeyMap.put("badbae9f2548d8b65ede2bd7564fb5cf", new ApiKeyDto("badbae9f2548d8b65ede2bd7564fb5cf", "cfe4c259b996ceff513a3115a1c2f83c", "task", "task"));
		apiKeyMap.put("a0b7434018686f816007afa55c8e6b5a", new ApiKeyDto("a0b7434018686f816007afa55c8e6b5a", "8641b5af0245295bc0bc9d9cee1e1545", "android", "android-app"));
		apiKeyMap.put("f8c1ac8d03fe6111f689a5384bae1d20", new ApiKeyDto("f8c1ac8d03fe6111f689a5384bae1d20", "29a85fd68b9b1e81ef82f8700c2e43e4", "ios", "ios-app"));
	}

	@Autowired
	private RedisService redisService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = { "/{code}/{key}" })
	public String index(@PathVariable String code, @PathVariable String key, HttpServletRequest req, HttpServletResponse rsp) {
		ApiRequest apiReq = this.getApiRequest(req);
		ApiResponse apiRsp = this.doDispatch(apiReq);
		String apiRspJson = this.buildApiFinalResponse(apiReq, apiRsp);
		this.saveNginxCacheInfo(req, code, key, apiRspJson);
		return WebHelper.outputJson(apiRspJson, rsp);
	}

	private ApiRequest getApiRequest(HttpServletRequest req) {
		String data = req.getParameter(Constant.DATA);
		JsonElement dataJsonEle = JsonUtil.jsonToObject(data, JsonElement.class);
		if (dataJsonEle != null && dataJsonEle.isJsonObject()) {
			return this.buildApiRequest(req, dataJsonEle.getAsJsonObject());
		}
		return new ApiRequest();
	}

	private ApiRequest buildApiRequest(HttpServletRequest req, JsonObject paramJsonObj) {
		ApiRequest apiReq = new ApiRequest();
		for (Entry<String, JsonElement> paramEntry : paramJsonObj.entrySet()) {
			String paramName = paramEntry.getKey();
			JsonElement paramValueEle = paramEntry.getValue();
			String paramValue = null;
			if (paramValueEle != null && !paramValueEle.isJsonNull() && paramValueEle.isJsonPrimitive()) {
				paramValue = paramValueEle.getAsString();
			}
			if (Constant.API_CODE.equals(paramName)) {
				apiReq.setApiCode(paramValue);
			} else if (Constant.API_VERSION.equals(paramName)) {
				apiReq.setApiVersion(paramValue);
			} else if (Constant.API_KEY.equals(paramName)) {
				apiReq.setApiKey(paramValue);
			} else if (Constant.API_SIGN.equals(paramName)) {
				apiReq.setApiSign(paramValue);
			} else if (Constant.USER_TOKEN.equals(paramName)) {
				apiReq.setUserToken(paramValue);
			} else {
				if (paramValueEle == null || paramValueEle.isJsonNull() || paramValueEle.isJsonPrimitive()) {
					apiReq.put(paramName, paramValue);
				} else if (paramValueEle.isJsonObject()) {
					apiReq.put(paramName, paramValueEle.getAsJsonObject());
				} else if (paramValueEle.isJsonArray()) {
					apiReq.put(paramName, paramValueEle.getAsJsonArray());
				}
			}
		}
		if (!StringUtils.isEmpty(apiReq.getApiKey())) {
			ApiKeyDto keyDto = apiKeyMap.get(apiReq.getApiKey());
			if (keyDto != null) {
				apiReq.setPlatform(keyDto.getPlatform());
				apiReq.setApiSource(keyDto.getApiSource());
			}
		}
		this.buildSysParamFromHeader(req, apiReq);
		return apiReq;
	}

	/**
	 * 请求控制分发
	 *
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private ApiResponse doDispatch(ApiRequest apiReq) {
		try {
			ApiMsgEnum checkMsg = this.checkParam(apiReq);
			if (checkMsg != null) {
				return new ApiResponse(checkMsg);
			}
			String apiCode = apiReq.getApiCode();
			// 此处可以把调用接口的IP,时间记录下来
			this.beforeInvokeService(apiReq);
			Object bean = SpringBeanProxy.getBeanByApiCode(apiCode);
			Method method = SpringBeanProxy.getMethodByApiCode(apiCode);
			try {
				Object rspObj = method.invoke(bean, new Object[] { apiReq });
				if (rspObj instanceof ApiResponse) {
					ApiResponse apiRsp = (ApiResponse) rspObj;
					this.afterInvokeService(apiReq, apiRsp);
					return apiRsp;
				}
				return new ApiResponse(ApiMsgEnum.SERVER_INTERNAL_ERROR);
			} catch (Exception e) {
				loger.error("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",method.invoke exception in " + apiCode, e);
				return new ApiResponse(ApiMsgEnum.SERVER_INTERNAL_ERROR);
			}
		} catch (Exception e) {
			loger.error("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",apiDispatche exception", e);
			return new ApiResponse(ApiMsgEnum.SERVER_INTERNAL_ERROR);
		}
	}

	/**
	 * 验证参数
	 * 
	 * @return
	 * @author Daniel
	 */
	private ApiMsgEnum checkParam(ApiRequest apiReq) {
		String apiKey = apiReq.getApiKey();
		if (StringUtils.isEmpty(apiKey) || apiKeyMap.get(apiKey) == null) {
			return ApiMsgEnum.BAD_REQUEST;
		}
		String apiCode = apiReq.getApiCode();
		if (StringUtils.isEmpty(apiCode)) {
			return ApiMsgEnum.BAD_REQUEST;
		}
		String inputApiSign = apiReq.getApiSign();
		if (StringUtils.isEmpty(inputApiSign)) {
			return ApiMsgEnum.BAD_REQUEST;
		}
		if (!Constant.INNER_API_SIGN.equals(inputApiSign)) {
			ApiKeyDto keyDto = apiKeyMap.get(apiReq.getApiKey());
			if (ApiSignUtil.checkSign(inputApiSign, apiReq, keyDto.getApiSecret())) {
				return ApiMsgEnum.SIGN_ERROR;
			}
		}
		ApiMethod apiMethod = SpringBeanProxy.getApiMethodByApiCode(apiCode);
		UserSessionDto sessionDto = null;
		if (!StringUtils.isEmpty(apiReq.getUserToken())) {
			sessionDto = redisService.getUserSession(apiReq.getUserToken());
			if (sessionDto != null) {
				apiReq.setCurrentUserId(sessionDto.getUserId());
				apiReq.setCurrentUserDisplayName(sessionDto.getNickname());
				apiReq.put(Constant.CURRENT_USER_ID, sessionDto.getUserId());
			}
		}
		if (apiMethod != null && apiMethod.needLogin()) {
			if (StringUtils.isEmpty(apiReq.getUserToken())) {
				return ApiMsgEnum.UserUnloginException;
			}

			if (sessionDto == null || sessionDto.getUserId() == null) {
				return ApiMsgEnum.UserUnloginException;
			}
			// apiReq.setUserType(sessionDto.getUserType());
		}
		return null;

	}

	/**
	 * 调用service之前
	 * 
	 * @param apiReq
	 * @author Daniel
	 */
	private void beforeInvokeService(ApiRequest apiReq) {
		currentReqeust.put(Thread.currentThread().getId(), apiReq);
	}

	/**
	 * 调用service之后
	 * 
	 * @param apiReq
	 * @author Daniel
	 */
	@SuppressWarnings("rawtypes")
	private void afterInvokeService(ApiRequest apiReq, ApiResponse apiRsp) {
		currentReqeust.remove(Thread.currentThread().getId());
	}

	/**
	 * 获取当前线程的AipRequest
	 * 
	 * @return
	 */
	public static ApiRequest getCurrentReqeust() {
		return currentReqeust.get(Thread.currentThread().getId());
	}

	protected boolean setToMemcached(String key, Object value, Integer expirySeconds) {
		if (expirySeconds != null) {
			return MemcachedUtil.getInstance().set(key, expirySeconds, value);
		} else {
			return MemcachedUtil.getInstance().set(key, value);
		}
	}

	protected Object getFromMemcached(String key) {
		return MemcachedUtil.getInstance().get(key);
	}

	/**
	 * 记录nginx访问信息
	 * 
	 * @param request
	 */
	private void saveNginxCacheInfo(HttpServletRequest request, String code, String key, final String result) {
		// String url = request.getRequestURI();
		// if (RegexUtil.checkString(nginxKeyRegex, url)) {
		// String data = request.getParameter(Constant.DATA);
		// final String host = request.getServerName() + url;
		// final Map<String, Object> reqParamMap = new HashMap<String,
		// Object>();
		// reqParamMap.put("codes", codes);
		// reqParamMap.put("key", host);
		// reqParamMap.put("nginxServer", request.getServerName());
		// reqParamMap.put(Constant.DATA, data);
		// myExecutor.execute(new Runnable() {
		// @Override
		// public void run() {
		// // MemcachedUtil.getInstance().set(host, result);
		// callApi(ApiMethodEnum.BACKEND_NGINX_COLLECTNGINXCACHE, reqParamMap);
		// }
		// });
		// }
	}
}
