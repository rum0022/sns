package com.instagram.user.bo;

import org.springframework.stereotype.Service;

import com.instagram.user.entity.UserEntity;

@Service
public class UserBO {
	
	@Autowired
	private UserRepository userRepository;
	
	// input : loginId     output:UserEntity (있거나 or null)
			public UserEntity getUserEntityByLoginId(String loginId) {
				return userRepository.findByLoginId(loginId);
	}

}
	
	