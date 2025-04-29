package com.hms.patient.mapper.patient;

import com.hms.patient.constant.Gender;
import com.hms.patient.dtos.accounts.PatientDto;
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

    public static PatientDto toDto(PatientEntity patientEntity) {
        if (patientEntity == null) {
            return null;
        }
        PatientDto patientDto = new PatientDto();
        patientDto.setAge(patientEntity.getAge());
        patientDto.setWeight(patientEntity.getWeight());
        patientDto.setHeight(patientEntity.getHeight());
        patientDto.setGender(patientEntity.getGender().name());

        patientDto.setName(patientEntity.getUser().getName());
        patientDto.setEmail(patientEntity.getUser().getEmail());
        patientDto.setPhone(patientEntity.getUser().getPhone());
        patientDto.setLocation(patientEntity.getUser().getLocation());
        return patientDto;
    }
}
