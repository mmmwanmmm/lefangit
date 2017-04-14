package com.lefanfs.apicenter.service.impl;

import com.lefanfs.apicenter.dao.UserScenariosMapper;
import com.lefanfs.apicenter.dto.AdListDto;
import com.lefanfs.apicenter.model.UserScenarios;
import com.lefanfs.apicenter.service.UserScenariosService;
import com.lefanfs.base.dto.ApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jani on 2017/3/8.
 */
@Service
public class UserScenariosServiceImpl extends BaseServiceImpl implements UserScenariosService {

    @Autowired
    private UserScenariosMapper userScenariosMapper;

    @Override
    public List<UserScenarios> selectUserScenariosList(int limit) {
        ApiRequest apiReq = new ApiRequest();
        this.setPageIndex(apiReq);
        List<UserScenarios> list = userScenariosMapper.selectUserScenariosList(apiReq);
        return list;
    }
}
