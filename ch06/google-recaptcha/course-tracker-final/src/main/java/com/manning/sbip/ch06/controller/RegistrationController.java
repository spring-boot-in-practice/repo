package com.manning.sbip.ch06.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.manning.sbip.ch06.dto.RecaptchaDto;
import com.manning.sbip.ch06.dto.UserDto;
import com.manning.sbip.ch06.event.UserRegistrationEvent;
import com.manning.sbip.ch06.model.ApplicationUser;
import com.manning.sbip.ch06.service.UserService;
import com.manning.sbip.ch06.service.impl.GoogleRecaptchaService;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Value("${app.email.verification:N}")
    private String emailVerification;

    @Autowired
    private GoogleRecaptchaService captchaService;

    @GetMapping("/adduser")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "add-user";
    }

    @PostMapping("/adduser")
    public String register(@Valid @ModelAttribute("user") UserDto userDto, HttpServletRequest httpServletRequest, BindingResult result) {
        if(result.hasErrors()) {
            return "add-user";
        }
        String response = httpServletRequest.getParameter("g-recaptcha-response");
        if(response == null) {
            return "add-user";
        }
        String ip = httpServletRequest.getRemoteAddr();
        RecaptchaDto recaptchaDto = captchaService.verify(ip, response);
        if(!recaptchaDto.isSuccess()) {
            return "redirect:adduser?incorrectCaptcha";
        }

        ApplicationUser applicationUser = userService.createUser(userDto);
        if("Y".equalsIgnoreCase(emailVerification)) {
            eventPublisher.publishEvent(new UserRegistrationEvent(applicationUser));
            return "redirect:adduser?validate";
        }
        return "redirect:adduser?success";

    }
}
