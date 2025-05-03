package com.hms.patient.repository;

import com.hms.patient.dtos.treatment.symptom.MedicalHistorySymptomDto;
import com.hms.patient.entity.treatment.symptom.MedicalHistorySymptom;
import com.hms.patient.entity.treatment.id.MedicalHistorySymptomId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicalHistorySymptomRepository extends JpaRepository<MedicalHistorySymptom, MedicalHistorySymptomId> {

    @Query("SELECT new com.hms.patient.dtos.treatment.symptom.MedicalHistorySymptomDto(" +
            "m.patientId, m.doctorId, m.dateModify, m.symptomId) " +
            "FROM MedicalHistorySymptom m " +
            "WHERE m.patientId = :patientId")
    List<MedicalHistorySymptomDto> findByPatientId(@Param("patientId") int patientId);

    @Query("SELECT new com.hms.patient.dtos.treatment.symptom.MedicalHistorySymptomDto(" +
            "m.patientId, m.doctorId, m.dateModify, m.symptomId) " +
            "FROM MedicalHistorySymptom m " +
            "WHERE m.doctorId = :doctorId")
    List<MedicalHistorySymptomDto> findByDoctorId(@Param("doctorId") int doctorId);

    void deleteAllByPatientIdAndDoctorIdAndDateModify(
            int patientId,
            int doctorId,
            LocalDateTime dateModify
    );

    List<MedicalHistorySymptom> findAllByPatientIdAndDoctorIdAndDateModify(
            int patientId,
            int doctorId,
            LocalDateTime dateModify);
}