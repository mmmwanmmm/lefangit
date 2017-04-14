package com.lefanfs.backend.interceptors;

import com.lefanfs.base.utils.JsonUtil;
import com.lefanfs.base.web.WebHelper;
import com.lefanfs.backend.dto.SysUserDto;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录拦截器
 * 
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	protected static final String SESSION_ADMIN_DTO = "adminDto";

	/**
	 * 请求处理之前被拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (this.isAccessAllowed(request, response)) {
			return this.onAccessAllowed(request, response);
		} else {
			return this.onAccessDenied(request, response);
		}
	}

	/**
	 * 
	 * 是否允许访问
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Daniel
	 */
	boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response) {
		SysUserDto adminDto = (SysUserDto) WebUtils.getSessionAttribute(request, SESSION_ADMIN_DTO);
		if (adminDto == null) {
			return false;
		}
		return true;
	}

	/**
	 * 允许访问
	 * 
	 * @param request
	 * @param response
	 * @throws java.io.IOException
	 * @author Daniel
	 */
	boolean onAccessAllowed(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return true;
	}

	/**
	 * 访问被拒绝
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author Daniel
	 */
	boolean onAccessDenied(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String loginUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/login";
		if (request.getServerPort() == 80) {
			loginUrl = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/login";
		}
		if (WebHelper.isAjaxRequest(request)) {
			Map<String, Object> retMap = new HashMap<String, Object>();
			retMap.put("needLogin", true);
			retMap.put("loginUrl", loginUrl);
			String json = JsonUtil.objectToJson(retMap);
			WebHelper.outputJson(json, response);
		} else {
			java.io.PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<script>");
			out.println("window.open('" + loginUrl + "','_top');");
			out.println("</script>");
			out.println("</html>");
		}
		return false;
	}

}
