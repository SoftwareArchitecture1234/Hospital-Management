package com.hms.appointment.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Schema(description = "Exception DTO", name = "ExceptionDto")
public class ExceptionDto {
    @Schema(description = "Thời gian xảy ra lỗi", example = "2023-10-01T12:00:00")
    private final LocalDateTime timestamp;
    @Schema(description = "Mã lỗi", example = "404")
    private final int status;
    @Schema(description = "Tên lỗi", example = "Not Found")
    private final String error;
    @Schema(description = "Thông điệp lỗi", example = "Patient not found")
    private final String message;
    @Schema(description = "Đường dẫn yêu cầu", example = "/api/patient/1")
    private final String path;

    public ExceptionDto(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
