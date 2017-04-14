package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.SysUserRole;

import java.util.List;
import java.util.Map;

public interface SysUserRoleMapper {

	int deleteByUserId(Integer userId);

	int insert(Map<String, Object> params);

	List<SysUserRole> selectList(Map<String, Object> params);

	int selectCount(Map<String, Object> params);

}