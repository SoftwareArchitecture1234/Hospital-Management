package com.hms.patient.repository;

import com.hms.patient.dtos.accounts.PatientInfoDto;
import com.hms.patient.entity.user.patient.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PatientRepository extends JpaRepository <PatientEntity, Integer> {
    @Query("SELECT new com.hms.patient.dtos.accounts.PatientInfoDto(" +
        "u.name, u.email, u.phone, u.location, p.age, " +
        "p.weight, p.height, p.gender) " +
        "FROM PatientEntity p " +
        "JOIN UserEntity u " +
        "ON p.patientId = u.userId WHERE u.phone = :phone AND u.email = :email")
    Optional<PatientInfoDto> findByPhoneAndEmail(String phone, String email);

    @Query("SELECT new com.hms.patient.dtos.accounts.PatientInfoDto(" +
            "u.name, u.email, u.phone, u.location, p.age, " +
            "p.weight, p.height, p.gender) " +
            "FROM PatientEntity p " +
            "JOIN UserEntity u " +
            "ON p.patientId = u.userId WHERE p.patientId = :patientId")
    Optional<PatientInfoDto> findByUserId(int patientId);
}
