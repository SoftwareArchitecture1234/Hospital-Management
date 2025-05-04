package com.hms.patient.dtos.treatment.symptom;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        name = "SymptomDto",
        description = "Đối tượng này đại diện cho thông tin triệu chứng trong hệ thống."
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SymptomDto {
    @Schema(
            name = "symptomId",
            description = "ID của triệu chứng",
            example = "1"
    )
    private int symptomId;

    @Schema(
            name = "symptomName",
            description = "Tên triệu chứng",
            example = "Headache"
    )
    private String symptomName;

    @Schema(
            name = "symptomDetails",
            description = "Chi tiết về triệu chứng",
            example = "Severe pain in the forehead area"
    )
    private String symptomDetails;
}