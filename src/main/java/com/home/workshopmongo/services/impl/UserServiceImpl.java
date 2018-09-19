package com.home.workshopmongo.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.workshopmongo.MessageProperties;
import com.home.workshopmongo.domain.User;
import com.home.workshopmongo.exceptions.UserNotFoundException;
import com.home.workshopmongo.repository.UserRepository;
import com.home.workshopmongo.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageProperties messageProperties;
	
	@Override
	public List<User> findAll() throws UserNotFoundException {
		LOGGER.info("Retrieving all users data from database");
		List<User> users = userRepository.findAll();

		if (users.isEmpty()) {
			throw new UserNotFoundException(messageProperties.getMessage("user.all.error.notFound"));
		}
		
		return users;
	}
	
	@Override
	public User findById(String id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new UserNotFoundException(messageProperties.getMessage("user.single.erro.notFound")));
	}

}
