package com.manning.sbip.ch10.security

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@EnableWebSecurity
class KotlinSecurityConfiguration : WebSecurityConfigurerAdapter(), ApplicationContextInitializer<GenericApplicationContext> {

    val beans = beans {
        bean("passwordEncoder") {
            BCryptPasswordEncoder()
        }
        bean {
            fun user(user : String, password: String, vararg  roles : String) = User.builder().username(user).password(ref<PasswordEncoder>().encode(password)).roles(*roles).build()
            InMemoryUserDetailsManager(user("user", "password", "USER"), user("admin", "password", "ADMIN"))
        }
    }

    override fun configure(http: HttpSecurity?) {
        http {
            formLogin {
                loginPage = "/login"
                failureUrl = "/login-error"
            }
            authorizeRequests {
                authorize("/login", permitAll)
                authorize("/login-error", permitAll)
                authorize(anyRequest, authenticated)
            }
        }
    }

    override fun initialize(applicationContext: GenericApplicationContext) {
        beans.initialize(applicationContext)
    }
}