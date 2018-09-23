package com.home.workshopmongo.resources.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.home.workshopmongo.exceptions.UserNotFoundException;
import com.home.workshopmongo.utils.MessageProperties;
import com.home.workshopmongo.utils.MessageResponse;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	private static Logger LOGGER = LogManager.getLogger(ResourceExceptionHandler.class);
	
	@Autowired
	private MessageProperties messageProperties;

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<MessageResponse> userNotFound(UserNotFoundException e) {
		LOGGER.info(e.getMessage());
		HttpStatus status = HttpStatus.NOT_FOUND;
		MessageResponse mr = new MessageResponse(status.value(), e.getMessage());
		return ResponseEntity.status(status).body(mr);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MessageResponse> genericError(Exception e) {
		LOGGER.info(e.getMessage());
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		MessageResponse mr = new MessageResponse(status.value(), messageProperties.getMessage("server.status.500"));
		return ResponseEntity.status(status).body(mr);
	}
	
}
