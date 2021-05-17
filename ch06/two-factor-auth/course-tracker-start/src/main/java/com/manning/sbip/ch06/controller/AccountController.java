package com.manning.sbip.ch06.controller;

import com.manning.sbip.ch06.exception.InvalidVerificationCode;
import com.manning.sbip.ch06.model.CustomUser;
import com.manning.sbip.ch06.model.TotpCode;
import com.manning.sbip.ch06.service.TotpService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final TotpService totpService;

    @GetMapping("/account")
    public String getAccount(Model model, @AuthenticationPrincipal CustomUser customUser) {
        if(customUser != null && !customUser.isTotpEnabled()) {
            model.addAttribute("totpEnabled", customUser.isTotpEnabled());
        }
        else {
            model.addAttribute("totpEnabled", true);
        }
        return "account";
    }

    @GetMapping("/setup-totp")
    public String getGoogleAuthenticatorQrUrl(Model model, @AuthenticationPrincipal CustomUser customUser) {
        String username = customUser.getUsername();
        boolean isTotp = customUser.isTotpEnabled();
        if(!isTotp) {
            model.addAttribute("qrUrl", totpService.generateAuthenticationQrUrl(username));
            model.addAttribute("code", new TotpCode());
        }
        model.addAttribute("totpEnabled", true);
        return "account";
    }

    @PostMapping("/confirm-totp")
    public String confirmGoogleAuthenticatorSetup(Model model, @AuthenticationPrincipal CustomUser customUser, TotpCode totpCode) {
        boolean isTotp = customUser.isTotpEnabled();
        if(!isTotp) {
            totpService.enableTotpForUser(customUser.getUsername(), Integer.valueOf(totpCode.getCode()));
            model.addAttribute("totpEnabled", true);
        }
        customUser.setTotpEnabled(true);
        return "redirect:/logout";
    }

    @ExceptionHandler(InvalidVerificationCode.class)
    public String handleInvalidTOTPVerificationCode(Model model, @AuthenticationPrincipal CustomUser user) {
        boolean userHasTotpEnabled = user.isTotpEnabled();
        model.addAttribute("totpEnabled",userHasTotpEnabled);
        model.addAttribute("confirmError",true);
        return "account";
    }
}
