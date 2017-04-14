package com.lefanfs.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefanfs.backend.dto.CompanyInfoDto;
import com.lefanfs.backend.dto.UserScenariosDto;
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
 * Created by Jani on 2017/3/14.
 */
@Controller
public class BackendUserScenariosController extends BackendBaseController {
    @RequestMapping(value = "/scenarios/scenariosInfoList")
    public ModelAndView scenariosInfoList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<UserScenariosDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_USER_SCENARIOS_LIST, null, req);
        List<UserScenariosDto> userScenariosDtoList = (List<UserScenariosDto>) apiFinalResponse.getResults();
        Map model = new HashMap();
        model.put("userScenariosDtoList",userScenariosDtoList);
        model.put("page",page);
        return new ModelAndView("/scenarios/scenariosInfoList",model);
    }

    @RequestMapping(value = "/scenarios/scenariosInfoToAdd")
    public ModelAndView toAdd(HttpServletRequest req){

        return new ModelAndView("/scenarios/scenariosInfoEditOrAddDetail");
    }

    @RequestMapping(value = "/scenarios/scenariosInfoAddOrUpdate")
    public String scenariosInfoAddOrUpdate(HttpServletRequest req, HttpServletResponse rsp) {
        if (org.springframework.util.StringUtils.isEmpty(req.getParameter("id"))){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADD_USER_SCENARIOS, null, req, rsp);
        }else {
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_UPDATE_USER_SCENARIOS, null, req, rsp);
        }
    }

    @RequestMapping(value = "/scenarios/scenariosInfoToUpdate")
    public ModelAndView toUpdate(HttpServletRequest req){
        String id=req.getParameter("id");
        Map param = new HashMap();
        param.put("id",id);
        TypeToken typeToken = new TypeToken<ApiFinalResponse<UserScenariosDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SELECT_USER_SCENARIOS, param, req);
        Map model = new HashMap();
        model.put("infoDetail",apiFinalResponse.getResults());
        return new ModelAndView("/scenarios/scenariosInfoEditOrAddDetail",model);
    }

    @RequestMapping(value = "/scenarios/scenariosInfoTodelete")
    public String todelete(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_DELETE_USER_SCENARIOS, null, req, rsp);
    }
}
