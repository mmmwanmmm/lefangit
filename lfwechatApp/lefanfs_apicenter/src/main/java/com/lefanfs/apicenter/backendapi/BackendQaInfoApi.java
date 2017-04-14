package com.lefanfs.apicenter.backendapi;

import com.lefanfs.apicenter.model.QaInfo;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by Jani on 2017/3/22.
 */
public interface BackendQaInfoApi {
    @SuppressWarnings("rawtypes")
    ApiResponse<List<QaInfo>> getQaInfoList(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    ApiResponse<QaInfo> addBkQaInfo(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    ApiResponse<QaInfo> aupdateBkQaInfo(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    ApiResponse<QaInfo> deleteBkQaInfo(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    ApiResponse<QaInfo> selectBkQaInfoById(ApiRequest apiReq);
}
