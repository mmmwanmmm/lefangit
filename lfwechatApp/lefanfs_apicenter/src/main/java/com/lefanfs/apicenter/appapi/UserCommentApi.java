package com.lefanfs.apicenter.appapi;

import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;

/**
 * Created by fanshuai on 16/11/21.
 */
public interface UserCommentApi {

    /**
     * 添加留言
     * @param apiReq
     * @return
     */
    ApiResponse addComment(ApiRequest apiReq);

    /**
     * 我的留言
     * @param apiReq
     * @return
     */
    ApiResponse myCommentList(ApiRequest apiReq);
}
