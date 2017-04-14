package com.lefanfs.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefanfs.backend.dto.CompanyInfoDto;
import com.lefanfs.backend.dto.QaInfoDto;
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
 * Created by Jani on 2017/3/22.
 */
@Controller
public class BackendQainfoController extends BackendBaseController  {


    @RequestMapping(value = "/qaInfo/qaInfoList")
    public ModelAndView qaInfoList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<QaInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SELECT_QAINFO_LIST, null, req);
        List<QaInfoDto> qaInfoList = (List<QaInfoDto>) apiFinalResponse.getResults();
        Map model = new HashMap();
        model.put("qaInfoList",qaInfoList);
        model.put("page",page);
        return new ModelAndView("/qaInfo/qaInfoList",model);
    }

    @RequestMapping(value = "/qaInfo/qaInfoToAdd")
    public ModelAndView toAdd(HttpServletRequest req){

        return new ModelAndView("/qaInfo/qaInfoEditOrAddDetail");
    }

    @RequestMapping(value = "/qaInfo/qaInfoAddOrUpdate")
    public String qainfoAddOrUpdate(HttpServletRequest req, HttpServletResponse rsp) {
        if (org.springframework.util.StringUtils.isEmpty(req.getParameter("id"))){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADD_QAINFO, null, req, rsp);
        }else {
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_UPDATE_QAINFO, null, req, rsp);
        }
    }

    @RequestMapping(value = "/qaInfo/qaInfoToUpdate")
    public ModelAndView toUpdate(HttpServletRequest req){
        String id=req.getParameter("id");
        Map param = new HashMap();
        param.put("id",id);
        TypeToken typeToken = new TypeToken<ApiFinalResponse<QaInfoDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INDETAILS_QAINFO, param, req);
        Map model = new HashMap();
        model.put("infoDetail",apiFinalResponse.getResults());
        return new ModelAndView("/qaInfo/qaInfoEditOrAddDetail",model);
    }

    @RequestMapping(value = "/qaInfo/qaInfoTodelete")
    public String todelete(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_DELETE_QAINFO, null, req, rsp);
    }
}
