package com.instagram.timeline.domain;

import java.util.List;

import com.instagram.comment.domain.CommentView;
import com.instagram.post.entity.PostEntity;
import com.instagram.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

// view용 객체
// 글 한개와 매핑됨 (글 1개와 카드뷰 1개)
@ToString
@Data
public class CardView {
	// 글 1개
	private PostEntity post;
	
	// 글쓴이 정보
	private UserEntity user;
	
	// 글하나에 대한 댓글들
	private List<CommentView> commentList;
	
	// 좋아요 개수
	private int likeCount; // 없으면 0
	
	// 로그인 된 사람이 좋아요를 누른지 여부
	private boolean filledLike;  //is로 네임을 시작하면 게터가 is로 시작함
}
