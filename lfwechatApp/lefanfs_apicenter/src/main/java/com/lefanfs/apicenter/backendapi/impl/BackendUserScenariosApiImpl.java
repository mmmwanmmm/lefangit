package com.lefanfs.apicenter.backendapi.impl;

import com.lefanfs.apicenter.backendapi.BackendUserScenariosApi;
import com.lefanfs.apicenter.dao.UserScenariosMapper;
import com.lefanfs.apicenter.model.LoanApplication;
import com.lefanfs.apicenter.model.UserScenarios;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiParam;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jani on 2017/3/14.
 */
@Service
@ApiService(descript = "后台案例管理相关API")
public class BackendUserScenariosApiImpl extends BaseServiceImpl implements BackendUserScenariosApi {

    @Autowired
    private UserScenariosMapper userScenariosMapper;

    /**
     * 用户贷款申请列表
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "用户案例列表", value = "backend-user-scenarios-list", apiParams = { })
    @Override
    public ApiResponse<List<UserScenarios>> getBkUserScenariosList(ApiRequest apiReq) {
        Integer page = apiReq.getInt("page");
        int pageSize = 10;
        if (page==null || page<1){
            page = 1;
        }
        page =(page - 1) * pageSize;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageIndex",page);
        paramMap.put("pageSize",pageSize);
        List<UserScenarios> userScenarioses=userScenariosMapper.selectUserScenariosByParam(paramMap);
        return new ApiResponse(ApiMsgEnum.SUCCESS,userScenarioses==null?0:userScenarioses.size(),userScenarioses);
    }

    /**
     * 新增公司服务信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "新增用户案例信息", value = "backend-add-userScenarios", apiParams = {@ApiParam(descript = "标题(*)", name = "title"), @ApiParam(descript = "内容(*)", name = "content")
            , @ApiParam(descript = "案例图(*)", name = "img")})
    @Override
    public ApiResponse<UserScenarios> addBkUserScenariosList(ApiRequest apiReq) {
        UserScenarios userScenarios = new UserScenarios();
        String title = apiReq.getString("title");
        String content = apiReq.getString("content");
        String img = apiReq.getString("img");
        userScenarios.setTitle(title);
        userScenarios.setContent(content);
        userScenarios.setImg(img);
        userScenarios.setCreateTime(new Date());
        userScenarios.setModifyTime(new Date());
        userScenarios.setDeleteFlag(0);
        int result = userScenariosMapper.insertSelective(userScenarios);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,userScenarios);
    }

    /**
     * 贷款申请状态修改
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "用户案例修改", value = "backend-update-user-scenarios", apiParams = { @ApiParam(descript = "案例ID", name = "id"),
            @ApiParam(descript = "标题", name = "title"),  @ApiParam(descript = "内容", name = "content"),  @ApiParam(descript = "案例图", name = "img")})
    @Override
    public ApiResponse<UserScenarios> updateBkUserScenariosById(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        String title = apiReq.getString("title");
        String content = apiReq.getString("content");
        String img = apiReq.getString("img");
        UserScenarios userScenarios=new UserScenarios();
        userScenarios.setId(id);
        userScenarios.setTitle(title);
        userScenarios.setContent(content);
        userScenarios.setImg(img);
        int result = this.userScenariosMapper.updateByPrimaryKeySelective(userScenarios);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }
    /**
     * 用户案例删除
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "用户案例删除", value = "backend-delete-user-scenarios", apiParams = { @ApiParam(descript = "贷款申请ID", name = "id")})
    @Override
    public ApiResponse<UserScenarios> deleteBkUserScenariosById(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        UserScenarios userScenarios=new UserScenarios();
        userScenarios.setId(id);
        userScenarios.setDeleteFlag(1);
        int result = this.userScenariosMapper.updateByPrimaryKeySelective(userScenarios);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    /**
     * 用户案例详情
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "用户案例详情", value = "backend-select-user-scenarios", apiParams = { @ApiParam(descript = "贷款申请ID", name = "id")})
    @Override
    public ApiResponse<UserScenarios> selectBkUserScenariosById(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        UserScenarios userScenarios = this.userScenariosMapper.selectByPrimaryKey(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,userScenarios);
    }
}
