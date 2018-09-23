package com.home.workshopmongo.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.home.workshopmongo.domain.Post;
import com.home.workshopmongo.exceptions.NotFoundException;

@Component
public interface PostService {

	List<Post> findAll();
	
	Post findById(String id) throws NotFoundException;
	
}
