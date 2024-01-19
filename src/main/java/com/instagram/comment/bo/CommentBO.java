package com.instagram.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.comment.domain.Comment;
import com.instagram.comment.mapper.CommentMapper;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentmapper;
	
	public void addComment(int userId, int postId,
			String content) {
		
		commentmapper.insertComment(userId, postId, content);
	}
	
	public List<Comment> getComment() {
		return commentmapper.selectComment();
	}
	
	public int deleteCommentById(int commnetId) {
		return commentmapper.deleteCommentById(commnetId);
	}
}
