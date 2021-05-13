package com.manning.sbip.ch06.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CT_USERS")
@RequiredArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Firstname can't be empty")
    private String firstName;
    @NotEmpty(message = "Lastname can't be empty")
    private String lastName;
    @NotEmpty(message = "Username can't be empty")
    private String username;
    @NotEmpty(message = "Email can't be empty")
    private String email;
    @NotEmpty(message = "Password can't be empty")
    private String password;
    @NotEmpty(message = "Confirm password can't be empty")
    private String confirmPassword;

    private boolean verified;
    @NotEmpty(message = "Security Pin can't be empty")
    private String securityPin;
    @NotEmpty(message = "Confirm Security Pin can't be empty")
    private String confirmSecurityPin;

    @NotNull
    private boolean totpEnabled;
}
