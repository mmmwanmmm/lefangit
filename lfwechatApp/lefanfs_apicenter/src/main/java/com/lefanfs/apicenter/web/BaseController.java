package com.lefanfs.apicenter.web;

import com.lefanfs.base.constants.Constant;
import com.lefanfs.base.dto.ApiFinalResponse;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ContentTypeEnum;
import com.lefanfs.base.utils.JsonUtil;
import com.lefanfs.base.web.WebHelper;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.MessageFormat;

public class BaseController {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected String buildApiFinalResponse(ApiRequest apiReq, ApiResponse apiRsp) {
		// 统一输出响应JSON
		ApiFinalResponse finalRsp = new ApiFinalResponse();
		finalRsp.setApiVersion(apiReq.getApiVersion());
		finalRsp.setApiCode(apiReq.getApiCode());
		finalRsp.setIsSuccess(apiRsp.getMsgEnum().getIsSuccess());
		finalRsp.setCode(apiRsp.getMsgEnum().getCode());
		if (apiRsp.getUserMsgArgs() != null && apiRsp.getUserMsgArgs().length > 0) {
			finalRsp.setUserMsg(MessageFormat.format(apiRsp.getMsgEnum().getUserMsg(), apiRsp.getUserMsgArgs()));
			finalRsp.setMsg(MessageFormat.format(apiRsp.getMsgEnum().getUserMsg(), apiRsp.getUserMsgArgs()));
		} else {
			finalRsp.setUserMsg(apiRsp.getMsgEnum().getUserMsg());
			finalRsp.setMsg(apiRsp.getMsgEnum().getMsg());
		}
		finalRsp.setCount(apiRsp.getCount());
		finalRsp.setResults(apiRsp.getResults());
		if (apiRsp.getCount() != null && apiRsp.getCount().intValue() > 0 && apiReq.getInt(Constant.PAGE_SIZE) != null && apiReq.getInt(Constant.PAGE_SIZE).intValue() > 0) {
			int pageSize = Integer.parseInt(apiReq.get(Constant.PAGE_SIZE).toString());
			int page = 1;
			if (apiReq.getInt(Constant.PAGE) != null && apiReq.getInt(Constant.PAGE).intValue() > 0) {
				page = apiReq.getInt(Constant.PAGE);
			}
			int totalPages = apiRsp.getCount() / pageSize;
			if (apiRsp.getCount() % pageSize > 0) {
				totalPages++;
			}
			finalRsp.setTotalPages(totalPages);
			finalRsp.setCurPage(page);
			finalRsp.setPageSize(pageSize);
		}
		// return JsonUtil.object2Fastjson(finalRsp);
		return JsonUtil.objectToJson(finalRsp);
	}

	@SuppressWarnings("rawtypes")
	protected String outoutApiFinalResponse(ApiRequest apiReq, ApiResponse apiRsp, HttpServletResponse rsp) {
		String apiRspJson = this.buildApiFinalResponse(apiReq, apiRsp);
		return WebHelper.outputJson(apiRspJson, rsp);
	}

	protected void buildSysParamFromHeader(HttpServletRequest req, ApiRequest apiReq) {
		apiReq.setRequestIp(WebHelper.getRequestIp(req));
		apiReq.setRequestTimestamp(System.currentTimeMillis());
		apiReq.setUserAgent(WebHelper.extractRequestHeader(req, "User-Agent"));
		JsonObject clientEventObj = this.extractClientEventFromHeader(req);
		if (clientEventObj != null) {
			apiReq.setDeviceNo(this.extractValueFromClientEvent(clientEventObj, Constant.DEVICE_NO));
			apiReq.setMacAddress(this.extractValueFromClientEvent(clientEventObj, Constant.MAC_ADDRESS));
			apiReq.setOsVersion(this.extractValueFromClientEvent(clientEventObj, Constant.OS_VERSION));
			apiReq.setModelName(this.extractValueFromClientEvent(clientEventObj, Constant.MODEL_NAME));
			apiReq.setChannelCode(this.extractValueFromClientEvent(clientEventObj, Constant.CHANNEL_CODE));
			apiReq.setClientBundleId(this.extractValueFromClientEvent(clientEventObj, Constant.CLIENT_BUNDLE_ID));
			apiReq.setClientVersionName(this.extractValueFromClientEvent(clientEventObj, Constant.CLIENT_VERSION_NAME));
			apiReq.setClientVersionCode(this.extractValueFromClientEvent(clientEventObj, Constant.CLIENT_VERSION_CODE));
			apiReq.setPushDeviceId(this.extractValueFromClientEvent(clientEventObj, Constant.PUSH_DEVICE_ID));
			apiReq.setUserToken(this.extractValueFromClientEvent(clientEventObj, Constant.USER_TOKEN));
			if (!StringUtils.isEmpty(apiReq.getDeviceNo()) || !StringUtils.isEmpty(apiReq.getMacAddress())) {
				apiReq.setImeiMac((!StringUtils.isEmpty(apiReq.getDeviceNo()) ? apiReq.getDeviceNo() : "") + "------" + (!StringUtils.isEmpty(apiReq.getMacAddress()) ? apiReq.getMacAddress() : ""));
			}
		}
	}

	private String extractValueFromClientEvent(JsonObject clientEventObj, String key) {
		if (clientEventObj.has(key)) {
			return clientEventObj.get(key).getAsString();
		}
		return null;
	}

	private JsonObject extractClientEventFromHeader(HttpServletRequest req) {
		String xClientEvent = WebHelper.extractRequestHeader(req, "X-Client-Event");
		if (StringUtils.isEmpty(xClientEvent)) {
			return null;
		}
		JsonObject jsonObj = JsonUtil.jsonToObject(xClientEvent, JsonObject.class);
		return jsonObj;
	}

	@Value("${resource.prefix}")
	protected String resourcePrefix;

	protected String getCdnUrl(ContentTypeEnum typeEnum, String resourcePath, String sizeFlag) {
		if (resourcePath != null && resourcePath.startsWith("http")) {
			return resourcePath;
		}
		return this.getStaticUrl(this.resourcePrefix, resourcePath, sizeFlag);
	}

	protected String getStaticUrl(String serverPrefix, String resourcePath, String sizeFlag) {
		if (resourcePath == null || "".equals(resourcePath)) {
			return null;
		}
		if (sizeFlag != null && !"".equals(sizeFlag)) {
			resourcePath = resourcePath + sizeFlag;
		}
		if (serverPrefix.endsWith("/")) {
			if (resourcePath.startsWith("/")) {
				return serverPrefix.substring(0, serverPrefix.length() - 1) + resourcePath;
			} else {
				return serverPrefix + resourcePath;
			}
		} else {
			if (resourcePath.startsWith("/")) {
				return serverPrefix + resourcePath;
			} else {
				return serverPrefix + "/" + resourcePath;
			}
		}
	}
    protected String output(String str, HttpServletResponse response, String contentType) {
        response.reset();
        response.setContentType(contentType);
        try {
            PrintWriter out = response.getWriter();
            out.print(str);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 输出JSON
     *
     * @param json
     * @param response
     * @return
     * @author daniel
     */
    protected String outputJson(String json, HttpServletResponse response) {
        return this.output(json, response, "text/html;charset=UTF-8");
    }
}
