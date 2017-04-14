package com.lefanfs.apicenter.web;

import com.lefanfs.base.constants.Constant;
import com.lefanfs.base.enums.ApiMsgEnum;
import com.lefanfs.base.enums.ApiServerEnum;
import com.lefanfs.base.enums.AppApiMethodEnum;
import com.lefanfs.base.utils.ApiSignUtil;
import com.lefanfs.base.utils.HttpClientUtil;
import com.lefanfs.base.utils.JsonUtil;
import com.lefanfs.base.web.WebHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/apitest")
public class ApiCenterTestController {

	@RequestMapping(value = { "", "/" })
	public String apitest(ModelMap modelMap) {
		modelMap.addAttribute("functionCodeCatalogMap", ApiServerEnum.getApiServerEnumMap());
		modelMap.addAttribute("apiMsgMap", ApiMsgEnum.getAll());
		modelMap.addAttribute("allApiList",AppApiMethodEnum.values());
		return "/apitest";
	}

	/**
	 * 根据类目获取API列表
	 * 
	 * @param catalog
	 * @param rsp
	 * @author Daniel
	 */
	@RequestMapping(value = "/getFunctionListByCatalog")
	public void getFunctionListByCatalog(@RequestParam String catalog, HttpServletResponse rsp) {
		Map<String, String> paramMap = AppApiMethodEnum.getApiMethodMapByServer(catalog);
		String json = JsonUtil.objectToJson(paramMap);
		WebHelper.outputJson(json, rsp);
	}

	/**
	 * 根据API CODE获取参数列表
	 * 
	 * @param functionCode
	 * @param rsp
	 * @author Daniel
	 */
	@RequestMapping(value = "/getParamsByFunctionCode")
	public void getParamsByFunctionCode(@RequestParam String functionCode, HttpServletResponse rsp) {
		Map<String, String> paramMap = AppApiMethodEnum.getApiParamMapByMethod(functionCode);
		String json = JsonUtil.objectToJson(paramMap);
		WebHelper.outputJson(json, rsp);
	}

	@RequestMapping(value = "/doApiTest")
	public String doApiTest(HttpServletRequest req, HttpServletResponse rsp) {
		String apiKey = req.getParameter("apiKey");
		String apiSecret = req.getParameter("apiSecret");
		String functioncode = req.getParameter("functionCodeInput");
		if (functioncode == null || "".equals(functioncode)) {
			functioncode = req.getParameter("functionCodeSelection");
		}
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put(Constant.API_KEY, apiKey);
		paramsMap.put(Constant.API_CODE, functioncode);
		String[] paramNameArr = req.getParameterValues("paramName");
		String[] paramValueArr = req.getParameterValues("paramValue");
		if (paramNameArr != null && paramNameArr.length > 0) {
			int n = 0;
			for (String paramName : paramNameArr) {
				if (paramName != null && !"".equals(paramName.trim())) {
					paramsMap.put(paramName, paramValueArr[n]);
				}
				n++;
			}
		}
		String apiSign = ApiSignUtil.buildSign(paramsMap, apiSecret);
		paramsMap.put(Constant.API_SIGN, apiSign);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put(Constant.DATA, JsonUtil.objectToJson(paramsMap, Map.class));

		StringBuffer defaultApiServer = new StringBuffer();
		defaultApiServer.append(req.getScheme() + "://");
		defaultApiServer.append(req.getServerName());
		if (req.getServerPort() != 80) {
			defaultApiServer.append(":" + req.getServerPort());
		}
		if (!StringUtils.isEmpty(req.getContextPath()) && !"/".equals(req.getContextPath())) {
			defaultApiServer.append(req.getContextPath());
		}
		defaultApiServer.append("/apicenter").append("/").append(functioncode).append("/").append(apiSign);
		String retJson = new HttpClientUtil().doHttpPost(defaultApiServer.toString(), dataMap);
		return WebHelper.outputJson(retJson, rsp);
	}
}
