package com.instagram.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.instagram.comment.domain.Comment;

@Mapper
public interface CommentMapper {
	
	public void insertComment(
			@Param("userId") int userId, 
			@Param("postId") int postId,
			@Param("content") String content);

	public List<Comment> selectComment(); 
	
	public int deleteCommentById(int commnetId);
}
