package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.UserScenarios;

import java.util.List;
import java.util.Map;

public interface UserScenariosMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserScenarios record);

    int insertSelective(UserScenarios record);

    UserScenarios selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserScenarios record);

    int updateByPrimaryKey(UserScenarios record);

    List<UserScenarios> selectUserScenariosList(Map<String, Object> paramMap);


    List<UserScenarios> selectUserScenariosByParam(Map<String, Object> paramMap);
}