package com.manning.sbip.ch06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "loginNew";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "loginNew";
    }

    @GetMapping("/login-verified")
    public String loginVerified(Model model) {
        model.addAttribute("verified", true);
        return "loginNew";
    }
    
    /* @PostMapping("/login")
	public String loginValidate() {
		return "redirect:/index";
	} */
}
