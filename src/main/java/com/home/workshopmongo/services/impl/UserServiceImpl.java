package com.home.workshopmongo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.workshopmongo.domain.User;
import com.home.workshopmongo.exceptions.UserNotFoundException;
import com.home.workshopmongo.repository.UserRepository;
import com.home.workshopmongo.services.UserService;
import com.home.workshopmongo.utils.MessageProperties;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageProperties messageProperties;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public User findById(String id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new UserNotFoundException(messageProperties.getMessage("user.error.notFound")));
	}
	
	@Override
	public User insert(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public void delete(String id) {
		User user = findById(id);
		userRepository.delete(user);
	}
	
	@Override
	public User update(User user) throws UserNotFoundException {
		User userRequest = findById(user.getId());
		userRequest.setName(user.getName());
		userRequest.setEmail(user.getEmail());
		return userRepository.save(userRequest);
	}

}
