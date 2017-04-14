package com.lefanfs.backend.interceptors;

import com.lefanfs.base.utils.JsonUtil;
import com.lefanfs.base.web.WebHelper;
import com.lefanfs.backend.dto.SysUserDto;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 权限拦截器
 * 
 * @author Daniel
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

	protected static final String SESSION_ADMIN_DTO = "adminDto";

	protected static final String SESSION_MENULIST_DTO = "menuListDto";

	protected static final String SESSION_MENU_MAP = "menuMap";

	/**
	 * 请求处理之前被拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (this.hasAuthority(request, response, handler)) {
			return this.onAccessAllowed(request, response);
		} else {
			return this.onAccessDenied(request, response);
		}
	}

	/**
	 * 
	 * 是否 有权限 访问
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Daniel
	 */
	@SuppressWarnings("unchecked")
	boolean hasAuthority(HttpServletRequest request, HttpServletResponse response, Object handler) {
		SysUserDto adminDto = (SysUserDto) WebUtils.getSessionAttribute(request, SESSION_ADMIN_DTO);
		if (adminDto == null) {
			return false;
		}
		// root用户拥有超级权限
		if ("root".equalsIgnoreCase(adminDto.getUsername())) {
			return true;
		}
		HandlerMethod hMethod = (HandlerMethod) handler;
		String fun_code = hMethod.getBean().getClass().getName() + "." + hMethod.getMethod().getName();
		Map<String, Integer> menuCodeMap = (Map<String, Integer>) WebUtils.getSessionAttribute(request, SESSION_MENU_MAP);
		return menuCodeMap != null && menuCodeMap.containsKey(fun_code);
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
	 * 无权限 访问
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author Daniel
	 */
	boolean onAccessDenied(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String authorityUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/noAuthority";
		if (request.getServerPort() == 80) {
			authorityUrl = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/noAuthority";
		}
		if (WebHelper.isAjaxRequest(request)) {
			Map<String, Object> retMap = new HashMap<String, Object>();
			retMap.put("noAuthority", true);
			retMap.put("authorityUrl", authorityUrl);
			String json = JsonUtil.objectToJson(retMap);
			WebHelper.outputJson(json, response);
		} else {
			java.io.PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<script>");
			out.println("window.location.href='" + authorityUrl + "'");
			out.println("</script>");
			out.println("</html>");
		}
		return false;
	}
}
