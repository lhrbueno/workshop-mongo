package com.home.workshopmongo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.workshopmongo.domain.Comment;
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

	@Override
	public Post findById(String id) {
		Optional<Post> post = postRepository.findById(id);
		return post.orElseThrow(() -> new NotFoundException(messageProperties.getMessage("post.error.notFound")));
	}
	
	@Override
	public Post insert(Post post) {
		return postRepository.save(post);
	}
	
	@Override
	public void delete(String id) {
		Post post = findById(id);
		postRepository.delete(post);
	}
	
	@Override
	public Post update(Post post) {
		Post postRequest = findById(post.getId());
		postRequest.setTitle(post.getTitle());
		postRequest.setBody(post.getBody());
		
		return postRepository.save(postRequest);
	}
	
	@Override
	public void addPostComment(String id, Comment comment) {
		Post post = findById(id);
		post.addComment(comment);
		postRepository.save(post);
	}
	
}
