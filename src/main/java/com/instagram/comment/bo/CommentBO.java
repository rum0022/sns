package com.instagram.comment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.comment.entity.CommentEntity;
import com.instagram.comment.repository.CommentRepository;

@Service
public class CommentBO {

	@Autowired
	private CommentRepository commentRepository;
	
	public void addComment(int userId, int postId,
			String content) {
		
		commentRepository.save(CommentEntity.builder()
				.postId(postId)
				.userId(userId)
				.content(content)
				.build());
	
	}
}
