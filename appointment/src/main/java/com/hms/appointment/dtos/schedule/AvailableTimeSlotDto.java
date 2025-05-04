package com.hms.appointment.dtos.schedule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/*
 * AvailableTimeSlotDto là một lớp DTO (Data Transfer Object) dùng để truyền tải thông tin về các ca làm việc còn trống.
 * Nó chứa các thuộc tính như ID bác sĩ, loại ca làm việc, ngày trong tuần, phòng khám, thời gian bắt đầu và kết thúc,
 * ngày khám và ghi chú.
 * Các thuộc tính này được sử dụng để xác định các ca làm việc còn trống cho bác sĩ trong một khoảng thời gian cụ thể.
 */
@Data
@Getter
@Setter
@Schema(
        name = "AvailableTimeSlotDto",
        description = "Danh sách các ca làm việc còn trống thỏa điều kiện"
)
public class AvailableTimeSlotDto {
    @Schema(
            name = "doctorId",
            description = "ID của bác sĩ",
            example = "1"
    )
    private int doctorId;
    @Schema(
            name = "shiftType",
            description = "Loại ca làm việc",
            example = "CA SÁNG"
    )
    private String shiftType;
    @Schema(
            name = "dateOfWeek",
            description = "Ngày trong tuần",
            example = "THỨ 2"
    )
    private String dateOfWeek;
    @Schema(
            name = "room",
            description = "Phòng khám",
            example = "PHÒNG 101"
    )
    private String room;
    @Schema(
            name = "startTime",
            description = "Thời gian bắt đầu",
            example = "08:00"
    )
    private String startTime;
    @Schema(
            name = "endTime",
            description = "Thời gian kết thúc",
            example = "09:00"
    )
    private String endTime;
    @Schema(
            name = "dateExam",
            description = "Ngày khám",
            example = "2025-05-05"
    )
    private String dateExam;
    @Schema(
            name = "note",
            description = "Ghi chú",
            example = "Ghi chú về ca làm việc"
    )
    private String note;
}
