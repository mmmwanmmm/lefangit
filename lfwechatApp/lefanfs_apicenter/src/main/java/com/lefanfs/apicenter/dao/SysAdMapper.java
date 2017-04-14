package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.SysAd;

public interface SysAdMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysAd record);

    int insertSelective(SysAd record);

    SysAd selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysAd record);

    int updateByPrimaryKey(SysAd record);
}