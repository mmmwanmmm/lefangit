package com.lefanfs.backend.web;

import com.lefanfs.backend.dto.UserInfoDto;
import com.lefanfs.base.dto.ApiFinalResponse;
import com.lefanfs.base.enums.BackendApiMethodEnum;
import com.lefanfs.base.utils.JsonUtil;
import com.google.gson.reflect.TypeToken;
import com.lefanfs.backend.dto.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 * 
 * @author Daniel
 */
@Controller
@RequestMapping("/user")
public class BackendUserController extends BackendBaseController {

	@RequestMapping(value = "/userList")
	public String userList(HttpServletRequest req, HttpServletResponse rsp) {
		Map<String, Object> appendMap = new HashMap<String, Object>();
		String json = this.callApi(BackendApiMethodEnum.BACKEND_USER_LIST, appendMap, req);
		Type type = new TypeToken<ApiFinalResponse<List<UserInfoDto>>>() {
		}.getType();
		ApiFinalResponse<List<UserInfo>> apiRsp = JsonUtil.jsonToObject(json, type);
		req.setAttribute("apiRsp", apiRsp);
		return "/user/userList";
	}

	@RequestMapping(value = "/viewUser/{userId}")
	public String viewUser(@PathVariable Long userId, HttpServletRequest req, HttpServletResponse rsp) {
		Map<String, Object> appendMap = new HashMap<String, Object>();
		appendMap.put("userId", userId);
		String json = this.callApi(BackendApiMethodEnum.BACKEND_USER_GETBYID, appendMap, req);
		Type type = new TypeToken<ApiFinalResponse<UserInfo>>() {
		}.getType();
		ApiFinalResponse<UserInfo> apiRsp = JsonUtil.jsonToObject(json, type);
		if (apiRsp != null) {
			req.setAttribute("user", apiRsp.getResults());
		}
		return "/user/viewUser";
	}

	@RequestMapping(value = "/changeStatus")
	public String changeStatus(HttpServletRequest req, HttpServletResponse rsp) {
		return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_USER_CHANGESTATUS, null, req, rsp);
	}
}
