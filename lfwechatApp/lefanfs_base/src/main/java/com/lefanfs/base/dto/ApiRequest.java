package com.lefanfs.base.dto;

import com.lefanfs.base.enums.ContentTypeEnum;
import com.lefanfs.base.utils.RegexUtil;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class ApiRequest extends HashMap<String, Object> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3518461079812863846L;

	private String apiVersion;

	private String apiKey;

	private String apiSign;

	private String apiCode;

	private String platform;

	private String apiSource;

	private String requestIp;

	private Long requestTimestamp;

	private String userAgent;

	private String deviceNo;

	private String macAddress;

	private String imeiMac;

	private String osVersion;

	private String modelName;

	private String channelCode;

	private String clientBundleId;

	private String clientVersionName;

	private String clientVersionCode;

	private String pushDeviceId;

	private String userToken;

	private String userType;

	private Long currentUserId;

	private String currentUserDisplayName;

	private ContentTypeEnum contentTypeEnum;

	public ApiRequest() {

	}

	public ApiRequest(ApiRequest oldApiReq) {
		this.apiVersion = oldApiReq.getApiVersion();
		this.apiKey = oldApiReq.getApiKey();
		this.apiSign = oldApiReq.getApiSign();
		this.apiCode = oldApiReq.getApiCode();
		this.platform = oldApiReq.getPlatform();
		this.apiSource = oldApiReq.getApiSource();
		this.requestIp = oldApiReq.getRequestIp();
		this.requestTimestamp = oldApiReq.getRequestTimestamp();
		this.userAgent = oldApiReq.getUserAgent();
		this.deviceNo = oldApiReq.getDeviceNo();
		this.macAddress = oldApiReq.getMacAddress();
		this.imeiMac = oldApiReq.getImeiMac();
		this.osVersion = oldApiReq.getOsVersion();
		this.modelName = oldApiReq.getModelName();
		this.channelCode = oldApiReq.getChannelCode();
		this.clientBundleId = oldApiReq.getClientBundleId();
		this.clientVersionName = oldApiReq.getClientVersionName();
		this.clientVersionCode = oldApiReq.getClientVersionCode();
		this.pushDeviceId = oldApiReq.getPushDeviceId();
		this.userToken = oldApiReq.getUserToken();
		this.userType = oldApiReq.getUserType();
		this.currentUserId = oldApiReq.getCurrentUserId();
		this.currentUserDisplayName = oldApiReq.getCurrentUserDisplayName();
	}

	public Map<String, Object> getSysParamMap() {
		Map<String, Object> sysParamMap = new HashMap<String, Object>();
		sysParamMap.put("apiVersion", this.apiVersion);
		sysParamMap.put("apiKey", this.apiKey);
		sysParamMap.put("apiSign", this.apiSign);
		sysParamMap.put("apiCode", this.apiCode);
		sysParamMap.put("platform", this.platform);
		sysParamMap.put("apiSource", this.apiSource);
		sysParamMap.put("requestIp", this.requestIp);
		sysParamMap.put("requestTimestamp", this.requestTimestamp);
		sysParamMap.put("userAgent", this.userAgent);
		sysParamMap.put("deviceNo", this.deviceNo);
		sysParamMap.put("macAddress", this.macAddress);
		sysParamMap.put("imeiMac", this.imeiMac);
		sysParamMap.put("osVersion", this.osVersion);
		sysParamMap.put("modelName", this.modelName);
		sysParamMap.put("channelCode", this.channelCode);
		sysParamMap.put("clientBundleId", this.clientBundleId);
		sysParamMap.put("clientVersionName", this.clientVersionName);
		sysParamMap.put("clientVersionCode", this.clientVersionCode);
		sysParamMap.put("pushDeviceId", this.pushDeviceId);
		sysParamMap.put("userToken", this.userToken);
		sysParamMap.put("userType", this.userType);
		sysParamMap.put("currentUserId", this.currentUserId);
		sysParamMap.put("currentUserDisplayName", this.currentUserDisplayName);

		return sysParamMap;
	}

	public Integer getInt(String key) {
		Object value = this.get(key);
		if (value != null && !"".equals(value)) {
			return Integer.valueOf(value.toString());
		}
		return null;
	}

	public Long getLong(String key) {
		Object value = this.get(key);
		if (value != null && !"".equals(value)) {
			return Long.valueOf(value.toString());
		}
		return null;
	}

	public Double getDouble(String key) {
		Object value = this.get(key);
		if (value != null && !"".equals(value)) {
			return Double.valueOf(value.toString());
		}
		return null;
	}

	public BigDecimal getBigDecimal(String key) {
		Object value = this.get(key);
		if (value != null && !"".equals(value)) {
			return new BigDecimal(value.toString());
		}
		return null;
	}

	public String getString(String key) {
		Object value = this.get(key);
		if (!StringUtils.isEmpty(value)) {
			return value.toString();
		}
		return null;
	}

	/**
	 * 通过(,)分割数组
	 * 
	 * @param key
	 * @return
	 */
	public String[] getStringArray(String key) {
		Object value = this.get(key);
		if (!StringUtils.isEmpty(value)) {
			String temp = value.toString();
			return temp.split(",");
		}
		return null;
	}

	public List<String> getStringList(String key) {
		String temp[] = this.getStringArray(key);
		if (temp != null && temp.length > 0) {
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < temp.length; i++) {
				list.add(temp[i]);
			}
			return list;
		}
		return null;
	}

	/**
	 * 通过(,)分割数组
	 * 
	 * @param key
	 * @return
	 */
	public Integer[] getIntArray(String key) {
		String temp[] = this.getStringArray(key);
		if (temp != null && temp.length > 0) {
			Integer[] array = new Integer[temp.length];
			for (int i = 0; i < temp.length; i++) {
				array[i] = Integer.valueOf(temp[i]);
			}
			return array;
		}
		return null;
	}

	public List<Integer> getIntList(String key) {
		String temp[] = this.getStringArray(key);
		if (temp != null && temp.length > 0) {
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < temp.length; i++) {
				list.add(Integer.valueOf(temp[i]));
			}
			return list;
		}
		return null;
	}

	/**
	 * 通过(,)分割数组
	 * 
	 * @param key
	 * @return
	 */
	public Long[] getLongArray(String key) {
		String temp[] = this.getStringArray(key);
		if (temp != null && temp.length > 0) {
			Long[] array = new Long[temp.length];
			for (int i = 0; i < temp.length; i++) {
				array[i] = Long.valueOf(temp[i]);
			}
			return array;
		}
		return null;
	}

	public List<Long> getLongList(String key) {
		String temp[] = this.getStringArray(key);
		if (temp != null && temp.length > 0) {
			List<Long> list = new ArrayList<Long>();
			for (int i = 0; i < temp.length; i++) {
				list.add(Long.parseLong(temp[i]));
			}
			return list;
		}
		return null;
	}

	public Set<Integer> getIntSet(String key) {
		String temp[] = this.getStringArray(key);
		if (temp != null && temp.length > 0) {
			Set<Integer> set = new HashSet<Integer>();
			for (int i = 0; i < temp.length; i++) {
				set.add(Integer.valueOf(temp[i]));
			}
			return set;
		}
		return null;
	}

	@Override
	public Object put(String key, Object value) {
		Object obj = null;
		if (value != null && value instanceof String) {
			String temp = (String) value;
			if ("".equals(temp)) {
				obj = super.put(key, null);
			} else if (RegexUtil.isWholeNumber(temp)) {
				try {
					obj = super.put(key, Integer.valueOf(temp));
				} catch (Exception e) {
					obj = super.put(key, temp);
				}
			} else {
				obj = super.put(key, value);
			}
		} else {
			obj = super.put(key, value);
		}
		return obj;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiSign() {
		return apiSign;
	}

	public void setApiSign(String apiSign) {
		this.apiSign = apiSign;
	}

	public String getApiCode() {
		return apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getApiSource() {
		return apiSource;
	}

	public void setApiSource(String apiSource) {
		this.apiSource = apiSource;
	}

	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	public Long getRequestTimestamp() {
		return requestTimestamp;
	}

	public void setRequestTimestamp(Long requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getClientBundleId() {
		return clientBundleId;
	}

	public void setClientBundleId(String clientBundleId) {
		this.clientBundleId = clientBundleId;
	}

	public String getClientVersionName() {
		return clientVersionName;
	}

	public void setClientVersionName(String clientVersionName) {
		this.clientVersionName = clientVersionName;
	}

	public String getClientVersionCode() {
		return clientVersionCode;
	}

	public void setClientVersionCode(String clientVersionCode) {
		this.clientVersionCode = clientVersionCode;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public Long getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(Long currentUserId) {
		this.currentUserId = currentUserId;
	}

	public ContentTypeEnum getContentTypeEnum() {
		return contentTypeEnum;
	}

	public void setContentTypeEnum(ContentTypeEnum contentTypeEnum) {
		this.contentTypeEnum = contentTypeEnum;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getCurrentUserDisplayName() {
		return currentUserDisplayName;
	}

	public void setCurrentUserDisplayName(String currentUserDisplayName) {
		this.currentUserDisplayName = currentUserDisplayName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getImeiMac() {
		return imeiMac;
	}

	public void setImeiMac(String imeiMac) {
		this.imeiMac = imeiMac;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getPushDeviceId() {
		return pushDeviceId;
	}

	public void setPushDeviceId(String pushDeviceId) {
		this.pushDeviceId = pushDeviceId;
	}

}
