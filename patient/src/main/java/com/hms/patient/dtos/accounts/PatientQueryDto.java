package com.hms.patient.dtos.accounts;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "PatientQueryDto",
        description = "Patient Query DTO"
)
public class PatientQueryDto {

    @Schema(
            name = "email",
            description = "Email of the patient",
            example = "example@gmail.com"
    )
    private String email;

    @Schema(
            name = "phone",
            description = "Phone number of the patient",
            example = "01234567890"
    )
    private String phone;

    @Schema(
            name = "patientId",
            description = "Patient ID",
            example = "1"
    )
    private int patientId;
}
