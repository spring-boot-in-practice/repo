package com.manning.sbip.ch06.service;

import com.manning.sbip.ch06.model.User;

public interface UserService {

    User createUser(User user);

    boolean isUsernameExists(String userName);
}
