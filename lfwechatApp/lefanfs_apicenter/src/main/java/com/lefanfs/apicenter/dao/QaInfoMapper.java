package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.QaInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QaInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QaInfo record);

    int insertSelective(QaInfo record);

    QaInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QaInfo record);

    int updateByPrimaryKey(QaInfo record);

   List<QaInfo> selectQaInfoList(Map<String, Object> paramMap);

   List<QaInfo> selectQaInfoListByParam(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
}