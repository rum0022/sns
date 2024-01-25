package com.instagram.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.instagram.comment.bo.CommentBO;
import com.instagram.comment.domain.CommentView;
import com.instagram.common.FileManagerService;
import com.instagram.like.bo.LikeBO;
import com.instagram.post.entity.PostEntity;
import com.instagram.post.repository.PostRepository;

@Service
public class PostBO {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	// select (게시글 뿌리기)
	public List<PostEntity> getPostList() {
		return postRepository.findAllByOrderByIdDesc();
	}

	// insert// input: 파라미터들   output:PostEntity
	public PostEntity addPost(int userId, String userLoginId, String content, 
			MultipartFile file) {
		
		String imagePath = null;
		// 이미지가 있으면 업로드 후 imagePath를 받아옴
		if (file != null) {
			imagePath = fileManagerService.saveFile(userLoginId, file);
		} 
		
		 return postRepository.save(PostEntity.builder()
				.userId(userId)
				.content(content)
				.imagePath(imagePath)
				.build());
	}
	
	// 삭제하기
	public void deletePostByPostId(int postId, int userId) {
		
		// 기존글 가져오기
		PostEntity post = postRepository.findByIdAndUserId(postId, userId);
		
		if (post == null) {
			return;
		}
		
		// 글삭제
		int deleteRowCount = postRepository.deleteById(postId);
		
		// 이미지 삭제하기
		if (deleteRowCount > 0 && post.getImagePath() != null) {
			fileManagerService.deleteFile(post.getImagePath());
		} 
		
		// 댓글들 삭제
		List<CommentView> commentList = commentBO.generateCommentViewListByPostId(postId);
		for (CommentView comment : commentList) {
			if (deleteRowCount > 0 && comment.getComment() != null) {
				commentBO.deleteCommentById(postId);
			}
		}
		
		// 좋아요들 삭제
		int like = likeBO.getlikeCountByPostId(postId);
		if (deleteRowCount > 0 && like > 0) {
			likeBO.deleteLikeByPostId(postId);
		}
	}
}
