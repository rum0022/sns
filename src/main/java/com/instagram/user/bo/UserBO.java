package com.instagram.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.user.entity.UserEntity;
import com.instagram.user.repository.UserRepository;

@Service
public class UserBO {
	
	@Autowired
	private UserRepository userRepository;
	
	// input : loginId     output:UserEntity (있거나 or null)
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
	
	// input : 파라미터 4개  output :  유저엔티티 받지만 가공해서 Integer id(pk) 
		public Integer addUser(String loginId, String password, String name, String email) {
			UserEntity userEntity = userRepository.save(
						UserEntity.builder()
							.loginId(loginId)
							.password(password)
							.name(name)
							.email(email)
							.build()
					);
			return userEntity == null ? null : userEntity.getId();
		}
		
		// input: loginId, password    output: userEntity
		public UserEntity getUserEntityByLoginIdPassword(String loginId, String password) {
			return userRepository.findByLoginIdAndPassword(loginId, password);
		}
		
		// input: userId        output: userEntity
		public UserEntity getUserEntityByUserId(int id) {
			return userRepository.findById(id).orElse(null); // 타입맞추기
		}
}
	
	