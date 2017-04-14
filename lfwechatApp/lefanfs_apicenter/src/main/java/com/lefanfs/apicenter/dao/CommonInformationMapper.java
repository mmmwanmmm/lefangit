package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.CommonInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommonInformationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommonInformation record);

    int insertSelective(CommonInformation record);

    CommonInformation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommonInformation record);

    int updateByPrimaryKey(CommonInformation record);

    List<CommonInformation> queryForList(@Param("informationTypes") List<Integer> informationTypeList, @Param("pageIndex") int pageIndex, @Param("pageSize") int appPageSize);

    List<CommonInformation> searchInformation(@Param("name") String name, @Param("type") Integer type, @Param("pageIndex") int startIndex, @Param("pageSize") int limit);
}