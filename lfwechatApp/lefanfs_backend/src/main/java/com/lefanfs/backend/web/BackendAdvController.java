package com.lefanfs.backend.web;

import com.lefanfs.base.dto.ApiFinalResponse;
import com.lefanfs.base.enums.BackendApiMethodEnum;
import com.lefanfs.base.utils.JsonUtil;
import com.google.gson.reflect.TypeToken;
import com.lefanfs.backend.dto.SysAdInfo;
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
 * 广告管理
 * 
 * @author Daniel
 */
@Controller
@RequestMapping("/adv")
public class BackendAdvController extends BackendBaseController {

	@RequestMapping(value = "/advList")
	public String advList(HttpServletRequest req, HttpServletResponse rsp) {
		Map<String, Object> appendMap = new HashMap<String, Object>();
		String json = this.callApi(BackendApiMethodEnum.BACKEND_ADV_LIST, appendMap, req);
		Type type = new TypeToken<ApiFinalResponse<List<SysAdInfo>>>() {
		}.getType();
		ApiFinalResponse<List<SysAdInfo>> apiRsp = JsonUtil.jsonToObject(json, type);
		req.setAttribute("apiRsp", apiRsp);
		return "/adv/advList";
	}

	@RequestMapping(value = "/createAdv")
	public String createAdv(HttpServletRequest req, HttpServletResponse rsp) {
		return "/adv/editAdv";
	}

	@RequestMapping(value = "/editAdv/{advId}")
	public String editAdv(@PathVariable Long advId, HttpServletRequest req, HttpServletResponse rsp) {
		Map<String, Object> appendMap = new HashMap<String, Object>();
		appendMap.put("advId", advId);
		String json = this.callApi(BackendApiMethodEnum.BACKEND_ADV_GETBYID, appendMap, req);
		Type type = new TypeToken<ApiFinalResponse<SysAdInfo>>() {
		}.getType();
		ApiFinalResponse<SysAdInfo> apiRsp = JsonUtil.jsonToObject(json, type);
		if (apiRsp != null) {
			req.setAttribute("adv", apiRsp.getResults());
		}
		return "/adv/editAdv";
	}

	@RequestMapping(value = "/saveAdv")
	public String saveAdv(HttpServletRequest req, HttpServletResponse rsp) {
		if (!StringUtils.isEmpty(req.getParameter("advId"))) {
			return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADV_UPDATE, null, req, rsp);
		} else {
			Map<String, Object> appendMap = new HashMap<String, Object>();
			return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADV_SAVE, appendMap, req, rsp);
		}
	}

	@RequestMapping(value = "/changeStatus")
	public String changeStatus(HttpServletRequest req, HttpServletResponse rsp) {
		return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADV_CHANGESTATUS, null, req, rsp);
	}
}
