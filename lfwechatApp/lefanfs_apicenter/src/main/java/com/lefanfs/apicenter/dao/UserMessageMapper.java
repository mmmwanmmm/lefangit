package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.dto.MsgListDto;
import com.lefanfs.apicenter.model.UserMessage;

import java.util.List;
import java.util.Map;

public interface UserMessageMapper {
	int deleteByPrimaryKey(Long id);

	int insert(UserMessage record);

	int insertSelective(UserMessage record);

	UserMessage selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserMessage record);

	int updateByPrimaryKey(UserMessage record);

	List<MsgListDto> selectList(Map<String, Object> paramMap);
}