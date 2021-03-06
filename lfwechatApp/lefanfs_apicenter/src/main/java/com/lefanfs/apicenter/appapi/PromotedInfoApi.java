package com.lefanfs.apicenter.appapi;

import com.lefanfs.apicenter.model.PromotedInfo;
import com.lefanfs.apicenter.model.UserPromoted;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by Jani on 2017/3/8.
 */
public interface PromotedInfoApi {

    /**
     * 我要成为推广大使
     * @param apiReq
     * @return
     */
    ApiResponse insertUserPromoted(ApiRequest apiReq) throws Exception;

    /**
     * 我的推广列表
     * @param apiReq
     * @return
     */
    ApiResponse<List<PromotedInfo>> getPromotedInfoList(ApiRequest apiReq);

    ApiResponse<PromotedInfo> selectPromotedInfoById(ApiRequest apiReq);

    ApiResponse<PromotedInfo> updatePromotedInfoById(ApiRequest apiReq);

    /**
     * 推广认证信息查询
     * @param apiReq
     * @return
     */
    ApiResponse<UserPromoted> selectUserPromotedInfoByuserId(ApiRequest apiReq);

    /**
     * 推广认证信息修改
     * @param apiReq
     * @return
     */
    ApiResponse updateUserPromoted(ApiRequest apiReq)   throws Exception;
}
