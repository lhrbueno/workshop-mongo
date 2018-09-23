package com.home.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.home.workshopmongo.domain.Post;
import com.home.workshopmongo.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostResource {

	@Autowired
	private PostService postService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<?> findAll() {
		List<Post> posts = postService.findAll();
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@ResponseBody
	public ResponseEntity<?> findById(@PathVariable String id) {
		Post post = postService.findById(id);
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
}
