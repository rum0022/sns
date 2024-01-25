package com.instagram.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.instagram.post.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer>{

	public List<PostEntity> findAllByOrderByIdDesc();
	
	public PostEntity findByIdAndUserId(
			@Param("id") Integer id, 
			@Param("userId") int userId);
	
	public int deleteById(int id);
}
