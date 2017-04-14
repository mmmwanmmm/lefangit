package com.lefanfs.apicenter.backendapi;

import com.lefanfs.apicenter.model.PromotedInfo;
import com.lefanfs.apicenter.model.UserPromoted;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jani on 2017/3/15.
 */
public interface BackendUserPromotedApi {
    ApiResponse<List<UserPromoted>> getBkUserPromotedList(ApiRequest apiReq);
    ApiResponse<UserPromoted> updateBkUserPromotedById(ApiRequest apiReq) throws IOException;
    ApiResponse<List<PromotedInfo>> getBkPromotedInfoList(ApiRequest apiReq);
    ApiResponse<PromotedInfo> updateBkPromotedInfoById(ApiRequest apiReq);
}
