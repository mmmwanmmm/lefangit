package com.lefanfs.apicenter.dao;

import com.lefanfs.apicenter.model.UserComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserComment record);

    int insertSelective(UserComment record);

    UserComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserComment record);

    int updateByPrimaryKey(UserComment record);

    List<UserComment> selectByUserIdWithOutReply(@Param("userId") Long userId, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    List<UserComment> selectByParentIdList(@Param("userCommentIdList") List<Long> userCommentIdList);

    List<UserComment> searchComment(@Param("comment") String comment, @Param("commentTo") Integer commentTo, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
}