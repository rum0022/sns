package com.instagram.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.post.entity.PostEntity;
import com.instagram.post.repository.PostRepository;

@Service
public class PostBO {
	
	@Autowired
	private PostRepository postRepository;
	
	public List<PostEntity> getPostEntityByUserId() {
		return postRepository.findAllByOrderByIdDesc();
	}

}
