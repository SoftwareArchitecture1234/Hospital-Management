package com.hms.patient.repository;

import com.hms.patient.entity.TempPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempPatientRepo extends JpaRepository<TempPatient, Long> {
    // findById method is already provided by JpaRepository
    // Optional<Patient> findById(Long id);
}
