package com.manning.sbip.ch06.security.digest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

//@Configuration
public class DigestSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().antMatcher("/**").addFilter(getDigestAuthFilter())
                .exceptionHandling().authenticationEntryPoint(getDigestEntryPoint())
                .and().authorizeRequests().antMatchers("/**").hasRole("USER");
    }

    private DigestAuthenticationFilter getDigestAuthFilter() throws Exception {
        DigestAuthenticationFilter digestFilter = new DigestAuthenticationFilter();
        digestFilter.setUserDetailsService(userDetailsServiceBean());
        digestFilter.setAuthenticationEntryPoint(getDigestEntryPoint());
        return digestFilter;
    }

    private DigestAuthenticationEntryPoint getDigestEntryPoint() {
        DigestAuthenticationEntryPoint digestEntryPoint = new DigestAuthenticationEntryPoint();
        digestEntryPoint.setRealmName("admin-digest-realm");
        digestEntryPoint.setKey("aacf33KKLLJJ9022*-");
        return digestEntryPoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("password")
                .roles("ADMIN");
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}