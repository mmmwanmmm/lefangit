package com.lefanfs.base.impl;

import com.lefanfs.base.constants.Constant;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.utils.Md5Util;
import com.lefanfs.base.utils.MemcachedUtil;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;

public abstract class SuperServiceImpl {

	protected void setPageIndex(ApiRequest apiReq) {
		if (apiReq.getInt(Constant.PAGE_INDEX) != null && apiReq.getInt(Constant.PAGE_INDEX) >= 0) {
			return;
		}
		if (apiReq.getInt(Constant.PAGE) == null || apiReq.getInt(Constant.PAGE) < 1) {
			apiReq.put(Constant.PAGE, 1);
		}
		int page = apiReq.getInt(Constant.PAGE);
		int pageSize = getAppPageSize(apiReq);
		int pageIndex = (page - 1) * pageSize;
		apiReq.put(Constant.PAGE_INDEX, pageIndex);
	}

	protected int getPageIndex(ApiRequest apiReq) {
		setPageIndex(apiReq);
		return apiReq.getInt(Constant.PAGE_INDEX);
	}

	protected int getAppPageSize(ApiRequest apiReq) {
		setAppPageSize(apiReq);
		return apiReq.getInt(Constant.PAGE_SIZE);
	}

	protected void setAppPageSize(ApiRequest apiReq) {
		if (apiReq.getInt(Constant.PAGE_SIZE) == null || apiReq.getInt(Constant.PAGE_SIZE) < 1) {
			apiReq.put(Constant.PAGE_SIZE, Constant.APP_PAGE_SIZE);
		}
	}

	protected void setBackendPageSize(ApiRequest apiReq) {
		if (StringUtils.isEmpty(apiReq.get(Constant.PAGE_SIZE))) {
			apiReq.put(Constant.PAGE_SIZE, Constant.BACKEND_PAGE_SIZE);
		}
		if (apiReq.getInt(Constant.PAGE) == null || apiReq.getInt(Constant.PAGE) < 1) {
			apiReq.put(Constant.PAGE, 1);
		}
		int page = apiReq.getInt(Constant.PAGE);
		int pageSize = apiReq.getInt(Constant.PAGE_SIZE);
		int pageIndex = (page - 1) * pageSize;
		apiReq.put(Constant.PAGE_INDEX, pageIndex);
	}

	protected String getStaticUrl(int staticServerCount, String serverPrefix, String resourcePath, String sizeFlag) {
		if (resourcePath == null || "".equals(resourcePath)) {
			return null;
		}
		if (sizeFlag != null && !"".equals(sizeFlag)) {
			resourcePath = resourcePath + sizeFlag;
		}
		int serverId = getStaticServerId(staticServerCount, resourcePath);
		serverPrefix = MessageFormat.format(serverPrefix, serverId);
		return serverPrefix + resourcePath;
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

	private int getStaticServerId(int serverCount, String resourcePath) {
		int serverId = 0;

		String md5 = Md5Util.encodeString(resourcePath);
		char firstChar = md5.charAt(0);
		serverId = Integer.valueOf(firstChar) % serverCount;

		return serverId + 1;
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

	protected boolean removeFromMemcached(String key) {
		return MemcachedUtil.getInstance().remove(key);
	}

}
