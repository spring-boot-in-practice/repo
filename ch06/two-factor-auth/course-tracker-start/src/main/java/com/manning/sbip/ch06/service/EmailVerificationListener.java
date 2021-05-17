package com.manning.sbip.ch06.service;

import com.manning.sbip.ch06.event.UserRegistrationEvent;
import com.manning.sbip.ch06.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationListener implements ApplicationListener<UserRegistrationEvent> {

    private final JavaMailSender mailSender;
    private final VerificationService verificationService;

    @Value("${disable-email-auth:false}")
    private boolean disableEmailAuthentication;

    @Autowired
    public EmailVerificationListener(JavaMailSender mailSender, VerificationService verificationService) {
        this.mailSender = mailSender;
        this.verificationService = verificationService;
    }

    public void onApplicationEvent(UserRegistrationEvent event) {
        if(disableEmailAuthentication) {
            return;
        }
    	User user = event.getUser();
        String username = user.getUsername();
        String verificationId = verificationService.createVerification(username);
        String email = event.getUser().getEmail();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Course Tracker Account Verification");
        //message.setText("Account activation link: http://localhost:8080/verify/email?id="+verificationId);
        message.setText(getText(user, verificationId));
        message.setTo(email);
        mailSender.send(message);
    }
    
    private String getText(User user, String verificationId) {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("Dear ").append(user.getFirstName()).append(" ").append(user.getLastName()).append(",").append(System.lineSeparator());
    	buffer.append("Your account has been successfully created in the Course Tracker application");
    	buffer.append("Active your account by clicking the following link: http://localhost:8080/verify/email?id=").append(verificationId);
    	buffer.append(System.lineSeparator()).append(System.lineSeparator());
    	buffer.append("Regards,").append(System.lineSeparator()).append("Course Tracker Team");
    	return buffer.toString();
    }
}
