package com.hms.patient.repository;

import com.hms.patient.entity.MedicalHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MedicalHistoryRepo extends JpaRepository<MedicalHistory, Long> {
    @Query("SELECT m FROM MedicalHistory m WHERE m.patient.id = :patientId " +
            "AND (:fromDate IS NULL OR m.date >= :fromDate) " +
            "AND (:toDate IS NULL OR m.date <= :toDate)")
    Page<MedicalHistory> findByPatientIdAndDateRange(
            @Param("patientId") Long patientId,
            @Param("fromDate") Date fromDate,
            @Param("toDate") Date toDate,
            Pageable pageable
    );
}