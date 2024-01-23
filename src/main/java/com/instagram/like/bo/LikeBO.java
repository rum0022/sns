package com.instagram.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.like.domain.Like;
import com.instagram.like.mapper.LikeMapper;

@Service
public class LikeBO {//  있으면 삭제 없으면 추가

	@Autowired
	private LikeMapper likeMapper;
	
	//input:postId, userId        output: X
	public void likeToggle(int postId, int userId) {
		
		// like가 있으면
		// -- 삭제
		if (likeMapper.selectLikeCountByPostIdUserId(postId, userId) > 0) {
			likeMapper.deletelikeByPostIdUserId(postId, userId);
		} else {	// 없으면
			// -- 추가	
			likeMapper.insertLike(postId, userId);
		}
	}	
	
	public int getlikeCountByPostId(int postId) {
		return likeMapper.selectlikeCountByPostId(postId);
	}
	
	public int getLikeByPostIdUserId(int postId, int userId) {
		return likeMapper.selectLikeCountByPostIdUserId(postId, userId);
	}
}
