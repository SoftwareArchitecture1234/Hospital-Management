package com.hms.patient.repository;

import com.hms.patient.dtos.accounts.PatientInfoDto;
import com.hms.patient.entity.user.patient.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PatientRepository extends JpaRepository <PatientEntity, Integer> {
    @Query("SELECT new com.hms.patient.dtos.accounts.PatientInfoDto(" +
            "u.user.name, u.user.email, u.user.phone, u.user.location, " +
            "u.age, u.weight, u.height, u.gender) " +
            "FROM PatientEntity u JOIN u.user " +
            "WHERE u.user.phone = ?1 AND u.user.email = ?2")
    PatientInfoDto findByPhoneAndEmail(String phone, String email);

    @Query("SELECT u FROM PatientEntity u JOIN u.user " +
            "WHERE u.user.name = ?1")
    PatientEntity findByName(String name);
}
