package com.lefanfs.base.utils;

import com.lefanfs.base.constants.Constant;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * API签名工具类
 * 
 * @author Daniel
 */
public class ApiSignUtil {

	/**
	 * 校验签名
	 * 
	 * @param inputSign
	 *            输入签名
	 * @param paramMap
	 *            入参对象
	 * @param apiCode
	 *            API编号
	 * @param apiSecret
	 *            API密钥
	 * @return
	 * @author Daniel
	 */
	public static boolean checkSign(String inputSign, Map<String, Object> paramMap, String apiSecret) {
		if (inputSign != null && !"".equals(inputSign) && paramMap != null && apiSecret != null && !"".equals(apiSecret)) {
			String rightSign = buildSign(paramMap, apiSecret);
			if (inputSign.equals(rightSign)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 构建签名你的方法上面请加上注释
	 * 
	 * @param paramMap
	 *            入参对象
	 * @param apiCode
	 *            API编号
	 * @param apiSecret
	 *            API密钥
	 * @return
	 * @author Daniel
	 */
	public static String buildSign(Map<String, Object> paramMap, String apiSecret) {
		String paramStr = buildParamStr(paramMap);
		return Md5Util.encodeString(paramStr + "&" + apiSecret);
	}

	private static String buildParamStr(Map<String, Object> oMap) {
		StringBuilder str = new StringBuilder();
		if (oMap != null && oMap.size() > 0) {
			Map<String, Object> paramMap = new TreeMap<String, Object>();
			paramMap.putAll(oMap);
			for (Entry<String, Object> entry : paramMap.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				if (!Constant.API_SIGN.equalsIgnoreCase(key)) {
					if (value != null && !"".equals(value.toString().trim()) && !"null".equals(value.toString())) {
						str.append(key);
						str.append("=");
						str.append(value.toString());
						str.append("&");
					}
				}
			}
		}
		return str.toString();
	}
}
