package com.lefanfs.backend.dto;

import java.util.Date;
import java.util.List;

public class UserCommentDTO {
    private Long id;

    private Long userId;

    private String nickName;

    private Integer commentType;

    private String comment;

    private Integer commentTo;

    private Date createTime;

    private Long createId;

    private Integer deleteFlag;

    private Long replyParentId;

    private List<UserCommentDTO> replyList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCommentTo() {
        return commentTo;
    }

    public void setCommentTo(Integer commentTo) {
        this.commentTo = commentTo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getReplyParentId() {
        return replyParentId;
    }

    public void setReplyParentId(Long replyParentId) {
        this.replyParentId = replyParentId;
    }

    public List<UserCommentDTO> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<UserCommentDTO> replyList) {
        this.replyList = replyList;
    }
}