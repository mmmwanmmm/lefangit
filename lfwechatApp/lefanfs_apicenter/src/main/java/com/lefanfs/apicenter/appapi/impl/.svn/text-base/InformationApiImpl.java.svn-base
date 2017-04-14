package com.lefanfs.apicenter.appapi.impl;

import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiParam;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import com.lefanfs.apicenter.appapi.InformationApi;
import com.lefanfs.apicenter.dao.CommonInformationMapper;
import com.lefanfs.apicenter.dto.CommonInformationDTO;
import com.lefanfs.apicenter.enums.InformationTypeEnum;
import com.lefanfs.apicenter.model.CommonInformation;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanshuai on 16/11/8.
 */
@Service
@ApiService(descript = "资讯API")
public class InformationApiImpl extends BaseServiceImpl implements InformationApi {
    @Autowired
    private CommonInformationMapper informationMapper;

    @ApiMethod(needLogin = true,descript = "获取好友列表", value = "information-list", apiParams = {
            @ApiParam(name = "user_token",descript = "当前用户token(*)"),
            @ApiParam(name = "informationTypeList",descript = "类型多个逗号间隔(*)"),
            @ApiParam(name = "page",descript = "当前页码(*)"),
            @ApiParam(name = "pageSize",descript = "一页显示多少条(*)")   })
    @Override
    public ApiResponse information(ApiRequest apiReq) {
        List<Integer> informationTypeList=apiReq.getIntList("informationTypeList");
        List<CommonInformationDTO> informationDTOs = new ArrayList<>();
        List<CommonInformation> informationList=informationMapper.queryForList(informationTypeList,getPageIndex(apiReq), getAppPageSize(apiReq));
        if (CollectionUtils.isEmpty(informationList)){
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,informationDTOs);
        }
        for (CommonInformation information:informationList){
            CommonInformationDTO dto = new CommonInformationDTO();
            informationDTOs.add(dto);
            dto.setId(information.getId());
            dto.setInformationName(information.getInformationName());
            dto.setCreateTime(information.getCreateTime().getTime());
            dto.setInformationIcon(this.getCdnUrl(null, information.getInformationIcon(), null));
            dto.setTypeName(InformationTypeEnum.getTypeName(information.getInformationType()));
            dto.setDetailUrl("http://api.bandexlife.cn/information/"+dto.getId());
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,informationDTOs);

    }
}
