package com.lefanfs.backend.web.information;

import com.lefanfs.base.dto.ApiFinalResponse;
import com.lefanfs.base.enums.BackendApiMethodEnum;
import com.google.gson.reflect.TypeToken;
import com.lefanfs.backend.dto.CommonInformationDto;
import com.lefanfs.backend.web.BackendBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/1/1.
 */
@Controller
public class InformationAdminController extends BackendBaseController {

    @RequestMapping(value = "/information/informationList")
    public ModelAndView list(HttpServletRequest req){
        String name = req.getParameter("name");
        String type = req.getParameter("type");
        String page = req.getParameter("page");
        if (StringUtils.isEmpty(page)){
            page="1";
        }
        Map param = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonInformationDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.INFO_SEARCH, param, req);
        Map model = new HashMap();
        model.put("infoList",apiFinalResponse.getResults());
        model.put("name",name==null?"":name);
        model.put("type",type==null?"":type);
        model.put("page",Integer.parseInt(page));
        return new ModelAndView("/information/informationList",model);
    }

    @RequestMapping(value = "/information/informationToAdd")
    public ModelAndView toAdd(HttpServletRequest req){

        return new ModelAndView("/information/informationEditOrAddDetail");
    }

    @RequestMapping(value = "/information/informationDoAddOrUpdate")
    public String doAdd(HttpServletRequest req,HttpServletResponse rsp){
        if (StringUtils.isEmpty(req.getParameter("id"))){
            return this.callApiAndOutput(BackendApiMethodEnum.INFO_ADD, null, req, rsp);
        }else {
            return this.callApiAndOutput(BackendApiMethodEnum.INFO_UPDATE, null, req, rsp);
        }
    }

    @RequestMapping(value = "/information/informationToUpdate")
    public ModelAndView toUpdate(HttpServletRequest req){
        String id=req.getParameter("id");
        Map param = new HashMap();
        param.put("id",id);
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CommonInformationDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.INFO_DETAIL, param, req);
        Map model = new HashMap();
        model.put("infoDetail",apiFinalResponse.getResults());
        return new ModelAndView("/information/informationEditOrAddDetail",model);
    }

}
