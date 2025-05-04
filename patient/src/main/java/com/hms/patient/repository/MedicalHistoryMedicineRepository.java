package com.hms.patient.repository;

import com.hms.patient.dtos.treatment.medicine.MedicalHistoryMedicineDto;
import com.hms.patient.entity.treatment.medical.MedicalHistoryMedicineEntity;
import com.hms.patient.entity.treatment.id.MedicalHistoryMedicineId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicalHistoryMedicineRepository extends JpaRepository<MedicalHistoryMedicineEntity, MedicalHistoryMedicineId> {

    @Query("SELECT new com.hms.patient.dtos.treatment.medicine.MedicalHistoryMedicineDto(" +
            "m.patientId, m.doctorId, m.dateModify, m.medicineId, " +
            "0, '') " + // Placeholder for quantity and instructions fields
            "FROM MedicalHistoryMedicineEntity m " +
            "WHERE m.patientId = :patientId")
    List<MedicalHistoryMedicineDto> findByPatientId(@Param("patientId") int patientId);

    @Query("SELECT new com.hms.patient.dtos.treatment.medicine.MedicalHistoryMedicineDto(" +
            "m.patientId, m.doctorId, m.dateModify, m.medicineId, " +
            "0, '') " + // Placeholder for quantity and instructions fields
            "FROM MedicalHistoryMedicineEntity m " +
            "WHERE m.doctorId = :doctorId")
    List<MedicalHistoryMedicineDto> findByDoctorId(@Param("doctorId") int doctorId);

    void deleteAllByPatientIdAndDoctorIdAndDateModify(
            int patientId,
            int doctorId,
            LocalDateTime dateModify
    );

    List<MedicalHistoryMedicineEntity> findAllByPatientIdAndDoctorIdAndDateModify(
            int patientId,
            int doctorId,
            LocalDateTime dateModify);
}