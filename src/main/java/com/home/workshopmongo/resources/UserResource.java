package com.home.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

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

import com.home.workshopmongo.domain.Post;
import com.home.workshopmongo.domain.User;
import com.home.workshopmongo.dto.UserDTO;
import com.home.workshopmongo.services.UserService;
import com.home.workshopmongo.utils.MessageProperties;
import com.home.workshopmongo.utils.MessageResponse;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageProperties messageProperties;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<?> findAll() {
		List<User> users = userService.findAll();
		List<UserDTO> usersDTO = users.stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
		return new ResponseEntity<List<UserDTO>>(usersDTO, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@ResponseBody
	public ResponseEntity<?> findOne(@PathVariable("id") String id) {
		User user = userService.findById(id);
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> addUser(@RequestBody User user) {
		User newUser = userService.insert(user);
		return new ResponseEntity<UserDTO>(new UserDTO(newUser), HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteUser(@PathVariable String id) {
		userService.delete(id);
		MessageResponse response = new MessageResponse(HttpStatus.OK.value(), messageProperties.getMessage("user.success.delete"));
		return new ResponseEntity<MessageResponse>(response, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	@ResponseBody
	public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User user) {
		user.setId(id);
		userService.update(user);
		MessageResponse response = new MessageResponse(HttpStatus.OK.value(), messageProperties.getMessage("user.success.update"));
		return new ResponseEntity<MessageResponse>(response, HttpStatus.OK);
	}
	
	@GetMapping("{id}/posts")
	@ResponseBody
	public ResponseEntity<?> getUserPosts(@PathVariable String id) {
		User user = userService.findById(id);
		return new ResponseEntity<List<Post>>(user.getPosts(), HttpStatus.OK);
	}
	
}
