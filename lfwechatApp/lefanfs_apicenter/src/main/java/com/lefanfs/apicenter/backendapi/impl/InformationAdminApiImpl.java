package com.lefanfs.apicenter.backendapi.impl;

import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import com.lefanfs.apicenter.backendapi.InformationAdminApi;
import com.lefanfs.apicenter.dao.CommonInformationMapper;
import com.lefanfs.apicenter.domain.CDNDomain;
import com.lefanfs.apicenter.model.CommonInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanshuai on 17/1/1.
 */
@Service
@ApiService(descript = "资讯基础管理服务")
public class InformationAdminApiImpl implements InformationAdminApi {
    @Autowired
    private CommonInformationMapper informationMapper;

    @Override
    @ApiMethod(descript = "资讯-搜索列表", value = "information-search", apiParams = { })
    public ApiResponse<List<CommonInformation>> searchInformation(ApiRequest apiReq) {
        String name=apiReq.getString("name");
        Integer type = apiReq.getInt("type");
        Integer page = apiReq.getInt("page");
        if (page==null || page<=0){
            page=1;
        }
        int limit = 10;
        int startIndex = (page-1)*limit;
        List<CommonInformation> informationList=informationMapper.searchInformation(name, type,startIndex,limit);
        if (CollectionUtils.isEmpty(informationList)){
            informationList = new ArrayList<>();
        }
        for (CommonInformation info:informationList){
            info.setInformationIcon(CDNDomain.getCdnUrl(info.getInformationIcon()));
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,informationList.size(),informationList);
    }

    @Override
    @ApiMethod(descript = "资讯-详情", value = "information-detail", apiParams = { })
    public ApiResponse<CommonInformation> informationDetail(ApiRequest apiReq) {
        Long id=apiReq.getLong("id");
        CommonInformation information=informationMapper.selectByPrimaryKey(id);
        if (information!=null){
            information.setInformationIcon(CDNDomain.getCdnUrl(information.getInformationIcon()));
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,information);
    }

    @Override
    @ApiMethod(descript = "资讯-搜索列表", value = "information-add", apiParams = { })
    public ApiResponse informationAdd(ApiRequest apiReq) {
        CommonInformation information = new CommonInformation();
        information.setInformationName(apiReq.getString("informationName"));
        information.setInformationContext(apiReq.getString("informationContext"));
        information.setInformationIcon(apiReq.getString("informationIcon"));
        information.setInformationType(apiReq.getInt("informationType"));
        informationMapper.insert(information);
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @Override
    @ApiMethod(descript = "资讯-搜索列表", value = "information-update", apiParams = { })
    public ApiResponse informationUpdate(ApiRequest apiReq) {
        Long id=apiReq.getLong("id");
        CommonInformation information = informationMapper.selectByPrimaryKey(id);
        if (information==null){
            return new ApiResponse(ApiMsgEnum.COMMON_FAIL,new String[]{"id不存在"});
        }
        information.setInformationName(apiReq.getString("informationName"));
        information.setInformationContext(apiReq.getString("informationContext"));
        information.setInformationIcon(apiReq.getString("informationIcon"));
        information.setInformationType(apiReq.getInt("informationType"));
        informationMapper.updateByPrimaryKey(information);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,information);
    }
}
