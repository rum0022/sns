package com.instagram.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.comment.bo.CommentBO;
import com.instagram.comment.domain.CommentView;
import com.instagram.post.bo.PostBO;
import com.instagram.post.entity.PostEntity;
import com.instagram.timeline.domain.CardView;
import com.instagram.user.bo.UserBO;
import com.instagram.user.entity.UserEntity;

@Service
public class TimeLineBO { // BO는 다른 비오를 부를 수 있다. 
	
	@Autowired
	private PostBO postBo;
	
	@Autowired
	private UserBO userBo;
	
	@Autowired
	private CommentBO commentBO;	

	// input: X        output: List<CardView>
	public List<CardView> generateCardViewList() {
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글목록을 다 가져온다. List<PostEntity>
		List<PostEntity> postList = postBo.getPostList();
		
		// 글목록 반복문 순회
		
		for(PostEntity post : postList) {
			// post 하나에 대응되는 하나의 카드를 만든다.
			CardView cardView = new CardView();
			
			// 글 1개
			cardView.setPost(post);	
			
			// 글쓴이 정보
			UserEntity user = userBo.getUserEntityByUserId(post.getUserId());
			cardView.setUser(user);
			
			// 댓글들 comment에는 댓글쓴이 정보가 없기때문에 commentview 만든것
			List<CommentView> commentList = commentBO.generateCommentViewListByPostId(post.getId());
			
			cardView.setCommentList(commentList);
			
			// 좋아요
			
			//★★★★★★★★★★★★★★ 마지막에 cardView를 list에 넣는다.!!!!!!! 중요!!!!
			cardViewList.add(cardView);
		}
		
		// post => cardview    => cardView리스트에 넣기
		
		return cardViewList;
	}
}
