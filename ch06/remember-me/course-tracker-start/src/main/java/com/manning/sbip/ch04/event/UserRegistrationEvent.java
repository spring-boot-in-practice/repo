package com.manning.sbip.ch04.event;

import com.manning.sbip.ch04.model.ApplicationUser;
import org.springframework.context.ApplicationEvent;

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
