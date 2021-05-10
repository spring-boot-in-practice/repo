package com.manning.sbip.ch06.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotEmpty(message="Enter your firstname")
    private String firstName;

    @NotEmpty(message="Enter your lastname")
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
