package com.instagram.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserRestController {

	@RequestMapping("/is-duplicated-id")
	public Map<String, Object> isDuplicateId(
			@RequestParam("loginId") String loginId) {
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("is-duplicated-id", true);
		
		return result;
	}
}
