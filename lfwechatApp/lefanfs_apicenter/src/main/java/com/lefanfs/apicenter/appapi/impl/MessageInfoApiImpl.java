package com.lefanfs.apicenter.appapi.impl;

import com.lefanfs.apicenter.appapi.MessageInfoApi;
import com.lefanfs.apicenter.dao.MessageInfoMapper;
import com.lefanfs.apicenter.dao.PromotedInfoMapper;
import com.lefanfs.apicenter.model.MessageInfo;
import com.lefanfs.apicenter.model.PromotedInfo;
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
 * Created by Jani on 2017/3/14.
 */
@Service
@ApiService(descript = "消息相关API")
public class MessageInfoApiImpl  extends BaseServiceImpl implements MessageInfoApi {


    @Autowired
    private MessageInfoMapper messageInfoMapper;

    /**
     * 我的推广列表
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "我的消息列表", value = "messageInfo-list", apiParams = { @ApiParam(name = "user_token",descript = "当前用户token(*)")})
    @Override
    public ApiResponse<List<MessageInfo>> getMessageInfoList(ApiRequest apiReq) {
        Long userId = apiReq.getCurrentUserId();
        this.setPageIndex(apiReq);
        apiReq.put("receiverId",userId);
        List<MessageInfo> list = messageInfoMapper.selectMessageInfoList(apiReq);
        return new ApiResponse<List<MessageInfo>>(ApiMsgEnum.SUCCESS, (list == null ? 0 : list.size()), list);
    }

    /**
     * 推广人详情
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "消息详情", value = "messageInfo-indetails", apiParams = { @ApiParam(name = "user_token",descript = "当前用户token(*)"),@ApiParam(descript = "推广标识ID", name = "id")})
    @Override
    public ApiResponse<MessageInfo> selectMessageInfoById(ApiRequest apiReq) {
        Integer id = apiReq.getInt("id");
        MessageInfo messageInfo = this.messageInfoMapper.selectByPrimaryKey(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,messageInfo);
    }
}
