package com.hms.patient.dtos.treatment.medicine;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        name = "MedicineQueryDto",
        description = "Đối tượng này được sử dụng để truy vấn thuốc."
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicineQueryDto {
    @Schema(
            name = "medicineName",
            description = "Tên thuốc để tìm kiếm",
            example = "paracetamol"
    )
    private String medicineName;

    @Schema(
            name = "medicineType",
            description = "Loại thuốc để tìm kiếm",
            example = "Analgesic"
    )
    private String medicineType;
}