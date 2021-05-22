package com.manning.sbip.ch04.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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

    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String username, String email, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
