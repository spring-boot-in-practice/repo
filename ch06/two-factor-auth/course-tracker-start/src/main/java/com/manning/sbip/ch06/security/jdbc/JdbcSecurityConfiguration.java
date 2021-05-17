package com.manning.sbip.ch06.security.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.manning.sbip.ch06.filter.TotpAuthFilter;
import com.manning.sbip.ch06.service.AdditionalWebAuthenticationDetailsSource;
import com.manning.sbip.ch06.service.DefaultAuthenticationSuccessHandler;
import com.manning.sbip.ch06.service.Oauth2AuthenticationSuccessHandler;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class JdbcSecurityConfiguration extends WebSecurityConfigurerAdapter {

 /*   @Autowired
    private DataSource dataSource;*/

 	@Autowired
 	private TotpAuthFilter totpAuthFilter;
 	
 	@Autowired
 	private UserDetailsService userDetailsService;
 


    @Override
    protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http.addFilterBefore(totpAuthFilter,
		 * UsernamePasswordAuthenticationFilter.class); http.authorizeRequests()
		 * .antMatchers("/adduser", "/login", "/login-error", "/login-verified",
		 * "/verify/email", "/setup-totp", "/confirm-totp").permitAll()
		 * .antMatchers("/totp-login",
		 * "/totp-login-error").hasAuthority("TOTP_AUTH_AUTHORITY")
		 * .anyRequest().hasRole("USER").and() .formLogin().loginPage("/login")
		 * .failureUrl("/login-error") .authenticationDetailsSource(new
		 * AdditionalWebAuthenticationDetailsSource());
		 */
    	
    	http.addFilterBefore(totpAuthFilter, UsernamePasswordAuthenticationFilter.class);
    	http.authorizeRequests()
    	.antMatchers("/adduser", "/login", "/login-error", "/login-verified", "/verify/email", "/setup-totp", "/confirm-totp").permitAll()
		.antMatchers("/totp-login", "/totp-login-error").hasAuthority("TOTP_AUTH_AUTHORITY")
    	.anyRequest().hasRole("USER").and()
    	.formLogin().loginPage("/login")
		.successHandler(new DefaultAuthenticationSuccessHandler()).failureUrl("/login-error")
        .authenticationDetailsSource(new AdditionalWebAuthenticationDetailsSource())
        .and().rememberMe()
        .authenticationSuccessHandler(new DefaultAuthenticationSuccessHandler())
        .and().oauth2Login().loginPage("/login").successHandler(new Oauth2AuthenticationSuccessHandler());
    }
    
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.jdbcAuthentication().dataSource(dataSource)
	 * .usersByUsernameQuery("select username,password,enabled from sbip_users where username = ?"
	 * )
	 * .authoritiesByUsernameQuery("select username,authority from sbip_authorities where username =?"
	 * ); }
	 */
    
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
