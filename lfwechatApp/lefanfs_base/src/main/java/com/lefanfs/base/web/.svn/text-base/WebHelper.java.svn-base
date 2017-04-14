package com.lefanfs.base.web;

import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class WebHelper {
	public static void sendRedirect(HttpServletResponse response, String url) throws IOException {
		response.sendRedirect(response.encodeRedirectURL(url));
	}

	@SuppressWarnings("unchecked")
	public static String getCurrRequestUrl(HttpServletRequest request) throws UnsupportedEncodingException {
		StringBuffer currRequestUrl = request.getRequestURL();
		Enumeration<String> parameterNames = request.getParameterNames();
		boolean flag = true;
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			String value = request.getParameter(paramName);
			if (flag) {
				currRequestUrl.append("?" + paramName + "=" + URLEncoder.encode(value, "UTF-8"));
				flag = false;
			} else {
				currRequestUrl.append("&" + paramName + "=" + URLEncoder.encode(value, "UTF-8"));
				flag = false;
			}
		}
		return currRequestUrl.toString();
	}

	/**
	 * 获取cookie
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 * @author Daniel
	 */
	public static String getCookie(HttpServletRequest request, String cookieName) {
		Cookie cookie = WebUtils.getCookie(request, cookieName);
		if (cookie != null) {
			return cookie.getValue();
		}
		return null;
	}

	/**
	 * 
	 * 设置cookie
	 * 
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param expiry
	 * @param domain
	 * @author Daniel
	 */
	public static void setCooKie(HttpServletResponse response, String cookieName, String cookieValue, int expiry, String domain) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(expiry);
		if (domain != null && !"".equals(domain.trim())) {
			cookie.setDomain(domain);
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 移除cookie
	 * 
	 * @param request
	 * @param response
	 * @param cookieName
	 */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String domain) {
		Cookie cookie = WebUtils.getCookie(request, cookieName);
		if (cookie != null) {
			cookie.setMaxAge(0);
			if (domain != null && !"".equals(domain.trim())) {
				cookie.setDomain(domain);
			}
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}

	/**
	 * 获取当前操作人IP
	 * 
	 * @param request
	 * @return
	 * @author Daniel
	 */
	public static String getRequestIp(HttpServletRequest request) {
		// return request.getRemoteAddr();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 是否ajax请求
	 * 
	 * @param request
	 * @return
	 * @author Daniel
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		if ((request.getHeader("accept").indexOf("application/json") > -1) || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)) {
			return true;
		}
		return false;
	}

	/**
	 * 输出JSON
	 * 
	 * @param json
	 * @param response
	 * @return
	 * @author daniel
	 */
	public static String outputJson(String json, HttpServletResponse response) {
		return output(json, response, "text/html;charset=UTF-8");
	}

	public static String outputText(String text, HttpServletResponse response) {
		return output(text, response, "text/html;charset=UTF-8");
	}

	public static String outputXml(String xml, HttpServletResponse response) {
		return output(xml, response, "text/xml;charset=UTF-8");
	}

	public static String output(String str, HttpServletResponse response, String contentType) {
		response.reset();
		response.setCharacterEncoding("UTF-8");
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

	public static void outputExcel(File file, HttpServletResponse rsp, String excelFileName) {
		try {
			rsp.setContentType("application/vnd.ms-excel");
			rsp.setCharacterEncoding("UTF-8");
			rsp.addHeader("Content-disposition", "attachment;filename=\"" + new String((excelFileName + "_" + file.getName()).getBytes("UTF-8"), "ISO8859_1") + "\"");
			ServletOutputStream os = rsp.getOutputStream();
			os.write(FileUtils.readFileToByteArray(file));
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String outputForJsonp(String jsonpCallback, String json, HttpServletResponse response) {
		return outputJson(jsonpCallback + "(" + json + ")", response);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> buildParamMapFromRequest(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Enumeration<String> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String name = e.nextElement();
			String value = request.getParameter(name);
			paramMap.put(name, value);
		}
		return paramMap;
	}

	@SuppressWarnings("unchecked")
	public static void setRequestAttributesFromRequestParam(HttpServletRequest request) {
		Enumeration<String> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String name = e.nextElement();
			String value = request.getParameter(name);
			request.setAttribute(name, value);
		}
	}

	@SuppressWarnings("rawtypes")
	public static String extractRequestHeader(HttpServletRequest req, String headerName) {
		Enumeration aHeader = req.getHeaders(headerName);
		if (aHeader != null) {
			while (aHeader.hasMoreElements()) {
				Object obj = aHeader.nextElement();
				if (!StringUtils.isEmpty(obj)) {
					return obj.toString();
				}
			}
		}
		return null;
	}

	public static String getRequestUrl(HttpServletRequest req) {
		String rquestUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getRequestURI();
		if (req.getServerPort() == 80) {
			rquestUrl = req.getScheme() + "://" + req.getServerName() + req.getRequestURI();
		}
		return rquestUrl;
	}
}
