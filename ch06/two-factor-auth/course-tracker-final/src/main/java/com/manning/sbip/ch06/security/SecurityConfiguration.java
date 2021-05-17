package com.manning.sbip.ch06.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.manning.sbip.ch06.filter.TotpAuthFilter;
import com.manning.sbip.ch06.service.DefaultAuthenticationSuccessHandler;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

 	@Autowired
 	private TotpAuthFilter totpAuthFilter;
 	
 	@Autowired
 	private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http.addFilterBefore(totpAuthFilter, UsernamePasswordAuthenticationFilter.class);
    	http.authorizeRequests()
    	.antMatchers("/adduser", "/login", "/login-error", "/login-verified", "/verify/email", "/setup-totp", "/confirm-totp").permitAll()
		.antMatchers("/totp-login", "/totp-login-error").hasAuthority("TOTP_AUTH_AUTHORITY")
    	.anyRequest().hasRole("USER").and()
    	.formLogin().loginPage("/login")
		.successHandler(new DefaultAuthenticationSuccessHandler()).failureUrl("/login-error");
    }

    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/h2-console/**", "/css/**", "/images/**");
	}
    
    @Override
    protected UserDetailsService userDetailsService() {
    	return userDetailsService;
    }

}
