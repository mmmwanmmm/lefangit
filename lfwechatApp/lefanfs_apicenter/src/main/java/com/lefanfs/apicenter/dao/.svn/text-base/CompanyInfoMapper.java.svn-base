package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.CompanyInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyInfo record);

    int insertSelective(CompanyInfo record);

    CompanyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyInfo record);

    int updateByPrimaryKey(CompanyInfo record);

    List<CompanyInfo> selectCompanyInfoList(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
}