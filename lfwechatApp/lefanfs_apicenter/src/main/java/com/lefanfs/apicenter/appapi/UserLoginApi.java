package com.lefanfs.apicenter.appapi;

import com.lefanfs.apicenter.model.UserInfo;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;

import java.io.IOException;

public interface UserLoginApi {

	@SuppressWarnings("rawtypes")
	ApiResponse login(ApiRequest apiReq) ;

	@SuppressWarnings("rawtypes")
	ApiResponse logout(ApiRequest apiReq);

    @SuppressWarnings("rawtypes")
    ApiResponse<UserInfo> sendSMS(ApiRequest apiRequest);

    @SuppressWarnings("rawtypes")
    ApiResponse<UserInfo> selectUserByOpenId(ApiRequest apiReq)  throws IOException;
}
