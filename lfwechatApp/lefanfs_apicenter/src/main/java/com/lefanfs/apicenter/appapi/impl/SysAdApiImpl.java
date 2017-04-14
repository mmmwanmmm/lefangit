package com.lefanfs.apicenter.appapi.impl;

import com.lefanfs.apicenter.appapi.SysAdApi;
import com.lefanfs.apicenter.dao.SysAdInfoMapper;
import com.lefanfs.apicenter.dto.AdListDto;
import com.lefanfs.apicenter.model.SysAdInfo;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiParam;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jani on 2017/3/9.
 */
@Service
@ApiService(descript = "广告位相关API")
public class SysAdApiImpl  extends BaseServiceImpl implements SysAdApi{

    @Autowired
    private SysAdInfoMapper sysAdInfoMapper;
    /**
     * 根据广告位CODE查询广告信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "根据广告位CODE查询广告信息", value = "select-adinfo-list", apiParams = { @ApiParam(descript = "广告位code(*)", name = "adCode")})
    @Override
    public ApiResponse<List<AdListDto>> getSysAdInfoList(ApiRequest apiReq) {
        String adCode = apiReq.getString("adCode").toString();
        this.setPageIndex(apiReq);
        apiReq.put("adCode",adCode);
        List<AdListDto> list = sysAdInfoMapper.selectList(apiReq);
        return new ApiResponse<List<AdListDto>>(ApiMsgEnum.SUCCESS, (list == null ? 0 : list.size()), list);
    }
}
