package com.company.web.springdemo.models;

import jakarta.validation.constraints.NotNull;

public class LoginDto {
    @NotNull(message = "Username can't be empty")
    private String username;
    @NotNull(message = "Username can't be empty")
    private String password;

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
