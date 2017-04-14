package com.lefanfs.apicenter.backendapi;

import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.apicenter.model.UserComment;

import java.util.List;

/**
 * Created by fanshuai on 17/1/4.
 */
public interface UserCommentAdminApi {

    /**
     * 用户留言列表
     * @param apiReq
     * @return
     */
    ApiResponse<List<UserComment>> getUserCommentList(ApiRequest apiReq);

    /**
     * 添加回复
     * @param apiReq
     * @return
     */
    ApiResponse addUserCommentReply(ApiRequest apiReq);

    /**
     * 用户留言详情
     * @param apiReq
     * @return
     */
    ApiResponse userCommentDetail(ApiRequest apiReq);


    /**
     * 删除留言
     * @param apiReq
     * @return
     */
    ApiResponse deleteUserCommentReply(ApiRequest apiReq);

}
