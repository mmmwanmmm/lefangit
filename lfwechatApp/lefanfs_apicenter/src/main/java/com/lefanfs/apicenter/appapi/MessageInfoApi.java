package com.lefanfs.apicenter.appapi;

import com.lefanfs.apicenter.model.MessageInfo;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by Jani on 2017/3/14.
 */
public interface MessageInfoApi {
    ApiResponse<List<MessageInfo>> getMessageInfoList(ApiRequest apiReq);

    ApiResponse<MessageInfo> selectMessageInfoById(ApiRequest apiReq);
}
