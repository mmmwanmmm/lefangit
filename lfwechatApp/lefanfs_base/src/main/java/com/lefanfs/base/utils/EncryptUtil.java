/**
 * 
 */
package com.lefanfs.base.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * 加密解密工具类
 * 
 * @author daniel
 */
public class EncryptUtil {

	/**
	 * 你的方法上面请加上注释
	 * 
	 * @param args
	 * @author daniel
	 */
	public static void main(String[] args) {
		String retStr = encryptByBase64("aaa%%%@@@");
		System.out.println("encryptViaBase64=" + retStr);

		String retStr2 = decryptByBase64(retStr);
		System.out.println("encryptViaBase64=" + retStr2);
	}

	/**
	 * 通过base64算法加密
	 * 
	 * @param inputStr
	 * @return
	 * @author daniel
	 */
	public static String encryptByBase64(String inputStr) {
		return EncryptUtil.encryptByBase64(inputStr, false);
	}

	/**
	 * 
	 * 通过base64算法加密
	 * 
	 * @param inputStr
	 * @param urlSafe
	 * @return
	 * @author Lynch
	 */
	public static String encryptByBase64(String inputStr, boolean urlSafe) {
		byte[] bytes = Base64.encodeBase64(inputStr.getBytes(), Boolean.FALSE, urlSafe);
		return new String(bytes);
	}

	/**
	 * 通过base64算法解密
	 * 
	 * @param inputStr
	 * @return
	 * @author daniel
	 */
	public static String decryptByBase64(String inputStr) {
		byte[] bytes = Base64.decodeBase64(inputStr.getBytes());
		return new String(bytes);
	}
}
