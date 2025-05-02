package com.hms.patient.repository;

import com.hms.patient.entity.treatment.MedicalHistory;
import com.hms.patient.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer> {
}
