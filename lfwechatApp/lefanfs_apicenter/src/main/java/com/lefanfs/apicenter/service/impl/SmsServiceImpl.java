package com.lefanfs.apicenter.service.impl;

import com.lefanfs.base.utils.HttpsUtil;
import com.lefanfs.apicenter.service.SmsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsServiceImpl extends BaseServiceImpl implements SmsService {

	@Override
	public boolean sendSms(String templateId, String smsTo, String... smsContent) {
		String url = leancloudAppServer + "/requestSmsCode";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mobilePhoneNumber", smsTo);
		String retJson = HttpsUtil.doHttpsPost(url, paramMap, this._buildSmsRequestHeader());
		System.out.println("sendSms retJson=" + retJson);
		if ("{}".equals(retJson)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean verifySmsCode(String smsTo, String inputCode) {
//		if (true){
//			return true;
//		}
		String url = leancloudAppServer + "/verifySmsCode/" + inputCode + "?mobilePhoneNumber=" + smsTo;
		String retJson = HttpsUtil.doHttpsPost(url, null, this._buildSmsRequestHeader());
		System.out.println("verifySmsCode retJson=" + retJson);
		if ("{}".equals(retJson)) {
			return true;
		}
		return false;
	}

	private Map<String, Object> _buildSmsRequestHeader() {
		Map<String, Object> headerMap = new HashMap<String, Object>();
		headerMap.put("X-AVOSCloud-Application-Id", this.leancloudAppId);
		headerMap.put("X-AVOSCloud-Application-Key", this.leancloudAppKey);
		headerMap.put("Content-Type", "application/json");
		return headerMap;
	}
}
