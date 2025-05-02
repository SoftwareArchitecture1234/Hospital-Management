package com.hms.patient.dtos.treatment.symptom;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        name = "SymptomQueryDto",
        description = "Đối tượng này được sử dụng để truy vấn triệu chứng."
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SymptomQueryDto {
    @Schema(
            name = "symptomName",
            description = "Tên triệu chứng để tìm kiếm",
            example = "headache"
    )
    private String symptomName;
}