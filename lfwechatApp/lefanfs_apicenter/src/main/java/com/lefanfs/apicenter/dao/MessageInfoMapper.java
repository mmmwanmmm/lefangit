package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.MessageInfo;

import java.util.List;
import java.util.Map;

public interface MessageInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageInfo record);

    int insertSelective(MessageInfo record);

    MessageInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageInfo record);

    int updateByPrimaryKey(MessageInfo record);

    List<MessageInfo> selectMessageInfoList(Map<String, Object> paramMap);
}