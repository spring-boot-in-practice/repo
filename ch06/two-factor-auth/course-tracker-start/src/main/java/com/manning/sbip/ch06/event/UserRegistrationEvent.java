package com.manning.sbip.ch06.event;

import org.springframework.context.ApplicationEvent;

import com.manning.sbip.ch06.model.User;

public class UserRegistrationEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = 3044501429709975619L;
	
	private User user;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param user the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public UserRegistrationEvent(User user) {
        super(user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
