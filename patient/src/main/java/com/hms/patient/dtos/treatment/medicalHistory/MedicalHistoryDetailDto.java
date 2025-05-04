package com.hms.patient.dtos.treatment.medicalHistory;

import com.hms.patient.dtos.treatment.medicine.MedicineDto;
import com.hms.patient.dtos.treatment.symptom.SymptomDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Schema(
        name = "MedicalHistoryDetailDto",
        description = "Đối tượng này đại diện cho thông tin chi tiết lịch sử khám bệnh."
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicalHistoryDetailDto {
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
            name = "invoiceId",
            description = "ID của hóa đơn",
            example = "3"
    )
    private String invoiceId;

    @Schema(
            name = "symptoms",
            description = "Danh sách các triệu chứng"
    )
    private List<SymptomDto> symptoms;

    @Schema(
            name = "medicines",
            description = "Danh sách các thuốc"
    )
    private List<MedicineDto> medicines;
}