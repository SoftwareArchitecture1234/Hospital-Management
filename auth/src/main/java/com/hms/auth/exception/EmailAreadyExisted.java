package com.hms.auth.exception;

public class EmailAreadyExisted extends RuntimeException {
    public EmailAreadyExisted(String message) {
        super(message);
    }
}