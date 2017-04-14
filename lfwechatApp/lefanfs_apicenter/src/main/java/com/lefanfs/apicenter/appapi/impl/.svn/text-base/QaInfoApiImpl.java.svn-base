package com.lefanfs.apicenter.appapi.impl;

import com.lefanfs.apicenter.appapi.QaInfoApi;
import com.lefanfs.apicenter.dao.QaInfoMapper;
import com.lefanfs.apicenter.model.QaInfo;
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
 * QA问答相关
 * Created by Jani on 2017/3/9.
 */
@Service
@ApiService(descript = "QA问答相关API")
public class QaInfoApiImpl  extends BaseServiceImpl implements QaInfoApi {

    @Autowired
    private QaInfoMapper qaInfoMapper;

    /**
     * QA问答列表查询
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "QA问答列表查询", value = "select-qainfo-list", apiParams = { @ApiParam(descript = "QA问答类型(*)", name = "type")})
    @Override
    public ApiResponse<List<QaInfo>> getQaInfoList(ApiRequest apiReq) {
        Integer type = apiReq.getInt("type");
        this.setPageIndex(apiReq);
        apiReq.put("type",type);
        List<QaInfo> list = qaInfoMapper.selectQaInfoList(apiReq);
        return new ApiResponse<List<QaInfo>>(ApiMsgEnum.SUCCESS, (list == null ? 0 : list.size()), list);
    }
}
