package com.home.workshopmongo.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.home.workshopmongo.domain.User;
import com.home.workshopmongo.dto.UserDTO;
import com.home.workshopmongo.exceptions.UserNotFoundException;
import com.home.workshopmongo.services.UserService;
import com.home.workshopmongo.utils.MessageProperties;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
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
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<?> updateUser(@PathVariable String id,  @RequestBody User user) {
		User userExists = userService.findById(id);
		return null;
	}
	
}
