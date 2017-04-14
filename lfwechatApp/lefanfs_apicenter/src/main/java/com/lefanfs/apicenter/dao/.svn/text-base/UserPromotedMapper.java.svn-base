package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.UserPromoted;

import java.util.List;
import java.util.Map;

public interface UserPromotedMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserPromoted record);

    int insertSelective(UserPromoted record);

    UserPromoted selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPromoted record);

    int updateByPrimaryKey(UserPromoted record);

    UserPromoted selectUserPromotedByUserId(Map<String, Object> paramMap);

    List<UserPromoted> selectUserPromotedByParam(Map<String, Object> paramMap);
}