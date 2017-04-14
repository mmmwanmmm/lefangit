package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.PromotedInfo;

import java.util.List;
import java.util.Map;

public interface PromotedInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PromotedInfo record);

    int insertSelective(PromotedInfo record);

    PromotedInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PromotedInfo record);

    int updateByPrimaryKey(PromotedInfo record);

    List<PromotedInfo> selectPromotedInfoList(Map<String, Object> paramMap);

    PromotedInfo  selectPromotedInfoByParam(Map<String, Object> paramMap);
}