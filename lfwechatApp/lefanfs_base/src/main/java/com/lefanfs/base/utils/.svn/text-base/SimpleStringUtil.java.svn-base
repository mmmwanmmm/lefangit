package com.lefanfs.base.utils;

/**
 * 简单字符串处理
 * 
 * @author kevin
 * 
 */
public class SimpleStringUtil {
	public static String replaceHtml(String text) {
		text = text.replaceAll("\\<", "&lt;");
		text = text.replaceAll("\\>", "&gt;");
		return text;
	}

	public static String replaceHtmlBr(String text) {
		text = text.replaceAll("\n", "<br>");
		return text;
	}

	public static void main(String[] a) {
		String text = "回家看看厉害吧[e_183][e_184][e_191][e_190][e_198]";
		text = replaceHtml("<script>alert(11);</script>");
		System.out.println(text);
	}
}
