package com.lefanfs.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefanfs.backend.dto.LoanApplicationDto;
import com.lefanfs.base.dto.ApiFinalResponse;
import com.lefanfs.base.enums.BackendApiMethodEnum;
import com.lefanfs.base.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jani on 2017/3/10.
 */
@Controller
public class BackendLoanApplicationController extends BackendBaseController  {


    @RequestMapping(value = "/loan/loanApplicationList")
    public ModelAndView loanApplicationList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String userId = req.getParameter("userId");
        String state = req.getParameter("state");
        String userPhone = req.getParameter("userPhone");
        String isTrafficAccident = req.getParameter("isTrafficAccident");
        String page = req.getParameter("page");
        if (StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<LoanApplicationDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_LOAN_APPLICATION_LIST, null, req);
        List<LoanApplicationDto> loanApplicationList = (List<LoanApplicationDto>) apiFinalResponse.getResults();
        Map model = new HashMap();
        model.put("loanApplicationList",loanApplicationList);
        model.put("userId",userId==null?"":userId);
        model.put("state",state==null?"":state);
        model.put("userPhone",userPhone==null?"":userPhone);
        model.put("isTrafficAccident",isTrafficAccident==null?"":isTrafficAccident);
        model.put("page",page);
        return new ModelAndView("/loan/loanApplicationList",model);
    }

    @RequestMapping(value = "/loan/loanApplicationTodelete")
    public String todelete(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_DELETE_LOAN_APPLICATION, null, req, rsp);
    }

    @RequestMapping(value = "/loan/loanApplicationToUpdate")
    public String toUpdate(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_UPDATE_LOAN_APPLICATION, null, req, rsp);
    }
}
