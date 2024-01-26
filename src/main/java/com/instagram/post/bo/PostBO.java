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

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	public void deletePostByPostIdUserId(int postId, int userId) {
		
		// 기존글 가져오기
		PostEntity post = postRepository.findById(postId).orElse(null);
		
		if (post == null) {
			log.error("[delete post] postId:{}, userId:{}", postId, userId);
			return;
		}
		
		// 글삭제
		postRepository.delete(post);
		
		// 이미지 삭제하기
		fileManagerService.deleteFile(post.getImagePath());
			
		// 댓글들 삭제
		commentBO.deleteCommentsByPostId(postId);
		
		// 좋아요들 삭제
		likeBO.deleteLikeByPostId(postId);
	}
}
