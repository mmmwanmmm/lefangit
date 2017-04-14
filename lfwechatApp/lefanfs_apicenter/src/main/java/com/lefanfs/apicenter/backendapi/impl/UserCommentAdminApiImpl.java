package com.lefanfs.apicenter.backendapi.impl;

import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import com.lefanfs.apicenter.backendapi.UserCommentAdminApi;
import com.lefanfs.apicenter.dao.UserCommentMapper;
import com.lefanfs.apicenter.model.UserComment;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 17/1/4.
 */
@Service
@ApiService(descript = "用户留言")
public class UserCommentAdminApiImpl extends BaseServiceImpl implements UserCommentAdminApi {
    @Autowired
    private UserCommentMapper userCommentMapper;
    @Override
    @ApiMethod(descript = "留言-查询", value = "comment-search", apiParams = { })
    public ApiResponse<List<UserComment>> getUserCommentList(ApiRequest apiReq) {
        Integer commentTo = apiReq.getInt("commentTo");
        String comment = apiReq.getString("comment");
        Integer page = apiReq.getInt("page");
        int pageSize = 10;
        if (page==null || page<1){
            page = 1;
        }
        List<UserComment> commentList=userCommentMapper.searchComment(comment,commentTo,(page-1)*pageSize,pageSize);
        return new ApiResponse(ApiMsgEnum.SUCCESS,commentList==null?0:commentList.size(),commentList);
    }

    @Override
    @ApiMethod(descript = "留言-添加回复", value = "comment-addReply", apiParams = { })
    public ApiResponse addUserCommentReply(ApiRequest apiReq) {
        Long replyParentId=apiReq.getLong("replyParentId");
        UserComment parentUserComment = userCommentMapper.selectByPrimaryKey(replyParentId);
        if (parentUserComment==null){
            return new ApiResponse(ApiMsgEnum.COMMON_FAIL,new String[]{"原留言id不存在"});
        }
        UserComment comment = new UserComment();
        comment.setComment(apiReq.getString("comment"));
        comment.setUserId(0l);
        comment.setNickName("管理员");
        comment.setCommentTo(parentUserComment.getCommentTo());
        comment.setReplyParentId(replyParentId);
        comment.setCreateTime(new Date());
        comment.setDeleteFlag(0);
        int id=userCommentMapper.insert(comment);
        if (id>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @Override
    @ApiMethod(descript = "留言-留言详情", value = "comment-detail", apiParams = { })
    public ApiResponse userCommentDetail(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        UserComment userComment = userCommentMapper.selectByPrimaryKey(id);
        if (userComment==null){
            return new ApiResponse(ApiMsgEnum.COMMON_FAIL,new String[]{"原留言id不存在"});
        }
        List<Long> parentIdList = new ArrayList<>();
        parentIdList.add(id);
        List<UserComment> replyList = userCommentMapper.selectByParentIdList(parentIdList);
        userComment.setReplyList(replyList);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,userComment);
    }

    @Override
    @ApiMethod(descript = "留言-删除留言", value = "comment-delete", apiParams = { })
    public ApiResponse deleteUserCommentReply(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        UserComment userComment = userCommentMapper.selectByPrimaryKey(id);
        if (userComment==null){
            return new ApiResponse(ApiMsgEnum.COMMON_FAIL,new String[]{"原留言id不存在"});
        }
        userComment.setDeleteFlag(1);
        int updatenum = userCommentMapper.updateByPrimaryKey(userComment);
        if (updatenum>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
