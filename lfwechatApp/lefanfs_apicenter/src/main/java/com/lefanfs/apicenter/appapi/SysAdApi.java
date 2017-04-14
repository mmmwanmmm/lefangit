package com.lefanfs.apicenter.appapi;

import com.lefanfs.apicenter.dto.AdListDto;
import com.lefanfs.apicenter.model.SysAdInfo;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;

import java.util.List;

/**
 * 广告位相关API
 * Created by Jani on 2017/3/9.
 */
public interface SysAdApi {
    /**
     * 根据广告位CODe查询广告位广告信息
     * @param apiReq
     * @return
     */
    ApiResponse<List<AdListDto>> getSysAdInfoList(ApiRequest apiReq);
}
