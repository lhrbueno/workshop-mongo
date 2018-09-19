package com.home.workshopmongo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.workshopmongo.domain.User;
import com.home.workshopmongo.exceptions.UserNotFoundException;
import com.home.workshopmongo.repository.UserRepository;
import com.home.workshopmongo.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository UserRepository;
	
	@Override
	public List<User> findAll() throws UserNotFoundException {
		return UserRepository.findAll();
	}

}
