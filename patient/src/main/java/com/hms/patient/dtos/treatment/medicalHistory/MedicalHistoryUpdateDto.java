package com.hms.patient.dtos.treatment.medicalHistory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(
        name = "MedicalHistoryCreateDto",
        description = "Đối tượng này đại diện cho thông tin lịch sử khám bệnh trong hệ thống."
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicalHistoryUpdateDto extends MedicalHistoryCreateDto{
    @Schema(
            name = "dateModify",
            description = "Thời gian tạo/cập nhật",
            example = "2023-04-15T10:30:00"
    )
    private LocalDateTime dateModify;
}
