package com.manning.sbip.ch06.service;

import com.manning.sbip.ch06.model.User;
import com.manning.sbip.ch06.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DefaultUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean isUsernameExists(String userName) {
        return userRepository.existsByUsername(userName);
    }
}
