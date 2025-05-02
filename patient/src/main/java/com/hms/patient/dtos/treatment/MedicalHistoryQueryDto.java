package com.hms.patient.dtos.treatment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "ServiceHistoryQueryDto",
        description = "Service history Query DTO"
)

public class MedicalHistoryQueryDto {

    @Schema(
            name = "patientId",
            description = "Patient ID",
            example = "1"
    )
    private int patientId;

    @Schema(
            name = "fromDate",
            description = "Start of date range",
            example = "2024-01-01"
    )
    private String fromDate;

    @Schema(
            name = "toDate",
            description = "End of date range",
            example = "2024-12-22"
    )
    private String toDate;

    @Schema(
            name = "page",
            description = "The current page of data",
            example = "1"
    )
    private int page;

    @Schema(
            name = "limit",
            description = "Limit of records displayed in each page",
            example = "10"
    )
    private int limit;
}
