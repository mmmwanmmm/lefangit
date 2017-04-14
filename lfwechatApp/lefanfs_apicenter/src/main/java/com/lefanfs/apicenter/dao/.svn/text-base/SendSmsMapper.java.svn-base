package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.SendSms;

import java.util.HashMap;

public interface SendSmsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SendSms record);

    int insertSelective(SendSms record);

    SendSms selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SendSms record);

    int updateByPrimaryKey(SendSms record);


    /**
     * 查询短信验证信息
     * @param map
     * @return
     */
    SendSms selectSendSMS(HashMap<String,Object> map);

    /**
     * 将验证失效
     * @param map
     * @return
     */
    int deleteSendSMSByParam(HashMap<String,Object> map);
}