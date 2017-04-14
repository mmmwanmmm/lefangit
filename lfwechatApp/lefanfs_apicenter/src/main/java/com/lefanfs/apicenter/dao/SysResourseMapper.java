package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.SysResourse;

public interface SysResourseMapper {
    int deleteByPrimaryKey(Long resourceId);

    int insert(SysResourse record);

    int insertSelective(SysResourse record);

    SysResourse selectByPrimaryKey(Long resourceId);

    int updateByPrimaryKeySelective(SysResourse record);

    int updateByPrimaryKey(SysResourse record);
}