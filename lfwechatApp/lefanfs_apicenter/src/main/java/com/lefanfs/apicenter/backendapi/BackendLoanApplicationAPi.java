package com.lefanfs.apicenter.backendapi;

import com.lefanfs.apicenter.model.LoanApplication;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;

import java.util.List;

/**
 * 后台贷款申请管理API
 * Created by Jani on 2017/3/10.
 */
public interface BackendLoanApplicationAPi {
    /**
     * 贷款申请列表
     *
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    ApiResponse<List<LoanApplication>> getBkLoanApplicationList(ApiRequest apiReq);

    /**
     * 贷款申请状态修改
     *
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    ApiResponse<LoanApplication> updateBkLoanApplicationById(ApiRequest apiReq)  throws Exception;
    /**
     * 贷款申请删除
     *
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    ApiResponse<LoanApplication> deleteBkLoanApplicationById(ApiRequest apiReq);
}
