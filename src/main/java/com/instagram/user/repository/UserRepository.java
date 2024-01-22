package com.instagram.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	// null 이거나 UserEntity(단건) 돌려준다
		public UserEntity findByLoginId(String loginId);
		
		// null 이거나 UserEntity(단건) 돌려준다
		public UserEntity findByLoginIdAndPassword(String loginId, String password);
		
		// pk로 가져오는건 내부에 있음.
}
