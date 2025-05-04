package com.hms.patient.service.patientService.impl;

import com.hms.patient.dtos.accounts.PatientDto;
import com.hms.patient.dtos.accounts.PatientInfoDto;
import com.hms.patient.dtos.accounts.PatientQueryDto;
import com.hms.patient.entity.user.UserEntity;
import com.hms.patient.entity.user.patient.PatientEntity;
import com.hms.patient.exception.ExceptionResourceNotFound;
import com.hms.patient.mapper.patient.PatientMapper;
import com.hms.patient.repository.PatientRepository;
import com.hms.patient.repository.UserRepository;
import com.hms.patient.service.patientService.PatientServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientServiceImply implements PatientServiceInterface {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    @Override
    public void registerPatient(PatientDto patientDto) {
        UserEntity userEntity = getUser(patientDto);
        PatientEntity patientEntity = PatientMapper.toEntity(patientDto);

        if (patientEntity != null) {
            patientEntity.setPatientId(userEntity.getUserId());
            patientRepository.save(patientEntity);
        } else {
            throw new IllegalArgumentException("Patient entity cannot be null");
        }
    }

    @Override
    public PatientInfoDto getPatient(PatientQueryDto patientQueryDto) {
        String email = patientQueryDto.getEmail();
        String phone = patientQueryDto.getPhone();
        int patientId = patientQueryDto.getPatientId();
        if (patientId != 0) {
            return patientRepository.findByUserId(patientId)
                    .orElseThrow(() -> new ExceptionResourceNotFound(
                            "Patient",
                            "patientId",
                            patientId
                    ));
        }
        return patientRepository.findByPhoneAndEmail(phone, email)
                .orElseThrow(() -> new ExceptionResourceNotFound(
                        "Patient",
                        "email or phone",
                        email + " and " + phone
                ));
    }

    @Override
    public void updatePatient(PatientDto patientDto) {
        PatientEntity patientEntity = patientRepository
                .findById(patientDto.getUserId())
                .orElseThrow(() -> new ExceptionResourceNotFound(
                        "Patient",
                        "userId",
                        patientDto.getUserId()
                ));

        this.updatePatientFields(patientDto, patientEntity);
        patientRepository.save(patientEntity);
    }

    private UserEntity getUser(PatientDto patientDto) {
        return userRepository
                .findById(patientDto.getUserId())
                .orElseThrow(() -> new ExceptionResourceNotFound(
                        "User",
                        "userId",
                        patientDto.getUserId()
                ));
    }
    private void updatePatientFields(PatientDto patientDto, PatientEntity patientEntity) {
        if (patientDto.getAge() != 0) {
            patientEntity.setAge(patientDto.getAge());
        }
        if (patientDto.getWeight() != 0) {
            patientEntity.setWeight(patientDto.getWeight());
        }
        if (patientDto.getHeight() != 0) {
            patientEntity.setHeight(patientDto.getHeight());
        }
    }
}