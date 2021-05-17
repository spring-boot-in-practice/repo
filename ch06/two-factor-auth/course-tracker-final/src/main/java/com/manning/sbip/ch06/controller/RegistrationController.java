package com.manning.sbip.ch06.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.manning.sbip.ch06.model.User;
import com.manning.sbip.ch06.service.UserService;

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adduser")
    public String register(Model model) {
        model.addAttribute("myUser", new User());
        return "add-user";
    }

    @PostMapping("/adduser")
    public String register(@Valid @ModelAttribute("myUser") User user, BindingResult result, HttpServletRequest httpRequest) {
        if(result.hasErrors()) {
            return "add-user";
        }
        userService.createUser(user);
        return "redirect:adduser?success";
    }
}
