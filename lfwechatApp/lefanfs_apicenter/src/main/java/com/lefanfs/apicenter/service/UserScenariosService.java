package com.lefanfs.apicenter.service;

import com.lefanfs.apicenter.model.UserScenarios;

import java.util.List;

/**
 * Created by Jani on 2017/3/8.
 */
public interface UserScenariosService {
    List<UserScenarios> selectUserScenariosList(int limit);
}
