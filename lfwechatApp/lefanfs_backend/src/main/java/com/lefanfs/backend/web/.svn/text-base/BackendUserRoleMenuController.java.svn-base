package com.lefanfs.backend.web;

import com.lefanfs.base.dto.ApiFinalResponse;
import com.lefanfs.base.enums.BackendApiMethodEnum;
import com.lefanfs.base.utils.JsonUtil;
import com.lefanfs.base.web.WebHelper;
import com.google.gson.reflect.TypeToken;
import com.lefanfs.backend.dto.*;
import org.apache.commons.collections.CollectionUtils;
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
 * 
 * 系统用户角色，菜单
 * 
 * @author hey
 */
@Controller
public class BackendUserRoleMenuController extends BackendBaseController {

	@RequestMapping(value = "/system/admin/roleEdit/{id}")
	public String adminRoleEdit(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse rsp) {
		req.setAttribute("admin", _getAuthUser(id));
		req.setAttribute("apiRsp", _getALLSysRoleDto());
		req.setAttribute("userRoleIds", _getStrUserRole(id));
		return "/system/admin/editRole";
	}

	@RequestMapping(value = "/system/admin/roleSave")
	public String adminRoleSave(HttpServletRequest req, HttpServletResponse rsp) {
		return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SYSUSERROLE_UPDATE, null, req, rsp);
	}

	/**
	 * 角色列表
	 */
	@RequestMapping(value = "/system/role/list")
	public String roleList(HttpServletRequest req, HttpServletResponse rsp) {
		req.setAttribute("apiRsp", _getALLSysRoleDto());
		return "/system/role/list";
	}

	@RequestMapping(value = "/system/role/edit")
	public String roleEdit(HttpServletRequest req, HttpServletResponse rsp) {
		if (!StringUtils.isEmpty(req.getParameter("roleId"))) {
			Map<String, Object> appendMap = new HashMap<String, Object>();
			appendMap.put("id", req.getParameter("roleId"));
			String json = this.callApi(BackendApiMethodEnum.BACKEND_SYSROLE_GETBYID, appendMap, null);
			Type type = new TypeToken<ApiFinalResponse<SysRoleDto>>() {
			}.getType();
			ApiFinalResponse<SysRoleDto> apiRsp = JsonUtil.jsonToObject(json, type);
			if (apiRsp != null) {
				req.setAttribute("roleDto", apiRsp.getResults());
			}
		}
		return "/system/role/edit";
	}

	@RequestMapping(value = "/system/role/save")
	public String roleSave(HttpServletRequest req, HttpServletResponse rsp) {
		if (StringUtils.isEmpty(req.getParameter("id"))) {
			return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SYSROLE_ADD, null, req, rsp);
		} else {
			return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SYSROLE_UPDATE, null, req, rsp);
		}
	}

	@RequestMapping(value = "/system/role/delete")
	public String roleDelete(HttpServletRequest req, HttpServletResponse rsp) {
		return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SYSROLE_DELETE, null, req, rsp);
	}

	/**
	 * 菜单列表
	 */
	@RequestMapping(value = "/system/menu/list")
	public String menuList(HttpServletRequest req, HttpServletResponse rsp) {
		return "/system/menu/list";
	}

	@RequestMapping(value = "/system/menu/treeData")
	public String treeData(HttpServletRequest req, HttpServletResponse rsp) {
		Map<String, Object> appendMap = new HashMap<String, Object>();
		String json = this.callApi(BackendApiMethodEnum.BACKEND_SYSMENU_TREELIST, appendMap, null);
		String retJson = "";
		Type type = new TypeToken<ApiFinalResponse<List<TreeData>>>() {
		}.getType();
		ApiFinalResponse<List<TreeData>> apiRsp = JsonUtil.jsonToObject(json, type);
		if (apiRsp != null) {
			List<TreeData> tree = apiRsp.getResults();
			retJson = JsonUtil.objectToJson(tree);
		}
		return WebHelper.outputJson(retJson, rsp);
	}

	@RequestMapping(value = "/system/menu/byRole")
	public String menuByRole(HttpServletRequest req, HttpServletResponse rsp) {
		Map<String, Object> appendMap = new HashMap<String, Object>();
		appendMap.put("justNeedResult", true);
		appendMap.put("roleId", req.getParameter("roleId"));
		String json = this.callApi(BackendApiMethodEnum.BACKEND_SYSROLEMENU_LIST, appendMap, null);
		String retJson = "";
		Type type = new TypeToken<ApiFinalResponse<List<SysRoleMenuDto>>>() {
		}.getType();
		ApiFinalResponse<List<SysRoleMenuDto>> apiRsp = JsonUtil.jsonToObject(json, type);
		if (apiRsp != null) {
			List<SysRoleMenuDto> tree = apiRsp.getResults();
			retJson = JsonUtil.objectToJson(tree);
		}
		return WebHelper.outputJson(retJson, rsp);
	}

	@RequestMapping(value = "/system/menu/edit")
	public String menuEdit(HttpServletRequest req, HttpServletResponse rsp) {
		if (!StringUtils.isEmpty(req.getParameter("id"))) {
			Map<String, Object> appendMap = new HashMap<String, Object>();
			appendMap.put("id", req.getParameter("id"));
			String json = this.callApi(BackendApiMethodEnum.BACKEND_SYSMENU_GETBYID, appendMap, null);
			Type type = new TypeToken<ApiFinalResponse<SysMenuDto>>() {
			}.getType();
			ApiFinalResponse<SysMenuDto> apiRsp = JsonUtil.jsonToObject(json, type);
			if (apiRsp != null) {
				req.setAttribute("menuDto", apiRsp.getResults());
			}
		}
		WebHelper.setRequestAttributesFromRequestParam(req);
		return "/system/menu/edit";
	}

	@RequestMapping(value = "/system/menu/save")
	public String menuSave(HttpServletRequest req, HttpServletResponse rsp) {
		if (StringUtils.isEmpty(req.getParameter("id"))) {
			return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SYSMENU_ADD, null, req, rsp);
		} else {
			return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SYSMENU_UPDATE, null, req, rsp);
		}
	}

	@RequestMapping(value = "/system/menu/delete")
	public String menuDelete(HttpServletRequest req, HttpServletResponse rsp) {
		return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SYSMENU_DELETE, null, req, rsp);
	}

	public SysUserDto _getAuthUser(Integer id) {
		Map<String, Object> appendMap = new HashMap<String, Object>();
		appendMap.put("id", id);
		String json = this.callApi(BackendApiMethodEnum.BACKEND_ADMIN_GETBYID, appendMap, null);
		Type type = new TypeToken<ApiFinalResponse<SysUserDto>>() {
		}.getType();
		ApiFinalResponse<SysUserDto> apiRsp = JsonUtil.jsonToObject(json, type);
		if (apiRsp != null) {
			return apiRsp.getResults();
		}
		return null;
	}

	public ApiFinalResponse<List<SysRoleDto>> _getALLSysRoleDto() {
		Map<String, Object> appendMap = new HashMap<String, Object>();
		String json = this.callApi(BackendApiMethodEnum.BACKEND_SYSROLE_LIST, appendMap, null);
		Type type = new TypeToken<ApiFinalResponse<List<SysRoleDto>>>() {
		}.getType();
		ApiFinalResponse<List<SysRoleDto>> apiRsp = JsonUtil.jsonToObject(json, type);
		return apiRsp;
	}

	public String _getStrUserRole(Integer userId) {
		List<SysUserRoleDto> roleDtoList = null;
		Map<String, Object> appendMap = new HashMap<String, Object>();
		appendMap.put("userId", userId);
		String json = this.callApi(BackendApiMethodEnum.BACKEND_SYSUSERROLE_LIST, appendMap, null);
		Type type = new TypeToken<ApiFinalResponse<List<SysUserRoleDto>>>() {
		}.getType();
		ApiFinalResponse<List<SysUserRoleDto>> apiRsp = JsonUtil.jsonToObject(json, type);
		if (apiRsp != null) {
			roleDtoList = apiRsp.getResults();
		}
		StringBuffer roleIdstr = new StringBuffer(",");
		if (!CollectionUtils.isEmpty(roleDtoList)) {
			for (SysUserRoleDto r : roleDtoList) {
				roleIdstr.append(r.getRoleId()).append(",");
			}
		}
		return roleIdstr.toString();
	}

}
