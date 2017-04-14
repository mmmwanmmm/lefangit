package com.lefanfs.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefanfs.backend.dto.PromotedInfoDto;
import com.lefanfs.backend.dto.UserPromotedDto;
import com.lefanfs.base.dto.ApiFinalResponse;
import com.lefanfs.base.enums.BackendApiMethodEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jani on 2017/3/15.
 */
@Controller
public class BackendUserPromotedController  extends BackendBaseController {

    @RequestMapping(value = "/promoted/userPromotedInfoList")
    public ModelAndView userPromotedInfoList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<UserPromotedDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_USER_PROMOTED_LIST, null, req);
        List<UserPromotedDto> userPromotedDtoList = (List<UserPromotedDto>) apiFinalResponse.getResults();
        Map model = new HashMap();
        model.put("userPromotedDtoList",userPromotedDtoList);
        model.put("page",page);
        return new ModelAndView("/promoted/userPromotedInfoList",model);
    }

    @RequestMapping(value = "/promoted/userPromotedInfoAddOrUpdate")
    public String userPromotedInfoAddOrUpdate(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_UPDATE_USER_PROMOTED, null, req, rsp);
    }

    @RequestMapping(value = "/promoted/promotedInfoList")
    public ModelAndView promotedInfoList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String userName = req.getParameter("userName");
        String userId = req.getParameter("userId");
        String page = req.getParameter("page");
        if (StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<PromotedInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND__PROMOTED_INFO_LIST, null, req);
        List<PromotedInfoDto> promotedInfoDtoList = (List<PromotedInfoDto>) apiFinalResponse.getResults();
        Map model = new HashMap();
        model.put("promotedInfoList",promotedInfoDtoList);
        model.put("userName",userName);
        model.put("userId",userId);
        model.put("page",page);
        return new ModelAndView("/promoted/promotedInfoList",model);
    }

    @RequestMapping(value = "/promoted/promotedInfoToReject")
    public ModelAndView promotedInfoToReject(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String id = req.getParameter("id");
        Map model = new HashMap();
        model.put("id",id);
        return new ModelAndView("/promoted/userPromotedReject",model);
    }
    @RequestMapping(value = "/promoted/promotedInfoToAcount")
    public ModelAndView promotedInfoToAcount(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String id = req.getParameter("id");
        Map model = new HashMap();
        model.put("id",id);
        return new ModelAndView("/promoted/promotedInfoAccount",model);
    }

    @RequestMapping(value = "/promoted/promotedInfoAddOrUpdate")
    public String promotedInfoAddOrUpdate(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_UPDATE_PROMOTED_INFO, null, req, rsp);
    }
}
