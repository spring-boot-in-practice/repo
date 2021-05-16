package com.manning.sbip.ch06.security;

import com.manning.sbip.ch06.handler.CustomAuthenticationFailureHandler;
import com.manning.sbip.ch06.service.impl.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/adduser", "/login", "/login-error", "/login-verified", "/login-disabled", "/verify/email").permitAll()
                .anyRequest().authenticated().and().formLogin().loginPage("/login").failureHandler(customAuthenticationFailureHandler)
                .and().rememberMe().key("remember-me-key").rememberMeCookieName("course-tracker-remember-me")
                .and().logout().deleteCookies("course-tracker-remember-me");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/images/*", "/css/*");
    }

    @Override
    protected UserDetailsService userDetailsService() {
    	return this.customUserDetailsService;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
