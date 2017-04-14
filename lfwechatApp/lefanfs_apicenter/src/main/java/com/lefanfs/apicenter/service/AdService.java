package com.lefanfs.apicenter.service;

import com.lefanfs.apicenter.dto.AdListDto;

import java.util.List;

/**
 * 广告服务
 * 
 * @author Daniel
 */
public interface AdService {
	List<AdListDto> selectList(String placeCode, int limit);
}
