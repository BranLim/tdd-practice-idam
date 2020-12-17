package com.zuhlke.idam.application.command;

public class RegisterUserCommand {
    private final String password;
    private final String username;
    private String email;

    public RegisterUserCommand(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
