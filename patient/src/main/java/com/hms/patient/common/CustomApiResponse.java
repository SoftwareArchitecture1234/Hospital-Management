package com.hms.patient.common;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CustomApiResponse<T> {
    private boolean status;
    private String message;
    private T data;
    private String path;
    private LocalDateTime timestamp;
}