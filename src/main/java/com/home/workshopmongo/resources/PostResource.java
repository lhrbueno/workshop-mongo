package com.home.workshopmongo.resources;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.home.workshopmongo.domain.Comment;
import com.home.workshopmongo.domain.Post;
import com.home.workshopmongo.services.PostService;
import com.home.workshopmongo.utils.MessageProperties;
import com.home.workshopmongo.utils.MessageResponse;

@RestController
@RequestMapping("/posts")
public class PostResource {

	@Autowired
	private PostService postService;
	
	@Autowired
	private MessageProperties messageProperties;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<?> findAll() {
		List<Post> posts = postService.findAll();
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@ResponseBody
	public ResponseEntity<?> findOne(@PathVariable String id) {
		Post post = postService.findById(id);
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> addPost(@RequestBody Post post) {
		Post newPost = postService.insert(post);
		return new ResponseEntity<Post>(newPost, HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	@ResponseBody
	public ResponseEntity<?> deletePost(@PathVariable String id) {
		postService.delete(id);
		MessageResponse response = new MessageResponse(HttpStatus.OK.value(), messageProperties.getMessage("post.success.delete"));
		return new ResponseEntity<MessageResponse>(response, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	@ResponseBody
	public ResponseEntity<?> updatePost(@PathVariable String id, @RequestBody Post post) {
		post.setId(id);
		postService.update(post);
		MessageResponse response = new MessageResponse(HttpStatus.OK.value(), messageProperties.getMessage("post.success.update"));
		return new ResponseEntity<MessageResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping("{id}")
	@ResponseBody
	public ResponseEntity<?> addPostComment(@PathVariable String id, @RequestBody Comment comment) {
		comment.setInstant(LocalDateTime.now());
		postService.addPostComment(id, comment);
		MessageResponse response = new MessageResponse(HttpStatus.OK.value(), messageProperties.getMessage("comment.success.add"));
		return new ResponseEntity<MessageResponse>(response, HttpStatus.OK);
	}
}
