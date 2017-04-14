package com.lefanfs.apicenter.util;

import com.lefanfs.apicenter.model.SendSms;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SendMessageUntil {
	private final static String url = "http://www.etuocloud.com/gateway.action";
	//应用 app_key
	private final static String APP_KEY = "4bdPcFT8OKYntrazrj1IK5MQkJnMs959";
	//应用 app_secret
	private final static String APP_SECRET = "o8StP550NV2CoYA0MNiNe1F5XbXswRWoiii4HOGrrXHWAbNMVvPk2Tdgrllf8fim";
	//接口响应格式 json或xml
	private final static String FORMAT = "json";
	private static String genSign(Map<String, String> params)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		//TreeMap 默认按key 升序
		Map<String,String> sortMap = new TreeMap<String,String>();
		sortMap.putAll(params);
		//以k1=v1&k2=v2...方式拼接参数
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> s : sortMap.entrySet()) {
			String k = s.getKey();
			String v = s.getValue();
			if(StringUtils.isBlank(v)){//过滤空值
				continue;
			}
			builder.append(k).append("=").append(v).append("&");
		}
		if (!sortMap.isEmpty()) {
			builder.deleteCharAt(builder.length() - 1);
		}
		//拼接应用的app_secret
		builder.append(APP_SECRET);
		//摘要
		MessageDigest instance = MessageDigest.getInstance("MD5");
		byte[] digest = instance.digest(builder.toString().getBytes("UTF-8"));
		//十六进制表示
		return new String(encodeHex(digest));
	}
	private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
		'e', 'f' };
	private static char[] encodeHex(byte[] data) {
		int l = data.length;
		char[] out = new char[l << 1];
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS_LOWER[0x0F & data[i]];
		}
		return out;
	}
	public static String sendSmsCustom(HttpServletResponse response,String cardtype,String carnumber,String time,String phonenumber,String cardnumber) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		String paramsmes=cardtype+","+cardnumber+","+carnumber+","+time;
		params.put("app_key", APP_KEY);
		params.put("view", FORMAT);
		params.put("method", "cn.etuo.cloud.api.sms.template");
		params.put("to", phonenumber);
		params.put("template", "777");
		params.put("params", paramsmes);
		params.put("sign", genSign(params));
		String result = HttpClientUtils.httpPost(url, params);
		return result;
	}
	public static String sendSmsCardAc(HttpServletResponse response,String cardtype,String phonenumber,String cardnumber,String cardpwd) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		String paramsmes=cardtype+","+cardnumber+","+cardpwd;
		params.put("app_key", APP_KEY);
		params.put("view", FORMAT);
		params.put("method", "cn.etuo.cloud.api.sms.template");
		params.put("to", phonenumber);
		params.put("template", "787");
		params.put("params", paramsmes);
		params.put("sign", genSign(params));
		String result = HttpClientUtils.httpPost(url, params);
		return result;
	}
    public static SendSms sendSmsValidateCode(String phonenumber,String smsCode) throws Exception {
        SendSms sendSms=new SendSms();
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=smsCode;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "305");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        sendSms.setSmsCode(smsCode);
        sendSms.setTelphone(phonenumber);
        return sendSms;
    }
    //贷款状态变更短信
    public static String sendSmsLoanApplication(String phonenumber,String loanStateStr) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=loanStateStr;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "992");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    //成为推广大使短信发送
    public static String sendSmsUserPromoted(String phonenumber) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes="4006303071";
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "990");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
}
