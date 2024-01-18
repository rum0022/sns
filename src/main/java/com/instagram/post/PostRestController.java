package com.instagram.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.instagram.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {

	@Autowired
	private PostBO postBo;
	
	// post -> insert
		@PostMapping("/create")
		public Map<String, Object> create(
				@RequestParam("content") String content,
				@RequestParam(value = "file", required=false) MultipartFile file,
				HttpSession session) {
			
			Integer userId = (Integer)session.getAttribute("userId");
			String userLoginId = (String)session.getAttribute("userLoginId");
			
			//응답값
			Map<String, Object> result = new HashMap<>();
			
			if (userId == null) {
				result.put("code", 500); // 비로그인 상태
				result.put("error_message", "로그인을 해주세요.");
				return result;
			}
			
			//db insert
			postBo.addPost(userId, userLoginId, content, file);
			
			result.put("code", 200);
			result.put("result", "성공");
			return result;
		}
	
}