package com.hms.patient.service.treatmentService.medicalhistoryService;

import com.hms.patient.dtos.accounts.PatientDto;
import com.hms.patient.dtos.accounts.PatientInfoDto;
import com.hms.patient.dtos.accounts.PatientQueryDto;
import com.hms.patient.dtos.treatment.MedicalHistoryQueryDto;
import com.hms.patient.entity.treatment.MedicalHistory;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface MedicalHistoryServiceInterface {
    /**
     * Lấy medical service theo id.
     * @param medicalHistoryId id medical history
     */
    void getMedicalHistoryById(int medicalHistoryId);

    /**
     * Lấy danh sách medical history thông tin truy vấn.
     * @param medicalHistoryQueryDto thông tin truy vấn medical history
     * @return danh sách có phân trang medical history
     */
    Page<MedicalHistory> listMedicalHistories(MedicalHistoryQueryDto medicalHistoryQueryDto);

    /**
     * Cập nhật thông tin bệnh nhân.
     * @param patientDto thông tin bệnh nhân
     */
    void updatePatient(PatientDto patientDto);
}
