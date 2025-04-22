package com.hms.auth.exception;

public class UsernameAlreadyExisted extends RuntimeException {
    public UsernameAlreadyExisted(String message) {
        super(message);
    }
}