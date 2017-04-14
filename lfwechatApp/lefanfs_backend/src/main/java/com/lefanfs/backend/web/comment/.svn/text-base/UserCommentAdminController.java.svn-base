package com.lefanfs.backend.web.comment;

import com.lefanfs.base.dto.ApiFinalResponse;
import com.lefanfs.base.enums.BackendApiMethodEnum;
import com.google.gson.reflect.TypeToken;
import com.lefanfs.backend.dto.UserCommentDTO;
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
 * Created by Thinkpad on 2017/1/4.
 */
@Controller
public class UserCommentAdminController extends BackendBaseController {

    @RequestMapping(value = "/comment/search")
    public ModelAndView commentList(HttpServletRequest req, HttpServletResponse rsp){
        String comment = req.getParameter("comment");
        String commentTo = req.getParameter("commentTo");
        String page = req.getParameter("page");
        if (StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<UserCommentDTO>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.COMMENT_SEARCH, null, req);
        List<UserCommentDTO> commentList = (List<UserCommentDTO>) apiFinalResponse.getResults();
        Map model = new HashMap();
        model.put("commentList",commentList);
        model.put("comment",comment==null?"":comment);
        model.put("commentTo",commentTo==null?"":commentTo);
        model.put("page",page);
        return new ModelAndView("/comment/commentList",model);
    }
    @RequestMapping(value = "/comment/detail")
    public ModelAndView commentDetail(HttpServletRequest req, HttpServletResponse rsp){
        TypeToken typeToken = new TypeToken<ApiFinalResponse<UserCommentDTO>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.COMMENT_DETAIL, null, req);
        UserCommentDTO comment = (UserCommentDTO) apiFinalResponse.getResults();
        Map model = new HashMap();
        model.put("comment",comment);
        return new ModelAndView("/comment/commentDetail",model);
    }
    @RequestMapping(value = "/comment/reply")
    public String commentAddReply(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.COMMENT_ADDREPLY, null, req, rsp);
    }
    @RequestMapping(value = "/comment/delete")
    public String commentDelete(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.COMMENT_DELETE, null, req, rsp);
    }


}
