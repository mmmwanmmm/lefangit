package com.lefanfs.apicenter.appapi;

import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;

/**
 * Created by fanshuai on 16/10/25.
 */
public interface UserInfoApi {
    @SuppressWarnings("rawtypes")
    ApiResponse loadUserInfo(ApiRequest apiReq);

    @SuppressWarnings("rawtypes")
    ApiResponse updateUserInfo(ApiRequest apiReq);

    ApiResponse updateUserIcon(ApiRequest apiReq);
}
