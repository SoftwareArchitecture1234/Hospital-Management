package com.hms.patient.service.patientService;

import com.hms.patient.dtos.accounts.PatientDto;
import com.hms.patient.dtos.accounts.PatientInfoDto;
import com.hms.patient.dtos.accounts.PatientQueryDto;

public interface PatientServiceInterface {
    /**
     * Tạo một bệnh nhân mới.
     * @param patientDto thông tin bệnh nhân
     */
    void registerPatient(PatientDto patientDto);

    /**
     * Lấy thông tin bệnh nhân dựa trên thông tin truy vấn.
     * @param patientQueryDto thông tin truy vấn bệnh nhân
     * @return thông tin bệnh nhân
     */
    PatientInfoDto getPatient(PatientQueryDto patientQueryDto);

    /**
     * Cập nhật thông tin bệnh nhân.
     * @param patientDto thông tin bệnh nhân
     */
    void updatePatient(PatientDto patientDto);
}
