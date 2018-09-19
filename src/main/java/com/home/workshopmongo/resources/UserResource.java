package com.home.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.home.workshopmongo.domain.User;
import com.home.workshopmongo.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<?> findAll() {
		List<User> users = userService.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
}
