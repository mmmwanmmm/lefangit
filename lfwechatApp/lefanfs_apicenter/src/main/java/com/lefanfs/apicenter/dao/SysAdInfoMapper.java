package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.dto.AdListDto;
import com.lefanfs.apicenter.model.SysAdInfo;

import java.util.List;
import java.util.Map;

public interface SysAdInfoMapper {
	int deleteByPrimaryKey(Long id);

	int insert(SysAdInfo record);

	int insertSelective(SysAdInfo record);

	SysAdInfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(SysAdInfo record);

	int updateByPrimaryKey(SysAdInfo record);

	List<AdListDto> selectList(Map<String, Object> paramMap);

	int selectCountForBackend(Map<String, Object> paramMap);

	List<SysAdInfo> selectListForBackend(Map<String, Object> paramMap);
}