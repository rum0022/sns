package com.instagram.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.post.bo.PostBO;
import com.instagram.post.entity.PostEntity;
import com.instagram.timeline.domain.CardView;
import com.instagram.user.bo.UserBO;

@Service
public class TimeLineBO {
	
	@Autowired
	private PostBO postBo;
	
	@Autowired
	private UserBO userBo;

	// input: X        output: List<CardView>
	public List<CardView> generateCardViewList() {
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글목록을 다 가져온다. List<PostEntity>
		List<PostEntity> postList = postBo.getPostList();
		
		
		// 글목록 반복문 순회
		for(PostEntity post : postList) {
			cardViewList.add(post);
		} 
		
		// post => cardview    => cardView리스트에 넣기
		
		return cardViewList;
	}
}
