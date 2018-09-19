package com.home.workshopmongo.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.home.workshopmongo.domain.User;
import com.home.workshopmongo.exceptions.UserNotFoundException;

@Component
public interface UserService {

	List<User> findAll() throws UserNotFoundException;
	
	User findById(String id) throws UserNotFoundException;
	
}
