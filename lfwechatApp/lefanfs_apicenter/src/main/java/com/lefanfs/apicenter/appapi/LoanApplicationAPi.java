package com.lefanfs.apicenter.appapi;

import com.lefanfs.apicenter.model.LoanApplication;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;

import java.util.List;

/**
 * 贷款申请
 * Created by Jani on 2017/3/8.
 */
public interface LoanApplicationAPi {
    ApiResponse insertLoanApplication(ApiRequest apiReq);

    ApiResponse<List<LoanApplication>> getLoanApplicationList(ApiRequest apiReq);

    ApiResponse<LoanApplication> selectLoanApplicationById(ApiRequest apiReq);
}
