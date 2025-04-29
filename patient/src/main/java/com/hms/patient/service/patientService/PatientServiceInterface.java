package com.hms.patient.service.patientService;

import com.hms.patient.dtos.accounts.PatientDto;
import com.hms.patient.dtos.accounts.PatientInfoDto;
import com.hms.patient.dtos.accounts.PatientQueryDto;

public interface PatientServiceInterface {
    void registerPatient(PatientDto patientDto);
    PatientInfoDto getPatient(PatientQueryDto patientQueryDto);
    void updatePatient(PatientDto patientDto);
}
