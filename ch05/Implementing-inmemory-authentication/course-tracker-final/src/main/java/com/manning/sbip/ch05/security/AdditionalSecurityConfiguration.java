package com.manning.sbip.ch05.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;

//@Configuration
public class AdditionalSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AccessDeniedHandler customAccessDeniedHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/delete/**").hasRole("ADMIN")
				.anyRequest().authenticated().and().formLogin().loginPage("/login").and().exceptionHandling()
				.accessDeniedHandler(customAccessDeniedHandler);

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/images/**", "/css/**", "/h2-console/**");
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {

		UserDetails user = User.withUsername("user").passwordEncoder(passwordEncoder()::encode).password("p@ssw0rd")
				.roles("USER").build();

		UserDetails admin = User.withUsername("admin").passwordEncoder(passwordEncoder()::encode).password("pa$$w0rd")
				.roles("ADMIN").build();

		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

		userDetailsManager.createUser(user);
		userDetailsManager.createUser(admin);

		return userDetailsManager;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
