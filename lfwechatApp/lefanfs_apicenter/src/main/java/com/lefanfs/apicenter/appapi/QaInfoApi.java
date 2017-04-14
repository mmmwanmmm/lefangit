package com.lefanfs.apicenter.appapi;

import com.lefanfs.apicenter.model.QaInfo;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by Jani on 2017/3/9.
 */
public interface QaInfoApi {
    /**
     * 查询QA信息列表
     * @param apiReq
     * @return
     */
    ApiResponse<List<QaInfo>> getQaInfoList(ApiRequest apiReq);
}
