package com.hms.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.auth.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByPatientId(Integer id);
    
}
