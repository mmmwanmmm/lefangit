package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserMapper {

	SysUser selectUser(Map<String, Object> paramMap);

	int countByUsername(String username);

	int deleteByPrimaryKey(Integer id);

	int insert(SysUser record);

	SysUser selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysUser record);

	int countListForBackend(Map<String, Object> paramMap);

	List<SysUser> selectListForBackend(Map<String, Object> paramMap);
}