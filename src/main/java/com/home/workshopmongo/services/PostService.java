package com.home.workshopmongo.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.home.workshopmongo.domain.Comment;
import com.home.workshopmongo.domain.Post;

@Component
public interface PostService {

	List<Post> findAll();
	
	Post findById(String id);
	
	Post insert(Post post);
	
	void delete(String id);
	
	Post update(Post post);
	
	void addPostComment(String id, Comment comment);
	
}
