package com.layhilltech.idam.application.command;

public class RegisterUserCommand {
    private final char[] password;
    private final String username;
    private String email;

    public RegisterUserCommand(String username, char[] password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
