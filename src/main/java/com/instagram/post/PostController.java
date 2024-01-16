package com.instagram.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.instagram.post.bo.PostBO;
import com.instagram.post.entity.PostEntity;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {

	@Autowired
	private PostBO postBO;
	
	@GetMapping("/post-list-view")
	public String postListView(HttpSession session, Model model) {
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null ) {
			return "redirect:/user/sign-in-view";
		}
		
		List<PostEntity> postEntityList = postBO.getPostEntityByUserId(userId);
		model.addAttribute("postEntityList", postEntityList);
		
		model.addAttribute("viewName", "timeline/postList");
		return "template/layout";
	}
}
