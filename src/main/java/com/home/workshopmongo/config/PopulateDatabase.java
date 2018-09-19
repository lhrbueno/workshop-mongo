package com.home.workshopmongo.config;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.home.workshopmongo.domain.User;
import com.home.workshopmongo.repository.UserRepository;

@Configuration
public class PopulateDatabase implements CommandLineRunner {

	private static Logger LOGGER = LogManager.getLogger(PopulateDatabase.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		LOGGER.info("Deleted users collection from database");
		
		User madara = new User(null, "Uchiha Madara", "madara@uchiha.konoha.co.jp");
		User hashirama = new User(null, "Alex Green", "hashirama@senjuu.konoha.co.jp");
		User d = new User(null, "D.", "d@d.co.jp");
		
		LOGGER.info("Populating users collection with: 'Madara', 'Hashirama' and 'D.'");
		userRepository.saveAll(Arrays.asList(madara, hashirama, d));
		
		
	}

}
