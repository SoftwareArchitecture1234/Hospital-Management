package com.hms.patient.dtos.schedule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 * RequestScheduleDto là một lớp DTO (Data Transfer Object) dùng để truyền thông tin lịch hẹn giữa các thành phần trong ứng dụng.
 * Nó chứa các thuộc tính như doctorId, patientId, dateExam, startTime, endTime và messageType.
 * Các thuộc tính này được sử dụng để xác định thông tin về bác sĩ, bệnh nhân, ngày giờ khám và loại thông điệp (tạo hoặc hủy lịch hẹn).
 */
@Data
@ToString
@Schema(
        description = "RequestScheduleDto là một lớp DTO (Data Transfer Object) dùng để truyền thông tin lịch hẹn giữa các thành phần trong ứng dụng."
)
@AllArgsConstructor
public class RequestScheduleDto {
    @Schema(
            description = "ID của bác sĩ",
            example = "1"
    )
    int doctorId;
    @Schema(
            description = "ID của bệnh nhân",
            example = "1"
    )
    int patientId;
    @Schema(
            description = "Ngày khám",
            example = "2023-10-01"
    )
    LocalDate dateExam;
    @Schema(
            description = "Thời gian bắt đầu",
            example = "08:00"
    )
    LocalTime startTime;
    @Schema(
            description = "Thời gian kết thúc",
            example = "09:00"
    )
    LocalTime endTime;

    @Schema(
            description = "Loại công việc (EXAMINATION, OPERATION)",
            example = "EXAMINATION"
    )
    String typeOfWork;
    @Schema(
            description = "Loại thông điệp (CREATE, CANCEL)",
            example = "CREATE"
    )
    String messageType; // CREATE, CANCEL

    public static RequestScheduleDto fromString(String message) {
        String[] parts = message.split(",");
        int doctorId = Integer.parseInt(parts[0].split("=")[1]);
        int patientId = Integer.parseInt(parts[1].split("=")[1]);
        LocalDate dateExam = LocalDate.parse(parts[2].split("=")[1]);
        LocalTime startTime = LocalTime.parse(parts[3].split("=")[1]);
        LocalTime endTime = LocalTime.parse(parts[4].split("=")[1]);
        String typeOfWork = parts[5].split("=")[1];
        String messageType = parts[6].split("=")[1];

        return new RequestScheduleDto(doctorId, patientId, dateExam, startTime, endTime, typeOfWork, messageType);
    }
}
