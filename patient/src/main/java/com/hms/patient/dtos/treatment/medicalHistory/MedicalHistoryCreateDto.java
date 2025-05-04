package com.hms.patient.dtos.treatment.medicalHistory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Schema(
        name = "MedicalHistoryCreateDto",
        description = "Đối tượng này đại diện cho thông tin lịch sử khám bệnh trong hệ thống."
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicalHistoryCreateDto {
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
            name = "typeOfTreatment",
            description = "Loại điều trị",
            example = "Outpatient"
    )
    private String typeOfTreatment;

    @Schema(
            name = "disease",
            description = "Tên bệnh",
            example = "Flu"
    )
    private String disease;

    @Schema(
            name = "notes",
            description = "Ghi chú",
            example = "Patient should rest for 3 days"
    )
    private String notes;

    @Schema(
            name = "paymentMethod",
            description = "Phương thức thanh toán",
            example = "cash"
    )
    private String paymentMethod;

    @Schema(
            name = "invoiceId",
            description = "ID của hóa đơn",
            example = "3"
    )
    private String invoiceId;

    @Schema(
            name = "symptomIds",
            description = "Danh sách các triệu chứng"
    )
    private List<Integer> symptomIds;

    @Schema(
            name = "medicineIds",
            description = "Danh sách các thuốc"
    )
    private List<Integer> medicineIds;
}