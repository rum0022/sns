package com.instagram.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.like.mapper.LikeMapper;

@Service
public class LikeBO {//  있으면 삭제 없으면 추가

	@Autowired
	private LikeMapper likeMapper;
	
	//input:postId, userId        output: X
	public void likeToggle(int postId, int userId) {
		
		// like가 있으면
		// -- 삭제
		if (likeMapper.selectLikeCountByPostIdOrUserId(postId, null) > 0) {
			likeMapper.deletelikeByPostIdUserId(postId, userId);
		} else {	// 없으면
			// -- 추가	
			likeMapper.insertLike(postId, userId);
		}
	}	
	
	public int getlikeCountByPostId(int postId) {
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, null);
	}
	
	public int getLikeByPostIdUserId(int postId, int userId) {
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId);
	}
	
	// input: postId, userId(null or)          output: boolean
	public boolean getLikeCountByPostIdUserId(int postId, Integer userId) {
		//비로그인이면 무조건 빈하트 => false
		if (userId == null) {
			return false;
		}
		
		// 로그인이 0보다 크면 (1이면) 채운다, 그렇지 않으면 false
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0;
	}
	
	public void deleteLikeByPostId(int postId) {
		likeMapper.deleteLikeByPostId(postId);
	}
}
