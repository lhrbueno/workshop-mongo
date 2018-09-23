package com.home.workshopmongo.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.home.workshopmongo.domain.User;

@Component
public interface UserService {

	List<User> findAll();
	
	User findById(String id);
	
	User insert(User user);
	
	void delete(String id);
	
	User update(User user);
	
}
