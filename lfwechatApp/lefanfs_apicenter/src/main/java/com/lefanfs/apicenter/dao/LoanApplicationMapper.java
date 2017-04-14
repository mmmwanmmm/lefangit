package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.LoanApplication;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoanApplicationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LoanApplication record);

    int insertSelective(LoanApplication record);

    LoanApplication selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoanApplication record);

    int updateByPrimaryKey(LoanApplication record);

    List<LoanApplication> selectLoanApplicationByParam(@Param("state") Integer state, @Param("userId") Long userId,@Param("userPhone") String userPhone,@Param("isTrafficAccident") Integer isTrafficAccident, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
}