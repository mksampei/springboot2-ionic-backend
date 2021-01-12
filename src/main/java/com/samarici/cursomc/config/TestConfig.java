package com.samarici.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.samarici.cursomc.services.DbService;
import com.samarici.cursomc.services.EmailService;
import com.samarici.cursomc.services.MockEmailService;

@Configuration
@Profile(value = "test")
public class TestConfig {

	@Autowired
	private DbService dbService;

	@Bean
	public boolean instantiateDataBase() throws ParseException {
		dbService.instantiateTestDataBase();
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
