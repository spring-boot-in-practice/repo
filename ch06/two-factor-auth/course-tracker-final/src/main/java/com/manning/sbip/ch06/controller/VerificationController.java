package com.manning.sbip.ch06.controller;

import com.manning.sbip.ch06.model.User;
import com.manning.sbip.ch06.repository.UserRepository;
import com.manning.sbip.ch06.service.VerificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VerificationController {

    private final VerificationService verificationService;
    private final UserRepository userRepository;

    @Autowired
    public VerificationController(VerificationService verificationService, UserRepository userRepository) {
        this.verificationService = verificationService;
        this.userRepository = userRepository;
    }

    @GetMapping("/verify/email")
    public String verifyEmail(@RequestParam String id) {
        String username = verificationService.getUsernameForVerificationId(id);
        if(username != null) {
            User user = userRepository.findByUsername(username);
            user.setVerified(true);
            userRepository.save(user);
        }
        return "redirect:/login-verified";
    }
}
