package com.manning.sbip.ch06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.manning.sbip.ch06.model.CustomUser;

@Component
public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        super.additionalAuthenticationChecks(userDetails, authentication);
        AdditionalWebAuthenticationDetails additionalWebAuthenticationDetails = (AdditionalWebAuthenticationDetails) authentication.getDetails();
        CustomUser customUser = (CustomUser) userDetails;
        if(!getPasswordEncoder().matches(additionalWebAuthenticationDetails.getSecurityPin(), customUser.getSecurityPin())) {
            throw new BadCredentialsException("Incorrect Security Pin");
        }
        customUser.setSecurityPin(null);
    }

    @Override
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        super.setPasswordEncoder(passwordEncoder);
    }

    @Override
    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }
}
