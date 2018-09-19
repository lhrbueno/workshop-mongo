package com.home.workshopmongo.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class MessageProperties {

	@Autowired
	private MessageSource messageSource;
	
	private MessageSourceAccessor accessor;
	
	@PostConstruct
	private void init() {
		accessor = new MessageSourceAccessor(messageSource);
	}
	
	public String getMessage(String messageCode) {
		return accessor.getMessage(messageCode);
	}
	
}
