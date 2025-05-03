package com.hms.patient.repository;

import com.hms.patient.dtos.treatment.medicalHistory.MedicalHistoryDto;
import com.hms.patient.entity.treatment.MedicalHistory;
import com.hms.patient.entity.treatment.id.MedicalHistoryId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, MedicalHistoryId> {

    @Query("SELECT new com.hms.patient.dtos.treatment.medicalHistory.MedicalHistoryDto(" +
            "m.patientId, m.doctorId, m.dateModify, m.typeOfTreatment, m.disease, m.notes, m.invoiceId) " +
            "FROM MedicalHistory m " +
            "WHERE m.patientId = :patientId")
    List<MedicalHistoryDto> findByPatientId(@Param("patientId") int patientId);

    @Query("SELECT new com.hms.patient.dtos.treatment.medicalHistory.MedicalHistoryDto(" +
            "m.patientId, m.doctorId, m.dateModify, m.typeOfTreatment, m.disease, m.notes, m.invoiceId) " +
            "FROM MedicalHistory m " +
            "WHERE m.doctorId = :doctorId")
    List<MedicalHistoryDto> findByDoctorId(@Param("doctorId") int doctorId);

    @Query("SELECT new com.hms.patient.dtos.treatment.medicalHistory.MedicalHistoryDto(" +
            "m.patientId, m.doctorId, m.dateModify, m.typeOfTreatment, m.disease, m.notes, m.invoiceId) " +
            "FROM MedicalHistory m " +
            "WHERE m.patientId = :patientId AND m.doctorId = :doctorId")
    List<MedicalHistoryDto> findByPatientIdAndDoctorId(
            @Param("patientId") int patientId,
            @Param("doctorId") int doctorId);

    @Query("SELECT new com.hms.patient.dtos.treatment.medicalHistory.MedicalHistoryDto(" +
            "m.patientId, m.doctorId, m.dateModify, m.typeOfTreatment, m.disease, m.notes, m.invoiceId) " +
            "FROM MedicalHistory m " +
            "WHERE m.patientId = :patientId AND m.doctorId = :doctorId AND m.dateModify = :dateModify")
    Optional<MedicalHistoryDto> findById(
            @Param("patientId") int patientId,
            @Param("doctorId") int doctorId,
            @Param("dateModify") LocalDateTime dateModify);

    @Query("SELECT new com.hms.patient.dtos.treatment.medicalHistory.MedicalHistoryDto(" +
            "m.patientId, m.doctorId, m.dateModify, m.typeOfTreatment, m.disease, m.notes, m.invoiceId) " +
            "FROM MedicalHistory m " +
            "WHERE m.dateModify BETWEEN :fromDate AND :toDate")
    List<MedicalHistoryDto> findByDateModifyBetween(
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate);

    @Query("SELECT new com.hms.patient.dtos.treatment.medicalHistory.MedicalHistoryDto(" +
            "m.patientId, m.doctorId, m.dateModify, m.typeOfTreatment, m.disease, m.notes, m.invoiceId) " +
            "FROM MedicalHistory m " +
            "WHERE (:patientId IS NULL OR m.patientId = :patientId) " +
            "AND (:doctorId IS NULL OR m.doctorId = :doctorId) " +
            "AND ((:fromDate IS NULL OR :toDate IS NULL) OR m.dateModify BETWEEN :fromDate AND :toDate)")
    Page<MedicalHistoryDto> findMedicalHistoriesWithFilters(
            @Param("patientId") Integer patientId,
            @Param("doctorId") Integer doctorId,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate,
            Pageable pageable);
}