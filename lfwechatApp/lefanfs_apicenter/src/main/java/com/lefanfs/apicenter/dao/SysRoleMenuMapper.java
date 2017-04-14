package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.SysRoleMenu;

import java.util.List;
import java.util.Map;

public interface SysRoleMenuMapper {

	int deleteByPrimaryKey(Integer id);

	int deleteByRoleId(Integer roleId);

	int insert(Map<String, Object> params);

	SysRoleMenu selectByPrimaryKey(Integer id);

	List<SysRoleMenu> selectList(Map<String, Object> params);

}