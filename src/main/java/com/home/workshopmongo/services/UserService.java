package com.home.workshopmongo.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.home.workshopmongo.domain.User;
import com.home.workshopmongo.exceptions.NotFoundException;

@Component
public interface UserService {

	List<User> findAll();
	
	User findById(String id) throws NotFoundException;
	
	User insert(User user);
	
	void delete(String id);
	
	User update(User user) throws NotFoundException;
	
}
