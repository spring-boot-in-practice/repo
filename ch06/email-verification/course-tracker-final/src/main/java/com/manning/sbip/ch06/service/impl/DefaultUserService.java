package com.manning.sbip.ch06.service.impl;

import com.manning.sbip.ch06.dto.UserDto;
import com.manning.sbip.ch06.model.ApplicationUser;
import com.manning.sbip.ch06.repository.UserRepository;
import com.manning.sbip.ch06.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApplicationUser createUser(UserDto userDto) {
       ApplicationUser applicationUser = new ApplicationUser();
       applicationUser.setFirstName(userDto.getFirstName());
       applicationUser.setLastName(userDto.getLastName());
       applicationUser.setEmail(userDto.getEmail());
       applicationUser.setUserName(userDto.getUsername());
       applicationUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
       applicationUser.setVerified(false);

       return userRepository.save(applicationUser);
    }

    public ApplicationUser save(ApplicationUser applicationUser) {
        return userRepository.save(applicationUser);
    }

    public ApplicationUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
