package com.instagram.timeline;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	
	
	@GetMapping("/sign-up")
	public String signUp(Model model) {
		model.addAttribute("signUp", "user/signUp");
		return "user/signUp";
	}
	

	@GetMapping("/list-view")
	public String listView(Model model) {
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
}
