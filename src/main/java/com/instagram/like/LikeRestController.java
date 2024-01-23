package com.instagram.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.like.bo.LikeBO;
import com.instagram.like.domain.Like;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {
	
	@Autowired
	private LikeBO likeBO;

	// 1) GET: /like?postId=13         @RequestParam("postId")
	// 2) GET: /like/13                @PathVariable  (패스안의 값을 꺼내오겠다)
	@RequestMapping("/like/{postId}") // 겟이랑 포스트 다 가능이므로
	public Map<String, Object> likeToggle(
			@PathVariable(name = "postId") int postId,
			HttpSession session) { 
			
		// 로그인 여부 확인
		Integer userId = (Integer)session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
			
		if (userId == null) {
			result.put("code", 300);
			result.put("error_message", "로그인이 되지 않은 사용자 입니다.");
			return result;
		}
		// BO 호출 -> likeToggle
		 likeBO.likeToggle(postId, userId);
		
		// 응답값
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}
