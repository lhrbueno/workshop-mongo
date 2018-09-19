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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.home.workshopmongo.MessageProperties;
import com.home.workshopmongo.domain.User;
import com.home.workshopmongo.dto.UserDTO;
import com.home.workshopmongo.exceptions.UserNotFoundException;
import com.home.workshopmongo.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	private static Logger LOGGER = LogManager.getLogger(UserResource.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageProperties messageProperties;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<?> findAll() {
		
		try {
			
			List<User> users = userService.findAll();
			List<UserDTO> usersDTO = users.stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
			LOGGER.info(messageProperties.getMessage("user.all.success"));
			return new ResponseEntity<List<UserDTO>>(usersDTO, HttpStatus.OK);
			
		} catch (UserNotFoundException e) {
			
			LOGGER.info(e.getMessage());
			Map<String, String> response = new HashMap<>();
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.ACCEPTED);
			
		} catch (Exception e) {
			
			LOGGER.info(e.getMessage(), e);
			Map<String, String> response = new HashMap<>();
			response.put("message", messageProperties.getMessage("server.status.500"));
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@GetMapping("{id}")
	@ResponseBody
	public ResponseEntity<?> findOne(@PathVariable("id") String id) {
		
		try {
			User user = userService.findById(id);
			LOGGER.info(messageProperties.getMessage("user.single.success"));
			return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
			
		} catch (UserNotFoundException e) {
			
			LOGGER.info(e.getMessage());
			Map<String, String> response = new HashMap<>();
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.ACCEPTED);
			
		} catch (Exception e) {
			
			LOGGER.info(e.getMessage(), e);
			Map<String, String> response = new HashMap<>();
			response.put("message", messageProperties.getMessage("server.status.500"));
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
}
