package com.lefanfs.apicenter.backendapi.impl;

import com.lefanfs.apicenter.backendapi.BackendCompanyInfoApi;
import com.lefanfs.apicenter.dao.CompanyInfoMapper;
import com.lefanfs.apicenter.model.CompanyInfo;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiParam;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Jani on 2017/3/10.
 */
@Service
@ApiService(descript = "公司服务相关API")
public class BackendCompanyInfoApiImpl extends BaseServiceImpl implements BackendCompanyInfoApi {

    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    /**
     * 公司服务列表
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "公司服务列表", value = "backend-companyInfo-list", apiParams = { })
    @Override
    public ApiResponse<List<CompanyInfo>> getBkCompanyInfoList(ApiRequest apiReq) {
        Integer page = apiReq.getInt("page");
        int pageSize = 10;
        if (page==null || page<1){
            page = 1;
        }
        List<CompanyInfo> companyInfoList=companyInfoMapper.selectCompanyInfoList((page - 1) * pageSize, pageSize);
        return new ApiResponse(ApiMsgEnum.SUCCESS,companyInfoList==null?0:companyInfoList.size(),companyInfoList);
    }

    /**
     * 新增公司服务信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "新增公司服务信息", value = "backend-add-companyInfo", apiParams = {@ApiParam(descript = "标题(*)", name = "title"), @ApiParam(descript = "内容(*)", name = "content")})
    @Override
    public ApiResponse<CompanyInfo> addBkCompanyInfoList(ApiRequest apiReq) {
        CompanyInfo companyInfo = new CompanyInfo();
        String title = apiReq.getString("title");
        String content = apiReq.getString("content");
        companyInfo.setTitle(title);
        companyInfo.setContent(content);
        companyInfo.setCreateTime(new Date());
        companyInfo.setModifyTime(new Date());
        companyInfo.setDeleteFlag(0);
        int result = companyInfoMapper.insertSelective(companyInfo);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,companyInfo);
    }

    /**
     * 修改公司服务信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "修改公司服务信息", value = "backend-update-companyInfo", apiParams = {@ApiParam(descript = "标识ID(*)", name = "id"),@ApiParam(descript = "标题(*)", name = "title"), @ApiParam(descript = "内容(*)", name = "content")})
    @Override
    public ApiResponse<CompanyInfo> updateBkCompanyInfoList(ApiRequest apiReq) {
        CompanyInfo companyInfo = new CompanyInfo();
        Integer id = apiReq.getInt("id");
        companyInfo.setId(id);
        if(!StringUtils.isEmpty(apiReq.get("title"))){
            String title = apiReq.getString("title");
            companyInfo.setTitle(title);
        }
        if(!StringUtils.isEmpty(apiReq.get("content"))){
            String content = apiReq.getString("content");
            companyInfo.setContent(content);
        }
        companyInfo.setModifyTime(new Date());
        int result = companyInfoMapper.updateByPrimaryKeySelective(companyInfo);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,companyInfo);
    }

    /**
     * 删除公司服务信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "删除公司服务信息", value = "backend-delete-companyInfo", apiParams = {@ApiParam(descript = "标识ID(*)", name = "id")})
    @Override
    public ApiResponse<CompanyInfo> deleteBkCompanyInfoList(ApiRequest apiReq) {
        CompanyInfo companyInfo = new CompanyInfo();
        Integer id = apiReq.getInt("id");
        companyInfo.setId(id);
        companyInfo.setDeleteFlag(1);
        int result = companyInfoMapper.updateByPrimaryKeySelective(companyInfo);
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 删除公司服务信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "公司服务详情", value = "backend-indetails-companyInfo", apiParams = {@ApiParam(descript = "标识ID(*)", name = "id")})
    @Override
    public ApiResponse<CompanyInfo> selectBkCompanyInfoById(ApiRequest apiReq) {
        Integer id = apiReq.getInt("id");
        CompanyInfo companyInfo1 = companyInfoMapper.selectByPrimaryKey(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,companyInfo1);
    }
}
