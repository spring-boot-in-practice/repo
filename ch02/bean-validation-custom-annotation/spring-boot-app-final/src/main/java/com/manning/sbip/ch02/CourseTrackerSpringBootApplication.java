package com.manning.sbip.ch02;

import com.manning.sbip.ch02.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


@SpringBootApplication
public class CourseTrackerSpringBootApplication implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(CourseTrackerSpringBootApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CourseTrackerSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        User user1 = new User("sbip01", "sbip");
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        logger.error("Password for user1 do not adhere to the password policy");
        violations.forEach(constraintViolation -> logger.error("Violation details: [{}].", constraintViolation.getMessage()));

        User user2 = new User("sbip02", "Sbip01$4UDfg");
        violations = validator.validate(user2);
        if(violations.isEmpty()) {
            logger.info("Password for user2 adhere to the password policy");
        }

        User user3 = new User("sbip03", "Sbip01$4UDfgggg");
        violations = validator.validate(user3);
        logger.error("Password for user3 violates maximum repetitive rule");
        violations.forEach(constraintViolation -> logger.error("Violation details: [{}].", constraintViolation.getMessage()));

        User user4 = new User("sbip04", "Sbip014UDfgggg");
        violations = validator.validate(user4);
        logger.error("Password for user4 violates special character rule");
        violations.forEach(constraintViolation -> logger.error("Violation details: [{}].", constraintViolation.getMessage()));

    }
}