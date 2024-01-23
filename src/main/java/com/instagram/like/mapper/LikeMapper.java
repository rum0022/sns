package com.instagram.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.instagram.like.domain.Like;

@Mapper
public interface LikeMapper {
	
	public void insertLike(
			@Param("postId") int postId,
			@Param("userId") int userId); 
	
	public int selectLikeCountByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public void deletelikeByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public int selectlikeCountByPostId(int postId);
	
	

}
