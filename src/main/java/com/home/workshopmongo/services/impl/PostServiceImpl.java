package com.home.workshopmongo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.workshopmongo.domain.Post;
import com.home.workshopmongo.exceptions.NotFoundException;
import com.home.workshopmongo.repository.PostRepository;
import com.home.workshopmongo.services.PostService;
import com.home.workshopmongo.utils.MessageProperties;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private MessageProperties messageProperties;
	
	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	public Post findById(String id) throws NotFoundException {
		Optional<Post> post = postRepository.findById(id);
		return post.orElseThrow(() -> new NotFoundException(messageProperties.getMessage("post.error.notFound")));
	}
	
}
