package com.chris.cmarket.Auth.Exception;

public class EmailAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String email;

    public EmailAlreadyExistException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}