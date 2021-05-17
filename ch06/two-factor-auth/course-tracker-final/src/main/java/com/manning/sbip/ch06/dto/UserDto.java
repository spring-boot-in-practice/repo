package com.manning.sbip.ch06.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class UserDto {

    @NotEmpty(message="Enter your first name")
    private String firstName;

    @NotEmpty(message="Enter your last name")
    private String lastName;

    @NotEmpty(message="Enter a username")
    private String username;

    @NotEmpty(message="Enter an email")
    @Email(message="Email is not valid")
    private String email;

    @NotEmpty(message="Enter a password")
    private String password;

    @NotEmpty(message="Confirm your password")
    private String confirmPassword;
}
