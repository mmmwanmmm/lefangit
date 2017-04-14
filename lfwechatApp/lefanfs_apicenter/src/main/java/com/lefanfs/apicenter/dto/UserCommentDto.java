package com.lefanfs.apicenter.dto;

import com.lefanfs.apicenter.model.UserComment;

import java.util.List;

/**
 * Created by fanshuai on 16/11/21.
 */
public class UserCommentDto {
    private Long id;

    private Long userId;

    private String nickName;

    private Integer commentType;

    private String comment;

    private Integer commentTo;

    private Long createTime;

    private Long createId;

    private Integer deleteFlag;

    private Long replyParentId;

    private List<UserCommentDto> replyCommentDto;

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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
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

    public List<UserCommentDto> getReplyCommentDto() {
        return replyCommentDto;
    }

    public void setReplyCommentDto(List<UserCommentDto> replyCommentDto) {
        this.replyCommentDto = replyCommentDto;
    }

    public static UserCommentDto getFromUserCommentEntity(UserComment entity){
        UserCommentDto dto = new UserCommentDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setNickName(entity.getNickName());
        dto.setComment(entity.getComment());
        dto.setCreateTime(entity.getCreateTime().getTime());
        return dto;
    }
}
