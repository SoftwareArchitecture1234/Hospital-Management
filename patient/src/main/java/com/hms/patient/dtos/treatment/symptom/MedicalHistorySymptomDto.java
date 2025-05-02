package com.hms.patient.dtos.treatment.symptom;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(
        name = "MedicalHistorySymptomDto",
        description = "Đối tượng này đại diện cho thông tin liên kết giữa lịch sử khám và triệu chứng."
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicalHistorySymptomDto {
    @Schema(
            name = "patientId",
            description = "ID của bệnh nhân",
            example = "1"
    )
    private int patientId;

    @Schema(
            name = "doctorId",
            description = "ID của bác sĩ",
            example = "2"
    )
    private int doctorId;

    @Schema(
            name = "dateModify",
            description = "Thời gian tạo/cập nhật",
            example = "2023-04-15T10:30:00"
    )
    private LocalDateTime dateModify;

    @Schema(
            name = "symptomId",
            description = "ID của triệu chứng",
            example = "3"
    )
    private int symptomId;
}