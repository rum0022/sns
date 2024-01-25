package com.instagram.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.instagram.like.domain.Like;

@Mapper
public interface LikeMapper {
//	public int selectLikeCountByPostIdUserId(
//			@Param("postId") int postId,
//			@Param("userId") int userId);
	
	//public int selectlikeCountByPostId(int postId); //ctrl + alt +H 이걸 쓰는 곳이 나옴 (아래 콜 하이어라키)
	
	// like count 쿼리 하나로 합치기
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId);  // null 허용
	
	public void insertLike(
			@Param("postId") int postId,
			@Param("userId") int userId); 
	
	public void deletelikeByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public void deleteLikeByPostId(int postId);
	
	

}
