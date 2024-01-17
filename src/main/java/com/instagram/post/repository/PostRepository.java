package com.instagram.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.post.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer>{

	public List<PostEntity> findAllByOrderByIdDesc();
}
