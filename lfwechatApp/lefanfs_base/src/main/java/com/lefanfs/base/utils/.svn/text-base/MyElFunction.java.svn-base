package com.lefanfs.base.utils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EL工具类
 * 
 * @author yijinchuan
 */
public class MyElFunction {

	public static String formatDate(long time, String pattern) {
		if (time == 0) {
			return null;
		}
		String str = null;
		try {
			Date d = new Date(time);
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			str = sdf.format(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String encodeUrl(String inputUrl) {
		if (inputUrl == null || "".equals(inputUrl.trim())) {
			return null;
		}
		String str = inputUrl;
		try {
			str = java.net.URLEncoder.encode(inputUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String decodeUrl(String inputUrl) {
		if (inputUrl == null || "".equals(inputUrl.trim())) {
			return null;
		}
		String str = inputUrl;
		try {
			str = java.net.URLDecoder.decode(inputUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 
	 * 过滤html标签
	 * 
	 * @param sourceHtml
	 * @return
	 * @author zhaozuowen
	 */
	public static String replaceHtml(String sourceHtml) {
		if (sourceHtml == null || "".equals(sourceHtml.trim())) {
			return null;
		}
		String str = sourceHtml;
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(sourceHtml);
		str = m_html.replaceAll(""); // 过滤html标签
		return str.trim(); // 返回文本字符串
	}

	public static String replaceEnter(String inputStr) {
		if (inputStr == null || "".equals(inputStr.trim())) {
			return null;
		}
		// System.out.println("replaceEnter=" + inputStr);
		// return inputStr.replaceAll("\\r\\n", "");
		Pattern p = Pattern.compile("\\s*|t|r|n");
		Matcher m = p.matcher(inputStr);
		String dest = m.replaceAll("");
		return dest;
	}

	public static String replaceImg(String inputStr, long width, long height) {
		if (inputStr == null || "".equals(inputStr.trim())) {
			return null;
		}
		Pattern p = Pattern.compile("(<img[\\s\\S]+?)src");
		Matcher m = p.matcher(inputStr);
		String dest = m.replaceAll("<img data-width='" + width + "' data-height='" + height + "' data-original-src");
		return dest;
	}

	public static void main(String[] args) {
		String str = "fsfsd<img    src=\"images/01.gif\">fsfsdf<img src=\"images/02.gif\">fsfsdf";
		String dest = replaceImg(str, 400, 300);
		System.out.println(dest);
		// StringBuilder str = new StringBuilder();
		// str.append("           黄国敬先生/小姐：");
		// str.append("   感谢您对我司工作的支持！您应聘的           部           职位已通过初次面试，请您于       月        日        时备齐毕业证、身份证等其它相关证件按指定的时间到我司面试。");
		// str.append("乘车路线：南头坐331（或其它途径）到松岗汽车站下车，在松岗松桥酒店旁坐中巴764到江边工业区芭田生态工程股份有限公司。");
		// str.append("如有不明之出，敬请与徐小姐联系，联系电话：27074138。");
		//
		// System.out.println(replaceEnter(str.toString()));
	}
}
