package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    UserInfo selectUserInfoByUserId(Map<String, Object> paramMap);

    List<UserInfo> selectUserInfoByParam(Map<String, Object> paramMap);
}