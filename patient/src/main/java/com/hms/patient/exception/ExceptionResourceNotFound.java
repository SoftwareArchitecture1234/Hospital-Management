package com.hms.patient.exception;

public class ExceptionResourceNotFound extends RuntimeException {
    public ExceptionResourceNotFound(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}