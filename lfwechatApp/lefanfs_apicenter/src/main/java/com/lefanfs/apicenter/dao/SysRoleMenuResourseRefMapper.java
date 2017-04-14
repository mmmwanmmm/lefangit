package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.SysRoleMenuResourseRef;

public interface SysRoleMenuResourseRefMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleMenuResourseRef record);

    int insertSelective(SysRoleMenuResourseRef record);

    SysRoleMenuResourseRef selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleMenuResourseRef record);

    int updateByPrimaryKey(SysRoleMenuResourseRef record);
}