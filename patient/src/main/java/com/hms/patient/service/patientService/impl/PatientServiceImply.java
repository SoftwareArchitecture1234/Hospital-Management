package com.hms.patient.service.patientService.impl;

import com.hms.patient.constant.Gender;
import com.hms.patient.constant.RoleType;
import com.hms.patient.dtos.accounts.PatientDto;
import com.hms.patient.dtos.accounts.PatientInfoDto;
import com.hms.patient.dtos.accounts.PatientQueryDto;
import com.hms.patient.entity.role.RoleEntity;
import com.hms.patient.entity.user.UserEntity;
import com.hms.patient.entity.user.patient.PatientEntity;
import com.hms.patient.mapper.patient.PatientMapper;
import com.hms.patient.repository.PatientRepository;
import com.hms.patient.repository.UserRepository;
import com.hms.patient.service.patientService.PatientServiceInterface;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientServiceImply implements PatientServiceInterface {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    @Override
    public void registerPatient(PatientDto patientDto) {
        UserEntity userEntity = createUser(patientDto);

        PatientEntity patientEntity = PatientMapper.toEntity(patientDto);
        if (patientEntity != null) {
            patientEntity.setUser(userEntity);
            patientRepository.save(patientEntity);
        } else {
            throw new IllegalArgumentException("Patient entity cannot be null");
        }
    }

    @Override
    public PatientInfoDto getPatient(PatientQueryDto patientQueryDto) {
        return patientRepository.findByPhoneAndEmail(
                patientQueryDto.getPhone(),
                patientQueryDto.getEmail()
        );
    }

    @Override
    public void updatePatient(PatientDto patientDto) {
        PatientEntity patientEntity = patientRepository.findByName(patientDto.getName());
        if (patientEntity == null) {
            throw new IllegalArgumentException("Patient not found");
        }
        this.updatePatientFields(patientDto, patientEntity);
        patientRepository.save(patientEntity);
    }

    private UserEntity createUser(PatientDto patientDto) {
        UserEntity userEntity = userRepository.findByNameAndPhone(patientDto.getName(), patientDto.getPhone());
        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setEmail(patientDto.getEmail());
            userEntity.setName(patientDto.getName());
            userEntity.setPassword(patientDto.getPassword());
            userEntity.setPhone(patientDto.getPhone());
            userEntity.setLocation(patientDto.getLocation());

            userRepository.save(userEntity);

            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setUserId(userEntity.getUserId());
            roleEntity.setRoleName(RoleType.ROLE_USER);
            userEntity.setRoles(List.of(roleEntity));
        }
        return userEntity;
    }
    private void updatePatientFields(PatientDto patientDto, PatientEntity patientEntity) {

        if (patientDto.getName() != null) {
            patientEntity.getUser().setName(patientDto.getName());
        }
        if (patientDto.getEmail() != null) {
            patientEntity.getUser().setEmail(patientDto.getEmail());
        }
        if (patientDto.getPassword() != null) {
            patientEntity.getUser().setPassword(patientDto.getPassword());
        }
        if (patientDto.getLocation() != null) {
            patientEntity.getUser().setLocation(patientDto.getLocation());
        }
        if (patientDto.getPhone() != null) {
            patientEntity.getUser().setPhone(patientDto.getPhone());
        }
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