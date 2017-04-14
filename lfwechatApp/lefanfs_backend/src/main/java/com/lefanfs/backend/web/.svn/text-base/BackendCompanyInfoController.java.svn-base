package com.lefanfs.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefanfs.backend.dto.CompanyInfoDto;
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
 * Created by Jani on 2017/3/13.
 */
@Controller
public class BackendCompanyInfoController extends BackendBaseController  {
    @RequestMapping(value = "/company/companyInfoList")
    public ModelAndView companyInfoList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CompanyInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_COMPANYINFO_LIST, null, req);
        List<CompanyInfoDto> companyInfoDtoList = (List<CompanyInfoDto>) apiFinalResponse.getResults();
        Map model = new HashMap();
        model.put("companyInfoDtoList",companyInfoDtoList);
        model.put("page",page);
        return new ModelAndView("/company/companyInfoList",model);
    }

    @RequestMapping(value = "/company/companyInfoToAdd")
    public ModelAndView toAdd(HttpServletRequest req){

        return new ModelAndView("/company/companyInfoEditOrAddDetail");
    }

    @RequestMapping(value = "/company/companyInfoAddOrUpdate")
    public String companyInfoAddOrUpdate(HttpServletRequest req, HttpServletResponse rsp) {
        if (org.springframework.util.StringUtils.isEmpty(req.getParameter("id"))){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADD_COMPANYINFO, null, req, rsp);
        }else {
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_UPDATE_COMPANYINFO, null, req, rsp);
        }
    }

    @RequestMapping(value = "/company/companyInfoToUpdate")
    public ModelAndView toUpdate(HttpServletRequest req){
        String id=req.getParameter("id");
        Map param = new HashMap();
        param.put("id",id);
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CompanyInfoDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INDETAILS_COMPANYINFO, param, req);
        Map model = new HashMap();
        model.put("infoDetail",apiFinalResponse.getResults());
        return new ModelAndView("/company/companyInfoEditOrAddDetail",model);
    }

    @RequestMapping(value = "/company/companyInfoTodelete")
    public String todelete(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_DELETE_COMPANYINFO, null, req, rsp);
    }
}
