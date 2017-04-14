package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.CommonArea;

import java.util.List;
import java.util.Map;

public interface CommonAreaMapper {
    int deleteByPrimaryKey(Long areaId);

    int insert(CommonArea record);

    int insertSelective(CommonArea record);

    CommonArea selectByPrimaryKey(Long areaId);

    int updateByPrimaryKeySelective(CommonArea record);

    int updateByPrimaryKey(CommonArea record);

    List<CommonArea> selectByType(int type);

    List<CommonArea> selectByParentId(Long parentId);

    List<CommonArea> selectAll();

    List<CommonArea> selectAreaByAreaIdList(Map<String, Object> paramMap);

}