package com.manning.sbip.ch06.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.manning.sbip.ch06.event.UserRegistrationEvent;
import com.manning.sbip.ch06.model.User;
import com.manning.sbip.ch06.service.GoogleRecaptchaService;
import com.manning.sbip.ch06.service.UserService;

@Controller
public class RegistrationController {

    private UserService userService;
    private final ApplicationEventPublisher eventPublisher;
    private GoogleRecaptchaService captchaService;

    @Autowired
    public RegistrationController(UserService userService, ApplicationEventPublisher eventPublisher, GoogleRecaptchaService captchaService) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
        this.captchaService = captchaService;
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
        
        /* String response = httpRequest.getParameter("g-recaptcha-response");
		if(response == null) {
			return "register";
		}
		
		String ip = httpRequest.getRemoteAddr();
		if(!captchaService.verify(ip, response).isSuccess()) {
			return "error";
		} */
        //user.setVerified(false);
        user.setVerified(true);
        userService.createUser(user);
        //eventPublisher.publishEvent(new UserRegistrationEvent(user));
        return "redirect:adduser?success";
    }
}
