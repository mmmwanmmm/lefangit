package com.lefanfs.backend.web;

import com.lefanfs.base.dto.ApiFinalResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import com.lefanfs.base.enums.BackendApiMethodEnum;
import com.lefanfs.base.utils.JsonUtil;
import com.google.gson.reflect.TypeToken;
import com.lefanfs.backend.dto.SysUserDto;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员
 * 
 * @author Daniel
 */
@Controller
@RequestMapping(value = "/system/admin")
public class BackendAdminController extends BackendBaseController {

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest req, HttpServletResponse rsp) {
		Map<String, Object> appendMap = new HashMap<String, Object>();
		String json = this.callApi(BackendApiMethodEnum.BACKEND_ADMIN_LIST, appendMap, req);
		Type type = new TypeToken<ApiFinalResponse<List<SysUserDto>>>() {
		}.getType();
		ApiFinalResponse<List<SysUserDto>> apiRsp = JsonUtil.jsonToObject(json, type);
		req.setAttribute("apiRsp", apiRsp);
		return "/system/admin/list";
	}

	@RequestMapping(value = "/create")
	public String create(HttpServletRequest req, HttpServletResponse rsp) {
		return "/system/admin/edit";
	}

	@RequestMapping(value = "/save")
	public String save(HttpServletRequest req, HttpServletResponse rsp) {
		if (!StringUtils.isEmpty(req.getParameter("id"))) {
			return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADMIN_UPDATE, null, req, rsp);
		} else {
			return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADMIN_ADD, null, req, rsp);
		}
	}

	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse rsp) {
		Map<String, Object> appendMap = new HashMap<String, Object>();
		appendMap.put("id", id);
		String json = this.callApi(BackendApiMethodEnum.BACKEND_ADMIN_GETBYID, appendMap, req);
		Type type = new TypeToken<ApiFinalResponse<SysUserDto>>() {
		}.getType();
		ApiFinalResponse<SysUserDto> apiRsp = JsonUtil.jsonToObject(json, type);
		if (apiRsp != null) {
			req.setAttribute("admin", apiRsp.getResults());
		}
		return "/system/admin/edit";
	}

	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest req, HttpServletResponse rsp) {
		return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADMIN_DELETE, null, req, rsp);
	}

	@RequestMapping(value = "/disable")
	public String disable(HttpServletRequest req, HttpServletResponse rsp) {
		return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADMIN_DISABLE, null, req, rsp);
	}

	@RequestMapping(value = "/enable")
	public String enable(HttpServletRequest req, HttpServletResponse rsp) {
		return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADMIN_ENABLE, null, req, rsp);
	}

	@RequestMapping(value = "/editPassword")
	public String editPassword(HttpServletRequest req, HttpServletResponse rsp) {
		return "/system/admin/editPassword";
	}

	@RequestMapping(value = "/updatePassword")
	public String updatePassword(HttpServletRequest req, HttpServletResponse rsp) {
		String old_password = req.getParameter("old_password");
		String new_password = req.getParameter("new_password");
		String confirm_new_password = req.getParameter("confirm_new_password");
		if (StringUtils.isEmpty(old_password) || StringUtils.isEmpty(new_password) || StringUtils.isEmpty(confirm_new_password)) {
			return this.outputApiMsg(BackendApiMethodEnum.BACKEND_ADMIN_UPDATEPASSWORD, ApiMsgEnum.MISS_PARAMETER.getIsSuccess(), ApiMsgEnum.MISS_PARAMETER.getMsg(), rsp);
		}
		if (!new_password.equals(confirm_new_password)) {
			return this.outputApiMsg(BackendApiMethodEnum.BACKEND_ADMIN_UPDATEPASSWORD, ApiMsgEnum.MISS_PARAMETER.getIsSuccess(), ApiMsgEnum.MISS_PARAMETER.getMsg(), rsp);
		}
		Map<String, Object> appendMap = new HashMap<String, Object>();
		appendMap.put("id", this.getSessionAdminId(req));
		return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADMIN_UPDATEPASSWORD, appendMap, req, rsp);
	}
}
