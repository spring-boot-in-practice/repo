package com.manning.sbip.ch10.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping


@Controller
class LoginController {

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/login-error")
    fun loginError(model: Model): String {
        model["loginError"] = true
        return "login"
    }
}