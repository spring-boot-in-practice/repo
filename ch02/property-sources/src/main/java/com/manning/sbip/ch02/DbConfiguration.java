package com.manning.sbip.ch02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:dbConfig.properties")
public class DbConfiguration {
	
	@Autowired
	private Environment env;
	
	@Override
	public String toString() {
		return "User: "+env.getProperty("user") +", Password: "+env.getProperty("password");
	}

}
