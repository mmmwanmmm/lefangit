package com.lefanfs.apicenter.appapi.impl;

import com.lefanfs.apicenter.appapi.UserScenariosApi;
import com.lefanfs.apicenter.dao.UserScenariosMapper;
import com.lefanfs.apicenter.dto.AdListDto;
import com.lefanfs.apicenter.model.UserInfo;
import com.lefanfs.apicenter.model.UserScenarios;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import com.lefanfs.apicenter.service.impl.UserScenariosServiceImpl;
import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiParam;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jani on 2017/3/7.
 */
@Service
@ApiService(descript = "用户案例相关API")
public class UserScenariosApiImpl extends BaseServiceImpl  implements UserScenariosApi {


    @Autowired
    private UserScenariosServiceImpl userScenariosServiceImpl;

    @Autowired
    private UserScenariosMapper userScenariosMapper;
    /**
     * 用户案例列表
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "用户案例列表", value = "scenarios-list")
    @Override
    public ApiResponse<List<UserScenarios>> getUserScenariosList(ApiRequest apiReq) {
        this.setPageIndex(apiReq);
        List<UserScenarios> list = userScenariosServiceImpl.selectUserScenariosList(this.getAppPageSize(apiReq));
        return new ApiResponse<List<UserScenarios>>(ApiMsgEnum.SUCCESS, (list == null ? 0 : list.size()), list);
    }

    /**
     * 用户案例详情
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "用户案例详情", value = "scenarios-getScenariosById", apiParams = { @ApiParam(descript = "用户案例ID(*)", name = "id") })
    @Override
    public ApiResponse selectUserScenariosById(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        Map retMap = new HashMap();
        UserScenarios userScenarios = userScenariosMapper.selectByPrimaryKey(id);
        retMap.put("userScenarios",userScenarios);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,retMap);
    }
}
