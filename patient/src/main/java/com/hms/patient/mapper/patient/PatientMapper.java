package com.hms.patient.mapper.patient;

import com.hms.patient.constant.Gender;
import com.hms.patient.dtos.accounts.PatientDto;
import com.hms.patient.dtos.accounts.PatientInfoDto;
import com.hms.patient.entity.user.patient.PatientEntity;


public class PatientMapper {
    public static PatientEntity toEntity(PatientDto patientDto) {
        if (patientDto == null) {
            return null;
        }
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setAge(patientDto.getAge());
        patientEntity.setWeight(patientDto.getWeight());
        patientEntity.setHeight(patientDto.getHeight());
        patientEntity.setGender(Gender.valueOf(patientDto.getGender().toUpperCase()));
        return patientEntity;
    }

    public static PatientInfoDto toDto(PatientEntity patientEntity) {
        if (patientEntity == null) {
            return null;
        }
        return null;
    }
}
