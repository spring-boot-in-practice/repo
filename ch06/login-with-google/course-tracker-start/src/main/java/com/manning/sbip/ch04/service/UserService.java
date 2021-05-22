package com.manning.sbip.ch04.service;

import com.manning.sbip.ch04.dto.UserDto;
import com.manning.sbip.ch04.model.ApplicationUser;

public interface UserService {
    ApplicationUser createUser(UserDto userDto);
    ApplicationUser findByUsername(String username);
}
