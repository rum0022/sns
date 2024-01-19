package com.instagram.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.comment.bo.CommentBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBo;
	
	// 댓글 쓰기
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		if (userId == null) {
			result.put("code", 500);
			result.put("error_message", "로그인을 해주세요");
		} 
			
		commentBo.addComment(userId, postId, content);
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	@ResponseBody
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("commentId") int commentId) {
		
		int row = commentBo.deleteCommentById(commentId);
		
		Map<String, Object> result = new HashMap<>();
		if (row > 0) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500); //실패
			result.put("error_message", "삭제하는데 실패했습니다.");
		}
		return result;
	}

}
