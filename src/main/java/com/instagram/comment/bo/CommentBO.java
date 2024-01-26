package com.instagram.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.comment.domain.Comment;
import com.instagram.comment.domain.CommentView;
import com.instagram.comment.mapper.CommentMapper;
import com.instagram.user.bo.UserBO;
import com.instagram.user.entity.UserEntity;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentmapper;
	
	@Autowired
	private UserBO userBO;
	
	public void addComment(int userId, int postId,
			String content) {
		
		commentmapper.insertComment(userId, postId, content);
	}
	
	public List<Comment> getComment() {
		return commentmapper.selectComment();
	}
	
	public List<CommentView> generateCommentViewListByPostId(int postId) {
		// 결과 리스트 만들기
		List<CommentView> commentViewList = new ArrayList<>();
		
		// 글에 해당하는 댓글 목록 가져오기 List<Comment> , 글번호에 해당하는 댓글들을 가져온다
		List<Comment> commentList = commentmapper.selectCommentListByPostId(postId);
		
		// 반복문 순회 Comment -> CommentView => 리스트에 넣기
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView(); //반복문의 개수만큼 뉴가 됨
			
			// 댓글 1개
			commentView.setComment(comment);	
			
			// 댓글 쓴이
			UserEntity user = userBO.getUserEntityByUserId(comment.getUserId());
			commentView.setUser(user);
			
			// 결과 리스트에 담기
			commentViewList.add(commentView);
		}
		// 결과 리스트 리턴하기
		return commentViewList;
	}
	
	public void deleteCommentById(int id) {
		commentmapper.deleteCommentById(id);
	}
	
	public void deleteCommentsByPostId(int postId) {
		commentmapper.deleteCommentsByPostId(postId);
	}
}
