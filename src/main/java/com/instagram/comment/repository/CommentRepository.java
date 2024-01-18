package com.instagram.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.comment.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{

}
