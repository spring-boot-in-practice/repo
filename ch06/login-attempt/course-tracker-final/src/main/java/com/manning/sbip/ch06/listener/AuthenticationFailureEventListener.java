package com.manning.sbip.ch06.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Service;

import com.manning.sbip.ch06.service.LoginAttemptService;

@Service
public class AuthenticationFailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {
        String username = (String) authenticationFailureBadCredentialsEvent.getAuthentication().getPrincipal();
        loginAttemptService.loginFailed(username);
    }
}