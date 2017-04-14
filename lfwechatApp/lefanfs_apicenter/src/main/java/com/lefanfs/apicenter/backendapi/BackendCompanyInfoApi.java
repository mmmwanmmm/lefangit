package com.lefanfs.apicenter.backendapi;

import com.lefanfs.apicenter.model.CompanyInfo;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by Jani on 2017/3/10.
 */
public interface BackendCompanyInfoApi {
    /**
     * 公司服务列表
     *
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    ApiResponse<List<CompanyInfo>> getBkCompanyInfoList(ApiRequest apiReq);
    /**
     * 新增公司服务信息
     *
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    ApiResponse<CompanyInfo> addBkCompanyInfoList(ApiRequest apiReq);

    /**
     * 修改公司服务信息
     *
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    ApiResponse<CompanyInfo> updateBkCompanyInfoList(ApiRequest apiReq);
    /**
     * 删除公司服务信息
     *
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    ApiResponse<CompanyInfo> deleteBkCompanyInfoList(ApiRequest apiReq);

    /**
     * 查询公司服务详情
     *
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    ApiResponse<CompanyInfo> selectBkCompanyInfoById(ApiRequest apiReq);
}
