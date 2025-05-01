package com.hms.appointment.dtos.schedule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * QueryScheduleDto là lớp DTO (Data Transfer Object) dùng để truyền tải thông tin truy vấn lịch hẹn.
 * Nó chứa các thuộc tính cần thiết để xác định lịch hẹn mà người dùng muốn tìm kiếm.
 */
@Data
@Schema(
        description = "Thông tin truy vấn lịch hẹn",
        title = "QueryScheduleDto"
)
public class QueryScheduleDto {
    @Schema(
            name = "dateExam",
            description = "Ngày khám",
            example = "05-05-2025"
    )
    private String dateExam;
    @Schema(
            name = "startTime",
            description = "Thời gian bắt đầu",
            example = "08:00"
    )
    private String startTime;
    @Schema(
            name = "endTime",
            description = "Thời gian kết thúc",
            example = "17:00"
    )
    private String endTime;
    @Schema(
            name = "shift",
            description = "Ca làm việc",
            example = "MORNING"
    )
    private String shift;
    @Schema(
            name = "typeExam",
            description = "Loại khám",
            example = "EXAMINATION"
    )
    private String typeExam;
}
