package com.lefanfs.apicenter.appapi.impl;

import com.lefanfs.apicenter.appapi.CompanyInfoApi;
import com.lefanfs.apicenter.dao.CompanyInfoMapper;
import com.lefanfs.apicenter.model.CompanyInfo;
import com.lefanfs.apicenter.model.UserScenarios;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiParam;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jani on 2017/3/9.
 */
@Service
@ApiService(descript = "公司服务介绍相关API")
public class CompanyInfoApiImpl  extends BaseServiceImpl implements CompanyInfoApi {

    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    /**
     * QA问答列表查询
     * @param apiReq
     * @return
     */
   /* @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "公司服务信息列表", value = "select-qainfo-list", apiParams = { @ApiParam(descript = "QA问答类型(*)", name = "type")})
    @Override
    public ApiResponse<List<CompanyInfo>> getCompanyInfoList(ApiRequest apiReq) {
        return null;
    }*/

    /**
     * 公司服务信息查询
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "公司服务信息查询", value = "select-companyInfo-byId", apiParams = { @ApiParam(descript = "公司服务信息标识ID(*)", name = "id")})
    @Override
    public ApiResponse<CompanyInfo> getCompanyInfoById(ApiRequest apiReq) {
        Integer id = apiReq.getInt("id");
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,companyInfo);
    }
}
