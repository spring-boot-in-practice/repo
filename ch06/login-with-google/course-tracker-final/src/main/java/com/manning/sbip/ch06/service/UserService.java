package com.manning.sbip.ch06.service;

import com.manning.sbip.ch06.dto.UserDto;
import com.manning.sbip.ch06.model.ApplicationUser;

public interface UserService {
    ApplicationUser createUser(UserDto userDto);
    ApplicationUser save(ApplicationUser applicationUser);
    ApplicationUser findByUsername(String username);
}
