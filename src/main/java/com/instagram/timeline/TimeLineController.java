package com.instagram.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.instagram.timeline.bo.TimeLineBO;
import com.instagram.timeline.domain.CardView;

@Controller
public class TimeLineController {
	
	@Autowired
	private TimeLineBO timeLineBO;

//	@Autowired
//	private PostBO postBO;
//	
//	@Autowired
//	private CommentBO commentBo;
	
	@GetMapping("/timeline/timeline-view")
	public String timelineView(Model model) {		
		// 글 목록 조회
		//List<PostEntity> postList = postBO.getPostList();
		// 댓글 뿌리기
		//List<Comment> commentList = commentBo.getComment();
		//model.addAttribute("commentList", commentList);
		//model.addAttribute("postList", postList);
		
		List<CardView> cardViewList = timeLineBO.generateCardViewList();
		
		model.addAttribute("cardViewList", "cardViewList");
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
	
	
}
