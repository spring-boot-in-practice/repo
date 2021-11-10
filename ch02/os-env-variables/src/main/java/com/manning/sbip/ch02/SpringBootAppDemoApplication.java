package com.manning.sbip.ch02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SpringBootAppDemoApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootAppDemoApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootAppDemoApplication.class, args);
		Environment env = applicationContext.getBean(Environment.class);
		log.info("Configured application timeout value: "+ env.getProperty("app.timeout"));
	}
}
