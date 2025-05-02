package com.hms.patient.dtos.treatment.medicine;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        name = "MedicineDto",
        description = "Đối tượng này đại diện cho thông tin thuốc trong hệ thống."
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicineDto {
    @Schema(
            name = "medicineId",
            description = "ID của thuốc",
            example = "1"
    )
    private int medicineId;

    @Schema(
            name = "medicineName",
            description = "Tên thuốc",
            example = "Paracetamol"
    )
    private String medicineName;

    @Schema(
            name = "medicineType",
            description = "Loại thuốc",
            example = "Analgesic"
    )
    private String medicineType;

    @Schema(
            name = "medicineDetails",
            description = "Chi tiết về thuốc",
            example = "Pain reliever and fever reducer"
    )
    private String medicineDetails;

    @Schema(
            name = "amount",
            description = "Số lượng có sẵn",
            example = "100"
    )
    private Integer amount;
}