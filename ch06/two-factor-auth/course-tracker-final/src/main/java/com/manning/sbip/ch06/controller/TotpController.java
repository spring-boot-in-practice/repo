package com.manning.sbip.ch06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TotpController {

    @GetMapping("/totp-login")
    public String totpLogin(){
        return "totpLogin";
    }


    @GetMapping("/totp-login-error")
    public String totpLoginError(Model model){
        model.addAttribute("loginError", true);
        return "totpLogin";
    }
}
