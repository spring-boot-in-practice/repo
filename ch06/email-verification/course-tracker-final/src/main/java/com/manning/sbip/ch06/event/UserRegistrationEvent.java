package com.manning.sbip.ch06.event;

import org.springframework.context.ApplicationEvent;

import com.manning.sbip.ch06.model.ApplicationUser;

public class UserRegistrationEvent extends ApplicationEvent {

    private ApplicationUser applicationUser;

    public UserRegistrationEvent(ApplicationUser applicationUser) {
        super(applicationUser);
        this.applicationUser = applicationUser;
    }

    public ApplicationUser getUser() {
        return applicationUser;
    }
}
