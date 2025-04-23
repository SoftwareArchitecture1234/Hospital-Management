package com.hms.patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExceptionResourceNotFound.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(
            ExceptionResourceNotFound ex, WebRequest request) {

        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }


}